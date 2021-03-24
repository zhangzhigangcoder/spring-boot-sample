package org.spring.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;

//@EnableConfigurationProperties({UserProperties.class, OrderProperties.class})
@ComponentScan(basePackages = {"org.spring.boot.properties"})
public class ApplicationTest {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationTest.class);
	}
}

