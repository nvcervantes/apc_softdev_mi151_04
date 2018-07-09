package com.google.android.gms.common.api.internal;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import com.google.android.gms.common.internal.zzbq;

public final class zzce
{
  private final Object zza;
  
  public zzce(Activity paramActivity)
  {
    zzbq.zza(paramActivity, "Activity must not be null");
    zza = paramActivity;
  }
  
  public final boolean zza()
  {
    return zza instanceof FragmentActivity;
  }
  
  public final boolean zzb()
  {
    return zza instanceof Activity;
  }
  
  public final Activity zzc()
  {
    return (Activity)zza;
  }
  
  public final FragmentActivity zzd()
  {
    return (FragmentActivity)zza;
  }
}
