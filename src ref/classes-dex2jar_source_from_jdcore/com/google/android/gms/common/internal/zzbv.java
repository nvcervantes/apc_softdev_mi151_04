package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;

public final class zzbv
  extends zzbgl
{
  public static final Parcelable.Creator<zzbv> CREATOR = new zzbw();
  private int zza;
  private final int zzb;
  private final int zzc;
  @Deprecated
  private final Scope[] zzd;
  
  zzbv(int paramInt1, int paramInt2, int paramInt3, Scope[] paramArrayOfScope)
  {
    zza = paramInt1;
    zzb = paramInt2;
    zzc = paramInt3;
    zzd = paramArrayOfScope;
  }
  
  public zzbv(int paramInt1, int paramInt2, Scope[] paramArrayOfScope)
  {
    this(1, paramInt1, paramInt2, null);
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt)
  {
    int i = zzbgo.zza(paramParcel);
    zzbgo.zza(paramParcel, 1, zza);
    zzbgo.zza(paramParcel, 2, zzb);
    zzbgo.zza(paramParcel, 3, zzc);
    zzbgo.zza(paramParcel, 4, zzd, paramInt, false);
    zzbgo.zza(paramParcel, i);
  }
}
