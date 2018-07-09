package kotlinx.coroutines.experimental.channels;

import kotlin.Metadata;

@Metadata(bv={1, 0, 2}, d1={"\000\022\n\002\030\002\n\000\n\002\030\002\n\002\030\002\n\002\b\002\bf\030\000 \004*\004\b\000\020\0012\b\022\004\022\002H\0010\0022\b\022\004\022\002H\0010\003:\001\004¨\006\005"}, d2={"Lkotlinx/coroutines/experimental/channels/Channel;", "E", "Lkotlinx/coroutines/experimental/channels/SendChannel;", "Lkotlinx/coroutines/experimental/channels/ReceiveChannel;", "Factory", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
public abstract interface Channel<E>
  extends SendChannel<E>, ReceiveChannel<E>
{
  public static final int CONFLATED = -1;
  public static final Factory Factory = new Factory(null);
  public static final int UNLIMITED = Integer.MAX_VALUE;
  
  @Metadata(bv={1, 0, 2}, d1={"\000\034\n\002\030\002\n\002\020\000\n\002\b\002\n\002\020\b\n\002\b\002\n\002\030\002\n\002\b\003\b\003\030\0002\0020\001B\007\b\002¢\006\002\020\002J\037\020\006\032\b\022\004\022\002H\b0\007\"\004\b\001\020\b2\b\b\002\020\t\032\0020\004H\002R\016\020\003\032\0020\004XT¢\006\002\n\000R\016\020\005\032\0020\004XT¢\006\002\n\000¨\006\n"}, d2={"Lkotlinx/coroutines/experimental/channels/Channel$Factory;", "", "()V", "CONFLATED", "", "UNLIMITED", "invoke", "Lkotlinx/coroutines/experimental/channels/Channel;", "E", "capacity", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
  public static final class Factory
  {
    public static final int CONFLATED = -1;
    public static final int UNLIMITED = Integer.MAX_VALUE;
    
    private Factory() {}
  }
}
