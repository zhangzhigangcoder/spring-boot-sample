package com.core.spi.service;

/**
 * spi impl
 * @author zhangzhigang
 */
public class HelloService1Impl implements HelloService {

	@Override
	public void hello() {
		System.out.println(this.getClass().getName());
	}
	
}