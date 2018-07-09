package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zzbhx
  extends zzbgl
{
  public static final Parcelable.Creator<zzbhx> CREATOR = new zzbhu();
  final String zza;
  final zzbhq<?, ?> zzb;
  private int zzc;
  
  zzbhx(int paramInt, String paramString, zzbhq<?, ?> paramZzbhq)
  {
    zzc = paramInt;
    zza = paramString;
    zzb = paramZzbhq;
  }
  
  zzbhx(String paramString, zzbhq<?, ?> paramZzbhq)
  {
    zzc = 1;
    zza = paramString;
    zzb = paramZzbhq;
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt)
  {
    int i = zzbgo.zza(paramParcel);
    zzbgo.zza(paramParcel, 1, zzc);
    zzbgo.zza(paramParcel, 2, zza, false);
    zzbgo.zza(paramParcel, 3, zzb, paramInt, false);
    zzbgo.zza(paramParcel, i);
  }
}
