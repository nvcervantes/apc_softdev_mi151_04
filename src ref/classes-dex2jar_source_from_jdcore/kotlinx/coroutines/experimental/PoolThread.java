package kotlinx.coroutines.experimental;

import kotlin.Metadata;
import kotlin.jvm.JvmField;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000\036\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\020\016\n\002\b\002\b\000\030\0002\0020\001B\035\022\006\020\002\032\0020\003\022\006\020\004\032\0020\005\022\006\020\006\032\0020\007¢\006\002\020\bR\020\020\002\032\0020\0038\006X\004¢\006\002\n\000¨\006\t"}, d2={"Lkotlinx/coroutines/experimental/PoolThread;", "Ljava/lang/Thread;", "dispatcher", "Lkotlinx/coroutines/experimental/ThreadPoolDispatcher;", "target", "Ljava/lang/Runnable;", "name", "", "(Lkotlinx/coroutines/experimental/ThreadPoolDispatcher;Ljava/lang/Runnable;Ljava/lang/String;)V", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
public final class PoolThread
  extends Thread
{
  @JvmField
  @NotNull
  public final ThreadPoolDispatcher dispatcher;
  
  public PoolThread(@NotNull ThreadPoolDispatcher paramThreadPoolDispatcher, @NotNull Runnable paramRunnable, @NotNull String paramString)
  {
    super(paramRunnable, paramString);
    dispatcher = paramThreadPoolDispatcher;
    setDaemon(true);
  }
}
