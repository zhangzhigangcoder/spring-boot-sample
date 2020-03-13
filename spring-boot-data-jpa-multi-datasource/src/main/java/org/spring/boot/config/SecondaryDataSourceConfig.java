package org.spring.boot.config;


import java.util.Map;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
		entityManagerFactoryRef = "entityManagerFactorySecondary",
		transactionManagerRef = "transactionManagerSecondary",
		basePackages = {"org.spring.boot.repository.secondary"}
		)
public class SecondaryDataSourceConfig {
	
	@Value("${spring.datasource.secondary.url}")
	private String url;
	
	@Value("${spring.datasource.secondary.username}")
	private String username;
	
	@Value("${spring.datasource.secondary.password}")
	private String password;
	
	@Value("${spring.datasource.secondary.driverClassName}")
	private String driverClass;
	
	@Resource
	private JpaProperties jpaProperties;
	
	@Bean(name = "secondaryDataSource")
	public DataSource primaryDataSource() {
		DataSource dataSource = DataSourceBuilder.create()
								.url(url)
								.username(username)
								.password(password)
								.driverClassName(driverClass)
								.build();
		return dataSource;
	}
	
	@Bean("entityManagerSecondary")
	public EntityManager entityManager(EntityManagerFactoryBuilder builder,@Qualifier("secondaryDataSource") DataSource dataSource) {
		return entityManagerFactorySecondary(builder,dataSource).getObject().createEntityManager();
	}
	
	@Bean("entityManagerFactorySecondary")
	public LocalContainerEntityManagerFactoryBean entityManagerFactorySecondary(EntityManagerFactoryBuilder builder,@Qualifier("secondaryDataSource") DataSource dataSource) {
		return builder
				.dataSource(dataSource)
				.properties(getVendorProperties())
				//设置实体类所在的地方
				.packages("org.spring.boot.entity.secondary")
				.persistenceUnit("secondaryPersistenceUnit")
				.build();
	}
	
	@Bean(name = "transactionManagerSecondary")
	public PlatformTransactionManager transactionManagerSecondary(
			EntityManagerFactoryBuilder builder,@Qualifier("secondaryDataSource") DataSource dataSource) {
		return new JpaTransactionManager(entityManagerFactorySecondary(builder, dataSource).getObject());
	}
	
	private Map<String,Object> getVendorProperties(){
		return jpaProperties.getHibernateProperties(new HibernateSettings());
	}
	
}
