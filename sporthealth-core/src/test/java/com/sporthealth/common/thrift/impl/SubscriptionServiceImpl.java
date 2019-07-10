package com.sporthealth.common.thrift.impl;

import com.sporthealth.common.mq.MQMessage;
import com.sporthealth.common.mq.MQService;
import com.sporthealth.common.mq.SendType;
import com.sporthealth.common.mq.impl.RabbitMQServiceImpl;
import com.sporthealth.common.mq.impl.SpotRateMessage;
import com.sporthealth.common.price.MySpotRate;
import com.sporthealth.common.thrift.*;
import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @program: sporthealth
 * @description: 订阅服务
 * @author: kongdingchao
 * @create: 2019-07-09 22:23
 **/
public class SubscriptionServiceImpl implements SubscriptionService.Iface {
    private final static Logger logger = LoggerFactory.getLogger(SubscriptionServiceImpl.class);
    private MQService service = RabbitMQServiceImpl.getInstance();
    private Map<String, ReqInfo> reqInfoMap = new ConcurrentHashMap<String, ReqInfo>();
    AtomicBoolean isRunning = new AtomicBoolean(false);
    private Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
            //临时模仿价源接口发价格
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(1);
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
        //处理
        Subscriber subscriber = reqInfo.getSubscriber();
        ReqInfo reqInfoCache = reqInfoMap.get(subscriber.getAppName());
        int nStatus = 0;
        String comment = "subscribe ok";
        logger.info("subscribe...");

        if (reqInfoCache != null) {
            List<Subscription> subscriptions = subscriber.getSubscriptions();
            List<Subscription> oldSubscriptions = reqInfoCache.getSubscriber().getSubscriptions();
            for (Subscription subscription : subscriptions) {
                switch (subscription.subType) {
                    case ADD:
                    {
                        if (!oldSubscriptions.contains(subscription)) {
                            oldSubscriptions.add(subscription);
                        }
                    }break;
                    case DEL:
                    {
                        if (oldSubscriptions.contains(subscription)) {
                            oldSubscriptions.remove(subscription);
                        }
                    }break;
                    case INIT:
                        oldSubscriptions.add(subscription);
                        break;
                    case CANCEL:
                        oldSubscriptions.remove(subscription);
                        break;
                    default:
                        nStatus = 1;
                        comment = "subscribe fail:" + subscription.toString();
                        break;
                }
            }
        }

        //开始推送牌价
        if (!isRunning.get()) {
            isRunning.set(true);
            thread.start();
        }

        //返回
        BaseResult baseResult = new BaseResult();
        baseResult.setId(reqInfo.getId());
        baseResult.setStatus(nStatus);
        baseResult.setComment(comment);

        logger.info("baseResult={}" + baseResult);
        return baseResult;
    }
}
