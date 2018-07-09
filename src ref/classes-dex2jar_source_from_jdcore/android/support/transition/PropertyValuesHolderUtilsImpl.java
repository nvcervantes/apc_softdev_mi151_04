package android.support.transition;

import android.animation.PropertyValuesHolder;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.Property;

abstract interface PropertyValuesHolderUtilsImpl
{
  public abstract PropertyValuesHolder ofPointF(Property<?, PointF> paramProperty, Path paramPath);
}
