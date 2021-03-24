package com.core.thread.concurrent.tools;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zhangzhigang
 *
 */
public class CountDownTest {
	
	public static void main(String[] args) {
		ExecutorService service = Executors.newCachedThreadPool();
		// 设置 state = 1
		final CountDownLatch cdOrder = new CountDownLatch(1);
		final CountDownLatch cdAnswer = new CountDownLatch(3);
		for (int i = 0; i < 3; i++) {
			service.execute(() -> {
				try {
					System.out.println("thread: " + Thread.currentThread().getName() + " 等待命令");
					// 如果state != 0,当前线程挂起，否则直接跳过
					cdOrder.await();
					System.out.println("thread: " + Thread.currentThread().getName() + " 接收到命令，正在比赛");
					Thread.sleep((long) (Math.random() * 10000));
					cdAnswer.countDown();
					System.out.println("thread: " + Thread.currentThread().getName() + " 比赛结束");
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
		}
		try {
			Thread.sleep((long) (Math.random() * 10000));
			System.out.println("thread: " + Thread.currentThread().getName() + " 准备发送命令");
			// state -1,unpark下一个节点
			cdOrder.countDown();
			System.out.println("thread: " + Thread.currentThread().getName() + " 已发送命令");
			cdAnswer.await();
			System.out.println("thread: " + Thread.currentThread().getName() + " 全部比赛结束");
		} catch (Exception e) {
			e.printStackTrace();
		}
		service.shutdown();
	}
}

