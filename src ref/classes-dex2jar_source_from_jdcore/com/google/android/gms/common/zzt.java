package com.google.android.gms.common;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.util.Log;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbig;
import com.google.android.gms.internal.zzbih;

@Hide
public class zzt
{
  private static zzt zza;
  private final Context zzb;
  
  private zzt(Context paramContext)
  {
    zzb = paramContext.getApplicationContext();
  }
  
  @Hide
  private static zzh zza(PackageInfo paramPackageInfo, zzh... paramVarArgs)
  {
    if (signatures == null) {
      return null;
    }
    if (signatures.length != 1)
    {
      Log.w("GoogleSignatureVerifier", "Package has more than one signature.");
      return null;
    }
    paramPackageInfo = signatures;
    int i = 0;
    paramPackageInfo = new zzi(paramPackageInfo[0].toByteArray());
    while (i < paramVarArgs.length)
    {
      if (paramVarArgs[i].equals(paramPackageInfo)) {
        return paramVarArgs[i];
      }
      i += 1;
    }
    return null;
  }
  
  private final zzp zza(String paramString)
  {
    try
    {
      PackageInfo localPackageInfo = zzbih.zza(zzb).zzb(paramString, 64);
      boolean bool = zzs.zzb(zzb);
      if (localPackageInfo == null) {
        paramString = "null pkg";
      }
      zzp localZzp;
      for (;;)
      {
        return zzp.zza(paramString);
        if (signatures.length != 1)
        {
          paramString = "single cert required";
        }
        else
        {
          paramString = new zzi(signatures[0].toByteArray());
          String str = packageName;
          localZzp = zzg.zza(str, paramString, bool);
          if ((!zza) || (applicationInfo == null) || ((applicationInfo.flags & 0x2) == 0) || ((bool) && (!zzazza))) {
            break;
          }
          paramString = "debuggable release cert app rejected";
        }
      }
      return localZzp;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      for (;;) {}
    }
    paramString = String.valueOf(paramString);
    if (paramString.length() != 0) {
      paramString = "no pkg ".concat(paramString);
    } else {
      paramString = new String("no pkg ");
    }
    return zzp.zza(paramString);
  }
  
  public static zzt zza(Context paramContext)
  {
    zzbq.zza(paramContext);
    try
    {
      if (zza == null)
      {
        zzg.zza(paramContext);
        zza = new zzt(paramContext);
      }
      return zza;
    }
    finally {}
  }
  
  @Hide
  public static boolean zza(PackageInfo paramPackageInfo, boolean paramBoolean)
  {
    if ((paramPackageInfo != null) && (signatures != null))
    {
      zzh[] arrayOfZzh;
      if (paramBoolean) {
        arrayOfZzh = zzk.zza;
      }
      for (;;)
      {
        paramPackageInfo = zza(paramPackageInfo, arrayOfZzh);
        break;
        arrayOfZzh = new zzh[1];
        arrayOfZzh[0] = zzk.zza[0];
      }
      if (paramPackageInfo != null) {
        return true;
      }
    }
    return false;
  }
  
  @Hide
  public final boolean zza(int paramInt)
  {
    String[] arrayOfString = zzbih.zza(zzb).zza(paramInt);
    Object localObject;
    int i;
    if ((arrayOfString != null) && (arrayOfString.length != 0))
    {
      localObject = null;
      i = arrayOfString.length;
      paramInt = 0;
    }
    while (paramInt < i)
    {
      zzp localZzp = zza(arrayOfString[paramInt]);
      localObject = localZzp;
      if (!zza)
      {
        paramInt += 1;
        localObject = localZzp;
        continue;
        localObject = zzp.zza("no pkgs");
      }
    }
    if (!zza) {
      if (zzb != null) {
        Log.d("GoogleCertificatesRslt", ((zzp)localObject).zzb(), zzb);
      } else {
        Log.d("GoogleCertificatesRslt", ((zzp)localObject).zzb());
      }
    }
    return zza;
  }
  
  @Hide
  public final boolean zza(PackageInfo paramPackageInfo)
  {
    if (paramPackageInfo == null) {
      return false;
    }
    if (zza(paramPackageInfo, false)) {
      return true;
    }
    if (zza(paramPackageInfo, true))
    {
      if (zzs.zzb(zzb)) {
        return true;
      }
      Log.w("GoogleSignatureVerifier", "Test-keys aren't accepted on this build.");
    }
    return false;
  }
}
