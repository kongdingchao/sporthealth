package com.sporthealth.monitor.Enum;

/**
 * @program: sporthealth
 * @description: 身份枚举
 * @author: kongdingchao
 * @create: 2019-06-20 22:40
 **/
public enum IdentifyEnum {

    /**
     * 默认空
     */
    NONE,
    /**
     * 日志
     */
    Log;

    public final boolean compareTo(String name) {
        return this.name().equals(name);
    }
}
