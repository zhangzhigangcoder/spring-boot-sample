package com.design.structural.proxy;

/**
 *  Target Class
 *  
 * @author zhangzhigang
 *
 */
public class CommandExecutorImpl implements CommandExecutor {

	@Override
	public void runCommand(String cmd) throws Exception {
		// some heavy implementation
		Runtime.getRuntime().exec(cmd);
		System.out.println("'" + cmd + "' command executed.");
	}
	
}
