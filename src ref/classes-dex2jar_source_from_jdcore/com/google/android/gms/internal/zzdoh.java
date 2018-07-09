package com.google.android.gms.internal;

import java.io.IOException;

public final class zzdoh
  extends zzflm<zzdoh>
{
  public String[] zza = zzflv.zzf;
  public int[] zzb = zzflv.zza;
  public byte[][] zzc = zzflv.zzg;
  
  public zzdoh()
  {
    zzax = null;
    zzay = -1;
  }
  
  public static zzdoh zza(byte[] paramArrayOfByte)
    throws zzflr
  {
    return (zzdoh)zzfls.zza(new zzdoh(), paramArrayOfByte);
  }
  
  public final boolean equals(Object paramObject)
  {
    if (paramObject == this) {
      return true;
    }
    if (!(paramObject instanceof zzdoh)) {
      return false;
    }
    paramObject = (zzdoh)paramObject;
    if (!zzflq.zza(zza, zza)) {
      return false;
    }
    if (!zzflq.zza(zzb, zzb)) {
      return false;
    }
    if (!zzflq.zza(zzc, zzc)) {
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
    int i;
    if ((zzax != null) && (!zzax.zzb())) {
      i = zzax.hashCode();
    } else {
      i = 0;
    }
    return ((((527 + j) * 31 + k) * 31 + m) * 31 + n) * 31 + i;
  }
  
  protected final int zza()
  {
    int i2 = super.zza();
    Object localObject = zza;
    int i1 = 0;
    int j = i2;
    int m;
    int k;
    int n;
    if (localObject != null)
    {
      j = i2;
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
        j = i2 + j + k * 1;
      }
    }
    int i = j;
    if (zzb != null)
    {
      i = j;
      if (zzb.length > 0)
      {
        i = 0;
        k = i;
        while (i < zzb.length)
        {
          k += zzflk.zza(zzb[i]);
          i += 1;
        }
        i = j + k + zzb.length * 1;
      }
    }
    j = i;
    if (zzc != null)
    {
      j = i;
      if (zzc.length > 0)
      {
        k = 0;
        m = k;
        j = i1;
        while (j < zzc.length)
        {
          localObject = zzc[j];
          i1 = k;
          n = m;
          if (localObject != null)
          {
            n = m + 1;
            i1 = k + zzflk.zzb((byte[])localObject);
          }
          j += 1;
          k = i1;
          m = n;
        }
        j = i + k + 1 * m;
      }
    }
    return j;
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
        paramZzflk.zza(2, zzb[i]);
        i += 1;
      }
    }
    if ((zzc != null) && (zzc.length > 0))
    {
      i = j;
      while (i < zzc.length)
      {
        localObject = zzc[i];
        if (localObject != null) {
          paramZzflk.zza(3, (byte[])localObject);
        }
        i += 1;
      }
    }
    super.zza(paramZzflk);
  }
}
