package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class zzcgv
  extends zzew
  implements zzcgu
{
  public zzcgv()
  {
    attachInterface(this, "com.google.android.gms.location.internal.IGeofencerCallbacks");
  }
  
  public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
    throws RemoteException
  {
    if (zza(paramInt1, paramParcel1, paramParcel2, paramInt2)) {
      return true;
    }
    switch (paramInt1)
    {
    default: 
      return false;
    case 3: 
      zza(paramParcel1.readInt(), (PendingIntent)zzex.zza(paramParcel1, PendingIntent.CREATOR));
      return true;
    case 2: 
      zzb(paramParcel1.readInt(), paramParcel1.createStringArray());
      return true;
    }
    zza(paramParcel1.readInt(), paramParcel1.createStringArray());
    return true;
  }
}
