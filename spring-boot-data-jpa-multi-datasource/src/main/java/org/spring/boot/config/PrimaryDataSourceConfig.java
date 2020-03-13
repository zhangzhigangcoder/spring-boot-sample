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
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
		entityManagerFactoryRef = "entityManagerFactoryPrimary",
		transactionManagerRef = "transactionManagerPrimary",
		basePackages = {"org.spring.boot.repository.primary"}
		)
public class PrimaryDataSourceConfig {
	
	@Value("${spring.datasource.primary.url}")
	private String url;
	
	@Value("${spring.datasource.primary.username}")
	private String username;
	
	@Value("${spring.datasource.primary.password}")
	private String password;
	
	@Value("${spring.datasource.primary.driverClassName}")
	private String driverClass;
	
	@Resource
	private JpaProperties jpaProperties;
	
	@Primary
	@Bean(name = "primaryDataSource")
	public DataSource primaryDataSource() {
		DataSource dataSource = DataSourceBuilder.create()
								.url(url)
								.username(username)
								.password(password)
								.driverClassName(driverClass)
								.build();
		return dataSource;
	}
	
	@Primary
	@Bean("entityManagerPrimary")
	public EntityManager entityManager(EntityManagerFactoryBuilder builder,@Qualifier("primaryDataSource") DataSource dataSource) {
		return entityManagerFactoryPrimary(builder,dataSource).getObject().createEntityManager();
	}
	
	@Primary
	@Bean("entityManagerFactoryPrimary")
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryPrimary(EntityManagerFactoryBuilder builder,@Qualifier("primaryDataSource") DataSource dataSource) {
		return builder
				.dataSource(dataSource)
				.properties(getVendorProperties())
				//设置实体类所在的地方
				.packages("org.spring.boot.entity.primary")
				.persistenceUnit("primaryPersistenceUnit")
				.build();
	}
	
	@Primary
	@Bean(name = "transactionManagerPrimary")
	public PlatformTransactionManager transactionManagerPrimary(
			EntityManagerFactoryBuilder builder,@Qualifier("primaryDataSource") DataSource dataSource) {
		return new JpaTransactionManager(entityManagerFactoryPrimary(builder, dataSource).getObject());
	}
	
	private Map<String,Object> getVendorProperties(){
		return jpaProperties.getHibernateProperties(new HibernateSettings());
	}
	
	
	
	
	

}
