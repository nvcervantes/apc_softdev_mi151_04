package android.support.transition;

import android.animation.ObjectAnimator;
import android.graphics.Path;
import android.graphics.PointF;
import android.os.Build.VERSION;
import android.util.Property;

class ObjectAnimatorUtils
{
  private static final ObjectAnimatorUtilsImpl IMPL = new ObjectAnimatorUtilsApi14();
  
  static
  {
    if (Build.VERSION.SDK_INT >= 21)
    {
      IMPL = new ObjectAnimatorUtilsApi21();
      return;
    }
  }
  
  ObjectAnimatorUtils() {}
  
  static <T> ObjectAnimator ofPointF(T paramT, Property<T, PointF> paramProperty, Path paramPath)
  {
    return IMPL.ofPointF(paramT, paramProperty, paramPath);
  }
}
