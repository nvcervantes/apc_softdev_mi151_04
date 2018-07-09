package com.google.android.gms.maps.model.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.IObjectWrapper.zza;
import com.google.android.gms.internal.zzev;
import com.google.android.gms.internal.zzex;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

public final class zzi
  extends zzev
  implements zzg
{
  zzi(IBinder paramIBinder)
  {
    super(paramIBinder, "com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
  }
  
  public final void zza()
    throws RemoteException
  {
    zzb(1, a_());
  }
  
  public final void zza(float paramFloat)
    throws RemoteException
  {
    Parcel localParcel = a_();
    localParcel.writeFloat(paramFloat);
    zzb(5, localParcel);
  }
  
  public final void zza(float paramFloat1, float paramFloat2)
    throws RemoteException
  {
    Parcel localParcel = a_();
    localParcel.writeFloat(paramFloat1);
    localParcel.writeFloat(paramFloat2);
    zzb(6, localParcel);
  }
  
  public final void zza(IObjectWrapper paramIObjectWrapper)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramIObjectWrapper);
    zzb(21, localParcel);
  }
  
  public final void zza(LatLng paramLatLng)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramLatLng);
    zzb(3, localParcel);
  }
  
  public final void zza(LatLngBounds paramLatLngBounds)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramLatLngBounds);
    zzb(9, localParcel);
  }
  
  public final void zza(boolean paramBoolean)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramBoolean);
    zzb(15, localParcel);
  }
  
  public final boolean zza(zzg paramZzg)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramZzg);
    paramZzg = zza(19, localParcel);
    boolean bool = zzex.zza(paramZzg);
    paramZzg.recycle();
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
  
  public final void zzb(float paramFloat)
    throws RemoteException
  {
    Parcel localParcel = a_();
    localParcel.writeFloat(paramFloat);
    zzb(11, localParcel);
  }
  
  public final void zzb(IObjectWrapper paramIObjectWrapper)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramIObjectWrapper);
    zzb(24, localParcel);
  }
  
  public final void zzb(boolean paramBoolean)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramBoolean);
    zzb(22, localParcel);
  }
  
  public final LatLng zzc()
    throws RemoteException
  {
    Parcel localParcel = zza(4, a_());
    LatLng localLatLng = (LatLng)zzex.zza(localParcel, LatLng.CREATOR);
    localParcel.recycle();
    return localLatLng;
  }
  
  public final void zzc(float paramFloat)
    throws RemoteException
  {
    Parcel localParcel = a_();
    localParcel.writeFloat(paramFloat);
    zzb(13, localParcel);
  }
  
  public final float zzd()
    throws RemoteException
  {
    Parcel localParcel = zza(7, a_());
    float f = localParcel.readFloat();
    localParcel.recycle();
    return f;
  }
  
  public final void zzd(float paramFloat)
    throws RemoteException
  {
    Parcel localParcel = a_();
    localParcel.writeFloat(paramFloat);
    zzb(17, localParcel);
  }
  
  public final float zze()
    throws RemoteException
  {
    Parcel localParcel = zza(8, a_());
    float f = localParcel.readFloat();
    localParcel.recycle();
    return f;
  }
  
  public final LatLngBounds zzf()
    throws RemoteException
  {
    Parcel localParcel = zza(10, a_());
    LatLngBounds localLatLngBounds = (LatLngBounds)zzex.zza(localParcel, LatLngBounds.CREATOR);
    localParcel.recycle();
    return localLatLngBounds;
  }
  
  public final float zzg()
    throws RemoteException
  {
    Parcel localParcel = zza(12, a_());
    float f = localParcel.readFloat();
    localParcel.recycle();
    return f;
  }
  
  public final float zzh()
    throws RemoteException
  {
    Parcel localParcel = zza(14, a_());
    float f = localParcel.readFloat();
    localParcel.recycle();
    return f;
  }
  
  public final boolean zzi()
    throws RemoteException
  {
    Parcel localParcel = zza(16, a_());
    boolean bool = zzex.zza(localParcel);
    localParcel.recycle();
    return bool;
  }
  
  public final float zzj()
    throws RemoteException
  {
    Parcel localParcel = zza(18, a_());
    float f = localParcel.readFloat();
    localParcel.recycle();
    return f;
  }
  
  public final int zzk()
    throws RemoteException
  {
    Parcel localParcel = zza(20, a_());
    int i = localParcel.readInt();
    localParcel.recycle();
    return i;
  }
  
  public final boolean zzl()
    throws RemoteException
  {
    Parcel localParcel = zza(23, a_());
    boolean bool = zzex.zza(localParcel);
    localParcel.recycle();
    return bool;
  }
  
  public final IObjectWrapper zzm()
    throws RemoteException
  {
    Parcel localParcel = zza(25, a_());
    IObjectWrapper localIObjectWrapper = IObjectWrapper.zza.zza(localParcel.readStrongBinder());
    localParcel.recycle();
    return localIObjectWrapper;
  }
}
