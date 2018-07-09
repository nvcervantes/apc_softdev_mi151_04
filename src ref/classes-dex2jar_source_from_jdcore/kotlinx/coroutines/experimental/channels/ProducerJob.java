package kotlinx.coroutines.experimental.channels;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.coroutines.experimental.CoroutineContext;
import kotlin.coroutines.experimental.CoroutineContext.Element;
import kotlin.coroutines.experimental.CoroutineContext.Key;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.experimental.Job;
import kotlinx.coroutines.experimental.Job.DefaultImpls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000\022\n\002\030\002\n\000\n\002\030\002\n\002\030\002\n\002\b\004\bf\030\000*\006\b\000\020\001 \0012\0020\0022\b\022\004\022\002H\0010\003R\030\020\004\032\b\022\004\022\0028\0000\003X¦\004¢\006\006\032\004\b\005\020\006¨\006\007"}, d2={"Lkotlinx/coroutines/experimental/channels/ProducerJob;", "E", "Lkotlinx/coroutines/experimental/Job;", "Lkotlinx/coroutines/experimental/channels/ReceiveChannel;", "channel", "getChannel", "()Lkotlinx/coroutines/experimental/channels/ReceiveChannel;", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
public abstract interface ProducerJob<E>
  extends Job, ReceiveChannel<E>
{
  @NotNull
  public abstract ReceiveChannel<E> getChannel();
  
  @Metadata(bv={1, 0, 2}, k=3, mv={1, 1, 7})
  public static final class DefaultImpls
  {
    public static <E, R> R fold(ProducerJob<? extends E> paramProducerJob, @NotNull R paramR, Function2<? super R, ? super CoroutineContext.Element, ? extends R> paramFunction2)
    {
      Intrinsics.checkParameterIsNotNull(paramFunction2, "operation");
      return Job.DefaultImpls.fold((Job)paramProducerJob, paramR, paramFunction2);
    }
    
    @Nullable
    public static <E_I1, E extends CoroutineContext.Element> E get(@NotNull ProducerJob<? extends E_I1> paramProducerJob, CoroutineContext.Key<E> paramKey)
    {
      Intrinsics.checkParameterIsNotNull(paramKey, "key");
      return Job.DefaultImpls.get((Job)paramProducerJob, paramKey);
    }
    
    @NotNull
    public static <E> CoroutineContext minusKey(@NotNull ProducerJob<? extends E> paramProducerJob, CoroutineContext.Key<?> paramKey)
    {
      Intrinsics.checkParameterIsNotNull(paramKey, "key");
      return Job.DefaultImpls.minusKey((Job)paramProducerJob, paramKey);
    }
    
    @NotNull
    public static <E> CoroutineContext plus(@NotNull ProducerJob<? extends E> paramProducerJob, CoroutineContext paramCoroutineContext)
    {
      Intrinsics.checkParameterIsNotNull(paramCoroutineContext, "context");
      return Job.DefaultImpls.plus((Job)paramProducerJob, paramCoroutineContext);
    }
    
    @Deprecated(level=DeprecationLevel.ERROR, message="Operator '+' on two Job objects is meaningless. Job is a coroutine context element and `+` is a set-sum operator for coroutine contexts. The job to the right of `+` just replaces the job the left of `+`.")
    @NotNull
    public static <E> Job plus(@NotNull ProducerJob<? extends E> paramProducerJob, Job paramJob)
    {
      Intrinsics.checkParameterIsNotNull(paramJob, "other");
      return Job.DefaultImpls.plus((Job)paramProducerJob, paramJob);
    }
  }
}
