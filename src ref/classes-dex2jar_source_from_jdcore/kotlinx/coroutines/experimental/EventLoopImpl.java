package kotlinx.coroutines.experimental;

import kotlin.Metadata;
import kotlin._Assertions;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000(\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\002\b\002\n\002\020\013\n\002\b\004\n\002\030\002\n\000\n\002\020\002\n\002\b\005\b\000\030\0002\0020\001B\r\022\006\020\002\032\0020\003¢\006\002\020\004J\016\020\f\032\0020\r2\006\020\016\032\0020\013J\b\020\017\032\0020\006H\024J\006\020\020\032\0020\rJ\b\020\021\032\0020\rH\024R\024\020\005\032\0020\0068TX\004¢\006\006\032\004\b\007\020\bR\024\020\t\032\0020\0068TX\004¢\006\006\032\004\b\t\020\bR\020\020\n\032\004\030\0010\013X\016¢\006\002\n\000R\016\020\002\032\0020\003X\004¢\006\002\n\000¨\006\022"}, d2={"Lkotlinx/coroutines/experimental/EventLoopImpl;", "Lkotlinx/coroutines/experimental/EventLoopBase;", "thread", "Ljava/lang/Thread;", "(Ljava/lang/Thread;)V", "canComplete", "", "getCanComplete", "()Z", "isCompleted", "parentJob", "Lkotlinx/coroutines/experimental/Job;", "initParentJob", "", "coroutine", "isCorrectThread", "shutdown", "unpark", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
public final class EventLoopImpl
  extends EventLoopBase
{
  private Job parentJob;
  private final Thread thread;
  
  public EventLoopImpl(@NotNull Thread paramThread)
  {
    thread = paramThread;
  }
  
  protected boolean getCanComplete()
  {
    return parentJob != null;
  }
  
  public final void initParentJob(@NotNull Job paramJob)
  {
    Intrinsics.checkParameterIsNotNull(paramJob, "coroutine");
    int i;
    if (parentJob == null) {
      i = 1;
    } else {
      i = 0;
    }
    if (i == 0) {
      throw ((Throwable)new IllegalArgumentException("Failed requirement.".toString()));
    }
    parentJob = paramJob;
  }
  
  protected boolean isCompleted()
  {
    Job localJob = parentJob;
    return (localJob != null) && (localJob.isCompleted() == true);
  }
  
  protected boolean isCorrectThread()
  {
    return Thread.currentThread() == thread;
  }
  
  public final void shutdown()
  {
    boolean bool = isCompleted();
    if ((_Assertions.ENABLED) && (!bool)) {
      throw ((Throwable)new AssertionError("Assertion failed"));
    }
    bool = isCorrectThread();
    if ((_Assertions.ENABLED) && (!bool)) {
      throw ((Throwable)new AssertionError("Assertion failed"));
    }
    while (processNextEvent() <= 0) {}
    rescheduleAllDelayed();
  }
  
  protected void unpark()
  {
    if (Thread.currentThread() != thread) {
      TimeSourceKt.getTimeSource().unpark(thread);
    }
  }
}
