package com.google.android.gms.internal;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

final class zzdyt
  extends WeakReference<Throwable>
{
  private final int zza;
  
  public zzdyt(Throwable paramThrowable, ReferenceQueue<Throwable> paramReferenceQueue)
  {
    super(paramThrowable, null);
    if (paramThrowable == null) {
      throw new NullPointerException("The referent cannot be null");
    }
    zza = System.identityHashCode(paramThrowable);
  }
  
  public final boolean equals(Object paramObject)
  {
    if (paramObject != null)
    {
      if (paramObject.getClass() != getClass()) {
        return false;
      }
      if (this == paramObject) {
        return true;
      }
      paramObject = (zzdyt)paramObject;
      if ((zza == zza) && (get() == paramObject.get())) {
        return true;
      }
    }
    return false;
  }
  
  public final int hashCode()
  {
    return zza;
  }
}
