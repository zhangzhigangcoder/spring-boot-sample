package org.spring.boot.aop.cglib.clazz;

import org.spring.boot.aop.cglib.CGLIBUserService;

/**
 * cglib生成的代理子类字节码
 * @author zhangzhigang
 *
 */
public class CGLIBUserService$$EnhancerByCGLIBProxy$$1465604435 extends CGLIBUserService
//  implements Factory
{
//  private boolean CGLIB$BOUND;
//  public static Object CGLIB$FACTORY_DATA;
//  private static final ThreadLocal CGLIB$THREAD_CALLBACKS;
//  private static final Callback[] CGLIB$STATIC_CALLBACKS;
//  private NoOp CGLIB$CALLBACK_0;
//  private MethodInterceptor CGLIB$CALLBACK_1;
//  private FixedValue CGLIB$CALLBACK_2;
//  private static Object CGLIB$CALLBACK_FILTER;
//  private static final Method CGLIB$sayHello$0$Method;
//  private static final MethodProxy CGLIB$sayHello$0$Proxy;
//  private static final Object[] CGLIB$emptyArgs;
//
//  static void CGLIB$STATICHOOK1()
//  {
//    CGLIB$THREAD_CALLBACKS = new ThreadLocal();
//    CGLIB$emptyArgs = new Object[0];
//    Class localClass1 = Class.forName("org.spring.boot.aop.cglib.CGLIBUserService$$EnhancerByCGLIBProxy$$-1465604435");
//    Class localClass2;
//    Method[] tmp50_47 = ReflectUtils.findMethods(new String[] { "sayHello", "(Ljava/lang/String;)Ljava/lang/String;" }, (localClass2 = Class.forName("org.spring.boot.aop.cglib.CGLIBUserService")).getDeclaredMethods());
//    CGLIB$sayHello$0$Method = tmp50_47[0];
//    CGLIB$sayHello$0$Proxy = MethodProxy.create(localClass2, localClass1, "(Ljava/lang/String;)Ljava/lang/String;", "sayHello", "CGLIB$sayHello$0");
//    tmp50_47;
//  }
//
//  final String CGLIB$sayHello$0(String paramString)
//  {
//    return super.sayHello(paramString);
//  }
//
//  public final String sayHello(String paramString)
//  {
//    MethodInterceptor tmp4_1 = this.CGLIB$CALLBACK_1;
//    if (tmp4_1 == null)
//    {
//      tmp4_1;
//      CGLIB$BIND_CALLBACKS(this);
//    }
//    MethodInterceptor tmp17_14 = this.CGLIB$CALLBACK_1;
//    if (tmp17_14 != null)
//      return (String)tmp17_14.intercept(this, CGLIB$sayHello$0$Method, new Object[] { paramString }, CGLIB$sayHello$0$Proxy);
//    return super.sayHello(paramString);
//  }
//
//  public static MethodProxy CGLIB$findMethodProxy(Signature paramSignature)
//  {
//    String tmp4_1 = paramSignature.toString();
//    switch (tmp4_1.hashCode())
//    {
//    case -1816210712:
//      if (tmp4_1.equals("sayHello(Ljava/lang/String;)Ljava/lang/String;"))
//        return CGLIB$sayHello$0$Proxy;
//    }
//  }
//
//  public final String sayBye(String paramString)
//  {
//    FixedValue tmp4_1 = this.CGLIB$CALLBACK_2;
//    if (tmp4_1 == null)
//    {
//      tmp4_1;
//      CGLIB$BIND_CALLBACKS(this);
//    }
//    return (String)this.CGLIB$CALLBACK_2.loadObject();
//  }
//
//  public CGLIBUserService$$EnhancerByCGLIBProxy$$-1465604435()
//  {
//    CGLIB$BIND_CALLBACKS(this);
//  }
//
//  public static void CGLIB$SET_THREAD_CALLBACKS(Callback[] paramArrayOfCallback)
//  {
//    CGLIB$THREAD_CALLBACKS.set(paramArrayOfCallback);
//  }
//
//  public static void CGLIB$SET_STATIC_CALLBACKS(Callback[] paramArrayOfCallback)
//  {
//    CGLIB$STATIC_CALLBACKS = paramArrayOfCallback;
//  }
//
//  private static final void CGLIB$BIND_CALLBACKS(Object paramObject)
//  {
//    // Byte code:
//    //   0: aload_0
//    //   1: checkcast 2	org/spring/boot/aop/cglib/CGLIBUserService$$EnhancerByCGLIBProxy$$-1465604435
//    //   4: astore_1
//    //   5: aload_1
//    //   6: getfield 131	org/spring/boot/aop/cglib/CGLIBUserService$$EnhancerByCGLIBProxy$$-1465604435:CGLIB$BOUND	Z
//    //   9: ifne +61 -> 70
//    //   12: aload_1
//    //   13: iconst_1
//    //   14: putfield 131	org/spring/boot/aop/cglib/CGLIBUserService$$EnhancerByCGLIBProxy$$-1465604435:CGLIB$BOUND	Z
//    //   17: getstatic 31	org/spring/boot/aop/cglib/CGLIBUserService$$EnhancerByCGLIBProxy$$-1465604435:CGLIB$THREAD_CALLBACKS	Ljava/lang/ThreadLocal;
//    //   20: invokevirtual 134	java/lang/ThreadLocal:get	()Ljava/lang/Object;
//    //   23: dup
//    //   24: ifnonnull +15 -> 39
//    //   27: pop
//    //   28: getstatic 129	org/spring/boot/aop/cglib/CGLIBUserService$$EnhancerByCGLIBProxy$$-1465604435:CGLIB$STATIC_CALLBACKS	[Lnet/sf/cglib/proxy/Callback;
//    //   31: dup
//    //   32: ifnonnull +7 -> 39
//    //   35: pop
//    //   36: goto +34 -> 70
//    //   39: checkcast 135	[Lnet/sf/cglib/proxy/Callback;
//    //   42: aload_1
//    //   43: swap
//    //   44: dup2
//    //   45: iconst_2
//    //   46: aaload
//    //   47: checkcast 116	net/sf/cglib/proxy/FixedValue
//    //   50: putfield 114	org/spring/boot/aop/cglib/CGLIBUserService$$EnhancerByCGLIBProxy$$-1465604435:CGLIB$CALLBACK_2	Lnet/sf/cglib/proxy/FixedValue;
//    //   53: dup2
//    //   54: iconst_1
//    //   55: aaload
//    //   56: checkcast 56	net/sf/cglib/proxy/MethodInterceptor
//    //   59: putfield 44	org/spring/boot/aop/cglib/CGLIBUserService$$EnhancerByCGLIBProxy$$-1465604435:CGLIB$CALLBACK_1	Lnet/sf/cglib/proxy/MethodInterceptor;
//    //   62: iconst_0
//    //   63: aaload
//    //   64: checkcast 137	net/sf/cglib/proxy/NoOp
//    //   67: putfield 139	org/spring/boot/aop/cglib/CGLIBUserService$$EnhancerByCGLIBProxy$$-1465604435:CGLIB$CALLBACK_0	Lnet/sf/cglib/proxy/NoOp;
//    //   70: return
//  }
//
//  public Object newInstance(Callback[] paramArrayOfCallback)
//  {
//    CGLIB$SET_THREAD_CALLBACKS(paramArrayOfCallback);
//    CGLIB$SET_THREAD_CALLBACKS(null);
//    return new -1465604435();
//  }
//
//  public Object newInstance(Callback paramCallback)
//  {
//    throw new IllegalStateException("More than one callback object required");
//  }
//
//  public Object newInstance(Class[] paramArrayOfClass, Object[] paramArrayOfObject, Callback[] paramArrayOfCallback)
//  {
//    CGLIB$SET_THREAD_CALLBACKS(paramArrayOfCallback);
//    Class[] tmp9_8 = paramArrayOfClass;
//    switch (tmp9_8.length)
//    {
//    case 0:
//      tmp9_8;
//      break;
//    default:
//      new -1465604435();
//      throw new IllegalArgumentException("Constructor not found");
//    }
//    CGLIB$SET_THREAD_CALLBACKS(null);
//  }
//
//  public Callback getCallback(int paramInt)
//  {
//    CGLIB$BIND_CALLBACKS(this);
//    switch (paramInt)
//    {
//    case 0:
//      break;
//    case 1:
//      break;
//    case 2:
//      break;
//    }
//    return null;
//  }
//
//  public void setCallback(int paramInt, Callback paramCallback)
//  {
//    switch (paramInt)
//    {
//    case 0:
//      this.CGLIB$CALLBACK_0 = ((NoOp)paramCallback);
//      break;
//    case 1:
//      this.CGLIB$CALLBACK_1 = ((MethodInterceptor)paramCallback);
//      break;
//    case 2:
//      this.CGLIB$CALLBACK_2 = ((FixedValue)paramCallback);
//      break;
//    }
//  }
//
//  public Callback[] getCallbacks()
//  {
//    CGLIB$BIND_CALLBACKS(this);
//    return new Callback[] { this.CGLIB$CALLBACK_0, this.CGLIB$CALLBACK_1, this.CGLIB$CALLBACK_2 };
//  }
//
//  public void setCallbacks(Callback[] paramArrayOfCallback)
//  {
//    Callback[] tmp2_1 = paramArrayOfCallback;
//    this.CGLIB$CALLBACK_0 = ((NoOp)tmp2_1[0]);
//    Callback[] tmp11_2 = tmp2_1;
//    this.CGLIB$CALLBACK_1 = ((MethodInterceptor)tmp11_2[1]);
//    this.CGLIB$CALLBACK_2 = ((FixedValue)tmp11_2[2]);
//  }
//
//  static
//  {
//    CGLIB$STATICHOOK1();
//  }
}