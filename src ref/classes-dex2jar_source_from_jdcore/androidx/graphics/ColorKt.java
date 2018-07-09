package androidx.graphics;

import android.graphics.Color;
import android.graphics.ColorSpace;
import android.support.annotation.ColorInt;
import android.support.annotation.RequiresApi;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\0006\n\000\n\002\020\b\n\002\b\002\n\002\020\007\n\002\020\t\n\002\b\004\n\002\030\002\n\002\b\005\n\002\020\013\n\002\b\b\n\002\030\002\n\002\b\007\n\002\020\016\n\002\b\002\032\r\020\030\032\0020\001*\0020\001H\n\032\r\020\030\032\0020\004*\0020\005H\n\032\r\020\030\032\0020\004*\0020\031H\n\032\r\020\032\032\0020\001*\0020\001H\n\032\r\020\032\032\0020\004*\0020\005H\n\032\r\020\032\032\0020\004*\0020\031H\n\032\r\020\033\032\0020\001*\0020\001H\n\032\r\020\033\032\0020\004*\0020\005H\n\032\r\020\033\032\0020\004*\0020\031H\n\032\r\020\034\032\0020\001*\0020\001H\n\032\r\020\034\032\0020\004*\0020\005H\n\032\r\020\034\032\0020\004*\0020\031H\n\032\025\020\035\032\0020\031*\0020\0312\006\020\036\032\0020\031H\002\032\r\020\037\032\0020\031*\0020\001H\b\032\r\020\037\032\0020\031*\0020\005H\b\032\r\020 \032\0020\001*\0020\005H\b\032\r\020 \032\0020\001*\0020!H\b\032\r\020\"\032\0020\005*\0020\001H\b\"\026\020\000\032\0020\001*\0020\0018Æ\002¢\006\006\032\004\b\002\020\003\"\026\020\000\032\0020\004*\0020\0058Ç\002¢\006\006\032\004\b\002\020\006\"\026\020\007\032\0020\001*\0020\0018Æ\002¢\006\006\032\004\b\b\020\003\"\026\020\007\032\0020\004*\0020\0058Ç\002¢\006\006\032\004\b\b\020\006\"\026\020\t\032\0020\n*\0020\0058Ç\002¢\006\006\032\004\b\013\020\f\"\026\020\r\032\0020\001*\0020\0018Æ\002¢\006\006\032\004\b\016\020\003\"\026\020\r\032\0020\004*\0020\0058Ç\002¢\006\006\032\004\b\016\020\006\"\026\020\017\032\0020\020*\0020\0058Ç\002¢\006\006\032\004\b\017\020\021\"\026\020\022\032\0020\020*\0020\0058Ç\002¢\006\006\032\004\b\022\020\021\"\026\020\023\032\0020\004*\0020\0018Ç\002¢\006\006\032\004\b\024\020\025\"\026\020\023\032\0020\004*\0020\0058Ç\002¢\006\006\032\004\b\024\020\006\"\026\020\026\032\0020\001*\0020\0018Æ\002¢\006\006\032\004\b\027\020\003\"\026\020\026\032\0020\004*\0020\0058Ç\002¢\006\006\032\004\b\027\020\006¨\006#"}, d2={"alpha", "", "getAlpha", "(I)I", "", "", "(J)F", "blue", "getBlue", "colorSpace", "Landroid/graphics/ColorSpace;", "getColorSpace", "(J)Landroid/graphics/ColorSpace;", "green", "getGreen", "isSrgb", "", "(J)Z", "isWideGamut", "luminance", "getLuminance", "(I)F", "red", "getRed", "component1", "Landroid/graphics/Color;", "component2", "component3", "component4", "plus", "c", "toColor", "toColorInt", "", "toColorLong", "core-ktx_release"}, k=2, mv={1, 1, 9})
public final class ColorKt
{
  @RequiresApi(26)
  public static final float component1(long paramLong)
  {
    return Color.red(paramLong);
  }
  
  @RequiresApi(26)
  public static final float component1(@NotNull Color paramColor)
  {
    return paramColor.getComponent(0);
  }
  
  public static final int component1(@ColorInt int paramInt)
  {
    return paramInt >> 24 & 0xFF;
  }
  
  @RequiresApi(26)
  public static final float component2(long paramLong)
  {
    return Color.green(paramLong);
  }
  
  @RequiresApi(26)
  public static final float component2(@NotNull Color paramColor)
  {
    return paramColor.getComponent(1);
  }
  
  public static final int component2(@ColorInt int paramInt)
  {
    return paramInt >> 16 & 0xFF;
  }
  
  @RequiresApi(26)
  public static final float component3(long paramLong)
  {
    return Color.blue(paramLong);
  }
  
  @RequiresApi(26)
  public static final float component3(@NotNull Color paramColor)
  {
    return paramColor.getComponent(2);
  }
  
  public static final int component3(@ColorInt int paramInt)
  {
    return paramInt >> 8 & 0xFF;
  }
  
  @RequiresApi(26)
  public static final float component4(long paramLong)
  {
    return Color.alpha(paramLong);
  }
  
