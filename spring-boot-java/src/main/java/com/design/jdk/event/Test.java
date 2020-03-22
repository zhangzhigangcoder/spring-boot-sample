package com.design.jdk.event;

public class Test {
	
	public static void main(String[] args) {
		Student s1 = new Student("zhang san");
		Student s2 = new Student("li si");
		Teacher t1 = new Teacher("lao wang");
		t1.addHomeworkListener(s1);
		t1.addHomeworkListener(s2);
		t1.addHomework("写一篇作业");
	}
}
