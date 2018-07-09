package kotlinx.coroutines.experimental;

import java.util.concurrent.Executor;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000\022\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\002\b\004\b\002\030\0002\0020\001B\r\022\006\020\002\032\0020\003¢\006\002\020\004R\024\020\002\032\0020\003X\004¢\006\b\n\000\032\004\b\005\020\006¨\006\007"}, d2={"Lkotlinx/coroutines/experimental/ExecutorCoroutineDispatcher;", "Lkotlinx/coroutines/experimental/ExecutorCoroutineDispatcherBase;", "executor", "Ljava/util/concurrent/Executor;", "(Ljava/util/concurrent/Executor;)V", "getExecutor", "()Ljava/util/concurrent/Executor;", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
final class ExecutorCoroutineDispatcher
  extends ExecutorCoroutineDispatcherBase
{
  @NotNull
  private final Executor executor;
  
  public ExecutorCoroutineDispatcher(@NotNull Executor paramExecutor)
  {
    executor = paramExecutor;
  }
  
  @NotNull
  public Executor getExecutor()
  {
    return executor;
  }
}
