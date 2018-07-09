package kotlin.math;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.DoubleCompanionObject;
import kotlin.jvm.internal.FloatCompanionObject;

@Metadata(bv={1, 0, 2}, d1={"\000\"\n\000\n\002\020\006\n\002\b\017\n\002\020\007\n\002\b\002\n\002\020\b\n\002\b\002\n\002\020\t\n\002\b7\032\021\020!\032\0020\0012\006\020\"\032\0020\001H\b\032\021\020!\032\0020\0212\006\020\"\032\0020\021H\b\032\021\020!\032\0020\0242\006\020#\032\0020\024H\b\032\021\020!\032\0020\0272\006\020#\032\0020\027H\b\032\021\020$\032\0020\0012\006\020\"\032\0020\001H\b\032\021\020$\032\0020\0212\006\020\"\032\0020\021H\b\032\020\020%\032\0020\0012\006\020\"\032\0020\001H\007\032\021\020%\032\0020\0212\006\020\"\032\0020\021H\b\032\021\020&\032\0020\0012\006\020\"\032\0020\001H\b\032\021\020&\032\0020\0212\006\020\"\032\0020\021H\b\032\020\020'\032\0020\0012\006\020\"\032\0020\001H\007\032\021\020'\032\0020\0212\006\020\"\032\0020\021H\b\032\021\020(\032\0020\0012\006\020\"\032\0020\001H\b\032\021\020(\032\0020\0212\006\020\"\032\0020\021H\b\032\031\020)\032\0020\0012\006\020*\032\0020\0012\006\020\"\032\0020\001H\b\032\031\020)\032\0020\0212\006\020*\032\0020\0212\006\020\"\032\0020\021H\b\032\020\020+\032\0020\0012\006\020\"\032\0020\001H\007\032\021\020+\032\0020\0212\006\020\"\032\0020\021H\b\032\021\020,\032\0020\0012\006\020\"\032\0020\001H\b\032\021\020,\032\0020\0212\006\020\"\032\0020\021H\b\032\021\020-\032\0020\0012\006\020\"\032\0020\001H\b\032\021\020-\032\0020\0212\006\020\"\032\0020\021H\b\032\021\020.\032\0020\0012\006\020\"\032\0020\001H\b\032\021\020.\032\0020\0212\006\020\"\032\0020\021H\b\032\021\020/\032\0020\0012\006\020\"\032\0020\001H\b\032\021\020/\032\0020\0212\006\020\"\032\0020\021H\b\032\021\0200\032\0020\0012\006\020\"\032\0020\001H\b\032\021\0200\032\0020\0212\006\020\"\032\0020\021H\b\032\021\0201\032\0020\0012\006\020\"\032\0020\001H\b\032\021\0201\032\0020\0212\006\020\"\032\0020\021H\b\032\031\0202\032\0020\0012\006\020\"\032\0020\0012\006\020*\032\0020\001H\b\032\031\0202\032\0020\0212\006\020\"\032\0020\0212\006\020*\032\0020\021H\b\032\021\0203\032\0020\0012\006\020\"\032\0020\001H\b\032\021\0203\032\0020\0212\006\020\"\032\0020\021H\b\032\021\0204\032\0020\0012\006\020\"\032\0020\001H\b\032\021\0204\032\0020\0212\006\020\"\032\0020\021H\b\032\030\0205\032\0020\0012\006\020\"\032\0020\0012\006\0206\032\0020\001H\007\032\030\0205\032\0020\0212\006\020\"\032\0020\0212\006\0206\032\0020\021H\007\032\021\0207\032\0020\0012\006\020\"\032\0020\001H\b\032\021\0207\032\0020\0212\006\020\"\032\0020\021H\b\032\020\0208\032\0020\0012\006\020\"\032\0020\001H\007\032\020\0208\032\0020\0212\006\020\"\032\0020\021H\007\032\031\0209\032\0020\0012\006\020:\032\0020\0012\006\020;\032\0020\001H\b\032\031\0209\032\0020\0212\006\020:\032\0020\0212\006\020;\032\0020\021H\b\032\031\0209\032\0020\0242\006\020:\032\0020\0242\006\020;\032\0020\024H\b\032\031\0209\032\0020\0272\006\020:\032\0020\0272\006\020;\032\0020\027H\b\032\031\020<\032\0020\0012\006\020:\032\0020\0012\006\020;\032\0020\001H\b\032\031\020<\032\0020\0212\006\020:\032\0020\0212\006\020;\032\0020\021H\b\032\031\020<\032\0020\0242\006\020:\032\0020\0242\006\020;\032\0020\024H\b\032\031\020<\032\0020\0272\006\020:\032\0020\0272\006\020;\032\0020\027H\b\032\021\020=\032\0020\0012\006\020\"\032\0020\001H\b\032\021\020=\032\0020\0212\006\020\"\032\0020\021H\b\032\021\020\032\032\0020\0012\006\020\"\032\0020\001H\b\032\021\020\032\032\0020\0212\006\020\"\032\0020\021H\b\032\021\020>\032\0020\0012\006\020\"\032\0020\001H\b\032\021\020>\032\0020\0212\006\020\"\032\0020\021H\b\032\021\020?\032\0020\0012\006\020\"\032\0020\001H\b\032\021\020?\032\0020\0212\006\020\"\032\0020\021H\b\032\021\020@\032\0020\0012\006\020\"\032\0020\001H\b\032\021\020@\032\0020\0212\006\020\"\032\0020\021H\b\032\021\020A\032\0020\0012\006\020\"\032\0020\001H\b\032\021\020A\032\0020\0212\006\020\"\032\0020\021H\b\032\021\020B\032\0020\0012\006\020\"\032\0020\001H\b\032\021\020B\032\0020\0212\006\020\"\032\0020\021H\b\032\020\020C\032\0020\0012\006\020\"\032\0020\001H\007\032\020\020C\032\0020\0212\006\020\"\032\0020\021H\007\032\025\020D\032\0020\001*\0020\0012\006\020E\032\0020\001H\b\032\025\020D\032\0020\021*\0020\0212\006\020E\032\0020\021H\b\032\r\020F\032\0020\001*\0020\001H\b\032\r\020F\032\0020\021*\0020\021H\b\032\025\020G\032\0020\001*\0020\0012\006\020H\032\0020\001H\b\032\025\020G\032\0020\021*\0020\0212\006\020H\032\0020\021H\b\032\r\020I\032\0020\001*\0020\001H\b\032\r\020I\032\0020\021*\0020\021H\b\032\025\020J\032\0020\001*\0020\0012\006\020\"\032\0020\001H\b\032\025\020J\032\0020\001*\0020\0012\006\020#\032\0020\024H\b\032\025\020J\032\0020\021*\0020\0212\006\020\"\032\0020\021H\b\032\025\020J\032\0020\021*\0020\0212\006\020#\032\0020\024H\b\032\f\020K\032\0020\024*\0020\001H\007\032\f\020K\032\0020\024*\0020\021H\007\032\f\020L\032\0020\027*\0020\001H\007\032\f\020L\032\0020\027*\0020\021H\007\032\025\020M\032\0020\001*\0020\0012\006\020\032\032\0020\001H\b\032\025\020M\032\0020\001*\0020\0012\006\020\032\032\0020\024H\b\032\025\020M\032\0020\021*\0020\0212\006\020\032\032\0020\021H\b\032\025\020M\032\0020\021*\0020\0212\006\020\032\032\0020\024H\b\"\026\020\000\032\0020\0018\006XT¢\006\b\n\000\022\004\b\002\020\003\"\016\020\004\032\0020\001X\004¢\006\002\n\000\"\026\020\005\032\0020\0018\006XT¢\006\b\n\000\022\004\b\006\020\003\"\016\020\007\032\0020\001X\004¢\006\002\n\000\"\016\020\b\032\0020\001X\004¢\006\002\n\000\"\016\020\t\032\0020\001X\004¢\006\002\n\000\"\016\020\n\032\0020\001X\004¢\006\002\n\000\"\016\020\013\032\0020\001X\004¢\006\002\n\000\"\037\020\f\032\0020\001*\0020\0018Æ\002X\004¢\006\f\022\004\b\r\020\016\032\004\b\017\020\020\"\037\020\f\032\0020\021*\0020\0218Æ\002X\004¢\006\f\022\004\b\r\020\022\032\004\b\017\020\023\"\037\020\f\032\0020\024*\0020\0248Æ\002X\004¢\006\f\022\004\b\r\020\025\032\004\b\017\020\026\"\037\020\f\032\0020\027*\0020\0278Æ\002X\004¢\006\f\022\004\b\r\020\030\032\004\b\017\020\031\"\037\020\032\032\0020\001*\0020\0018Æ\002X\004¢\006\f\022\004\b\033\020\016\032\004\b\034\020\020\"\037\020\032\032\0020\021*\0020\0218Æ\002X\004¢\006\f\022\004\b\033\020\022\032\004\b\034\020\023\"\036\020\032\032\0020\024*\0020\0248FX\004¢\006\f\022\004\b\033\020\025\032\004\b\034\020\026\"\036\020\032\032\0020\024*\0020\0278FX\004¢\006\f\022\004\b\033\020\030\032\004\b\034\020\035\"\037\020\036\032\0020\001*\0020\0018Æ\002X\004¢\006\f\022\004\b\037\020\016\032\004\b \020\020\"\037\020\036\032\0020\021*\0020\0218Æ\002X\004¢\006\f\022\004\b\037\020\022\032\004\b \020\023¨\006N"}, d2={"E", "", "E$annotations", "()V", "LN2", "PI", "PI$annotations", "epsilon", "taylor_2_bound", "taylor_n_bound", "upper_taylor_2_bound", "upper_taylor_n_bound", "absoluteValue", "absoluteValue$annotations", "(D)V", "getAbsoluteValue", "(D)D", "", "(F)V", "(F)F", "", "(I)V", "(I)I", "", "(J)V", "(J)J", "sign", "sign$annotations", "getSign", "(J)I", "ulp", "ulp$annotations", "getUlp", "abs", "x", "n", "acos", "acosh", "asin", "asinh", "atan", "atan2", "y", "atanh", "ceil", "cos", "cosh", "exp", "expm1", "floor", "hypot", "ln", "ln1p", "log", "base", "log10", "log2", "max", "a", "b", "min", "round", "sin", "sinh", "sqrt", "tan", "tanh", "truncate", "IEEErem", "divisor", "nextDown", "nextTowards", "to", "nextUp", "pow", "roundToInt", "roundToLong", "withSign", "kotlin-stdlib"}, k=2, mv={1, 1, 9})
@JvmName(name="MathKt")
public final class MathKt
{
  public static final double E = 2.718281828459045D;
  private static final double LN2 = Math.log(2.0D);
  public static final double PI = 3.141592653589793D;
  private static final double epsilon = Math.ulp(1.0D);
  private static final double taylor_2_bound = Math.sqrt(epsilon);
  private static final double taylor_n_bound = Math.sqrt(taylor_2_bound);
  private static final double upper_taylor_2_bound;
  private static final double upper_taylor_n_bound;
  
