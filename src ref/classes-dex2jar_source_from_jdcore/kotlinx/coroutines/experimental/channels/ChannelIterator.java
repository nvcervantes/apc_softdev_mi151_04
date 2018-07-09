package kotlinx.coroutines.experimental.channels;

import kotlin.Metadata;
import kotlin.coroutines.experimental.Continuation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000\024\n\002\030\002\n\000\n\002\020\000\n\000\n\002\020\013\n\002\b\003\bf\030\000*\006\b\000\020\001 \0012\0020\002J\021\020\003\032\0020\004H¦Bø\001\000¢\006\002\020\005J\021\020\006\032\0028\000H¦Bø\001\000¢\006\002\020\005\002\004\n\002\b\t¨\006\007"}, d2={"Lkotlinx/coroutines/experimental/channels/ChannelIterator;", "E", "", "hasNext", "", "(Lkotlin/coroutines/experimental/Continuation;)Ljava/lang/Object;", "next", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
public abstract interface ChannelIterator<E>
{
  @Nullable
  public abstract Object hasNext(@NotNull Continuation<? super Boolean> paramContinuation);
  
  @Nullable
  public abstract Object next(@NotNull Continuation<? super E> paramContinuation);
}
