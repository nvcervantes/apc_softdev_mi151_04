package com.google.android.gms.common.util;

import android.os.Process;

public final class zzu
{
  private static String zza;
  private static final int zzb = ;
  
  public static String zza()
  {
    if (zza == null) {
      zza = zza(zzb);
    }
    return zza;
  }
  
  /* Error */
  private static String zza(int paramInt)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: iload_0
    //   3: ifgt +5 -> 8
    //   6: aconst_null
    //   7: areturn
    //   8: invokestatic 33	android/os/StrictMode:allowThreadDiskReads	()Landroid/os/StrictMode$ThreadPolicy;
    //   11: astore_2
    //   12: new 35	java/lang/StringBuilder
    //   15: dup
    //   16: bipush 25
    //   18: invokespecial 39	java/lang/StringBuilder:<init>	(I)V
    //   21: astore_1
    //   22: aload_1
    //   23: ldc 41
    //   25: invokevirtual 45	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   28: pop
    //   29: aload_1
    //   30: iload_0
    //   31: invokevirtual 48	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   34: pop
    //   35: aload_1
    //   36: ldc 50
    //   38: invokevirtual 45	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   41: pop
    //   42: new 52	java/io/BufferedReader
    //   45: dup
    //   46: new 54	java/io/FileReader
    //   49: dup
    //   50: aload_1
    //   51: invokevirtual 57	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   54: invokespecial 60	java/io/FileReader:<init>	(Ljava/lang/String;)V
    //   57: invokespecial 63	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   60: astore_1
    //   61: aload_2
    //   62: invokestatic 67	android/os/StrictMode:setThreadPolicy	(Landroid/os/StrictMode$ThreadPolicy;)V
    //   65: aload_1
    //   66: invokevirtual 70	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   69: invokevirtual 75	java/lang/String:trim	()Ljava/lang/String;
    //   72: astore_2
    //   73: aload_1
    //   74: invokestatic 80	com/google/android/gms/common/util/zzp:zza	(Ljava/io/Closeable;)V
    //   77: aload_2
    //   78: areturn
    //   79: astore_2
    //   80: goto +13 -> 93
    //   83: astore_1
    //   84: aload_2
    //   85: invokestatic 67	android/os/StrictMode:setThreadPolicy	(Landroid/os/StrictMode$ThreadPolicy;)V
    //   88: aload_1
    //   89: athrow
    //   90: astore_2
    //   91: aload_3
    //   92: astore_1
    //   93: aload_1
    //   94: invokestatic 80	com/google/android/gms/common/util/zzp:zza	(Ljava/io/Closeable;)V
    //   97: aload_2
    //   98: athrow
    //   99: aconst_null
    //   100: astore_1
    //   101: aload_1
    //   102: invokestatic 80	com/google/android/gms/common/util/zzp:zza	(Ljava/io/Closeable;)V
    //   105: aconst_null
    //   106: areturn
    //   107: astore_1
    //   108: goto -9 -> 99
    //   111: astore_2
    //   112: goto -11 -> 101
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	115	0	paramInt	int
    //   21	53	1	localObject1	Object
    //   83	6	1	localObject2	Object
    //   92	10	1	localObject3	Object
    //   107	1	1	localIOException1	java.io.IOException
    //   11	67	2	localObject4	Object
    //   79	6	2	localThreadPolicy	android.os.StrictMode.ThreadPolicy
    //   90	8	2	localObject5	Object
    //   111	1	2	localIOException2	java.io.IOException
    //   1	91	3	localObject6	Object
    // Exception table:
    //   from	to	target	type
    //   61	73	79	finally
    //   12	61	83	finally
    //   8	12	90	finally
    //   84	90	90	finally
    //   8	12	107	java/io/IOException
    //   84	90	107	java/io/IOException
    //   61	73	111	java/io/IOException
  }
}
