package org.spring.boot.bean.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.annotation.Configuration;

/**
 * 用来注册BeanDefinition，对beanFactory管理，在配置类扫描过后调用，每个方法只会调用一次,层次较高
 *	应用： 
 *		扫描配置类：internalConfigurationAnnotationProcessor=ConfigurationClassPostProcessor
 */
@Configuration
public class MyBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
//		System.out.println("-----BeanFactoryPostProcessor:postProcessBeanFactory--------");
//		for (String beanName : beanFactory.getBeanDefinitionNames()) {
//			BeanDefinition bd = beanFactory.getBeanDefinition(beanName);
//			if (null != bd && bd instanceof AbstractBeanDefinition) {
//				String beanClass = null == bd.getBeanClassName() ? bd.getFactoryBeanName() : bd.getBeanClassName();
//				beanClass = null == beanClass ? bd.getFactoryMethodName() : beanClass;
//				if (null != beanClass && beanClass.startsWith("org.spring.boot")) {
//					System.out.println(beanClass + "-" + ((AbstractBeanDefinition) bd).getAutowireMode());
//				} if (null == beanClass) {
//					System.out.println(beanClass + "-" + ((AbstractBeanDefinition) bd).getAutowireMode());
//				}
//				
//			}
//		}
	}

	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
		System.out.println("-----BeanDefinitionRegistryPostProcessor:postProcessBeanDefinitionRegistry--------");
	}

}
