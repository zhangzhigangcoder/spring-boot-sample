package com.design.creational.builder;

public class Test {

	public static void main(String[] args) {
		Computer computer = new Computer.ComputerBuilder("500 GB", "2 GB")
							.setBluetoothEnabled(true)
							.setGraphicsGardEnabled(true).build();
		System.out.println(computer);
	}
	
}
