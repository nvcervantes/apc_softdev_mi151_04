package com.google.android.gms.common.data;

import android.os.Bundle;
import com.google.android.gms.common.internal.Hide;
import java.util.Iterator;

public abstract class AbstractDataBuffer<T>
  implements DataBuffer<T>
{
  @Hide
  protected final DataHolder zza;
  
  @Hide
  protected AbstractDataBuffer(DataHolder paramDataHolder)
  {
    zza = paramDataHolder;
  }
  
  @Deprecated
  public final void close()
  {
    release();
  }
  
  public abstract T get(int paramInt);
  
  public int getCount()
  {
    if (zza == null) {
      return 0;
    }
    return zza.zza;
  }
  
  @Deprecated
  public boolean isClosed()
  {
    return (zza == null) || (zza.zze());
  }
  
  public Iterator<T> iterator()
  {
    return new zzb(this);
  }
  
  public void release()
  {
    if (zza != null) {
      zza.close();
    }
  }
  
  public Iterator<T> singleRefIterator()
  {
    return new zzh(this);
  }
  
  @Hide
  public final Bundle zza()
  {
    return zza.zzc();
  }
}
