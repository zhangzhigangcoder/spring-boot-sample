package com.core.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author zhangzhigang
 *
 */
public class Test3 {
	private static int num = 0;
	private static volatile boolean flag = false;
	private static AtomicLong anum = new AtomicLong(0);


	public static void main(String[] args) throws InterruptedException {
		int size = 1000;
		CountDownLatch countDownLatch = new CountDownLatch(size);

		for (int j = 0; j < size; j++) {
//			for (int i = 0; i < 1000; i++) {
				new Thread(() -> {
					anum.incrementAndGet();
//					num++;
					try {
						Thread.sleep(20);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					countDownLatch.countDown();
				}).start();
//			}

		}
		countDownLatch.await();
		System.out.println("num = " + anum);
	}
}