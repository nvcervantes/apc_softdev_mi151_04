package com.google.android.gms.maps.model.internal;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PatternItem;
import java.util.List;

@Hide
public abstract interface zzd
  extends IInterface
{
  public abstract void zza()
    throws RemoteException;
  
  public abstract void zza(double paramDouble)
    throws RemoteException;
  
  public abstract void zza(float paramFloat)
    throws RemoteException;
  
  public abstract void zza(int paramInt)
    throws RemoteException;
  
  public abstract void zza(IObjectWrapper paramIObjectWrapper)
    throws RemoteException;
  
  public abstract void zza(LatLng paramLatLng)
    throws RemoteException;
  
  public abstract void zza(List<PatternItem> paramList)
    throws RemoteException;
  
  public abstract void zza(boolean paramBoolean)
    throws RemoteException;
  
  public abstract boolean zza(zzd paramZzd)
    throws RemoteException;
  
  public abstract String zzb()
    throws RemoteException;
  
  public abstract void zzb(float paramFloat)
    throws RemoteException;
  
  public abstract void zzb(int paramInt)
    throws RemoteException;
  
  public abstract void zzb(boolean paramBoolean)
    throws RemoteException;
  
  public abstract LatLng zzc()
    throws RemoteException;
  
  public abstract double zzd()
    throws RemoteException;
  
  public abstract float zze()
    throws RemoteException;
  
  public abstract int zzf()
    throws RemoteException;
  
  public abstract int zzg()
    throws RemoteException;
  
  public abstract float zzh()
    throws RemoteException;
  
  public abstract boolean zzi()
    throws RemoteException;
  
  public abstract int zzj()
    throws RemoteException;
  
  public abstract boolean zzk()
    throws RemoteException;
  
  public abstract List<PatternItem> zzl()
    throws RemoteException;
  
  public abstract IObjectWrapper zzm()
    throws RemoteException;
}
