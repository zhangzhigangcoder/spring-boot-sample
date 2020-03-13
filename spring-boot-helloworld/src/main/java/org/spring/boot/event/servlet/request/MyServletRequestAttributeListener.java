package org.spring.boot.event.servlet.request;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;

/**
 * 自定义Request Attribute Listener
 * @author zhangzhigang
 *
 */
public class MyServletRequestAttributeListener implements ServletRequestAttributeListener {

	/**
	 * request.setAttribute(name, o);
	 */
	@Override
	public void attributeAdded(ServletRequestAttributeEvent srae) {
//		System.out.println("---ServletRequestAttributeListener:attributeAdded:" + srae.getName() + "---");
	}

	@Override
	public void attributeRemoved(ServletRequestAttributeEvent srae) {
//		System.out.println("---ServletRequestAttributeListener:attributeRemoved:" + srae.getName() + "---");
	}

	@Override
	public void attributeReplaced(ServletRequestAttributeEvent srae) {
//		System.out.println("---ServletRequestAttributeListener:attributeReplaced:" + srae.getName() + "---");
	}
	
}
