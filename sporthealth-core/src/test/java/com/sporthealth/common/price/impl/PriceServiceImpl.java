package com.sporthealth.common.price.impl;

import com.sporthealth.common.price.Collector;
import com.sporthealth.common.price.PriceService;
import com.sporthealth.common.price.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @program: sporthealth
 * @description: TODO
 * @author: kongdingchao
 * @create: 2019-07-08 21:40
 **/
public class PriceServiceImpl implements PriceService{
    private final static Logger logger = LoggerFactory.getLogger(PriceServiceImpl.class);
    private Collector collector = new CollectorImpl("USDCNY;GBPUSD;EURUSD;USDCNY;GBPUSD;EURUSD;USDCNY;GBPUSD;EURUSD;USDCNY;GBPUSD;EURUSD;USDCNY;GBPUSD;EURUSD;USDCNY;GBPUSD;EURUSD;USDCNY;GBPUSD;EURUSD;");
    private Publisher publisher = null;

    @Override
    public void start() {
        if (null != collector) {
            collector.collect();
        }

        if (null != publisher) {
            publisher.publish();
        }
        logger.info("Start OK!");
    }

    @Override
    public void stop() {
        if (null != collector) {
            collector.stopCollect();
        }

        if (null != publisher) {
            publisher.stopPublish();
        }
        logger.info("Stop OK!");
    }

    public static void main(String[] args) {
        PriceServiceImpl priceService = new PriceServiceImpl();
        priceService.start();
    }
}
