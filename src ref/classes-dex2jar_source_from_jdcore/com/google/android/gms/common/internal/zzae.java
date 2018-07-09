package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public final class zzae
  implements Handler.Callback
{
  private final zzaf zza;
  private final ArrayList<GoogleApiClient.ConnectionCallbacks> zzb = new ArrayList();
  private ArrayList<GoogleApiClient.ConnectionCallbacks> zzc = new ArrayList();
  private final ArrayList<GoogleApiClient.OnConnectionFailedListener> zzd = new ArrayList();
  private volatile boolean zze = false;
  private final AtomicInteger zzf = new AtomicInteger(0);
  private boolean zzg = false;
  private final Handler zzh;
  private final Object zzi = new Object();
  
  public zzae(Looper paramLooper, zzaf paramZzaf)
  {
    zza = paramZzaf;
    zzh = new Handler(paramLooper, this);
  }
  
  public final boolean handleMessage(Message arg1)
  {
    if (what == 1)
    {
      GoogleApiClient.ConnectionCallbacks localConnectionCallbacks = (GoogleApiClient.ConnectionCallbacks)obj;
      synchronized (zzi)
      {
        if ((zze) && (zza.zzs()) && (zzb.contains(localConnectionCallbacks))) {
          localConnectionCallbacks.onConnected(zza.q_());
        }
        return true;
      }
    }
    int i = what;
    ??? = new StringBuilder(45);
    ???.append("Don't know how to handle message: ");
    ???.append(i);
    Log.wtf("GmsClientEvents", ???.toString(), new Exception());
    return false;
  }
  
  public final void zza()
  {
    zze = false;
    zzf.incrementAndGet();
  }
  
  public final void zza(int paramInt)
  {
    boolean bool;
    if (Looper.myLooper() == zzh.getLooper()) {
      bool = true;
    } else {
      bool = false;
    }
    zzbq.zza(bool, "onUnintentionalDisconnection must only be called on the Handler thread");
    zzh.removeMessages(1);
    synchronized (zzi)
    {
      zzg = true;
      ArrayList localArrayList = new ArrayList(zzb);
      int k = zzf.get();
      localArrayList = (ArrayList)localArrayList;
      int m = localArrayList.size();
      int i = 0;
      while (i < m)
      {
        Object localObject3 = localArrayList.get(i);
        int j = i + 1;
        localObject3 = (GoogleApiClient.ConnectionCallbacks)localObject3;
        if ((!zze) || (zzf.get() != k)) {
          break;
        }
        i = j;
        if (zzb.contains(localObject3))
        {
          ((GoogleApiClient.ConnectionCallbacks)localObject3).onConnectionSuspended(paramInt);
          i = j;
        }
      }
      zzc.clear();
      zzg = false;
      return;
    }
  }
  
  public final void zza(Bundle paramBundle)
  {
    ??? = Looper.myLooper();
    Object localObject2 = zzh.getLooper();
    boolean bool2 = true;
    boolean bool1;
    if (??? == localObject2) {
      bool1 = true;
    } else {
      bool1 = false;
    }
    zzbq.zza(bool1, "onConnectionSuccess must only be called on the Handler thread");
    for (;;)
    {
      synchronized (zzi)
      {
        zzbq.zza(zzg ^ true);
        zzh.removeMessages(1);
        zzg = true;
        if (zzc.size() == 0)
        {
          bool1 = bool2;
          zzbq.zza(bool1);
          localObject2 = new ArrayList(zzb);
          int k = zzf.get();
          localObject2 = (ArrayList)localObject2;
          int m = ((ArrayList)localObject2).size();
          int i = 0;
          if (i < m)
          {
            Object localObject3 = ((ArrayList)localObject2).get(i);
            int j = i + 1;
            localObject3 = (GoogleApiClient.ConnectionCallbacks)localObject3;
            if ((zze) && (zza.zzs()) && (zzf.get() == k))
            {
              i = j;
              if (zzc.contains(localObject3)) {
                continue;
              }
              ((GoogleApiClient.ConnectionCallbacks)localObject3).onConnected(paramBundle);
              i = j;
              continue;
            }
          }
          zzc.clear();
          zzg = false;
          return;
        }
      }
      bool1 = false;
    }
  }
  
  public final void zza(ConnectionResult paramConnectionResult)
  {
    ??? = Looper.myLooper();
    Object localObject2 = zzh.getLooper();
    int i = 0;
    boolean bool;
    if (??? == localObject2) {
      bool = true;
    } else {
      bool = false;
    }
    zzbq.zza(bool, "onConnectionFailure must only be called on the Handler thread");
    zzh.removeMessages(1);
    synchronized (zzi)
    {
      localObject2 = new ArrayList(zzd);
      int k = zzf.get();
      localObject2 = (ArrayList)localObject2;
      int m = ((ArrayList)localObject2).size();
      while (i < m)
      {
        Object localObject3 = ((ArrayList)localObject2).get(i);
        int j = i + 1;
        localObject3 = (GoogleApiClient.OnConnectionFailedListener)localObject3;
        if ((zze) && (zzf.get() == k))
        {
          i = j;
          if (zzd.contains(localObject3))
          {
            ((GoogleApiClient.OnConnectionFailedListener)localObject3).onConnectionFailed(paramConnectionResult);
            i = j;
          }
        }
        else
        {
          return;
        }
      }
      return;
    }
  }
  
  public final void zza(GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks)
  {
    zzbq.zza(paramConnectionCallbacks);
    synchronized (zzi)
    {
      if (zzb.contains(paramConnectionCallbacks))
      {
        String str = String.valueOf(paramConnectionCallbacks);
        StringBuilder localStringBuilder = new StringBuilder(62 + String.valueOf(str).length());
        localStringBuilder.append("registerConnectionCallbacks(): listener ");
        localStringBuilder.append(str);
        localStringBuilder.append(" is already registered");
        Log.w("GmsClientEvents", localStringBuilder.toString());
      }
      else
      {
        zzb.add(paramConnectionCallbacks);
      }
      if (zza.zzs()) {
        zzh.sendMessage(zzh.obtainMessage(1, paramConnectionCallbacks));
      }
      return;
    }
  }
  
  public final void zza(GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    zzbq.zza(paramOnConnectionFailedListener);
    synchronized (zzi)
    {
      if (zzd.contains(paramOnConnectionFailedListener))
      {
        paramOnConnectionFailedListener = String.valueOf(paramOnConnectionFailedListener);
        StringBuilder localStringBuilder = new StringBuilder(67 + String.valueOf(paramOnConnectionFailedListener).length());
        localStringBuilder.append("registerConnectionFailedListener(): listener ");
        localStringBuilder.append(paramOnConnectionFailedListener);
        localStringBuilder.append(" is already registered");
        Log.w("GmsClientEvents", localStringBuilder.toString());
      }
      else
      {
        zzd.add(paramOnConnectionFailedListener);
      }
      return;
    }
  }
  
  public final void zzb()
  {
    zze = true;
  }
  
  public final boolean zzb(GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks)
  {
    zzbq.zza(paramConnectionCallbacks);
    synchronized (zzi)
    {
      boolean bool = zzb.contains(paramConnectionCallbacks);
      return bool;
    }
  }
  
  public final boolean zzb(GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    zzbq.zza(paramOnConnectionFailedListener);
    synchronized (zzi)
    {
      boolean bool = zzd.contains(paramOnConnectionFailedListener);
      return bool;
    }
  }
  
  public final void zzc(GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks)
  {
    zzbq.zza(paramConnectionCallbacks);
    synchronized (zzi)
    {
      if (!zzb.remove(paramConnectionCallbacks))
      {
        paramConnectionCallbacks = String.valueOf(paramConnectionCallbacks);
        StringBuilder localStringBuilder = new StringBuilder(52 + String.valueOf(paramConnectionCallbacks).length());
        localStringBuilder.append("unregisterConnectionCallbacks(): listener ");
        localStringBuilder.append(paramConnectionCallbacks);
        localStringBuilder.append(" not found");
        Log.w("GmsClientEvents", localStringBuilder.toString());
      }
      else if (zzg)
      {
        zzc.add(paramConnectionCallbacks);
      }
      return;
    }
  }
  
  public final void zzc(GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    zzbq.zza(paramOnConnectionFailedListener);
    synchronized (zzi)
    {
      if (!zzd.remove(paramOnConnectionFailedListener))
      {
        paramOnConnectionFailedListener = String.valueOf(paramOnConnectionFailedListener);
        StringBuilder localStringBuilder = new StringBuilder(57 + String.valueOf(paramOnConnectionFailedListener).length());
        localStringBuilder.append("unregisterConnectionFailedListener(): listener ");
        localStringBuilder.append(paramOnConnectionFailedListener);
        localStringBuilder.append(" not found");
        Log.w("GmsClientEvents", localStringBuilder.toString());
      }
      return;
    }
  }
}
