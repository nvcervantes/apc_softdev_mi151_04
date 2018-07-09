package android.support.transition;

import android.animation.Animator;
import android.graphics.Matrix;
import android.os.Build.VERSION;
import android.widget.ImageView;

class ImageViewUtils
{
  private static final ImageViewUtilsImpl IMPL = new ImageViewUtilsApi14();
  
  static
  {
    if (Build.VERSION.SDK_INT >= 21)
    {
      IMPL = new ImageViewUtilsApi21();
      return;
    }
  }
  
  ImageViewUtils() {}
  
  static void animateTransform(ImageView paramImageView, Matrix paramMatrix)
  {
    IMPL.animateTransform(paramImageView, paramMatrix);
  }
  
  static void reserveEndAnimateTransform(ImageView paramImageView, Animator paramAnimator)
  {
    IMPL.reserveEndAnimateTransform(paramImageView, paramAnimator);
  }
  
  static void startAnimateTransform(ImageView paramImageView)
  {
    IMPL.startAnimateTransform(paramImageView);
  }
}
