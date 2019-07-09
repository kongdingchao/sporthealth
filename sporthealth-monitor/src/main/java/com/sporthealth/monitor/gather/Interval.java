package com.sporthealth.monitor.gather;

import com.sporthealth.monitor.MonitorRequest;
import com.sporthealth.monitor.MonitorResponse;

/**
 * @program: sporthealth
 * @description: 处理定时
 * @author: kongdingchao
 * @create: 2019-06-21 21:31
 **/
public interface Interval {
    public void setInterval(long interval);
    public long getInterval();
}
