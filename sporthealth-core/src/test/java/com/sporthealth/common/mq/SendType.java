package com.sporthealth.common.mq;

/**
 * @program: sporthealth
 * @description: 发送类型
 * @author: kongdingchao
 * @create: 2019-07-09 22:38
 **/
public enum SendType {
    COMMON,			//普通非阻塞
    COMMON_BLOCK,	//普通阻塞
    ASYN_BLOCK;		//异步阻塞
}
