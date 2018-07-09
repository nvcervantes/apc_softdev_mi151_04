package kotlin.text;

import java.util.Set;
import java.util.regex.Pattern;
import kotlin.Metadata;
import kotlin.internal.InlineOnly;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000\034\n\000\n\002\030\002\n\002\030\002\n\002\020\016\n\000\n\002\020\"\n\002\030\002\n\002\b\002\032\r\020\000\032\0020\001*\0020\002H\b\032\r\020\000\032\0020\001*\0020\003H\b\032\033\020\000\032\0020\001*\0020\0032\f\020\004\032\b\022\004\022\0020\0060\005H\b\032\025\020\000\032\0020\001*\0020\0032\006\020\007\032\0020\006H\b¨\006\b"}, d2={"toRegex", "Lkotlin/text/Regex;", "Ljava/util/regex/Pattern;", "", "options", "", "Lkotlin/text/RegexOption;", "option", "kotlin-stdlib"}, k=5, mv={1, 1, 9}, xi=1, xs="kotlin/text/StringsKt")
class StringsKt__RegexExtensionsKt
  extends StringsKt__IndentKt
{
  public StringsKt__RegexExtensionsKt() {}
  
  @InlineOnly
  private static final Regex toRegex(@NotNull String paramString)
  {
    return new Regex(paramString);
  }
  
  @InlineOnly
  private static final Regex toRegex(@NotNull String paramString, Set<? extends RegexOption> paramSet)
  {
    return new Regex(paramString, paramSet);
  }
  
  @InlineOnly
  private static final Regex toRegex(@NotNull String paramString, RegexOption paramRegexOption)
  {
    return new Regex(paramString, paramRegexOption);
  }
  
  @InlineOnly
  private static final Regex toRegex(@NotNull Pattern paramPattern)
  {
    return new Regex(paramPattern);
  }
}
