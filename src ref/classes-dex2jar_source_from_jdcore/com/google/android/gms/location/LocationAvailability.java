package com.google.android.gms.location;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.Arrays;

public final class LocationAvailability
  extends zzbgl
  implements ReflectedParcelable
{
  public static final Parcelable.Creator<LocationAvailability> CREATOR = new zzaa();
  @Deprecated
  private int zza;
  @Deprecated
  private int zzb;
  private long zzc;
  private int zzd;
  private zzaj[] zze;
  
  @Hide
  LocationAvailability(int paramInt1, int paramInt2, int paramInt3, long paramLong, zzaj[] paramArrayOfZzaj)
  {
    zzd = paramInt1;
    zza = paramInt2;
    zzb = paramInt3;
    zzc = paramLong;
    zze = paramArrayOfZzaj;
  }
  
  public static LocationAvailability extractLocationAvailability(Intent paramIntent)
  {
    if (!hasLocationAvailability(paramIntent)) {
      return null;
    }
    return (LocationAvailability)paramIntent.getExtras().getParcelable("com.google.android.gms.location.EXTRA_LOCATION_AVAILABILITY");
  }
  
  public static boolean hasLocationAvailability(Intent paramIntent)
  {
    if (paramIntent == null) {
      return false;
    }
    return paramIntent.hasExtra("com.google.android.gms.location.EXTRA_LOCATION_AVAILABILITY");
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
      paramObject = (LocationAvailability)paramObject;
      if ((zza == zza) && (zzb == zzb) && (zzc == zzc) && (zzd == zzd) && (Arrays.equals(zze, zze))) {
        return true;
      }
    }
    return false;
  }
  
  public final int hashCode()
  {
    return Arrays.hashCode(new Object[] { Integer.valueOf(zzd), Integer.valueOf(zza), Integer.valueOf(zzb), Long.valueOf(zzc), zze });
  }
  
  public final boolean isLocationAvailable()
  {
    return zzd < 1000;
  }
  
  public final String toString()
  {
    boolean bool = isLocationAvailable();
    StringBuilder localStringBuilder = new StringBuilder(48);
    localStringBuilder.append("LocationAvailability[isLocationAvailable: ");
    localStringBuilder.append(bool);
    localStringBuilder.append("]");
    return localStringBuilder.toString();
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt)
  {
    int i = zzbgo.zza(paramParcel);
    zzbgo.zza(paramParcel, 1, zza);
    zzbgo.zza(paramParcel, 2, zzb);
    zzbgo.zza(paramParcel, 3, zzc);
    zzbgo.zza(paramParcel, 4, zzd);
    zzbgo.zza(paramParcel, 5, zze, paramInt, false);
    zzbgo.zza(paramParcel, i);
  }
}
