package kotlin.text;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.ArraysKt;
import kotlin.collections.IntIterator;
import kotlin.internal.InlineOnly;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.sequences.SequencesKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000x\n\000\n\002\030\002\n\002\020\016\n\002\030\002\n\002\b\004\n\002\030\002\n\000\n\002\030\002\n\000\n\002\020\022\n\000\n\002\030\002\n\000\n\002\020\b\n\002\b\002\n\002\020\031\n\000\n\002\020\025\n\002\b\n\n\002\020\013\n\002\b\002\n\002\020\r\n\002\b\006\n\002\030\002\n\000\n\002\020\021\n\002\020\000\n\002\b\b\n\002\020\f\n\002\b\021\n\002\020 \n\000\n\002\030\002\n\002\b\016\032\021\020\006\032\0020\0022\006\020\007\032\0020\bH\b\032\021\020\006\032\0020\0022\006\020\t\032\0020\nH\b\032\021\020\006\032\0020\0022\006\020\013\032\0020\fH\b\032\031\020\006\032\0020\0022\006\020\013\032\0020\f2\006\020\r\032\0020\016H\b\032!\020\006\032\0020\0022\006\020\013\032\0020\f2\006\020\017\032\0020\0202\006\020\021\032\0020\020H\b\032)\020\006\032\0020\0022\006\020\013\032\0020\f2\006\020\017\032\0020\0202\006\020\021\032\0020\0202\006\020\r\032\0020\016H\b\032\021\020\006\032\0020\0022\006\020\022\032\0020\023H\b\032!\020\006\032\0020\0022\006\020\022\032\0020\0232\006\020\017\032\0020\0202\006\020\021\032\0020\020H\b\032!\020\006\032\0020\0022\006\020\024\032\0020\0252\006\020\017\032\0020\0202\006\020\021\032\0020\020H\b\032\n\020\026\032\0020\002*\0020\002\032\025\020\027\032\0020\020*\0020\0022\006\020\030\032\0020\020H\b\032\025\020\031\032\0020\020*\0020\0022\006\020\030\032\0020\020H\b\032\035\020\032\032\0020\020*\0020\0022\006\020\033\032\0020\0202\006\020\034\032\0020\020H\b\032\034\020\035\032\0020\020*\0020\0022\006\020\036\032\0020\0022\b\b\002\020\037\032\0020 \032\025\020!\032\0020 *\0020\0022\006\020\t\032\0020\bH\b\032\025\020!\032\0020 *\0020\0022\006\020\"\032\0020#H\b\032\n\020$\032\0020\002*\0020\002\032\034\020%\032\0020 *\0020\0022\006\020&\032\0020\0022\b\b\002\020\037\032\0020 \032 \020'\032\0020 *\004\030\0010\0022\b\020\036\032\004\030\0010\0022\b\b\002\020\037\032\0020 \0322\020(\032\0020\002*\0020\0022\006\020)\032\0020*2\026\020+\032\f\022\b\b\001\022\004\030\0010-0,\"\004\030\0010-H\b¢\006\002\020.\032*\020(\032\0020\002*\0020\0022\026\020+\032\f\022\b\b\001\022\004\030\0010-0,\"\004\030\0010-H\b¢\006\002\020/\032:\020(\032\0020\002*\0020\0032\006\020)\032\0020*2\006\020(\032\0020\0022\026\020+\032\f\022\b\b\001\022\004\030\0010-0,\"\004\030\0010-H\b¢\006\002\0200\0322\020(\032\0020\002*\0020\0032\006\020(\032\0020\0022\026\020+\032\f\022\b\b\001\022\004\030\0010-0,\"\004\030\0010-H\b¢\006\002\0201\032\r\0202\032\0020\002*\0020\002H\b\032\n\0203\032\0020 *\0020#\032\035\0204\032\0020\020*\0020\0022\006\0205\032\002062\006\0207\032\0020\020H\b\032\035\0204\032\0020\020*\0020\0022\006\0208\032\0020\0022\006\0207\032\0020\020H\b\032\035\0209\032\0020\020*\0020\0022\006\0205\032\002062\006\0207\032\0020\020H\b\032\035\0209\032\0020\020*\0020\0022\006\0208\032\0020\0022\006\0207\032\0020\020H\b\032\035\020:\032\0020\020*\0020\0022\006\020\030\032\0020\0202\006\020;\032\0020\020H\b\0324\020<\032\0020 *\0020#2\006\020=\032\0020\0202\006\020\036\032\0020#2\006\020>\032\0020\0202\006\020\021\032\0020\0202\b\b\002\020\037\032\0020 \0324\020<\032\0020 *\0020\0022\006\020=\032\0020\0202\006\020\036\032\0020\0022\006\020>\032\0020\0202\006\020\021\032\0020\0202\b\b\002\020\037\032\0020 \032\022\020?\032\0020\002*\0020#2\006\020@\032\0020\020\032$\020A\032\0020\002*\0020\0022\006\020B\032\002062\006\020C\032\002062\b\b\002\020\037\032\0020 \032$\020A\032\0020\002*\0020\0022\006\020D\032\0020\0022\006\020E\032\0020\0022\b\b\002\020\037\032\0020 \032$\020F\032\0020\002*\0020\0022\006\020B\032\002062\006\020C\032\002062\b\b\002\020\037\032\0020 \032$\020F\032\0020\002*\0020\0022\006\020D\032\0020\0022\006\020E\032\0020\0022\b\b\002\020\037\032\0020 \032\"\020G\032\b\022\004\022\0020\0020H*\0020#2\006\020I\032\0020J2\b\b\002\020K\032\0020\020\032\034\020L\032\0020 *\0020\0022\006\020M\032\0020\0022\b\b\002\020\037\032\0020 \032$\020L\032\0020 *\0020\0022\006\020M\032\0020\0022\006\020N\032\0020\0202\b\b\002\020\037\032\0020 \032\025\020O\032\0020\002*\0020\0022\006\020N\032\0020\020H\b\032\035\020O\032\0020\002*\0020\0022\006\020N\032\0020\0202\006\020\034\032\0020\020H\b\032\027\020P\032\0020\f*\0020\0022\b\b\002\020\r\032\0020\016H\b\032\r\020Q\032\0020\023*\0020\002H\b\0323\020Q\032\0020\023*\0020\0022\006\020R\032\0020\0232\b\b\002\020S\032\0020\0202\b\b\002\020N\032\0020\0202\b\b\002\020\034\032\0020\020H\b\032\r\020T\032\0020\002*\0020\002H\b\032\025\020T\032\0020\002*\0020\0022\006\020)\032\0020*H\b\032\027\020U\032\0020J*\0020\0022\b\b\002\020V\032\0020\020H\b\032\r\020W\032\0020\002*\0020\002H\b\032\025\020W\032\0020\002*\0020\0022\006\020)\032\0020*H\b\"\033\020\000\032\b\022\004\022\0020\0020\001*\0020\0038F¢\006\006\032\004\b\004\020\005¨\006X"}, d2={"CASE_INSENSITIVE_ORDER", "Ljava/util/Comparator;", "", "Lkotlin/String$Companion;", "getCASE_INSENSITIVE_ORDER", "(Lkotlin/jvm/internal/StringCompanionObject;)Ljava/util/Comparator;", "String", "stringBuffer", "Ljava/lang/StringBuffer;", "stringBuilder", "Ljava/lang/StringBuilder;", "bytes", "", "charset", "Ljava/nio/charset/Charset;", "offset", "", "length", "chars", "", "codePoints", "", "capitalize", "codePointAt", "index", "codePointBefore", "codePointCount", "beginIndex", "endIndex", "compareTo", "other", "ignoreCase", "", "contentEquals", "charSequence", "", "decapitalize", "endsWith", "suffix", "equals", "format", "locale", "Ljava/util/Locale;", "args", "", "", "(Ljava/lang/String;Ljava/util/Locale;[Ljava/lang/Object;)Ljava/lang/String;", "(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;", "(Lkotlin/jvm/internal/StringCompanionObject;Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;", "(Lkotlin/jvm/internal/StringCompanionObject;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;", "intern", "isBlank", "nativeIndexOf", "ch", "", "fromIndex", "str", "nativeLastIndexOf", "offsetByCodePoints", "codePointOffset", "regionMatches", "thisOffset", "otherOffset", "repeat", "n", "replace", "oldChar", "newChar", "oldValue", "newValue", "replaceFirst", "split", "", "regex", "Ljava/util/regex/Pattern;", "limit", "startsWith", "prefix", "startIndex", "substring", "toByteArray", "toCharArray", "destination", "destinationOffset", "toLowerCase", "toPattern", "flags", "toUpperCase", "kotlin-stdlib"}, k=5, mv={1, 1, 9}, xi=1, xs="kotlin/text/StringsKt")
class StringsKt__StringsJVMKt
  extends StringsKt__StringNumberConversionsKt
{
  public StringsKt__StringsJVMKt() {}
  
  @InlineOnly
  private static final String String(StringBuffer paramStringBuffer)
  {
    return new String(paramStringBuffer);
  }
  
  @InlineOnly
  private static final String String(StringBuilder paramStringBuilder)
  {
    return new String(paramStringBuilder);
  }
  
  @InlineOnly
  private static final String String(byte[] paramArrayOfByte)
  {
    return new String(paramArrayOfByte, Charsets.UTF_8);
  }
  
  @InlineOnly
  private static final String String(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    return new String(paramArrayOfByte, paramInt1, paramInt2, Charsets.UTF_8);
  }
  
  @InlineOnly
  private static final String String(byte[] paramArrayOfByte, int paramInt1, int paramInt2, Charset paramCharset)
  {
    return new String(paramArrayOfByte, paramInt1, paramInt2, paramCharset);
  }
  
  @InlineOnly
  private static final String String(byte[] paramArrayOfByte, Charset paramCharset)
  {
    return new String(paramArrayOfByte, paramCharset);
  }
  
  @InlineOnly
  private static final String String(char[] paramArrayOfChar)
  {
    return new String(paramArrayOfChar);
  }
  
  @InlineOnly
  private static final String String(char[] paramArrayOfChar, int paramInt1, int paramInt2)
  {
    return new String(paramArrayOfChar, paramInt1, paramInt2);
  }
  
  @InlineOnly
  private static final String String(int[] paramArrayOfInt, int paramInt1, int paramInt2)
  {
    return new String(paramArrayOfInt, paramInt1, paramInt2);
  }
  
  @NotNull
  public static final String capitalize(@NotNull String paramString)
  {
    Intrinsics.checkParameterIsNotNull(paramString, "$receiver");
    int i;
    if (((CharSequence)paramString).length() > 0) {
      i = 1;
    } else {
      i = 0;
    }
    Object localObject = paramString;
    if (i != 0)
    {
      localObject = paramString;
      if (Character.isLowerCase(paramString.charAt(0)))
      {
        localObject = new StringBuilder();
        String str = paramString.substring(0, 1);
        Intrinsics.checkExpressionValueIsNotNull(str, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        if (str == null) {
          throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        str = str.toUpperCase();
        Intrinsics.checkExpressionValueIsNotNull(str, "(this as java.lang.String).toUpperCase()");
        ((StringBuilder)localObject).append(str);
        paramString = paramString.substring(1);
        Intrinsics.checkExpressionValueIsNotNull(paramString, "(this as java.lang.String).substring(startIndex)");
        ((StringBuilder)localObject).append(paramString);
        localObject = ((StringBuilder)localObject).toString();
      }
    }
    return localObject;
  }
  
  @InlineOnly
  private static final int codePointAt(@NotNull String paramString, int paramInt)
  {
    if (paramString == null) {
      throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }
    return paramString.codePointAt(paramInt);
  }
  
  @InlineOnly
  private static final int codePointBefore(@NotNull String paramString, int paramInt)
  {
    if (paramString == null) {
      throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }
    return paramString.codePointBefore(paramInt);
  }
  
  @InlineOnly
  private static final int codePointCount(@NotNull String paramString, int paramInt1, int paramInt2)
  {
    if (paramString == null) {
      throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }
    return paramString.codePointCount(paramInt1, paramInt2);
  }
  
  public static final int compareTo(@NotNull String paramString1, @NotNull String paramString2, boolean paramBoolean)
  {
    Intrinsics.checkParameterIsNotNull(paramString1, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramString2, "other");
    if (paramBoolean) {
      return paramString1.compareToIgnoreCase(paramString2);
    }
    return paramString1.compareTo(paramString2);
  }
  
  @InlineOnly
  private static final boolean contentEquals(@NotNull String paramString, CharSequence paramCharSequence)
  {
    if (paramString == null) {
      throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }
    return paramString.contentEquals(paramCharSequence);
  }
  
  @InlineOnly
  private static final boolean contentEquals(@NotNull String paramString, StringBuffer paramStringBuffer)
  {
    if (paramString == null) {
      throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }
    return paramString.contentEquals(paramStringBuffer);
  }
  
  @NotNull
  public static final String decapitalize(@NotNull String paramString)
  {
    Intrinsics.checkParameterIsNotNull(paramString, "$receiver");
    int i;
    if (((CharSequence)paramString).length() > 0) {
      i = 1;
    } else {
      i = 0;
    }
    Object localObject = paramString;
    if (i != 0)
    {
      localObject = paramString;
      if (Character.isUpperCase(paramString.charAt(0)))
      {
        localObject = new StringBuilder();
        String str = paramString.substring(0, 1);
        Intrinsics.checkExpressionValueIsNotNull(str, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        if (str == null) {
          throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        str = str.toLowerCase();
        Intrinsics.checkExpressionValueIsNotNull(str, "(this as java.lang.String).toLowerCase()");
        ((StringBuilder)localObject).append(str);
        paramString = paramString.substring(1);
        Intrinsics.checkExpressionValueIsNotNull(paramString, "(this as java.lang.String).substring(startIndex)");
        ((StringBuilder)localObject).append(paramString);
        localObject = ((StringBuilder)localObject).toString();
      }
    }
    return localObject;
  }
  
  public static final boolean endsWith(@NotNull String paramString1, @NotNull String paramString2, boolean paramBoolean)
  {
    Intrinsics.checkParameterIsNotNull(paramString1, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramString2, "suffix");
    if (!paramBoolean) {
      return paramString1.endsWith(paramString2);
    }
    return StringsKt.regionMatches(paramString1, paramString1.length() - paramString2.length(), paramString2, 0, paramString2.length(), true);
  }
  
  public static final boolean equals(@Nullable String paramString1, @Nullable String paramString2, boolean paramBoolean)
  {
    if (paramString1 == null) {
      return paramString2 == null;
    }
    if (!paramBoolean) {
      return paramString1.equals(paramString2);
    }
    return paramString1.equalsIgnoreCase(paramString2);
  }
  
  @InlineOnly
  private static final String format(@NotNull String paramString, Locale paramLocale, Object... paramVarArgs)
  {
    paramString = String.format(paramLocale, paramString, Arrays.copyOf(paramVarArgs, paramVarArgs.length));
    Intrinsics.checkExpressionValueIsNotNull(paramString, "java.lang.String.format(locale, this, *args)");
    return paramString;
  }
  
  @InlineOnly
  private static final String format(@NotNull String paramString, Object... paramVarArgs)
  {
    paramString = String.format(paramString, Arrays.copyOf(paramVarArgs, paramVarArgs.length));
    Intrinsics.checkExpressionValueIsNotNull(paramString, "java.lang.String.format(this, *args)");
    return paramString;
  }
  
  @InlineOnly
  private static final String format(@NotNull StringCompanionObject paramStringCompanionObject, String paramString, Object... paramVarArgs)
  {
    paramStringCompanionObject = String.format(paramString, Arrays.copyOf(paramVarArgs, paramVarArgs.length));
    Intrinsics.checkExpressionValueIsNotNull(paramStringCompanionObject, "java.lang.String.format(format, *args)");
    return paramStringCompanionObject;
  }
  
  @InlineOnly
  private static final String format(@NotNull StringCompanionObject paramStringCompanionObject, Locale paramLocale, String paramString, Object... paramVarArgs)
  {
    paramStringCompanionObject = String.format(paramLocale, paramString, Arrays.copyOf(paramVarArgs, paramVarArgs.length));
    Intrinsics.checkExpressionValueIsNotNull(paramStringCompanionObject, "java.lang.String.format(locale, format, *args)");
    return paramStringCompanionObject;
  }
  
  @NotNull
  public static final Comparator<String> getCASE_INSENSITIVE_ORDER(@NotNull StringCompanionObject paramStringCompanionObject)
  {
    Intrinsics.checkParameterIsNotNull(paramStringCompanionObject, "$receiver");
    paramStringCompanionObject = String.CASE_INSENSITIVE_ORDER;
    Intrinsics.checkExpressionValueIsNotNull(paramStringCompanionObject, "java.lang.String.CASE_INSENSITIVE_ORDER");
    return paramStringCompanionObject;
  }
  
  @InlineOnly
  private static final String intern(@NotNull String paramString)
  {
    if (paramString == null) {
      throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }
    paramString = paramString.intern();
    Intrinsics.checkExpressionValueIsNotNull(paramString, "(this as java.lang.String).intern()");
    return paramString;
  }
  
  public static final boolean isBlank(@NotNull CharSequence paramCharSequence)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    int i = paramCharSequence.length();
    boolean bool = false;
    if (i != 0)
    {
      Object localObject = (Iterable)StringsKt.getIndices(paramCharSequence);
      if (((localObject instanceof Collection)) && (((Collection)localObject).isEmpty())) {}
      do
      {
        while (!((Iterator)localObject).hasNext())
        {
          i = 1;
          break;
          localObject = ((Iterable)localObject).iterator();
        }
      } while (CharsKt.isWhitespace(paramCharSequence.charAt(((IntIterator)localObject).nextInt())));
      i = 0;
      if (i == 0) {}
    }
    else
    {
      bool = true;
    }
    return bool;
  }
  
  @InlineOnly
  private static final int nativeIndexOf(@NotNull String paramString, char paramChar, int paramInt)
  {
    if (paramString == null) {
      throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }
    return paramString.indexOf(paramChar, paramInt);
  }
  
  @InlineOnly
  private static final int nativeIndexOf(@NotNull String paramString1, String paramString2, int paramInt)
  {
    if (paramString1 == null) {
      throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }
    return paramString1.indexOf(paramString2, paramInt);
  }
  
  @InlineOnly
  private static final int nativeLastIndexOf(@NotNull String paramString, char paramChar, int paramInt)
  {
    if (paramString == null) {
      throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }
    return paramString.lastIndexOf(paramChar, paramInt);
  }
  
  @InlineOnly
  private static final int nativeLastIndexOf(@NotNull String paramString1, String paramString2, int paramInt)
  {
    if (paramString1 == null) {
      throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }
    return paramString1.lastIndexOf(paramString2, paramInt);
  }
  
  @InlineOnly
  private static final int offsetByCodePoints(@NotNull String paramString, int paramInt1, int paramInt2)
  {
    if (paramString == null) {
      throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }
    return paramString.offsetByCodePoints(paramInt1, paramInt2);
  }
  
  public static final boolean regionMatches(@NotNull CharSequence paramCharSequence1, int paramInt1, @NotNull CharSequence paramCharSequence2, int paramInt2, int paramInt3, boolean paramBoolean)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence1, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramCharSequence2, "other");
    if (((paramCharSequence1 instanceof String)) && ((paramCharSequence2 instanceof String))) {
      return StringsKt.regionMatches((String)paramCharSequence1, paramInt1, (String)paramCharSequence2, paramInt2, paramInt3, paramBoolean);
    }
    return StringsKt.regionMatchesImpl(paramCharSequence1, paramInt1, paramCharSequence2, paramInt2, paramInt3, paramBoolean);
  }
  
  public static final boolean regionMatches(@NotNull String paramString1, int paramInt1, @NotNull String paramString2, int paramInt2, int paramInt3, boolean paramBoolean)
  {
    Intrinsics.checkParameterIsNotNull(paramString1, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramString2, "other");
    if (!paramBoolean) {
      return paramString1.regionMatches(paramInt1, paramString2, paramInt2, paramInt3);
    }
    return paramString1.regionMatches(paramBoolean, paramInt1, paramString2, paramInt2, paramInt3);
  }
  
  @NotNull
  public static final String repeat(@NotNull CharSequence paramCharSequence, int paramInt)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    int k = 0;
    int m = 1;
    int j;
    if (paramInt >= 0) {
      j = 1;
    } else {
      j = 0;
    }
    if (j == 0)
    {
      paramCharSequence = new StringBuilder();
      paramCharSequence.append("Count 'n' must be non-negative, but was ");
      paramCharSequence.append(paramInt);
      paramCharSequence.append('.');
      throw ((Throwable)new IllegalArgumentException(paramCharSequence.toString().toString()));
    }
    StringBuilder localStringBuilder;
    switch (paramInt)
    {
    default: 
      switch (paramCharSequence.length())
      {
      default: 
        localStringBuilder = new StringBuilder(paramCharSequence.length() * paramInt);
        if (1 > paramInt) {
          break label227;
        }
        j = m;
      }
      break;
    case 1: 
      return paramCharSequence.toString();
    case 0: 
      return "";
      int i = paramCharSequence.charAt(0);
      paramCharSequence = new char[paramInt];
      j = paramCharSequence.length;
      paramInt = k;
      while (paramInt < j)
      {
        paramCharSequence[paramInt] = i;
        paramInt += 1;
      }
      return new String(paramCharSequence);
      return "";
    }
    for (;;)
    {
      localStringBuilder.append(paramCharSequence);
      if (j == paramInt) {
        break;
      }
      j += 1;
    }
    label227:
    paramCharSequence = localStringBuilder.toString();
    Intrinsics.checkExpressionValueIsNotNull(paramCharSequence, "sb.toString()");
    return paramCharSequence;
  }
  
  @NotNull
  public static final String replace(@NotNull String paramString, char paramChar1, char paramChar2, boolean paramBoolean)
  {
    Intrinsics.checkParameterIsNotNull(paramString, "$receiver");
    if (!paramBoolean)
    {
      paramString = paramString.replace(paramChar1, paramChar2);
      Intrinsics.checkExpressionValueIsNotNull(paramString, "(this as java.lang.Strin…replace(oldChar, newChar)");
      return paramString;
    }
    return SequencesKt.joinToString$default(StringsKt.splitToSequence$default((CharSequence)paramString, new char[] { paramChar1 }, paramBoolean, 0, 4, null), (CharSequence)String.valueOf(paramChar2), null, null, 0, null, null, 62, null);
  }
  
  @NotNull
  public static final String replace(@NotNull String paramString1, @NotNull String paramString2, @NotNull String paramString3, boolean paramBoolean)
  {
    Intrinsics.checkParameterIsNotNull(paramString1, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramString2, "oldValue");
    Intrinsics.checkParameterIsNotNull(paramString3, "newValue");
    return SequencesKt.joinToString$default(StringsKt.splitToSequence$default((CharSequence)paramString1, new String[] { paramString2 }, paramBoolean, 0, 4, null), (CharSequence)paramString3, null, null, 0, null, null, 62, null);
  }
  
  @NotNull
  public static final String replaceFirst(@NotNull String paramString, char paramChar1, char paramChar2, boolean paramBoolean)
  {
    Intrinsics.checkParameterIsNotNull(paramString, "$receiver");
    CharSequence localCharSequence = (CharSequence)paramString;
    int i = StringsKt.indexOf$default(localCharSequence, paramChar1, 0, paramBoolean, 2, null);
    if (i < 0) {
      return paramString;
    }
    return StringsKt.replaceRange(localCharSequence, i, i + 1, (CharSequence)String.valueOf(paramChar2)).toString();
  }
  
  @NotNull
  public static final String replaceFirst(@NotNull String paramString1, @NotNull String paramString2, @NotNull String paramString3, boolean paramBoolean)
  {
    Intrinsics.checkParameterIsNotNull(paramString1, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramString2, "oldValue");
    Intrinsics.checkParameterIsNotNull(paramString3, "newValue");
    CharSequence localCharSequence = (CharSequence)paramString1;
    int i = StringsKt.indexOf$default(localCharSequence, paramString2, 0, paramBoolean, 2, null);
    if (i < 0) {
      return paramString1;
    }
    return StringsKt.replaceRange(localCharSequence, i, paramString2.length() + i, (CharSequence)paramString3).toString();
  }
  
  @NotNull
  public static final List<String> split(@NotNull CharSequence paramCharSequence, @NotNull Pattern paramPattern, int paramInt)
  {
    Intrinsics.checkParameterIsNotNull(paramCharSequence, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramPattern, "regex");
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
    int i = paramInt;
    if (paramInt == 0) {
      i = -1;
    }
    paramCharSequence = paramPattern.split(paramCharSequence, i);
    Intrinsics.checkExpressionValueIsNotNull(paramCharSequence, "regex.split(this, if (limit == 0) -1 else limit)");
    return ArraysKt.asList((Object[])paramCharSequence);
  }
  
  public static final boolean startsWith(@NotNull String paramString1, @NotNull String paramString2, int paramInt, boolean paramBoolean)
  {
    Intrinsics.checkParameterIsNotNull(paramString1, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramString2, "prefix");
    if (!paramBoolean) {
      return paramString1.startsWith(paramString2, paramInt);
    }
    return StringsKt.regionMatches(paramString1, paramInt, paramString2, 0, paramString2.length(), paramBoolean);
  }
  
  public static final boolean startsWith(@NotNull String paramString1, @NotNull String paramString2, boolean paramBoolean)
  {
    Intrinsics.checkParameterIsNotNull(paramString1, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramString2, "prefix");
    if (!paramBoolean) {
      return paramString1.startsWith(paramString2);
    }
    return StringsKt.regionMatches(paramString1, 0, paramString2, 0, paramString2.length(), paramBoolean);
  }
  
  @InlineOnly
  private static final String substring(@NotNull String paramString, int paramInt)
  {
    if (paramString == null) {
      throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }
    paramString = paramString.substring(paramInt);
    Intrinsics.checkExpressionValueIsNotNull(paramString, "(this as java.lang.String).substring(startIndex)");
    return paramString;
  }
  
  @InlineOnly
  private static final String substring(@NotNull String paramString, int paramInt1, int paramInt2)
  {
    if (paramString == null) {
      throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }
    paramString = paramString.substring(paramInt1, paramInt2);
    Intrinsics.checkExpressionValueIsNotNull(paramString, "(this as java.lang.Strin…ing(startIndex, endIndex)");
    return paramString;
  }
  
  @InlineOnly
  private static final byte[] toByteArray(@NotNull String paramString, Charset paramCharset)
  {
    if (paramString == null) {
      throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }
    paramString = paramString.getBytes(paramCharset);
    Intrinsics.checkExpressionValueIsNotNull(paramString, "(this as java.lang.String).getBytes(charset)");
    return paramString;
  }
  
  @InlineOnly
  private static final char[] toCharArray(@NotNull String paramString)
  {
    if (paramString == null) {
      throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }
    paramString = paramString.toCharArray();
    Intrinsics.checkExpressionValueIsNotNull(paramString, "(this as java.lang.String).toCharArray()");
    return paramString;
  }
  
  @InlineOnly
  private static final char[] toCharArray(@NotNull String paramString, char[] paramArrayOfChar, int paramInt1, int paramInt2, int paramInt3)
  {
    if (paramString == null) {
      throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }
    paramString.getChars(paramInt2, paramInt3, paramArrayOfChar, paramInt1);
    return paramArrayOfChar;
  }
  
  @InlineOnly
  private static final String toLowerCase(@NotNull String paramString)
  {
    if (paramString == null) {
      throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }
    paramString = paramString.toLowerCase();
    Intrinsics.checkExpressionValueIsNotNull(paramString, "(this as java.lang.String).toLowerCase()");
    return paramString;
  }
  
  @InlineOnly
  private static final String toLowerCase(@NotNull String paramString, Locale paramLocale)
  {
    if (paramString == null) {
      throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }
    paramString = paramString.toLowerCase(paramLocale);
    Intrinsics.checkExpressionValueIsNotNull(paramString, "(this as java.lang.String).toLowerCase(locale)");
    return paramString;
  }
  
  @InlineOnly
  private static final Pattern toPattern(@NotNull String paramString, int paramInt)
  {
    paramString = Pattern.compile(paramString, paramInt);
    Intrinsics.checkExpressionValueIsNotNull(paramString, "java.util.regex.Pattern.compile(this, flags)");
    return paramString;
  }
  
  @InlineOnly
  private static final String toUpperCase(@NotNull String paramString)
  {
    if (paramString == null) {
      throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }
    paramString = paramString.toUpperCase();
    Intrinsics.checkExpressionValueIsNotNull(paramString, "(this as java.lang.String).toUpperCase()");
    return paramString;
  }
  
  @InlineOnly
  private static final String toUpperCase(@NotNull String paramString, Locale paramLocale)
  {
    if (paramString == null) {
      throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }
    paramString = paramString.toUpperCase(paramLocale);
    Intrinsics.checkExpressionValueIsNotNull(paramString, "(this as java.lang.String).toUpperCase(locale)");
    return paramString;
  }
}
