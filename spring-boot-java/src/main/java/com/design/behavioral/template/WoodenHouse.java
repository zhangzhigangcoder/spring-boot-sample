package com.design.behavioral.template;

public class WoodenHouse extends HouseTmplate {

	@Override
	void buildWalls() {
		System.out.println("Building Wooden Walls");
	}

	@Override
	void buildPillars() {
		System.out.println("Building Pillars with Wood coating");
	}
	
}
