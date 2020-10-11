package com.ds.stack;

import java.util.Arrays;

/**
 * 栈应用：实现中缀表达式计算器
 *
 */
public class Calculator {

	private String expression; // 待计算表达式
	private ArrayStack numStack; // 数栈
	private ArrayStack operStack; // 操作符栈

	public Calculator(String expression, int maxSize) {
		this.expression = expression;
		numStack = new ArrayStack(maxSize);
		operStack = new ArrayStack(maxSize);
	}

	/**
	 * 计算
	 * 
	 * @return
	 */
	public int calculate() {
		int index = 0, num1 = 0, num2 = 0, oper = 0, res = 0;
		char ch = ' ';
		while (true) {
			// 扫描
			ch = expression.subSequence(index, index + 1).charAt(0);
			// 判断
			if (isOper(ch)) { // 是运算符
				if (!operStack.isEmpty()) {
					// 比较优先级
					if (priority(ch) <= priority(operStack.peek())) {
						num1 = numStack.pop();
						num2 = numStack.pop();
						oper = operStack.pop();
						res = cal(num1, num2, oper);
						// 把计算结果入栈
						numStack.push(res);
					}
				}
				// 当前操作符入栈
				operStack.push(ch);
			} else { // 是运算数
				numStack.push(ch - 48);// '1' -> 49
			}
			index++;
			if (index == expression.length()) {
				break;
			}
		}
		while (true) {
			// 符号栈为空，则计算结束
			if (operStack.isEmpty()) {
				break;
			}
			num1 = numStack.pop();
			num2 = numStack.pop();
			oper = operStack.pop();
			res = cal(num1, num2, oper);
			// 当前操作符入栈
			numStack.push(res);
		}
		return numStack.pop();
	}

	/**
	 * 返回运算符优先级
	 * 
	 * @param oper
	 * @return
	 */
	public static int priority(int oper) {
		if (Arrays.asList('*', '/').contains((char) oper)) {
			return 1;
		} else if (Arrays.asList('+', '-').contains((char) oper)) {
			return 0;
		} else {
			return -1;
		}
	}

	/**
	 * 判断是否是运算符
	 * 
	 * @param val
	 * @return
	 */
	public static boolean isOper(char val) {
		return Arrays.asList('*', '/', '+', '-').contains(val);
	}

	/**
	 * 运算
	 * 
	 * @param num1
	 * @param num2
	 * @param oper
	 * @return
	 */
	public static int cal(int num1, int num2, int oper) {
		int res = 0;
		switch (oper) {
		case '+':
			res = num1 + num2;
			break;
		case '-':
			res = num2 - num1;
			break;
		case '*':
			res = num1 * num2;
			break;
		case '/':
			res = num2 / num1;
			break;
		}
		return res;
	}
}