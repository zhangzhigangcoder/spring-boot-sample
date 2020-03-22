package com.core.jdk8.foreach;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

/**
 * 演示 集合类forEach方法
 * @author zhangzhigang
 *
 */
public class ForEachExample {

	public static void main(String[] args) {
		List<Integer> myList = new ArrayList<>();
		for (int i=0; i< 10; i++) {
			myList.add(i);
		}
		// traversing using Iterator
		Iterator<Integer> it = myList.iterator();
		while (it.hasNext()) {
			Integer i = it.next();
			System.out.println("Iterator Value: " + i);
		}
		// traversing through forEach method
		myList.forEach(new Consumer<Integer>() {

			@Override
			public void accept(Integer t) {
				System.out.println("forEach anonymous class value: " + t);
			}
		
		});
		
		// Consumer本身是@FunctionalInterface，可以用lambda简写
		myList.forEach(p -> System.out.println("forEach anonymous class value: " + p));
	}
}
