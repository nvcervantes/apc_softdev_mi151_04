package kotlinx.coroutines.experimental;

import java.util.concurrent.locks.LockSupport;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\0000\n\002\030\002\n\002\030\002\n\002\b\002\n\002\020\t\n\000\n\002\020\002\n\000\n\002\020\000\n\002\b\003\n\002\030\002\n\002\b\004\n\002\030\002\n\002\b\002\bÀ\002\030\0002\0020\001B\007\b\002¢\006\002\020\002J\b\020\003\032\0020\004H\026J\030\020\005\032\0020\0062\006\020\007\032\0020\b2\006\020\t\032\0020\004H\026J\b\020\n\032\0020\006H\026J\020\020\013\032\0020\f2\006\020\r\032\0020\fH\026J\b\020\016\032\0020\006H\026J\020\020\017\032\0020\0062\006\020\020\032\0020\021H\026J\b\020\022\032\0020\006H\026¨\006\023"}, d2={"Lkotlinx/coroutines/experimental/DefaultTimeSource;", "Lkotlinx/coroutines/experimental/TimeSource;", "()V", "nanoTime", "", "parkNanos", "", "blocker", "", "nanos", "registerTimeLoopThread", "trackTask", "Ljava/lang/Runnable;", "block", "unTrackTask", "unpark", "thread", "Ljava/lang/Thread;", "unregisterTimeLoopThread", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
public final class DefaultTimeSource
  implements TimeSource
{
  public static final DefaultTimeSource INSTANCE;
  
  static
  {
    new DefaultTimeSource();
  }
  
  private DefaultTimeSource()
  {
    INSTANCE = (DefaultTimeSource)this;
  }
  
  public long nanoTime()
  {
    return System.nanoTime();
  }
  
  public void parkNanos(@NotNull Object paramObject, long paramLong)
  {
    Intrinsics.checkParameterIsNotNull(paramObject, "blocker");
    LockSupport.parkNanos(paramObject, paramLong);
  }
  
  public void registerTimeLoopThread() {}
  
  @NotNull
  public Runnable trackTask(@NotNull Runnable paramRunnable)
  {
    Intrinsics.checkParameterIsNotNull(paramRunnable, "block");
    return paramRunnable;
  }
  
  public void unTrackTask() {}
  
  public void unpark(@NotNull Thread paramThread)
  {
    Intrinsics.checkParameterIsNotNull(paramThread, "thread");
    LockSupport.unpark(paramThread);
  }
  
  public void unregisterTimeLoopThread() {}
}
