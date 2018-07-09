package com.google.android.gms.common;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.internal.zzak;
import com.google.android.gms.common.util.zzj;
import com.google.android.gms.internal.zzbig;
import com.google.android.gms.internal.zzbih;

@Hide
public class zzf
{
  public static final String GOOGLE_PLAY_SERVICES_PACKAGE = "com.google.android.gms";
  public static final int GOOGLE_PLAY_SERVICES_VERSION_CODE = zzs.GOOGLE_PLAY_SERVICES_VERSION_CODE;
  private static final zzf zza = new zzf();
  
  zzf() {}
  
  public static int zza(Context paramContext, int paramInt)
  {
    int i = zzs.zza(paramContext, paramInt);
    paramInt = i;
    if (zzs.zzc(paramContext, i)) {
      paramInt = 18;
    }
    return paramInt;
  }
  
  @Nullable
  @Hide
  public static Intent zza(Context paramContext, int paramInt, @Nullable String paramString)
  {
    switch (paramInt)
    {
    default: 
      return null;
    case 3: 
      return zzak.zza("com.google.android.gms");
    }
    if ((paramContext != null) && (zzj.zzb(paramContext))) {
      return zzak.zza();
    }
    return zzak.zza("com.google.android.gms", zza(paramContext, paramString));
  }
  
  public static zzf zza()
  {
    return zza;
  }
  
  private static String zza(@Nullable Context paramContext, @Nullable String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("gcore_");
    localStringBuilder.append(GOOGLE_PLAY_SERVICES_VERSION_CODE);
    localStringBuilder.append("-");
    if (!TextUtils.isEmpty(paramString)) {
      localStringBuilder.append(paramString);
    }
    localStringBuilder.append("-");
    if (paramContext != null) {
      localStringBuilder.append(paramContext.getPackageName());
    }
    localStringBuilder.append("-");
    if (paramContext != null) {}
    try
    {
      localStringBuilder.append(zzazzbgetPackageName0versionCode);
      return localStringBuilder.toString();
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      for (;;) {}
    }
  }
  
  @Hide
  public static void zzb(Context paramContext)
    throws GooglePlayServicesRepairableException, GooglePlayServicesNotAvailableException
  {
    zzs.zza(paramContext);
  }
  
  @Hide
  public static boolean zzb(Context paramContext, int paramInt)
  {
    return zzs.zzc(paramContext, paramInt);
  }
  
  @Hide
  public static void zzc(Context paramContext)
  {
    zzs.zzc(paramContext);
  }
  
  @Hide
  public static int zzd(Context paramContext)
  {
    return zzs.zzd(paramContext);
  }
  
  @Nullable
  public PendingIntent getErrorResolutionPendingIntent(Context paramContext, int paramInt1, int paramInt2)
  {
    return zza(paramContext, paramInt1, paramInt2, null);
  }
  
  public String getErrorString(int paramInt)
  {
    return zzs.getErrorString(paramInt);
  }
  
  public int isGooglePlayServicesAvailable(Context paramContext)
  {
    return zza(paramContext, -1);
  }
  
  public boolean isUserResolvableError(int paramInt)
  {
    return zzs.isUserRecoverableError(paramInt);
  }
  
  @Nullable
  @Hide
  public final PendingIntent zza(Context paramContext, int paramInt1, int paramInt2, @Nullable String paramString)
  {
    paramString = zza(paramContext, paramInt1, paramString);
    if (paramString == null) {
      return null;
    }
    return PendingIntent.getActivity(paramContext, paramInt2, paramString, 268435456);
  }
  
  @Deprecated
  @Nullable
  @Hide
  public final Intent zza(int paramInt)
  {
    return zza(null, paramInt, null);
  }
}
