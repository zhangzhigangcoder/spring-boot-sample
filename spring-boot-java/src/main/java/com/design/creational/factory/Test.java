package com.design.creational.factory;

public class Test {

	public static void main(String[] args) {
		Computer pc = ComputerFactory.getInstance(ComputerFactory.PC, "2 GB", "500 GB", "2.4 GHz");
		Computer server = ComputerFactory.getInstance(ComputerFactory.SERVER, "16 GB", "1 TB", "2.9 GHz");
		System.out.println("Factory PC Config: " + pc);
		System.out.println("Factory Server Config: " + server);
		
	}
}
