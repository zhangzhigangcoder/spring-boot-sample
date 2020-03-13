package org.spring.boot.event.servlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 自定义Servlet Context Listener
 * @author zhangzhigang
 *
 */
public class MyServletContextListener implements ServletContextListener {

	/**
	 * web容器初始化
	 */
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("-----------ServletContextListener:contextInitialized------------");
	}

	/**
	 * web容器销毁
	 */
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("-----------ServletContextListener:contextDestroyed------------");
	}
	
}
