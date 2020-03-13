package org.spring.boot.bean.aware;

import javax.servlet.ServletContext;

import org.springframework.web.context.ServletContextAware;

/**
 * 自定义ServletContextAware
 * @author zhangzhigang
 *
 */
public class MyServletContextAware implements ServletContextAware {

	@Override
	public void setServletContext(ServletContext servletContext) {
	}


}
