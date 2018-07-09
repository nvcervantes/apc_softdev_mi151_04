package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.zzbt;

public final class zzcyw
  extends zzbgl
{
  public static final Parcelable.Creator<zzcyw> CREATOR = new zzcyx();
  private int zza;
  private final ConnectionResult zzb;
  private final zzbt zzc;
  
  public zzcyw(int paramInt)
  {
    this(new ConnectionResult(8, null), null);
  }
  
  zzcyw(int paramInt, ConnectionResult paramConnectionResult, zzbt paramZzbt)
  {
    zza = paramInt;
    zzb = paramConnectionResult;
    zzc = paramZzbt;
  }
  
  private zzcyw(ConnectionResult paramConnectionResult, zzbt paramZzbt)
  {
    this(1, paramConnectionResult, null);
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt)
  {
    int i = zzbgo.zza(paramParcel);
    zzbgo.zza(paramParcel, 1, zza);
    zzbgo.zza(paramParcel, 2, zzb, paramInt, false);
    zzbgo.zza(paramParcel, 3, zzc, paramInt, false);
    zzbgo.zza(paramParcel, i);
  }
  
  public final ConnectionResult zza()
  {
    return zzb;
  }
  
  public final zzbt zzb()
  {
    return zzc;
  }
}
