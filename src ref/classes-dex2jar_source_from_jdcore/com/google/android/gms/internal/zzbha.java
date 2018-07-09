package com.google.android.gms.internal;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.common.internal.zzr;

public final class zzbha
  extends zzab<zzbhd>
{
  public zzbha(Context paramContext, Looper paramLooper, zzr paramZzr, GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    super(paramContext, paramLooper, 39, paramZzr, paramConnectionCallbacks, paramOnConnectionFailedListener);
  }
  
  public final String zza()
  {
    return "com.google.android.gms.common.service.START";
  }
  
  protected final String zzb()
  {
    return "com.google.android.gms.common.internal.service.ICommonService";
  }
}
