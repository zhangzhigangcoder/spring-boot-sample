package org.spring.boot.test.base;

import org.junit.runner.RunWith;
import org.spring.boot.bean.test.ApplicationTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationTest.class)
public class BaseTest {

}
