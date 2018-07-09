package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zzbhm
  extends zzbgl
{
  public static final Parcelable.Creator<zzbhm> CREATOR = new zzbho();
  final String zza;
  final int zzb;
  private int zzc;
  
  zzbhm(int paramInt1, String paramString, int paramInt2)
  {
    zzc = paramInt1;
    zza = paramString;
    zzb = paramInt2;
  }
  
  zzbhm(String paramString, int paramInt)
  {
    zzc = 1;
    zza = paramString;
    zzb = paramInt;
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramInt = zzbgo.zza(paramParcel);
    zzbgo.zza(paramParcel, 1, zzc);
    zzbgo.zza(paramParcel, 2, zza, false);
    zzbgo.zza(paramParcel, 3, zzb);
    zzbgo.zza(paramParcel, paramInt);
  }
}
