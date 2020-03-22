package com.design.behavioral.mediator;

/**
 * Concrete Colleague
 * 
 * @author zhangzhigang
 *
 */
public class UserImpl extends User{
	
	public UserImpl(ChatMediator med, String name) {
		super(med, name);
	}

	@Override
	void send(String msg) {
		System.out.println(this.name + ": Sending Message= " + msg);
		mediator.sendMessage(msg, this);
	}

	@Override
	void receive(String msg) {
		System.out.println(this.name + ": Received Message: " + msg);
	}
}
