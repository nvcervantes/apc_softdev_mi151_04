package com.google.android.gms.common.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import java.util.Arrays;

public final class zzah
{
  private final String zza;
  private final String zzb;
  private final ComponentName zzc;
  private final int zzd;
  
  public zzah(ComponentName paramComponentName, int paramInt)
  {
    zza = null;
    zzb = null;
    zzc = ((ComponentName)zzbq.zza(paramComponentName));
    zzd = 129;
  }
  
  public zzah(String paramString1, String paramString2, int paramInt)
  {
    zza = zzbq.zza(paramString1);
    zzb = zzbq.zza(paramString2);
    zzc = null;
    zzd = paramInt;
  }
  
  public final boolean equals(Object paramObject)
  {
    if (this == paramObject) {
      return true;
    }
    if (!(paramObject instanceof zzah)) {
      return false;
    }
    paramObject = (zzah)paramObject;
    return (zzbg.zza(zza, zza)) && (zzbg.zza(zzb, zzb)) && (zzbg.zza(zzc, zzc)) && (zzd == zzd);
  }
  
  public final int hashCode()
  {
    return Arrays.hashCode(new Object[] { zza, zzb, zzc, Integer.valueOf(zzd) });
  }
  
  public final String toString()
  {
    if (zza == null) {
      return zzc.flattenToString();
    }
    return zza;
  }
  
  public final Intent zza(Context paramContext)
  {
    if (zza != null) {
      return new Intent(zza).setPackage(zzb);
    }
    return new Intent().setComponent(zzc);
  }
  
  public final String zza()
  {
    return zzb;
  }
  
  public final ComponentName zzb()
  {
    return zzc;
  }
  
  public final int zzc()
  {
    return zzd;
  }
}
