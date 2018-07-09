package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.location.zzu;
import com.google.android.gms.location.zzv;
import com.google.android.gms.location.zzx;
import com.google.android.gms.location.zzy;

@Hide
public final class zzchn
  extends zzbgl
{
  public static final Parcelable.Creator<zzchn> CREATOR = new zzcho();
  private int zza;
  private zzchl zzb;
  private zzx zzc;
  private PendingIntent zzd;
  private zzu zze;
  private zzcgr zzf;
  
  zzchn(int paramInt, zzchl paramZzchl, IBinder paramIBinder1, PendingIntent paramPendingIntent, IBinder paramIBinder2, IBinder paramIBinder3)
  {
    zza = paramInt;
    zzb = paramZzchl;
    Object localObject = null;
    if (paramIBinder1 == null) {
      paramZzchl = null;
    } else {
      paramZzchl = zzy.zza(paramIBinder1);
    }
    zzc = paramZzchl;
    zzd = paramPendingIntent;
    if (paramIBinder2 == null) {
      paramZzchl = null;
    } else {
      paramZzchl = zzv.zza(paramIBinder2);
    }
    zze = paramZzchl;
    if (paramIBinder3 == null)
    {
      paramZzchl = localObject;
    }
    else if (paramIBinder3 == null)
    {
      paramZzchl = localObject;
    }
    else
    {
      paramZzchl = paramIBinder3.queryLocalInterface("com.google.android.gms.location.internal.IFusedLocationProviderCallback");
      if ((paramZzchl instanceof zzcgr)) {
        paramZzchl = (zzcgr)paramZzchl;
      } else {
        paramZzchl = new zzcgt(paramIBinder3);
      }
    }
    zzf = paramZzchl;
  }
  
  public static zzchn zza(zzu paramZzu, @Nullable zzcgr paramZzcgr)
  {
    IBinder localIBinder = paramZzu.asBinder();
    if (paramZzcgr != null) {}
    for (paramZzu = paramZzcgr.asBinder();; paramZzu = null) {
      break;
    }
    return new zzchn(2, null, null, null, localIBinder, paramZzu);
  }
  
  public static zzchn zza(zzx paramZzx, @Nullable zzcgr paramZzcgr)
  {
    IBinder localIBinder = paramZzx.asBinder();
    if (paramZzcgr != null) {}
    for (paramZzx = paramZzcgr.asBinder();; paramZzx = null) {
      break;
    }
    return new zzchn(2, null, localIBinder, null, null, paramZzx);
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
    zzbgo.zza(paramParcel, 4, zzd, paramInt, false);
    if (zze == null) {
      localObject1 = null;
    } else {
      localObject1 = zze.asBinder();
    }
    zzbgo.zza(paramParcel, 5, (IBinder)localObject1, false);
    if (zzf == null) {
      localObject1 = localObject2;
    } else {
      localObject1 = zzf.asBinder();
    }
    zzbgo.zza(paramParcel, 6, (IBinder)localObject1, false);
    zzbgo.zza(paramParcel, i);
  }
}
