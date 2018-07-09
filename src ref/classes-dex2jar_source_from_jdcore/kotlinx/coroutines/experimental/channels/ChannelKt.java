package kotlinx.coroutines.experimental.channels;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000\020\n\000\n\002\030\002\n\002\b\002\n\002\020\b\n\000\032\022\020\000\032\b\022\004\022\002H\0020\001\"\004\b\000\020\002\032\032\020\000\032\b\022\004\022\002H\0020\001\"\004\b\000\020\0022\006\020\003\032\0020\004¨\006\005"}, d2={"Channel", "Lkotlinx/coroutines/experimental/channels/Channel;", "E", "capacity", "", "kotlinx-coroutines-core"}, k=2, mv={1, 1, 7})
public final class ChannelKt
{
  @NotNull
  public static final <E> Channel<E> Channel()
  {
    return (Channel)new RendezvousChannel();
  }
  
  @NotNull
  public static final <E> Channel<E> Channel(int paramInt)
  {
    if (paramInt != Integer.MAX_VALUE)
    {
      switch (paramInt)
      {
      default: 
        return (Channel)new ArrayChannel(paramInt);
      case 0: 
        return (Channel)new RendezvousChannel();
      }
      return (Channel)new ConflatedChannel();
    }
    return (Channel)new LinkedListChannel();
  }
}
