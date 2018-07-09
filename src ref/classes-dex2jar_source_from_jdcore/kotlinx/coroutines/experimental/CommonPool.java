package kotlinx.coroutines.experimental;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Metadata;
import kotlin.coroutines.experimental.CoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000R\n\002\030\002\n\002\030\002\n\002\b\002\n\002\030\002\n\000\n\002\020\013\n\002\b\003\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\002\n\002\020\b\n\000\n\002\020\002\n\000\n\002\030\002\n\002\030\002\n\002\b\005\n\002\020\t\n\002\b\002\n\002\020\016\n\002\b\002\bÆ\002\030\0002\0020\001B\007\b\002¢\006\002\020\002J$\020\007\032\004\030\001H\b\"\004\b\000\020\b2\f\020\t\032\b\022\004\022\002H\b0\nH\b¢\006\002\020\013J\b\020\f\032\0020\rH\002J\b\020\016\032\0020\rH\002J\b\020\017\032\0020\020H\002J\030\020\021\032\0020\0222\006\020\023\032\0020\0242\006\020\t\032\0020\025H\026J\b\020\026\032\0020\004H\003J\r\020\027\032\0020\022H\001¢\006\002\b\030J\025\020\031\032\0020\0222\006\020\032\032\0020\033H\001¢\006\002\b\034J\b\020\035\032\0020\036H\026J\r\020\005\032\0020\022H\001¢\006\002\b\037R\024\020\003\032\004\030\0010\0048\002@\002X\016¢\006\002\n\000R\016\020\005\032\0020\006X\016¢\006\002\n\000¨\006 "}, d2={"Lkotlinx/coroutines/experimental/CommonPool;", "Lkotlinx/coroutines/experimental/CoroutineDispatcher;", "()V", "_pool", "Ljava/util/concurrent/Executor;", "usePrivatePool", "", "Try", "T", "block", "Lkotlin/Function0;", "(Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "createPlainPool", "Ljava/util/concurrent/ExecutorService;", "createPool", "defaultParallelism", "", "dispatch", "", "context", "Lkotlin/coroutines/experimental/CoroutineContext;", "Ljava/lang/Runnable;", "getOrCreatePoolSync", "restore", "restore$kotlinx_coroutines_core", "shutdown", "timeout", "", "shutdown$kotlinx_coroutines_core", "toString", "", "usePrivatePool$kotlinx_coroutines_core", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
public final class CommonPool
  extends CoroutineDispatcher
{
  public static final CommonPool INSTANCE;
  private static volatile Executor _pool;
  private static boolean usePrivatePool;
  
  static
  {
    new CommonPool();
  }
  
  private CommonPool()
  {
    INSTANCE = (CommonPool)this;
  }
  
  private final <T> T Try(Function0<? extends T> paramFunction0)
  {
    try
    {
      paramFunction0 = paramFunction0.invoke();
      return paramFunction0;
    }
    catch (Throwable paramFunction0)
    {
      for (;;) {}
    }
    return null;
  }
  
  private final ExecutorService createPlainPool()
  {
    Object localObject = new AtomicInteger();
    localObject = Executors.newFixedThreadPool(defaultParallelism(), (ThreadFactory)new ThreadFactory()
    {
      @NotNull
      public final Thread newThread(Runnable paramAnonymousRunnable)
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("CommonPool-worker-");
        localStringBuilder.append($threadId.incrementAndGet());
        paramAnonymousRunnable = new Thread(paramAnonymousRunnable, localStringBuilder.toString());
        paramAnonymousRunnable.setDaemon(true);
        return paramAnonymousRunnable;
      }
    });
    Intrinsics.checkExpressionValueIsNotNull(localObject, "Executors.newFixedThread…Daemon = true }\n        }");
    return localObject;
  }
  
  private final ExecutorService createPool()
  {
    localObject5 = null;
    try
    {
      localObject3 = Class.forName("java.util.concurrent.ForkJoinPool");
    }
    catch (Throwable localThrowable1)
    {
      Object localObject3;
      for (;;) {}
    }
    localObject3 = null;
    if ((localObject3 == null) || (!usePrivatePool)) {}
    try
    {
      localObject1 = ((Class)localObject3).getMethod("commonPool", new Class[0]);
      if (localObject1 == null) {
        break label160;
      }
      localObject1 = ((Method)localObject1).invoke(null, new Object[0]);
    }
    catch (Throwable localThrowable2)
    {
      for (;;)
      {
        Object localObject1;
        Object localObject4;
        continue;
        Object localObject2 = null;
      }
    }
    localObject4 = localObject1;
    if (!(localObject1 instanceof ExecutorService)) {
      localObject4 = null;
    }
    localObject1 = (ExecutorService)localObject4;
    break label73;
    localObject1 = null;
    label73:
    if (localObject1 != null) {
      return localObject1;
    }
    try
    {
      localObject3 = ((Class)localObject3).getConstructor(new Class[] { Integer.TYPE }).newInstance(new Object[] { Integer.valueOf(INSTANCE.defaultParallelism()) });
      localObject1 = localObject3;
      if (!(localObject3 instanceof ExecutorService)) {
        localObject1 = null;
      }
      localObject1 = (ExecutorService)localObject1;
    }
    catch (Throwable localThrowable3)
    {
      for (;;)
      {
        localObject2 = localObject5;
      }
    }
    if (localObject1 != null) {
      return localObject1;
    }
    return createPlainPool();
    return createPlainPool();
  }
  
  private final int defaultParallelism()
  {
    return RangesKt.coerceAtLeast(Runtime.getRuntime().availableProcessors() - 1, 1);
  }
  
  private final Executor getOrCreatePoolSync()
  {
    try
    {
      Object localObject1 = _pool;
      if (localObject1 == null)
      {
        localObject1 = createPool();
        _pool = (Executor)localObject1;
        localObject1 = (Executor)localObject1;
      }
      return localObject1;
    }
    finally {}
  }
  
  public void dispatch(@NotNull CoroutineContext paramCoroutineContext, @NotNull Runnable paramRunnable)
  {
    Intrinsics.checkParameterIsNotNull(paramCoroutineContext, "context");
    Intrinsics.checkParameterIsNotNull(paramRunnable, "block");
    try
    {
      paramCoroutineContext = _pool;
      if (paramCoroutineContext == null) {
        paramCoroutineContext = getOrCreatePoolSync();
      }
      paramCoroutineContext.execute(TimeSourceKt.getTimeSource().trackTask(paramRunnable));
      return;
    }
    catch (RejectedExecutionException paramCoroutineContext)
    {
      for (;;) {}
    }
    TimeSourceKt.getTimeSource().unTrackTask();
    DefaultExecutor.INSTANCE.execute(paramRunnable);
  }
  
  public final void restore$kotlinx_coroutines_core()
  {
    try
    {
      shutdown$kotlinx_coroutines_core(0L);
      usePrivatePool = false;
      _pool = (Executor)null;
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  public final void shutdown$kotlinx_coroutines_core(long paramLong)
  {
    try
    {
      Object localObject3 = _pool;
      Object localObject1 = localObject3;
      if (!(localObject3 instanceof ExecutorService)) {
        localObject1 = null;
      }
      localObject1 = (ExecutorService)localObject1;
      if (localObject1 != null)
      {
        ((ExecutorService)localObject1).shutdown();
        if (paramLong > 0) {
          ((ExecutorService)localObject1).awaitTermination(paramLong, TimeUnit.MILLISECONDS);
        }
        localObject1 = ((Iterable)((ExecutorService)localObject1).shutdownNow()).iterator();
        while (((Iterator)localObject1).hasNext())
        {
          localObject3 = (Runnable)((Iterator)localObject1).next();
          DefaultExecutor localDefaultExecutor = DefaultExecutor.INSTANCE;
          Intrinsics.checkExpressionValueIsNotNull(localObject3, "it");
          localDefaultExecutor.execute((Runnable)localObject3);
        }
      }
      _pool = (Executor)shutdown.2.INSTANCE;
      return;
    }
    finally {}
  }
  
  @NotNull
  public String toString()
  {
    return "CommonPool";
  }
  
  public final void usePrivatePool$kotlinx_coroutines_core()
  {
    try
    {
      shutdown$kotlinx_coroutines_core(0L);
      usePrivatePool = true;
      _pool = (Executor)null;
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
}
