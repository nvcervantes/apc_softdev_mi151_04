package com.google.android.gms.internal;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

public final class zzcym
  extends zzbgl
  implements Result
{
  public static final Parcelable.Creator<zzcym> CREATOR = new zzcyn();
  private int zza;
  private int zzb;
  private Intent zzc;
  
  public zzcym()
  {
    this(0, null);
  }
  
  zzcym(int paramInt1, int paramInt2, Intent paramIntent)
  {
    zza = paramInt1;
    zzb = paramInt2;
    zzc = paramIntent;
  }
  
  private zzcym(int paramInt, Intent paramIntent)
  {
    this(2, 0, null);
  }
  
  public final Status getStatus()
  {
    if (zzb == 0) {
      return Status.zza;
    }
    return Status.zze;
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt)
  {
    int i = zzbgo.zza(paramParcel);
    zzbgo.zza(paramParcel, 1, zza);
    zzbgo.zza(paramParcel, 2, zzb);
    zzbgo.zza(paramParcel, 3, zzc, paramInt, false);
    zzbgo.zza(paramParcel, i);
  }
}
