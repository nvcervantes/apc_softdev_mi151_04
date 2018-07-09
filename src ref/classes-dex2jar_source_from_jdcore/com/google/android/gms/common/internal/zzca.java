package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.res.Resources;
import com.google.android.gms.R.string;

@Hide
public final class zzca
{
  private final Resources zza;
  private final String zzb;
  
  public zzca(Context paramContext)
  {
    zzbq.zza(paramContext);
    zza = paramContext.getResources();
    zzb = zza.getResourcePackageName(R.string.common_google_play_services_unknown_issue);
  }
  
  public final String zza(String paramString)
  {
    int i = zza.getIdentifier(paramString, "string", zzb);
    if (i == 0) {
      return null;
    }
    return zza.getString(i);
  }
}
