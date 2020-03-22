package com.design.behavioral.observer;

public class MyTopicSubscriber implements Observer {

	private String name;
	private Subject topic;
	
	public MyTopicSubscriber(String name) {
		this.name = name;
	}
	
	@Override
	public void update() {
		String msg = (String) topic.getUpdate(this);
		if (null == msg) {
			System.out.println(name + " No new message");
		} else {
			System.out.println(name + " Consuming message " + msg);
		}
	}

	@Override
	public void setSubject(Subject sub) {
		this.topic = sub;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((topic == null) ? 0 : topic.hashCode());
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
		MyTopicSubscriber other = (MyTopicSubscriber) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (topic == null) {
			if (other.topic != null)
				return false;
		} else if (!topic.equals(other.topic))
			return false;
		return true;
	}
	
	
	
	
}
