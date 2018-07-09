package com.google.android.gms.common;

import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.Hide;

@Hide
class zzp
{
  private static final zzp zzc = new zzp(true, null, null);
  final boolean zza;
  final Throwable zzb;
  private String zzd;
  
  zzp(boolean paramBoolean, String paramString, Throwable paramThrowable)
  {
    zza = paramBoolean;
    zzd = paramString;
    zzb = paramThrowable;
  }
  
  static zzp zza()
  {
    return zzc;
  }
  
  static zzp zza(@NonNull String paramString)
  {
    return new zzp(false, paramString, null);
  }
  
  static zzp zza(String paramString, zzh paramZzh, boolean paramBoolean1, boolean paramBoolean2)
  {
    return new zzr(paramString, paramZzh, paramBoolean1, paramBoolean2, null);
  }
  
  static zzp zza(@NonNull String paramString, @NonNull Throwable paramThrowable)
  {
    return new zzp(false, paramString, paramThrowable);
  }
  
  String zzb()
  {
    return zzd;
  }
}
