package com.google.android.gms.internal;

import java.io.IOException;
import java.io.InputStream;

public abstract class zzfhb
{
  private static volatile boolean zze = true;
  int zza;
  int zzb = 100;
  int zzc = Integer.MAX_VALUE;
  private boolean zzd = false;
  
  private zzfhb() {}
  
  public static long zza(long paramLong)
  {
    return paramLong >>> 1 ^ -(paramLong & 1L);
  }
  
  public static zzfhb zza(InputStream paramInputStream)
  {
    if (paramInputStream == null)
    {
      paramInputStream = zzfhz.zzb;
      return zza(paramInputStream, 0, paramInputStream.length, false);
    }
    return new zzfhe(paramInputStream, 4096, null);
  }
  
  public static zzfhb zza(byte[] paramArrayOfByte)
  {
    return zza(paramArrayOfByte, 0, paramArrayOfByte.length, false);
  }
  
  public static zzfhb zza(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    return zza(paramArrayOfByte, paramInt1, paramInt2, false);
  }
  
  static zzfhb zza(byte[] paramArrayOfByte, int paramInt1, int paramInt2, boolean paramBoolean)
  {
    paramArrayOfByte = new zzfhd(paramArrayOfByte, paramInt1, paramInt2, paramBoolean, null);
    try
    {
      paramArrayOfByte.zzd(paramInt2);
      return paramArrayOfByte;
    }
    catch (zzfie paramArrayOfByte)
    {
      throw new IllegalArgumentException(paramArrayOfByte);
    }
  }
  
  public static int zzg(int paramInt)
  {
    return -(paramInt & 0x1) ^ paramInt >>> 1;
  }
  
  public abstract int zza()
    throws IOException;
  
  public abstract <T extends zzfhu<T, ?>> T zza(T paramT, zzfhm paramZzfhm)
    throws IOException;
  
  public abstract void zza(int paramInt)
    throws zzfie;
  
  public abstract void zza(zzfjd paramZzfjd, zzfhm paramZzfhm)
    throws IOException;
  
  public abstract double zzb()
    throws IOException;
  
  public abstract boolean zzb(int paramInt)
    throws IOException;
  
  public abstract float zzc()
    throws IOException;
  
  public final int zzc(int paramInt)
  {
    paramInt = zzc;
    zzc = Integer.MAX_VALUE;
    return paramInt;
  }
  
  public abstract int zzd(int paramInt)
    throws zzfie;
  
  public abstract long zzd()
    throws IOException;
  
  public abstract long zze()
    throws IOException;
  
  public abstract void zze(int paramInt);
  
  public abstract int zzf()
    throws IOException;
  
  public abstract void zzf(int paramInt)
    throws IOException;
  
  public abstract long zzg()
    throws IOException;
  
  public abstract int zzh()
    throws IOException;
  
  public abstract boolean zzi()
    throws IOException;
  
  public abstract String zzj()
    throws IOException;
  
  public abstract String zzk()
    throws IOException;
  
  public abstract zzfgs zzl()
    throws IOException;
  
  public abstract int zzm()
    throws IOException;
  
  public abstract int zzn()
    throws IOException;
  
  public abstract int zzo()
    throws IOException;
  
  public abstract long zzp()
    throws IOException;
  
  public abstract int zzq()
    throws IOException;
  
  public abstract long zzr()
    throws IOException;
  
  public abstract int zzs()
    throws IOException;
  
  abstract long zzt()
    throws IOException;
  
  public abstract int zzu();
  
  public abstract boolean zzv()
    throws IOException;
  
  public abstract int zzw();
}
