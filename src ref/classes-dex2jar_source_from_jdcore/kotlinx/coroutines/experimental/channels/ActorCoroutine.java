package kotlinx.coroutines.experimental.channels;

import kotlin.Metadata;
import kotlin.coroutines.experimental.CoroutineContext;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000(\n\002\030\002\n\000\n\002\030\002\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\020\013\n\002\b\002\b\022\030\000*\004\b\000\020\0012\b\022\004\022\002H\0010\0022\b\022\004\022\002H\0010\0032\b\022\004\022\002H\0010\004B#\022\006\020\005\032\0020\006\022\f\020\007\032\b\022\004\022\0028\0000\b\022\006\020\t\032\0020\n¢\006\002\020\013¨\006\f"}, d2={"Lkotlinx/coroutines/experimental/channels/ActorCoroutine;", "E", "Lkotlinx/coroutines/experimental/channels/ChannelCoroutine;", "Lkotlinx/coroutines/experimental/channels/ActorScope;", "Lkotlinx/coroutines/experimental/channels/ActorJob;", "parentContext", "Lkotlin/coroutines/experimental/CoroutineContext;", "channel", "Lkotlinx/coroutines/experimental/channels/Channel;", "active", "", "(Lkotlin/coroutines/experimental/CoroutineContext;Lkotlinx/coroutines/experimental/channels/Channel;Z)V", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
class ActorCoroutine<E>
  extends ChannelCoroutine<E>
  implements ActorScope<E>, ActorJob<E>
{
  public ActorCoroutine(@NotNull CoroutineContext paramCoroutineContext, @NotNull Channel<E> paramChannel, boolean paramBoolean)
  {
    super(paramCoroutineContext, paramChannel, paramBoolean);
  }
}
