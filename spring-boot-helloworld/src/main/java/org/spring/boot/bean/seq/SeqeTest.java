package org.spring.boot.bean.seq;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 测试相同Bean加载顺序
 * 默认情况下，相同的类型是不能注入两次的，否则会报下面错误：
 *	The bean 'sqInterface', could not be registered.
 *	A bean with that name has already been defined in class 
 *	path resource [org/spring/boot/bean/seq/SeqeConfig.class] and overriding is disabled.
 *
 *修改以下参数，可以实现后面的覆盖前面的效果：
 *	DefaultListableBeanFactory.allowBeanDefinitionOverriding=true
 *
 *配置方式：
 *	SpringApplication springApplication = new SpringApplication(Application.class);
 *	springApplication.setAllowBeanDefinitionOverriding(true);
 *	springApplication.run(args);
 */
//@Configuration
public class SeqeTest implements InitializingBean {

	@Autowired
	private SQInterface sqInterface;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		sqInterface.m();
	}
	
}
