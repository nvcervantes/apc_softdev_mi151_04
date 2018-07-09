package android.support.design.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.os.Build.VERSION;
import android.support.annotation.ColorInt;
import android.support.v4.math.MathUtils;
import android.support.v4.text.TextDirectionHeuristicCompat;
import android.support.v4.text.TextDirectionHeuristicsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.R.styleable;
import android.support.v7.widget.TintTypedArray;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.view.View;
import android.view.animation.Interpolator;

final class CollapsingTextHelper
{
  private static final boolean DEBUG_DRAW = false;
  private static final Paint DEBUG_DRAW_PAINT;
  private static final boolean USE_SCALING_TEXTURE;
  private boolean mBoundsChanged;
  private final Rect mCollapsedBounds;
  private float mCollapsedDrawX;
  private float mCollapsedDrawY;
  private int mCollapsedShadowColor;
  private float mCollapsedShadowDx;
  private float mCollapsedShadowDy;
  private float mCollapsedShadowRadius;
  private ColorStateList mCollapsedTextColor;
  private int mCollapsedTextGravity = 16;
  private float mCollapsedTextSize = 15.0F;
  private Typeface mCollapsedTypeface;
  private final RectF mCurrentBounds;
  private float mCurrentDrawX;
  private float mCurrentDrawY;
  private float mCurrentTextSize;
  private Typeface mCurrentTypeface;
  private boolean mDrawTitle;
  private final Rect mExpandedBounds;
  private float mExpandedDrawX;
  private float mExpandedDrawY;
  private float mExpandedFraction;
  private int mExpandedShadowColor;
  private float mExpandedShadowDx;
  private float mExpandedShadowDy;
  private float mExpandedShadowRadius;
  private ColorStateList mExpandedTextColor;
  private int mExpandedTextGravity = 16;
  private float mExpandedTextSize = 15.0F;
  private Bitmap mExpandedTitleTexture;
  private Typeface mExpandedTypeface;
  private boolean mIsRtl;
  private Interpolator mPositionInterpolator;
  private float mScale;
  private int[] mState;
  private CharSequence mText;
  private final TextPaint mTextPaint;
  private Interpolator mTextSizeInterpolator;
  private CharSequence mTextToDraw;
  private float mTextureAscent;
  private float mTextureDescent;
  private Paint mTexturePaint;
  private boolean mUseTexture;
  private final View mView;
  
  static
  {
    boolean bool;
    if (Build.VERSION.SDK_INT < 18) {
      bool = true;
    } else {
      bool = false;
    }
    USE_SCALING_TEXTURE = bool;
    if (DEBUG_DRAW_PAINT != null)
    {
      DEBUG_DRAW_PAINT.setAntiAlias(true);
      DEBUG_DRAW_PAINT.setColor(-65281);
    }
  }
  
  public CollapsingTextHelper(View paramView)
  {
    mView = paramView;
    mTextPaint = new TextPaint(129);
    mCollapsedBounds = new Rect();
    mExpandedBounds = new Rect();
    mCurrentBounds = new RectF();
  }
  
  private boolean areTypefacesDifferent(Typeface paramTypeface1, Typeface paramTypeface2)
  {
    return ((paramTypeface1 != null) && (!paramTypeface1.equals(paramTypeface2))) || ((paramTypeface1 == null) && (paramTypeface2 != null));
  }
  
  private static int blendColors(int paramInt1, int paramInt2, float paramFloat)
  {
    float f1 = 1.0F - paramFloat;
    float f2 = Color.alpha(paramInt1);
    float f3 = Color.alpha(paramInt2);
    float f4 = Color.red(paramInt1);
    float f5 = Color.red(paramInt2);
    float f6 = Color.green(paramInt1);
    float f7 = Color.green(paramInt2);
    float f8 = Color.blue(paramInt1);
    float f9 = Color.blue(paramInt2);
    return Color.argb((int)(f2 * f1 + f3 * paramFloat), (int)(f4 * f1 + f5 * paramFloat), (int)(f6 * f1 + f7 * paramFloat), (int)(f8 * f1 + f9 * paramFloat));
  }
  
