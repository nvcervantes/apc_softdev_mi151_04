package kotlinx.coroutines.experimental;

import kotlin.Metadata;
import kotlin.coroutines.experimental.Continuation;
import kotlin.coroutines.experimental.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\0000\n\002\030\002\n\000\n\002\030\002\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\000\n\002\020\013\n\002\b\f\n\002\020\002\n\000\n\002\020\003\n\002\b\005\b&\030\000*\006\b\000\020\001 \0002\0020\0022\b\022\004\022\002H\0010\0032\0020\004B\025\022\006\020\005\032\0020\006\022\006\020\007\032\0020\b¢\006\002\020\tJ\020\020\024\032\0020\0252\006\020\026\032\0020\027H\004J\023\020\030\032\0020\0252\006\020\031\032\0028\000¢\006\002\020\032J\016\020\033\032\0020\0252\006\020\026\032\0020\027R\034\020\n\032\0020\0068\006X\004¢\006\016\n\000\022\004\b\013\020\f\032\004\b\r\020\016R\021\020\017\032\0020\0068F¢\006\006\032\004\b\020\020\016R\024\020\021\032\0020\b8DX\004¢\006\006\032\004\b\022\020\023R\016\020\005\032\0020\006X\004¢\006\002\n\000¨\006\034"}, d2={"Lkotlinx/coroutines/experimental/AbstractCoroutine;", "T", "Lkotlinx/coroutines/experimental/JobSupport;", "Lkotlin/coroutines/experimental/Continuation;", "Lkotlinx/coroutines/experimental/CoroutineScope;", "parentContext", "Lkotlin/coroutines/experimental/CoroutineContext;", "active", "", "(Lkotlin/coroutines/experimental/CoroutineContext;Z)V", "context", "context$annotations", "()V", "getContext", "()Lkotlin/coroutines/experimental/CoroutineContext;", "coroutineContext", "getCoroutineContext", "hasCancellingState", "getHasCancellingState", "()Z", "handleException", "", "exception", "", "resume", "value", "(Ljava/lang/Object;)V", "resumeWithException", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
public abstract class AbstractCoroutine<T>
  extends JobSupport
  implements Continuation<T>, CoroutineScope
{
  @NotNull
  private final CoroutineContext context;
  private final CoroutineContext parentContext;
  
  public AbstractCoroutine(@NotNull CoroutineContext paramCoroutineContext, boolean paramBoolean)
  {
    super(paramBoolean);
    parentContext = paramCoroutineContext;
    context = parentContext.plus((CoroutineContext)this);
  }
  
  @NotNull
  public final CoroutineContext getContext()
  {
    return context;
  }
  
  @NotNull
  public final CoroutineContext getCoroutineContext()
  {
    return context;
  }
  
  protected final boolean getHasCancellingState()
  {
    return true;
  }
  
  protected final void handleException(@NotNull Throwable paramThrowable)
  {
    Intrinsics.checkParameterIsNotNull(paramThrowable, "exception");
    CoroutineExceptionHandlerKt.handleCoroutineException(parentContext, paramThrowable);
  }
  
  public final void resume(T paramT)
  {
    do
    {
      localObject = JobSupport.access$getState$p(this);
      if (!(localObject instanceof JobSupport.Incomplete)) {
        break;
      }
    } while (!updateState(localObject, paramT, 0));
    return;
    if ((localObject instanceof JobSupport.Cancelled)) {
      return;
    }
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append("Already resumed, but got value ");
    ((StringBuilder)localObject).append(paramT);
    throw ((Throwable)new IllegalStateException(((StringBuilder)localObject).toString().toString()));
  }
  
  public final void resumeWithException(@NotNull Throwable paramThrowable)
  {
    Intrinsics.checkParameterIsNotNull(paramThrowable, "exception");
    do
    {
      localObject = JobSupport.access$getState$p(this);
      if (!(localObject instanceof JobSupport.Incomplete)) {
        break;
      }
    } while (!updateState(localObject, new JobSupport.CompletedExceptionally(paramThrowable), 0));
    return;
    if ((localObject instanceof JobSupport.Cancelled))
    {
      if (paramThrowable != ((JobSupport.Cancelled)localObject).getException()) {
        CoroutineExceptionHandlerKt.handleCoroutineException(context, paramThrowable);
      }
      return;
    }
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append("Already resumed, but got exception ");
    ((StringBuilder)localObject).append(paramThrowable);
    throw ((Throwable)new IllegalStateException(((StringBuilder)localObject).toString(), paramThrowable));
  }
}
