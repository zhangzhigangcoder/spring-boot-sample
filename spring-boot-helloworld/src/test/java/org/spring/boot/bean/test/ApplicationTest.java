package org.spring.boot.bean.test;

import org.spring.boot.properties.OrderProperties;
import org.spring.boot.properties.UserProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@EnableConfigurationProperties({UserProperties.class, OrderProperties.class})
@ComponentScan(basePackages = {"org.spring.boot.properties"})
public class ApplicationTest {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationTest.class);
	}
}

