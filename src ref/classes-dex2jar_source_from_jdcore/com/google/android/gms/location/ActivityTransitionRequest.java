package com.google.android.gms.location;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import com.google.android.gms.internal.zzbgq;
import com.google.android.gms.internal.zzcfs;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

public class ActivityTransitionRequest
  extends zzbgl
{
  public static final Parcelable.Creator<ActivityTransitionRequest> CREATOR = new zzf();
  public static final Comparator<ActivityTransition> IS_SAME_TRANSITION = new zze();
  private final List<ActivityTransition> zza;
  @Nullable
  private final String zzb;
  private final List<zzcfs> zzc;
  
  public ActivityTransitionRequest(List<ActivityTransition> paramList)
  {
    this(paramList, null, null);
  }
  
  @Hide
  public ActivityTransitionRequest(List<ActivityTransition> paramList, @Nullable String paramString, @Nullable List<zzcfs> paramList1)
  {
    zzbq.zza(paramList, "transitions can't be null");
    boolean bool;
    if (paramList.size() > 0) {
      bool = true;
    } else {
      bool = false;
    }
    zzbq.zzb(bool, "transitions can't be empty.");
    TreeSet localTreeSet = new TreeSet(IS_SAME_TRANSITION);
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
    {
      ActivityTransition localActivityTransition = (ActivityTransition)localIterator.next();
      zzbq.zzb(localTreeSet.add(localActivityTransition), String.format("Found duplicated transition: %s.", new Object[] { localActivityTransition }));
    }
    zza = Collections.unmodifiableList(paramList);
    zzb = paramString;
    if (paramList1 == null) {
      paramList = Collections.emptyList();
    } else {
      paramList = Collections.unmodifiableList(paramList1);
    }
    zzc = paramList;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {
      return true;
    }
    if (paramObject != null)
    {
      if (getClass() != paramObject.getClass()) {
        return false;
      }
      paramObject = (ActivityTransitionRequest)paramObject;
      if ((zzbg.zza(zza, zza)) && (zzbg.zza(zzb, zzb)) && (zzbg.zza(zzc, zzc))) {
        return true;
      }
    }
    return false;
  }
  
  public int hashCode()
  {
    int k = zza.hashCode();
    String str = zzb;
    int j = 0;
    int i;
    if (str != null) {
      i = zzb.hashCode();
    } else {
      i = 0;
    }
    if (zzc != null) {
      j = zzc.hashCode();
    }
    return (k * 31 + i) * 31 + j;
  }
  
  public void serializeToIntentExtra(Intent paramIntent)
  {
    zzbgq.zza(this, paramIntent, "com.google.android.location.internal.EXTRA_ACTIVITY_TRANSITION_REQUEST");
  }
  
  public String toString()
  {
    String str1 = String.valueOf(zza);
    String str2 = zzb;
    String str3 = String.valueOf(zzc);
    StringBuilder localStringBuilder = new StringBuilder(61 + String.valueOf(str1).length() + String.valueOf(str2).length() + String.valueOf(str3).length());
    localStringBuilder.append("ActivityTransitionRequest [mTransitions=");
    localStringBuilder.append(str1);
    localStringBuilder.append(", mTag='");
    localStringBuilder.append(str2);
    localStringBuilder.append('\'');
    localStringBuilder.append(", mClients=");
    localStringBuilder.append(str3);
    localStringBuilder.append(']');
    return localStringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramInt = zzbgo.zza(paramParcel);
    zzbgo.zzc(paramParcel, 1, zza, false);
    zzbgo.zza(paramParcel, 2, zzb, false);
    zzbgo.zzc(paramParcel, 3, zzc, false);
    zzbgo.zza(paramParcel, paramInt);
  }
}
