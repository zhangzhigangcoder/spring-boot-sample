package com.design.behavioral.state;

public class TVStopState implements State{

	@Override
	public void doAction() {
		System.out.println("TV is turned OFF");
	}
	
}