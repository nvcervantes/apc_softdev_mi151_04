package com.google.android.gms.common.util;

import android.support.annotation.Nullable;
import java.util.regex.Pattern;

public final class zzw
{
  private static final Pattern zza = Pattern.compile("\\$\\{(.*?)\\}");
  
  public static boolean zza(@Nullable String paramString)
  {
    return (paramString == null) || (paramString.trim().isEmpty());
  }
}
