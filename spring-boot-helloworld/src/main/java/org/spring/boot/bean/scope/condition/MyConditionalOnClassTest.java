package org.spring.boot.bean.scope.condition;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;

/**
 * 测试@ConditionalOnClass,常用有：
 * 	@ConditionalOnClass：集合中只要有一个没有就验证不通过
 * 	@ConditionalOnMissingClass:集合中只要有一个存在就验证不通过
 *  @ConditionalOnBean:Spring容器中存在指定bean时校验通过,如果有@Bean可省略参数，此时会取方法返回值类型
 *  @ConditionalOnMissingBean:Spring容器中不存在指定bean时校验通过,如果有@Bean可省略参数，此时会取方法返回值类型，
 *  @ConditionalOnProperty
 *  @ConditionalOnResource(resources = "classpath:META-INF/services/javax.validation.spi.ValidationProvider")
 * @author zhangzhigang
 *
 */
//@Configuration
@ConditionalOnBean(type="com.boot.bean.scope.condition.MyConditionalTest")
public class MyConditionalOnClassTest {
	
	public MyConditionalOnClassTest() {
		System.out.println("----------MyConditionalOnClassTest-------------");
	}
	
}
