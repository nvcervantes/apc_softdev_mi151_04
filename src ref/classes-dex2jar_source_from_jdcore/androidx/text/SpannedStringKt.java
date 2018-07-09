package androidx.text;

import android.text.Spanned;
import android.text.SpannedString;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000\032\n\000\n\002\020\021\n\000\n\002\020\000\n\002\030\002\n\002\b\002\n\002\020\r\n\000\032&\020\000\032\n\022\006\b\001\022\002H\0020\001\"\n\b\000\020\002\030\001*\0020\003*\0020\004H\b¢\006\002\020\005\032\r\020\006\032\0020\004*\0020\007H\b¨\006\b"}, d2={"getSpans", "", "T", "", "Landroid/text/Spanned;", "(Landroid/text/Spanned;)[Ljava/lang/Object;", "toSpanned", "", "core-ktx_release"}, k=2, mv={1, 1, 9})
public final class SpannedStringKt
{
  private static final <T> T[] getSpans(@NotNull Spanned paramSpanned)
  {
    int i = paramSpanned.length();
    Intrinsics.reifiedOperationMarker(4, "T");
    paramSpanned = paramSpanned.getSpans(0, i, Object.class);
    Intrinsics.checkExpressionValueIsNotNull(paramSpanned, "getSpans(0, length, T::class.java)");
    return paramSpanned;
  }
  
  @NotNull
  public static final Spanned toSpanned(@NotNull CharSequence paramCharSequence)
  {
    paramCharSequence = SpannedString.valueOf(paramCharSequence);
    Intrinsics.checkExpressionValueIsNotNull(paramCharSequence, "SpannedString.valueOf(this)");
    return (Spanned)paramCharSequence;
  }
}
