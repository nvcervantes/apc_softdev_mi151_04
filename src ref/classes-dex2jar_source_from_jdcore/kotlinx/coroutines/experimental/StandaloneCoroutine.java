package kotlinx.coroutines.experimental;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.experimental.CoroutineContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000(\n\002\030\002\n\002\030\002\n\002\020\002\n\000\n\002\030\002\n\000\n\002\020\013\n\002\b\003\n\002\020\000\n\000\n\002\020\b\n\000\b\022\030\0002\b\022\004\022\0020\0020\001B\025\022\006\020\003\032\0020\004\022\006\020\005\032\0020\006¢\006\002\020\007J\032\020\b\032\0020\0022\b\020\t\032\004\030\0010\n2\006\020\013\032\0020\fH\024R\016\020\003\032\0020\004X\004¢\006\002\n\000¨\006\r"}, d2={"Lkotlinx/coroutines/experimental/StandaloneCoroutine;", "Lkotlinx/coroutines/experimental/AbstractCoroutine;", "", "parentContext", "Lkotlin/coroutines/experimental/CoroutineContext;", "active", "", "(Lkotlin/coroutines/experimental/CoroutineContext;Z)V", "afterCompletion", "state", "", "mode", "", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
class StandaloneCoroutine
  extends AbstractCoroutine<Unit>
{
  private final CoroutineContext parentContext;
  
  public StandaloneCoroutine(@NotNull CoroutineContext paramCoroutineContext, boolean paramBoolean)
  {
    super(paramCoroutineContext, paramBoolean);
    parentContext = paramCoroutineContext;
  }
  
  protected void afterCompletion(@Nullable Object paramObject, int paramInt)
  {
    if ((paramObject instanceof JobSupport.CompletedExceptionally)) {
      CoroutineExceptionHandlerKt.handleCoroutineException(parentContext, ((JobSupport.CompletedExceptionally)paramObject).getException());
    }
  }
}
