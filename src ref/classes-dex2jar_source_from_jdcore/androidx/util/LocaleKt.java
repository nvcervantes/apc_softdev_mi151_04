package androidx.util;

import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import java.util.Locale;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000\016\n\000\n\002\020\b\n\002\030\002\n\002\b\003\"\025\020\000\032\0020\001*\0020\0028G¢\006\006\032\004\b\003\020\004¨\006\005"}, d2={"layoutDirection", "", "Ljava/util/Locale;", "getLayoutDirection", "(Ljava/util/Locale;)I", "core-ktx_release"}, k=2, mv={1, 1, 9})
public final class LocaleKt
{
  @RequiresApi(17)
  public static final int getLayoutDirection(@NotNull Locale paramLocale)
  {
    return TextUtils.getLayoutDirectionFromLocale(paramLocale);
  }
}