  static
  {
    double d = 1;
    upper_taylor_2_bound = d / taylor_2_bound;
    upper_taylor_n_bound = d / taylor_n_bound;
  }
  
  @SinceKotlin(version="1.2")
  @InlineOnly
  private static final double IEEErem(double paramDouble1, double paramDouble2)
  {
    return Math.IEEEremainder(paramDouble1, paramDouble2);
  }
  
  @SinceKotlin(version="1.2")
  @InlineOnly
  private static final float IEEErem(float paramFloat1, float paramFloat2)
  {
    return (float)Math.IEEEremainder(paramFloat1, paramFloat2);
  }
  
  @SinceKotlin(version="1.2")
  @InlineOnly
  private static final double abs(double paramDouble)
  {
    return Math.abs(paramDouble);
  }
  
  @SinceKotlin(version="1.2")
  @InlineOnly
  private static final float abs(float paramFloat)
  {
    return Math.abs(paramFloat);
  }
  
  @SinceKotlin(version="1.2")
  @InlineOnly
  private static final int abs(int paramInt)
  {
    return Math.abs(paramInt);
  }
  
  @SinceKotlin(version="1.2")
  @InlineOnly
  private static final long abs(long paramLong)
  {
    return Math.abs(paramLong);
  }
  
  @SinceKotlin(version="1.2")
  @InlineOnly
  private static final double acos(double paramDouble)
  {
    return Math.acos(paramDouble);
  }
  
