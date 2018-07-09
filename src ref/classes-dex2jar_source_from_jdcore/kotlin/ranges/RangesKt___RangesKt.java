package kotlin.ranges;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000b\n\002\b\002\n\002\020\017\n\002\b\002\n\002\020\005\n\002\020\006\n\002\020\007\n\002\020\b\n\002\020\t\n\002\020\n\n\002\b\005\n\002\030\002\n\000\n\002\030\002\n\002\b\002\n\002\020\013\n\002\b\b\n\002\030\002\n\000\n\002\030\002\n\002\030\002\n\002\020\f\n\002\b\025\n\002\030\002\n\002\030\002\n\002\030\002\n\000\032'\020\000\032\002H\001\"\016\b\000\020\001*\b\022\004\022\002H\0010\002*\002H\0012\006\020\003\032\002H\001¢\006\002\020\004\032\022\020\000\032\0020\005*\0020\0052\006\020\003\032\0020\005\032\022\020\000\032\0020\006*\0020\0062\006\020\003\032\0020\006\032\022\020\000\032\0020\007*\0020\0072\006\020\003\032\0020\007\032\022\020\000\032\0020\b*\0020\b2\006\020\003\032\0020\b\032\022\020\000\032\0020\t*\0020\t2\006\020\003\032\0020\t\032\022\020\000\032\0020\n*\0020\n2\006\020\003\032\0020\n\032'\020\013\032\002H\001\"\016\b\000\020\001*\b\022\004\022\002H\0010\002*\002H\0012\006\020\f\032\002H\001¢\006\002\020\004\032\022\020\013\032\0020\005*\0020\0052\006\020\f\032\0020\005\032\022\020\013\032\0020\006*\0020\0062\006\020\f\032\0020\006\032\022\020\013\032\0020\007*\0020\0072\006\020\f\032\0020\007\032\022\020\013\032\0020\b*\0020\b2\006\020\f\032\0020\b\032\022\020\013\032\0020\t*\0020\t2\006\020\f\032\0020\t\032\022\020\013\032\0020\n*\0020\n2\006\020\f\032\0020\n\0323\020\r\032\002H\001\"\016\b\000\020\001*\b\022\004\022\002H\0010\002*\002H\0012\b\020\003\032\004\030\001H\0012\b\020\f\032\004\030\001H\001¢\006\002\020\016\032/\020\r\032\002H\001\"\016\b\000\020\001*\b\022\004\022\002H\0010\002*\002H\0012\f\020\017\032\b\022\004\022\002H\0010\020H\007¢\006\002\020\021\032-\020\r\032\002H\001\"\016\b\000\020\001*\b\022\004\022\002H\0010\002*\002H\0012\f\020\017\032\b\022\004\022\002H\0010\022¢\006\002\020\023\032\032\020\r\032\0020\005*\0020\0052\006\020\003\032\0020\0052\006\020\f\032\0020\005\032\032\020\r\032\0020\006*\0020\0062\006\020\003\032\0020\0062\006\020\f\032\0020\006\032\032\020\r\032\0020\007*\0020\0072\006\020\003\032\0020\0072\006\020\f\032\0020\007\032\032\020\r\032\0020\b*\0020\b2\006\020\003\032\0020\b2\006\020\f\032\0020\b\032\030\020\r\032\0020\b*\0020\b2\f\020\017\032\b\022\004\022\0020\b0\022\032\032\020\r\032\0020\t*\0020\t2\006\020\003\032\0020\t2\006\020\f\032\0020\t\032\030\020\r\032\0020\t*\0020\t2\f\020\017\032\b\022\004\022\0020\t0\022\032\032\020\r\032\0020\n*\0020\n2\006\020\003\032\0020\n2\006\020\f\032\0020\n\032 \020\024\032\0020\025*\b\022\004\022\0020\0050\0222\006\020\026\032\0020\006H\002¢\006\002\b\027\032 \020\024\032\0020\025*\b\022\004\022\0020\0050\0222\006\020\026\032\0020\007H\002¢\006\002\b\027\032 \020\024\032\0020\025*\b\022\004\022\0020\0050\0222\006\020\026\032\0020\bH\002¢\006\002\b\027\032 \020\024\032\0020\025*\b\022\004\022\0020\0050\0222\006\020\026\032\0020\tH\002¢\006\002\b\027\032 \020\024\032\0020\025*\b\022\004\022\0020\0050\0222\006\020\026\032\0020\nH\002¢\006\002\b\027\032 \020\024\032\0020\025*\b\022\004\022\0020\0060\0222\006\020\026\032\0020\005H\002¢\006\002\b\030\032 \020\024\032\0020\025*\b\022\004\022\0020\0060\0222\006\020\026\032\0020\007H\002¢\006\002\b\030\032 \020\024\032\0020\025*\b\022\004\022\0020\0060\0222\006\020\026\032\0020\bH\002¢\006\002\b\030\032 \020\024\032\0020\025*\b\022\004\022\0020\0060\0222\006\020\026\032\0020\tH\002¢\006\002\b\030\032 \020\024\032\0020\025*\b\022\004\022\0020\0060\0222\006\020\026\032\0020\nH\002¢\006\002\b\030\032 \020\024\032\0020\025*\b\022\004\022\0020\0070\0222\006\020\026\032\0020\005H\002¢\006\002\b\031\032 \020\024\032\0020\025*\b\022\004\022\0020\0070\0222\006\020\026\032\0020\006H\002¢\006\002\b\031\032 \020\024\032\0020\025*\b\022\004\022\0020\0070\0222\006\020\026\032\0020\bH\002¢\006\002\b\031\032 \020\024\032\0020\025*\b\022\004\022\0020\0070\0222\006\020\026\032\0020\tH\002¢\006\002\b\031\032 \020\024\032\0020\025*\b\022\004\022\0020\0070\0222\006\020\026\032\0020\nH\002¢\006\002\b\031\032 \020\024\032\0020\025*\b\022\004\022\0020\b0\0222\006\020\026\032\0020\005H\002¢\006\002\b\032\032 \020\024\032\0020\025*\b\022\004\022\0020\b0\0222\006\020\026\032\0020\006H\002¢\006\002\b\032\032 \020\024\032\0020\025*\b\022\004\022\0020\b0\0222\006\020\026\032\0020\007H\002¢\006\002\b\032\032 \020\024\032\0020\025*\b\022\004\022\0020\b0\0222\006\020\026\032\0020\tH\002¢\006\002\b\032\032 \020\024\032\0020\025*\b\022\004\022\0020\b0\0222\006\020\026\032\0020\nH\002¢\006\002\b\032\032 \020\024\032\0020\025*\b\022\004\022\0020\t0\0222\006\020\026\032\0020\005H\002¢\006\002\b\033\032 \020\024\032\0020\025*\b\022\004\022\0020\t0\0222\006\020\026\032\0020\006H\002¢\006\002\b\033\032 \020\024\032\0020\025*\b\022\004\022\0020\t0\0222\006\020\026\032\0020\007H\002¢\006\002\b\033\032 \020\024\032\0020\025*\b\022\004\022\0020\t0\0222\006\020\026\032\0020\bH\002¢\006\002\b\033\032 \020\024\032\0020\025*\b\022\004\022\0020\t0\0222\006\020\026\032\0020\nH\002¢\006\002\b\033\032 \020\024\032\0020\025*\b\022\004\022\0020\n0\0222\006\020\026\032\0020\005H\002¢\006\002\b\034\032 \020\024\032\0020\025*\b\022\004\022\0020\n0\0222\006\020\026\032\0020\006H\002¢\006\002\b\034\032 \020\024\032\0020\025*\b\022\004\022\0020\n0\0222\006\020\026\032\0020\007H\002¢\006\002\b\034\032 \020\024\032\0020\025*\b\022\004\022\0020\n0\0222\006\020\026\032\0020\bH\002¢\006\002\b\034\032 \020\024\032\0020\025*\b\022\004\022\0020\n0\0222\006\020\026\032\0020\tH\002¢\006\002\b\034\032\025\020\035\032\0020\036*\0020\0052\006\020\037\032\0020\005H\004\032\025\020\035\032\0020\036*\0020\0052\006\020\037\032\0020\bH\004\032\025\020\035\032\0020 *\0020\0052\006\020\037\032\0020\tH\004\032\025\020\035\032\0020\036*\0020\0052\006\020\037\032\0020\nH\004\032\025\020\035\032\0020!*\0020\"2\006\020\037\032\0020\"H\004\032\025\020\035\032\0020\036*\0020\b2\006\020\037\032\0020\005H\004\032\025\020\035\032\0020\036*\0020\b2\006\020\037\032\0020\bH\004\032\025\020\035\032\0020 *\0020\b2\006\020\037\032\0020\tH\004\032\025\020\035\032\0020\036*\0020\b2\006\020\037\032\0020\nH\004\032\025\020\035\032\0020 *\0020\t2\006\020\037\032\0020\005H\004\032\025\020\035\032\0020 *\0020\t2\006\020\037\032\0020\bH\004\032\025\020\035\032\0020 *\0020\t2\006\020\037\032\0020\tH\004\032\025\020\035\032\0020 *\0020\t2\006\020\037\032\0020\nH\004\032\025\020\035\032\0020\036*\0020\n2\006\020\037\032\0020\005H\004\032\025\020\035\032\0020\036*\0020\n2\006\020\037\032\0020\bH\004\032\025\020\035\032\0020 *\0020\n2\006\020\037\032\0020\tH\004\032\025\020\035\032\0020\036*\0020\n2\006\020\037\032\0020\nH\004\032\n\020#\032\0020!*\0020!\032\n\020#\032\0020\036*\0020\036\032\n\020#\032\0020 *\0020 \032\025\020$\032\0020!*\0020!2\006\020$\032\0020\bH\004\032\025\020$\032\0020\036*\0020\0362\006\020$\032\0020\bH\004\032\025\020$\032\0020 *\0020 2\006\020$\032\0020\tH\004\032\023\020%\032\004\030\0010\005*\0020\006H\000¢\006\002\020&\032\023\020%\032\004\030\0010\005*\0020\007H\000¢\006\002\020'\032\023\020%\032\004\030\0010\005*\0020\bH\000¢\006\002\020(\032\023\020%\032\004\030\0010\005*\0020\tH\000¢\006\002\020)\032\023\020%\032\004\030\0010\005*\0020\nH\000¢\006\002\020*\032\023\020+\032\004\030\0010\b*\0020\006H\000¢\006\002\020,\032\023\020+\032\004\030\0010\b*\0020\007H\000¢\006\002\020-\032\023\020+\032\004\030\0010\b*\0020\tH\000¢\006\002\020.\032\023\020/\032\004\030\0010\t*\0020\006H\000¢\006\002\0200\032\023\020/\032\004\030\0010\t*\0020\007H\000¢\006\002\0201\032\023\0202\032\004\030\0010\n*\0020\006H\000¢\006\002\0203\032\023\0202\032\004\030\0010\n*\0020\007H\000¢\006\002\0204\032\023\0202\032\004\030\0010\n*\0020\bH\000¢\006\002\0205\032\023\0202\032\004\030\0010\n*\0020\tH\000¢\006\002\0206\032\025\0207\032\00208*\0020\0052\006\020\037\032\0020\005H\004\032\025\0207\032\00208*\0020\0052\006\020\037\032\0020\bH\004\032\025\0207\032\00209*\0020\0052\006\020\037\032\0020\tH\004\032\025\0207\032\00208*\0020\0052\006\020\037\032\0020\nH\004\032\025\0207\032\0020:*\0020\"2\006\020\037\032\0020\"H\004\032\025\0207\032\00208*\0020\b2\006\020\037\032\0020\005H\004\032\025\0207\032\00208*\0020\b2\006\020\037\032\0020\bH\004\032\025\0207\032\00209*\0020\b2\006\020\037\032\0020\tH\004\032\025\0207\032\00208*\0020\b2\006\020\037\032\0020\nH\004\032\025\0207\032\00209*\0020\t2\006\020\037\032\0020\005H\004\032\025\0207\032\00209*\0020\t2\006\020\037\032\0020\bH\004\032\025\0207\032\00209*\0020\t2\006\020\037\032\0020\tH\004\032\025\0207\032\00209*\0020\t2\006\020\037\032\0020\nH\004\032\025\0207\032\00208*\0020\n2\006\020\037\032\0020\005H\004\032\025\0207\032\00208*\0020\n2\006\020\037\032\0020\bH\004\032\025\0207\032\00209*\0020\n2\006\020\037\032\0020\tH\004\032\025\0207\032\00208*\0020\n2\006\020\037\032\0020\nH\004¨\006;"}, d2={"coerceAtLeast", "T", "", "minimumValue", "(Ljava/lang/Comparable;Ljava/lang/Comparable;)Ljava/lang/Comparable;", "", "", "", "", "", "", "coerceAtMost", "maximumValue", "coerceIn", "(Ljava/lang/Comparable;Ljava/lang/Comparable;Ljava/lang/Comparable;)Ljava/lang/Comparable;", "range", "Lkotlin/ranges/ClosedFloatingPointRange;", "(Ljava/lang/Comparable;Lkotlin/ranges/ClosedFloatingPointRange;)Ljava/lang/Comparable;", "Lkotlin/ranges/ClosedRange;", "(Ljava/lang/Comparable;Lkotlin/ranges/ClosedRange;)Ljava/lang/Comparable;", "contains", "", "value", "byteRangeContains", "doubleRangeContains", "floatRangeContains", "intRangeContains", "longRangeContains", "shortRangeContains", "downTo", "Lkotlin/ranges/IntProgression;", "to", "Lkotlin/ranges/LongProgression;", "Lkotlin/ranges/CharProgression;", "", "reversed", "step", "toByteExactOrNull", "(D)Ljava/lang/Byte;", "(F)Ljava/lang/Byte;", "(I)Ljava/lang/Byte;", "(J)Ljava/lang/Byte;", "(S)Ljava/lang/Byte;", "toIntExactOrNull", "(D)Ljava/lang/Integer;", "(F)Ljava/lang/Integer;", "(J)Ljava/lang/Integer;", "toLongExactOrNull", "(D)Ljava/lang/Long;", "(F)Ljava/lang/Long;", "toShortExactOrNull", "(D)Ljava/lang/Short;", "(F)Ljava/lang/Short;", "(I)Ljava/lang/Short;", "(J)Ljava/lang/Short;", "until", "Lkotlin/ranges/IntRange;", "Lkotlin/ranges/LongRange;", "Lkotlin/ranges/CharRange;", "kotlin-stdlib"}, k=5, mv={1, 1, 9}, xi=1, xs="kotlin/ranges/RangesKt")
class RangesKt___RangesKt
  extends RangesKt__RangesKt
{
  public RangesKt___RangesKt() {}
  
  @JvmName(name="byteRangeContains")
  public static final boolean byteRangeContains(@NotNull ClosedRange<Byte> paramClosedRange, double paramDouble)
  {
    Intrinsics.checkParameterIsNotNull(paramClosedRange, "$receiver");
    Byte localByte = RangesKt.toByteExactOrNull(paramDouble);
    if (localByte != null) {
      return paramClosedRange.contains((Comparable)localByte);
    }
    return false;
  }
  
  @JvmName(name="byteRangeContains")
  public static final boolean byteRangeContains(@NotNull ClosedRange<Byte> paramClosedRange, float paramFloat)
  {
    Intrinsics.checkParameterIsNotNull(paramClosedRange, "$receiver");
    Byte localByte = RangesKt.toByteExactOrNull(paramFloat);
    if (localByte != null) {
      return paramClosedRange.contains((Comparable)localByte);
    }
    return false;
  }
  
  @JvmName(name="byteRangeContains")
  public static final boolean byteRangeContains(@NotNull ClosedRange<Byte> paramClosedRange, int paramInt)
  {
    Intrinsics.checkParameterIsNotNull(paramClosedRange, "$receiver");
    Byte localByte = RangesKt.toByteExactOrNull(paramInt);
    if (localByte != null) {
      return paramClosedRange.contains((Comparable)localByte);
    }
    return false;
  }
  
  @JvmName(name="byteRangeContains")
  public static final boolean byteRangeContains(@NotNull ClosedRange<Byte> paramClosedRange, long paramLong)
  {
    Intrinsics.checkParameterIsNotNull(paramClosedRange, "$receiver");
    Byte localByte = RangesKt.toByteExactOrNull(paramLong);
    if (localByte != null) {
      return paramClosedRange.contains((Comparable)localByte);
    }
    return false;
  }
  
  @JvmName(name="byteRangeContains")
  public static final boolean byteRangeContains(@NotNull ClosedRange<Byte> paramClosedRange, short paramShort)
  {
    Intrinsics.checkParameterIsNotNull(paramClosedRange, "$receiver");
    Byte localByte = RangesKt.toByteExactOrNull(paramShort);
    if (localByte != null) {
      return paramClosedRange.contains((Comparable)localByte);
    }
    return false;
  }
  
  public static final byte coerceAtLeast(byte paramByte1, byte paramByte2)
  {
    byte b = paramByte1;
    if (paramByte1 < paramByte2) {
      b = paramByte2;
    }
    return b;
  }
  
  public static final double coerceAtLeast(double paramDouble1, double paramDouble2)
  {
    double d = paramDouble1;
    if (paramDouble1 < paramDouble2) {
      d = paramDouble2;
    }
    return d;
  }
  
  public static final float coerceAtLeast(float paramFloat1, float paramFloat2)
  {
    float f = paramFloat1;
    if (paramFloat1 < paramFloat2) {
      f = paramFloat2;
    }
    return f;
  }
  
  public static final int coerceAtLeast(int paramInt1, int paramInt2)
  {
    int i = paramInt1;
    if (paramInt1 < paramInt2) {
      i = paramInt2;
    }
    return i;
  }
  
  public static final long coerceAtLeast(long paramLong1, long paramLong2)
  {
    long l = paramLong1;
    if (paramLong1 < paramLong2) {
      l = paramLong2;
    }
    return l;
  }
  
  @NotNull
  public static final <T extends Comparable<? super T>> T coerceAtLeast(@NotNull T paramT1, @NotNull T paramT2)
  {
    Intrinsics.checkParameterIsNotNull(paramT1, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramT2, "minimumValue");
    T ? = paramT1;
    if (paramT1.compareTo(paramT2) < 0) {
      ? = paramT2;
    }
    return ?;
  }
  
  public static final short coerceAtLeast(short paramShort1, short paramShort2)
  {
    short s = paramShort1;
    if (paramShort1 < paramShort2) {
      s = paramShort2;
    }
    return s;
  }
  
  public static final byte coerceAtMost(byte paramByte1, byte paramByte2)
  {
    byte b = paramByte1;
    if (paramByte1 > paramByte2) {
      b = paramByte2;
    }
    return b;
  }
  
  public static final double coerceAtMost(double paramDouble1, double paramDouble2)
  {
    double d = paramDouble1;
    if (paramDouble1 > paramDouble2) {
      d = paramDouble2;
    }
    return d;
  }
  
  public static final float coerceAtMost(float paramFloat1, float paramFloat2)
  {
    float f = paramFloat1;
    if (paramFloat1 > paramFloat2) {
      f = paramFloat2;
    }
    return f;
  }
  
  public static final int coerceAtMost(int paramInt1, int paramInt2)
  {
    int i = paramInt1;
    if (paramInt1 > paramInt2) {
      i = paramInt2;
    }
    return i;
  }
  
  public static final long coerceAtMost(long paramLong1, long paramLong2)
  {
    long l = paramLong1;
    if (paramLong1 > paramLong2) {
      l = paramLong2;
    }
    return l;
  }
  
  @NotNull
  public static final <T extends Comparable<? super T>> T coerceAtMost(@NotNull T paramT1, @NotNull T paramT2)
  {
    Intrinsics.checkParameterIsNotNull(paramT1, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramT2, "maximumValue");
    T ? = paramT1;
    if (paramT1.compareTo(paramT2) > 0) {
      ? = paramT2;
    }
    return ?;
  }
  
  public static final short coerceAtMost(short paramShort1, short paramShort2)
  {
    short s = paramShort1;
    if (paramShort1 > paramShort2) {
      s = paramShort2;
    }
    return s;
  }
  
  public static final byte coerceIn(byte paramByte1, byte paramByte2, byte paramByte3)
  {
    if (paramByte2 > paramByte3)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Cannot coerce value to an empty range: maximum ");
      localStringBuilder.append(paramByte3);
      localStringBuilder.append(" is less than minimum ");
      localStringBuilder.append(paramByte2);
      localStringBuilder.append('.');
      throw ((Throwable)new IllegalArgumentException(localStringBuilder.toString()));
    }
    if (paramByte1 < paramByte2) {
      return paramByte2;
    }
    if (paramByte1 > paramByte3) {
      return paramByte3;
    }
    return paramByte1;
  }
  
  public static final double coerceIn(double paramDouble1, double paramDouble2, double paramDouble3)
  {
    if (paramDouble2 > paramDouble3)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Cannot coerce value to an empty range: maximum ");
      localStringBuilder.append(paramDouble3);
      localStringBuilder.append(" is less than minimum ");
      localStringBuilder.append(paramDouble2);
      localStringBuilder.append('.');
      throw ((Throwable)new IllegalArgumentException(localStringBuilder.toString()));
    }
    if (paramDouble1 < paramDouble2) {
      return paramDouble2;
    }
    if (paramDouble1 > paramDouble3) {
      return paramDouble3;
    }
    return paramDouble1;
  }
  
  public static final float coerceIn(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    if (paramFloat2 > paramFloat3)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Cannot coerce value to an empty range: maximum ");
      localStringBuilder.append(paramFloat3);
      localStringBuilder.append(" is less than minimum ");
      localStringBuilder.append(paramFloat2);
      localStringBuilder.append('.');
      throw ((Throwable)new IllegalArgumentException(localStringBuilder.toString()));
    }
    if (paramFloat1 < paramFloat2) {
      return paramFloat2;
    }
    if (paramFloat1 > paramFloat3) {
      return paramFloat3;
    }
    return paramFloat1;
  }
  
  public static final int coerceIn(int paramInt1, int paramInt2, int paramInt3)
  {
    if (paramInt2 > paramInt3)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Cannot coerce value to an empty range: maximum ");
      localStringBuilder.append(paramInt3);
      localStringBuilder.append(" is less than minimum ");
      localStringBuilder.append(paramInt2);
      localStringBuilder.append('.');
      throw ((Throwable)new IllegalArgumentException(localStringBuilder.toString()));
    }
    if (paramInt1 < paramInt2) {
      return paramInt2;
    }
    if (paramInt1 > paramInt3) {
      return paramInt3;
    }
    return paramInt1;
  }
  
  public static final int coerceIn(int paramInt, @NotNull ClosedRange<Integer> paramClosedRange)
  {
    Intrinsics.checkParameterIsNotNull(paramClosedRange, "range");
    if ((paramClosedRange instanceof ClosedFloatingPointRange)) {
      return ((Number)RangesKt.coerceIn((Comparable)Integer.valueOf(paramInt), (ClosedFloatingPointRange)paramClosedRange)).intValue();
    }
    if (paramClosedRange.isEmpty())
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Cannot coerce value to an empty range: ");
      localStringBuilder.append(paramClosedRange);
      localStringBuilder.append('.');
      throw ((Throwable)new IllegalArgumentException(localStringBuilder.toString()));
    }
    if (paramInt < ((Number)paramClosedRange.getStart()).intValue()) {
      return ((Number)paramClosedRange.getStart()).intValue();
    }
    int i = paramInt;
    if (paramInt > ((Number)paramClosedRange.getEndInclusive()).intValue()) {
      i = ((Number)paramClosedRange.getEndInclusive()).intValue();
    }
    return i;
  }
  
  public static final long coerceIn(long paramLong1, long paramLong2, long paramLong3)
  {
    if (paramLong2 > paramLong3)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Cannot coerce value to an empty range: maximum ");
      localStringBuilder.append(paramLong3);
      localStringBuilder.append(" is less than minimum ");
      localStringBuilder.append(paramLong2);
      localStringBuilder.append('.');
      throw ((Throwable)new IllegalArgumentException(localStringBuilder.toString()));
    }
    if (paramLong1 < paramLong2) {
      return paramLong2;
    }
    if (paramLong1 > paramLong3) {
      return paramLong3;
    }
    return paramLong1;
  }
  
  public static final long coerceIn(long paramLong, @NotNull ClosedRange<Long> paramClosedRange)
  {
    Intrinsics.checkParameterIsNotNull(paramClosedRange, "range");
    if ((paramClosedRange instanceof ClosedFloatingPointRange)) {
      return ((Number)RangesKt.coerceIn((Comparable)Long.valueOf(paramLong), (ClosedFloatingPointRange)paramClosedRange)).longValue();
    }
    if (paramClosedRange.isEmpty())
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Cannot coerce value to an empty range: ");
      localStringBuilder.append(paramClosedRange);
      localStringBuilder.append('.');
      throw ((Throwable)new IllegalArgumentException(localStringBuilder.toString()));
    }
    if (paramLong < ((Number)paramClosedRange.getStart()).longValue()) {
      return ((Number)paramClosedRange.getStart()).longValue();
    }
    long l = paramLong;
    if (paramLong > ((Number)paramClosedRange.getEndInclusive()).longValue()) {
      l = ((Number)paramClosedRange.getEndInclusive()).longValue();
    }
    return l;
  }
  
  @NotNull
  public static final <T extends Comparable<? super T>> T coerceIn(@NotNull T paramT1, @Nullable T paramT2, @Nullable T paramT3)
  {
    Intrinsics.checkParameterIsNotNull(paramT1, "$receiver");
    if ((paramT2 != null) && (paramT3 != null))
    {
      if (paramT2.compareTo(paramT3) > 0)
      {
        paramT1 = new StringBuilder();
        paramT1.append("Cannot coerce value to an empty range: maximum ");
        paramT1.append(paramT3);
        paramT1.append(" is less than minimum ");
        paramT1.append(paramT2);
        paramT1.append('.');
        throw ((Throwable)new IllegalArgumentException(paramT1.toString()));
      }
      if (paramT1.compareTo(paramT2) < 0) {
        return paramT2;
      }
      if (paramT1.compareTo(paramT3) > 0) {
        return paramT3;
      }
    }
    else
    {
      if ((paramT2 != null) && (paramT1.compareTo(paramT2) < 0)) {
        return paramT2;
      }
      if ((paramT3 != null) && (paramT1.compareTo(paramT3) > 0)) {
        return paramT3;
      }
    }
    return paramT1;
  }
  
  @SinceKotlin(version="1.1")
  @NotNull
  public static final <T extends Comparable<? super T>> T coerceIn(@NotNull T paramT, @NotNull ClosedFloatingPointRange<T> paramClosedFloatingPointRange)
  {
    Intrinsics.checkParameterIsNotNull(paramT, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramClosedFloatingPointRange, "range");
    if (paramClosedFloatingPointRange.isEmpty())
    {
      paramT = new StringBuilder();
      paramT.append("Cannot coerce value to an empty range: ");
      paramT.append(paramClosedFloatingPointRange);
      paramT.append('.');
      throw ((Throwable)new IllegalArgumentException(paramT.toString()));
    }
    if ((paramClosedFloatingPointRange.lessThanOrEquals(paramT, paramClosedFloatingPointRange.getStart())) && (!paramClosedFloatingPointRange.lessThanOrEquals(paramClosedFloatingPointRange.getStart(), paramT))) {
      return paramClosedFloatingPointRange.getStart();
    }
    Object localObject = paramT;
    if (paramClosedFloatingPointRange.lessThanOrEquals(paramClosedFloatingPointRange.getEndInclusive(), paramT))
    {
      localObject = paramT;
      if (!paramClosedFloatingPointRange.lessThanOrEquals(paramT, paramClosedFloatingPointRange.getEndInclusive())) {
        localObject = paramClosedFloatingPointRange.getEndInclusive();
      }
    }
    return localObject;
  }
  
  @NotNull
  public static final <T extends Comparable<? super T>> T coerceIn(@NotNull T paramT, @NotNull ClosedRange<T> paramClosedRange)
  {
    Intrinsics.checkParameterIsNotNull(paramT, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramClosedRange, "range");
    if ((paramClosedRange instanceof ClosedFloatingPointRange)) {
      return RangesKt.coerceIn(paramT, (ClosedFloatingPointRange)paramClosedRange);
    }
    if (paramClosedRange.isEmpty())
    {
      paramT = new StringBuilder();
      paramT.append("Cannot coerce value to an empty range: ");
      paramT.append(paramClosedRange);
      paramT.append('.');
      throw ((Throwable)new IllegalArgumentException(paramT.toString()));
    }
    if (paramT.compareTo(paramClosedRange.getStart()) < 0) {
      return paramClosedRange.getStart();
    }
    Object localObject = paramT;
    if (paramT.compareTo(paramClosedRange.getEndInclusive()) > 0) {
      localObject = paramClosedRange.getEndInclusive();
    }
    return localObject;
  }
  
  public static final short coerceIn(short paramShort1, short paramShort2, short paramShort3)
  {
    if (paramShort2 > paramShort3)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Cannot coerce value to an empty range: maximum ");
      localStringBuilder.append(paramShort3);
      localStringBuilder.append(" is less than minimum ");
      localStringBuilder.append(paramShort2);
      localStringBuilder.append('.');
      throw ((Throwable)new IllegalArgumentException(localStringBuilder.toString()));
    }
    if (paramShort1 < paramShort2) {
      return paramShort2;
    }
    if (paramShort1 > paramShort3) {
      return paramShort3;
    }
    return paramShort1;
  }
  
  @JvmName(name="doubleRangeContains")
  public static final boolean doubleRangeContains(@NotNull ClosedRange<Double> paramClosedRange, byte paramByte)
  {
    Intrinsics.checkParameterIsNotNull(paramClosedRange, "$receiver");
    return paramClosedRange.contains((Comparable)Double.valueOf(paramByte));
  }
  
  @JvmName(name="doubleRangeContains")
  public static final boolean doubleRangeContains(@NotNull ClosedRange<Double> paramClosedRange, float paramFloat)
  {
    Intrinsics.checkParameterIsNotNull(paramClosedRange, "$receiver");
    return paramClosedRange.contains((Comparable)Double.valueOf(paramFloat));
  }
  
  @JvmName(name="doubleRangeContains")
  public static final boolean doubleRangeContains(@NotNull ClosedRange<Double> paramClosedRange, int paramInt)
  {
    Intrinsics.checkParameterIsNotNull(paramClosedRange, "$receiver");
    return paramClosedRange.contains((Comparable)Double.valueOf(paramInt));
  }
  
  @JvmName(name="doubleRangeContains")
  public static final boolean doubleRangeContains(@NotNull ClosedRange<Double> paramClosedRange, long paramLong)
  {
    Intrinsics.checkParameterIsNotNull(paramClosedRange, "$receiver");
    return paramClosedRange.contains((Comparable)Double.valueOf(paramLong));
  }
  
  @JvmName(name="doubleRangeContains")
  public static final boolean doubleRangeContains(@NotNull ClosedRange<Double> paramClosedRange, short paramShort)
  {
    Intrinsics.checkParameterIsNotNull(paramClosedRange, "$receiver");
    return paramClosedRange.contains((Comparable)Double.valueOf(paramShort));
  }
  
  @NotNull
  public static final CharProgression downTo(char paramChar1, char paramChar2)
  {
    return CharProgression.Companion.fromClosedRange(paramChar1, paramChar2, -1);
  }
  
  @NotNull
  public static final IntProgression downTo(byte paramByte1, byte paramByte2)
  {
    return IntProgression.Companion.fromClosedRange(paramByte1, paramByte2, -1);
  }
  
  @NotNull
  public static final IntProgression downTo(byte paramByte, int paramInt)
  {
    return IntProgression.Companion.fromClosedRange(paramByte, paramInt, -1);
  }
  
  @NotNull
  public static final IntProgression downTo(byte paramByte, short paramShort)
  {
    return IntProgression.Companion.fromClosedRange(paramByte, paramShort, -1);
  }
  
  @NotNull
  public static final IntProgression downTo(int paramInt, byte paramByte)
  {
    return IntProgression.Companion.fromClosedRange(paramInt, paramByte, -1);
  }
  
  @NotNull
  public static final IntProgression downTo(int paramInt1, int paramInt2)
  {
    return IntProgression.Companion.fromClosedRange(paramInt1, paramInt2, -1);
  }
  
  @NotNull
  public static final IntProgression downTo(int paramInt, short paramShort)
  {
    return IntProgression.Companion.fromClosedRange(paramInt, paramShort, -1);
  }
  
  @NotNull
  public static final IntProgression downTo(short paramShort, byte paramByte)
  {
    return IntProgression.Companion.fromClosedRange(paramShort, paramByte, -1);
  }
  
  @NotNull
  public static final IntProgression downTo(short paramShort, int paramInt)
  {
    return IntProgression.Companion.fromClosedRange(paramShort, paramInt, -1);
  }
  
  @NotNull
  public static final IntProgression downTo(short paramShort1, short paramShort2)
  {
    return IntProgression.Companion.fromClosedRange(paramShort1, paramShort2, -1);
  }
  
  @NotNull
  public static final LongProgression downTo(byte paramByte, long paramLong)
  {
    return LongProgression.Companion.fromClosedRange(paramByte, paramLong, -1L);
  }
  
  @NotNull
  public static final LongProgression downTo(int paramInt, long paramLong)
  {
    return LongProgression.Companion.fromClosedRange(paramInt, paramLong, -1L);
  }
  
  @NotNull
  public static final LongProgression downTo(long paramLong, byte paramByte)
  {
    return LongProgression.Companion.fromClosedRange(paramLong, paramByte, -1L);
  }
  
  @NotNull
  public static final LongProgression downTo(long paramLong, int paramInt)
  {
    return LongProgression.Companion.fromClosedRange(paramLong, paramInt, -1L);
  }
  
  @NotNull
  public static final LongProgression downTo(long paramLong1, long paramLong2)
  {
    return LongProgression.Companion.fromClosedRange(paramLong1, paramLong2, -1L);
  }
  
  @NotNull
  public static final LongProgression downTo(long paramLong, short paramShort)
  {
    return LongProgression.Companion.fromClosedRange(paramLong, paramShort, -1L);
  }
  
  @NotNull
  public static final LongProgression downTo(short paramShort, long paramLong)
  {
    return LongProgression.Companion.fromClosedRange(paramShort, paramLong, -1L);
  }
  
  @JvmName(name="floatRangeContains")
  public static final boolean floatRangeContains(@NotNull ClosedRange<Float> paramClosedRange, byte paramByte)
  {
    Intrinsics.checkParameterIsNotNull(paramClosedRange, "$receiver");
    return paramClosedRange.contains((Comparable)Float.valueOf(paramByte));
  }
  
  @JvmName(name="floatRangeContains")
  public static final boolean floatRangeContains(@NotNull ClosedRange<Float> paramClosedRange, double paramDouble)
  {
    Intrinsics.checkParameterIsNotNull(paramClosedRange, "$receiver");
    return paramClosedRange.contains((Comparable)Float.valueOf((float)paramDouble));
  }
  
  @JvmName(name="floatRangeContains")
  public static final boolean floatRangeContains(@NotNull ClosedRange<Float> paramClosedRange, int paramInt)
  {
    Intrinsics.checkParameterIsNotNull(paramClosedRange, "$receiver");
    return paramClosedRange.contains((Comparable)Float.valueOf(paramInt));
  }
  
  @JvmName(name="floatRangeContains")
  public static final boolean floatRangeContains(@NotNull ClosedRange<Float> paramClosedRange, long paramLong)
  {
    Intrinsics.checkParameterIsNotNull(paramClosedRange, "$receiver");
    return paramClosedRange.contains((Comparable)Float.valueOf((float)paramLong));
  }
  
  @JvmName(name="floatRangeContains")
  public static final boolean floatRangeContains(@NotNull ClosedRange<Float> paramClosedRange, short paramShort)
  {
    Intrinsics.checkParameterIsNotNull(paramClosedRange, "$receiver");
    return paramClosedRange.contains((Comparable)Float.valueOf(paramShort));
  }
  
  @JvmName(name="intRangeContains")
  public static final boolean intRangeContains(@NotNull ClosedRange<Integer> paramClosedRange, byte paramByte)
  {
    Intrinsics.checkParameterIsNotNull(paramClosedRange, "$receiver");
    return paramClosedRange.contains((Comparable)Integer.valueOf(paramByte));
  }
  
  @JvmName(name="intRangeContains")
  public static final boolean intRangeContains(@NotNull ClosedRange<Integer> paramClosedRange, double paramDouble)
  {
    Intrinsics.checkParameterIsNotNull(paramClosedRange, "$receiver");
    Integer localInteger = RangesKt.toIntExactOrNull(paramDouble);
    if (localInteger != null) {
      return paramClosedRange.contains((Comparable)localInteger);
    }
    return false;
  }
  
  @JvmName(name="intRangeContains")
  public static final boolean intRangeContains(@NotNull ClosedRange<Integer> paramClosedRange, float paramFloat)
  {
    Intrinsics.checkParameterIsNotNull(paramClosedRange, "$receiver");
    Integer localInteger = RangesKt.toIntExactOrNull(paramFloat);
    if (localInteger != null) {
      return paramClosedRange.contains((Comparable)localInteger);
    }
    return false;
  }
  
  @JvmName(name="intRangeContains")
  public static final boolean intRangeContains(@NotNull ClosedRange<Integer> paramClosedRange, long paramLong)
  {
    Intrinsics.checkParameterIsNotNull(paramClosedRange, "$receiver");
    Integer localInteger = RangesKt.toIntExactOrNull(paramLong);
    if (localInteger != null) {
      return paramClosedRange.contains((Comparable)localInteger);
    }
    return false;
  }
  
  @JvmName(name="intRangeContains")
  public static final boolean intRangeContains(@NotNull ClosedRange<Integer> paramClosedRange, short paramShort)
  {
    Intrinsics.checkParameterIsNotNull(paramClosedRange, "$receiver");
    return paramClosedRange.contains((Comparable)Integer.valueOf(paramShort));
  }
  
  @JvmName(name="longRangeContains")
  public static final boolean longRangeContains(@NotNull ClosedRange<Long> paramClosedRange, byte paramByte)
  {
    Intrinsics.checkParameterIsNotNull(paramClosedRange, "$receiver");
    return paramClosedRange.contains((Comparable)Long.valueOf(paramByte));
  }
  
  @JvmName(name="longRangeContains")
  public static final boolean longRangeContains(@NotNull ClosedRange<Long> paramClosedRange, double paramDouble)
  {
    Intrinsics.checkParameterIsNotNull(paramClosedRange, "$receiver");
    Long localLong = RangesKt.toLongExactOrNull(paramDouble);
    if (localLong != null) {
      return paramClosedRange.contains((Comparable)localLong);
    }
    return false;
  }
  
  @JvmName(name="longRangeContains")
  public static final boolean longRangeContains(@NotNull ClosedRange<Long> paramClosedRange, float paramFloat)
  {
    Intrinsics.checkParameterIsNotNull(paramClosedRange, "$receiver");
    Long localLong = RangesKt.toLongExactOrNull(paramFloat);
    if (localLong != null) {
      return paramClosedRange.contains((Comparable)localLong);
    }
    return false;
  }
  
  @JvmName(name="longRangeContains")
  public static final boolean longRangeContains(@NotNull ClosedRange<Long> paramClosedRange, int paramInt)
  {
    Intrinsics.checkParameterIsNotNull(paramClosedRange, "$receiver");
    return paramClosedRange.contains((Comparable)Long.valueOf(paramInt));
  }
  
  @JvmName(name="longRangeContains")
  public static final boolean longRangeContains(@NotNull ClosedRange<Long> paramClosedRange, short paramShort)
  {
    Intrinsics.checkParameterIsNotNull(paramClosedRange, "$receiver");
    return paramClosedRange.contains((Comparable)Long.valueOf(paramShort));
  }
  
  @NotNull
  public static final CharProgression reversed(@NotNull CharProgression paramCharProgression)
  {
    Intrinsics.checkParameterIsNotNull(paramCharProgression, "$receiver");
    return CharProgression.Companion.fromClosedRange(paramCharProgression.getLast(), paramCharProgression.getFirst(), -paramCharProgression.getStep());
  }
  
  @NotNull
  public static final IntProgression reversed(@NotNull IntProgression paramIntProgression)
  {
    Intrinsics.checkParameterIsNotNull(paramIntProgression, "$receiver");
    return IntProgression.Companion.fromClosedRange(paramIntProgression.getLast(), paramIntProgression.getFirst(), -paramIntProgression.getStep());
  }
  
  @NotNull
  public static final LongProgression reversed(@NotNull LongProgression paramLongProgression)
  {
    Intrinsics.checkParameterIsNotNull(paramLongProgression, "$receiver");
    return LongProgression.Companion.fromClosedRange(paramLongProgression.getLast(), paramLongProgression.getFirst(), -paramLongProgression.getStep());
  }
  
  @JvmName(name="shortRangeContains")
  public static final boolean shortRangeContains(@NotNull ClosedRange<Short> paramClosedRange, byte paramByte)
  {
    Intrinsics.checkParameterIsNotNull(paramClosedRange, "$receiver");
    return paramClosedRange.contains((Comparable)Short.valueOf((short)paramByte));
  }
  
  @JvmName(name="shortRangeContains")
  public static final boolean shortRangeContains(@NotNull ClosedRange<Short> paramClosedRange, double paramDouble)
  {
    Intrinsics.checkParameterIsNotNull(paramClosedRange, "$receiver");
    Short localShort = RangesKt.toShortExactOrNull(paramDouble);
    if (localShort != null) {
      return paramClosedRange.contains((Comparable)localShort);
    }
    return false;
  }
  
  @JvmName(name="shortRangeContains")
  public static final boolean shortRangeContains(@NotNull ClosedRange<Short> paramClosedRange, float paramFloat)
  {
    Intrinsics.checkParameterIsNotNull(paramClosedRange, "$receiver");
    Short localShort = RangesKt.toShortExactOrNull(paramFloat);
    if (localShort != null) {
      return paramClosedRange.contains((Comparable)localShort);
    }
    return false;
  }
  
  @JvmName(name="shortRangeContains")
  public static final boolean shortRangeContains(@NotNull ClosedRange<Short> paramClosedRange, int paramInt)
  {
    Intrinsics.checkParameterIsNotNull(paramClosedRange, "$receiver");
    Short localShort = RangesKt.toShortExactOrNull(paramInt);
    if (localShort != null) {
      return paramClosedRange.contains((Comparable)localShort);
    }
    return false;
  }
  
  @JvmName(name="shortRangeContains")
  public static final boolean shortRangeContains(@NotNull ClosedRange<Short> paramClosedRange, long paramLong)
  {
    Intrinsics.checkParameterIsNotNull(paramClosedRange, "$receiver");
    Short localShort = RangesKt.toShortExactOrNull(paramLong);
    if (localShort != null) {
      return paramClosedRange.contains((Comparable)localShort);
    }
    return false;
  }
  
  @NotNull
  public static final CharProgression step(@NotNull CharProgression paramCharProgression, int paramInt)
  {
    Intrinsics.checkParameterIsNotNull(paramCharProgression, "$receiver");
    boolean bool;
    if (paramInt > 0) {
      bool = true;
    } else {
      bool = false;
    }
    RangesKt.checkStepIsPositive(bool, (Number)Integer.valueOf(paramInt));
    CharProgression.Companion localCompanion = CharProgression.Companion;
    char c1 = paramCharProgression.getFirst();
    char c2 = paramCharProgression.getLast();
    if (paramCharProgression.getStep() <= 0) {
      paramInt = -paramInt;
    }
    return localCompanion.fromClosedRange(c1, c2, paramInt);
  }
  
  @NotNull
  public static final IntProgression step(@NotNull IntProgression paramIntProgression, int paramInt)
  {
    Intrinsics.checkParameterIsNotNull(paramIntProgression, "$receiver");
    boolean bool;
    if (paramInt > 0) {
      bool = true;
    } else {
      bool = false;
    }
    RangesKt.checkStepIsPositive(bool, (Number)Integer.valueOf(paramInt));
    IntProgression.Companion localCompanion = IntProgression.Companion;
    int i = paramIntProgression.getFirst();
    int j = paramIntProgression.getLast();
    if (paramIntProgression.getStep() <= 0) {
      paramInt = -paramInt;
    }
    return localCompanion.fromClosedRange(i, j, paramInt);
  }
  
  @NotNull
  public static final LongProgression step(@NotNull LongProgression paramLongProgression, long paramLong)
  {
    Intrinsics.checkParameterIsNotNull(paramLongProgression, "$receiver");
    boolean bool;
    if (paramLong > 0L) {
      bool = true;
    } else {
      bool = false;
    }
    RangesKt.checkStepIsPositive(bool, (Number)Long.valueOf(paramLong));
    LongProgression.Companion localCompanion = LongProgression.Companion;
    long l1 = paramLongProgression.getFirst();
    long l2 = paramLongProgression.getLast();
    if (paramLongProgression.getStep() <= 0L) {
      for (;;)
      {
        paramLong = -paramLong;
      }
    }
    return localCompanion.fromClosedRange(l1, l2, paramLong);
  }
  
  @Nullable
  public static final Byte toByteExactOrNull(double paramDouble)
  {
    double d1 = Byte.MIN_VALUE;
    double d2 = 127;
    if ((paramDouble >= d1) && (paramDouble <= d2)) {
      return Byte.valueOf((byte)(int)paramDouble);
    }
    return null;
  }
  
  @Nullable
  public static final Byte toByteExactOrNull(float paramFloat)
  {
    float f1 = Byte.MIN_VALUE;
    float f2 = 127;
    if ((paramFloat >= f1) && (paramFloat <= f2)) {
      return Byte.valueOf((byte)(int)paramFloat);
    }
    return null;
  }
  
  @Nullable
  public static final Byte toByteExactOrNull(int paramInt)
  {
    if ((-128 <= paramInt) && (127 >= paramInt)) {
      return Byte.valueOf((byte)paramInt);
    }
    return null;
  }
  
  @Nullable
  public static final Byte toByteExactOrNull(long paramLong)
  {
    long l1 = Byte.MIN_VALUE;
    long l2 = 127;
    if ((l1 <= paramLong) && (l2 >= paramLong)) {
      return Byte.valueOf((byte)(int)paramLong);
    }
    return null;
  }
  
  @Nullable
  public static final Byte toByteExactOrNull(short paramShort)
  {
    short s1 = (short)Byte.MIN_VALUE;
    short s2 = (short)127;
    if ((s1 <= paramShort) && (s2 >= paramShort)) {
      return Byte.valueOf((byte)paramShort);
    }
    return null;
  }
  
  @Nullable
  public static final Integer toIntExactOrNull(double paramDouble)
  {
    double d1 = Integer.MIN_VALUE;
    double d2 = Integer.MAX_VALUE;
    if ((paramDouble >= d1) && (paramDouble <= d2)) {
      return Integer.valueOf((int)paramDouble);
    }
    return null;
  }
  
  @Nullable
  public static final Integer toIntExactOrNull(float paramFloat)
  {
    float f1 = Integer.MIN_VALUE;
    float f2 = Integer.MAX_VALUE;
    if ((paramFloat >= f1) && (paramFloat <= f2)) {
      return Integer.valueOf((int)paramFloat);
    }
    return null;
  }
  
  @Nullable
  public static final Integer toIntExactOrNull(long paramLong)
  {
    long l1 = Integer.MIN_VALUE;
    long l2 = Integer.MAX_VALUE;
    if ((l1 <= paramLong) && (l2 >= paramLong)) {
      return Integer.valueOf((int)paramLong);
    }
    return null;
  }
  
  @Nullable
  public static final Long toLongExactOrNull(double paramDouble)
  {
    double d1 = Long.MIN_VALUE;
    double d2 = Long.MAX_VALUE;
    if ((paramDouble >= d1) && (paramDouble <= d2)) {
      return Long.valueOf(paramDouble);
    }
    return null;
  }
  
  @Nullable
  public static final Long toLongExactOrNull(float paramFloat)
  {
    float f1 = (float)Long.MIN_VALUE;
    float f2 = (float)Long.MAX_VALUE;
    if ((paramFloat >= f1) && (paramFloat <= f2)) {
      return Long.valueOf(paramFloat);
    }
    return null;
  }
  
  @Nullable
  public static final Short toShortExactOrNull(double paramDouble)
  {
    double d1 = '耀';
    double d2 = '翿';
    if ((paramDouble >= d1) && (paramDouble <= d2)) {
      return Short.valueOf((short)(int)paramDouble);
    }
    return null;
  }
  
  @Nullable
  public static final Short toShortExactOrNull(float paramFloat)
  {
    float f1 = '耀';
    float f2 = '翿';
    if ((paramFloat >= f1) && (paramFloat <= f2)) {
      return Short.valueOf((short)(int)paramFloat);
    }
    return null;
  }
  
  @Nullable
  public static final Short toShortExactOrNull(int paramInt)
  {
    if ((32768 <= paramInt) && (32767 >= paramInt)) {
      return Short.valueOf((short)paramInt);
    }
    return null;
  }
  
  @Nullable
  public static final Short toShortExactOrNull(long paramLong)
  {
    long l1 = '耀';
    long l2 = '翿';
    if ((l1 <= paramLong) && (l2 >= paramLong)) {
      return Short.valueOf((short)(int)paramLong);
    }
    return null;
  }
  
  @NotNull
  public static final CharRange until(char paramChar1, char paramChar2)
  {
    if (paramChar2 <= 0) {
      return CharRange.Companion.getEMPTY();
    }
    return new CharRange(paramChar1, (char)(paramChar2 - '\001'));
  }
  
  @NotNull
  public static final IntRange until(byte paramByte1, byte paramByte2)
  {
    return new IntRange(paramByte1, paramByte2 - 1);
  }
  
  @NotNull
  public static final IntRange until(byte paramByte, int paramInt)
  {
    if (paramInt <= Integer.MIN_VALUE) {
      return IntRange.Companion.getEMPTY();
    }
    return new IntRange(paramByte, paramInt - 1);
  }
  
  @NotNull
  public static final IntRange until(byte paramByte, short paramShort)
  {
    return new IntRange(paramByte, paramShort - 1);
  }
  
  @NotNull
  public static final IntRange until(int paramInt, byte paramByte)
  {
    return new IntRange(paramInt, paramByte - 1);
  }
  
  @NotNull
  public static final IntRange until(int paramInt1, int paramInt2)
  {
    if (paramInt2 <= Integer.MIN_VALUE) {
      return IntRange.Companion.getEMPTY();
    }
    return new IntRange(paramInt1, paramInt2 - 1);
  }
  
  @NotNull
  public static final IntRange until(int paramInt, short paramShort)
  {
    return new IntRange(paramInt, paramShort - 1);
  }
  
  @NotNull
  public static final IntRange until(short paramShort, byte paramByte)
  {
    return new IntRange(paramShort, paramByte - 1);
  }
  
  @NotNull
  public static final IntRange until(short paramShort, int paramInt)
  {
    if (paramInt <= Integer.MIN_VALUE) {
      return IntRange.Companion.getEMPTY();
    }
    return new IntRange(paramShort, paramInt - 1);
  }
  
  @NotNull
  public static final IntRange until(short paramShort1, short paramShort2)
  {
    return new IntRange(paramShort1, paramShort2 - 1);
  }
  
  @NotNull
  public static final LongRange until(byte paramByte, long paramLong)
  {
    if (paramLong <= Long.MIN_VALUE) {
      return LongRange.Companion.getEMPTY();
    }
    return new LongRange(paramByte, paramLong - 1L);
  }
  
  @NotNull
  public static final LongRange until(int paramInt, long paramLong)
  {
    if (paramLong <= Long.MIN_VALUE) {
      return LongRange.Companion.getEMPTY();
    }
    return new LongRange(paramInt, paramLong - 1L);
  }
  
  @NotNull
  public static final LongRange until(long paramLong, byte paramByte)
  {
    return new LongRange(paramLong, paramByte - 1L);
  }
  
  @NotNull
  public static final LongRange until(long paramLong, int paramInt)
  {
    return new LongRange(paramLong, paramInt - 1L);
  }
  
  @NotNull
  public static final LongRange until(long paramLong1, long paramLong2)
  {
    if (paramLong2 <= Long.MIN_VALUE) {
      return LongRange.Companion.getEMPTY();
    }
    return new LongRange(paramLong1, paramLong2 - 1L);
  }
  
  @NotNull
  public static final LongRange until(long paramLong, short paramShort)
  {
    return new LongRange(paramLong, paramShort - 1L);
  }
  
  @NotNull
  public static final LongRange until(short paramShort, long paramLong)
  {
    if (paramLong <= Long.MIN_VALUE) {
      return LongRange.Companion.getEMPTY();
    }
    return new LongRange(paramShort, paramLong - 1L);
  }
}
