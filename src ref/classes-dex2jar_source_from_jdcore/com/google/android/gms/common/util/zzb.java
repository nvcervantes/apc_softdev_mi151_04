package com.google.android.gms.common.util;

import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.internal.zzbg;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

@Hide
public final class zzb
{
  public static <T> ArrayList<T> zza(T[] paramArrayOfT)
  {
    int i = 0;
    int j = paramArrayOfT.length;
    ArrayList localArrayList = new ArrayList(j);
    while (i < j)
    {
      localArrayList.add(paramArrayOfT[i]);
      i += 1;
    }
    return localArrayList;
  }
  
  public static void zza(StringBuilder paramStringBuilder, double[] paramArrayOfDouble)
  {
    int i = 0;
    int j = paramArrayOfDouble.length;
    while (i < j)
    {
      if (i != 0) {
        paramStringBuilder.append(",");
      }
      paramStringBuilder.append(Double.toString(paramArrayOfDouble[i]));
      i += 1;
    }
  }
  
  public static void zza(StringBuilder paramStringBuilder, float[] paramArrayOfFloat)
  {
    int i = 0;
    int j = paramArrayOfFloat.length;
    while (i < j)
    {
      if (i != 0) {
        paramStringBuilder.append(",");
      }
      paramStringBuilder.append(Float.toString(paramArrayOfFloat[i]));
      i += 1;
    }
  }
  
  public static void zza(StringBuilder paramStringBuilder, long[] paramArrayOfLong)
  {
    int i = 0;
    int j = paramArrayOfLong.length;
    while (i < j)
    {
      if (i != 0) {
        paramStringBuilder.append(",");
      }
      paramStringBuilder.append(Long.toString(paramArrayOfLong[i]));
      i += 1;
    }
  }
  
  public static <T> void zza(StringBuilder paramStringBuilder, T[] paramArrayOfT)
  {
    int i = 0;
    int j = paramArrayOfT.length;
    while (i < j)
    {
      if (i != 0) {
        paramStringBuilder.append(",");
      }
      paramStringBuilder.append(paramArrayOfT[i].toString());
      i += 1;
    }
  }
  
  public static void zza(StringBuilder paramStringBuilder, String[] paramArrayOfString)
  {
    int i = 0;
    int j = paramArrayOfString.length;
    while (i < j)
    {
      if (i != 0) {
        paramStringBuilder.append(",");
      }
      paramStringBuilder.append("\"");
      paramStringBuilder.append(paramArrayOfString[i]);
      paramStringBuilder.append("\"");
      i += 1;
    }
  }
  
  public static void zza(StringBuilder paramStringBuilder, boolean[] paramArrayOfBoolean)
  {
    int i = 0;
    int j = paramArrayOfBoolean.length;
    while (i < j)
    {
      if (i != 0) {
        paramStringBuilder.append(",");
      }
      paramStringBuilder.append(Boolean.toString(paramArrayOfBoolean[i]));
      i += 1;
    }
  }
  
  public static byte[] zza(byte[]... paramVarArgs)
  {
    if (paramVarArgs.length == 0) {
      return new byte[0];
    }
    int i = 0;
    int j = i;
    while (i < paramVarArgs.length)
    {
      j += paramVarArgs[i].length;
      i += 1;
    }
    byte[] arrayOfByte1 = Arrays.copyOf(paramVarArgs[0], j);
    byte[] arrayOfByte2 = paramVarArgs[0];
    i = 1;
    j = arrayOfByte2.length;
    while (i < paramVarArgs.length)
    {
      arrayOfByte2 = paramVarArgs[i];
      System.arraycopy(arrayOfByte2, 0, arrayOfByte1, j, arrayOfByte2.length);
      j += arrayOfByte2.length;
      i += 1;
    }
    return arrayOfByte1;
  }
  
  public static Integer[] zza(int[] paramArrayOfInt)
  {
    if (paramArrayOfInt == null) {
      return null;
    }
    int j = paramArrayOfInt.length;
    Integer[] arrayOfInteger = new Integer[j];
    int i = 0;
    while (i < j)
    {
      arrayOfInteger[i] = Integer.valueOf(paramArrayOfInt[i]);
      i += 1;
    }
    return arrayOfInteger;
  }
  
  public static <T> T[] zza(T[] paramArrayOfT1, T... paramVarArgs)
  {
    if (paramArrayOfT1 == null) {
      return null;
    }
    if (paramVarArgs.length == 0) {
      return Arrays.copyOf(paramArrayOfT1, paramArrayOfT1.length);
    }
    Object[] arrayOfObject = (Object[])Array.newInstance(paramVarArgs.getClass().getComponentType(), paramArrayOfT1.length);
    int m;
    int j;
    T ?;
    if (paramVarArgs.length == 1)
    {
      m = paramArrayOfT1.length;
      j = 0;
      for (i = j;; i = k)
      {
        k = i;
        if (j >= m) {
          break;
        }
        ? = paramArrayOfT1[j];
        k = i;
        if (!zzbg.zza(paramVarArgs[0], ?))
        {
          arrayOfObject[i] = ?;
          k = i + 1;
        }
        j += 1;
      }
    }
    int n = paramArrayOfT1.length;
    int k = 0;
    for (int i = k; k < n; i = j)
    {
      ? = paramArrayOfT1[k];
      m = paramVarArgs.length;
      j = 0;
      while (j < m)
      {
        if (zzbg.zza(paramVarArgs[j], ?)) {
          break label156;
        }
        j += 1;
      }
      j = -1;
      label156:
      if (j >= 0) {
        m = 1;
      } else {
        m = 0;
      }
      j = i;
      if (m == 0)
      {
        arrayOfObject[i] = ?;
        j = i + 1;
      }
      k += 1;
    }
    k = i;
    if (arrayOfObject == null) {
      return null;
    }
    paramArrayOfT1 = arrayOfObject;
    if (k != arrayOfObject.length) {
      paramArrayOfT1 = Arrays.copyOf(arrayOfObject, k);
    }
    return paramArrayOfT1;
  }
}
