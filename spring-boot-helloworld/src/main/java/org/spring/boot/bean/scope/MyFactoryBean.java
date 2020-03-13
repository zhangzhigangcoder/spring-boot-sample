package org.spring.boot.bean.scope;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.annotation.Configuration;

/**
 * 	1. FactoryBean是BeanFactory支持的、用来暴露bean实例的接口，通常用来实例化一些比较复杂的对象
 *  2. 这种方式中，FactoryBean中会多一个FactoryBean实例
 *  3. 延迟加载，只有自定义对象在被依赖时，才会调用getObject方法(FactoryBeanRegistrySupport.factoryBeanObjectCache缓存)
 *     如果不想延迟加载，可以用SmartFactoryBean接口
 *  应用：
 *  	mybatis中的SqlSessionFactoryBean
 *  
 * @author zhangzhigang
 *
 */
@Configuration
public class MyFactoryBean implements FactoryBean<UserFactoryBean> {

	public MyFactoryBean() {
		System.out.println("-----------");
	}
	
	@Override
	public UserFactoryBean getObject() throws Exception {
		UserFactoryBean u = new UserFactoryBean();
		u.setName("张三");
		return u;
	}

	@Override
	public Class<?> getObjectType() {
		return UserFactoryBean.class;
	}

}
