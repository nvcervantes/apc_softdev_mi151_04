package kotlinx.coroutines.experimental;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000\n\n\000\n\002\030\002\n\002\b\005\"\032\020\000\032\0020\001X\016¢\006\016\n\000\032\004\b\002\020\003\"\004\b\004\020\005¨\006\006"}, d2={"timeSource", "Lkotlinx/coroutines/experimental/TimeSource;", "getTimeSource", "()Lkotlinx/coroutines/experimental/TimeSource;", "setTimeSource", "(Lkotlinx/coroutines/experimental/TimeSource;)V", "kotlinx-coroutines-core"}, k=2, mv={1, 1, 7})
public final class TimeSourceKt
{
  @NotNull
  private static TimeSource timeSource = (TimeSource)DefaultTimeSource.INSTANCE;
  
  @NotNull
  public static final TimeSource getTimeSource()
  {
    return timeSource;
  }
  
  public static final void setTimeSource(@NotNull TimeSource paramTimeSource)
  {
    Intrinsics.checkParameterIsNotNull(paramTimeSource, "<set-?>");
    timeSource = paramTimeSource;
  }
}
