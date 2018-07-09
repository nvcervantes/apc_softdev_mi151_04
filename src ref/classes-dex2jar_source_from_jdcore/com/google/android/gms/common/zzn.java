package com.google.android.gms.common;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.internal.zzat;
import com.google.android.gms.common.internal.zzau;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import com.google.android.gms.internal.zzew;

@Hide
public final class zzn
  extends zzbgl
{
  public static final Parcelable.Creator<zzn> CREATOR = new zzo();
  private final String zza;
  private final zzh zzb;
  private final boolean zzc;
  
  zzn(String paramString, IBinder paramIBinder, boolean paramBoolean)
  {
    zza = paramString;
    zzb = zza(paramIBinder);
    zzc = paramBoolean;
  }
  
  zzn(String paramString, zzh paramZzh, boolean paramBoolean)
  {
    zza = paramString;
    zzb = paramZzh;
    zzc = paramBoolean;
  }
  
  private static zzh zza(IBinder paramIBinder)
  {
    if (paramIBinder == null) {
      return null;
    }
    try
    {
      paramIBinder = zzau.zza(paramIBinder).zzb();
      if (paramIBinder == null) {
        paramIBinder = null;
      } else {
        paramIBinder = (byte[])com.google.android.gms.dynamic.zzn.zza(paramIBinder);
      }
      if (paramIBinder != null) {
        return new zzi(paramIBinder);
      }
      Log.e("GoogleCertificatesQuery", "Could not unwrap certificate");
      return null;
    }
    catch (RemoteException paramIBinder)
    {
      Log.e("GoogleCertificatesQuery", "Could not unwrap certificate", paramIBinder);
    }
    return null;
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramInt = zzbgo.zza(paramParcel);
    zzbgo.zza(paramParcel, 1, zza, false);
    IBinder localIBinder;
    if (zzb == null)
    {
      Log.w("GoogleCertificatesQuery", "certificate binder is null");
      localIBinder = null;
    }
    else
    {
      localIBinder = zzb.asBinder();
    }
    zzbgo.zza(paramParcel, 2, localIBinder, false);
    zzbgo.zza(paramParcel, 3, zzc);
    zzbgo.zza(paramParcel, paramInt);
  }
}
