package kotlinx.coroutines.experimental.channels;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000\024\n\002\030\002\n\002\020\000\n\002\b\004\n\002\020\002\n\002\b\004\bf\030\0002\0020\001J\020\020\005\032\0020\0062\006\020\007\032\0020\001H&J\024\020\b\032\004\030\0010\0012\b\020\t\032\004\030\0010\001H&R\024\020\002\032\004\030\0010\001X¦\004¢\006\006\032\004\b\003\020\004¨\006\n"}, d2={"Lkotlinx/coroutines/experimental/channels/Send;", "", "pollResult", "getPollResult", "()Ljava/lang/Object;", "completeResumeSend", "", "token", "tryResumeSend", "idempotent", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
public abstract interface Send
{
  public abstract void completeResumeSend(@NotNull Object paramObject);
  
  @Nullable
  public abstract Object getPollResult();
  
  @Nullable
  public abstract Object tryResumeSend(@Nullable Object paramObject);
}
