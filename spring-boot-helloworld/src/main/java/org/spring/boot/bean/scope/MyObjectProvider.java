package org.spring.boot.bean.scope;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.Configuration;

/**
 * 测试ObjectProvider使用方法
 *	参考：DependencyObjectProvider
 */
public class MyObjectProvider implements ObjectProvider<Object> {

	@Override
	public Object getObject() throws BeansException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getObject(Object... args) throws BeansException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getIfAvailable() throws BeansException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getIfUnique() throws BeansException {
		// TODO Auto-generated method stub
		return null;
	}


}
