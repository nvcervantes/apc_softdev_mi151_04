package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.content.Context;
import android.location.Location;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzci;
import com.google.android.gms.common.api.internal.zzck;
import com.google.android.gms.common.api.internal.zzcz;
import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.internal.zzd;
import com.google.android.gms.common.internal.zzr;
import com.google.android.gms.location.ActivityTransitionRequest;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.zzal;

@Hide
public final class zzchh
  extends zzcfq
{
  private final zzcha zze = new zzcha(paramContext, zzd);
  
  public zzchh(Context paramContext, Looper paramLooper, GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener, String paramString)
  {
    this(paramContext, paramLooper, paramConnectionCallbacks, paramOnConnectionFailedListener, paramString, zzr.zza(paramContext));
  }
  
  public zzchh(Context paramContext, Looper paramLooper, GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener, String paramString, zzr paramZzr)
  {
    super(paramContext, paramLooper, paramConnectionCallbacks, paramOnConnectionFailedListener, paramString, paramZzr);
  }
  
  public final void zza(long paramLong, PendingIntent paramPendingIntent)
    throws RemoteException
  {
    zzae();
    zzbq.zza(paramPendingIntent);
    boolean bool;
    if (paramLong >= 0L) {
      bool = true;
    } else {
      bool = false;
    }
    zzbq.zzb(bool, "detectionIntervalMillis must be >= 0");
    ((zzcgw)zzaf()).zza(paramLong, true, paramPendingIntent);
  }
  
  public final void zza(PendingIntent paramPendingIntent)
    throws RemoteException
  {
    zzae();
    zzbq.zza(paramPendingIntent);
    ((zzcgw)zzaf()).zza(paramPendingIntent);
  }
  
  public final void zza(PendingIntent paramPendingIntent, zzn<Status> paramZzn)
    throws RemoteException
  {
    zzae();
    zzbq.zza(paramZzn, "ResultHolder not provided.");
    paramZzn = new zzcz(paramZzn);
    ((zzcgw)zzaf()).zza(paramPendingIntent, paramZzn);
  }
  
  public final void zza(PendingIntent paramPendingIntent, zzcgr paramZzcgr)
    throws RemoteException
  {
    zze.zza(paramPendingIntent, paramZzcgr);
  }
  
  public final void zza(Location paramLocation)
    throws RemoteException
  {
    zze.zza(paramLocation);
  }
  
  public final void zza(zzck<LocationListener> paramZzck, zzcgr paramZzcgr)
    throws RemoteException
  {
    zze.zza(paramZzck, paramZzcgr);
  }
  
  public final void zza(zzcgr paramZzcgr)
    throws RemoteException
  {
    zze.zza(paramZzcgr);
  }
  
  public final void zza(zzchl paramZzchl, zzci<LocationCallback> paramZzci, zzcgr paramZzcgr)
    throws RemoteException
  {
    synchronized (zze)
    {
      zze.zza(paramZzchl, paramZzci, paramZzcgr);
      return;
    }
  }
  
  public final void zza(ActivityTransitionRequest paramActivityTransitionRequest, PendingIntent paramPendingIntent, zzn<Status> paramZzn)
    throws RemoteException
  {
    zzae();
    zzbq.zza(paramZzn, "ResultHolder not provided.");
    paramZzn = new zzcz(paramZzn);
    ((zzcgw)zzaf()).zza(paramActivityTransitionRequest, paramPendingIntent, paramZzn);
  }
  
  public final void zza(GeofencingRequest paramGeofencingRequest, PendingIntent paramPendingIntent, zzn<Status> paramZzn)
    throws RemoteException
  {
    zzae();
    zzbq.zza(paramGeofencingRequest, "geofencingRequest can't be null.");
    zzbq.zza(paramPendingIntent, "PendingIntent must be specified.");
    zzbq.zza(paramZzn, "ResultHolder not provided.");
    paramZzn = new zzchi(paramZzn);
    ((zzcgw)zzaf()).zza(paramGeofencingRequest, paramPendingIntent, paramZzn);
  }
  
  public final void zza(LocationRequest paramLocationRequest, PendingIntent paramPendingIntent, zzcgr paramZzcgr)
    throws RemoteException
  {
    zze.zza(paramLocationRequest, paramPendingIntent, paramZzcgr);
  }
  
  public final void zza(LocationRequest paramLocationRequest, zzci<LocationListener> paramZzci, zzcgr paramZzcgr)
    throws RemoteException
  {
    synchronized (zze)
    {
      zze.zza(paramLocationRequest, paramZzci, paramZzcgr);
      return;
    }
  }
  
  public final void zza(LocationSettingsRequest paramLocationSettingsRequest, zzn<LocationSettingsResult> paramZzn, String paramString)
    throws RemoteException
  {
    zzae();
    boolean bool2 = false;
    if (paramLocationSettingsRequest != null) {
      bool1 = true;
    } else {
      bool1 = false;
    }
    zzbq.zzb(bool1, "locationSettingsRequest can't be null nor empty.");
    boolean bool1 = bool2;
    if (paramZzn != null) {
      bool1 = true;
    }
    zzbq.zzb(bool1, "listener can't be null.");
    paramZzn = new zzchk(paramZzn);
    ((zzcgw)zzaf()).zza(paramLocationSettingsRequest, paramZzn, paramString);
  }
  
  public final void zza(zzal paramZzal, zzn<Status> paramZzn)
    throws RemoteException
  {
    zzae();
    zzbq.zza(paramZzal, "removeGeofencingRequest can't be null.");
    zzbq.zza(paramZzn, "ResultHolder not provided.");
    paramZzn = new zzchj(paramZzn);
    ((zzcgw)zzaf()).zza(paramZzal, paramZzn);
  }
  
  public final void zza(boolean paramBoolean)
    throws RemoteException
  {
    zze.zza(paramBoolean);
  }
  
  public final void zzb(zzck<LocationCallback> paramZzck, zzcgr paramZzcgr)
    throws RemoteException
  {
    zze.zzb(paramZzck, paramZzcgr);
  }
  
  public final void zzg()
  {
    synchronized (zze)
    {
      boolean bool = zzs();
      if (bool) {
        try
        {
          zze.zzc();
          zze.zzd();
        }
        catch (Exception localException)
        {
          Log.e("LocationClientImpl", "Client disconnected before listeners could be cleaned up", localException);
        }
      }
      super.zzg();
      return;
    }
  }
  
  public final Location zzi()
    throws RemoteException
  {
    return zze.zza();
  }
  
  public final LocationAvailability zzj()
    throws RemoteException
  {
    return zze.zzb();
  }
}
