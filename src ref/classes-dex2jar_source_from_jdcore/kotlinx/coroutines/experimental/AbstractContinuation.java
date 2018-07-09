package kotlinx.coroutines.experimental;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import kotlin.Metadata;
import kotlin.coroutines.experimental.Continuation;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\0002\n\002\030\002\n\000\n\002\030\002\n\002\030\002\n\000\n\002\020\013\n\000\n\002\020\b\n\002\b\002\n\002\030\002\n\000\n\002\020\002\n\000\n\002\020\003\n\002\b\n\b \030\000*\006\b\000\020\001 \0002\0020\0022\b\022\004\022\002H\0010\003B\025\022\006\020\004\032\0020\005\022\006\020\006\032\0020\007¢\006\002\020\bJ\020\020\013\032\0020\f2\006\020\r\032\0020\016H\024J\025\020\017\032\0020\f2\006\020\020\032\0028\000H\026¢\006\002\020\021J\035\020\022\032\0020\f2\006\020\020\032\0028\0002\006\020\006\032\0020\007H\004¢\006\002\020\023J\020\020\024\032\0020\f2\006\020\r\032\0020\016H\026J\030\020\025\032\0020\f2\006\020\r\032\0020\0162\006\020\006\032\0020\007H\004J\b\020\026\032\0020\005H\004J\b\020\027\032\0020\005H\004R\016\020\t\032\0020\nX\004¢\006\002\n\000R\020\020\006\032\0020\0078\004X\004¢\006\002\n\000¨\006\030"}, d2={"Lkotlinx/coroutines/experimental/AbstractContinuation;", "T", "Lkotlinx/coroutines/experimental/JobSupport;", "Lkotlin/coroutines/experimental/Continuation;", "active", "", "resumeMode", "", "(ZI)V", "_decision", "Lkotlinx/atomicfu/AtomicInt;", "handleException", "", "exception", "", "resume", "value", "(Ljava/lang/Object;)V", "resumeImpl", "(Ljava/lang/Object;I)V", "resumeWithException", "resumeWithExceptionImpl", "tryResume", "trySuspend", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
public abstract class AbstractContinuation<T>
  extends JobSupport
  implements Continuation<T>
{
  private static final AtomicIntegerFieldUpdater _decision$FU = AtomicIntegerFieldUpdater.newUpdater(AbstractContinuation.class, "_decision");
  private volatile int _decision;
  @JvmField
  protected final int resumeMode;
  
  public AbstractContinuation(boolean paramBoolean, int paramInt)
  {
    super(paramBoolean);
    resumeMode = paramInt;
    _decision = 0;
  }
  
  protected void handleException(@NotNull Throwable paramThrowable)
  {
    Intrinsics.checkParameterIsNotNull(paramThrowable, "exception");
    CoroutineExceptionHandlerKt.handleCoroutineException(getContext(), paramThrowable);
  }
  
  public void resume(T paramT)
  {
    resumeImpl(paramT, resumeMode);
  }
  
  protected final void resumeImpl(T paramT, int paramInt)
  {
    do
    {
      localObject = JobSupport.access$getState$p(this);
      if (!(localObject instanceof JobSupport.Incomplete)) {
        break;
      }
    } while (!updateState(localObject, paramT, paramInt));
    return;
    if ((localObject instanceof JobSupport.Cancelled)) {
      return;
    }
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append("Already resumed, but got value ");
    ((StringBuilder)localObject).append(paramT);
    throw ((Throwable)new IllegalStateException(((StringBuilder)localObject).toString().toString()));
  }
  
  public void resumeWithException(@NotNull Throwable paramThrowable)
  {
    Intrinsics.checkParameterIsNotNull(paramThrowable, "exception");
    resumeWithExceptionImpl(paramThrowable, resumeMode);
  }
  
  protected final void resumeWithExceptionImpl(@NotNull Throwable paramThrowable, int paramInt)
  {
    Intrinsics.checkParameterIsNotNull(paramThrowable, "exception");
    do
    {
      localObject = JobSupport.access$getState$p(this);
      if (!(localObject instanceof JobSupport.Incomplete)) {
        break;
      }
    } while (!updateState(localObject, new JobSupport.CompletedExceptionally(paramThrowable), paramInt));
    return;
    if ((localObject instanceof JobSupport.Cancelled))
    {
      if ((Intrinsics.areEqual(paramThrowable, ((JobSupport.Cancelled)localObject).getException()) ^ true)) {
        CoroutineExceptionHandlerKt.handleCoroutineException(getContext(), paramThrowable);
      }
      return;
    }
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append("Already resumed, but got exception ");
    ((StringBuilder)localObject).append(paramThrowable);
    throw ((Throwable)new IllegalStateException(((StringBuilder)localObject).toString(), paramThrowable));
  }
  
  protected final boolean tryResume()
  {
    do
    {
      switch (_decision)
      {
      default: 
        throw ((Throwable)new IllegalStateException("Already resumed".toString()));
      case 1: 
        return false;
      }
    } while (!_decision$FU.compareAndSet(this, 0, 2));
    return true;
  }
  
  protected final boolean trySuspend()
  {
    do
    {
      int i = _decision;
      if (i != 0)
      {
        if (i != 2) {
          throw ((Throwable)new IllegalStateException("Already suspended".toString()));
        }
        return false;
      }
    } while (!_decision$FU.compareAndSet(this, 0, 1));
    return true;
  }
}
