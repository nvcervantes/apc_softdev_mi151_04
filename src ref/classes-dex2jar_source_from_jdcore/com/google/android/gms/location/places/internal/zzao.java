package com.google.android.gms.location.places.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;

public final class zzao
  extends zzbgl
{
  public static final Parcelable.Creator<zzao> CREATOR = new zze();
  private int zza;
  private int zzb;
  
  zzao(int paramInt1, int paramInt2)
  {
    zza = paramInt1;
    zzb = paramInt2;
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramInt = zzbgo.zza(paramParcel);
    zzbgo.zza(paramParcel, 1, zza);
    zzbgo.zza(paramParcel, 2, zzb);
    zzbgo.zza(paramParcel, paramInt);
  }
}
