package android.support.transition;

import android.animation.ObjectAnimator;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.Property;

abstract interface ObjectAnimatorUtilsImpl
{
  public abstract <T> ObjectAnimator ofPointF(T paramT, Property<T, PointF> paramProperty, Path paramPath);
}
