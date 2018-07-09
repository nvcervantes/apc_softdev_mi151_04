package com.google.android.gms.location;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import com.google.android.gms.internal.zzbgq;
import java.util.Collections;
import java.util.List;

public class ActivityTransitionResult
  extends zzbgl
{
  public static final Parcelable.Creator<ActivityTransitionResult> CREATOR = new zzg();
  private final List<ActivityTransitionEvent> zza;
  
  public ActivityTransitionResult(List<ActivityTransitionEvent> paramList)
  {
    zzbq.zza(paramList, "transitionEvents list can't be null.");
    if (!paramList.isEmpty())
    {
      int i = 1;
      while (i < paramList.size())
      {
        boolean bool;
        if (((ActivityTransitionEvent)paramList.get(i)).getElapsedRealTimeNanos() >= ((ActivityTransitionEvent)paramList.get(i - 1)).getElapsedRealTimeNanos()) {
          bool = true;
        } else {
          bool = false;
        }
        zzbq.zzb(bool);
        i += 1;
      }
    }
    zza = Collections.unmodifiableList(paramList);
  }
  
  @Nullable
  public static ActivityTransitionResult extractResult(Intent paramIntent)
  {
    if (!hasResult(paramIntent)) {
      return null;
    }
    return (ActivityTransitionResult)zzbgq.zza(paramIntent, "com.google.android.location.internal.EXTRA_ACTIVITY_TRANSITION_RESULT", CREATOR);
  }
  
  public static boolean hasResult(@Nullable Intent paramIntent)
  {
    if (paramIntent == null) {
      return false;
    }
    return paramIntent.hasExtra("com.google.android.location.internal.EXTRA_ACTIVITY_TRANSITION_RESULT");
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {
      return true;
    }
    if ((paramObject != null) && (getClass() == paramObject.getClass())) {
      return zza.equals(zza);
    }
    return false;
  }
  
  public List<ActivityTransitionEvent> getTransitionEvents()
  {
    return zza;
  }
  
  public int hashCode()
  {
    return zza.hashCode();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramInt = zzbgo.zza(paramParcel);
    zzbgo.zzc(paramParcel, 1, getTransitionEvents(), false);
    zzbgo.zza(paramParcel, paramInt);
  }
}
