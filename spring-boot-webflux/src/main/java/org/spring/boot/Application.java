package org.spring.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		
//		 当同时引入web和webflux依赖时，要指定WebApplicationType为REACTIVE
//		new SpringApplicationBuilder(Application.class)
//		.web(WebApplicationType.REACTIVE)
//		.run(args);
	}
	
}
