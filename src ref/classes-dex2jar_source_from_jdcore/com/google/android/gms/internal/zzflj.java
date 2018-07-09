package com.google.android.gms.internal;

import java.io.IOException;

public final class zzflj
{
  private final byte[] zza;
  private final int zzb;
  private final int zzc;
  private int zzd;
  private int zze;
  private int zzf;
  private int zzg;
  private int zzh = Integer.MAX_VALUE;
  private int zzi;
  private int zzj = 64;
  private int zzk = 67108864;
  
  private zzflj(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    zza = paramArrayOfByte;
    zzb = paramInt1;
    paramInt2 += paramInt1;
    zzd = paramInt2;
    zzc = paramInt2;
    zzf = paramInt1;
  }
  
  public static zzflj zza(byte[] paramArrayOfByte)
  {
    return zza(paramArrayOfByte, 0, paramArrayOfByte.length);
  }
  
  public static zzflj zza(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    return new zzflj(paramArrayOfByte, 0, paramInt2);
  }
  
  private final void zzf(int paramInt)
    throws IOException
  {
    if (paramInt < 0) {
      throw zzflr.zzb();
    }
    if (zzf + paramInt > zzh)
    {
      zzf(zzh - zzf);
      throw zzflr.zza();
    }
    if (paramInt <= zzd - zzf)
    {
      zzf += paramInt;
      return;
    }
    throw zzflr.zza();
  }
  
  private final void zzn()
  {
    zzd += zze;
    int i = zzd;
    if (i > zzh)
    {
      zze = (i - zzh);
      zzd -= zze;
      return;
    }
    zze = 0;
  }
  
  private final byte zzo()
    throws IOException
  {
    if (zzf == zzd) {
      throw zzflr.zza();
    }
    byte[] arrayOfByte = zza;
    int i = zzf;
    zzf = (i + 1);
    return arrayOfByte[i];
  }
  
  public final int zza()
    throws IOException
  {
    if (zzf == zzd)
    {
      zzg = 0;
      return 0;
    }
    zzg = zzh();
    if (zzg == 0) {
      throw new zzflr("Protocol message contained an invalid tag (zero).");
    }
    return zzg;
  }
  
  public final void zza(int paramInt)
    throws zzflr
  {
    if (zzg != paramInt) {
      throw new zzflr("Protocol message end-group tag did not match expected tag.");
    }
  }
  
  public final void zza(zzfls paramZzfls)
    throws IOException
  {
    int i = zzh();
    if (zzi >= zzj) {
      throw zzflr.zzd();
    }
    i = zzc(i);
    zzi += 1;
    paramZzfls.zza(this);
    zza(0);
    zzi -= 1;
    zzd(i);
  }
  
  public final void zza(zzfls paramZzfls, int paramInt)
    throws IOException
  {
    if (zzi >= zzj) {
      throw zzflr.zzd();
    }
    zzi += 1;
    paramZzfls.zza(this);
    zza(paramInt << 3 | 0x4);
    zzi -= 1;
  }
  
  public final byte[] zza(int paramInt1, int paramInt2)
  {
    if (paramInt2 == 0) {
      return zzflv.zzh;
    }
    byte[] arrayOfByte = new byte[paramInt2];
    int i = zzb;
    System.arraycopy(zza, i + paramInt1, arrayOfByte, 0, paramInt2);
    return arrayOfByte;
  }
  
  public final long zzb()
    throws IOException
  {
    return zzi();
  }
  
  final void zzb(int paramInt1, int paramInt2)
  {
    StringBuilder localStringBuilder;
    if (paramInt1 > zzf - zzb)
    {
      paramInt2 = zzf;
      int i = zzb;
      localStringBuilder = new StringBuilder(50);
      localStringBuilder.append("Position ");
      localStringBuilder.append(paramInt1);
      localStringBuilder.append(" is beyond current ");
      localStringBuilder.append(paramInt2 - i);
      throw new IllegalArgumentException(localStringBuilder.toString());
    }
    if (paramInt1 < 0)
    {
      localStringBuilder = new StringBuilder(24);
      localStringBuilder.append("Bad position ");
      localStringBuilder.append(paramInt1);
      throw new IllegalArgumentException(localStringBuilder.toString());
    }
    zzf = (zzb + paramInt1);
    zzg = paramInt2;
  }
  
