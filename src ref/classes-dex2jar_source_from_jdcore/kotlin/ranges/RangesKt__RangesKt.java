package kotlin.ranges;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\0000\n\000\n\002\020\002\n\000\n\002\020\013\n\000\n\002\020\004\n\000\n\002\030\002\n\000\n\002\020\017\n\002\b\002\n\002\030\002\n\002\020\006\n\002\020\007\n\000\032\030\020\000\032\0020\0012\006\020\002\032\0020\0032\006\020\004\032\0020\005H\000\0320\020\006\032\b\022\004\022\002H\b0\007\"\016\b\000\020\b*\b\022\004\022\002H\b0\t*\002H\b2\006\020\n\032\002H\bH\002¢\006\002\020\013\032\033\020\006\032\b\022\004\022\0020\r0\f*\0020\r2\006\020\n\032\0020\rH\002\032\033\020\006\032\b\022\004\022\0020\0160\f*\0020\0162\006\020\n\032\0020\016H\002¨\006\017"}, d2={"checkStepIsPositive", "", "isPositive", "", "step", "", "rangeTo", "Lkotlin/ranges/ClosedRange;", "T", "", "that", "(Ljava/lang/Comparable;Ljava/lang/Comparable;)Lkotlin/ranges/ClosedRange;", "Lkotlin/ranges/ClosedFloatingPointRange;", "", "", "kotlin-stdlib"}, k=5, mv={1, 1, 9}, xi=1, xs="kotlin/ranges/RangesKt")
class RangesKt__RangesKt
{
  public RangesKt__RangesKt() {}
  
  public static final void checkStepIsPositive(boolean paramBoolean, @NotNull Number paramNumber)
  {
    Intrinsics.checkParameterIsNotNull(paramNumber, "step");
    if (!paramBoolean)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Step must be positive, was: ");
      localStringBuilder.append(paramNumber);
      localStringBuilder.append('.');
      throw ((Throwable)new IllegalArgumentException(localStringBuilder.toString()));
    }
  }
  
  @SinceKotlin(version="1.1")
  @NotNull
  public static final ClosedFloatingPointRange<Double> rangeTo(double paramDouble1, double paramDouble2)
  {
    return (ClosedFloatingPointRange)new ClosedDoubleRange(paramDouble1, paramDouble2);
  }
  
  @SinceKotlin(version="1.1")
  @NotNull
  public static final ClosedFloatingPointRange<Float> rangeTo(float paramFloat1, float paramFloat2)
  {
    return (ClosedFloatingPointRange)new ClosedFloatRange(paramFloat1, paramFloat2);
  }
  
  @NotNull
  public static final <T extends Comparable<? super T>> ClosedRange<T> rangeTo(@NotNull T paramT1, @NotNull T paramT2)
  {
    Intrinsics.checkParameterIsNotNull(paramT1, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramT2, "that");
    return (ClosedRange)new ComparableRange(paramT1, paramT2);
  }
}
