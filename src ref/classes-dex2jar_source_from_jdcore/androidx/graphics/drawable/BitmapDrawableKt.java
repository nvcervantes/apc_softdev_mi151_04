package androidx.graphics.drawable;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000\022\n\000\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\000\032\025\020\000\032\0020\001*\0020\0022\006\020\003\032\0020\004H\b¨\006\005"}, d2={"toDrawable", "Landroid/graphics/drawable/BitmapDrawable;", "Landroid/graphics/Bitmap;", "resources", "Landroid/content/res/Resources;", "core-ktx_release"}, k=2, mv={1, 1, 9})
public final class BitmapDrawableKt
{
  @NotNull
  public static final BitmapDrawable toDrawable(@NotNull Bitmap paramBitmap, @NotNull Resources paramResources)
  {
    return new BitmapDrawable(paramResources, paramBitmap);
  }
}
