package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Hide;

@Hide
public final class zzcgl
  extends zzbgl
  implements Result
{
  public static final Parcelable.Creator<zzcgl> CREATOR = new zzcgm();
  @Hide
  private static zzcgl zza = new zzcgl(Status.zza);
  private final Status zzb;
  
  @Hide
  public zzcgl(Status paramStatus)
  {
    zzb = paramStatus;
  }
  
  public final Status getStatus()
  {
    return zzb;
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt)
  {
    int i = zzbgo.zza(paramParcel);
    zzbgo.zza(paramParcel, 1, getStatus(), paramInt, false);
    zzbgo.zza(paramParcel, i);
  }
}
