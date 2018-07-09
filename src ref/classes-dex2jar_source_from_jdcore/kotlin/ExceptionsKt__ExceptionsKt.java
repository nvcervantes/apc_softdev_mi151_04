package kotlin;

import java.io.PrintStream;
import java.io.PrintWriter;
import kotlin.internal.InlineOnly;
import kotlin.internal.PlatformImplementations;
import kotlin.internal.PlatformImplementationsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000&\n\000\n\002\020\021\n\002\030\002\n\002\020\003\n\002\b\005\n\002\020\002\n\002\b\003\n\002\030\002\n\000\n\002\030\002\n\000\032\022\020\b\032\0020\t*\0020\0032\006\020\n\032\0020\003\032\r\020\013\032\0020\t*\0020\003H\b\032\025\020\013\032\0020\t*\0020\0032\006\020\f\032\0020\rH\b\032\025\020\013\032\0020\t*\0020\0032\006\020\016\032\0020\017H\b\"$\020\000\032\b\022\004\022\0020\0020\001*\0020\0038FX\004¢\006\f\022\004\b\004\020\005\032\004\b\006\020\007¨\006\020"}, d2={"stackTrace", "", "Ljava/lang/StackTraceElement;", "", "stackTrace$annotations", "(Ljava/lang/Throwable;)V", "getStackTrace", "(Ljava/lang/Throwable;)[Ljava/lang/StackTraceElement;", "addSuppressed", "", "exception", "printStackTrace", "stream", "Ljava/io/PrintStream;", "writer", "Ljava/io/PrintWriter;", "kotlin-stdlib"}, k=5, mv={1, 1, 9}, xi=1, xs="kotlin/ExceptionsKt")
class ExceptionsKt__ExceptionsKt
{
  public ExceptionsKt__ExceptionsKt() {}
  
  public static final void addSuppressed(@NotNull Throwable paramThrowable1, @NotNull Throwable paramThrowable2)
  {
    Intrinsics.checkParameterIsNotNull(paramThrowable1, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramThrowable2, "exception");
    PlatformImplementationsKt.IMPLEMENTATIONS.addSuppressed(paramThrowable1, paramThrowable2);
  }
  
  @NotNull
  public static final StackTraceElement[] getStackTrace(@NotNull Throwable paramThrowable)
  {
    Intrinsics.checkParameterIsNotNull(paramThrowable, "$receiver");
    paramThrowable = paramThrowable.getStackTrace();
    if (paramThrowable == null) {
      Intrinsics.throwNpe();
    }
    return paramThrowable;
  }
  
  @InlineOnly
  private static final void printStackTrace(@NotNull Throwable paramThrowable)
  {
    if (paramThrowable == null) {
      throw new TypeCastException("null cannot be cast to non-null type java.lang.Throwable");
    }
    paramThrowable.printStackTrace();
  }
  
  @InlineOnly
  private static final void printStackTrace(@NotNull Throwable paramThrowable, PrintStream paramPrintStream)
  {
    if (paramThrowable == null) {
      throw new TypeCastException("null cannot be cast to non-null type java.lang.Throwable");
    }
    paramThrowable.printStackTrace(paramPrintStream);
  }
  
  @InlineOnly
  private static final void printStackTrace(@NotNull Throwable paramThrowable, PrintWriter paramPrintWriter)
  {
    if (paramThrowable == null) {
      throw new TypeCastException("null cannot be cast to non-null type java.lang.Throwable");
    }
    paramThrowable.printStackTrace(paramPrintWriter);
  }
}
