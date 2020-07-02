package com.core.base.jdk8.time;

import java.time.LocalDateTime;

/**
 * Immutability Thread safe
 * 
 * @author zhangzhigang
 *
 */
public class LocalDateTimeSample {

	public static void main(String[] args) {
		LocalDateTime dt = LocalDateTime.now();
		System.out.println(dt);
	}

}
