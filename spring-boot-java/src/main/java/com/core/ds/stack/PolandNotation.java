package com.core.ds.stack;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 栈应用：实现逆波兰(后缀表达式)计算器
 *
 */
public class PolandNotation {

	public static void main(String[] args) {
		// (3+4)*5/35-6 -> "3 4 + 5 * 35 / 6 -"
		String suffixExpression = "3 4 + 5 * 35 / 6 -";
		int res = calculate(suffixExpression);
		System.out.println(res);
	}
	
	/**
	 * 表达式计算
	 * @param suffixExpression
	 * @return
	 */
	public static int calculate(String  suffixExpression) {
		List<String> list = Arrays.asList(suffixExpression.split(" "));
		Stack<String> stack = new Stack<>();
		list.forEach(e -> {
			if (e.matches("\\d+")) { // 匹配数字
				stack.push(e);
			} else {
				// pop出两个数，并运算，再入栈
				int num2 = Integer.parseInt(stack.pop());
				int num1 = Integer.parseInt(stack.pop());
				int res = cal(num1, num2, e);
				// 计算结果入栈
				stack.push(res + "");
			}
		});
		return Integer.parseInt(stack.pop());
	}
	
	/**
	 * 运算
	 * 
	 * @param num1
	 * @param num2
	 * @param oper
	 * @return
	 */
	public static int cal(int num1, int num2, String oper) {
		int res = 0;
		switch (oper) {
		case "+":
			res = num1 + num2;
			break;
		case "-":
			res = num1 - num2;
			break;
		case "*":
			res = num1 * num2;
			break;
		case "/":
			res = num1 / num2;
			break;
		}
		return res;
	}
	
}