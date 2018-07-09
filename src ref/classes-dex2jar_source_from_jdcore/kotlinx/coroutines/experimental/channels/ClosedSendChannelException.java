package kotlinx.coroutines.experimental.channels;

import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000\026\n\002\030\002\n\002\030\002\n\002\030\002\n\000\n\002\020\016\n\002\b\002\030\0002\0060\001j\002`\002B\017\022\b\020\003\032\004\030\0010\004¢\006\002\020\005¨\006\006"}, d2={"Lkotlinx/coroutines/experimental/channels/ClosedSendChannelException;", "Ljava/util/concurrent/CancellationException;", "Lkotlinx/coroutines/experimental/CancellationException;", "message", "", "(Ljava/lang/String;)V", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
public final class ClosedSendChannelException
  extends CancellationException
{
  public ClosedSendChannelException(@Nullable String paramString)
  {
    super(paramString);
  }
}
