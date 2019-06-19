package com.sporthealth.common.thread;

import java.util.concurrent.TimeUnit;

/**
 * @program: sporthealth
 * @description: TODO
 * @author: kongdingchao
 * @create: 2019-04-29 21:06
 **/
public class ThreadState {


    public static void main(String[] args) throws Exception {
        new Thread(new TimeWaiting(), "TimeWatingThread").start();
        new Thread(new Waiting(), "WatingThread").start();
        new Thread(new Blocked(), "BlockedThread1").start();
        new Thread(new Blocked(), "BlockedThread2").start();
    }
    /**
     * [tom@Tom ~]$ jps
     3269 Jps
     3243 ThreadState
     [tom@Tom ~]$ jstack 3243
     2019-04-21 00:04:51
     Full thread dump Java HotSpot(TM) 64-Bit Server VM (25.201-b09 mixed mode):

     "Attach Listener" #14 daemon prio=9 os_prio=0 tid=0x00007fa098001000 nid=0xce3 waiting on condition [0x0000000000000000]
     java.lang.Thread.State: RUNNABLE

     "DestroyJavaVM" #13 prio=5 os_prio=0 tid=0x00007fa0d4009800 nid=0xcac waiting on condition [0x0000000000000000]
     java.lang.Thread.State: RUNNABLE

     "WaitingThread2" #12 prio=5 os_prio=0 tid=0x00007fa0d40db800 nid=0xcbd waiting for monitor entry [0x00007fa0b5567000]
     java.lang.Thread.State: BLOCKED (on object monitor)
     at ThreadState$Blocked.run(ThreadState.java:52)
     - waiting to lock <0x00000000f685fc40> (a java.lang.Class for ThreadState$Blocked)
     at java.lang.Thread.run(Thread.java:748)

     "WaitingThread1" #11 prio=5 os_prio=0 tid=0x00007fa0d40da000 nid=0xcbc waiting on condition [0x00007fa0b5668000]
     java.lang.Thread.State: TIMED_WAITING (sleeping)
     at java.lang.Thread.sleep(Native Method)
     at java.lang.Thread.sleep(Thread.java:340)
     at java.util.concurrent.TimeUnit.sleep(TimeUnit.java:386)
     at ThreadState$Blocked.run(ThreadState.java:52)
     - locked <0x00000000f685fc40> (a java.lang.Class for ThreadState$Blocked)
     at java.lang.Thread.run(Thread.java:748)

     "WatingThread" #10 prio=5 os_prio=0 tid=0x00007fa0d40d8000 nid=0xcbb in Object.wait() [0x00007fa0b5769000]
     java.lang.Thread.State: WAITING (on object monitor)
     at java.lang.Object.wait(Native Method)
     - waiting on <0x00000000f685e588> (a java.lang.Class for ThreadState$Waiting)
     at java.lang.Object.wait(Object.java:502)
     at ThreadState$Waiting.run(ThreadState.java:37)
     - locked <0x00000000f685e588> (a java.lang.Class for ThreadState$Waiting)
     at java.lang.Thread.run(Thread.java:748)

     "TimeWatingThread" #9 prio=5 os_prio=0 tid=0x00007fa0d40d6000 nid=0xcba waiting on condition [0x00007fa0b586a000]
     java.lang.Thread.State: TIMED_WAITING (sleeping)
     at java.lang.Thread.sleep(Native Method)
     at java.lang.Thread.sleep(Thread.java:340)
     at java.util.concurrent.TimeUnit.sleep(TimeUnit.java:386)
     at ThreadState$TimeWaiting.run(ThreadState.java:23)
     at java.lang.Thread.run(Thread.java:748)

     "Service Thread" #8 daemon prio=9 os_prio=0 tid=0x00007fa0d40c0800 nid=0xcb8 runnable [0x0000000000000000]
     java.lang.Thread.State: RUNNABLE

     "C1 CompilerThread2" #7 daemon prio=9 os_prio=0 tid=0x00007fa0d40bd000 nid=0xcb7 waiting on condition [0x0000000000000000]
     java.lang.Thread.State: RUNNABLE

     "C2 CompilerThread1" #6 daemon prio=9 os_prio=0 tid=0x00007fa0d40bb800 nid=0xcb6 waiting on condition [0x0000000000000000]
     java.lang.Thread.State: RUNNABLE

     "C2 CompilerThread0" #5 daemon prio=9 os_prio=0 tid=0x00007fa0d40b8800 nid=0xcb5 waiting on condition [0x0000000000000000]
     java.lang.Thread.State: RUNNABLE

     "Signal Dispatcher" #4 daemon prio=9 os_prio=0 tid=0x00007fa0d40b7000 nid=0xcb4 runnable [0x0000000000000000]
     java.lang.Thread.State: RUNNABLE

     "Finalizer" #3 daemon prio=8 os_prio=0 tid=0x00007fa0d4084000 nid=0xcb3 in Object.wait() [0x00007fa0b5f71000]
     java.lang.Thread.State: WAITING (on object monitor)
     at java.lang.Object.wait(Native Method)
     - waiting on <0x00000000f6808ed0> (a java.lang.ref.ReferenceQueue$Lock)
     at java.lang.ref.ReferenceQueue.remove(ReferenceQueue.java:144)
     - locked <0x00000000f6808ed0> (a java.lang.ref.ReferenceQueue$Lock)
     at java.lang.ref.ReferenceQueue.remove(ReferenceQueue.java:165)
     at java.lang.ref.Finalizer$FinalizerThread.run(Finalizer.java:216)

     "Reference Handler" #2 daemon prio=10 os_prio=0 tid=0x00007fa0d4081000 nid=0xcb2 in Object.wait() [0x00007fa0b6072000]
     java.lang.Thread.State: WAITING (on object monitor)
     at java.lang.Object.wait(Native Method)
     - waiting on <0x00000000f6806bf8> (a java.lang.ref.Reference$Lock)
     at java.lang.Object.wait(Object.java:502)
     at java.lang.ref.Reference.tryHandlePending(Reference.java:191)
     - locked <0x00000000f6806bf8> (a java.lang.ref.Reference$Lock)
     at java.lang.ref.Reference$ReferenceHandler.run(Reference.java:153)

     "VM Thread" os_prio=0 tid=0x00007fa0d4077800 nid=0xcb1 runnable

     "GC task thread#0 (ParallelGC)" os_prio=0 tid=0x00007fa0d401e800 nid=0xcad runnable

     "GC task thread#1 (ParallelGC)" os_prio=0 tid=0x00007fa0d4020800 nid=0xcae runnable

     "GC task thread#2 (ParallelGC)" os_prio=0 tid=0x00007fa0d4022000 nid=0xcaf runnable

     "GC task thread#3 (ParallelGC)" os_prio=0 tid=0x00007fa0d4024000 nid=0xcb0 runnable

     "VM Periodic Task Thread" os_prio=0 tid=0x00007fa0d40c3000 nid=0xcb9 waiting on condition

     JNI global references: 5
     */
    static class TimeWaiting implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Waiting implements Runnable {
        @Override
        public void run() {
            while (true) {
                synchronized(Waiting.class) {
                    try {
                        Waiting.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    static class Blocked implements Runnable {
        @Override
        public void run() {
            synchronized(Blocked.class) {
                while (true) {
                    try {
                        TimeUnit.SECONDS.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
