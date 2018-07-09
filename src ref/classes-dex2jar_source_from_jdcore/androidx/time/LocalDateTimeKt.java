package androidx.time;

import android.support.annotation.RequiresApi;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000\022\n\000\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\000\032\r\020\000\032\0020\001*\0020\002H\n\032\r\020\003\032\0020\004*\0020\002H\n¨\006\005"}, d2={"component1", "Ljava/time/LocalDate;", "Ljava/time/LocalDateTime;", "component2", "Ljava/time/LocalTime;", "core-ktx_release"}, k=2, mv={1, 1, 9})
public final class LocalDateTimeKt
{
  @Deprecated(message="These extensions are for java.* APIs and not android.* APIs and thus are out of scope for this project. Star https://youtrack.jetbrains.com/issue/KT-21585 for future support of extensions like these. These extensions will be removed before core-ktx 1.0!")
  @RequiresApi(26)
  @NotNull
  public static final LocalDate component1(@NotNull LocalDateTime paramLocalDateTime)
  {
    paramLocalDateTime = paramLocalDateTime.toLocalDate();
    Intrinsics.checkExpressionValueIsNotNull(paramLocalDateTime, "toLocalDate()");
    return paramLocalDateTime;
  }
  
  @Deprecated(message="These extensions are for java.* APIs and not android.* APIs and thus are out of scope for this project. Star https://youtrack.jetbrains.com/issue/KT-21585 for future support of extensions like these. These extensions will be removed before core-ktx 1.0!")
  @RequiresApi(26)
  @NotNull
  public static final LocalTime component2(@NotNull LocalDateTime paramLocalDateTime)
  {
    paramLocalDateTime = paramLocalDateTime.toLocalTime();
    Intrinsics.checkExpressionValueIsNotNull(paramLocalDateTime, "toLocalTime()");
    return paramLocalDateTime;
  }
}
