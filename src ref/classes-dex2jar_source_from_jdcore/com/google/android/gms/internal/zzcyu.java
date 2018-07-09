package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbr;

public final class zzcyu
  extends zzbgl
{
  public static final Parcelable.Creator<zzcyu> CREATOR = new zzcyv();
  private int zza;
  private zzbr zzb;
  
  zzcyu(int paramInt, zzbr paramZzbr)
  {
    zza = paramInt;
    zzb = paramZzbr;
  }
  
  public zzcyu(zzbr paramZzbr)
  {
    this(1, paramZzbr);
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt)
  {
    int i = zzbgo.zza(paramParcel);
    zzbgo.zza(paramParcel, 1, zza);
    zzbgo.zza(paramParcel, 2, zzb, paramInt, false);
    zzbgo.zza(paramParcel, i);
  }
}