  @SinceKotlin(version="1.2")
  @InlineOnly
  private static final float acos(float paramFloat)
  {
    return (float)Math.acos(paramFloat);
  }
  
  @SinceKotlin(version="1.2")
  public static final double acosh(double paramDouble)
  {
    double d1 = 1;
    if (paramDouble < d1) {
      return DoubleCompanionObject.INSTANCE.getNaN();
    }
    if (paramDouble > upper_taylor_2_bound) {
      return Math.log(paramDouble) + LN2;
    }
    double d2 = paramDouble - d1;
    if (d2 >= taylor_n_bound) {
      return Math.log(paramDouble + Math.sqrt(paramDouble * paramDouble - d1));
    }
    d1 = Math.sqrt(d2);
    paramDouble = d1;
    if (d1 >= taylor_2_bound) {
      paramDouble = d1 - d1 * d1 * d1 / 12;
    }
    return paramDouble * Math.sqrt(2.0D);
  }
  
  @SinceKotlin(version="1.2")
  @InlineOnly
  private static final float acosh(float paramFloat)
  {
    return (float)acosh(paramFloat);
  }
  
  @SinceKotlin(version="1.2")
  @InlineOnly
  private static final double asin(double paramDouble)
  {
    return Math.asin(paramDouble);
  }
  
