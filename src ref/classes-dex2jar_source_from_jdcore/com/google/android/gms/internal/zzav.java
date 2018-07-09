package com.google.android.gms.internal;

import java.io.UnsupportedEncodingException;

public class zzav
  extends zzr<String>
{
  private final Object zza = new Object();
  private zzz<String> zzb;
  
  public zzav(int paramInt, String paramString, zzz<String> paramZzz, zzy paramZzy)
  {
    super(paramInt, paramString, paramZzy);
    zzb = paramZzz;
  }
  
  protected final zzx<String> zza(zzp paramZzp)
  {
    try
    {
      str = new String(zzb, zzap.zza(zzc));
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      String str;
      for (;;) {}
    }
    str = new String(zzb);
    return zzx.zza(str, zzap.zza(paramZzp));
  }
  
  protected void zzc(String paramString)
  {
    synchronized (zza)
    {
      zzz localZzz = zzb;
      if (localZzz != null) {
        localZzz.zza(paramString);
      }
      return;
    }
  }
}
