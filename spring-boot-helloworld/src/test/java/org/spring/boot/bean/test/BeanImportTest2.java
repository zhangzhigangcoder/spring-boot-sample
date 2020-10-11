package org.spring.boot.bean.test;

import org.junit.Assert;
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

public class BeanImportTest2 extends  BaseTest {

	@Autowired
	private OrderProperties orderProperties;

	@Test
	public void test() {
		System.out.println("-------------------");
		System.out.println(null == orderProperties);
	}
}
