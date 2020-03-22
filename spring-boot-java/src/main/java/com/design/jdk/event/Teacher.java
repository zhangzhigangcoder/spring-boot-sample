package com.design.jdk.event;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Teacher {
	private String name;
	private List<String> homeworks;
	private Set<HomeworkListener> homeworkListenerList;
	
	public Teacher(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void addHomework(String homework) {
		if (null == this.homeworks) {
			this.homeworks = new ArrayList<>();
		}
		System.out.printf("%s布置了作业: %s\n", this.name, homework);
		this.homeworks.add(homework);
		HomeworkEventObject event = new HomeworkEventObject(this);
		if (null != this.homeworkListenerList) {
			this.homeworkListenerList.forEach(listener -> {
				listener.publish(event, homework);
			});
		}
	}

	public void addHomeworkListener(HomeworkListener homeworkListener) {
		if (null == this.homeworkListenerList) {
			this.homeworkListenerList = new HashSet<>();
		}
		this.homeworkListenerList.add(homeworkListener);
	}
	
	
	
	
}
