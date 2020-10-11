package com.core.thread.concurrent.atomic;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * 用AtomicStampedReference解决ABA问题
 *
 */
public class AtomicStampedReferenceTest {

	public static void main(String[] args) {
		//通过版本戳解决ABA问题
		AtomicStampedReference<String> stampedRef = new AtomicStampedReference<>("A", 1);
		new Thread(() -> {
			//获取期望值
			String expectRef = stampedRef.getReference();
			//获取期望版本戳
			Integer expectStamp = stampedRef.getStamp();
			//打印期望值和期望版本戳
			System.out.println(Thread.currentThread().getName() + " expect: " + expectRef + "-" + expectStamp);
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//打印实际值和实际版本戳
			System.out.println(Thread.currentThread().getName() + " actual: " + stampedRef.getReference() + "-" + stampedRef.getStamp());
			//进行CAS操作（带版本戳）
			boolean result = stampedRef.compareAndSet("A", "X", expectStamp, expectStamp + 1);
			//打印操作结果
			System.out.println(Thread.currentThread().getName() + " result: " + result + " => final reference = " + stampedRef.getReference() + "-" + stampedRef.getStamp());
		}, "线程1").start();

		new Thread(() -> {
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// 进行ABA操作（带版本戳）
			System.out.print(Thread.currentThread().getName() + " change: " + stampedRef.getReference() + "-" + stampedRef.getStamp());
			stampedRef.compareAndSet("A", "B", stampedRef.getStamp(), stampedRef.getStamp() + 1);
			System.out.print(" -- > B" + "-" + stampedRef.getStamp());
			stampedRef.compareAndSet("B", "A", stampedRef.getStamp(), stampedRef.getStamp() + 1);
			System.out.println(" -- > A" + "-" + stampedRef.getStamp());
		},"线程2").start();

	}

	/**
	 * ABA问题复现
	 */
	public static void aba() {
		AtomicReference<String> reference = new AtomicReference<>("A");
		new Thread(() -> {
			//获取期望值
			String expect = reference.get();
			//打印期望值
			System.out.println(Thread.currentThread().getName() + " expect: " + expect);
			try {
				// 模拟在获取期望值和更新操作期间别的线程在执行
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//打印实际值
			System.out.println(Thread.currentThread().getName() + " actual: " + reference.get());
			//进行CAS操作
			boolean result = reference.compareAndSet("A", "X");
			//打印操作结果
			System.out.println(Thread.currentThread().getName() + " result: " + result + " ==》 final reference = " + reference.get());
		}, "线程1").start();

		new Thread(() -> {
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//进行ABA操作
			System.out.print(Thread.currentThread().getName() + " change: " + reference.get());
			reference.compareAndSet("A", "B");
			System.out.print(" -- > B");
			reference.compareAndSet("B", "A");
			System.out.println(" -- > A");
		}, "线程2").start();

	}

}
