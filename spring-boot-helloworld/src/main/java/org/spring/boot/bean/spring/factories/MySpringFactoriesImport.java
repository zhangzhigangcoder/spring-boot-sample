package org.spring.boot.bean.spring.factories;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.boot.bean.spring.autoconfigure.metadata.properties.MyAutoConfigureMetadataProperties;
import org.spring.boot.config.MyAutoConfiguration;
import org.spring.boot.config.SpringFactoryInter;
import org.spring.boot.config.SpringFactorySub2;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.support.SpringFactoriesLoader;
import org.springframework.util.CollectionUtils;

/**
 * 测试spring.factories自动配置类机制
 * 说明：
 * 	1.以EnableAutoConfiguration为key的配置类可以被加载到spring容器中，
 * 	      前提是经过AutoConfigurationImportFilter过滤,如OnClassCondition,OnBeanCondition
 * 	2.以其它key配置的类要自己处理SpringFactoriesLoader.loadFactories()
 *  3.注意,这种类加载机制实在普通@Component类加载之后加载
 *    @see MyImportTest.2
 * @author zhangzhigang
 *
 */
public class MySpringFactoriesImport {
	
	private static final Logger log = LoggerFactory.getLogger(MySpringFactoriesImport.class);

	public MySpringFactoriesImport() {
		log.info("--------------MySpringFactoriesImport------------------");
	}
	
	public void test() {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		List<MyAutoConfigureMetadataProperties> clazzs = SpringFactoriesLoader.loadFactories(MyAutoConfigureMetadataProperties.class, loader);
		if (!CollectionUtils.isEmpty(clazzs)) {
			MyAutoConfigureMetadataProperties metaData = clazzs.get(0);
			metaData.test();
		}
		log.info("-------- MySpringFactoriesImport:test() method called---------");
	}
	
	/**
	 * 测试加载顺序
	 * @see MyAutoConfiguration
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean(SpringFactoryInter.class)
	public SpringFactoryInter springFactoryInter() {
		return new SpringFactorySub2();
	}
}

