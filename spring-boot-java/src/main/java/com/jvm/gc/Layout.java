package com.jvm.gc;

import org.openjdk.jol.info.ClassLayout;

/**
 * 查看对象布局
 * 
 * @author zhangzhigang
 *	
 */
public class Layout {
	
	public static void main(String[] args) {
		
		Object o = new Object(); 
		
//		Student s = new Student("zhang", 1);
//		
//		Map m = new HashMap();
//		m.put("1", "v");
		System.out.println(ClassLayout.parseInstance(o).toPrintable());
		
		synchronized (o) {
			System.out.println(ClassLayout.parseInstance(o).toPrintable());
//			synchronized (s) {
//				System.out.println(ClassLayout.parseInstance(s).toPrintable());
//			}
		}
		// 11000011
		// 00110100
		// 10111011
		// 1111111

		// 01100001
		// 10011010
		// 01011101
		// 11111111
		System.out.println(Integer.toBinaryString(System.identityHashCode(o)));
//		System.out.println(Integer.toBinaryString(o.hashCode())); // 10
		System.out.println(ClassLayout.parseInstance(o).toPrintable());
	}
}
