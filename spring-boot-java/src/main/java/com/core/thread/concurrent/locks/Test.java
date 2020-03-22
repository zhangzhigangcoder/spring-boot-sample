package com.core.thread.concurrent.locks;

public class Test {

	static boolean stop = false;

	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
//		int SHARED_SHIFT = 16;
//		int SHARED_UNIT = (1 << SHARED_SHIFT);
//		int EXCLUSIVE_MASK = (1 << SHARED_SHIFT) - 1;
//		// 1 00000000 00000000
//		// 11111111 11111111
//		System.out.println(Integer.toBinaryString(SHARED_UNIT));
//		System.out.println(Integer.toBinaryString(EXCLUSIVE_MASK));
		System.out.println(new ThreadLocalHold().get());
	}

}

class ThreadLocalHold extends ThreadLocal<String> {

	@Override
	protected String initialValue() {
		return "123";
	}
}
