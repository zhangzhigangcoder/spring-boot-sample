package com.jvm.tools.jstack;

/**
 *
 * 演示 TIMED_WAITING
 * @author zhangzhigang
 * @date 2023-01-08 20:21
 */
public class ThreadSleepTest {

    public static void main(String[] args) {
        System.out.println("hello -1");
        try {
            Thread.sleep(1000 * 60 * 10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("hello - 2");
    }
}
