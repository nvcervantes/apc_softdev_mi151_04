package com.google.android.gms.internal;

import java.io.IOException;

public final class zzfms
  extends zzflm<zzfms>
  implements Cloneable
{
  private static volatile zzfms[] zza;
  private String zzb = "";
  private String zzc = "";
  
  public zzfms()
  {
    zzax = null;
    zzay = -1;
  }
  
  public static zzfms[] zzb()
  {
    if (zza == null) {
      synchronized (zzflq.zzb)
      {
        if (zza == null) {
          zza = new zzfms[0];
        }
      }
    }
    return zza;
  }
  
  private zzfms zzg()
  {
    try
    {
      zzfms localZzfms = (zzfms)super.zzc();
      return localZzfms;
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      throw new AssertionError(localCloneNotSupportedException);
    }
  }
  
  public final boolean equals(Object paramObject)
  {
    if (paramObject == this) {
      return true;
    }
    if (!(paramObject instanceof zzfms)) {
      return false;
    }
    paramObject = (zzfms)paramObject;
    if (zzb == null)
    {
      if (zzb != null) {
        return false;
      }
    }
    else if (!zzb.equals(zzb)) {
      return false;
    }
    if (zzc == null)
    {
      if (zzc != null) {
        return false;
      }
    }
    else if (!zzc.equals(zzc)) {
      return false;
    }
    if ((zzax != null) && (!zzax.zzb())) {
      return zzax.equals(zzax);
    }
    if (zzax != null) {
      return zzax.zzb();
    }
    return true;
  }
  
  public final int hashCode()
  {
    int n = getClass().getName().hashCode();
    String str = zzb;
    int m = 0;
    int i;
    if (str == null) {
      i = 0;
    } else {
      i = zzb.hashCode();
    }
    int j;
    if (zzc == null) {
      j = 0;
    } else {
      j = zzc.hashCode();
    }
    int k = m;
    if (zzax != null) {
      if (zzax.zzb()) {
        k = m;
      } else {
        k = zzax.hashCode();
      }
    }
    return (((527 + n) * 31 + i) * 31 + j) * 31 + k;
  }
  
  protected final int zza()
  {
    int j = super.zza();
    int i = j;
    if (zzb != null)
    {
      i = j;
      if (!zzb.equals("")) {
        i = j + zzflk.zzb(1, zzb);
      }
    }
    j = i;
    if (zzc != null)
    {
      j = i;
      if (!zzc.equals("")) {
        j = i + zzflk.zzb(2, zzc);
      }
    }
    return j;
  }
  
  public final void zza(zzflk paramZzflk)
    throws IOException
  {
    if ((zzb != null) && (!zzb.equals(""))) {
      paramZzflk.zza(1, zzb);
    }
    if ((zzc != null) && (!zzc.equals(""))) {
      paramZzflk.zza(2, zzc);
    }
    super.zza(paramZzflk);
  }
}
