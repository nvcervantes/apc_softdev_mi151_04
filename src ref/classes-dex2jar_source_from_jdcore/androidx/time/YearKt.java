package androidx.time;

import android.support.annotation.RequiresApi;
import java.time.Year;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000\016\n\000\n\002\020\b\n\002\030\002\n\002\b\002\032\r\020\000\032\0020\001*\0020\002H\b\032\r\020\003\032\0020\002*\0020\001H\b¨\006\004"}, d2={"asInt", "", "Ljava/time/Year;", "asYear", "core-ktx_release"}, k=2, mv={1, 1, 9})
public final class YearKt
{
  @Deprecated(message="These extensions are for java.* APIs and not android.* APIs and thus are out of scope for this project. Star https://youtrack.jetbrains.com/issue/KT-21585 for future support of extensions like these. These extensions will be removed before core-ktx 1.0!", replaceWith=@ReplaceWith(expression="this.value", imports={}))
  @RequiresApi(26)
  public static final int asInt(@NotNull Year paramYear)
  {
    return paramYear.getValue();
  }
  
  @Deprecated(message="These extensions are for java.* APIs and not android.* APIs and thus are out of scope for this project. Star https://youtrack.jetbrains.com/issue/KT-21585 for future support of extensions like these. These extensions will be removed before core-ktx 1.0!", replaceWith=@ReplaceWith(expression="Year.of(this)", imports={"java.time.Year"}))
  @RequiresApi(26)
  @NotNull
  public static final Year asYear(int paramInt)
  {
    Year localYear = Year.of(paramInt);
    Intrinsics.checkExpressionValueIsNotNull(localYear, "Year.of(this)");
    return localYear;
  }
}
