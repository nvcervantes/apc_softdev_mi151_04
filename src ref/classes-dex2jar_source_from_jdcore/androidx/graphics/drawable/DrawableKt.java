package androidx.graphics.drawable;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Px;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000\"\n\000\n\002\030\002\n\002\030\002\n\000\n\002\020\b\n\002\b\002\n\002\030\002\n\000\n\002\020\002\n\002\b\005\032*\020\000\032\0020\001*\0020\0022\b\b\003\020\003\032\0020\0042\b\b\003\020\005\032\0020\0042\n\b\002\020\006\032\004\030\0010\007\0322\020\b\032\0020\t*\0020\0022\b\b\003\020\n\032\0020\0042\b\b\003\020\013\032\0020\0042\b\b\003\020\f\032\0020\0042\b\b\003\020\r\032\0020\004¨\006\016"}, d2={"toBitmap", "Landroid/graphics/Bitmap;", "Landroid/graphics/drawable/Drawable;", "width", "", "height", "config", "Landroid/graphics/Bitmap$Config;", "updateBounds", "", "left", "top", "right", "bottom", "core-ktx_release"}, k=2, mv={1, 1, 9})
public final class DrawableKt
{
  @NotNull
  public static final Bitmap toBitmap(@NotNull Drawable paramDrawable, @Px int paramInt1, @Px int paramInt2, @Nullable Bitmap.Config paramConfig)
  {
    if ((paramDrawable instanceof BitmapDrawable)) {
      if (paramConfig != null)
      {
        localObject = ((BitmapDrawable)paramDrawable).getBitmap();
        Intrinsics.checkExpressionValueIsNotNull(localObject, "bitmap");
        if (!Intrinsics.areEqual(((Bitmap)localObject).getConfig(), paramConfig)) {}
      }
      else
      {
        paramDrawable = (BitmapDrawable)paramDrawable;
        if ((paramInt1 == paramDrawable.getIntrinsicWidth()) && (paramInt2 == paramDrawable.getIntrinsicHeight()))
        {
          paramDrawable = paramDrawable.getBitmap();
          Intrinsics.checkExpressionValueIsNotNull(paramDrawable, "bitmap");
          return paramDrawable;
        }
        paramDrawable = Bitmap.createScaledBitmap(paramDrawable.getBitmap(), paramInt1, paramInt2, true);
        Intrinsics.checkExpressionValueIsNotNull(paramDrawable, "Bitmap.createScaledBitma…map, width, height, true)");
        return paramDrawable;
      }
    }
    Object localObject = paramDrawable.getBounds();
    int i = left;
    int j = top;
    int k = right;
    int m = bottom;
    if (paramConfig == null) {
      paramConfig = Bitmap.Config.ARGB_8888;
    }
    paramConfig = Bitmap.createBitmap(paramInt1, paramInt2, paramConfig);
    paramDrawable.setBounds(0, 0, paramInt1, paramInt2);
    paramDrawable.draw(new Canvas(paramConfig));
    paramDrawable.setBounds(i, j, k, m);
    Intrinsics.checkExpressionValueIsNotNull(paramConfig, "bitmap");
    return paramConfig;
  }
  
  public static final void updateBounds(@NotNull Drawable paramDrawable, @Px int paramInt1, @Px int paramInt2, @Px int paramInt3, @Px int paramInt4)
  {
    paramDrawable.setBounds(paramInt1, paramInt2, paramInt3, paramInt4);
  }
}
