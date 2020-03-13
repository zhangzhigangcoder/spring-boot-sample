package org.spring.boot.bean.scope.imports;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @Import注解在@Component扫描之后解析
 * 	1.如果MyImportSelector实现了ImportSelector接口,则调用MyImportSelector.selectImports()方法,获取类集合String[]
 *  2.如果实现了DeferredImportSelector接口,会在ConfigurationClassParser.processDeferredImportSelectors()调用,
 *    例AutoConfigurationImportSelector,这里保证了spring.factories文件在普通@Component类之后加载
 *  3.如果MyImportSelector实现ImportBeanDefinitionRegistrar接口，则调用registerBeanDefinitions()方法，该类不会添加到spring容器中
 *  4.如果MyImportSelector是普通类，则会直接添加到spring容器中
 *  5.步骤1中的String[]集合中的每个类会递归执行1,2,3
 *  6.@Import参数是有顺序的
 * @author zhangzhigang
 *
 */
@Configuration
@Import(MyImportSelector.class)
public class MyImportTest {
	
}
