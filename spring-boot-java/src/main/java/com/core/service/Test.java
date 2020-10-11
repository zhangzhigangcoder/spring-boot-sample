package com.core.service;

import java.util.ServiceLoader;

/**
 * spi test
 * 
 * @author zhangzhigang
 */
public class Test {

	public static void main(String[] args) {
			ServiceLoader<HelloService> loaders = ServiceLoader.load(HelloService.class);
			for (HelloService helloService : loaders) {
				helloService.hello();
			}
		}
}