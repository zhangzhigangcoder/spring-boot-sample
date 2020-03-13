package org.spring.boot.aop.cglib.clazz;

/**
 * cglib生成的 f1(FastClass)子类字节码
 * @author zhangzhigang
 *
 */
public class CGLIBUserService$$FastClassByCGLIBProxy$$1888674521
//extends FastClass
{
//  public CGLIBUserService$$FastClassByCGLIBProxy$$-1888674521(Class paramClass)
//  {
//    super(paramClass);
//  }
//
//  public int getIndex(Signature paramSignature)
//  {
//    String tmp4_1 = paramSignature.toString();
//    switch (tmp4_1.hashCode())
//    {
//    case -1844017852:
//      if (tmp4_1.equals("sayBye(Ljava/lang/String;)Ljava/lang/String;"))
//        return 2;
//    case -1816210712:
//    case 1826985398:
//    case 1913648695:
//    case 1984935277:
//    case 2070406617:
//    }
//  }
//
//  public int getIndex(String paramString, Class[] paramArrayOfClass)
//  {
//    String tmp3_0 = paramString;
//    switch (tmp3_0.hashCode())
//    {
//    case -2012993625:
//      if (tmp3_0.equals("sayHello"))
//      {
//        Class[] tmp72_1 = paramArrayOfClass;
//        switch (tmp72_1.length)
//        {
//        case 1:
//          Class[] tmp92_72 = tmp72_1;
//          if (!tmp92_72[0].getName().equals("java.lang.String"))
//            break;
//          tmp92_72;
//          return 0;
//        default:
//          break;
//        }
//      }
//    case -1776922004:
//    case -1295482945:
//    case -909361405:
//    case 100184:
//    case 147696667:
//    }
//  }
//
//  public int getIndex(Class[] paramArrayOfClass)
//  {
//    Class[] tmp1_0 = paramArrayOfClass;
//    switch (tmp1_0.length)
//    {
//    case 0:
//      tmp1_0;
//      return 0;
//    }
//    break label26;
//  }
//
//  // ERROR //
//  public Object invoke(int paramInt, Object paramObject, Object[] paramArrayOfObject)
//    throws InvocationTargetException
//  {
//    // Byte code:
//    //   0: aload_2
//    //   1: checkcast 69	org/spring/boot/aop/cglib/CGLIBUserService
//    //   4: iload_1
//    //   5: tableswitch	default:+100 -> 105, 0:+39->44, 1:+49->54, 2:+59->64, 3:+69->74, 4:+84->89, 5:+88->93
//    //   45: iconst_0
//    //   46: aaload
//    //   47: checkcast 41	java/lang/String
//    //   50: invokevirtual 72	org/spring/boot/aop/cglib/CGLIBUserService:sayHello	(Ljava/lang/String;)Ljava/lang/String;
//    //   53: areturn
//    //   54: aload_3
//    //   55: iconst_0
//    //   56: aaload
//    //   57: checkcast 41	java/lang/String
//    //   60: invokevirtual 74	org/spring/boot/aop/cglib/CGLIBUserService:eat	(Ljava/lang/String;)Ljava/lang/String;
//    //   63: areturn
//    //   64: aload_3
//    //   65: iconst_0
//    //   66: aaload
//    //   67: checkcast 41	java/lang/String
//    //   70: invokevirtual 76	org/spring/boot/aop/cglib/CGLIBUserService:sayBye	(Ljava/lang/String;)Ljava/lang/String;
//    //   73: areturn
//    //   74: aload_3
//    //   75: iconst_0
//    //   76: aaload
//    //   77: invokevirtual 77	org/spring/boot/aop/cglib/CGLIBUserService:equals	(Ljava/lang/Object;)Z
//    //   80: new 79	java/lang/Boolean
//    //   83: dup_x1
//    //   84: swap
//    //   85: invokespecial 82	java/lang/Boolean:<init>	(Z)V
//    //   88: areturn
//    //   89: invokevirtual 83	org/spring/boot/aop/cglib/CGLIBUserService:toString	()Ljava/lang/String;
//    //   92: areturn
//    //   93: invokevirtual 84	org/spring/boot/aop/cglib/CGLIBUserService:hashCode	()I
//    //   96: new 86	java/lang/Integer
//    //   99: dup_x1
//    //   100: swap
//    //   101: invokespecial 89	java/lang/Integer:<init>	(I)V
//    //   104: areturn
//    //   105: goto +12 -> 117
//    //   108: new 67	java/lang/reflect/InvocationTargetException
//    //   111: dup_x1
//    //   112: swap
//    //   113: invokespecial 94	java/lang/reflect/InvocationTargetException:<init>	(Ljava/lang/Throwable;)V
//    //   116: athrow
//    //   117: new 96	java/lang/IllegalArgumentException
//    //   120: dup
//    //   121: ldc 98
//    //   123: invokespecial 101	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
//    //   126: athrow
//    //
//    // Exception table:
//    //   from	to	target	type
//    //   5	108	108	java/lang/Throwable
//  }
//
//  public Object newInstance(int paramInt, Object[] paramArrayOfObject)
//    throws InvocationTargetException
//  {
//    try
//    {
//      switch (paramInt)
//      {
//      case 0:
//        return new CGLIBUserService();
//      }
//    }
//    catch (Throwable localThrowable)
//    {
//      throw new InvocationTargetException(localThrowable);
//    }
//    throw new IllegalArgumentException("Cannot find matching method/constructor");
//  }
//
//  public int getMaxIndex()
//  {
//    return 5;
//  }
}