package com.google.android.gms.internal;

import com.google.android.gms.common.internal.Hide;

@Hide
public class zzbfx<T>
{
  private static final Object zza = new Object();
  private static zzbgd zzb;
  private static int zzc = 0;
  private static String zzd = "com.google.android.providers.gsf.permission.READ_GSERVICES";
  private String zze;
  private T zzf;
  private T zzg = null;
  
  protected zzbfx(String paramString, T paramT)
  {
    zze = paramString;
    zzf = paramT;
  }
  
  public static zzbfx<Float> zza(String paramString, Float paramFloat)
  {
    return new zzbgb(paramString, paramFloat);
  }
  
  public static zzbfx<Integer> zza(String paramString, Integer paramInteger)
  {
    return new zzbga(paramString, paramInteger);
  }
  
  public static zzbfx<Long> zza(String paramString, Long paramLong)
  {
    return new zzbfz(paramString, paramLong);
  }
  
  public static zzbfx<String> zza(String paramString1, String paramString2)
  {
    return new zzbgc(paramString1, paramString2);
  }
  
  public static zzbfx<Boolean> zza(String paramString, boolean paramBoolean)
  {
    return new zzbfy(paramString, Boolean.valueOf(paramBoolean));
  }
}
