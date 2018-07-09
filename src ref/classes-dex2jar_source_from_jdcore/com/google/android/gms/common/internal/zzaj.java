package com.google.android.gms.common.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import com.google.android.gms.common.stats.zza;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

final class zzaj
  implements ServiceConnection
{
  private final Set<ServiceConnection> zza;
  private int zzb;
  private boolean zzc;
  private IBinder zzd;
  private final zzah zze;
  private ComponentName zzf;
  
  public zzaj(zzai paramZzai, zzah paramZzah)
  {
    zze = paramZzah;
    zza = new HashSet();
    zzb = 2;
  }
  
  public final void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder)
  {
    synchronized (zzai.zza(zzg))
    {
      zzai.zzb(zzg).removeMessages(1, zze);
      zzd = paramIBinder;
      zzf = paramComponentName;
      Iterator localIterator = zza.iterator();
      while (localIterator.hasNext()) {
        ((ServiceConnection)localIterator.next()).onServiceConnected(paramComponentName, paramIBinder);
      }
      zzb = 1;
      return;
    }
  }
  
  public final void onServiceDisconnected(ComponentName paramComponentName)
  {
    synchronized (zzai.zza(zzg))
    {
      zzai.zzb(zzg).removeMessages(1, zze);
      zzd = null;
      zzf = paramComponentName;
      Iterator localIterator = zza.iterator();
      while (localIterator.hasNext()) {
        ((ServiceConnection)localIterator.next()).onServiceDisconnected(paramComponentName);
      }
      zzb = 2;
      return;
    }
  }
  
  public final void zza(ServiceConnection paramServiceConnection, String paramString)
  {
    zzai.zzd(zzg);
    zzai.zzc(zzg);
    zze.zza(zzai.zzc(zzg));
    zza.add(paramServiceConnection);
  }
  
  public final void zza(String paramString)
  {
    zzb = 3;
    zzc = zzai.zzd(zzg).zza(zzai.zzc(zzg), paramString, zze.zza(zzai.zzc(zzg)), this, zze.zzc());
    if (zzc)
    {
      paramString = zzai.zzb(zzg).obtainMessage(1, zze);
      zzai.zzb(zzg).sendMessageDelayed(paramString, zzai.zze(zzg));
      return;
    }
    zzb = 2;
    try
    {
      zzai.zzd(zzg);
      zzai.zzc(zzg).unbindService(this);
      return;
    }
    catch (IllegalArgumentException paramString) {}
  }
  
  public final boolean zza()
  {
    return zzc;
  }
  
  public final boolean zza(ServiceConnection paramServiceConnection)
  {
    return zza.contains(paramServiceConnection);
  }
  
  public final int zzb()
  {
    return zzb;
  }
  
  public final void zzb(ServiceConnection paramServiceConnection, String paramString)
  {
    zzai.zzd(zzg);
    zzai.zzc(zzg);
    zza.remove(paramServiceConnection);
  }
  
  public final void zzb(String paramString)
  {
    zzai.zzb(zzg).removeMessages(1, zze);
    zzai.zzd(zzg);
    zzai.zzc(zzg).unbindService(this);
    zzc = false;
    zzb = 2;
  }
  
  public final boolean zzc()
  {
    return zza.isEmpty();
  }
  
  public final IBinder zzd()
  {
    return zzd;
  }
  
  public final ComponentName zze()
  {
    return zzf;
  }
}
