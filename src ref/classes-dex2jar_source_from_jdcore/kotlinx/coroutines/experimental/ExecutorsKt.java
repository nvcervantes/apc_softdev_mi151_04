package kotlinx.coroutines.experimental;

import java.util.concurrent.Executor;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000\016\n\000\n\002\030\002\n\002\030\002\n\002\b\002\032\n\020\000\032\0020\001*\0020\002\032\f\020\003\032\0020\001*\0020\002H\007¨\006\004"}, d2={"asCoroutineDispatcher", "Lkotlinx/coroutines/experimental/CoroutineDispatcher;", "Ljava/util/concurrent/Executor;", "toCoroutineDispatcher", "kotlinx-coroutines-core"}, k=2, mv={1, 1, 7})
public final class ExecutorsKt
{
  @NotNull
  public static final CoroutineDispatcher asCoroutineDispatcher(@NotNull Executor paramExecutor)
  {
    Intrinsics.checkParameterIsNotNull(paramExecutor, "$receiver");
    return (CoroutineDispatcher)new ExecutorCoroutineDispatcher(paramExecutor);
  }
  
  @Deprecated(message="Renamed to `asCoroutineDispatcher`", replaceWith=@ReplaceWith(expression="asCoroutineDispatcher()", imports={}))
  @NotNull
  public static final CoroutineDispatcher toCoroutineDispatcher(@NotNull Executor paramExecutor)
  {
    Intrinsics.checkParameterIsNotNull(paramExecutor, "$receiver");
    return (CoroutineDispatcher)new ExecutorCoroutineDispatcher(paramExecutor);
  }
}
