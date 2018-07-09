package kotlinx.coroutines.experimental;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000\034\n\000\n\002\020\b\n\002\b\003\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\000\032\034\020\004\032\0020\0052\b\b\002\020\006\032\0020\0072\n\b\002\020\b\032\004\030\0010\t\"\016\020\000\032\0020\001XT¢\006\002\n\000\"\016\020\002\032\0020\001XT¢\006\002\n\000\"\016\020\003\032\0020\001XT¢\006\002\n\000¨\006\n"}, d2={"DELAYED", "", "REMOVED", "RESCHEDULED", "EventLoop", "Lkotlinx/coroutines/experimental/CoroutineDispatcher;", "thread", "Ljava/lang/Thread;", "parentJob", "Lkotlinx/coroutines/experimental/Job;", "kotlinx-coroutines-core"}, k=2, mv={1, 1, 7})
public final class EventLoopKt
{
  private static final int DELAYED = 0;
  private static final int REMOVED = 1;
  private static final int RESCHEDULED = 2;
  
  @NotNull
  public static final CoroutineDispatcher EventLoop(@NotNull Thread paramThread, @Nullable Job paramJob)
  {
    Intrinsics.checkParameterIsNotNull(paramThread, "thread");
    paramThread = new EventLoopImpl(paramThread);
    if (paramJob != null) {
      paramThread.initParentJob(paramJob);
    }
    return (CoroutineDispatcher)paramThread;
  }
}
