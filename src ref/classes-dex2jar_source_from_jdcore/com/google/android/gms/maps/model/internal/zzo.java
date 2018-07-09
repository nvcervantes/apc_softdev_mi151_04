package com.google.android.gms.maps.model.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzev;
import com.google.android.gms.internal.zzex;

public final class zzo
  extends zzev
  implements zzm
{
  zzo(IBinder paramIBinder)
  {
    super(paramIBinder, "com.google.android.gms.maps.model.internal.IIndoorLevelDelegate");
  }
  
  public final String zza()
    throws RemoteException
  {
    Parcel localParcel = zza(1, a_());
    String str = localParcel.readString();
    localParcel.recycle();
    return str;
  }
  
  public final boolean zza(zzm paramZzm)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramZzm);
    paramZzm = zza(4, localParcel);
    boolean bool = zzex.zza(paramZzm);
    paramZzm.recycle();
    return bool;
  }
  
  public final String zzb()
    throws RemoteException
  {
    Parcel localParcel = zza(2, a_());
    String str = localParcel.readString();
    localParcel.recycle();
    return str;
  }
  
  public final void zzc()
    throws RemoteException
  {
    zzb(3, a_());
  }
  
  public final int zzd()
    throws RemoteException
  {
    Parcel localParcel = zza(5, a_());
    int i = localParcel.readInt();
    localParcel.recycle();
    return i;
  }
}
