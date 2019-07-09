package com.sporthealth.common.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @program: sporthealth
 * @description: Jdk拦截器代理
 * @author: kongdingchao
 * @create: 2019-06-20 23:32
 **/
public class InterceptorJdkProxy  implements InvocationHandler {
    Object target = null;
    String interceptorClass = null;
    InterceptorJdkProxy(Object target, String interceptorClass) {
        this.target = target;
        this.interceptorClass = interceptorClass;
    }

    public static Object bind(Object target, String interceptorClass) {
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(), new InterceptorJdkProxy(target, interceptorClass));
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (interceptorClass == null) {
            return method.invoke(target, args);
        }
        Object result = null;
        Interceptor interceptor = (Interceptor)Class.forName(interceptorClass).newInstance();
        if (interceptor.before(proxy, target, method, args)) {
            result = method.invoke(target, args);
        } else {
            interceptor.around(proxy, target, method, args);
        }

        interceptor.after(proxy, target, method, args);
        return result;
    }
}
