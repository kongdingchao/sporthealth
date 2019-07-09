package com.sporthealth.common.thread;

import java.util.concurrent.TimeUnit;

/**
 * @program: sporthealth
 * @description: TODO
 * @author: kongdingchao
 * @create: 2019-06-29 14:18
 **/
public class ThreadLocalTest {
    static ThreadLocal<Long> threadLocal = new ThreadLocal<Long>();
    static void begin() {
        long beginTime = System.currentTimeMillis();
        threadLocal.set(beginTime);
    }

    static void end() {
        Long beginTime = threadLocal.get();
        Long endTime = System.currentTimeMillis();
        System.out.println(Thread.currentThread().getName() + " begin time:" + beginTime);
        System.out.println(Thread.currentThread().getName() + " end time:" + endTime);
        System.out.println(Thread.currentThread().getName() + " cost time:"+ (endTime - beginTime));
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    begin();
                    TimeUnit.SECONDS.sleep(2);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    end();
                    System.out.println(Thread.currentThread().getName() + " finished.");
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    begin();
                    TimeUnit.SECONDS.sleep(4);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    end();
                    System.out.println(Thread.currentThread().getName() + " finished.");
                }
            }
        });

        thread1.start();
        thread1.join();
        thread2.start();
        thread2.join();
    }
}
