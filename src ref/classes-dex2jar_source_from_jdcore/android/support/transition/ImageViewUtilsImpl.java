package android.support.transition;

import android.animation.Animator;
import android.graphics.Matrix;
import android.support.annotation.RequiresApi;
import android.widget.ImageView;

@RequiresApi(14)
abstract interface ImageViewUtilsImpl
{
  public abstract void animateTransform(ImageView paramImageView, Matrix paramMatrix);
  
  public abstract void reserveEndAnimateTransform(ImageView paramImageView, Animator paramAnimator);
  
  public abstract void startAnimateTransform(ImageView paramImageView);
}
