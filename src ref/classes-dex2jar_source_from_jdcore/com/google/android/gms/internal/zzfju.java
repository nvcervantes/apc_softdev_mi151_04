package com.google.android.gms.internal;

import java.io.IOException;
import java.io.InputStream;

final class zzfju
  extends InputStream
{
  private zzfjt zza;
  private zzfgy zzb;
  private int zzc;
  private int zzd;
  private int zze;
  private int zzf;
  
  public zzfju(zzfjq paramZzfjq)
  {
    zza();
  }
  
  private final int zza(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    int i = paramInt1;
    paramInt1 = paramInt2;
    while (paramInt1 > 0)
    {
      zzb();
      if (zzb == null)
      {
        if (paramInt1 != paramInt2) {
          break;
        }
        return -1;
      }
      int k = Math.min(zzc - zzd, paramInt1);
      int j = i;
      if (paramArrayOfByte != null)
      {
        zzb.zza(paramArrayOfByte, zzd, i, k);
        j = i + k;
      }
      zzd += k;
      paramInt1 -= k;
      i = j;
    }
    return paramInt2 - paramInt1;
  }
  
  private final void zza()
  {
    zza = new zzfjt(zzg, null);
    zzb = ((zzfgy)zza.next());
    zzc = zzb.zza();
    zzd = 0;
    zze = 0;
  }
  
  private final void zzb()
  {
    if ((zzb != null) && (zzd == zzc))
    {
      zze += zzc;
      zzd = 0;
      if (zza.hasNext())
      {
        zzb = ((zzfgy)zza.next());
        zzc = zzb.zza();
        return;
      }
      zzb = null;
      zzc = 0;
    }
  }
  
  public final int available()
    throws IOException
  {
    int i = zze;
    int j = zzd;
    return zzg.zza() - (i + j);
  }
  
  public final void mark(int paramInt)
  {
    zzf = (zze + zzd);
  }
  
  public final boolean markSupported()
  {
    return true;
  }
  
  public final int read()
    throws IOException
  {
    zzb();
    if (zzb == null) {
      return -1;
    }
    zzfgy localZzfgy = zzb;
    int i = zzd;
    zzd = (i + 1);
    return localZzfgy.zza(i) & 0xFF;
  }
  
  public final int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    if (paramArrayOfByte == null) {
      throw new NullPointerException();
    }
    if ((paramInt1 >= 0) && (paramInt2 >= 0) && (paramInt2 <= paramArrayOfByte.length - paramInt1)) {
      return zza(paramArrayOfByte, paramInt1, paramInt2);
    }
    throw new IndexOutOfBoundsException();
  }
  
  public final void reset()
  {
    try
    {
      zza();
      zza(null, 0, zzf);
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  public final long skip(long paramLong)
  {
    if (paramLong < 0L) {
      throw new IndexOutOfBoundsException();
    }
    long l = paramLong;
    if (paramLong > 2147483647L) {
      l = 2147483647L;
    }
    return zza(null, 0, (int)l);
  }
}
