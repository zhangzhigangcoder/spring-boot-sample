package com.core.thread.concurrent.locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhangzhigang
 *
 */
public class ReentrantLockTest {

	public static void main(String[] args) {
		Lock lock = new ReentrantLock();
		lock.lock();
		lock.lock();
		lock.unlock();
//		Outputer output = new Outputer();
//		
//		new Thread(() -> {
//			while(true) {
//				try {
//					Thread.sleep(10);
//					output.output("11111111111111111");
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//			}
//		}).start();
//		
//		new Thread(() -> {
//			while(true) {
//				try {
//					Thread.sleep(10);
//					output.output("2222222222222222");
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//			}
//		}).start();
	}
	
	static class Outputer {
		Lock lock = new ReentrantLock();
		
		public void output(String name) throws InterruptedException {
			System.out.println(name);
			lock.lock();
			try {
//				Thread.sleep(1000);
				for (int i = 0; i < name.length(); i++) {
					System.out.print(name.charAt(i));	
				}
				System.out.println();
			} finally {
				// lock次数  > unlock次数时,只有该线程能通过
				// lock次数  < unlock次数时，会抛异常
				lock.unlock();
			}
		}
	}
}

