package org.spring.boot.bean.aware;

import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyBeanClassLoaderAware implements BeanClassLoaderAware {

	@Override
	public void setBeanClassLoader(ClassLoader classLoader) {
		System.out.println("-----BeanClassLoaderAware:setBeanClassLoader--------");
	}

	
}
