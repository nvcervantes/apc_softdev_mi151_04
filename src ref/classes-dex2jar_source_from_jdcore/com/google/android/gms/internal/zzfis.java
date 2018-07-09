package com.google.android.gms.internal;

final class zzfis
  implements zzfjb
{
  private zzfjb[] zza;
  
  zzfis(zzfjb... paramVarArgs)
  {
    zza = paramVarArgs;
  }
  
  public final boolean zza(Class<?> paramClass)
  {
    zzfjb[] arrayOfZzfjb = zza;
    int j = arrayOfZzfjb.length;
    int i = 0;
    while (i < j)
    {
      if (arrayOfZzfjb[i].zza(paramClass)) {
        return true;
      }
      i += 1;
    }
    return false;
  }
  
  public final zzfja zzb(Class<?> paramClass)
  {
    zzfjb[] arrayOfZzfjb = zza;
    int i = 0;
    int j = arrayOfZzfjb.length;
    while (i < j)
    {
      zzfjb localZzfjb = arrayOfZzfjb[i];
      if (localZzfjb.zza(paramClass)) {
        return localZzfjb.zzb(paramClass);
      }
      i += 1;
    }
    paramClass = String.valueOf(paramClass.getName());
    if (paramClass.length() != 0) {
      paramClass = "No factory is available for message type: ".concat(paramClass);
    } else {
      paramClass = new String("No factory is available for message type: ");
    }
    throw new UnsupportedOperationException(paramClass);
  }
}