  @SinceKotlin(version="1.2")
  @InlineOnly
  private static final float asin(float paramFloat)
  {
    return (float)Math.asin(paramFloat);
  }
  
  @SinceKotlin(version="1.2")
  public static final double asinh(double paramDouble)
  {
    if (paramDouble >= taylor_n_bound)
    {
      if (paramDouble > upper_taylor_n_bound)
      {
        if (paramDouble > upper_taylor_2_bound) {
          return Math.log(paramDouble) + LN2;
        }
        paramDouble *= 2;
        return Math.log(paramDouble + 1 / paramDouble);
      }
      return Math.log(paramDouble + Math.sqrt(paramDouble * paramDouble + 1));
    }
    if (paramDouble <= -taylor_n_bound) {
      return -asinh(-paramDouble);
    }
    double d = paramDouble;
    if (Math.abs(paramDouble) >= taylor_2_bound) {
      d = paramDouble - paramDouble * paramDouble * paramDouble / 6;
    }
    return d;
  }
  
  @SinceKotlin(version="1.2")
  @InlineOnly
  private static final float asinh(float paramFloat)
  {
    return (float)asinh(paramFloat);
  }
  
  @SinceKotlin(version="1.2")
  @InlineOnly
  private static final double atan(double paramDouble)
  {
    return Math.atan(paramDouble);
  }
  
  @SinceKotlin(version="1.2")
  @InlineOnly
  private static final float atan(float paramFloat)
  {
    return (float)Math.atan(paramFloat);
  }
  
  @SinceKotlin(version="1.2")
  @InlineOnly
  private static final double atan2(double paramDouble1, double paramDouble2)
  {
    return Math.atan2(paramDouble1, paramDouble2);
  }
  
