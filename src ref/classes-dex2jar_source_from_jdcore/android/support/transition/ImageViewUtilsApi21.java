package android.support.transition;

import android.animation.Animator;
import android.graphics.Matrix;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.widget.ImageView;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@RequiresApi(21)
class ImageViewUtilsApi21
  implements ImageViewUtilsImpl
{
  private static final String TAG = "ImageViewUtilsApi21";
  private static Method sAnimateTransformMethod;
  private static boolean sAnimateTransformMethodFetched;
  
  ImageViewUtilsApi21() {}
  
  private void fetchAnimateTransformMethod()
  {
    if (!sAnimateTransformMethodFetched)
    {
      try
      {
        sAnimateTransformMethod = ImageView.class.getDeclaredMethod("animateTransform", new Class[] { Matrix.class });
        sAnimateTransformMethod.setAccessible(true);
      }
      catch (NoSuchMethodException localNoSuchMethodException)
      {
        Log.i("ImageViewUtilsApi21", "Failed to retrieve animateTransform method", localNoSuchMethodException);
      }
      sAnimateTransformMethodFetched = true;
    }
  }
  
  public void animateTransform(ImageView paramImageView, Matrix paramMatrix)
  {
    fetchAnimateTransformMethod();
    if (sAnimateTransformMethod != null) {}
    try
    {
      sAnimateTransformMethod.invoke(paramImageView, new Object[] { paramMatrix });
      return;
    }
    catch (InvocationTargetException paramImageView)
    {
      throw new RuntimeException(paramImageView.getCause());
      return;
    }
    catch (IllegalAccessException paramImageView) {}
  }
  
  public void reserveEndAnimateTransform(ImageView paramImageView, Animator paramAnimator) {}
  
  public void startAnimateTransform(ImageView paramImageView) {}
}
