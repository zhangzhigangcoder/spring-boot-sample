package org.spring.boot.bean.scope.condition;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 自定义Conditional
 * 调用：在@Component扫描后进行判断
 * 
 * @author zhangzhigang
 *
 */
//@Configuration
@MyConditional
public class MyConditionalTest {
	
	private static final Logger log = LoggerFactory.getLogger(MyConditionalTest.class);
	
	// 注意此处会根据参数类型自动注入
	public MyConditionalTest() {
		log.info("----------MyConditionalTest-------------");
	}
}
