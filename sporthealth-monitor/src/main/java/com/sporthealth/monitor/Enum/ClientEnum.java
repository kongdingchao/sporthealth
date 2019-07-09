package com.sporthealth.monitor.Enum;

/**
 * @program: sporthealth
 * @description: 客户端类型
 * @author: kongdingchao
 * @create: 2019-06-21 21:12
 **/
public enum  ClientEnum {
    Real,
    Interval;

    public final boolean compareTo(String name) {
        return this.name().equals(name);
    }
}
