package com.design.creational.factory.abstractFactory;

public class Test {

	public static void main(String[] args) {
		PCFactory pcFactory = new PCFactory("2 GB", "500 GB", "2.4 GHz");
		Computer pc = ComputerFactory.getInstance(pcFactory);
		
		ServerFactory serverFactory = new ServerFactory("16 GB", "1 TB", "2.9 GHz");
		Computer server = ComputerFactory.getInstance(serverFactory);
		
		System.out.println("AbstractFactory PC Config: " + pc);
		System.out.println("AbstractFactory Server Config: " + server);
	}
	
}
