package kotlin.text;

import kotlin.Metadata;
import kotlin.internal.InlineOnly;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000J\n\000\n\002\030\002\n\002\030\002\n\000\n\002\020\f\n\002\020\r\n\002\030\002\n\002\030\002\n\002\030\002\n\002\020\000\n\002\020\013\n\002\020\005\n\002\020\031\n\002\020\006\n\002\020\007\n\002\020\b\n\002\020\t\n\002\020\n\n\002\020\016\n\000\032\022\020\000\032\0060\001j\002`\002*\0060\001j\002`\002\032\035\020\000\032\0060\001j\002`\002*\0060\001j\002`\0022\006\020\003\032\0020\004H\b\032\037\020\000\032\0060\001j\002`\002*\0060\001j\002`\0022\b\020\003\032\004\030\0010\005H\b\032\022\020\000\032\0060\006j\002`\007*\0060\006j\002`\007\032\037\020\000\032\0060\006j\002`\007*\0060\006j\002`\0072\b\020\003\032\004\030\0010\bH\b\032\037\020\000\032\0060\006j\002`\007*\0060\006j\002`\0072\b\020\003\032\004\030\0010\tH\b\032\035\020\000\032\0060\006j\002`\007*\0060\006j\002`\0072\006\020\003\032\0020\nH\b\032\035\020\000\032\0060\006j\002`\007*\0060\006j\002`\0072\006\020\003\032\0020\013H\b\032\035\020\000\032\0060\006j\002`\007*\0060\006j\002`\0072\006\020\003\032\0020\004H\b\032\035\020\000\032\0060\006j\002`\007*\0060\006j\002`\0072\006\020\003\032\0020\fH\b\032\037\020\000\032\0060\006j\002`\007*\0060\006j\002`\0072\b\020\003\032\004\030\0010\005H\b\032\035\020\000\032\0060\006j\002`\007*\0060\006j\002`\0072\006\020\003\032\0020\rH\b\032\035\020\000\032\0060\006j\002`\007*\0060\006j\002`\0072\006\020\003\032\0020\016H\b\032\035\020\000\032\0060\006j\002`\007*\0060\006j\002`\0072\006\020\003\032\0020\017H\b\032\035\020\000\032\0060\006j\002`\007*\0060\006j\002`\0072\006\020\003\032\0020\020H\b\032\035\020\000\032\0060\006j\002`\007*\0060\006j\002`\0072\006\020\003\032\0020\021H\b\032\037\020\000\032\0060\006j\002`\007*\0060\006j\002`\0072\b\020\003\032\004\030\0010\022H\b\032%\020\000\032\0060\006j\002`\007*\0060\006j\002`\0072\016\020\003\032\n\030\0010\006j\004\030\001`\007H\b¨\006\023"}, d2={"appendln", "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "value", "", "", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "Ljava/lang/StringBuffer;", "", "", "", "", "", "", "", "", "", "", "kotlin-stdlib"}, k=5, mv={1, 1, 9}, xi=1, xs="kotlin/text/StringsKt")
class StringsKt__StringBuilderJVMKt
  extends StringsKt__RegexExtensionsKt
{
  public StringsKt__StringBuilderJVMKt() {}
  
  @NotNull
  public static final Appendable appendln(@NotNull Appendable paramAppendable)
  {
    Intrinsics.checkParameterIsNotNull(paramAppendable, "$receiver");
    paramAppendable = paramAppendable.append((CharSequence)SystemProperties.LINE_SEPARATOR);
    Intrinsics.checkExpressionValueIsNotNull(paramAppendable, "append(SystemProperties.LINE_SEPARATOR)");
    return paramAppendable;
  }
  
  @InlineOnly
  private static final Appendable appendln(@NotNull Appendable paramAppendable, char paramChar)
  {
    paramAppendable = paramAppendable.append(paramChar);
    Intrinsics.checkExpressionValueIsNotNull(paramAppendable, "append(value)");
    return StringsKt.appendln(paramAppendable);
  }
  
  @InlineOnly
  private static final Appendable appendln(@NotNull Appendable paramAppendable, CharSequence paramCharSequence)
  {
    paramAppendable = paramAppendable.append(paramCharSequence);
    Intrinsics.checkExpressionValueIsNotNull(paramAppendable, "append(value)");
    return StringsKt.appendln(paramAppendable);
  }
  
  @NotNull
  public static final StringBuilder appendln(@NotNull StringBuilder paramStringBuilder)
  {
    Intrinsics.checkParameterIsNotNull(paramStringBuilder, "$receiver");
    paramStringBuilder.append(SystemProperties.LINE_SEPARATOR);
    Intrinsics.checkExpressionValueIsNotNull(paramStringBuilder, "append(SystemProperties.LINE_SEPARATOR)");
    return paramStringBuilder;
  }
  
  @InlineOnly
  private static final StringBuilder appendln(@NotNull StringBuilder paramStringBuilder, byte paramByte)
  {
    paramStringBuilder.append(paramByte);
    Intrinsics.checkExpressionValueIsNotNull(paramStringBuilder, "append(value.toInt())");
    return StringsKt.appendln(paramStringBuilder);
  }
  
  @InlineOnly
  private static final StringBuilder appendln(@NotNull StringBuilder paramStringBuilder, char paramChar)
  {
    paramStringBuilder.append(paramChar);
    Intrinsics.checkExpressionValueIsNotNull(paramStringBuilder, "append(value)");
    return StringsKt.appendln(paramStringBuilder);
  }
  
  @InlineOnly
  private static final StringBuilder appendln(@NotNull StringBuilder paramStringBuilder, double paramDouble)
  {
    paramStringBuilder.append(paramDouble);
    Intrinsics.checkExpressionValueIsNotNull(paramStringBuilder, "append(value)");
    return StringsKt.appendln(paramStringBuilder);
  }
  
  @InlineOnly
  private static final StringBuilder appendln(@NotNull StringBuilder paramStringBuilder, float paramFloat)
  {
    paramStringBuilder.append(paramFloat);
    Intrinsics.checkExpressionValueIsNotNull(paramStringBuilder, "append(value)");
    return StringsKt.appendln(paramStringBuilder);
  }
  
  @InlineOnly
  private static final StringBuilder appendln(@NotNull StringBuilder paramStringBuilder, int paramInt)
  {
    paramStringBuilder.append(paramInt);
    Intrinsics.checkExpressionValueIsNotNull(paramStringBuilder, "append(value)");
    return StringsKt.appendln(paramStringBuilder);
  }
  
  @InlineOnly
  private static final StringBuilder appendln(@NotNull StringBuilder paramStringBuilder, long paramLong)
  {
    paramStringBuilder.append(paramLong);
    Intrinsics.checkExpressionValueIsNotNull(paramStringBuilder, "append(value)");
    return StringsKt.appendln(paramStringBuilder);
  }
  
  @InlineOnly
  private static final StringBuilder appendln(@NotNull StringBuilder paramStringBuilder, CharSequence paramCharSequence)
  {
    paramStringBuilder.append(paramCharSequence);
    Intrinsics.checkExpressionValueIsNotNull(paramStringBuilder, "append(value)");
    return StringsKt.appendln(paramStringBuilder);
  }
  
  @InlineOnly
  private static final StringBuilder appendln(@NotNull StringBuilder paramStringBuilder, Object paramObject)
  {
    paramStringBuilder.append(paramObject);
    Intrinsics.checkExpressionValueIsNotNull(paramStringBuilder, "append(value)");
    return StringsKt.appendln(paramStringBuilder);
  }
  
  @InlineOnly
  private static final StringBuilder appendln(@NotNull StringBuilder paramStringBuilder, String paramString)
  {
    paramStringBuilder.append(paramString);
    Intrinsics.checkExpressionValueIsNotNull(paramStringBuilder, "append(value)");
    return StringsKt.appendln(paramStringBuilder);
  }
  
  @InlineOnly
  private static final StringBuilder appendln(@NotNull StringBuilder paramStringBuilder, StringBuffer paramStringBuffer)
  {
    paramStringBuilder.append(paramStringBuffer);
    Intrinsics.checkExpressionValueIsNotNull(paramStringBuilder, "append(value)");
    return StringsKt.appendln(paramStringBuilder);
  }
  
  @InlineOnly
  private static final StringBuilder appendln(@NotNull StringBuilder paramStringBuilder1, StringBuilder paramStringBuilder2)
  {
    paramStringBuilder1.append((CharSequence)paramStringBuilder2);
    Intrinsics.checkExpressionValueIsNotNull(paramStringBuilder1, "append(value)");
    return StringsKt.appendln(paramStringBuilder1);
  }
  
  @InlineOnly
  private static final StringBuilder appendln(@NotNull StringBuilder paramStringBuilder, short paramShort)
  {
    paramStringBuilder.append(paramShort);
    Intrinsics.checkExpressionValueIsNotNull(paramStringBuilder, "append(value.toInt())");
    return StringsKt.appendln(paramStringBuilder);
  }
  
  @InlineOnly
  private static final StringBuilder appendln(@NotNull StringBuilder paramStringBuilder, boolean paramBoolean)
  {
    paramStringBuilder.append(paramBoolean);
    Intrinsics.checkExpressionValueIsNotNull(paramStringBuilder, "append(value)");
    return StringsKt.appendln(paramStringBuilder);
  }
  
  @InlineOnly
  private static final StringBuilder appendln(@NotNull StringBuilder paramStringBuilder, char[] paramArrayOfChar)
  {
    paramStringBuilder.append(paramArrayOfChar);
    Intrinsics.checkExpressionValueIsNotNull(paramStringBuilder, "append(value)");
    return StringsKt.appendln(paramStringBuilder);
  }
}
