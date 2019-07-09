package com.sporthealth.monitor.impl;

import com.sporthealth.monitor.Client;
import com.sporthealth.monitor.ClientFactory;
import com.sporthealth.monitor.Enum.ClientEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @program: sporthealth
 * @description: Monitor工厂(饿汉模式)
 * @author: kongdingchao
 * @create: 2019-06-20 21:26
 **/
public class MonitorClientFactory implements ClientFactory{
    private final static Logger logger = LoggerFactory.getLogger(MonitorClientFactory.class);
    private MonitorClientFactory() {}
    private static final MonitorClientFactory SINGLETON = new MonitorClientFactory();

    static {
        SINGLETON.init();
    }

    public static ClientFactory getSingleton() {
        return SINGLETON;
    }

    private ClassPathXmlApplicationContext context;

    @Override
    public void init() {
        //默认以ClassPathXmlApplicationContext方式初始化
        context = new ClassPathXmlApplicationContext("com.sporthealth.monitor/spring-monitor.xml");
    }

    @Override
    public void destroy() {
        context.destroy();
    }

    @Override
    public Client findClient(String type) {
        if (ClientEnum.Real.compareTo(type)) {
            return (Client) context.getBean("realMonitorClient");
        } else {
            return (Client) context.getBean("intervalMonitorClient");
        }
    }
}
