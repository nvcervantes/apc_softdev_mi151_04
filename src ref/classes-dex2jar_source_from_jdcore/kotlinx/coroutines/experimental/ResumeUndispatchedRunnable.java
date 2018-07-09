package kotlinx.coroutines.experimental;

import kotlin.Metadata;
import kotlin.Unit;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000\034\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\002\020\002\n\002\b\003\b\002\030\0002\0020\001B\033\022\006\020\002\032\0020\003\022\f\020\004\032\b\022\004\022\0020\0060\005¢\006\002\020\007J\b\020\b\032\0020\006H\026R\024\020\004\032\b\022\004\022\0020\0060\005X\004¢\006\002\n\000R\016\020\002\032\0020\003X\004¢\006\002\n\000¨\006\t"}, d2={"Lkotlinx/coroutines/experimental/ResumeUndispatchedRunnable;", "Ljava/lang/Runnable;", "dispatcher", "Lkotlinx/coroutines/experimental/CoroutineDispatcher;", "continuation", "Lkotlinx/coroutines/experimental/CancellableContinuation;", "", "(Lkotlinx/coroutines/experimental/CoroutineDispatcher;Lkotlinx/coroutines/experimental/CancellableContinuation;)V", "run", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
final class ResumeUndispatchedRunnable
  implements Runnable
{
  private final CancellableContinuation<Unit> continuation;
  private final CoroutineDispatcher dispatcher;
  
  public ResumeUndispatchedRunnable(@NotNull CoroutineDispatcher paramCoroutineDispatcher, @NotNull CancellableContinuation<? super Unit> paramCancellableContinuation)
  {
    dispatcher = paramCoroutineDispatcher;
    continuation = paramCancellableContinuation;
  }
  
  public void run()
  {
    continuation.resumeUndispatched(dispatcher, Unit.INSTANCE);
  }
}
