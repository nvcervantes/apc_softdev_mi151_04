package kotlin.internal.jdk7;

import kotlin.Metadata;
import kotlin.internal.PlatformImplementations;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000\032\n\002\030\002\n\002\030\002\n\002\b\002\n\002\020\002\n\000\n\002\020\003\n\002\b\002\b\020\030\0002\0020\001B\005¢\006\002\020\002J\030\020\003\032\0020\0042\006\020\005\032\0020\0062\006\020\007\032\0020\006H\026¨\006\b"}, d2={"Lkotlin/internal/jdk7/JDK7PlatformImplementations;", "Lkotlin/internal/PlatformImplementations;", "()V", "addSuppressed", "", "cause", "", "exception", "kotlin-stdlib-jdk7"}, k=1, mv={1, 1, 9})
public class JDK7PlatformImplementations
  extends PlatformImplementations
{
  public JDK7PlatformImplementations() {}
  
  public void addSuppressed(@NotNull Throwable paramThrowable1, @NotNull Throwable paramThrowable2)
  {
    Intrinsics.checkParameterIsNotNull(paramThrowable1, "cause");
    Intrinsics.checkParameterIsNotNull(paramThrowable2, "exception");
    paramThrowable1.addSuppressed(paramThrowable2);
  }
}
