package com.google.android.gms.location.places.internal;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.location.places.PlaceFilter;
import com.google.android.gms.location.places.PlaceReport;

public abstract interface zzr
  extends IInterface
{
  public abstract void zza(PlaceFilter paramPlaceFilter, zzau paramZzau, zzx paramZzx)
    throws RemoteException;
  
  public abstract void zza(PlaceReport paramPlaceReport, zzau paramZzau, zzx paramZzx)
    throws RemoteException;
}
