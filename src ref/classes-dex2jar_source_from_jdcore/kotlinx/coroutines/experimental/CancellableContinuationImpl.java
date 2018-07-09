package kotlinx.coroutines.experimental;

import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.coroutines.experimental.Continuation;
import kotlin.coroutines.experimental.CoroutineContext;
import kotlin.coroutines.experimental.CoroutineContext.Key;
import kotlin.coroutines.experimental.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000J\n\002\030\002\n\000\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\000\n\002\020\b\n\002\b\002\n\002\030\002\n\002\b\004\n\002\020\002\n\000\n\002\020\000\n\002\b\006\n\002\020\016\n\002\b\006\n\002\020\003\n\000\n\002\030\002\n\002\b\003\b\001\030\000*\006\b\000\020\001 \0002\b\022\004\022\002H\0010\0022\b\022\004\022\002H\0010\003B\033\022\f\020\004\032\b\022\004\022\0028\0000\005\022\006\020\006\032\0020\007¢\006\002\020\bJ\032\020\016\032\0020\0172\b\020\020\032\004\030\0010\0212\006\020\022\032\0020\007H\024J\020\020\023\032\0020\0172\006\020\024\032\0020\021H\026J\n\020\025\032\004\030\0010\021H\001J\b\020\026\032\0020\017H\026J\b\020\027\032\0020\030H\026J!\020\031\032\004\030\0010\0212\006\020\032\032\0028\0002\b\020\033\032\004\030\0010\021H\026¢\006\002\020\034J\022\020\035\032\004\030\0010\0212\006\020\036\032\0020\037H\026J\031\020 \032\0020\017*\0020!2\006\020\032\032\0028\000H\026¢\006\002\020\"J\024\020#\032\0020\017*\0020!2\006\020\036\032\0020\037H\026R\024\020\t\032\004\030\0010\n8\002@\002X\016¢\006\002\n\000R\024\020\013\032\0020\n8VX\004¢\006\006\032\004\b\f\020\rR\024\020\004\032\b\022\004\022\0028\0000\005X\004¢\006\002\n\000¨\006$"}, d2={"Lkotlinx/coroutines/experimental/CancellableContinuationImpl;", "T", "Lkotlinx/coroutines/experimental/AbstractContinuation;", "Lkotlinx/coroutines/experimental/CancellableContinuation;", "delegate", "Lkotlin/coroutines/experimental/Continuation;", "resumeMode", "", "(Lkotlin/coroutines/experimental/Continuation;I)V", "_context", "Lkotlin/coroutines/experimental/CoroutineContext;", "context", "getContext", "()Lkotlin/coroutines/experimental/CoroutineContext;", "afterCompletion", "", "state", "", "mode", "completeResume", "token", "getResult", "initCancellability", "toString", "", "tryResume", "value", "idempotent", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "tryResumeWithException", "exception", "", "resumeUndispatched", "Lkotlinx/coroutines/experimental/CoroutineDispatcher;", "(Lkotlinx/coroutines/experimental/CoroutineDispatcher;Ljava/lang/Object;)V", "resumeUndispatchedWithException", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
@PublishedApi
public final class CancellableContinuationImpl<T>
  extends AbstractContinuation<T>
  implements CancellableContinuation<T>
{
  private volatile CoroutineContext _context;
  private final Continuation<T> delegate;
  
  public CancellableContinuationImpl(@NotNull Continuation<? super T> paramContinuation, int paramInt)
  {
    super(true, paramInt);
    delegate = paramContinuation;
  }
  
  protected void afterCompletion(@Nullable Object paramObject, int paramInt)
  {
    if (tryResume()) {
      return;
    }
    if ((paramObject instanceof JobSupport.CompletedExceptionally))
    {
      ResumeModeKt.resumeWithExceptionMode(delegate, ((JobSupport.CompletedExceptionally)paramObject).getException(), paramInt);
      return;
    }
    ResumeModeKt.resumeMode(delegate, CancellableContinuationKt.access$getSuccessfulResult(paramObject), paramInt);
  }
  
  public void completeResume(@NotNull Object paramObject)
  {
    Intrinsics.checkParameterIsNotNull(paramObject, "token");
    completeUpdateState(paramObject, getState(), resumeMode);
  }
  
  @NotNull
  public CoroutineContext getContext()
  {
    CoroutineContext localCoroutineContext = _context;
    if (localCoroutineContext != null) {
      return localCoroutineContext;
    }
    localCoroutineContext = delegate.getContext().plus((CoroutineContext)this);
    _context = localCoroutineContext;
    return localCoroutineContext;
  }
  
  @PublishedApi
  @Nullable
  public final Object getResult()
  {
    if (trySuspend()) {
      return IntrinsicsKt.getCOROUTINE_SUSPENDED();
    }
    Object localObject = getState();
    if ((localObject instanceof JobSupport.CompletedExceptionally)) {
      throw ((JobSupport.CompletedExceptionally)localObject).getException();
    }
    return CancellableContinuationKt.access$getSuccessfulResult(localObject);
  }
  
  public void initCancellability()
  {
    initParentJob((Job)delegate.getContext().get((CoroutineContext.Key)Job.Key));
  }
  
  public void resumeUndispatched(@NotNull CoroutineDispatcher paramCoroutineDispatcher, T paramT)
  {
    Intrinsics.checkParameterIsNotNull(paramCoroutineDispatcher, "$receiver");
    Continuation localContinuation = delegate;
    Object localObject = localContinuation;
    if (!(localContinuation instanceof DispatchedContinuation)) {
      localObject = null;
    }
    localObject = (DispatchedContinuation)localObject;
    if (localObject != null)
    {
      int i;
      if (dispatcher == paramCoroutineDispatcher) {
        i = 3;
      } else {
        i = resumeMode;
      }
      resumeImpl(paramT, i);
      return;
    }
    throw ((Throwable)new IllegalArgumentException("Must be used with DispatchedContinuation"));
  }
  
  public void resumeUndispatchedWithException(@NotNull CoroutineDispatcher paramCoroutineDispatcher, @NotNull Throwable paramThrowable)
  {
    Intrinsics.checkParameterIsNotNull(paramCoroutineDispatcher, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramThrowable, "exception");
    Continuation localContinuation = delegate;
    Object localObject = localContinuation;
    if (!(localContinuation instanceof DispatchedContinuation)) {
      localObject = null;
    }
    localObject = (DispatchedContinuation)localObject;
    if (localObject != null)
    {
      int i;
      if (dispatcher == paramCoroutineDispatcher) {
        i = 3;
      } else {
        i = resumeMode;
      }
      resumeWithExceptionImpl(paramThrowable, i);
      return;
    }
    throw ((Throwable)new IllegalArgumentException("Must be used with DispatchedContinuation"));
  }
  
  @NotNull
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(super.toString());
    localStringBuilder.append('[');
    localStringBuilder.append(CoroutineDispatcherKt.toDebugString(delegate));
    localStringBuilder.append(']');
    return localStringBuilder.toString();
  }
  
  @Nullable
  public Object tryResume(T paramT, @Nullable Object paramObject)
  {
    Object localObject2;
    Object localObject1;
    do
    {
      localObject2 = getState();
      if (!(localObject2 instanceof JobSupport.Incomplete)) {
        break;
      }
      if (paramObject == null) {
        localObject1 = paramT;
      } else {
        localObject1 = new CompletedIdempotentResult(paramObject, paramT, (JobSupport.Incomplete)localObject2);
      }
    } while (!tryUpdateState(localObject2, localObject1));
    return localObject2;
    if ((localObject2 instanceof CompletedIdempotentResult))
    {
      localObject1 = (CompletedIdempotentResult)localObject2;
      if (idempotentResume == paramObject)
      {
        int i;
        if (result == paramT) {
          i = 1;
        } else {
          i = 0;
        }
        if (i == 0) {
          throw ((Throwable)new IllegalStateException("Non-idempotent resume".toString()));
        }
        return token;
      }
      return null;
    }
    return null;
  }
  
  @Nullable
  public Object tryResumeWithException(@NotNull Throwable paramThrowable)
  {
    Intrinsics.checkParameterIsNotNull(paramThrowable, "exception");
    Object localObject;
    do
    {
      localObject = getState();
      if (!(localObject instanceof JobSupport.Incomplete)) {
        break;
      }
    } while (!tryUpdateState(localObject, new JobSupport.CompletedExceptionally(paramThrowable)));
    return localObject;
    return null;
  }
}
