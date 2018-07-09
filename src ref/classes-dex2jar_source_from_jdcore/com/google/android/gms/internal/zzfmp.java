package com.google.android.gms.internal;

import java.io.IOException;

public final class zzfmp
  extends zzflm<zzfmp>
  implements Cloneable
{
  private int zza = 0;
  private String zzb = "";
  private String zzc = "";
  
  public zzfmp()
  {
    zzax = null;
    zzay = -1;
  }
  
  private zzfmp zzb()
  {
    try
    {
      zzfmp localZzfmp = (zzfmp)super.zzc();
      return localZzfmp;
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
    if (!(paramObject instanceof zzfmp)) {
      return false;
    }
    paramObject = (zzfmp)paramObject;
    if (zza != zza) {
      return false;
    }
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
    int i1 = zza;
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
    return ((((527 + n) * 31 + i1) * 31 + i) * 31 + j) * 31 + k;
  }
  
  protected final int zza()
  {
    int j = super.zza();
    int i = j;
    if (zza != 0) {
      i = j + zzflk.zzb(1, zza);
    }
    j = i;
    if (zzb != null)
    {
      j = i;
      if (!zzb.equals("")) {
        j = i + zzflk.zzb(2, zzb);
      }
    }
    i = j;
    if (zzc != null)
    {
      i = j;
      if (!zzc.equals("")) {
        i = j + zzflk.zzb(3, zzc);
      }
    }
    return i;
  }
  
  public final void zza(zzflk paramZzflk)
    throws IOException
  {
    if (zza != 0) {
      paramZzflk.zza(1, zza);
    }
    if ((zzb != null) && (!zzb.equals(""))) {
      paramZzflk.zza(2, zzb);
    }
    if ((zzc != null) && (!zzc.equals(""))) {
      paramZzflk.zza(3, zzc);
    }
    super.zza(paramZzflk);
  }
}
