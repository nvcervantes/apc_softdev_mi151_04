package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zzbhj
  extends zzbgl
{
  public static final Parcelable.Creator<zzbhj> CREATOR = new zzbhk();
  private int zza;
  private final zzbhl zzb;
  
  zzbhj(int paramInt, zzbhl paramZzbhl)
  {
    zza = paramInt;
    zzb = paramZzbhl;
  }
  
  private zzbhj(zzbhl paramZzbhl)
  {
    zza = 1;
    zzb = paramZzbhl;
  }
  
  public static zzbhj zza(zzbhr<?, ?> paramZzbhr)
  {
    if ((paramZzbhr instanceof zzbhl)) {
      return new zzbhj((zzbhl)paramZzbhr);
    }
    throw new IllegalArgumentException("Unsupported safe parcelable field converter class.");
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt)
  {
    int i = zzbgo.zza(paramParcel);
    zzbgo.zza(paramParcel, 1, zza);
    zzbgo.zza(paramParcel, 2, zzb, paramInt, false);
    zzbgo.zza(paramParcel, i);
  }
  
  public final zzbhr<?, ?> zza()
  {
    if (zzb != null) {
      return zzb;
    }
    throw new IllegalStateException("There was no converter wrapped in this ConverterWrapper.");
  }
}
