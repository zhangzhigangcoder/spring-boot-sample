package org.spring.boot.session;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * SessionAutoConfiguration
 * 	-> SessionRepositoryFilterConfiguration
 * 		-> SessionRepositoryFilter#SessionRepositoryRequestWrapper 
 * 			-> RedisIndexedSessionRepository
 * RedisHttpSessionConfiguration 主配置类
 * SpringHttpSessionConfiguration Listener
 * @author qw
 *
 */
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 30, redisNamespace = "CAS_SSO_SESSION")
@SpringBootApplication
public class SessionApplication {

	public static void main(String[] args) {
		SpringApplication.run(SessionApplication.class, args);
	}
}
