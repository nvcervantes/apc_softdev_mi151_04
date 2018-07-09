package android.support.design.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.R.color;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.ViewCompat;
import android.view.ViewPropertyAnimator;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.view.animation.Interpolator;

@RequiresApi(14)
class FloatingActionButtonImpl
{
  static final Interpolator ANIM_INTERPOLATOR = AnimationUtils.FAST_OUT_LINEAR_IN_INTERPOLATOR;
  static final int ANIM_STATE_HIDING = 1;
  static final int ANIM_STATE_NONE = 0;
  static final int ANIM_STATE_SHOWING = 2;
  static final int[] EMPTY_STATE_SET = new int[0];
  static final int[] ENABLED_STATE_SET;
  static final int[] FOCUSED_ENABLED_STATE_SET;
  static final long PRESSED_ANIM_DELAY = 100L;
  static final long PRESSED_ANIM_DURATION = 100L;
  static final int[] PRESSED_ENABLED_STATE_SET = { 16842919, 16842910 };
  static final int SHOW_HIDE_ANIM_DURATION = 200;
  int mAnimState = 0;
  CircularBorderDrawable mBorderDrawable;
  Drawable mContentBackground;
  float mElevation;
  private ViewTreeObserver.OnPreDrawListener mPreDrawListener;
  float mPressedTranslationZ;
  Drawable mRippleDrawable;
  private float mRotation;
  ShadowDrawableWrapper mShadowDrawable;
  final ShadowViewDelegate mShadowViewDelegate;
  Drawable mShapeDrawable;
  private final StateListAnimator mStateListAnimator;
  private final Rect mTmpRect = new Rect();
  final VisibilityAwareImageButton mView;
  
  static
  {
    FOCUSED_ENABLED_STATE_SET = new int[] { 16842908, 16842910 };
    ENABLED_STATE_SET = new int[] { 16842910 };
  }
  
  FloatingActionButtonImpl(VisibilityAwareImageButton paramVisibilityAwareImageButton, ShadowViewDelegate paramShadowViewDelegate)
  {
    mView = paramVisibilityAwareImageButton;
    mShadowViewDelegate = paramShadowViewDelegate;
    mStateListAnimator = new StateListAnimator();
    mStateListAnimator.addState(PRESSED_ENABLED_STATE_SET, createAnimator(new ElevateToTranslationZAnimation()));
    mStateListAnimator.addState(FOCUSED_ENABLED_STATE_SET, createAnimator(new ElevateToTranslationZAnimation()));
    mStateListAnimator.addState(ENABLED_STATE_SET, createAnimator(new ResetElevationAnimation()));
    mStateListAnimator.addState(EMPTY_STATE_SET, createAnimator(new DisabledElevationAnimation()));
    mRotation = mView.getRotation();
  }
  
  private ValueAnimator createAnimator(@NonNull ShadowAnimatorImpl paramShadowAnimatorImpl)
  {
    ValueAnimator localValueAnimator = new ValueAnimator();
    localValueAnimator.setInterpolator(ANIM_INTERPOLATOR);
    localValueAnimator.setDuration(100L);
    localValueAnimator.addListener(paramShadowAnimatorImpl);
    localValueAnimator.addUpdateListener(paramShadowAnimatorImpl);
    localValueAnimator.setFloatValues(new float[] { 0.0F, 1.0F });
    return localValueAnimator;
  }
  
  private static ColorStateList createColorStateList(int paramInt)
  {
    return new ColorStateList(new int[][] { FOCUSED_ENABLED_STATE_SET, PRESSED_ENABLED_STATE_SET, new int[0] }, new int[] { paramInt, paramInt, 0 });
  }
  
  private void ensurePreDrawListener()
  {
    if (mPreDrawListener == null) {
      mPreDrawListener = new ViewTreeObserver.OnPreDrawListener()
      {
        public boolean onPreDraw()
        {
          onPreDraw();
          return true;
        }
      };
    }
  }
  
  private boolean shouldAnimateVisibilityChange()
  {
    return (ViewCompat.isLaidOut(mView)) && (!mView.isInEditMode());
  }
  
