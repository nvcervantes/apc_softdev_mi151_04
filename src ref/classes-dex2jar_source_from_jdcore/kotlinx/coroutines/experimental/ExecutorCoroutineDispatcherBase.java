package kotlinx.coroutines.experimental;

import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.experimental.Continuation;
import kotlin.coroutines.experimental.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000\\\n\002\030\002\n\002\030\002\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\003\n\002\020\002\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\020\013\n\000\n\002\020\000\n\000\n\002\020\b\n\000\n\002\030\002\n\000\n\002\020\t\n\000\n\002\030\002\n\002\b\002\n\002\030\002\n\000\n\002\020\016\n\000\b \030\0002\0020\0012\0020\002B\005¢\006\002\020\003J\030\020\b\032\0020\t2\006\020\n\032\0020\0132\006\020\f\032\0020\rH\026J\023\020\016\032\0020\0172\b\020\020\032\004\030\0010\021H\002J\b\020\022\032\0020\023H\026J \020\024\032\0020\0252\006\020\026\032\0020\0272\006\020\030\032\0020\0312\006\020\f\032\0020\rH\026J&\020\032\032\0020\t2\006\020\026\032\0020\0272\006\020\030\032\0020\0312\f\020\033\032\b\022\004\022\0020\t0\034H\026J\b\020\035\032\0020\036H\026R\022\020\004\032\0020\005X¦\004¢\006\006\032\004\b\006\020\007¨\006\037"}, d2={"Lkotlinx/coroutines/experimental/ExecutorCoroutineDispatcherBase;", "Lkotlinx/coroutines/experimental/CoroutineDispatcher;", "Lkotlinx/coroutines/experimental/Delay;", "()V", "executor", "Ljava/util/concurrent/Executor;", "getExecutor", "()Ljava/util/concurrent/Executor;", "dispatch", "", "context", "Lkotlin/coroutines/experimental/CoroutineContext;", "block", "Ljava/lang/Runnable;", "equals", "", "other", "", "hashCode", "", "invokeOnTimeout", "Lkotlinx/coroutines/experimental/DisposableHandle;", "time", "", "unit", "Ljava/util/concurrent/TimeUnit;", "scheduleResumeAfterDelay", "continuation", "Lkotlinx/coroutines/experimental/CancellableContinuation;", "toString", "", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
public abstract class ExecutorCoroutineDispatcherBase
  extends CoroutineDispatcher
  implements Delay
{
  public ExecutorCoroutineDispatcherBase() {}
  
  @Nullable
  public Object delay(long paramLong, @NotNull TimeUnit paramTimeUnit, @NotNull Continuation<? super Unit> paramContinuation)
  {
    return Delay.DefaultImpls.delay(this, paramLong, paramTimeUnit, paramContinuation);
  }
  
  public void dispatch(@NotNull CoroutineContext paramCoroutineContext, @NotNull Runnable paramRunnable)
  {
    Intrinsics.checkParameterIsNotNull(paramCoroutineContext, "context");
    Intrinsics.checkParameterIsNotNull(paramRunnable, "block");
    try
    {
      getExecutor().execute(TimeSourceKt.getTimeSource().trackTask(paramRunnable));
      return;
    }
    catch (RejectedExecutionException paramCoroutineContext)
    {
      for (;;) {}
    }
    TimeSourceKt.getTimeSource().unTrackTask();
    DefaultExecutor.INSTANCE.execute(paramRunnable);
  }
  
  public boolean equals(@Nullable Object paramObject)
  {
    return ((paramObject instanceof ExecutorCoroutineDispatcherBase)) && (((ExecutorCoroutineDispatcherBase)paramObject).getExecutor() == getExecutor());
  }
  
  @NotNull
  public abstract Executor getExecutor();
  
  public int hashCode()
  {
    return System.identityHashCode(getExecutor());
  }
  
  @NotNull
  public DisposableHandle invokeOnTimeout(long paramLong, @NotNull TimeUnit paramTimeUnit, @NotNull Runnable paramRunnable)
  {
    Intrinsics.checkParameterIsNotNull(paramTimeUnit, "unit");
    Intrinsics.checkParameterIsNotNull(paramRunnable, "block");
    localObject3 = null;
    try
    {
      Object localObject4 = getExecutor();
      localObject1 = localObject4;
      if (!(localObject4 instanceof ScheduledExecutorService)) {
        localObject1 = null;
      }
      localObject4 = (ScheduledExecutorService)localObject1;
      localObject1 = localObject3;
      if (localObject4 != null) {
        localObject1 = ((ScheduledExecutorService)localObject4).schedule(paramRunnable, paramLong, paramTimeUnit);
      }
    }
    catch (RejectedExecutionException localRejectedExecutionException)
    {
      for (;;)
      {
        Object localObject1;
        Object localObject2 = localObject3;
      }
    }
    if (localObject1 != null) {
      return (DisposableHandle)new DisposableFutureHandle((Future)localObject1);
    }
    return DefaultExecutor.INSTANCE.invokeOnTimeout(paramLong, paramTimeUnit, paramRunnable);
  }
  
  public void scheduleResumeAfterDelay(long paramLong, @NotNull TimeUnit paramTimeUnit, @NotNull CancellableContinuation<? super Unit> paramCancellableContinuation)
  {
    Intrinsics.checkParameterIsNotNull(paramTimeUnit, "unit");
    Intrinsics.checkParameterIsNotNull(paramCancellableContinuation, "continuation");
    localObject3 = null;
    try
    {
      Object localObject4 = getExecutor();
      localObject1 = localObject4;
      if (!(localObject4 instanceof ScheduledExecutorService)) {
        localObject1 = null;
      }
      localObject4 = (ScheduledExecutorService)localObject1;
      localObject1 = localObject3;
      if (localObject4 != null) {
        localObject1 = ((ScheduledExecutorService)localObject4).schedule((Runnable)new ResumeUndispatchedRunnable((CoroutineDispatcher)this, paramCancellableContinuation), paramLong, paramTimeUnit);
      }
    }
    catch (RejectedExecutionException localRejectedExecutionException)
    {
      for (;;)
      {
        Object localObject1;
        Object localObject2 = localObject3;
      }
    }
    if (localObject1 != null)
    {
      JobKt.cancelFutureOnCompletion((Job)paramCancellableContinuation, (Future)localObject1);
      return;
    }
    DefaultExecutor.INSTANCE.scheduleResumeAfterDelay(paramLong, paramTimeUnit, paramCancellableContinuation);
  }
  
  @NotNull
  public String toString()
  {
    return getExecutor().toString();
  }
}
