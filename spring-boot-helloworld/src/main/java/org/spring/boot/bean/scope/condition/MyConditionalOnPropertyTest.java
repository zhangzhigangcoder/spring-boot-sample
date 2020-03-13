package org.spring.boot.bean.scope.condition;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

/**
 * 测试@ConditionalOnProperty
 * 	key = prefix + name
 * 	1.key不存在,且matchIfMissing=true,返回true，反之false
 *  2.key存在
 *  	2.1 havingValue为空，key对应的value不等于false，返回true，反之false
 *  	2.2 havingValue不为空，key对应的value等于havingValue，返回true,反之false
 *  	
 * @author zhangzhigang
 *
 */
@Configuration
@ConditionalOnProperty(prefix = "boot.user", name = "name", havingValue = "zhangzhigang", matchIfMissing = true)
public class MyConditionalOnPropertyTest {
	
	public MyConditionalOnPropertyTest() {
		System.out.println("----------MyConditionalOnPropertyTest-------------");
	}
	
}
