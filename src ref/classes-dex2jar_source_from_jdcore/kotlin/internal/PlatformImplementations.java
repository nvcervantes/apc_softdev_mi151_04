package kotlin.internal;

import java.util.regex.MatchResult;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.MatchGroup;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000,\n\002\030\002\n\002\020\000\n\002\b\002\n\002\020\002\n\000\n\002\020\003\n\002\b\002\n\002\030\002\n\000\n\002\030\002\n\000\n\002\020\016\n\000\b\020\030\0002\0020\001B\005¢\006\002\020\002J\030\020\003\032\0020\0042\006\020\005\032\0020\0062\006\020\007\032\0020\006H\026J\032\020\b\032\004\030\0010\t2\006\020\n\032\0020\0132\006\020\f\032\0020\rH\026¨\006\016"}, d2={"Lkotlin/internal/PlatformImplementations;", "", "()V", "addSuppressed", "", "cause", "", "exception", "getMatchResultNamedGroup", "Lkotlin/text/MatchGroup;", "matchResult", "Ljava/util/regex/MatchResult;", "name", "", "kotlin-stdlib"}, k=1, mv={1, 1, 9})
public class PlatformImplementations
{
  public PlatformImplementations() {}
  
  public void addSuppressed(@NotNull Throwable paramThrowable1, @NotNull Throwable paramThrowable2)
  {
    Intrinsics.checkParameterIsNotNull(paramThrowable1, "cause");
    Intrinsics.checkParameterIsNotNull(paramThrowable2, "exception");
  }
  
  @Nullable
  public MatchGroup getMatchResultNamedGroup(@NotNull MatchResult paramMatchResult, @NotNull String paramString)
  {
    Intrinsics.checkParameterIsNotNull(paramMatchResult, "matchResult");
    Intrinsics.checkParameterIsNotNull(paramString, "name");
    throw ((Throwable)new UnsupportedOperationException("Retrieving groups by name is not supported on this platform."));
  }
}
