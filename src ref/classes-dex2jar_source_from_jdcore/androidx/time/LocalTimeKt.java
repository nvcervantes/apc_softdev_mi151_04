package androidx.time;

import android.support.annotation.RequiresApi;
import java.time.LocalTime;
import kotlin.Deprecated;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000\016\n\000\n\002\020\b\n\002\030\002\n\002\b\004\032\r\020\000\032\0020\001*\0020\002H\n\032\r\020\003\032\0020\001*\0020\002H\n\032\r\020\004\032\0020\001*\0020\002H\n\032\r\020\005\032\0020\001*\0020\002H\n¨\006\006"}, d2={"component1", "", "Ljava/time/LocalTime;", "component2", "component3", "component4", "core-ktx_release"}, k=2, mv={1, 1, 9})
public final class LocalTimeKt
{
  @Deprecated(message="These extensions are for java.* APIs and not android.* APIs and thus are out of scope for this project. Star https://youtrack.jetbrains.com/issue/KT-21585 for future support of extensions like these. These extensions will be removed before core-ktx 1.0!")
  @RequiresApi(26)
  public static final int component1(@NotNull LocalTime paramLocalTime)
  {
    return paramLocalTime.getHour();
  }
  
  @Deprecated(message="These extensions are for java.* APIs and not android.* APIs and thus are out of scope for this project. Star https://youtrack.jetbrains.com/issue/KT-21585 for future support of extensions like these. These extensions will be removed before core-ktx 1.0!")
  @RequiresApi(26)
  public static final int component2(@NotNull LocalTime paramLocalTime)
  {
    return paramLocalTime.getMinute();
  }
  
  @Deprecated(message="These extensions are for java.* APIs and not android.* APIs and thus are out of scope for this project. Star https://youtrack.jetbrains.com/issue/KT-21585 for future support of extensions like these. These extensions will be removed before core-ktx 1.0!")
  @RequiresApi(26)
  public static final int component3(@NotNull LocalTime paramLocalTime)
  {
    return paramLocalTime.getSecond();
  }
  
  @Deprecated(message="These extensions are for java.* APIs and not android.* APIs and thus are out of scope for this project. Star https://youtrack.jetbrains.com/issue/KT-21585 for future support of extensions like these. These extensions will be removed before core-ktx 1.0!")
  @RequiresApi(26)
  public static final int component4(@NotNull LocalTime paramLocalTime)
  {
    return paramLocalTime.getNano();
  }
}
