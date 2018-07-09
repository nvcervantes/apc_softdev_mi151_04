package com.google.android.gms.common;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;

@Hide
public final class zzc
  extends zzbgl
{
  public static final Parcelable.Creator<zzc> CREATOR = new zzd();
  private String zza;
  private int zzb;
  
  public zzc(String paramString, int paramInt)
  {
    zza = paramString;
    zzb = paramInt;
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramInt = zzbgo.zza(paramParcel);
    zzbgo.zza(paramParcel, 1, zza, false);
    zzbgo.zza(paramParcel, 2, zzb);
    zzbgo.zza(paramParcel, paramInt);
  }
}
