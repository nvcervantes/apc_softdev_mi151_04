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
import kotlinx.coroutines.experimental.selects.SelectInstance;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\0002\n\002\030\002\n\000\n\002\030\002\n\000\n\002\020\013\n\002\b\t\n\002\020\002\n\002\b\002\n\002\030\002\n\000\n\002\030\002\n\002\030\002\n\002\020\000\n\002\b\002\bf\030\000*\006\b\000\020\001 \0012\0020\002J\021\020\t\032\0028\000H¦@ø\001\000¢\006\002\020\nJ\r\020\013\032\0028\000H&¢\006\002\020\fJH\020\r\032\0020\016\"\004\b\001\020\0172\f\020\020\032\b\022\004\022\002H\0170\0212\"\020\022\032\036\b\001\022\004\022\0028\000\022\n\022\b\022\004\022\002H\0170\024\022\006\022\004\030\0010\0250\023H&ø\001\000¢\006\002\020\026R\022\020\003\032\0020\004X¦\004¢\006\006\032\004\b\003\020\005R\032\020\006\032\0020\0048VX\004¢\006\f\022\004\b\007\020\b\032\004\b\006\020\005\002\004\n\002\b\t¨\006\027"}, d2={"Lkotlinx/coroutines/experimental/Deferred;", "T", "Lkotlinx/coroutines/experimental/Job;", "isCompletedExceptionally", "", "()Z", "isComputing", "isComputing$annotations", "()V", "await", "(Lkotlin/coroutines/experimental/Continuation;)Ljava/lang/Object;", "getCompleted", "()Ljava/lang/Object;", "registerSelectAwait", "", "R", "select", "Lkotlinx/coroutines/experimental/selects/SelectInstance;", "block", "Lkotlin/Function2;", "Lkotlin/coroutines/experimental/Continuation;", "", "(Lkotlinx/coroutines/experimental/selects/SelectInstance;Lkotlin/jvm/functions/Function2;)V", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
public abstract interface Deferred<T>
  extends Job
{
  @Nullable
  public abstract Object await(@NotNull Continuation<? super T> paramContinuation);
  
  public abstract T getCompleted();
  
  public abstract boolean isCompletedExceptionally();
  
  public abstract boolean isComputing();
  
  public abstract <R> void registerSelectAwait(@NotNull SelectInstance<? super R> paramSelectInstance, @NotNull Function2<? super T, ? super Continuation<? super R>, ? extends Object> paramFunction2);
  
  @Metadata(bv={1, 0, 2}, k=3, mv={1, 1, 7})
  public static final class DefaultImpls
  {
    public static <T, R> R fold(Deferred<? extends T> paramDeferred, @NotNull R paramR, Function2<? super R, ? super CoroutineContext.Element, ? extends R> paramFunction2)
    {
      Intrinsics.checkParameterIsNotNull(paramFunction2, "operation");
      return Job.DefaultImpls.fold((Job)paramDeferred, paramR, paramFunction2);
    }
    
    @Nullable
    public static <T, E extends CoroutineContext.Element> E get(@NotNull Deferred<? extends T> paramDeferred, CoroutineContext.Key<E> paramKey)
    {
      Intrinsics.checkParameterIsNotNull(paramKey, "key");
      return Job.DefaultImpls.get((Job)paramDeferred, paramKey);
    }
    
    public static <T> boolean isComputing(Deferred<? extends T> paramDeferred)
    {
      return paramDeferred.isActive();
    }
    
    @NotNull
    public static <T> CoroutineContext minusKey(@NotNull Deferred<? extends T> paramDeferred, CoroutineContext.Key<?> paramKey)
    {
      Intrinsics.checkParameterIsNotNull(paramKey, "key");
      return Job.DefaultImpls.minusKey((Job)paramDeferred, paramKey);
    }
    
    @NotNull
    public static <T> CoroutineContext plus(@NotNull Deferred<? extends T> paramDeferred, CoroutineContext paramCoroutineContext)
    {
      Intrinsics.checkParameterIsNotNull(paramCoroutineContext, "context");
      return Job.DefaultImpls.plus((Job)paramDeferred, paramCoroutineContext);
    }
    
    @Deprecated(level=DeprecationLevel.ERROR, message="Operator '+' on two Job objects is meaningless. Job is a coroutine context element and `+` is a set-sum operator for coroutine contexts. The job to the right of `+` just replaces the job the left of `+`.")
    @NotNull
    public static <T> Job plus(@NotNull Deferred<? extends T> paramDeferred, Job paramJob)
    {
      Intrinsics.checkParameterIsNotNull(paramJob, "other");
      return Job.DefaultImpls.plus((Job)paramDeferred, paramJob);
    }
  }
}
