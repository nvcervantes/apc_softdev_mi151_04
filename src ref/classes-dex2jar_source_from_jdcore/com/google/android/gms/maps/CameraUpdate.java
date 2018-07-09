package com.google.android.gms.maps;

import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.dynamic.IObjectWrapper;

public final class CameraUpdate
{
  private final IObjectWrapper zza;
  
  CameraUpdate(IObjectWrapper paramIObjectWrapper)
  {
    zza = ((IObjectWrapper)zzbq.zza(paramIObjectWrapper));
  }
  
  @Hide
  public final IObjectWrapper zza()
  {
    return zza;
  }
}
