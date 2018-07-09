package kotlinx.coroutines.experimental;

import kotlin.Metadata;
import kotlin.coroutines.experimental.Continuation;
import kotlin.coroutines.experimental.CoroutineContext;
import kotlin.coroutines.experimental.jvm.internal.CoroutineImpl;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.experimental.selects.SelectInstance;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000<\n\002\030\002\n\000\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\000\n\002\020\013\n\002\b\007\n\002\020\002\n\002\b\002\n\002\030\002\n\000\n\002\030\002\n\002\030\002\n\002\020\000\n\002\b\002\b\023\030\000*\004\b\000\020\0012\b\022\004\022\002H\0010\0022\b\022\004\022\002H\0010\003B\025\022\006\020\004\032\0020\005\022\006\020\006\032\0020\007¢\006\002\020\bJ\023\020\t\032\0028\000H@ø\001\000¢\006\004\b\n\020\013J\r\020\f\032\0028\000H\026¢\006\002\020\rJH\020\016\032\0020\017\"\004\b\001\020\0202\f\020\021\032\b\022\004\022\002H\0200\0222\"\020\023\032\036\b\001\022\004\022\0028\000\022\n\022\b\022\004\022\002H\0200\025\022\006\022\004\030\0010\0260\024H\026ø\001\000¢\006\002\020\027\002\004\n\002\b\t¨\006\030"}, d2={"Lkotlinx/coroutines/experimental/DeferredCoroutine;", "T", "Lkotlinx/coroutines/experimental/AbstractCoroutine;", "Lkotlinx/coroutines/experimental/Deferred;", "parentContext", "Lkotlin/coroutines/experimental/CoroutineContext;", "active", "", "(Lkotlin/coroutines/experimental/CoroutineContext;Z)V", "await", "await$suspendImpl", "(Lkotlinx/coroutines/experimental/DeferredCoroutine;Lkotlin/coroutines/experimental/Continuation;)Ljava/lang/Object;", "getCompleted", "()Ljava/lang/Object;", "registerSelectAwait", "", "R", "select", "Lkotlinx/coroutines/experimental/selects/SelectInstance;", "block", "Lkotlin/Function2;", "Lkotlin/coroutines/experimental/Continuation;", "", "(Lkotlinx/coroutines/experimental/selects/SelectInstance;Lkotlin/jvm/functions/Function2;)V", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
class DeferredCoroutine<T>
  extends AbstractCoroutine<T>
  implements Deferred<T>
{
  public DeferredCoroutine(@NotNull CoroutineContext paramCoroutineContext, boolean paramBoolean)
  {
    super(paramCoroutineContext, paramBoolean);
  }
  
  public Object await(Continuation<? super T> paramContinuation)
  {
    return await$suspendImpl(this, paramContinuation);
  }
  
  public T getCompleted()
  {
    return getCompletedInternal();
  }
  
  public boolean isComputing()
  {
    return Deferred.DefaultImpls.isComputing(this);
  }
  
  public <R> void registerSelectAwait(@NotNull SelectInstance<? super R> paramSelectInstance, @NotNull Function2<? super T, ? super Continuation<? super R>, ? extends Object> paramFunction2)
  {
    Intrinsics.checkParameterIsNotNull(paramSelectInstance, "select");
    Intrinsics.checkParameterIsNotNull(paramFunction2, "block");
    registerSelectAwaitInternal(paramSelectInstance, paramFunction2);
  }
}
