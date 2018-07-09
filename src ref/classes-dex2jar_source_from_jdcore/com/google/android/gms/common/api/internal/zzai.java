package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.data.DataHolder;

public abstract class zzai<L>
  implements zzcl<L>
{
  private final DataHolder zza;
  
  protected zzai(DataHolder paramDataHolder)
  {
    zza = paramDataHolder;
  }
  
  public final void zza()
  {
    if (zza != null) {
      zza.close();
    }
  }
  
  public final void zza(L paramL)
  {
    zza(paramL, zza);
  }
  
  protected abstract void zza(L paramL, DataHolder paramDataHolder);
}
