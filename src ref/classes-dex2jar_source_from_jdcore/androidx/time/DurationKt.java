package androidx.time;

import android.support.annotation.RequiresApi;
import java.time.Duration;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000\024\n\000\n\002\020\t\n\002\030\002\n\000\n\002\020\b\n\002\b\013\032\r\020\000\032\0020\001*\0020\002H\n\032\r\020\003\032\0020\004*\0020\002H\n\032\025\020\005\032\0020\002*\0020\0022\006\020\006\032\0020\001H\n\032\r\020\007\032\0020\002*\0020\004H\b\032\r\020\007\032\0020\002*\0020\001H\b\032\r\020\b\032\0020\002*\0020\004H\b\032\r\020\b\032\0020\002*\0020\001H\b\032\r\020\t\032\0020\002*\0020\004H\b\032\r\020\t\032\0020\002*\0020\001H\b\032\r\020\n\032\0020\002*\0020\004H\b\032\r\020\n\032\0020\002*\0020\001H\b\032\r\020\013\032\0020\002*\0020\004H\b\032\r\020\013\032\0020\002*\0020\001H\b\032\025\020\f\032\0020\002*\0020\0022\006\020\r\032\0020\001H\n\032\r\020\016\032\0020\002*\0020\002H\n¨\006\017"}, d2={"component1", "", "Ljava/time/Duration;", "component2", "", "div", "divisor", "hours", "millis", "minutes", "nanos", "seconds", "times", "multiplicand", "unaryMinus", "core-ktx_release"}, k=2, mv={1, 1, 9})
public final class DurationKt
{
  @Deprecated(message="These extensions are for java.* APIs and not android.* APIs and thus are out of scope for this project. Star https://youtrack.jetbrains.com/issue/KT-21585 for future support of extensions like these. These extensions will be removed before core-ktx 1.0!")
  @RequiresApi(26)
  public static final long component1(@NotNull Duration paramDuration)
  {
    return paramDuration.getSeconds();
  }
  
  @Deprecated(message="These extensions are for java.* APIs and not android.* APIs and thus are out of scope for this project. Star https://youtrack.jetbrains.com/issue/KT-21585 for future support of extensions like these. These extensions will be removed before core-ktx 1.0!")
  @RequiresApi(26)
  public static final int component2(@NotNull Duration paramDuration)
  {
    return paramDuration.getNano();
  }
  
  @Deprecated(message="These extensions are for java.* APIs and not android.* APIs and thus are out of scope for this project. Star https://youtrack.jetbrains.com/issue/KT-21585 for future support of extensions like these. These extensions will be removed before core-ktx 1.0!", replaceWith=@ReplaceWith(expression="this.dividedBy(divisor)", imports={}))
  @RequiresApi(26)
  @NotNull
  public static final Duration div(@NotNull Duration paramDuration, long paramLong)
  {
    paramDuration = paramDuration.dividedBy(paramLong);
    Intrinsics.checkExpressionValueIsNotNull(paramDuration, "dividedBy(divisor)");
    return paramDuration;
  }
  
  @Deprecated(message="These extensions are for java.* APIs and not android.* APIs and thus are out of scope for this project. Star https://youtrack.jetbrains.com/issue/KT-21585 for future support of extensions like these. These extensions will be removed before core-ktx 1.0!", replaceWith=@ReplaceWith(expression="Duration.ofHours(this.toLong())", imports={"java.time.Duration"}))
  @RequiresApi(26)
  @NotNull
  public static final Duration hours(int paramInt)
  {
    Duration localDuration = Duration.ofHours(paramInt);
    Intrinsics.checkExpressionValueIsNotNull(localDuration, "Duration.ofHours(toLong())");
    return localDuration;
  }
  
  @Deprecated(message="These extensions are for java.* APIs and not android.* APIs and thus are out of scope for this project. Star https://youtrack.jetbrains.com/issue/KT-21585 for future support of extensions like these. These extensions will be removed before core-ktx 1.0!", replaceWith=@ReplaceWith(expression="Duration.ofHours(this)", imports={"java.time.Duration"}))
  @RequiresApi(26)
  @NotNull
  public static final Duration hours(long paramLong)
  {
    Duration localDuration = Duration.ofHours(paramLong);
    Intrinsics.checkExpressionValueIsNotNull(localDuration, "Duration.ofHours(this)");
    return localDuration;
  }
  
  @Deprecated(message="These extensions are for java.* APIs and not android.* APIs and thus are out of scope for this project. Star https://youtrack.jetbrains.com/issue/KT-21585 for future support of extensions like these. These extensions will be removed before core-ktx 1.0!", replaceWith=@ReplaceWith(expression="Duration.ofMillis(this.toLong())", imports={"java.time.Duration"}))
  @RequiresApi(26)
  @NotNull
  public static final Duration millis(int paramInt)
  {
    Duration localDuration = Duration.ofMillis(paramInt);
    Intrinsics.checkExpressionValueIsNotNull(localDuration, "Duration.ofMillis(toLong())");
    return localDuration;
  }
  
  @Deprecated(message="These extensions are for java.* APIs and not android.* APIs and thus are out of scope for this project. Star https://youtrack.jetbrains.com/issue/KT-21585 for future support of extensions like these. These extensions will be removed before core-ktx 1.0!", replaceWith=@ReplaceWith(expression="Duration.ofMillis(this)", imports={"java.time.Duration"}))
  @RequiresApi(26)
  @NotNull
  public static final Duration millis(long paramLong)
  {
    Duration localDuration = Duration.ofMillis(paramLong);
    Intrinsics.checkExpressionValueIsNotNull(localDuration, "Duration.ofMillis(this)");
    return localDuration;
  }
  
