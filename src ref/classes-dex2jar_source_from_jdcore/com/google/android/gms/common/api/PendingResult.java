package com.google.android.gms.common.api;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.Hide;
import java.util.concurrent.TimeUnit;

public abstract class PendingResult<R extends Result>
{
  public PendingResult() {}
  
  @NonNull
  public abstract R await();
  
  @NonNull
  public abstract R await(long paramLong, @NonNull TimeUnit paramTimeUnit);
  
  public abstract void cancel();
  
  public abstract boolean isCanceled();
  
  public abstract void setResultCallback(@NonNull ResultCallback<? super R> paramResultCallback);
  
  public abstract void setResultCallback(@NonNull ResultCallback<? super R> paramResultCallback, long paramLong, @NonNull TimeUnit paramTimeUnit);
  
  @NonNull
  public <S extends Result> TransformedResult<S> then(@NonNull ResultTransform<? super R, ? extends S> paramResultTransform)
  {
    throw new UnsupportedOperationException();
  }
  
  @Hide
  public void zza(@NonNull zza paramZza)
  {
    throw new UnsupportedOperationException();
  }
  
  @Nullable
  @Hide
  public Integer zzb()
  {
    throw new UnsupportedOperationException();
  }
  
  @Hide
  public static abstract interface zza
  {
    @Hide
    public abstract void zza(Status paramStatus);
  }
}
