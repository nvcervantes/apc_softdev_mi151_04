package com.google.android.gms.common.data;

import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.internal.zzbq;
import java.util.Iterator;
import java.util.NoSuchElementException;

@Hide
public class zzb<T>
  implements Iterator<T>
{
  protected final DataBuffer<T> zza;
  protected int zzb;
  
  public zzb(DataBuffer<T> paramDataBuffer)
  {
    zza = ((DataBuffer)zzbq.zza(paramDataBuffer));
    zzb = -1;
  }
  
  public boolean hasNext()
  {
    return zzb < zza.getCount() - 1;
  }
  
  public T next()
  {
    if (!hasNext())
    {
      i = zzb;
      localObject = new StringBuilder(46);
      ((StringBuilder)localObject).append("Cannot advance the iterator beyond ");
      ((StringBuilder)localObject).append(i);
      throw new NoSuchElementException(((StringBuilder)localObject).toString());
    }
    Object localObject = zza;
    int i = zzb + 1;
    zzb = i;
    return ((DataBuffer)localObject).get(i);
  }
  
  public void remove()
  {
    throw new UnsupportedOperationException("Cannot remove elements from a DataBufferIterator");
  }
}
