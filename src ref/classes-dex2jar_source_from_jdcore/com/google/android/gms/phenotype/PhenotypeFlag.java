package com.google.android.gms.phenotype;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Binder;
import android.os.Build.VERSION;
import android.os.UserManager;
import android.support.v4.content.PermissionChecker;
import android.util.Log;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.internal.zzdob;

@Deprecated
@KeepForSdk
public abstract class PhenotypeFlag<T>
{
  private static final Object zzb = new Object();
  @SuppressLint({"StaticFieldLeak"})
  private static Context zzc;
  private static boolean zzd = false;
  private static Boolean zze;
  final String zza;
  private final Factory zzf;
  private final String zzg;
  private final T zzh;
  private T zzi = null;
  
  private PhenotypeFlag(Factory paramFactory, String paramString, T paramT)
  {
    if ((Factory.zza(paramFactory) == null) && (Factory.zzb(paramFactory) == null)) {
      throw new IllegalArgumentException("Must pass a valid SharedPreferences file name or ContentProvider URI");
    }
    if ((Factory.zza(paramFactory) != null) && (Factory.zzb(paramFactory) != null)) {
      throw new IllegalArgumentException("Must pass one of SharedPreferences file name or ContentProvider URI");
    }
    zzf = paramFactory;
    String str1 = String.valueOf(Factory.zzc(paramFactory));
    String str2 = String.valueOf(paramString);
    if (str2.length() != 0) {
      str1 = str1.concat(str2);
    } else {
      str1 = new String(str1);
    }
    zzg = str1;
    paramFactory = String.valueOf(Factory.zzd(paramFactory));
    paramString = String.valueOf(paramString);
    if (paramString.length() != 0) {
      paramFactory = paramFactory.concat(paramString);
    } else {
      paramFactory = new String(paramFactory);
    }
    zza = paramFactory;
    zzh = paramT;
  }
  
  @KeepForSdk
  public static void maybeInit(Context paramContext)
  {
    zzdob.zzb(paramContext);
    if (zzc == null) {
      zzdob.zza(paramContext);
    }
    for (;;)
    {
      Context localContext;
      synchronized (zzb)
      {
        if ((Build.VERSION.SDK_INT < 24) || (!paramContext.isDeviceProtectedStorage()))
        {
          localContext = paramContext.getApplicationContext();
          if (localContext != null) {
            break label78;
          }
        }
        if (zzc != paramContext) {
          zze = null;
        }
        zzc = paramContext;
        zzd = false;
        return;
      }
      return;
      label78:
      paramContext = localContext;
    }
  }
  
  private static <V> V zza(zza<V> paramZza)
  {
    try
    {
      Object localObject = paramZza.zza();
      return localObject;
    }
    catch (SecurityException localSecurityException)
    {
      long l;
      for (;;) {}
    }
    l = Binder.clearCallingIdentity();
    try
    {
      paramZza = paramZza.zza();
      return paramZza;
    }
    finally
    {
      Binder.restoreCallingIdentity(l);
    }
  }
  
  static boolean zza(String paramString, boolean paramBoolean)
  {
    if (zzd()) {
      return ((Boolean)zza(new zzq(paramString, false))).booleanValue();
    }
    return false;
  }
  
  private static PhenotypeFlag<String> zzb(Factory paramFactory, String paramString1, String paramString2)
  {
    return new zzs(paramFactory, paramString1, paramString2);
  }
  
  @TargetApi(24)
  private final T zzb()
  {
    Object localObject;
    if (!zza("gms:phenotype:phenotype_flag:debug_bypass_phenotype", false))
    {
      if (Factory.zzb(zzf) != null)
      {
        localObject = (String)zza(new zzo(this, zza.zza(zzc.getContentResolver(), Factory.zzb(zzf))));
        if (localObject != null) {
          return zza((String)localObject);
        }
      }
      else if (Factory.zza(zzf) != null)
      {
        if ((Build.VERSION.SDK_INT >= 24) && (!zzc.isDeviceProtectedStorage()) && (!((UserManager)zzc.getSystemService(UserManager.class)).isUserUnlocked())) {
          return null;
        }
        localObject = zzc.getSharedPreferences(Factory.zza(zzf), 0);
        if (((SharedPreferences)localObject).contains(zza)) {
          return zza((SharedPreferences)localObject);
        }
      }
    }
    else
    {
      localObject = String.valueOf(zza);
      if (((String)localObject).length() != 0) {
        localObject = "Bypass reading Phenotype values for flag: ".concat((String)localObject);
      } else {
        localObject = new String("Bypass reading Phenotype values for flag: ");
      }
      Log.w("PhenotypeFlag", (String)localObject);
    }
    return null;
  }
  
  private final T zzc()
  {
    if ((!Factory.zzf(zzf)) && (zzd()))
    {
      String str = (String)zza(new zzp(this));
      if (str != null) {
        return zza(str);
      }
    }
    return null;
  }
  
  private static boolean zzd()
  {
    if (zze == null)
    {
      Context localContext = zzc;
      boolean bool = false;
      if (localContext != null)
      {
        if (PermissionChecker.checkCallingOrSelfPermission(zzc, "com.google.android.providers.gsf.permission.READ_GSERVICES") == 0) {
          bool = true;
        }
        zze = Boolean.valueOf(bool);
      }
      else
      {
        return false;
      }
    }
    return zze.booleanValue();
  }
  
  @KeepForSdk
  public T get()
  {
    if (zzc == null) {
      throw new IllegalStateException("Must call PhenotypeFlag.init() first");
    }
    Object localObject;
    if (Factory.zze(zzf))
    {
      localObject = zzc();
      if (localObject != null) {
        return localObject;
      }
      localObject = zzb();
      if (localObject != null) {
        return localObject;
      }
    }
    else
    {
      localObject = zzb();
      if (localObject != null) {
        return localObject;
      }
      localObject = zzc();
      if (localObject != null) {
        return localObject;
      }
    }
    return zzh;
  }
  
  public abstract T zza(SharedPreferences paramSharedPreferences);
  
  public abstract T zza(String paramString);
  
  @KeepForSdk
  public static class Factory
  {
    private final String zza;
    private final Uri zzb;
    private final String zzc;
    private final String zzd;
    private final boolean zze;
    private final boolean zzf;
    
    @KeepForSdk
    public Factory(Uri paramUri)
    {
      this(null, paramUri, "", "", false, false);
    }
    
    private Factory(String paramString1, Uri paramUri, String paramString2, String paramString3, boolean paramBoolean1, boolean paramBoolean2)
    {
      zza = paramString1;
      zzb = paramUri;
      zzc = paramString2;
      zzd = paramString3;
      zze = paramBoolean1;
      zzf = paramBoolean2;
    }
    
    @KeepForSdk
    public PhenotypeFlag<String> createFlag(String paramString1, String paramString2)
    {
      return PhenotypeFlag.zza(this, paramString1, paramString2);
    }
    
    @KeepForSdk
    public Factory withGservicePrefix(String paramString)
    {
      if (zze) {
        throw new IllegalStateException("Cannot set GServices prefix and skip GServices");
      }
      return new Factory(zza, zzb, paramString, zzd, zze, zzf);
    }
    
    @KeepForSdk
    public Factory withPhenotypePrefix(String paramString)
    {
      return new Factory(zza, zzb, zzc, paramString, zze, zzf);
    }
  }
  
  static abstract interface zza<V>
  {
    public abstract V zza();
  }
}
