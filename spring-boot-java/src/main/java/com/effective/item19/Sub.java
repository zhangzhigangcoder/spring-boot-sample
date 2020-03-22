package com.effective.item19;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * 子类
 * 测试:子类重写方法依赖了需要子类构造方法初始化的实例域
 * @author zhangzhigang
 */
public class Sub extends Super {

	private final Instant instant;
	
	public Sub() {
		instant = Instant.now();
	}
	
	public void overrideMe() {
		System.out.println(instant);
	}
	
	public static void main(String[] args) {
		Sub sub = new Sub();
		// null
		// 2019-05-01T09:42:01.964Z
		sub.overrideMe();
		List<Super> list = new ArrayList<>();
		list.add(new Super());
		list.add(sub);
	}
}

