package android.support.transition;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.TypeEvaluator;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

class TransitionUtils
{
  private static final int MAX_IMAGE_SIZE = 1048576;
  
  TransitionUtils() {}
  
  static View copyViewImage(ViewGroup paramViewGroup, View paramView1, View paramView2)
  {
    Matrix localMatrix = new Matrix();
    localMatrix.setTranslate(-paramView2.getScrollX(), -paramView2.getScrollY());
    ViewUtils.transformMatrixToGlobal(paramView1, localMatrix);
    ViewUtils.transformMatrixToLocal(paramViewGroup, localMatrix);
    paramView2 = new RectF(0.0F, 0.0F, paramView1.getWidth(), paramView1.getHeight());
    localMatrix.mapRect(paramView2);
    int i = Math.round(left);
    int j = Math.round(top);
    int k = Math.round(right);
    int m = Math.round(bottom);
    paramViewGroup = new ImageView(paramView1.getContext());
    paramViewGroup.setScaleType(ImageView.ScaleType.CENTER_CROP);
    paramView1 = createViewBitmap(paramView1, localMatrix, paramView2);
    if (paramView1 != null) {
      paramViewGroup.setImageBitmap(paramView1);
    }
    paramViewGroup.measure(View.MeasureSpec.makeMeasureSpec(k - i, 1073741824), View.MeasureSpec.makeMeasureSpec(m - j, 1073741824));
    paramViewGroup.layout(i, j, k, m);
    return paramViewGroup;
  }
  
  private static Bitmap createViewBitmap(View paramView, Matrix paramMatrix, RectF paramRectF)
  {
    int j = Math.round(paramRectF.width());
    int i = Math.round(paramRectF.height());
    if ((j > 0) && (i > 0))
    {
      float f = Math.min(1.0F, 1048576.0F / (j * i));
      j = (int)(j * f);
      i = (int)(i * f);
      paramMatrix.postTranslate(-left, -top);
      paramMatrix.postScale(f, f);
      paramRectF = Bitmap.createBitmap(j, i, Bitmap.Config.ARGB_8888);
      Canvas localCanvas = new Canvas(paramRectF);
      localCanvas.concat(paramMatrix);
      paramView.draw(localCanvas);
      return paramRectF;
    }
    return null;
  }
  
  static Animator mergeAnimators(Animator paramAnimator1, Animator paramAnimator2)
  {
    if (paramAnimator1 == null) {
      return paramAnimator2;
    }
    if (paramAnimator2 == null) {
      return paramAnimator1;
    }
    AnimatorSet localAnimatorSet = new AnimatorSet();
    localAnimatorSet.playTogether(new Animator[] { paramAnimator1, paramAnimator2 });
    return localAnimatorSet;
  }
  
  static class MatrixEvaluator
    implements TypeEvaluator<Matrix>
  {
    final float[] mTempEndValues = new float[9];
    final Matrix mTempMatrix = new Matrix();
    final float[] mTempStartValues = new float[9];
    
    MatrixEvaluator() {}
    
    public Matrix evaluate(float paramFloat, Matrix paramMatrix1, Matrix paramMatrix2)
    {
      paramMatrix1.getValues(mTempStartValues);
      paramMatrix2.getValues(mTempEndValues);
      int i = 0;
      while (i < 9)
      {
        float f1 = mTempEndValues[i];
        float f2 = mTempStartValues[i];
        mTempEndValues[i] = (mTempStartValues[i] + (f1 - f2) * paramFloat);
        i += 1;
      }
      mTempMatrix.setValues(mTempEndValues);
      return mTempMatrix;
    }
  }
}
