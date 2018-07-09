package com.google.android.gms.internal;

import android.os.SystemClock;
import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class zzaf
{
  public static boolean zza = Log.isLoggable("Volley", 2);
  private static String zzb = "Volley";
  
  public zzaf() {}
  
  public static void zza(String paramString, Object... paramVarArgs)
  {
    if (zza) {
      Log.v(zzb, zzd(paramString, paramVarArgs));
    }
  }
  
  public static void zza(Throwable paramThrowable, String paramString, Object... paramVarArgs)
  {
    Log.e(zzb, zzd(paramString, paramVarArgs), paramThrowable);
  }
  
  public static void zzb(String paramString, Object... paramVarArgs)
  {
    Log.d(zzb, zzd(paramString, paramVarArgs));
  }
  
  public static void zzc(String paramString, Object... paramVarArgs)
  {
    Log.e(zzb, zzd(paramString, paramVarArgs));
  }
  
  private static String zzd(String paramString, Object... paramVarArgs)
  {
    if (paramVarArgs != null) {
      paramString = String.format(Locale.US, paramString, paramVarArgs);
    }
    Object localObject = new Throwable().fillInStackTrace().getStackTrace();
    String str = "<unknown>";
    int i = 2;
    for (;;)
    {
      paramVarArgs = str;
      if (i >= localObject.length) {
        break;
      }
      if (!localObject[i].getClass().equals(zzaf.class))
      {
        paramVarArgs = localObject[i].getClassName();
        paramVarArgs = paramVarArgs.substring(paramVarArgs.lastIndexOf('.') + 1);
        paramVarArgs = paramVarArgs.substring(paramVarArgs.lastIndexOf('$') + 1);
        str = localObject[i].getMethodName();
        localObject = new StringBuilder(String.valueOf(paramVarArgs).length() + 1 + String.valueOf(str).length());
        ((StringBuilder)localObject).append(paramVarArgs);
        ((StringBuilder)localObject).append(".");
        ((StringBuilder)localObject).append(str);
        paramVarArgs = ((StringBuilder)localObject).toString();
        break;
      }
      i += 1;
    }
    return String.format(Locale.US, "[%d] %s: %s", new Object[] { Long.valueOf(Thread.currentThread().getId()), paramVarArgs, paramString });
  }
  
  static final class zza
  {
    public static final boolean zza = zzaf.zza;
    private final List<zzag> zzb = new ArrayList();
    private boolean zzc = false;
    
    zza() {}
    
    protected final void finalize()
      throws Throwable
    {
      if (!zzc)
      {
        zza("Request on the loose");
        zzaf.zzc("Marker log finalized without finish() - uncaught exit point for request", new Object[0]);
      }
    }
    
    public final void zza(String paramString)
    {
      try
      {
        zzc = true;
        if (zzb.size() == 0)
        {
          l1 = 0L;
        }
        else
        {
          l1 = zzb.get(0)).zzc;
          l2 = zzb.get(zzb.size() - 1)).zzc;
          l1 = l2 - l1;
        }
        if (l1 <= 0L) {
          return;
        }
        long l2 = zzb.get(0)).zzc;
        zzaf.zzb("(%-4d ms) %s", new Object[] { Long.valueOf(l1), paramString });
        paramString = zzb.iterator();
        for (long l1 = l2; paramString.hasNext(); l1 = l2)
        {
          zzag localZzag = (zzag)paramString.next();
          l2 = zzc;
          zzaf.zzb("(+%-4d) [%2d] %s", new Object[] { Long.valueOf(l2 - l1), Long.valueOf(zzb), zza });
        }
        return;
      }
      finally {}
    }
    
    public final void zza(String paramString, long paramLong)
    {
      try
      {
        if (zzc) {
          throw new IllegalStateException("Marker added to finished log");
        }
        zzb.add(new zzag(paramString, paramLong, SystemClock.elapsedRealtime()));
        return;
      }
      finally {}
    }
  }
}
