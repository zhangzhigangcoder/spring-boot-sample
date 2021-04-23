package com.core.collection.queue.deque.block;

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
        deque.putLast("1");
        deque.putFirst("2");

        String two = deque.takeFirst();
        System.out.println(two);
        String one = deque.takeLast();
        System.out.println(one);
    }
}
