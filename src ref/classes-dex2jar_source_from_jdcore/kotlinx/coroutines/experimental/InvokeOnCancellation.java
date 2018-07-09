package kotlinx.coroutines.experimental;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000*\n\002\030\002\n\002\030\002\n\002\030\002\n\002\b\002\n\002\030\002\n\002\020\003\n\002\020\002\n\002\030\002\n\002\b\004\n\002\020\016\n\000\b\002\030\0002\b\022\004\022\0020\0020\001B'\022\006\020\003\032\0020\002\022\030\020\004\032\024\022\006\022\004\030\0010\006\022\004\022\0020\0070\005j\002`\b¢\006\002\020\tJ\022\020\n\032\0020\0072\b\020\013\032\004\030\0010\006H\026J\b\020\f\032\0020\rH\026R \020\004\032\024\022\006\022\004\030\0010\006\022\004\022\0020\0070\005j\002`\bX\004¢\006\002\n\000¨\006\016"}, d2={"Lkotlinx/coroutines/experimental/InvokeOnCancellation;", "Lkotlinx/coroutines/experimental/JobCancellationNode;", "Lkotlinx/coroutines/experimental/Job;", "job", "handler", "Lkotlin/Function1;", "", "", "Lkotlinx/coroutines/experimental/CompletionHandler;", "(Lkotlinx/coroutines/experimental/Job;Lkotlin/jvm/functions/Function1;)V", "invokeOnce", "reason", "toString", "", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
final class InvokeOnCancellation
  extends JobCancellationNode<Job>
{
  private final Function1<Throwable, Unit> handler;
  
  public InvokeOnCancellation(@NotNull Job paramJob, @NotNull Function1<? super Throwable, Unit> paramFunction1)
  {
    super(paramJob);
    handler = paramFunction1;
  }
  
  public void invokeOnce(@Nullable Throwable paramThrowable)
  {
    handler.invoke(paramThrowable);
  }
  
  @NotNull
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("InvokeOnCancellation[");
    localStringBuilder.append(handler.getClass().getName());
    localStringBuilder.append('@');
    localStringBuilder.append(Integer.toHexString(System.identityHashCode(handler)));
    localStringBuilder.append(']');
    return localStringBuilder.toString();
  }
}