  @SinceKotlin(version="1.2")
  @InlineOnly
  private static final float atan2(float paramFloat1, float paramFloat2)
  {
    return (float)Math.atan2(paramFloat1, paramFloat2);
  }
  
  @SinceKotlin(version="1.2")
  public static final double atanh(double paramDouble)
  {
    if (Math.abs(paramDouble) < taylor_n_bound)
    {
      d = paramDouble;
      if (Math.abs(paramDouble) > taylor_2_bound) {
        d = paramDouble + paramDouble * paramDouble * paramDouble / 3;
      }
      return d;
    }
    double d = 1;
    return Math.log((d + paramDouble) / (d - paramDouble)) / 2;
  }
  
  @SinceKotlin(version="1.2")
  @InlineOnly
  private static final float atanh(float paramFloat)
  {
    return (float)atanh(paramFloat);
  }
  
  @SinceKotlin(version="1.2")
  @InlineOnly
  private static final double ceil(double paramDouble)
  {
    return Math.ceil(paramDouble);
  }
  
  @SinceKotlin(version="1.2")
  @InlineOnly
  private static final float ceil(float paramFloat)
  {
    return (float)Math.ceil(paramFloat);
  }
  
  @SinceKotlin(version="1.2")
  @InlineOnly
  private static final double cos(double paramDouble)
  {
    return Math.cos(paramDouble);
  }
  
  @SinceKotlin(version="1.2")
  @InlineOnly
  private static final float cos(float paramFloat)
  {
    return (float)Math.cos(paramFloat);
  }
  
  @SinceKotlin(version="1.2")
  @InlineOnly
  private static final double cosh(double paramDouble)
  {
    return Math.cosh(paramDouble);
  }
  
  @SinceKotlin(version="1.2")
  @InlineOnly
  private static final float cosh(float paramFloat)
  {
    return (float)Math.cosh(paramFloat);
  }
  
  @SinceKotlin(version="1.2")
  @InlineOnly
  private static final double exp(double paramDouble)
  {
    return Math.exp(paramDouble);
  }
  
  @SinceKotlin(version="1.2")
  @InlineOnly
  private static final float exp(float paramFloat)
  {
    return (float)Math.exp(paramFloat);
  }
  
  @SinceKotlin(version="1.2")
  @InlineOnly
  private static final double expm1(double paramDouble)
  {
    return Math.expm1(paramDouble);
  }
  
  @SinceKotlin(version="1.2")
  @InlineOnly
  private static final float expm1(float paramFloat)
  {
    return (float)Math.expm1(paramFloat);
  }
  
  @SinceKotlin(version="1.2")
  @InlineOnly
  private static final double floor(double paramDouble)
  {
    return Math.floor(paramDouble);
  }
  
  @SinceKotlin(version="1.2")
  @InlineOnly
  private static final float floor(float paramFloat)
  {
    return (float)Math.floor(paramFloat);
  }
  
  private static final double getAbsoluteValue(double paramDouble)
  {
    return Math.abs(paramDouble);
  }
  
  private static final float getAbsoluteValue(float paramFloat)
  {
    return Math.abs(paramFloat);
  }
  
  private static final int getAbsoluteValue(int paramInt)
  {
    return Math.abs(paramInt);
  }
  
  private static final long getAbsoluteValue(long paramLong)
  {
    return Math.abs(paramLong);
  }
  
  private static final double getSign(double paramDouble)
  {
    return Math.signum(paramDouble);
  }
  
  private static final float getSign(float paramFloat)
  {
    return Math.signum(paramFloat);
  }
  
  public static final int getSign(int paramInt)
  {
    if (paramInt < 0) {
      return -1;
    }
    if (paramInt > 0) {
      return 1;
    }
    return 0;
  }
  
