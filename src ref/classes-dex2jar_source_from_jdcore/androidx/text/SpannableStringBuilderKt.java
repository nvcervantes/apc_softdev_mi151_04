package androidx.text;

import android.support.annotation.ColorInt;
import android.text.SpannableStringBuilder;
import android.text.SpannedString;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000:\n\000\n\002\030\002\n\000\n\002\030\002\n\002\030\002\n\002\020\002\n\002\030\002\n\002\b\002\n\002\020\b\n\002\b\003\n\002\020\000\n\000\n\002\020\021\n\002\b\004\n\002\020\007\n\002\b\003\032\"\020\000\032\0020\0012\027\020\002\032\023\022\004\022\0020\004\022\004\022\0020\0050\003¢\006\002\b\006H\b\0320\020\007\032\0020\004*\0020\0042\b\b\001\020\b\032\0020\t2\027\020\002\032\023\022\004\022\0020\004\022\004\022\0020\0050\003¢\006\002\b\006H\b\032&\020\n\032\0020\004*\0020\0042\027\020\002\032\023\022\004\022\0020\004\022\004\022\0020\0050\003¢\006\002\b\006H\b\0320\020\b\032\0020\004*\0020\0042\b\b\001\020\b\032\0020\t2\027\020\002\032\023\022\004\022\0020\004\022\004\022\0020\0050\003¢\006\002\b\006H\b\032.\020\013\032\0020\004*\0020\0042\006\020\f\032\0020\r2\027\020\002\032\023\022\004\022\0020\004\022\004\022\0020\0050\003¢\006\002\b\006H\b\032?\020\013\032\0020\004*\0020\0042\022\020\016\032\n\022\006\b\001\022\0020\r0\017\"\0020\r2\027\020\002\032\023\022\004\022\0020\004\022\004\022\0020\0050\003¢\006\002\b\006H\b¢\006\002\020\020\032&\020\021\032\0020\004*\0020\0042\027\020\002\032\023\022\004\022\0020\004\022\004\022\0020\0050\003¢\006\002\b\006H\b\032.\020\022\032\0020\004*\0020\0042\006\020\023\032\0020\0242\027\020\002\032\023\022\004\022\0020\004\022\004\022\0020\0050\003¢\006\002\b\006H\b\032&\020\025\032\0020\004*\0020\0042\027\020\002\032\023\022\004\022\0020\004\022\004\022\0020\0050\003¢\006\002\b\006H\b\032&\020\026\032\0020\004*\0020\0042\027\020\002\032\023\022\004\022\0020\004\022\004\022\0020\0050\003¢\006\002\b\006H\b¨\006\027"}, d2={"buildSpannedString", "Landroid/text/SpannedString;", "builderAction", "Lkotlin/Function1;", "Landroid/text/SpannableStringBuilder;", "", "Lkotlin/ExtensionFunctionType;", "backgroundColor", "color", "", "bold", "inSpans", "span", "", "spans", "", "(Landroid/text/SpannableStringBuilder;[Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)Landroid/text/SpannableStringBuilder;", "italic", "scale", "proportion", "", "strikeThrough", "underline", "core-ktx_release"}, k=2, mv={1, 1, 9})
public final class SpannableStringBuilderKt
{
  @NotNull
  public static final SpannableStringBuilder backgroundColor(@NotNull SpannableStringBuilder paramSpannableStringBuilder, @ColorInt int paramInt, @NotNull Function1<? super SpannableStringBuilder, Unit> paramFunction1)
  {
    BackgroundColorSpan localBackgroundColorSpan = new BackgroundColorSpan(paramInt);
    paramInt = paramSpannableStringBuilder.length();
    paramFunction1.invoke(paramSpannableStringBuilder);
    paramSpannableStringBuilder.setSpan(localBackgroundColorSpan, paramInt, paramSpannableStringBuilder.length(), 17);
    return paramSpannableStringBuilder;
  }
  
  @NotNull
  public static final SpannableStringBuilder bold(@NotNull SpannableStringBuilder paramSpannableStringBuilder, @NotNull Function1<? super SpannableStringBuilder, Unit> paramFunction1)
  {
    StyleSpan localStyleSpan = new StyleSpan(1);
    int i = paramSpannableStringBuilder.length();
    paramFunction1.invoke(paramSpannableStringBuilder);
    paramSpannableStringBuilder.setSpan(localStyleSpan, i, paramSpannableStringBuilder.length(), 17);
    return paramSpannableStringBuilder;
  }
  
