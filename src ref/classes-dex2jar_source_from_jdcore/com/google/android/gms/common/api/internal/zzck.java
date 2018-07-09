package com.google.android.gms.common.api.internal;

public final class zzck<L>
{
  private final L zza;
  private final String zzb;
  
  zzck(L paramL, String paramString)
  {
    zza = paramL;
    zzb = paramString;
  }
  
  public final boolean equals(Object paramObject)
  {
    if (this == paramObject) {
      return true;
    }
    if (!(paramObject instanceof zzck)) {
      return false;
    }
    paramObject = (zzck)paramObject;
    return (zza == zza) && (zzb.equals(zzb));
  }
  
  public final int hashCode()
  {
    return System.identityHashCode(zza) * 31 + zzb.hashCode();
  }
}
