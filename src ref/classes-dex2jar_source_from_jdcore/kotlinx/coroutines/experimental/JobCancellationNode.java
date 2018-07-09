package kotlinx.coroutines.experimental;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000&\n\002\030\002\n\000\n\002\030\002\n\002\030\002\n\002\b\003\n\002\030\002\n\000\n\002\020\002\n\000\n\002\020\003\n\002\b\002\b \030\000*\n\b\000\020\001 \001*\0020\0022\b\022\004\022\002H\0010\003B\r\022\006\020\004\032\0028\000¢\006\002\020\005J\023\020\b\032\0020\t2\b\020\n\032\004\030\0010\013H\002J\022\020\f\032\0020\t2\b\020\n\032\004\030\0010\013H&R\016\020\006\032\0020\007X\004¢\006\002\n\000¨\006\r"}, d2={"Lkotlinx/coroutines/experimental/JobCancellationNode;", "J", "Lkotlinx/coroutines/experimental/Job;", "Lkotlinx/coroutines/experimental/JobNode;", "job", "(Lkotlinx/coroutines/experimental/Job;)V", "_invoked", "Lkotlinx/atomicfu/AtomicInt;", "invoke", "", "reason", "", "invokeOnce", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
public abstract class JobCancellationNode<J extends Job>
  extends JobNode<J>
{
  private static final AtomicIntegerFieldUpdater _invoked$FU = AtomicIntegerFieldUpdater.newUpdater(JobCancellationNode.class, "_invoked");
  private volatile int _invoked = 0;
  
  public JobCancellationNode(@NotNull J paramJ)
  {
    super(paramJ);
  }
  
  public final void invoke(@Nullable Throwable paramThrowable)
  {
    if (_invoked$FU.compareAndSet(this, 0, 1)) {
      invokeOnce(paramThrowable);
    }
  }
  
  public abstract void invokeOnce(@Nullable Throwable paramThrowable);
}
