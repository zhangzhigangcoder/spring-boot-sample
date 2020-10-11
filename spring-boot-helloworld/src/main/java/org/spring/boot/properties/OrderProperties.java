package org.spring.boot.properties;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 框架配置
 *@ConfigurationProperties解析类:ConfigurationPropertiesBindingPostProcessor
 * @author zhangzhigang
 */
@EnableConfigurationProperties
//@ConditionalOnMissingBean(UserProperties.class)
@ConfigurationProperties(prefix = OrderProperties.ORDER_PREFIX)
//	@Configuration
public class OrderProperties {
	
	static final String ORDER_PREFIX = "boot.order";

	public OrderProperties() {
		System.out.println("-------------OrderProperties---------------");
	}
	
	private String no;
	
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

}



