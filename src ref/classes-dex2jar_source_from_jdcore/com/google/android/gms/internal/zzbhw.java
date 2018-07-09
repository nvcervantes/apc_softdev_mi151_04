package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public final class zzbhw
  extends zzbgl
{
  public static final Parcelable.Creator<zzbhw> CREATOR = new zzbhz();
  final String zza;
  private int zzb;
  private ArrayList<zzbhx> zzc;
  
  zzbhw(int paramInt, String paramString, ArrayList<zzbhx> paramArrayList)
  {
    zzb = paramInt;
    zza = paramString;
    zzc = paramArrayList;
  }
  
  zzbhw(String paramString, Map<String, zzbhq<?, ?>> paramMap)
  {
    zzb = 1;
    zza = paramString;
    if (paramMap == null)
    {
      paramString = null;
    }
    else
    {
      ArrayList localArrayList = new ArrayList();
      Iterator localIterator = paramMap.keySet().iterator();
      for (;;)
      {
        paramString = localArrayList;
        if (!localIterator.hasNext()) {
          break;
        }
        paramString = (String)localIterator.next();
        localArrayList.add(new zzbhx(paramString, (zzbhq)paramMap.get(paramString)));
      }
    }
    zzc = paramString;
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramInt = zzbgo.zza(paramParcel);
    zzbgo.zza(paramParcel, 1, zzb);
    zzbgo.zza(paramParcel, 2, zza, false);
    zzbgo.zzc(paramParcel, 3, zzc, false);
    zzbgo.zza(paramParcel, paramInt);
  }
  
  final HashMap<String, zzbhq<?, ?>> zza()
  {
    HashMap localHashMap = new HashMap();
    int j = zzc.size();
    int i = 0;
    while (i < j)
    {
      zzbhx localZzbhx = (zzbhx)zzc.get(i);
      localHashMap.put(zza, zzb);
      i += 1;
    }
    return localHashMap;
  }
}
