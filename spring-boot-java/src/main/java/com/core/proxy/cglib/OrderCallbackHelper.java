package com.core.proxy.cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.CallbackHelper;


/**
 * @author zhangzhigang
 *
 */
@SuppressWarnings("rawtypes")
public class OrderCallbackHelper extends CallbackHelper {

	public OrderCallbackHelper(Class superclass, Class[] interfaces) {
		super(superclass, interfaces);
	}

	@Override
	protected Object getCallback(Method method) {
//		if (method.getDeclaringClass() != Object.class && method.getReturnType() == String.class) {
//			return new FixedValueInterceptor();
//		}
		return new FixedValueInterceptor();
//		return NoOp.INSTANCE;
	}




}
