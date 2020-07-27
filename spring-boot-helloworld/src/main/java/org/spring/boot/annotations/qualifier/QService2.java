package org.spring.boot.annotations.qualifier;

import org.springframework.stereotype.Service;

@QualifierTest
@Service
public class QService2 implements QInterface {
	
	@Override
	public void m() {
		System.out.println("QService2");
	}
	
}
