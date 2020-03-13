package org.spring.boot.bean.scope.imports;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(MyImportBeanDefinitionRegistrar.class)
public @interface ImportBean {
	
	/**
	 * 要注入Class数组
	 * @return
	 */
	Class<?>[] value() default {};
}
