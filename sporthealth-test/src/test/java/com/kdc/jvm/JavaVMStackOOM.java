package com.kdc.jvm;

import java.util.LinkedList;
import java.util.List;

/**
 * @program: sporthealth
 * @description: Java虚拟机内存和本地方法栈内存溢出异常测试
 * -Xss128k
 * @author: kongdingchao
 * @create: 2019-02-16 21:31
 **/
public class JavaVMStackOOM {
    static class OOMObject {
        private int value = 1;

        public void stackLeak() {
            value += 1;
            stackLeak();
        }

        public int getValue() {
            return value;
        }
    }

    public static void main(String args[]) throws Throwable{
        OOMObject oomObject = new OOMObject();

        try {
            oomObject.stackLeak();
        } catch (Throwable e) {
            System.out.println("value=" + oomObject.getValue());
            throw e;
        }
    }
}
