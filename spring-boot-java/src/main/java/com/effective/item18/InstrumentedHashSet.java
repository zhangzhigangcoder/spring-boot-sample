package com.effective.item18;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

/**
 * 包装类
 * 此例中会出现重复计数
 * @author zhangzhigang
 *@see com.effective.item18.InstrumentedSet<E>
 */
public class InstrumentedHashSet<E> extends HashSet<E> {

	private static final long serialVersionUID = -5396599747040412969L;
	
	private int addCount = 0;
	
	public InstrumentedHashSet() {
	}

	public InstrumentedHashSet(int initCap, float loadFactor) {
		super(initCap, loadFactor);
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
		InstrumentedHashSet<String> set = new InstrumentedHashSet<>();
		set.addAll(Arrays.asList("snap","crackle","pop"));
		System.out.println(set.getAddCount());
	}

	
}
