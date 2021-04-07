package com.core.thread.concurrent.queue.block;

import java.util.Arrays;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 有界阻塞队列
 * 内部实现：ReentrantLock + Condition + 数组
 */
public class ArrayBlockingQueueTest {

    public static void main(String[] args) {

        final BlockingQueue<String> queue = new ArrayBlockingQueue<String>(1);

        for (int i = 0; i < 4; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            String log = queue.take();
                            parseLog(log);
                            if (Arrays.asList("13", "14", "15", "16").contains(log)) {
                                break;
                            }
                        } catch (InterruptedException e) {
                            System.out.println("---:" + Thread.currentThread().getName());
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }

        System.out.println("begin:" + (System.currentTimeMillis() / 1000));

        for (int i = 0; i < 16; i++) {
            final String log = "" + (i + 1);
            try {
                queue.put(log);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("-----");
    }

    public static void parseLog(String log) {
        System.out.println(log + ":" + (System.currentTimeMillis() / 1000));

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
