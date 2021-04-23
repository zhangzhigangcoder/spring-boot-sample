package com.core.collection.queue;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @Desc
 * 无界队列，有序
 * 内部通过数组 + 堆实现
 * @Author zhangzhigang
 * @Data 2021-04-03 23:37
 */
public class PriorityQueueTest {
    public static void main(String[] args) {
        PriorityQueue<String> priorityQueue = new PriorityQueue<>(Comparator.reverseOrder());
        priorityQueue.add("orange");
        priorityQueue.add("fig");
        priorityQueue.add("watermelon");
        priorityQueue.add("lemon");
        while (priorityQueue.size() != 0) {
            System.out.println(priorityQueue.remove());
        }
    }
}
