package com.google.android.gms.common.data;

import android.os.Bundle;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.internal.Hide;
import java.util.Iterator;

public abstract interface DataBuffer<T>
  extends Releasable, Iterable<T>
{
  @Deprecated
  public abstract void close();
  
  public abstract T get(int paramInt);
  
  public abstract int getCount();
  
  @Deprecated
  public abstract boolean isClosed();
  
  public abstract Iterator<T> iterator();
  
  public abstract void release();
  
  public abstract Iterator<T> singleRefIterator();
  
  @Hide
  public abstract Bundle zza();
}
