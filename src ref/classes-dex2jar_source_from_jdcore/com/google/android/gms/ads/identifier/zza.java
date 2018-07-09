package com.google.android.gms.ads.identifier;

import java.util.Map;

final class zza
  extends Thread
{
  zza(AdvertisingIdClient paramAdvertisingIdClient, Map paramMap) {}
  
  /* Error */
  public final void run()
  {
    // Byte code:
    //   0: new 23	com/google/android/gms/ads/identifier/zzc
    //   3: dup
    //   4: invokespecial 24	com/google/android/gms/ads/identifier/zzc:<init>	()V
    //   7: pop
    //   8: aload_0
    //   9: getfield 10	com/google/android/gms/ads/identifier/zza:zza	Ljava/util/Map;
    //   12: astore_2
    //   13: ldc 26
    //   15: invokestatic 32	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   18: invokevirtual 36	android/net/Uri:buildUpon	()Landroid/net/Uri$Builder;
    //   21: astore_3
    //   22: aload_2
    //   23: invokeinterface 42 1 0
    //   28: invokeinterface 48 1 0
    //   33: astore 4
    //   35: aload 4
    //   37: invokeinterface 54 1 0
    //   42: ifeq +36 -> 78
    //   45: aload 4
    //   47: invokeinterface 58 1 0
    //   52: checkcast 60	java/lang/String
    //   55: astore 5
    //   57: aload_3
    //   58: aload 5
    //   60: aload_2
    //   61: aload 5
    //   63: invokeinterface 64 2 0
    //   68: checkcast 60	java/lang/String
    //   71: invokevirtual 70	android/net/Uri$Builder:appendQueryParameter	(Ljava/lang/String;Ljava/lang/String;)Landroid/net/Uri$Builder;
    //   74: pop
    //   75: goto -40 -> 35
    //   78: aload_3
    //   79: invokevirtual 74	android/net/Uri$Builder:build	()Landroid/net/Uri;
    //   82: invokevirtual 78	android/net/Uri:toString	()Ljava/lang/String;
    //   85: astore 6
    //   87: new 80	java/net/URL
    //   90: dup
    //   91: aload 6
    //   93: invokespecial 83	java/net/URL:<init>	(Ljava/lang/String;)V
    //   96: invokevirtual 87	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   99: checkcast 89	java/net/HttpURLConnection
    //   102: astore_2
    //   103: aload_2
    //   104: invokevirtual 93	java/net/HttpURLConnection:getResponseCode	()I
    //   107: istore_1
    //   108: iload_1
    //   109: sipush 200
    //   112: if_icmplt +10 -> 122
    //   115: iload_1
    //   116: sipush 300
    //   119: if_icmplt +59 -> 178
    //   122: new 95	java/lang/StringBuilder
    //   125: dup
    //   126: bipush 65
    //   128: aload 6
    //   130: invokestatic 99	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   133: invokevirtual 102	java/lang/String:length	()I
    //   136: iadd
    //   137: invokespecial 105	java/lang/StringBuilder:<init>	(I)V
    //   140: astore_3
    //   141: aload_3
    //   142: ldc 107
    //   144: invokevirtual 111	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   147: pop
    //   148: aload_3
    //   149: iload_1
    //   150: invokevirtual 114	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   153: pop
    //   154: aload_3
    //   155: ldc 116
    //   157: invokevirtual 111	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   160: pop
    //   161: aload_3
    //   162: aload 6
    //   164: invokevirtual 111	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   167: pop
    //   168: ldc 118
    //   170: aload_3
    //   171: invokevirtual 119	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   174: invokestatic 125	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   177: pop
    //   178: aload_2
    //   179: invokevirtual 128	java/net/HttpURLConnection:disconnect	()V
    //   182: return
    //   183: astore_3
    //   184: aload_2
    //   185: invokevirtual 128	java/net/HttpURLConnection:disconnect	()V
    //   188: aload_3
    //   189: athrow
    //   190: astore_2
    //   191: aload_2
    //   192: invokevirtual 133	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   195: astore_3
    //   196: new 95	java/lang/StringBuilder
    //   199: dup
    //   200: bipush 27
    //   202: aload 6
    //   204: invokestatic 99	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   207: invokevirtual 102	java/lang/String:length	()I
    //   210: iadd
    //   211: aload_3
    //   212: invokestatic 99	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   215: invokevirtual 102	java/lang/String:length	()I
    //   218: iadd
    //   219: invokespecial 105	java/lang/StringBuilder:<init>	(I)V
    //   222: astore 5
    //   224: ldc -121
    //   226: astore 4
    //   228: aload 5
    //   230: aload 4
    //   232: invokevirtual 111	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   235: pop
    //   236: aload 5
    //   238: aload 6
    //   240: invokevirtual 111	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   243: pop
    //   244: aload 5
    //   246: ldc -119
    //   248: invokevirtual 111	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   251: pop
    //   252: aload 5
    //   254: aload_3
    //   255: invokevirtual 111	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   258: pop
    //   259: ldc 118
    //   261: aload 5
    //   263: invokevirtual 119	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   266: aload_2
    //   267: invokestatic 140	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   270: pop
    //   271: return
    //   272: astore_2
    //   273: aload_2
    //   274: invokevirtual 141	java/lang/IndexOutOfBoundsException:getMessage	()Ljava/lang/String;
    //   277: astore_3
    //   278: new 95	java/lang/StringBuilder
    //   281: dup
    //   282: bipush 32
    //   284: aload 6
    //   286: invokestatic 99	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   289: invokevirtual 102	java/lang/String:length	()I
    //   292: iadd
    //   293: aload_3
    //   294: invokestatic 99	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   297: invokevirtual 102	java/lang/String:length	()I
    //   300: iadd
    //   301: invokespecial 105	java/lang/StringBuilder:<init>	(I)V
    //   304: astore 5
    //   306: ldc -113
    //   308: astore 4
    //   310: goto -82 -> 228
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	313	0	this	zza
    //   107	43	1	i	int
    //   12	173	2	localObject1	Object
    //   190	77	2	localIOException	java.io.IOException
    //   272	2	2	localIndexOutOfBoundsException	IndexOutOfBoundsException
    //   21	150	3	localObject2	Object
    //   183	6	3	localObject3	Object
    //   195	99	3	str1	String
    //   33	276	4	localObject4	Object
    //   55	250	5	localObject5	Object
    //   85	200	6	str2	String
    // Exception table:
    //   from	to	target	type
    //   103	108	183	finally
    //   122	178	183	finally
    //   87	103	190	java/io/IOException
    //   87	103	190	java/lang/RuntimeException
    //   178	182	190	java/io/IOException
    //   178	182	190	java/lang/RuntimeException
    //   184	190	190	java/io/IOException
    //   184	190	190	java/lang/RuntimeException
    //   87	103	272	java/lang/IndexOutOfBoundsException
    //   178	182	272	java/lang/IndexOutOfBoundsException
    //   184	190	272	java/lang/IndexOutOfBoundsException
  }
}
