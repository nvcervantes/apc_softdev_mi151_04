package kotlinx.coroutines.experimental.channels;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000\026\n\002\030\002\n\000\n\002\020\000\n\002\b\004\n\002\020\002\n\002\b\006\bf\030\000*\006\b\000\020\001 \0002\0020\002J\020\020\006\032\0020\0072\006\020\b\032\0020\002H&J!\020\t\032\004\030\0010\0022\006\020\n\032\0028\0002\b\020\013\032\004\030\0010\002H&¢\006\002\020\fR\022\020\003\032\0020\002X¦\004¢\006\006\032\004\b\004\020\005¨\006\r"}, d2={"Lkotlinx/coroutines/experimental/channels/ReceiveOrClosed;", "E", "", "offerResult", "getOfferResult", "()Ljava/lang/Object;", "completeResumeReceive", "", "token", "tryResumeReceive", "value", "idempotent", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
public abstract interface ReceiveOrClosed<E>
{
  public abstract void completeResumeReceive(@NotNull Object paramObject);
  
  @NotNull
  public abstract Object getOfferResult();
  
  @Nullable
  public abstract Object tryResumeReceive(E paramE, @Nullable Object paramObject);
}
