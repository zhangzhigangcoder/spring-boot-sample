package spring.boot.sample;

import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.spring.boot.Application;
import org.spring.boot.cache.CacheService;
import org.spring.boot.entity.City;
import org.spring.boot.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
	
	@Autowired
	private CacheService cacheService;
	
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
		cacheService.setModel(city.getId().toString(), city);
		System.out.println(cacheService.getModel(city.getId().toString(), City.class));
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
