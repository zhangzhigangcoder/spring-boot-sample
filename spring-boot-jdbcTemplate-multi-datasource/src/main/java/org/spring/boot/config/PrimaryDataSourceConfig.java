package org.spring.boot.config;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class PrimaryDataSourceConfig {
	
	@Value("${spring.datasource.primary.url}")
	private String url;
	
	@Value("${spring.datasource.primary.username}")
	private String username;
	
	@Value("${spring.datasource.primary.password}")
	private String password;
	
	@Value("${spring.datasource.primary.driverClassName}")
	private String driverClass;
	
	@Bean(name = "primaryDataSource")
	@Primary
	public DataSource clusterDataSource() {
		DataSource dataSource = DataSourceBuilder.create()
								.url(url)
								.username(username)
								.password(password)
								.driverClassName(driverClass)
								.build();
		return dataSource;
	}
	
	@Bean(name = "primaryJdbcTemplate")
	public JdbcTemplate primaryJdbcTemplate(
			@Qualifier("primaryDataSource") DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

}
