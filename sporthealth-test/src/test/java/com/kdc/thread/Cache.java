package com.kdc.thread;

import org.apache.commons.collections.CollectionUtils;

import java.util.AbstractMap;
import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @program: sporthealth
 * @description: ReentrantReadWriteLock
 * @author: kongdingchao
 * @create: 2019-05-04 10:57
 **/
public class Cache {
    static HashMap<String, Object> map = new HashMap<String, Object>();
    static ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
    static Lock readLock = reentrantReadWriteLock.readLock();
    static Lock writeLock = reentrantReadWriteLock.writeLock();

    public static final Object get(String key) {
        readLock.lock();
        try{
            return map.get(key);
        } finally {
            readLock.unlock();
        }
    }

    public static final Object put(String key, Object obj) {
        writeLock.lock();
        try{
            return map.put(key, obj);
        } finally {
            writeLock.unlock();
        }
    }

    public static void clear() {
        writeLock.lock();
        try{
            map.clear();
        } finally {
            writeLock.unlock();
        }
    }

    static class ReadHandler implements Runnable {
        @Override
        public void run() {
            String key = String.valueOf(Math.random() % 10);
            System.out.println("ReadHandler key = " + key + ",value = " + Cache.get(key));
        }
    }

    static class WriteHandler implements Runnable {
        @Override
        public void run() {
            String key = String.valueOf(Math.random() % 10);
            Cache.put(key, key);
            System.out.println("WriteHandler key = " + key + ",value = " + key);
        }
    }

    public static void main(String[] args) throws Exception {
        ExecutorService writePool = Executors.newFixedThreadPool(16);
        for (int i = 0; i < 1000; i++) {
            writePool.execute(new WriteHandler());
        }

        ExecutorService readPool = Executors.newFixedThreadPool(16);
        for (int i = 0; i < 1000; i++) {
            readPool.execute(new ReadHandler());
        }

        ConcurrentHashMap<String, Object> map = new ConcurrentHashMap<String, Object>();
        map.put("1", 2);
        TimeUnit.SECONDS.sleep(3);
    }
}
