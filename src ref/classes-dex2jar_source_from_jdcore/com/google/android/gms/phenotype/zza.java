package com.google.android.gms.phenotype;

import android.content.ContentResolver;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class zza
{
  private static final ConcurrentHashMap<Uri, zza> zza = new ConcurrentHashMap();
  private static String[] zzg = { "key", "value" };
  private final ContentResolver zzb;
  private final Uri zzc;
  private final ContentObserver zzd;
  private final Object zze = new Object();
  private volatile Map<String, String> zzf;
  
  private zza(ContentResolver paramContentResolver, Uri paramUri)
  {
    zzb = paramContentResolver;
    zzc = paramUri;
    zzd = new zzb(this, null);
  }
  
  public static zza zza(ContentResolver paramContentResolver, Uri paramUri)
  {
    zza localZza2 = (zza)zza.get(paramUri);
    zza localZza1 = localZza2;
    if (localZza2 == null)
    {
      paramContentResolver = new zza(paramContentResolver, paramUri);
      localZza1 = (zza)zza.putIfAbsent(paramUri, paramContentResolver);
      if (localZza1 == null)
      {
        zzb.registerContentObserver(zzc, false, zzd);
        return paramContentResolver;
      }
    }
    return localZza1;
  }
  
  private final Map<String, String> zzc()
  {
    HashMap localHashMap = new HashMap();
    Cursor localCursor = zzb.query(zzc, zzg, null, null, null);
    if (localCursor != null) {
      try
      {
        while (localCursor.moveToNext()) {
          localHashMap.put(localCursor.getString(0), localCursor.getString(1));
        }
        return localHashMap;
      }
      finally
      {
        localCursor.close();
      }
    }
    return localMap;
  }
  
  public final Map<String, String> zza()
  {
    Object localObject1;
    if (PhenotypeFlag.zza("gms:phenotype:phenotype_flag:debug_disable_caching", false)) {
      localObject1 = zzc();
    } else {
      localObject1 = zzf;
    }
    if (localObject1 == null) {
      synchronized (zze)
      {
        Map localMap1 = zzf;
        localObject1 = localMap1;
        if (localMap1 == null)
        {
          localObject1 = zzc();
          zzf = ((Map)localObject1);
        }
        return localObject1;
      }
    }
    return localMap;
  }
  
  public final void zzb()
  {
    synchronized (zze)
    {
      zzf = null;
      return;
    }
  }
}
