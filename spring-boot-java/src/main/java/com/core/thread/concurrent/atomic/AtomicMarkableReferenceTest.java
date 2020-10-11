package com.core.thread.concurrent.atomic;

import java.util.concurrent.atomic.AtomicMarkableReference;

/**
 * AtomicMarkableReference解决不了ABA问题
 *
 */
public class AtomicMarkableReferenceTest {

	private static AtomicMarkableReference markableRef = new AtomicMarkableReference<>(0, false);

	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread(() -> {
			FinalDemo.writer();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		t1.start();
		t1.join(1);
		for (int i = 0; i < 10; i++) {
			new Thread(() -> {
				FinalDemo.reader();
			}).start();
		}
	}
}

class FinalDemo {
	private int a;  //普通域
	private final int b; //final域
	private static FinalDemo finalDemo;

	public FinalDemo() {
		b = 1; // 2. 写final域
		a = 1; // 1. 写普通域
	}

	public static void writer() {
		finalDemo = new FinalDemo();
	}

	public static void reader() {
		FinalDemo demo = finalDemo; // 3.读对象引用
//		int a = demo.a;    //4.读普通域
//		int b = demo.b;    //5.读final域
		if (demo.a != demo.b) {
			System.out.println("a = " + demo.a + " b = " + demo.b);
		}
	}
}
