/**
 * Creation Date:2016年8月3日-下午5:02:57
 * <p>
 * Copyright 2008-2016 © 同程网 Inc. All Rights Reserved
 */
package com.core.base.validate;


import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.groups.Default;

/**
 * 验证实体工具类 <br/>
 */
public class ValidationUtils {

    private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    /**
     * 验证对象属性
     * @param obj 传入的Object
     */
    public static <T> void validateBean(T obj) {
        Set<ConstraintViolation<T>> set = validator.validate(obj,Default.class);
        if (null != set && !set.isEmpty()) {
            Optional<ConstraintViolation<T>> op = set.parallelStream().findFirst();
            if (op.isPresent()) {
                ConstraintViolation<T> cv = op.get();
                System.out.println(cv.getMessage());
            }
        }
    }
    
    
    public static void main(String[] args) {
    	UserBo user = new UserBo();
    	user.setAccount("123");
    	user.setDeviceCode("666");
    	user.setDeviceType(1);
    	user.setMobile("12345678912");
    	ValidationUtils.validateBean(user);
	}
    
}
