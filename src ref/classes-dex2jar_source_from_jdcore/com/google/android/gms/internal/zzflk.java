package com.google.android.gms.internal;

import java.io.IOException;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ReadOnlyBufferException;

public final class zzflk
{
  private final ByteBuffer zza;
  
  private zzflk(ByteBuffer paramByteBuffer)
  {
    zza = paramByteBuffer;
    zza.order(ByteOrder.LITTLE_ENDIAN);
  }
  
  private zzflk(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    this(ByteBuffer.wrap(paramArrayOfByte, paramInt1, paramInt2));
  }
  
  public static int zza(int paramInt)
  {
    if (paramInt >= 0) {
      return zzd(paramInt);
    }
    return 10;
  }
  
  public static int zza(long paramLong)
  {
    if ((paramLong & 0xFFFFFFFFFFFFFF80) == 0L) {
      return 1;
    }
    if ((paramLong & 0xFFFFFFFFFFFFC000) == 0L) {
      return 2;
    }
    if ((paramLong & 0xFFFFFFFFFFE00000) == 0L) {
      return 3;
    }
    if ((paramLong & 0xFFFFFFFFF0000000) == 0L) {
      return 4;
    }
    if ((paramLong & 0xFFFFFFF800000000) == 0L) {
      return 5;
    }
    if ((paramLong & 0xFFFFFC0000000000) == 0L) {
      return 6;
    }
    if ((paramLong & 0xFFFE000000000000) == 0L) {
      return 7;
    }
    if ((paramLong & 0xFF00000000000000) == 0L) {
      return 8;
    }
    if ((paramLong & 0x8000000000000000) == 0L) {
      return 9;
    }
    return 10;
  }
  
  private static int zza(CharSequence paramCharSequence)
  {
    int n = paramCharSequence.length();
    int m = 0;
    int j = 0;
    while ((j < n) && (paramCharSequence.charAt(j) < '')) {
      j += 1;
    }
    int i = n;
    for (;;)
    {
      k = i;
      if (j >= n) {
        break label229;
      }
      k = paramCharSequence.charAt(j);
      if (k >= 2048) {
        break;
      }
      i += (127 - k >>> 31);
      j += 1;
    }
    int i2 = paramCharSequence.length();
    int k = m;
    while (j < i2)
    {
      int i3 = paramCharSequence.charAt(j);
      if (i3 < 2048)
      {
        k += (127 - i3 >>> 31);
        m = j;
      }
      else
      {
        int i1 = k + 2;
        k = i1;
        m = j;
        if (55296 <= i3)
        {
          k = i1;
          m = j;
          if (i3 <= 57343)
          {
            if (Character.codePointAt(paramCharSequence, j) < 65536)
            {
              paramCharSequence = new StringBuilder(39);
              paramCharSequence.append("Unpaired surrogate at index ");
              paramCharSequence.append(j);
              throw new IllegalArgumentException(paramCharSequence.toString());
            }
            m = j + 1;
            k = i1;
          }
        }
      }
      j = m + 1;
    }
    k = i + k;
    label229:
    if (k < n)
    {
      long l = k;
      paramCharSequence = new StringBuilder(54);
      paramCharSequence.append("UTF-8 length does not fit in int: ");
      paramCharSequence.append(l + 4294967296L);
      throw new IllegalArgumentException(paramCharSequence.toString());
    }
    return k;
  }
  
