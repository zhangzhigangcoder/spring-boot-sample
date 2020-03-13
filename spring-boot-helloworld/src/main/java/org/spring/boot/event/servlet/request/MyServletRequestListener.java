package org.spring.boot.event.servlet.request;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

/**
 * 自定义Request Listener
 * @author zhangzhigang
 *
 */
public class MyServletRequestListener implements ServletRequestListener {
	
	/**
	 * request init(在filter之前执行)
	 */
	@Override
	public void requestInitialized(ServletRequestEvent sre) {
		System.out.println("------------------->请求创建");
	}
	
	/**
	 * request destroy
	 */
	@Override
	public void requestDestroyed(ServletRequestEvent sre) {
		System.out.println("------------------->请求销毁");
	}
	
}
