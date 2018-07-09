package kotlinx.coroutines.experimental;

import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.experimental.Continuation;
import kotlin.coroutines.experimental.jvm.internal.CoroutineIntrinsics;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\0002\n\002\030\002\n\002\020\000\n\000\n\002\020\002\n\000\n\002\020\t\n\000\n\002\030\002\n\002\b\002\n\002\030\002\n\000\n\002\030\002\n\002\b\002\n\002\030\002\n\000\bf\030\0002\0020\001J#\020\002\032\0020\0032\006\020\004\032\0020\0052\b\b\002\020\006\032\0020\007H@ø\001\000¢\006\002\020\bJ \020\t\032\0020\n2\006\020\004\032\0020\0052\006\020\006\032\0020\0072\006\020\013\032\0020\fH\026J&\020\r\032\0020\0032\006\020\004\032\0020\0052\006\020\006\032\0020\0072\f\020\016\032\b\022\004\022\0020\0030\017H&\002\004\n\002\b\t¨\006\020"}, d2={"Lkotlinx/coroutines/experimental/Delay;", "", "delay", "", "time", "", "unit", "Ljava/util/concurrent/TimeUnit;", "(JLjava/util/concurrent/TimeUnit;Lkotlin/coroutines/experimental/Continuation;)Ljava/lang/Object;", "invokeOnTimeout", "Lkotlinx/coroutines/experimental/DisposableHandle;", "block", "Ljava/lang/Runnable;", "scheduleResumeAfterDelay", "continuation", "Lkotlinx/coroutines/experimental/CancellableContinuation;", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
public abstract interface Delay
{
  @Nullable
  public abstract Object delay(long paramLong, @NotNull TimeUnit paramTimeUnit, @NotNull Continuation<? super Unit> paramContinuation);
  
  @NotNull
  public abstract DisposableHandle invokeOnTimeout(long paramLong, @NotNull TimeUnit paramTimeUnit, @NotNull Runnable paramRunnable);
  
  public abstract void scheduleResumeAfterDelay(long paramLong, @NotNull TimeUnit paramTimeUnit, @NotNull CancellableContinuation<? super Unit> paramCancellableContinuation);
  
  @Metadata(bv={1, 0, 2}, k=3, mv={1, 1, 7})
  public static final class DefaultImpls
  {
    @Nullable
    public static Object delay(Delay paramDelay, @NotNull long paramLong, @NotNull TimeUnit paramTimeUnit, Continuation<? super Unit> paramContinuation)
    {
      int i = 0;
      long l = 0;
      if (paramLong >= l) {
        i = 1;
      }
      if (i == 0)
      {
        paramDelay = new StringBuilder();
        paramDelay.append("Delay time ");
        paramDelay.append(paramLong);
        paramDelay.append(" cannot be negative");
        throw ((Throwable)new IllegalArgumentException(paramDelay.toString().toString()));
      }
      if (paramLong <= l) {
        return Unit.INSTANCE;
      }
      paramContinuation = new CancellableContinuationImpl(CoroutineIntrinsics.normalizeContinuation(paramContinuation), 1);
      paramContinuation.initCancellability();
      paramDelay.scheduleResumeAfterDelay(paramLong, paramTimeUnit, (CancellableContinuation)paramContinuation);
      return paramContinuation.getResult();
    }
    
    @NotNull
    public static DisposableHandle invokeOnTimeout(Delay paramDelay, @NotNull long paramLong, @NotNull TimeUnit paramTimeUnit, Runnable paramRunnable)
    {
      Intrinsics.checkParameterIsNotNull(paramTimeUnit, "unit");
      Intrinsics.checkParameterIsNotNull(paramRunnable, "block");
      return DefaultExecutor.INSTANCE.invokeOnTimeout(paramLong, paramTimeUnit, paramRunnable);
    }
  }
}
