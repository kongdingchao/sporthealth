package com.sporthealth.common.thread;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: sporthealth
 * @description:
 *
测试结果（ReeetrantLock）:
SetThread_4:300
exchange thread count:45
SetThread_4:3000
exchange thread count:182
SetThread_4:30000
exchange thread count:701


 * @author: kongdingchao
 * @create: 2019-06-26 21:28
 **/
public class ReentrantLockTest {
    private static ReentrantLock reentrantLock = new ReentrantLock();
    private static Condition rtCondition = reentrantLock.newCondition();
    private static Map<String, Integer> map = new HashMap<String, Integer>();
    private static AtomicInteger id = new AtomicInteger(0);
    private static int exCount = 0;
    private static String lastThreadName = "";

    public static void main(String[] args) throws InterruptedException {
        ExecutorService setPool = Executors.newFixedThreadPool(16, new ThreadFactory() {
            private int number = 1;
            @Override
            public Thread newThread(@NotNull Runnable r) {
                return new Thread(r, "SetThread_" + number++);
            }
        });

        ExecutorService getPool = Executors.newFixedThreadPool(16, new ThreadFactory() {
            @Override
            public Thread newThread(@NotNull Runnable r) {
                return new Thread(r, "GetThread");
            }
        });

        for (int i = 0; i < 30000; i++) {
            setPool.execute(new RTSetMapRunnable());
            //getPool.execute(new SetMapRunnable());
        }

        TimeUnit.SECONDS.sleep(2);
        System.out.println("exchange thread count:" + exCount);

    }

    private static class RTSetMapRunnable implements Runnable {


            @Override
            public void run() {
                reentrantLock.lock();
                try {
                    String name = Thread.currentThread().getName();
                    if (!lastThreadName.equals(name)) {
                        System.out.println("curThreadName:" + name);
                        System.out.println("lastThreadName:" + lastThreadName);
                        exCount++;
                        lastThreadName = name;
                    }
                    map.put(name, id.getAndIncrement());
                    System.out.println(name + ":" + id);
                } catch (Exception e) {

                }finally {
                    reentrantLock.unlock();
                }
            }
    }

    private static class SetMapRunnable implements Runnable {


        @Override
        public void run() {

            synchronized (ReentrantLockTest.class){
                String name = Thread.currentThread().getName();
                if (!lastThreadName.equals(name)) {
                    System.out.println("curThreadName:" + name);
                    System.out.println("lastThreadName:" + lastThreadName);
                    exCount++;
                    lastThreadName = name;
                }
                map.put(name, id.getAndIncrement());
                System.out.println(name + ":" + id);
            }
        }
    }
}
