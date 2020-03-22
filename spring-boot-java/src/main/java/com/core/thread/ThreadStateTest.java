package com.core.thread;

/**
 * @author zhangzhigang
 *
 */
public class ThreadStateTest {
	
	
	public static void main(String[] args) throws Exception {
		Object lock = new Object();
		new Thread(() -> {
			synchronized (lock) {
				try {
					// TIMED_WAITING
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "t1").start();
		
		Thread.sleep(500);
		
		new Thread(() -> {
			// BLOCKED
			synchronized (lock) {
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "t2").start();
		
	}
}
