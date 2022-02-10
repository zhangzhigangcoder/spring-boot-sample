package com.design.creational.factory.factorymethod;

/**
 * Factory Class
 * 
 * @author zhangzhigang
 */
public class ComputerFactory {
	
	public static final String PC = "PC";
	
	public static final String SERVER = "SERVER";
	
	public static Computer getInstance(String type, String ram, String hdd, String cpu) {
		Computer computer = null;
		
		switch (type) {
		case PC:
			computer = new PC(ram, hdd, cpu);
			break;
		case SERVER:
			computer = new Server(ram, hdd, cpu);
			break;
		}
		return computer;
	}
}
