package com.core.thread;

import java.util.Date;

/**
 * https://www.journaldev.com/1024/java-thread-join-example
 * @author zhangzhigang
 *
 */
public class ThreadJoinTest {
	
	
	public static void main(String[] args) {
		Thread t1 = new Thread(new MyRunable(), "t1");
		Thread t2 = new Thread(new MyRunable(), "t2");
		Thread t3 = new Thread(new MyRunable(), "t3");
		t1.start();
		
		System.out.println("Main Thread: -1 " + new Date());
		
		// start second thread after waiting for 2 seconds or if it's dead
		try {
			// main线程状态为：
			//  join() WAITING
			//	join(long millis) TIMED_WAITING
			t1.join(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Main Thread: -2 " + new Date());
		
		t2.start();
		
		// start third thread only when first thread is dead
		try {
			t1.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Main Thread: -3 " + new Date());
		
		t3.start();
		
		// let all threads finish execution before finishing main thread
		try {
			t1.join();
			System.out.println("Main Thread: -4 " + new Date());
			t2.join();
			System.out.println("Main Thread: -5 " + new Date());
			t3.join();
			System.out.println("Main Thread: -6 " + new Date());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("All threads are dead, exiting main thread");
		
	}

	static class MyRunable implements Runnable {

		@Override
		public void run() {
		System.out.println("Thread started: " + Thread.currentThread().getName() +" " + new Date().getSeconds());
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Thread ended: " + Thread.currentThread().getName() +" " + new Date().getSeconds());
//			while(true) {}
		}

	}
}

