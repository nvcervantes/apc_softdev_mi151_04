package kotlinx.coroutines.experimental;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000\n\n\000\n\002\030\002\n\002\b\004\032\022\020\000\032\b\022\004\022\002H\0020\001\"\004\b\000\020\002\032\037\020\000\032\b\022\004\022\002H\0020\001\"\004\b\000\020\0022\006\020\003\032\002H\002¢\006\002\020\004¨\006\005"}, d2={"CompletableDeferred", "Lkotlinx/coroutines/experimental/CompletableDeferred;", "T", "value", "(Ljava/lang/Object;)Lkotlinx/coroutines/experimental/CompletableDeferred;", "kotlinx-coroutines-core"}, k=2, mv={1, 1, 7})
public final class CompletableDeferredKt
{
  @NotNull
  public static final <T> CompletableDeferred<T> CompletableDeferred()
  {
    return (CompletableDeferred)new CompletableDeferredImpl();
  }
  
  @NotNull
  public static final <T> CompletableDeferred<T> CompletableDeferred(T paramT)
  {
    CompletableDeferredImpl localCompletableDeferredImpl = new CompletableDeferredImpl();
    localCompletableDeferredImpl.complete(paramT);
    return (CompletableDeferred)localCompletableDeferredImpl;
  }
}
