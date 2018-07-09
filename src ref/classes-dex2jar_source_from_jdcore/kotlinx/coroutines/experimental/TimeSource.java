package kotlinx.coroutines.experimental;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000(\n\002\030\002\n\002\020\000\n\000\n\002\020\t\n\000\n\002\020\002\n\002\b\004\n\002\030\002\n\002\b\004\n\002\030\002\n\002\b\002\b`\030\0002\0020\001J\b\020\002\032\0020\003H&J\030\020\004\032\0020\0052\006\020\006\032\0020\0012\006\020\007\032\0020\003H&J\b\020\b\032\0020\005H&J\020\020\t\032\0020\n2\006\020\013\032\0020\nH&J\b\020\f\032\0020\005H&J\020\020\r\032\0020\0052\006\020\016\032\0020\017H&J\b\020\020\032\0020\005H&¨\006\021"}, d2={"Lkotlinx/coroutines/experimental/TimeSource;", "", "nanoTime", "", "parkNanos", "", "blocker", "nanos", "registerTimeLoopThread", "trackTask", "Ljava/lang/Runnable;", "block", "unTrackTask", "unpark", "thread", "Ljava/lang/Thread;", "unregisterTimeLoopThread", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
public abstract interface TimeSource
{
  public abstract long nanoTime();
  
  public abstract void parkNanos(@NotNull Object paramObject, long paramLong);
  
  public abstract void registerTimeLoopThread();
  
  @NotNull
  public abstract Runnable trackTask(@NotNull Runnable paramRunnable);
  
  public abstract void unTrackTask();
  
  public abstract void unpark(@NotNull Thread paramThread);
  
  public abstract void unregisterTimeLoopThread();
}
