package kotlinx.coroutines.experimental;

import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.experimental.Continuation;
import kotlin.coroutines.experimental.ContinuationInterceptor;
import kotlin.coroutines.experimental.CoroutineContext;
import kotlin.coroutines.experimental.CoroutineContext.Element;
import kotlin.coroutines.experimental.CoroutineContext.Key;
import kotlin.coroutines.experimental.jvm.internal.CoroutineIntrinsics;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000\"\n\000\n\002\030\002\n\002\030\002\n\002\b\002\n\002\020\002\n\000\n\002\020\t\n\000\n\002\030\002\n\002\b\002\032#\020\000\032\0020\0052\006\020\006\032\0020\0072\b\b\002\020\b\032\0020\tH@ø\001\000¢\006\002\020\n\"\030\020\000\032\0020\001*\0020\0028@X\004¢\006\006\032\004\b\003\020\004\002\004\n\002\b\t¨\006\013"}, d2={"delay", "Lkotlinx/coroutines/experimental/Delay;", "Lkotlin/coroutines/experimental/CoroutineContext;", "getDelay", "(Lkotlin/coroutines/experimental/CoroutineContext;)Lkotlinx/coroutines/experimental/Delay;", "", "time", "", "unit", "Ljava/util/concurrent/TimeUnit;", "(JLjava/util/concurrent/TimeUnit;Lkotlin/coroutines/experimental/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k=2, mv={1, 1, 7})
public final class DelayKt
{
  @Nullable
  public static final Object delay(long paramLong, @NotNull TimeUnit paramTimeUnit, @NotNull Continuation<? super Unit> paramContinuation)
  {
    int i = 0;
    long l = 0;
    if (paramLong >= l) {
      i = 1;
    }
    if (i == 0)
    {
      paramTimeUnit = new StringBuilder();
      paramTimeUnit.append("Delay time ");
      paramTimeUnit.append(paramLong);
      paramTimeUnit.append(" cannot be negative");
      throw ((Throwable)new IllegalArgumentException(paramTimeUnit.toString().toString()));
    }
    if (paramLong <= l) {
      return Unit.INSTANCE;
    }
    paramContinuation = new CancellableContinuationImpl(CoroutineIntrinsics.normalizeContinuation(paramContinuation), 1);
    paramContinuation.initCancellability();
    CancellableContinuation localCancellableContinuation = (CancellableContinuation)paramContinuation;
    getDelay(localCancellableContinuation.getContext()).scheduleResumeAfterDelay(paramLong, paramTimeUnit, localCancellableContinuation);
    return paramContinuation.getResult();
  }
  
  @NotNull
  public static final Delay getDelay(@NotNull CoroutineContext paramCoroutineContext)
  {
    Intrinsics.checkParameterIsNotNull(paramCoroutineContext, "$receiver");
    CoroutineContext.Element localElement = paramCoroutineContext.get((CoroutineContext.Key)ContinuationInterceptor.Key);
    paramCoroutineContext = localElement;
    if (!(localElement instanceof Delay)) {
      paramCoroutineContext = null;
    }
    paramCoroutineContext = (Delay)paramCoroutineContext;
    if (paramCoroutineContext != null) {
      return paramCoroutineContext;
    }
    return (Delay)DefaultExecutor.INSTANCE;
  }
}
