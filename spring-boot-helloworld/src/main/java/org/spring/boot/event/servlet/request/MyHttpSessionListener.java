package org.spring.boot.event.servlet.request;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * 自定义http session listener
 * @author zhangzhigang
 *
 */
public class MyHttpSessionListener implements HttpSessionListener {

	/**
	 * session created
	 * request.getSession(true);
	 */
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		System.out.println("-----------HttpSessionListener:sessionCreated-------------");
	}

	/**
	 * session destroyed
	 */
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		System.out.println("-----------HttpSessionListener:sessionDestroyed-------------");
	}
	
}
