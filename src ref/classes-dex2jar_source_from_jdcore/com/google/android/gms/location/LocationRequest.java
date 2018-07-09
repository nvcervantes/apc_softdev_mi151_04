package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.SystemClock;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.Arrays;

public final class LocationRequest
  extends zzbgl
  implements ReflectedParcelable
{
  public static final Parcelable.Creator<LocationRequest> CREATOR = new zzab();
  public static final int PRIORITY_BALANCED_POWER_ACCURACY = 102;
  public static final int PRIORITY_HIGH_ACCURACY = 100;
  public static final int PRIORITY_LOW_POWER = 104;
  public static final int PRIORITY_NO_POWER = 105;
  private int zza;
  private long zzb;
  private long zzc;
  private boolean zzd;
  private long zze;
  private int zzf;
  private float zzg;
  private long zzh;
  
  @Hide
  public LocationRequest()
  {
    zza = 102;
    zzb = 3600000L;
    zzc = 600000L;
    zzd = false;
    zze = Long.MAX_VALUE;
    zzf = Integer.MAX_VALUE;
    zzg = 0.0F;
    zzh = 0L;
  }
  
  @Hide
  LocationRequest(int paramInt1, long paramLong1, long paramLong2, boolean paramBoolean, long paramLong3, int paramInt2, float paramFloat, long paramLong4)
  {
    zza = paramInt1;
    zzb = paramLong1;
    zzc = paramLong2;
    zzd = paramBoolean;
    zze = paramLong3;
    zzf = paramInt2;
    zzg = paramFloat;
    zzh = paramLong4;
  }
  
  public static LocationRequest create()
  {
    return new LocationRequest();
  }
  
  private static void zza(long paramLong)
  {
    if (paramLong < 0L)
    {
      StringBuilder localStringBuilder = new StringBuilder(38);
      localStringBuilder.append("invalid interval: ");
      localStringBuilder.append(paramLong);
      throw new IllegalArgumentException(localStringBuilder.toString());
    }
  }
  
  public final boolean equals(Object paramObject)
  {
    if (this == paramObject) {
      return true;
    }
    if (!(paramObject instanceof LocationRequest)) {
      return false;
    }
    paramObject = (LocationRequest)paramObject;
    return (zza == zza) && (zzb == zzb) && (zzc == zzc) && (zzd == zzd) && (zze == zze) && (zzf == zzf) && (zzg == zzg) && (getMaxWaitTime() == paramObject.getMaxWaitTime());
  }
  
  public final long getExpirationTime()
  {
    return zze;
  }
  
  public final long getFastestInterval()
  {
    return zzc;
  }
  
  public final long getInterval()
  {
    return zzb;
  }
  
  public final long getMaxWaitTime()
  {
    long l2 = zzh;
    long l1 = l2;
    if (l2 < zzb) {
      l1 = zzb;
    }
    return l1;
  }
  
  public final int getNumUpdates()
  {
    return zzf;
  }
  
  public final int getPriority()
  {
    return zza;
  }
  
  public final float getSmallestDisplacement()
  {
    return zzg;
  }
  
  public final int hashCode()
  {
    return Arrays.hashCode(new Object[] { Integer.valueOf(zza), Long.valueOf(zzb), Float.valueOf(zzg), Long.valueOf(zzh) });
  }
  
  public final boolean isFastestIntervalExplicitlySet()
  {
    return zzd;
  }
  
  public final LocationRequest setExpirationDuration(long paramLong)
  {
    long l2 = SystemClock.elapsedRealtime();
    long l1 = Long.MAX_VALUE;
    if (paramLong > Long.MAX_VALUE - l2) {}
    for (paramLong = l1;; paramLong += l2)
    {
      zze = paramLong;
      break;
    }
    if (zze < 0L) {
      zze = 0L;
    }
    return this;
  }
  
  public final LocationRequest setExpirationTime(long paramLong)
  {
    zze = paramLong;
    if (zze < 0L) {
      zze = 0L;
    }
    return this;
  }
  
  public final LocationRequest setFastestInterval(long paramLong)
  {
    zza(paramLong);
    zzd = true;
    zzc = paramLong;
    return this;
  }
  
  public final LocationRequest setInterval(long paramLong)
  {
    zza(paramLong);
    zzb = paramLong;
    if (!zzd) {
      zzc = ((zzb / 6.0D));
    }
    return this;
  }
  
  public final LocationRequest setMaxWaitTime(long paramLong)
  {
    zza(paramLong);
    zzh = paramLong;
    return this;
  }
  
  public final LocationRequest setNumUpdates(int paramInt)
  {
    if (paramInt <= 0)
    {
      StringBuilder localStringBuilder = new StringBuilder(31);
      localStringBuilder.append("invalid numUpdates: ");
      localStringBuilder.append(paramInt);
      throw new IllegalArgumentException(localStringBuilder.toString());
    }
    zzf = paramInt;
    return this;
  }
  
  public final LocationRequest setPriority(int paramInt)
  {
    switch (paramInt)
    {
    case 101: 
    case 103: 
    default: 
      StringBuilder localStringBuilder = new StringBuilder(28);
      localStringBuilder.append("invalid quality: ");
      localStringBuilder.append(paramInt);
      throw new IllegalArgumentException(localStringBuilder.toString());
    }
    zza = paramInt;
    return this;
  }
  
  public final LocationRequest setSmallestDisplacement(float paramFloat)
  {
    if (paramFloat < 0.0F)
    {
      StringBuilder localStringBuilder = new StringBuilder(37);
      localStringBuilder.append("invalid displacement: ");
      localStringBuilder.append(paramFloat);
      throw new IllegalArgumentException(localStringBuilder.toString());
    }
    zzg = paramFloat;
    return this;
  }
  
  public final String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Request[");
    String str;
    switch (zza)
    {
    case 101: 
    case 103: 
    default: 
      str = "???";
      break;
    case 105: 
      str = "PRIORITY_NO_POWER";
      break;
    case 104: 
      str = "PRIORITY_LOW_POWER";
      break;
    case 102: 
      str = "PRIORITY_BALANCED_POWER_ACCURACY";
      break;
    case 100: 
      str = "PRIORITY_HIGH_ACCURACY";
    }
    localStringBuilder.append(str);
    if (zza != 105)
    {
      localStringBuilder.append(" requested=");
      localStringBuilder.append(zzb);
      localStringBuilder.append("ms");
    }
    localStringBuilder.append(" fastest=");
    localStringBuilder.append(zzc);
    localStringBuilder.append("ms");
    if (zzh > zzb)
    {
      localStringBuilder.append(" maxWait=");
      localStringBuilder.append(zzh);
      localStringBuilder.append("ms");
    }
    if (zzg > 0.0F)
    {
      localStringBuilder.append(" smallestDisplacement=");
      localStringBuilder.append(zzg);
      localStringBuilder.append("m");
    }
    if (zze != Long.MAX_VALUE)
    {
      long l1 = zze;
      long l2 = SystemClock.elapsedRealtime();
      localStringBuilder.append(" expireIn=");
      localStringBuilder.append(l1 - l2);
      localStringBuilder.append("ms");
    }
    if (zzf != Integer.MAX_VALUE)
    {
      localStringBuilder.append(" num=");
      localStringBuilder.append(zzf);
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
    zzbgo.zza(paramParcel, 6, zzf);
    zzbgo.zza(paramParcel, 7, zzg);
    zzbgo.zza(paramParcel, 8, zzh);
    zzbgo.zza(paramParcel, paramInt);
  }
}
