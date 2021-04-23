package com.core.collection.queue.deque;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Desc
 * @Author zhangzhigang
 * @Data 2021-04-17 18:45
 */
public class ArrayDequeTest {
    public static void main(String[] args) {
        Deque<String> queue = new ArrayDeque<>();
        queue.add("orange");
        queue.add("fig");
        queue.add("watermelon");
        queue.add("lemon");
        while (queue.size() != 0) {
            System.out.println(queue.remove());
        }
    }
}
