package org.spring.boot.bean.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Configuration;

/**
 * 自定义BeanFactoryPostProcessor
 * 调用: PostProcessorRegistrationDelegate.invokeBeanFactoryPostProcessors
 * 用来对BeanFactory的管理，在配置类扫描之后调用，只会调用一次
 * @author zhangzhigang
 *
 */
//@Configuration
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		System.out.println("-----BeanFactoryPostProcessor:postProcessBeanFactory--------");
	}

}
