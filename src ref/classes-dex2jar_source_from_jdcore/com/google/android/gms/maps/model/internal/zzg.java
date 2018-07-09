package com.google.android.gms.maps.model.internal;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

@Hide
public abstract interface zzg
  extends IInterface
{
  public abstract void zza()
    throws RemoteException;
  
  public abstract void zza(float paramFloat)
    throws RemoteException;
  
  public abstract void zza(float paramFloat1, float paramFloat2)
    throws RemoteException;
  
  public abstract void zza(IObjectWrapper paramIObjectWrapper)
    throws RemoteException;
  
  public abstract void zza(LatLng paramLatLng)
    throws RemoteException;
  
  public abstract void zza(LatLngBounds paramLatLngBounds)
    throws RemoteException;
  
  public abstract void zza(boolean paramBoolean)
    throws RemoteException;
  
  public abstract boolean zza(zzg paramZzg)
    throws RemoteException;
  
  public abstract String zzb()
    throws RemoteException;
  
  public abstract void zzb(float paramFloat)
    throws RemoteException;
  
  public abstract void zzb(IObjectWrapper paramIObjectWrapper)
    throws RemoteException;
  
  public abstract void zzb(boolean paramBoolean)
    throws RemoteException;
  
  public abstract LatLng zzc()
    throws RemoteException;
  
  public abstract void zzc(float paramFloat)
    throws RemoteException;
  
  public abstract float zzd()
    throws RemoteException;
  
  public abstract void zzd(float paramFloat)
    throws RemoteException;
  
  public abstract float zze()
    throws RemoteException;
  
  public abstract LatLngBounds zzf()
    throws RemoteException;
  
  public abstract float zzg()
    throws RemoteException;
  
  public abstract float zzh()
    throws RemoteException;
  
  public abstract boolean zzi()
    throws RemoteException;
  
  public abstract float zzj()
    throws RemoteException;
  
  public abstract int zzk()
    throws RemoteException;
  
  public abstract boolean zzl()
    throws RemoteException;
  
  public abstract IObjectWrapper zzm()
    throws RemoteException;
}
