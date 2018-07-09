package com.google.android.gms.maps.model.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzev;
import com.google.android.gms.internal.zzex;
import java.util.ArrayList;
import java.util.List;

public final class zzl
  extends zzev
  implements zzj
{
  zzl(IBinder paramIBinder)
  {
    super(paramIBinder, "com.google.android.gms.maps.model.internal.IIndoorBuildingDelegate");
  }
  
  public final int zza()
    throws RemoteException
  {
    Parcel localParcel = zza(1, a_());
    int i = localParcel.readInt();
    localParcel.recycle();
    return i;
  }
  
  public final boolean zza(zzj paramZzj)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramZzj);
    paramZzj = zza(5, localParcel);
    boolean bool = zzex.zza(paramZzj);
    paramZzj.recycle();
    return bool;
  }
  
  public final int zzb()
    throws RemoteException
  {
    Parcel localParcel = zza(2, a_());
    int i = localParcel.readInt();
    localParcel.recycle();
    return i;
  }
  
  public final List<IBinder> zzc()
    throws RemoteException
  {
    Parcel localParcel = zza(3, a_());
    ArrayList localArrayList = localParcel.createBinderArrayList();
    localParcel.recycle();
    return localArrayList;
  }
  
  public final boolean zzd()
    throws RemoteException
  {
    Parcel localParcel = zza(4, a_());
    boolean bool = zzex.zza(localParcel);
    localParcel.recycle();
    return bool;
  }
  
  public final int zze()
    throws RemoteException
  {
    Parcel localParcel = zza(6, a_());
    int i = localParcel.readInt();
    localParcel.recycle();
    return i;
  }
}
