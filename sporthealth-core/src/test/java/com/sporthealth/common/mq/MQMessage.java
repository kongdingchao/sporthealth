package com.sporthealth.common.mq;

import org.apache.thrift.TException;
import org.apache.thrift.TSerializer;

/**
 * @program: sporthealth
 * @description: TODO
 * @author: kongdingchao
 * @create: 2019-07-09 22:39
 **/
public interface MQMessage {
    /**
    * @Description: 序列化数据
    */
    byte[] serialize(TSerializer serializer) throws TException;
}
