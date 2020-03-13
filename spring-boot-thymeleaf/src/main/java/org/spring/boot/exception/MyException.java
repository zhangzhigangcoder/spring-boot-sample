package org.spring.boot.exception;

public class MyException extends Exception {
	

	private static final long serialVersionUID = 5218850135643700983L;

	public MyException(String message) {
		super(message);
	}
}
