package com.google.android.gms.internal;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public final class zzbfo
{
  private static int zza(byte[] paramArrayOfByte, int paramInt)
  {
    int i = paramArrayOfByte[paramInt];
    int j = paramArrayOfByte[(paramInt + 1)];
    int k = paramArrayOfByte[(paramInt + 2)];
    return (paramArrayOfByte[(paramInt + 3)] & 0xFF) << 24 | i & 0xFF | (j & 0xFF) << 8 | (k & 0xFF) << 16;
  }
  
  private static long zza(long paramLong1, long paramLong2, long paramLong3)
  {
    paramLong1 = (paramLong1 ^ paramLong2) * paramLong3;
    paramLong1 = (paramLong2 ^ paramLong1 ^ paramLong1 >>> 47) * paramLong3;
    return (paramLong1 ^ paramLong1 >>> 47) * paramLong3;
  }
  
  public static long zza(byte[] paramArrayOfByte)
  {
    int i = paramArrayOfByte.length;
    if ((i >= 0) && (i <= paramArrayOfByte.length))
    {
      long l1;
      long l2;
      long l3;
      long l4;
      long l5;
      if (i <= 32)
      {
        if (i <= 16)
        {
          if (i >= 8)
          {
            l1 = -7286425919675154353L + (i << 1);
            l2 = zzb(paramArrayOfByte, 0) - 7286425919675154353L;
            l3 = zzb(paramArrayOfByte, i + 0 - 8);
            return zza(Long.rotateRight(l3, 37) * l1 + l2, (Long.rotateRight(l2, 25) + l3) * l1, l1);
          }
          if (i >= 4)
          {
            l1 = i << 1;
            l2 = zza(paramArrayOfByte, 0);
            return zza(i + ((l2 & 0xFFFFFFFF) << 3), zza(paramArrayOfByte, i + 0 - 4) & 0xFFFFFFFF, -7286425919675154353L + l1);
          }
          if (i > 0)
          {
            int j = paramArrayOfByte[0];
            int k = paramArrayOfByte[((i >> 1) + 0)];
            int m = paramArrayOfByte[(0 + (i - 1))];
            l1 = ((j & 0xFF) + ((k & 0xFF) << 8)) * -7286425919675154353L ^ (i + ((m & 0xFF) << 2)) * -4348849565147123417L;
            return (l1 ^ l1 >>> 47) * -7286425919675154353L;
          }
          return -7286425919675154353L;
        }
        l1 = -7286425919675154353L + (i << 1);
        l2 = zzb(paramArrayOfByte, 0) * -5435081209227447693L;
        l3 = zzb(paramArrayOfByte, 8);
        i += 0;
        l4 = zzb(paramArrayOfByte, i - 8) * l1;
        l5 = zzb(paramArrayOfByte, i - 16);
        return zza(Long.rotateRight(l2 + l3, 43) + Long.rotateRight(l4, 30) + l5 * -7286425919675154353L, l2 + Long.rotateRight(l3 - 7286425919675154353L, 18) + l4, l1);
      }
      if (i <= 64)
      {
        l1 = -7286425919675154353L + (i << 1);
        l2 = zzb(paramArrayOfByte, 0) * -7286425919675154353L;
        l4 = zzb(paramArrayOfByte, 8);
        i += 0;
        l5 = zzb(paramArrayOfByte, i - 8) * l1;
        l3 = zzb(paramArrayOfByte, i - 16);
        l3 = Long.rotateRight(l2 + l4, 43) + Long.rotateRight(l5, 30) + l3 * -7286425919675154353L;
        l4 = zza(l3, l2 + Long.rotateRight(l4 - 7286425919675154353L, 18) + l5, l1);
        l5 = zzb(paramArrayOfByte, 16) * l1;
        long l6 = zzb(paramArrayOfByte, 24);
        l3 = (l3 + zzb(paramArrayOfByte, i - 32)) * l1;
        long l7 = zzb(paramArrayOfByte, i - 24);
        return zza(Long.rotateRight(l5 + l6, 43) + Long.rotateRight(l3, 30) + (l4 + l7) * l1, l5 + Long.rotateRight(l6 + l2, 18) + l3, l1);
      }
      return zza(paramArrayOfByte, 0, i);
    }
    paramArrayOfByte = new StringBuilder(67);
    paramArrayOfByte.append("Out of bound index with offput: 0 and length: ");
    paramArrayOfByte.append(i);
    throw new IndexOutOfBoundsException(paramArrayOfByte.toString());
  }
  
  private static long zza(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    long[] arrayOfLong1 = new long[2];
    long[] arrayOfLong2 = new long[2];
    long l3 = 95310865018149119L + zzb(paramArrayOfByte, 0);
    int i = paramInt2 - 1;
    paramInt2 = 0 + (i / 64 << 6);
    i &= 0x3F;
    int j = paramInt2 + i - 63;
    long l2 = 2480279821605975764L;
    long l4;
    for (long l1 = 1390051526045402406L;; l1 = l4)
    {
      byte[] arrayOfByte = paramArrayOfByte;
      l3 = Long.rotateRight(l3 + l2 + arrayOfLong1[0] + zzb(arrayOfByte, paramInt1 + 8), 37);
      l2 = Long.rotateRight(l2 + arrayOfLong1[1] + zzb(arrayOfByte, paramInt1 + 48), 42);
      l4 = l3 * -5435081209227447693L ^ arrayOfLong2[1];
      l2 = l2 * -5435081209227447693L + (arrayOfLong1[0] + zzb(paramArrayOfByte, paramInt1 + 40));
      l3 = Long.rotateRight(l1 + arrayOfLong2[0], 33) * -5435081209227447693L;
      zza(paramArrayOfByte, paramInt1, arrayOfLong1[1] * -5435081209227447693L, l4 + arrayOfLong2[0], arrayOfLong1);
      zza(paramArrayOfByte, paramInt1 + 32, l3 + arrayOfLong2[1], l2 + zzb(paramArrayOfByte, paramInt1 + 16), arrayOfLong2);
      paramInt1 += 64;
      if (paramInt1 == paramInt2)
      {
        l1 = -5435081209227447693L + ((l4 & 0xFF) << 1);
        arrayOfLong2[0] += i;
        arrayOfLong1[0] += arrayOfLong2[0];
        arrayOfLong2[0] += arrayOfLong1[0];
        l3 = Long.rotateRight(l3 + l2 + arrayOfLong1[0] + zzb(paramArrayOfByte, j + 8), 37);
        long l5 = Long.rotateRight(l2 + arrayOfLong1[1] + zzb(paramArrayOfByte, j + 48), 42);
        l2 = l3 * l1 ^ arrayOfLong2[1] * 9L;
        l3 = l5 * l1 + (arrayOfLong1[0] * 9L + zzb(paramArrayOfByte, j + 40));
        l4 = Long.rotateRight(l4 + arrayOfLong2[0], 33) * l1;
        zza(paramArrayOfByte, j, arrayOfLong1[1] * l1, l2 + arrayOfLong2[0], arrayOfLong1);
        zza(paramArrayOfByte, j + 32, l4 + arrayOfLong2[1], l3 + zzb(paramArrayOfByte, j + 16), arrayOfLong2);
        return zza(zza(arrayOfLong1[0], arrayOfLong2[0], l1) + (l3 ^ l3 >>> 47) * -4348849565147123417L + l2, zza(arrayOfLong1[1], arrayOfLong2[1], l1) + l4, l1);
      }
    }
  }
  
  private static void zza(byte[] paramArrayOfByte, int paramInt, long paramLong1, long paramLong2, long[] paramArrayOfLong)
  {
    long l4 = zzb(paramArrayOfByte, paramInt);
    long l2 = zzb(paramArrayOfByte, paramInt + 8);
    long l3 = zzb(paramArrayOfByte, paramInt + 16);
    long l1 = zzb(paramArrayOfByte, paramInt + 24);
    paramLong1 += l4;
    paramLong2 = Long.rotateRight(paramLong2 + paramLong1 + l1, 21);
    l2 = paramLong1 + l2 + l3;
    l3 = Long.rotateRight(l2, 44);
    paramArrayOfLong[0] = (l2 + l1);
    paramArrayOfLong[1] = (paramLong2 + l3 + paramLong1);
  }
  
  private static long zzb(byte[] paramArrayOfByte, int paramInt)
  {
    paramArrayOfByte = ByteBuffer.wrap(paramArrayOfByte, paramInt, 8);
    paramArrayOfByte.order(ByteOrder.LITTLE_ENDIAN);
    return paramArrayOfByte.getLong();
  }
}
