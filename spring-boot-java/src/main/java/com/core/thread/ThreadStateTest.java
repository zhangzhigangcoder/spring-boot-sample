package com.core.thread;

/**
 * @author zhangzhigang
 *
 */
public class ThreadStateTest {
	
	
	public static void main(String[] args) throws Exception {
		Object lock = new Object();
		Thread t1 = new Thread(() -> {
			synchronized (lock) {
				try {
					// TIMED_WAITING
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "t1");

		t1.start();
		
		Thread.sleep(100);
		
		Thread t2 = new Thread(() -> {
			// BLOCKED
			synchronized (lock) {
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "t2");

		t2.start();

		Thread.sleep(100);

		System.out.println(t2.getState());
	}
}
