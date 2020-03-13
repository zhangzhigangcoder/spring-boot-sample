package org.spring.boot.config;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class SecondaryDataSourceConfig {
	
	@Value("${spring.datasource.secondary.url}")
	private String url;
	
	@Value("${spring.datasource.secondary.username}")
	private String username;
	
	@Value("${spring.datasource.secondary.password}")
	private String password;
	
	@Value("${spring.datasource.secondary.driverClassName}")
	private String driverClass;
	
	@Bean(name = "secondaryDataSource")
	public DataSource clusterDataSource() {
		DataSource dataSource = DataSourceBuilder.create()
								.url(url)
								.username(username)
								.password(password)
								.driverClassName(driverClass)
								.build();
		return dataSource;
	}
	
	@Bean(name = "secondaryJdbcTemplate")
	public JdbcTemplate primaryJdbcTemplate(
			@Qualifier("secondaryDataSource") DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

}