  public final boolean zzb(int paramInt)
    throws IOException
  {
    switch (paramInt & 0x7)
    {
    default: 
      throw new zzflr("Protocol message tag had invalid wire type.");
    case 5: 
      zzj();
      return true;
    case 4: 
      return false;
    case 3: 
      int i;
      do
      {
        i = zza();
      } while ((i != 0) && (zzb(i)));
      zza(paramInt >>> 3 << 3 | 0x4);
      return true;
    case 2: 
      zzf(zzh());
      return true;
    case 1: 
      zzk();
      return true;
    }
    zzh();
    return true;
  }
  
  public final int zzc()
    throws IOException
  {
    return zzh();
  }
  
  public final int zzc(int paramInt)
    throws zzflr
  {
    if (paramInt < 0) {
      throw zzflr.zzb();
    }
    paramInt += zzf;
    int i = zzh;
    if (paramInt > i) {
      throw zzflr.zza();
    }
    zzh = paramInt;
    zzn();
    return i;
  }
  
  public final void zzd(int paramInt)
  {
    zzh = paramInt;
    zzn();
  }
  
  public final boolean zzd()
    throws IOException
  {
    return zzh() != 0;
  }
  
  public final String zze()
    throws IOException
  {
    int i = zzh();
    if (i < 0) {
      throw zzflr.zzb();
    }
    if (i > zzd - zzf) {
      throw zzflr.zza();
    }
    String str = new String(zza, zzf, i, zzflq.zza);
    zzf += i;
    return str;
  }
  
  public final void zze(int paramInt)
  {
    zzb(paramInt, zzg);
  }
  
  public final byte[] zzf()
    throws IOException
  {
    int i = zzh();
    if (i < 0) {
      throw zzflr.zzb();
    }
    if (i == 0) {
      return zzflv.zzh;
    }
    if (i > zzd - zzf) {
      throw zzflr.zza();
    }
    byte[] arrayOfByte = new byte[i];
    System.arraycopy(zza, zzf, arrayOfByte, 0, i);
    zzf += i;
    return arrayOfByte;
  }
  
  public final long zzg()
    throws IOException
  {
    long l = zzi();
    return l >>> 1 ^ -(l & 1L);
  }
  
  public final int zzh()
    throws IOException
  {
    int i = zzo();
    if (i >= 0) {
      return i;
    }
    i &= 0x7F;
    int j = zzo();
    if (j >= 0) {
      j <<= 7;
    }
    for (;;)
    {
      return i | j;
      i |= (j & 0x7F) << 7;
      j = zzo();
      if (j >= 0)
      {
        j <<= 14;
      }
      else
      {
        i |= (j & 0x7F) << 14;
        j = zzo();
        if (j < 0) {
          break;
        }
        j <<= 21;
      }
    }
    int k = zzo();
    j = i | (j & 0x7F) << 21 | k << 28;
    if (k < 0)
    {
      i = 0;
      while (i < 5)
      {
        if (zzo() >= 0) {
          return j;
        }
        i += 1;
      }
      throw zzflr.zzc();
    }
    return j;
  }
  
  public final long zzi()
    throws IOException
  {
    int i = 0;
    long l = 0L;
    while (i < 64)
    {
      int j = zzo();
      l |= (j & 0x7F) << i;
      if ((j & 0x80) == 0) {
        return l;
      }
      i += 7;
    }
    throw zzflr.zzc();
  }
  
  public final int zzj()
    throws IOException
  {
    return zzo() & 0xFF | (zzo() & 0xFF) << 8 | (zzo() & 0xFF) << 16 | (zzo() & 0xFF) << 24;
  }
  
  public final long zzk()
    throws IOException
  {
    int i = zzo();
    int j = zzo();
    int k = zzo();
    int m = zzo();
    int n = zzo();
    int i1 = zzo();
    int i2 = zzo();
    int i3 = zzo();
    return i & 0xFF | (j & 0xFF) << 8 | (k & 0xFF) << 16 | (m & 0xFF) << 24 | (n & 0xFF) << 32 | (i1 & 0xFF) << 40 | (i2 & 0xFF) << 48 | (i3 & 0xFF) << 56;
  }
  
  public final int zzl()
  {
    if (zzh == Integer.MAX_VALUE) {
      return -1;
    }
    int i = zzf;
    return zzh - i;
  }
  
  public final int zzm()
  {
    return zzf - zzb;
  }
}
