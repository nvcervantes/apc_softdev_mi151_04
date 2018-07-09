package kotlinx.coroutines.experimental;

import kotlin.Metadata;
import kotlin.coroutines.experimental.CoroutineContext;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000\032\n\002\030\002\n\002\020\000\n\000\n\002\030\002\n\002\b\b\n\002\020\013\n\002\b\002\bf\030\0002\0020\001R\032\020\002\032\0020\0038&X§\004¢\006\f\022\004\b\004\020\005\032\004\b\006\020\007R\032\020\b\032\0020\0038VX\004¢\006\f\022\004\b\t\020\005\032\004\b\n\020\007R\022\020\013\032\0020\fX¦\004¢\006\006\032\004\b\013\020\r¨\006\016"}, d2={"Lkotlinx/coroutines/experimental/CoroutineScope;", "", "context", "Lkotlin/coroutines/experimental/CoroutineContext;", "context$annotations", "()V", "getContext", "()Lkotlin/coroutines/experimental/CoroutineContext;", "coroutineContext", "coroutineContext$annotations", "getCoroutineContext", "isActive", "", "()Z", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
public abstract interface CoroutineScope
{
  @NotNull
  public abstract CoroutineContext getContext();
  
  @NotNull
  public abstract CoroutineContext getCoroutineContext();
  
  public abstract boolean isActive();
  
  @Metadata(bv={1, 0, 2}, k=3, mv={1, 1, 7})
  public static final class DefaultImpls
  {
    @NotNull
    public static CoroutineContext getCoroutineContext(CoroutineScope paramCoroutineScope)
    {
      return paramCoroutineScope.getContext();
    }
  }
}
