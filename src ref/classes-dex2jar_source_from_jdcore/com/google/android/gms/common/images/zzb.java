package com.google.android.gms.common.images;

import android.net.Uri;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.internal.zzbg;
import java.util.Arrays;

@Hide
final class zzb
{
  public final Uri zza;
  
  public zzb(Uri paramUri)
  {
    zza = paramUri;
  }
  
  public final boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof zzb)) {
      return false;
    }
    if (this == paramObject) {
      return true;
    }
    return zzbg.zza(zza, zza);
  }
  
  public final int hashCode()
  {
    return Arrays.hashCode(new Object[] { zza });
  }
}
