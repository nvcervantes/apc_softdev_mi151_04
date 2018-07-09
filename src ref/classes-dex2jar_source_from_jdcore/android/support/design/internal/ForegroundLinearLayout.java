package android.support.design.internal;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.design.R.styleable;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.view.Gravity;

@RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
public class ForegroundLinearLayout
  extends LinearLayoutCompat
{
  private Drawable mForeground;
  boolean mForegroundBoundsChanged = false;
  private int mForegroundGravity = 119;
  protected boolean mForegroundInPadding = true;
  private final Rect mOverlayBounds = new Rect();
  private final Rect mSelfBounds = new Rect();
  
  public ForegroundLinearLayout(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public ForegroundLinearLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }
  
  public ForegroundLinearLayout(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.ForegroundLinearLayout, paramInt, 0);
    mForegroundGravity = paramContext.getInt(R.styleable.ForegroundLinearLayout_android_foregroundGravity, mForegroundGravity);
    paramAttributeSet = paramContext.getDrawable(R.styleable.ForegroundLinearLayout_android_foreground);
    if (paramAttributeSet != null) {
      setForeground(paramAttributeSet);
    }
    mForegroundInPadding = paramContext.getBoolean(R.styleable.ForegroundLinearLayout_foregroundInsidePadding, true);
    paramContext.recycle();
  }
  
  public void draw(@NonNull Canvas paramCanvas)
  {
    super.draw(paramCanvas);
    if (mForeground != null)
    {
      Drawable localDrawable = mForeground;
      if (mForegroundBoundsChanged)
      {
        mForegroundBoundsChanged = false;
        Rect localRect1 = mSelfBounds;
        Rect localRect2 = mOverlayBounds;
        int i = getRight() - getLeft();
        int j = getBottom() - getTop();
        if (mForegroundInPadding) {
          localRect1.set(0, 0, i, j);
        } else {
          localRect1.set(getPaddingLeft(), getPaddingTop(), i - getPaddingRight(), j - getPaddingBottom());
        }
        Gravity.apply(mForegroundGravity, localDrawable.getIntrinsicWidth(), localDrawable.getIntrinsicHeight(), localRect1, localRect2);
        localDrawable.setBounds(localRect2);
      }
      localDrawable.draw(paramCanvas);
    }
  }
  
  @RequiresApi(21)
  public void drawableHotspotChanged(float paramFloat1, float paramFloat2)
  {
    super.drawableHotspotChanged(paramFloat1, paramFloat2);
    if (mForeground != null) {
      mForeground.setHotspot(paramFloat1, paramFloat2);
    }
  }
  
  protected void drawableStateChanged()
  {
    super.drawableStateChanged();
    if ((mForeground != null) && (mForeground.isStateful())) {
      mForeground.setState(getDrawableState());
    }
  }
  
  public Drawable getForeground()
  {
    return mForeground;
  }
  
  public int getForegroundGravity()
  {
    return mForegroundGravity;
  }
  
  @RequiresApi(11)
  public void jumpDrawablesToCurrentState()
  {
    super.jumpDrawablesToCurrentState();
    if (mForeground != null) {
      mForeground.jumpToCurrentState();
    }
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    mForegroundBoundsChanged = (paramBoolean | mForegroundBoundsChanged);
  }
  
  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    mForegroundBoundsChanged = true;
  }
  
  public void setForeground(Drawable paramDrawable)
  {
    if (mForeground != paramDrawable)
    {
      if (mForeground != null)
      {
        mForeground.setCallback(null);
        unscheduleDrawable(mForeground);
      }
      mForeground = paramDrawable;
      if (paramDrawable != null)
      {
        setWillNotDraw(false);
        paramDrawable.setCallback(this);
        if (paramDrawable.isStateful()) {
          paramDrawable.setState(getDrawableState());
        }
        if (mForegroundGravity == 119) {
          paramDrawable.getPadding(new Rect());
        }
      }
      else
      {
        setWillNotDraw(true);
      }
      requestLayout();
      invalidate();
    }
  }
  
  public void setForegroundGravity(int paramInt)
  {
    if (mForegroundGravity != paramInt)
    {
      int i = paramInt;
      if ((0x800007 & paramInt) == 0) {
        i = paramInt | 0x800003;
      }
      paramInt = i;
      if ((i & 0x70) == 0) {
        paramInt = i | 0x30;
      }
      mForegroundGravity = paramInt;
      if ((mForegroundGravity == 119) && (mForeground != null))
      {
        Rect localRect = new Rect();
        mForeground.getPadding(localRect);
      }
      requestLayout();
    }
  }
  
  protected boolean verifyDrawable(Drawable paramDrawable)
  {
    return (super.verifyDrawable(paramDrawable)) || (paramDrawable == mForeground);
  }
}
