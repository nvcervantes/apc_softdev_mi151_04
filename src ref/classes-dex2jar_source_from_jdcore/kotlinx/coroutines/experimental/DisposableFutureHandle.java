package kotlinx.coroutines.experimental;

import java.util.concurrent.Future;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000\036\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\002\b\002\n\002\020\002\n\000\n\002\020\016\n\000\030\0002\0020\001B\021\022\n\020\002\032\006\022\002\b\0030\003¢\006\002\020\004J\b\020\005\032\0020\006H\026J\b\020\007\032\0020\bH\026R\022\020\002\032\006\022\002\b\0030\003X\004¢\006\002\n\000¨\006\t"}, d2={"Lkotlinx/coroutines/experimental/DisposableFutureHandle;", "Lkotlinx/coroutines/experimental/DisposableHandle;", "future", "Ljava/util/concurrent/Future;", "(Ljava/util/concurrent/Future;)V", "dispose", "", "toString", "", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
public final class DisposableFutureHandle
  implements DisposableHandle
{
  private final Future<?> future;
  
  public DisposableFutureHandle(@NotNull Future<?> paramFuture)
  {
    future = paramFuture;
  }
  
  public void dispose()
  {
    future.cancel(false);
  }
  
  @NotNull
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("DisposableFutureHandle[");
    localStringBuilder.append(future);
    localStringBuilder.append(']');
    return localStringBuilder.toString();
  }
  
  @Deprecated(message="Replace with `dispose`", replaceWith=@ReplaceWith(expression="dispose()", imports={}))
  public void unregister()
  {
    DisposableHandle.DefaultImpls.unregister(this);
  }
}
