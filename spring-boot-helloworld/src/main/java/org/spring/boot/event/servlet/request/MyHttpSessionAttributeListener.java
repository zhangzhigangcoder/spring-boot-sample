package org.spring.boot.event.servlet.request;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

/**
 * 自定义 http session attribute listener
 * @author zhangzhigang
 *
 */
public class MyHttpSessionAttributeListener implements HttpSessionAttributeListener {

	/**
	 * request.getSession(true).setAttribute("name", "zhang");
	 */
	@Override
	public void attributeAdded(HttpSessionBindingEvent se) {
		System.out.println("----HttpSessionAttributeListener:attributeAdded:" + se.getName() + "-----");
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent se) {
		System.out.println("----HttpSessionAttributeListener:attributeRemoved:" + se.getName() + "-----");
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent se) {
		System.out.println("----HttpSessionAttributeListener:attributeReplaced:" + se.getName() + "-----");
	}

	
}
