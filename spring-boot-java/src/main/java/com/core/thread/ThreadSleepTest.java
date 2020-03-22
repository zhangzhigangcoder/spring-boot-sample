package com.core.thread;

/**
 * https://www.journaldev.com/1020/thread-sleep-java
 * @author zhangzhigang
 *
 */
public class ThreadSleepTest {
	
	public static void main(String[] args) throws Exception {
		long start = System.currentTimeMillis();
		// 当前线程状态为TIMED_WAITING
		Thread.sleep(1000);
		System.out.println("Sleep time in ms = " + (System.currentTimeMillis() - start));
	}
}
