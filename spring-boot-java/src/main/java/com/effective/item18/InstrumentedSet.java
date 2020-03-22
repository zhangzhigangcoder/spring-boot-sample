package com.effective.item18;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * 包装类
 * 组合父类去实现自己的逻辑，而不是直接继承父类去重写方法来实现自己的逻辑
 * 这样做的好处是：可以不用考虑父类内部的实现细节
 * 例如父类中的addAll方法内部是调用的add方法，如果采用继承的方式，会重复计数
 * @author zhangzhigang
 *@see com.effective.item18.InstrumentedHashSet<E>
 */
public class InstrumentedSet<E> extends ForwardingSet<E> {

	private int addCount = 0;
	
	public InstrumentedSet(Set<E> set) {
		super(set);
	}
	
	@Override
	public boolean add(E e) {
		addCount ++;
		return super.add(e);
	}
	
	@Override
	public boolean addAll(Collection<? extends E> c) {
		addCount += c.size();
		return super.addAll(c);
	}
	
	public int getAddCount() {
		return addCount;
	}
	
	public static void main(String[] args) {
		InstrumentedSet<String> set = new InstrumentedSet<>(new HashSet<>());
		set.addAll(Arrays.asList("snap","crackle","pop"));
		System.out.println(set.getAddCount());
	}

	
}
