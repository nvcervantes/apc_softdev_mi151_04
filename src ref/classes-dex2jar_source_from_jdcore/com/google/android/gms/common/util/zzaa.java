package com.google.android.gms.common.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.WorkSource;
import android.support.annotation.Nullable;
import android.util.Log;
import com.google.android.gms.internal.zzbig;
import com.google.android.gms.internal.zzbih;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class zzaa
{
  private static final Method zza = ;
  private static final Method zzb = zzb();
  private static final Method zzc = zzc();
  private static final Method zzd = zzd();
  private static final Method zze = zze();
  
  private static WorkSource zza(int paramInt, String paramString)
  {
    WorkSource localWorkSource = new WorkSource();
    if (zzb != null)
    {
      String str = paramString;
      if (paramString == null) {
        str = "";
      }
      try
      {
        zzb.invoke(localWorkSource, new Object[] { Integer.valueOf(paramInt), str });
        return localWorkSource;
      }
      catch (Exception paramString)
      {
        Log.wtf("WorkSourceUtil", "Unable to assign blame through WorkSource", paramString);
        return localWorkSource;
      }
    }
    if (zza != null) {
      zza.invoke(localWorkSource, new Object[] { Integer.valueOf(paramInt) });
    }
    return localWorkSource;
  }
  
  @Nullable
  public static WorkSource zza(Context paramContext, @Nullable String paramString)
  {
    if ((paramContext != null) && (paramContext.getPackageManager() != null)) {
      if (paramString == null) {
        return null;
      }
    }
    try
    {
      paramContext = zzbih.zza(paramContext).zza(paramString, 0);
      if (paramContext == null)
      {
        paramContext = String.valueOf(paramString);
        if (paramContext.length() != 0) {
          paramContext = "Could not get applicationInfo from package: ".concat(paramContext);
        } else {
          paramContext = new String("Could not get applicationInfo from package: ");
        }
        Log.e("WorkSourceUtil", paramContext);
        return null;
      }
      return zza(uid, paramString);
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      for (;;) {}
    }
    paramContext = String.valueOf(paramString);
    if (paramContext.length() != 0) {
      paramContext = "Could not find package: ".concat(paramContext);
    } else {
      paramContext = new String("Could not find package: ");
    }
    Log.e("WorkSourceUtil", paramContext);
    return null;
  }
  
  @Nullable
  private static String zza(WorkSource paramWorkSource, int paramInt)
  {
    if (zze != null) {
      try
      {
        paramWorkSource = (String)zze.invoke(paramWorkSource, new Object[] { Integer.valueOf(paramInt) });
        return paramWorkSource;
      }
      catch (Exception paramWorkSource)
      {
        Log.wtf("WorkSourceUtil", "Unable to assign blame through WorkSource", paramWorkSource);
      }
    }
    return null;
  }
  
  private static Method zza()
  {
    try
    {
      Method localMethod = WorkSource.class.getMethod("add", new Class[] { Integer.TYPE });
      return localMethod;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    return null;
  }
  
  public static List<String> zza(@Nullable WorkSource paramWorkSource)
  {
    int j = 0;
    int i;
    if (paramWorkSource == null) {
      i = 0;
    } else {
      i = zzb(paramWorkSource);
    }
    if (i == 0) {
      return Collections.emptyList();
    }
    ArrayList localArrayList = new ArrayList();
    while (j < i)
    {
      String str = zza(paramWorkSource, j);
      if (!zzw.zza(str)) {
        localArrayList.add(str);
      }
      j += 1;
    }
    return localArrayList;
  }
  
  public static boolean zza(Context paramContext)
  {
    if (paramContext == null) {
      return false;
    }
    if (paramContext.getPackageManager() == null) {
      return false;
    }
    return zzbih.zza(paramContext).zza("android.permission.UPDATE_DEVICE_STATS", paramContext.getPackageName()) == 0;
  }
  
  private static int zzb(WorkSource paramWorkSource)
  {
    if (zzc != null) {
      try
      {
        int i = ((Integer)zzc.invoke(paramWorkSource, new Object[0])).intValue();
        return i;
      }
      catch (Exception paramWorkSource)
      {
        Log.wtf("WorkSourceUtil", "Unable to assign blame through WorkSource", paramWorkSource);
      }
    }
    return 0;
  }
  
  private static Method zzb()
  {
    if (zzs.zzd()) {}
    try
    {
      Method localMethod = WorkSource.class.getMethod("add", new Class[] { Integer.TYPE, String.class });
      return localMethod;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    return null;
  }
  
  private static Method zzc()
  {
    try
    {
      Method localMethod = WorkSource.class.getMethod("size", new Class[0]);
      return localMethod;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    return null;
  }
  
  private static Method zzd()
  {
    try
    {
      Method localMethod = WorkSource.class.getMethod("get", new Class[] { Integer.TYPE });
      return localMethod;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    return null;
  }
  
  private static Method zze()
  {
    if (zzs.zzd()) {}
    try
    {
      Method localMethod = WorkSource.class.getMethod("getName", new Class[] { Integer.TYPE });
      return localMethod;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    return null;
  }
}
