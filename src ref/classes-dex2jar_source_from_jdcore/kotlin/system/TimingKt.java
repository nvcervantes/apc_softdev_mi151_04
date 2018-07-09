package kotlin.system;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000\024\n\000\n\002\020\t\n\000\n\002\030\002\n\002\020\002\n\002\b\002\032\027\020\000\032\0020\0012\f\020\002\032\b\022\004\022\0020\0040\003H\b\032\027\020\005\032\0020\0012\f\020\002\032\b\022\004\022\0020\0040\003H\b¨\006\006"}, d2={"measureNanoTime", "", "block", "Lkotlin/Function0;", "", "measureTimeMillis", "kotlin-stdlib"}, k=2, mv={1, 1, 9})
@JvmName(name="TimingKt")
public final class TimingKt
{
  public static final long measureNanoTime(@NotNull Function0<Unit> paramFunction0)
  {
    Intrinsics.checkParameterIsNotNull(paramFunction0, "block");
    long l = System.nanoTime();
    paramFunction0.invoke();
    return System.nanoTime() - l;
  }
  
  public static final long measureTimeMillis(@NotNull Function0<Unit> paramFunction0)
  {
    Intrinsics.checkParameterIsNotNull(paramFunction0, "block");
    long l = System.currentTimeMillis();
    paramFunction0.invoke();
    return System.currentTimeMillis() - l;
  }
}
