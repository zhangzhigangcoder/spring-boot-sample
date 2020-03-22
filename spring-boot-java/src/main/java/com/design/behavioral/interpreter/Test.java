package com.design.behavioral.interpreter;

public class Test {
	
	public static void main(String[] args) {
		String str1 = "28 in Binary";
		String str2 = "28 in Hexadecimal";
		
		InterpreterClient client = new InterpreterClient(new InterpreterContext());
		System.out.println(str1 + "= " + client.interpret(str1));
		System.out.println(str2 + "= " + client.interpret(str2));
	}
}
