package kotlin.system;

import kotlin.Metadata;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;

@Metadata(bv={1, 0, 2}, d1={"\000\016\n\000\n\002\020\001\n\000\n\002\020\b\n\000\032\021\020\000\032\0020\0012\006\020\002\032\0020\003H\b¨\006\004"}, d2={"exitProcess", "", "status", "", "kotlin-stdlib"}, k=2, mv={1, 1, 9})
@JvmName(name="ProcessKt")
public final class ProcessKt
{
  @InlineOnly
  private static final Void exitProcess(int paramInt)
  {
    System.exit(paramInt);
    throw ((Throwable)new RuntimeException("System.exit returned normally, while it was supposed to halt JVM."));
  }
}
