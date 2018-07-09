package com.google.android.gms.internal;

import java.util.AbstractList;
import java.util.Collection;
import java.util.List;
import java.util.RandomAccess;

abstract class zzfgn<E>
  extends AbstractList<E>
  implements zzfid<E>
{
  private boolean zza = true;
  
  zzfgn() {}
  
  public void add(int paramInt, E paramE)
  {
    zzc();
    super.add(paramInt, paramE);
  }
  
  public boolean add(E paramE)
  {
    zzc();
    return super.add(paramE);
  }
  
  public boolean addAll(int paramInt, Collection<? extends E> paramCollection)
  {
    zzc();
    return super.addAll(paramInt, paramCollection);
  }
  
  public boolean addAll(Collection<? extends E> paramCollection)
  {
    zzc();
    return super.addAll(paramCollection);
  }
  
  public void clear()
  {
    zzc();
    super.clear();
  }
  
  public boolean equals(Object paramObject)
  {
    if (paramObject == this) {
      return true;
    }
    if (!(paramObject instanceof List)) {
      return false;
    }
    if (!(paramObject instanceof RandomAccess)) {
      return super.equals(paramObject);
    }
    paramObject = (List)paramObject;
    int j = size();
    if (j != paramObject.size()) {
      return false;
    }
    int i = 0;
    while (i < j)
    {
      if (!get(i).equals(paramObject.get(i))) {
        return false;
      }
      i += 1;
    }
    return true;
  }
  
  public int hashCode()
  {
    int k = size();
    int j = 1;
    int i = 0;
    while (i < k)
    {
      j = j * 31 + get(i).hashCode();
      i += 1;
    }
    return j;
  }
  
  public E remove(int paramInt)
  {
    zzc();
    return super.remove(paramInt);
  }
  
  public boolean remove(Object paramObject)
  {
    zzc();
    return super.remove(paramObject);
  }
  
  public boolean removeAll(Collection<?> paramCollection)
  {
    zzc();
    return super.removeAll(paramCollection);
  }
  
  public boolean retainAll(Collection<?> paramCollection)
  {
    zzc();
    return super.retainAll(paramCollection);
  }
  
  public E set(int paramInt, E paramE)
  {
    zzc();
    return super.set(paramInt, paramE);
  }
  
  public final boolean zza()
  {
    return zza;
  }
  
  public final void zzb()
  {
    zza = false;
  }
  
  protected final void zzc()
  {
    if (!zza) {
      throw new UnsupportedOperationException();
    }
  }
}
