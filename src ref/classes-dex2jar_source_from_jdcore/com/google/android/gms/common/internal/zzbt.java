package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;

public final class zzbt
  extends zzbgl
{
  public static final Parcelable.Creator<zzbt> CREATOR = new zzbu();
  private int zza;
  private IBinder zzb;
  private ConnectionResult zzc;
  private boolean zzd;
  private boolean zze;
  
  zzbt(int paramInt, IBinder paramIBinder, ConnectionResult paramConnectionResult, boolean paramBoolean1, boolean paramBoolean2)
  {
    zza = paramInt;
    zzb = paramIBinder;
    zzc = paramConnectionResult;
    zzd = paramBoolean1;
    zze = paramBoolean2;
  }
  
  public final boolean equals(Object paramObject)
  {
    if (this == paramObject) {
      return true;
    }
    if (!(paramObject instanceof zzbt)) {
      return false;
    }
    paramObject = (zzbt)paramObject;
    return (zzc.equals(zzc)) && (zza().equals(paramObject.zza()));
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt)
  {
    int i = zzbgo.zza(paramParcel);
    zzbgo.zza(paramParcel, 1, zza);
    zzbgo.zza(paramParcel, 2, zzb, false);
    zzbgo.zza(paramParcel, 3, zzc, paramInt, false);
    zzbgo.zza(paramParcel, 4, zzd);
    zzbgo.zza(paramParcel, 5, zze);
    zzbgo.zza(paramParcel, i);
  }
  
  public final zzan zza()
  {
    IBinder localIBinder = zzb;
    if (localIBinder == null) {
      return null;
    }
    IInterface localIInterface = localIBinder.queryLocalInterface("com.google.android.gms.common.internal.IAccountAccessor");
    if ((localIInterface instanceof zzan)) {
      return (zzan)localIInterface;
    }
    return new zzap(localIBinder);
  }
  
  public final ConnectionResult zzb()
  {
    return zzc;
  }
  
  public final boolean zzc()
  {
    return zzd;
  }
  
  public final boolean zzd()
  {
    return zze;
  }
}
