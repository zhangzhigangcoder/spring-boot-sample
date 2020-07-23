package com.system.cache;

import java.util.BitSet;

/**
 * 自定义Bloom过滤器处理缓存穿透问题
 * 
 *
 */
public class CustomBloomFilter {
	private final int size;
	private final BitSet bitSet;
	
	public CustomBloomFilter(int size) {
		this.size = size;
		this.bitSet = new BitSet(this.size);
	}
	
	public void addValue(String value) {
		this.bitSet.set(Math.abs(value.hashCode()));
	}
	
	public boolean exists(String value) {
		return this.bitSet.get(Math.abs(value.hashCode()));
	}
	
	public static void main(String[] args) {
		CustomBloomFilter filter = new CustomBloomFilter(10);
		filter.addValue("www.baidu.com");
		filter.addValue("www.163.com");
		System.out.println(filter.exists("www.126.com"));
		System.out.println(filter.exists("www.163.com"));
		System.out.println(filter.exists("www.baidu.com"));
	}
}