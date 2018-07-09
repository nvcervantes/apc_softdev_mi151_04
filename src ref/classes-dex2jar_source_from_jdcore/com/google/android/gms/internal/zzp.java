package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

public final class zzp
{
  public final int zza;
  public final byte[] zzb;
  public final Map<String, String> zzc;
  public final List<zzl> zzd;
  public final boolean zze;
  private long zzf;
  
  private zzp(int paramInt, byte[] paramArrayOfByte, Map<String, String> paramMap, List<zzl> paramList, boolean paramBoolean, long paramLong)
  {
    zza = paramInt;
    zzb = paramArrayOfByte;
    zzc = paramMap;
    if (paramList == null) {}
    for (paramArrayOfByte = null;; paramArrayOfByte = Collections.unmodifiableList(paramList))
    {
      zzd = paramArrayOfByte;
      break;
    }
    zze = paramBoolean;
    zzf = paramLong;
  }
  
  @Deprecated
  public zzp(int paramInt, byte[] paramArrayOfByte, Map<String, String> paramMap, boolean paramBoolean, long paramLong)
  {
    this(paramInt, paramArrayOfByte, paramMap, zza(paramMap), paramBoolean, paramLong);
  }
  
  public zzp(int paramInt, byte[] paramArrayOfByte, boolean paramBoolean, long paramLong, List<zzl> paramList)
  {
    this(paramInt, paramArrayOfByte, zza(paramList), paramList, paramBoolean, paramLong);
  }
  
  @Deprecated
  public zzp(byte[] paramArrayOfByte, Map<String, String> paramMap)
  {
    this(200, paramArrayOfByte, paramMap, false, 0L);
  }
  
  private static List<zzl> zza(Map<String, String> paramMap)
  {
    if (paramMap == null) {
      return null;
    }
    if (paramMap.isEmpty()) {
      return Collections.emptyList();
    }
    ArrayList localArrayList = new ArrayList(paramMap.size());
    paramMap = paramMap.entrySet().iterator();
    while (paramMap.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)paramMap.next();
      localArrayList.add(new zzl((String)localEntry.getKey(), (String)localEntry.getValue()));
    }
    return localArrayList;
  }
  
  private static Map<String, String> zza(List<zzl> paramList)
  {
    if (paramList == null) {
      return null;
    }
    if (paramList.isEmpty()) {
      return Collections.emptyMap();
    }
    TreeMap localTreeMap = new TreeMap(String.CASE_INSENSITIVE_ORDER);
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      zzl localZzl = (zzl)paramList.next();
      localTreeMap.put(localZzl.zza(), localZzl.zzb());
    }
    return localTreeMap;
  }
}