  private void updateFromViewRotation()
  {
    if (Build.VERSION.SDK_INT == 19) {
      if (mRotation % 90.0F != 0.0F)
      {
        if (mView.getLayerType() != 1) {
          mView.setLayerType(1, null);
        }
      }
      else if (mView.getLayerType() != 0) {
        mView.setLayerType(0, null);
      }
    }
    if (mShadowDrawable != null) {
      mShadowDrawable.setRotation(-mRotation);
    }
    if (mBorderDrawable != null) {
      mBorderDrawable.setRotation(-mRotation);
    }
  }
  
  CircularBorderDrawable createBorderDrawable(int paramInt, ColorStateList paramColorStateList)
  {
    Context localContext = mView.getContext();
    CircularBorderDrawable localCircularBorderDrawable = newCircularDrawable();
    localCircularBorderDrawable.setGradientColors(ContextCompat.getColor(localContext, R.color.design_fab_stroke_top_outer_color), ContextCompat.getColor(localContext, R.color.design_fab_stroke_top_inner_color), ContextCompat.getColor(localContext, R.color.design_fab_stroke_end_inner_color), ContextCompat.getColor(localContext, R.color.design_fab_stroke_end_outer_color));
    localCircularBorderDrawable.setBorderWidth(paramInt);
    localCircularBorderDrawable.setBorderTint(paramColorStateList);
    return localCircularBorderDrawable;
  }
  
  GradientDrawable createShapeDrawable()
  {
    GradientDrawable localGradientDrawable = newGradientDrawableForShape();
    localGradientDrawable.setShape(1);
    localGradientDrawable.setColor(-1);
    return localGradientDrawable;
  }
  
  final Drawable getContentBackground()
  {
    return mContentBackground;
  }
  
  float getElevation()
  {
    return mElevation;
  }
  
  void getPadding(Rect paramRect)
  {
    mShadowDrawable.getPadding(paramRect);
  }
  
  void hide(@Nullable final InternalVisibilityChangedListener paramInternalVisibilityChangedListener, final boolean paramBoolean)
  {
    if (isOrWillBeHidden()) {
      return;
    }
    mView.animate().cancel();
    if (shouldAnimateVisibilityChange())
    {
      mAnimState = 1;
      mView.animate().scaleX(0.0F).scaleY(0.0F).alpha(0.0F).setDuration(200L).setInterpolator(AnimationUtils.FAST_OUT_LINEAR_IN_INTERPOLATOR).setListener(new AnimatorListenerAdapter()
      {
        private boolean mCancelled;
        
        public void onAnimationCancel(Animator paramAnonymousAnimator)
        {
          mCancelled = true;
        }
        
        public void onAnimationEnd(Animator paramAnonymousAnimator)
        {
          mAnimState = 0;
          if (!mCancelled)
          {
            paramAnonymousAnimator = mView;
            int i;
            if (paramBoolean) {
              i = 8;
            } else {
              i = 4;
            }
            paramAnonymousAnimator.internalSetVisibility(i, paramBoolean);
            if (paramInternalVisibilityChangedListener != null) {
              paramInternalVisibilityChangedListener.onHidden();
            }
          }
        }
        
        public void onAnimationStart(Animator paramAnonymousAnimator)
        {
          mView.internalSetVisibility(0, paramBoolean);
          mCancelled = false;
        }
      });
      return;
    }
    VisibilityAwareImageButton localVisibilityAwareImageButton = mView;
    int i;
    if (paramBoolean) {
      i = 8;
    } else {
      i = 4;
    }
    localVisibilityAwareImageButton.internalSetVisibility(i, paramBoolean);
    if (paramInternalVisibilityChangedListener != null) {
      paramInternalVisibilityChangedListener.onHidden();
    }
  }
  
  boolean isOrWillBeHidden()
  {
    int i = mView.getVisibility();
    boolean bool2 = false;
    boolean bool1 = false;
    if (i == 0)
    {
      if (mAnimState == 1) {
        bool1 = true;
      }
      return bool1;
    }
    bool1 = bool2;
    if (mAnimState != 2) {
      bool1 = true;
    }
    return bool1;
  }
  
