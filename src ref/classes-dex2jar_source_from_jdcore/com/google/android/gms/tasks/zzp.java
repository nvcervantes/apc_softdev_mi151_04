package com.google.android.gms.tasks;

import android.app.Activity;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.api.internal.LifecycleCallback;
import com.google.android.gms.common.api.internal.zzcf;
import com.google.android.gms.common.internal.zzbq;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;

final class zzp<TResult>
  extends Task<TResult>
{
  private final Object zza = new Object();
  private final zzn<TResult> zzb = new zzn();
  private boolean zzc;
  private TResult zzd;
  private Exception zze;
  
  zzp() {}
  
  private final void zza()
  {
    zzbq.zza(zzc, "Task is not yet complete");
  }
  
  private final void zzb()
  {
    zzbq.zza(zzc ^ true, "Task is already complete");
  }
  
  private final void zzc()
  {
    synchronized (zza)
    {
      if (!zzc) {
        return;
      }
      zzb.zza(this);
      return;
    }
  }
  
  @NonNull
  public final Task<TResult> addOnCompleteListener(@NonNull Activity paramActivity, @NonNull OnCompleteListener<TResult> paramOnCompleteListener)
  {
    paramOnCompleteListener = new zze(TaskExecutors.MAIN_THREAD, paramOnCompleteListener);
    zzb.zza(paramOnCompleteListener);
    zza.zzb(paramActivity).zza(paramOnCompleteListener);
    zzc();
    return this;
  }
  
  @NonNull
  public final Task<TResult> addOnCompleteListener(@NonNull OnCompleteListener<TResult> paramOnCompleteListener)
  {
    return addOnCompleteListener(TaskExecutors.MAIN_THREAD, paramOnCompleteListener);
  }
  
  @NonNull
  public final Task<TResult> addOnCompleteListener(@NonNull Executor paramExecutor, @NonNull OnCompleteListener<TResult> paramOnCompleteListener)
  {
    zzb.zza(new zze(paramExecutor, paramOnCompleteListener));
    zzc();
    return this;
  }
  
  @NonNull
  public final Task<TResult> addOnFailureListener(@NonNull Activity paramActivity, @NonNull OnFailureListener paramOnFailureListener)
  {
    paramOnFailureListener = new zzg(TaskExecutors.MAIN_THREAD, paramOnFailureListener);
    zzb.zza(paramOnFailureListener);
    zza.zzb(paramActivity).zza(paramOnFailureListener);
    zzc();
    return this;
  }
  
  @NonNull
  public final Task<TResult> addOnFailureListener(@NonNull OnFailureListener paramOnFailureListener)
  {
    return addOnFailureListener(TaskExecutors.MAIN_THREAD, paramOnFailureListener);
  }
  
  @NonNull
  public final Task<TResult> addOnFailureListener(@NonNull Executor paramExecutor, @NonNull OnFailureListener paramOnFailureListener)
  {
    zzb.zza(new zzg(paramExecutor, paramOnFailureListener));
    zzc();
    return this;
  }
  
  @NonNull
  public final Task<TResult> addOnSuccessListener(@NonNull Activity paramActivity, @NonNull OnSuccessListener<? super TResult> paramOnSuccessListener)
  {
    paramOnSuccessListener = new zzi(TaskExecutors.MAIN_THREAD, paramOnSuccessListener);
    zzb.zza(paramOnSuccessListener);
    zza.zzb(paramActivity).zza(paramOnSuccessListener);
    zzc();
    return this;
  }
  
  @NonNull
  public final Task<TResult> addOnSuccessListener(@NonNull OnSuccessListener<? super TResult> paramOnSuccessListener)
  {
    return addOnSuccessListener(TaskExecutors.MAIN_THREAD, paramOnSuccessListener);
  }
  
  @NonNull
  public final Task<TResult> addOnSuccessListener(@NonNull Executor paramExecutor, @NonNull OnSuccessListener<? super TResult> paramOnSuccessListener)
  {
    zzb.zza(new zzi(paramExecutor, paramOnSuccessListener));
    zzc();
    return this;
  }
  
  @NonNull
  public final <TContinuationResult> Task<TContinuationResult> continueWith(@NonNull Continuation<TResult, TContinuationResult> paramContinuation)
  {
    return continueWith(TaskExecutors.MAIN_THREAD, paramContinuation);
  }
  
  @NonNull
  public final <TContinuationResult> Task<TContinuationResult> continueWith(@NonNull Executor paramExecutor, @NonNull Continuation<TResult, TContinuationResult> paramContinuation)
  {
    zzp localZzp = new zzp();
    zzb.zza(new zza(paramExecutor, paramContinuation, localZzp));
    zzc();
    return localZzp;
  }
  
  @NonNull
  public final <TContinuationResult> Task<TContinuationResult> continueWithTask(@NonNull Continuation<TResult, Task<TContinuationResult>> paramContinuation)
  {
    return continueWithTask(TaskExecutors.MAIN_THREAD, paramContinuation);
  }
  
  @NonNull
  public final <TContinuationResult> Task<TContinuationResult> continueWithTask(@NonNull Executor paramExecutor, @NonNull Continuation<TResult, Task<TContinuationResult>> paramContinuation)
  {
    zzp localZzp = new zzp();
    zzb.zza(new zzc(paramExecutor, paramContinuation, localZzp));
    zzc();
    return localZzp;
  }
  
  @Nullable
  public final Exception getException()
  {
    synchronized (zza)
    {
      Exception localException = zze;
      return localException;
    }
  }
  
  public final TResult getResult()
  {
    synchronized (zza)
    {
      zza();
      if (zze != null) {
        throw new RuntimeExecutionException(zze);
      }
      Object localObject2 = zzd;
      return localObject2;
    }
  }
  
  public final <X extends Throwable> TResult getResult(@NonNull Class<X> paramClass)
    throws Throwable
  {
    synchronized (zza)
    {
      zza();
      if (paramClass.isInstance(zze)) {
        throw ((Throwable)paramClass.cast(zze));
      }
      if (zze != null) {
        throw new RuntimeExecutionException(zze);
      }
      paramClass = zzd;
      return paramClass;
    }
  }
  
  public final boolean isComplete()
  {
    synchronized (zza)
    {
      boolean bool = zzc;
      return bool;
    }
  }
  
  public final boolean isSuccessful()
  {
    for (;;)
    {
      synchronized (zza)
      {
        if ((zzc) && (zze == null))
        {
          bool = true;
          return bool;
        }
      }
      boolean bool = false;
    }
  }
  
  @NonNull
  public final <TContinuationResult> Task<TContinuationResult> onSuccessTask(@NonNull SuccessContinuation<TResult, TContinuationResult> paramSuccessContinuation)
  {
    return onSuccessTask(TaskExecutors.MAIN_THREAD, paramSuccessContinuation);
  }
  
  @NonNull
  public final <TContinuationResult> Task<TContinuationResult> onSuccessTask(Executor paramExecutor, SuccessContinuation<TResult, TContinuationResult> paramSuccessContinuation)
  {
    zzp localZzp = new zzp();
    zzb.zza(new zzk(paramExecutor, paramSuccessContinuation, localZzp));
    zzc();
    return localZzp;
  }
  
  public final void zza(@NonNull Exception paramException)
  {
    zzbq.zza(paramException, "Exception must not be null");
    synchronized (zza)
    {
      zzb();
      zzc = true;
      zze = paramException;
      zzb.zza(this);
      return;
    }
  }
  
  public final void zza(TResult paramTResult)
  {
    synchronized (zza)
    {
      zzb();
      zzc = true;
      zzd = paramTResult;
      zzb.zza(this);
      return;
    }
  }
  
  public final boolean zzb(@NonNull Exception paramException)
  {
    zzbq.zza(paramException, "Exception must not be null");
    synchronized (zza)
    {
      if (zzc) {
        return false;
      }
      zzc = true;
      zze = paramException;
      zzb.zza(this);
      return true;
    }
  }
  
  public final boolean zzb(TResult paramTResult)
  {
    synchronized (zza)
    {
      if (zzc) {
        return false;
      }
      zzc = true;
      zzd = paramTResult;
      zzb.zza(this);
      return true;
    }
  }
  
  static class zza
    extends LifecycleCallback
  {
    private final List<WeakReference<zzm<?>>> zza = new ArrayList();
    
    private zza(zzcf paramZzcf)
    {
      super();
      zzd.zza("TaskOnStopCallback", this);
    }
    
    public static zza zzb(Activity paramActivity)
    {
      zzcf localZzcf = zza(paramActivity);
      zza localZza = (zza)localZzcf.zza("TaskOnStopCallback", zza.class);
      paramActivity = localZza;
      if (localZza == null) {
        paramActivity = new zza(localZzcf);
      }
      return paramActivity;
    }
    
    public final <T> void zza(zzm<T> paramZzm)
    {
      synchronized (zza)
      {
        zza.add(new WeakReference(paramZzm));
        return;
      }
    }
    
    @MainThread
    public final void zzb()
    {
      synchronized (zza)
      {
        Iterator localIterator = zza.iterator();
        while (localIterator.hasNext())
        {
          zzm localZzm = (zzm)((WeakReference)localIterator.next()).get();
          if (localZzm != null) {
            localZzm.zza();
          }
        }
        zza.clear();
        return;
      }
    }
  }
}
