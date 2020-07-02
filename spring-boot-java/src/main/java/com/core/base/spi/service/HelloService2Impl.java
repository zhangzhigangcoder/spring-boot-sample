package com.core.base.spi.service;

/**
 * spi impl
 * @author zhangzhigang
 */
public class HelloService2Impl implements HelloService {

	@Override
	public void hello() {
		System.out.println(this.getClass().getName());
	}
	
}