  boolean isOrWillBeShown()
  {
    int i = mView.getVisibility();
    boolean bool2 = false;
    boolean bool1 = false;
    if (i != 0)
    {
      if (mAnimState == 2) {
        bool1 = true;
      }
      return bool1;
    }
    bool1 = bool2;
    if (mAnimState != 1) {
      bool1 = true;
    }
    return bool1;
  }
  
  void jumpDrawableToCurrentState()
  {
    mStateListAnimator.jumpToCurrentState();
  }
  
  CircularBorderDrawable newCircularDrawable()
  {
    return new CircularBorderDrawable();
  }
  
  GradientDrawable newGradientDrawableForShape()
  {
    return new GradientDrawable();
  }
  
  void onAttachedToWindow()
  {
    if (requirePreDrawListener())
    {
      ensurePreDrawListener();
      mView.getViewTreeObserver().addOnPreDrawListener(mPreDrawListener);
    }
  }
  
  void onCompatShadowChanged() {}
  
  void onDetachedFromWindow()
  {
    if (mPreDrawListener != null)
    {
      mView.getViewTreeObserver().removeOnPreDrawListener(mPreDrawListener);
      mPreDrawListener = null;
    }
  }
  
  void onDrawableStateChanged(int[] paramArrayOfInt)
  {
    mStateListAnimator.setState(paramArrayOfInt);
  }
  
  void onElevationsChanged(float paramFloat1, float paramFloat2)
  {
    if (mShadowDrawable != null)
    {
      mShadowDrawable.setShadowSize(paramFloat1, mPressedTranslationZ + paramFloat1);
      updatePadding();
    }
  }
  
  void onPaddingUpdated(Rect paramRect) {}
  
  void onPreDraw()
  {
    float f = mView.getRotation();
    if (mRotation != f)
    {
      mRotation = f;
      updateFromViewRotation();
    }
  }
  
  boolean requirePreDrawListener()
  {
    return true;
  }
  
  void setBackgroundDrawable(ColorStateList paramColorStateList, PorterDuff.Mode paramMode, int paramInt1, int paramInt2)
  {
    mShapeDrawable = DrawableCompat.wrap(createShapeDrawable());
    DrawableCompat.setTintList(mShapeDrawable, paramColorStateList);
    if (paramMode != null) {
      DrawableCompat.setTintMode(mShapeDrawable, paramMode);
    }
    mRippleDrawable = DrawableCompat.wrap(createShapeDrawable());
    DrawableCompat.setTintList(mRippleDrawable, createColorStateList(paramInt1));
    if (paramInt2 > 0)
    {
      mBorderDrawable = createBorderDrawable(paramInt2, paramColorStateList);
      paramColorStateList = new Drawable[3];
      paramColorStateList[0] = mBorderDrawable;
      paramColorStateList[1] = mShapeDrawable;
      paramColorStateList[2] = mRippleDrawable;
    }
    else
    {
      mBorderDrawable = null;
      paramColorStateList = new Drawable[2];
      paramColorStateList[0] = mShapeDrawable;
      paramColorStateList[1] = mRippleDrawable;
    }
    mContentBackground = new LayerDrawable(paramColorStateList);
    mShadowDrawable = new ShadowDrawableWrapper(mView.getContext(), mContentBackground, mShadowViewDelegate.getRadius(), mElevation, mElevation + mPressedTranslationZ);
    mShadowDrawable.setAddPaddingForCorners(false);
    mShadowViewDelegate.setBackgroundDrawable(mShadowDrawable);
  }
  
  void setBackgroundTintList(ColorStateList paramColorStateList)
  {
    if (mShapeDrawable != null) {
      DrawableCompat.setTintList(mShapeDrawable, paramColorStateList);
    }
    if (mBorderDrawable != null) {
      mBorderDrawable.setBorderTint(paramColorStateList);
    }
  }
  
  void setBackgroundTintMode(PorterDuff.Mode paramMode)
  {
    if (mShapeDrawable != null) {
      DrawableCompat.setTintMode(mShapeDrawable, paramMode);
    }
  }
  
  final void setElevation(float paramFloat)
  {
    if (mElevation != paramFloat)
    {
      mElevation = paramFloat;
      onElevationsChanged(paramFloat, mPressedTranslationZ);
    }
  }
  
