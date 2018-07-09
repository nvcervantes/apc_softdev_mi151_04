package com.google.android.gms.common.api.internal;

import android.support.annotation.BinderThread;
import com.google.android.gms.internal.zzcyo;
import com.google.android.gms.internal.zzcyw;
import java.lang.ref.WeakReference;

final class zzav
  extends zzcyo
{
  private final WeakReference<zzao> zza;
  
  zzav(zzao paramZzao)
  {
    zza = new WeakReference(paramZzao);
  }
  
  @BinderThread
  public final void zza(zzcyw paramZzcyw)
  {
    zzao localZzao = (zzao)zza.get();
    if (localZzao == null) {
      return;
    }
    zzao.zzc(localZzao).zza(new zzaw(this, localZzao, localZzao, paramZzcyw));
  }
}
