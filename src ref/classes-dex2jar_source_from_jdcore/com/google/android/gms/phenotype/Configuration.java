package com.google.android.gms.phenotype;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

@KeepForSdk
public class Configuration
  extends zzbgl
  implements Comparable<Configuration>
{
  @KeepForSdk
  public static final Parcelable.Creator<Configuration> CREATOR = new zzc();
  private int zza;
  private zzi[] zzb;
  private String[] zzc;
  private Map<String, zzi> zzd;
  
  public Configuration(int paramInt, zzi[] paramArrayOfZzi, String[] paramArrayOfString)
  {
    zza = paramInt;
    zzb = paramArrayOfZzi;
    zzd = new TreeMap();
    paramInt = 0;
    int i = paramArrayOfZzi.length;
    while (paramInt < i)
    {
      zzi localZzi = paramArrayOfZzi[paramInt];
      zzd.put(zza, localZzi);
      paramInt += 1;
    }
    zzc = paramArrayOfString;
    if (zzc != null) {
      Arrays.sort(zzc);
    }
  }
  
  public boolean equals(Object paramObject)
  {
    if ((paramObject instanceof Configuration))
    {
      paramObject = (Configuration)paramObject;
      if ((zza == zza) && (zzn.zza(zzd, zzd)) && (Arrays.equals(zzc, zzc))) {
        return true;
      }
    }
    return false;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder("Configuration(");
    localStringBuilder.append(zza);
    localStringBuilder.append(", ");
    localStringBuilder.append("(");
    Object localObject = zzd.values().iterator();
    while (((Iterator)localObject).hasNext())
    {
      localStringBuilder.append((zzi)((Iterator)localObject).next());
      localStringBuilder.append(", ");
    }
    localStringBuilder.append(")");
    localStringBuilder.append(", ");
    localStringBuilder.append("(");
    if (zzc != null)
    {
      localObject = zzc;
      int j = localObject.length;
      int i = 0;
      while (i < j)
      {
        localStringBuilder.append(localObject[i]);
        localStringBuilder.append(", ");
        i += 1;
      }
    }
    localStringBuilder.append("null");
    localStringBuilder.append(")");
    localStringBuilder.append(")");
    return localStringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    int i = zzbgo.zza(paramParcel);
    zzbgo.zza(paramParcel, 2, zza);
    zzbgo.zza(paramParcel, 3, zzb, paramInt, false);
    zzbgo.zza(paramParcel, 4, zzc, false);
    zzbgo.zza(paramParcel, i);
  }
}
