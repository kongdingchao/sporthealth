package com.sporthealth.monitor;

import com.sporthealth.monitor.gather.Gather;

/**
 * @program: sporthealth
 * @description: 监控客户端
 * @author: kongdingchao
 * @create: 2019-06-20 21:32
 **/
public interface Client {
    /**
     * 开启客户端
     */
    public void start();
    /**
     * 关闭客户端
     */
    public void stop();
}
