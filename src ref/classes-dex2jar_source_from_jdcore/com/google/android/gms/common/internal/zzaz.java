package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

final class zzaz
  implements zzay
{
  private final IBinder zza;
  
  zzaz(IBinder paramIBinder)
  {
    zza = paramIBinder;
  }
  
  public final IBinder asBinder()
  {
    return zza;
  }
  
  public final void zza(zzaw paramZzaw, zzz paramZzz)
    throws RemoteException
  {
    Parcel localParcel1 = Parcel.obtain();
    Parcel localParcel2 = Parcel.obtain();
    try
    {
      localParcel1.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
      localParcel1.writeStrongBinder(paramZzaw.asBinder());
      localParcel1.writeInt(1);
      paramZzz.writeToParcel(localParcel1, 0);
      zza.transact(46, localParcel1, localParcel2, 0);
      localParcel2.readException();
      return;
    }
    finally
    {
      localParcel2.recycle();
      localParcel1.recycle();
    }
  }
}
