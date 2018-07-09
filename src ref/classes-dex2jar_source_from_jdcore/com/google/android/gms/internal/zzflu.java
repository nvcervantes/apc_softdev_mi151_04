package com.google.android.gms.internal;

import java.util.Arrays;

final class zzflu
{
  final int zza;
  final byte[] zzb;
  
  zzflu(int paramInt, byte[] paramArrayOfByte)
  {
    zza = paramInt;
    zzb = paramArrayOfByte;
  }
  
  public final boolean equals(Object paramObject)
  {
    if (paramObject == this) {
      return true;
    }
    if (!(paramObject instanceof zzflu)) {
      return false;
    }
    paramObject = (zzflu)paramObject;
    return (zza == zza) && (Arrays.equals(zzb, zzb));
  }
  
  public final int hashCode()
  {
    return (527 + zza) * 31 + Arrays.hashCode(zzb);
  }
}
