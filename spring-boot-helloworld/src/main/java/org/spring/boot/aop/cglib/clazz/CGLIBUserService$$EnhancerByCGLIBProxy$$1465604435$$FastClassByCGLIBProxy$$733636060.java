package org.spring.boot.aop.cglib.clazz;

/**
 * cglib生成的 f2(FastClass)子类字节码
 * @author zhangzhigang
 *
 */
public class CGLIBUserService$$EnhancerByCGLIBProxy$$1465604435$$FastClassByCGLIBProxy$$733636060
//extends FastClass
{
//  public int getIndex(Signature paramSignature)
//  {
//    String tmp4_1 = paramSignature.toString();
//    switch (tmp4_1.hashCode())
//    {
//    case -2055565910:
//      if (tmp4_1.equals("CGLIB$SET_THREAD_CALLBACKS([Lnet/sf/cglib/proxy/Callback;)V"))
//        return 8;
//    case -1844017852:
//    case -1816210712:
//    case -1457535688:
//    case -894172689:
//    case -623122092:
//    case -419626537:
//    case 560567118:
//    case 811063227:
//    case 973717575:
//    case 1221173700:
//    case 1230699260:
//    case 1298742135:
//    case 1584330438:
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
//        Class[] tmp152_1 = paramArrayOfClass;
//        switch (tmp152_1.length)
//        {
//        case 1:
//          Class[] tmp172_152 = tmp152_1;
//          if (!tmp172_152[0].getName().equals("java.lang.String"))
//            break;
//          tmp172_152;
//          return 3;
//        default:
//          break;
//        }
//      }
//    case -1983192202:
//    case -1776922004:
//    case -1295482945:
//    case -1053468136:
//    case -909361405:
//    case -60403779:
//    case 100184:
//    case 85179481:
//    case 147696667:
//    case 161998109:
//    case 495524492:
//    case 1154623345:
//    case 1811874389:
//    case 1817099975:
//    case 1905679803:
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
//    //   1: checkcast 125	org/spring/boot/aop/cglib/CGLIBUserService$$EnhancerByCGLIBProxy$$-1465604435
//    //   4: iload_1
//    //   5: tableswitch	default:+285 -> 290, 0:+87->92, 1:+97->102, 2:+119->124, 3:+129->134, 4:+139->144, 5:+149->154, 6:+160->165, 7:+180->185, 8:+191->196, 9:+202->207, 10:+215->220, 11:+219->224, 12:+224->229, 13:+234->239, 14:+244->249, 15:+254->259, 16:+269->274, 17:+273->278
//    //   93: iconst_0
//    //   94: aaload
//    //   95: checkcast 127	[Lnet/sf/cglib/proxy/Callback;
//    //   98: invokevirtual 130	org/spring/boot/aop/cglib/CGLIBUserService$$EnhancerByCGLIBProxy$$-1465604435:newInstance	([Lnet/sf/cglib/proxy/Callback;)Ljava/lang/Object;
//    //   101: areturn
//    //   102: aload_3
//    //   103: iconst_0
//    //   104: aaload
//    //   105: checkcast 118	[Ljava/lang/Class;
//    //   108: aload_3
//    //   109: iconst_1
//    //   110: aaload
//    //   111: checkcast 132	[Ljava/lang/Object;
//    //   114: aload_3
//    //   115: iconst_2
//    //   116: aaload
//    //   117: checkcast 127	[Lnet/sf/cglib/proxy/Callback;
//    //   120: invokevirtual 135	org/spring/boot/aop/cglib/CGLIBUserService$$EnhancerByCGLIBProxy$$-1465604435:newInstance	([Ljava/lang/Class;[Ljava/lang/Object;[Lnet/sf/cglib/proxy/Callback;)Ljava/lang/Object;
//    //   123: areturn
//    //   124: aload_3
//    //   125: iconst_0
//    //   126: aaload
//    //   127: checkcast 137	net/sf/cglib/proxy/Callback
//    //   130: invokevirtual 140	org/spring/boot/aop/cglib/CGLIBUserService$$EnhancerByCGLIBProxy$$-1465604435:newInstance	(Lnet/sf/cglib/proxy/Callback;)Ljava/lang/Object;
//    //   133: areturn
//    //   134: aload_3
//    //   135: iconst_0
//    //   136: aaload
//    //   137: checkcast 65	java/lang/String
//    //   140: invokevirtual 143	org/spring/boot/aop/cglib/CGLIBUserService$$EnhancerByCGLIBProxy$$-1465604435:sayHello	(Ljava/lang/String;)Ljava/lang/String;
//    //   143: areturn
//    //   144: aload_3
//    //   145: iconst_0
//    //   146: aaload
//    //   147: checkcast 65	java/lang/String
//    //   150: invokevirtual 145	org/spring/boot/aop/cglib/CGLIBUserService$$EnhancerByCGLIBProxy$$-1465604435:sayBye	(Ljava/lang/String;)Ljava/lang/String;
//    //   153: areturn
//    //   154: aload_3
//    //   155: iconst_0
//    //   156: aaload
//    //   157: checkcast 127	[Lnet/sf/cglib/proxy/Callback;
//    //   160: invokevirtual 148	org/spring/boot/aop/cglib/CGLIBUserService$$EnhancerByCGLIBProxy$$-1465604435:setCallbacks	([Lnet/sf/cglib/proxy/Callback;)V
//    //   163: aconst_null
//    //   164: areturn
//    //   165: aload_3
//    //   166: iconst_0
//    //   167: aaload
//    //   168: checkcast 150	java/lang/Number
//    //   171: invokevirtual 153	java/lang/Number:intValue	()I
//    //   174: aload_3
//    //   175: iconst_1
//    //   176: aaload
//    //   177: checkcast 137	net/sf/cglib/proxy/Callback
//    //   180: invokevirtual 156	org/spring/boot/aop/cglib/CGLIBUserService$$EnhancerByCGLIBProxy$$-1465604435:setCallback	(ILnet/sf/cglib/proxy/Callback;)V
//    //   183: aconst_null
//    //   184: areturn
//    //   185: aload_3
//    //   186: iconst_0
//    //   187: aaload
//    //   188: checkcast 127	[Lnet/sf/cglib/proxy/Callback;
//    //   191: invokestatic 158	org/spring/boot/aop/cglib/CGLIBUserService$$EnhancerByCGLIBProxy$$-1465604435:CGLIB$SET_STATIC_CALLBACKS	([Lnet/sf/cglib/proxy/Callback;)V
//    //   194: aconst_null
//    //   195: areturn
//    //   196: aload_3
//    //   197: iconst_0
//    //   198: aaload
//    //   199: checkcast 127	[Lnet/sf/cglib/proxy/Callback;
//    //   202: invokestatic 160	org/spring/boot/aop/cglib/CGLIBUserService$$EnhancerByCGLIBProxy$$-1465604435:CGLIB$SET_THREAD_CALLBACKS	([Lnet/sf/cglib/proxy/Callback;)V
//    //   205: aconst_null
//    //   206: areturn
//    //   207: aload_3
//    //   208: iconst_0
//    //   209: aaload
//    //   210: checkcast 150	java/lang/Number
//    //   213: invokevirtual 153	java/lang/Number:intValue	()I
//    //   216: invokevirtual 163	org/spring/boot/aop/cglib/CGLIBUserService$$EnhancerByCGLIBProxy$$-1465604435:getCallback	(I)Lnet/sf/cglib/proxy/Callback;
//    //   219: areturn
//    //   220: invokevirtual 166	org/spring/boot/aop/cglib/CGLIBUserService$$EnhancerByCGLIBProxy$$-1465604435:getCallbacks	()[Lnet/sf/cglib/proxy/Callback;
//    //   223: areturn
//    //   224: invokestatic 169	org/spring/boot/aop/cglib/CGLIBUserService$$EnhancerByCGLIBProxy$$-1465604435:CGLIB$STATICHOOK1	()V
//    //   227: aconst_null
//    //   228: areturn
//    //   229: aload_3
//    //   230: iconst_0
//    //   231: aaload
//    //   232: checkcast 63	net/sf/cglib/core/Signature
//    //   235: invokestatic 172	org/spring/boot/aop/cglib/CGLIBUserService$$EnhancerByCGLIBProxy$$-1465604435:CGLIB$findMethodProxy	(Lnet/sf/cglib/core/Signature;)Lnet/sf/cglib/proxy/MethodProxy;
//    //   238: areturn
//    //   239: aload_3
//    //   240: iconst_0
//    //   241: aaload
//    //   242: checkcast 65	java/lang/String
//    //   245: invokevirtual 174	org/spring/boot/aop/cglib/CGLIBUserService$$EnhancerByCGLIBProxy$$-1465604435:CGLIB$sayHello$0	(Ljava/lang/String;)Ljava/lang/String;
//    //   248: areturn
//    //   249: aload_3
//    //   250: iconst_0
//    //   251: aaload
//    //   252: checkcast 65	java/lang/String
//    //   255: invokevirtual 176	org/spring/boot/aop/cglib/CGLIBUserService$$EnhancerByCGLIBProxy$$-1465604435:eat	(Ljava/lang/String;)Ljava/lang/String;
//    //   258: areturn
//    //   259: aload_3
//    //   260: iconst_0
//    //   261: aaload
//    //   262: invokevirtual 177	org/spring/boot/aop/cglib/CGLIBUserService$$EnhancerByCGLIBProxy$$-1465604435:equals	(Ljava/lang/Object;)Z
//    //   265: new 179	java/lang/Boolean
//    //   268: dup_x1
//    //   269: swap
//    //   270: invokespecial 182	java/lang/Boolean:<init>	(Z)V
//    //   273: areturn
//    //   274: invokevirtual 183	org/spring/boot/aop/cglib/CGLIBUserService$$EnhancerByCGLIBProxy$$-1465604435:toString	()Ljava/lang/String;
//    //   277: areturn
//    //   278: invokevirtual 184	org/spring/boot/aop/cglib/CGLIBUserService$$EnhancerByCGLIBProxy$$-1465604435:hashCode	()I
//    //   281: new 186	java/lang/Integer
//    //   284: dup_x1
//    //   285: swap
//    //   286: invokespecial 189	java/lang/Integer:<init>	(I)V
//    //   289: areturn
//    //   290: goto +12 -> 302
//    //   293: new 123	java/lang/reflect/InvocationTargetException
//    //   296: dup_x1
//    //   297: swap
//    //   298: invokespecial 194	java/lang/reflect/InvocationTargetException:<init>	(Ljava/lang/Throwable;)V
//    //   301: athrow
//    //   302: new 196	java/lang/IllegalArgumentException
//    //   305: dup
//    //   306: ldc 198
//    //   308: invokespecial 201	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
//    //   311: athrow
//    //
//    // Exception table:
//    //   from	to	target	type
//    //   5	293	293	java/lang/Throwable
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
//        return new CGLIBUserService..EnhancerByCGLIBProxy..-1465604435();
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
//    return 17;
//  }
}