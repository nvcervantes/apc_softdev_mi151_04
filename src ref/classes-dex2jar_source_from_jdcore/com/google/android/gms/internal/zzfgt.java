package com.google.android.gms.internal;

import java.util.Iterator;
import java.util.NoSuchElementException;

final class zzfgt
  implements Iterator
{
  private int zza = 0;
  private final int zzb = zzc.zza();
  
  zzfgt(zzfgs paramZzfgs) {}
  
  private final byte zza()
  {
    try
    {
      zzfgs localZzfgs = zzc;
      int i = zza;
      zza = (i + 1);
      byte b = localZzfgs.zza(i);
      return b;
    }
    catch (IndexOutOfBoundsException localIndexOutOfBoundsException)
    {
      throw new NoSuchElementException(localIndexOutOfBoundsException.getMessage());
    }
  }
  
  public final boolean hasNext()
  {
    return zza < zzb;
  }
  
  public final void remove()
  {
    throw new UnsupportedOperationException();
  }
}
