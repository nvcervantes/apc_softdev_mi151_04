package okhttp3.internal.tls;

import javax.security.auth.x500.X500Principal;

final class DistinguishedNameParser
{
  private int beg;
  private char[] chars;
  private int cur;
  private final String dn;
  private int end;
  private final int length;
  private int pos;
  
  DistinguishedNameParser(X500Principal paramX500Principal)
  {
    dn = paramX500Principal.getName("RFC2253");
    length = dn.length();
  }
  
  private String escapedAV()
  {
    beg = pos;
    end = pos;
    label208:
    do
    {
      for (;;)
      {
        if (pos >= length) {
          return new String(chars, beg, end - beg);
        }
        i = chars[pos];
        if (i == 32) {
          break label208;
        }
        if (i == 59) {
          break;
        }
        if (i != 92) {}
        switch (i)
        {
        default: 
          arrayOfChar = chars;
          i = end;
          end = (i + 1);
          arrayOfChar[i] = chars[pos];
          pos += 1;
          continue;
          arrayOfChar = chars;
          i = end;
          end = (i + 1);
          arrayOfChar[i] = getEscaped();
          pos += 1;
        }
      }
      return new String(chars, beg, end - beg);
      cur = end;
      pos += 1;
      char[] arrayOfChar = chars;
      int i = end;
      end = (i + 1);
      arrayOfChar[i] = ' ';
      while ((pos < length) && (chars[pos] == ' '))
      {
        arrayOfChar = chars;
        i = end;
        end = (i + 1);
        arrayOfChar[i] = ' ';
        pos += 1;
      }
    } while ((pos != length) && (chars[pos] != ',') && (chars[pos] != '+') && (chars[pos] != ';'));
    return new String(chars, beg, cur - beg);
  }
  
  private int getByte(int paramInt)
  {
    int i = paramInt + 1;
    if (i >= length)
    {
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("Malformed DN: ");
      localStringBuilder.append(dn);
      throw new IllegalStateException(localStringBuilder.toString());
    }
    paramInt = chars[paramInt];
    if ((paramInt >= 48) && (paramInt <= 57))
    {
      paramInt -= 48;
    }
    else if ((paramInt >= 97) && (paramInt <= 102))
    {
      paramInt -= 87;
    }
    else
    {
      if ((paramInt < 65) || (paramInt > 70)) {
        break label218;
      }
      paramInt -= 55;
    }
    i = chars[i];
    if ((i >= 48) && (i <= 57))
    {
      i -= 48;
    }
    else if ((i >= 97) && (i <= 102))
    {
      i -= 87;
    }
    else
    {
      if ((i < 65) || (i > 70)) {
        break label182;
      }
      i -= 55;
    }
    return (paramInt << 4) + i;
    label182:
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Malformed DN: ");
    localStringBuilder.append(dn);
    throw new IllegalStateException(localStringBuilder.toString());
    label218:
    localStringBuilder = new StringBuilder();
    localStringBuilder.append("Malformed DN: ");
    localStringBuilder.append(dn);
    throw new IllegalStateException(localStringBuilder.toString());
  }
  
