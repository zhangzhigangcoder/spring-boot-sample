package com.core.proxy.cglib;

import net.sf.cglib.proxy.FixedValue;


/**
 * 对拦截的所有方法都返回相同值，注意如果返回类型不能转换，则会报错
 * @author zhangzhigang
 *
 */
public class FixedValueInterceptor implements FixedValue {

	@Override
	public Object loadObject() throws Exception {
		return "hello cglib";
	}

}
