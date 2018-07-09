package com.google.android.gms.internal;

import java.io.IOException;
import java.util.Arrays;

final class zzfhd
  extends zzfhb
{
  private final byte[] zzd;
  private final boolean zze;
  private int zzf;
  private int zzg;
  private int zzh;
  private int zzi;
  private int zzj;
  private int zzk = Integer.MAX_VALUE;
  
  private zzfhd(byte[] paramArrayOfByte, int paramInt1, int paramInt2, boolean paramBoolean)
  {
    super(null);
    zzd = paramArrayOfByte;
    zzf = (paramInt2 + paramInt1);
    zzh = paramInt1;
    zzi = zzh;
    zze = paramBoolean;
  }
  
  private final void zzaa()
  {
    zzf += zzg;
    int i = zzf - zzi;
    if (i > zzk)
    {
      zzg = (i - zzk);
      zzf -= zzg;
      return;
    }
    zzg = 0;
  }
  
  private final byte zzab()
    throws IOException
  {
    if (zzh == zzf) {
      throw zzfie.zza();
    }
    byte[] arrayOfByte = zzd;
    int i = zzh;
    zzh = (i + 1);
    return arrayOfByte[i];
  }
  
  private final long zzx()
    throws IOException
  {
    int i = zzh;
    if (zzf != i)
    {
      byte[] arrayOfByte = zzd;
      int j = i + 1;
      int k = arrayOfByte[i];
      if (k >= 0)
      {
        zzh = j;
        return k;
      }
      if (zzf - j >= 9)
      {
        i = j + 1;
        k ^= arrayOfByte[j] << 7;
        if (k < 0) {
          j = k ^ 0xFFFFFF80;
        }
        for (;;)
        {
          l1 = j;
          for (;;)
          {
            break label332;
            j = i + 1;
            k ^= arrayOfByte[i] << 14;
            if (k < 0) {
              break;
            }
            l1 = k ^ 0x3F80;
            i = j;
          }
          i = j + 1;
          j = k ^ arrayOfByte[j] << 21;
          if (j >= 0) {
            break;
          }
          j ^= 0xFFE03F80;
        }
        long l1 = j;
        j = i + 1;
        long l2 = l1 ^ arrayOfByte[i] << 28;
        if (l2 >= 0L)
        {
          l1 = 266354560L;
          i = j;
          l1 = l2 ^ l1;
        }
        else
        {
          i = j + 1;
          l2 ^= arrayOfByte[j] << 35;
          if (l2 < 0L) {}
          for (l1 = -34093383808L;; l1 = -558586000294016L)
          {
            l1 = l2 ^ l1;
            break label332;
            j = i + 1;
            l2 ^= arrayOfByte[i] << 42;
            if (l2 >= 0L)
            {
              l1 = 4363953127296L;
              i = j;
              break;
            }
            i = j + 1;
            l2 ^= arrayOfByte[j] << 49;
            if (l2 >= 0L) {
              break label288;
            }
          }
          label288:
          j = i + 1;
          l1 = l2 ^ arrayOfByte[i] << 56 ^ 0xFE03F80FE03F80;
          i = j;
          if (l1 < 0L)
          {
            if (arrayOfByte[j] < 0L) {
              break label340;
            }
            i = j + 1;
          }
        }
        label332:
        zzh = i;
        return l1;
      }
    }
    label340:
    return zzt();
  }
  
  private final int zzy()
    throws IOException
  {
    int i = zzh;
    if (zzf - i < 4) {
      throw zzfie.zza();
    }
    byte[] arrayOfByte = zzd;
    zzh = (i + 4);
    int j = arrayOfByte[i];
    int k = arrayOfByte[(i + 1)];
    int m = arrayOfByte[(i + 2)];
    return (arrayOfByte[(i + 3)] & 0xFF) << 24 | j & 0xFF | (k & 0xFF) << 8 | (m & 0xFF) << 16;
  }
  
  private final long zzz()
    throws IOException
  {
    int i = zzh;
    if (zzf - i < 8) {
      throw zzfie.zza();
    }
    byte[] arrayOfByte = zzd;
    zzh = (i + 8);
    return arrayOfByte[i] & 0xFF | (arrayOfByte[(i + 1)] & 0xFF) << 8 | (arrayOfByte[(i + 2)] & 0xFF) << 16 | (arrayOfByte[(i + 3)] & 0xFF) << 24 | (arrayOfByte[(i + 4)] & 0xFF) << 32 | (arrayOfByte[(i + 5)] & 0xFF) << 40 | (arrayOfByte[(i + 6)] & 0xFF) << 48 | (arrayOfByte[(i + 7)] & 0xFF) << 56;
  }
  
  public final int zza()
    throws IOException
  {
    if (zzv())
    {
      zzj = 0;
      return 0;
    }
    zzj = zzs();
    if (zzj >>> 3 == 0) {
      throw zzfie.zzd();
    }
    return zzj;
  }
  
  public final <T extends zzfhu<T, ?>> T zza(T paramT, zzfhm paramZzfhm)
    throws IOException
  {
    int i = zzs();
    if (zza >= zzb) {
      throw zzfie.zzg();
    }
    i = zzd(i);
    zza += 1;
    paramT = zzfhu.zza(paramT, this, paramZzfhm);
    zza(0);
    zza -= 1;
    zze(i);
    return paramT;
  }
  
  public final void zza(int paramInt)
    throws zzfie
  {
    if (zzj != paramInt) {
      throw zzfie.zze();
    }
  }
  
  public final void zza(zzfjd paramZzfjd, zzfhm paramZzfhm)
    throws IOException
  {
    int i = zzs();
    if (zza >= zzb) {
      throw zzfie.zzg();
    }
    i = zzd(i);
    zza += 1;
    paramZzfjd.zzb(this, paramZzfhm);
    zza(0);
    zza -= 1;
    zze(i);
  }
  
  public final double zzb()
    throws IOException
  {
    return Double.longBitsToDouble(zzz());
  }
  
  public final boolean zzb(int paramInt)
    throws IOException
  {
    int j = 0;
    int i = 0;
    switch (paramInt & 0x7)
    {
    default: 
      throw zzfie.zzf();
    case 5: 
      zzf(4);
      return true;
    case 4: 
      return false;
    case 3: 
      do
      {
        i = zza();
      } while ((i != 0) && (zzb(i)));
      zza(paramInt >>> 3 << 3 | 0x4);
      return true;
    case 2: 
      zzf(zzs());
      return true;
    case 1: 
      zzf(8);
      return true;
    }
    paramInt = j;
    if (zzf - zzh >= 10)
    {
      paramInt = i;
      while (paramInt < 10)
      {
        byte[] arrayOfByte = zzd;
        i = zzh;
        zzh = (i + 1);
        if (arrayOfByte[i] >= 0) {
          break label189;
        }
        paramInt += 1;
      }
      throw zzfie.zzc();
    }
    for (;;)
    {
      if (paramInt >= 10) {
        break label191;
      }
      if (zzab() >= 0) {
        break;
      }
      paramInt += 1;
    }
    label189:
    return true;
    label191:
    throw zzfie.zzc();
  }
  
  public final float zzc()
    throws IOException
  {
    return Float.intBitsToFloat(zzy());
  }
  
  public final int zzd(int paramInt)
    throws zzfie
  {
    if (paramInt < 0) {
      throw zzfie.zzb();
    }
    paramInt += zzw();
    int i = zzk;
    if (paramInt > i) {
      throw zzfie.zza();
    }
    zzk = paramInt;
    zzaa();
    return i;
  }
  
  public final long zzd()
    throws IOException
  {
    return zzx();
  }
  
  public final long zze()
    throws IOException
  {
    return zzx();
  }
  
  public final void zze(int paramInt)
  {
    zzk = paramInt;
    zzaa();
  }
  
  public final int zzf()
    throws IOException
  {
    return zzs();
  }
  
  public final void zzf(int paramInt)
    throws IOException
  {
    if ((paramInt >= 0) && (paramInt <= zzf - zzh))
    {
      zzh += paramInt;
      return;
    }
    if (paramInt < 0) {
      throw zzfie.zzb();
    }
    throw zzfie.zza();
  }
  
  public final long zzg()
    throws IOException
  {
    return zzz();
  }
  
  public final int zzh()
    throws IOException
  {
    return zzy();
  }
  
  public final boolean zzi()
    throws IOException
  {
    return zzx() != 0L;
  }
  
  public final String zzj()
    throws IOException
  {
    int i = zzs();
    if ((i > 0) && (i <= zzf - zzh))
    {
      String str = new String(zzd, zzh, i, zzfhz.zza);
      zzh += i;
      return str;
    }
    if (i == 0) {
      return "";
    }
    if (i < 0) {
      throw zzfie.zzb();
    }
    throw zzfie.zza();
  }
  
  public final String zzk()
    throws IOException
  {
    int i = zzs();
    if ((i > 0) && (i <= zzf - zzh))
    {
      if (!zzfks.zza(zzd, zzh, zzh + i)) {
        throw zzfie.zzi();
      }
      int j = zzh;
      zzh += i;
      return new String(zzd, j, i, zzfhz.zza);
    }
    if (i == 0) {
      return "";
    }
    if (i <= 0) {
      throw zzfie.zzb();
    }
    throw zzfie.zza();
  }
  
  public final zzfgs zzl()
    throws IOException
  {
    int i = zzs();
    Object localObject;
    if ((i > 0) && (i <= zzf - zzh))
    {
      localObject = zzfgs.zza(zzd, zzh, i);
      zzh += i;
      return localObject;
    }
    if (i == 0) {
      return zzfgs.zza;
    }
    if ((i > 0) && (i <= zzf - zzh))
    {
      int j = zzh;
      zzh += i;
      localObject = Arrays.copyOfRange(zzd, j, zzh);
    }
    else
    {
      if (i > 0) {
        break label124;
      }
      if (i != 0) {
        break label120;
      }
      localObject = zzfhz.zzb;
    }
    return zzfgs.zzb((byte[])localObject);
    label120:
    throw zzfie.zzb();
    label124:
    throw zzfie.zza();
  }
  
  public final int zzm()
    throws IOException
  {
    return zzs();
  }
  
  public final int zzn()
    throws IOException
  {
    return zzs();
  }
  
  public final int zzo()
    throws IOException
  {
    return zzy();
  }
  
  public final long zzp()
    throws IOException
  {
    return zzz();
  }
  
  public final int zzq()
    throws IOException
  {
    return zzg(zzs());
  }
  
  public final long zzr()
    throws IOException
  {
    return zza(zzx());
  }
  
  public final int zzs()
    throws IOException
  {
    int i = zzh;
    if (zzf != i)
    {
      byte[] arrayOfByte = zzd;
      int j = i + 1;
      int k = arrayOfByte[i];
      if (k >= 0)
      {
        zzh = j;
        return k;
      }
      if (zzf - j >= 9)
      {
        i = j + 1;
        k ^= arrayOfByte[j] << 7;
        if (k < 0)
        {
          j = k ^ 0xFFFFFF80;
        }
        else
        {
          j = i + 1;
          k ^= arrayOfByte[i] << 14;
          if (k >= 0)
          {
            k ^= 0x3F80;
            i = j;
            j = k;
          }
          for (;;)
          {
            break;
            i = j + 1;
            j = k ^ arrayOfByte[j] << 21;
            if (j < 0)
            {
              j ^= 0xFFE03F80;
            }
            else
            {
              int m = i + 1;
              int n = arrayOfByte[i];
              k = j ^ n << 28 ^ 0xFE03F80;
              j = k;
              i = m;
              if (n < 0)
              {
                n = m + 1;
                j = k;
                i = n;
                if (arrayOfByte[m] < 0)
                {
                  m = n + 1;
                  j = k;
                  i = m;
                  if (arrayOfByte[n] < 0)
                  {
                    n = m + 1;
                    j = k;
                    i = n;
                    if (arrayOfByte[m] < 0)
                    {
                      m = n + 1;
                      j = k;
                      i = m;
                      if (arrayOfByte[n] < 0)
                      {
                        i = m + 1;
                        if (arrayOfByte[m] < 0) {
                          break label263;
                        }
                        j = k;
                      }
                    }
                  }
                }
              }
            }
          }
        }
        zzh = i;
        return j;
      }
    }
    label263:
    return (int)zzt();
  }
  
  final long zzt()
    throws IOException
  {
    long l = 0L;
    int i = 0;
    while (i < 64)
    {
      int j = zzab();
      l |= (j & 0x7F) << i;
      if ((j & 0x80) == 0) {
        return l;
      }
      i += 7;
    }
    throw zzfie.zzc();
  }
  
  public final int zzu()
  {
    if (zzk == Integer.MAX_VALUE) {
      return -1;
    }
    return zzk - zzw();
  }
  
  public final boolean zzv()
    throws IOException
  {
    return zzh == zzf;
  }
  
  public final int zzw()
  {
    return zzh - zzi;
  }
}
