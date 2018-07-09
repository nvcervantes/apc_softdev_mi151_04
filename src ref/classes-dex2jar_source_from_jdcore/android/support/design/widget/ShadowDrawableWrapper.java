package android.support.design.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.FillType;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.Drawable;
import android.support.design.R.color;
import android.support.v4.content.ContextCompat;
import android.support.v7.graphics.drawable.DrawableWrapper;

class ShadowDrawableWrapper
  extends DrawableWrapper
{
  static final double COS_45 = Math.cos(Math.toRadians(45.0D));
  static final float SHADOW_BOTTOM_SCALE = 1.0F;
  static final float SHADOW_HORIZ_SCALE = 0.5F;
  static final float SHADOW_MULTIPLIER = 1.5F;
  static final float SHADOW_TOP_SCALE = 0.25F;
  private boolean mAddPaddingForCorners = true;
  final RectF mContentBounds;
  float mCornerRadius;
  final Paint mCornerShadowPaint;
  Path mCornerShadowPath;
  private boolean mDirty = true;
  final Paint mEdgeShadowPaint;
  float mMaxShadowSize;
  private boolean mPrintedShadowClipWarning = false;
  float mRawMaxShadowSize;
  float mRawShadowSize;
  private float mRotation;
  private final int mShadowEndColor;
  private final int mShadowMiddleColor;
  float mShadowSize;
  private final int mShadowStartColor;
  
  public ShadowDrawableWrapper(Context paramContext, Drawable paramDrawable, float paramFloat1, float paramFloat2, float paramFloat3)
  {
    super(paramDrawable);
    mShadowStartColor = ContextCompat.getColor(paramContext, R.color.design_fab_shadow_start_color);
    mShadowMiddleColor = ContextCompat.getColor(paramContext, R.color.design_fab_shadow_mid_color);
    mShadowEndColor = ContextCompat.getColor(paramContext, R.color.design_fab_shadow_end_color);
    mCornerShadowPaint = new Paint(5);
    mCornerShadowPaint.setStyle(Paint.Style.FILL);
    mCornerRadius = Math.round(paramFloat1);
    mContentBounds = new RectF();
    mEdgeShadowPaint = new Paint(mCornerShadowPaint);
    mEdgeShadowPaint.setAntiAlias(false);
    setShadowSize(paramFloat2, paramFloat3);
  }
  
  private void buildComponents(Rect paramRect)
  {
    float f = mRawMaxShadowSize * 1.5F;
    mContentBounds.set(left + mRawMaxShadowSize, top + f, right - mRawMaxShadowSize, bottom - f);
    getWrappedDrawable().setBounds((int)mContentBounds.left, (int)mContentBounds.top, (int)mContentBounds.right, (int)mContentBounds.bottom);
    buildShadowCorners();
  }
  
  private void buildShadowCorners()
  {
    Object localObject = new RectF(-mCornerRadius, -mCornerRadius, mCornerRadius, mCornerRadius);
    RectF localRectF = new RectF((RectF)localObject);
    localRectF.inset(-mShadowSize, -mShadowSize);
    if (mCornerShadowPath == null) {
      mCornerShadowPath = new Path();
    } else {
      mCornerShadowPath.reset();
    }
    mCornerShadowPath.setFillType(Path.FillType.EVEN_ODD);
    mCornerShadowPath.moveTo(-mCornerRadius, 0.0F);
    mCornerShadowPath.rLineTo(-mShadowSize, 0.0F);
    mCornerShadowPath.arcTo(localRectF, 180.0F, 90.0F, false);
    mCornerShadowPath.arcTo((RectF)localObject, 270.0F, -90.0F, false);
    mCornerShadowPath.close();
    float f1 = -top;
    if (f1 > 0.0F)
    {
      f2 = mCornerRadius / f1;
      float f3 = (1.0F - f2) / 2.0F;
      localPaint = mCornerShadowPaint;
      i = mShadowStartColor;
      j = mShadowMiddleColor;
      k = mShadowEndColor;
      Shader.TileMode localTileMode = Shader.TileMode.CLAMP;
      localPaint.setShader(new RadialGradient(0.0F, 0.0F, f1, new int[] { 0, i, j, k }, new float[] { 0.0F, f2, f3 + f2, 1.0F }, localTileMode));
    }
    Paint localPaint = mEdgeShadowPaint;
    f1 = top;
    float f2 = top;
    int i = mShadowStartColor;
    int j = mShadowMiddleColor;
    int k = mShadowEndColor;
    localObject = Shader.TileMode.CLAMP;
    localPaint.setShader(new LinearGradient(0.0F, f1, 0.0F, f2, new int[] { i, j, k }, new float[] { 0.0F, 0.5F, 1.0F }, (Shader.TileMode)localObject));
    mEdgeShadowPaint.setAntiAlias(false);
  }
  
  public static float calculateHorizontalPadding(float paramFloat1, float paramFloat2, boolean paramBoolean)
  {
    if (paramBoolean) {
      return (float)(paramFloat1 + (1.0D - COS_45) * paramFloat2);
    }
    return paramFloat1;
  }
  
  public static float calculateVerticalPadding(float paramFloat1, float paramFloat2, boolean paramBoolean)
  {
    if (paramBoolean) {
      return (float)(paramFloat1 * 1.5F + (1.0D - COS_45) * paramFloat2);
    }
    return paramFloat1 * 1.5F;
  }
  
  private void drawShadow(Canvas paramCanvas)
  {
    int k = paramCanvas.save();
    paramCanvas.rotate(mRotation, mContentBounds.centerX(), mContentBounds.centerY());
    float f3 = -mCornerRadius - mShadowSize;
    float f4 = mCornerRadius;
    float f1 = mContentBounds.width();
    float f5 = 2.0F * f4;
    if (f1 - f5 > 0.0F) {
      i = 1;
    } else {
      i = 0;
    }
    int j;
    if (mContentBounds.height() - f5 > 0.0F) {
      j = 1;
    } else {
      j = 0;
    }
    float f2 = mRawShadowSize;
    float f8 = mRawShadowSize;
    float f6 = mRawShadowSize;
    float f9 = mRawShadowSize;
    f1 = mRawShadowSize;
    float f7 = mRawShadowSize;
    f6 = f4 / (f6 - f9 * 0.5F + f4);
    f2 = f4 / (f2 - f8 * 0.25F + f4);
    f1 = f4 / (f1 - f7 * 1.0F + f4);
    int m = paramCanvas.save();
    paramCanvas.translate(mContentBounds.left + f4, mContentBounds.top + f4);
    paramCanvas.scale(f6, f2);
    paramCanvas.drawPath(mCornerShadowPath, mCornerShadowPaint);
    if (i != 0)
    {
      paramCanvas.scale(1.0F / f6, 1.0F);
      paramCanvas.drawRect(0.0F, f3, mContentBounds.width() - f5, -mCornerRadius, mEdgeShadowPaint);
    }
    paramCanvas.restoreToCount(m);
    m = paramCanvas.save();
    paramCanvas.translate(mContentBounds.right - f4, mContentBounds.bottom - f4);
    paramCanvas.scale(f6, f1);
    paramCanvas.rotate(180.0F);
    paramCanvas.drawPath(mCornerShadowPath, mCornerShadowPaint);
    if (i != 0)
    {
      paramCanvas.scale(1.0F / f6, 1.0F);
      paramCanvas.drawRect(0.0F, f3, mContentBounds.width() - f5, -mCornerRadius + mShadowSize, mEdgeShadowPaint);
    }
    paramCanvas.restoreToCount(m);
    int i = paramCanvas.save();
    paramCanvas.translate(mContentBounds.left + f4, mContentBounds.bottom - f4);
    paramCanvas.scale(f6, f1);
    paramCanvas.rotate(270.0F);
    paramCanvas.drawPath(mCornerShadowPath, mCornerShadowPaint);
    if (j != 0)
    {
      paramCanvas.scale(1.0F / f1, 1.0F);
      paramCanvas.drawRect(0.0F, f3, mContentBounds.height() - f5, -mCornerRadius, mEdgeShadowPaint);
    }
    paramCanvas.restoreToCount(i);
    i = paramCanvas.save();
    paramCanvas.translate(mContentBounds.right - f4, mContentBounds.top + f4);
    paramCanvas.scale(f6, f2);
    paramCanvas.rotate(90.0F);
    paramCanvas.drawPath(mCornerShadowPath, mCornerShadowPaint);
    if (j != 0)
    {
      paramCanvas.scale(1.0F / f2, 1.0F);
      paramCanvas.drawRect(0.0F, f3, mContentBounds.height() - f5, -mCornerRadius, mEdgeShadowPaint);
    }
    paramCanvas.restoreToCount(i);
    paramCanvas.restoreToCount(k);
  }
  
  private static int toEven(float paramFloat)
  {
    int j = Math.round(paramFloat);
    int i = j;
    if (j % 2 == 1) {
      i = j - 1;
    }
    return i;
  }
  
  public void draw(Canvas paramCanvas)
  {
    if (mDirty)
    {
      buildComponents(getBounds());
      mDirty = false;
    }
    drawShadow(paramCanvas);
    super.draw(paramCanvas);
  }
  
  public float getCornerRadius()
  {
    return mCornerRadius;
  }
  
  public float getMaxShadowSize()
  {
    return mRawMaxShadowSize;
  }
  
  public float getMinHeight()
  {
    return Math.max(mRawMaxShadowSize, mCornerRadius + mRawMaxShadowSize * 1.5F / 2.0F) * 2.0F + mRawMaxShadowSize * 1.5F * 2.0F;
  }
  
  public float getMinWidth()
  {
    return Math.max(mRawMaxShadowSize, mCornerRadius + mRawMaxShadowSize / 2.0F) * 2.0F + mRawMaxShadowSize * 2.0F;
  }
  
  public int getOpacity()
  {
    return -3;
  }
  
  public boolean getPadding(Rect paramRect)
  {
    int i = (int)Math.ceil(calculateVerticalPadding(mRawMaxShadowSize, mCornerRadius, mAddPaddingForCorners));
    int j = (int)Math.ceil(calculateHorizontalPadding(mRawMaxShadowSize, mCornerRadius, mAddPaddingForCorners));
    paramRect.set(j, i, j, i);
    return true;
  }
  
  public float getShadowSize()
  {
    return mRawShadowSize;
  }
  
  protected void onBoundsChange(Rect paramRect)
  {
    mDirty = true;
  }
  
  public void setAddPaddingForCorners(boolean paramBoolean)
  {
    mAddPaddingForCorners = paramBoolean;
    invalidateSelf();
  }
  
  public void setAlpha(int paramInt)
  {
    super.setAlpha(paramInt);
    mCornerShadowPaint.setAlpha(paramInt);
    mEdgeShadowPaint.setAlpha(paramInt);
  }
  
  public void setCornerRadius(float paramFloat)
  {
    paramFloat = Math.round(paramFloat);
    if (mCornerRadius == paramFloat) {
      return;
    }
    mCornerRadius = paramFloat;
    mDirty = true;
    invalidateSelf();
  }
  
  public void setMaxShadowSize(float paramFloat)
  {
    setShadowSize(mRawShadowSize, paramFloat);
  }
  
  final void setRotation(float paramFloat)
  {
    if (mRotation != paramFloat)
    {
      mRotation = paramFloat;
      invalidateSelf();
    }
  }
  
  public void setShadowSize(float paramFloat)
  {
    setShadowSize(paramFloat, mRawMaxShadowSize);
  }
  
  void setShadowSize(float paramFloat1, float paramFloat2)
  {
    if ((paramFloat1 >= 0.0F) && (paramFloat2 >= 0.0F))
    {
      float f = toEven(paramFloat1);
      paramFloat2 = toEven(paramFloat2);
      paramFloat1 = f;
      if (f > paramFloat2)
      {
        if (!mPrintedShadowClipWarning) {
          mPrintedShadowClipWarning = true;
        }
        paramFloat1 = paramFloat2;
      }
      if ((mRawShadowSize == paramFloat1) && (mRawMaxShadowSize == paramFloat2)) {
        return;
      }
      mRawShadowSize = paramFloat1;
      mRawMaxShadowSize = paramFloat2;
      mShadowSize = Math.round(paramFloat1 * 1.5F);
      mMaxShadowSize = paramFloat2;
      mDirty = true;
      invalidateSelf();
      return;
    }
    throw new IllegalArgumentException("invalid shadow size");
  }
}
