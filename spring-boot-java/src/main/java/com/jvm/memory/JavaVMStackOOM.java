package com.jvm.memory;

/**
 * 
 * 栈深度大于虚拟机允许的最大深度OutOfMemoryError 没有测出来
 * -Xss2M
 * 
 * @author zhangzhigang
 *
 */
public class JavaVMStackOOM {

	private void dontStop() {
		while(true) {}
	}
	
	public void stackLeakByThread() {
		while(true) {
			Thread t = new Thread(() -> dontStop());
			t.start();
		}
	}
	
	public static void main(String[] args) throws Exception {
		JavaVMStackOOM oom = new JavaVMStackOOM();
		oom.stackLeakByThread();
	}
	
	
}
