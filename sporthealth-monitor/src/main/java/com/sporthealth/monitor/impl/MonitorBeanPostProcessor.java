package com.sporthealth.monitor.impl;

import com.sporthealth.common.reflect.InterceptorJdkProxy;
import com.sporthealth.monitor.Enum.IdentifyEnum;
import com.sporthealth.monitor.gather.Gather;
import com.sporthealth.monitor.gather.Interval;
import com.sporthealth.monitor.gather.Real;
import com.sporthealth.monitor.gather.impl.GatherInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @program: sporthealth
 * @description: 实现MonitorClient的注入功能，以及自动注册Gather的功能
 * @author: kongdingchao
 * @create: 2019-06-20 22:34
 **/
public class MonitorBeanPostProcessor implements BeanPostProcessor {
    private final static Logger logger = LoggerFactory.getLogger(MonitorBeanPostProcessor.class);
    private MonitorClient realMonitorClient;
    private MonitorClient intervalMonitorClient;

    public void setRealMonitorClient(MonitorClient realMonitorClient) {
        this.realMonitorClient = realMonitorClient;
    }

    public void setIntervalMonitorClient(MonitorClient intervalMonitorClient) {
        this.intervalMonitorClient = intervalMonitorClient;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String s) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String s) throws BeansException {

        if (bean instanceof Gather) {
            Gather gather = (Gather)InterceptorJdkProxy.bind(bean, GatherInterceptor.class.getName());
            if (bean instanceof Real) {
                if (IdentifyEnum.NONE.compareTo(gather.identify())){
                    realMonitorClient.registryGather(gather);
                } else {
                    realMonitorClient.registryGather(gather.identify(), gather);
                }
                logger.info("注册实时采集器...size={}", realMonitorClient.getGatherMap().size());
            }
            if (bean instanceof Interval) {
                if (IdentifyEnum.NONE.compareTo(gather.identify())){
                    intervalMonitorClient.registryGather(gather);
                } else {
                    intervalMonitorClient.registryGather(gather.identify(), gather);
                }
                logger.info("注册定时采集器...size={}", intervalMonitorClient.getGatherMap().size());
            }
            return gather;
        }

        return bean;
    }
}
