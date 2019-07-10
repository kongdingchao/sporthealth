package com.sporthealth.common.mq.impl;

import com.sporthealth.common.mq.MQMessage;
import com.sporthealth.common.thrift.SpotRate;
import org.apache.thrift.TException;
import org.apache.thrift.TSerializer;

/**
 * @program: sporthealth
 * @description: TODO
 * @author: kongdingchao
 * @create: 2019-07-09 23:03
 **/
public class SpotRateMessage implements MQMessage {
    private SpotRate mySpotRate = null;
    public SpotRateMessage() {
        mySpotRate = new SpotRate();
    }
    public SpotRateMessage(SpotRate mySpotRate) {
        this.mySpotRate = mySpotRate;
    }

    @Override
    public byte[] serialize(TSerializer serializer) throws TException {
        if (null != mySpotRate) {
            return serializer.serialize(mySpotRate);
        }
        return null;
    }
}
