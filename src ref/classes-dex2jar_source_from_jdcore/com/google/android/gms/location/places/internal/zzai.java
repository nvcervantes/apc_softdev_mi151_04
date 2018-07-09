package com.google.android.gms.location.places.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.common.internal.zzbi;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceLikelihood;
import java.util.Arrays;

public final class zzai
  extends zzbgl
  implements PlaceLikelihood
{
  @Hide
  public static final Parcelable.Creator<zzai> CREATOR = new zzaj();
  private PlaceEntity zza;
  private float zzb;
  
  @Hide
  zzai(PlaceEntity paramPlaceEntity, float paramFloat)
  {
    zza = paramPlaceEntity;
    zzb = paramFloat;
  }
  
  public final boolean equals(Object paramObject)
  {
    if (this == paramObject) {
      return true;
    }
    if (!(paramObject instanceof zzai)) {
      return false;
    }
    paramObject = (zzai)paramObject;
    return (zza.equals(zza)) && (zzb == zzb);
  }
  
  public final float getLikelihood()
  {
    return zzb;
  }
  
  public final Place getPlace()
  {
    return zza;
  }
  
  public final int hashCode()
  {
    return Arrays.hashCode(new Object[] { zza, Float.valueOf(zzb) });
  }
  
  public final boolean isDataValid()
  {
    return true;
  }
  
  public final String toString()
  {
    return zzbg.zza(this).zza("place", zza).zza("likelihood", Float.valueOf(zzb)).toString();
  }
  
  @Hide
  public final void writeToParcel(Parcel paramParcel, int paramInt)
  {
    int i = zzbgo.zza(paramParcel);
    zzbgo.zza(paramParcel, 1, zza, paramInt, false);
    zzbgo.zza(paramParcel, 2, zzb);
    zzbgo.zza(paramParcel, i);
  }
}
