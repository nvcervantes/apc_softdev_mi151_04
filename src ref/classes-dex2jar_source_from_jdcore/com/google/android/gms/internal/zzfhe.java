package com.google.android.gms.internal;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

final class zzfhe
  extends zzfhb
{
  private final InputStream zzd;
  private final byte[] zze;
  private int zzf;
  private int zzg;
  private int zzh;
  private int zzi;
  private int zzj;
  private int zzk = Integer.MAX_VALUE;
  private zzfhf zzl = null;
  
  private zzfhe(InputStream paramInputStream, int paramInt)
  {
    super(null);
    zzfhz.zza(paramInputStream, "input");
    zzd = paramInputStream;
    zze = new byte[paramInt];
    zzf = 0;
    zzh = 0;
    zzj = 0;
  }
  
  private final void zzaa()
  {
    zzf += zzg;
    int i = zzj + zzf;
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
      zzh(1);
    }
    byte[] arrayOfByte = zze;
    int i = zzh;
    zzh = (i + 1);
    return arrayOfByte[i];
  }
  
  private final void zzh(int paramInt)
    throws IOException
  {
    if (!zzi(paramInt))
    {
      if (paramInt > zzc - zzj - zzh) {
        throw zzfie.zzh();
      }
      throw zzfie.zza();
    }
  }
  
  private final boolean zzi(int paramInt)
    throws IOException
  {
    int i;
    do
    {
      if (zzh + paramInt <= zzf)
      {
        localStringBuilder = new StringBuilder(77);
        localStringBuilder.append("refillBuffer() called when ");
        localStringBuilder.append(paramInt);
        localStringBuilder.append(" bytes were already available in buffer");
        throw new IllegalStateException(localStringBuilder.toString());
      }
      if (paramInt > zzc - zzj - zzh) {
        return false;
      }
      if (zzj + zzh + paramInt > zzk) {
        return false;
      }
      i = zzh;
      if (i > 0)
      {
        if (zzf > i) {
          System.arraycopy(zze, i, zze, 0, zzf - i);
        }
        zzj += i;
        zzf -= i;
        zzh = 0;
      }
      i = zzd.read(zze, zzf, Math.min(zze.length - zzf, zzc - zzj - zzf));
      if ((i == 0) || (i < -1) || (i > zze.length)) {
        break label250;
      }
      if (i <= 0) {
        break;
      }
      zzf += i;
      zzaa();
    } while (zzf < paramInt);
    return true;
    return false;
    label250:
    StringBuilder localStringBuilder = new StringBuilder(102);
    localStringBuilder.append("InputStream#read(byte[]) returned invalid result: ");
    localStringBuilder.append(i);
    localStringBuilder.append("\nThe InputStream implementation is buggy.");
    throw new IllegalStateException(localStringBuilder.toString());
  }
  
  private final byte[] zzj(int paramInt)
    throws IOException
  {
    byte[] arrayOfByte1 = zzk(paramInt);
    if (arrayOfByte1 != null) {
      return arrayOfByte1;
    }
    int j = zzh;
    int i = zzf - zzh;
    zzj += zzf;
    zzh = 0;
    zzf = 0;
    Object localObject = zzl(paramInt - i);
    arrayOfByte1 = new byte[paramInt];
    System.arraycopy(zze, j, arrayOfByte1, 0, i);
    localObject = ((List)localObject).iterator();
    paramInt = i;
    while (((Iterator)localObject).hasNext())
    {
      byte[] arrayOfByte2 = (byte[])((Iterator)localObject).next();
      System.arraycopy(arrayOfByte2, 0, arrayOfByte1, paramInt, arrayOfByte2.length);
      paramInt += arrayOfByte2.length;
    }
    return arrayOfByte1;
  }
  
  private final byte[] zzk(int paramInt)
    throws IOException
  {
    if (paramInt == 0) {
      return zzfhz.zzb;
    }
    if (paramInt < 0) {
      throw zzfie.zzb();
    }
    int i = zzj + zzh + paramInt;
    if (i - zzc > 0) {
      throw zzfie.zzh();
    }
    if (i > zzk)
    {
      zzf(zzk - zzj - zzh);
      throw zzfie.zza();
    }
    i = zzf - zzh;
    int j = paramInt - i;
    if ((j >= 4096) && (j > zzd.available())) {
      return null;
    }
    byte[] arrayOfByte = new byte[paramInt];
    System.arraycopy(zze, zzh, arrayOfByte, 0, i);
    zzj += zzf;
    zzh = 0;
    zzf = 0;
    while (i < arrayOfByte.length)
    {
      j = zzd.read(arrayOfByte, i, paramInt - i);
      if (j == -1) {
        throw zzfie.zza();
      }
      zzj += j;
      i += j;
    }
    return arrayOfByte;
  }
  
  private final List<byte[]> zzl(int paramInt)
    throws IOException
  {
    ArrayList localArrayList = new ArrayList();
    while (paramInt > 0)
    {
      byte[] arrayOfByte = new byte[Math.min(paramInt, 4096)];
      int i = 0;
      while (i < arrayOfByte.length)
      {
        int j = zzd.read(arrayOfByte, i, arrayOfByte.length - i);
        if (j == -1) {
          throw zzfie.zza();
        }
        zzj += j;
        i += j;
      }
      paramInt -= arrayOfByte.length;
      localArrayList.add(arrayOfByte);
    }
    return localArrayList;
  }
  
  private final long zzx()
    throws IOException
  {
    int i = zzh;
    if (zzf != i)
    {
      byte[] arrayOfByte = zze;
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
    int j = zzh;
    int i = j;
    if (zzf - j < 4)
    {
      zzh(4);
      i = zzh;
    }
    byte[] arrayOfByte = zze;
    zzh = (i + 4);
    j = arrayOfByte[i];
    int k = arrayOfByte[(i + 1)];
    int m = arrayOfByte[(i + 2)];
    return (arrayOfByte[(i + 3)] & 0xFF) << 24 | j & 0xFF | (k & 0xFF) << 8 | (m & 0xFF) << 16;
  }
  
  private final long zzz()
    throws IOException
  {
    int j = zzh;
    int i = j;
    if (zzf - j < 8)
    {
      zzh(8);
      i = zzh;
    }
    byte[] arrayOfByte = zze;
    zzh = (i + 8);
    return arrayOfByte[i] & 0xFF | (arrayOfByte[(i + 1)] & 0xFF) << 8 | (arrayOfByte[(i + 2)] & 0xFF) << 16 | (arrayOfByte[(i + 3)] & 0xFF) << 24 | (arrayOfByte[(i + 4)] & 0xFF) << 32 | (arrayOfByte[(i + 5)] & 0xFF) << 40 | (arrayOfByte[(i + 6)] & 0xFF) << 48 | (arrayOfByte[(i + 7)] & 0xFF) << 56;
  }
  
  public final int zza()
    throws IOException
  {
    if (zzv())
    {
      zzi = 0;
      return 0;
    }
    zzi = zzs();
    if (zzi >>> 3 == 0) {
      throw zzfie.zzd();
    }
    return zzi;
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
    if (zzi != paramInt) {
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
        byte[] arrayOfByte = zze;
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
    paramInt += zzj + zzh;
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
    if ((paramInt <= zzf - zzh) && (paramInt >= 0))
    {
      zzh += paramInt;
      return;
    }
    if (paramInt < 0) {
      throw zzfie.zzb();
    }
    if (zzj + zzh + paramInt > zzk)
    {
      zzf(zzk - zzj - zzh);
      throw zzfie.zza();
    }
    int i = zzf - zzh;
    int j;
    for (zzh = zzf;; zzh = zzf)
    {
      zzh(1);
      j = paramInt - i;
      if (j <= zzf) {
        break;
      }
      i += zzf;
    }
    zzh = j;
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
    String str;
    if ((i > 0) && (i <= zzf - zzh))
    {
      str = new String(zze, zzh, i, zzfhz.zza);
      zzh += i;
      return str;
    }
    if (i == 0) {
      return "";
    }
    if (i <= zzf)
    {
      zzh(i);
      str = new String(zze, zzh, i, zzfhz.zza);
      zzh += i;
      return str;
    }
    return new String(zzj(i), zzfhz.zza);
  }
  
  public final String zzk()
    throws IOException
  {
    int k = zzs();
    int i = zzh;
    int m = zzf;
    int j = 0;
    byte[] arrayOfByte;
    if ((k <= m - i) && (k > 0))
    {
      arrayOfByte = zze;
      zzh = (i + k);
    }
    else
    {
      if (k == 0) {
        return "";
      }
      if (k <= zzf)
      {
        zzh(k);
        arrayOfByte = zze;
        zzh = k;
        i = j;
      }
      else
      {
        arrayOfByte = zzj(k);
        i = j;
      }
    }
    if (!zzfks.zza(arrayOfByte, i, i + k)) {
      throw zzfie.zzi();
    }
    return new String(arrayOfByte, i, k, zzfhz.zza);
  }
  
  public final zzfgs zzl()
    throws IOException
  {
    int i = zzs();
    if ((i <= zzf - zzh) && (i > 0))
    {
      localObject1 = zzfgs.zza(zze, zzh, i);
      zzh += i;
      return localObject1;
    }
    if (i == 0) {
      return zzfgs.zza;
    }
    Object localObject1 = zzk(i);
    if (localObject1 != null) {
      return zzfgs.zzb((byte[])localObject1);
    }
    int j = zzh;
    int k = zzf - zzh;
    zzj += zzf;
    zzh = 0;
    zzf = 0;
    Object localObject2 = zzl(i - k);
    localObject1 = new ArrayList(1 + ((List)localObject2).size());
    ((List)localObject1).add(zzfgs.zza(zze, j, k));
    localObject2 = ((List)localObject2).iterator();
    while (((Iterator)localObject2).hasNext()) {
      ((List)localObject1).add(zzfgs.zzb((byte[])((Iterator)localObject2).next()));
    }
    return zzfgs.zza((Iterable)localObject1);
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
      byte[] arrayOfByte = zze;
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
                          break label264;
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
    label264:
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
    int i = zzj;
    int j = zzh;
    return zzk - (i + j);
  }
  
  public final boolean zzv()
    throws IOException
  {
    return (zzh == zzf) && (!zzi(1));
  }
  
  public final int zzw()
  {
    return zzj + zzh;
  }
}
