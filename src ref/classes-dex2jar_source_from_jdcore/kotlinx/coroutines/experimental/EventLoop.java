package kotlinx.coroutines.experimental;

import kotlin.Deprecated;
import kotlin.Metadata;

@Metadata(bv={1, 0, 2}, d1={"\000\022\n\002\030\002\n\002\020\000\n\000\n\002\020\t\n\002\b\002\bf\030\000 \0042\0020\001:\001\004J\b\020\002\032\0020\003H&¨\006\005"}, d2={"Lkotlinx/coroutines/experimental/EventLoop;", "", "processNextEvent", "", "Factory", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
public abstract interface EventLoop
{
  public static final Factory Factory = new Factory(null);
  
  public abstract long processNextEvent();
  
  @Deprecated(message="Companion object to be removed, no replacement")
  @Metadata(bv={1, 0, 2}, d1={"\000\036\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\000\b\003\030\0002\0020\001B\007\b\002¢\006\002\020\002J\037\020\003\032\0020\0042\b\b\002\020\005\032\0020\0062\n\b\002\020\007\032\004\030\0010\bH\002¨\006\t"}, d2={"Lkotlinx/coroutines/experimental/EventLoop$Factory;", "", "()V", "invoke", "Lkotlinx/coroutines/experimental/CoroutineDispatcher;", "thread", "Ljava/lang/Thread;", "parentJob", "Lkotlinx/coroutines/experimental/Job;", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
  public static final class Factory
  {
    private Factory() {}
  }
}
