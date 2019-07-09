package com.sporthealth.monitor.impl;

import com.sporthealth.monitor.Client;
import com.sporthealth.monitor.gather.Gather;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: sporthealth
 * @description: 监控客户端
 * @author: kongdingchao
 * @create: 2019-06-20 21:59
 **/
public abstract class MonitorClient implements Client {
    private final static Logger logger = LoggerFactory.getLogger(MonitorClient.class);
    private Map<String, Gather> gatherMap = new HashMap<String, Gather>();

    public Map<String, Gather> getGatherMap() {
        return gatherMap;
    }

    /**
     * @Description: 注册采集
     */
    public void registryGather(Gather gather) {
        gatherMap.put(gather.identify(), gather);
        logger.info("registryGather gather = {}", gather);
    }
    /**
     * @Description: 注册采集
     */
    public void registryGather(String name, Gather gather) {
        gatherMap.put(name, gather);
        logger.info("registryGather name = {} gather = {}", name, gather);
    }
}
