package com.sporthealth.monitor.impl;

import com.sporthealth.monitor.MonitorRequest;
import com.sporthealth.monitor.gather.Gather;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: sporthealth
 * @description: 实时监控客户端
 * @author: kongdingchao
 * @create: 2019-06-21 20:30
 **/
public class RealMonitorClient extends MonitorClient {
    private final static Logger logger = LoggerFactory.getLogger(RealMonitorClient.class);
    private ExecutorService pool = Executors.newFixedThreadPool(16, new ThreadFactory() {
        private final ThreadFactory defaultFactory = Executors.defaultThreadFactory();
        private final AtomicInteger threadNumber = new AtomicInteger();

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = defaultFactory.newThread(r);
            thread.setName("RealMonitorClient-" + threadNumber.getAndIncrement());
            return thread;
        }
    });
    @Override
    public void start() {
        logger.info("Starting...");

        //接受请求

        //采集
        for (Map.Entry<String, Gather> stringGatherEntry : getGatherMap().entrySet()) {
            //String name = stringGatherEntry.getKey();
            final Gather gather = stringGatherEntry.getValue();
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    MonitorRequest request = null;
                    gather.gather(request);
                }
            });
        }
    }

    @Override
    public void stop() {
        logger.info("Stopped");

    }
}
