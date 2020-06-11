package org.spring.boot.session;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * SessionAutoConfiguration
 * RedisHttpSessionConfiguration 主配置类
 *  * 	-> SessionRepositoryFilterConfiguration
 * 		-> SessionRepositoryFilter#SessionRepositoryRequestWrapper 
 * 			-> RedisIndexedSessionRepository
 * SpringHttpSessionConfiguration 
 * 	-> RedisMessageListenerContainer Listener
 * SessionFlashMapManager
 * @author qw
 *
 */
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 300000000, redisNamespace = "CAS_SSO_SESSION")
@SpringBootApplication
public class SessionApplication {

	public static void main(String[] args) {
		SpringApplication.run(SessionApplication.class, args);
	}
}
