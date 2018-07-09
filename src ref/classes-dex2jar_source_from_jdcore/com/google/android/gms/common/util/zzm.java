package com.google.android.gms.common.util;

public final class zzm
{
  private static final char[] zza = { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70 };
  private static final char[] zzb = { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102 };
  
  public static String zza(byte[] paramArrayOfByte)
  {
    int i = 0;
    int j = paramArrayOfByte.length;
    StringBuilder localStringBuilder = new StringBuilder(j << 1);
    while (i < j)
    {
      localStringBuilder.append(zza[((paramArrayOfByte[i] & 0xF0) >>> 4)]);
      localStringBuilder.append(zza[(paramArrayOfByte[i] & 0xF)]);
      i += 1;
    }
    return localStringBuilder.toString();
  }
  
  public static String zzb(byte[] paramArrayOfByte)
  {
    int i = 0;
    char[] arrayOfChar = new char[paramArrayOfByte.length << 1];
    int j = 0;
    while (i < paramArrayOfByte.length)
    {
      int k = paramArrayOfByte[i] & 0xFF;
      int m = j + 1;
      arrayOfChar[j] = zzb[(k >>> 4)];
      j = m + 1;
      arrayOfChar[m] = zzb[(k & 0xF)];
      i += 1;
    }
    return new String(arrayOfChar);
  }
}
