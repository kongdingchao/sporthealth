package com.sporthealth.common.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @program: sporthealth
 * @description: TODO
 * @author: kongdingchao
 * @create: 2019-06-29 13:48
 **/
public class SemaphoreTest {
    static Semaphore semaphore = new Semaphore(3);
    static final int THREAD_COUNT = Runtime.getRuntime().availableProcessors() * 2;
    static ExecutorService threadPool = Executors.newFixedThreadPool(THREAD_COUNT);
    static CountDownLatch countDownLatch = new CountDownLatch(THREAD_COUNT);

    public static void main(String[] args) throws InterruptedException {
        System.out.println("THREAD_COUNT:" + THREAD_COUNT);
        for (int i = 0; i < THREAD_COUNT; i++) {
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        semaphore.acquire();
                        System.out.println(Thread.currentThread().getName() + " run:" + semaphore);
                        semaphore.release();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        countDownLatch.countDown();
                    }
                }
            });



        }
        System.out.println("waiting...");
        countDownLatch.await();
        threadPool.shutdown();
    }
}
