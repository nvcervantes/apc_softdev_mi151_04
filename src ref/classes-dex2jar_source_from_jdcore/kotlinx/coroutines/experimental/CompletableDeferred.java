package kotlinx.coroutines.experimental;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.coroutines.experimental.CoroutineContext;
import kotlin.coroutines.experimental.CoroutineContext.Element;
import kotlin.coroutines.experimental.CoroutineContext.Key;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000\032\n\002\030\002\n\000\n\002\030\002\n\000\n\002\020\013\n\002\b\004\n\002\020\003\n\000\bf\030\000*\004\b\000\020\0012\b\022\004\022\002H\0010\002J\025\020\003\032\0020\0042\006\020\005\032\0028\000H&¢\006\002\020\006J\020\020\007\032\0020\0042\006\020\b\032\0020\tH&¨\006\n"}, d2={"Lkotlinx/coroutines/experimental/CompletableDeferred;", "T", "Lkotlinx/coroutines/experimental/Deferred;", "complete", "", "value", "(Ljava/lang/Object;)Z", "completeExceptionally", "exception", "", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
public abstract interface CompletableDeferred<T>
  extends Deferred<T>
{
  public abstract boolean complete(T paramT);
  
  public abstract boolean completeExceptionally(@NotNull Throwable paramThrowable);
  
  @Metadata(bv={1, 0, 2}, k=3, mv={1, 1, 7})
  public static final class DefaultImpls
  {
    public static <T, R> R fold(CompletableDeferred<T> paramCompletableDeferred, @NotNull R paramR, Function2<? super R, ? super CoroutineContext.Element, ? extends R> paramFunction2)
    {
      Intrinsics.checkParameterIsNotNull(paramFunction2, "operation");
      return Deferred.DefaultImpls.fold((Deferred)paramCompletableDeferred, paramR, paramFunction2);
    }
    
    @Nullable
    public static <T, E extends CoroutineContext.Element> E get(@NotNull CompletableDeferred<T> paramCompletableDeferred, CoroutineContext.Key<E> paramKey)
    {
      Intrinsics.checkParameterIsNotNull(paramKey, "key");
      return Deferred.DefaultImpls.get((Deferred)paramCompletableDeferred, paramKey);
    }
    
    public static <T> boolean isComputing(CompletableDeferred<T> paramCompletableDeferred)
    {
      return Deferred.DefaultImpls.isComputing((Deferred)paramCompletableDeferred);
    }
    
    @NotNull
    public static <T> CoroutineContext minusKey(@NotNull CompletableDeferred<T> paramCompletableDeferred, CoroutineContext.Key<?> paramKey)
    {
      Intrinsics.checkParameterIsNotNull(paramKey, "key");
      return Deferred.DefaultImpls.minusKey((Deferred)paramCompletableDeferred, paramKey);
    }
    
    @NotNull
    public static <T> CoroutineContext plus(@NotNull CompletableDeferred<T> paramCompletableDeferred, CoroutineContext paramCoroutineContext)
    {
      Intrinsics.checkParameterIsNotNull(paramCoroutineContext, "context");
      return Deferred.DefaultImpls.plus((Deferred)paramCompletableDeferred, paramCoroutineContext);
    }
    
    @Deprecated(level=DeprecationLevel.ERROR, message="Operator '+' on two Job objects is meaningless. Job is a coroutine context element and `+` is a set-sum operator for coroutine contexts. The job to the right of `+` just replaces the job the left of `+`.")
    @NotNull
    public static <T> Job plus(@NotNull CompletableDeferred<T> paramCompletableDeferred, Job paramJob)
    {
      Intrinsics.checkParameterIsNotNull(paramJob, "other");
      return Deferred.DefaultImpls.plus((Deferred)paramCompletableDeferred, paramJob);
    }
  }
}
