package com.google.android.gms.maps.model;

import android.os.RemoteException;
import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.maps.model.internal.zzm;

public final class IndoorLevel
{
  private final zzm zza;
  
  @Hide
  public IndoorLevel(zzm paramZzm)
  {
    zza = ((zzm)zzbq.zza(paramZzm));
  }
  
  public final void activate()
  {
    try
    {
      zza.zzc();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public final boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof IndoorLevel)) {
      return false;
    }
    try
    {
      boolean bool = zza.zza(zza);
      return bool;
    }
    catch (RemoteException paramObject)
    {
      throw new RuntimeRemoteException(paramObject);
    }
  }
  
  @NonNull
  public final String getName()
  {
    try
    {
      String str = zza.zza();
      return str;
    }
    catch (RemoteException localRemoteException)
    {
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  @NonNull
  public final String getShortName()
  {
    try
    {
      String str = zza.zzb();
      return str;
    }
    catch (RemoteException localRemoteException)
    {
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public final int hashCode()
  {
    try
    {
      int i = zza.zzd();
      return i;
    }
    catch (RemoteException localRemoteException)
    {
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
}
