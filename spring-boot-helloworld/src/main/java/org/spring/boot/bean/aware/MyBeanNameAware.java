package org.spring.boot.bean.aware;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyBeanNameAware implements BeanNameAware {

	@Override
	public void setBeanName(String name) {
		System.out.println("-----BeanNameAware:setBeanName:" + name + "--------");
	}

	
}
