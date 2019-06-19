package com.sporthealth.common.jvm;

import java.util.ArrayList;

/**
 * @program: sporthealth
 * @description: Java运行时常量池导致的内存溢出异常测试
 * -XX:PermSize=10M -XX:MaxPermSize=10M
 * @author: kongdingchao
 * @create: 2019-02-16 21:31
 **/
public class RuntimeConstantPoolOOM {

    public static void main(String args[]) throws Throwable{
        ArrayList<String> list = new ArrayList<String>();
        int i = 0;
        while (true) {
            list.add(String.valueOf(i++).intern());
        }

    }
}
