package com.design.jdk.event;

public class Student implements HomeworkListener {

	private String name;
	
	public Student(String name) {
		this.name = name;
	}
	
	@Override
	public void publish(HomeworkEventObject event, String homework) {
		System.out.printf("学生%s观察到%s老师布置了作业<%s>\n", this.name, event.getTeacher().getName(), homework);
	}
}
