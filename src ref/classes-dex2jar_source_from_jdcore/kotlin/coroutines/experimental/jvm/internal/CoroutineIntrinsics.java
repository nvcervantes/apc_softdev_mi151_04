package kotlin.coroutines.experimental.jvm.internal;

import kotlin.Metadata;
import kotlin.coroutines.experimental.Continuation;
import kotlin.coroutines.experimental.ContinuationInterceptor;
import kotlin.coroutines.experimental.CoroutineContext;
import kotlin.coroutines.experimental.CoroutineContext.Key;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000\022\n\000\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\003\032*\020\000\032\b\022\004\022\002H\0020\001\"\004\b\000\020\0022\006\020\003\032\0020\0042\f\020\005\032\b\022\004\022\002H\0020\001H\000\032 \020\006\032\b\022\004\022\002H\0020\001\"\004\b\000\020\0022\f\020\005\032\b\022\004\022\002H\0020\001¨\006\007"}, d2={"interceptContinuationIfNeeded", "Lkotlin/coroutines/experimental/Continuation;", "T", "context", "Lkotlin/coroutines/experimental/CoroutineContext;", "continuation", "normalizeContinuation", "kotlin-stdlib"}, k=2, mv={1, 1, 9})
@JvmName(name="CoroutineIntrinsics")
public final class CoroutineIntrinsics
{
  @NotNull
  public static final <T> Continuation<T> interceptContinuationIfNeeded(@NotNull CoroutineContext paramCoroutineContext, @NotNull Continuation<? super T> paramContinuation)
  {
    Intrinsics.checkParameterIsNotNull(paramCoroutineContext, "context");
    Intrinsics.checkParameterIsNotNull(paramContinuation, "continuation");
    paramCoroutineContext = (ContinuationInterceptor)paramCoroutineContext.get((CoroutineContext.Key)ContinuationInterceptor.Key);
    if (paramCoroutineContext != null)
    {
      paramCoroutineContext = paramCoroutineContext.interceptContinuation(paramContinuation);
      if (paramCoroutineContext != null) {
        return paramCoroutineContext;
      }
    }
    return paramContinuation;
  }
  
  @NotNull
  public static final <T> Continuation<T> normalizeContinuation(@NotNull Continuation<? super T> paramContinuation)
  {
    Intrinsics.checkParameterIsNotNull(paramContinuation, "continuation");
    if (!(paramContinuation instanceof CoroutineImpl)) {
      localObject1 = null;
    } else {
      localObject1 = paramContinuation;
    }
    Object localObject2 = (CoroutineImpl)localObject1;
    Object localObject1 = paramContinuation;
    if (localObject2 != null)
    {
      localObject2 = ((CoroutineImpl)localObject2).getFacade();
      localObject1 = paramContinuation;
      if (localObject2 != null) {
        localObject1 = localObject2;
      }
    }
    return localObject1;
  }
}
