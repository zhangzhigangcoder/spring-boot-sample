package org.spring.boot.annotations.qualifier;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;

/**
 * 测试@Qualifier用法
 *	用法一：和@Autowired直接配合使用，将注入方式由类型改为名称
 *	用法二：和自定义注解配合使用，可以通过自定义注解进行过滤注入
 */
@Configuration
public class QTest implements InitializingBean {

	/**
	 * 用法一
	 */
	@Qualifier("QService2")
	@Autowired
	private QInterface qInterface;
	
	/**
	 * 用法二
	 */
	@QualifierTest
	@Autowired
	private List<QInterface> qInterfaceList;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		qInterface.m();
	}
	
}
