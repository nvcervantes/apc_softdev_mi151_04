package kotlin.text;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ReplaceWith;
import kotlin.TuplesKt;
import kotlin.TypeCastException;
import kotlin.collections.ArraysKt;
import kotlin.collections.CharIterator;
import kotlin.collections.CollectionsKt;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.ranges.IntProgression;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000t\n\000\n\002\030\002\n\002\020\r\n\002\b\003\n\002\020\b\n\002\b\003\n\002\020\016\n\002\b\002\n\002\020\013\n\002\b\003\n\002\020\f\n\000\n\002\030\002\n\002\b\003\n\002\030\002\n\000\n\002\020\036\n\002\b\f\n\002\020\031\n\002\b\006\n\002\030\002\n\002\b\003\n\002\030\002\n\000\n\002\020 \n\002\b\b\n\002\020\021\n\002\b\017\n\002\030\002\n\002\030\002\n\002\b\033\032\034\020\t\032\0020\n*\0020\0022\006\020\013\032\0020\0022\b\b\002\020\f\032\0020\r\032\034\020\016\032\0020\n*\0020\0022\006\020\013\032\0020\0022\b\b\002\020\f\032\0020\r\032\037\020\017\032\0020\r*\0020\0022\006\020\020\032\0020\0212\b\b\002\020\f\032\0020\rH\002\032\037\020\017\032\0020\r*\0020\0022\006\020\013\032\0020\0022\b\b\002\020\f\032\0020\rH\002\032\025\020\017\032\0020\r*\0020\0022\006\020\022\032\0020\023H\n\032\034\020\024\032\0020\r*\0020\0022\006\020\020\032\0020\0212\b\b\002\020\f\032\0020\r\032\034\020\024\032\0020\r*\0020\0022\006\020\025\032\0020\0022\b\b\002\020\f\032\0020\r\032:\020\026\032\020\022\004\022\0020\006\022\004\022\0020\n\030\0010\027*\0020\0022\f\020\030\032\b\022\004\022\0020\n0\0312\b\b\002\020\032\032\0020\0062\b\b\002\020\f\032\0020\r\032E\020\026\032\020\022\004\022\0020\006\022\004\022\0020\n\030\0010\027*\0020\0022\f\020\030\032\b\022\004\022\0020\n0\0312\006\020\032\032\0020\0062\006\020\f\032\0020\r2\006\020\033\032\0020\rH\002¢\006\002\b\034\032:\020\035\032\020\022\004\022\0020\006\022\004\022\0020\n\030\0010\027*\0020\0022\f\020\030\032\b\022\004\022\0020\n0\0312\b\b\002\020\032\032\0020\0062\b\b\002\020\f\032\0020\r\032\022\020\036\032\0020\r*\0020\0022\006\020\037\032\0020\006\032&\020 \032\0020\006*\0020\0022\006\020\020\032\0020\0212\b\b\002\020\032\032\0020\0062\b\b\002\020\f\032\0020\r\032;\020 \032\0020\006*\0020\0022\006\020\013\032\0020\0022\006\020\032\032\0020\0062\006\020!\032\0020\0062\006\020\f\032\0020\r2\b\b\002\020\033\032\0020\rH\002¢\006\002\b\"\032&\020 \032\0020\006*\0020\0022\006\020#\032\0020\n2\b\b\002\020\032\032\0020\0062\b\b\002\020\f\032\0020\r\032&\020$\032\0020\006*\0020\0022\006\020%\032\0020&2\b\b\002\020\032\032\0020\0062\b\b\002\020\f\032\0020\r\032,\020$\032\0020\006*\0020\0022\f\020\030\032\b\022\004\022\0020\n0\0312\b\b\002\020\032\032\0020\0062\b\b\002\020\f\032\0020\r\032\r\020'\032\0020\r*\0020\002H\b\032\r\020(\032\0020\r*\0020\002H\b\032\r\020)\032\0020\r*\0020\002H\b\032 \020*\032\0020\r*\004\030\0010\002H\b\002\016\n\f\b\000\022\002\030\001\032\004\b\003\020\000\032 \020+\032\0020\r*\004\030\0010\002H\b\002\016\n\f\b\000\022\002\030\001\032\004\b\003\020\000\032\r\020,\032\0020-*\0020\002H\002\032&\020.\032\0020\006*\0020\0022\006\020\020\032\0020\0212\b\b\002\020\032\032\0020\0062\b\b\002\020\f\032\0020\r\032&\020.\032\0020\006*\0020\0022\006\020#\032\0020\n2\b\b\002\020\032\032\0020\0062\b\b\002\020\f\032\0020\r\032&\020/\032\0020\006*\0020\0022\006\020%\032\0020&2\b\b\002\020\032\032\0020\0062\b\b\002\020\f\032\0020\r\032,\020/\032\0020\006*\0020\0022\f\020\030\032\b\022\004\022\0020\n0\0312\b\b\002\020\032\032\0020\0062\b\b\002\020\f\032\0020\r\032\020\0200\032\b\022\004\022\0020\n01*\0020\002\032\020\0202\032\b\022\004\022\0020\n03*\0020\002\032\025\0204\032\0020\r*\0020\0022\006\020\022\032\0020\023H\f\032\017\0205\032\0020\n*\004\030\0010\nH\b\032\034\0206\032\0020\002*\0020\0022\006\0207\032\0020\0062\b\b\002\0208\032\0020\021\032\034\0206\032\0020\n*\0020\n2\006\0207\032\0020\0062\b\b\002\0208\032\0020\021\032\034\0209\032\0020\002*\0020\0022\006\0207\032\0020\0062\b\b\002\0208\032\0020\021\032\034\0209\032\0020\n*\0020\n2\006\0207\032\0020\0062\b\b\002\0208\032\0020\021\032G\020:\032\b\022\004\022\0020\00101*\0020\0022\016\020;\032\n\022\006\b\001\022\0020\n0<2\b\b\002\020\032\032\0020\0062\b\b\002\020\f\032\0020\r2\b\b\002\020=\032\0020\006H\002¢\006\004\b>\020?\032=\020:\032\b\022\004\022\0020\00101*\0020\0022\006\020;\032\0020&2\b\b\002\020\032\032\0020\0062\b\b\002\020\f\032\0020\r2\b\b\002\020=\032\0020\006H\002¢\006\002\b>\0324\020@\032\0020\r*\0020\0022\006\020A\032\0020\0062\006\020\013\032\0020\0022\006\020B\032\0020\0062\006\0207\032\0020\0062\006\020\f\032\0020\rH\000\032\022\020C\032\0020\002*\0020\0022\006\020D\032\0020\002\032\022\020C\032\0020\n*\0020\n2\006\020D\032\0020\002\032\032\020E\032\0020\002*\0020\0022\006\020\032\032\0020\0062\006\020!\032\0020\006\032\022\020E\032\0020\002*\0020\0022\006\020F\032\0020\001\032\035\020E\032\0020\n*\0020\n2\006\020\032\032\0020\0062\006\020!\032\0020\006H\b\032\025\020E\032\0020\n*\0020\n2\006\020F\032\0020\001H\b\032\022\020G\032\0020\002*\0020\0022\006\020\025\032\0020\002\032\022\020G\032\0020\n*\0020\n2\006\020\025\032\0020\002\032\022\020H\032\0020\002*\0020\0022\006\020I\032\0020\002\032\032\020H\032\0020\002*\0020\0022\006\020D\032\0020\0022\006\020\025\032\0020\002\032\022\020H\032\0020\n*\0020\n2\006\020I\032\0020\002\032\032\020H\032\0020\n*\0020\n2\006\020D\032\0020\0022\006\020\025\032\0020\002\032+\020J\032\0020\n*\0020\0022\006\020\022\032\0020\0232\024\b\b\020K\032\016\022\004\022\0020M\022\004\022\0020\0020LH\b\032\035\020J\032\0020\n*\0020\0022\006\020\022\032\0020\0232\006\020N\032\0020\nH\b\032$\020O\032\0020\n*\0020\n2\006\020I\032\0020\0212\006\020N\032\0020\n2\b\b\002\020P\032\0020\n\032$\020O\032\0020\n*\0020\n2\006\020I\032\0020\n2\006\020N\032\0020\n2\b\b\002\020P\032\0020\n\032$\020Q\032\0020\n*\0020\n2\006\020I\032\0020\0212\006\020N\032\0020\n2\b\b\002\020P\032\0020\n\032$\020Q\032\0020\n*\0020\n2\006\020I\032\0020\n2\006\020N\032\0020\n2\b\b\002\020P\032\0020\n\032$\020R\032\0020\n*\0020\n2\006\020I\032\0020\0212\006\020N\032\0020\n2\b\b\002\020P\032\0020\n\032$\020R\032\0020\n*\0020\n2\006\020I\032\0020\n2\006\020N\032\0020\n2\b\b\002\020P\032\0020\n\032$\020S\032\0020\n*\0020\n2\006\020I\032\0020\0212\006\020N\032\0020\n2\b\b\002\020P\032\0020\n\032$\020S\032\0020\n*\0020\n2\006\020I\032\0020\n2\006\020N\032\0020\n2\b\b\002\020P\032\0020\n\032\035\020T\032\0020\n*\0020\0022\006\020\022\032\0020\0232\006\020N\032\0020\nH\b\032\"\020U\032\0020\002*\0020\0022\006\020\032\032\0020\0062\006\020!\032\0020\0062\006\020N\032\0020\002\032\032\020U\032\0020\002*\0020\0022\006\020F\032\0020\0012\006\020N\032\0020\002\032%\020U\032\0020\n*\0020\n2\006\020\032\032\0020\0062\006\020!\032\0020\0062\006\020N\032\0020\002H\b\032\035\020U\032\0020\n*\0020\n2\006\020F\032\0020\0012\006\020N\032\0020\002H\b\032=\020V\032\b\022\004\022\0020\n03*\0020\0022\022\020;\032\n\022\006\b\001\022\0020\n0<\"\0020\n2\b\b\002\020\f\032\0020\r2\b\b\002\020=\032\0020\006¢\006\002\020W\0320\020V\032\b\022\004\022\0020\n03*\0020\0022\n\020;\032\0020&\"\0020\0212\b\b\002\020\f\032\0020\r2\b\b\002\020=\032\0020\006\032/\020V\032\b\022\004\022\0020\n03*\0020\0022\006\020I\032\0020\n2\006\020\f\032\0020\r2\006\020=\032\0020\006H\002¢\006\002\bX\032%\020V\032\b\022\004\022\0020\n03*\0020\0022\006\020\022\032\0020\0232\b\b\002\020=\032\0020\006H\b\032=\020Y\032\b\022\004\022\0020\n01*\0020\0022\022\020;\032\n\022\006\b\001\022\0020\n0<\"\0020\n2\b\b\002\020\f\032\0020\r2\b\b\002\020=\032\0020\006¢\006\002\020Z\0320\020Y\032\b\022\004\022\0020\n01*\0020\0022\n\020;\032\0020&\"\0020\0212\b\b\002\020\f\032\0020\r2\b\b\002\020=\032\0020\006\032\034\020[\032\0020\r*\0020\0022\006\020\020\032\0020\0212\b\b\002\020\f\032\0020\r\032\034\020[\032\0020\r*\0020\0022\006\020D\032\0020\0022\b\b\002\020\f\032\0020\r\032$\020[\032\0020\r*\0020\0022\006\020D\032\0020\0022\006\020\032\032\0020\0062\b\b\002\020\f\032\0020\r\032\022\020\\\032\0020\002*\0020\0022\006\020F\032\0020\001\032\035\020\\\032\0020\002*\0020\n2\006\020]\032\0020\0062\006\020^\032\0020\006H\b\032\037\020_\032\0020\n*\0020\0022\006\020\032\032\0020\0062\b\b\002\020!\032\0020\006H\b\032\022\020_\032\0020\n*\0020\0022\006\020F\032\0020\001\032\022\020_\032\0020\n*\0020\n2\006\020F\032\0020\001\032\034\020`\032\0020\n*\0020\n2\006\020I\032\0020\0212\b\b\002\020P\032\0020\n\032\034\020`\032\0020\n*\0020\n2\006\020I\032\0020\n2\b\b\002\020P\032\0020\n\032\034\020a\032\0020\n*\0020\n2\006\020I\032\0020\0212\b\b\002\020P\032\0020\n\032\034\020a\032\0020\n*\0020\n2\006\020I\032\0020\n2\b\b\002\020P\032\0020\n\032\034\020b\032\0020\n*\0020\n2\006\020I\032\0020\0212\b\b\002\020P\032\0020\n\032\034\020b\032\0020\n*\0020\n2\006\020I\032\0020\n2\b\b\002\020P\032\0020\n\032\034\020c\032\0020\n*\0020\n2\006\020I\032\0020\0212\b\b\002\020P\032\0020\n\032\034\020c\032\0020\n*\0020\n2\006\020I\032\0020\n2\b\b\002\020P\032\0020\n\032\n\020d\032\0020\002*\0020\002\032!\020d\032\0020\002*\0020\0022\022\020e\032\016\022\004\022\0020\021\022\004\022\0020\r0LH\b\032\026\020d\032\0020\002*\0020\0022\n\020%\032\0020&\"\0020\021\032\r\020d\032\0020\n*\0020\nH\b\032!\020d\032\0020\n*\0020\n2\022\020e\032\016\022\004\022\0020\021\022\004\022\0020\r0LH\b\032\026\020d\032\0020\n*\0020\n2\n\020%\032\0020&\"\0020\021\032\n\020f\032\0020\002*\0020\002\032!\020f\032\0020\002*\0020\0022\022\020e\032\016\022\004\022\0020\021\022\004\022\0020\r0LH\b\032\026\020f\032\0020\002*\0020\0022\n\020%\032\0020&\"\0020\021\032\r\020f\032\0020\n*\0020\nH\b\032!\020f\032\0020\n*\0020\n2\022\020e\032\016\022\004\022\0020\021\022\004\022\0020\r0LH\b\032\026\020f\032\0020\n*\0020\n2\n\020%\032\0020&\"\0020\021\032\n\020g\032\0020\002*\0020\002\032!\020g\032\0020\002*\0020\0022\022\020e\032\016\022\004\022\0020\021\022\004\022\0020\r0LH\b\032\026\020g\032\0020\002*\0020\0022\n\020%\032\0020&\"\0020\021\032\r\020g\032\0020\n*\0020\nH\b\032!\020g\032\0020\n*\0020\n2\022\020e\032\016\022\004\022\0020\021\022\004\022\0020\r0LH\b\032\026\020g\032\0020\n*\0020\n2\n\020%\032\0020&\"\0020\021\"\025\020\000\032\0020\001*\0020\0028F¢\006\006\032\004\b\003\020\004\"\025\020\005\032\0020\006*\0020\0028F¢\006\006\032\004\b\007\020\b¨\006h"}, d2={"indices", "Lkotlin/ranges/IntRange;", "", "getIndices", "(Ljava/lang/CharSequence;)Lkotlin/ranges/IntRange;", "lastIndex", "", "getLastIndex", "(Ljava/lang/CharSequence;)I", "commonPrefixWith", "", "other", "ignoreCase", "", "commonSuffixWith", "contains", "char", "", "regex", "Lkotlin/text/Regex;", "endsWith", "suffix", "findAnyOf", "Lkotlin/Pair;", "strings", "", "startIndex", "last", "findAnyOf$StringsKt__StringsKt", "findLastAnyOf", "hasSurrogatePairAt", "index", "indexOf", "endIndex", "indexOf$StringsKt__StringsKt", "string", "indexOfAny", "chars", "", "isEmpty", "isNotBlank", "isNotEmpty", "isNullOrBlank", "isNullOrEmpty", "iterator", "Lkotlin/collections/CharIterator;", "lastIndexOf", "lastIndexOfAny", "lineSequence", "Lkotlin/sequences/Sequence;", "lines", "", "matches", "orEmpty", "padEnd", "length", "padChar", "padStart", "rangesDelimitedBy", "delimiters", "", "limit", "rangesDelimitedBy$StringsKt__StringsKt", "(Ljava/lang/CharSequence;[Ljava/lang/String;IZI)Lkotlin/sequences/Sequence;", "regionMatchesImpl", "thisOffset", "otherOffset", "removePrefix", "prefix", "removeRange", "range", "removeSuffix", "removeSurrounding", "delimiter", "replace", "transform", "Lkotlin/Function1;", "Lkotlin/text/MatchResult;", "replacement", "replaceAfter", "missingDelimiterValue", "replaceAfterLast", "replaceBefore", "replaceBeforeLast", "replaceFirst", "replaceRange", "split", "(Ljava/lang/CharSequence;[Ljava/lang/String;ZI)Ljava/util/List;", "split$StringsKt__StringsKt", "splitToSequence", "(Ljava/lang/CharSequence;[Ljava/lang/String;ZI)Lkotlin/sequences/Sequence;", "startsWith", "subSequence", "start", "end", "substring", "substringAfter", "substringAfterLast", "substringBefore", "substringBeforeLast", "trim", "predicate", "trimEnd", "trimStart", "kotlin-stdlib"}, k=5, mv={1, 1, 9}, xi=1, xs="kotlin/text/StringsKt")
class StringsKt__StringsKt
  extends StringsKt__StringsJVMKt
{
  public StringsKt__StringsKt() {}
  
  @NotNull
  public static final String commonPrefixWith(@NotNull CharSequence paramCharSequence1, @NotNull CharSequence paramCharSequence2, boolean paramBoolean)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence1, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramCharSequence2, "other");
    int j = Math.min(paramCharSequence1.length(), paramCharSequence2.length());
    int i = 0;
    while ((i < j) && (CharsKt.equals(paramCharSequence1.charAt(i), paramCharSequence2.charAt(i), paramBoolean))) {
      i += 1;
    }
    int k = i - 1;
    if (!StringsKt.hasSurrogatePairAt(paramCharSequence1, k))
    {
      j = i;
      if (!StringsKt.hasSurrogatePairAt(paramCharSequence2, k)) {}
    }
    else
    {
      j = i - 1;
    }
    return paramCharSequence1.subSequence(0, j).toString();
  }
  
  @NotNull
  public static final String commonSuffixWith(@NotNull CharSequence paramCharSequence1, @NotNull CharSequence paramCharSequence2, boolean paramBoolean)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence1, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramCharSequence2, "other");
    int k = paramCharSequence1.length();
    int m = paramCharSequence2.length();
    int j = Math.min(k, m);
    int i = 0;
    while ((i < j) && (CharsKt.equals(paramCharSequence1.charAt(k - i - 1), paramCharSequence2.charAt(m - i - 1), paramBoolean))) {
      i += 1;
    }
    if (!StringsKt.hasSurrogatePairAt(paramCharSequence1, k - i - 1))
    {
      j = i;
      if (!StringsKt.hasSurrogatePairAt(paramCharSequence2, m - i - 1)) {}
    }
    else
    {
      j = i - 1;
    }
    return paramCharSequence1.subSequence(k - j, k).toString();
  }
  
  public static final boolean contains(@NotNull CharSequence paramCharSequence, char paramChar, boolean paramBoolean)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    return StringsKt.indexOf$default(paramCharSequence, paramChar, 0, paramBoolean, 2, null) >= 0;
  }
  
  public static final boolean contains(@NotNull CharSequence paramCharSequence1, @NotNull CharSequence paramCharSequence2, boolean paramBoolean)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence1, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramCharSequence2, "other");
    boolean bool2 = paramCharSequence2 instanceof String;
    boolean bool1 = false;
    if (bool2 ? StringsKt.indexOf$default(paramCharSequence1, (String)paramCharSequence2, 0, paramBoolean, 2, null) >= 0 : indexOf$StringsKt__StringsKt$default(paramCharSequence1, paramCharSequence2, 0, paramCharSequence1.length(), paramBoolean, false, 16, null) >= 0) {
      bool1 = true;
    }
    return bool1;
  }
  
  @InlineOnly
  private static final boolean contains(@NotNull CharSequence paramCharSequence, Regex paramRegex)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    return paramRegex.containsMatchIn(paramCharSequence);
  }
  
  public static final boolean endsWith(@NotNull CharSequence paramCharSequence, char paramChar, boolean paramBoolean)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    return (paramCharSequence.length() > 0) && (CharsKt.equals(paramCharSequence.charAt(StringsKt.getLastIndex(paramCharSequence)), paramChar, paramBoolean));
  }
  
  public static final boolean endsWith(@NotNull CharSequence paramCharSequence1, @NotNull CharSequence paramCharSequence2, boolean paramBoolean)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence1, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramCharSequence2, "suffix");
    if ((!paramBoolean) && ((paramCharSequence1 instanceof String)) && ((paramCharSequence2 instanceof String))) {
      return StringsKt.endsWith$default((String)paramCharSequence1, (String)paramCharSequence2, false, 2, null);
    }
    return StringsKt.regionMatchesImpl(paramCharSequence1, paramCharSequence1.length() - paramCharSequence2.length(), paramCharSequence2, 0, paramCharSequence2.length(), paramBoolean);
  }
  
  @Nullable
  public static final Pair<Integer, String> findAnyOf(@NotNull CharSequence paramCharSequence, @NotNull Collection<String> paramCollection, int paramInt, boolean paramBoolean)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramCollection, "strings");
    return findAnyOf$StringsKt__StringsKt(paramCharSequence, paramCollection, paramInt, paramBoolean, false);
  }
  
  private static final Pair<Integer, String> findAnyOf$StringsKt__StringsKt(@NotNull CharSequence paramCharSequence, Collection<String> paramCollection, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
  {
    if ((!paramBoolean1) && (paramCollection.size() == 1))
    {
      paramCollection = (String)CollectionsKt.single((Iterable)paramCollection);
      if (!paramBoolean2) {
        paramInt = StringsKt.indexOf$default(paramCharSequence, paramCollection, paramInt, false, 4, null);
      } else {
        paramInt = StringsKt.lastIndexOf$default(paramCharSequence, paramCollection, paramInt, false, 4, null);
      }
      if (paramInt < 0) {
        return null;
      }
      return TuplesKt.to(Integer.valueOf(paramInt), paramCollection);
    }
    Object localObject;
    if (!paramBoolean2) {
      localObject = (IntProgression)new IntRange(RangesKt.coerceAtLeast(paramInt, 0), paramCharSequence.length());
    } else {
      localObject = RangesKt.downTo(RangesKt.coerceAtMost(paramInt, StringsKt.getLastIndex(paramCharSequence)), 0);
    }
    int i;
    int j;
    Iterator localIterator;
    String str;
    if ((paramCharSequence instanceof String))
    {
      paramInt = ((IntProgression)localObject).getFirst();
      i = ((IntProgression)localObject).getLast();
      j = ((IntProgression)localObject).getStep();
      if (j > 0 ? paramInt <= i : paramInt >= i) {
        for (;;)
        {
          localIterator = ((Iterable)paramCollection).iterator();
          while (localIterator.hasNext())
          {
            localObject = localIterator.next();
            str = (String)localObject;
            if (StringsKt.regionMatches(str, 0, (String)paramCharSequence, paramInt, str.length(), paramBoolean1)) {
              break label223;
            }
          }
          localObject = null;
          label223:
          localObject = (String)localObject;
          if (localObject != null) {
            return TuplesKt.to(Integer.valueOf(paramInt), localObject);
          }
          if (paramInt == i) {
            break;
          }
          paramInt += j;
        }
      }
    }
    else
    {
      paramInt = ((IntProgression)localObject).getFirst();
      i = ((IntProgression)localObject).getLast();
      j = ((IntProgression)localObject).getStep();
      if (j > 0 ? paramInt <= i : paramInt >= i) {
        for (;;)
        {
          localIterator = ((Iterable)paramCollection).iterator();
          while (localIterator.hasNext())
          {
            localObject = localIterator.next();
            str = (String)localObject;
            if (StringsKt.regionMatchesImpl((CharSequence)str, 0, paramCharSequence, paramInt, str.length(), paramBoolean1)) {
              break label362;
            }
          }
          localObject = null;
          label362:
          localObject = (String)localObject;
          if (localObject != null) {
            return TuplesKt.to(Integer.valueOf(paramInt), localObject);
          }
          if (paramInt == i) {
            break;
          }
          paramInt += j;
        }
      }
    }
    return null;
  }
  
  @Nullable
  public static final Pair<Integer, String> findLastAnyOf(@NotNull CharSequence paramCharSequence, @NotNull Collection<String> paramCollection, int paramInt, boolean paramBoolean)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramCollection, "strings");
    return findAnyOf$StringsKt__StringsKt(paramCharSequence, paramCollection, paramInt, paramBoolean, true);
  }
  
  @NotNull
  public static final IntRange getIndices(@NotNull CharSequence paramCharSequence)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    return new IntRange(0, paramCharSequence.length() - 1);
  }
  
  public static final int getLastIndex(@NotNull CharSequence paramCharSequence)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    return paramCharSequence.length() - 1;
  }
  
  public static final boolean hasSurrogatePairAt(@NotNull CharSequence paramCharSequence, int paramInt)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    int i = paramCharSequence.length();
    return (paramInt >= 0) && (i - 2 >= paramInt) && (Character.isHighSurrogate(paramCharSequence.charAt(paramInt))) && (Character.isLowSurrogate(paramCharSequence.charAt(paramInt + 1)));
  }
  
  public static final int indexOf(@NotNull CharSequence paramCharSequence, char paramChar, int paramInt, boolean paramBoolean)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    if ((!paramBoolean) && ((paramCharSequence instanceof String))) {
      return ((String)paramCharSequence).indexOf(paramChar, paramInt);
    }
    return StringsKt.indexOfAny(paramCharSequence, new char[] { paramChar }, paramInt, paramBoolean);
  }
  
  public static final int indexOf(@NotNull CharSequence paramCharSequence, @NotNull String paramString, int paramInt, boolean paramBoolean)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramString, "string");
    if ((!paramBoolean) && ((paramCharSequence instanceof String))) {
      return ((String)paramCharSequence).indexOf(paramString, paramInt);
    }
    return indexOf$StringsKt__StringsKt$default(paramCharSequence, (CharSequence)paramString, paramInt, paramCharSequence.length(), paramBoolean, false, 16, null);
  }
  
  private static final int indexOf$StringsKt__StringsKt(@NotNull CharSequence paramCharSequence1, CharSequence paramCharSequence2, int paramInt1, int paramInt2, boolean paramBoolean1, boolean paramBoolean2)
  {
    IntProgression localIntProgression;
    if (!paramBoolean2) {
      localIntProgression = (IntProgression)new IntRange(RangesKt.coerceAtLeast(paramInt1, 0), RangesKt.coerceAtMost(paramInt2, paramCharSequence1.length()));
    } else {
      localIntProgression = RangesKt.downTo(RangesKt.coerceAtMost(paramInt1, StringsKt.getLastIndex(paramCharSequence1)), RangesKt.coerceAtLeast(paramInt2, 0));
    }
    int i;
    if (((paramCharSequence1 instanceof String)) && ((paramCharSequence2 instanceof String)))
    {
      paramInt1 = localIntProgression.getFirst();
      paramInt2 = localIntProgression.getLast();
      i = localIntProgression.getStep();
      if (i > 0 ? paramInt1 <= paramInt2 : paramInt1 >= paramInt2) {
        for (;;)
        {
          if (StringsKt.regionMatches((String)paramCharSequence2, 0, (String)paramCharSequence1, paramInt1, paramCharSequence2.length(), paramBoolean1)) {
            return paramInt1;
          }
          if (paramInt1 == paramInt2) {
            break;
          }
          paramInt1 += i;
        }
      }
    }
    else
    {
      paramInt1 = localIntProgression.getFirst();
      paramInt2 = localIntProgression.getLast();
      i = localIntProgression.getStep();
      if (i > 0 ? paramInt1 <= paramInt2 : paramInt1 >= paramInt2) {
        for (;;)
        {
          if (StringsKt.regionMatchesImpl(paramCharSequence2, 0, paramCharSequence1, paramInt1, paramCharSequence2.length(), paramBoolean1)) {
            return paramInt1;
          }
          if (paramInt1 == paramInt2) {
            break;
          }
          paramInt1 += i;
        }
      }
    }
    return -1;
  }
  
  public static final int indexOfAny(@NotNull CharSequence paramCharSequence, @NotNull Collection<String> paramCollection, int paramInt, boolean paramBoolean)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramCollection, "strings");
    paramCharSequence = findAnyOf$StringsKt__StringsKt(paramCharSequence, paramCollection, paramInt, paramBoolean, false);
    if (paramCharSequence != null)
    {
      paramCharSequence = (Integer)paramCharSequence.getFirst();
      if (paramCharSequence != null) {
        return paramCharSequence.intValue();
      }
    }
    return -1;
  }
  
  public static final int indexOfAny(@NotNull CharSequence paramCharSequence, @NotNull char[] paramArrayOfChar, int paramInt, boolean paramBoolean)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramArrayOfChar, "chars");
    int i;
    if ((!paramBoolean) && (paramArrayOfChar.length == 1) && ((paramCharSequence instanceof String)))
    {
      i = ArraysKt.single(paramArrayOfChar);
      return ((String)paramCharSequence).indexOf(i, paramInt);
    }
    paramInt = RangesKt.coerceAtLeast(paramInt, 0);
    int j = StringsKt.getLastIndex(paramCharSequence);
    if (paramInt <= j) {
      for (;;)
      {
        char c = paramCharSequence.charAt(paramInt);
        int k = paramArrayOfChar.length;
        i = 0;
        while (i < k)
        {
          if (CharsKt.equals(paramArrayOfChar[i], c, paramBoolean))
          {
            i = 1;
            break label119;
          }
          i += 1;
        }
        i = 0;
        label119:
        if (i != 0) {
          return paramInt;
        }
        if (paramInt == j) {
          break;
        }
        paramInt += 1;
      }
    }
    return -1;
  }
  
  @InlineOnly
  private static final boolean isEmpty(@NotNull CharSequence paramCharSequence)
  {
    return paramCharSequence.length() == 0;
  }
  
  @InlineOnly
  private static final boolean isNotBlank(@NotNull CharSequence paramCharSequence)
  {
    return StringsKt.isBlank(paramCharSequence) ^ true;
  }
  
  @InlineOnly
  private static final boolean isNotEmpty(@NotNull CharSequence paramCharSequence)
  {
    return paramCharSequence.length() > 0;
  }
  
  @InlineOnly
  private static final boolean isNullOrBlank(@Nullable CharSequence paramCharSequence)
  {
    return (paramCharSequence == null) || (StringsKt.isBlank(paramCharSequence));
  }
  
  @InlineOnly
  private static final boolean isNullOrEmpty(@Nullable CharSequence paramCharSequence)
  {
    return (paramCharSequence == null) || (paramCharSequence.length() == 0);
  }
  
  @NotNull
  public static final CharIterator iterator(@NotNull CharSequence paramCharSequence)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    (CharIterator)new CharIterator()
    {
      private int index;
      
      public boolean hasNext()
      {
        return index < receiver$0.length();
      }
      
      public char nextChar()
      {
        CharSequence localCharSequence = receiver$0;
        int i = index;
        index = (i + 1);
        return localCharSequence.charAt(i);
      }
    };
  }
  
  public static final int lastIndexOf(@NotNull CharSequence paramCharSequence, char paramChar, int paramInt, boolean paramBoolean)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    if ((!paramBoolean) && ((paramCharSequence instanceof String))) {
      return ((String)paramCharSequence).lastIndexOf(paramChar, paramInt);
    }
    return StringsKt.lastIndexOfAny(paramCharSequence, new char[] { paramChar }, paramInt, paramBoolean);
  }
  
  public static final int lastIndexOf(@NotNull CharSequence paramCharSequence, @NotNull String paramString, int paramInt, boolean paramBoolean)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramString, "string");
    if ((!paramBoolean) && ((paramCharSequence instanceof String))) {
      return ((String)paramCharSequence).lastIndexOf(paramString, paramInt);
    }
    return indexOf$StringsKt__StringsKt(paramCharSequence, (CharSequence)paramString, paramInt, 0, paramBoolean, true);
  }
  
  public static final int lastIndexOfAny(@NotNull CharSequence paramCharSequence, @NotNull Collection<String> paramCollection, int paramInt, boolean paramBoolean)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramCollection, "strings");
    paramCharSequence = findAnyOf$StringsKt__StringsKt(paramCharSequence, paramCollection, paramInt, paramBoolean, true);
    if (paramCharSequence != null)
    {
      paramCharSequence = (Integer)paramCharSequence.getFirst();
      if (paramCharSequence != null) {
        return paramCharSequence.intValue();
      }
    }
    return -1;
  }
  
  public static final int lastIndexOfAny(@NotNull CharSequence paramCharSequence, @NotNull char[] paramArrayOfChar, int paramInt, boolean paramBoolean)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramArrayOfChar, "chars");
    int i;
    if ((!paramBoolean) && (paramArrayOfChar.length == 1) && ((paramCharSequence instanceof String)))
    {
      i = ArraysKt.single(paramArrayOfChar);
      return ((String)paramCharSequence).lastIndexOf(i, paramInt);
    }
    paramInt = RangesKt.coerceAtMost(paramInt, StringsKt.getLastIndex(paramCharSequence));
    while (paramInt >= 0)
    {
      char c = paramCharSequence.charAt(paramInt);
      int k = 0;
      int m = paramArrayOfChar.length;
      i = 0;
      int j;
      for (;;)
      {
        j = k;
        if (i >= m) {
          break;
        }
        if (CharsKt.equals(paramArrayOfChar[i], c, paramBoolean))
        {
          j = 1;
          break;
        }
        i += 1;
      }
      if (j != 0) {
        return paramInt;
      }
      paramInt -= 1;
    }
    return -1;
  }
  
  @NotNull
  public static final Sequence<String> lineSequence(@NotNull CharSequence paramCharSequence)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    return StringsKt.splitToSequence$default(paramCharSequence, new String[] { "\r\n", "\n", "\r" }, false, 0, 6, null);
  }
  
  @NotNull
  public static final List<String> lines(@NotNull CharSequence paramCharSequence)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    return SequencesKt.toList(StringsKt.lineSequence(paramCharSequence));
  }
  
  @InlineOnly
  private static final boolean matches(@NotNull CharSequence paramCharSequence, Regex paramRegex)
  {
    return paramRegex.matches(paramCharSequence);
  }
  
  @InlineOnly
  private static final String orEmpty(@Nullable String paramString)
  {
    if (paramString != null) {
      return paramString;
    }
    return "";
  }
  
  @NotNull
  public static final CharSequence padEnd(@NotNull CharSequence paramCharSequence, int paramInt, char paramChar)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    if (paramInt < 0)
    {
      paramCharSequence = new StringBuilder();
      paramCharSequence.append("Desired length ");
      paramCharSequence.append(paramInt);
      paramCharSequence.append(" is less than zero.");
      throw ((Throwable)new IllegalArgumentException(paramCharSequence.toString()));
    }
    if (paramInt <= paramCharSequence.length()) {
      return paramCharSequence.subSequence(0, paramCharSequence.length());
    }
    StringBuilder localStringBuilder = new StringBuilder(paramInt);
    localStringBuilder.append(paramCharSequence);
    int i = paramInt - paramCharSequence.length();
    paramInt = 1;
    if (1 <= i) {
      for (;;)
      {
        localStringBuilder.append(paramChar);
        if (paramInt == i) {
          break;
        }
        paramInt += 1;
      }
    }
    return (CharSequence)localStringBuilder;
  }
  
  @NotNull
  public static final String padEnd(@NotNull String paramString, int paramInt, char paramChar)
  {
    Intrinsics.checkParameterIsNotNull(paramString, "$receiver");
    return StringsKt.padEnd((CharSequence)paramString, paramInt, paramChar).toString();
  }
  
  @NotNull
  public static final CharSequence padStart(@NotNull CharSequence paramCharSequence, int paramInt, char paramChar)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    if (paramInt < 0)
    {
      paramCharSequence = new StringBuilder();
      paramCharSequence.append("Desired length ");
      paramCharSequence.append(paramInt);
      paramCharSequence.append(" is less than zero.");
      throw ((Throwable)new IllegalArgumentException(paramCharSequence.toString()));
    }
    if (paramInt <= paramCharSequence.length()) {
      return paramCharSequence.subSequence(0, paramCharSequence.length());
    }
    StringBuilder localStringBuilder = new StringBuilder(paramInt);
    int i = paramInt - paramCharSequence.length();
    paramInt = 1;
    if (1 <= i) {
      for (;;)
      {
        localStringBuilder.append(paramChar);
        if (paramInt == i) {
          break;
        }
        paramInt += 1;
      }
    }
    localStringBuilder.append(paramCharSequence);
    return (CharSequence)localStringBuilder;
  }
  
  @NotNull
  public static final String padStart(@NotNull String paramString, int paramInt, char paramChar)
  {
    Intrinsics.checkParameterIsNotNull(paramString, "$receiver");
    return StringsKt.padStart((CharSequence)paramString, paramInt, paramChar).toString();
  }
  
  private static final Sequence<IntRange> rangesDelimitedBy$StringsKt__StringsKt(@NotNull CharSequence paramCharSequence, char[] paramArrayOfChar, int paramInt1, final boolean paramBoolean, int paramInt2)
  {
    int i;
    if (paramInt2 >= 0) {
      i = 1;
    } else {
      i = 0;
    }
    if (i == 0)
    {
      paramCharSequence = new StringBuilder();
      paramCharSequence.append("Limit must be non-negative, but was ");
      paramCharSequence.append(paramInt2);
      paramCharSequence.append('.');
      throw ((Throwable)new IllegalArgumentException(paramCharSequence.toString().toString()));
    }
    (Sequence)new DelimitedRangesSequence(paramCharSequence, paramInt1, paramInt2, (Function2)new Lambda(paramArrayOfChar)
    {
      @Nullable
      public final Pair<Integer, Integer> invoke(@NotNull CharSequence paramAnonymousCharSequence, int paramAnonymousInt)
      {
        Intrinsics.checkParameterIsNotNull(paramAnonymousCharSequence, "$receiver");
        paramAnonymousInt = StringsKt.indexOfAny(paramAnonymousCharSequence, $delimiters, paramAnonymousInt, paramBoolean);
        if (paramAnonymousInt < 0) {
          return null;
        }
        return TuplesKt.to(Integer.valueOf(paramAnonymousInt), Integer.valueOf(1));
      }
    });
  }
  
  private static final Sequence<IntRange> rangesDelimitedBy$StringsKt__StringsKt(@NotNull CharSequence paramCharSequence, String[] paramArrayOfString, int paramInt1, final boolean paramBoolean, int paramInt2)
  {
    int i;
    if (paramInt2 >= 0) {
      i = 1;
    } else {
      i = 0;
    }
    if (i == 0)
    {
      paramCharSequence = new StringBuilder();
      paramCharSequence.append("Limit must be non-negative, but was ");
      paramCharSequence.append(paramInt2);
      paramCharSequence.append('.');
      throw ((Throwable)new IllegalArgumentException(paramCharSequence.toString().toString()));
    }
    (Sequence)new DelimitedRangesSequence(paramCharSequence, paramInt1, paramInt2, (Function2)new Lambda(ArraysKt.asList((Object[])paramArrayOfString))
    {
      @Nullable
      public final Pair<Integer, Integer> invoke(@NotNull CharSequence paramAnonymousCharSequence, int paramAnonymousInt)
      {
        Intrinsics.checkParameterIsNotNull(paramAnonymousCharSequence, "$receiver");
        paramAnonymousCharSequence = StringsKt__StringsKt.access$findAnyOf(paramAnonymousCharSequence, (Collection)$delimitersList, paramAnonymousInt, paramBoolean, false);
        if (paramAnonymousCharSequence != null) {
          return TuplesKt.to(paramAnonymousCharSequence.getFirst(), Integer.valueOf(((String)paramAnonymousCharSequence.getSecond()).length()));
        }
        return null;
      }
    });
  }
  
  public static final boolean regionMatchesImpl(@NotNull CharSequence paramCharSequence1, int paramInt1, @NotNull CharSequence paramCharSequence2, int paramInt2, int paramInt3, boolean paramBoolean)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence1, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramCharSequence2, "other");
    if ((paramInt2 >= 0) && (paramInt1 >= 0) && (paramInt1 <= paramCharSequence1.length() - paramInt3))
    {
      if (paramInt2 > paramCharSequence2.length() - paramInt3) {
        return false;
      }
      int i = 0;
      while (i < paramInt3)
      {
        if (!CharsKt.equals(paramCharSequence1.charAt(paramInt1 + i), paramCharSequence2.charAt(paramInt2 + i), paramBoolean)) {
          return false;
        }
        i += 1;
      }
      return true;
    }
    return false;
  }
  
  @NotNull
  public static final CharSequence removePrefix(@NotNull CharSequence paramCharSequence1, @NotNull CharSequence paramCharSequence2)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence1, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramCharSequence2, "prefix");
    if (StringsKt.startsWith$default(paramCharSequence1, paramCharSequence2, false, 2, null)) {
      return paramCharSequence1.subSequence(paramCharSequence2.length(), paramCharSequence1.length());
    }
    return paramCharSequence1.subSequence(0, paramCharSequence1.length());
  }
  
  @NotNull
  public static final String removePrefix(@NotNull String paramString, @NotNull CharSequence paramCharSequence)
  {
    Intrinsics.checkParameterIsNotNull(paramString, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "prefix");
    if (StringsKt.startsWith$default((CharSequence)paramString, paramCharSequence, false, 2, null))
    {
      paramString = paramString.substring(paramCharSequence.length());
      Intrinsics.checkExpressionValueIsNotNull(paramString, "(this as java.lang.String).substring(startIndex)");
      return paramString;
    }
    return paramString;
  }
  
  @NotNull
  public static final CharSequence removeRange(@NotNull CharSequence paramCharSequence, int paramInt1, int paramInt2)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    if (paramInt2 < paramInt1)
    {
      paramCharSequence = new StringBuilder();
      paramCharSequence.append("End index (");
      paramCharSequence.append(paramInt2);
      paramCharSequence.append(") is less than start index (");
      paramCharSequence.append(paramInt1);
      paramCharSequence.append(").");
      throw ((Throwable)new IndexOutOfBoundsException(paramCharSequence.toString()));
    }
    if (paramInt2 == paramInt1) {
      return paramCharSequence.subSequence(0, paramCharSequence.length());
    }
    StringBuilder localStringBuilder = new StringBuilder(paramCharSequence.length() - (paramInt2 - paramInt1));
    localStringBuilder.append(paramCharSequence, 0, paramInt1);
    localStringBuilder.append(paramCharSequence, paramInt2, paramCharSequence.length());
    return (CharSequence)localStringBuilder;
  }
  
  @NotNull
  public static final CharSequence removeRange(@NotNull CharSequence paramCharSequence, @NotNull IntRange paramIntRange)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramIntRange, "range");
    return StringsKt.removeRange(paramCharSequence, paramIntRange.getStart().intValue(), paramIntRange.getEndInclusive().intValue() + 1);
  }
  
  @InlineOnly
  private static final String removeRange(@NotNull String paramString, int paramInt1, int paramInt2)
  {
    if (paramString == null) {
      throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
    }
    return StringsKt.removeRange((CharSequence)paramString, paramInt1, paramInt2).toString();
  }
  
  @InlineOnly
  private static final String removeRange(@NotNull String paramString, IntRange paramIntRange)
  {
    if (paramString == null) {
      throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
    }
    return StringsKt.removeRange((CharSequence)paramString, paramIntRange).toString();
  }
  
  @NotNull
  public static final CharSequence removeSuffix(@NotNull CharSequence paramCharSequence1, @NotNull CharSequence paramCharSequence2)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence1, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramCharSequence2, "suffix");
    if (StringsKt.endsWith$default(paramCharSequence1, paramCharSequence2, false, 2, null)) {
      return paramCharSequence1.subSequence(0, paramCharSequence1.length() - paramCharSequence2.length());
    }
    return paramCharSequence1.subSequence(0, paramCharSequence1.length());
  }
  
  @NotNull
  public static final String removeSuffix(@NotNull String paramString, @NotNull CharSequence paramCharSequence)
  {
    Intrinsics.checkParameterIsNotNull(paramString, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "suffix");
    if (StringsKt.endsWith$default((CharSequence)paramString, paramCharSequence, false, 2, null))
    {
      paramString = paramString.substring(0, paramString.length() - paramCharSequence.length());
      Intrinsics.checkExpressionValueIsNotNull(paramString, "(this as java.lang.Strin…ing(startIndex, endIndex)");
      return paramString;
    }
    return paramString;
  }
  
  @NotNull
  public static final CharSequence removeSurrounding(@NotNull CharSequence paramCharSequence1, @NotNull CharSequence paramCharSequence2)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence1, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramCharSequence2, "delimiter");
    return StringsKt.removeSurrounding(paramCharSequence1, paramCharSequence2, paramCharSequence2);
  }
  
  @NotNull
  public static final CharSequence removeSurrounding(@NotNull CharSequence paramCharSequence1, @NotNull CharSequence paramCharSequence2, @NotNull CharSequence paramCharSequence3)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence1, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramCharSequence2, "prefix");
    Intrinsics.checkParameterIsNotNull(paramCharSequence3, "suffix");
    if ((paramCharSequence1.length() >= paramCharSequence2.length() + paramCharSequence3.length()) && (StringsKt.startsWith$default(paramCharSequence1, paramCharSequence2, false, 2, null)) && (StringsKt.endsWith$default(paramCharSequence1, paramCharSequence3, false, 2, null))) {
      return paramCharSequence1.subSequence(paramCharSequence2.length(), paramCharSequence1.length() - paramCharSequence3.length());
    }
    return paramCharSequence1.subSequence(0, paramCharSequence1.length());
  }
  
  @NotNull
  public static final String removeSurrounding(@NotNull String paramString, @NotNull CharSequence paramCharSequence)
  {
    Intrinsics.checkParameterIsNotNull(paramString, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "delimiter");
    return StringsKt.removeSurrounding(paramString, paramCharSequence, paramCharSequence);
  }
  
  @NotNull
  public static final String removeSurrounding(@NotNull String paramString, @NotNull CharSequence paramCharSequence1, @NotNull CharSequence paramCharSequence2)
  {
    Intrinsics.checkParameterIsNotNull(paramString, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramCharSequence1, "prefix");
    Intrinsics.checkParameterIsNotNull(paramCharSequence2, "suffix");
    if (paramString.length() >= paramCharSequence1.length() + paramCharSequence2.length())
    {
      CharSequence localCharSequence = (CharSequence)paramString;
      if ((StringsKt.startsWith$default(localCharSequence, paramCharSequence1, false, 2, null)) && (StringsKt.endsWith$default(localCharSequence, paramCharSequence2, false, 2, null)))
      {
        paramString = paramString.substring(paramCharSequence1.length(), paramString.length() - paramCharSequence2.length());
        Intrinsics.checkExpressionValueIsNotNull(paramString, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        return paramString;
      }
    }
    return paramString;
  }
  
  @InlineOnly
  private static final String replace(@NotNull CharSequence paramCharSequence, Regex paramRegex, String paramString)
  {
    return paramRegex.replace(paramCharSequence, paramString);
  }
  
  @InlineOnly
  private static final String replace(@NotNull CharSequence paramCharSequence, Regex paramRegex, Function1<? super MatchResult, ? extends CharSequence> paramFunction1)
  {
    return paramRegex.replace(paramCharSequence, paramFunction1);
  }
  
  @NotNull
  public static final String replaceAfter(@NotNull String paramString1, char paramChar, @NotNull String paramString2, @NotNull String paramString3)
  {
    Intrinsics.checkParameterIsNotNull(paramString1, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramString2, "replacement");
    Intrinsics.checkParameterIsNotNull(paramString3, "missingDelimiterValue");
    CharSequence localCharSequence = (CharSequence)paramString1;
    int i = StringsKt.indexOf$default(localCharSequence, paramChar, 0, false, 6, null);
    if (i == -1) {
      return paramString3;
    }
    return StringsKt.replaceRange(localCharSequence, i + 1, paramString1.length(), (CharSequence)paramString2).toString();
  }
  
  @NotNull
  public static final String replaceAfter(@NotNull String paramString1, @NotNull String paramString2, @NotNull String paramString3, @NotNull String paramString4)
  {
    Intrinsics.checkParameterIsNotNull(paramString1, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramString2, "delimiter");
    Intrinsics.checkParameterIsNotNull(paramString3, "replacement");
    Intrinsics.checkParameterIsNotNull(paramString4, "missingDelimiterValue");
    CharSequence localCharSequence = (CharSequence)paramString1;
    int i = StringsKt.indexOf$default(localCharSequence, paramString2, 0, false, 6, null);
    if (i == -1) {
      return paramString4;
    }
    return StringsKt.replaceRange(localCharSequence, i + paramString2.length(), paramString1.length(), (CharSequence)paramString3).toString();
  }
  
  @NotNull
  public static final String replaceAfterLast(@NotNull String paramString1, char paramChar, @NotNull String paramString2, @NotNull String paramString3)
  {
    Intrinsics.checkParameterIsNotNull(paramString1, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramString2, "replacement");
    Intrinsics.checkParameterIsNotNull(paramString3, "missingDelimiterValue");
    CharSequence localCharSequence = (CharSequence)paramString1;
    int i = StringsKt.lastIndexOf$default(localCharSequence, paramChar, 0, false, 6, null);
    if (i == -1) {
      return paramString3;
    }
    return StringsKt.replaceRange(localCharSequence, i + 1, paramString1.length(), (CharSequence)paramString2).toString();
  }
  
  @NotNull
  public static final String replaceAfterLast(@NotNull String paramString1, @NotNull String paramString2, @NotNull String paramString3, @NotNull String paramString4)
  {
    Intrinsics.checkParameterIsNotNull(paramString1, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramString2, "delimiter");
    Intrinsics.checkParameterIsNotNull(paramString3, "replacement");
    Intrinsics.checkParameterIsNotNull(paramString4, "missingDelimiterValue");
    CharSequence localCharSequence = (CharSequence)paramString1;
    int i = StringsKt.lastIndexOf$default(localCharSequence, paramString2, 0, false, 6, null);
    if (i == -1) {
      return paramString4;
    }
    return StringsKt.replaceRange(localCharSequence, i + paramString2.length(), paramString1.length(), (CharSequence)paramString3).toString();
  }
  
  @NotNull
  public static final String replaceBefore(@NotNull String paramString1, char paramChar, @NotNull String paramString2, @NotNull String paramString3)
  {
    Intrinsics.checkParameterIsNotNull(paramString1, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramString2, "replacement");
    Intrinsics.checkParameterIsNotNull(paramString3, "missingDelimiterValue");
    paramString1 = (CharSequence)paramString1;
    int i = StringsKt.indexOf$default(paramString1, paramChar, 0, false, 6, null);
    if (i == -1) {
      return paramString3;
    }
    return StringsKt.replaceRange(paramString1, 0, i, (CharSequence)paramString2).toString();
  }
  
  @NotNull
  public static final String replaceBefore(@NotNull String paramString1, @NotNull String paramString2, @NotNull String paramString3, @NotNull String paramString4)
  {
    Intrinsics.checkParameterIsNotNull(paramString1, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramString2, "delimiter");
    Intrinsics.checkParameterIsNotNull(paramString3, "replacement");
    Intrinsics.checkParameterIsNotNull(paramString4, "missingDelimiterValue");
    paramString1 = (CharSequence)paramString1;
    int i = StringsKt.indexOf$default(paramString1, paramString2, 0, false, 6, null);
    if (i == -1) {
      return paramString4;
    }
    return StringsKt.replaceRange(paramString1, 0, i, (CharSequence)paramString3).toString();
  }
  
  @NotNull
  public static final String replaceBeforeLast(@NotNull String paramString1, char paramChar, @NotNull String paramString2, @NotNull String paramString3)
  {
    Intrinsics.checkParameterIsNotNull(paramString1, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramString2, "replacement");
    Intrinsics.checkParameterIsNotNull(paramString3, "missingDelimiterValue");
    paramString1 = (CharSequence)paramString1;
    int i = StringsKt.lastIndexOf$default(paramString1, paramChar, 0, false, 6, null);
    if (i == -1) {
      return paramString3;
    }
    return StringsKt.replaceRange(paramString1, 0, i, (CharSequence)paramString2).toString();
  }
  
  @NotNull
  public static final String replaceBeforeLast(@NotNull String paramString1, @NotNull String paramString2, @NotNull String paramString3, @NotNull String paramString4)
  {
    Intrinsics.checkParameterIsNotNull(paramString1, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramString2, "delimiter");
    Intrinsics.checkParameterIsNotNull(paramString3, "replacement");
    Intrinsics.checkParameterIsNotNull(paramString4, "missingDelimiterValue");
    paramString1 = (CharSequence)paramString1;
    int i = StringsKt.lastIndexOf$default(paramString1, paramString2, 0, false, 6, null);
    if (i == -1) {
      return paramString4;
    }
    return StringsKt.replaceRange(paramString1, 0, i, (CharSequence)paramString3).toString();
  }
  
  @InlineOnly
  private static final String replaceFirst(@NotNull CharSequence paramCharSequence, Regex paramRegex, String paramString)
  {
    return paramRegex.replaceFirst(paramCharSequence, paramString);
  }
  
  @NotNull
  public static final CharSequence replaceRange(@NotNull CharSequence paramCharSequence1, int paramInt1, int paramInt2, @NotNull CharSequence paramCharSequence2)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence1, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramCharSequence2, "replacement");
    if (paramInt2 < paramInt1)
    {
      paramCharSequence1 = new StringBuilder();
      paramCharSequence1.append("End index (");
      paramCharSequence1.append(paramInt2);
      paramCharSequence1.append(") is less than start index (");
      paramCharSequence1.append(paramInt1);
      paramCharSequence1.append(").");
      throw ((Throwable)new IndexOutOfBoundsException(paramCharSequence1.toString()));
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramCharSequence1, 0, paramInt1);
    localStringBuilder.append(paramCharSequence2);
    localStringBuilder.append(paramCharSequence1, paramInt2, paramCharSequence1.length());
    return (CharSequence)localStringBuilder;
  }
  
  @NotNull
  public static final CharSequence replaceRange(@NotNull CharSequence paramCharSequence1, @NotNull IntRange paramIntRange, @NotNull CharSequence paramCharSequence2)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence1, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramIntRange, "range");
    Intrinsics.checkParameterIsNotNull(paramCharSequence2, "replacement");
    return StringsKt.replaceRange(paramCharSequence1, paramIntRange.getStart().intValue(), paramIntRange.getEndInclusive().intValue() + 1, paramCharSequence2);
  }
  
  @InlineOnly
  private static final String replaceRange(@NotNull String paramString, int paramInt1, int paramInt2, CharSequence paramCharSequence)
  {
    if (paramString == null) {
      throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
    }
    return StringsKt.replaceRange((CharSequence)paramString, paramInt1, paramInt2, paramCharSequence).toString();
  }
  
  @InlineOnly
  private static final String replaceRange(@NotNull String paramString, IntRange paramIntRange, CharSequence paramCharSequence)
  {
    if (paramString == null) {
      throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
    }
    return StringsKt.replaceRange((CharSequence)paramString, paramIntRange, paramCharSequence).toString();
  }
  
  @InlineOnly
  private static final List<String> split(@NotNull CharSequence paramCharSequence, Regex paramRegex, int paramInt)
  {
    return paramRegex.split(paramCharSequence, paramInt);
  }
  
  @NotNull
  public static final List<String> split(@NotNull CharSequence paramCharSequence, @NotNull char[] paramArrayOfChar, boolean paramBoolean, int paramInt)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramArrayOfChar, "delimiters");
    if (paramArrayOfChar.length == 1) {
      return split$StringsKt__StringsKt(paramCharSequence, String.valueOf(paramArrayOfChar[0]), paramBoolean, paramInt);
    }
    Object localObject = SequencesKt.asIterable(rangesDelimitedBy$StringsKt__StringsKt$default(paramCharSequence, paramArrayOfChar, 0, paramBoolean, paramInt, 2, null));
    paramArrayOfChar = (Collection)new ArrayList(CollectionsKt.collectionSizeOrDefault((Iterable)localObject, 10));
    localObject = ((Iterable)localObject).iterator();
    while (((Iterator)localObject).hasNext()) {
      paramArrayOfChar.add(StringsKt.substring(paramCharSequence, (IntRange)((Iterator)localObject).next()));
    }
    return (List)paramArrayOfChar;
  }
  
  @NotNull
  public static final List<String> split(@NotNull CharSequence paramCharSequence, @NotNull String[] paramArrayOfString, boolean paramBoolean, int paramInt)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramArrayOfString, "delimiters");
    if (((Object[])paramArrayOfString).length == 1)
    {
      int i = 0;
      localObject = paramArrayOfString[0];
      if (((CharSequence)localObject).length() == 0) {
        i = 1;
      }
      if (i == 0) {
        return split$StringsKt__StringsKt(paramCharSequence, (String)localObject, paramBoolean, paramInt);
      }
    }
    Object localObject = SequencesKt.asIterable(rangesDelimitedBy$StringsKt__StringsKt$default(paramCharSequence, paramArrayOfString, 0, paramBoolean, paramInt, 2, null));
    paramArrayOfString = (Collection)new ArrayList(CollectionsKt.collectionSizeOrDefault((Iterable)localObject, 10));
    localObject = ((Iterable)localObject).iterator();
    while (((Iterator)localObject).hasNext()) {
      paramArrayOfString.add(StringsKt.substring(paramCharSequence, (IntRange)((Iterator)localObject).next()));
    }
    return (List)paramArrayOfString;
  }
  
  private static final List<String> split$StringsKt__StringsKt(@NotNull CharSequence paramCharSequence, String paramString, boolean paramBoolean, int paramInt)
  {
    int k = 0;
    int i;
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
    int m = StringsKt.indexOf(paramCharSequence, paramString, 0, paramBoolean);
    if ((m != -1) && (paramInt != 1))
    {
      if (paramInt > 0) {
        i = 1;
      } else {
        i = 0;
      }
      int j = 10;
      if (i != 0) {
        j = RangesKt.coerceAtMost(paramInt, 10);
      }
      ArrayList localArrayList = new ArrayList(j);
      j = m;
      int n;
      do
      {
        localArrayList.add(paramCharSequence.subSequence(k, j).toString());
        m = paramString.length() + j;
        if ((i != 0) && (localArrayList.size() == paramInt - 1)) {
          break;
        }
        n = StringsKt.indexOf(paramCharSequence, paramString, m, paramBoolean);
        k = m;
        j = n;
      } while (n != -1);
      localArrayList.add(paramCharSequence.subSequence(m, paramCharSequence.length()).toString());
      return (List)localArrayList;
    }
    return CollectionsKt.listOf(paramCharSequence.toString());
  }
  
  @NotNull
  public static final Sequence<String> splitToSequence(@NotNull CharSequence paramCharSequence, @NotNull char[] paramArrayOfChar, boolean paramBoolean, int paramInt)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramArrayOfChar, "delimiters");
    SequencesKt.map(rangesDelimitedBy$StringsKt__StringsKt$default(paramCharSequence, paramArrayOfChar, 0, paramBoolean, paramInt, 2, null), (Function1)new Lambda(paramCharSequence)
    {
      @NotNull
      public final String invoke(@NotNull IntRange paramAnonymousIntRange)
      {
        Intrinsics.checkParameterIsNotNull(paramAnonymousIntRange, "it");
        return StringsKt.substring(receiver$0, paramAnonymousIntRange);
      }
    });
  }
  
  @NotNull
  public static final Sequence<String> splitToSequence(@NotNull CharSequence paramCharSequence, @NotNull String[] paramArrayOfString, boolean paramBoolean, int paramInt)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramArrayOfString, "delimiters");
    SequencesKt.map(rangesDelimitedBy$StringsKt__StringsKt$default(paramCharSequence, paramArrayOfString, 0, paramBoolean, paramInt, 2, null), (Function1)new Lambda(paramCharSequence)
    {
      @NotNull
      public final String invoke(@NotNull IntRange paramAnonymousIntRange)
      {
        Intrinsics.checkParameterIsNotNull(paramAnonymousIntRange, "it");
        return StringsKt.substring(receiver$0, paramAnonymousIntRange);
      }
    });
  }
  
  public static final boolean startsWith(@NotNull CharSequence paramCharSequence, char paramChar, boolean paramBoolean)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    int i = paramCharSequence.length();
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (i > 0)
    {
      bool1 = bool2;
      if (CharsKt.equals(paramCharSequence.charAt(0), paramChar, paramBoolean)) {
        bool1 = true;
      }
    }
    return bool1;
  }
  
  public static final boolean startsWith(@NotNull CharSequence paramCharSequence1, @NotNull CharSequence paramCharSequence2, int paramInt, boolean paramBoolean)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence1, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramCharSequence2, "prefix");
    if ((!paramBoolean) && ((paramCharSequence1 instanceof String)) && ((paramCharSequence2 instanceof String))) {
      return StringsKt.startsWith$default((String)paramCharSequence1, (String)paramCharSequence2, paramInt, false, 4, null);
    }
    return StringsKt.regionMatchesImpl(paramCharSequence1, paramInt, paramCharSequence2, 0, paramCharSequence2.length(), paramBoolean);
  }
  
  public static final boolean startsWith(@NotNull CharSequence paramCharSequence1, @NotNull CharSequence paramCharSequence2, boolean paramBoolean)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence1, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramCharSequence2, "prefix");
    if ((!paramBoolean) && ((paramCharSequence1 instanceof String)) && ((paramCharSequence2 instanceof String))) {
      return StringsKt.startsWith$default((String)paramCharSequence1, (String)paramCharSequence2, false, 2, null);
    }
    return StringsKt.regionMatchesImpl(paramCharSequence1, 0, paramCharSequence2, 0, paramCharSequence2.length(), paramBoolean);
  }
  
  @NotNull
  public static final CharSequence subSequence(@NotNull CharSequence paramCharSequence, @NotNull IntRange paramIntRange)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramIntRange, "range");
    return paramCharSequence.subSequence(paramIntRange.getStart().intValue(), paramIntRange.getEndInclusive().intValue() + 1);
  }
  
  @Deprecated(message="Use parameters named startIndex and endIndex.", replaceWith=@ReplaceWith(expression="subSequence(startIndex = start, endIndex = end)", imports={}))
  @InlineOnly
  private static final CharSequence subSequence(@NotNull String paramString, int paramInt1, int paramInt2)
  {
    return paramString.subSequence(paramInt1, paramInt2);
  }
  
  @InlineOnly
  private static final String substring(@NotNull CharSequence paramCharSequence, int paramInt1, int paramInt2)
  {
    return paramCharSequence.subSequence(paramInt1, paramInt2).toString();
  }
  
  @NotNull
  public static final String substring(@NotNull CharSequence paramCharSequence, @NotNull IntRange paramIntRange)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramIntRange, "range");
    return paramCharSequence.subSequence(paramIntRange.getStart().intValue(), paramIntRange.getEndInclusive().intValue() + 1).toString();
  }
  
  @NotNull
  public static final String substring(@NotNull String paramString, @NotNull IntRange paramIntRange)
  {
    Intrinsics.checkParameterIsNotNull(paramString, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramIntRange, "range");
    paramString = paramString.substring(paramIntRange.getStart().intValue(), paramIntRange.getEndInclusive().intValue() + 1);
    Intrinsics.checkExpressionValueIsNotNull(paramString, "(this as java.lang.Strin…ing(startIndex, endIndex)");
    return paramString;
  }
  
  @NotNull
  public static final String substringAfter(@NotNull String paramString1, char paramChar, @NotNull String paramString2)
  {
    Intrinsics.checkParameterIsNotNull(paramString1, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramString2, "missingDelimiterValue");
    int i = StringsKt.indexOf$default((CharSequence)paramString1, paramChar, 0, false, 6, null);
    if (i == -1) {
      return paramString2;
    }
    paramString1 = paramString1.substring(i + 1, paramString1.length());
    Intrinsics.checkExpressionValueIsNotNull(paramString1, "(this as java.lang.Strin…ing(startIndex, endIndex)");
    return paramString1;
  }
  
  @NotNull
  public static final String substringAfter(@NotNull String paramString1, @NotNull String paramString2, @NotNull String paramString3)
  {
    Intrinsics.checkParameterIsNotNull(paramString1, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramString2, "delimiter");
    Intrinsics.checkParameterIsNotNull(paramString3, "missingDelimiterValue");
    int i = StringsKt.indexOf$default((CharSequence)paramString1, paramString2, 0, false, 6, null);
    if (i == -1) {
      return paramString3;
    }
    paramString1 = paramString1.substring(i + paramString2.length(), paramString1.length());
    Intrinsics.checkExpressionValueIsNotNull(paramString1, "(this as java.lang.Strin…ing(startIndex, endIndex)");
    return paramString1;
  }
  
  @NotNull
  public static final String substringAfterLast(@NotNull String paramString1, char paramChar, @NotNull String paramString2)
  {
    Intrinsics.checkParameterIsNotNull(paramString1, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramString2, "missingDelimiterValue");
    int i = StringsKt.lastIndexOf$default((CharSequence)paramString1, paramChar, 0, false, 6, null);
    if (i == -1) {
      return paramString2;
    }
    paramString1 = paramString1.substring(i + 1, paramString1.length());
    Intrinsics.checkExpressionValueIsNotNull(paramString1, "(this as java.lang.Strin…ing(startIndex, endIndex)");
    return paramString1;
  }
  
  @NotNull
  public static final String substringAfterLast(@NotNull String paramString1, @NotNull String paramString2, @NotNull String paramString3)
  {
    Intrinsics.checkParameterIsNotNull(paramString1, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramString2, "delimiter");
    Intrinsics.checkParameterIsNotNull(paramString3, "missingDelimiterValue");
    int i = StringsKt.lastIndexOf$default((CharSequence)paramString1, paramString2, 0, false, 6, null);
    if (i == -1) {
      return paramString3;
    }
    paramString1 = paramString1.substring(i + paramString2.length(), paramString1.length());
    Intrinsics.checkExpressionValueIsNotNull(paramString1, "(this as java.lang.Strin…ing(startIndex, endIndex)");
    return paramString1;
  }
  
  @NotNull
  public static final String substringBefore(@NotNull String paramString1, char paramChar, @NotNull String paramString2)
  {
    Intrinsics.checkParameterIsNotNull(paramString1, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramString2, "missingDelimiterValue");
    int i = StringsKt.indexOf$default((CharSequence)paramString1, paramChar, 0, false, 6, null);
    if (i == -1) {
      return paramString2;
    }
    paramString1 = paramString1.substring(0, i);
    Intrinsics.checkExpressionValueIsNotNull(paramString1, "(this as java.lang.Strin…ing(startIndex, endIndex)");
    return paramString1;
  }
  
  @NotNull
  public static final String substringBefore(@NotNull String paramString1, @NotNull String paramString2, @NotNull String paramString3)
  {
    Intrinsics.checkParameterIsNotNull(paramString1, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramString2, "delimiter");
    Intrinsics.checkParameterIsNotNull(paramString3, "missingDelimiterValue");
    int i = StringsKt.indexOf$default((CharSequence)paramString1, paramString2, 0, false, 6, null);
    if (i == -1) {
      return paramString3;
    }
    paramString1 = paramString1.substring(0, i);
    Intrinsics.checkExpressionValueIsNotNull(paramString1, "(this as java.lang.Strin…ing(startIndex, endIndex)");
    return paramString1;
  }
  
  @NotNull
  public static final String substringBeforeLast(@NotNull String paramString1, char paramChar, @NotNull String paramString2)
  {
    Intrinsics.checkParameterIsNotNull(paramString1, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramString2, "missingDelimiterValue");
    int i = StringsKt.lastIndexOf$default((CharSequence)paramString1, paramChar, 0, false, 6, null);
    if (i == -1) {
      return paramString2;
    }
    paramString1 = paramString1.substring(0, i);
    Intrinsics.checkExpressionValueIsNotNull(paramString1, "(this as java.lang.Strin…ing(startIndex, endIndex)");
    return paramString1;
  }
  
  @NotNull
  public static final String substringBeforeLast(@NotNull String paramString1, @NotNull String paramString2, @NotNull String paramString3)
  {
    Intrinsics.checkParameterIsNotNull(paramString1, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramString2, "delimiter");
    Intrinsics.checkParameterIsNotNull(paramString3, "missingDelimiterValue");
    int i = StringsKt.lastIndexOf$default((CharSequence)paramString1, paramString2, 0, false, 6, null);
    if (i == -1) {
      return paramString3;
    }
    paramString1 = paramString1.substring(0, i);
    Intrinsics.checkExpressionValueIsNotNull(paramString1, "(this as java.lang.Strin…ing(startIndex, endIndex)");
    return paramString1;
  }
  
  @NotNull
  public static final CharSequence trim(@NotNull CharSequence paramCharSequence)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    int j = paramCharSequence.length() - 1;
    int i = 0;
    int k = 0;
    while (i <= j)
    {
      int m;
      if (k == 0) {
        m = i;
      } else {
        m = j;
      }
      boolean bool = CharsKt.isWhitespace(paramCharSequence.charAt(m));
      if (k == 0)
      {
        if (!bool) {
          k = 1;
        } else {
          i += 1;
        }
      }
      else
      {
        if (!bool) {
          break;
        }
        j -= 1;
      }
    }
    return paramCharSequence.subSequence(i, j + 1);
  }
  
  @NotNull
  public static final CharSequence trim(@NotNull CharSequence paramCharSequence, @NotNull Function1<? super Character, Boolean> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "predicate");
    int j = paramCharSequence.length() - 1;
    int i = 0;
    int k = 0;
    while (i <= j)
    {
      int m;
      if (k == 0) {
        m = i;
      } else {
        m = j;
      }
      boolean bool = ((Boolean)paramFunction1.invoke(Character.valueOf(paramCharSequence.charAt(m)))).booleanValue();
      if (k == 0)
      {
        if (!bool) {
          k = 1;
        } else {
          i += 1;
        }
      }
      else
      {
        if (!bool) {
          break;
        }
        j -= 1;
      }
    }
    return paramCharSequence.subSequence(i, j + 1);
  }
  
  @NotNull
  public static final CharSequence trim(@NotNull CharSequence paramCharSequence, @NotNull char... paramVarArgs)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramVarArgs, "chars");
    int j = paramCharSequence.length() - 1;
    int i = 0;
    int k = 0;
    while (i <= j)
    {
      int m;
      if (k == 0) {
        m = i;
      } else {
        m = j;
      }
      boolean bool = ArraysKt.contains(paramVarArgs, paramCharSequence.charAt(m));
      if (k == 0)
      {
        if (!bool) {
          k = 1;
        } else {
          i += 1;
        }
      }
      else
      {
        if (!bool) {
          break;
        }
        j -= 1;
      }
    }
    return paramCharSequence.subSequence(i, j + 1);
  }
  
  @InlineOnly
  private static final String trim(@NotNull String paramString)
  {
    if (paramString == null) {
      throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
    }
    return StringsKt.trim((CharSequence)paramString).toString();
  }
  
  @NotNull
  public static final String trim(@NotNull String paramString, @NotNull Function1<? super Character, Boolean> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramString, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "predicate");
    paramString = (CharSequence)paramString;
    int j = paramString.length() - 1;
    int i = 0;
    int k = 0;
    while (i <= j)
    {
      int m;
      if (k == 0) {
        m = i;
      } else {
        m = j;
      }
      boolean bool = ((Boolean)paramFunction1.invoke(Character.valueOf(paramString.charAt(m)))).booleanValue();
      if (k == 0)
      {
        if (!bool) {
          k = 1;
        } else {
          i += 1;
        }
      }
      else
      {
        if (!bool) {
          break;
        }
        j -= 1;
      }
    }
    return paramString.subSequence(i, j + 1).toString();
  }
  
  @NotNull
  public static final String trim(@NotNull String paramString, @NotNull char... paramVarArgs)
  {
    Intrinsics.checkParameterIsNotNull(paramString, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramVarArgs, "chars");
    paramString = (CharSequence)paramString;
    int j = paramString.length() - 1;
    int i = 0;
    int k = 0;
    while (i <= j)
    {
      int m;
      if (k == 0) {
        m = i;
      } else {
        m = j;
      }
      boolean bool = ArraysKt.contains(paramVarArgs, paramString.charAt(m));
      if (k == 0)
      {
        if (!bool) {
          k = 1;
        } else {
          i += 1;
        }
      }
      else
      {
        if (!bool) {
          break;
        }
        j -= 1;
      }
    }
    return paramString.subSequence(i, j + 1).toString();
  }
  
  @NotNull
  public static final CharSequence trimEnd(@NotNull CharSequence paramCharSequence)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    int i = paramCharSequence.length();
    int j;
    do
    {
      j = i - 1;
      if (j < 0) {
        break;
      }
      i = j;
    } while (CharsKt.isWhitespace(paramCharSequence.charAt(j)));
    return (CharSequence)paramCharSequence.subSequence(0, j + 1).toString();
    return (CharSequence)"";
  }
  
  @NotNull
  public static final CharSequence trimEnd(@NotNull CharSequence paramCharSequence, @NotNull Function1<? super Character, Boolean> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "predicate");
    int i = paramCharSequence.length();
    int j;
    do
    {
      j = i - 1;
      if (j < 0) {
        break;
      }
      i = j;
    } while (((Boolean)paramFunction1.invoke(Character.valueOf(paramCharSequence.charAt(j)))).booleanValue());
    return (CharSequence)paramCharSequence.subSequence(0, j + 1).toString();
    return (CharSequence)"";
  }
  
  @NotNull
  public static final CharSequence trimEnd(@NotNull CharSequence paramCharSequence, @NotNull char... paramVarArgs)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramVarArgs, "chars");
    int i = paramCharSequence.length();
    int j;
    do
    {
      j = i - 1;
      if (j < 0) {
        break;
      }
      i = j;
    } while (ArraysKt.contains(paramVarArgs, paramCharSequence.charAt(j)));
    return (CharSequence)paramCharSequence.subSequence(0, j + 1).toString();
    return (CharSequence)"";
  }
  
  @InlineOnly
  private static final String trimEnd(@NotNull String paramString)
  {
    if (paramString == null) {
      throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
    }
    return StringsKt.trimEnd((CharSequence)paramString).toString();
  }
  
  @NotNull
  public static final String trimEnd(@NotNull String paramString, @NotNull Function1<? super Character, Boolean> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramString, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "predicate");
    paramString = (CharSequence)paramString;
    int i = paramString.length();
    int j;
    do
    {
      j = i - 1;
      if (j < 0) {
        break;
      }
      i = j;
    } while (((Boolean)paramFunction1.invoke(Character.valueOf(paramString.charAt(j)))).booleanValue());
    paramString = (CharSequence)paramString.subSequence(0, j + 1).toString();
    break label87;
    paramString = (CharSequence)"";
    label87:
    return paramString.toString();
  }
  
  @NotNull
  public static final String trimEnd(@NotNull String paramString, @NotNull char... paramVarArgs)
  {
    Intrinsics.checkParameterIsNotNull(paramString, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramVarArgs, "chars");
    paramString = (CharSequence)paramString;
    int i = paramString.length();
    int j;
    do
    {
      j = i - 1;
      if (j < 0) {
        break;
      }
      i = j;
    } while (ArraysKt.contains(paramVarArgs, paramString.charAt(j)));
    paramString = (CharSequence)paramString.subSequence(0, j + 1).toString();
    break label76;
    paramString = (CharSequence)"";
    label76:
    return paramString.toString();
  }
  
  @NotNull
  public static final CharSequence trimStart(@NotNull CharSequence paramCharSequence)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    int j = paramCharSequence.length();
    int i = 0;
    while (i < j)
    {
      if (!CharsKt.isWhitespace(paramCharSequence.charAt(i))) {
        return paramCharSequence.subSequence(i, paramCharSequence.length());
      }
      i += 1;
    }
    return (CharSequence)"";
  }
  
  @NotNull
  public static final CharSequence trimStart(@NotNull CharSequence paramCharSequence, @NotNull Function1<? super Character, Boolean> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "predicate");
    int j = paramCharSequence.length();
    int i = 0;
    while (i < j)
    {
      if (!((Boolean)paramFunction1.invoke(Character.valueOf(paramCharSequence.charAt(i)))).booleanValue()) {
        return paramCharSequence.subSequence(i, paramCharSequence.length());
      }
      i += 1;
    }
    return (CharSequence)"";
  }
  
  @NotNull
  public static final CharSequence trimStart(@NotNull CharSequence paramCharSequence, @NotNull char... paramVarArgs)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramVarArgs, "chars");
    int j = paramCharSequence.length();
    int i = 0;
    while (i < j)
    {
      if (!ArraysKt.contains(paramVarArgs, paramCharSequence.charAt(i))) {
        return paramCharSequence.subSequence(i, paramCharSequence.length());
      }
      i += 1;
    }
    return (CharSequence)"";
  }
  
  @InlineOnly
  private static final String trimStart(@NotNull String paramString)
  {
    if (paramString == null) {
      throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
    }
    return StringsKt.trimStart((CharSequence)paramString).toString();
  }
  
  @NotNull
  public static final String trimStart(@NotNull String paramString, @NotNull Function1<? super Character, Boolean> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramString, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "predicate");
    paramString = (CharSequence)paramString;
    int j = paramString.length();
    int i = 0;
    while (i < j)
    {
      if (!((Boolean)paramFunction1.invoke(Character.valueOf(paramString.charAt(i)))).booleanValue())
      {
        paramString = paramString.subSequence(i, paramString.length());
        break label88;
      }
      i += 1;
    }
    paramString = (CharSequence)"";
    label88:
    return paramString.toString();
  }
  
  @NotNull
  public static final String trimStart(@NotNull String paramString, @NotNull char... paramVarArgs)
  {
    Intrinsics.checkParameterIsNotNull(paramString, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramVarArgs, "chars");
    paramString = (CharSequence)paramString;
    int j = paramString.length();
    int i = 0;
    while (i < j)
    {
      if (!ArraysKt.contains(paramVarArgs, paramString.charAt(i)))
      {
        paramString = paramString.subSequence(i, paramString.length());
        break label77;
      }
      i += 1;
    }
    paramString = (CharSequence)"";
    label77:
    return paramString.toString();
  }
}
