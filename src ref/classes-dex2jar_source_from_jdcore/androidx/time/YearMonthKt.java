package androidx.time;

import android.support.annotation.RequiresApi;
import java.time.Month;
import java.time.YearMonth;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000\022\n\000\n\002\020\b\n\002\030\002\n\000\n\002\030\002\n\000\032\r\020\000\032\0020\001*\0020\002H\n\032\r\020\003\032\0020\004*\0020\002H\n¨\006\005"}, d2={"component1", "", "Ljava/time/YearMonth;", "component2", "Ljava/time/Month;", "core-ktx_release"}, k=2, mv={1, 1, 9})
public final class YearMonthKt
{
  @Deprecated(message="These extensions are for java.* APIs and not android.* APIs and thus are out of scope for this project. Star https://youtrack.jetbrains.com/issue/KT-21585 for future support of extensions like these. These extensions will be removed before core-ktx 1.0!")
  @RequiresApi(26)
  public static final int component1(@NotNull YearMonth paramYearMonth)
  {
    return paramYearMonth.getYear();
  }
  
  @Deprecated(message="These extensions are for java.* APIs and not android.* APIs and thus are out of scope for this project. Star https://youtrack.jetbrains.com/issue/KT-21585 for future support of extensions like these. These extensions will be removed before core-ktx 1.0!")
  @RequiresApi(26)
  @NotNull
  public static final Month component2(@NotNull YearMonth paramYearMonth)
  {
    paramYearMonth = paramYearMonth.getMonth();
    Intrinsics.checkExpressionValueIsNotNull(paramYearMonth, "month");
    return paramYearMonth;
  }
}
