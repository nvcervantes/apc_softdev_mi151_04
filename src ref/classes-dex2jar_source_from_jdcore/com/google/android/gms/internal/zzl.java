package com.google.android.gms.internal;

import android.text.TextUtils;

public final class zzl
{
  private final String zza;
  private final String zzb;
  
  public zzl(String paramString1, String paramString2)
  {
    zza = paramString1;
    zzb = paramString2;
  }
  
  public final boolean equals(Object paramObject)
  {
    if (this == paramObject) {
      return true;
    }
    if (paramObject != null)
    {
      if (getClass() != paramObject.getClass()) {
        return false;
      }
      paramObject = (zzl)paramObject;
      if ((TextUtils.equals(zza, zza)) && (TextUtils.equals(zzb, zzb))) {
        return true;
      }
    }
    return false;
  }
  
  public final int hashCode()
  {
    return zza.hashCode() * 31 + zzb.hashCode();
  }
  
  public final String toString()
  {
    String str1 = zza;
    String str2 = zzb;
    StringBuilder localStringBuilder = new StringBuilder(20 + String.valueOf(str1).length() + String.valueOf(str2).length());
    localStringBuilder.append("Header[name=");
    localStringBuilder.append(str1);
    localStringBuilder.append(",value=");
    localStringBuilder.append(str2);
    localStringBuilder.append("]");
    return localStringBuilder.toString();
  }
  
  public final String zza()
  {
    return zza;
  }
  
  public final String zzb()
  {
    return zzb;
  }
}
