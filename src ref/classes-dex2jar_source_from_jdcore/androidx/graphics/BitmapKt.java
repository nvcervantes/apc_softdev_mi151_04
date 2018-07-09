package androidx.graphics;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.ColorSpace;
import android.support.annotation.ColorInt;
import android.support.annotation.RequiresApi;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\0008\n\000\n\002\030\002\n\000\n\002\020\b\n\002\b\002\n\002\030\002\n\000\n\002\020\013\n\000\n\002\030\002\n\002\b\002\n\002\030\002\n\002\030\002\n\002\020\002\n\002\030\002\n\002\b\b\032#\020\000\032\0020\0012\006\020\002\032\0020\0032\006\020\004\032\0020\0032\b\b\002\020\005\032\0020\006H\b\0327\020\000\032\0020\0012\006\020\002\032\0020\0032\006\020\004\032\0020\0032\b\b\002\020\005\032\0020\0062\b\b\002\020\007\032\0020\b2\b\b\002\020\t\032\0020\nH\b\032&\020\013\032\0020\001*\0020\0012\027\020\f\032\023\022\004\022\0020\016\022\004\022\0020\0170\r¢\006\002\b\020H\b\032\035\020\021\032\0020\003*\0020\0012\006\020\022\032\0020\0032\006\020\023\032\0020\003H\n\032'\020\024\032\0020\001*\0020\0012\006\020\002\032\0020\0032\006\020\004\032\0020\0032\b\b\002\020\025\032\0020\bH\b\032'\020\026\032\0020\017*\0020\0012\006\020\022\032\0020\0032\006\020\023\032\0020\0032\b\b\001\020\027\032\0020\003H\n¨\006\030"}, d2={"createBitmap", "Landroid/graphics/Bitmap;", "width", "", "height", "config", "Landroid/graphics/Bitmap$Config;", "hasAlpha", "", "colorSpace", "Landroid/graphics/ColorSpace;", "applyCanvas", "block", "Lkotlin/Function1;", "Landroid/graphics/Canvas;", "", "Lkotlin/ExtensionFunctionType;", "get", "x", "y", "scale", "filter", "set", "color", "core-ktx_release"}, k=2, mv={1, 1, 9})
public final class BitmapKt
{
  @NotNull
  public static final Bitmap applyCanvas(@NotNull Bitmap paramBitmap, @NotNull Function1<? super Canvas, Unit> paramFunction1)
  {
    paramFunction1.invoke(new Canvas(paramBitmap));
    return paramBitmap;
  }
  
  @NotNull
  public static final Bitmap createBitmap(int paramInt1, int paramInt2, @NotNull Bitmap.Config paramConfig)
  {
    paramConfig = Bitmap.createBitmap(paramInt1, paramInt2, paramConfig);
    Intrinsics.checkExpressionValueIsNotNull(paramConfig, "Bitmap.createBitmap(width, height, config)");
    return paramConfig;
  }
  
  @RequiresApi(26)
  @NotNull
  public static final Bitmap createBitmap(int paramInt1, int paramInt2, @NotNull Bitmap.Config paramConfig, boolean paramBoolean, @NotNull ColorSpace paramColorSpace)
  {
    paramConfig = Bitmap.createBitmap(paramInt1, paramInt2, paramConfig, paramBoolean, paramColorSpace);
    Intrinsics.checkExpressionValueIsNotNull(paramConfig, "Bitmap.createBitmap(widt…ig, hasAlpha, colorSpace)");
    return paramConfig;
  }
  
  public static final int get(@NotNull Bitmap paramBitmap, int paramInt1, int paramInt2)
  {
    return paramBitmap.getPixel(paramInt1, paramInt2);
  }
  
  @NotNull
  public static final Bitmap scale(@NotNull Bitmap paramBitmap, int paramInt1, int paramInt2, boolean paramBoolean)
  {
    paramBitmap = Bitmap.createScaledBitmap(paramBitmap, paramInt1, paramInt2, paramBoolean);
    Intrinsics.checkExpressionValueIsNotNull(paramBitmap, "Bitmap.createScaledBitma…s, width, height, filter)");
    return paramBitmap;
  }
  
  public static final void set(@NotNull Bitmap paramBitmap, int paramInt1, int paramInt2, @ColorInt int paramInt3)
  {
    paramBitmap.setPixel(paramInt1, paramInt2, paramInt3);
  }
}
