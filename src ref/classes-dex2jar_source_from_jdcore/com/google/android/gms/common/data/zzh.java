package com.google.android.gms.common.data;

import com.google.android.gms.common.internal.Hide;
import java.util.NoSuchElementException;

@Hide
public final class zzh<T>
  extends zzb<T>
{
  private T zzc;
  
  public zzh(DataBuffer<T> paramDataBuffer)
  {
    super(paramDataBuffer);
  }
  
  public final T next()
  {
    Object localObject;
    if (!hasNext())
    {
      int i = zzb;
      localObject = new StringBuilder(46);
      ((StringBuilder)localObject).append("Cannot advance the iterator beyond ");
      ((StringBuilder)localObject).append(i);
      throw new NoSuchElementException(((StringBuilder)localObject).toString());
    }
    zzb += 1;
    if (zzb == 0)
    {
      zzc = zza.get(0);
      if (!(zzc instanceof zzc))
      {
        localObject = String.valueOf(zzc.getClass());
        StringBuilder localStringBuilder = new StringBuilder(44 + String.valueOf(localObject).length());
        localStringBuilder.append("DataBuffer reference of type ");
        localStringBuilder.append((String)localObject);
        localStringBuilder.append(" is not movable");
        throw new IllegalStateException(localStringBuilder.toString());
      }
    }
    else
    {
      ((zzc)zzc).zza(zzb);
    }
    return zzc;
  }
}
