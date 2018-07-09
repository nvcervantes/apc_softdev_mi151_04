package com.google.android.gms.common;

import android.content.Context;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.internal.zzba;
import com.google.android.gms.common.internal.zzbb;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.dynamite.DynamiteModule.zzc;

@Hide
final class zzg
{
  private static volatile zzba zza;
  private static final Object zzb = new Object();
  private static Context zzc;
  
  static zzp zza(String paramString, zzh paramZzh, boolean paramBoolean)
  {
    try
    {
      if (zza == null)
      {
        zzbq.zza(zzc);
        synchronized (zzb)
        {
          if (zza == null) {
            zza = zzbb.zza(DynamiteModule.zza(zzc, DynamiteModule.zzc, "com.google.android.gms.googlecertificates").zza("com.google.android.gms.common.GoogleCertificatesImpl"));
          }
        }
      }
      zzbq.zza(zzc);
      ??? = new zzn(paramString, paramZzh, paramBoolean);
      try
      {
        boolean bool = zza.zza((zzn)???, com.google.android.gms.dynamic.zzn.zza(zzc.getPackageManager()));
        if (bool) {
          return zzp.zza();
        }
        bool = true;
        if ((paramBoolean) || (!zzazza)) {
          bool = false;
        }
        return zzp.zza(paramString, paramZzh, paramBoolean, bool);
      }
      catch (RemoteException paramString)
      {
        Log.e("GoogleCertificates", "Failed to get Google certificates from remote", paramString);
        paramZzh = "module call";
      }
    }
    catch (DynamiteModule.zzc paramString)
    {
      for (;;)
      {
        paramZzh = "module init";
      }
    }
    return zzp.zza(paramZzh, paramString);
  }
  
  static void zza(Context paramContext)
  {
    try
    {
      if (zzc == null)
      {
        if (paramContext != null) {
          zzc = paramContext.getApplicationContext();
        }
      }
      else {
        Log.w("GoogleCertificates", "GoogleCertificates has been initialized already");
      }
      return;
    }
    finally {}
  }
}
