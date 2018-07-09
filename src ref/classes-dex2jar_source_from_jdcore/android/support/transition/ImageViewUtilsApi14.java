package android.support.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.graphics.Matrix;
import android.support.annotation.RequiresApi;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

@RequiresApi(14)
class ImageViewUtilsApi14
  implements ImageViewUtilsImpl
{
  ImageViewUtilsApi14() {}
  
  public void animateTransform(ImageView paramImageView, Matrix paramMatrix)
  {
    paramImageView.setImageMatrix(paramMatrix);
  }
  
  public void reserveEndAnimateTransform(final ImageView paramImageView, Animator paramAnimator)
  {
    paramAnimator.addListener(new AnimatorListenerAdapter()
    {
      public void onAnimationEnd(Animator paramAnonymousAnimator)
      {
        ImageView.ScaleType localScaleType = (ImageView.ScaleType)paramImageView.getTag(R.id.save_scale_type);
        paramImageView.setScaleType(localScaleType);
        paramImageView.setTag(R.id.save_scale_type, null);
        if (localScaleType == ImageView.ScaleType.MATRIX)
        {
          paramImageView.setImageMatrix((Matrix)paramImageView.getTag(R.id.save_image_matrix));
          paramImageView.setTag(R.id.save_image_matrix, null);
        }
        paramAnonymousAnimator.removeListener(this);
      }
    });
  }
  
  public void startAnimateTransform(ImageView paramImageView)
  {
    ImageView.ScaleType localScaleType = paramImageView.getScaleType();
    paramImageView.setTag(R.id.save_scale_type, localScaleType);
    if (localScaleType == ImageView.ScaleType.MATRIX) {
      paramImageView.setTag(R.id.save_image_matrix, paramImageView.getImageMatrix());
    } else {
      paramImageView.setScaleType(ImageView.ScaleType.MATRIX);
    }
    paramImageView.setImageMatrix(MatrixUtils.IDENTITY_MATRIX);
  }
}
