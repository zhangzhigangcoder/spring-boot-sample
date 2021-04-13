package com.jvm.gc;

import com.core.copy.Student;
import org.openjdk.jol.info.ClassLayout;

/**
 * 查看对象布局
 * 
 * @author zhangzhigang
 *	
 */
public class Layout {
	
	public static void main(String[] args) throws InterruptedException {

//		Thread.sleep(5);

//		Object o = new Object(); 
		
		Student o = new Student("zhang", 1);
//		
//		Map m = new HashMap();
//		m.put("1", "v");
		System.out.println(ClassLayout.parseInstance(o).toPrintable());

		new Thread(() -> {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			synchronized (o) {
//				o.notify();
				System.out.println("111111");
			}
		}).start();


		synchronized (o) {
//			o.wait();
			System.out.println(ClassLayout.parseInstance(o).toPrintable());
			synchronized (o) {
				System.out.println(ClassLayout.parseInstance(o).toPrintable());
			}
		}
		// 11000011
		// 00110100
		// 10111011
		// 1111111

		// 01100001
		// 10011010
		// 01011101
		// 11111111
//		System.out.println(Integer.toBinaryString(System.identityHashCode(o)));
//		System.out.println(Integer.toBinaryString(o.hashCode())); // 10
		System.out.println(ClassLayout.parseInstance(o).toPrintable());
	}



}
