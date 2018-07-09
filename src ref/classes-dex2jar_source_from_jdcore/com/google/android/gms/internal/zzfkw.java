package com.google.android.gms.internal;

final class zzfkw
  extends zzfkt
{
  zzfkw() {}
  
  private static int zza(byte[] paramArrayOfByte, int paramInt1, long paramLong, int paramInt2)
  {
    switch (paramInt2)
    {
    default: 
      throw new AssertionError();
    case 2: 
      return zzfks.zza(paramInt1, zzfkq.zza(paramArrayOfByte, paramLong), zzfkq.zza(paramArrayOfByte, paramLong + 1L));
    case 1: 
      return zzfks.zza(paramInt1, zzfkq.zza(paramArrayOfByte, paramLong));
    }
    return zzfks.zza(paramInt1);
  }
  
  private static int zza(byte[] paramArrayOfByte, long paramLong, int paramInt)
  {
    int i;
    long l1;
    if (paramInt < 16)
    {
      i = 0;
    }
    else
    {
      l1 = paramLong;
      i = 0;
      while (i < paramInt)
      {
        if (zzfkq.zza(paramArrayOfByte, l1) < 0) {
          break label54;
        }
        i += 1;
        l1 += 1L;
      }
      i = paramInt;
    }
    label54:
    paramInt -= i;
    paramLong += i;
    label173:
    label269:
    do
    {
      long l2;
      do
      {
        for (;;)
        {
          j = 0;
          i = paramInt;
          paramInt = j;
          for (;;)
          {
            l1 = paramLong;
            if (i <= 0) {
              break;
            }
            l1 = paramLong + 1L;
            paramInt = zzfkq.zza(paramArrayOfByte, paramLong);
            if (paramInt < 0) {
              break;
            }
            i -= 1;
            paramLong = l1;
          }
          if (i == 0) {
            return 0;
          }
          i -= 1;
          if (paramInt >= -32) {
            break label173;
          }
          if (i == 0) {
            return paramInt;
          }
          i -= 1;
          if (paramInt < -62) {
            break;
          }
          if (zzfkq.zza(paramArrayOfByte, l1) > -65) {
            return -1;
          }
          paramLong = l1 + 1L;
          paramInt = i;
        }
        return -1;
        if (paramInt >= -16) {
          break label269;
        }
        if (i < 2) {
          return zza(paramArrayOfByte, paramInt, l1, i);
        }
        i -= 2;
        l2 = l1 + 1L;
        j = zzfkq.zza(paramArrayOfByte, l1);
        if ((j > -65) || ((paramInt == -32) && (j < -96)) || ((paramInt == -19) && (j >= -96))) {
          break;
        }
        paramLong = l2 + 1L;
        paramInt = i;
      } while (zzfkq.zza(paramArrayOfByte, l2) <= -65);
      return -1;
      if (i < 3) {
        return zza(paramArrayOfByte, paramInt, l1, i);
      }
      i -= 3;
      paramLong = l1 + 1L;
      int j = zzfkq.zza(paramArrayOfByte, l1);
      if ((j > -65) || ((paramInt << 28) + (j + 112) >> 30 != 0)) {
        break;
      }
      l1 = paramLong + 1L;
      if (zzfkq.zza(paramArrayOfByte, paramLong) > -65) {
        break;
      }
      paramLong = l1 + 1L;
      paramInt = i;
    } while (zzfkq.zza(paramArrayOfByte, l1) <= -65);
    return -1;
  }
  
  final int zza(int paramInt1, byte[] paramArrayOfByte, int paramInt2, int paramInt3)
  {
    if ((paramInt2 | paramInt3 | paramArrayOfByte.length - paramInt3) < 0) {
      throw new ArrayIndexOutOfBoundsException(String.format("Array length=%d, index=%d, limit=%d", new Object[] { Integer.valueOf(paramArrayOfByte.length), Integer.valueOf(paramInt2), Integer.valueOf(paramInt3) }));
    }
    long l = paramInt2;
    return zza(paramArrayOfByte, l, (int)(paramInt3 - l));
  }
  
  final int zza(CharSequence paramCharSequence, byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    long l1 = paramInt1;
    long l3 = l1 + paramInt2;
    int j = paramCharSequence.length();
    if ((j <= paramInt2) && (paramArrayOfByte.length - paramInt2 >= paramInt1))
    {
      paramInt2 = 0;
      while (paramInt2 < j)
      {
        paramInt1 = paramCharSequence.charAt(paramInt2);
        if (paramInt1 >= 128) {
          break;
        }
        zzfkq.zza(paramArrayOfByte, l1, (byte)paramInt1);
        paramInt2 += 1;
        l1 += 1L;
      }
      paramInt1 = paramInt2;
      long l2 = l1;
      if (paramInt2 == j) {
        return (int)l1;
      }
      while (paramInt1 < j)
      {
        int i = paramCharSequence.charAt(paramInt1);
        if ((i < 128) && (l2 < l3))
        {
          zzfkq.zza(paramArrayOfByte, l2, (byte)i);
          l1 = l2 + 1L;
        }
        else
        {
          long l4;
          if ((i < 2048) && (l2 <= l3 - 2L))
          {
            l4 = l2 + 1L;
            zzfkq.zza(paramArrayOfByte, l2, (byte)(0x3C0 | i >>> 6));
            l1 = l4 + 1L;
            zzfkq.zza(paramArrayOfByte, l4, (byte)(i & 0x3F | 0x80));
          }
          else if (((i < 55296) || (57343 < i)) && (l2 <= l3 - 3L))
          {
            l1 = l2 + 1L;
            zzfkq.zza(paramArrayOfByte, l2, (byte)(0x1E0 | i >>> 12));
            l2 = l1 + 1L;
            zzfkq.zza(paramArrayOfByte, l1, (byte)(i >>> 6 & 0x3F | 0x80));
            zzfkq.zza(paramArrayOfByte, l2, (byte)(i & 0x3F | 0x80));
            l1 = l2 + 1L;
          }
          else
          {
            if (l2 > l3 - 4L) {
              break label495;
            }
            paramInt2 = paramInt1 + 1;
            if (paramInt2 == j) {
              break label482;
            }
            char c2 = paramCharSequence.charAt(paramInt2);
            if (!Character.isSurrogatePair(i, c2))
            {
              paramInt1 = paramInt2;
              break label482;
            }
            paramInt1 = Character.toCodePoint(i, c2);
            l1 = l2 + 1L;
            zzfkq.zza(paramArrayOfByte, l2, (byte)(0xF0 | paramInt1 >>> 18));
            l2 = l1 + 1L;
            zzfkq.zza(paramArrayOfByte, l1, (byte)(paramInt1 >>> 12 & 0x3F | 0x80));
            l4 = l2 + 1L;
            zzfkq.zza(paramArrayOfByte, l2, (byte)(paramInt1 >>> 6 & 0x3F | 0x80));
            l1 = l4 + 1L;
            zzfkq.zza(paramArrayOfByte, l4, (byte)(paramInt1 & 0x3F | 0x80));
            paramInt1 = paramInt2;
          }
        }
        paramInt1 += 1;
        l2 = l1;
        continue;
        label482:
        throw new zzfkv(paramInt1 - 1, j);
        label495:
        if ((55296 <= i) && (i <= 57343))
        {
          paramInt2 = paramInt1 + 1;
          if ((paramInt2 == j) || (!Character.isSurrogatePair(i, paramCharSequence.charAt(paramInt2)))) {
            throw new zzfkv(paramInt1, j);
          }
        }
        paramCharSequence = new StringBuilder(46);
        paramCharSequence.append("Failed writing ");
        paramCharSequence.append(i);
        paramCharSequence.append(" at index ");
        paramCharSequence.append(l2);
        throw new ArrayIndexOutOfBoundsException(paramCharSequence.toString());
      }
      return (int)l2;
    }
    char c1 = paramCharSequence.charAt(j - 1);
    paramCharSequence = new StringBuilder(37);
    paramCharSequence.append("Failed writing ");
    paramCharSequence.append(c1);
    paramCharSequence.append(" at index ");
    paramCharSequence.append(paramInt1 + paramInt2);
    throw new ArrayIndexOutOfBoundsException(paramCharSequence.toString());
  }
}
