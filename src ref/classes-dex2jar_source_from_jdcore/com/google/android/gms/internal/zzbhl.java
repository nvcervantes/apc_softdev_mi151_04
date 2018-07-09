package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.util.SparseArray;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public final class zzbhl
  extends zzbgl
  implements zzbhr<String, Integer>
{
  public static final Parcelable.Creator<zzbhl> CREATOR = new zzbhn();
  private int zza;
  private final HashMap<String, Integer> zzb;
  private final SparseArray<String> zzc;
  private final ArrayList<zzbhm> zzd;
  
  public zzbhl()
  {
    zza = 1;
    zzb = new HashMap();
    zzc = new SparseArray();
    zzd = null;
  }
  
  zzbhl(int paramInt, ArrayList<zzbhm> paramArrayList)
  {
    zza = paramInt;
    zzb = new HashMap();
    zzc = new SparseArray();
    zzd = null;
    zza(paramArrayList);
  }
  
  private final void zza(ArrayList<zzbhm> paramArrayList)
  {
    paramArrayList = (ArrayList)paramArrayList;
    int j = paramArrayList.size();
    int i = 0;
    while (i < j)
    {
      Object localObject = paramArrayList.get(i);
      i += 1;
      localObject = (zzbhm)localObject;
      zza(zza, zzb);
    }
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
      localArrayList.add(new zzbhm(str, ((Integer)zzb.get(str)).intValue()));
    }
    zzbgo.zzc(paramParcel, 2, localArrayList, false);
    zzbgo.zza(paramParcel, paramInt);
  }
  
  public final zzbhl zza(String paramString, int paramInt)
  {
    zzb.put(paramString, Integer.valueOf(paramInt));
    zzc.put(paramInt, paramString);
    return this;
  }
}
