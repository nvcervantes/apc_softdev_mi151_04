package kotlinx.coroutines.experimental.channels;

import kotlin.Metadata;
import kotlin.coroutines.experimental.CoroutineContext;
import kotlinx.coroutines.experimental.CoroutineScope;
import kotlinx.coroutines.experimental.CoroutineScope.DefaultImpls;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000\022\n\002\030\002\n\000\n\002\030\002\n\002\030\002\n\002\b\004\bf\030\000*\006\b\000\020\001 \0002\0020\0022\b\022\004\022\002H\0010\003R\030\020\004\032\b\022\004\022\0028\0000\003X¦\004¢\006\006\032\004\b\005\020\006¨\006\007"}, d2={"Lkotlinx/coroutines/experimental/channels/ProducerScope;", "E", "Lkotlinx/coroutines/experimental/CoroutineScope;", "Lkotlinx/coroutines/experimental/channels/SendChannel;", "channel", "getChannel", "()Lkotlinx/coroutines/experimental/channels/SendChannel;", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
public abstract interface ProducerScope<E>
  extends CoroutineScope, SendChannel<E>
{
  @NotNull
  public abstract SendChannel<E> getChannel();
  
  @Metadata(bv={1, 0, 2}, k=3, mv={1, 1, 7})
  public static final class DefaultImpls
  {
    @NotNull
    public static <E> CoroutineContext getCoroutineContext(ProducerScope<? super E> paramProducerScope)
    {
      return CoroutineScope.DefaultImpls.getCoroutineContext((CoroutineScope)paramProducerScope);
    }
  }
}
