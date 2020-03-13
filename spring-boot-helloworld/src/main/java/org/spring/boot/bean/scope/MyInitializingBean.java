package org.spring.boot.bean.scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;

/**
 * 自定义InitializingBean
 * 调用: Bean初始化完之后 AbstractAutowireCapableBeanFactory.invokeInitMethods,只会调用一次
 * 执行顺序：
 * 		构造方法 -> @PostConstruct -> afterPropertiesSet -> initMethod -> @PreDestroy -> destroyMethod
 * 
 */
@Configuration
public class MyInitializingBean implements InitializingBean, DisposableBean {

	public MyInitializingBean() {
		System.out.println("--------MyInitializingBean:construct---------");
	}
	
	/**
	 * CommonAnnotationBeanPostProcessor(InitDestroyAnnotationBeanPostProcessor):postProcessBeforeInitialization
	 */
	@PostConstruct
	public void postConstruct() {
		System.out.println("--------MyInitializingBean:@PostConstruct---------");
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("--------InitializingBean:afterPropertiesSet---------");
	}
	
	public void initMethod() {
		System.out.println("--------MyInitializingBean:initMethod---------");
	}
	
	/**
	 * CommonAnnotationBeanPostProcessor(InitDestroyAnnotationBeanPostProcessor):postProcessBeforeDestruction
	 */
	@PreDestroy
	public void preDestory() {
		System.out.println("--------MyInitializingBean:@PreDestroy---------");
	}
	
	@Override
	public void destroy() throws Exception {
		System.out.println("--------InitializingBean:DisposableBean---------");
	}
	
	public void destroyMethod() {
		System.out.println("--------MyInitializingBean:destroyMethod---------");
	}
}