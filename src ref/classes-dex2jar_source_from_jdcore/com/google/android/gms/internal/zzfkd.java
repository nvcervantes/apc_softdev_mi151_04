package com.google.android.gms.internal;

import java.util.Map.Entry;

final class zzfkd
  implements Comparable<zzfkd>, Map.Entry<K, V>
{
  private final K zza;
  private V zzb;
  
  zzfkd(K paramK, V paramV)
  {
    zza = paramV;
    Object localObject;
    zzb = localObject;
  }
  
  zzfkd(Map.Entry<K, V> paramEntry)
  {
    this(paramEntry, (Comparable)localObject.getKey(), localObject.getValue());
  }
  
  private static boolean zza(Object paramObject1, Object paramObject2)
  {
    if (paramObject1 == null) {
      return paramObject2 == null;
    }
    return paramObject1.equals(paramObject2);
  }
  
  public final boolean equals(Object paramObject)
  {
    if (paramObject == this) {
      return true;
    }
    if (!(paramObject instanceof Map.Entry)) {
      return false;
    }
    paramObject = (Map.Entry)paramObject;
    return (zza(zza, paramObject.getKey())) && (zza(zzb, paramObject.getValue()));
  }
  
  public final V getValue()
  {
    return zzb;
  }
  
  public final int hashCode()
  {
    Comparable localComparable = zza;
    int j = 0;
    int i;
    if (localComparable == null) {
      i = 0;
    } else {
      i = zza.hashCode();
    }
    if (zzb != null) {
      j = zzb.hashCode();
    }
    return i ^ j;
  }
  
  public final V setValue(V paramV)
  {
    zzfjy.zza(zzc);
    Object localObject = zzb;
    zzb = paramV;
    return localObject;
  }
  
  public final String toString()
  {
    String str1 = String.valueOf(zza);
    String str2 = String.valueOf(zzb);
    StringBuilder localStringBuilder = new StringBuilder(1 + String.valueOf(str1).length() + String.valueOf(str2).length());
    localStringBuilder.append(str1);
    localStringBuilder.append("=");
    localStringBuilder.append(str2);
    return localStringBuilder.toString();
  }
}