  public static final int getSign(long paramLong)
  {
    if (paramLong < 0L) {
      return -1;
    }
    if (paramLong > 0L) {
      return 1;
    }
    return 0;
  }
  
  private static final double getUlp(double paramDouble)
  {
    return Math.ulp(paramDouble);
  }
  
  private static final float getUlp(float paramFloat)
  {
    return Math.ulp(paramFloat);
  }
  
  @SinceKotlin(version="1.2")
  @InlineOnly
  private static final double hypot(double paramDouble1, double paramDouble2)
  {
    return Math.hypot(paramDouble1, paramDouble2);
  }
  
  @SinceKotlin(version="1.2")
  @InlineOnly
  private static final float hypot(float paramFloat1, float paramFloat2)
  {
    return (float)Math.hypot(paramFloat1, paramFloat2);
  }
  
  @SinceKotlin(version="1.2")
  @InlineOnly
  private static final double ln(double paramDouble)
  {
    return Math.log(paramDouble);
  }
  
  @SinceKotlin(version="1.2")
  @InlineOnly
  private static final float ln(float paramFloat)
  {
    return (float)Math.log(paramFloat);
  }
  
  @SinceKotlin(version="1.2")
  @InlineOnly
  private static final double ln1p(double paramDouble)
  {
    return Math.log1p(paramDouble);
  }
  
  @SinceKotlin(version="1.2")
  @InlineOnly
  private static final float ln1p(float paramFloat)
  {
    return (float)Math.log1p(paramFloat);
  }
  
  @SinceKotlin(version="1.2")
  public static final double log(double paramDouble1, double paramDouble2)
  {
    if ((paramDouble2 > 0.0D) && (paramDouble2 != 1.0D)) {
      return Math.log(paramDouble1) / Math.log(paramDouble2);
    }
    return DoubleCompanionObject.INSTANCE.getNaN();
  }
  
  @SinceKotlin(version="1.2")
  public static final float log(float paramFloat1, float paramFloat2)
  {
    if ((paramFloat2 > 0.0F) && (paramFloat2 != 1.0F)) {
      return (float)(Math.log(paramFloat1) / Math.log(paramFloat2));
    }
    return FloatCompanionObject.INSTANCE.getNaN();
  }
  
  @SinceKotlin(version="1.2")
  @InlineOnly
  private static final double log10(double paramDouble)
  {
    return Math.log10(paramDouble);
  }
  
  @SinceKotlin(version="1.2")
  @InlineOnly
  private static final float log10(float paramFloat)
  {
    return (float)Math.log10(paramFloat);
  }
  
  @SinceKotlin(version="1.2")
  public static final double log2(double paramDouble)
  {
    return Math.log(paramDouble) / LN2;
  }
  
  @SinceKotlin(version="1.2")
  public static final float log2(float paramFloat)
  {
    return (float)(Math.log(paramFloat) / LN2);
  }
  
  @SinceKotlin(version="1.2")
  @InlineOnly
  private static final double max(double paramDouble1, double paramDouble2)
  {
    return Math.max(paramDouble1, paramDouble2);
  }
  
  @SinceKotlin(version="1.2")
  @InlineOnly
  private static final float max(float paramFloat1, float paramFloat2)
  {
    return Math.max(paramFloat1, paramFloat2);
  }
  
  @SinceKotlin(version="1.2")
  @InlineOnly
  private static final int max(int paramInt1, int paramInt2)
  {
    return Math.max(paramInt1, paramInt2);
  }
  
  @SinceKotlin(version="1.2")
  @InlineOnly
  private static final long max(long paramLong1, long paramLong2)
  {
    return Math.max(paramLong1, paramLong2);
  }
  
  @SinceKotlin(version="1.2")
  @InlineOnly
  private static final double min(double paramDouble1, double paramDouble2)
  {
    return Math.min(paramDouble1, paramDouble2);
  }
  
  @SinceKotlin(version="1.2")
  @InlineOnly
  private static final float min(float paramFloat1, float paramFloat2)
  {
    return Math.min(paramFloat1, paramFloat2);
  }
  
