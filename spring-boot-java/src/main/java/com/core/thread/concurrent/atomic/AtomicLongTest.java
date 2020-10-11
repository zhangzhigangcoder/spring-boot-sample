package com.core.thread.concurrent.atomic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;

/**
 * AtomicLong使用
 */
public class AtomicLongTest {

	private static AtomicLong num = new AtomicLong(0);

	public static void main(String[] args) throws InterruptedException {
		int size = 1000;
		CountDownLatch countDownLatch = new CountDownLatch(size);
		for (int i = 0; i < size; i++) {
			new Thread(() -> {
				num.incrementAndGet();
				countDownLatch.countDown();
			}).start();
		}
		countDownLatch.await();
		System.out.println("num = " + num);
	}

}
