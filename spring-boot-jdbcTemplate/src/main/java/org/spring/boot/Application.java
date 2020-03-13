package org.spring.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

//@EnableTransactionManagement    // 默认会开启事物
@SpringBootApplication
// 将代理类绑定到线程上
@EnableAspectJAutoProxy(exposeProxy=true)
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
