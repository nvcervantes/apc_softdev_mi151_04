package kotlinx.coroutines.experimental;

import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin._Assertions;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

@Metadata(bv={1, 0, 2}, d1={"\0004\n\002\030\002\n\002\030\002\n\002\030\002\n\002\b\002\n\002\020\b\n\000\n\002\020\t\n\002\b\005\n\002\030\002\n\000\n\002\020\013\n\002\b\005\n\002\020\002\n\002\b\f\bÁ\002\030\0002\0020\0012\0020\002B\007\b\002¢\006\002\020\003J\b\020\024\032\0020\025H\003J\b\020\026\032\0020\rH\003J\r\020\027\032\0020\025H\001¢\006\002\b\030J\b\020\031\032\0020\017H\024J\b\020\032\032\0020\025H\003J\b\020\033\032\0020\025H\026J\025\020\034\032\0020\0252\006\020\035\032\0020\007H\001¢\006\002\b\036J\b\020\037\032\0020\rH\002J\b\020 \032\0020\025H\024R\016\020\004\032\0020\005XT¢\006\002\n\000R\016\020\006\032\0020\007XT¢\006\002\n\000R\016\020\b\032\0020\005XT¢\006\002\n\000R\016\020\t\032\0020\007X\004¢\006\002\n\000R\016\020\n\032\0020\005XT¢\006\002\n\000R\016\020\013\032\0020\005XT¢\006\002\n\000R\024\020\f\032\004\030\0010\r8\002@\002X\016¢\006\002\n\000R\024\020\016\032\0020\0178TX\004¢\006\006\032\004\b\020\020\021R\022\020\022\032\0020\0058\002@\002X\016¢\006\002\n\000R\024\020\023\032\0020\0178TX\004¢\006\006\032\004\b\023\020\021¨\006!"}, d2={"Lkotlinx/coroutines/experimental/DefaultExecutor;", "Lkotlinx/coroutines/experimental/EventLoopBase;", "Ljava/lang/Runnable;", "()V", "ACTIVE", "", "DEFAULT_KEEP_ALIVE", "", "FRESH", "KEEP_ALIVE_NANOS", "SHUTDOWN_ACK", "SHUTDOWN_REQ", "_thread", "Ljava/lang/Thread;", "canComplete", "", "getCanComplete", "()Z", "debugStatus", "isCompleted", "acknowledgeShutdown", "", "createThreadSync", "ensureStarted", "ensureStarted$kotlinx_coroutines_core", "isCorrectThread", "notifyStartup", "run", "shutdown", "timeout", "shutdown$kotlinx_coroutines_core", "thread", "unpark", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
public final class DefaultExecutor
  extends EventLoopBase
  implements Runnable
{
  private static final int ACTIVE = 1;
  private static final long DEFAULT_KEEP_ALIVE = 1000L;
  private static final int FRESH = 0;
  public static final DefaultExecutor INSTANCE;
  private static final long KEEP_ALIVE_NANOS = 0L;
  private static final int SHUTDOWN_ACK = 3;
  private static final int SHUTDOWN_REQ = 2;
  private static volatile Thread _thread;
  private static volatile int debugStatus;
  
  static
  {
    new DefaultExecutor();
  }
  
  private DefaultExecutor()
  {
    INSTANCE = (DefaultExecutor)this;
    TimeUnit localTimeUnit = TimeUnit.MILLISECONDS;
    try
    {
      localLong = Long.getLong("kotlinx.coroutines.DefaultExecutor.keepAlive", 1000L);
    }
    catch (SecurityException localSecurityException)
    {
      Long localLong;
      for (;;) {}
    }
    localLong = Long.valueOf(1000L);
    Intrinsics.checkExpressionValueIsNotNull(localLong, "try {\n            java.l…AULT_KEEP_ALIVE\n        }");
    KEEP_ALIVE_NANOS = localTimeUnit.toNanos(localLong.longValue());
  }
  
  private final void acknowledgeShutdown()
  {
    try
    {
      debugStatus = 3;
      clearAll();
      if (this == null) {
        throw new TypeCastException("null cannot be cast to non-null type java.lang.Object");
      }
      ((Object)this).notifyAll();
      return;
    }
    finally {}
  }
  
  private final Thread createThreadSync()
  {
    try
    {
      Thread localThread = _thread;
      if (localThread == null)
      {
        localThread = new Thread((Runnable)this, "kotlinx.coroutines.DefaultExecutor");
        _thread = localThread;
        localThread.setDaemon(true);
        localThread.start();
      }
      return localThread;
    }
    finally {}
  }
  
  private final void notifyStartup()
  {
    try
    {
      debugStatus = 1;
      if (this == null) {
        throw new TypeCastException("null cannot be cast to non-null type java.lang.Object");
      }
      ((Object)this).notifyAll();
      return;
    }
    finally {}
  }
  
  private final Thread thread()
  {
    Thread localThread = _thread;
    if (localThread != null) {
      return localThread;
    }
    return createThreadSync();
  }
  
  public final void ensureStarted$kotlinx_coroutines_core()
  {
    for (;;)
    {
      try
      {
        if (_thread == null)
        {
          i = 1;
          if ((_Assertions.ENABLED) && (i == 0)) {
            throw ((Throwable)new AssertionError("Assertion failed"));
          }
          debugStatus = 0;
          createThreadSync();
          if (debugStatus == 0)
          {
            if (this == null) {
              throw new TypeCastException("null cannot be cast to non-null type java.lang.Object");
            }
            ((Object)this).wait();
            continue;
          }
          return;
        }
      }
      finally {}
      int i = 0;
    }
  }
  
  protected boolean getCanComplete()
  {
    return false;
  }
  
  protected boolean isCompleted()
  {
    return false;
  }
  
  protected boolean isCorrectThread()
  {
    return true;
  }
  
  public void run()
  {
    TimeSourceKt.getTimeSource().registerTimeLoopThread();
    notifyStartup();
    long l1 = Long.MAX_VALUE;
    for (;;)
    {
      try
      {
        Thread.interrupted();
        long l4 = processNextEvent();
        long l3 = l1;
        long l2 = l4;
        if (l4 == Long.MAX_VALUE) {
          if (l1 == Long.MAX_VALUE)
          {
            l3 = TimeSourceKt.getTimeSource().nanoTime();
            l2 = l1;
            if (l1 != Long.MAX_VALUE) {
              break label203;
            }
            l2 = l3 + KEEP_ALIVE_NANOS;
            break label203;
            l1 = RangesKt.coerceAtMost(l4, l1);
            l3 = l2;
            l2 = l1;
          }
          else
          {
            l2 = RangesKt.coerceAtMost(l4, KEEP_ALIVE_NANOS);
            l3 = l1;
          }
        }
        l1 = l3;
        if (l2 <= 0) {
          continue;
        }
        if (debugStatus == 2)
        {
          acknowledgeShutdown();
          return;
        }
        TimeSourceKt.getTimeSource().parkNanos(this, l2);
        l1 = l3;
        continue;
        l1 = l2 - l3;
      }
      finally
      {
        _thread = (Thread)null;
        TimeSourceKt.getTimeSource().unregisterTimeLoopThread();
        if (!isEmpty()) {
          thread();
        }
      }
      label203:
      if (l1 > 0) {}
    }
  }
  
  public final void shutdown$kotlinx_coroutines_core(long paramLong)
  {
    try
    {
      if (_thread != null)
      {
        long l = System.currentTimeMillis();
        if (debugStatus == 1) {
          debugStatus = 2;
        }
        unpark();
        while ((debugStatus != 3) && (_thread != null) && (l + paramLong - System.currentTimeMillis() > 0))
        {
          if (this == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.Object");
          }
          ((Object)this).wait(paramLong);
        }
      }
      debugStatus = 0;
      return;
    }
    finally {}
  }
  
  protected void unpark()
  {
    TimeSourceKt.getTimeSource().unpark(thread());
  }
}
