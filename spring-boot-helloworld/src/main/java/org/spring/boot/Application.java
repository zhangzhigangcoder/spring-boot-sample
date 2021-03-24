package org.spring.boot;

import com.agent.TestAgent;
import org.spring.boot.bean.scope.imports.ImportBean;
import org.spring.boot.bean.scope.imports.UserBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ImportBean({UserBean.class})
@EnableScheduling
public class Application {

	public static void main(String[] args) {
//		ConfigurableApplicationContext applicationContext = SpringApplication.run(Application.class, args);
//		System.out.println(applicationContext.getEnvironment().getProperty("boot.order.orderNo"));
//		SpringApplication springApplication = new SpringApplication(Application.class);
//		springApplication.setAllowBeanDefinitionOverriding(true);
//		springApplication.run(args);
		SpringApplication.run(Application.class);
		TestAgent.premain(null, null);
	}
}

