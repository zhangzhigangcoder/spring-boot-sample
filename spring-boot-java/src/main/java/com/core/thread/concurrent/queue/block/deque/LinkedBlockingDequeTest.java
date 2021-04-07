package com.core.thread.concurrent.queue.block.deque;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @Desc
 *  有界双向阻塞队列
 *  内部通过ReentrantLock + Condition + 双向链表 实现
 *
 * @Author zhangzhigang
 * @Data 2021-04-03 21:19
 */
public class LinkedBlockingDequeTest {

    public static void main(String[] args) throws InterruptedException {
        BlockingDeque<String> deque = new LinkedBlockingDeque<String>(1);
        deque.addFirst("1");
        deque.addLast("2");

        String two = deque.takeLast();
        System.out.println(two);
        String one = deque.takeFirst();
        System.out.println(one);

    }
}
