package com.google.android.gms.clearcut;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.Arrays;

public final class zzc
  extends zzbgl
{
  public static final Parcelable.Creator<zzc> CREATOR = new zzd();
  private boolean zza;
  private long zzb;
  private long zzc;
  
  public zzc(boolean paramBoolean, long paramLong1, long paramLong2)
  {
    zza = paramBoolean;
    zzb = paramLong1;
    zzc = paramLong2;
  }
  
  public final boolean equals(Object paramObject)
  {
    if (this == paramObject) {
      return true;
    }
    if ((paramObject instanceof zzc))
    {
      paramObject = (zzc)paramObject;
      if ((zza == zza) && (zzb == zzb) && (zzc == zzc)) {
        return true;
      }
    }
    return false;
  }
  
  public final int hashCode()
  {
    return Arrays.hashCode(new Object[] { Boolean.valueOf(zza), Long.valueOf(zzb), Long.valueOf(zzc) });
  }
  
  public final String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder("CollectForDebugParcelable[skipPersistentStorage: ");
    localStringBuilder.append(zza);
    localStringBuilder.append(",collectForDebugStartTimeMillis: ");
    localStringBuilder.append(zzb);
    localStringBuilder.append(",collectForDebugExpiryTimeMillis: ");
    localStringBuilder.append(zzc);
    localStringBuilder.append("]");
    return localStringBuilder.toString();
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramInt = zzbgo.zza(paramParcel);
    zzbgo.zza(paramParcel, 1, zza);
    zzbgo.zza(paramParcel, 2, zzc);
    zzbgo.zza(paramParcel, 3, zzb);
    zzbgo.zza(paramParcel, paramInt);
  }
}
