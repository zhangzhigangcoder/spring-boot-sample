package org.spring.boot.aop.cglib;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.cglib.proxy.CallbackFilter;

/**
 * CGLIB实现动态代理 过滤器
 * 
 * @author zhangzhigang
 *
 */
public class CGLIBFilter implements CallbackFilter {
	
	private static final Logger log = LoggerFactory.getLogger(CGLIBFilter.class);

	@Override
	public int accept(Method method) {
		int result = 0;//  callbacks[result]
		switch (method.getName()) {
			case "sayHello":
				result = 1;
				break;
			case "sayBye":
				result = 2;
				break;
			case "getName":
				result = 3;
				break;
		}
		return result;
	}

}
