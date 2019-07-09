package com.sporthealth.common.thread;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;

/**
 * @program: sporthealth
 * @description: TODO
 * @author: kongdingchao
 * @create: 2019-06-29 14:09
 **/
public class ExchangerTest {
    static Exchanger<String> exchanger = new Exchanger<>();

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String A = "银行流水111";
                    exchanger.exchange(A);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    System.out.println(Thread.currentThread().getName() + " finished.");
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String B = "银行流水111";
                    String A = exchanger.exchange(B);
                    if (!A.equals(B)) {
                        System.out.println("流水不同，A:" + A + "\nB:" + B);
                    } else {
                        System.out.println("流水相同，A:" + A + "\nB:" + B);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    System.out.println(Thread.currentThread().getName() + " finished.");
                }
            }
        });

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
    }
}
