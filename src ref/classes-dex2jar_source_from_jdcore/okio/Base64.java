package okio;

import java.io.UnsupportedEncodingException;

final class Base64
{
  private static final byte[] MAP = { 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47 };
  private static final byte[] URL_MAP = { 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 45, 95 };
  
  private Base64() {}
  
  public static byte[] decode(String paramString)
  {
    int m = paramString.length();
    while (m > 0)
    {
      i = paramString.charAt(m - 1);
      if ((i != 61) && (i != 10) && (i != 13) && (i != 32) && (i != 9)) {
        break;
      }
      m -= 1;
    }
    byte[] arrayOfByte = new byte[(int)(m * 6L / 8L)];
    int i2 = 0;
    int i = i2;
    int k = i;
    int j = k;
    int n = k;
    int i1 = i;
    k = i2;
    while (k < m)
    {
      int i4 = paramString.charAt(k);
      if ((i4 >= 65) && (i4 <= 90))
      {
        i = i4 - 65;
      }
      else if ((i4 >= 97) && (i4 <= 122))
      {
        i = i4 - 71;
      }
      else if ((i4 >= 48) && (i4 <= 57))
      {
        i = i4 + 4;
      }
      else if ((i4 != 43) && (i4 != 45))
      {
        if ((i4 != 47) && (i4 != 95))
        {
          i = i1;
          i2 = n;
          i3 = j;
          if (i4 == 10) {
            break label374;
          }
          i = i1;
          i2 = n;
          i3 = j;
          if (i4 == 13) {
            break label374;
          }
          i = i1;
          i2 = n;
          i3 = j;
          if (i4 == 32) {
            break label374;
          }
          if (i4 == 9)
          {
            i = i1;
            i2 = n;
            i3 = j;
            break label374;
          }
          return null;
        }
        i = 63;
      }
      else
      {
        i = 62;
      }
      n = n << 6 | (byte)i;
      i1 += 1;
      i = i1;
      i2 = n;
      int i3 = j;
      if (i1 % 4 == 0)
      {
        i = j + 1;
        arrayOfByte[j] = ((byte)(n >> 16));
        j = i + 1;
        arrayOfByte[i] = ((byte)(n >> 8));
        arrayOfByte[j] = ((byte)n);
        i3 = j + 1;
        i2 = n;
        i = i1;
      }
      label374:
      k += 1;
      i1 = i;
      n = i2;
      j = i3;
    }
    k = i1 % 4;
    if (k == 1) {
      return null;
    }
    if (k == 2)
    {
      arrayOfByte[j] = ((byte)(n << 12 >> 16));
      i = j + 1;
    }
    else
    {
      i = j;
      if (k == 3)
      {
        k = n << 6;
        m = j + 1;
        arrayOfByte[j] = ((byte)(k >> 16));
        i = m + 1;
        arrayOfByte[m] = ((byte)(k >> 8));
      }
    }
    if (i == arrayOfByte.length) {
      return arrayOfByte;
    }
    paramString = new byte[i];
    System.arraycopy(arrayOfByte, 0, paramString, 0, i);
    return paramString;
  }
  
  public static String encode(byte[] paramArrayOfByte)
  {
    return encode(paramArrayOfByte, MAP);
  }
  
  private static String encode(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
  {
    int i = 0;
    byte[] arrayOfByte = new byte[(paramArrayOfByte1.length + 2) / 3 * 4];
    int k = paramArrayOfByte1.length - paramArrayOfByte1.length % 3;
    int j = 0;
    int m;
    while (i < k)
    {
      m = j + 1;
      arrayOfByte[j] = paramArrayOfByte2[((paramArrayOfByte1[i] & 0xFF) >> 2)];
      j = m + 1;
      int i1 = paramArrayOfByte1[i];
      int n = i + 1;
      arrayOfByte[m] = paramArrayOfByte2[((i1 & 0x3) << 4 | (paramArrayOfByte1[n] & 0xFF) >> 4)];
      m = j + 1;
      i1 = paramArrayOfByte1[n];
      n = i + 2;
      arrayOfByte[j] = paramArrayOfByte2[((i1 & 0xF) << 2 | (paramArrayOfByte1[n] & 0xFF) >> 6)];
      j = m + 1;
      arrayOfByte[m] = paramArrayOfByte2[(paramArrayOfByte1[n] & 0x3F)];
      i += 3;
    }
    switch (paramArrayOfByte1.length % 3)
    {
    default: 
      break;
    case 2: 
      i = j + 1;
      arrayOfByte[j] = paramArrayOfByte2[((paramArrayOfByte1[k] & 0xFF) >> 2)];
      j = i + 1;
      m = paramArrayOfByte1[k];
      k += 1;
      arrayOfByte[i] = paramArrayOfByte2[((m & 0x3) << 4 | (paramArrayOfByte1[k] & 0xFF) >> 4)];
      arrayOfByte[j] = paramArrayOfByte2[((paramArrayOfByte1[k] & 0xF) << 2)];
      arrayOfByte[(j + 1)] = 61;
      break;
    case 1: 
      i = j + 1;
      arrayOfByte[j] = paramArrayOfByte2[((paramArrayOfByte1[k] & 0xFF) >> 2)];
      j = i + 1;
      arrayOfByte[i] = paramArrayOfByte2[((paramArrayOfByte1[k] & 0x3) << 4)];
      arrayOfByte[j] = 61;
      arrayOfByte[(j + 1)] = 61;
    }
    try
    {
      paramArrayOfByte1 = new String(arrayOfByte, "US-ASCII");
      return paramArrayOfByte1;
    }
    catch (UnsupportedEncodingException paramArrayOfByte1)
    {
      throw new AssertionError(paramArrayOfByte1);
    }
  }
  
  public static String encodeUrl(byte[] paramArrayOfByte)
  {
    return encode(paramArrayOfByte, URL_MAP);
  }
}
