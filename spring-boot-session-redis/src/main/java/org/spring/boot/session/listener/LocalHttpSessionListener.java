package org.spring.boot.session.listener;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.context.annotation.Configuration;

/**
 * session生命周期监听
 * 
 */
@Configuration
public class LocalHttpSessionListener implements HttpSessionListener {
	
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		HttpSession session = se.getSession();
		System.out.println(String.format("session[id=%s]被创建", session.getId()));
	}
	
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		HttpSession session = se.getSession();
		System.out.println(String.format("session[id=%s]已过期", session.getId()));
	}
	
}