  private static int zza(CharSequence paramCharSequence, byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    int k = paramCharSequence.length();
    int m = paramInt2 + paramInt1;
    paramInt2 = 0;
    int n;
    while (paramInt2 < k)
    {
      j = paramInt2 + paramInt1;
      if (j >= m) {
        break;
      }
      n = paramCharSequence.charAt(paramInt2);
      if (n >= 128) {
        break;
      }
      paramArrayOfByte[j] = ((byte)n);
      paramInt2 += 1;
    }
    if (paramInt2 == k) {
      return paramInt1 + k;
    }
    int j = paramInt1 + paramInt2;
    paramInt1 = paramInt2;
    while (paramInt1 < k)
    {
      int i = paramCharSequence.charAt(paramInt1);
      if ((i < 128) && (j < m))
      {
        paramInt2 = j + 1;
        paramArrayOfByte[j] = ((byte)i);
      }
      for (;;)
      {
        break;
        if ((i < 2048) && (j <= m - 2))
        {
          n = j + 1;
          paramArrayOfByte[j] = ((byte)(0x3C0 | i >>> 6));
          paramInt2 = n + 1;
          paramArrayOfByte[n] = ((byte)(i & 0x3F | 0x80));
        }
        else if (((i < 55296) || (57343 < i)) && (j <= m - 3))
        {
          paramInt2 = j + 1;
          paramArrayOfByte[j] = ((byte)(0x1E0 | i >>> 12));
          j = paramInt2 + 1;
          paramArrayOfByte[paramInt2] = ((byte)(i >>> 6 & 0x3F | 0x80));
          paramInt2 = j + 1;
          paramArrayOfByte[j] = ((byte)(i & 0x3F | 0x80));
        }
        else
        {
          if (j > m - 4) {
            break label460;
          }
          paramInt2 = paramInt1 + 1;
          if (paramInt2 == paramCharSequence.length()) {
            break label423;
          }
          char c = paramCharSequence.charAt(paramInt2);
          if (!Character.isSurrogatePair(i, c))
          {
            paramInt1 = paramInt2;
            break label423;
          }
          paramInt1 = Character.toCodePoint(i, c);
          n = j + 1;
          paramArrayOfByte[j] = ((byte)(0xF0 | paramInt1 >>> 18));
          j = n + 1;
          paramArrayOfByte[n] = ((byte)(paramInt1 >>> 12 & 0x3F | 0x80));
          n = j + 1;
          paramArrayOfByte[j] = ((byte)(paramInt1 >>> 6 & 0x3F | 0x80));
          j = n + 1;
          paramArrayOfByte[n] = ((byte)(paramInt1 & 0x3F | 0x80));
          paramInt1 = paramInt2;
          paramInt2 = j;
        }
      }
      paramInt1 += 1;
      j = paramInt2;
      continue;
      label423:
      paramCharSequence = new StringBuilder(39);
      paramCharSequence.append("Unpaired surrogate at index ");
      paramCharSequence.append(paramInt1 - 1);
      throw new IllegalArgumentException(paramCharSequence.toString());
      label460:
      paramCharSequence = new StringBuilder(37);
      paramCharSequence.append("Failed writing ");
      paramCharSequence.append(i);
      paramCharSequence.append(" at index ");
      paramCharSequence.append(j);
      throw new ArrayIndexOutOfBoundsException(paramCharSequence.toString());
    }
    return j;
  }
  
  public static int zza(String paramString)
  {
    int i = zza(paramString);
    return zzd(i) + i;
  }
  
  public static zzflk zza(byte[] paramArrayOfByte)
  {
    return zza(paramArrayOfByte, 0, paramArrayOfByte.length);
  }
  
  public static zzflk zza(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    return new zzflk(paramArrayOfByte, 0, paramInt2);
  }
  
  private static void zza(CharSequence paramCharSequence, ByteBuffer paramByteBuffer)
  {
    if (paramByteBuffer.isReadOnly()) {
      throw new ReadOnlyBufferException();
    }
    if (paramByteBuffer.hasArray()) {
      try
      {
        paramByteBuffer.position(zza(paramCharSequence, paramByteBuffer.array(), paramByteBuffer.arrayOffset() + paramByteBuffer.position(), paramByteBuffer.remaining()) - paramByteBuffer.arrayOffset());
        return;
      }
      catch (ArrayIndexOutOfBoundsException paramCharSequence)
      {
        paramByteBuffer = new BufferOverflowException();
        paramByteBuffer.initCause(paramCharSequence);
        throw paramByteBuffer;
      }
    }
    zzb(paramCharSequence, paramByteBuffer);
  }
  
