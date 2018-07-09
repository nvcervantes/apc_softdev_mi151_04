package kotlin;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import kotlin.internal.InlineOnly;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000$\n\000\n\002\030\002\n\002\b\t\n\002\020\006\n\000\n\002\030\002\n\002\020\007\n\002\020\b\n\002\020\t\n\002\b\002\032\r\020\000\032\0020\001*\0020\001H\n\032\025\020\002\032\0020\001*\0020\0012\006\020\003\032\0020\001H\n\032\r\020\004\032\0020\001*\0020\001H\n\032\025\020\005\032\0020\001*\0020\0012\006\020\003\032\0020\001H\n\032\025\020\006\032\0020\001*\0020\0012\006\020\003\032\0020\001H\n\032\025\020\007\032\0020\001*\0020\0012\006\020\003\032\0020\001H\n\032\025\020\b\032\0020\001*\0020\0012\006\020\003\032\0020\001H\n\032\025\020\t\032\0020\001*\0020\0012\006\020\003\032\0020\001H\n\032\r\020\n\032\0020\001*\0020\013H\b\032\025\020\n\032\0020\001*\0020\0132\006\020\f\032\0020\rH\b\032\r\020\n\032\0020\001*\0020\016H\b\032\025\020\n\032\0020\001*\0020\0162\006\020\f\032\0020\rH\b\032\r\020\n\032\0020\001*\0020\017H\b\032\025\020\n\032\0020\001*\0020\0172\006\020\f\032\0020\rH\b\032\r\020\n\032\0020\001*\0020\020H\b\032\025\020\n\032\0020\001*\0020\0202\006\020\f\032\0020\rH\b\032\r\020\021\032\0020\001*\0020\001H\n¨\006\022"}, d2={"dec", "Ljava/math/BigDecimal;", "div", "other", "inc", "minus", "mod", "plus", "rem", "times", "toBigDecimal", "", "mathContext", "Ljava/math/MathContext;", "", "", "", "unaryMinus", "kotlin-stdlib"}, k=5, mv={1, 1, 9}, xi=1, xs="kotlin/MathKt")
class MathKt__BigDecimalsKt
{
  public MathKt__BigDecimalsKt() {}
  
  @SinceKotlin(version="1.2")
  @InlineOnly
  private static final BigDecimal dec(@NotNull BigDecimal paramBigDecimal)
  {
    Intrinsics.checkParameterIsNotNull(paramBigDecimal, "$receiver");
    paramBigDecimal = paramBigDecimal.subtract(BigDecimal.ONE);
    Intrinsics.checkExpressionValueIsNotNull(paramBigDecimal, "this.subtract(BigDecimal.ONE)");
    return paramBigDecimal;
  }
  
  @InlineOnly
  private static final BigDecimal div(@NotNull BigDecimal paramBigDecimal1, BigDecimal paramBigDecimal2)
  {
    Intrinsics.checkParameterIsNotNull(paramBigDecimal1, "$receiver");
    paramBigDecimal1 = paramBigDecimal1.divide(paramBigDecimal2, RoundingMode.HALF_EVEN);
    Intrinsics.checkExpressionValueIsNotNull(paramBigDecimal1, "this.divide(other, RoundingMode.HALF_EVEN)");
    return paramBigDecimal1;
  }
  
  @SinceKotlin(version="1.2")
  @InlineOnly
  private static final BigDecimal inc(@NotNull BigDecimal paramBigDecimal)
  {
    Intrinsics.checkParameterIsNotNull(paramBigDecimal, "$receiver");
    paramBigDecimal = paramBigDecimal.add(BigDecimal.ONE);
    Intrinsics.checkExpressionValueIsNotNull(paramBigDecimal, "this.add(BigDecimal.ONE)");
    return paramBigDecimal;
  }
  
  @InlineOnly
  private static final BigDecimal minus(@NotNull BigDecimal paramBigDecimal1, BigDecimal paramBigDecimal2)
  {
    Intrinsics.checkParameterIsNotNull(paramBigDecimal1, "$receiver");
    paramBigDecimal1 = paramBigDecimal1.subtract(paramBigDecimal2);
    Intrinsics.checkExpressionValueIsNotNull(paramBigDecimal1, "this.subtract(other)");
    return paramBigDecimal1;
  }
  
