package com.google.android.gms.common.util;

import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.zzbq;
import java.util.Set;

public final class zzv
{
  public static String[] zza(Set<Scope> paramSet)
  {
    zzbq.zza(paramSet, "scopes can't be null.");
    paramSet = (Scope[])paramSet.toArray(new Scope[paramSet.size()]);
    zzbq.zza(paramSet, "scopes can't be null.");
    int i = 0;
    String[] arrayOfString = new String[paramSet.length];
    while (i < paramSet.length)
    {
      arrayOfString[i] = paramSet[i].zza();
      i += 1;
    }
    return arrayOfString;
  }
}
