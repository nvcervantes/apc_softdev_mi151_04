package android.support.transition;

import android.animation.PropertyValuesHolder;
import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.RequiresApi;
import android.util.Property;

@RequiresApi(14)
class PropertyValuesHolderUtilsApi14
  implements PropertyValuesHolderUtilsImpl
{
  PropertyValuesHolderUtilsApi14() {}
  
  public PropertyValuesHolder ofPointF(Property<?, PointF> paramProperty, Path paramPath)
  {
    return PropertyValuesHolder.ofFloat(new PathProperty(paramProperty, paramPath), new float[] { 0.0F, 1.0F });
  }
}
