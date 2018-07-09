package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.common.internal.zzbg;
import java.util.Arrays;

public final class zzh<O extends Api.ApiOptions>
{
  private final boolean zza;
  private final int zzb;
  private final Api<O> zzc;
  private final O zzd;
  
  private zzh(Api<O> paramApi)
  {
    zza = true;
    zzc = paramApi;
    zzd = null;
    zzb = System.identityHashCode(this);
  }
  
  private zzh(Api<O> paramApi, O paramO)
  {
    zza = false;
    zzc = paramApi;
    zzd = paramO;
    zzb = Arrays.hashCode(new Object[] { zzc, zzd });
  }
  
  public static <O extends Api.ApiOptions> zzh<O> zza(Api<O> paramApi)
  {
    return new zzh(paramApi);
  }
  
  public static <O extends Api.ApiOptions> zzh<O> zza(Api<O> paramApi, O paramO)
  {
    return new zzh(paramApi, paramO);
  }
  
  public final boolean equals(Object paramObject)
  {
    if (paramObject == this) {
      return true;
    }
    if (!(paramObject instanceof zzh)) {
      return false;
    }
    paramObject = (zzh)paramObject;
    return (!zza) && (!zza) && (zzbg.zza(zzc, zzc)) && (zzbg.zza(zzd, zzd));
  }
  
  public final int hashCode()
  {
    return zzb;
  }
  
  public final String zza()
  {
    return zzc.zzd();
  }
}
