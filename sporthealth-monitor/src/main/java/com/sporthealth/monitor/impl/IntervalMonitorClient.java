package com.sporthealth.monitor.impl;

import com.sporthealth.monitor.MonitorRequest;
import com.sporthealth.monitor.gather.Gather;
import com.sporthealth.monitor.gather.Interval;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: sporthealth
 * @description: 实时监控客户端
 * @author: kongdingchao
 * @create: 2019-06-21 20:30
 **/
public class IntervalMonitorClient extends MonitorClient {
    private final static Logger logger = LoggerFactory.getLogger(IntervalMonitorClient.class);

    private ScheduledExecutorService pool = Executors.newScheduledThreadPool(16, new ThreadFactory() {
        private final ThreadFactory defaultFactory = Executors.defaultThreadFactory();
        private final AtomicInteger threadNumber = new AtomicInteger();

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = defaultFactory.newThread(r);
            thread.setName("IntervalMonitorClient-" + threadNumber.getAndIncrement());
            return thread;
        }
    });
    @Override
    public void start() {
        logger.info("Starting...");

        //接受请求

        //通知采集器进行采集
        for (Map.Entry<String, Gather> stringGatherEntry : getGatherMap().entrySet()) {
            //String name = stringGatherEntry.getKey();
            final Gather gather = stringGatherEntry.getValue();
            final Interval interval = (Interval) stringGatherEntry.getValue();
            pool.scheduleAtFixedRate(new Runnable() {
                @Override
                public void run() {
                    logger.debug("Do intervalGather....interval={}", interval.getInterval());
                    MonitorRequest request = null;
                    gather.gather(request);
                    /*setChanged();
                    notifyObservers(request);*/
                }
            }, 0, interval.getInterval(), TimeUnit.SECONDS);
        }
    }

    @Override
    public void stop() {
        logger.info("Stopped");

    }
}
