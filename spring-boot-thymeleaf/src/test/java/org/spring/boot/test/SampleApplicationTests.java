package org.spring.boot.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.spring.boot.controller.MainController;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SampleApplicationTests {

	@Test
	public void homePagetTest() {
		System.out.println(new MainController().homePage());
	}

}
