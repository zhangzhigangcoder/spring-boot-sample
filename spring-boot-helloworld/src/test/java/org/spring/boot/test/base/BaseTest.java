package org.spring.boot.test.base;

import org.junit.runner.RunWith;
import org.spring.boot.ApplicationTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 配置文件读取规则：
 *  配置文件优先使用test/respurces下面的
 * 如果@SpringBootTest中配置了类，就用该类中的扫描配置，扫描不到测试类
 * 如果上面没有配置类，就从当前测试类所在包逐层向外面找带有@SpringBootConfiguration注解的类(通常@SpringBootApplication)
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationTest.class)
//@ActiveProfiles("dev")
public class BaseTest {

}
