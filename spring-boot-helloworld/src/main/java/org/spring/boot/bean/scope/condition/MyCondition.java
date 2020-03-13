package org.spring.boot.bean.scope.condition;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class MyCondition implements Condition {
	
	private static final Logger log = LoggerFactory.getLogger(MyCondition.class);
	
	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		log.info("----------MyCondition.matches-------------");
		return true;
	}
}
