package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.content.ContentProviderClient;
import android.content.Context;
import android.location.Location;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.common.api.internal.zzci;
import com.google.android.gms.common.api.internal.zzck;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.zzr;
import com.google.android.gms.location.zzu;
import com.google.android.gms.location.zzx;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Hide
public final class zzcha
{
  private final zzchr<zzcgw> zza;
  private final Context zzb;
  private ContentProviderClient zzc = null;
  private boolean zzd = false;
  private final Map<zzck<LocationListener>, zzchf> zze = new HashMap();
  private final Map<zzck<Object>, zzche> zzf = new HashMap();
  private final Map<zzck<LocationCallback>, zzchb> zzg = new HashMap();
  
  public zzcha(Context paramContext, zzchr<zzcgw> paramZzchr)
  {
    zzb = paramContext;
    zza = paramZzchr;
  }
  
  private final zzchf zza(zzci<LocationListener> paramZzci)
  {
    synchronized (zze)
    {
      zzchf localZzchf2 = (zzchf)zze.get(paramZzci.zzc());
      zzchf localZzchf1 = localZzchf2;
      if (localZzchf2 == null) {
        localZzchf1 = new zzchf(paramZzci);
      }
      zze.put(paramZzci.zzc(), localZzchf1);
      return localZzchf1;
    }
  }
  
  private final zzchb zzb(zzci<LocationCallback> paramZzci)
  {
    synchronized (zzg)
    {
      zzchb localZzchb2 = (zzchb)zzg.get(paramZzci.zzc());
      zzchb localZzchb1 = localZzchb2;
      if (localZzchb2 == null) {
        localZzchb1 = new zzchb(paramZzci);
      }
      zzg.put(paramZzci.zzc(), localZzchb1);
      return localZzchb1;
    }
  }
  
  public final Location zza()
    throws RemoteException
  {
    zza.zza();
    return ((zzcgw)zza.zzb()).zza(zzb.getPackageName());
  }
  
  public final void zza(PendingIntent paramPendingIntent, zzcgr paramZzcgr)
    throws RemoteException
  {
    zza.zza();
    zzcgw localZzcgw = (zzcgw)zza.zzb();
    if (paramZzcgr != null) {}
    for (paramZzcgr = paramZzcgr.asBinder();; paramZzcgr = null) {
      break;
    }
    localZzcgw.zza(new zzchn(2, null, null, paramPendingIntent, null, paramZzcgr));
  }
  
  public final void zza(Location paramLocation)
    throws RemoteException
  {
    zza.zza();
    ((zzcgw)zza.zzb()).zza(paramLocation);
  }
  
  public final void zza(zzck<LocationListener> paramZzck, zzcgr paramZzcgr)
    throws RemoteException
  {
    zza.zza();
    zzbq.zza(paramZzck, "Invalid null listener key");
    synchronized (zze)
    {
      paramZzck = (zzchf)zze.remove(paramZzck);
      if (paramZzck != null)
      {
        paramZzck.zza();
        ((zzcgw)zza.zzb()).zza(zzchn.zza(paramZzck, paramZzcgr));
      }
      return;
    }
  }
  
  public final void zza(zzcgr paramZzcgr)
    throws RemoteException
  {
    zza.zza();
    ((zzcgw)zza.zzb()).zza(paramZzcgr);
  }
  
  public final void zza(zzchl paramZzchl, zzci<LocationCallback> paramZzci, zzcgr paramZzcgr)
    throws RemoteException
  {
    zza.zza();
    paramZzci = zzb(paramZzci);
    zzcgw localZzcgw = (zzcgw)zza.zzb();
    IBinder localIBinder = paramZzci.asBinder();
    if (paramZzcgr != null) {}
    for (paramZzci = paramZzcgr.asBinder();; paramZzci = null) {
      break;
    }
    localZzcgw.zza(new zzchn(1, paramZzchl, null, null, localIBinder, paramZzci));
  }
  
  public final void zza(LocationRequest paramLocationRequest, PendingIntent paramPendingIntent, zzcgr paramZzcgr)
    throws RemoteException
  {
    zza.zza();
    zzcgw localZzcgw = (zzcgw)zza.zzb();
    zzchl localZzchl = zzchl.zza(paramLocationRequest);
    if (paramZzcgr != null) {}
    for (paramLocationRequest = paramZzcgr.asBinder();; paramLocationRequest = null) {
      break;
    }
    localZzcgw.zza(new zzchn(1, localZzchl, null, paramPendingIntent, null, paramLocationRequest));
  }
  
  public final void zza(LocationRequest paramLocationRequest, zzci<LocationListener> paramZzci, zzcgr paramZzcgr)
    throws RemoteException
  {
    zza.zza();
    Object localObject = zza(paramZzci);
    paramZzci = (zzcgw)zza.zzb();
    zzchl localZzchl = zzchl.zza(paramLocationRequest);
    localObject = ((zzx)localObject).asBinder();
    if (paramZzcgr != null) {}
    for (paramLocationRequest = paramZzcgr.asBinder();; paramLocationRequest = null) {
      break;
    }
    paramZzci.zza(new zzchn(1, localZzchl, (IBinder)localObject, null, null, paramLocationRequest));
  }
  
  public final void zza(boolean paramBoolean)
    throws RemoteException
  {
    zza.zza();
    ((zzcgw)zza.zzb()).zza(paramBoolean);
    zzd = paramBoolean;
  }
  
  public final LocationAvailability zzb()
    throws RemoteException
  {
    zza.zza();
    return ((zzcgw)zza.zzb()).zzb(zzb.getPackageName());
  }
  
  public final void zzb(zzck<LocationCallback> paramZzck, zzcgr paramZzcgr)
    throws RemoteException
  {
    zza.zza();
    zzbq.zza(paramZzck, "Invalid null listener key");
    synchronized (zzg)
    {
      paramZzck = (zzchb)zzg.remove(paramZzck);
      if (paramZzck != null)
      {
        paramZzck.zza();
        ((zzcgw)zza.zzb()).zza(zzchn.zza(paramZzck, paramZzcgr));
      }
      return;
    }
  }
  
  public final void zzc()
    throws RemoteException
  {
    synchronized (zze)
    {
      Iterator localIterator = zze.values().iterator();
      Object localObject4;
      while (localIterator.hasNext())
      {
        localObject4 = (zzchf)localIterator.next();
        if (localObject4 != null) {
          ((zzcgw)zza.zzb()).zza(zzchn.zza((zzx)localObject4, null));
        }
      }
      zze.clear();
      synchronized (zzg)
      {
        localIterator = zzg.values().iterator();
        while (localIterator.hasNext())
        {
          localObject4 = (zzchb)localIterator.next();
          if (localObject4 != null) {
            ((zzcgw)zza.zzb()).zza(zzchn.zza((zzu)localObject4, null));
          }
        }
        zzg.clear();
        synchronized (zzf)
        {
          localIterator = zzf.values().iterator();
          while (localIterator.hasNext())
          {
            localObject4 = (zzche)localIterator.next();
            if (localObject4 != null) {
              ((zzcgw)zza.zzb()).zza(new zzcfw(2, null, ((zzr)localObject4).asBinder(), null));
            }
          }
          zzf.clear();
          return;
        }
      }
    }
  }
  
  public final void zzd()
    throws RemoteException
  {
    if (zzd) {
      zza(false);
    }
  }
}
