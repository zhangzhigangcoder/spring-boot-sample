package com.core.proxy.jdk;
//package com.sun.proxy;

import com.core.proxy.jdk.Order;
import com.core.proxy.jdk.OrderService;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.UndeclaredThrowableException;

/**
 * ProxyClassFactory 生成的代理类字节码
 * 
 * @author zhangzhigang
 *
 */
public final class $Proxy0 extends Proxy implements OrderService {
	private static final long serialVersionUID = -6310918168123410007L;
	private static Method m1;
	private static Method m2;
	private static Method m3;
	private static Method m4;
	private static Method m0;

	public $Proxy0(InvocationHandler var1) {
      super(var1);
   }

	public final boolean equals(Object var1) {
      try {
         return (Boolean)super.h.invoke(this, m1, new Object[]{var1});
      } catch (RuntimeException | Error var3) {
         throw var3;
      } catch (Throwable var4) {
         throw new UndeclaredThrowableException(var4);
      }
   }

	public final String toString() {
      try {
         return (String)super.h.invoke(this, m2, (Object[])null);
      } catch (RuntimeException | Error var2) {
         throw var2;
      } catch (Throwable var3) {
         throw new UndeclaredThrowableException(var3);
      }
   }

	public final void add(Order var1) {
      try {
         super.h.invoke(this, m3, new Object[]{var1});
      } catch (RuntimeException | Error var3) {
         throw var3;
      } catch (Throwable var4) {
         throw new UndeclaredThrowableException(var4);
      }
   }

	public final void delete(String var1) {
      try {
         super.h.invoke(this, m4, new Object[]{var1});
      } catch (RuntimeException | Error var3) {
         throw var3;
      } catch (Throwable var4) {
         throw new UndeclaredThrowableException(var4);
      }
   }

	public final int hashCode() {
      try {
         return (Integer)super.h.invoke(this, m0, (Object[])null);
      } catch (RuntimeException | Error var2) {
         throw var2;
      } catch (Throwable var3) {
         throw new UndeclaredThrowableException(var3);
      }
   }

	static {
		try {
			m1 = Class.forName("java.lang.Object").getMethod("equals", Class.forName("java.lang.Object"));
			m2 = Class.forName("java.lang.Object").getMethod("toString");
			m3 = Class.forName("com.core.proxy.jdk.OrderService").getMethod("add",
					Class.forName("com.core.proxy.jdk.Order"));
			m4 = Class.forName("com.core.proxy.jdk.OrderService").getMethod("delete",
					Class.forName("java.lang.String"));
			m0 = Class.forName("java.lang.Object").getMethod("hashCode");
		} catch (NoSuchMethodException var2) {
			throw new NoSuchMethodError(var2.getMessage());
		} catch (ClassNotFoundException var3) {
			throw new NoClassDefFoundError(var3.getMessage());
		}
	}
}