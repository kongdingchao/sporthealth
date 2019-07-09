package com.sporthealth.monitor.service;

import com.sporthealth.monitor.Message;

/**
 * @program: sporthealth
 * @description: TODO
 * @author: kongdingchao
 * @create: 2019-06-22 12:24
 **/
public interface RabbitMQService {
    /**
     * @Description: 初始服务类:开始接收数据
     * @return void
     */
    public void init();
    /**
     * @Description: 销毁服务类:关闭接收数据
     * @return void
     */
    public void destroy();

    /**
     * 发送数据,msg-消息，isBlock-是否阻塞
     */
    public void sendData(Message msg, boolean isBlock);
}
