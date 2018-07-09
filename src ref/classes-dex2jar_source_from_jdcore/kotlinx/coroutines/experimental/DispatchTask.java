package kotlinx.coroutines.experimental;

import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.coroutines.experimental.Continuation;
import kotlin.coroutines.experimental.CoroutineContext;
import kotlin.coroutines.experimental.CoroutineContext.Key;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000,\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\020\000\n\000\n\002\020\013\n\002\b\003\n\002\020\002\n\000\n\002\020\016\n\000\b\000\030\000*\006\b\000\020\001 \0002\0020\002B-\022\f\020\003\032\b\022\004\022\0028\0000\004\022\b\020\005\032\004\030\0010\006\022\006\020\007\032\0020\b\022\006\020\t\032\0020\b¢\006\002\020\nJ\b\020\013\032\0020\fH\027J\b\020\r\032\0020\016H\026R\016\020\t\032\0020\bX\004¢\006\002\n\000R\024\020\003\032\b\022\004\022\0028\0000\004X\004¢\006\002\n\000R\016\020\007\032\0020\bX\004¢\006\002\n\000R\020\020\005\032\004\030\0010\006X\004¢\006\002\n\000¨\006\017"}, d2={"Lkotlinx/coroutines/experimental/DispatchTask;", "T", "Ljava/lang/Runnable;", "continuation", "Lkotlin/coroutines/experimental/Continuation;", "value", "", "exception", "", "cancellable", "(Lkotlin/coroutines/experimental/Continuation;Ljava/lang/Object;ZZ)V", "run", "", "toString", "", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
public final class DispatchTask<T>
  implements Runnable
{
  private final boolean cancellable;
  private final Continuation<T> continuation;
  private final boolean exception;
  private final Object value;
  
  public DispatchTask(@NotNull Continuation<? super T> paramContinuation, @Nullable Object paramObject, boolean paramBoolean1, boolean paramBoolean2)
  {
    continuation = paramContinuation;
    value = paramObject;
    exception = paramBoolean1;
    cancellable = paramBoolean2;
  }
  
  public void run()
  {
    Object localObject3 = continuation.getContext();
    Object localObject1;
    if (cancellable) {
      localObject1 = (Job)((CoroutineContext)localObject3).get((CoroutineContext.Key)Job.Key);
    } else {
      localObject1 = null;
    }
    localObject3 = CoroutineContextKt.updateContext((CoroutineContext)localObject3);
    if (localObject1 != null) {}
    try
    {
      if (!((Job)localObject1).isActive())
      {
        continuation.resumeWithException(((Job)localObject1).getCompletionException());
      }
      else if (exception)
      {
        localObject1 = continuation;
        Object localObject4 = value;
        if (localObject4 == null) {
          throw new TypeCastException("null cannot be cast to non-null type kotlin.Throwable");
        }
        ((Continuation)localObject1).resumeWithException((Throwable)localObject4);
      }
      else
      {
        continuation.resume(value);
      }
      localObject1 = Unit.INSTANCE;
      CoroutineContextKt.restoreContext((String)localObject3);
      return;
    }
    finally
    {
      for (;;) {}
    }
    CoroutineContextKt.restoreContext((String)localObject3);
    throw ((Throwable)localObject1);
  }
  
  @NotNull
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("DispatchTask[");
    localStringBuilder.append(value);
    localStringBuilder.append(", cancellable=");
    localStringBuilder.append(cancellable);
    localStringBuilder.append(", ");
    localStringBuilder.append(CoroutineDispatcherKt.toDebugString(continuation));
    localStringBuilder.append(']');
    return localStringBuilder.toString();
  }
}
