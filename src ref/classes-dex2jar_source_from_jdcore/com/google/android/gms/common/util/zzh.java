package com.google.android.gms.common.util;

import android.database.CharArrayBuffer;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Hide;

@Hide
public final class zzh
{
  public static void zza(String paramString, CharArrayBuffer paramCharArrayBuffer)
  {
    if (TextUtils.isEmpty(paramString)) {
      sizeCopied = 0;
    } else if ((data != null) && (data.length >= paramString.length())) {
      paramString.getChars(0, paramString.length(), data, 0);
    } else {
      data = paramString.toCharArray();
    }
    sizeCopied = paramString.length();
  }
}
