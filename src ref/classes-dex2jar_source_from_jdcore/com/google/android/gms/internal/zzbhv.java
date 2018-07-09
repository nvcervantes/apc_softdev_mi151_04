package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbq;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public final class zzbhv
  extends zzbgl
{
  public static final Parcelable.Creator<zzbhv> CREATOR = new zzbhy();
  private int zza;
  private final HashMap<String, Map<String, zzbhq<?, ?>>> zzb;
  private final ArrayList<zzbhw> zzc;
  private final String zzd;
  
  zzbhv(int paramInt, ArrayList<zzbhw> paramArrayList, String paramString)
  {
    zza = paramInt;
    zzc = null;
    HashMap localHashMap = new HashMap();
    int i = paramArrayList.size();
    paramInt = 0;
    while (paramInt < i)
    {
      zzbhw localZzbhw = (zzbhw)paramArrayList.get(paramInt);
      localHashMap.put(zza, localZzbhw.zza());
      paramInt += 1;
    }
    zzb = localHashMap;
    zzd = ((String)zzbq.zza(paramString));
    zzb();
  }
  
  private final void zzb()
  {
    Iterator localIterator1 = zzb.keySet().iterator();
    while (localIterator1.hasNext())
    {
      Object localObject = (String)localIterator1.next();
      localObject = (Map)zzb.get(localObject);
      Iterator localIterator2 = ((Map)localObject).keySet().iterator();
      while (localIterator2.hasNext()) {
        ((zzbhq)((Map)localObject).get((String)localIterator2.next())).zza(this);
      }
    }
  }
  
  public final String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    Iterator localIterator1 = zzb.keySet().iterator();
    while (localIterator1.hasNext())
    {
      Object localObject = (String)localIterator1.next();
      localStringBuilder.append((String)localObject);
      localStringBuilder.append(":\n");
      localObject = (Map)zzb.get(localObject);
      Iterator localIterator2 = ((Map)localObject).keySet().iterator();
      while (localIterator2.hasNext())
      {
        String str = (String)localIterator2.next();
        localStringBuilder.append("  ");
        localStringBuilder.append(str);
        localStringBuilder.append(": ");
        localStringBuilder.append(((Map)localObject).get(str));
      }
    }
    return localStringBuilder.toString();
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramInt = zzbgo.zza(paramParcel);
    zzbgo.zza(paramParcel, 1, zza);
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = zzb.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      localArrayList.add(new zzbhw(str, (Map)zzb.get(str)));
    }
    zzbgo.zzc(paramParcel, 2, localArrayList, false);
    zzbgo.zza(paramParcel, 3, zzd, false);
    zzbgo.zza(paramParcel, paramInt);
  }
  
  public final String zza()
  {
    return zzd;
  }
  
  public final Map<String, zzbhq<?, ?>> zza(String paramString)
  {
    return (Map)zzb.get(paramString);
  }
}
