package org.spring.boot;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.spring.boot.util.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JdbcTest {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Test
	public void testQuery() {
		String sql = "select * from t_city";
		ResultSet rs = jdbcTemplate.query(sql);
		try {
			while (rs.next()) {
				String id = rs.getString("id");
				String cityName = rs.getString("city_name");
				int age = rs.getInt("age");
				double num = rs.getDouble("num");
				System.out.println(id + "-" + cityName + "-" + age + "-" + num);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUpdate() {
		String sql = "update city set province_id = 3 where id = 1";
		jdbcTemplate.update(sql);
	}

	
}
