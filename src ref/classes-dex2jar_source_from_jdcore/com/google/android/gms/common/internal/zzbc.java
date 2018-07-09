package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.zzn;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzev;
import com.google.android.gms.internal.zzex;

public final class zzbc
  extends zzev
  implements zzba
{
  zzbc(IBinder paramIBinder)
  {
    super(paramIBinder, "com.google.android.gms.common.internal.IGoogleCertificatesApi");
  }
  
  public final boolean zza(zzn paramZzn, IObjectWrapper paramIObjectWrapper)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramZzn);
    zzex.zza(localParcel, paramIObjectWrapper);
    paramZzn = zza(5, localParcel);
    boolean bool = zzex.zza(paramZzn);
    paramZzn.recycle();
    return bool;
  }
}