  @Deprecated(level=DeprecationLevel.WARNING, message="Use rem(other) instead", replaceWith=@ReplaceWith(expression="rem(other)", imports={}))
  @InlineOnly
  private static final BigDecimal mod(@NotNull BigDecimal paramBigDecimal1, BigDecimal paramBigDecimal2)
  {
    Intrinsics.checkParameterIsNotNull(paramBigDecimal1, "$receiver");
    paramBigDecimal1 = paramBigDecimal1.remainder(paramBigDecimal2);
    Intrinsics.checkExpressionValueIsNotNull(paramBigDecimal1, "this.remainder(other)");
    return paramBigDecimal1;
  }
  
  @InlineOnly
  private static final BigDecimal plus(@NotNull BigDecimal paramBigDecimal1, BigDecimal paramBigDecimal2)
  {
    Intrinsics.checkParameterIsNotNull(paramBigDecimal1, "$receiver");
    paramBigDecimal1 = paramBigDecimal1.add(paramBigDecimal2);
    Intrinsics.checkExpressionValueIsNotNull(paramBigDecimal1, "this.add(other)");
    return paramBigDecimal1;
  }
  
  @InlineOnly
  private static final BigDecimal rem(@NotNull BigDecimal paramBigDecimal1, BigDecimal paramBigDecimal2)
  {
    Intrinsics.checkParameterIsNotNull(paramBigDecimal1, "$receiver");
    paramBigDecimal1 = paramBigDecimal1.remainder(paramBigDecimal2);
    Intrinsics.checkExpressionValueIsNotNull(paramBigDecimal1, "this.remainder(other)");
    return paramBigDecimal1;
  }
  
  @InlineOnly
  private static final BigDecimal times(@NotNull BigDecimal paramBigDecimal1, BigDecimal paramBigDecimal2)
  {
    Intrinsics.checkParameterIsNotNull(paramBigDecimal1, "$receiver");
    paramBigDecimal1 = paramBigDecimal1.multiply(paramBigDecimal2);
    Intrinsics.checkExpressionValueIsNotNull(paramBigDecimal1, "this.multiply(other)");
    return paramBigDecimal1;
  }
  
  @SinceKotlin(version="1.2")
  @InlineOnly
  private static final BigDecimal toBigDecimal(double paramDouble)
  {
    return new BigDecimal(String.valueOf(paramDouble));
  }
  
  @SinceKotlin(version="1.2")
  @InlineOnly
  private static final BigDecimal toBigDecimal(double paramDouble, MathContext paramMathContext)
  {
    return new BigDecimal(String.valueOf(paramDouble), paramMathContext);
  }
  
  @SinceKotlin(version="1.2")
  @InlineOnly
  private static final BigDecimal toBigDecimal(float paramFloat)
  {
    return new BigDecimal(String.valueOf(paramFloat));
  }
  
  @SinceKotlin(version="1.2")
  @InlineOnly
  private static final BigDecimal toBigDecimal(float paramFloat, MathContext paramMathContext)
  {
    return new BigDecimal(String.valueOf(paramFloat), paramMathContext);
  }
  
  @SinceKotlin(version="1.2")
  @InlineOnly
  private static final BigDecimal toBigDecimal(int paramInt)
  {
    return new BigDecimal(paramInt);
  }
  
  @SinceKotlin(version="1.2")
  @InlineOnly
  private static final BigDecimal toBigDecimal(int paramInt, MathContext paramMathContext)
  {
    return new BigDecimal(paramInt, paramMathContext);
  }
  
  @SinceKotlin(version="1.2")
  @InlineOnly
  private static final BigDecimal toBigDecimal(long paramLong)
  {
    return new BigDecimal(paramLong);
  }
  
  @SinceKotlin(version="1.2")
  @InlineOnly
  private static final BigDecimal toBigDecimal(long paramLong, MathContext paramMathContext)
  {
    return new BigDecimal(paramLong, paramMathContext);
  }
  
  @InlineOnly
  private static final BigDecimal unaryMinus(@NotNull BigDecimal paramBigDecimal)
  {
    Intrinsics.checkParameterIsNotNull(paramBigDecimal, "$receiver");
    paramBigDecimal = paramBigDecimal.negate();
    Intrinsics.checkExpressionValueIsNotNull(paramBigDecimal, "this.negate()");
    return paramBigDecimal;
  }
}
