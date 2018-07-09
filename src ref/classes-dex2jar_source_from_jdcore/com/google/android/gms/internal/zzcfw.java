package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.location.zzr;
import com.google.android.gms.location.zzs;

@Hide
public final class zzcfw
  extends zzbgl
{
  public static final Parcelable.Creator<zzcfw> CREATOR = new zzcfx();
  private int zza;
  private zzcfu zzb;
  private zzr zzc;
  private zzcgr zzd;
  
  zzcfw(int paramInt, zzcfu paramZzcfu, IBinder paramIBinder1, IBinder paramIBinder2)
  {
    zza = paramInt;
    zzb = paramZzcfu;
    Object localObject = null;
    if (paramIBinder1 == null) {
      paramZzcfu = null;
    } else {
      paramZzcfu = zzs.zza(paramIBinder1);
    }
    zzc = paramZzcfu;
    if (paramIBinder2 == null)
    {
      paramZzcfu = localObject;
    }
    else if (paramIBinder2 == null)
    {
      paramZzcfu = localObject;
    }
    else
    {
      paramZzcfu = paramIBinder2.queryLocalInterface("com.google.android.gms.location.internal.IFusedLocationProviderCallback");
      if ((paramZzcfu instanceof zzcgr)) {
        paramZzcfu = (zzcgr)paramZzcfu;
      } else {
        paramZzcfu = new zzcgt(paramIBinder2);
      }
    }
    zzd = paramZzcfu;
  }
  
  @Hide
  public final void writeToParcel(Parcel paramParcel, int paramInt)
  {
    int i = zzbgo.zza(paramParcel);
    zzbgo.zza(paramParcel, 1, zza);
    zzbgo.zza(paramParcel, 2, zzb, paramInt, false);
    Object localObject1 = zzc;
    Object localObject2 = null;
    if (localObject1 == null) {
      localObject1 = null;
    } else {
      localObject1 = zzc.asBinder();
    }
    zzbgo.zza(paramParcel, 3, (IBinder)localObject1, false);
    if (zzd == null) {
      localObject1 = localObject2;
    } else {
      localObject1 = zzd.asBinder();
    }
    zzbgo.zza(paramParcel, 4, (IBinder)localObject1, false);
    zzbgo.zza(paramParcel, i);
  }
}
