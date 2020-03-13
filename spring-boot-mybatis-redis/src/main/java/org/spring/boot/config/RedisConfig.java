package org.spring.boot.config;

import org.spring.boot.entity.City;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
	
//	@Bean
//	public JedisConnectionFactory jedisConnectionFactory() {
//		return new JedisConnectionFactory();
//	}
	
	@Bean
	public RedisTemplate<String, City> redisTemplate(RedisConnectionFactory factory) {
		RedisTemplate<String, City> template = new RedisTemplate<>();
		template.setConnectionFactory(factory);
		template.setKeySerializer(new StringRedisSerializer());
		template.setValueSerializer(new RedisObjectSerializer());
		return template;
	}

}
