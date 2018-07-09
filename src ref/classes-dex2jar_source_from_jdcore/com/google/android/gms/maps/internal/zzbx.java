package com.google.android.gms.maps.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzev;
import com.google.android.gms.internal.zzex;

public final class zzbx
  extends zzev
  implements IUiSettingsDelegate
{
  zzbx(IBinder paramIBinder)
  {
    super(paramIBinder, "com.google.android.gms.maps.internal.IUiSettingsDelegate");
  }
  
  public final boolean isCompassEnabled()
    throws RemoteException
  {
    Parcel localParcel = zza(10, a_());
    boolean bool = zzex.zza(localParcel);
    localParcel.recycle();
    return bool;
  }
  
  public final boolean isIndoorLevelPickerEnabled()
    throws RemoteException
  {
    Parcel localParcel = zza(17, a_());
    boolean bool = zzex.zza(localParcel);
    localParcel.recycle();
    return bool;
  }
  
  public final boolean isMapToolbarEnabled()
    throws RemoteException
  {
    Parcel localParcel = zza(19, a_());
    boolean bool = zzex.zza(localParcel);
    localParcel.recycle();
    return bool;
  }
  
  public final boolean isMyLocationButtonEnabled()
    throws RemoteException
  {
    Parcel localParcel = zza(11, a_());
    boolean bool = zzex.zza(localParcel);
    localParcel.recycle();
    return bool;
  }
  
  public final boolean isRotateGesturesEnabled()
    throws RemoteException
  {
    Parcel localParcel = zza(15, a_());
    boolean bool = zzex.zza(localParcel);
    localParcel.recycle();
    return bool;
  }
  
  public final boolean isScrollGesturesEnabled()
    throws RemoteException
  {
    Parcel localParcel = zza(12, a_());
    boolean bool = zzex.zza(localParcel);
    localParcel.recycle();
    return bool;
  }
  
  public final boolean isTiltGesturesEnabled()
    throws RemoteException
  {
    Parcel localParcel = zza(14, a_());
    boolean bool = zzex.zza(localParcel);
    localParcel.recycle();
    return bool;
  }
  
  public final boolean isZoomControlsEnabled()
    throws RemoteException
  {
    Parcel localParcel = zza(9, a_());
    boolean bool = zzex.zza(localParcel);
    localParcel.recycle();
    return bool;
  }
  
  public final boolean isZoomGesturesEnabled()
    throws RemoteException
  {
    Parcel localParcel = zza(13, a_());
    boolean bool = zzex.zza(localParcel);
    localParcel.recycle();
    return bool;
  }
  
  public final void setAllGesturesEnabled(boolean paramBoolean)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramBoolean);
    zzb(8, localParcel);
  }
  
  public final void setCompassEnabled(boolean paramBoolean)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramBoolean);
    zzb(2, localParcel);
  }
  
  public final void setIndoorLevelPickerEnabled(boolean paramBoolean)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramBoolean);
    zzb(16, localParcel);
  }
  
  public final void setMapToolbarEnabled(boolean paramBoolean)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramBoolean);
    zzb(18, localParcel);
  }
  
  public final void setMyLocationButtonEnabled(boolean paramBoolean)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramBoolean);
    zzb(3, localParcel);
  }
  
  public final void setRotateGesturesEnabled(boolean paramBoolean)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramBoolean);
    zzb(7, localParcel);
  }
  
  public final void setScrollGesturesEnabled(boolean paramBoolean)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramBoolean);
    zzb(4, localParcel);
  }
  
  public final void setTiltGesturesEnabled(boolean paramBoolean)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramBoolean);
    zzb(6, localParcel);
  }
  
  public final void setZoomControlsEnabled(boolean paramBoolean)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramBoolean);
    zzb(1, localParcel);
  }
  
  public final void setZoomGesturesEnabled(boolean paramBoolean)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramBoolean);
    zzb(5, localParcel);
  }
}
