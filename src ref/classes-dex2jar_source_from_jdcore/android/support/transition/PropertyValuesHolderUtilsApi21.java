package android.support.transition;

import android.animation.PropertyValuesHolder;
import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.RequiresApi;
import android.util.Property;

@RequiresApi(21)
class PropertyValuesHolderUtilsApi21
  implements PropertyValuesHolderUtilsImpl
{
  PropertyValuesHolderUtilsApi21() {}
  
  public PropertyValuesHolder ofPointF(Property<?, PointF> paramProperty, Path paramPath)
  {
    return PropertyValuesHolder.ofObject(paramProperty, null, paramPath);
  }
}
