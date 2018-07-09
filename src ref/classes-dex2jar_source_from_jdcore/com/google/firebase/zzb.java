package com.google.firebase;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzda;
import com.google.android.gms.common.internal.Hide;

@Hide
public final class zzb
  implements zzda
{
  public zzb() {}
  
  public final Exception zza(Status paramStatus)
  {
    if (paramStatus.getStatusCode() == 8) {
      return new FirebaseException(paramStatus.zza());
    }
    return new FirebaseApiNotAvailableException(paramStatus.zza());
  }
}
