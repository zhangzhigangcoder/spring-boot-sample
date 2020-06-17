package com.core.data.struct.stack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * 栈应用：实现逆波兰(后缀表达式)计算器
 *
 */
public class PolandNotation {

	// 运算符优先级定义
	private static Map<String, Integer> operations = new HashMap<String, Integer>() {
		private static final long serialVersionUID = 3082911361946594649L;

		{
			put("(", -1);
			put("+", 1);
			put("-", 1);
			put("*", 2);
			put("/", 2);
		}
	};
	
	public static void main(String[] args) {
		List<String> list = parseToSuffixExpressionList("2+(3+4)*5/35-6");
		list.forEach(e -> System.out.print(e + "\t"));
		int res = calculate(list);
		System.out.println();
		System.out.println("计算结果:" + res);
	}
	
	/**
	 * 表达式计算
	 * @param suffixExpression
	 * @return
	 */
	public static int calculate(List<String>  suffixExpressList) {
		Stack<String> stack = new Stack<>();
		suffixExpressList.forEach(e -> {
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
	
	/**
	 * 将表达式转换成List
	 * @param expression
	 * @return
	 */
	private static List<String> parseToList(String expression) {
		List<String> list = new ArrayList<String>();
		String[] str = 	expression.split("\\d+");
		int index = 0, idx1 = 0, idx2 = 0;
		for (int i = 0; i < str.length - 1; i++) {
			if ("".equals(str[i])) {
				continue;
			}
			idx1 = expression.indexOf(str[i], index);
			idx2 = expression.indexOf(str[i+1], idx1 + 1);
			if (list.size() == 0 && idx1 != 0) {
				list.add(expression.substring(0, idx1));
			}
			for (char ch : str[i].toCharArray()) {
				list.add(String.valueOf(ch));
			}
			list.add(expression.substring(idx1 + str[i].length(), idx2));
			if (i == str.length - 2) {
				for (char ch : str[i+1].toCharArray()) {
					list.add(String.valueOf(ch));
				}
				list.add(expression.substring(idx2 + str[i+1].length(), expression.length()));
			}
			index = idx1 + str[i].length();
		}
		return list;
	}
	
	/**
	 * 将中缀表达式转换成List
	 * @param expression
	 * @return
	 */
	public static List<String> parseToSuffixExpressionList(String expression) {
		List<String> expressList = parseToList(expression);
		Stack<String> operStack = new Stack<>(); // 符号栈
		List<String> result = new ArrayList<>(); // 存放中间结果
		for (String item : expressList) {
			// 判断类型
			if (item.matches("\\d+")) { 
				// 是数字，将其压入ls
				result.add(item);
			} else if (item.equals("(")) { 
				// 是左括号，则直接压入operStack
				operStack.push(item);
			} else if (item.equals(")")) { // 是右括号
				// 是右括号")",则依次弹出s1栈顶的运算符，并压入ls，直到遇到左括号为止，此时将这一对括号丢弃
				while (!operStack.peek().equals("(")) {
					result.add(operStack.pop());
				}
				operStack.pop(); // 将"("弹出operStack栈，即清除小括号
			} else {
				// 是运算符,当优先级小于等于operStack栈顶运算符时，将operStack栈顶运算符弹出并加入ls中
				// 再次转到d1与operStack栈中新的栈顶运算符相比较
				while (operStack.size() != 0 && operations.get(item) <= operations.get(operStack.peek())) {
					result.add(operStack.pop());
				}
				// 否则直接压入(operStack为空 || 栈顶为"(" || item优先级比栈顶高)
				operStack.push(item);
			}
		}
		// 把operStack中剩余的运算符依次弹出并加入ls中
		while (operStack.size() != 0) {
			result.add(operStack.pop());
		}
		return result;
	}
}