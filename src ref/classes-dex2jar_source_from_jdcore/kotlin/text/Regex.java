package kotlin.text;

import java.io.Serializable;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReference;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.IntRange;
import kotlin.reflect.KDeclarationContainer;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000f\n\002\030\002\n\002\030\002\n\002\030\002\n\000\n\002\020\016\n\002\b\002\n\002\030\002\n\002\b\002\n\002\020\"\n\002\b\002\n\002\030\002\n\002\b\007\n\002\020\013\n\000\n\002\020\r\n\000\n\002\030\002\n\000\n\002\020\b\n\000\n\002\030\002\n\002\b\004\n\002\030\002\n\002\b\003\n\002\020 \n\002\b\004\n\002\020\000\n\002\b\003\030\000 ,2\0060\001j\002`\002:\002,-B\017\b\026\022\006\020\003\032\0020\004¢\006\002\020\005B\027\b\026\022\006\020\003\032\0020\004\022\006\020\006\032\0020\007¢\006\002\020\bB\035\b\026\022\006\020\003\032\0020\004\022\f\020\t\032\b\022\004\022\0020\0070\n¢\006\002\020\013B\017\b\001\022\006\020\f\032\0020\r¢\006\002\020\016J\016\020\024\032\0020\0252\006\020\026\032\0020\027J\032\020\030\032\004\030\0010\0312\006\020\026\032\0020\0272\b\b\002\020\032\032\0020\033J\036\020\034\032\b\022\004\022\0020\0310\0352\006\020\026\032\0020\0272\b\b\002\020\032\032\0020\033J\020\020\036\032\004\030\0010\0312\006\020\026\032\0020\027J\021\020\037\032\0020\0252\006\020\026\032\0020\027H\004J\"\020 \032\0020\0042\006\020\026\032\0020\0272\022\020!\032\016\022\004\022\0020\031\022\004\022\0020\0270\"J\026\020 \032\0020\0042\006\020\026\032\0020\0272\006\020#\032\0020\004J\026\020$\032\0020\0042\006\020\026\032\0020\0272\006\020#\032\0020\004J\036\020%\032\b\022\004\022\0020\0040&2\006\020\026\032\0020\0272\b\b\002\020'\032\0020\033J\006\020(\032\0020\rJ\b\020)\032\0020\004H\026J\b\020*\032\0020+H\002R\026\020\017\032\n\022\004\022\0020\007\030\0010\nX\016¢\006\002\n\000R\016\020\f\032\0020\rX\004¢\006\002\n\000R\027\020\t\032\b\022\004\022\0020\0070\n8F¢\006\006\032\004\b\020\020\021R\021\020\003\032\0020\0048F¢\006\006\032\004\b\022\020\023¨\006."}, d2={"Lkotlin/text/Regex;", "Ljava/io/Serializable;", "Lkotlin/io/Serializable;", "pattern", "", "(Ljava/lang/String;)V", "option", "Lkotlin/text/RegexOption;", "(Ljava/lang/String;Lkotlin/text/RegexOption;)V", "options", "", "(Ljava/lang/String;Ljava/util/Set;)V", "nativePattern", "Ljava/util/regex/Pattern;", "(Ljava/util/regex/Pattern;)V", "_options", "getOptions", "()Ljava/util/Set;", "getPattern", "()Ljava/lang/String;", "containsMatchIn", "", "input", "", "find", "Lkotlin/text/MatchResult;", "startIndex", "", "findAll", "Lkotlin/sequences/Sequence;", "matchEntire", "matches", "replace", "transform", "Lkotlin/Function1;", "replacement", "replaceFirst", "split", "", "limit", "toPattern", "toString", "writeReplace", "", "Companion", "Serialized", "kotlin-stdlib"}, k=1, mv={1, 1, 9})
public final class Regex
  implements Serializable
{
  public static final Companion Companion = new Companion(null);
  private Set<? extends RegexOption> _options;
  private final Pattern nativePattern;
  
  public Regex(@NotNull String paramString)
  {
    this(paramString);
  }
  
  public Regex(@NotNull String paramString, @NotNull Set<? extends RegexOption> paramSet)
  {
    this(paramString);
  }
  
  public Regex(@NotNull String paramString, @NotNull RegexOption paramRegexOption)
  {
    this(paramString);
  }
  
  @PublishedApi
  public Regex(@NotNull Pattern paramPattern)
  {
    nativePattern = paramPattern;
  }
  
  private final Object writeReplace()
  {
    String str = nativePattern.pattern();
    Intrinsics.checkExpressionValueIsNotNull(str, "nativePattern.pattern()");
    return new Serialized(str, nativePattern.flags());
  }
  
  public final boolean containsMatchIn(@NotNull CharSequence paramCharSequence)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "input");
    return nativePattern.matcher(paramCharSequence).find();
  }
  
  @Nullable
  public final MatchResult find(@NotNull CharSequence paramCharSequence, int paramInt)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "input");
    Matcher localMatcher = nativePattern.matcher(paramCharSequence);
    Intrinsics.checkExpressionValueIsNotNull(localMatcher, "nativePattern.matcher(input)");
    return RegexKt.access$findNext(localMatcher, paramInt, paramCharSequence);
  }
  
  @NotNull
  public final Sequence<MatchResult> findAll(@NotNull final CharSequence paramCharSequence, final int paramInt)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "input");
    SequencesKt.generateSequence((Function0)new Lambda(paramCharSequence)
    {
      @Nullable
      public final MatchResult invoke()
      {
        return this$0.find(paramCharSequence, paramInt);
      }
    }, (Function1)findAll.2.INSTANCE);
  }
  
  @NotNull
  public final Set<RegexOption> getOptions()
  {
    Object localObject = _options;
    if (localObject != null) {
      return localObject;
    }
    int i = nativePattern.flags();
    localObject = EnumSet.allOf(RegexOption.class);
    CollectionsKt.retainAll((Iterable)localObject, (Function1)new Regex.fromInt..inlined.apply.lambda.1(i));
    localObject = Collections.unmodifiableSet((Set)localObject);
    Intrinsics.checkExpressionValueIsNotNull(localObject, "Collections.unmodifiable… == it.value }\n        })");
    _options = ((Set)localObject);
    return localObject;
  }
  
  @NotNull
  public final String getPattern()
  {
    String str = nativePattern.pattern();
    Intrinsics.checkExpressionValueIsNotNull(str, "nativePattern.pattern()");
    return str;
  }
  
  @Nullable
  public final MatchResult matchEntire(@NotNull CharSequence paramCharSequence)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "input");
    Matcher localMatcher = nativePattern.matcher(paramCharSequence);
    Intrinsics.checkExpressionValueIsNotNull(localMatcher, "nativePattern.matcher(input)");
    return RegexKt.access$matchEntire(localMatcher, paramCharSequence);
  }
  
  public final boolean matches(@NotNull CharSequence paramCharSequence)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "input");
    return nativePattern.matcher(paramCharSequence).matches();
  }
  
  @NotNull
  public final String replace(@NotNull CharSequence paramCharSequence, @NotNull String paramString)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "input");
    Intrinsics.checkParameterIsNotNull(paramString, "replacement");
    paramCharSequence = nativePattern.matcher(paramCharSequence).replaceAll(paramString);
    Intrinsics.checkExpressionValueIsNotNull(paramCharSequence, "nativePattern.matcher(in…).replaceAll(replacement)");
    return paramCharSequence;
  }
  
  @NotNull
  public final String replace(@NotNull CharSequence paramCharSequence, @NotNull Function1<? super MatchResult, ? extends CharSequence> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "input");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "transform");
    int i = 0;
    Object localObject = find$default(this, paramCharSequence, 0, 2, null);
    if (localObject != null)
    {
      int k = paramCharSequence.length();
      StringBuilder localStringBuilder = new StringBuilder(k);
      int j;
      MatchResult localMatchResult;
      do
      {
        if (localObject == null) {
          Intrinsics.throwNpe();
        }
        localStringBuilder.append(paramCharSequence, i, ((MatchResult)localObject).getRange().getStart().intValue());
        localStringBuilder.append((CharSequence)paramFunction1.invoke(localObject));
        j = ((MatchResult)localObject).getRange().getEndInclusive().intValue() + 1;
        localMatchResult = ((MatchResult)localObject).next();
        if (j >= k) {
          break;
        }
        i = j;
        localObject = localMatchResult;
      } while (localMatchResult != null);
      if (j < k) {
        localStringBuilder.append(paramCharSequence, j, k);
      }
      paramCharSequence = localStringBuilder.toString();
      Intrinsics.checkExpressionValueIsNotNull(paramCharSequence, "sb.toString()");
      return paramCharSequence;
    }
    return paramCharSequence.toString();
  }
  
  @NotNull
  public final String replaceFirst(@NotNull CharSequence paramCharSequence, @NotNull String paramString)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "input");
    Intrinsics.checkParameterIsNotNull(paramString, "replacement");
    paramCharSequence = nativePattern.matcher(paramCharSequence).replaceFirst(paramString);
    Intrinsics.checkExpressionValueIsNotNull(paramCharSequence, "nativePattern.matcher(in…replaceFirst(replacement)");
    return paramCharSequence;
  }
  
  @NotNull
  public final List<String> split(@NotNull CharSequence paramCharSequence, int paramInt)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "input");
    if (paramInt >= 0) {
      i = 1;
    } else {
      i = 0;
    }
    if (i == 0)
    {
      paramCharSequence = new StringBuilder();
      paramCharSequence.append("Limit must be non-negative, but was ");
      paramCharSequence.append(paramInt);
      paramCharSequence.append('.');
      throw ((Throwable)new IllegalArgumentException(paramCharSequence.toString().toString()));
    }
    Pattern localPattern = nativePattern;
    int i = paramInt;
    if (paramInt == 0) {
      i = -1;
    }
    paramCharSequence = localPattern.split(paramCharSequence, i);
    Intrinsics.checkExpressionValueIsNotNull(paramCharSequence, "nativePattern.split(inpu…imit == 0) -1 else limit)");
    return ArraysKt.asList((Object[])paramCharSequence);
  }
  
  @NotNull
  public final Pattern toPattern()
  {
    return nativePattern;
  }
  
  @NotNull
  public String toString()
  {
    String str = nativePattern.toString();
    Intrinsics.checkExpressionValueIsNotNull(str, "nativePattern.toString()");
    return str;
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\000\"\n\002\030\002\n\002\020\000\n\002\b\002\n\002\020\b\n\002\b\002\n\002\020\016\n\002\b\003\n\002\030\002\n\000\b\003\030\0002\0020\001B\007\b\002¢\006\002\020\002J\020\020\003\032\0020\0042\006\020\005\032\0020\004H\002J\016\020\006\032\0020\0072\006\020\b\032\0020\007J\016\020\t\032\0020\0072\006\020\b\032\0020\007J\016\020\n\032\0020\0132\006\020\b\032\0020\007¨\006\f"}, d2={"Lkotlin/text/Regex$Companion;", "", "()V", "ensureUnicodeCase", "", "flags", "escape", "", "literal", "escapeReplacement", "fromLiteral", "Lkotlin/text/Regex;", "kotlin-stdlib"}, k=1, mv={1, 1, 9})
  public static final class Companion
  {
    private Companion() {}
    
    private final int ensureUnicodeCase(int paramInt)
    {
      int i = paramInt;
      if ((paramInt & 0x2) != 0) {
        i = paramInt | 0x40;
      }
      return i;
    }
    
    @NotNull
    public final String escape(@NotNull String paramString)
    {
      Intrinsics.checkParameterIsNotNull(paramString, "literal");
      paramString = Pattern.quote(paramString);
      Intrinsics.checkExpressionValueIsNotNull(paramString, "Pattern.quote(literal)");
      return paramString;
    }
    
    @NotNull
    public final String escapeReplacement(@NotNull String paramString)
    {
      Intrinsics.checkParameterIsNotNull(paramString, "literal");
      paramString = Matcher.quoteReplacement(paramString);
      Intrinsics.checkExpressionValueIsNotNull(paramString, "Matcher.quoteReplacement(literal)");
      return paramString;
    }
    
    @NotNull
    public final Regex fromLiteral(@NotNull String paramString)
    {
      Intrinsics.checkParameterIsNotNull(paramString, "literal");
      return new Regex(paramString, RegexOption.LITERAL);
    }
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\000$\n\002\030\002\n\002\030\002\n\002\030\002\n\000\n\002\020\016\n\000\n\002\020\b\n\002\b\006\n\002\020\000\n\002\b\002\b\002\030\000 \0162\0060\001j\002`\002:\001\016B\025\022\006\020\003\032\0020\004\022\006\020\005\032\0020\006¢\006\002\020\007J\b\020\f\032\0020\rH\002R\021\020\005\032\0020\006¢\006\b\n\000\032\004\b\b\020\tR\021\020\003\032\0020\004¢\006\b\n\000\032\004\b\n\020\013¨\006\017"}, d2={"Lkotlin/text/Regex$Serialized;", "Ljava/io/Serializable;", "Lkotlin/io/Serializable;", "pattern", "", "flags", "", "(Ljava/lang/String;I)V", "getFlags", "()I", "getPattern", "()Ljava/lang/String;", "readResolve", "", "Companion", "kotlin-stdlib"}, k=1, mv={1, 1, 9})
  private static final class Serialized
    implements Serializable
  {
    public static final Companion Companion = new Companion(null);
    private static final long serialVersionUID = 0L;
    private final int flags;
    @NotNull
    private final String pattern;
    
    public Serialized(@NotNull String paramString, int paramInt)
    {
      pattern = paramString;
      flags = paramInt;
    }
    
    private final Object readResolve()
    {
      Pattern localPattern = Pattern.compile(pattern, flags);
      Intrinsics.checkExpressionValueIsNotNull(localPattern, "Pattern.compile(pattern, flags)");
      return new Regex(localPattern);
    }
    
    public final int getFlags()
    {
      return flags;
    }
    
    @NotNull
    public final String getPattern()
    {
      return pattern;
    }
    
    @Metadata(bv={1, 0, 2}, d1={"\000\022\n\002\030\002\n\002\020\000\n\002\b\002\n\002\020\t\n\000\b\003\030\0002\0020\001B\007\b\002¢\006\002\020\002R\016\020\003\032\0020\004XT¢\006\002\n\000¨\006\005"}, d2={"Lkotlin/text/Regex$Serialized$Companion;", "", "()V", "serialVersionUID", "", "kotlin-stdlib"}, k=1, mv={1, 1, 9})
    public static final class Companion
    {
      private Companion() {}
    }
  }
}
