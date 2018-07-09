package com.google.android.gms.internal;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.lang.reflect.Field;

public final class zzdyq
{
  private static zzdyr zza;
  private static int zzb;
  
  static
  {
    int i = 1;
    try
    {
      localInteger = zza();
      if (localInteger != null) {}
      Object localObject;
      localPrintStream = System.err;
    }
    catch (Throwable localThrowable1)
    {
      try
      {
        if (localInteger.intValue() >= 19) {
          localObject = new zzdyv();
        } else if ((Boolean.getBoolean("com.google.devtools.build.android.desugar.runtime.twr_disable_mimic") ^ true)) {
          localObject = new zzdyu();
        } else {
          localObject = new zza();
        }
      }
      catch (Throwable localThrowable2)
      {
        Integer localInteger;
        PrintStream localPrintStream;
        String str;
        StringBuilder localStringBuilder;
        zza localZza;
        for (;;) {}
      }
      localThrowable1 = localThrowable1;
      localInteger = null;
    }
    str = zza.class.getName();
    localStringBuilder = new StringBuilder(132 + String.valueOf(str).length());
    localStringBuilder.append("An error has occured when initializing the try-with-resources desuguring strategy. The default strategy ");
    localStringBuilder.append(str);
    localStringBuilder.append("will be used. The error is: ");
    localPrintStream.println(localStringBuilder.toString());
    localThrowable1.printStackTrace(System.err);
    localZza = new zza();
    zza = localZza;
    if (localInteger != null) {
      i = localInteger.intValue();
    }
    zzb = i;
  }
  
  private static Integer zza()
  {
    try
    {
      Integer localInteger = (Integer)Class.forName("android.os.Build$VERSION").getField("SDK_INT").get(null);
      return localInteger;
    }
    catch (Exception localException)
    {
      System.err.println("Failed to retrieve value from android.os.Build$VERSION.SDK_INT due to the following exception.");
      localException.printStackTrace(System.err);
    }
    return null;
  }
  
  public static void zza(Throwable paramThrowable, PrintStream paramPrintStream)
  {
    zza.zza(paramThrowable, paramPrintStream);
  }
  
  public static void zza(Throwable paramThrowable, PrintWriter paramPrintWriter)
  {
    zza.zza(paramThrowable, paramPrintWriter);
  }
  
  static final class zza
    extends zzdyr
  {
    zza() {}
    
    public final void zza(Throwable paramThrowable, PrintStream paramPrintStream)
    {
      paramThrowable.printStackTrace(paramPrintStream);
    }
    
    public final void zza(Throwable paramThrowable, PrintWriter paramPrintWriter)
    {
      paramThrowable.printStackTrace(paramPrintWriter);
    }
  }
}
