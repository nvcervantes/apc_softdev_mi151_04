package androidx.time;

import android.support.annotation.RequiresApi;
import java.time.Instant;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000\024\n\000\n\002\030\002\n\002\020\t\n\002\b\003\n\002\020\b\n\000\032\r\020\000\032\0020\001*\0020\002H\b\032\r\020\003\032\0020\001*\0020\002H\b\032\r\020\004\032\0020\002*\0020\001H\n\032\r\020\005\032\0020\006*\0020\001H\n¨\006\007"}, d2={"asEpochMillis", "Ljava/time/Instant;", "", "asEpochSeconds", "component1", "component2", "", "core-ktx_release"}, k=2, mv={1, 1, 9})
public final class InstantKt
{
  @Deprecated(message="These extensions are for java.* APIs and not android.* APIs and thus are out of scope for this project. Star https://youtrack.jetbrains.com/issue/KT-21585 for future support of extensions like these. These extensions will be removed before core-ktx 1.0!", replaceWith=@ReplaceWith(expression="Instant.ofEpochMilli(this)", imports={"java.time.Instant"}))
  @RequiresApi(26)
  @NotNull
  public static final Instant asEpochMillis(long paramLong)
  {
    Instant localInstant = Instant.ofEpochMilli(paramLong);
    Intrinsics.checkExpressionValueIsNotNull(localInstant, "Instant.ofEpochMilli(this)");
    return localInstant;
  }
  
  @Deprecated(message="These extensions are for java.* APIs and not android.* APIs and thus are out of scope for this project. Star https://youtrack.jetbrains.com/issue/KT-21585 for future support of extensions like these. These extensions will be removed before core-ktx 1.0!", replaceWith=@ReplaceWith(expression="Instant.ofEpochSecond(this)", imports={"java.time.Instant"}))
  @RequiresApi(26)
  @NotNull
  public static final Instant asEpochSeconds(long paramLong)
  {
    Instant localInstant = Instant.ofEpochSecond(paramLong);
    Intrinsics.checkExpressionValueIsNotNull(localInstant, "Instant.ofEpochSecond(this)");
    return localInstant;
  }
  
  @Deprecated(message="These extensions are for java.* APIs and not android.* APIs and thus are out of scope for this project. Star https://youtrack.jetbrains.com/issue/KT-21585 for future support of extensions like these. These extensions will be removed before core-ktx 1.0!")
  @RequiresApi(26)
  public static final long component1(@NotNull Instant paramInstant)
  {
    return paramInstant.getEpochSecond();
  }
  
  @Deprecated(message="These extensions are for java.* APIs and not android.* APIs and thus are out of scope for this project. Star https://youtrack.jetbrains.com/issue/KT-21585 for future support of extensions like these. These extensions will be removed before core-ktx 1.0!")
  @RequiresApi(26)
  public static final int component2(@NotNull Instant paramInstant)
  {
    return paramInstant.getNano();
  }
}
