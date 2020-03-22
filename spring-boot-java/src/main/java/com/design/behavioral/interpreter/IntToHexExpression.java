package com.design.behavioral.interpreter;

public class IntToHexExpression implements Expression {

	private int i;
	
	public IntToHexExpression(int c) {
		this.i = c;
	}
	
	@Override
	public String interpret(InterpreterContext context) {
		return context.getHexadecimalFormat(i);
	}

	
}
