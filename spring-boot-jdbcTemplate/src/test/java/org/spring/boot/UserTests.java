package org.spring.boot;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.spring.boot.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTests {
	
	@Autowired
	private IUserService userService;
	
	@Before
	public void setUp() {}
	
	@Test
	public void test() {
//		userService.transaction_success();
		userService.notTransaction__required_not_transaction_exception();
	}
}



