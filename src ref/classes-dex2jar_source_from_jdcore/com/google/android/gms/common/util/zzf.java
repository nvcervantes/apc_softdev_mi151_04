package com.google.android.gms.common.util;

import android.support.v4.util.ArrayMap;
import android.support.v4.util.ArraySet;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class zzf
{
  private static <K, V> Map<K, V> zza(int paramInt, boolean paramBoolean, K[] paramArrayOfK, V[] paramArrayOfV)
  {
    int i = 0;
    Map localMap = zzb(paramInt, false);
    paramInt = i;
    while (paramInt < paramArrayOfK.length)
    {
      localMap.put(paramArrayOfK[paramInt], paramArrayOfV[paramInt]);
      paramInt += 1;
    }
    return localMap;
  }
  
  public static <K, V> Map<K, V> zza(K paramK1, V paramV1, K paramK2, V paramV2, K paramK3, V paramV3)
  {
    Map localMap = zzb(3, false);
    localMap.put(paramK1, paramV1);
    localMap.put(paramK2, paramV2);
    localMap.put(paramK3, paramV3);
    return Collections.unmodifiableMap(localMap);
  }
  
  public static <K, V> Map<K, V> zza(K paramK1, V paramV1, K paramK2, V paramV2, K paramK3, V paramV3, K paramK4, V paramV4, K paramK5, V paramV5, K paramK6, V paramV6)
  {
    Map localMap = zzb(6, false);
    localMap.put(paramK1, paramV1);
    localMap.put(paramK2, paramV2);
    localMap.put(paramK3, paramV3);
    localMap.put(paramK4, paramV4);
    localMap.put(paramK5, paramV5);
    localMap.put(paramK6, paramV6);
    return Collections.unmodifiableMap(localMap);
  }
  
  public static <K, V> Map<K, V> zza(K[] paramArrayOfK, V[] paramArrayOfV)
  {
    if (paramArrayOfK.length != paramArrayOfV.length)
    {
      int i = paramArrayOfK.length;
      int j = paramArrayOfV.length;
      paramArrayOfK = new StringBuilder(66);
      paramArrayOfK.append("Key and values array lengths not equal: ");
      paramArrayOfK.append(i);
      paramArrayOfK.append(" != ");
      paramArrayOfK.append(j);
      throw new IllegalArgumentException(paramArrayOfK.toString());
    }
    switch (paramArrayOfK.length)
    {
    default: 
      return Collections.unmodifiableMap(zza(paramArrayOfK.length, false, paramArrayOfK, paramArrayOfV));
    case 1: 
      return Collections.singletonMap(paramArrayOfK[0], paramArrayOfV[0]);
    }
    return Collections.emptyMap();
  }
  
  private static <T> Set<T> zza(int paramInt, boolean paramBoolean)
  {
    if (paramInt <= 256) {
      return new ArraySet(paramInt);
    }
    return new HashSet(paramInt, 1.0F);
  }
  
  public static <T> Set<T> zza(T paramT1, T paramT2, T paramT3)
  {
    Set localSet = zza(3, false);
    localSet.add(paramT1);
    localSet.add(paramT2);
    localSet.add(paramT3);
    return Collections.unmodifiableSet(localSet);
  }
  
  public static <T> Set<T> zza(T... paramVarArgs)
  {
    Object localObject1;
    Object localObject2;
    switch (paramVarArgs.length)
    {
    default: 
      localObject1 = zza(paramVarArgs.length, false);
      Collections.addAll((Collection)localObject1, paramVarArgs);
      return Collections.unmodifiableSet((Set)localObject1);
    case 4: 
      localObject1 = paramVarArgs[0];
      localObject2 = paramVarArgs[1];
      T ? = paramVarArgs[2];
      paramVarArgs = paramVarArgs[3];
      Set localSet = zza(4, false);
      localSet.add(localObject1);
      localSet.add(localObject2);
      localSet.add(?);
      localSet.add(paramVarArgs);
      return Collections.unmodifiableSet(localSet);
    case 3: 
      return zza(paramVarArgs[0], paramVarArgs[1], paramVarArgs[2]);
    case 2: 
      localObject1 = paramVarArgs[0];
      paramVarArgs = paramVarArgs[1];
      localObject2 = zza(2, false);
      ((Set)localObject2).add(localObject1);
      ((Set)localObject2).add(paramVarArgs);
      return Collections.unmodifiableSet((Set)localObject2);
    case 1: 
      return Collections.singleton(paramVarArgs[0]);
    }
    return Collections.emptySet();
  }
  
  private static <K, V> Map<K, V> zzb(int paramInt, boolean paramBoolean)
  {
    if (paramInt <= 256) {
      return new ArrayMap(paramInt);
    }
    return new HashMap(paramInt, 1.0F);
  }
}
