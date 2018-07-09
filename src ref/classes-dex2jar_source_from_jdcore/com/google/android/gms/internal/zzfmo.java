package com.google.android.gms.internal;

import java.io.IOException;

public final class zzfmo
  extends zzflm<zzfmo>
  implements Cloneable
{
  private String[] zza = zzflv.zzf;
  private String[] zzb = zzflv.zzf;
  private int[] zzc = zzflv.zza;
  private long[] zzd = zzflv.zzb;
  private long[] zze = zzflv.zzb;
  
  public zzfmo()
  {
    zzax = null;
    zzay = -1;
  }
  
  private zzfmo zzb()
  {
    try
    {
      zzfmo localZzfmo = (zzfmo)super.zzc();
      if ((zza != null) && (zza.length > 0)) {
        zza = ((String[])zza.clone());
      }
      if ((zzb != null) && (zzb.length > 0)) {
        zzb = ((String[])zzb.clone());
      }
      if ((zzc != null) && (zzc.length > 0)) {
        zzc = ((int[])zzc.clone());
      }
      if ((zzd != null) && (zzd.length > 0)) {
        zzd = ((long[])zzd.clone());
      }
      if ((zze != null) && (zze.length > 0)) {
        zze = ((long[])zze.clone());
      }
      return localZzfmo;
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
    if (!(paramObject instanceof zzfmo)) {
      return false;
    }
    paramObject = (zzfmo)paramObject;
    if (!zzflq.zza(zza, zza)) {
      return false;
    }
    if (!zzflq.zza(zzb, zzb)) {
      return false;
    }
    if (!zzflq.zza(zzc, zzc)) {
      return false;
    }
    if (!zzflq.zza(zzd, zzd)) {
      return false;
    }
    if (!zzflq.zza(zze, zze)) {
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
    int k = zzflq.zza(zza);
    int m = zzflq.zza(zzb);
    int n = zzflq.zza(zzc);
    int i1 = zzflq.zza(zzd);
    int i2 = zzflq.zza(zze);
    int i;
    if ((zzax != null) && (!zzax.zzb())) {
      i = zzax.hashCode();
    } else {
      i = 0;
    }
    return ((((((527 + j) * 31 + k) * 31 + m) * 31 + n) * 31 + i1) * 31 + i2) * 31 + i;
  }
  
  protected final int zza()
  {
    int i1 = super.zza();
    Object localObject = zza;
    int i2 = 0;
    int i = i1;
    int m;
    int k;
    int n;
    if (localObject != null)
    {
      i = i1;
      if (zza.length > 0)
      {
        m = 0;
        i = m;
        k = i;
        j = i;
        i = m;
        while (i < zza.length)
        {
          localObject = zza[i];
          n = j;
          m = k;
          if (localObject != null)
          {
            m = k + 1;
            n = j + zzflk.zza((String)localObject);
          }
          i += 1;
          j = n;
          k = m;
        }
        i = i1 + j + k * 1;
      }
    }
    int j = i;
    if (zzb != null)
    {
      j = i;
      if (zzb.length > 0)
      {
        n = 0;
        j = n;
        m = j;
        k = j;
        j = n;
        while (j < zzb.length)
        {
          localObject = zzb[j];
          i1 = k;
          n = m;
          if (localObject != null)
          {
            n = m + 1;
            i1 = k + zzflk.zza((String)localObject);
          }
          j += 1;
          k = i1;
          m = n;
        }
        j = i + k + m * 1;
      }
    }
    i = j;
    if (zzc != null)
    {
      i = j;
      if (zzc.length > 0)
      {
        i = 0;
        k = i;
        while (i < zzc.length)
        {
          k += zzflk.zza(zzc[i]);
          i += 1;
        }
        i = j + k + zzc.length * 1;
      }
    }
    j = i;
    if (zzd != null)
    {
      j = i;
      if (zzd.length > 0)
      {
        j = 0;
        k = j;
        while (j < zzd.length)
        {
          k += zzflk.zza(zzd[j]);
          j += 1;
        }
        j = i + k + zzd.length * 1;
      }
    }
    i = j;
    if (zze != null)
    {
      i = j;
      if (zze.length > 0)
      {
        k = 0;
        i = i2;
        while (i < zze.length)
        {
          k += zzflk.zza(zze[i]);
          i += 1;
        }
        i = j + k + 1 * zze.length;
      }
    }
    return i;
  }
  
  public final void zza(zzflk paramZzflk)
    throws IOException
  {
    Object localObject = zza;
    int j = 0;
    int i;
    if ((localObject != null) && (zza.length > 0))
    {
      i = 0;
      while (i < zza.length)
      {
        localObject = zza[i];
        if (localObject != null) {
          paramZzflk.zza(1, (String)localObject);
        }
        i += 1;
      }
    }
    if ((zzb != null) && (zzb.length > 0))
    {
      i = 0;
      while (i < zzb.length)
      {
        localObject = zzb[i];
        if (localObject != null) {
          paramZzflk.zza(2, (String)localObject);
        }
        i += 1;
      }
    }
    if ((zzc != null) && (zzc.length > 0))
    {
      i = 0;
      while (i < zzc.length)
      {
        paramZzflk.zza(3, zzc[i]);
        i += 1;
      }
    }
    if ((zzd != null) && (zzd.length > 0))
    {
      i = 0;
      while (i < zzd.length)
      {
        paramZzflk.zzb(4, zzd[i]);
        i += 1;
      }
    }
    if ((zze != null) && (zze.length > 0))
    {
      i = j;
      while (i < zze.length)
      {
        paramZzflk.zzb(5, zze[i]);
        i += 1;
      }
    }
    super.zza(paramZzflk);
  }
}
