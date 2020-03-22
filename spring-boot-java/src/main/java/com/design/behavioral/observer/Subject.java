package com.design.behavioral.observer;

public interface Subject {
	
	// methods to register and unregister observer
	void register(Observer obj);
	void unregister(Observer obj);
	
	// method to notify observers of change
	void notifyObservers();
	
	// method to get updates form subject
	Object getUpdate(Observer obj);
}