  @NotNull
  public static final SpannedString buildSpannedString(@NotNull Function1<? super SpannableStringBuilder, Unit> paramFunction1)
  {
    SpannableStringBuilder localSpannableStringBuilder = new SpannableStringBuilder();
    paramFunction1.invoke(localSpannableStringBuilder);
    return new SpannedString((CharSequence)localSpannableStringBuilder);
  }
  
  @NotNull
  public static final SpannableStringBuilder color(@NotNull SpannableStringBuilder paramSpannableStringBuilder, @ColorInt int paramInt, @NotNull Function1<? super SpannableStringBuilder, Unit> paramFunction1)
  {
    ForegroundColorSpan localForegroundColorSpan = new ForegroundColorSpan(paramInt);
    paramInt = paramSpannableStringBuilder.length();
    paramFunction1.invoke(paramSpannableStringBuilder);
    paramSpannableStringBuilder.setSpan(localForegroundColorSpan, paramInt, paramSpannableStringBuilder.length(), 17);
    return paramSpannableStringBuilder;
  }
  
  @NotNull
  public static final SpannableStringBuilder inSpans(@NotNull SpannableStringBuilder paramSpannableStringBuilder, @NotNull Object paramObject, @NotNull Function1<? super SpannableStringBuilder, Unit> paramFunction1)
  {
    int i = paramSpannableStringBuilder.length();
    paramFunction1.invoke(paramSpannableStringBuilder);
    paramSpannableStringBuilder.setSpan(paramObject, i, paramSpannableStringBuilder.length(), 17);
    return paramSpannableStringBuilder;
  }
  
  @NotNull
  public static final SpannableStringBuilder inSpans(@NotNull SpannableStringBuilder paramSpannableStringBuilder, @NotNull Object[] paramArrayOfObject, @NotNull Function1<? super SpannableStringBuilder, Unit> paramFunction1)
  {
    int j = paramSpannableStringBuilder.length();
    paramFunction1.invoke(paramSpannableStringBuilder);
    int i = 0;
    int k = paramArrayOfObject.length;
    while (i < k)
    {
      paramSpannableStringBuilder.setSpan(paramArrayOfObject[i], j, paramSpannableStringBuilder.length(), 17);
      i += 1;
    }
    return paramSpannableStringBuilder;
  }
  
  @NotNull
  public static final SpannableStringBuilder italic(@NotNull SpannableStringBuilder paramSpannableStringBuilder, @NotNull Function1<? super SpannableStringBuilder, Unit> paramFunction1)
  {
    StyleSpan localStyleSpan = new StyleSpan(2);
    int i = paramSpannableStringBuilder.length();
    paramFunction1.invoke(paramSpannableStringBuilder);
    paramSpannableStringBuilder.setSpan(localStyleSpan, i, paramSpannableStringBuilder.length(), 17);
    return paramSpannableStringBuilder;
  }
  
  @NotNull
  public static final SpannableStringBuilder scale(@NotNull SpannableStringBuilder paramSpannableStringBuilder, float paramFloat, @NotNull Function1<? super SpannableStringBuilder, Unit> paramFunction1)
  {
    RelativeSizeSpan localRelativeSizeSpan = new RelativeSizeSpan(paramFloat);
    int i = paramSpannableStringBuilder.length();
    paramFunction1.invoke(paramSpannableStringBuilder);
    paramSpannableStringBuilder.setSpan(localRelativeSizeSpan, i, paramSpannableStringBuilder.length(), 17);
    return paramSpannableStringBuilder;
  }
  
  @NotNull
  public static final SpannableStringBuilder strikeThrough(@NotNull SpannableStringBuilder paramSpannableStringBuilder, @NotNull Function1<? super SpannableStringBuilder, Unit> paramFunction1)
  {
    StrikethroughSpan localStrikethroughSpan = new StrikethroughSpan();
    int i = paramSpannableStringBuilder.length();
    paramFunction1.invoke(paramSpannableStringBuilder);
    paramSpannableStringBuilder.setSpan(localStrikethroughSpan, i, paramSpannableStringBuilder.length(), 17);
    return paramSpannableStringBuilder;
  }
  
  @NotNull
  public static final SpannableStringBuilder underline(@NotNull SpannableStringBuilder paramSpannableStringBuilder, @NotNull Function1<? super SpannableStringBuilder, Unit> paramFunction1)
  {
    UnderlineSpan localUnderlineSpan = new UnderlineSpan();
    int i = paramSpannableStringBuilder.length();
    paramFunction1.invoke(paramSpannableStringBuilder);
    paramSpannableStringBuilder.setSpan(localUnderlineSpan, i, paramSpannableStringBuilder.length(), 17);
    return paramSpannableStringBuilder;
  }
}