  public static int zzb(int paramInt)
  {
    return zzd(paramInt << 3);
  }
  
  public static int zzb(int paramInt1, int paramInt2)
  {
    return zzb(paramInt1) + zza(paramInt2);
  }
  
  public static int zzb(int paramInt, zzfls paramZzfls)
  {
    paramInt = zzb(paramInt);
    int i = paramZzfls.zzf();
    return paramInt + (zzd(i) + i);
  }
  
  public static int zzb(int paramInt, String paramString)
  {
    return zzb(paramInt) + zza(paramString);
  }
  
  public static int zzb(int paramInt, byte[] paramArrayOfByte)
  {
    return zzb(paramInt) + zzb(paramArrayOfByte);
  }
  
  public static int zzb(byte[] paramArrayOfByte)
  {
    return zzd(paramArrayOfByte.length) + paramArrayOfByte.length;
  }
  
  private final void zzb(long paramLong)
    throws IOException
  {
    for (;;)
    {
      if ((paramLong & 0xFFFFFFFFFFFFFF80) == 0L)
      {
        zzf((int)paramLong);
        return;
      }
      zzf((int)paramLong & 0x7F | 0x80);
      paramLong >>>= 7;
    }
  }
  
  private static void zzb(CharSequence paramCharSequence, ByteBuffer paramByteBuffer)
  {
    int n = paramCharSequence.length();
    int j = 0;
    while (j < n)
    {
      int i = paramCharSequence.charAt(j);
      if (i < 128)
      {
        int k = i;
        paramByteBuffer.put((byte)k);
      }
      else
      {
        if (i < 2048) {}
        for (int m = 0x3C0 | i >>> 6;; m = i >>> 6 & 0x3F | 0x80)
        {
          paramByteBuffer.put((byte)m);
          m = i & 0x3F | 0x80;
          break;
          if ((i >= 55296) && (57343 >= i))
          {
            m = j + 1;
            if (m != paramCharSequence.length())
            {
              char c = paramCharSequence.charAt(m);
              if (!Character.isSurrogatePair(i, c))
              {
                j = m;
              }
              else
              {
                j = Character.toCodePoint(i, c);
                paramByteBuffer.put((byte)(0xF0 | j >>> 18));
                paramByteBuffer.put((byte)(j >>> 12 & 0x3F | 0x80));
                paramByteBuffer.put((byte)(j >>> 6 & 0x3F | 0x80));
                paramByteBuffer.put((byte)(j & 0x3F | 0x80));
                j = m;
                break label290;
              }
            }
            paramCharSequence = new StringBuilder(39);
            paramCharSequence.append("Unpaired surrogate at index ");
            paramCharSequence.append(j - 1);
            throw new IllegalArgumentException(paramCharSequence.toString());
          }
          paramByteBuffer.put((byte)(0x1E0 | i >>> 12));
        }
      }
      label290:
      j += 1;
    }
  }
  
  private final void zzc(long paramLong)
    throws IOException
  {
    if (zza.remaining() < 8) {
      throw new zzfll(zza.position(), zza.limit());
    }
    zza.putLong(paramLong);
  }
  
  public static int zzd(int paramInt)
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
  
  private static long zzd(long paramLong)
  {
    return paramLong << 1 ^ paramLong >> 63;
  }
  
  public static int zze(int paramInt)
  {
    return paramInt >> 31 ^ paramInt << 1;
  }
  
  public static int zze(int paramInt, long paramLong)
  {
    return zzb(paramInt) + zza(paramLong);
  }
  
  public static int zzf(int paramInt, long paramLong)
  {
    return zzb(paramInt) + zza(zzd(paramLong));
  }
  
  private final void zzf(int paramInt)
    throws IOException
  {
    byte b = (byte)paramInt;
    if (!zza.hasRemaining()) {
      throw new zzfll(zza.position(), zza.limit());
    }
    zza.put(b);
  }
  
