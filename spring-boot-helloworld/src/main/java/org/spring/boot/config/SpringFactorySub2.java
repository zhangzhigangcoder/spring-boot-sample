package org.spring.boot.config;

/**
 * 测试spring.factories和直接@Configuration加载顺序
 */
public class SpringFactorySub2 implements SpringFactoryInter {
	
	public SpringFactorySub2() {
		System.out.println("----------SpringFactorySub2-------");
	}
	
	public String getName() {
		return "SpringFactorySub2";
	}
	
}

