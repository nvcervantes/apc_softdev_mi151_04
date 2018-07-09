package com.google.android.gms.common.api.internal;

import android.app.Activity;
import android.support.v4.util.ArraySet;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.zzbq;

public class zzah
  extends zzo
{
  private final ArraySet<zzh<?>> zze = new ArraySet();
  private zzbm zzf;
  
  private zzah(zzcf paramZzcf)
  {
    super(paramZzcf);
    zzd.zza("ConnectionlessLifecycleHelper", this);
  }
  
  public static void zza(Activity paramActivity, zzbm paramZzbm, zzh<?> paramZzh)
  {
    zzcf localZzcf = zza(paramActivity);
    zzah localZzah = (zzah)localZzcf.zza("ConnectionlessLifecycleHelper", zzah.class);
    paramActivity = localZzah;
    if (localZzah == null) {
      paramActivity = new zzah(localZzcf);
    }
    zzf = paramZzbm;
    zzbq.zza(paramZzh, "ApiKey cannot be null");
    zze.add(paramZzh);
    paramZzbm.zza(paramActivity);
  }
  
  private final void zzi()
  {
    if (!zze.isEmpty()) {
      zzf.zza(this);
    }
  }
  
  public final void zza()
  {
    super.zza();
    zzi();
  }
  
  protected final void zza(ConnectionResult paramConnectionResult, int paramInt)
  {
    zzf.zzb(paramConnectionResult, paramInt);
  }
  
  public final void zzb()
  {
    super.zzb();
    zzf.zzb(this);
  }
  
  protected final void zzc()
  {
    zzf.zzd();
  }
  
  public final void zze()
  {
    super.zze();
    zzi();
  }
  
  final ArraySet<zzh<?>> zzf()
  {
    return zze;
  }
}
