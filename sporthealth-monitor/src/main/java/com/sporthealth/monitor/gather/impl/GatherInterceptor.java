package com.sporthealth.monitor.gather.impl;


import com.sporthealth.common.reflect.Interceptor;
import com.sporthealth.common.utils.DateUtils;
import com.sporthealth.monitor.gather.Gather;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NamedThreadLocal;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;

/**
 * @program: sporthealth
 * @description: 采集拦截器
 * @author: kongdingchao
 * @create: 2019-06-20 23:13
 **/
public class GatherInterceptor implements Interceptor {
    private final static Logger logger = LoggerFactory.getLogger(GatherInterceptor.class);
    private static final ThreadLocal<Long> startTimeThreadLocal =
            new NamedThreadLocal<Long>("ThreadLocal StartTime");

    @Override
    public boolean before(Object proxy, Object target, Method method, Object[] args) {
        if (logger.isDebugEnabled()){
            if (target instanceof Gather && method.getName().equals("gather")) {
                long beginTime = System.currentTimeMillis();//1、开始时间
                startTimeThreadLocal.set(beginTime);		//线程绑定变量（该数据只有当前请求的线程可见）
                logger.debug("开始计时: {}  Identify: {} Method:gather", new SimpleDateFormat("hh:mm:ss.SSS")
                        .format(beginTime), ((Gather)target).identify());
            }
        }
        return true;
    }

    @Override
    public void after(Object proxy, Object target, Method method, Object[] args) {
        if (logger.isDebugEnabled()){
            if (target instanceof Gather && method.getName().equals("gather")) {
                long beginTime = startTimeThreadLocal.get();//得到线程绑定的局部变量（开始时间）
                long endTime = System.currentTimeMillis();    //2、结束时间
                logger.debug("计时结束：{}  耗时：{}  Identify: {}  Method:gather 最大内存: {}m  已分配内存: {}m  已分配内存中的剩余空间: {}m  最大可用内存: {}m",
                        new SimpleDateFormat("hh:mm:ss.SSS").format(endTime), DateUtils.formatDateTime(endTime - beginTime),
                        ((Gather) target).identify(), Runtime.getRuntime().maxMemory() / 1024 / 1024, Runtime.getRuntime().totalMemory() / 1024 / 1024, Runtime.getRuntime().freeMemory() / 1024 / 1024,
                        (Runtime.getRuntime().maxMemory() - Runtime.getRuntime().totalMemory() + Runtime.getRuntime().freeMemory()) / 1024 / 1024);
                //删除线程变量中的数据，防止内存泄漏
                startTimeThreadLocal.remove();
            }
        }
    }

    @Override
    public void around(Object proxy, Object target, Method method, Object[] args) {

    }
}
