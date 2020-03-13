package org.spring.boot.aop.cglib;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.cglib.proxy.Dispatcher;

/**
 * CGLIB实现动态代理 返回值固定拦截器
 * 
 * @author zhangzhigang
 *
 */
public class CGLIBProxy3 implements Dispatcher {
	
	private CGLIBInterface target;
	
	private static final Logger log = LoggerFactory.getLogger(CGLIBProxy3.class);

	public CGLIBProxy3() {
	}
	
	public CGLIBProxy3(CGLIBInterface target) {
		this.target = target;
	}
	
	@Override
	public Object loadObject() throws Exception {
		return target;
	}

	
}
