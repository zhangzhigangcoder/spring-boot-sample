package com.core.thread.concurrent.locks;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionTest {

	public static void main(String[] args) throws InterruptedException {
//		Lock lock = new ReentrantLock();
//		Condition condition = lock.newCondition();
//		try {
//			lock.lock();
//			condition.await();
//		} finally {
//			lock.unlock();
//		}
		
		
		
		
		

		final Business business = new Business();
		new Thread(new Runnable() {

			@Override
			public void run() {

				for (int i = 1; i <= 50; i++) {
					business.sub(i);
				}
			}
		}).start();

		for (int i = 1; i <= 50; i++) {
			business.main(i);
		}

	}

	static class Business {
		private static final Lock lock = new ReentrantLock();
		private static final Condition condition = lock.newCondition();
		private boolean bShouldSub = true;

		public void sub(int i) {
			lock.lock();
			try {
				while (!bShouldSub) {
					try {
						// add tailWaiter(condition) --> unpark next node.thread --> park current thread
						condition.await();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				for (int j = 1; j <= 10; j++) {
					System.out.println("sub thread sequence of " + j + ",loop of " + i);
				}
				bShouldSub = false;
				// add firstWaiter into tail(aqs)(unpark current thread)
				condition.signal();
			} finally {
				lock.unlock();
			}
		}

		public void main(int i) {
			lock.lock();
			try {
				while (bShouldSub) {
					try {
						condition.await();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				for (int j = 1; j <= 100; j++) {
					System.out.println("main thread sequence of " + j + ",loop of " + i);
				}
				bShouldSub = true;
				condition.signal();
			} finally {
				lock.unlock();
			}
		}

	}
}