  private char getEscaped()
  {
    pos += 1;
    if (pos == length)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Unexpected end of DN: ");
      localStringBuilder.append(dn);
      throw new IllegalStateException(localStringBuilder.toString());
    }
    int i = chars[pos];
    if ((i != 32) && (i != 37) && (i != 92) && (i != 95)) {
      switch (i)
      {
      default: 
        switch (i)
        {
        default: 
          switch (i)
          {
          default: 
            return getUTF8();
          }
          break;
        }
        break;
      }
    }
    return chars[pos];
  }
  
  private char getUTF8()
  {
    int i = getByte(pos);
    pos += 1;
    if (i < 128) {
      return (char)i;
    }
    if ((i >= 192) && (i <= 247))
    {
      int j;
      if (i <= 223)
      {
        i &= 0x1F;
        j = 1;
      }
      else if (i <= 239)
      {
        j = 2;
        i &= 0xF;
      }
      else
      {
        j = 3;
        i &= 0x7;
      }
      int k = 0;
      while (k < j)
      {
        pos += 1;
        if (pos != length)
        {
          if (chars[pos] != '\\') {
            return '?';
          }
          pos += 1;
          int m = getByte(pos);
          pos += 1;
          if ((m & 0xC0) != 128) {
            return '?';
          }
          i = (i << 6) + (m & 0x3F);
          k += 1;
        }
        else
        {
          return '?';
        }
      }
      return (char)i;
    }
    return '?';
  }
  
  private String hexAV()
  {
    if (pos + 4 >= length)
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("Unexpected end of DN: ");
      ((StringBuilder)localObject).append(dn);
      throw new IllegalStateException(((StringBuilder)localObject).toString());
    }
    beg = pos;
    int i;
    for (pos += 1; (pos != length) && (chars[pos] != '+') && (chars[pos] != ',') && (chars[pos] != ';'); pos += 1)
    {
      if (chars[pos] == ' ')
      {
        end = pos;
        for (pos += 1; (pos < length) && (chars[pos] == ' '); pos += 1) {}
      }
      if ((chars[pos] >= 'A') && (chars[pos] <= 'F'))
      {
        localObject = chars;
        i = pos;
        localObject[i] = ((char)(localObject[i] + ' '));
      }
    }
    end = pos;
    int k = end - beg;
    if ((k >= 5) && ((k & 0x1) != 0))
    {
      localObject = new byte[k / 2];
      i = 0;
      int j = beg + 1;
      while (i < localObject.length)
      {
        localObject[i] = ((byte)getByte(j));
        j += 2;
        i += 1;
      }
      return new String(chars, beg, k);
    }
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append("Unexpected end of DN: ");
    ((StringBuilder)localObject).append(dn);
    throw new IllegalStateException(((StringBuilder)localObject).toString());
  }
  
  private String nextAT()
  {
    while ((pos < length) && (chars[pos] == ' ')) {
      pos += 1;
    }
    if (pos == length) {
      return null;
    }
    beg = pos;
    for (pos += 1; (pos < length) && (chars[pos] != '=') && (chars[pos] != ' '); pos += 1) {}
    StringBuilder localStringBuilder;
    if (pos >= length)
    {
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("Unexpected end of DN: ");
      localStringBuilder.append(dn);
      throw new IllegalStateException(localStringBuilder.toString());
    }
    end = pos;
    if (chars[pos] == ' ')
    {
      while ((pos < length) && (chars[pos] != '=') && (chars[pos] == ' ')) {
        pos += 1;
      }
      if ((chars[pos] != '=') || (pos == length))
      {
        localStringBuilder = new StringBuilder();
        localStringBuilder.append("Unexpected end of DN: ");
        localStringBuilder.append(dn);
        throw new IllegalStateException(localStringBuilder.toString());
      }
    }
    for (pos += 1; (pos < length) && (chars[pos] == ' '); pos += 1) {}
    if ((end - beg > 4) && (chars[(beg + 3)] == '.') && ((chars[beg] == 'O') || (chars[beg] == 'o')) && ((chars[(beg + 1)] == 'I') || (chars[(beg + 1)] == 'i')) && ((chars[(beg + 2)] == 'D') || (chars[(beg + 2)] == 'd'))) {
      beg += 4;
    }
    return new String(chars, beg, end - beg);
  }
  
  private String quotedAV()
  {
    pos += 1;
    beg = pos;
    for (end = beg;; end += 1)
    {
      if (pos == length)
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("Unexpected end of DN: ");
        localStringBuilder.append(dn);
        throw new IllegalStateException(localStringBuilder.toString());
      }
      if (chars[pos] == '"')
      {
        for (pos += 1; (pos < length) && (chars[pos] == ' '); pos += 1) {}
        return new String(chars, beg, end - beg);
      }
      if (chars[pos] == '\\') {
        chars[end] = getEscaped();
      } else {
        chars[end] = chars[pos];
      }
      pos += 1;
    }
  }
  
  public String findMostSpecific(String paramString)
  {
    pos = 0;
    beg = 0;
    end = 0;
    cur = 0;
    chars = dn.toCharArray();
    String str1 = nextAT();
    String str2 = str1;
    if (str1 == null) {
      return null;
    }
    do
    {
      str1 = "";
      if (pos == length) {
        return null;
      }
      switch (chars[pos])
      {
      default: 
        str1 = escapedAV();
        break;
      case '#': 
        str1 = hexAV();
        break;
      case '"': 
        str1 = quotedAV();
      }
      if (paramString.equalsIgnoreCase(str2)) {
        return str1;
      }
      if (pos >= length) {
        return null;
      }
      if ((chars[pos] != ',') && (chars[pos] != ';') && (chars[pos] != '+'))
      {
        paramString = new StringBuilder();
        paramString.append("Malformed DN: ");
        paramString.append(dn);
        throw new IllegalStateException(paramString.toString());
      }
      pos += 1;
      str1 = nextAT();
      str2 = str1;
    } while (str1 != null);
    paramString = new StringBuilder();
    paramString.append("Malformed DN: ");
    paramString.append(dn);
    throw new IllegalStateException(paramString.toString());
  }
}
