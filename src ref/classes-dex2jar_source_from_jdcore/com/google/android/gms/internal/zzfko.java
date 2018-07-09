package com.google.android.gms.internal;

import java.io.IOException;
import java.util.Arrays;

public final class zzfko
{
  private static final zzfko zza = new zzfko(0, new int[0], new Object[0], false);
  private int zzb;
  private int[] zzc;
  private Object[] zzd;
  private int zze = -1;
  private boolean zzf;
  
  private zzfko()
  {
    this(0, new int[8], new Object[8], true);
  }
  
  private zzfko(int paramInt, int[] paramArrayOfInt, Object[] paramArrayOfObject, boolean paramBoolean)
  {
    zzb = paramInt;
    zzc = paramArrayOfInt;
    zzd = paramArrayOfObject;
    zzf = paramBoolean;
  }
  
  public static zzfko zza()
  {
    return zza;
  }
  
  static zzfko zza(zzfko paramZzfko1, zzfko paramZzfko2)
  {
    int i = zzb + zzb;
    int[] arrayOfInt = Arrays.copyOf(zzc, i);
    System.arraycopy(zzc, 0, arrayOfInt, zzb, zzb);
    Object[] arrayOfObject = Arrays.copyOf(zzd, i);
    System.arraycopy(zzd, 0, arrayOfObject, zzb, zzb);
    return new zzfko(i, arrayOfInt, arrayOfObject, true);
  }
  
  private void zza(int paramInt, Object paramObject)
  {
    zzf();
    if (zzb == zzc.length)
    {
      if (zzb < 4) {
        i = 8;
      } else {
        i = zzb >> 1;
      }
      int i = zzb + i;
      zzc = Arrays.copyOf(zzc, i);
      zzd = Arrays.copyOf(zzd, i);
    }
    zzc[zzb] = paramInt;
    zzd[zzb] = paramObject;
    zzb += 1;
  }
  
  static zzfko zzb()
  {
    return new zzfko();
  }
  
  private final void zzf()
  {
    if (!zzf) {
      throw new UnsupportedOperationException();
    }
  }
  
  public final boolean equals(Object paramObject)
  {
    if (this == paramObject) {
      return true;
    }
    if (paramObject == null) {
      return false;
    }
    if (!(paramObject instanceof zzfko)) {
      return false;
    }
    paramObject = (zzfko)paramObject;
    if (zzb == zzb)
    {
      Object localObject = zzc;
      int[] arrayOfInt = zzc;
      int j = zzb;
      int i = 0;
      while (i < j)
      {
        if (localObject[i] != arrayOfInt[i])
        {
          i = 0;
          break label87;
        }
        i += 1;
      }
      i = 1;
      label87:
      if (i != 0)
      {
        localObject = zzd;
        paramObject = zzd;
        j = zzb;
        i = 0;
        while (i < j)
        {
          if (!localObject[i].equals(paramObject[i]))
          {
            i = 0;
            break label141;
          }
          i += 1;
        }
        i = 1;
        label141:
        return i != 0;
      }
    }
    return false;
  }
  
  public final int hashCode()
  {
    return ((527 + zzb) * 31 + Arrays.hashCode(zzc)) * 31 + Arrays.deepHashCode(zzd);
  }
  
  public final void zza(zzfhg paramZzfhg)
    throws IOException
  {
    int i = 0;
    while (i < zzb)
    {
      int k = zzc[i];
      int j = k >>> 3;
      k &= 0x7;
      if (k != 5) {
        switch (k)
        {
        default: 
          throw zzfie.zzf();
        case 3: 
          paramZzfhg.zza(j, 3);
          ((zzfko)zzd[i]).zza(paramZzfhg);
          paramZzfhg.zza(j, 4);
          break;
        case 2: 
          paramZzfhg.zza(j, (zzfgs)zzd[i]);
          break;
        case 1: 
          paramZzfhg.zzb(j, ((Long)zzd[i]).longValue());
          break;
        case 0: 
          paramZzfhg.zza(j, ((Long)zzd[i]).longValue());
          break;
        }
      } else {
        paramZzfhg.zzd(j, ((Integer)zzd[i]).intValue());
      }
      i += 1;
    }
  }
  
  final void zza(zzfli paramZzfli)
  {
    if (paramZzfli.zza() == zzfhu.zzg.zzm)
    {
      i = zzb - 1;
      while (i >= 0)
      {
        paramZzfli.zza(zzc[i] >>> 3, zzd[i]);
        i -= 1;
      }
      return;
    }
    int i = 0;
    while (i < zzb)
    {
      paramZzfli.zza(zzc[i] >>> 3, zzd[i]);
      i += 1;
    }
  }
  
  final void zza(StringBuilder paramStringBuilder, int paramInt)
  {
    int i = 0;
    while (i < zzb)
    {
      zzfjf.zza(paramStringBuilder, paramInt, String.valueOf(zzc[i] >>> 3), zzd[i]);
      i += 1;
    }
  }
  
  final boolean zza(int paramInt, zzfhb paramZzfhb)
    throws IOException
  {
    zzf();
    switch (paramInt & 0x7)
    {
    default: 
      throw zzfie.zzf();
    case 5: 
      zza(paramInt, Integer.valueOf(paramZzfhb.zzh()));
      return true;
    case 4: 
      return false;
    case 3: 
      zzfko localZzfko = new zzfko();
      int i;
      do
      {
        i = paramZzfhb.zza();
      } while ((i != 0) && (localZzfko.zza(i, paramZzfhb)));
      paramZzfhb.zza(paramInt >>> 3 << 3 | 0x4);
      zza(paramInt, localZzfko);
      return true;
    case 2: 
      zza(paramInt, paramZzfhb.zzl());
      return true;
    case 1: 
      zza(paramInt, Long.valueOf(paramZzfhb.zzg()));
      return true;
    }
    zza(paramInt, Long.valueOf(paramZzfhb.zze()));
    return true;
  }
  
  public final void zzc()
  {
    zzf = false;
  }
  
  public final int zzd()
  {
    int i = zze;
    if (i != -1) {
      return i;
    }
    i = 0;
    int j = 0;
    while (i < zzb)
    {
      j += zzfhg.zzd(zzc[i] >>> 3, (zzfgs)zzd[i]);
      i += 1;
    }
    zze = j;
    return j;
  }
  
  public final int zze()
  {
    int i = zze;
    if (i != -1) {
      return i;
    }
    int j = 0;
    int k = 0;
    while (j < zzb)
    {
      int m = zzc[j];
      i = m >>> 3;
      m &= 0x7;
      if (m != 5) {
        switch (m)
        {
        default: 
          throw new IllegalStateException(zzfie.zzf());
        case 3: 
          i = (zzfhg.zzf(i) << 1) + ((zzfko)zzd[j]).zze();
          break;
        case 2: 
          i = zzfhg.zzc(i, (zzfgs)zzd[j]);
          break;
        case 1: 
          i = zzfhg.zze(i, ((Long)zzd[j]).longValue());
          break;
        }
      }
      for (i = zzfhg.zzd(i, ((Long)zzd[j]).longValue());; i = zzfhg.zzg(i, ((Integer)zzd[j]).intValue()))
      {
        k += i;
        break;
      }
      j += 1;
    }
    zze = k;
    return k;
  }
}
