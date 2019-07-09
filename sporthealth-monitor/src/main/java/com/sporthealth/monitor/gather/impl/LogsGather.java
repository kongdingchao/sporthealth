package com.sporthealth.monitor.gather.impl;

import com.sporthealth.monitor.Enum.IdentifyEnum;
import com.sporthealth.monitor.MonitorRequest;
import com.sporthealth.monitor.MonitorResponse;
import com.sporthealth.monitor.gather.Gather;
import com.sporthealth.monitor.gather.Interval;
import com.sporthealth.monitor.gather.Real;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Observable;

/**
 * @program: sporthealth
 * @description: 日志采集
 * @author: kongdingchao
 * @create: 2019-06-20 21:50
 **/
public class LogsGather implements Gather,Interval,Real {
    private final static Logger logger = LoggerFactory.getLogger(LogsGather.class);
    private long interval = 1;
    @Override
    public void gather(MonitorRequest request) {
        logger.debug("gather info start:request={}", request);
    }

    @Override
    public String identify() {
        return IdentifyEnum.Log.name();
    }

    @Override
    public void update(Observable o, Object arg) {
        gather((MonitorRequest)arg);
    }

    @Override
    public void setInterval(long interval) {
        this.interval = interval;
    }

    @Override
    public long getInterval() {
        return interval;
    }
}
