package org.spring.boot.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 框架配置
 *@ConfigurationProperties解析类:ConfigurationPropertiesBindingPostProcessor
 * @author zhangzhigang
 *	1. 不同目录
 *		从下向上加载，后面会覆盖前面，即最外层的优先级最高
 * 		.config/
 * 		.
 * 		classpath:/config/
 * 		classpath:/
 *
 *
 * 2. 同目录
 * 		.properties会覆盖.yml文件
 * 		application-{profile}.yml会覆盖application.yml
 */

@EnableConfigurationProperties
//@ConditionalOnMissingBean(UserProperties.class)
@ConfigurationProperties(prefix = OrderProperties.ORDER_PREFIX)
@Configuration
public class OrderProperties {
	
	static final String ORDER_PREFIX = "boot.order";

	public OrderProperties() {
		System.out.println("-------------OrderProperties---------------");
	}
	
	private String no;

	private String id;
	
	private String code;
	
	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}



