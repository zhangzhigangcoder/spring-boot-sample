package com.core.innerclass;

import com.core.innerclass.OuterClass.InnerClass;
import com.core.innerclass.OuterClass.StaticNestedClass;

public class Test {
	
	public static void main(String[] args) {
		OuterClass outer = new OuterClass(1, 2, 3, 4);
		
		// static nested classes example
		StaticNestedClass staticNestedClass = new StaticNestedClass();
		StaticNestedClass staticNestedClass2 = new StaticNestedClass();
		
		System.out.println(staticNestedClass.getName());
		staticNestedClass.d = 10;
		System.out.println(staticNestedClass.d);
		System.out.println(staticNestedClass2.d);
		
		// inner class example
		InnerClass innerClass = outer.new InnerClass();
		System.out.println(innerClass.getName());
		System.out.println(innerClass);
		innerClass.setValues();
		System.out.println(innerClass);
		
		// calling method using local inner class
		outer.print("Outer");
		
		// calling method using anonymous inner class
	}

}
