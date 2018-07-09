package android.support.transition;

import android.animation.PropertyValuesHolder;
import android.graphics.Path;
import android.graphics.PointF;
import android.os.Build.VERSION;
import android.util.Property;

class PropertyValuesHolderUtils
{
  private static final PropertyValuesHolderUtilsImpl IMPL = new PropertyValuesHolderUtilsApi14();
  
  static
  {
    if (Build.VERSION.SDK_INT >= 21)
    {
      IMPL = new PropertyValuesHolderUtilsApi21();
      return;
    }
  }
  
  PropertyValuesHolderUtils() {}
  
  static PropertyValuesHolder ofPointF(Property<?, PointF> paramProperty, Path paramPath)
  {
    return IMPL.ofPointF(paramProperty, paramPath);
  }
}
