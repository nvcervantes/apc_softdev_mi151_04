package com.google.android.gms.common.internal;

import android.content.Intent;
import android.net.Uri;
import android.net.Uri.Builder;
import android.support.annotation.Nullable;
import android.text.TextUtils;

@Hide
public final class zzak
{
  private static final Uri zza;
  private static final Uri zzb;
  
  static
  {
    Uri localUri = Uri.parse("https://plus.google.com/");
    zza = localUri;
    zzb = localUri.buildUpon().appendPath("circles").appendPath("find").build();
  }
  
  public static Intent zza()
  {
    Intent localIntent = new Intent("com.google.android.clockwork.home.UPDATE_ANDROID_WEAR_ACTION");
    localIntent.setPackage("com.google.android.wearable.app");
    return localIntent;
  }
  
  public static Intent zza(String paramString)
  {
    paramString = Uri.fromParts("package", paramString, null);
    Intent localIntent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
    localIntent.setData(paramString);
    return localIntent;
  }
  
  public static Intent zza(String paramString1, @Nullable String paramString2)
  {
    Intent localIntent = new Intent("android.intent.action.VIEW");
    paramString1 = Uri.parse("market://details").buildUpon().appendQueryParameter("id", paramString1);
    if (!TextUtils.isEmpty(paramString2)) {
      paramString1.appendQueryParameter("pcampaignid", paramString2);
    }
    localIntent.setData(paramString1.build());
    localIntent.setPackage("com.android.vending");
    localIntent.addFlags(524288);
    return localIntent;
  }
}