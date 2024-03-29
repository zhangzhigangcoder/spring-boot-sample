package org.spring.boot;

import org.spring.boot.bean.scope.imports.ImportBean;
import org.spring.boot.bean.scope.imports.UserBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ImportBean({UserBean.class})
@EnableScheduling
public class Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(Application.class, args);
	}
}

