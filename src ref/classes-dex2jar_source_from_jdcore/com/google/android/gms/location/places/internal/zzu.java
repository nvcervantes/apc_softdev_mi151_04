package com.google.android.gms.location.places.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzev;
import com.google.android.gms.internal.zzex;
import com.google.android.gms.location.places.AddPlaceRequest;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.maps.model.LatLngBounds;
import java.util.List;

public final class zzu
  extends zzev
  implements zzt
{
  zzu(IBinder paramIBinder)
  {
    super(paramIBinder, "com.google.android.gms.location.places.internal.IGooglePlacesService");
  }
  
  public final void zza(AddPlaceRequest paramAddPlaceRequest, zzau paramZzau, zzx paramZzx)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramAddPlaceRequest);
    zzex.zza(localParcel, paramZzau);
    zzex.zza(localParcel, paramZzx);
    zzb(14, localParcel);
  }
  
  public final void zza(String paramString, int paramInt1, int paramInt2, int paramInt3, zzau paramZzau, zzv paramZzv)
    throws RemoteException
  {
    Parcel localParcel = a_();
    localParcel.writeString(paramString);
    localParcel.writeInt(paramInt1);
    localParcel.writeInt(paramInt2);
    localParcel.writeInt(paramInt3);
    zzex.zza(localParcel, paramZzau);
    zzex.zza(localParcel, paramZzv);
    zzb(20, localParcel);
  }
  
  public final void zza(String paramString, zzau paramZzau, zzv paramZzv)
    throws RemoteException
  {
    Parcel localParcel = a_();
    localParcel.writeString(paramString);
    zzex.zza(localParcel, paramZzau);
    zzex.zza(localParcel, paramZzv);
    zzb(19, localParcel);
  }
  
  public final void zza(String paramString, LatLngBounds paramLatLngBounds, int paramInt, AutocompleteFilter paramAutocompleteFilter, zzau paramZzau, zzx paramZzx)
    throws RemoteException
  {
    Parcel localParcel = a_();
    localParcel.writeString(paramString);
    zzex.zza(localParcel, paramLatLngBounds);
    localParcel.writeInt(paramInt);
    zzex.zza(localParcel, paramAutocompleteFilter);
    zzex.zza(localParcel, paramZzau);
    zzex.zza(localParcel, paramZzx);
    zzb(28, localParcel);
  }
  
  public final void zza(List<String> paramList, zzau paramZzau, zzx paramZzx)
    throws RemoteException
  {
    Parcel localParcel = a_();
    localParcel.writeStringList(paramList);
    zzex.zza(localParcel, paramZzau);
    zzex.zza(localParcel, paramZzx);
    zzb(17, localParcel);
  }
}
