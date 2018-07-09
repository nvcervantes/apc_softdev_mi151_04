package kotlinx.coroutines.experimental;

import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000\036\n\002\030\002\n\002\030\002\n\002\030\002\n\000\n\002\020\016\n\002\b\002\n\002\030\002\n\002\b\002\030\0002\0060\001j\002`\002B\017\b\026\022\006\020\003\032\0020\004¢\006\002\020\005B\031\b\000\022\006\020\003\032\0020\004\022\b\020\006\032\004\030\0010\007¢\006\002\020\bR\022\020\006\032\004\030\0010\0078\000X\004¢\006\002\n\000¨\006\t"}, d2={"Lkotlinx/coroutines/experimental/TimeoutException;", "Ljava/util/concurrent/CancellationException;", "Lkotlinx/coroutines/experimental/CancellationException;", "message", "", "(Ljava/lang/String;)V", "coroutine", "Lkotlinx/coroutines/experimental/Job;", "(Ljava/lang/String;Lkotlinx/coroutines/experimental/Job;)V", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
public final class TimeoutException
  extends CancellationException
{
  @JvmField
  @Nullable
  public final Job coroutine;
  
  public TimeoutException(@NotNull String paramString)
  {
    this(paramString, null);
  }
  
  public TimeoutException(@NotNull String paramString, @Nullable Job paramJob)
  {
    super(paramString);
    coroutine = paramJob;
  }
}
