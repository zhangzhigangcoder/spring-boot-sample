package org.spring.boot.bean.test;

public class TestObj {

	private static final Integer i;

	private final String s;

	static {
		i = 1;
		System.out.println("---static---");
	}

	{
		s = "11";
		System.out.println("---const---");
	}

	public TestObj() {
		System.out.println("---TestObj---");
	}

	public static void main(String[] args) {
		TestObj t1 = new TestObj();
		TestObj t2 = new TestObj();
	}

}
