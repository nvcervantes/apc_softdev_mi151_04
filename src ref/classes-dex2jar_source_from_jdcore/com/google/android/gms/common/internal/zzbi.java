package com.google.android.gms.common.internal;

import java.util.ArrayList;
import java.util.List;

public final class zzbi
{
  private final List<String> zza;
  private final Object zzb;
  
  private zzbi(Object paramObject)
  {
    zzb = zzbq.zza(paramObject);
    zza = new ArrayList();
  }
  
  public final String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder(100);
    localStringBuilder.append(zzb.getClass().getSimpleName());
    localStringBuilder.append('{');
    int j = zza.size();
    int i = 0;
    while (i < j)
    {
      localStringBuilder.append((String)zza.get(i));
      if (i < j - 1) {
        localStringBuilder.append(", ");
      }
      i += 1;
    }
    localStringBuilder.append('}');
    return localStringBuilder.toString();
  }
  
  public final zzbi zza(String paramString, Object paramObject)
  {
    List localList = zza;
    paramString = (String)zzbq.zza(paramString);
    paramObject = String.valueOf(paramObject);
    StringBuilder localStringBuilder = new StringBuilder(1 + String.valueOf(paramString).length() + String.valueOf(paramObject).length());
    localStringBuilder.append(paramString);
    localStringBuilder.append("=");
    localStringBuilder.append(paramObject);
    localList.add(localStringBuilder.toString());
    return this;
  }
}
