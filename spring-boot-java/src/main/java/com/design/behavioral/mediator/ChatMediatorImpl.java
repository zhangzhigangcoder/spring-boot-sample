package com.design.behavioral.mediator;

import java.util.ArrayList;
import java.util.List;

/**
 * Concrete Mediator
 * 
 * @author zhangzhigang
 *
 */
public class ChatMediatorImpl implements ChatMediator {
	
	private List<User> users;
	
	public ChatMediatorImpl() {
		this.users = new ArrayList<>();
	}

	@Override
	public void sendMessage(String msg, User user) {
		users.forEach(u -> {if (!u.equals(user)) {u.receive(msg);} });
	}

	@Override
	public void addUser(User user) {
		this.users.add(user);
	}
	
}
