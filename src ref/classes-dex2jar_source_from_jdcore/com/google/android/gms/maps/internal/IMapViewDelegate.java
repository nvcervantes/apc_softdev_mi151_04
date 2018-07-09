package com.google.android.gms.maps.internal;

import android.os.Bundle;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.dynamic.IObjectWrapper;

@Hide
public abstract interface IMapViewDelegate
  extends IInterface
{
  public abstract IGoogleMapDelegate getMap()
    throws RemoteException;
  
  public abstract void getMapAsync(zzap paramZzap)
    throws RemoteException;
  
  public abstract IObjectWrapper getView()
    throws RemoteException;
  
  public abstract void onCreate(Bundle paramBundle)
    throws RemoteException;
  
  public abstract void onDestroy()
    throws RemoteException;
  
  public abstract void onEnterAmbient(Bundle paramBundle)
    throws RemoteException;
  
  public abstract void onExitAmbient()
    throws RemoteException;
  
  public abstract void onLowMemory()
    throws RemoteException;
  
  public abstract void onPause()
    throws RemoteException;
  
  public abstract void onResume()
    throws RemoteException;
  
  public abstract void onSaveInstanceState(Bundle paramBundle)
    throws RemoteException;
  
  public abstract void onStart()
    throws RemoteException;
  
  public abstract void onStop()
    throws RemoteException;
}
