package kotlinx.coroutines.experimental.channels;

import java.io.Closeable;
import kotlin.Metadata;

@Metadata(bv={1, 0, 2}, d1={"\000\026\n\002\030\002\n\000\n\002\030\002\n\002\030\002\n\000\n\002\020\002\n\000\bf\030\000*\006\b\000\020\001 \0012\b\022\004\022\002H\0010\0022\0020\003J\b\020\004\032\0020\005H&¨\006\006"}, d2={"Lkotlinx/coroutines/experimental/channels/SubscriptionReceiveChannel;", "T", "Lkotlinx/coroutines/experimental/channels/ReceiveChannel;", "Ljava/io/Closeable;", "close", "", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
public abstract interface SubscriptionReceiveChannel<T>
  extends ReceiveChannel<T>, Closeable
{
  public abstract void close();
}
