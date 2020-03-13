package org.spring.boot.bean.scope.imports;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

/**
 * 测试ImportBeanDefinitionRegistrar
 * 应用：
 * 1.aop
 * 		@see org.springframework.context.annotation.AspectJAutoProxyRegistrar
 * 		@see org.springframework.boot.autoconfigure.aop.AopAutoConfiguration
 * 2.mybatis
 * 		@see org.mybatis.spring.annotation.MapperScannerRegistrar
 * @author zhangzhigang
 *
 */
public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
		AnnotationAttributes annoAttrs = AnnotationAttributes.fromMap(importingClassMetadata.getAnnotationAttributes(ImportBean.class.getName())); 
		// 注入BeanDefinition
		for(Class<?> clazz : annoAttrs.getClassArray("value")) {
			RootBeanDefinition beanDefinition = new RootBeanDefinition(clazz);
			// 根据形参选择构造函数，并用指定值实例化对象
			beanDefinition.getConstructorArgumentValues().addGenericArgumentValue("张三");
			// 修改对象name属性值
			beanDefinition.getPropertyValues().add("name", "李四");
			registry.registerBeanDefinition(clazz.getSimpleName(), beanDefinition);
		}
	}
}
