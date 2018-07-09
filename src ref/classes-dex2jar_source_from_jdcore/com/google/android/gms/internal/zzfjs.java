package com.google.android.gms.internal;

import java.util.Arrays;
import java.util.Stack;

final class zzfjs
{
  private final Stack<zzfgs> zza = new Stack();
  
  private zzfjs() {}
  
  private static int zza(int paramInt)
  {
    int i = Arrays.binarySearch(zzfjq.zzh(), paramInt);
    paramInt = i;
    if (i < 0) {
      paramInt = -(i + 1) - 1;
    }
    return paramInt;
  }
  
  private final zzfgs zza(zzfgs paramZzfgs1, zzfgs paramZzfgs2)
  {
    zza(paramZzfgs1);
    zza(paramZzfgs2);
    for (paramZzfgs1 = (zzfgs)zza.pop(); !zza.isEmpty(); paramZzfgs1 = new zzfjq((zzfgs)zza.pop(), paramZzfgs1, null)) {}
    return paramZzfgs1;
  }
  
  private final void zza(zzfgs paramZzfgs)
  {
    for (;;)
    {
      if (paramZzfgs.zzf())
      {
        int i = zza(paramZzfgs.zza());
        int j = zzfjq.zzh()[(i + 1)];
        if ((!zza.isEmpty()) && (((zzfgs)zza.peek()).zza() < j))
        {
          i = zzfjq.zzh()[i];
          for (localObject = (zzfgs)zza.pop(); (!zza.isEmpty()) && (((zzfgs)zza.peek()).zza() < i); localObject = new zzfjq((zzfgs)zza.pop(), (zzfgs)localObject, null)) {}
          for (paramZzfgs = new zzfjq((zzfgs)localObject, paramZzfgs, null); !zza.isEmpty(); paramZzfgs = new zzfjq((zzfgs)zza.pop(), paramZzfgs, null))
          {
            i = zza(paramZzfgs.zza());
            i = zzfjq.zzh()[(i + 1)];
            if (((zzfgs)zza.peek()).zza() >= i) {
              break;
            }
          }
          zza.push(paramZzfgs);
          return;
        }
        zza.push(paramZzfgs);
        return;
      }
      if (!(paramZzfgs instanceof zzfjq)) {
        break;
      }
      paramZzfgs = (zzfjq)paramZzfgs;
      zza(zzfjq.zza(paramZzfgs));
      paramZzfgs = zzfjq.zzb(paramZzfgs);
    }
    paramZzfgs = String.valueOf(paramZzfgs.getClass());
    Object localObject = new StringBuilder(49 + String.valueOf(paramZzfgs).length());
    ((StringBuilder)localObject).append("Has a new type of ByteString been created? Found ");
    ((StringBuilder)localObject).append(paramZzfgs);
    throw new IllegalArgumentException(((StringBuilder)localObject).toString());
  }
}
