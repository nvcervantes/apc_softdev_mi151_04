package kotlinx.coroutines.experimental.channels;

import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000\024\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\002\b\003\bf\030\000 \006*\004\b\000\020\0012\b\022\004\022\002H\0010\002:\001\006J\016\020\003\032\b\022\004\022\0028\0000\004H\027J\016\020\005\032\b\022\004\022\0028\0000\004H&¨\006\007"}, d2={"Lkotlinx/coroutines/experimental/channels/BroadcastChannel;", "E", "Lkotlinx/coroutines/experimental/channels/SendChannel;", "open", "Lkotlinx/coroutines/experimental/channels/SubscriptionReceiveChannel;", "openSubscription", "Factory", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
public abstract interface BroadcastChannel<E>
  extends SendChannel<E>
{
  public static final Factory Factory = new Factory(null);
  
  @Deprecated(message="Renamed to `openSubscription`", replaceWith=@ReplaceWith(expression="openSubscription()", imports={}))
  @NotNull
  public abstract SubscriptionReceiveChannel<E> open();
  
  @NotNull
  public abstract SubscriptionReceiveChannel<E> openSubscription();
  
  @Metadata(bv={1, 0, 2}, k=3, mv={1, 1, 7})
  public static final class DefaultImpls
  {
    @Deprecated(message="Renamed to `openSubscription`", replaceWith=@ReplaceWith(expression="openSubscription()", imports={}))
    @NotNull
    public static <E> SubscriptionReceiveChannel<E> open(BroadcastChannel<E> paramBroadcastChannel)
    {
      return paramBroadcastChannel.openSubscription();
    }
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\000\032\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\002\b\002\n\002\020\b\n\000\b\003\030\0002\0020\001B\007\b\002¢\006\002\020\002J\035\020\003\032\b\022\004\022\002H\0050\004\"\004\b\001\020\0052\006\020\006\032\0020\007H\002¨\006\b"}, d2={"Lkotlinx/coroutines/experimental/channels/BroadcastChannel$Factory;", "", "()V", "invoke", "Lkotlinx/coroutines/experimental/channels/BroadcastChannel;", "E", "capacity", "", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
  public static final class Factory
  {
    private Factory() {}
  }
}
