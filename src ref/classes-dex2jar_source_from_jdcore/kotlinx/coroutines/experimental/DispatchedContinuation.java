package kotlinx.coroutines.experimental;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.experimental.Continuation;
import kotlin.coroutines.experimental.CoroutineContext;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\0002\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\002\b\003\n\002\030\002\n\002\b\003\n\002\020\002\n\002\b\007\n\002\020\003\n\002\b\004\n\002\020\016\n\000\b\000\030\000*\006\b\000\020\001 \0002\b\022\004\022\002H\0010\002B\033\022\006\020\003\032\0020\004\022\f\020\005\032\b\022\004\022\0028\0000\002¢\006\002\020\006J\027\020\013\032\0020\f2\006\020\r\032\0028\000H\000¢\006\004\b\016\020\017J\025\020\020\032\0020\f2\006\020\r\032\0028\000H\026¢\006\002\020\017J\026\020\021\032\0020\f2\006\020\r\032\0028\000H\b¢\006\002\020\017J\021\020\022\032\0020\f2\006\020\023\032\0020\024H\bJ\026\020\025\032\0020\f2\006\020\r\032\0028\000H\b¢\006\002\020\017J\021\020\026\032\0020\f2\006\020\023\032\0020\024H\bJ\020\020\027\032\0020\f2\006\020\023\032\0020\024H\026J\b\020\030\032\0020\031H\026R\022\020\007\032\0020\bX\005¢\006\006\032\004\b\t\020\nR\026\020\005\032\b\022\004\022\0028\0000\0028\006X\004¢\006\002\n\000R\020\020\003\032\0020\0048\006X\004¢\006\002\n\000¨\006\032"}, d2={"Lkotlinx/coroutines/experimental/DispatchedContinuation;", "T", "Lkotlin/coroutines/experimental/Continuation;", "dispatcher", "Lkotlinx/coroutines/experimental/CoroutineDispatcher;", "continuation", "(Lkotlinx/coroutines/experimental/CoroutineDispatcher;Lkotlin/coroutines/experimental/Continuation;)V", "context", "Lkotlin/coroutines/experimental/CoroutineContext;", "getContext", "()Lkotlin/coroutines/experimental/CoroutineContext;", "dispatchYield", "", "value", "dispatchYield$kotlinx_coroutines_core", "(Ljava/lang/Object;)V", "resume", "resumeCancellable", "resumeCancellableWithException", "exception", "", "resumeUndispatched", "resumeUndispatchedWithException", "resumeWithException", "toString", "", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
public final class DispatchedContinuation<T>
  implements Continuation<T>
{
  @JvmField
  @NotNull
  public final Continuation<T> continuation;
  @JvmField
  @NotNull
  public final CoroutineDispatcher dispatcher;
  
  public DispatchedContinuation(@NotNull CoroutineDispatcher paramCoroutineDispatcher, @NotNull Continuation<? super T> paramContinuation)
  {
    dispatcher = paramCoroutineDispatcher;
    continuation = paramContinuation;
  }
  
  public final void dispatchYield$kotlinx_coroutines_core(T paramT)
  {
    CoroutineContext localCoroutineContext = continuation.getContext();
    dispatcher.dispatch(localCoroutineContext, (Runnable)new DispatchTask(continuation, paramT, false, true));
  }
  
  @NotNull
  public CoroutineContext getContext()
  {
    return continuation.getContext();
  }
  
  public void resume(T paramT)
  {
    Object localObject = continuation.getContext();
    if (dispatcher.isDispatchNeeded((CoroutineContext)localObject))
    {
      dispatcher.dispatch((CoroutineContext)localObject, (Runnable)new DispatchTask(continuation, paramT, false, false));
      return;
    }
    localObject = CoroutineContextKt.updateContext(getContext());
    try
    {
      continuation.resume(paramT);
      paramT = Unit.INSTANCE;
      return;
    }
    finally
    {
      CoroutineContextKt.restoreContext((String)localObject);
    }
  }
  
  public final void resumeCancellable(T paramT)
  {
    Object localObject = continuation.getContext();
    if (dispatcher.isDispatchNeeded((CoroutineContext)localObject))
    {
      dispatcher.dispatch((CoroutineContext)localObject, (Runnable)new DispatchTask(continuation, paramT, false, true));
      return;
    }
    localObject = CoroutineContextKt.updateContext(getContext());
    try
    {
      continuation.resume(paramT);
      paramT = Unit.INSTANCE;
      return;
    }
    finally
    {
      InlineMarker.finallyStart(1);
      CoroutineContextKt.restoreContext((String)localObject);
      InlineMarker.finallyEnd(1);
    }
  }
  
  public final void resumeCancellableWithException(@NotNull Throwable paramThrowable)
  {
    Intrinsics.checkParameterIsNotNull(paramThrowable, "exception");
    Object localObject = continuation.getContext();
    if (dispatcher.isDispatchNeeded((CoroutineContext)localObject))
    {
      dispatcher.dispatch((CoroutineContext)localObject, (Runnable)new DispatchTask(continuation, paramThrowable, true, true));
      return;
    }
    localObject = CoroutineContextKt.updateContext(getContext());
    try
    {
      continuation.resumeWithException(paramThrowable);
      paramThrowable = Unit.INSTANCE;
      return;
    }
    finally
    {
      InlineMarker.finallyStart(1);
      CoroutineContextKt.restoreContext((String)localObject);
      InlineMarker.finallyEnd(1);
    }
  }
  
  public final void resumeUndispatched(T paramT)
  {
    String str = CoroutineContextKt.updateContext(getContext());
    try
    {
      continuation.resume(paramT);
      paramT = Unit.INSTANCE;
      return;
    }
    finally
    {
      InlineMarker.finallyStart(1);
      CoroutineContextKt.restoreContext(str);
      InlineMarker.finallyEnd(1);
    }
  }
  
  public final void resumeUndispatchedWithException(@NotNull Throwable paramThrowable)
  {
    Intrinsics.checkParameterIsNotNull(paramThrowable, "exception");
    String str = CoroutineContextKt.updateContext(getContext());
    try
    {
      continuation.resumeWithException(paramThrowable);
      paramThrowable = Unit.INSTANCE;
      return;
    }
    finally
    {
      InlineMarker.finallyStart(1);
      CoroutineContextKt.restoreContext(str);
      InlineMarker.finallyEnd(1);
    }
  }
  
  public void resumeWithException(@NotNull Throwable paramThrowable)
  {
    Intrinsics.checkParameterIsNotNull(paramThrowable, "exception");
    Object localObject = continuation.getContext();
    if (dispatcher.isDispatchNeeded((CoroutineContext)localObject))
    {
      dispatcher.dispatch((CoroutineContext)localObject, (Runnable)new DispatchTask(continuation, paramThrowable, true, false));
      return;
    }
    localObject = CoroutineContextKt.updateContext(getContext());
    try
    {
      continuation.resumeWithException(paramThrowable);
      paramThrowable = Unit.INSTANCE;
      return;
    }
    finally
    {
      CoroutineContextKt.restoreContext((String)localObject);
    }
  }
  
  @NotNull
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("DispatchedContinuation[");
    localStringBuilder.append(dispatcher);
    localStringBuilder.append(", ");
    localStringBuilder.append(CoroutineDispatcherKt.toDebugString(continuation));
    localStringBuilder.append(']');
    return localStringBuilder.toString();
  }
}
