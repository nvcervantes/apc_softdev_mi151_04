package com.google.android.gms.location.places.internal;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.location.places.AddPlaceRequest;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.maps.model.LatLngBounds;
import java.util.List;

@Hide
public abstract interface zzt
  extends IInterface
{
  public abstract void zza(AddPlaceRequest paramAddPlaceRequest, zzau paramZzau, zzx paramZzx)
    throws RemoteException;
  
  public abstract void zza(String paramString, int paramInt1, int paramInt2, int paramInt3, zzau paramZzau, zzv paramZzv)
    throws RemoteException;
  
  public abstract void zza(String paramString, zzau paramZzau, zzv paramZzv)
    throws RemoteException;
  
  public abstract void zza(String paramString, LatLngBounds paramLatLngBounds, int paramInt, AutocompleteFilter paramAutocompleteFilter, zzau paramZzau, zzx paramZzx)
    throws RemoteException;
  
  public abstract void zza(List<String> paramList, zzau paramZzau, zzx paramZzx)
    throws RemoteException;
}
