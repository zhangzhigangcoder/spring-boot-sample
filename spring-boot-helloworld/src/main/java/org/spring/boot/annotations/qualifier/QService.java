package org.spring.boot.annotations.qualifier;

@QualifierTest
//@Service
public class QService implements QInterface {
	
	@Override
	public void m() {
		System.out.println("QService");
	}
	
}
