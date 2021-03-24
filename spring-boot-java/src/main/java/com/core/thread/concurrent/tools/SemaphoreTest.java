package com.core.thread.concurrent.tools;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 锁池的概念，AQS实现，没有可用锁时，线程挂起(park)
 * @author zhangzhigang
 *
 */
public class SemaphoreTest {
	
	public static void main(String[] args) {
		ExecutorService service = Executors.newCachedThreadPool();
		Semaphore sp = new Semaphore(3);
		for (int i = 0; i < 10; i++) {
			service.execute(() -> {
				try {
					sp.acquire();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("thread: " + Thread.currentThread().getName() + ",已使用" + (3-sp.availablePermits()) + "个");
				try {
					Thread.sleep((long) (Math.random() * 10000));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("thread: " + Thread.currentThread().getName() + " 准备释放锁");
				sp.release();
				System.out.println("thread: " + Thread.currentThread().getName() + ",已使用" + (3-sp.availablePermits()) + "个");
			});
		}
		service.shutdown();
	}
}

