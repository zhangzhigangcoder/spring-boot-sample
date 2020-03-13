package org.spring.boot.strategy.keygen;

public class Test {
	public static void main(String[] args) {
		SnowflakeKeyGenerator keyGenerator = new SnowflakeKeyGenerator();
		String pk;
		try {
			pk = keyGenerator.generateKey().toString();
			System.out.println(pk);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}