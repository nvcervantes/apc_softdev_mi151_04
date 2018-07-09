package kotlinx.coroutines.experimental;

import kotlin.Metadata;
import kotlin.coroutines.experimental.Continuation;
import kotlin.coroutines.experimental.CoroutineContext;
import kotlin.jvm.functions.Function2;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\0000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\002\030\002\n\002\030\002\n\002\020\000\n\002\030\002\n\002\b\003\n\002\020\002\n\000\b\002\030\000*\004\b\000\020\0012\b\022\004\022\002H\0010\002B9\022\006\020\003\032\0020\004\022'\020\005\032#\b\001\022\004\022\0020\007\022\n\022\b\022\004\022\0028\0000\b\022\006\022\004\030\0010\t0\006¢\006\002\b\nø\001\000¢\006\002\020\013J\b\020\r\032\0020\016H\024R4\020\005\032#\b\001\022\004\022\0020\007\022\n\022\b\022\004\022\0028\0000\b\022\006\022\004\030\0010\t0\006¢\006\002\b\nX\004ø\001\000¢\006\004\n\002\020\f\002\004\n\002\b\t¨\006\017"}, d2={"Lkotlinx/coroutines/experimental/LazyDeferredCoroutine;", "T", "Lkotlinx/coroutines/experimental/DeferredCoroutine;", "parentContext", "Lkotlin/coroutines/experimental/CoroutineContext;", "block", "Lkotlin/Function2;", "Lkotlinx/coroutines/experimental/CoroutineScope;", "Lkotlin/coroutines/experimental/Continuation;", "", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/coroutines/experimental/CoroutineContext;Lkotlin/jvm/functions/Function2;)V", "Lkotlin/jvm/functions/Function2;", "onStart", "", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
final class LazyDeferredCoroutine<T>
  extends DeferredCoroutine<T>
{
  private final Function2<CoroutineScope, Continuation<? super T>, Object> block;
  
  public LazyDeferredCoroutine(@NotNull CoroutineContext paramCoroutineContext, @NotNull Function2<? super CoroutineScope, ? super Continuation<? super T>, ? extends Object> paramFunction2)
  {
    super(paramCoroutineContext, false);
    block = paramFunction2;
  }
  
  protected void onStart()
  {
    CancellableKt.startCoroutineCancellable(block, this, (Continuation)this);
  }
}
