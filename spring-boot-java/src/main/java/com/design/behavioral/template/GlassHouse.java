package com.design.behavioral.template;

public class GlassHouse extends HouseTmplate {

	@Override
	void buildWalls() {
		System.out.println("Building Glass Walls");
	}

	@Override
	void buildPillars() {
		System.out.println("Building Pillars with glass coating");
	}
	
}
