package kotlinx.coroutines.experimental;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000*\n\002\030\002\n\002\030\002\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\002\n\002\020\002\n\000\n\002\020\003\n\000\n\002\020\016\n\000\b\000\030\0002\b\022\004\022\0020\0020\001B\025\022\006\020\003\032\0020\002\022\006\020\004\032\0020\005¢\006\002\020\006J\023\020\007\032\0020\b2\b\020\t\032\004\030\0010\nH\002J\b\020\013\032\0020\fH\026R\016\020\004\032\0020\005X\004¢\006\002\n\000¨\006\r"}, d2={"Lkotlinx/coroutines/experimental/DisposeOnCompletion;", "Lkotlinx/coroutines/experimental/JobNode;", "Lkotlinx/coroutines/experimental/Job;", "job", "handle", "Lkotlinx/coroutines/experimental/DisposableHandle;", "(Lkotlinx/coroutines/experimental/Job;Lkotlinx/coroutines/experimental/DisposableHandle;)V", "invoke", "", "reason", "", "toString", "", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
public final class DisposeOnCompletion
  extends JobNode<Job>
{
  private final DisposableHandle handle;
  
  public DisposeOnCompletion(@NotNull Job paramJob, @NotNull DisposableHandle paramDisposableHandle)
  {
    super(paramJob);
    handle = paramDisposableHandle;
  }
  
  public void invoke(@Nullable Throwable paramThrowable)
  {
    handle.dispose();
  }
  
  @NotNull
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("DisposeOnCompletion[");
    localStringBuilder.append(handle);
    localStringBuilder.append(']');
    return localStringBuilder.toString();
  }
}
