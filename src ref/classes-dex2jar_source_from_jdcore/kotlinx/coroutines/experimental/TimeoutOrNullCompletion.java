package kotlinx.coroutines.experimental;

import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.coroutines.experimental.Continuation;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000,\n\002\030\002\n\000\n\002\030\002\n\000\n\002\020\t\n\000\n\002\030\002\n\000\n\002\030\002\n\002\b\002\n\002\020\002\n\000\n\002\020\003\n\000\b\002\030\000*\004\b\000\020\0012\020\022\006\022\004\030\001H\001\022\004\022\002H\0010\002B%\022\006\020\003\032\0020\004\022\006\020\005\032\0020\006\022\016\020\007\032\n\022\006\022\004\030\0018\0000\b¢\006\002\020\tJ\020\020\n\032\0020\0132\006\020\f\032\0020\rH\026¨\006\016"}, d2={"Lkotlinx/coroutines/experimental/TimeoutOrNullCompletion;", "T", "Lkotlinx/coroutines/experimental/TimeoutCompletion;", "time", "", "unit", "Ljava/util/concurrent/TimeUnit;", "cont", "Lkotlin/coroutines/experimental/Continuation;", "(JLjava/util/concurrent/TimeUnit;Lkotlin/coroutines/experimental/Continuation;)V", "resumeWithException", "", "exception", "", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
final class TimeoutOrNullCompletion<T>
  extends TimeoutCompletion<T, T>
{
  public TimeoutOrNullCompletion(long paramLong, @NotNull TimeUnit paramTimeUnit, @NotNull Continuation<? super T> paramContinuation)
  {
    super(paramLong, paramTimeUnit, paramContinuation);
  }
  
  public void resumeWithException(@NotNull Throwable paramThrowable)
  {
    Intrinsics.checkParameterIsNotNull(paramThrowable, "exception");
    if (((paramThrowable instanceof TimeoutException)) && (coroutine == (TimeoutOrNullCompletion)this))
    {
      CoroutineDispatcherKt.resumeDirect(cont, null);
      return;
    }
    CoroutineDispatcherKt.resumeDirectWithException(cont, paramThrowable);
  }
}
