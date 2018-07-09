package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.SystemClock;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.Arrays;

@Hide
public final class zzj
  extends zzbgl
{
  public static final Parcelable.Creator<zzj> CREATOR = new zzk();
  @Hide
  private boolean zza;
  @Hide
  private long zzb;
  @Hide
  private float zzc;
  @Hide
  private long zzd;
  @Hide
  private int zze;
  
  public zzj()
  {
    this(true, 50L, 0.0F, Long.MAX_VALUE, Integer.MAX_VALUE);
  }
  
  @Hide
  zzj(boolean paramBoolean, long paramLong1, float paramFloat, long paramLong2, int paramInt)
  {
    zza = paramBoolean;
    zzb = paramLong1;
    zzc = paramFloat;
    zzd = paramLong2;
    zze = paramInt;
  }
  
  public final boolean equals(Object paramObject)
  {
    if (this == paramObject) {
      return true;
    }
    if (!(paramObject instanceof zzj)) {
      return false;
    }
    paramObject = (zzj)paramObject;
    return (zza == zza) && (zzb == zzb) && (Float.compare(zzc, zzc) == 0) && (zzd == zzd) && (zze == zze);
  }
  
  public final int hashCode()
  {
    return Arrays.hashCode(new Object[] { Boolean.valueOf(zza), Long.valueOf(zzb), Float.valueOf(zzc), Long.valueOf(zzd), Integer.valueOf(zze) });
  }
  
  public final String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("DeviceOrientationRequest[mShouldUseMag=");
    localStringBuilder.append(zza);
    localStringBuilder.append(" mMinimumSamplingPeriodMs=");
    localStringBuilder.append(zzb);
    localStringBuilder.append(" mSmallestAngleChangeRadians=");
    localStringBuilder.append(zzc);
    if (zzd != Long.MAX_VALUE)
    {
      long l1 = zzd;
      long l2 = SystemClock.elapsedRealtime();
      localStringBuilder.append(" expireIn=");
      localStringBuilder.append(l1 - l2);
      localStringBuilder.append("ms");
    }
    if (zze != Integer.MAX_VALUE)
    {
      localStringBuilder.append(" num=");
      localStringBuilder.append(zze);
    }
    localStringBuilder.append(']');
    return localStringBuilder.toString();
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramInt = zzbgo.zza(paramParcel);
    zzbgo.zza(paramParcel, 1, zza);
    zzbgo.zza(paramParcel, 2, zzb);
    zzbgo.zza(paramParcel, 3, zzc);
    zzbgo.zza(paramParcel, 4, zzd);
    zzbgo.zza(paramParcel, 5, zze);
    zzbgo.zza(paramParcel, paramInt);
  }
}
