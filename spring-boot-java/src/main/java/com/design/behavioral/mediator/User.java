package com.design.behavioral.mediator;

/**
 * colleague Interface
 * 
 * @author zhangzhigang
 *
 */
public abstract class User {
	
	protected ChatMediator mediator;
	
	protected String name;
	
	public User(ChatMediator med, String name) {
		this.mediator = med;
		this.name = name;
	}
	
	abstract void send(String msg);
	
	abstract void receive(String msg);

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
}
