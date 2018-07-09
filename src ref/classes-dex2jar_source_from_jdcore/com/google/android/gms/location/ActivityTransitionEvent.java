package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.Arrays;

public class ActivityTransitionEvent
  extends zzbgl
{
  public static final Parcelable.Creator<ActivityTransitionEvent> CREATOR = new zzd();
  private final int zza;
  private final int zzb;
  private final long zzc;
  
  public ActivityTransitionEvent(int paramInt1, int paramInt2, long paramLong)
  {
    DetectedActivity.zza(paramInt1);
    ActivityTransition.zza(paramInt2);
    zza = paramInt1;
    zzb = paramInt2;
    zzc = paramLong;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {
      return true;
    }
    if (!(paramObject instanceof ActivityTransitionEvent)) {
      return false;
    }
    paramObject = (ActivityTransitionEvent)paramObject;
    return (zza == zza) && (zzb == zzb) && (zzc == zzc);
  }
  
  public int getActivityType()
  {
    return zza;
  }
  
  public long getElapsedRealTimeNanos()
  {
    return zzc;
  }
  
  public int getTransitionType()
  {
    return zzb;
  }
  
  public int hashCode()
  {
    return Arrays.hashCode(new Object[] { Integer.valueOf(zza), Integer.valueOf(zzb), Long.valueOf(zzc) });
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder1 = new StringBuilder();
    int i = zza;
    StringBuilder localStringBuilder2 = new StringBuilder(24);
    localStringBuilder2.append("ActivityType ");
    localStringBuilder2.append(i);
    localStringBuilder1.append(localStringBuilder2.toString());
    localStringBuilder1.append(" ");
    i = zzb;
    localStringBuilder2 = new StringBuilder(26);
    localStringBuilder2.append("TransitionType ");
    localStringBuilder2.append(i);
    localStringBuilder1.append(localStringBuilder2.toString());
    localStringBuilder1.append(" ");
    long l = zzc;
    localStringBuilder2 = new StringBuilder(41);
    localStringBuilder2.append("ElapsedRealTimeNanos ");
    localStringBuilder2.append(l);
    localStringBuilder1.append(localStringBuilder2.toString());
    return localStringBuilder1.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramInt = zzbgo.zza(paramParcel);
    zzbgo.zza(paramParcel, 1, getActivityType());
    zzbgo.zza(paramParcel, 2, getTransitionType());
    zzbgo.zza(paramParcel, 3, getElapsedRealTimeNanos());
    zzbgo.zza(paramParcel, paramInt);
  }
}
