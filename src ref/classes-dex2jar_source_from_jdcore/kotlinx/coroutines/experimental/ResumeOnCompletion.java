package kotlinx.coroutines.experimental;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.experimental.Continuation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000(\n\002\030\002\n\002\030\002\n\002\030\002\n\002\b\002\n\002\030\002\n\002\020\002\n\002\b\003\n\002\020\003\n\000\n\002\020\016\n\000\b\002\030\0002\b\022\004\022\0020\0020\001B\033\022\006\020\003\032\0020\002\022\f\020\004\032\b\022\004\022\0020\0060\005¢\006\002\020\007J\023\020\b\032\0020\0062\b\020\t\032\004\030\0010\nH\002J\b\020\013\032\0020\fH\026R\024\020\004\032\b\022\004\022\0020\0060\005X\004¢\006\002\n\000¨\006\r"}, d2={"Lkotlinx/coroutines/experimental/ResumeOnCompletion;", "Lkotlinx/coroutines/experimental/JobNode;", "Lkotlinx/coroutines/experimental/Job;", "job", "continuation", "Lkotlin/coroutines/experimental/Continuation;", "", "(Lkotlinx/coroutines/experimental/Job;Lkotlin/coroutines/experimental/Continuation;)V", "invoke", "reason", "", "toString", "", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
final class ResumeOnCompletion
  extends JobNode<Job>
{
  private final Continuation<Unit> continuation;
  
  public ResumeOnCompletion(@NotNull Job paramJob, @NotNull Continuation<? super Unit> paramContinuation)
  {
    super(paramJob);
    continuation = paramContinuation;
  }
  
  public void invoke(@Nullable Throwable paramThrowable)
  {
    continuation.resume(Unit.INSTANCE);
  }
  
  @NotNull
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("ResumeOnCompletion[");
    localStringBuilder.append(continuation);
    localStringBuilder.append(']');
    return localStringBuilder.toString();
  }
}
