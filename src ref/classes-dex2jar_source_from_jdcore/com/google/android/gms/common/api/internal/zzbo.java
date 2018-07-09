package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.api.Api.zze;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzbz;
import com.google.android.gms.internal.zzcyj;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public final class zzbo<O extends Api.ApiOptions>
  implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, zzu
{
  private final Queue<zza> zzb = new LinkedList();
  private final Api.zze zzc;
  private final Api.zzb zzd;
  private final zzh<O> zze;
  private final zzae zzf;
  private final Set<zzj> zzg = new HashSet();
  private final Map<zzck<?>, zzcr> zzh = new HashMap();
  private final int zzi;
  private final zzcv zzj;
  private boolean zzk;
  private int zzl = -1;
  private ConnectionResult zzm = null;
  
  @WorkerThread
  public zzbo(GoogleApi<O> paramGoogleApi)
  {
    Object localObject1;
    zzc = localObject1.zza(zzbm.zza(paramGoogleApi).getLooper(), this);
    if ((zzc instanceof zzbz)) {}
    for (Object localObject2 = zzbz.zzi();; localObject2 = zzc)
    {
      zzd = ((Api.zzb)localObject2);
      break;
    }
    zze = localObject1.zzc();
    zzf = new zzae();
    zzi = localObject1.zzd();
    if (zzc.l_())
    {
      zzj = localObject1.zza(zzbm.zzc(paramGoogleApi), zzbm.zza(paramGoogleApi));
      return;
    }
    zzj = null;
  }
  
  @WorkerThread
  private final void zzb(ConnectionResult paramConnectionResult)
  {
    Iterator localIterator = zzg.iterator();
    while (localIterator.hasNext())
    {
      zzj localZzj = (zzj)localIterator.next();
      String str = null;
      if (paramConnectionResult == ConnectionResult.zza) {
        str = zzc.zzw();
      }
      localZzj.zza(zze, paramConnectionResult, str);
    }
    zzg.clear();
  }
  
  @WorkerThread
  private final void zzb(zza paramZza)
  {
    paramZza.zza(zzf, zzk());
    try
    {
      paramZza.zza(this);
      return;
    }
    catch (DeadObjectException paramZza)
    {
      for (;;) {}
    }
    onConnectionSuspended(1);
    zzc.zzg();
  }
  
  private final void zzn()
  {
    zzl = -1;
    zzbm.zza(zza, -1);
  }
  
  @WorkerThread
  private final void zzo()
  {
    zzd();
    zzb(ConnectionResult.zza);
    zzq();
    Iterator localIterator = zzh.values().iterator();
    for (;;)
    {
      zzcr localZzcr;
      if (localIterator.hasNext()) {
        localZzcr = (zzcr)localIterator.next();
      }
      try
      {
        zza.zza(zzd, new TaskCompletionSource());
      }
      catch (DeadObjectException localDeadObjectException)
      {
        for (;;) {}
      }
      catch (RemoteException localRemoteException) {}
      onConnectionSuspended(1);
      zzc.zzg();
      while ((zzc.zzs()) && (!zzb.isEmpty())) {
        zzb((zza)zzb.remove());
      }
      zzr();
      return;
    }
  }
  
  @WorkerThread
  private final void zzp()
  {
    zzd();
    zzk = true;
    zzf.zzc();
    zzbm.zza(zza).sendMessageDelayed(Message.obtain(zzbm.zza(zza), 9, zze), zzbm.zzd(zza));
    zzbm.zza(zza).sendMessageDelayed(Message.obtain(zzbm.zza(zza), 11, zze), zzbm.zze(zza));
    zzn();
  }
  
  @WorkerThread
  private final void zzq()
  {
    if (zzk)
    {
      zzbm.zza(zza).removeMessages(11, zze);
      zzbm.zza(zza).removeMessages(9, zze);
      zzk = false;
    }
  }
  
  private final void zzr()
  {
    zzbm.zza(zza).removeMessages(12, zze);
    zzbm.zza(zza).sendMessageDelayed(zzbm.zza(zza).obtainMessage(12, zze), zzbm.zzi(zza));
  }
  
  public final void onConnected(@Nullable Bundle paramBundle)
  {
    if (Looper.myLooper() == zzbm.zza(zza).getLooper())
    {
      zzo();
      return;
    }
    zzbm.zza(zza).post(new zzbp(this));
  }
  
  @WorkerThread
  public final void onConnectionFailed(@NonNull ConnectionResult paramConnectionResult)
  {
    com.google.android.gms.common.internal.zzbq.zza(zzbm.zza(zza));
    if (zzj != null) {
      zzj.zzb();
    }
    zzd();
    zzn();
    zzb(paramConnectionResult);
    if (paramConnectionResult.getErrorCode() == 4)
    {
      zza(zzbm.zzf());
      return;
    }
    if (zzb.isEmpty())
    {
      zzm = paramConnectionResult;
      return;
    }
    synchronized (zzbm.zzg())
    {
      if ((zzbm.zzf(zza) != null) && (zzbm.zzg(zza).contains(zze)))
      {
        zzbm.zzf(zza).zzb(paramConnectionResult, zzi);
        return;
      }
      if (!zza.zza(paramConnectionResult, zzi))
      {
        if (paramConnectionResult.getErrorCode() == 18) {
          zzk = true;
        }
        if (zzk)
        {
          zzbm.zza(zza).sendMessageDelayed(Message.obtain(zzbm.zza(zza), 9, zze), zzbm.zzd(zza));
          return;
        }
        paramConnectionResult = zze.zza();
        ??? = new StringBuilder(38 + String.valueOf(paramConnectionResult).length());
        ((StringBuilder)???).append("API: ");
        ((StringBuilder)???).append(paramConnectionResult);
        ((StringBuilder)???).append(" is not available on this device.");
        zza(new Status(17, ((StringBuilder)???).toString()));
      }
      return;
    }
  }
  
