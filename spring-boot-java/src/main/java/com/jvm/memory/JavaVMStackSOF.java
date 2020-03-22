package com.jvm.memory;

/**
 * 
 * 栈深度大于虚拟机允许的最大深度StackOverflowError
 * -Xss128k
 * 
 * @author zhangzhigang
 *
 */
public class JavaVMStackSOF {
	
	private int stackLength = 1;
	
	public void stackLeak() {
		stackLength++;
		stackLeak();
	}
	
	public static void main(String[] args) {
		JavaVMStackSOF oom = new JavaVMStackSOF();
		try {
			oom.stackLeak();
		} catch (Exception e) {
			System.out.println("stack length: " + oom.stackLength);
			throw e;
		}
	}
	
	
}
