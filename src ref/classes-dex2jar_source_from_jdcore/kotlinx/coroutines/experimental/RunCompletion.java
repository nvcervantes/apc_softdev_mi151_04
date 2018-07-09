package kotlinx.coroutines.experimental;

import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.coroutines.experimental.Continuation;
import kotlin.coroutines.experimental.CoroutineContext;
import kotlin.coroutines.experimental.intrinsics.IntrinsicsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000.\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\020\b\n\002\b\004\n\002\020\002\n\000\n\002\020\000\n\002\b\003\b\003\030\000*\006\b\000\020\001 \0002\b\022\004\022\002H\0010\002B#\022\006\020\003\032\0020\004\022\f\020\005\032\b\022\004\022\0028\0000\006\022\006\020\007\032\0020\b¢\006\002\020\tJ\032\020\f\032\0020\r2\b\020\016\032\004\030\0010\0172\006\020\020\032\0020\bH\024J\n\020\021\032\004\030\0010\017H\001R\024\020\003\032\0020\004X\004¢\006\b\n\000\032\004\b\n\020\013R\024\020\005\032\b\022\004\022\0028\0000\006X\004¢\006\002\n\000¨\006\022"}, d2={"Lkotlinx/coroutines/experimental/RunCompletion;", "T", "Lkotlinx/coroutines/experimental/AbstractContinuation;", "context", "Lkotlin/coroutines/experimental/CoroutineContext;", "delegate", "Lkotlin/coroutines/experimental/Continuation;", "resumeMode", "", "(Lkotlin/coroutines/experimental/CoroutineContext;Lkotlin/coroutines/experimental/Continuation;I)V", "getContext", "()Lkotlin/coroutines/experimental/CoroutineContext;", "afterCompletion", "", "state", "", "mode", "getResult", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
final class RunCompletion<T>
  extends AbstractContinuation<T>
{
  @NotNull
  private final CoroutineContext context;
  private final Continuation<T> delegate;
  
  public RunCompletion(@NotNull CoroutineContext paramCoroutineContext, @NotNull Continuation<? super T> paramContinuation, int paramInt)
  {
    super(true, paramInt);
    context = paramCoroutineContext;
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
    ResumeModeKt.resumeMode(delegate, paramObject, paramInt);
  }
  
  @NotNull
  public CoroutineContext getContext()
  {
    return context;
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
    return localObject;
  }
}
