package com.core.jdk8.grammar;

/**
 *
 */
public class Something {

	public Something() {
	}
	
	public Something(String something) {
		System.out.println(something);
	}
	
	// static methods
	public static String startsWith(String s) {
		return String.valueOf(s.charAt(0));
	}
	
	public String endWith(String s) {
		return String.valueOf(s.charAt(s.length()-1));
	}
	
}
