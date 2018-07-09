package kotlin.text;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.Unit;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000H\n\000\n\002\020\016\n\000\n\002\020\b\n\000\n\002\030\002\n\002\030\002\n\002\030\002\n\002\020\002\n\002\030\002\n\002\b\002\n\002\030\002\n\002\030\002\n\000\n\002\020\021\n\002\020\r\n\000\n\002\020\000\n\002\b\b\n\002\020\f\n\000\032.\020\000\032\0020\0012\006\020\002\032\0020\0032\033\020\004\032\027\022\b\022\0060\006j\002`\007\022\004\022\0020\b0\005¢\006\002\b\tH\b\032&\020\000\032\0020\0012\033\020\004\032\027\022\b\022\0060\006j\002`\007\022\004\022\0020\b0\005¢\006\002\b\tH\b\0325\020\n\032\002H\013\"\f\b\000\020\013*\0060\fj\002`\r*\002H\0132\026\020\016\032\f\022\b\b\001\022\004\030\0010\0200\017\"\004\030\0010\020¢\006\002\020\021\032/\020\n\032\0060\006j\002`\007*\0060\006j\002`\0072\026\020\016\032\f\022\b\b\001\022\004\030\0010\0220\017\"\004\030\0010\022¢\006\002\020\023\032/\020\n\032\0060\006j\002`\007*\0060\006j\002`\0072\026\020\016\032\f\022\b\b\001\022\004\030\0010\0010\017\"\004\030\0010\001¢\006\002\020\024\0329\020\025\032\0020\b\"\004\b\000\020\013*\0060\fj\002`\r2\006\020\026\032\002H\0132\024\020\027\032\020\022\004\022\002H\013\022\004\022\0020\020\030\0010\005H\000¢\006\002\020\030\032!\020\031\032\0020\b*\0060\006j\002`\0072\006\020\032\032\0020\0032\006\020\016\032\0020\033H\n¨\006\034"}, d2={"buildString", "", "capacity", "", "builderAction", "Lkotlin/Function1;", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "", "Lkotlin/ExtensionFunctionType;", "append", "T", "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "value", "", "", "(Ljava/lang/Appendable;[Ljava/lang/CharSequence;)Ljava/lang/Appendable;", "", "(Ljava/lang/StringBuilder;[Ljava/lang/Object;)Ljava/lang/StringBuilder;", "(Ljava/lang/StringBuilder;[Ljava/lang/String;)Ljava/lang/StringBuilder;", "appendElement", "element", "transform", "(Ljava/lang/Appendable;Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)V", "set", "index", "", "kotlin-stdlib"}, k=5, mv={1, 1, 9}, xi=1, xs="kotlin/text/StringsKt")
class StringsKt__StringBuilderKt
  extends StringsKt__StringBuilderJVMKt
{
  public StringsKt__StringBuilderKt() {}
  
  @NotNull
  public static final <T extends Appendable> T append(@NotNull T paramT, @NotNull CharSequence... paramVarArgs)
  {
    Intrinsics.checkParameterIsNotNull(paramT, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramVarArgs, "value");
    int i = 0;
    int j = paramVarArgs.length;
    while (i < j)
    {
      paramT.append(paramVarArgs[i]);
      i += 1;
    }
    return paramT;
  }
  
  @NotNull
  public static final StringBuilder append(@NotNull StringBuilder paramStringBuilder, @NotNull Object... paramVarArgs)
  {
    Intrinsics.checkParameterIsNotNull(paramStringBuilder, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramVarArgs, "value");
    int i = 0;
    int j = paramVarArgs.length;
    while (i < j)
    {
      paramStringBuilder.append(paramVarArgs[i]);
      i += 1;
    }
    return paramStringBuilder;
  }
  
  @NotNull
  public static final StringBuilder append(@NotNull StringBuilder paramStringBuilder, @NotNull String... paramVarArgs)
  {
    Intrinsics.checkParameterIsNotNull(paramStringBuilder, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramVarArgs, "value");
    int i = 0;
    int j = paramVarArgs.length;
    while (i < j)
    {
      paramStringBuilder.append(paramVarArgs[i]);
      i += 1;
    }
    return paramStringBuilder;
  }
  
  public static final <T> void appendElement(@NotNull Appendable paramAppendable, T paramT, @Nullable Function1<? super T, ? extends CharSequence> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramAppendable, "$receiver");
    if (paramFunction1 != null)
    {
      paramAppendable.append((CharSequence)paramFunction1.invoke(paramT));
      return;
    }
    boolean bool;
    if (paramT != null) {
      bool = paramT instanceof CharSequence;
    } else {
      bool = true;
    }
    if (bool)
    {
      paramAppendable.append((CharSequence)paramT);
      return;
    }
    if ((paramT instanceof Character))
    {
      paramAppendable.append(((Character)paramT).charValue());
      return;
    }
    paramAppendable.append((CharSequence)String.valueOf(paramT));
  }
  
  @SinceKotlin(version="1.1")
  @InlineOnly
  private static final String buildString(int paramInt, Function1<? super StringBuilder, Unit> paramFunction1)
  {
    StringBuilder localStringBuilder = new StringBuilder(paramInt);
    paramFunction1.invoke(localStringBuilder);
    paramFunction1 = localStringBuilder.toString();
    Intrinsics.checkExpressionValueIsNotNull(paramFunction1, "StringBuilder(capacity).…builderAction).toString()");
    return paramFunction1;
  }
  
  @InlineOnly
  private static final String buildString(Function1<? super StringBuilder, Unit> paramFunction1)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    paramFunction1.invoke(localStringBuilder);
    paramFunction1 = localStringBuilder.toString();
    Intrinsics.checkExpressionValueIsNotNull(paramFunction1, "StringBuilder().apply(builderAction).toString()");
    return paramFunction1;
  }
  
  @InlineOnly
  private static final void set(@NotNull StringBuilder paramStringBuilder, int paramInt, char paramChar)
  {
    Intrinsics.checkParameterIsNotNull(paramStringBuilder, "$receiver");
    paramStringBuilder.setCharAt(paramInt, paramChar);
  }
}
