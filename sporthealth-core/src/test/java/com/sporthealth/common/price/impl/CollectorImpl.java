package com.sporthealth.common.price.impl;

import com.rabbitmq.client.*;
import org.apache.thrift.TDeserializer;
import com.sporthealth.common.price.Collector;
import com.sporthealth.common.price.MySpotRate;
import com.sporthealth.common.thrift.*;
import org.apache.thrift.TException;
import org.apache.thrift.transport.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.thrift.protocol.TBinaryProtocol;

/**
 * @program: sporthealth
 * @description: 采集器
 * @author: kongdingchao
 * @create: 2019-07-08 21:45
 **/
public class CollectorImpl implements Collector {
    private final static Logger logger = LoggerFactory.getLogger(CollectorImpl.class);
    public static LinkedBlockingQueue<MySpotRate> mySpotRateQueue = new LinkedBlockingQueue<MySpotRate>(10000);
    public static Lock lock = new ReentrantLock();
    public static Condition condition = lock.newCondition();
    private static AtomicBoolean connected = new AtomicBoolean(false);
    private ExecutorService pool = Executors.newCachedThreadPool();//Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 1);//io密集型-cpu个数*1

    private Channel amq_channel = null;
    private List<String> symbols = new ArrayList<String>();
    CollectorImpl(String symbols) {
        String[] lt = symbols.split(";");
        for (String s : lt) {
            this.symbols.add(s);
        }
    }

    private static class MQConsumer extends DefaultConsumer {

        public MQConsumer(Channel channel) {
            super(channel);

            logger.info("Create MQConsumer, channel={}", channel);
        }

        @Override
        public void handleDelivery(String consumerTag, Envelope envelope,
                                   AMQP.BasicProperties properties, byte[] body) throws IOException {
            try {
                TDeserializer tds = new TDeserializer();
                SpotRate rate = new SpotRate();

                tds.deserialize(rate, body); // 反序列化，获取通用报文
                logger.info("handleDelivery a rate={}", rate);

                MySpotRate mySpotRate = new MySpotRate();
                mySpotRate.setBid(rate.getBid());
                mySpotRate.setOffer(rate.getOffer());
                mySpotRate.setMid(rate.getMid());
                mySpotRate.setPriceState(MySpotRate.PriceState.Normal);
                try {
                    mySpotRateQueue.put(mySpotRate);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } catch (TException e) {
                logger.error("处理handleDelivery异常", e);
            }
        }
    }

    static class CollectJob implements Runnable {
        private AtomicReference<SubscriptionService.Client> client = new AtomicReference<SubscriptionService.Client>();
        String symbol;
        CollectJob(String symbol) {
            this.symbol = symbol;
        }
        @Override
        public void run() {
            //等待连接
            lock.lock();
            try {
                if (!connected.get()) {
                    condition.await();
                }
            } catch (Exception e) {
                e.printStackTrace();
                return;
            } finally {
                lock.unlock();
            }

            //发起订阅
            logger.info("Request price....symbol = {}", symbol);
            SubscriptionService.Client thriftClient = null;
            if (client.get() == null) {
                TSocket tTransport = new TSocket("127.0.0.1", 8899); // Thrift服务传输通道，ip和端口由QTP提供
                //TTransport transport = new TFramedTransport(tTransport);
                TBinaryProtocol protocol = new TBinaryProtocol(tTransport); // Thrift通信协议
                thriftClient = new SubscriptionService.Client(protocol);
                client.set(thriftClient);
                try {
                    tTransport.open();
                } catch (TTransportException e) {
                    e.printStackTrace();
                }
            } else {
                thriftClient = client.get();
            }
            List<Subscription> subscriptions = new LinkedList<>();
            List<String> feilds = new LinkedList<>();
            feilds.add("Bid");
            feilds.add("Offer");
            subscriptions.add(new Subscription(SubType.INIT, symbol, feilds));
            Subscriber subscriber = new Subscriber();
            subscriber.setAppName("QES");
            subscriber.setSubscriptions(subscriptions);
            ReqInfo reqInfo = new ReqInfo();
            reqInfo.setId(UUID.randomUUID().toString());
            reqInfo.setSubscriber(subscriber);
            try {
                BaseResult result = thriftClient.subscribe(reqInfo);
                if (result.getStatus() == 0) {
                    logger.info("subscriping success!reqInfo={}", reqInfo);

                    //订阅成功，则去消费牌价队列
                    while (connected.get() && !Thread.currentThread().isInterrupted()) {
                        MySpotRate mySpotRate = null;
                        try {
                            mySpotRate = mySpotRateQueue.take();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        logger.debug("Recive a mySpotRate:" + mySpotRate);

                        //处理
                        //handle();
                    }
                } else {
                    logger.error("subscriping fail!reqInfo={}", reqInfo);
                }
            } catch (TException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void collect() {
        logger.info("Start collect!");
        //异步建立连接,接收外部消息形成MQ队列
        new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (!connected.get()) {
                    //创建连接
                    ConnectionFactory factory = new ConnectionFactory();
                    factory.setHost("127.0.0.1");
                    factory.setPort(5672);
                    factory.setUsername("admin");
                    factory.setPassword("admin");
                    try {
                        Connection amq_connection = factory.newConnection(Executors.newSingleThreadExecutor());
                        Channel amq_channel = amq_connection.createChannel();

                        //开始接收
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                MQConsumer myConsumer = new MQConsumer(amq_channel);
                                try {
                                    amq_channel.basicConsume("tc_queue", true, "QE", myConsumer);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }).start();

                        //当前mq连接已创建好，通知多线程订阅
                        connected.set(true);
                        condition.signalAll();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (TimeoutException e) {
                        e.printStackTrace();
                    }
                }
                lock.unlock();

                //临时模仿价源接口发价格

            }
        }).start();

        //发起订阅+处理
        for (String symbol : symbols) {
            pool.execute(new CollectJob(symbol));
        }
    }

    @Override
    public void stopCollect() {
        logger.info("Stop collect!");

        //断掉连接
        connected.set(false);
    }
}
