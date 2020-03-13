package org.spring.boot.event.servlet;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;

/**
 * 自定义Servlet Context Attribute Listener
 * @author zhangzhigang
 *
 */
public class MyServletContextAttributeListener implements ServletContextAttributeListener {

	/**
	 * request.getServletContext().setAttribute(String name, Object object);
	 */
	@Override
	public void attributeAdded(ServletContextAttributeEvent scae) {
		System.out.println("----ServletContextAttributeListener:attributeAdded:" + scae.getName() + "-----");
	}

	@Override
	public void attributeRemoved(ServletContextAttributeEvent scae) {
		System.out.println("----ServletContextAttributeListener:attributeRemoved:" + scae.getName() + "-----");
	}

	@Override
	public void attributeReplaced(ServletContextAttributeEvent scae) {
		System.out.println("----ServletContextAttributeListener:attributeReplaced:" + scae.getName() + "-----");
	}

	
}
