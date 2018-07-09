package kotlinx.coroutines.experimental;

import java.util.concurrent.locks.LockSupport;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.coroutines.experimental.ContinuationInterceptor;
import kotlin.coroutines.experimental.CoroutineContext;
import kotlin.coroutines.experimental.CoroutineContext.Key;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000:\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\020\013\n\002\b\002\n\002\030\002\n\000\n\002\020\002\n\000\n\002\020\000\n\000\n\002\020\b\n\002\b\003\b\002\030\000*\004\b\000\020\0012\b\022\004\022\002H\0010\002B\035\022\006\020\003\032\0020\004\022\006\020\005\032\0020\006\022\006\020\007\032\0020\b¢\006\002\020\tJ\032\020\f\032\0020\r2\b\020\016\032\004\030\0010\0172\006\020\020\032\0020\021H\024J\r\020\022\032\0028\000H\007¢\006\002\020\023R\016\020\005\032\0020\006X\004¢\006\002\n\000R\020\020\n\032\004\030\0010\013X\004¢\006\002\n\000R\016\020\007\032\0020\bX\004¢\006\002\n\000¨\006\024"}, d2={"Lkotlinx/coroutines/experimental/BlockingCoroutine;", "T", "Lkotlinx/coroutines/experimental/AbstractCoroutine;", "parentContext", "Lkotlin/coroutines/experimental/CoroutineContext;", "blockedThread", "Ljava/lang/Thread;", "privateEventLoop", "", "(Lkotlin/coroutines/experimental/CoroutineContext;Ljava/lang/Thread;Z)V", "eventLoop", "Lkotlinx/coroutines/experimental/EventLoop;", "afterCompletion", "", "state", "", "mode", "", "joinBlocking", "()Ljava/lang/Object;", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
final class BlockingCoroutine<T>
  extends AbstractCoroutine<T>
{
  private final Thread blockedThread;
  private final EventLoop eventLoop;
  private final boolean privateEventLoop;
  
  public BlockingCoroutine(@NotNull CoroutineContext paramCoroutineContext, @NotNull Thread paramThread, boolean paramBoolean)
  {
    super(paramCoroutineContext, true);
    blockedThread = paramThread;
    privateEventLoop = paramBoolean;
    paramThread = paramCoroutineContext.get((CoroutineContext.Key)ContinuationInterceptor.Key);
    paramCoroutineContext = paramThread;
    if (!(paramThread instanceof EventLoop)) {
      paramCoroutineContext = null;
    }
    eventLoop = ((EventLoop)paramCoroutineContext);
    if ((privateEventLoop) && (!(eventLoop instanceof EventLoopImpl))) {
      throw ((Throwable)new IllegalArgumentException("Failed requirement.".toString()));
    }
  }
  
  protected void afterCompletion(@Nullable Object paramObject, int paramInt)
  {
    if ((Intrinsics.areEqual(Thread.currentThread(), blockedThread) ^ true)) {
      LockSupport.unpark(blockedThread);
    }
  }
  
  public final T joinBlocking()
  {
    TimeSourceKt.getTimeSource().registerTimeLoopThread();
    for (;;)
    {
      if (Thread.interrupted())
      {
        localObject1 = (Throwable)new InterruptedException();
        cancel((Throwable)localObject1);
        throw ((Throwable)localObject1);
      }
      Object localObject1 = eventLoop;
      long l;
      if (localObject1 != null) {
        l = ((EventLoop)localObject1).processNextEvent();
      } else {
        l = Long.MAX_VALUE;
      }
      if (isCompleted())
      {
        if (privateEventLoop)
        {
          localObject1 = eventLoop;
          if (localObject1 == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.experimental.EventLoopImpl");
          }
          ((EventLoopImpl)localObject1).shutdown();
        }
        TimeSourceKt.getTimeSource().unregisterTimeLoopThread();
        Object localObject2 = getState();
        if (!(localObject2 instanceof JobSupport.CompletedExceptionally)) {
          localObject1 = null;
        } else {
          localObject1 = localObject2;
        }
        localObject1 = (JobSupport.CompletedExceptionally)localObject1;
        if (localObject1 != null) {
          throw ((JobSupport.CompletedExceptionally)localObject1).getException();
        }
        return localObject2;
      }
      TimeSourceKt.getTimeSource().parkNanos(this, l);
    }
  }
}
