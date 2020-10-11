package com.jvm.memory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
  * 指令重排
 * 1. 编译器优化重排
 * 2. 指令并行重排
 * 3. 内存系统重排
 *
 * 	原子性: jvm本身支持基本类型数据读写操作的原子性，方法和代码块级别可以用synchronized和ReentrantLock解决
 * 	有序性: 指令重排序引起，可以用volatile解决
 * 	可见性: 由工作内存和主内存同步延迟，指令重排序引起，可以用sychronized和volatile解决
 * 除了通过sychronized和volatile解决上述问题之外，JMM还提供了happens-before原则处理，内容如下：
 *
 * 		1. 程序顺序原则：在一个线程内必须保证语义串行性，即按照代码顺序执行
 *
 * 		2. 锁规则解锁(unlock)操作必然发生在后续的同一个锁的加锁(lock)之前，
 *    	   即对于一个锁解锁后，再加锁，那么加锁的动作必须在解锁动作之后(同一个锁)
 *
 * 		3. volatile规则：volatile变量的写，先发生于读，这保证了volatile变量的可见性，
 * 	  	   即volatile变量在每次被线程访问时，都强迫从主内存中读取该变量的值，
 * 	  	   而当该变量发生变化时，又会强迫将最新的值刷新到主内存，任何时刻，不同的线程总是能够看到该变量的最新值
 *
 *      4. 线程启动规则：线程的start()方法先于它的每一个动作，即如果线程A在执行线程B的start方法之前修改了共享变量的值，
 * 	  	   则当线程B执行start方法时，线程A对共享变量的修改对线程B可见
 *
 * 		5. 传递性：如果操作A先行发生于操作B，而操作B又先行发生于操作C，则操作A先行发生于操作C
 *
 * 		6. 线程终止规则：线程的所有操作都先行发生于线程的终止检测，Thread.join()方法的作用是等待当前执行的线程终止。
 * 	  	   假设在线程B终止之前修改了共享变量，线程A从线程B的join方法成功返回后，线程B对共享变量的修改将对线程A可见
 *
 * 		7. 线程中断规则：一个线程被其他线程interrupt了，那么检测中断(isInterrupted)或者跑出InterruptedException一定看得到
 *
 * 		8. 对象终结规则：一个对象的初始化完成先于finalize()方法的开始
 *
 * 参考：
 * 		https://www.toutiao.com/i6629813495053419016
 * 		https://blog.csdn.net/qq_33229669/article/details/108423590
 */
public class InstructReSort {
	private static int a = 0, b = 0, x1 = 0, x2 = 0;

	public static void main(String[] args) {
		new Thread(() -> {
			x2 = a;
			b = 1;
		}).start();

		new Thread(() -> {
			x1 = b;
			a = 2;
		}).start();

		System.out.println("x1=" + x1 + ",x2=" + x2);
	}

	/**
	 * 编译器指令重排(没有测试出来)
	 * 期待重排为：
	 * 		int a = 0, b = 0;
	 * 		b = 1;
	 *  	a = 2;
	 * 		int x2 = a;
	 * 		int x1 = b;
	 *
	 */
	private static void compile() {
		int a = 0, b = 0;
		int x2 = a;
		int x1 = b;
		b = 1;
		a = 2;

	}
}
