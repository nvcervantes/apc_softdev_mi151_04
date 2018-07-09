package kotlin.coroutines.experimental;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.coroutines.experimental.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000.\n\002\030\002\n\000\n\002\030\002\n\002\b\003\n\002\020\000\n\002\b\002\n\002\030\002\n\002\b\005\n\002\020\002\n\002\b\004\n\002\020\003\n\002\b\003\b\001\030\000 \025*\006\b\000\020\001 \0002\b\022\004\022\002H\0010\002:\002\025\026B\025\b\021\022\f\020\003\032\b\022\004\022\0028\0000\002¢\006\002\020\004B\037\b\000\022\f\020\003\032\b\022\004\022\0028\0000\002\022\b\020\005\032\004\030\0010\006¢\006\002\020\007J\n\020\r\032\004\030\0010\006H\001J\025\020\016\032\0020\0172\006\020\020\032\0028\000H\026¢\006\002\020\021J\020\020\022\032\0020\0172\006\020\023\032\0020\024H\026R\024\020\b\032\0020\t8VX\004¢\006\006\032\004\b\n\020\013R\024\020\003\032\b\022\004\022\0028\0000\002X\004¢\006\002\n\000R\024\020\f\032\004\030\0010\0068\002@\002X\016¢\006\002\n\000¨\006\027"}, d2={"Lkotlin/coroutines/experimental/SafeContinuation;", "T", "Lkotlin/coroutines/experimental/Continuation;", "delegate", "(Lkotlin/coroutines/experimental/Continuation;)V", "initialResult", "", "(Lkotlin/coroutines/experimental/Continuation;Ljava/lang/Object;)V", "context", "Lkotlin/coroutines/experimental/CoroutineContext;", "getContext", "()Lkotlin/coroutines/experimental/CoroutineContext;", "result", "getResult", "resume", "", "value", "(Ljava/lang/Object;)V", "resumeWithException", "exception", "", "Companion", "Fail", "kotlin-stdlib"}, k=1, mv={1, 1, 9})
@PublishedApi
public final class SafeContinuation<T>
  implements Continuation<T>
{
  public static final Companion Companion = new Companion(null);
  private static final AtomicReferenceFieldUpdater<SafeContinuation<?>, Object> RESULT = AtomicReferenceFieldUpdater.newUpdater(SafeContinuation.class, Object.class, "result");
  private static final Object RESUMED;
  private static final Object UNDECIDED = new Object();
  private final Continuation<T> delegate;
  private volatile Object result;
  
  static
  {
    RESUMED = new Object();
  }
  
  @PublishedApi
  public SafeContinuation(@NotNull Continuation<? super T> paramContinuation)
  {
    this(paramContinuation, Companion.access$getUNDECIDED$p(Companion));
  }
  
  public SafeContinuation(@NotNull Continuation<? super T> paramContinuation, @Nullable Object paramObject)
  {
    delegate = paramContinuation;
    result = paramObject;
  }
  
  private static final AtomicReferenceFieldUpdater<SafeContinuation<?>, Object> getRESULT()
  {
    return Companion.access$getRESULT$p(Companion);
  }
  
  @NotNull
  public CoroutineContext getContext()
  {
    return delegate.getContext();
  }
  
  @PublishedApi
  @Nullable
  public final Object getResult()
  {
    Object localObject2 = result;
    Object localObject1 = localObject2;
    if (localObject2 == Companion.access$getUNDECIDED$p(Companion))
    {
      if (Companion.access$getRESULT$p(Companion).compareAndSet(this, Companion.access$getUNDECIDED$p(Companion), IntrinsicsKt.getCOROUTINE_SUSPENDED())) {
        return IntrinsicsKt.getCOROUTINE_SUSPENDED();
      }
      localObject1 = result;
    }
    if (localObject1 == Companion.access$getRESUMED$p(Companion)) {
      return IntrinsicsKt.getCOROUTINE_SUSPENDED();
    }
    if ((localObject1 instanceof Fail)) {
      throw ((Fail)localObject1).getException();
    }
    return localObject1;
  }
  
  public void resume(T paramT)
  {
    do
    {
      Object localObject;
      do
      {
        localObject = result;
        if (localObject != Companion.access$getUNDECIDED$p(Companion)) {
          break;
        }
      } while (!Companion.access$getRESULT$p(Companion).compareAndSet(this, Companion.access$getUNDECIDED$p(Companion), paramT));
      return;
      if (localObject != IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
        break;
      }
    } while (!Companion.access$getRESULT$p(Companion).compareAndSet(this, IntrinsicsKt.getCOROUTINE_SUSPENDED(), Companion.access$getRESUMED$p(Companion)));
    delegate.resume(paramT);
    return;
    throw ((Throwable)new IllegalStateException("Already resumed"));
  }
  
  public void resumeWithException(@NotNull Throwable paramThrowable)
  {
    Intrinsics.checkParameterIsNotNull(paramThrowable, "exception");
    do
    {
      Object localObject;
      do
      {
        localObject = result;
        if (localObject != Companion.access$getUNDECIDED$p(Companion)) {
          break;
        }
      } while (!Companion.access$getRESULT$p(Companion).compareAndSet(this, Companion.access$getUNDECIDED$p(Companion), new Fail(paramThrowable)));
      return;
      if (localObject != IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
        break;
      }
    } while (!Companion.access$getRESULT$p(Companion).compareAndSet(this, IntrinsicsKt.getCOROUTINE_SUSPENDED(), Companion.access$getRESUMED$p(Companion)));
    delegate.resumeWithException(paramThrowable);
    return;
    throw ((Throwable)new IllegalStateException("Already resumed"));
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\000\030\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\002\030\002\n\002\b\n\b\003\030\0002\0020\001B\007\b\002¢\006\002\020\002R`\020\003\032F\022\024\022\022\022\002\b\003 \006*\b\022\002\b\003\030\0010\0050\005\022\006\022\004\030\0010\001 \006*\"\022\024\022\022\022\002\b\003 \006*\b\022\002\b\003\030\0010\0050\005\022\006\022\004\030\0010\001\030\0010\0040\0048\002X\004¢\006\016\n\000\022\004\b\007\020\002\032\004\b\b\020\tR\026\020\n\032\004\030\0010\001X\004¢\006\b\n\000\032\004\b\013\020\fR\026\020\r\032\004\030\0010\001X\004¢\006\b\n\000\032\004\b\016\020\f¨\006\017"}, d2={"Lkotlin/coroutines/experimental/SafeContinuation$Companion;", "", "()V", "RESULT", "Ljava/util/concurrent/atomic/AtomicReferenceFieldUpdater;", "Lkotlin/coroutines/experimental/SafeContinuation;", "kotlin.jvm.PlatformType", "RESULT$annotations", "getRESULT", "()Ljava/util/concurrent/atomic/AtomicReferenceFieldUpdater;", "RESUMED", "getRESUMED", "()Ljava/lang/Object;", "UNDECIDED", "getUNDECIDED", "kotlin-stdlib"}, k=1, mv={1, 1, 9})
  public static final class Companion
  {
    private Companion() {}
    
    private final AtomicReferenceFieldUpdater<SafeContinuation<?>, Object> getRESULT()
    {
      return SafeContinuation.access$getRESULT$cp();
    }
    
    private final Object getRESUMED()
    {
      return SafeContinuation.access$getRESUMED$cp();
    }
    
    private final Object getUNDECIDED()
    {
      return SafeContinuation.access$getUNDECIDED$cp();
    }
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\000\022\n\002\030\002\n\002\020\000\n\000\n\002\020\003\n\002\b\004\b\002\030\0002\0020\001B\r\022\006\020\002\032\0020\003¢\006\002\020\004R\021\020\002\032\0020\003¢\006\b\n\000\032\004\b\005\020\006¨\006\007"}, d2={"Lkotlin/coroutines/experimental/SafeContinuation$Fail;", "", "exception", "", "(Ljava/lang/Throwable;)V", "getException", "()Ljava/lang/Throwable;", "kotlin-stdlib"}, k=1, mv={1, 1, 9})
  private static final class Fail
  {
    @NotNull
    private final Throwable exception;
    
    public Fail(@NotNull Throwable paramThrowable)
    {
      exception = paramThrowable;
    }
    
    @NotNull
    public final Throwable getException()
    {
      return exception;
    }
  }
}
