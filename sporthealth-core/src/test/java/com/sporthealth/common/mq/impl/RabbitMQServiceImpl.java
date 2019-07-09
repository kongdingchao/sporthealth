package com.sporthealth.common.mq.impl;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.sporthealth.common.mq.MQMessage;
import com.sporthealth.common.mq.MQService;
import com.sporthealth.common.mq.SendType;
import org.apache.thrift.TException;
import org.apache.thrift.TSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program: sporthealth
 * @description: rabbitmq服务
 * @author: kongdingchao
 * @create: 2019-07-09 22:41
 **/
public class RabbitMQServiceImpl implements MQService {
    private final static Logger logger = LoggerFactory.getLogger(RabbitMQServiceImpl.class);
    private ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 1);//io密集型-cpu个数*1,cpu密集型-cpu个数*2
    private Channel channel;
    private Map<Long, MQMessage> msgCaches = Collections.synchronizedMap(new HashMap<Long, MQMessage>());
    private final SortedSet<Long> confirmSet = Collections.synchronizedSortedSet(new TreeSet<Long>());
    private RabbitMQServiceImpl() {
        //MQ连接QTP
        try {
            logger.info("connectMQ...{}", this);
            ConnectionFactory factory = new ConnectionFactory();
            // 设置自动恢复
            factory.setAutomaticRecoveryEnabled(true);
            //factory.setNetworkRecoveryInterval(2);// 默认5000ms
            factory.setTopologyRecoveryEnabled(false);// 设置不重新声明交换器，队列等信息。

            factory.setHost("127.0.0.1");
            factory.setPort(5672);
            factory.setUsername("admin");
            factory.setPassword("123456");
            //创建单线程池，保证牌价先后顺序
            Connection connection = factory.newConnection(Executors.newSingleThreadExecutor());
            channel = connection.createChannel();

            logger.info("Rabbit mq connect success, RabbitMQServiceImpl={}", this);

        } catch (Exception e) {
            logger.error("处理connectMQ异常", e);
        }
    }

    static MQService service = null;
    public static synchronized MQService getInstance() {
        if (service == null) {
            service = new RabbitMQServiceImpl();
        }
        return service;
    }

    @Override
    public void sendData(SendType sendType, MQMessage message) {
        pool.execute(new Runnable() {
            @Override
            public void run() {
                TSerializer ts = new TSerializer();
                try {
                    if (sendType.equals(SendType.COMMON)) {//普通模式

                        channel.basicPublish("tc_exchange", "tc_queue", null, message.serialize(ts));

                    } else if (sendType.equals(SendType.COMMON_BLOCK)) {//普通阻塞模式
                        channel.basicPublish("tc_exchange", "tc_queue", null, message.serialize(ts));
                        try {
                            if (channel.waitForConfirms()) {
                                logger.info("basicPublish a msg:success!");
                            } else {
                                logger.error("basicPublish msg fail!");
                            }
                        } catch (InterruptedException e1) {
                            e1.printStackTrace();
                        }
                    } else if (sendType.equals(SendType.ASYN_BLOCK)) {//异步阻塞模式

                        channel.addConfirmListener(new ConfirmListener() {
                            public void handleAck(long deliveryTag, boolean multiple)
                                    throws IOException {

                                MQMessage msg = msgCaches.get(deliveryTag);
                                if (msg != null) {
                                    logger.info("ASYN_BLOCK basicPublish a msg success!deliveryTag={}", deliveryTag);
                                    msgCaches.remove(deliveryTag);
                                }

                                if (multiple) {
                                    confirmSet.headSet(deliveryTag + 1).clear();
                                } else {
                                    confirmSet.remove(deliveryTag);
                                }
                            }

                            public void handleNack(long deliveryTag,
                                                   boolean multiple) throws IOException {
                                logger.error("ASYN_BLOCK basicPublish msg fail!deliveryTag={}", deliveryTag);
                                MQMessage msg = msgCaches.get(deliveryTag);
                                if (msg != null) {
                                    //错误返回
                                    logger.info("ASYN_BLOCK basicPublish a msg error!deliveryTag={}", deliveryTag);
                                    msgCaches.remove(deliveryTag);
                                }

                                if (multiple) {
                                    confirmSet.headSet(deliveryTag + 1).clear();
                                } else {
                                    confirmSet.remove(deliveryTag);
                                }
                            }
                        });


                        long nextSeqNo = channel.getNextPublishSeqNo();
                        channel.basicPublish("tc_exchange", "tc_queue", null, message.serialize(ts));
                        logger.info("ASYN_BLOCK basicPublish msg:nextSeqNo={}", nextSeqNo);
                        confirmSet.add(nextSeqNo);
                        msgCaches.put(nextSeqNo, message);

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (TException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
