package org.spring.boot.bean.test;

import org.junit.Test;
import org.spring.boot.bean.scope.MyFactoryBean;
import org.spring.boot.bean.scope.UserFactoryBean;
import org.spring.boot.bean.scope.imports.UserBean;
import org.spring.boot.bean.spring.factories.MySpringFactoriesImport;
import org.spring.boot.properties.OrderProperties;
import org.spring.boot.properties.UserProperties;
import org.spring.boot.test.base.BaseTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class BeanImportTest extends BaseTest {

	@Autowired
	private UserBean userBean;
	
	@Autowired
	private MySpringFactoriesImport mySpringFactoriesImport;
	
	@Autowired
	private OrderProperties orderProperties;
	
	@Autowired
	private UserProperties userProperties;
	
	@Autowired
	private UserFactoryBean userFactoryBean;
	
	@Autowired
	@Qualifier("myFactoryBean")
	private MyFactoryBean myfactoryBean;
	
	/**
	 * 测试 @Import + ImportBeanDefinitionRegistrar方式注入Bean
	 */
	@Test
	public void testImportWithImportBeanDefinitionRegistrar() {
		if (null != userBean) {
			System.out.println(userBean.getName());
		}
	}
	
	/**
	 * 测试 spring.factories方式注入Bean
	 */
	@Test
	public void testSpringFactories() {
		mySpringFactoriesImport.test();
	}
	
	/**
	 * 测试 @ConfigurationProperties + @EnableConfigurationProperties
	 */
	@Test
	public void testConfigurationProperties() {
		System.out.println("========================");
		System.out.println(orderProperties.getNo());
		System.out.println(orderProperties.getCode());
		System.out.println(userProperties.getName());
	}
	
	/**
	 * 测试FactoryBean接口向spring容器注入Bean
	 */
	@Test
	public void testFactoryBean() {
		if (null != userFactoryBean) {
			System.out.println(userFactoryBean.getName());
		}
//		if (null != myfactoryBean) {
//			System.out.println(myfactoryBean.getObjectType());
//		}
	}
}
