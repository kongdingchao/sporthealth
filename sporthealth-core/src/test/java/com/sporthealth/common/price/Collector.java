package com.sporthealth.common.price;

/**
 * @program: sporthealth
 * @description: 牌价采集
 * @author: kongdingchao
 * @create: 2019-07-08 21:36
 **/
public interface Collector {
    void collect();
    void stopCollect();
}
