package kotlin.jvm.internal;

import kotlin.Metadata;
import kotlin.collections.BooleanIterator;
import kotlin.collections.ByteIterator;
import kotlin.collections.CharIterator;
import kotlin.collections.DoubleIterator;
import kotlin.collections.FloatIterator;
import kotlin.collections.IntIterator;
import kotlin.collections.LongIterator;
import kotlin.collections.ShortIterator;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000F\n\000\n\002\030\002\n\000\n\002\020\030\n\002\030\002\n\002\020\022\n\002\030\002\n\002\020\031\n\002\030\002\n\002\020\023\n\002\030\002\n\002\020\024\n\002\030\002\n\002\020\025\n\002\030\002\n\002\020\026\n\002\030\002\n\002\020\027\n\000\032\016\020\000\032\0020\0012\006\020\002\032\0020\003\032\016\020\000\032\0020\0042\006\020\002\032\0020\005\032\016\020\000\032\0020\0062\006\020\002\032\0020\007\032\016\020\000\032\0020\b2\006\020\002\032\0020\t\032\016\020\000\032\0020\n2\006\020\002\032\0020\013\032\016\020\000\032\0020\f2\006\020\002\032\0020\r\032\016\020\000\032\0020\0162\006\020\002\032\0020\017\032\016\020\000\032\0020\0202\006\020\002\032\0020\021¨\006\022"}, d2={"iterator", "Lkotlin/collections/BooleanIterator;", "array", "", "Lkotlin/collections/ByteIterator;", "", "Lkotlin/collections/CharIterator;", "", "Lkotlin/collections/DoubleIterator;", "", "Lkotlin/collections/FloatIterator;", "", "Lkotlin/collections/IntIterator;", "", "Lkotlin/collections/LongIterator;", "", "Lkotlin/collections/ShortIterator;", "", "kotlin-runtime"}, k=2, mv={1, 1, 9})
public final class ArrayIteratorsKt
{
  @NotNull
  public static final BooleanIterator iterator(@NotNull boolean[] paramArrayOfBoolean)
  {
    Intrinsics.checkParameterIsNotNull(paramArrayOfBoolean, "array");
    return (BooleanIterator)new ArrayBooleanIterator(paramArrayOfBoolean);
  }
  
  @NotNull
  public static final ByteIterator iterator(@NotNull byte[] paramArrayOfByte)
  {
    Intrinsics.checkParameterIsNotNull(paramArrayOfByte, "array");
    return (ByteIterator)new ArrayByteIterator(paramArrayOfByte);
  }
  
  @NotNull
  public static final CharIterator iterator(@NotNull char[] paramArrayOfChar)
  {
    Intrinsics.checkParameterIsNotNull(paramArrayOfChar, "array");
    return (CharIterator)new ArrayCharIterator(paramArrayOfChar);
  }
  
  @NotNull
  public static final DoubleIterator iterator(@NotNull double[] paramArrayOfDouble)
  {
    Intrinsics.checkParameterIsNotNull(paramArrayOfDouble, "array");
    return (DoubleIterator)new ArrayDoubleIterator(paramArrayOfDouble);
  }
  
  @NotNull
  public static final FloatIterator iterator(@NotNull float[] paramArrayOfFloat)
  {
    Intrinsics.checkParameterIsNotNull(paramArrayOfFloat, "array");
    return (FloatIterator)new ArrayFloatIterator(paramArrayOfFloat);
  }
  
  @NotNull
  public static final IntIterator iterator(@NotNull int[] paramArrayOfInt)
  {
    Intrinsics.checkParameterIsNotNull(paramArrayOfInt, "array");
    return (IntIterator)new ArrayIntIterator(paramArrayOfInt);
  }
  
  @NotNull
  public static final LongIterator iterator(@NotNull long[] paramArrayOfLong)
  {
    Intrinsics.checkParameterIsNotNull(paramArrayOfLong, "array");
    return (LongIterator)new ArrayLongIterator(paramArrayOfLong);
  }
  
  @NotNull
  public static final ShortIterator iterator(@NotNull short[] paramArrayOfShort)
  {
    Intrinsics.checkParameterIsNotNull(paramArrayOfShort, "array");
    return (ShortIterator)new ArrayShortIterator(paramArrayOfShort);
  }
}
