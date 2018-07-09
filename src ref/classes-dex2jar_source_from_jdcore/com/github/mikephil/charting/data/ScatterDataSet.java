package com.github.mikephil.charting.data;

import com.github.mikephil.charting.charts.ScatterChart.ScatterShape;
import com.github.mikephil.charting.interfaces.datasets.IScatterDataSet;
import com.github.mikephil.charting.renderer.scatter.ChevronDownShapeRenderer;
import com.github.mikephil.charting.renderer.scatter.ChevronUpShapeRenderer;
import com.github.mikephil.charting.renderer.scatter.CircleShapeRenderer;
import com.github.mikephil.charting.renderer.scatter.CrossShapeRenderer;
import com.github.mikephil.charting.renderer.scatter.IShapeRenderer;
import com.github.mikephil.charting.renderer.scatter.SquareShapeRenderer;
import com.github.mikephil.charting.renderer.scatter.TriangleShapeRenderer;
import com.github.mikephil.charting.renderer.scatter.XShapeRenderer;
import java.util.ArrayList;
import java.util.List;

public class ScatterDataSet
  extends LineScatterCandleRadarDataSet<Entry>
  implements IScatterDataSet
{
  private int mScatterShapeHoleColor = 1122867;
  private float mScatterShapeHoleRadius = 0.0F;
  protected IShapeRenderer mShapeRenderer = new SquareShapeRenderer();
  private float mShapeSize = 15.0F;
  
  public ScatterDataSet(List<Entry> paramList, String paramString)
  {
    super(paramList, paramString);
  }
  
  public static IShapeRenderer getRendererForShape(ScatterChart.ScatterShape paramScatterShape)
  {
    switch (1.$SwitchMap$com$github$mikephil$charting$charts$ScatterChart$ScatterShape[paramScatterShape.ordinal()])
    {
    default: 
      return null;
    case 7: 
      return new ChevronDownShapeRenderer();
    case 6: 
      return new ChevronUpShapeRenderer();
    case 5: 
      return new XShapeRenderer();
    case 4: 
      return new CrossShapeRenderer();
    case 3: 
      return new TriangleShapeRenderer();
    case 2: 
      return new CircleShapeRenderer();
    }
    return new SquareShapeRenderer();
  }
  
  public DataSet<Entry> copy()
  {
    Object localObject = new ArrayList();
    int i = 0;
    while (i < mValues.size())
    {
      ((List)localObject).add(((Entry)mValues.get(i)).copy());
      i += 1;
    }
    localObject = new ScatterDataSet((List)localObject, getLabel());
    mDrawValues = mDrawValues;
    mValueColors = mValueColors;
    mColors = mColors;
    mShapeSize = mShapeSize;
    mShapeRenderer = mShapeRenderer;
    mScatterShapeHoleRadius = mScatterShapeHoleRadius;
    mScatterShapeHoleColor = mScatterShapeHoleColor;
    mHighlightLineWidth = mHighlightLineWidth;
    mHighLightColor = mHighLightColor;
    mHighlightDashPathEffect = mHighlightDashPathEffect;
    return localObject;
  }
  
  public int getScatterShapeHoleColor()
  {
    return mScatterShapeHoleColor;
  }
  
  public float getScatterShapeHoleRadius()
  {
    return mScatterShapeHoleRadius;
  }
  
  public float getScatterShapeSize()
  {
    return mShapeSize;
  }
  
  public IShapeRenderer getShapeRenderer()
  {
    return mShapeRenderer;
  }
  
  public void setScatterShape(ScatterChart.ScatterShape paramScatterShape)
  {
    mShapeRenderer = getRendererForShape(paramScatterShape);
  }
  
  public void setScatterShapeHoleColor(int paramInt)
  {
    mScatterShapeHoleColor = paramInt;
  }
  
  public void setScatterShapeHoleRadius(float paramFloat)
  {
    mScatterShapeHoleRadius = paramFloat;
  }
  
  public void setScatterShapeSize(float paramFloat)
  {
    mShapeSize = paramFloat;
  }
  
  public void setShapeRenderer(IShapeRenderer paramIShapeRenderer)
  {
    mShapeRenderer = paramIShapeRenderer;
  }
}
