package com.google.android.gms.internal;

import java.io.IOException;
import java.util.Arrays;

public final class zzfmq
  extends zzflm<zzfmq>
  implements Cloneable
{
  private byte[] zza = zzflv.zzh;
  private String zzb = "";
  private byte[][] zzc = zzflv.zzg;
  private boolean zzd = false;
  
  public zzfmq()
  {
    zzax = null;
    zzay = -1;
  }
  
  private zzfmq zzb()
  {
    try
    {
      zzfmq localZzfmq = (zzfmq)super.zzc();
      if ((zzc != null) && (zzc.length > 0)) {
        zzc = ((byte[][])zzc.clone());
      }
      return localZzfmq;
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
    if (!(paramObject instanceof zzfmq)) {
      return false;
    }
    paramObject = (zzfmq)paramObject;
    if (!Arrays.equals(zza, zza)) {
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
    if (!zzflq.zza(zzc, zzc)) {
      return false;
    }
    if (zzd != zzd) {
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
    int i1 = Arrays.hashCode(zza);
    String str = zzb;
    int m = 0;
    int i;
    if (str == null) {
      i = 0;
    } else {
      i = zzb.hashCode();
    }
    int i2 = zzflq.zza(zzc);
    int j;
    if (zzd) {
      j = 1231;
    } else {
      j = 1237;
    }
    int k = m;
    if (zzax != null) {
      if (zzax.zzb()) {
        k = m;
      } else {
        k = zzax.hashCode();
      }
    }
    return (((((527 + n) * 31 + i1) * 31 + i) * 31 + i2) * 31 + j) * 31 + k;
  }
  
  protected final int zza()
  {
    int j = super.zza();
    int i = j;
    if (!Arrays.equals(zza, zzflv.zzh)) {
      i = j + zzflk.zzb(1, zza);
    }
    j = i;
    if (zzc != null)
    {
      j = i;
      if (zzc.length > 0)
      {
        j = 0;
        int k = 0;
        int n;
        for (int m = k; j < zzc.length; m = n)
        {
          byte[] arrayOfByte = zzc[j];
          int i1 = k;
          n = m;
          if (arrayOfByte != null)
          {
            n = m + 1;
            i1 = k + zzflk.zzb(arrayOfByte);
          }
          j += 1;
          k = i1;
        }
        j = i + k + m * 1;
      }
    }
    i = j;
    if (zzd) {
      i = j + (zzflk.zzb(3) + 1);
    }
    j = i;
    if (zzb != null)
    {
      j = i;
      if (!zzb.equals("")) {
        j = i + zzflk.zzb(4, zzb);
      }
    }
    return j;
  }
  
  public final void zza(zzflk paramZzflk)
    throws IOException
  {
    if (!Arrays.equals(zza, zzflv.zzh)) {
      paramZzflk.zza(1, zza);
    }
    if ((zzc != null) && (zzc.length > 0))
    {
      int i = 0;
      while (i < zzc.length)
      {
        byte[] arrayOfByte = zzc[i];
        if (arrayOfByte != null) {
          paramZzflk.zza(2, arrayOfByte);
        }
        i += 1;
      }
    }
    if (zzd) {
      paramZzflk.zza(3, zzd);
    }
    if ((zzb != null) && (!zzb.equals(""))) {
      paramZzflk.zza(4, zzb);
    }
    super.zza(paramZzflk);
  }
}
