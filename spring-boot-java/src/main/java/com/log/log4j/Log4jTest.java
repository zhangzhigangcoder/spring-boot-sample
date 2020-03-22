package com.log.log4j;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Log4jTest {
	
	private static Logger logger = Logger.getLogger(Log4jTest.class);
	
	public static void main(String[] args) {
		PropertyConfigurator.configure("src/main/resources/config/log4j.properties");
		logger.debug("this is a debug message.");
		logger.info("this is a info message.");
		logger.error("this is a error message.");
	}
	
}
