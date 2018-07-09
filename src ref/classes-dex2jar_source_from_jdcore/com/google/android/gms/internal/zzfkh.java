package com.google.android.gms.internal;

final class zzfkh
{
  static String zza(zzfgs paramZzfgs)
  {
    zzfki localZzfki = new zzfki(paramZzfgs);
    StringBuilder localStringBuilder = new StringBuilder(localZzfki.zza());
    int i = 0;
    while (i < localZzfki.zza())
    {
      int j = localZzfki.zza(i);
      if (j != 34) {
        if (j != 39) {
          if (j != 92) {
            switch (j)
            {
            default: 
              char c;
              if ((j >= 32) && (j <= 126))
              {
                c = (char)j;
              }
              else
              {
                localStringBuilder.append('\\');
                localStringBuilder.append((char)((j >>> 6 & 0x3) + 48));
                localStringBuilder.append((char)((j >>> 3 & 0x7) + 48));
                c = (char)(48 + (j & 0x7));
              }
              localStringBuilder.append(c);
              break;
            case 13: 
              paramZzfgs = "\\r";
              break;
            case 12: 
              paramZzfgs = "\\f";
              break;
            case 11: 
              paramZzfgs = "\\v";
              break;
            case 10: 
              paramZzfgs = "\\n";
              break;
            case 9: 
              paramZzfgs = "\\t";
              break;
            case 8: 
              paramZzfgs = "\\b";
              break;
            case 7: 
              paramZzfgs = "\\a";
            }
          }
        }
      }
      for (;;)
      {
        localStringBuilder.append(paramZzfgs);
        break;
        paramZzfgs = "\\\\";
        continue;
        paramZzfgs = "\\'";
        continue;
        paramZzfgs = "\\\"";
      }
      i += 1;
    }
    return localStringBuilder.toString();
  }
}
