package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.clearcut.zze;

public final class zzbfs
  extends zzev
  implements zzbfr
{
  zzbfs(IBinder paramIBinder)
  {
    super(paramIBinder, "com.google.android.gms.clearcut.internal.IClearcutLoggerService");
  }
  
  public final void zza(zzbfp paramZzbfp, zze paramZze)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramZzbfp);
    zzex.zza(localParcel, paramZze);
    zzc(1, localParcel);
  }
}
