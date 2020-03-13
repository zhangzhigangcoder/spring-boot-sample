package org.spring.boot.bean.scope;

import org.springframework.beans.factory.BeanDefinitionStoreException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Configuration;

/**
 * 自定义BeanDefinitionRegistry
 * @author zhangzhigang
 *
 */
@Configuration
public class MyBeanDefinitionRegistry implements BeanDefinitionRegistry {

	@Override
	public void registerAlias(String name, String alias) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeAlias(String alias) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isAlias(String name) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String[] getAliases(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition)
			throws BeanDefinitionStoreException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeBeanDefinition(String beanName) throws NoSuchBeanDefinitionException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public BeanDefinition getBeanDefinition(String beanName) throws NoSuchBeanDefinitionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean containsBeanDefinition(String beanName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String[] getBeanDefinitionNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getBeanDefinitionCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isBeanNameInUse(String beanName) {
		// TODO Auto-generated method stub
		return false;
	}


}