  public final void zza()
  {
    if (zza.remaining() != 0) {
      throw new IllegalStateException(String.format("Did not write as much data as expected, %s bytes remaining.", new Object[] { Integer.valueOf(zza.remaining()) }));
    }
  }
  
  public final void zza(int paramInt, double paramDouble)
    throws IOException
  {
    zzc(paramInt, 1);
    zzc(Double.doubleToLongBits(paramDouble));
  }
  
  public final void zza(int paramInt, float paramFloat)
    throws IOException
  {
    zzc(paramInt, 5);
    paramInt = Float.floatToIntBits(paramFloat);
    if (zza.remaining() < 4) {
      throw new zzfll(zza.position(), zza.limit());
    }
    zza.putInt(paramInt);
  }
  
  public final void zza(int paramInt1, int paramInt2)
    throws IOException
  {
    zzc(paramInt1, 0);
    if (paramInt2 >= 0)
    {
      zzc(paramInt2);
      return;
    }
    zzb(paramInt2);
  }
  
  public final void zza(int paramInt, long paramLong)
    throws IOException
  {
    zzc(paramInt, 0);
    zzb(paramLong);
  }
  
  public final void zza(int paramInt, zzfls paramZzfls)
    throws IOException
  {
    zzc(paramInt, 2);
    zza(paramZzfls);
  }
  
  public final void zza(int paramInt, String paramString)
    throws IOException
  {
    zzc(paramInt, 2);
    try
    {
      paramInt = zzd(paramString.length());
      if (paramInt == zzd(paramString.length() * 3))
      {
        int i = zza.position();
        if (zza.remaining() < paramInt) {
          throw new zzfll(i + paramInt, zza.limit());
        }
        zza.position(i + paramInt);
        zza(paramString, zza);
        int j = zza.position();
        zza.position(i);
        zzc(j - i - paramInt);
        zza.position(j);
        return;
      }
      zzc(zza(paramString));
      zza(paramString, zza);
      return;
    }
    catch (BufferOverflowException paramString)
    {
      zzfll localZzfll = new zzfll(zza.position(), zza.limit());
      localZzfll.initCause(paramString);
      throw localZzfll;
    }
  }
  
  public final void zza(int paramInt, boolean paramBoolean)
    throws IOException
  {
    zzc(paramInt, 0);
    byte b = (byte)paramBoolean;
    if (!zza.hasRemaining()) {
      throw new zzfll(zza.position(), zza.limit());
    }
    zza.put(b);
  }
  
  public final void zza(int paramInt, byte[] paramArrayOfByte)
    throws IOException
  {
    zzc(paramInt, 2);
    zzc(paramArrayOfByte.length);
    zzc(paramArrayOfByte);
  }
  
  public final void zza(zzfls paramZzfls)
    throws IOException
  {
    zzc(paramZzfls.zze());
    paramZzfls.zza(this);
  }
  
  public final void zzb(int paramInt, long paramLong)
    throws IOException
  {
    zzc(paramInt, 0);
    zzb(paramLong);
  }
  
  public final void zzc(int paramInt)
    throws IOException
  {
    for (;;)
    {
      if ((paramInt & 0xFFFFFF80) == 0)
      {
        zzf(paramInt);
        return;
      }
      zzf(paramInt & 0x7F | 0x80);
      paramInt >>>= 7;
    }
  }
  
  public final void zzc(int paramInt1, int paramInt2)
    throws IOException
  {
    zzc(paramInt1 << 3 | paramInt2);
  }
  
  public final void zzc(int paramInt, long paramLong)
    throws IOException
  {
    zzc(paramInt, 1);
    zzc(paramLong);
  }
  
  public final void zzc(byte[] paramArrayOfByte)
    throws IOException
  {
    int i = paramArrayOfByte.length;
    if (zza.remaining() >= i)
    {
      zza.put(paramArrayOfByte, 0, i);
      return;
    }
    throw new zzfll(zza.position(), zza.limit());
  }
  
  public final void zzd(int paramInt, long paramLong)
    throws IOException
  {
    zzc(paramInt, 0);
    zzb(zzd(paramLong));
  }
}