  @SinceKotlin(version="1.2")
  @InlineOnly
  private static final int min(int paramInt1, int paramInt2)
  {
    return Math.min(paramInt1, paramInt2);
  }
  
  @SinceKotlin(version="1.2")
  @InlineOnly
  private static final long min(long paramLong1, long paramLong2)
  {
    return Math.min(paramLong1, paramLong2);
  }
  
  @SinceKotlin(version="1.2")
  @InlineOnly
  private static final double nextDown(double paramDouble)
  {
    return Math.nextAfter(paramDouble, DoubleCompanionObject.INSTANCE.getNEGATIVE_INFINITY());
  }
  
  @SinceKotlin(version="1.2")
  @InlineOnly
  private static final float nextDown(float paramFloat)
  {
    return Math.nextAfter(paramFloat, DoubleCompanionObject.INSTANCE.getNEGATIVE_INFINITY());
  }
  
  @SinceKotlin(version="1.2")
  @InlineOnly
  private static final double nextTowards(double paramDouble1, double paramDouble2)
  {
    return Math.nextAfter(paramDouble1, paramDouble2);
  }
  
  @SinceKotlin(version="1.2")
  @InlineOnly
  private static final float nextTowards(float paramFloat1, float paramFloat2)
  {
    return Math.nextAfter(paramFloat1, paramFloat2);
  }
  
  @SinceKotlin(version="1.2")
  @InlineOnly
  private static final double nextUp(double paramDouble)
  {
    return Math.nextUp(paramDouble);
  }
  
  @SinceKotlin(version="1.2")
  @InlineOnly
  private static final float nextUp(float paramFloat)
  {
    return Math.nextUp(paramFloat);
  }
  
  @SinceKotlin(version="1.2")
  @InlineOnly
  private static final double pow(double paramDouble1, double paramDouble2)
  {
    return Math.pow(paramDouble1, paramDouble2);
  }
  
  @SinceKotlin(version="1.2")
  @InlineOnly
  private static final double pow(double paramDouble, int paramInt)
  {
    return Math.pow(paramDouble, paramInt);
  }
  
  @SinceKotlin(version="1.2")
  @InlineOnly
  private static final float pow(float paramFloat1, float paramFloat2)
  {
    return (float)Math.pow(paramFloat1, paramFloat2);
  }
  
  @SinceKotlin(version="1.2")
  @InlineOnly
  private static final float pow(float paramFloat, int paramInt)
  {
    return (float)Math.pow(paramFloat, paramInt);
  }
  
  @SinceKotlin(version="1.2")
  @InlineOnly
  private static final double round(double paramDouble)
  {
    return Math.rint(paramDouble);
  }
  
  @SinceKotlin(version="1.2")
  @InlineOnly
  private static final float round(float paramFloat)
  {
    return (float)Math.rint(paramFloat);
  }
  
  @SinceKotlin(version="1.2")
  public static final int roundToInt(double paramDouble)
  {
    if (Double.isNaN(paramDouble)) {
      throw ((Throwable)new IllegalArgumentException("Cannot round NaN value."));
    }
    if (paramDouble > Integer.MAX_VALUE) {
      return Integer.MAX_VALUE;
    }
    if (paramDouble < Integer.MIN_VALUE) {
      return Integer.MIN_VALUE;
    }
    return (int)Math.round(paramDouble);
  }
  
  @SinceKotlin(version="1.2")
  public static final int roundToInt(float paramFloat)
  {
    if (Float.isNaN(paramFloat)) {
      throw ((Throwable)new IllegalArgumentException("Cannot round NaN value."));
    }
    return Math.round(paramFloat);
  }
  
  @SinceKotlin(version="1.2")
  public static final long roundToLong(double paramDouble)
  {
    if (Double.isNaN(paramDouble)) {
      throw ((Throwable)new IllegalArgumentException("Cannot round NaN value."));
    }
    return Math.round(paramDouble);
  }
  
