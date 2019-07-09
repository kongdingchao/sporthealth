package com.sporthealth.common.price.impl;

import com.sporthealth.common.price.Collector;
import com.sporthealth.common.price.MySpotRate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * @program: sporthealth
 * @description: TODO
 * @author: kongdingchao
 * @create: 2019-07-08 21:45
 **/
public class CollectorImpl implements Collector {
    private final static Logger logger = LoggerFactory.getLogger(CollectorImpl.class);
    public static LinkedBlockingQueue<MySpotRate> msgQueue = new LinkedBlockingQueue<MySpotRate>(10000);
    public static Lock lock = new ReentrantLock();
    public static Condition condition = lock.newCondition();
    private static AtomicBoolean connected = new AtomicBoolean(false);
    private ExecutorService pool = Executors.newCachedThreadPool();//Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 1);//io密集型-cpu个数*1

    private List<String> symbols = null;
    CollectorImpl(String symbols) {
        String[] lt = symbols.split(";");
        this.symbols = new ArrayList<String>(lt.length);
        for (String s : lt) {
            this.symbols.add(s);
        }
    }
    static class CollectJob implements Runnable {
        String symbol;
        CollectJob(String symbol) {
            this.symbol = symbol;
        }
        @Override
        public void run() {
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

            //接受接口消息
            while (connected.get() && !Thread.currentThread().isInterrupted()) {
                MySpotRate msg = null;
                try {
                    msg = msgQueue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                logger.debug("Recive a msg:" + msg);

                //处理
                //handle();
            }

        }
    }

    @Override
    public void collect() {
        logger.info("Start collect!");
        //建立连接--异步,接收外部消息
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
                    connected.set(true);
                    condition.signalAll();
                }
                lock.unlock();

                //临时模仿价源接口发价格
                while (true) {
                    try {
                        TimeUnit.MILLISECONDS.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    MySpotRate mySpotRate = new MySpotRate();
                    Random random = new Random();
                    double bid = 1.0 + random.nextInt(100) % 100 / 100.0;
                    double offer = bid + random.nextInt(100) % 100 / 100.0;
                    double mid = (bid + offer) / 2;
                    mySpotRate.setBid(bid);
                    mySpotRate.setOffer(offer);
                    mySpotRate.setMid(mid);
                    mySpotRate.setPriceState(MySpotRate.PriceState.Normal);
                    try {
                        msgQueue.put(mySpotRate);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
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
