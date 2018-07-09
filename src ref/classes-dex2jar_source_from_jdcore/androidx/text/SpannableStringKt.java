package androidx.text;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000\034\n\000\n\002\020\002\n\002\030\002\n\002\b\002\n\002\020\000\n\002\b\002\n\002\020\r\n\000\032\r\020\000\032\0020\001*\0020\002H\b\032\025\020\003\032\0020\001*\0020\0022\006\020\004\032\0020\005H\n\032\025\020\006\032\0020\001*\0020\0022\006\020\004\032\0020\005H\n\032\r\020\007\032\0020\002*\0020\bH\b¨\006\t"}, d2={"clearSpans", "", "Landroid/text/Spannable;", "minusAssign", "span", "", "plusAssign", "toSpannable", "", "core-ktx_release"}, k=2, mv={1, 1, 9})
public final class SpannableStringKt
{
  public static final void clearSpans(@NotNull Spannable paramSpannable)
  {
    Object localObject = (Spanned)paramSpannable;
    int j = ((Spanned)localObject).length();
    int i = 0;
    localObject = ((Spanned)localObject).getSpans(0, j, Object.class);
    Intrinsics.checkExpressionValueIsNotNull(localObject, "getSpans(0, length, T::class.java)");
    j = localObject.length;
    while (i < j)
    {
      paramSpannable.removeSpan(localObject[i]);
      i += 1;
    }
  }
  
  public static final void minusAssign(@NotNull Spannable paramSpannable, @NotNull Object paramObject)
  {
    paramSpannable.removeSpan(paramObject);
  }
  
  public static final void plusAssign(@NotNull Spannable paramSpannable, @NotNull Object paramObject)
  {
    paramSpannable.setSpan(paramObject, 0, paramSpannable.length(), 17);
  }
  
  @NotNull
  public static final Spannable toSpannable(@NotNull CharSequence paramCharSequence)
  {
    paramCharSequence = SpannableString.valueOf(paramCharSequence);
    Intrinsics.checkExpressionValueIsNotNull(paramCharSequence, "SpannableString.valueOf(this)");
    return (Spannable)paramCharSequence;
  }
}
