package com.google.android.gms.location.places.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzev;
import com.google.android.gms.internal.zzex;
import com.google.android.gms.location.places.PlaceFilter;
import com.google.android.gms.location.places.PlaceReport;

public final class zzs
  extends zzev
  implements zzr
{
  zzs(IBinder paramIBinder)
  {
    super(paramIBinder, "com.google.android.gms.location.places.internal.IGooglePlaceDetectionService");
  }
  
  public final void zza(PlaceFilter paramPlaceFilter, zzau paramZzau, zzx paramZzx)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramPlaceFilter);
    zzex.zza(localParcel, paramZzau);
    zzex.zza(localParcel, paramZzx);
    zzb(6, localParcel);
  }
  
  public final void zza(PlaceReport paramPlaceReport, zzau paramZzau, zzx paramZzx)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramPlaceReport);
    zzex.zza(localParcel, paramZzau);
    zzex.zza(localParcel, paramZzx);
    zzb(7, localParcel);
  }
}
