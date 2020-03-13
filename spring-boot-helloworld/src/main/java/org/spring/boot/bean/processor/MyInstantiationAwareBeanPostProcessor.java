package org.spring.boot.bean.processor;

import java.beans.PropertyDescriptor;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;

/**
 * 自定义初始化bean
 * @see org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean() resolveBeforeInstantiation
 * @author zhangzhigang
 *
 */
public class MyInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {

	/**
	 * 如果返回值不为null，则会使用该对象，不会再使用beanClass构造方法初始化对象
	 */
	@Override
	public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
		return null;
	}
	
	/**
	 * 如果返回false，则不会初始化bean的成员变量
	 */
	@Override
	public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
		return true;
	}
	
	@Override
	public PropertyValues postProcessPropertyValues(PropertyValues pvs, PropertyDescriptor[] pds, Object bean,
			String beanName) throws BeansException {
		return pvs;
	}
}
