package android.support.transition;

import android.animation.ObjectAnimator;
import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.RequiresApi;
import android.util.Property;

@RequiresApi(14)
class ObjectAnimatorUtilsApi14
  implements ObjectAnimatorUtilsImpl
{
  ObjectAnimatorUtilsApi14() {}
  
  public <T> ObjectAnimator ofPointF(T paramT, Property<T, PointF> paramProperty, Path paramPath)
  {
    return ObjectAnimator.ofFloat(paramT, new PathProperty(paramProperty, paramPath), new float[] { 0.0F, 1.0F });
  }
}
