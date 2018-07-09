package com.google.android.gms.internal;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.zzd;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.location.places.PlacesStatusCodes;

@Deprecated
@Hide
public final class zzchx
  extends zzd<zzchw>
  implements Result
{
  private final Status zzb;
  
  @Hide
  public zzchx(DataHolder paramDataHolder)
  {
    this(paramDataHolder, PlacesStatusCodes.zza(paramDataHolder.zzb()));
  }
  
  @Hide
  private zzchx(DataHolder paramDataHolder, Status paramStatus)
  {
    super(paramDataHolder, zzchw.CREATOR);
    boolean bool;
    if ((paramDataHolder != null) && (paramDataHolder.zzb() != paramStatus.getStatusCode())) {
      bool = false;
    } else {
      bool = true;
    }
    zzbq.zzb(bool);
    zzb = paramStatus;
  }
  
  public final Status getStatus()
  {
    return zzb;
  }
}
