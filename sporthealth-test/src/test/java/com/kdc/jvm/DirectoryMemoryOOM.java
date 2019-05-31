package com.kdc.jvm;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * @program: sporthealth
 * @description: 本机直接内存溢出异常测试
 * -Xmx20M -XX:MaxDirectMemorySize=10M -XX:+HeapDumpOnOutOfMemoryError
 * @author: kongdingchao
 * @create: 2019-02-16 21:31
 **/
public class DirectoryMemoryOOM {

    private static final int _1MB = 1024 * 1024; //1Mb

    public static void main(String args[]) throws Throwable{
        Field unsaFefield = Unsafe.class.getDeclaredFields()[0];
        unsaFefield.setAccessible(true);
        Unsafe unsafe = (Unsafe)unsaFefield.get(null);
        unsafe.allocateMemory(_1MB);
        while (true) {
            unsafe.allocateMemory(_1MB);
        }
    }
}
