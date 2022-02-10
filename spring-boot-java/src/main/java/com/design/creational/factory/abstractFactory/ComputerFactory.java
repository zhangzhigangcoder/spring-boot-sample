package com.design.creational.factory.abstractFactory;

/**
 * Factory class for Factories
 * 
 * @author zhangzhigang
 */
public class ComputerFactory {

	public static Computer getInstance(ComputerAbstractFactory factory) {
		return factory.createComputer();
	}

}
