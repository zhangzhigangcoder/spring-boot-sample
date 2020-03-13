package org.spring.boot.config;

import org.spring.boot.bean.scope.MyInitializingBean;
import org.spring.boot.bean.spring.factories.MySpringFactoriesImport;
import org.spring.boot.event.servlet.MyServletContextAttributeListener;
import org.spring.boot.event.servlet.MyServletContextListener;
import org.spring.boot.event.servlet.request.MyHttpSessionAttributeListener;
import org.spring.boot.event.servlet.request.MyHttpSessionListener;
import org.spring.boot.event.servlet.request.MyServletRequestAttributeListener;
import org.spring.boot.event.servlet.request.MyServletRequestListener;
import org.spring.boot.filter.TokenFilter;
import org.spring.boot.properties.OrderProperties;
import org.spring.boot.properties.UserProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

/**
 * 统一配置类
 * @EnableConfigurationProperties作用如下：
 * 	1.将UserProperties文件加入spring容器
 *  2.将ConfigurationPropertiesBindingPostProcessor加入spring容器中(如果容器中不存在)
 *  	该类作用是检测容器中所有添加了@ConfigurationProperties注解类，并进行配置。
 *  	换句话说，如果容器中已经存在了该类，也可以在@ConfigurationProperties注解上直接添加@Configuration注解
 *  	直接进行注入，不过这样不利于同一管理
 * @author zhangzhigang
 *
 */
@Configuration
@EnableConfigurationProperties({UserProperties.class, OrderProperties.class})
public class MyAutoConfiguration {

	/**
	 * 添加 Filter
	 * 等价于  @WebFilter @ServletComponentScan
	 * @return
	 */
//	@Bean
	public FilterRegistrationBean<TokenFilter> tokenFilter() {
		/**
		 *  DelegatingFilterProxyRegistrationBean
		 *  ApplicationContextFacade -> ApplicationContext
		 */
		FilterRegistrationBean<TokenFilter> registration = new FilterRegistrationBean<>();
		registration.setFilter(new TokenFilter());
		registration.setEnabled(false);
		return registration;
	}
	
	/**
	 * 添加 ServletRequestListener
	 * 等价于 @WebListener  @ServletComponentScan
	 * @return
	 */
//	@Bean
	public ServletListenerRegistrationBean<MyServletRequestListener> myServletRequestListener() {
		ServletListenerRegistrationBean<MyServletRequestListener> registration = new ServletListenerRegistrationBean<>();
		registration.setListener(new MyServletRequestListener());
		registration.setEnabled(false);
		return registration;
	}
	
	/**
	 * 添加 ServletRequestAttributeListener
	 */
//	@Bean
	public ServletListenerRegistrationBean<MyServletRequestAttributeListener> myServletRequestAttributeListener() {
		ServletListenerRegistrationBean<MyServletRequestAttributeListener> registration = new ServletListenerRegistrationBean<>();
		registration.setListener(new MyServletRequestAttributeListener());
		registration.setEnabled(false);
		return registration;
	}
	
	/**
	 * 添加 ServletContextListener
	 */
//	@Bean
	public ServletListenerRegistrationBean<MyServletContextListener> myServletContextListener() {
		ServletListenerRegistrationBean<MyServletContextListener> registration = new ServletListenerRegistrationBean<>();
		registration.setListener(new MyServletContextListener());
		registration.setEnabled(false);
		return registration;
	}
	
	/**
	 * 添加 ServletContextAttributeListener
	 */
//	@Bean
	public ServletListenerRegistrationBean<MyServletContextAttributeListener> myServletContextAttributeListener() {
		ServletListenerRegistrationBean<MyServletContextAttributeListener> registration = new ServletListenerRegistrationBean<>();
		registration.setListener(new MyServletContextAttributeListener());
		registration.setEnabled(false);
		return registration;
	}
	
	/**
	 * 添加 HttpSessionListener
	 */
//	@Bean
	public ServletListenerRegistrationBean<MyHttpSessionListener> myHttpSessionListener() {
		ServletListenerRegistrationBean<MyHttpSessionListener> registration = new ServletListenerRegistrationBean<>();
		registration.setListener(new MyHttpSessionListener());
		registration.setEnabled(false);
		return registration;
	}
	
	/**
	 * 添加 HttpSessionAttributeListener
	 */
//	@Bean
	public ServletListenerRegistrationBean<MyHttpSessionAttributeListener> myHttpSessionAttributeListener() {
		ServletListenerRegistrationBean<MyHttpSessionAttributeListener> registration = new ServletListenerRegistrationBean<>();
		registration.setListener(new MyHttpSessionAttributeListener());
		registration.setEnabled(false);
		return registration;
	}
	
	
	/**
	 * 添加 Servlet ServletRegistrationBean
	 * 等价于 @WebServlet @ServletComponentScan
	 */
	
	
	/**
	 * 测试加载顺序
	 * @see MySpringFactoriesImport
	 * @return
	 */
//	@Bean
//	@ConditionalOnMissingBean(SpringFactoryInter.class)
	public SpringFactoryInter springFactoryInter() {
		return new SpringFactorySub();
	}
	
	/**
	 * 测试InitializingBean + initMethod + destroyMethod
	 * @return
	 */
//	@Bean(initMethod="initMethod", destroyMethod="destroyMethod")
//	@Lazy
	public MyInitializingBean myInitializingBean() {
		return new MyInitializingBean();
	}
	
}