  @SinceKotlin(version="1.2")
  public static final long roundToLong(float paramFloat)
  {
    return roundToLong(paramFloat);
  }
  
  @SinceKotlin(version="1.2")
  @InlineOnly
  private static final double sign(double paramDouble)
  {
    return Math.signum(paramDouble);
  }
  
  @SinceKotlin(version="1.2")
  @InlineOnly
  private static final float sign(float paramFloat)
  {
    return Math.signum(paramFloat);
  }
  
  @SinceKotlin(version="1.2")
  @InlineOnly
  private static final double sin(double paramDouble)
  {
    return Math.sin(paramDouble);
  }
  
  @SinceKotlin(version="1.2")
  @InlineOnly
  private static final float sin(float paramFloat)
  {
    return (float)Math.sin(paramFloat);
  }
  
  @SinceKotlin(version="1.2")
  @InlineOnly
  private static final double sinh(double paramDouble)
  {
    return Math.sinh(paramDouble);
  }
  
  @SinceKotlin(version="1.2")
  @InlineOnly
  private static final float sinh(float paramFloat)
  {
    return (float)Math.sinh(paramFloat);
  }
  
  @SinceKotlin(version="1.2")
  @InlineOnly
  private static final double sqrt(double paramDouble)
  {
    return Math.sqrt(paramDouble);
  }
  
  @SinceKotlin(version="1.2")
  @InlineOnly
  private static final float sqrt(float paramFloat)
  {
    return (float)Math.sqrt(paramFloat);
  }
  
  @SinceKotlin(version="1.2")
  @InlineOnly
  private static final double tan(double paramDouble)
  {
    return Math.tan(paramDouble);
  }
  
  @SinceKotlin(version="1.2")
  @InlineOnly
  private static final float tan(float paramFloat)
  {
    return (float)Math.tan(paramFloat);
  }
  
  @SinceKotlin(version="1.2")
  @InlineOnly
  private static final double tanh(double paramDouble)
  {
    return Math.tanh(paramDouble);
  }
  
  @SinceKotlin(version="1.2")
  @InlineOnly
  private static final float tanh(float paramFloat)
  {
    return (float)Math.tanh(paramFloat);
  }
  
  @SinceKotlin(version="1.2")
  public static final double truncate(double paramDouble)
  {
    double d = paramDouble;
    if (!Double.isNaN(paramDouble))
    {
      if (Double.isInfinite(paramDouble)) {
        return paramDouble;
      }
      if (paramDouble > 0) {
        return Math.floor(paramDouble);
      }
      d = Math.ceil(paramDouble);
    }
    return d;
  }
  
  @SinceKotlin(version="1.2")
  public static final float truncate(float paramFloat)
  {
    float f = paramFloat;
    if (!Float.isNaN(paramFloat))
    {
      if (Float.isInfinite(paramFloat)) {
        return paramFloat;
      }
      if (paramFloat > 0) {
        return (float)Math.floor(paramFloat);
      }
      f = (float)Math.ceil(paramFloat);
    }
    return f;
  }
  
  @SinceKotlin(version="1.2")
  @InlineOnly
  private static final double withSign(double paramDouble1, double paramDouble2)
  {
    return Math.copySign(paramDouble1, paramDouble2);
  }
  
  @SinceKotlin(version="1.2")
  @InlineOnly
  private static final double withSign(double paramDouble, int paramInt)
  {
    return Math.copySign(paramDouble, paramInt);
  }
  
  @SinceKotlin(version="1.2")
  @InlineOnly
  private static final float withSign(float paramFloat1, float paramFloat2)
  {
    return Math.copySign(paramFloat1, paramFloat2);
  }
  
  @SinceKotlin(version="1.2")
  @InlineOnly
  private static final float withSign(float paramFloat, int paramInt)
  {
    return Math.copySign(paramFloat, paramInt);
  }
}
