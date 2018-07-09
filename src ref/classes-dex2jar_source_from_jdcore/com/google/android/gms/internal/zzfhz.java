package com.google.android.gms.internal;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public final class zzfhz
{
  static final Charset zza = Charset.forName("UTF-8");
  public static final byte[] zzb;
  private static Charset zzc = Charset.forName("ISO-8859-1");
  private static ByteBuffer zzd;
  private static zzfhb zze = zzfhb.zza(zzb);
  
  static
  {
    byte[] arrayOfByte = new byte[0];
    zzb = arrayOfByte;
    zzd = ByteBuffer.wrap(arrayOfByte);
  }
  
  static int zza(int paramInt1, byte[] paramArrayOfByte, int paramInt2, int paramInt3)
  {
    int i = paramInt1;
    paramInt1 = paramInt2;
    while (paramInt1 < paramInt2 + paramInt3)
    {
      i = i * 31 + paramArrayOfByte[paramInt1];
      paramInt1 += 1;
    }
    return i;
  }
  
  public static int zza(long paramLong)
  {
    return (int)(paramLong ^ paramLong >>> 32);
  }
  
  public static int zza(boolean paramBoolean)
  {
    if (paramBoolean) {
      return 1231;
    }
    return 1237;
  }
  
  public static int zza(byte[] paramArrayOfByte)
  {
    int i = paramArrayOfByte.length;
    int j = zza(i, paramArrayOfByte, 0, i);
    i = j;
    if (j == 0) {
      i = 1;
    }
    return i;
  }
  
  static <T> T zza(T paramT)
  {
    if (paramT == null) {
      throw new NullPointerException();
    }
    return paramT;
  }
  
  static <T> T zza(T paramT, String paramString)
  {
    if (paramT == null) {
      throw new NullPointerException(paramString);
    }
    return paramT;
  }
  
  static boolean zza(zzfjc paramZzfjc)
  {
    return false;
  }
}