  @Deprecated(message="These extensions are for java.* APIs and not android.* APIs and thus are out of scope for this project. Star https://youtrack.jetbrains.com/issue/KT-21585 for future support of extensions like these. These extensions will be removed before core-ktx 1.0!", replaceWith=@ReplaceWith(expression="Duration.ofMinutes(this.toLong())", imports={"java.time.Duration"}))
  @RequiresApi(26)
  @NotNull
  public static final Duration minutes(int paramInt)
  {
    Duration localDuration = Duration.ofMinutes(paramInt);
    Intrinsics.checkExpressionValueIsNotNull(localDuration, "Duration.ofMinutes(toLong())");
    return localDuration;
  }
  
  @Deprecated(message="These extensions are for java.* APIs and not android.* APIs and thus are out of scope for this project. Star https://youtrack.jetbrains.com/issue/KT-21585 for future support of extensions like these. These extensions will be removed before core-ktx 1.0!", replaceWith=@ReplaceWith(expression="Duration.ofMinutes(this)", imports={"java.time.Duration"}))
  @RequiresApi(26)
  @NotNull
  public static final Duration minutes(long paramLong)
  {
    Duration localDuration = Duration.ofMinutes(paramLong);
    Intrinsics.checkExpressionValueIsNotNull(localDuration, "Duration.ofMinutes(this)");
    return localDuration;
  }
  
  @Deprecated(message="These extensions are for java.* APIs and not android.* APIs and thus are out of scope for this project. Star https://youtrack.jetbrains.com/issue/KT-21585 for future support of extensions like these. These extensions will be removed before core-ktx 1.0!", replaceWith=@ReplaceWith(expression="Duration.ofNanos(this.toLong())", imports={"java.time.Duration"}))
  @RequiresApi(26)
  @NotNull
  public static final Duration nanos(int paramInt)
  {
    Duration localDuration = Duration.ofNanos(paramInt);
    Intrinsics.checkExpressionValueIsNotNull(localDuration, "Duration.ofNanos(toLong())");
    return localDuration;
  }
  
  @Deprecated(message="These extensions are for java.* APIs and not android.* APIs and thus are out of scope for this project. Star https://youtrack.jetbrains.com/issue/KT-21585 for future support of extensions like these. These extensions will be removed before core-ktx 1.0!", replaceWith=@ReplaceWith(expression="Duration.ofNanos(this)", imports={"java.time.Duration"}))
  @RequiresApi(26)
  @NotNull
  public static final Duration nanos(long paramLong)
  {
    Duration localDuration = Duration.ofNanos(paramLong);
    Intrinsics.checkExpressionValueIsNotNull(localDuration, "Duration.ofNanos(this)");
    return localDuration;
  }
  
  @Deprecated(message="These extensions are for java.* APIs and not android.* APIs and thus are out of scope for this project. Star https://youtrack.jetbrains.com/issue/KT-21585 for future support of extensions like these. These extensions will be removed before core-ktx 1.0!", replaceWith=@ReplaceWith(expression="Duration.ofSeconds(this.toLong())", imports={"java.time.Duration"}))
  @RequiresApi(26)
  @NotNull
  public static final Duration seconds(int paramInt)
  {
    Duration localDuration = Duration.ofSeconds(paramInt);
    Intrinsics.checkExpressionValueIsNotNull(localDuration, "Duration.ofSeconds(toLong())");
    return localDuration;
  }
  
  @Deprecated(message="These extensions are for java.* APIs and not android.* APIs and thus are out of scope for this project. Star https://youtrack.jetbrains.com/issue/KT-21585 for future support of extensions like these. These extensions will be removed before core-ktx 1.0!", replaceWith=@ReplaceWith(expression="Duration.ofSeconds(this)", imports={"java.time.Duration"}))
  @RequiresApi(26)
  @NotNull
  public static final Duration seconds(long paramLong)
  {
    Duration localDuration = Duration.ofSeconds(paramLong);
    Intrinsics.checkExpressionValueIsNotNull(localDuration, "Duration.ofSeconds(this)");
    return localDuration;
  }
  
  @Deprecated(message="These extensions are for java.* APIs and not android.* APIs and thus are out of scope for this project. Star https://youtrack.jetbrains.com/issue/KT-21585 for future support of extensions like these. These extensions will be removed before core-ktx 1.0!", replaceWith=@ReplaceWith(expression="this.multipliedBy(multiplicand)", imports={}))
  @RequiresApi(26)
  @NotNull
  public static final Duration times(@NotNull Duration paramDuration, long paramLong)
  {
    paramDuration = paramDuration.multipliedBy(paramLong);
    Intrinsics.checkExpressionValueIsNotNull(paramDuration, "multipliedBy(multiplicand)");
    return paramDuration;
  }
  
  @Deprecated(message="These extensions are for java.* APIs and not android.* APIs and thus are out of scope for this project. Star https://youtrack.jetbrains.com/issue/KT-21585 for future support of extensions like these. These extensions will be removed before core-ktx 1.0!", replaceWith=@ReplaceWith(expression="this.negated()", imports={}))
  @RequiresApi(26)
  @NotNull
  public static final Duration unaryMinus(@NotNull Duration paramDuration)
  {
    paramDuration = paramDuration.negated();
    Intrinsics.checkExpressionValueIsNotNull(paramDuration, "negated()");
    return paramDuration;
  }
}
