package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.os.Looper;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.Result;

public final class zzbw<O extends Api.ApiOptions>
  extends zzak
{
  private final GoogleApi<O> zza;
  
  public zzbw(GoogleApi<O> paramGoogleApi)
  {
    super("Method is not supported by connectionless client. APIs supporting connectionless client must not call this method.");
    zza = paramGoogleApi;
  }
  
  public final <A extends Api.zzb, R extends Result, T extends zzm<R, A>> T zza(@NonNull T paramT)
  {
    return zza.zza(paramT);
  }
  
  public final void zza(zzdh paramZzdh) {}
  
  public final Context zzb()
  {
    return zza.zzg();
  }
  
  public final <A extends Api.zzb, T extends zzm<? extends Result, A>> T zzb(@NonNull T paramT)
  {
    return zza.zzb(paramT);
  }
  
  public final void zzb(zzdh paramZzdh) {}
  
  public final Looper zzc()
  {
    return zza.zzf();
  }
}
