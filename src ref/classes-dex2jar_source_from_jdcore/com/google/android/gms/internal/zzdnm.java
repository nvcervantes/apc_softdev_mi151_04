package com.google.android.gms.internal;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class zzdnm
{
  private static Uri zza = Uri.parse("content://com.google.android.gsf.gservices");
  private static Uri zzb = Uri.parse("content://com.google.android.gsf.gservices/prefix");
  private static Pattern zzc = Pattern.compile("^(1|true|t|on|yes|y)$", 2);
  private static Pattern zzd = Pattern.compile("^(0|false|f|off|no|n)$", 2);
  private static final AtomicBoolean zze = new AtomicBoolean();
  private static HashMap<String, String> zzf;
  private static HashMap<String, Boolean> zzg = new HashMap();
  private static HashMap<String, Integer> zzh = new HashMap();
  private static HashMap<String, Long> zzi = new HashMap();
  private static HashMap<String, Float> zzj = new HashMap();
  private static Object zzk;
  private static boolean zzl;
  private static String[] zzm = new String[0];
  
  public zzdnm() {}
  
  public static long zza(ContentResolver paramContentResolver, String paramString, long paramLong)
  {
    Object localObject2 = zzb(paramContentResolver);
    localObject1 = zzi;
    paramLong = 0L;
    localObject1 = (Long)zza((HashMap)localObject1, paramString, Long.valueOf(0L));
    if (localObject1 != null) {
      return ((Long)localObject1).longValue();
    }
    paramContentResolver = zza(paramContentResolver, paramString, null);
    if (paramContentResolver == null) {
      paramContentResolver = (ContentResolver)localObject1;
    }
    try
    {
      long l = Long.parseLong(paramContentResolver);
      paramContentResolver = Long.valueOf(l);
      paramLong = l;
    }
    catch (NumberFormatException paramContentResolver)
    {
      for (;;)
      {
        paramContentResolver = (ContentResolver)localObject1;
      }
    }
    zza(localObject2, zzi, paramString, paramContentResolver);
    return paramLong;
  }
  
  private static <T> T zza(HashMap<String, T> paramHashMap, String paramString, T paramT)
  {
    for (;;)
    {
      try
      {
        if (paramHashMap.containsKey(paramString))
        {
          paramHashMap = paramHashMap.get(paramString);
          if (paramHashMap != null) {
            return paramHashMap;
          }
        }
        else
        {
          return null;
        }
      }
      finally {}
      paramHashMap = paramT;
    }
  }
  
  public static String zza(ContentResolver paramContentResolver, String paramString1, String paramString2)
  {
    for (;;)
    {
      int i;
      Cursor localCursor;
      try
      {
        zza(paramContentResolver);
        Object localObject = zzk;
        if (zzf.containsKey(paramString1))
        {
          paramContentResolver = (String)zzf.get(paramString1);
          if (paramContentResolver == null) {
            break label286;
          }
          return paramContentResolver;
        }
        paramString2 = zzm;
        int j = paramString2.length;
        i = 0;
        if (i < j)
        {
          if (!paramString1.startsWith(paramString2[i])) {
            break label296;
          }
          if ((!zzl) || (zzf.isEmpty()))
          {
            paramString2 = zzm;
            zzf.putAll(zza(paramContentResolver, paramString2));
            zzl = true;
            if (zzf.containsKey(paramString1))
            {
              paramContentResolver = (String)zzf.get(paramString1);
              if (paramContentResolver == null) {
                break label291;
              }
              return paramContentResolver;
            }
          }
          return null;
        }
        localCursor = paramContentResolver.query(zza, null, null, new String[] { paramString1 }, null);
        if (localCursor != null) {
          try
          {
            if (localCursor.moveToFirst())
            {
              paramString2 = localCursor.getString(1);
              paramContentResolver = paramString2;
              if (paramString2 != null)
              {
                paramContentResolver = paramString2;
                if (paramString2.equals(null)) {
                  paramContentResolver = null;
                }
              }
              zza(localObject, paramString1, paramContentResolver);
              if (paramContentResolver == null) {
                paramContentResolver = null;
              }
              if (localCursor != null) {
                localCursor.close();
              }
              return paramContentResolver;
            }
          }
          finally
          {
            continue;
          }
        }
        zza(localObject, paramString1, null);
        if (localCursor != null) {
          localCursor.close();
        }
        return null;
      }
      finally {}
      if (localCursor != null) {
        localCursor.close();
      }
      throw paramContentResolver;
      label286:
      paramContentResolver = null;
      continue;
      label291:
      paramContentResolver = null;
      continue;
      label296:
      i += 1;
    }
  }
  
  private static Map<String, String> zza(ContentResolver paramContentResolver, String... paramVarArgs)
  {
    paramContentResolver = paramContentResolver.query(zzb, null, null, paramVarArgs, null);
    paramVarArgs = new TreeMap();
    if (paramContentResolver == null) {
      return paramVarArgs;
    }
    try
    {
      while (paramContentResolver.moveToNext()) {
        paramVarArgs.put(paramContentResolver.getString(0), paramContentResolver.getString(1));
      }
      return paramVarArgs;
    }
    finally
    {
      paramContentResolver.close();
    }
  }
  
  private static void zza(ContentResolver paramContentResolver)
  {
    if (zzf == null)
    {
      zze.set(false);
      zzf = new HashMap();
      zzk = new Object();
      zzl = false;
      paramContentResolver.registerContentObserver(zza, true, new zzdnn(null));
      return;
    }
    if (zze.getAndSet(false))
    {
      zzf.clear();
      zzg.clear();
      zzh.clear();
      zzi.clear();
      zzj.clear();
      zzk = new Object();
      zzl = false;
    }
  }
  
  private static void zza(Object paramObject, String paramString1, String paramString2)
  {
    try
    {
      if (paramObject == zzk) {
        zzf.put(paramString1, paramString2);
      }
      return;
    }
    finally {}
  }
  
  private static <T> void zza(Object paramObject, HashMap<String, T> paramHashMap, String paramString, T paramT)
  {
    try
    {
      if (paramObject == zzk)
      {
        paramHashMap.put(paramString, paramT);
        zzf.remove(paramString);
      }
      return;
    }
    finally {}
  }
  
  public static boolean zza(ContentResolver paramContentResolver, String paramString, boolean paramBoolean)
  {
    Object localObject = zzb(paramContentResolver);
    Boolean localBoolean = (Boolean)zza(zzg, paramString, Boolean.valueOf(paramBoolean));
    if (localBoolean != null) {
      return localBoolean.booleanValue();
    }
    String str = zza(paramContentResolver, paramString, null);
    paramContentResolver = localBoolean;
    boolean bool = paramBoolean;
    if (str != null) {
      if (str.equals(""))
      {
        paramContentResolver = localBoolean;
        bool = paramBoolean;
      }
      else if (zzc.matcher(str).matches())
      {
        paramContentResolver = Boolean.valueOf(true);
        bool = true;
      }
      else if (zzd.matcher(str).matches())
      {
        paramContentResolver = Boolean.valueOf(false);
        bool = false;
      }
      else
      {
        paramContentResolver = new StringBuilder("attempt to read gservices key ");
        paramContentResolver.append(paramString);
        paramContentResolver.append(" (value \"");
        paramContentResolver.append(str);
        paramContentResolver.append("\") as boolean");
        Log.w("Gservices", paramContentResolver.toString());
        bool = paramBoolean;
        paramContentResolver = localBoolean;
      }
    }
    zza(localObject, zzg, paramString, paramContentResolver);
    return bool;
  }
  
  private static Object zzb(ContentResolver paramContentResolver)
  {
    try
    {
      zza(paramContentResolver);
      paramContentResolver = zzk;
      return paramContentResolver;
    }
    finally {}
  }
}
