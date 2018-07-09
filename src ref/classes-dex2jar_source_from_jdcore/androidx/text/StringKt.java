package androidx.text;

import android.text.TextUtils;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000\b\n\000\n\002\020\016\n\000\032\r\020\000\032\0020\001*\0020\001H\b¨\006\002"}, d2={"htmlEncode", "", "core-ktx_release"}, k=2, mv={1, 1, 9})
public final class StringKt
{
  @NotNull
  public static final String htmlEncode(@NotNull String paramString)
  {
    paramString = TextUtils.htmlEncode(paramString);
    Intrinsics.checkExpressionValueIsNotNull(paramString, "TextUtils.htmlEncode(this)");
    return paramString;
  }
}
