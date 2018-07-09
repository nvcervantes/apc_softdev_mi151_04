package com.google.android.gms.internal;

import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class zzfhg
  extends zzfgr
{
  private static final Logger zzb = Logger.getLogger(zzfhg.class.getName());
  private static final boolean zzc = zzfkq.zza();
  zzfhi zza;
  
  private zzfhg() {}
  
  static int zza(int paramInt)
  {
    if (paramInt > 4096) {
      return 4096;
    }
    return paramInt;
  }
  
  public static int zza(int paramInt, zzfik paramZzfik)
  {
    paramInt = zzf(paramInt);
    int i = paramZzfik.zzb();
    return paramInt + (zzh(i) + i);
  }
  
  public static int zza(zzfik paramZzfik)
  {
    int i = paramZzfik.zzb();
    return zzh(i) + i;
  }
  
  public static zzfhg zza(OutputStream paramOutputStream, int paramInt)
  {
    return new zzd(paramOutputStream, paramInt);
  }
  
  public static zzfhg zza(byte[] paramArrayOfByte)
  {
    return zzb(paramArrayOfByte, 0, paramArrayOfByte.length);
  }
  
  public static int zzb(double paramDouble)
  {
    return 8;
  }
  
  public static int zzb(float paramFloat)
  {
    return 4;
  }
  
  public static int zzb(int paramInt, double paramDouble)
  {
    return zzf(paramInt) + 8;
  }
  
  public static int zzb(int paramInt, zzfik paramZzfik)
  {
    return (zzf(1) << 1) + zzf(2, paramInt) + zza(3, paramZzfik);
  }
  
  public static int zzb(int paramInt, String paramString)
  {
    return zzf(paramInt) + zzb(paramString);
  }
  
  public static int zzb(int paramInt, boolean paramBoolean)
  {
    return zzf(paramInt) + 1;
  }
  
  public static int zzb(zzfgs paramZzfgs)
  {
    int i = paramZzfgs.zza();
    return zzh(i) + i;
  }
  
  public static int zzb(zzfjc paramZzfjc)
  {
    int i = paramZzfjc.zza();
    return zzh(i) + i;
  }
  
  public static int zzb(String paramString)
  {
    try
    {
      i = zzfks.zza(paramString);
    }
    catch (zzfkv localZzfkv)
    {
      int i;
      for (;;) {}
    }
    i = paramString.getBytes(zzfhz.zza).length;
    return zzh(i) + i;
  }
  
  public static int zzb(boolean paramBoolean)
  {
    return 1;
  }
  
  public static int zzb(byte[] paramArrayOfByte)
  {
    int i = paramArrayOfByte.length;
    return zzh(i) + i;
  }
  
  public static zzfhg zzb(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    return new zzb(paramArrayOfByte, paramInt1, paramInt2);
  }
  
  public static int zzc(int paramInt, long paramLong)
  {
    return zzf(paramInt) + zze(paramLong);
  }
  
  public static int zzc(int paramInt, zzfgs paramZzfgs)
  {
    paramInt = zzf(paramInt);
    int i = paramZzfgs.zza();
    return paramInt + (zzh(i) + i);
  }
  
  public static int zzc(int paramInt, zzfjc paramZzfjc)
  {
    return zzf(paramInt) + zzb(paramZzfjc);
  }
  
  @Deprecated
  public static int zzc(zzfjc paramZzfjc)
  {
    return paramZzfjc.zza();
  }
  
  public static int zzd(int paramInt, long paramLong)
  {
    return zzf(paramInt) + zze(paramLong);
  }
  
  public static int zzd(int paramInt, zzfgs paramZzfgs)
  {
    return (zzf(1) << 1) + zzf(2, paramInt) + zzc(3, paramZzfgs);
  }
  
  public static int zzd(int paramInt, zzfjc paramZzfjc)
  {
    return (zzf(1) << 1) + zzf(2, paramInt) + zzc(3, paramZzfjc);
  }
  
  public static int zzd(long paramLong)
  {
    return zze(paramLong);
  }
  
  public static int zze(int paramInt1, int paramInt2)
  {
    return zzf(paramInt1) + zzg(paramInt2);
  }
  
  public static int zze(int paramInt, long paramLong)
  {
    return zzf(paramInt) + 8;
  }
  
  public static int zze(long paramLong)
  {
    if ((paramLong & 0xFFFFFFFFFFFFFF80) == 0L) {
      return 1;
    }
    if (paramLong < 0L) {
      return 10;
    }
    if ((paramLong & 0xFFFFFFF800000000) != 0L)
    {
      j = 6;
      paramLong >>>= 28;
    }
    else
    {
      j = 2;
    }
    int i = j;
    long l = paramLong;
    if ((paramLong & 0xFFFFFFFFFFE00000) != 0L)
    {
      i = j + 2;
      l = paramLong >>> 14;
    }
    int j = i;
    if ((l & 0xFFFFFFFFFFFFC000) != 0L) {
      j = i + 1;
    }
    return j;
  }
  
  public static int zzf(int paramInt)
  {
    return zzh(paramInt << 3);
  }
  
  public static int zzf(int paramInt1, int paramInt2)
  {
    return zzf(paramInt1) + zzh(paramInt2);
  }
  
  public static int zzf(long paramLong)
  {
    return zze(zzi(paramLong));
  }
  
  public static int zzg(int paramInt)
  {
    if (paramInt >= 0) {
      return zzh(paramInt);
    }
    return 10;
  }
  
  public static int zzg(int paramInt1, int paramInt2)
  {
    return zzf(paramInt1) + 4;
  }
  
  public static int zzg(long paramLong)
  {
    return 8;
  }
  
  public static int zzh(int paramInt)
  {
    if ((paramInt & 0xFFFFFF80) == 0) {
      return 1;
    }
    if ((paramInt & 0xC000) == 0) {
      return 2;
    }
    if ((0xFFE00000 & paramInt) == 0) {
      return 3;
    }
    if ((paramInt & 0xF0000000) == 0) {
      return 4;
    }
    return 5;
  }
  
  public static int zzh(int paramInt1, int paramInt2)
  {
    return zzf(paramInt1) + zzg(paramInt2);
  }
  
  public static int zzh(long paramLong)
  {
    return 8;
  }
  
  public static int zzi(int paramInt)
  {
    return zzh(zzo(paramInt));
  }
  
  private static long zzi(long paramLong)
  {
    return paramLong << 1 ^ paramLong >> 63;
  }
  
  public static int zzj(int paramInt)
  {
    return 4;
  }
  
  public static int zzk(int paramInt)
  {
    return 4;
  }
  
  public static int zzl(int paramInt)
  {
    return zzg(paramInt);
  }
  
  static int zzm(int paramInt)
  {
    return zzh(paramInt) + paramInt;
  }
  
  @Deprecated
  public static int zzn(int paramInt)
  {
    return zzh(paramInt);
  }
  
  private static int zzo(int paramInt)
  {
    return paramInt >> 31 ^ paramInt << 1;
  }
  
  public abstract void zza()
    throws IOException;
  
  public abstract void zza(byte paramByte)
    throws IOException;
  
  public final void zza(double paramDouble)
    throws IOException
  {
    zzc(Double.doubleToRawLongBits(paramDouble));
  }
  
  public final void zza(float paramFloat)
    throws IOException
  {
    zze(Float.floatToRawIntBits(paramFloat));
  }
  
  public final void zza(int paramInt, double paramDouble)
    throws IOException
  {
    zzb(paramInt, Double.doubleToRawLongBits(paramDouble));
  }
  
  public abstract void zza(int paramInt1, int paramInt2)
    throws IOException;
  
  public abstract void zza(int paramInt, long paramLong)
    throws IOException;
  
  public abstract void zza(int paramInt, zzfgs paramZzfgs)
    throws IOException;
  
  public abstract void zza(int paramInt, zzfjc paramZzfjc)
    throws IOException;
  
  public abstract void zza(int paramInt, String paramString)
    throws IOException;
  
  public abstract void zza(int paramInt, boolean paramBoolean)
    throws IOException;
  
  public abstract void zza(long paramLong)
    throws IOException;
  
  public abstract void zza(zzfgs paramZzfgs)
    throws IOException;
  
  public abstract void zza(zzfjc paramZzfjc)
    throws IOException;
  
  public abstract void zza(String paramString)
    throws IOException;
  
  final void zza(String paramString, zzfkv paramZzfkv)
    throws IOException
  {
    zzb.logp(Level.WARNING, "com.google.protobuf.CodedOutputStream", "inefficientWriteStringNoTag", "Converting ill-formed UTF-16. Your Protocol Buffer will not round trip correctly!", paramZzfkv);
    paramString = paramString.getBytes(zzfhz.zza);
    try
    {
      zzc(paramString.length);
      zza(paramString, 0, paramString.length);
      return;
    }
    catch (zzc paramString)
    {
      throw paramString;
    }
    catch (IndexOutOfBoundsException paramString)
    {
      throw new zzc(paramString);
    }
  }
  
  public final void zza(boolean paramBoolean)
    throws IOException
  {
    zza((byte)paramBoolean);
  }
  
  public abstract int zzb();
  
  public abstract void zzb(int paramInt)
    throws IOException;
  
  public abstract void zzb(int paramInt1, int paramInt2)
    throws IOException;
  
  public abstract void zzb(int paramInt, long paramLong)
    throws IOException;
  
  public abstract void zzb(int paramInt, zzfgs paramZzfgs)
    throws IOException;
  
  public abstract void zzb(int paramInt, zzfjc paramZzfjc)
    throws IOException;
  
  public final void zzb(long paramLong)
    throws IOException
  {
    zza(zzi(paramLong));
  }
  
  public final void zzc()
  {
    if (zzb() != 0) {
      throw new IllegalStateException("Did not write as much data as expected.");
    }
  }
  
  public abstract void zzc(int paramInt)
    throws IOException;
  
  public abstract void zzc(int paramInt1, int paramInt2)
    throws IOException;
  
  public abstract void zzc(long paramLong)
    throws IOException;
  
  public abstract void zzc(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException;
  
  public final void zzd(int paramInt)
    throws IOException
  {
    zzc(zzo(paramInt));
  }
  
  public abstract void zzd(int paramInt1, int paramInt2)
    throws IOException;
  
  abstract void zzd(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException;
  
  public abstract void zze(int paramInt)
    throws IOException;
  
  @Deprecated
  public final void zze(int paramInt, zzfjc paramZzfjc)
    throws IOException
  {
    zza(paramInt, 3);
    paramZzfjc.zza(this);
    zza(paramInt, 4);
  }
  
  static abstract class zza
    extends zzfhg
  {
    final byte[] zzb;
    final int zzc;
    int zzd;
    int zze;
    
    zza(int paramInt)
    {
      super();
      if (paramInt < 0) {
        throw new IllegalArgumentException("bufferSize must be >= 0");
      }
      zzb = new byte[Math.max(paramInt, 20)];
      zzc = zzb.length;
    }
    
    public final int zzb()
    {
      throw new UnsupportedOperationException("spaceLeft() can only be called on CodedOutputStreams that are writing to a flat array or ByteBuffer.");
    }
    
    final void zzb(byte paramByte)
    {
      byte[] arrayOfByte = zzb;
      int i = zzd;
      zzd = (i + 1);
      arrayOfByte[i] = paramByte;
      zze += 1;
    }
    
    final void zzi(int paramInt1, int paramInt2)
    {
      zzo(paramInt1 << 3 | paramInt2);
    }
    
    final void zzi(long paramLong)
    {
      long l = paramLong;
      byte[] arrayOfByte;
      int i;
      if (zzfhg.zzd())
      {
        l = zzd;
        for (;;)
        {
          if ((paramLong & 0xFFFFFFFFFFFFFF80) == 0L)
          {
            arrayOfByte = zzb;
            i = zzd;
            zzd = (i + 1);
            zzfkq.zza(arrayOfByte, i, (byte)(int)paramLong);
            i = (int)(zzd - l);
            zze += i;
            return;
          }
          arrayOfByte = zzb;
          i = zzd;
          zzd = (i + 1);
          zzfkq.zza(arrayOfByte, i, (byte)((int)paramLong & 0x7F | 0x80));
          paramLong >>>= 7;
        }
      }
      for (;;)
      {
        if ((l & 0xFFFFFFFFFFFFFF80) == 0L)
        {
          arrayOfByte = zzb;
          i = zzd;
          zzd = (i + 1);
          arrayOfByte[i] = ((byte)(int)l);
          zze += 1;
          return;
        }
        arrayOfByte = zzb;
        i = zzd;
        zzd = (i + 1);
        arrayOfByte[i] = ((byte)((int)l & 0x7F | 0x80));
        zze += 1;
        l >>>= 7;
      }
    }
    
    final void zzj(long paramLong)
    {
      byte[] arrayOfByte = zzb;
      int i = zzd;
      zzd = (i + 1);
      arrayOfByte[i] = ((byte)(int)(paramLong & 0xFF));
      arrayOfByte = zzb;
      i = zzd;
      zzd = (i + 1);
      arrayOfByte[i] = ((byte)(int)(paramLong >> 8 & 0xFF));
      arrayOfByte = zzb;
      i = zzd;
      zzd = (i + 1);
      arrayOfByte[i] = ((byte)(int)(paramLong >> 16 & 0xFF));
      arrayOfByte = zzb;
      i = zzd;
      zzd = (i + 1);
      arrayOfByte[i] = ((byte)(int)(paramLong >> 24 & 0xFF));
      arrayOfByte = zzb;
      i = zzd;
      zzd = (i + 1);
      arrayOfByte[i] = ((byte)(int)(paramLong >> 32));
      arrayOfByte = zzb;
      i = zzd;
      zzd = (i + 1);
      arrayOfByte[i] = ((byte)(int)(paramLong >> 40));
      arrayOfByte = zzb;
      i = zzd;
      zzd = (i + 1);
      arrayOfByte[i] = ((byte)(int)(paramLong >> 48));
      arrayOfByte = zzb;
      i = zzd;
      zzd = (i + 1);
      arrayOfByte[i] = ((byte)(int)(paramLong >> 56));
      zze += 8;
    }
    
    final void zzo(int paramInt)
    {
      int i = paramInt;
      byte[] arrayOfByte;
      if (zzfhg.zzd())
      {
        long l = zzd;
        for (;;)
        {
          if ((paramInt & 0xFFFFFF80) == 0)
          {
            arrayOfByte = zzb;
            i = zzd;
            zzd = (i + 1);
            zzfkq.zza(arrayOfByte, i, (byte)paramInt);
            paramInt = (int)(zzd - l);
            zze += paramInt;
            return;
          }
          arrayOfByte = zzb;
          i = zzd;
          zzd = (i + 1);
          zzfkq.zza(arrayOfByte, i, (byte)(paramInt & 0x7F | 0x80));
          paramInt >>>= 7;
        }
      }
      for (;;)
      {
        if ((i & 0xFFFFFF80) == 0)
        {
          arrayOfByte = zzb;
          paramInt = zzd;
          zzd = (paramInt + 1);
          arrayOfByte[paramInt] = ((byte)i);
          zze += 1;
          return;
        }
        arrayOfByte = zzb;
        paramInt = zzd;
        zzd = (paramInt + 1);
        arrayOfByte[paramInt] = ((byte)(i & 0x7F | 0x80));
        zze += 1;
        i >>>= 7;
      }
    }
    
    final void zzp(int paramInt)
    {
      byte[] arrayOfByte = zzb;
      int i = zzd;
      zzd = (i + 1);
      arrayOfByte[i] = ((byte)paramInt);
      arrayOfByte = zzb;
      i = zzd;
      zzd = (i + 1);
      arrayOfByte[i] = ((byte)(paramInt >> 8));
      arrayOfByte = zzb;
      i = zzd;
      zzd = (i + 1);
      arrayOfByte[i] = ((byte)(paramInt >> 16));
      arrayOfByte = zzb;
      i = zzd;
      zzd = (i + 1);
      arrayOfByte[i] = (paramInt >> 24);
      zze += 4;
    }
  }
  
  static class zzb
    extends zzfhg
  {
    private final byte[] zzb;
    private final int zzc;
    private final int zzd;
    private int zze;
    
    zzb(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    {
      super();
      if (paramArrayOfByte == null) {
        throw new NullPointerException("buffer");
      }
      int i = paramArrayOfByte.length;
      int j = paramInt1 + paramInt2;
      if ((paramInt1 | paramInt2 | i - j) < 0) {
        throw new IllegalArgumentException(String.format("Array range is invalid. Buffer.length=%d, offset=%d, length=%d", new Object[] { Integer.valueOf(paramArrayOfByte.length), Integer.valueOf(paramInt1), Integer.valueOf(paramInt2) }));
      }
      zzb = paramArrayOfByte;
      zzc = paramInt1;
      zze = paramInt1;
      zzd = j;
    }
    
    public void zza() {}
    
    public final void zza(byte paramByte)
      throws IOException
    {
      try
      {
        byte[] arrayOfByte = zzb;
        int i = zze;
        zze = (i + 1);
        arrayOfByte[i] = paramByte;
        return;
      }
      catch (IndexOutOfBoundsException localIndexOutOfBoundsException)
      {
        throw new zzfhg.zzc(String.format("Pos: %d, limit: %d, len: %d", new Object[] { Integer.valueOf(zze), Integer.valueOf(zzd), Integer.valueOf(1) }), localIndexOutOfBoundsException);
      }
    }
    
    public final void zza(int paramInt1, int paramInt2)
      throws IOException
    {
      zzc(paramInt1 << 3 | paramInt2);
    }
    
    public final void zza(int paramInt, long paramLong)
      throws IOException
    {
      zza(paramInt, 0);
      zza(paramLong);
    }
    
    public final void zza(int paramInt, zzfgs paramZzfgs)
      throws IOException
    {
      zza(paramInt, 2);
      zza(paramZzfgs);
    }
    
    public final void zza(int paramInt, zzfjc paramZzfjc)
      throws IOException
    {
      zza(paramInt, 2);
      zza(paramZzfjc);
    }
    
    public final void zza(int paramInt, String paramString)
      throws IOException
    {
      zza(paramInt, 2);
      zza(paramString);
    }
    
    public final void zza(int paramInt, boolean paramBoolean)
      throws IOException
    {
      zza(paramInt, 0);
      zza((byte)paramBoolean);
    }
    
    public final void zza(long paramLong)
      throws IOException
    {
      long l = paramLong;
      byte[] arrayOfByte;
      int i;
      if (zzfhg.zzd())
      {
        l = paramLong;
        if (zzb() >= 10) {
          for (;;)
          {
            if ((paramLong & 0xFFFFFFFFFFFFFF80) == 0L)
            {
              arrayOfByte = zzb;
              i = zze;
              zze = (i + 1);
              zzfkq.zza(arrayOfByte, i, (byte)(int)paramLong);
              return;
            }
            arrayOfByte = zzb;
            i = zze;
            zze = (i + 1);
            zzfkq.zza(arrayOfByte, i, (byte)((int)paramLong & 0x7F | 0x80));
            paramLong >>>= 7;
          }
        }
      }
      for (;;)
      {
        if ((l & 0xFFFFFFFFFFFFFF80) == 0L) {}
        try
        {
          arrayOfByte = zzb;
          i = zze;
          zze = (i + 1);
          arrayOfByte[i] = ((byte)(int)l);
          return;
        }
        catch (IndexOutOfBoundsException localIndexOutOfBoundsException)
        {
          for (;;) {}
        }
        arrayOfByte = zzb;
        i = zze;
        zze = (i + 1);
        arrayOfByte[i] = ((byte)((int)l & 0x7F | 0x80));
        l >>>= 7;
      }
      throw new zzfhg.zzc(String.format("Pos: %d, limit: %d, len: %d", new Object[] { Integer.valueOf(zze), Integer.valueOf(zzd), Integer.valueOf(1) }), arrayOfByte);
    }
    
    public final void zza(zzfgs paramZzfgs)
      throws IOException
    {
      zzc(paramZzfgs.zza());
      paramZzfgs.zza(this);
    }
    
    public final void zza(zzfjc paramZzfjc)
      throws IOException
    {
      zzc(paramZzfjc.zza());
      paramZzfjc.zza(this);
    }
    
    public final void zza(String paramString)
      throws IOException
    {
      int i = zze;
      try
      {
        int k = zzh(paramString.length() * 3);
        int j = zzh(paramString.length());
        if (j == k)
        {
          zze = (i + j);
          k = zzfks.zza(paramString, zzb, zze, zzb());
          zze = i;
          zzc(k - i - j);
          zze = k;
          return;
        }
        zzc(zzfks.zza(paramString));
        zze = zzfks.zza(paramString, zzb, zze, zzb());
        return;
      }
      catch (IndexOutOfBoundsException paramString)
      {
        throw new zzfhg.zzc(paramString);
      }
      catch (zzfkv localZzfkv)
      {
        zze = i;
        zza(paramString, localZzfkv);
      }
    }
    
    public final void zza(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
      throws IOException
    {
      zzc(paramArrayOfByte, paramInt1, paramInt2);
    }
    
    public final int zzb()
    {
      return zzd - zze;
    }
    
    public final void zzb(int paramInt)
      throws IOException
    {
      if (paramInt >= 0)
      {
        zzc(paramInt);
        return;
      }
      zza(paramInt);
    }
    
    public final void zzb(int paramInt1, int paramInt2)
      throws IOException
    {
      zza(paramInt1, 0);
      zzb(paramInt2);
    }
    
    public final void zzb(int paramInt, long paramLong)
      throws IOException
    {
      zza(paramInt, 1);
      zzc(paramLong);
    }
    
    public final void zzb(int paramInt, zzfgs paramZzfgs)
      throws IOException
    {
      zza(1, 3);
      zzc(2, paramInt);
      zza(3, paramZzfgs);
      zza(1, 4);
    }
    
    public final void zzb(int paramInt, zzfjc paramZzfjc)
      throws IOException
    {
      zza(1, 3);
      zzc(2, paramInt);
      zza(3, paramZzfjc);
      zza(1, 4);
    }
    
    public final void zzc(int paramInt)
      throws IOException
    {
      int i = paramInt;
      byte[] arrayOfByte;
      if (zzfhg.zzd())
      {
        i = paramInt;
        if (zzb() >= 10) {
          for (;;)
          {
            if ((paramInt & 0xFFFFFF80) == 0)
            {
              arrayOfByte = zzb;
              i = zze;
              zze = (i + 1);
              zzfkq.zza(arrayOfByte, i, (byte)paramInt);
              return;
            }
            arrayOfByte = zzb;
            i = zze;
            zze = (i + 1);
            zzfkq.zza(arrayOfByte, i, (byte)(paramInt & 0x7F | 0x80));
            paramInt >>>= 7;
          }
        }
      }
      for (;;)
      {
        if ((i & 0xFFFFFF80) == 0) {}
        try
        {
          arrayOfByte = zzb;
          paramInt = zze;
          zze = (paramInt + 1);
          arrayOfByte[paramInt] = ((byte)i);
          return;
        }
        catch (IndexOutOfBoundsException localIndexOutOfBoundsException)
        {
          for (;;) {}
        }
        arrayOfByte = zzb;
        paramInt = zze;
        zze = (paramInt + 1);
        arrayOfByte[paramInt] = ((byte)(i & 0x7F | 0x80));
        i >>>= 7;
      }
      throw new zzfhg.zzc(String.format("Pos: %d, limit: %d, len: %d", new Object[] { Integer.valueOf(zze), Integer.valueOf(zzd), Integer.valueOf(1) }), arrayOfByte);
    }
    
    public final void zzc(int paramInt1, int paramInt2)
      throws IOException
    {
      zza(paramInt1, 0);
      zzc(paramInt2);
    }
    
    public final void zzc(long paramLong)
      throws IOException
    {
      try
      {
        byte[] arrayOfByte = zzb;
        int i = zze;
        zze = (i + 1);
        arrayOfByte[i] = ((byte)(int)paramLong);
        arrayOfByte = zzb;
        i = zze;
        zze = (i + 1);
        arrayOfByte[i] = ((byte)(int)(paramLong >> 8));
        arrayOfByte = zzb;
        i = zze;
        zze = (i + 1);
        arrayOfByte[i] = ((byte)(int)(paramLong >> 16));
        arrayOfByte = zzb;
        i = zze;
        zze = (i + 1);
        arrayOfByte[i] = ((byte)(int)(paramLong >> 24));
        arrayOfByte = zzb;
        i = zze;
        zze = (i + 1);
        arrayOfByte[i] = ((byte)(int)(paramLong >> 32));
        arrayOfByte = zzb;
        i = zze;
        zze = (i + 1);
        arrayOfByte[i] = ((byte)(int)(paramLong >> 40));
        arrayOfByte = zzb;
        i = zze;
        zze = (i + 1);
        arrayOfByte[i] = ((byte)(int)(paramLong >> 48));
        arrayOfByte = zzb;
        i = zze;
        zze = (i + 1);
        arrayOfByte[i] = ((byte)(int)(paramLong >> 56));
        return;
      }
      catch (IndexOutOfBoundsException localIndexOutOfBoundsException)
      {
        throw new zzfhg.zzc(String.format("Pos: %d, limit: %d, len: %d", new Object[] { Integer.valueOf(zze), Integer.valueOf(zzd), Integer.valueOf(1) }), localIndexOutOfBoundsException);
      }
    }
    
    public final void zzc(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
      throws IOException
    {
      try
      {
        System.arraycopy(paramArrayOfByte, paramInt1, zzb, zze, paramInt2);
        zze += paramInt2;
        return;
      }
      catch (IndexOutOfBoundsException paramArrayOfByte)
      {
        throw new zzfhg.zzc(String.format("Pos: %d, limit: %d, len: %d", new Object[] { Integer.valueOf(zze), Integer.valueOf(zzd), Integer.valueOf(paramInt2) }), paramArrayOfByte);
      }
    }
    
    public final void zzd(int paramInt1, int paramInt2)
      throws IOException
    {
      zza(paramInt1, 5);
      zze(paramInt2);
    }
    
    public final void zzd(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
      throws IOException
    {
      zzc(paramInt2);
      zzc(paramArrayOfByte, 0, paramInt2);
    }
    
    public final void zze(int paramInt)
      throws IOException
    {
      try
      {
        byte[] arrayOfByte = zzb;
        int i = zze;
        zze = (i + 1);
        arrayOfByte[i] = ((byte)paramInt);
        arrayOfByte = zzb;
        i = zze;
        zze = (i + 1);
        arrayOfByte[i] = ((byte)(paramInt >> 8));
        arrayOfByte = zzb;
        i = zze;
        zze = (i + 1);
        arrayOfByte[i] = ((byte)(paramInt >> 16));
        arrayOfByte = zzb;
        i = zze;
        zze = (i + 1);
        arrayOfByte[i] = (paramInt >> 24);
        return;
      }
      catch (IndexOutOfBoundsException localIndexOutOfBoundsException)
      {
        throw new zzfhg.zzc(String.format("Pos: %d, limit: %d, len: %d", new Object[] { Integer.valueOf(zze), Integer.valueOf(zzd), Integer.valueOf(1) }), localIndexOutOfBoundsException);
      }
    }
  }
  
  public static final class zzc
    extends IOException
  {
    zzc()
    {
      super();
    }
    
    zzc(String paramString, Throwable paramThrowable)
    {
      super(paramThrowable);
    }
    
    zzc(Throwable paramThrowable)
    {
      super(paramThrowable);
    }
  }
  
  static final class zzd
    extends zzfhg.zza
  {
    private final OutputStream zzf;
    
    zzd(OutputStream paramOutputStream, int paramInt)
    {
      super();
      if (paramOutputStream == null) {
        throw new NullPointerException("out");
      }
      zzf = paramOutputStream;
    }
    
    private final void zze()
      throws IOException
    {
      zzf.write(zzb, 0, zzd);
      zzd = 0;
    }
    
    private final void zzq(int paramInt)
      throws IOException
    {
      if (zzc - zzd < paramInt) {
        zze();
      }
    }
    
    public final void zza()
      throws IOException
    {
      if (zzd > 0) {
        zze();
      }
    }
    
    public final void zza(byte paramByte)
      throws IOException
    {
      if (zzd == zzc) {
        zze();
      }
      zzb(paramByte);
    }
    
    public final void zza(int paramInt1, int paramInt2)
      throws IOException
    {
      zzc(paramInt1 << 3 | paramInt2);
    }
    
    public final void zza(int paramInt, long paramLong)
      throws IOException
    {
      zzq(20);
      zzi(paramInt, 0);
      zzi(paramLong);
    }
    
    public final void zza(int paramInt, zzfgs paramZzfgs)
      throws IOException
    {
      zza(paramInt, 2);
      zza(paramZzfgs);
    }
    
    public final void zza(int paramInt, zzfjc paramZzfjc)
      throws IOException
    {
      zza(paramInt, 2);
      zza(paramZzfjc);
    }
    
    public final void zza(int paramInt, String paramString)
      throws IOException
    {
      zza(paramInt, 2);
      zza(paramString);
    }
    
    public final void zza(int paramInt, boolean paramBoolean)
      throws IOException
    {
      zzq(11);
      zzi(paramInt, 0);
      zzb((byte)paramBoolean);
    }
    
    public final void zza(long paramLong)
      throws IOException
    {
      zzq(10);
      zzi(paramLong);
    }
    
    public final void zza(zzfgs paramZzfgs)
      throws IOException
    {
      zzc(paramZzfgs.zza());
      paramZzfgs.zza(this);
    }
    
    public final void zza(zzfjc paramZzfjc)
      throws IOException
    {
      zzc(paramZzfjc.zza());
      paramZzfjc.zza(this);
    }
    
    public final void zza(String paramString)
      throws IOException
    {
      for (;;)
      {
        try
        {
          j = paramString.length() * 3;
          i = zzh(j);
          k = i + j;
          if (k > zzc)
          {
            arrayOfByte = new byte[j];
            i = zzfks.zza(paramString, arrayOfByte, 0, j);
            zzc(i);
            zza(arrayOfByte, 0, i);
            return;
          }
          if (k > zzc - zzd) {
            zze();
          }
          k = zzh(paramString.length());
          j = zzd;
          if (k != i) {}
        }
        catch (zzfkv localZzfkv1)
        {
          int j;
          int i;
          int k;
          byte[] arrayOfByte;
          int m;
          zza(paramString, localZzfkv1);
          return;
        }
        try
        {
          zzd = (j + k);
          m = zzfks.zza(paramString, zzb, zzd, zzc - zzd);
          zzd = j;
          i = m - j - k;
          zzo(i);
          zzd = m;
          continue;
          i = zzfks.zza(paramString);
          zzo(i);
          zzd = zzfks.zza(paramString, zzb, zzd, i);
          zze += i;
          return;
        }
        catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException) {}catch (zzfkv localZzfkv2) {}
      }
      throw new zzfhg.zzc(arrayOfByte);
      zze -= zzd - j;
      zzd = j;
      throw arrayOfByte;
    }
    
    public final void zza(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
      throws IOException
    {
      zzc(paramArrayOfByte, paramInt1, paramInt2);
    }
    
    public final void zzb(int paramInt)
      throws IOException
    {
      if (paramInt >= 0)
      {
        zzc(paramInt);
        return;
      }
      zza(paramInt);
    }
    
    public final void zzb(int paramInt1, int paramInt2)
      throws IOException
    {
      zzq(20);
      zzi(paramInt1, 0);
      if (paramInt2 >= 0)
      {
        zzo(paramInt2);
        return;
      }
      zzi(paramInt2);
    }
    
    public final void zzb(int paramInt, long paramLong)
      throws IOException
    {
      zzq(18);
      zzi(paramInt, 1);
      zzj(paramLong);
    }
    
    public final void zzb(int paramInt, zzfgs paramZzfgs)
      throws IOException
    {
      zza(1, 3);
      zzc(2, paramInt);
      zza(3, paramZzfgs);
      zza(1, 4);
    }
    
    public final void zzb(int paramInt, zzfjc paramZzfjc)
      throws IOException
    {
      zza(1, 3);
      zzc(2, paramInt);
      zza(3, paramZzfjc);
      zza(1, 4);
    }
    
    public final void zzc(int paramInt)
      throws IOException
    {
      zzq(10);
      zzo(paramInt);
    }
    
    public final void zzc(int paramInt1, int paramInt2)
      throws IOException
    {
      zzq(20);
      zzi(paramInt1, 0);
      zzo(paramInt2);
    }
    
    public final void zzc(long paramLong)
      throws IOException
    {
      zzq(8);
      zzj(paramLong);
    }
    
    public final void zzc(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
      throws IOException
    {
      if (zzc - zzd >= paramInt2)
      {
        System.arraycopy(paramArrayOfByte, paramInt1, zzb, zzd, paramInt2);
        zzd += paramInt2;
      }
      else
      {
        int i = zzc - zzd;
        System.arraycopy(paramArrayOfByte, paramInt1, zzb, zzd, i);
        paramInt1 += i;
        paramInt2 -= i;
        zzd = zzc;
        zze += i;
        zze();
        if (paramInt2 <= zzc)
        {
          System.arraycopy(paramArrayOfByte, paramInt1, zzb, 0, paramInt2);
          zzd = paramInt2;
        }
        else
        {
          zzf.write(paramArrayOfByte, paramInt1, paramInt2);
        }
      }
      zze += paramInt2;
    }
    
    public final void zzd(int paramInt1, int paramInt2)
      throws IOException
    {
      zzq(14);
      zzi(paramInt1, 5);
      zzp(paramInt2);
    }
    
    public final void zzd(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
      throws IOException
    {
      zzc(paramInt2);
      zzc(paramArrayOfByte, 0, paramInt2);
    }
    
    public final void zze(int paramInt)
      throws IOException
    {
      zzq(4);
      zzp(paramInt);
    }
  }
}
