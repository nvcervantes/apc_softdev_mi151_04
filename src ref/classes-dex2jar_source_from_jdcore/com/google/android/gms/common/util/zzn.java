package com.google.android.gms.common.util;

public final class zzn
{
  public static String zza(byte[] paramArrayOfByte, int paramInt1, int paramInt2, boolean paramBoolean)
  {
    if ((paramArrayOfByte != null) && (paramArrayOfByte.length != 0) && (paramInt2 > 0) && (paramInt2 <= paramArrayOfByte.length))
    {
      StringBuilder localStringBuilder = new StringBuilder(57 * ((paramInt2 + 16 - 1) / 16));
      paramInt1 = 0;
      int i = paramInt1;
      int j = paramInt2;
      while (j > 0)
      {
        Object[] arrayOfObject;
        if (paramInt1 == 0) {
          if (paramInt2 < 65536)
          {
            str = "%04X:";
            arrayOfObject = new Object[1];
            arrayOfObject[0] = Integer.valueOf(i);
          }
        }
        for (String str = String.format(str, arrayOfObject);; str = " -")
        {
          localStringBuilder.append(str);
          break label140;
          str = "%08X:";
          arrayOfObject = new Object[1];
          arrayOfObject[0] = Integer.valueOf(i);
          break;
          if (paramInt1 != 8) {
            break label140;
          }
        }
        label140:
        localStringBuilder.append(String.format(" %02X", new Object[] { Integer.valueOf(paramArrayOfByte[i] & 0xFF) }));
        j -= 1;
        paramInt1 += 1;
        if ((paramInt1 == 16) || (j == 0))
        {
          localStringBuilder.append('\n');
          paramInt1 = 0;
        }
        i += 1;
      }
      return localStringBuilder.toString();
    }
    return null;
  }
}
