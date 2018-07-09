package androidx.net;

import android.net.Uri;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000\f\n\000\n\002\030\002\n\002\020\016\n\000\032\r\020\000\032\0020\001*\0020\002H\b¨\006\003"}, d2={"toUri", "Landroid/net/Uri;", "", "core-ktx_release"}, k=2, mv={1, 1, 9})
public final class UriKt
{
  @NotNull
  public static final Uri toUri(@NotNull String paramString)
  {
    paramString = Uri.parse(paramString);
    Intrinsics.checkExpressionValueIsNotNull(paramString, "Uri.parse(this)");
    return paramString;
  }
}