  public final void onConnectionSuspended(int paramInt)
  {
    if (Looper.myLooper() == zzbm.zza(zza).getLooper())
    {
      zzp();
      return;
    }
    zzbm.zza(zza).post(new zzbq(this));
  }
  
  @WorkerThread
  public final void zza()
  {
    com.google.android.gms.common.internal.zzbq.zza(zzbm.zza(zza));
    zza(zzbm.zza);
    zzf.zzb();
    zzck[] arrayOfZzck = (zzck[])zzh.keySet().toArray(new zzck[zzh.size()]);
    int i = 0;
    int j = arrayOfZzck.length;
    while (i < j)
    {
      zza(new zzf(arrayOfZzck[i], new TaskCompletionSource()));
      i += 1;
    }
    zzb(new ConnectionResult(4));
    if (zzc.zzs()) {
      zzc.zza(new zzbs(this));
    }
  }
  
  @WorkerThread
  public final void zza(@NonNull ConnectionResult paramConnectionResult)
  {
    com.google.android.gms.common.internal.zzbq.zza(zzbm.zza(zza));
    zzc.zzg();
    onConnectionFailed(paramConnectionResult);
  }
  
  public final void zza(ConnectionResult paramConnectionResult, Api<?> paramApi, boolean paramBoolean)
  {
    if (Looper.myLooper() == zzbm.zza(zza).getLooper())
    {
      onConnectionFailed(paramConnectionResult);
      return;
    }
    zzbm.zza(zza).post(new zzbr(this, paramConnectionResult));
  }
  
  @WorkerThread
  public final void zza(Status paramStatus)
  {
    com.google.android.gms.common.internal.zzbq.zza(zzbm.zza(zza));
    Iterator localIterator = zzb.iterator();
    while (localIterator.hasNext()) {
      ((zza)localIterator.next()).zza(paramStatus);
    }
    zzb.clear();
  }
  
  @WorkerThread
  public final void zza(zza paramZza)
  {
    com.google.android.gms.common.internal.zzbq.zza(zzbm.zza(zza));
    if (zzc.zzs())
    {
      zzb(paramZza);
      zzr();
      return;
    }
    zzb.add(paramZza);
    if ((zzm != null) && (zzm.hasResolution()))
    {
      onConnectionFailed(zzm);
      return;
    }
    zzi();
  }
  
  @WorkerThread
  public final void zza(zzj paramZzj)
  {
    com.google.android.gms.common.internal.zzbq.zza(zzbm.zza(zza));
    zzg.add(paramZzj);
  }
  
  public final Api.zze zzb()
  {
    return zzc;
  }
  
  public final Map<zzck<?>, zzcr> zzc()
  {
    return zzh;
  }
  
  @WorkerThread
  public final void zzd()
  {
    com.google.android.gms.common.internal.zzbq.zza(zzbm.zza(zza));
    zzm = null;
  }
  
  @WorkerThread
  public final ConnectionResult zze()
  {
    com.google.android.gms.common.internal.zzbq.zza(zzbm.zza(zza));
    return zzm;
  }
  
  @WorkerThread
  public final void zzf()
  {
    com.google.android.gms.common.internal.zzbq.zza(zzbm.zza(zza));
    if (zzk) {
      zzi();
    }
  }
  
  @WorkerThread
  public final void zzg()
  {
    com.google.android.gms.common.internal.zzbq.zza(zzbm.zza(zza));
    if (zzk)
    {
      zzq();
      Status localStatus;
      if (zzbm.zzh(zza).isGooglePlayServicesAvailable(zzbm.zzc(zza)) == 18) {
        localStatus = new Status(8, "Connection timed out while waiting for Google Play services update to complete.");
      } else {
        localStatus = new Status(8, "API failed to connect while resuming due to an unknown error.");
      }
      zza(localStatus);
      zzc.zzg();
    }
  }
  
  @WorkerThread
  public final void zzh()
  {
    com.google.android.gms.common.internal.zzbq.zza(zzbm.zza(zza));
    if ((zzc.zzs()) && (zzh.size() == 0))
    {
      if (zzf.zza())
      {
        zzr();
        return;
      }
      zzc.zzg();
    }
  }
  
  @WorkerThread
  public final void zzi()
  {
    com.google.android.gms.common.internal.zzbq.zza(zzbm.zza(zza));
    if (!zzc.zzs())
    {
      if (zzc.zzt()) {
        return;
      }
      if (zzc.zzu())
      {
        zzc.zzx();
        if (zzbm.zzb(zza) != 0)
        {
          zzbm.zzh(zza);
          int i = GoogleApiAvailability.zza(zzbm.zzc(zza), zzc.zzx());
          zzc.zzx();
          zzbm.zza(zza, i);
          if (i != 0)
          {
            onConnectionFailed(new ConnectionResult(i, null));
            return;
          }
        }
      }
      zzbu localZzbu = new zzbu(zza, zzc, zze);
      if (zzc.l_()) {
        zzj.zza(localZzbu);
      }
      zzc.zza(localZzbu);
    }
  }
  
  final boolean zzj()
  {
    return zzc.zzs();
  }
  
  public final boolean zzk()
  {
    return zzc.l_();
  }
  
  public final int zzl()
  {
    return zzi;
  }
  
  final zzcyj zzm()
  {
    if (zzj == null) {
      return null;
    }
    return zzj.zza();
  }
}
