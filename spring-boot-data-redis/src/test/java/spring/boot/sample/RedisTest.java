package spring.boot.sample;

import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.spring.boot.Application;
import org.spring.boot.cache.CityRepository;
import org.spring.boot.entity.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class RedisTest {
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
	@Autowired
	private CityRepository cityRepository;
	
	@Test
	public void stringTest() {
		// 保存字符串
		stringRedisTemplate.opsForValue().set("aaa", "111");
		String value = stringRedisTemplate.opsForValue().get("aaa");
		System.out.println(value);
	}
	
	@Test
	public void cityTest() {
		City city = new City(UUID.randomUUID().toString(), "苏州");
		// 保存字符串
		redisTemplate.opsForValue().set(city.getId().toString(), city);
		for(int i = 0; i < 50; i++) {
			System.out.println((City) redisTemplate.opsForValue().get(city.getId().toString()));
		}
//		try {
//			Thread.sleep(3600 * 1000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		
//		redisTemplate.execute(new RedisCallback<String>() {
//
//			@Override
//			public String doInRedis(RedisConnection connection) throws DataAccessException {
//				connection.flushDb();
//				return "ok";
//			}
//			
//		});
	}
	
	/**
	 * 测试方法缓存
	 */
	@Test
	public void cityCacheTest() {
		System.out.println(cityRepository.findCity("").getCityName());
		System.out.println(cityRepository.findCity("").getCityName());
	}
}