  private void calculateBaseOffsets()
  {
    throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\r\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.provideAs(TypeTransformer.java:780)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.e1expr(TypeTransformer.java:496)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:713)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.enexpr(TypeTransformer.java:698)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:719)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.s2stmt(TypeTransformer.java:820)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.sxStmt(TypeTransformer.java:843)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:206)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\r\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\r\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\r\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\r\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\r\n");
  }
  
  private void calculateCurrentOffsets()
  {
    calculateOffsets(mExpandedFraction);
  }
  
  private boolean calculateIsRtl(CharSequence paramCharSequence)
  {
    int j = ViewCompat.getLayoutDirection(mView);
    int i = 1;
    if (j != 1) {
      i = 0;
    }
    TextDirectionHeuristicCompat localTextDirectionHeuristicCompat;
    if (i != 0) {
      localTextDirectionHeuristicCompat = TextDirectionHeuristicsCompat.FIRSTSTRONG_RTL;
    } else {
      localTextDirectionHeuristicCompat = TextDirectionHeuristicsCompat.FIRSTSTRONG_LTR;
    }
    return localTextDirectionHeuristicCompat.isRtl(paramCharSequence, 0, paramCharSequence.length());
  }
  
  private void calculateOffsets(float paramFloat)
  {
    interpolateBounds(paramFloat);
    mCurrentDrawX = lerp(mExpandedDrawX, mCollapsedDrawX, paramFloat, mPositionInterpolator);
    mCurrentDrawY = lerp(mExpandedDrawY, mCollapsedDrawY, paramFloat, mPositionInterpolator);
    setInterpolatedTextSize(lerp(mExpandedTextSize, mCollapsedTextSize, paramFloat, mTextSizeInterpolator));
    if (mCollapsedTextColor != mExpandedTextColor) {
      mTextPaint.setColor(blendColors(getCurrentExpandedTextColor(), getCurrentCollapsedTextColor(), paramFloat));
    } else {
      mTextPaint.setColor(getCurrentCollapsedTextColor());
    }
    mTextPaint.setShadowLayer(lerp(mExpandedShadowRadius, mCollapsedShadowRadius, paramFloat, null), lerp(mExpandedShadowDx, mCollapsedShadowDx, paramFloat, null), lerp(mExpandedShadowDy, mCollapsedShadowDy, paramFloat, null), blendColors(mExpandedShadowColor, mCollapsedShadowColor, paramFloat));
    ViewCompat.postInvalidateOnAnimation(mView);
  }
  
  private void calculateUsingTextSize(float paramFloat)
  {
    if (mText == null) {
      return;
    }
    float f2 = mCollapsedBounds.width();
    float f3 = mExpandedBounds.width();
    boolean bool2 = isClose(paramFloat, mCollapsedTextSize);
    boolean bool1 = true;
    float f1;
    int i;
    if (bool2)
    {
      f1 = mCollapsedTextSize;
      mScale = 1.0F;
      if (areTypefacesDifferent(mCurrentTypeface, mCollapsedTypeface))
      {
        mCurrentTypeface = mCollapsedTypeface;
        i = 1;
      }
      else
      {
        i = 0;
      }
      paramFloat = f2;
    }
    else
    {
      f1 = mExpandedTextSize;
      if (areTypefacesDifferent(mCurrentTypeface, mExpandedTypeface))
      {
        mCurrentTypeface = mExpandedTypeface;
        i = 1;
      }
      else
      {
        i = 0;
      }
      if (isClose(paramFloat, mExpandedTextSize)) {
        mScale = 1.0F;
      } else {
        mScale = (paramFloat / mExpandedTextSize);
      }
      paramFloat = mCollapsedTextSize / mExpandedTextSize;
      if (f3 * paramFloat > f2) {
        paramFloat = Math.min(f2 / paramFloat, f3);
      } else {
        paramFloat = f3;
      }
    }
    int j = i;
    if (paramFloat > 0.0F)
    {
      if ((mCurrentTextSize == f1) && (!mBoundsChanged) && (i == 0)) {
        i = 0;
      } else {
        i = 1;
      }
      mCurrentTextSize = f1;
      mBoundsChanged = false;
      j = i;
    }
    if ((mTextToDraw == null) || (j != 0))
    {
      mTextPaint.setTextSize(mCurrentTextSize);
      mTextPaint.setTypeface(mCurrentTypeface);
      Object localObject = mTextPaint;
      if (mScale == 1.0F) {
        bool1 = false;
      }
      ((TextPaint)localObject).setLinearText(bool1);
      localObject = TextUtils.ellipsize(mText, mTextPaint, paramFloat, TextUtils.TruncateAt.END);
      if (!TextUtils.equals((CharSequence)localObject, mTextToDraw))
      {
        mTextToDraw = ((CharSequence)localObject);
        mIsRtl = calculateIsRtl(mTextToDraw);
      }
    }
  }
  
  private void clearTexture()
  {
    if (mExpandedTitleTexture != null)
    {
      mExpandedTitleTexture.recycle();
      mExpandedTitleTexture = null;
    }
  }
  
  private void ensureExpandedTexture()
  {
    if ((mExpandedTitleTexture == null) && (!mExpandedBounds.isEmpty()))
    {
      if (TextUtils.isEmpty(mTextToDraw)) {
        return;
      }
      calculateOffsets(0.0F);
      mTextureAscent = mTextPaint.ascent();
      mTextureDescent = mTextPaint.descent();
      int i = Math.round(mTextPaint.measureText(mTextToDraw, 0, mTextToDraw.length()));
      int j = Math.round(mTextureDescent - mTextureAscent);
      if (i > 0)
      {
        if (j <= 0) {
          return;
        }
        mExpandedTitleTexture = Bitmap.createBitmap(i, j, Bitmap.Config.ARGB_8888);
        new Canvas(mExpandedTitleTexture).drawText(mTextToDraw, 0, mTextToDraw.length(), 0.0F, j - mTextPaint.descent(), mTextPaint);
        if (mTexturePaint == null) {
          mTexturePaint = new Paint(3);
        }
        return;
      }
      return;
    }
  }
  
  @ColorInt
  private int getCurrentCollapsedTextColor()
  {
    if (mState != null) {
      return mCollapsedTextColor.getColorForState(mState, 0);
    }
    return mCollapsedTextColor.getDefaultColor();
  }
  
  @ColorInt
  private int getCurrentExpandedTextColor()
  {
    if (mState != null) {
      return mExpandedTextColor.getColorForState(mState, 0);
    }
    return mExpandedTextColor.getDefaultColor();
  }
  
  private void interpolateBounds(float paramFloat)
  {
    mCurrentBounds.left = lerp(mExpandedBounds.left, mCollapsedBounds.left, paramFloat, mPositionInterpolator);
    mCurrentBounds.top = lerp(mExpandedDrawY, mCollapsedDrawY, paramFloat, mPositionInterpolator);
    mCurrentBounds.right = lerp(mExpandedBounds.right, mCollapsedBounds.right, paramFloat, mPositionInterpolator);
    mCurrentBounds.bottom = lerp(mExpandedBounds.bottom, mCollapsedBounds.bottom, paramFloat, mPositionInterpolator);
  }
  
  private static boolean isClose(float paramFloat1, float paramFloat2)
  {
    return Math.abs(paramFloat1 - paramFloat2) < 0.001F;
  }
  
  private static float lerp(float paramFloat1, float paramFloat2, float paramFloat3, Interpolator paramInterpolator)
  {
    float f = paramFloat3;
    if (paramInterpolator != null) {
      f = paramInterpolator.getInterpolation(paramFloat3);
    }
    return AnimationUtils.lerp(paramFloat1, paramFloat2, f);
  }
  
  private Typeface readFontFamilyTypeface(int paramInt)
  {
    TypedArray localTypedArray = mView.getContext().obtainStyledAttributes(paramInt, new int[] { 16843692 });
    try
    {
      Object localObject1 = localTypedArray.getString(0);
      if (localObject1 != null)
      {
        localObject1 = Typeface.create((String)localObject1, 0);
        return localObject1;
      }
      return null;
    }
    finally
    {
      localTypedArray.recycle();
    }
  }
  
  private static boolean rectEquals(Rect paramRect, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    return (left == paramInt1) && (top == paramInt2) && (right == paramInt3) && (bottom == paramInt4);
  }
  
  private void setInterpolatedTextSize(float paramFloat)
  {
    calculateUsingTextSize(paramFloat);
    boolean bool;
    if ((USE_SCALING_TEXTURE) && (mScale != 1.0F)) {
      bool = true;
    } else {
      bool = false;
    }
    mUseTexture = bool;
    if (mUseTexture) {
      ensureExpandedTexture();
    }
    ViewCompat.postInvalidateOnAnimation(mView);
  }
  
  public void draw(Canvas paramCanvas)
  {
    int j = paramCanvas.save();
    if ((mTextToDraw != null) && (mDrawTitle))
    {
      float f4 = mCurrentDrawX;
      float f3 = mCurrentDrawY;
      int i;
      if ((mUseTexture) && (mExpandedTitleTexture != null)) {
        i = 1;
      } else {
        i = 0;
      }
      float f1;
      if (i != 0)
      {
        f1 = mTextureAscent * mScale;
        f2 = mTextureDescent;
        f2 = mScale;
      }
      else
      {
        f1 = mTextPaint.ascent() * mScale;
        mTextPaint.descent();
        f2 = mScale;
      }
      float f2 = f3;
      if (i != 0) {
        f2 = f3 + f1;
      }
      if (mScale != 1.0F) {
        paramCanvas.scale(mScale, mScale, f4, f2);
      }
      if (i != 0) {
        paramCanvas.drawBitmap(mExpandedTitleTexture, f4, f2, mTexturePaint);
      } else {
        paramCanvas.drawText(mTextToDraw, 0, mTextToDraw.length(), f4, f2, mTextPaint);
      }
    }
    paramCanvas.restoreToCount(j);
  }
  
  ColorStateList getCollapsedTextColor()
  {
    return mCollapsedTextColor;
  }
  
  int getCollapsedTextGravity()
  {
    return mCollapsedTextGravity;
  }
  
  float getCollapsedTextSize()
  {
    return mCollapsedTextSize;
  }
  
  Typeface getCollapsedTypeface()
  {
    if (mCollapsedTypeface != null) {
      return mCollapsedTypeface;
    }
    return Typeface.DEFAULT;
  }
  
  ColorStateList getExpandedTextColor()
  {
    return mExpandedTextColor;
  }
  
  int getExpandedTextGravity()
  {
    return mExpandedTextGravity;
  }
  
  float getExpandedTextSize()
  {
    return mExpandedTextSize;
  }
  
  Typeface getExpandedTypeface()
  {
    if (mExpandedTypeface != null) {
      return mExpandedTypeface;
    }
    return Typeface.DEFAULT;
  }
  
  float getExpansionFraction()
  {
    return mExpandedFraction;
  }
  
  CharSequence getText()
  {
    return mText;
  }
  
  final boolean isStateful()
  {
    return ((mCollapsedTextColor != null) && (mCollapsedTextColor.isStateful())) || ((mExpandedTextColor != null) && (mExpandedTextColor.isStateful()));
  }
  
  void onBoundsChanged()
  {
    boolean bool;
    if ((mCollapsedBounds.width() > 0) && (mCollapsedBounds.height() > 0) && (mExpandedBounds.width() > 0) && (mExpandedBounds.height() > 0)) {
      bool = true;
    } else {
      bool = false;
    }
    mDrawTitle = bool;
  }
  
  public void recalculate()
  {
    if ((mView.getHeight() > 0) && (mView.getWidth() > 0))
    {
      calculateBaseOffsets();
      calculateCurrentOffsets();
    }
  }
  
  void setCollapsedBounds(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if (!rectEquals(mCollapsedBounds, paramInt1, paramInt2, paramInt3, paramInt4))
    {
      mCollapsedBounds.set(paramInt1, paramInt2, paramInt3, paramInt4);
      mBoundsChanged = true;
      onBoundsChanged();
    }
  }
  
  void setCollapsedTextAppearance(int paramInt)
  {
    TintTypedArray localTintTypedArray = TintTypedArray.obtainStyledAttributes(mView.getContext(), paramInt, R.styleable.TextAppearance);
    if (localTintTypedArray.hasValue(R.styleable.TextAppearance_android_textColor)) {
      mCollapsedTextColor = localTintTypedArray.getColorStateList(R.styleable.TextAppearance_android_textColor);
    }
    if (localTintTypedArray.hasValue(R.styleable.TextAppearance_android_textSize)) {
      mCollapsedTextSize = localTintTypedArray.getDimensionPixelSize(R.styleable.TextAppearance_android_textSize, (int)mCollapsedTextSize);
    }
    mCollapsedShadowColor = localTintTypedArray.getInt(R.styleable.TextAppearance_android_shadowColor, 0);
    mCollapsedShadowDx = localTintTypedArray.getFloat(R.styleable.TextAppearance_android_shadowDx, 0.0F);
    mCollapsedShadowDy = localTintTypedArray.getFloat(R.styleable.TextAppearance_android_shadowDy, 0.0F);
    mCollapsedShadowRadius = localTintTypedArray.getFloat(R.styleable.TextAppearance_android_shadowRadius, 0.0F);
    localTintTypedArray.recycle();
    if (Build.VERSION.SDK_INT >= 16) {
      mCollapsedTypeface = readFontFamilyTypeface(paramInt);
    }
    recalculate();
  }
  
  void setCollapsedTextColor(ColorStateList paramColorStateList)
  {
    if (mCollapsedTextColor != paramColorStateList)
    {
      mCollapsedTextColor = paramColorStateList;
      recalculate();
    }
  }
  
  void setCollapsedTextGravity(int paramInt)
  {
    if (mCollapsedTextGravity != paramInt)
    {
      mCollapsedTextGravity = paramInt;
      recalculate();
    }
  }
  
  void setCollapsedTextSize(float paramFloat)
  {
    if (mCollapsedTextSize != paramFloat)
    {
      mCollapsedTextSize = paramFloat;
      recalculate();
    }
  }
  
  void setCollapsedTypeface(Typeface paramTypeface)
  {
    if (areTypefacesDifferent(mCollapsedTypeface, paramTypeface))
    {
      mCollapsedTypeface = paramTypeface;
      recalculate();
    }
  }
  
  void setExpandedBounds(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if (!rectEquals(mExpandedBounds, paramInt1, paramInt2, paramInt3, paramInt4))
    {
      mExpandedBounds.set(paramInt1, paramInt2, paramInt3, paramInt4);
      mBoundsChanged = true;
      onBoundsChanged();
    }
  }
  
  void setExpandedTextAppearance(int paramInt)
  {
    TintTypedArray localTintTypedArray = TintTypedArray.obtainStyledAttributes(mView.getContext(), paramInt, R.styleable.TextAppearance);
    if (localTintTypedArray.hasValue(R.styleable.TextAppearance_android_textColor)) {
      mExpandedTextColor = localTintTypedArray.getColorStateList(R.styleable.TextAppearance_android_textColor);
    }
    if (localTintTypedArray.hasValue(R.styleable.TextAppearance_android_textSize)) {
      mExpandedTextSize = localTintTypedArray.getDimensionPixelSize(R.styleable.TextAppearance_android_textSize, (int)mExpandedTextSize);
    }
    mExpandedShadowColor = localTintTypedArray.getInt(R.styleable.TextAppearance_android_shadowColor, 0);
    mExpandedShadowDx = localTintTypedArray.getFloat(R.styleable.TextAppearance_android_shadowDx, 0.0F);
    mExpandedShadowDy = localTintTypedArray.getFloat(R.styleable.TextAppearance_android_shadowDy, 0.0F);
    mExpandedShadowRadius = localTintTypedArray.getFloat(R.styleable.TextAppearance_android_shadowRadius, 0.0F);
    localTintTypedArray.recycle();
    if (Build.VERSION.SDK_INT >= 16) {
      mExpandedTypeface = readFontFamilyTypeface(paramInt);
    }
    recalculate();
  }
  
  void setExpandedTextColor(ColorStateList paramColorStateList)
  {
    if (mExpandedTextColor != paramColorStateList)
    {
      mExpandedTextColor = paramColorStateList;
      recalculate();
    }
  }
  
  void setExpandedTextGravity(int paramInt)
  {
    if (mExpandedTextGravity != paramInt)
    {
      mExpandedTextGravity = paramInt;
      recalculate();
    }
  }
  
  void setExpandedTextSize(float paramFloat)
  {
    if (mExpandedTextSize != paramFloat)
    {
      mExpandedTextSize = paramFloat;
      recalculate();
    }
  }
  
  void setExpandedTypeface(Typeface paramTypeface)
  {
    if (areTypefacesDifferent(mExpandedTypeface, paramTypeface))
    {
      mExpandedTypeface = paramTypeface;
      recalculate();
    }
  }
  
  void setExpansionFraction(float paramFloat)
  {
    paramFloat = MathUtils.clamp(paramFloat, 0.0F, 1.0F);
    if (paramFloat != mExpandedFraction)
    {
      mExpandedFraction = paramFloat;
      calculateCurrentOffsets();
    }
  }
  
  void setPositionInterpolator(Interpolator paramInterpolator)
  {
    mPositionInterpolator = paramInterpolator;
    recalculate();
  }
  
  final boolean setState(int[] paramArrayOfInt)
  {
    mState = paramArrayOfInt;
    if (isStateful())
    {
      recalculate();
      return true;
    }
    return false;
  }
  
  void setText(CharSequence paramCharSequence)
  {
    if ((paramCharSequence == null) || (!paramCharSequence.equals(mText)))
    {
      mText = paramCharSequence;
      mTextToDraw = null;
      clearTexture();
      recalculate();
    }
  }
  
  void setTextSizeInterpolator(Interpolator paramInterpolator)
  {
    mTextSizeInterpolator = paramInterpolator;
    recalculate();
  }
  
  void setTypefaces(Typeface paramTypeface)
  {
    mExpandedTypeface = paramTypeface;
    mCollapsedTypeface = paramTypeface;
    recalculate();
  }
}
