package com.design.behavioral.template;

public abstract class HouseTmplate {
	
	/**
	 *  template method,final so subclasses can't override
	 */
	public final void buildHouse() {
		buildFoundation();
		buildPillars();
		buildWalls();
		buildWindows();
		System.out.println("House is built.");
	}
	
	// default implementation
	private void buildWindows() {
		System.out.println("Building Glass Windows");
	}
	
	// methos to be implemented by subclasses
	abstract void buildWalls();
	abstract void buildPillars();
	
	private void buildFoundation() {
		System.out.println("Building foundation with cement,iron rods and sand");
	}
}
