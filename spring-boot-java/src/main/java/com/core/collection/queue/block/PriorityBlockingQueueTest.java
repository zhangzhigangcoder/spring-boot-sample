package com.core.collection.queue.block;

import lombok.extern.slf4j.Slf4j;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * 无界阻塞优先级队列
 * 内部实现：ReentrantLock + Condition + 数组
 */
@Slf4j
public class PriorityBlockingQueueTest {

    public static void main(String[] args) {

        final BlockingQueue<String> queue = new PriorityBlockingQueue<>(10, Comparator.reverseOrder());

        // 消费
        consume(queue);

        // 生产
        produce(queue);
    }

    /**
     * 生产者
     * @param queue
     */
    public static void produce(BlockingQueue<String> queue) {
        queue.add("orange");
        queue.add("fig");
        queue.add("watermelon");
        queue.add("lemon");
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
                            log.info("take data = {}", data);
                        } catch (InterruptedException e) {
                            log.error("error = {}", e.getMessage());
                        }
                    }
                }
            }).start();
        }
    }

}
