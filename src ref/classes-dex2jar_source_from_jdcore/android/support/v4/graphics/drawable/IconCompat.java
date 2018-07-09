package android.support.v4.graphics.drawable;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.Intent.ShortcutIconResource;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Parcelable;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.annotation.VisibleForTesting;
import android.support.v4.content.ContextCompat;

public class IconCompat
{
  private static final float ADAPTIVE_ICON_INSET_FACTOR = 0.25F;
  private static final int AMBIENT_SHADOW_ALPHA = 30;
  private static final float BLUR_FACTOR = 0.010416667F;
  private static final float DEFAULT_VIEW_PORT_SCALE = 0.6666667F;
  private static final float ICON_DIAMETER_FACTOR = 0.9166667F;
  private static final int KEY_SHADOW_ALPHA = 61;
  private static final float KEY_SHADOW_OFFSET_FACTOR = 0.020833334F;
  private static final int TYPE_ADAPTIVE_BITMAP = 5;
  private static final int TYPE_BITMAP = 1;
  private static final int TYPE_DATA = 3;
  private static final int TYPE_RESOURCE = 2;
  private static final int TYPE_URI = 4;
  private int mInt1;
  private int mInt2;
  private Object mObj1;
  private final int mType;
  
  private IconCompat(int paramInt)
  {
    mType = paramInt;
  }
  
