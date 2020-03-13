package org.spring.boot.env;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 测试@PropertySource注解使用
 * 说明： 默认只支持.properties解析,可以自定义factory参数
 * @author zhangzhigang
 *
 */
@Configuration
@PropertySource(value = {"classpath:order.properties"})
public class MyPropertySource {
	
}
