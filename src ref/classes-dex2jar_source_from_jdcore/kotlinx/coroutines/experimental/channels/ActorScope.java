package kotlinx.coroutines.experimental.channels;

import kotlin.Metadata;
import kotlin.coroutines.experimental.CoroutineContext;
import kotlinx.coroutines.experimental.CoroutineScope;
import kotlinx.coroutines.experimental.CoroutineScope.DefaultImpls;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000\030\n\002\030\002\n\000\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\002\b\003\bf\030\000*\004\b\000\020\0012\0020\0022\b\022\004\022\002H\0010\003R\030\020\004\032\b\022\004\022\0028\0000\005X¦\004¢\006\006\032\004\b\006\020\007¨\006\b"}, d2={"Lkotlinx/coroutines/experimental/channels/ActorScope;", "E", "Lkotlinx/coroutines/experimental/CoroutineScope;", "Lkotlinx/coroutines/experimental/channels/ReceiveChannel;", "channel", "Lkotlinx/coroutines/experimental/channels/Channel;", "getChannel", "()Lkotlinx/coroutines/experimental/channels/Channel;", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
public abstract interface ActorScope<E>
  extends CoroutineScope, ReceiveChannel<E>
{
  @NotNull
  public abstract Channel<E> getChannel();
  
  @Metadata(bv={1, 0, 2}, k=3, mv={1, 1, 7})
  public static final class DefaultImpls
  {
    @NotNull
    public static <E> CoroutineContext getCoroutineContext(ActorScope<E> paramActorScope)
    {
      return CoroutineScope.DefaultImpls.getCoroutineContext((CoroutineScope)paramActorScope);
    }
  }
}
