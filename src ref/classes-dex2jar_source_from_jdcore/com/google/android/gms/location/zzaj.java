package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.Arrays;

@Hide
public final class zzaj
  extends zzbgl
{
  public static final Parcelable.Creator<zzaj> CREATOR = new zzak();
  private int zza;
  private int zzb;
  private long zzc;
  private long zzd;
  
  zzaj(int paramInt1, int paramInt2, long paramLong1, long paramLong2)
  {
    zza = paramInt1;
    zzb = paramInt2;
    zzc = paramLong1;
    zzd = paramLong2;
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
      paramObject = (zzaj)paramObject;
      if ((zza == zza) && (zzb == zzb) && (zzc == zzc) && (zzd == zzd)) {
        return true;
      }
    }
    return false;
  }
  
  public final int hashCode()
  {
    return Arrays.hashCode(new Object[] { Integer.valueOf(zzb), Integer.valueOf(zza), Long.valueOf(zzd), Long.valueOf(zzc) });
  }
  
  public final String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder("NetworkLocationStatus:");
    localStringBuilder.append(" Wifi status: ");
    localStringBuilder.append(zza);
    localStringBuilder.append(" Cell status: ");
    localStringBuilder.append(zzb);
    localStringBuilder.append(" elapsed time NS: ");
    localStringBuilder.append(zzd);
    localStringBuilder.append(" system time ms: ");
    localStringBuilder.append(zzc);
    return localStringBuilder.toString();
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramInt = zzbgo.zza(paramParcel);
    zzbgo.zza(paramParcel, 1, zza);
    zzbgo.zza(paramParcel, 2, zzb);
    zzbgo.zza(paramParcel, 3, zzc);
    zzbgo.zza(paramParcel, 4, zzd);
    zzbgo.zza(paramParcel, paramInt);
  }
}
