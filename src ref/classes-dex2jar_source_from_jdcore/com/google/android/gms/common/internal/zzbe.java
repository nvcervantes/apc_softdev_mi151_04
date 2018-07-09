package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.IObjectWrapper.zza;
import com.google.android.gms.internal.zzev;
import com.google.android.gms.internal.zzex;

public final class zzbe
  extends zzev
  implements zzbd
{
  zzbe(IBinder paramIBinder)
  {
    super(paramIBinder, "com.google.android.gms.common.internal.ISignInButtonCreator");
  }
  
  public final IObjectWrapper zza(IObjectWrapper paramIObjectWrapper, zzbv paramZzbv)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramIObjectWrapper);
    zzex.zza(localParcel, paramZzbv);
    paramIObjectWrapper = zza(2, localParcel);
    paramZzbv = IObjectWrapper.zza.zza(paramIObjectWrapper.readStrongBinder());
    paramIObjectWrapper.recycle();
    return paramZzbv;
  }
}