  @VisibleForTesting
  static Bitmap createLegacyIconFromAdaptiveIcon(Bitmap paramBitmap, boolean paramBoolean)
  {
    int i = (int)(0.6666667F * Math.min(paramBitmap.getWidth(), paramBitmap.getHeight()));
    Bitmap localBitmap = Bitmap.createBitmap(i, i, Bitmap.Config.ARGB_8888);
    Canvas localCanvas = new Canvas(localBitmap);
    Paint localPaint = new Paint(3);
    float f1 = i;
    float f2 = 0.5F * f1;
    float f3 = 0.9166667F * f2;
    if (paramBoolean)
    {
      float f4 = 0.010416667F * f1;
      localPaint.setColor(0);
      localPaint.setShadowLayer(f4, 0.0F, 0.020833334F * f1, 1023410176);
      localCanvas.drawCircle(f2, f2, f3, localPaint);
      localPaint.setShadowLayer(f4, 0.0F, 0.0F, 503316480);
      localCanvas.drawCircle(f2, f2, f3, localPaint);
      localPaint.clearShadowLayer();
    }
    localPaint.setColor(-16777216);
    BitmapShader localBitmapShader = new BitmapShader(paramBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
    Matrix localMatrix = new Matrix();
    localMatrix.setTranslate(-(paramBitmap.getWidth() - i) / 2, -(paramBitmap.getHeight() - i) / 2);
    localBitmapShader.setLocalMatrix(localMatrix);
    localPaint.setShader(localBitmapShader);
    localCanvas.drawCircle(f2, f2, f3, localPaint);
    localCanvas.setBitmap(null);
    return localBitmap;
  }
  
  public static IconCompat createWithAdaptiveBitmap(Bitmap paramBitmap)
  {
    if (paramBitmap == null) {
      throw new IllegalArgumentException("Bitmap must not be null.");
    }
    IconCompat localIconCompat = new IconCompat(5);
    mObj1 = paramBitmap;
    return localIconCompat;
  }
  
  public static IconCompat createWithBitmap(Bitmap paramBitmap)
  {
    if (paramBitmap == null) {
      throw new IllegalArgumentException("Bitmap must not be null.");
    }
    IconCompat localIconCompat = new IconCompat(1);
    mObj1 = paramBitmap;
    return localIconCompat;
  }
  
  public static IconCompat createWithContentUri(Uri paramUri)
  {
    if (paramUri == null) {
      throw new IllegalArgumentException("Uri must not be null.");
    }
    return createWithContentUri(paramUri.toString());
  }
  
  public static IconCompat createWithContentUri(String paramString)
  {
    if (paramString == null) {
      throw new IllegalArgumentException("Uri must not be null.");
    }
    IconCompat localIconCompat = new IconCompat(4);
    mObj1 = paramString;
    return localIconCompat;
  }
  
  public static IconCompat createWithData(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    if (paramArrayOfByte == null) {
      throw new IllegalArgumentException("Data must not be null.");
    }
    IconCompat localIconCompat = new IconCompat(3);
    mObj1 = paramArrayOfByte;
    mInt1 = paramInt1;
    mInt2 = paramInt2;
    return localIconCompat;
  }
  
  public static IconCompat createWithResource(Context paramContext, @DrawableRes int paramInt)
  {
    if (paramContext == null) {
      throw new IllegalArgumentException("Context must not be null.");
    }
    IconCompat localIconCompat = new IconCompat(2);
    mInt1 = paramInt;
    mObj1 = paramContext;
    return localIconCompat;
  }
  
  @Deprecated
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public void addToShortcutIntent(@NonNull Intent paramIntent)
  {
    addToShortcutIntent(paramIntent, null);
  }
  
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public void addToShortcutIntent(@NonNull Intent paramIntent, @Nullable Drawable paramDrawable)
  {
    int i = mType;
    Object localObject1;
    if (i != 5)
    {
      Object localObject2;
      switch (i)
      {
      default: 
        throw new IllegalArgumentException("Icon type not supported for intent shortcuts");
      case 2: 
        if (paramDrawable == null)
        {
          paramIntent.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", Intent.ShortcutIconResource.fromContext((Context)mObj1, mInt1));
          return;
        }
        localObject1 = (Context)mObj1;
        localObject2 = ContextCompat.getDrawable((Context)localObject1, mInt1);
        if ((((Drawable)localObject2).getIntrinsicWidth() > 0) && (((Drawable)localObject2).getIntrinsicHeight() > 0))
        {
          localObject1 = Bitmap.createBitmap(((Drawable)localObject2).getIntrinsicWidth(), ((Drawable)localObject2).getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        }
        else
        {
          i = ((ActivityManager)((Context)localObject1).getSystemService("activity")).getLauncherLargeIconSize();
          localObject1 = Bitmap.createBitmap(i, i, Bitmap.Config.ARGB_8888);
        }
        ((Drawable)localObject2).setBounds(0, 0, ((Bitmap)localObject1).getWidth(), ((Bitmap)localObject1).getHeight());
        ((Drawable)localObject2).draw(new Canvas((Bitmap)localObject1));
        break;
      case 1: 
        localObject2 = (Bitmap)mObj1;
        localObject1 = localObject2;
        if (paramDrawable == null) {
          break;
        }
        localObject1 = ((Bitmap)localObject2).copy(((Bitmap)localObject2).getConfig(), true);
        break;
      }
    }
    else
    {
      localObject1 = createLegacyIconFromAdaptiveIcon((Bitmap)mObj1, true);
    }
    if (paramDrawable != null)
    {
      i = ((Bitmap)localObject1).getWidth();
      int j = ((Bitmap)localObject1).getHeight();
      paramDrawable.setBounds(i / 2, j / 2, i, j);
      paramDrawable.draw(new Canvas((Bitmap)localObject1));
    }
    paramIntent.putExtra("android.intent.extra.shortcut.ICON", (Parcelable)localObject1);
  }
  
  @RequiresApi(23)
  public Icon toIcon()
  {
    switch (mType)
    {
    default: 
      throw new IllegalArgumentException("Unknown type");
    case 5: 
      if (Build.VERSION.SDK_INT >= 26) {
        return Icon.createWithAdaptiveBitmap((Bitmap)mObj1);
      }
      return Icon.createWithBitmap(createLegacyIconFromAdaptiveIcon((Bitmap)mObj1, false));
    case 4: 
      return Icon.createWithContentUri((String)mObj1);
    case 3: 
      return Icon.createWithData((byte[])mObj1, mInt1, mInt2);
    case 2: 
      return Icon.createWithResource((Context)mObj1, mInt1);
    }
    return Icon.createWithBitmap((Bitmap)mObj1);
  }
}
