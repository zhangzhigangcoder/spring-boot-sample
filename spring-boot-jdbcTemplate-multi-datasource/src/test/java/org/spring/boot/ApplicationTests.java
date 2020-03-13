package org.spring.boot;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {
	
	@Autowired
	@Qualifier("primaryJdbcTemplate")
	private JdbcTemplate jdbcTemplate1;
	

	@Autowired
	@Qualifier("secondaryJdbcTemplate")
	private JdbcTemplate jdbcTemplate2;
	
	
	@Before
	public void setUp() {
		jdbcTemplate1.update("delete from city");
		jdbcTemplate2.update("delete from user");
	}
	
	@Test
	public void test() {
		
		jdbcTemplate1.update("insert into city(id,city_name,province_id) values (?,?,?)",1,"suzhou",1);
		jdbcTemplate1.update("insert into city(id,city_name,province_id) values (?,?,?)",2,"yangzhou",1);

		
		jdbcTemplate2.update("insert into user(id,user_name) values (?,?)",1,"zhangsan");

		
		Assert.assertEquals("2", jdbcTemplate1.queryForObject("select count(1) from city", String.class));

		Assert.assertEquals("1", jdbcTemplate2.queryForObject("select count(1) from user", String.class));

		
	}

	
}
