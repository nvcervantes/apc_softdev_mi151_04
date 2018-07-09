package com.google.android.gms.internal;

import java.io.IOException;

public final class zzfmt
  extends zzflm<zzfmt>
  implements Cloneable
{
  private int zza = -1;
  private int zzb = 0;
  
  public zzfmt()
  {
    zzax = null;
    zzay = -1;
  }
  
  private zzfmt zzb()
  {
    try
    {
      zzfmt localZzfmt = (zzfmt)super.zzc();
      return localZzfmt;
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      throw new AssertionError(localCloneNotSupportedException);
    }
  }
  
  private final zzfmt zzb(zzflj paramZzflj)
    throws IOException
  {
    for (;;)
    {
      int k = paramZzflj.zza();
      if (k == 0) {
        break;
      }
      if (k != 8)
      {
        if (k != 16)
        {
          if (super.zza(paramZzflj, k)) {
            continue;
          }
          return this;
        }
        j = paramZzflj.zzm();
        i = j;
      }
      try
      {
        m = paramZzflj.zzc();
        if (m == 100) {
          break label188;
        }
        switch (m)
        {
        }
      }
      catch (IllegalArgumentException localIllegalArgumentException)
      {
        for (;;)
        {
          int m;
          StringBuilder localStringBuilder;
          continue;
        }
      }
      int i = j;
      localStringBuilder = new StringBuilder(45);
      i = j;
      localStringBuilder.append(m);
      i = j;
      localStringBuilder.append(" is not a valid enum MobileSubtype");
      i = j;
      throw new IllegalArgumentException(localStringBuilder.toString());
      label188:
      i = j;
      zzb = m;
      continue;
      int j = paramZzflj.zzm();
      i = j;
      m = paramZzflj.zzc();
      switch (m)
      {
      case -1: 
        break;
      case 0: 
      case 1: 
      case 2: 
      case 3: 
      case 4: 
      case 5: 
      case 6: 
      case 7: 
      case 8: 
      case 9: 
      case 10: 
      case 11: 
      case 12: 
      case 13: 
      case 14: 
      case 15: 
      case 16: 
      case 17: 
        i = j;
        zza = m;
        continue;
        i = j;
        localStringBuilder = new StringBuilder(43);
        i = j;
        localStringBuilder.append(m);
        i = j;
        localStringBuilder.append(" is not a valid enum NetworkType");
        i = j;
        throw new IllegalArgumentException(localStringBuilder.toString());
        paramZzflj.zze(i);
        zza(paramZzflj, k);
      }
    }
    return this;
  }
  
  public final boolean equals(Object paramObject)
  {
    if (paramObject == this) {
      return true;
    }
    if (!(paramObject instanceof zzfmt)) {
      return false;
    }
    paramObject = (zzfmt)paramObject;
    if (zza != zza) {
      return false;
    }
    if (zzb != zzb) {
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
    int j = getClass().getName().hashCode();
    int k = zza;
    int m = zzb;
    int i;
    if ((zzax != null) && (!zzax.zzb())) {
      i = zzax.hashCode();
    } else {
      i = 0;
    }
    return (((527 + j) * 31 + k) * 31 + m) * 31 + i;
  }
  
  protected final int zza()
  {
    int j = super.zza();
    int i = j;
    if (zza != -1) {
      i = j + zzflk.zzb(1, zza);
    }
    j = i;
    if (zzb != 0) {
      j = i + zzflk.zzb(2, zzb);
    }
    return j;
  }
  
  public final void zza(zzflk paramZzflk)
    throws IOException
  {
    if (zza != -1) {
      paramZzflk.zza(1, zza);
    }
    if (zzb != 0) {
      paramZzflk.zza(2, zzb);
    }
    super.zza(paramZzflk);
  }
}
