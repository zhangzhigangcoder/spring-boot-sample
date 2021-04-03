package com.core.thread.concurrent.tools;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhangzhigang
 *
 */
public class CyclicBarrierTest {
	
	public static void main(String[] args) {
//		ExecutorService service = Executors.newCachedThreadPool();
//		final CyclicBarrier cb = new CyclicBarrier(3);
//		for (int i = 0; i < 3; i++) {
//			service.execute(() -> {
//				try {
//					Thread.sleep((long) (Math.random() * 10000));
//					System.out.println("thread: " + Thread.currentThread().getName() + "到达集合点1，已有" + (cb.getNumberWaiting()+1) + "人到达");
//					cb.await();
//					Thread.sleep((long) (Math.random() * 10000));
//					System.out.println("thread: " + Thread.currentThread().getName() + "到达集合点2，已有" + (cb.getNumberWaiting()+1) + "人到达");
//					cb.await();
//					Thread.sleep((long) (Math.random() * 10000));
//					System.out.println("thread: " + Thread.currentThread().getName() + "到达集合点3，已有" + (cb.getNumberWaiting()+1) + "人到达");
//					cb.await();
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			});
//		}
//		service.shutdown();
//		System.out.println(System.getProperty("profileActive"));

		AtomicInteger atomicInteger = new AtomicInteger();
		int HASH_INCREMENT = 0x61c88647;

		System.out.println(atomicInteger.getAndAdd(HASH_INCREMENT) & 15);
		System.out.println(atomicInteger.getAndAdd(HASH_INCREMENT) & 15);
		System.out.println(atomicInteger.getAndAdd(HASH_INCREMENT) & 15);
		System.out.println(atomicInteger.getAndAdd(HASH_INCREMENT) & 15);

	}
}

