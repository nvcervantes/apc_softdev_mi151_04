package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.SimpleArrayMap;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.R.string;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.util.zzj;
import com.google.android.gms.internal.zzbig;
import com.google.android.gms.internal.zzbih;

public final class zzu
{
  private static final SimpleArrayMap<String, String> zza = new SimpleArrayMap();
  
  public static String zza(Context paramContext)
  {
    return paramContext.getResources().getString(R.string.common_google_play_services_notification_channel_name);
  }
  
  @Nullable
  public static String zza(Context paramContext, int paramInt)
  {
    Resources localResources = paramContext.getResources();
    if (paramInt != 20)
    {
      switch (paramInt)
      {
      default: 
        switch (paramInt)
        {
        default: 
          paramContext = new StringBuilder(33);
          paramContext.append("Unexpected error code ");
          paramContext.append(paramInt);
          paramContext = paramContext.toString();
        }
      case 11: 
      case 10: 
      case 9: 
      case 8: 
        for (;;)
        {
          Log.e("GoogleApiAvailability", paramContext);
          return null;
          Log.e("GoogleApiAvailability", "The specified account could not be signed in.");
          return zza(paramContext, "common_google_play_services_sign_in_failed_title");
          paramContext = "One of the API components you attempted to connect to is not available.";
          continue;
          paramContext = "The application is not licensed to the user.";
          continue;
          paramContext = "Developer error occurred. Please see logs for detailed information";
          continue;
          paramContext = "Google Play services is invalid. Cannot recover.";
          continue;
          paramContext = "Internal error occurred. Please see logs for detailed information";
        }
      case 7: 
        Log.e("GoogleApiAvailability", "Network error occurred. Please retry request later.");
        return zza(paramContext, "common_google_play_services_network_error_title");
      case 5: 
        Log.e("GoogleApiAvailability", "An invalid account was specified when connecting. Please provide a valid account.");
        return zza(paramContext, "common_google_play_services_invalid_account_title");
      case 4: 
      case 6: 
        return null;
      case 3: 
        return localResources.getString(R.string.common_google_play_services_enable_title);
      case 2: 
        return localResources.getString(R.string.common_google_play_services_update_title);
      }
      return localResources.getString(R.string.common_google_play_services_install_title);
    }
    Log.e("GoogleApiAvailability", "The current user profile is restricted and could not use authenticated features.");
    return zza(paramContext, "common_google_play_services_restricted_profile_title");
  }
  
  @Nullable
  private static String zza(Context paramContext, String paramString)
  {
    synchronized (zza)
    {
      String str = (String)zza.get(paramString);
      if (str != null) {
        return str;
      }
      paramContext = GooglePlayServicesUtil.getRemoteResource(paramContext);
      if (paramContext == null) {
        return null;
      }
      int i = paramContext.getIdentifier(paramString, "string", "com.google.android.gms");
      if (i == 0)
      {
        paramContext = String.valueOf(paramString);
        if (paramContext.length() != 0) {
          paramContext = "Missing resource: ".concat(paramContext);
        } else {
          paramContext = new String("Missing resource: ");
        }
        Log.w("GoogleApiAvailability", paramContext);
        return null;
      }
      paramContext = paramContext.getString(i);
      if (TextUtils.isEmpty(paramContext))
      {
        paramContext = String.valueOf(paramString);
        if (paramContext.length() != 0) {
          paramContext = "Got empty resource: ".concat(paramContext);
        } else {
          paramContext = new String("Got empty resource: ");
        }
        Log.w("GoogleApiAvailability", paramContext);
        return null;
      }
      zza.put(paramString, paramContext);
      return paramContext;
    }
  }
  
  private static String zza(Context paramContext, String paramString1, String paramString2)
  {
    Resources localResources = paramContext.getResources();
    paramString1 = zza(paramContext, paramString1);
    paramContext = paramString1;
    if (paramString1 == null) {
      paramContext = localResources.getString(R.string.common_google_play_services_unknown_issue);
    }
    return String.format(getConfigurationlocale, paramContext, new Object[] { paramString2 });
  }
  
  private static String zzb(Context paramContext)
  {
    String str1 = paramContext.getPackageName();
    try
    {
      String str2 = zzbih.zza(paramContext).zzb(str1).toString();
      return str2;
    }
    catch (PackageManager.NameNotFoundException|NullPointerException localNameNotFoundException)
    {
      for (;;) {}
    }
    paramContext = getApplicationInfoname;
    if (TextUtils.isEmpty(paramContext)) {
      return str1;
    }
    return paramContext;
  }
  
  @NonNull
  public static String zzb(Context paramContext, int paramInt)
  {
    String str1;
    if (paramInt == 6) {
      str1 = zza(paramContext, "common_google_play_services_resolution_required_title");
    } else {
      str1 = zza(paramContext, paramInt);
    }
    String str2 = str1;
    if (str1 == null) {
      str2 = paramContext.getResources().getString(R.string.common_google_play_services_notification_ticker);
    }
    return str2;
  }
  
  @NonNull
  public static String zzc(Context paramContext, int paramInt)
  {
    Resources localResources = paramContext.getResources();
    String str = zzb(paramContext);
    if (paramInt != 5)
    {
      if (paramInt != 7)
      {
        if (paramInt != 9)
        {
          if (paramInt != 20)
          {
            switch (paramInt)
            {
            default: 
              switch (paramInt)
              {
              default: 
                return localResources.getString(R.string.common_google_play_services_unknown_issue, new Object[] { str });
              case 18: 
                return localResources.getString(R.string.common_google_play_services_updating_text, new Object[] { str });
              case 17: 
                return zza(paramContext, "common_google_play_services_sign_in_failed_text", str);
              }
              return zza(paramContext, "common_google_play_services_api_unavailable_text", str);
            case 3: 
              return localResources.getString(R.string.common_google_play_services_enable_text, new Object[] { str });
            case 2: 
              if (zzj.zzb(paramContext)) {
                return localResources.getString(R.string.common_google_play_services_wear_update_text);
              }
              return localResources.getString(R.string.common_google_play_services_update_text, new Object[] { str });
            }
            return localResources.getString(R.string.common_google_play_services_install_text, new Object[] { str });
          }
          return zza(paramContext, "common_google_play_services_restricted_profile_text", str);
        }
        return localResources.getString(R.string.common_google_play_services_unsupported_text, new Object[] { str });
      }
      return zza(paramContext, "common_google_play_services_network_error_text", str);
    }
    return zza(paramContext, "common_google_play_services_invalid_account_text", str);
  }
  
  @NonNull
  public static String zzd(Context paramContext, int paramInt)
  {
    if (paramInt == 6) {
      return zza(paramContext, "common_google_play_services_resolution_required_text", zzb(paramContext));
    }
    return zzc(paramContext, paramInt);
  }
  
  @NonNull
  public static String zze(Context paramContext, int paramInt)
  {
    paramContext = paramContext.getResources();
    switch (paramInt)
    {
    default: 
      paramInt = 17039370;
    }
    for (;;)
    {
      return paramContext.getString(paramInt);
      paramInt = R.string.common_google_play_services_enable_button;
      continue;
      paramInt = R.string.common_google_play_services_update_button;
      continue;
      paramInt = R.string.common_google_play_services_install_button;
    }
  }
}