  final void setPressedTranslationZ(float paramFloat)
  {
    if (mPressedTranslationZ != paramFloat)
    {
      mPressedTranslationZ = paramFloat;
      onElevationsChanged(mElevation, paramFloat);
    }
  }
  
  void setRippleColor(int paramInt)
  {
    if (mRippleDrawable != null) {
      DrawableCompat.setTintList(mRippleDrawable, createColorStateList(paramInt));
    }
  }
  
  void show(@Nullable final InternalVisibilityChangedListener paramInternalVisibilityChangedListener, final boolean paramBoolean)
  {
    if (isOrWillBeShown()) {
      return;
    }
    mView.animate().cancel();
    if (shouldAnimateVisibilityChange())
    {
      mAnimState = 2;
      if (mView.getVisibility() != 0)
      {
        mView.setAlpha(0.0F);
        mView.setScaleY(0.0F);
        mView.setScaleX(0.0F);
      }
      mView.animate().scaleX(1.0F).scaleY(1.0F).alpha(1.0F).setDuration(200L).setInterpolator(AnimationUtils.LINEAR_OUT_SLOW_IN_INTERPOLATOR).setListener(new AnimatorListenerAdapter()
      {
        public void onAnimationEnd(Animator paramAnonymousAnimator)
        {
          mAnimState = 0;
          if (paramInternalVisibilityChangedListener != null) {
            paramInternalVisibilityChangedListener.onShown();
          }
        }
        
        public void onAnimationStart(Animator paramAnonymousAnimator)
        {
          mView.internalSetVisibility(0, paramBoolean);
        }
      });
      return;
    }
    mView.internalSetVisibility(0, paramBoolean);
    mView.setAlpha(1.0F);
    mView.setScaleY(1.0F);
    mView.setScaleX(1.0F);
    if (paramInternalVisibilityChangedListener != null) {
      paramInternalVisibilityChangedListener.onShown();
    }
  }
  
  final void updatePadding()
  {
    Rect localRect = mTmpRect;
    getPadding(localRect);
    onPaddingUpdated(localRect);
    mShadowViewDelegate.setShadowPadding(left, top, right, bottom);
  }
  
  private class DisabledElevationAnimation
    extends FloatingActionButtonImpl.ShadowAnimatorImpl
  {
    DisabledElevationAnimation()
    {
      super(null);
    }
    
    protected float getTargetShadowSize()
    {
      return 0.0F;
    }
  }
  
  private class ElevateToTranslationZAnimation
    extends FloatingActionButtonImpl.ShadowAnimatorImpl
  {
    ElevateToTranslationZAnimation()
    {
      super(null);
    }
    
    protected float getTargetShadowSize()
    {
      return mElevation + mPressedTranslationZ;
    }
  }
  
  static abstract interface InternalVisibilityChangedListener
  {
    public abstract void onHidden();
    
    public abstract void onShown();
  }
  
  private class ResetElevationAnimation
    extends FloatingActionButtonImpl.ShadowAnimatorImpl
  {
    ResetElevationAnimation()
    {
      super(null);
    }
    
    protected float getTargetShadowSize()
    {
      return mElevation;
    }
  }
  
  private abstract class ShadowAnimatorImpl
    extends AnimatorListenerAdapter
    implements ValueAnimator.AnimatorUpdateListener
  {
    private float mShadowSizeEnd;
    private float mShadowSizeStart;
    private boolean mValidValues;
    
    private ShadowAnimatorImpl() {}
    
    protected abstract float getTargetShadowSize();
    
    public void onAnimationEnd(Animator paramAnimator)
    {
      mShadowDrawable.setShadowSize(mShadowSizeEnd);
      mValidValues = false;
    }
    
    public void onAnimationUpdate(ValueAnimator paramValueAnimator)
    {
      if (!mValidValues)
      {
        mShadowSizeStart = mShadowDrawable.getShadowSize();
        mShadowSizeEnd = getTargetShadowSize();
        mValidValues = true;
      }
      mShadowDrawable.setShadowSize(mShadowSizeStart + (mShadowSizeEnd - mShadowSizeStart) * paramValueAnimator.getAnimatedFraction());
    }
  }
}
