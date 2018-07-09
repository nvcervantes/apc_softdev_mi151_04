package com.google.android.gms.common.internal;

import android.os.Looper;
import android.util.Log;

@Hide
public final class zzc
{
  public static void zza(Object paramObject)
  {
    if (paramObject == null) {
      throw new IllegalArgumentException("null reference");
    }
  }
  
  public static void zza(Object paramObject1, Object paramObject2)
  {
    if (paramObject1 == null) {
      throw new IllegalArgumentException(String.valueOf(paramObject2));
    }
  }
  
  public static void zza(String paramString)
  {
    if (Looper.getMainLooper().getThread() != Thread.currentThread())
    {
      String str1 = String.valueOf(Thread.currentThread());
      String str2 = String.valueOf(Looper.getMainLooper().getThread());
      StringBuilder localStringBuilder = new StringBuilder(57 + String.valueOf(str1).length() + String.valueOf(str2).length());
      localStringBuilder.append("checkMainThread: current thread ");
      localStringBuilder.append(str1);
      localStringBuilder.append(" IS NOT the main thread ");
      localStringBuilder.append(str2);
      localStringBuilder.append("!");
      Log.e("Asserts", localStringBuilder.toString());
      throw new IllegalStateException(paramString);
    }
  }
  
  public static void zza(boolean paramBoolean)
  {
    if (!paramBoolean) {
      throw new IllegalStateException();
    }
  }
}
