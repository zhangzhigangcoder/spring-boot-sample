package com.design.jdk.event;

import java.util.EventObject;

public class HomeworkEventObject extends EventObject {

	private static final long serialVersionUID = -7453080042723393286L;

	public HomeworkEventObject(Object source) {
		super(source);
	}
	
	public Teacher getTeacher() {
		return (Teacher) super.getSource();
	}

}
