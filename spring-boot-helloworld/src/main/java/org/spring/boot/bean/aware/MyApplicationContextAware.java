package org.spring.boot.bean.aware;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

/**
 * 获取ApplicationContext，常用作工具类，来获取spring容器Bean
 * @author zhangzhigang
 *
 */
@Configuration
public class MyApplicationContextAware implements ApplicationContextAware {

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		System.out.println("-----ApplicationContextAware:setApplicationContext--------");		
	}
	
}
