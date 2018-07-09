package com.github.mikephil.charting.data;

import com.github.mikephil.charting.interfaces.datasets.IPieDataSet;
import com.github.mikephil.charting.utils.Utils;
import java.util.ArrayList;
import java.util.List;

public class PieDataSet
  extends DataSet<PieEntry>
  implements IPieDataSet
{
  private boolean mAutomaticallyDisableSliceSpacing;
  private float mShift = 18.0F;
  private float mSliceSpace = 0.0F;
  private int mValueLineColor = -16777216;
  private float mValueLinePart1Length = 0.3F;
  private float mValueLinePart1OffsetPercentage = 75.0F;
  private float mValueLinePart2Length = 0.4F;
  private boolean mValueLineVariableLength = true;
  private float mValueLineWidth = 1.0F;
  private ValuePosition mXValuePosition = ValuePosition.INSIDE_SLICE;
  private ValuePosition mYValuePosition = ValuePosition.INSIDE_SLICE;
  
  public PieDataSet(List<PieEntry> paramList, String paramString)
  {
    super(paramList, paramString);
  }
  
  protected void calcMinMax(PieEntry paramPieEntry)
  {
    if (paramPieEntry == null) {
      return;
    }
    calcMinMaxY(paramPieEntry);
  }
  
  public DataSet<PieEntry> copy()
  {
    Object localObject = new ArrayList();
    int i = 0;
    while (i < mValues.size())
    {
      ((List)localObject).add(((PieEntry)mValues.get(i)).copy());
      i += 1;
    }
    localObject = new PieDataSet((List)localObject, getLabel());
    mColors = mColors;
    mSliceSpace = mSliceSpace;
    mShift = mShift;
    return localObject;
  }
  
  public float getSelectionShift()
  {
    return mShift;
  }
  
  public float getSliceSpace()
  {
    return mSliceSpace;
  }
  
  public int getValueLineColor()
  {
    return mValueLineColor;
  }
  
  public float getValueLinePart1Length()
  {
    return mValueLinePart1Length;
  }
  
  public float getValueLinePart1OffsetPercentage()
  {
    return mValueLinePart1OffsetPercentage;
  }
  
  public float getValueLinePart2Length()
  {
    return mValueLinePart2Length;
  }
  
  public float getValueLineWidth()
  {
    return mValueLineWidth;
  }
  
  public ValuePosition getXValuePosition()
  {
    return mXValuePosition;
  }
  
  public ValuePosition getYValuePosition()
  {
    return mYValuePosition;
  }
  
  public boolean isAutomaticallyDisableSliceSpacingEnabled()
  {
    return mAutomaticallyDisableSliceSpacing;
  }
  
  public boolean isValueLineVariableLength()
  {
    return mValueLineVariableLength;
  }
  
  public void setAutomaticallyDisableSliceSpacing(boolean paramBoolean)
  {
    mAutomaticallyDisableSliceSpacing = paramBoolean;
  }
  
  public void setSelectionShift(float paramFloat)
  {
    mShift = Utils.convertDpToPixel(paramFloat);
  }
  
  public void setSliceSpace(float paramFloat)
  {
    float f = paramFloat;
    if (paramFloat > 20.0F) {
      f = 20.0F;
    }
    paramFloat = f;
    if (f < 0.0F) {
      paramFloat = 0.0F;
    }
    mSliceSpace = Utils.convertDpToPixel(paramFloat);
  }
  
  public void setValueLineColor(int paramInt)
  {
    mValueLineColor = paramInt;
  }
  
  public void setValueLinePart1Length(float paramFloat)
  {
    mValueLinePart1Length = paramFloat;
  }
  
  public void setValueLinePart1OffsetPercentage(float paramFloat)
  {
    mValueLinePart1OffsetPercentage = paramFloat;
  }
  
  public void setValueLinePart2Length(float paramFloat)
  {
    mValueLinePart2Length = paramFloat;
  }
  
  public void setValueLineVariableLength(boolean paramBoolean)
  {
    mValueLineVariableLength = paramBoolean;
  }
  
  public void setValueLineWidth(float paramFloat)
  {
    mValueLineWidth = paramFloat;
  }
  
  public void setXValuePosition(ValuePosition paramValuePosition)
  {
    mXValuePosition = paramValuePosition;
  }
  
  public void setYValuePosition(ValuePosition paramValuePosition)
  {
    mYValuePosition = paramValuePosition;
  }
  
  public static enum ValuePosition
  {
    INSIDE_SLICE,  OUTSIDE_SLICE;
    
    private ValuePosition() {}
  }
}
