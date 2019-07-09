package com.sporthealth.common.thrift.impl;

import com.sporthealth.common.mq.MQMessage;
import com.sporthealth.common.mq.MQService;
import com.sporthealth.common.mq.SendType;
import com.sporthealth.common.mq.impl.RabbitMQServiceImpl;
import com.sporthealth.common.mq.impl.SpotRateMessage;
import com.sporthealth.common.price.MySpotRate;
import com.sporthealth.common.thrift.BaseResult;
import com.sporthealth.common.thrift.ReqInfo;
import com.sporthealth.common.thrift.SpotRate;
import com.sporthealth.common.thrift.SubscriptionService;
import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @program: sporthealth
 * @description: 订阅服务
 * @author: kongdingchao
 * @create: 2019-07-09 22:23
 **/
public class SubscriptionServiceImpl implements SubscriptionService.Iface {
    private final static Logger logger = LoggerFactory.getLogger(SubscriptionServiceImpl.class);
    MQService service = RabbitMQServiceImpl.getInstance();
    private Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
            //临时模仿价源接口发价格
            while (true) {
                try {
                    TimeUnit.MILLISECONDS.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                SpotRate mySpotRate = new SpotRate();
                Random random = new Random();
                double bid = 1.0 + random.nextInt(100) % 100 / 100.0;
                double offer = bid + random.nextInt(100) % 100 / 100.0;
                double mid = (bid + offer) / 2;
                mySpotRate.setBid(bid);
                mySpotRate.setOffer(offer);
                mySpotRate.setMid(mid);

                //给RabbitMQ发送数据
                MQMessage message = new SpotRateMessage(mySpotRate);
                service.sendData(SendType.COMMON, message);
            }
        }
    });
    @Override
    public void ping() throws TException {
        logger.info("ping...");
    }

    @Override
    public BaseResult subscribe(ReqInfo reqInfo) throws TException {
        BaseResult baseResult = new BaseResult();
        baseResult.setId(reqInfo.getId());
        baseResult.setStatus(0);
        baseResult.setComment("subscribe ok");
        logger.info("subscribe...");
        thread.start();
        return baseResult;
    }
}
