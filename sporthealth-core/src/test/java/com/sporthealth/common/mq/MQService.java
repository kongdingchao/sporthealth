package com.sporthealth.common.mq;

/**
 * @program: sporthealth
 * @description: TODO
 * @author: kongdingchao
 * @create: 2019-07-09 22:37
 **/
public interface MQService {
    void sendData(SendType sendType, MQMessage message);
}
