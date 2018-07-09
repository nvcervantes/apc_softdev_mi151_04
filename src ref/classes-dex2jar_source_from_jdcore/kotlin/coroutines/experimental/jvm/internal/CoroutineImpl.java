package kotlin.coroutines.experimental.jvm.internal;

import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.coroutines.experimental.Continuation;
import kotlin.coroutines.experimental.CoroutineContext;
import kotlin.coroutines.experimental.intrinsics.IntrinsicsKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\0002\n\002\030\002\n\002\030\002\n\002\030\002\n\002\020\000\n\000\n\002\020\b\n\002\b\003\n\002\030\002\n\002\b\t\n\002\020\002\n\002\b\004\n\002\020\003\n\002\b\003\b&\030\0002\0020\0012\n\022\006\022\004\030\0010\0030\002B\037\022\006\020\004\032\0020\005\022\020\020\006\032\f\022\006\022\004\030\0010\003\030\0010\002¢\006\002\020\007J$\020\022\032\b\022\004\022\0020\0230\0022\b\020\024\032\004\030\0010\0032\n\020\006\032\006\022\002\b\0030\002H\026J\032\020\022\032\b\022\004\022\0020\0230\0022\n\020\006\032\006\022\002\b\0030\002H\026J\036\020\025\032\004\030\0010\0032\b\020\026\032\004\030\0010\0032\b\020\027\032\004\030\0010\030H$J\022\020\031\032\0020\0232\b\020\024\032\004\030\0010\003H\026J\020\020\032\032\0020\0232\006\020\027\032\0020\030H\026R\020\020\b\032\004\030\0010\tX\004¢\006\002\n\000R\030\020\n\032\f\022\006\022\004\030\0010\003\030\0010\002X\016¢\006\002\n\000R\034\020\006\032\f\022\006\022\004\030\0010\003\030\0010\0028\004@\004X\016¢\006\002\n\000R\024\020\013\032\0020\t8VX\004¢\006\006\032\004\b\f\020\rR\031\020\016\032\n\022\006\022\004\030\0010\0030\0028F¢\006\006\032\004\b\017\020\020R\022\020\021\032\0020\0058\004@\004X\016¢\006\002\n\000¨\006\033"}, d2={"Lkotlin/coroutines/experimental/jvm/internal/CoroutineImpl;", "Lkotlin/jvm/internal/Lambda;", "Lkotlin/coroutines/experimental/Continuation;", "", "arity", "", "completion", "(ILkotlin/coroutines/experimental/Continuation;)V", "_context", "Lkotlin/coroutines/experimental/CoroutineContext;", "_facade", "context", "getContext", "()Lkotlin/coroutines/experimental/CoroutineContext;", "facade", "getFacade", "()Lkotlin/coroutines/experimental/Continuation;", "label", "create", "", "value", "doResume", "data", "exception", "", "resume", "resumeWithException", "kotlin-stdlib"}, k=1, mv={1, 1, 9})
public abstract class CoroutineImpl
  extends Lambda
  implements Continuation<Object>
{
  private final CoroutineContext _context;
  private Continuation<Object> _facade;
  @JvmField
  @Nullable
  protected Continuation<Object> completion;
  @JvmField
  protected int label;
  
  public CoroutineImpl(int paramInt, @Nullable Continuation<Object> paramContinuation)
  {
    super(paramInt);
    completion = paramContinuation;
    if (completion != null) {
      paramInt = 0;
    } else {
      paramInt = -1;
    }
    label = paramInt;
    paramContinuation = completion;
    if (paramContinuation != null) {
      paramContinuation = paramContinuation.getContext();
    } else {
      paramContinuation = null;
    }
    _context = paramContinuation;
  }
  
  @NotNull
  public Continuation<Unit> create(@Nullable Object paramObject, @NotNull Continuation<?> paramContinuation)
  {
    Intrinsics.checkParameterIsNotNull(paramContinuation, "completion");
    throw ((Throwable)new IllegalStateException("create(Any?;Continuation) has not been overridden"));
  }
  
  @NotNull
  public Continuation<Unit> create(@NotNull Continuation<?> paramContinuation)
  {
    Intrinsics.checkParameterIsNotNull(paramContinuation, "completion");
    throw ((Throwable)new IllegalStateException("create(Continuation) has not been overridden"));
  }
  
  @Nullable
  protected abstract Object doResume(@Nullable Object paramObject, @Nullable Throwable paramThrowable);
  
  @NotNull
  public CoroutineContext getContext()
  {
    CoroutineContext localCoroutineContext = _context;
    if (localCoroutineContext == null) {
      Intrinsics.throwNpe();
    }
    return localCoroutineContext;
  }
  
  @NotNull
  public final Continuation<Object> getFacade()
  {
    if (_facade == null)
    {
      localObject = _context;
      if (localObject == null) {
        Intrinsics.throwNpe();
      }
      _facade = CoroutineIntrinsics.interceptContinuationIfNeeded((CoroutineContext)localObject, (Continuation)this);
    }
    Object localObject = _facade;
    if (localObject == null) {
      Intrinsics.throwNpe();
    }
    return localObject;
  }
  
  public void resume(@Nullable Object paramObject)
  {
    Continuation localContinuation = completion;
    if (localContinuation == null) {
      Intrinsics.throwNpe();
    }
    try
    {
      paramObject = doResume(paramObject, null);
      if (paramObject != IntrinsicsKt.getCOROUTINE_SUSPENDED())
      {
        if (localContinuation == null) {
          throw new TypeCastException("null cannot be cast to non-null type kotlin.coroutines.experimental.Continuation<kotlin.Any?>");
        }
        localContinuation.resume(paramObject);
        return;
      }
    }
    catch (Throwable paramObject)
    {
      localContinuation.resumeWithException(paramObject);
    }
  }
  
  public void resumeWithException(@NotNull Throwable paramThrowable)
  {
    Intrinsics.checkParameterIsNotNull(paramThrowable, "exception");
    Continuation localContinuation = completion;
    if (localContinuation == null) {
      Intrinsics.throwNpe();
    }
    try
    {
      paramThrowable = doResume(null, paramThrowable);
      if (paramThrowable != IntrinsicsKt.getCOROUTINE_SUSPENDED())
      {
        if (localContinuation == null) {
          throw new TypeCastException("null cannot be cast to non-null type kotlin.coroutines.experimental.Continuation<kotlin.Any?>");
        }
        localContinuation.resume(paramThrowable);
        return;
      }
    }
    catch (Throwable paramThrowable)
    {
      localContinuation.resumeWithException(paramThrowable);
    }
  }
}
