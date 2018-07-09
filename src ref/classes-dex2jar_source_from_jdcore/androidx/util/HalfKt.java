package androidx.util;

import android.support.annotation.RequiresApi;
import android.util.Half;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000\030\n\000\n\002\030\002\n\002\020\n\n\002\020\006\n\002\020\007\n\002\020\016\n\000\032\r\020\000\032\0020\001*\0020\002H\b\032\r\020\000\032\0020\001*\0020\003H\b\032\r\020\000\032\0020\001*\0020\004H\b\032\r\020\000\032\0020\001*\0020\005H\b¨\006\006"}, d2={"toHalf", "Landroid/util/Half;", "", "", "", "", "core-ktx_release"}, k=2, mv={1, 1, 9})
public final class HalfKt
{
  @RequiresApi(26)
  @NotNull
  public static final Half toHalf(double paramDouble)
  {
    Half localHalf = Half.valueOf((float)paramDouble);
    Intrinsics.checkExpressionValueIsNotNull(localHalf, "Half.valueOf(this)");
    return localHalf;
  }
  
  @RequiresApi(26)
  @NotNull
  public static final Half toHalf(float paramFloat)
  {
    Half localHalf = Half.valueOf(paramFloat);
    Intrinsics.checkExpressionValueIsNotNull(localHalf, "Half.valueOf(this)");
    return localHalf;
  }
  
  @RequiresApi(26)
  @NotNull
  public static final Half toHalf(@NotNull String paramString)
  {
    paramString = Half.valueOf(paramString);
    Intrinsics.checkExpressionValueIsNotNull(paramString, "Half.valueOf(this)");
    return paramString;
  }
  
  @RequiresApi(26)
  @NotNull
  public static final Half toHalf(short paramShort)
  {
    Half localHalf = Half.valueOf(paramShort);
    Intrinsics.checkExpressionValueIsNotNull(localHalf, "Half.valueOf(this)");
    return localHalf;
  }
}
