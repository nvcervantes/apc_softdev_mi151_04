package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.internal.zzbig;
import com.google.android.gms.internal.zzbih;

public final class zzbf
{
  private static Object zza = new Object();
  private static boolean zzb;
  private static String zzc;
  private static int zzd;
  
  public static String zza(Context paramContext)
  {
    zzc(paramContext);
    return zzc;
  }
  
  public static int zzb(Context paramContext)
  {
    zzc(paramContext);
    return zzd;
  }
  
  private static void zzc(Context paramContext)
  {
    synchronized (zza)
    {
      if (zzb) {
        return;
      }
      zzb = true;
      String str = paramContext.getPackageName();
      paramContext = zzbih.zza(paramContext);
      try
      {
        paramContext = zza128metaData;
        if (paramContext == null) {
          return;
        }
        zzc = paramContext.getString("com.google.app.id");
        zzd = paramContext.getInt("com.google.android.gms.version");
      }
      catch (PackageManager.NameNotFoundException paramContext)
      {
        Log.wtf("MetadataValueReader", "This should never happen.", paramContext);
      }
      return;
    }
  }
}
