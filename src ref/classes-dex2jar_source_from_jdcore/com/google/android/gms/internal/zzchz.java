package com.google.android.gms.internal;

import android.os.Looper;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.internal.zzbq;

@Hide
public final class zzchz
{
  public static Looper zza()
  {
    boolean bool;
    if (Looper.myLooper() != null) {
      bool = true;
    } else {
      bool = false;
    }
    zzbq.zza(bool, "Can't create handler inside thread that has not called Looper.prepare()");
    return Looper.myLooper();
  }
  
  public static Looper zza(@Nullable Looper paramLooper)
  {
    if (paramLooper != null) {
      return paramLooper;
    }
    return zza();
  }
}
