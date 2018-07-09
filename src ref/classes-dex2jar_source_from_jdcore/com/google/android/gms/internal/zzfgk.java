package com.google.android.gms.internal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public abstract class zzfgk<MessageType extends zzfgj<MessageType, BuilderType>, BuilderType extends zzfgk<MessageType, BuilderType>>
  implements zzfjd
{
  public zzfgk() {}
  
  protected static <T> void zza(Iterable<T> paramIterable, List<? super T> paramList)
  {
    zzfhz.zza(paramIterable);
    if ((paramIterable instanceof zzfil))
    {
      Object localObject = ((zzfil)paramIterable).zza();
      paramIterable = (zzfil)paramList;
      int j = paramList.size();
      paramList = ((List)localObject).iterator();
      while (paramList.hasNext())
      {
        localObject = paramList.next();
        if (localObject == null)
        {
          int i = paramIterable.size();
          paramList = new StringBuilder(37);
          paramList.append("Element at index ");
          paramList.append(i - j);
          paramList.append(" is null.");
          paramList = paramList.toString();
          i = paramIterable.size() - 1;
          while (i >= j)
          {
            paramIterable.remove(i);
            i -= 1;
          }
          throw new NullPointerException(paramList);
        }
        if ((localObject instanceof zzfgs)) {
          paramIterable.zza((zzfgs)localObject);
        } else {
          paramIterable.add((String)localObject);
        }
      }
      return;
    }
    if ((paramIterable instanceof zzfjm))
    {
      paramList.addAll((Collection)paramIterable);
      return;
    }
    zzb(paramIterable, paramList);
  }
  
  private static <T> void zzb(Iterable<T> paramIterable, List<? super T> paramList)
  {
    if (((paramList instanceof ArrayList)) && ((paramIterable instanceof Collection))) {
      ((ArrayList)paramList).ensureCapacity(paramList.size() + ((Collection)paramIterable).size());
    }
    int j = paramList.size();
    paramIterable = paramIterable.iterator();
    while (paramIterable.hasNext())
    {
      Object localObject = paramIterable.next();
      if (localObject == null)
      {
        int i = paramList.size();
        paramIterable = new StringBuilder(37);
        paramIterable.append("Element at index ");
        paramIterable.append(i - j);
        paramIterable.append(" is null.");
        paramIterable = paramIterable.toString();
        i = paramList.size() - 1;
        while (i >= j)
        {
          paramList.remove(i);
          i -= 1;
        }
        throw new NullPointerException(paramIterable);
      }
      paramList.add(localObject);
    }
  }
  
  public abstract BuilderType zza();
  
  protected abstract BuilderType zza(MessageType paramMessageType);
  
  public abstract BuilderType zza(zzfhb paramZzfhb, zzfhm paramZzfhm)
    throws IOException;
}
