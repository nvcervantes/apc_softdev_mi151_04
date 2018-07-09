package com.google.android.gms.internal;

import java.util.Iterator;
import java.util.Stack;

final class zzfjt
  implements Iterator<zzfgy>
{
  private final Stack<zzfjq> zza = new Stack();
  private zzfgy zzb = zza(paramZzfgs);
  
  private zzfjt(zzfgs paramZzfgs) {}
  
  private final zzfgy zza()
  {
    zzfgy localZzfgy;
    do
    {
      if (zza.isEmpty()) {
        return null;
      }
      localZzfgy = zza(zzfjq.zzb((zzfjq)zza.pop()));
    } while (localZzfgy.zzb());
    return localZzfgy;
  }
  
  private final zzfgy zza(zzfgs paramZzfgs)
  {
    while ((paramZzfgs instanceof zzfjq))
    {
      paramZzfgs = (zzfjq)paramZzfgs;
      zza.push(paramZzfgs);
      paramZzfgs = zzfjq.zza(paramZzfgs);
    }
    return (zzfgy)paramZzfgs;
  }
  
  public final boolean hasNext()
  {
    return zzb != null;
  }
  
  public final void remove()
  {
    throw new UnsupportedOperationException();
  }
}
