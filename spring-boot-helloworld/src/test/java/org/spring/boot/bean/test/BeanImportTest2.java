package org.spring.boot.bean.test;

import org.junit.Test;
import org.spring.boot.properties.OrderProperties;
import org.spring.boot.test.base.BaseTest;
import org.springframework.beans.factory.annotation.Autowired;

public class BeanImportTest2 extends  BaseTest {

	@Autowired
	private OrderProperties orderProperties;

	@Test
	public void test() {
		System.out.println("-------------------");
		System.out.println(orderProperties.getId());
	}
}
