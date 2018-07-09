package com.google.android.gms.phenotype;

import android.net.Uri;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions.NoOptions;
import com.google.android.gms.common.api.Api.zza;
import com.google.android.gms.common.api.Api.zzf;
import com.google.android.gms.internal.zzcvy;
import com.google.android.gms.internal.zzcvz;

@KeepForSdk
public final class Phenotype
{
  private static final Api.zzf<zzcvz> zza = new Api.zzf();
  private static final Api.zza<zzcvz, Api.ApiOptions.NoOptions> zzb = new zzl();
  @Deprecated
  private static Api<Api.ApiOptions.NoOptions> zzc = new Api("Phenotype.API", zzb, zza);
  @Deprecated
  private static zzm zzd = new zzcvy();
  
  private Phenotype() {}
  
  @KeepForSdk
  public static Uri getContentProviderUri(String paramString)
  {
    paramString = String.valueOf(Uri.encode(paramString));
    if (paramString.length() != 0) {
      paramString = "content://com.google.android.gms.phenotype/".concat(paramString);
    } else {
      paramString = new String("content://com.google.android.gms.phenotype/");
    }
    return Uri.parse(paramString);
  }
}
