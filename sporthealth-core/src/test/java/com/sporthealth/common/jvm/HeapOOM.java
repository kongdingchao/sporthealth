package com.sporthealth.common.jvm;

import java.util.LinkedList;
import java.util.List;

/**
 * @program: sporthealth
 * @description: Java堆内存溢出异常测试
 * -Xms20m -Xmx20m -XX:+PrintGCDetails -xx:SurvivorRatio=8
 * -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 * @author: kongdingchao
 * @create: 2019-02-16 21:31
 **/
public class HeapOOM {
    static class OOMObject {
        private double value;//8 bit
    }

    public static void main(String args[]) {
        List<OOMObject> oomObjects = new LinkedList<OOMObject>();//Heap List
        while (true) {
            oomObjects.add(new OOMObject());
        }
    }
}
