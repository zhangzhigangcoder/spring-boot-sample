package com.design.behavioral.mediator;

/**
 * Mediator Interface
 * 
 * @author zhangzhigang
 *
 */
public interface ChatMediator {
	
	void sendMessage(String msg, User user);
	
	void addUser(User user);
}
