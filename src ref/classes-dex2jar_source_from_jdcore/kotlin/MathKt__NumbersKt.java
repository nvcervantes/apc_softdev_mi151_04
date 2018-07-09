package kotlin;

import kotlin.internal.InlineOnly;
import kotlin.jvm.internal.DoubleCompanionObject;
import kotlin.jvm.internal.FloatCompanionObject;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000&\n\000\n\002\020\006\n\002\030\002\n\000\n\002\020\t\n\002\020\007\n\002\030\002\n\002\020\b\n\000\n\002\020\013\n\002\b\005\032\025\020\000\032\0020\001*\0020\0022\006\020\003\032\0020\004H\b\032\025\020\000\032\0020\005*\0020\0062\006\020\003\032\0020\007H\b\032\r\020\b\032\0020\t*\0020\001H\b\032\r\020\b\032\0020\t*\0020\005H\b\032\r\020\n\032\0020\t*\0020\001H\b\032\r\020\n\032\0020\t*\0020\005H\b\032\r\020\013\032\0020\t*\0020\001H\b\032\r\020\013\032\0020\t*\0020\005H\b\032\r\020\f\032\0020\004*\0020\001H\b\032\r\020\f\032\0020\007*\0020\005H\b\032\r\020\r\032\0020\004*\0020\001H\b\032\r\020\r\032\0020\007*\0020\005H\b¨\006\016"}, d2={"fromBits", "", "Lkotlin/Double$Companion;", "bits", "", "", "Lkotlin/Float$Companion;", "", "isFinite", "", "isInfinite", "isNaN", "toBits", "toRawBits", "kotlin-stdlib"}, k=5, mv={1, 1, 9}, xi=1, xs="kotlin/MathKt")
class MathKt__NumbersKt
  extends MathKt__BigIntegersKt
{
  public MathKt__NumbersKt() {}
  
  @SinceKotlin(version="1.2")
  @InlineOnly
  private static final double fromBits(@NotNull DoubleCompanionObject paramDoubleCompanionObject, long paramLong)
  {
    return Double.longBitsToDouble(paramLong);
  }
  
  @SinceKotlin(version="1.2")
  @InlineOnly
  private static final float fromBits(@NotNull FloatCompanionObject paramFloatCompanionObject, int paramInt)
  {
    return Float.intBitsToFloat(paramInt);
  }
  
  @InlineOnly
  private static final boolean isFinite(double paramDouble)
  {
    return (!Double.isInfinite(paramDouble)) && (!Double.isNaN(paramDouble));
  }
  
  @InlineOnly
  private static final boolean isFinite(float paramFloat)
  {
    return (!Float.isInfinite(paramFloat)) && (!Float.isNaN(paramFloat));
  }
  
  @InlineOnly
  private static final boolean isInfinite(double paramDouble)
  {
    return Double.isInfinite(paramDouble);
  }
  
  @InlineOnly
  private static final boolean isInfinite(float paramFloat)
  {
    return Float.isInfinite(paramFloat);
  }
  
  @InlineOnly
  private static final boolean isNaN(double paramDouble)
  {
    return Double.isNaN(paramDouble);
  }
  
  @InlineOnly
  private static final boolean isNaN(float paramFloat)
  {
    return Float.isNaN(paramFloat);
  }
  
  @SinceKotlin(version="1.2")
  @InlineOnly
  private static final int toBits(float paramFloat)
  {
    return Float.floatToIntBits(paramFloat);
  }
  
  @SinceKotlin(version="1.2")
  @InlineOnly
  private static final long toBits(double paramDouble)
  {
    return Double.doubleToLongBits(paramDouble);
  }
  
  @SinceKotlin(version="1.2")
  @InlineOnly
  private static final int toRawBits(float paramFloat)
  {
    return Float.floatToRawIntBits(paramFloat);
  }
  
  @SinceKotlin(version="1.2")
  @InlineOnly
  private static final long toRawBits(double paramDouble)
  {
    return Double.doubleToRawLongBits(paramDouble);
  }
}
