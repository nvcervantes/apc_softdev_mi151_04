package android.support.transition;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.widget.FrameLayout;

@SuppressLint({"ViewConstructor"})
@RequiresApi(14)
class GhostViewApi14
  extends View
  implements GhostViewImpl
{
  Matrix mCurrentMatrix;
  private int mDeltaX;
  private int mDeltaY;
  private final Matrix mMatrix = new Matrix();
  private final ViewTreeObserver.OnPreDrawListener mOnPreDrawListener = new ViewTreeObserver.OnPreDrawListener()
  {
    public boolean onPreDraw()
    {
      mCurrentMatrix = mView.getMatrix();
      ViewCompat.postInvalidateOnAnimation(GhostViewApi14.this);
      if ((mStartParent != null) && (mStartView != null))
      {
        mStartParent.endViewTransition(mStartView);
        ViewCompat.postInvalidateOnAnimation(mStartParent);
        mStartParent = null;
        mStartView = null;
      }
      return true;
    }
  };
  int mReferences;
  ViewGroup mStartParent;
  View mStartView;
  final View mView;
  
  GhostViewApi14(View paramView)
  {
    super(paramView.getContext());
    mView = paramView;
    setLayerType(2, null);
  }
  
  static GhostViewApi14 getGhostView(@NonNull View paramView)
  {
    return (GhostViewApi14)paramView.getTag(R.id.ghost_view);
  }
  
  private static void setGhostView(@NonNull View paramView, GhostViewApi14 paramGhostViewApi14)
  {
    paramView.setTag(R.id.ghost_view, paramGhostViewApi14);
  }
  
  protected void onAttachedToWindow()
  {
    super.onAttachedToWindow();
    setGhostView(mView, this);
    int[] arrayOfInt1 = new int[2];
    int[] arrayOfInt2 = new int[2];
    getLocationOnScreen(arrayOfInt1);
    mView.getLocationOnScreen(arrayOfInt2);
    arrayOfInt2[0] = ((int)(arrayOfInt2[0] - mView.getTranslationX()));
    arrayOfInt2[1] = ((int)(arrayOfInt2[1] - mView.getTranslationY()));
    mDeltaX = (arrayOfInt2[0] - arrayOfInt1[0]);
    mDeltaY = (arrayOfInt2[1] - arrayOfInt1[1]);
    mView.getViewTreeObserver().addOnPreDrawListener(mOnPreDrawListener);
    mView.setVisibility(4);
  }
  
  protected void onDetachedFromWindow()
  {
    mView.getViewTreeObserver().removeOnPreDrawListener(mOnPreDrawListener);
    mView.setVisibility(0);
    setGhostView(mView, null);
    super.onDetachedFromWindow();
  }
  
  protected void onDraw(Canvas paramCanvas)
  {
    mMatrix.set(mCurrentMatrix);
    mMatrix.postTranslate(mDeltaX, mDeltaY);
    paramCanvas.setMatrix(mMatrix);
    mView.draw(paramCanvas);
  }
  
  public void reserveEndViewTransition(ViewGroup paramViewGroup, View paramView)
  {
    mStartParent = paramViewGroup;
    mStartView = paramView;
  }
  
  public void setVisibility(int paramInt)
  {
    super.setVisibility(paramInt);
    View localView = mView;
    if (paramInt == 0) {
      paramInt = 4;
    } else {
      paramInt = 0;
    }
    localView.setVisibility(paramInt);
  }
  
  static class Creator
    implements GhostViewImpl.Creator
  {
    Creator() {}
    
    private static FrameLayout findFrameLayout(ViewGroup paramViewGroup)
    {
      while (!(paramViewGroup instanceof FrameLayout))
      {
        paramViewGroup = paramViewGroup.getParent();
        if (!(paramViewGroup instanceof ViewGroup)) {
          return null;
        }
        paramViewGroup = (ViewGroup)paramViewGroup;
      }
      return (FrameLayout)paramViewGroup;
    }
    
    public GhostViewImpl addGhost(View paramView, ViewGroup paramViewGroup, Matrix paramMatrix)
    {
      GhostViewApi14 localGhostViewApi14 = GhostViewApi14.getGhostView(paramView);
      paramMatrix = localGhostViewApi14;
      if (localGhostViewApi14 == null)
      {
        paramViewGroup = findFrameLayout(paramViewGroup);
        if (paramViewGroup == null) {
          return null;
        }
        paramMatrix = new GhostViewApi14(paramView);
        paramViewGroup.addView(paramMatrix);
      }
      mReferences += 1;
      return paramMatrix;
    }
    
    public void removeGhost(View paramView)
    {
      paramView = GhostViewApi14.getGhostView(paramView);
      if (paramView != null)
      {
        mReferences -= 1;
        if (mReferences <= 0)
        {
          Object localObject = paramView.getParent();
          if ((localObject instanceof ViewGroup))
          {
            localObject = (ViewGroup)localObject;
            ((ViewGroup)localObject).endViewTransition(paramView);
            ((ViewGroup)localObject).removeView(paramView);
          }
        }
      }
    }
  }
}
