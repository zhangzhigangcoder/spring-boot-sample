package com.jvm.tools.jstack;

/**
 *
 * 演示线程的同步
 * @author zhangzhigang
 * @date 2023-01-08 20:21
 */
public class ThreadSyncTest {

    public static void main(String[] args) {
        Number number = new Number();
        Thread t1 = new Thread(number);
        Thread t2 = new Thread(number);

        t1.setName("线程1");
        t2.setName("线程2");

        t1.start();
        t2.start();
    }
}

class Number implements Runnable {
    private int number = 1;

    @Override
    public void run() {
        while (true) {
            synchronized (this) {
                if (number <= 1000) {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + ":" + number);
                    number++;
                } else {
                    break;
                }
            }
        }
    }
}
