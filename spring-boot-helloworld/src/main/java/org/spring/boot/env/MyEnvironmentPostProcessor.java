package org.spring.boot.env;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * 测试EnvironmentPostProcessor使用
 * https://mp.weixin.qq.com/s/EW4zjvrvJlE0PIJbIYAUhA
 * @author zhangzhigang
 *
 */
public class MyEnvironmentPostProcessor implements EnvironmentPostProcessor {

	@Override
	public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
		
	}
	
}
