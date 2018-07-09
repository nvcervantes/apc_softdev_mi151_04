package kotlinx.coroutines.experimental;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.coroutines.experimental.AbstractCoroutineContextElement;
import kotlin.coroutines.experimental.Continuation;
import kotlin.coroutines.experimental.ContinuationInterceptor;
import kotlin.coroutines.experimental.CoroutineContext;
import kotlin.coroutines.experimental.CoroutineContext.Key;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\0008\n\002\030\002\n\002\030\002\n\002\030\002\n\002\b\002\n\002\020\002\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\002\b\003\n\002\020\013\n\002\b\003\n\002\020\016\n\000\b&\030\0002\0020\0012\0020\002B\005¢\006\002\020\003J\030\020\004\032\0020\0052\006\020\006\032\0020\0072\006\020\b\032\0020\tH&J\"\020\n\032\b\022\004\022\002H\f0\013\"\004\b\000\020\f2\f\020\r\032\b\022\004\022\002H\f0\013H\026J\020\020\016\032\0020\0172\006\020\006\032\0020\007H\026J\021\020\020\032\0020\0002\006\020\021\032\0020\000H\002J\b\020\022\032\0020\023H\026¨\006\024"}, d2={"Lkotlinx/coroutines/experimental/CoroutineDispatcher;", "Lkotlin/coroutines/experimental/AbstractCoroutineContextElement;", "Lkotlin/coroutines/experimental/ContinuationInterceptor;", "()V", "dispatch", "", "context", "Lkotlin/coroutines/experimental/CoroutineContext;", "block", "Ljava/lang/Runnable;", "interceptContinuation", "Lkotlin/coroutines/experimental/Continuation;", "T", "continuation", "isDispatchNeeded", "", "plus", "other", "toString", "", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
public abstract class CoroutineDispatcher
  extends AbstractCoroutineContextElement
  implements ContinuationInterceptor
{
  public CoroutineDispatcher()
  {
    super((CoroutineContext.Key)ContinuationInterceptor.Key);
  }
  
  public abstract void dispatch(@NotNull CoroutineContext paramCoroutineContext, @NotNull Runnable paramRunnable);
  
  @NotNull
  public <T> Continuation<T> interceptContinuation(@NotNull Continuation<? super T> paramContinuation)
  {
    Intrinsics.checkParameterIsNotNull(paramContinuation, "continuation");
    return (Continuation)new DispatchedContinuation(this, paramContinuation);
  }
  
  public boolean isDispatchNeeded(@NotNull CoroutineContext paramCoroutineContext)
  {
    Intrinsics.checkParameterIsNotNull(paramCoroutineContext, "context");
    return true;
  }
  
  @Deprecated(level=DeprecationLevel.ERROR, message="Operator '+' on two CoroutineDispatcher objects is meaningless. CoroutineDispatcher is a coroutine context element and `+` is a set-sum operator for coroutine contexts. The dispatcher to the right of `+` just replaces the dispatcher the left of `+`.")
  @NotNull
  public final CoroutineDispatcher plus(@NotNull CoroutineDispatcher paramCoroutineDispatcher)
  {
    Intrinsics.checkParameterIsNotNull(paramCoroutineDispatcher, "other");
    return paramCoroutineDispatcher;
  }
  
  @NotNull
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("");
    localStringBuilder.append(getClass().getSimpleName());
    localStringBuilder.append('@');
    localStringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
    return localStringBuilder.toString();
  }
}
