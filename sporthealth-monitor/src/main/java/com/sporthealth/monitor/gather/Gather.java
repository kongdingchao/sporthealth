package com.sporthealth.monitor.gather;

import com.sporthealth.monitor.MonitorRequest;
import com.sporthealth.monitor.MonitorResponse;

import java.util.Observer;

/**
 * @program: sporthealth
 * @description: 采集器
 * @author: kongdingchao
 * @create: 2019-06-20 21:39
 **/
public interface Gather extends Observer{
    /**
     * 收集数据并返回
     *
     * @return 返回给服务器的数据
     */
    void gather(MonitorRequest request);

    /**
     * 获得Gather的唯一表示，用于区分响应服务端的请求
     *
     * @return
     */
    String identify();
}
