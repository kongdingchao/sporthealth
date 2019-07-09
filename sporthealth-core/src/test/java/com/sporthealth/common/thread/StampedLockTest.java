package com.sporthealth.common.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.StampedLock;

/**
 * @program: sporthealth
 * @description: TODO
 * @author: kongdingchao
 * @create: 2019-06-26 22:29
 **/

public class StampedLockTest {
    private int balance;
    private StampedLock lock = new StampedLock();

    private static AtomicInteger id = new AtomicInteger(1);
    private static StampedLockTest test = new StampedLockTest();
    private static ExecutorService writePool = Executors.newFixedThreadPool(16);
    private static ExecutorService readPool = Executors.newFixedThreadPool(16);

    public static void main(String[] args) {

        for (int i = 0; i < 1000; i++) {
            writePool.execute(new Runnable() {
                @Override
                public void run() {
                    //test.write(id.getAndIncrement());
                    //Thread.yield();
                    test.conditionReadWrite(id.getAndIncrement());
                }
            });
        }

        for (int i = 0; i < 4000; i++) {
            readPool.execute(new Runnable() {
                @Override
                public void run() {
                    //test.read();
                    //Thread.yield();
                    test.optimisticRead();
                }
            });
        }



    }

    public void conditionReadWrite (int value) {
// 首先判断balance的值是否符合更新的条件
        long stamp = lock.readLock();
        while (balance > 0) {
            long writeStamp = lock.tryConvertToWriteLock(stamp);
            if(writeStamp != 0) { // 成功转换成为写锁
                stamp = writeStamp;
                balance += value;
                break;
            } else {
// 没有转换成写锁，这里需要首先释放读锁，然后再拿到写锁
                lock.unlockRead(stamp);
// 获取写锁
                stamp = lock.writeLock();
            }
        }
        System.out.println("write:" + balance);
        lock.unlock(stamp);
    }
    public void optimisticRead() {
        long stamp = lock.tryOptimisticRead();
        int c = balance;
// 这里可能会出现了写操作，因此要进行判断
        if(!lock.validate(stamp)) {
// 要从新读取
            long readStamp = lock.readLock();
            c = balance;
            stamp = readStamp;
        }
///
        System.out.println("read:" + c);
        lock.unlockRead(stamp);
    }
    public void read () {
        long stamp = lock.readLock();
        lock.tryOptimisticRead();
        int c = balance;
// ...
        System.out.println("read:" + c);
        lock.unlockRead(stamp);
    }
    public void write(int value) {
        long stamp = lock.writeLock();
        balance += value;
        System.out.println("write:" + value);
        lock.unlockWrite(stamp);
    }

}
