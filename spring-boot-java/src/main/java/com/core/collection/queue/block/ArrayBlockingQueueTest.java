package com.core.collection.queue.block;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 有界阻塞队列
 * 内部实现：ReentrantLock + Condition + 数组
 */
@Slf4j
public class ArrayBlockingQueueTest {

    public static void main(String[] args) throws InterruptedException {

        final BlockingQueue<String> queue = new ArrayBlockingQueue<String>(1);

        // 消费
        consume(queue);

        Thread.sleep(1000L);

        // 生产
        produce(queue);

    }

    /**
     * 生产者
     * @param queue
     */
    public static void produce(BlockingQueue<String> queue) {
        for (int i = 0; i < 16; i++) {
            final String data = "" + (i + 1);
            try {
                queue.put(data);
                log.info("put data = {}", data);
            } catch (InterruptedException e) {
                log.error("error = {}", e.getMessage());
            }
        }
        log.info("生产结束");
    }

    /**
     * 消费者
     * @param queue
     */
    public static void consume(BlockingQueue<String> queue) {
        for (int i = 0; i < 4; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            String data = queue.take();
                            log.info(Thread.currentThread().getName() + " take data = {}", data);
                        } catch (InterruptedException e) {
                            log.error("error = {}", e.getMessage());
                        }
                    }
                }
            }).start();
        }
    }

}
