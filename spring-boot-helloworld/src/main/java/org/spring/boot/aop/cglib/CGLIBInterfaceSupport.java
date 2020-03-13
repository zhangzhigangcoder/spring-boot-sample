package org.spring.boot.aop.cglib;

/**
 * CGLIB实现动态代理 代理接口
 * 
 * @author zhangzhigang
 *
 */
public class CGLIBInterfaceSupport implements CGLIBInterface {
	
	public String getName(String name) {
		return "get:" + name;
	}
	
}
