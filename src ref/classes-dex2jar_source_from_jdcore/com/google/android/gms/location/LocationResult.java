package com.google.android.gms.location;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public final class LocationResult
  extends zzbgl
  implements ReflectedParcelable
{
  public static final Parcelable.Creator<LocationResult> CREATOR = new zzac();
  static final List<Location> zza = ;
  private final List<Location> zzb;
  
  @Hide
  LocationResult(List<Location> paramList)
  {
    zzb = paramList;
  }
  
  public static LocationResult create(List<Location> paramList)
  {
    Object localObject = paramList;
    if (paramList == null) {
      localObject = zza;
    }
    return new LocationResult((List)localObject);
  }
  
  public static LocationResult extractResult(Intent paramIntent)
  {
    if (!hasResult(paramIntent)) {
      return null;
    }
    return (LocationResult)paramIntent.getExtras().getParcelable("com.google.android.gms.location.EXTRA_LOCATION_RESULT");
  }
  
  public static boolean hasResult(Intent paramIntent)
  {
    if (paramIntent == null) {
      return false;
    }
    return paramIntent.hasExtra("com.google.android.gms.location.EXTRA_LOCATION_RESULT");
  }
  
  public final boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof LocationResult)) {
      return false;
    }
    paramObject = (LocationResult)paramObject;
    if (zzb.size() != zzb.size()) {
      return false;
    }
    paramObject = zzb.iterator();
    Iterator localIterator = zzb.iterator();
    while (paramObject.hasNext())
    {
      Location localLocation1 = (Location)localIterator.next();
      Location localLocation2 = (Location)paramObject.next();
      if (localLocation1.getTime() != localLocation2.getTime()) {
        return false;
      }
    }
    return true;
  }
  
  public final Location getLastLocation()
  {
    int i = zzb.size();
    if (i == 0) {
      return null;
    }
    return (Location)zzb.get(i - 1);
  }
  
  @NonNull
  public final List<Location> getLocations()
  {
    return zzb;
  }
  
  public final int hashCode()
  {
    Iterator localIterator = zzb.iterator();
    long l;
    for (int i = 17; localIterator.hasNext(); i = i * 31 + (int)(l ^ l >>> 32)) {
      l = ((Location)localIterator.next()).getTime();
    }
    return i;
  }
  
  public final String toString()
  {
    String str = String.valueOf(zzb);
    StringBuilder localStringBuilder = new StringBuilder(27 + String.valueOf(str).length());
    localStringBuilder.append("LocationResult[locations: ");
    localStringBuilder.append(str);
    localStringBuilder.append("]");
    return localStringBuilder.toString();
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramInt = zzbgo.zza(paramParcel);
    zzbgo.zzc(paramParcel, 1, getLocations(), false);
    zzbgo.zza(paramParcel, paramInt);
  }
}
