package org.spring.boot.bean.spring.autoconfigure.metadata.properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 测试spring-autoconfigure-metadata.properties配置类机制
 * 说明：配合spring.factories使用
 * @author zhangzhigang
 *
 */
public class MyAutoConfigureMetadataProperties {
	
	private static final Logger log = LoggerFactory.getLogger(MyAutoConfigureMetadataProperties.class);
	
	public MyAutoConfigureMetadataProperties() {
		log.info("--------------MyAutoConfigureMetadataProperties------------------");
	}
	
	public void test() {
		log.info("--------MyAutoConfigureMetadataProperties() method called---------");

	}
}

