package kotlinx.coroutines.experimental;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.experimental.Continuation;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000\036\n\000\n\002\020\002\n\000\n\002\030\002\n\002\b\004\n\002\020\003\n\002\b\003\n\002\020\016\n\000\032%\020\000\032\0020\001\"\004\b\000\020\002*\b\022\004\022\002H\0020\0032\006\020\004\032\002H\002H\000¢\006\002\020\005\032 \020\006\032\0020\001\"\004\b\000\020\002*\b\022\004\022\002H\0020\0032\006\020\007\032\0020\bH\000\032%\020\t\032\0020\001\"\004\b\000\020\002*\b\022\004\022\002H\0020\0032\006\020\004\032\002H\002H\000¢\006\002\020\005\032 \020\n\032\0020\001\"\004\b\000\020\002*\b\022\004\022\002H\0020\0032\006\020\007\032\0020\bH\000\032\020\020\013\032\0020\f*\006\022\002\b\0030\003H\000¨\006\r"}, d2={"resumeCancellable", "", "T", "Lkotlin/coroutines/experimental/Continuation;", "value", "(Lkotlin/coroutines/experimental/Continuation;Ljava/lang/Object;)V", "resumeCancellableWithException", "exception", "", "resumeDirect", "resumeDirectWithException", "toDebugString", "", "kotlinx-coroutines-core"}, k=2, mv={1, 1, 7})
public final class CoroutineDispatcherKt
{
  public static final <T> void resumeCancellable(@NotNull Continuation<? super T> paramContinuation, T paramT)
  {
    Intrinsics.checkParameterIsNotNull(paramContinuation, "$receiver");
    if ((paramContinuation instanceof DispatchedContinuation))
    {
      DispatchedContinuation localDispatchedContinuation = (DispatchedContinuation)paramContinuation;
      paramContinuation = continuation.getContext();
      if (dispatcher.isDispatchNeeded(paramContinuation))
      {
        dispatcher.dispatch(paramContinuation, (Runnable)new DispatchTask(continuation, paramT, false, true));
        return;
      }
      paramContinuation = CoroutineContextKt.updateContext(localDispatchedContinuation.getContext());
      try
      {
        continuation.resume(paramT);
        paramT = Unit.INSTANCE;
        return;
      }
      finally
      {
        CoroutineContextKt.restoreContext(paramContinuation);
      }
    }
    paramContinuation.resume(paramT);
  }
  
  public static final <T> void resumeCancellableWithException(@NotNull Continuation<? super T> paramContinuation, @NotNull Throwable paramThrowable)
  {
    Intrinsics.checkParameterIsNotNull(paramContinuation, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramThrowable, "exception");
    if ((paramContinuation instanceof DispatchedContinuation))
    {
      DispatchedContinuation localDispatchedContinuation = (DispatchedContinuation)paramContinuation;
      paramContinuation = continuation.getContext();
      if (dispatcher.isDispatchNeeded(paramContinuation))
      {
        dispatcher.dispatch(paramContinuation, (Runnable)new DispatchTask(continuation, paramThrowable, true, true));
        return;
      }
      paramContinuation = CoroutineContextKt.updateContext(localDispatchedContinuation.getContext());
      try
      {
        continuation.resumeWithException(paramThrowable);
        paramThrowable = Unit.INSTANCE;
        return;
      }
      finally
      {
        CoroutineContextKt.restoreContext(paramContinuation);
      }
    }
    paramContinuation.resumeWithException(paramThrowable);
  }
  
  public static final <T> void resumeDirect(@NotNull Continuation<? super T> paramContinuation, T paramT)
  {
    Intrinsics.checkParameterIsNotNull(paramContinuation, "$receiver");
    if ((paramContinuation instanceof DispatchedContinuation))
    {
      continuation.resume(paramT);
      return;
    }
    paramContinuation.resume(paramT);
  }
  
  public static final <T> void resumeDirectWithException(@NotNull Continuation<? super T> paramContinuation, @NotNull Throwable paramThrowable)
  {
    Intrinsics.checkParameterIsNotNull(paramContinuation, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramThrowable, "exception");
    if ((paramContinuation instanceof DispatchedContinuation))
    {
      continuation.resumeWithException(paramThrowable);
      return;
    }
    paramContinuation.resumeWithException(paramThrowable);
  }
  
  @NotNull
  public static final String toDebugString(@NotNull Continuation<?> paramContinuation)
  {
    Intrinsics.checkParameterIsNotNull(paramContinuation, "$receiver");
    if ((paramContinuation instanceof DispatchedContinuation)) {
      return paramContinuation.toString();
    }
    paramContinuation = paramContinuation.getClass().getName();
    Intrinsics.checkExpressionValueIsNotNull(paramContinuation, "this::class.java.name");
    return paramContinuation;
  }
}
