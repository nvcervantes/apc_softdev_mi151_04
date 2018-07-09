package com.google.android.gms.iid;

import android.os.IBinder;
import android.os.Message;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzev;
import com.google.android.gms.internal.zzex;

public final class zzk
  extends zzev
  implements zzi
{
  zzk(IBinder paramIBinder)
  {
    super(paramIBinder, "com.google.android.gms.iid.IMessengerCompat");
  }
  
  public final void zza(Message paramMessage)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramMessage);
    zzc(1, localParcel);
  }
}
