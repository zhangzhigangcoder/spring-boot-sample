package org.spring.boot.bean.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Configuration;

/**
 * 自定义BeanPostProcessor
 * 调用: 
 * 	Bean初始化完之后 
 * 		AbstractAutowireCapableBeanFactory.applyBeanPostProcessorsBeforeInitialization，
 * 		AbstractAutowireCapableBeanFactory.applyBeanPostProcessorsAfterInitialization
 * 在Bean初始化过程中调用，会对每个Bean都会调用，慎重使用
 * @author zhangzhigang
 *
 */
//@Configuration
public class MyBeanPostProcessor implements BeanPostProcessor {

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
//		System.out.println("-----BeanPostProcessor:postProcessBeforeInitialization--------");
		return bean;
	}
	
	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
//		System.out.println("-----BeanPostProcessor:postProcessBeforeInitialization--------");
		return bean;
	}
}
