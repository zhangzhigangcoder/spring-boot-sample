package com.core.thread;

import com.google.common.collect.Lists;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author zhangzhigang
 *
 */
public class Test {
	private static int num = 0, num2 = 0;
	private static boolean flag = false;

	public static synchronized void write() {
		flag = true;
	}

	public static synchronized void read() {
		if (flag) {
			System.out.println(flag);
		}

	}

	public static void main(String[] args) throws InterruptedException {
//		for (int j = 0; j < 10; j++) {
//			for (int i = 0; i < 1000; i++) {
//				new Thread(() -> {
//					flag = false;
//					read();
//				}).start();
//			}

			new Thread(() -> {
				while (true) {
					// 可以证明synchronized关键字可以强制刷新整个工作内存
					synchronized (Test.class) {
						if (flag) {
							break;
						}
					}
				}
				System.out.println("-----------" + num);
			}).start();

		Thread.sleep(2000);
//		synchronized (Test.class) {
			flag = true;
			System.out.println("flag is set true");
//		}


//		}

	}
}