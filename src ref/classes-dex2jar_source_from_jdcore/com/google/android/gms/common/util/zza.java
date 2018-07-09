package com.google.android.gms.common.util;

import com.google.android.gms.common.internal.Hide;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Hide
public final class zza
{
  public static MessageDigest zza(String paramString)
  {
    int i = 0;
    while (i < 2)
    {
      try
      {
        MessageDigest localMessageDigest = MessageDigest.getInstance(paramString);
        if (localMessageDigest != null) {
          return localMessageDigest;
        }
      }
      catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
      {
        for (;;) {}
      }
      i += 1;
    }
    return null;
  }
}
