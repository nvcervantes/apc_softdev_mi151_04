package kotlinx.coroutines.experimental;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.coroutines.experimental.Continuation;
import kotlin.coroutines.experimental.CoroutineContext;
import kotlin.coroutines.experimental.CoroutineContext.Element;
import kotlin.coroutines.experimental.CoroutineContext.Key;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\0004\n\002\030\002\n\000\n\002\030\002\n\002\030\002\n\000\n\002\020\013\n\002\b\002\n\002\020\002\n\000\n\002\020\000\n\002\b\007\n\002\020\003\n\000\n\002\030\002\n\002\b\003\bf\030\000*\006\b\000\020\001 \0002\b\022\004\022\002H\0010\0022\0020\003J\020\020\007\032\0020\b2\006\020\t\032\0020\nH&J\b\020\013\032\0020\bH&J#\020\f\032\004\030\0010\n2\006\020\r\032\0028\0002\n\b\002\020\016\032\004\030\0010\nH&¢\006\002\020\017J\022\020\020\032\004\030\0010\n2\006\020\021\032\0020\022H&J\031\020\023\032\0020\b*\0020\0242\006\020\r\032\0028\000H&¢\006\002\020\025J\024\020\026\032\0020\b*\0020\0242\006\020\021\032\0020\022H&R\022\020\004\032\0020\005X¦\004¢\006\006\032\004\b\004\020\006¨\006\027"}, d2={"Lkotlinx/coroutines/experimental/CancellableContinuation;", "T", "Lkotlin/coroutines/experimental/Continuation;", "Lkotlinx/coroutines/experimental/Job;", "isCancelled", "", "()Z", "completeResume", "", "token", "", "initCancellability", "tryResume", "value", "idempotent", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "tryResumeWithException", "exception", "", "resumeUndispatched", "Lkotlinx/coroutines/experimental/CoroutineDispatcher;", "(Lkotlinx/coroutines/experimental/CoroutineDispatcher;Ljava/lang/Object;)V", "resumeUndispatchedWithException", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
public abstract interface CancellableContinuation<T>
  extends Continuation<T>, Job
{
  public abstract void completeResume(@NotNull Object paramObject);
  
  public abstract void initCancellability();
  
  public abstract boolean isCancelled();
  
  public abstract void resumeUndispatched(@NotNull CoroutineDispatcher paramCoroutineDispatcher, T paramT);
  
  public abstract void resumeUndispatchedWithException(@NotNull CoroutineDispatcher paramCoroutineDispatcher, @NotNull Throwable paramThrowable);
  
  @Nullable
  public abstract Object tryResume(T paramT, @Nullable Object paramObject);
  
  @Nullable
  public abstract Object tryResumeWithException(@NotNull Throwable paramThrowable);
  
  @Metadata(bv={1, 0, 2}, k=3, mv={1, 1, 7})
  public static final class DefaultImpls
  {
    public static <T, R> R fold(CancellableContinuation<? super T> paramCancellableContinuation, @NotNull R paramR, Function2<? super R, ? super CoroutineContext.Element, ? extends R> paramFunction2)
    {
      Intrinsics.checkParameterIsNotNull(paramFunction2, "operation");
      return Job.DefaultImpls.fold((Job)paramCancellableContinuation, paramR, paramFunction2);
    }
    
    @Nullable
    public static <T, E extends CoroutineContext.Element> E get(@NotNull CancellableContinuation<? super T> paramCancellableContinuation, CoroutineContext.Key<E> paramKey)
    {
      Intrinsics.checkParameterIsNotNull(paramKey, "key");
      return Job.DefaultImpls.get((Job)paramCancellableContinuation, paramKey);
    }
    
    @NotNull
    public static <T> CoroutineContext minusKey(@NotNull CancellableContinuation<? super T> paramCancellableContinuation, CoroutineContext.Key<?> paramKey)
    {
      Intrinsics.checkParameterIsNotNull(paramKey, "key");
      return Job.DefaultImpls.minusKey((Job)paramCancellableContinuation, paramKey);
    }
    
    @NotNull
    public static <T> CoroutineContext plus(@NotNull CancellableContinuation<? super T> paramCancellableContinuation, CoroutineContext paramCoroutineContext)
    {
      Intrinsics.checkParameterIsNotNull(paramCoroutineContext, "context");
      return Job.DefaultImpls.plus((Job)paramCancellableContinuation, paramCoroutineContext);
    }
    
    @Deprecated(level=DeprecationLevel.ERROR, message="Operator '+' on two Job objects is meaningless. Job is a coroutine context element and `+` is a set-sum operator for coroutine contexts. The job to the right of `+` just replaces the job the left of `+`.")
    @NotNull
    public static <T> Job plus(@NotNull CancellableContinuation<? super T> paramCancellableContinuation, Job paramJob)
    {
      Intrinsics.checkParameterIsNotNull(paramJob, "other");
      return Job.DefaultImpls.plus((Job)paramCancellableContinuation, paramJob);
    }
  }
}
