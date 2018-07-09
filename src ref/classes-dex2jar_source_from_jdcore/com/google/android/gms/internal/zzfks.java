package com.google.android.gms.internal;

final class zzfks
{
  private static final zzfkt zza;
  
  static
  {
    int i;
    if ((zzfkq.zza()) && (zzfkq.zzb())) {
      i = 1;
    } else {
      i = 0;
    }
    Object localObject;
    if (i != 0) {
      localObject = new zzfkw();
    } else {
      localObject = new zzfku();
    }
    zza = (zzfkt)localObject;
  }
  
  static int zza(CharSequence paramCharSequence)
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
        break label205;
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
            if (Character.codePointAt(paramCharSequence, j) < 65536) {
              throw new zzfkv(j, i2);
            }
            m = j + 1;
            k = i1;
          }
        }
      }
      j = m + 1;
    }
    k = i + k;
    label205:
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
  
  static int zza(CharSequence paramCharSequence, byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    return zza.zza(paramCharSequence, paramArrayOfByte, paramInt1, paramInt2);
  }
  
  public static boolean zza(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    return zza.zza(0, paramArrayOfByte, paramInt1, paramInt2) == 0;
  }
  
  private static int zzb(int paramInt)
  {
    int i = paramInt;
    if (paramInt > -12) {
      i = -1;
    }
    return i;
  }
  
  private static int zzb(int paramInt1, int paramInt2)
  {
    if ((paramInt1 <= -12) && (paramInt2 <= -65)) {
      return paramInt1 ^ paramInt2 << 8;
    }
    return -1;
  }
  
  private static int zzb(int paramInt1, int paramInt2, int paramInt3)
  {
    if ((paramInt1 <= -12) && (paramInt2 <= -65) && (paramInt3 <= -65)) {
      return paramInt1 ^ paramInt2 << 8 ^ paramInt3 << 16;
    }
    return -1;
  }
  
  private static int zzc(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    int i = paramArrayOfByte[(paramInt1 - 1)];
    switch (paramInt2 - paramInt1)
    {
    default: 
      throw new AssertionError();
    case 2: 
      return zzb(i, paramArrayOfByte[paramInt1], paramArrayOfByte[(paramInt1 + 1)]);
    case 1: 
      return zzb(i, paramArrayOfByte[paramInt1]);
    }
    return zzb(i);
  }
}
