package com.sporthealth.common.reflect;

import java.lang.reflect.Method;

/**
 * @program: sporthealth
 * @description: TODO
 * @author: kongdingchao
 * @create: 2019-06-20 23:16
 **/
public interface Interceptor {
    public boolean before(Object proxy, Object target, Method method, Object[] args);
    public void after(Object proxy, Object target, Method method, Object[] args);
    public void around(Object proxy, Object target, Method method, Object[] args);
}
