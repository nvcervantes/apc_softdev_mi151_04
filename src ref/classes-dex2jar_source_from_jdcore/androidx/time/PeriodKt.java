package androidx.time;

import android.support.annotation.RequiresApi;
import java.time.Period;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000\016\n\000\n\002\020\b\n\002\030\002\n\002\b\t\032\r\020\000\032\0020\001*\0020\002H\n\032\r\020\003\032\0020\001*\0020\002H\n\032\r\020\004\032\0020\001*\0020\002H\n\032\r\020\005\032\0020\002*\0020\001H\b\032\r\020\006\032\0020\002*\0020\001H\b\032\025\020\007\032\0020\002*\0020\0022\006\020\b\032\0020\001H\n\032\r\020\t\032\0020\002*\0020\002H\n\032\r\020\n\032\0020\002*\0020\001H\b¨\006\013"}, d2={"component1", "", "Ljava/time/Period;", "component2", "component3", "days", "months", "times", "multiplicand", "unaryMinus", "years", "core-ktx_release"}, k=2, mv={1, 1, 9})
public final class PeriodKt
{
  @Deprecated(message="These extensions are for java.* APIs and not android.* APIs and thus are out of scope for this project. Star https://youtrack.jetbrains.com/issue/KT-21585 for future support of extensions like these. These extensions will be removed before core-ktx 1.0!")
  @RequiresApi(26)
  public static final int component1(@NotNull Period paramPeriod)
  {
    return paramPeriod.getYears();
  }
  
  @Deprecated(message="These extensions are for java.* APIs and not android.* APIs and thus are out of scope for this project. Star https://youtrack.jetbrains.com/issue/KT-21585 for future support of extensions like these. These extensions will be removed before core-ktx 1.0!")
  @RequiresApi(26)
  public static final int component2(@NotNull Period paramPeriod)
  {
    return paramPeriod.getMonths();
  }
  
  @Deprecated(message="These extensions are for java.* APIs and not android.* APIs and thus are out of scope for this project. Star https://youtrack.jetbrains.com/issue/KT-21585 for future support of extensions like these. These extensions will be removed before core-ktx 1.0!")
  @RequiresApi(26)
  public static final int component3(@NotNull Period paramPeriod)
  {
    return paramPeriod.getDays();
  }
  
  @Deprecated(message="These extensions are for java.* APIs and not android.* APIs and thus are out of scope for this project. Star https://youtrack.jetbrains.com/issue/KT-21585 for future support of extensions like these. These extensions will be removed before core-ktx 1.0!", replaceWith=@ReplaceWith(expression="Period.ofDays(this)", imports={"java.time.Period"}))
  @RequiresApi(26)
  @NotNull
  public static final Period days(int paramInt)
  {
    Period localPeriod = Period.ofDays(paramInt);
    Intrinsics.checkExpressionValueIsNotNull(localPeriod, "Period.ofDays(this)");
    return localPeriod;
  }
  
  @Deprecated(message="These extensions are for java.* APIs and not android.* APIs and thus are out of scope for this project. Star https://youtrack.jetbrains.com/issue/KT-21585 for future support of extensions like these. These extensions will be removed before core-ktx 1.0!", replaceWith=@ReplaceWith(expression="Period.ofMonths(this)", imports={"java.time.Period"}))
  @RequiresApi(26)
  @NotNull
  public static final Period months(int paramInt)
  {
    Period localPeriod = Period.ofMonths(paramInt);
    Intrinsics.checkExpressionValueIsNotNull(localPeriod, "Period.ofMonths(this)");
    return localPeriod;
  }
  
  @Deprecated(message="These extensions are for java.* APIs and not android.* APIs and thus are out of scope for this project. Star https://youtrack.jetbrains.com/issue/KT-21585 for future support of extensions like these. These extensions will be removed before core-ktx 1.0!", replaceWith=@ReplaceWith(expression="this.multipliedBy(multiplicand)", imports={}))
  @RequiresApi(26)
  @NotNull
  public static final Period times(@NotNull Period paramPeriod, int paramInt)
  {
    paramPeriod = paramPeriod.multipliedBy(paramInt);
    Intrinsics.checkExpressionValueIsNotNull(paramPeriod, "multipliedBy(multiplicand)");
    return paramPeriod;
  }
  
  @Deprecated(message="These extensions are for java.* APIs and not android.* APIs and thus are out of scope for this project. Star https://youtrack.jetbrains.com/issue/KT-21585 for future support of extensions like these. These extensions will be removed before core-ktx 1.0!", replaceWith=@ReplaceWith(expression="this.negated()", imports={}))
  @RequiresApi(26)
  @NotNull
  public static final Period unaryMinus(@NotNull Period paramPeriod)
  {
    paramPeriod = paramPeriod.negated();
    Intrinsics.checkExpressionValueIsNotNull(paramPeriod, "negated()");
    return paramPeriod;
  }
  
  @Deprecated(message="These extensions are for java.* APIs and not android.* APIs and thus are out of scope for this project. Star https://youtrack.jetbrains.com/issue/KT-21585 for future support of extensions like these. These extensions will be removed before core-ktx 1.0!", replaceWith=@ReplaceWith(expression="Period.ofYears(this)", imports={"java.time.Period"}))
  @RequiresApi(26)
  @NotNull
  public static final Period years(int paramInt)
  {
    Period localPeriod = Period.ofYears(paramInt);
    Intrinsics.checkExpressionValueIsNotNull(localPeriod, "Period.ofYears(this)");
    return localPeriod;
  }
}
