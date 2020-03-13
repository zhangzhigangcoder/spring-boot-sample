package org.spring.boot.config;

/**
 * 测试spring.factories和直接@Configuration加载顺序
 */
public class SpringFactorySub implements SpringFactoryInter {
	
	public SpringFactorySub() {
		System.out.println("----------SpringFactorySub-------");
	}
	
	public String getName() {
		return "SpringFactorySub";
	}
}

