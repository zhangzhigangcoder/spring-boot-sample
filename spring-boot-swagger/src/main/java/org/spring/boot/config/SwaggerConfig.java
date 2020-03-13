package org.spring.boot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger Configuration
 * 
 * @author zhangzhigang
 * 
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("org.spring.boot.controller"))
				.paths(PathSelectors.any())
				.build();
	}
	
	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("Spring Boot使用Wagger2构建Restful APIs")
				.description("Swagger Sample")
				.termsOfServiceUrl("http://localhost:8080")
				.contact("zhangzhigang")
				.version("0.0.1")
				.build();
	}
}