  @RequiresApi(26)
  public static final float component4(@NotNull Color paramColor)
  {
    return paramColor.getComponent(3);
  }
  
  public static final int component4(@ColorInt int paramInt)
  {
    return paramInt & 0xFF;
  }
  
  @RequiresApi(26)
  public static final float getAlpha(long paramLong)
  {
    return Color.alpha(paramLong);
  }
  
  public static final int getAlpha(@ColorInt int paramInt)
  {
    return paramInt >> 24 & 0xFF;
  }
  
  @RequiresApi(26)
  public static final float getBlue(long paramLong)
  {
    return Color.blue(paramLong);
  }
  
  public static final int getBlue(@ColorInt int paramInt)
  {
    return paramInt & 0xFF;
  }
  
  @RequiresApi(26)
  @NotNull
  public static final ColorSpace getColorSpace(long paramLong)
  {
    ColorSpace localColorSpace = Color.colorSpace(paramLong);
    Intrinsics.checkExpressionValueIsNotNull(localColorSpace, "Color.colorSpace(this)");
    return localColorSpace;
  }
  
  @RequiresApi(26)
  public static final float getGreen(long paramLong)
  {
    return Color.green(paramLong);
  }
  
  public static final int getGreen(@ColorInt int paramInt)
  {
    return paramInt >> 8 & 0xFF;
  }
  
  @RequiresApi(26)
  public static final float getLuminance(@ColorInt int paramInt)
  {
    return Color.luminance(paramInt);
  }
  
  @RequiresApi(26)
  public static final float getLuminance(long paramLong)
  {
    return Color.luminance(paramLong);
  }
  
  @RequiresApi(26)
  public static final float getRed(long paramLong)
  {
    return Color.red(paramLong);
  }
  
  public static final int getRed(@ColorInt int paramInt)
  {
    return paramInt >> 16 & 0xFF;
  }
  
  @RequiresApi(26)
  public static final boolean isSrgb(long paramLong)
  {
    return Color.isSrgb(paramLong);
  }
  
  @RequiresApi(26)
  public static final boolean isWideGamut(long paramLong)
  {
    return Color.isWideGamut(paramLong);
  }
  
  @RequiresApi(26)
  @NotNull
  public static final Color plus(@NotNull Color paramColor1, @NotNull Color paramColor2)
  {
    if ((Intrinsics.areEqual(paramColor1.getModel(), paramColor2.getModel()) ^ true))
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("Color models must match (");
      ((StringBuilder)localObject).append(paramColor1.getModel());
      ((StringBuilder)localObject).append(" vs ");
      ((StringBuilder)localObject).append(paramColor2.getModel());
      throw ((Throwable)new IllegalArgumentException(((StringBuilder)localObject).toString()));
    }
    Object localObject = paramColor2;
    if ((Intrinsics.areEqual(paramColor1.getColorSpace(), paramColor2.getColorSpace()) ^ true)) {
      localObject = paramColor2.convert(paramColor1.getColorSpace());
    }
    Intrinsics.checkExpressionValueIsNotNull(localObject, "s");
    paramColor2 = ((Color)localObject).getComponents();
    float[] arrayOfFloat = paramColor1.getComponents();
    float f4 = ((Color)localObject).alpha();
    float f3 = paramColor1.alpha() * (1.0F - f4);
    int k = paramColor1.getComponentCount() - 1;
    arrayOfFloat[k] = (f4 + f3);
    float f5 = arrayOfFloat[k];
    int j = 0;
    float f2 = f3;
    int i = j;
    float f1 = f4;
    if (f5 > 0)
    {
      f1 = f4 / arrayOfFloat[k];
      f2 = f3 / arrayOfFloat[k];
      i = j;
    }
    while (i < k)
    {
      arrayOfFloat[i] = (paramColor2[i] * f1 + arrayOfFloat[i] * f2);
      i += 1;
    }
    paramColor1 = Color.valueOf(arrayOfFloat, paramColor1.getColorSpace());
    Intrinsics.checkExpressionValueIsNotNull(paramColor1, "Color.valueOf(dst, colorSpace)");
    return paramColor1;
  }
  
  @RequiresApi(26)
  @NotNull
  public static final Color toColor(@ColorInt int paramInt)
  {
    Color localColor = Color.valueOf(paramInt);
    Intrinsics.checkExpressionValueIsNotNull(localColor, "Color.valueOf(this)");
    return localColor;
  }
  
  @RequiresApi(26)
  @NotNull
  public static final Color toColor(long paramLong)
  {
    Color localColor = Color.valueOf(paramLong);
    Intrinsics.checkExpressionValueIsNotNull(localColor, "Color.valueOf(this)");
    return localColor;
  }
  
  @ColorInt
  @RequiresApi(26)
  public static final int toColorInt(long paramLong)
  {
    return Color.toArgb(paramLong);
  }
  
  @ColorInt
  public static final int toColorInt(@NotNull String paramString)
  {
    return Color.parseColor(paramString);
  }
  
  @RequiresApi(26)
  public static final long toColorLong(@ColorInt int paramInt)
  {
    return Color.pack(paramInt);
  }
}
