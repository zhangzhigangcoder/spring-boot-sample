package org.spring.boot;

import org.spring.boot.bean.scope.imports.ImportBean;
import org.spring.boot.bean.scope.imports.UserBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@ImportBean({UserBean.class})
public class Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(Application.class, args);
//		System.out.println(applicationContext.getEnvironment().getProperty("boot.order.orderNo"));
	}
}
