package androidx.graphics.drawable;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.ColorInt;
import android.support.annotation.RequiresApi;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000\020\n\000\n\002\030\002\n\002\020\b\n\002\030\002\n\000\032\r\020\000\032\0020\001*\0020\002H\b\032\r\020\000\032\0020\001*\0020\003H\b¨\006\004"}, d2={"toDrawable", "Landroid/graphics/drawable/ColorDrawable;", "", "Landroid/graphics/Color;", "core-ktx_release"}, k=2, mv={1, 1, 9})
public final class ColorDrawableKt
{
  @NotNull
  public static final ColorDrawable toDrawable(@ColorInt int paramInt)
  {
    return new ColorDrawable(paramInt);
  }
  
  @RequiresApi(26)
  @NotNull
  public static final ColorDrawable toDrawable(@NotNull Color paramColor)
  {
    return new ColorDrawable(paramColor.toArgb());
  }
}
