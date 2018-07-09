package kotlin.text;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000\\\n\002\b\003\n\002\020\016\n\000\n\002\030\002\n\002\b\003\n\002\030\002\n\000\n\002\030\002\n\002\b\002\n\002\030\002\n\000\n\002\020\b\n\002\b\002\n\002\020\013\n\000\n\002\020\005\n\002\b\004\n\002\020\006\n\002\b\003\n\002\020\007\n\002\b\007\n\002\020\t\n\002\b\004\n\002\020\n\n\002\b\005\0324\020\000\032\004\030\001H\001\"\004\b\000\020\0012\006\020\002\032\0020\0032\022\020\004\032\016\022\004\022\0020\003\022\004\022\002H\0010\005H\b¢\006\004\b\006\020\007\032\r\020\b\032\0020\t*\0020\003H\b\032\025\020\b\032\0020\t*\0020\0032\006\020\n\032\0020\013H\b\032\016\020\f\032\004\030\0010\t*\0020\003H\007\032\026\020\f\032\004\030\0010\t*\0020\0032\006\020\n\032\0020\013H\007\032\r\020\r\032\0020\016*\0020\003H\b\032\025\020\r\032\0020\016*\0020\0032\006\020\017\032\0020\020H\b\032\016\020\021\032\004\030\0010\016*\0020\003H\007\032\026\020\021\032\004\030\0010\016*\0020\0032\006\020\017\032\0020\020H\007\032\r\020\022\032\0020\023*\0020\003H\b\032\r\020\024\032\0020\025*\0020\003H\b\032\025\020\024\032\0020\025*\0020\0032\006\020\017\032\0020\020H\b\032\023\020\026\032\004\030\0010\025*\0020\003H\007¢\006\002\020\027\032\033\020\026\032\004\030\0010\025*\0020\0032\006\020\017\032\0020\020H\007¢\006\002\020\030\032\r\020\031\032\0020\032*\0020\003H\b\032\023\020\033\032\004\030\0010\032*\0020\003H\007¢\006\002\020\034\032\r\020\035\032\0020\036*\0020\003H\b\032\023\020\037\032\004\030\0010\036*\0020\003H\007¢\006\002\020 \032\r\020!\032\0020\020*\0020\003H\b\032\025\020!\032\0020\020*\0020\0032\006\020\017\032\0020\020H\b\032\023\020\"\032\004\030\0010\020*\0020\003H\007¢\006\002\020#\032\033\020\"\032\004\030\0010\020*\0020\0032\006\020\017\032\0020\020H\007¢\006\002\020$\032\r\020%\032\0020&*\0020\003H\b\032\025\020%\032\0020&*\0020\0032\006\020\017\032\0020\020H\b\032\023\020'\032\004\030\0010&*\0020\003H\007¢\006\002\020(\032\033\020'\032\004\030\0010&*\0020\0032\006\020\017\032\0020\020H\007¢\006\002\020)\032\r\020*\032\0020+*\0020\003H\b\032\025\020*\032\0020+*\0020\0032\006\020\017\032\0020\020H\b\032\023\020,\032\004\030\0010+*\0020\003H\007¢\006\002\020-\032\033\020,\032\004\030\0010+*\0020\0032\006\020\017\032\0020\020H\007¢\006\002\020.\032\025\020/\032\0020\003*\0020\0252\006\020\017\032\0020\020H\b\032\025\020/\032\0020\003*\0020\0202\006\020\017\032\0020\020H\b\032\025\020/\032\0020\003*\0020&2\006\020\017\032\0020\020H\b\032\025\020/\032\0020\003*\0020+2\006\020\017\032\0020\020H\b¨\0060"}, d2={"screenFloatValue", "T", "str", "", "parse", "Lkotlin/Function1;", "screenFloatValue$StringsKt__StringNumberConversionsKt", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "toBigDecimal", "Ljava/math/BigDecimal;", "mathContext", "Ljava/math/MathContext;", "toBigDecimalOrNull", "toBigInteger", "Ljava/math/BigInteger;", "radix", "", "toBigIntegerOrNull", "toBoolean", "", "toByte", "", "toByteOrNull", "(Ljava/lang/String;)Ljava/lang/Byte;", "(Ljava/lang/String;I)Ljava/lang/Byte;", "toDouble", "", "toDoubleOrNull", "(Ljava/lang/String;)Ljava/lang/Double;", "toFloat", "", "toFloatOrNull", "(Ljava/lang/String;)Ljava/lang/Float;", "toInt", "toIntOrNull", "(Ljava/lang/String;)Ljava/lang/Integer;", "(Ljava/lang/String;I)Ljava/lang/Integer;", "toLong", "", "toLongOrNull", "(Ljava/lang/String;)Ljava/lang/Long;", "(Ljava/lang/String;I)Ljava/lang/Long;", "toShort", "", "toShortOrNull", "(Ljava/lang/String;)Ljava/lang/Short;", "(Ljava/lang/String;I)Ljava/lang/Short;", "toString", "kotlin-stdlib"}, k=5, mv={1, 1, 9}, xi=1, xs="kotlin/text/StringsKt")
class StringsKt__StringNumberConversionsKt
  extends StringsKt__StringBuilderKt
{
  public StringsKt__StringNumberConversionsKt() {}
  
  private static final <T> T screenFloatValue$StringsKt__StringNumberConversionsKt(String paramString, Function1<? super String, ? extends T> paramFunction1)
  {
    Object localObject = null;
    try
    {
      if (ScreenFloatValueRegEx.value.matches((CharSequence)paramString)) {
        localObject = paramFunction1.invoke(paramString);
      }
      return localObject;
    }
    catch (NumberFormatException paramString) {}
    return null;
  }
  
  @SinceKotlin(version="1.2")
  @InlineOnly
  private static final BigDecimal toBigDecimal(@NotNull String paramString)
  {
    return new BigDecimal(paramString);
  }
  
  @SinceKotlin(version="1.2")
  @InlineOnly
  private static final BigDecimal toBigDecimal(@NotNull String paramString, MathContext paramMathContext)
  {
    return new BigDecimal(paramString, paramMathContext);
  }
  
  @SinceKotlin(version="1.2")
  @Nullable
  public static final BigDecimal toBigDecimalOrNull(@NotNull String paramString)
  {
    Intrinsics.checkParameterIsNotNull(paramString, "$receiver");
    BigDecimal localBigDecimal = null;
    try
    {
      if (ScreenFloatValueRegEx.value.matches((CharSequence)paramString)) {
        localBigDecimal = new BigDecimal(paramString);
      }
      return localBigDecimal;
    }
    catch (NumberFormatException paramString) {}
    return null;
  }
  
  @SinceKotlin(version="1.2")
  @Nullable
  public static final BigDecimal toBigDecimalOrNull(@NotNull String paramString, @NotNull MathContext paramMathContext)
  {
    Intrinsics.checkParameterIsNotNull(paramString, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramMathContext, "mathContext");
    BigDecimal localBigDecimal = null;
    try
    {
      if (ScreenFloatValueRegEx.value.matches((CharSequence)paramString)) {
        localBigDecimal = new BigDecimal(paramString, paramMathContext);
      }
      return localBigDecimal;
    }
    catch (NumberFormatException paramString) {}
    return null;
  }
  
  @SinceKotlin(version="1.2")
  @InlineOnly
  private static final BigInteger toBigInteger(@NotNull String paramString)
  {
    return new BigInteger(paramString);
  }
  
  @SinceKotlin(version="1.2")
  @InlineOnly
  private static final BigInteger toBigInteger(@NotNull String paramString, int paramInt)
  {
    return new BigInteger(paramString, CharsKt.checkRadix(paramInt));
  }
  
  @SinceKotlin(version="1.2")
  @Nullable
  public static final BigInteger toBigIntegerOrNull(@NotNull String paramString)
  {
    Intrinsics.checkParameterIsNotNull(paramString, "$receiver");
    return StringsKt.toBigIntegerOrNull(paramString, 10);
  }
  
  @SinceKotlin(version="1.2")
  @Nullable
  public static final BigInteger toBigIntegerOrNull(@NotNull String paramString, int paramInt)
  {
    Intrinsics.checkParameterIsNotNull(paramString, "$receiver");
    CharsKt.checkRadix(paramInt);
    int j = paramString.length();
    int i = 0;
    switch (j)
    {
    default: 
      if (paramString.charAt(0) == '-') {
        i = 1;
      }
      break;
    case 1: 
      if (CharsKt.digitOf(paramString.charAt(0), paramInt) >= 0) {
        break label97;
      }
      return null;
    case 0: 
      return null;
    }
    while (i < j)
    {
      if (CharsKt.digitOf(paramString.charAt(i), paramInt) < 0) {
        return null;
      }
      i += 1;
    }
    label97:
    return new BigInteger(paramString, CharsKt.checkRadix(paramInt));
  }
  
  @InlineOnly
  private static final boolean toBoolean(@NotNull String paramString)
  {
    return Boolean.parseBoolean(paramString);
  }
  
  @InlineOnly
  private static final byte toByte(@NotNull String paramString)
  {
    return Byte.parseByte(paramString);
  }
  
  @SinceKotlin(version="1.1")
  @InlineOnly
  private static final byte toByte(@NotNull String paramString, int paramInt)
  {
    return Byte.parseByte(paramString, CharsKt.checkRadix(paramInt));
  }
  
  @SinceKotlin(version="1.1")
  @Nullable
  public static final Byte toByteOrNull(@NotNull String paramString)
  {
    Intrinsics.checkParameterIsNotNull(paramString, "$receiver");
    return StringsKt.toByteOrNull(paramString, 10);
  }
  
  @SinceKotlin(version="1.1")
  @Nullable
  public static final Byte toByteOrNull(@NotNull String paramString, int paramInt)
  {
    Intrinsics.checkParameterIsNotNull(paramString, "$receiver");
    paramString = StringsKt.toIntOrNull(paramString, paramInt);
    if (paramString != null)
    {
      paramInt = paramString.intValue();
      if (paramInt >= -128)
      {
        if (paramInt > 127) {
          return null;
        }
        return Byte.valueOf((byte)paramInt);
      }
      return null;
    }
    return null;
  }
  
  @InlineOnly
  private static final double toDouble(@NotNull String paramString)
  {
    return Double.parseDouble(paramString);
  }
  
  @SinceKotlin(version="1.1")
  @Nullable
  public static final Double toDoubleOrNull(@NotNull String paramString)
  {
    Intrinsics.checkParameterIsNotNull(paramString, "$receiver");
    Double localDouble = null;
    try
    {
      if (ScreenFloatValueRegEx.value.matches((CharSequence)paramString))
      {
        double d = Double.parseDouble(paramString);
        localDouble = Double.valueOf(d);
      }
      return localDouble;
    }
    catch (NumberFormatException paramString) {}
    return null;
  }
  
  @InlineOnly
  private static final float toFloat(@NotNull String paramString)
  {
    return Float.parseFloat(paramString);
  }
  
  @SinceKotlin(version="1.1")
  @Nullable
  public static final Float toFloatOrNull(@NotNull String paramString)
  {
    Intrinsics.checkParameterIsNotNull(paramString, "$receiver");
    Float localFloat = null;
    try
    {
      if (ScreenFloatValueRegEx.value.matches((CharSequence)paramString))
      {
        float f = Float.parseFloat(paramString);
        localFloat = Float.valueOf(f);
      }
      return localFloat;
    }
    catch (NumberFormatException paramString) {}
    return null;
  }
  
  @InlineOnly
  private static final int toInt(@NotNull String paramString)
  {
    return Integer.parseInt(paramString);
  }
  
  @SinceKotlin(version="1.1")
  @InlineOnly
  private static final int toInt(@NotNull String paramString, int paramInt)
  {
    return Integer.parseInt(paramString, CharsKt.checkRadix(paramInt));
  }
  
  @SinceKotlin(version="1.1")
  @Nullable
  public static final Integer toIntOrNull(@NotNull String paramString)
  {
    Intrinsics.checkParameterIsNotNull(paramString, "$receiver");
    return StringsKt.toIntOrNull(paramString, 10);
  }
  
  @SinceKotlin(version="1.1")
  @Nullable
  public static final Integer toIntOrNull(@NotNull String paramString, int paramInt)
  {
    Intrinsics.checkParameterIsNotNull(paramString, "$receiver");
    CharsKt.checkRadix(paramInt);
    int i1 = paramString.length();
    if (i1 == 0) {
      return null;
    }
    int m = 0;
    int n = 0;
    int i = paramString.charAt(0);
    int j = -2147483647;
    int k;
    if (i < 48)
    {
      if (i1 == 1) {
        return null;
      }
      if (i == 45)
      {
        j = Integer.MIN_VALUE;
        i = 1;
      }
      else
      {
        if (i != 43) {
          break label83;
        }
        i = 0;
      }
      k = i;
      i = 1;
      break label90;
      label83:
      return null;
    }
    else
    {
      i = 0;
      k = i;
    }
    label90:
    int i2 = j / paramInt;
    i1 -= 1;
    if (i <= i1) {
      for (m = n;; m = n)
      {
        n = CharsKt.digitOf(paramString.charAt(i), paramInt);
        if (n < 0) {
          return null;
        }
        if (m < i2) {
          return null;
        }
        m *= paramInt;
        if (m < j + n) {
          return null;
        }
        n = m - n;
        m = n;
        if (i == i1) {
          break;
        }
        i += 1;
      }
    }
    if (k != 0) {
      return Integer.valueOf(m);
    }
    return Integer.valueOf(-m);
  }
  
  @InlineOnly
  private static final long toLong(@NotNull String paramString)
  {
    return Long.parseLong(paramString);
  }
  
  @SinceKotlin(version="1.1")
  @InlineOnly
  private static final long toLong(@NotNull String paramString, int paramInt)
  {
    return Long.parseLong(paramString, CharsKt.checkRadix(paramInt));
  }
  
  @SinceKotlin(version="1.1")
  @Nullable
  public static final Long toLongOrNull(@NotNull String paramString)
  {
    Intrinsics.checkParameterIsNotNull(paramString, "$receiver");
    return StringsKt.toLongOrNull(paramString, 10);
  }
  
  @SinceKotlin(version="1.1")
  @Nullable
  public static final Long toLongOrNull(@NotNull String paramString, int paramInt)
  {
    Intrinsics.checkParameterIsNotNull(paramString, "$receiver");
    CharsKt.checkRadix(paramInt);
    int k = paramString.length();
    if (k == 0) {
      return null;
    }
    int i = 0;
    int j = 0;
    int m = paramString.charAt(0);
    long l1 = -9223372036854775807L;
    if (m < 48)
    {
      if (k == 1) {
        return null;
      }
      if (m == 45)
      {
        l1 = Long.MIN_VALUE;
        i = 1;
      }
      else
      {
        if (m != 43) {
          break label88;
        }
        i = j;
      }
      j = i;
      i = 1;
      break label92;
      label88:
      return null;
    }
    else
    {
      j = 0;
    }
    label92:
    long l4 = paramInt;
    long l5 = l1 / l4;
    long l2 = 0L;
    k -= 1;
    long l3 = l2;
    if (i <= k)
    {
      for (;;)
      {
        m = CharsKt.digitOf(paramString.charAt(i), paramInt);
        if (m < 0) {
          return null;
        }
        if (l2 < l5) {
          return null;
        }
        l2 *= l4;
        l3 = m;
        if (l2 < l1 + l3) {
          return null;
        }
        l2 -= l3;
        if (i == k) {
          break;
        }
        i += 1;
      }
      l3 = l2;
    }
    if (j != 0) {
      return Long.valueOf(l3);
    }
    return Long.valueOf(-l3);
  }
  
  @InlineOnly
  private static final short toShort(@NotNull String paramString)
  {
    return Short.parseShort(paramString);
  }
  
  @SinceKotlin(version="1.1")
  @InlineOnly
  private static final short toShort(@NotNull String paramString, int paramInt)
  {
    return Short.parseShort(paramString, CharsKt.checkRadix(paramInt));
  }
  
  @SinceKotlin(version="1.1")
  @Nullable
  public static final Short toShortOrNull(@NotNull String paramString)
  {
    Intrinsics.checkParameterIsNotNull(paramString, "$receiver");
    return StringsKt.toShortOrNull(paramString, 10);
  }
  
  @SinceKotlin(version="1.1")
  @Nullable
  public static final Short toShortOrNull(@NotNull String paramString, int paramInt)
  {
    Intrinsics.checkParameterIsNotNull(paramString, "$receiver");
    paramString = StringsKt.toIntOrNull(paramString, paramInt);
    if (paramString != null)
    {
      paramInt = paramString.intValue();
      if (paramInt >= 32768)
      {
        if (paramInt > 32767) {
          return null;
        }
        return Short.valueOf((short)paramInt);
      }
      return null;
    }
    return null;
  }
  
  @SinceKotlin(version="1.1")
  @InlineOnly
  private static final String toString(byte paramByte, int paramInt)
  {
    String str = Integer.toString(paramByte, CharsKt.checkRadix(CharsKt.checkRadix(paramInt)));
    Intrinsics.checkExpressionValueIsNotNull(str, "java.lang.Integer.toStri…(this, checkRadix(radix))");
    return str;
  }
  
  @SinceKotlin(version="1.1")
  @InlineOnly
  private static final String toString(int paramInt1, int paramInt2)
  {
    String str = Integer.toString(paramInt1, CharsKt.checkRadix(paramInt2));
    Intrinsics.checkExpressionValueIsNotNull(str, "java.lang.Integer.toStri…(this, checkRadix(radix))");
    return str;
  }
  
  @SinceKotlin(version="1.1")
  @InlineOnly
  private static final String toString(long paramLong, int paramInt)
  {
    String str = Long.toString(paramLong, CharsKt.checkRadix(paramInt));
    Intrinsics.checkExpressionValueIsNotNull(str, "java.lang.Long.toString(this, checkRadix(radix))");
    return str;
  }
  
  @SinceKotlin(version="1.1")
  @InlineOnly
  private static final String toString(short paramShort, int paramInt)
  {
    String str = Integer.toString(paramShort, CharsKt.checkRadix(CharsKt.checkRadix(paramInt)));
    Intrinsics.checkExpressionValueIsNotNull(str, "java.lang.Integer.toStri…(this, checkRadix(radix))");
    return str;
  }
}
