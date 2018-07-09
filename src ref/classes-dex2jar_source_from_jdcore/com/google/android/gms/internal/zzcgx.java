package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.location.Location;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.api.internal.zzca;
import com.google.android.gms.location.ActivityTransitionRequest;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.zzal;

public final class zzcgx
  extends zzev
  implements zzcgw
{
  zzcgx(IBinder paramIBinder)
  {
    super(paramIBinder, "com.google.android.gms.location.internal.IGoogleLocationManagerService");
  }
  
  public final Location zza(String paramString)
    throws RemoteException
  {
    Object localObject = a_();
    ((Parcel)localObject).writeString(paramString);
    paramString = zza(21, (Parcel)localObject);
    localObject = (Location)zzex.zza(paramString, Location.CREATOR);
    paramString.recycle();
    return localObject;
  }
  
  public final void zza(long paramLong, boolean paramBoolean, PendingIntent paramPendingIntent)
    throws RemoteException
  {
    Parcel localParcel = a_();
    localParcel.writeLong(paramLong);
    zzex.zza(localParcel, true);
    zzex.zza(localParcel, paramPendingIntent);
    zzb(5, localParcel);
  }
  
  public final void zza(PendingIntent paramPendingIntent)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramPendingIntent);
    zzb(6, localParcel);
  }
  
  public final void zza(PendingIntent paramPendingIntent, zzca paramZzca)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramPendingIntent);
    zzex.zza(localParcel, paramZzca);
    zzb(73, localParcel);
  }
  
  public final void zza(Location paramLocation)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramLocation);
    zzb(13, localParcel);
  }
  
  public final void zza(zzcfw paramZzcfw)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramZzcfw);
    zzb(75, localParcel);
  }
  
  public final void zza(zzcgr paramZzcgr)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramZzcgr);
    zzb(67, localParcel);
  }
  
  public final void zza(zzchn paramZzchn)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramZzchn);
    zzb(59, localParcel);
  }
  
  public final void zza(ActivityTransitionRequest paramActivityTransitionRequest, PendingIntent paramPendingIntent, zzca paramZzca)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramActivityTransitionRequest);
    zzex.zza(localParcel, paramPendingIntent);
    zzex.zza(localParcel, paramZzca);
    zzb(72, localParcel);
  }
  
  public final void zza(GeofencingRequest paramGeofencingRequest, PendingIntent paramPendingIntent, zzcgu paramZzcgu)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramGeofencingRequest);
    zzex.zza(localParcel, paramPendingIntent);
    zzex.zza(localParcel, paramZzcgu);
    zzb(57, localParcel);
  }
  
  public final void zza(LocationSettingsRequest paramLocationSettingsRequest, zzcgy paramZzcgy, String paramString)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramLocationSettingsRequest);
    zzex.zza(localParcel, paramZzcgy);
    localParcel.writeString(paramString);
    zzb(63, localParcel);
  }
  
  public final void zza(zzal paramZzal, zzcgu paramZzcgu)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramZzal);
    zzex.zza(localParcel, paramZzcgu);
    zzb(74, localParcel);
  }
  
  public final void zza(boolean paramBoolean)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramBoolean);
    zzb(12, localParcel);
  }
  
  public final LocationAvailability zzb(String paramString)
    throws RemoteException
  {
    Object localObject = a_();
    ((Parcel)localObject).writeString(paramString);
    paramString = zza(34, (Parcel)localObject);
    localObject = (LocationAvailability)zzex.zza(paramString, LocationAvailability.CREATOR);
    paramString.recycle();
    return localObject;
  }
}
