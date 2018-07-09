package kotlinx.coroutines.experimental;

import kotlin.Metadata;
import kotlin.coroutines.experimental.Continuation;
import kotlin.coroutines.experimental.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000\"\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\002\b\005\n\002\020\002\n\002\b\004\n\002\020\003\n\000\b\002\030\000*\006\b\000\020\001 \0002\b\022\004\022\002H\0010\002B\033\022\006\020\003\032\0020\004\022\f\020\005\032\b\022\004\022\0028\0000\002¢\006\002\020\006J\026\020\t\032\0020\n2\006\020\013\032\0028\000H\001¢\006\002\020\fJ\021\020\r\032\0020\n2\006\020\016\032\0020\017H\001R\024\020\003\032\0020\004X\004¢\006\b\n\000\032\004\b\007\020\b¨\006\020"}, d2={"Lkotlinx/coroutines/experimental/RunContinuationDirect;", "T", "Lkotlin/coroutines/experimental/Continuation;", "context", "Lkotlin/coroutines/experimental/CoroutineContext;", "continuation", "(Lkotlin/coroutines/experimental/CoroutineContext;Lkotlin/coroutines/experimental/Continuation;)V", "getContext", "()Lkotlin/coroutines/experimental/CoroutineContext;", "resume", "", "value", "(Ljava/lang/Object;)V", "resumeWithException", "exception", "", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
final class RunContinuationDirect<T>
  implements Continuation<T>
{
  @NotNull
  private final CoroutineContext context;
  
  public RunContinuationDirect(@NotNull CoroutineContext paramCoroutineContext, @NotNull Continuation<? super T> paramContinuation)
  {
    $$delegate_0 = paramContinuation;
    context = paramCoroutineContext;
  }
  
  @NotNull
  public CoroutineContext getContext()
  {
    return context;
  }
  
  public void resume(T paramT)
  {
    $$delegate_0.resume(paramT);
  }
  
  public void resumeWithException(@NotNull Throwable paramThrowable)
  {
    Intrinsics.checkParameterIsNotNull(paramThrowable, "exception");
    $$delegate_0.resumeWithException(paramThrowable);
  }
}
