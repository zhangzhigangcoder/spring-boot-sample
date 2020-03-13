package org.spring.boot.aop.cglib;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.cglib.proxy.FixedValue;

/**
 * CGLIB实现动态代理 返回值固定拦截器
 * 
 * @author zhangzhigang
 *
 */
public class CGLIBProxy2 implements FixedValue {
	
	private static final Logger log = LoggerFactory.getLogger(CGLIBProxy2.class);

	@Override
	public Object loadObject() throws Exception {
		log.info("FixedValue");
		return "Good Bye";
	}


	
}
