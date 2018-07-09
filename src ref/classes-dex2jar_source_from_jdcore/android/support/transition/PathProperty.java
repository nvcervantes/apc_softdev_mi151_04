package android.support.transition;

import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PointF;
import android.util.Property;

class PathProperty<T>
  extends Property<T, Float>
{
  private float mCurrentFraction;
  private final float mPathLength;
  private final PathMeasure mPathMeasure;
  private final PointF mPointF = new PointF();
  private final float[] mPosition = new float[2];
  private final Property<T, PointF> mProperty;
  
  PathProperty(Property<T, PointF> paramProperty, Path paramPath)
  {
    super(Float.class, paramProperty.getName());
    mProperty = paramProperty;
    mPathMeasure = new PathMeasure(paramPath, false);
    mPathLength = mPathMeasure.getLength();
  }
  
  public Float get(T paramT)
  {
    return Float.valueOf(mCurrentFraction);
  }
  
  public void set(T paramT, Float paramFloat)
  {
    mCurrentFraction = paramFloat.floatValue();
    mPathMeasure.getPosTan(mPathLength * paramFloat.floatValue(), mPosition, null);
    mPointF.x = mPosition[0];
    mPointF.y = mPosition[1];
    mProperty.set(paramT, mPointF);
  }
}
