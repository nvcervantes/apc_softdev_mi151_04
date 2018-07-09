package com.github.mikephil.charting.data;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Typeface;
import com.github.mikephil.charting.components.Legend.LegendForm;
import com.github.mikephil.charting.components.YAxis.AxisDependency;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Utils;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseDataSet<T extends Entry>
  implements IDataSet<T>
{
  protected YAxis.AxisDependency mAxisDependency = YAxis.AxisDependency.LEFT;
  protected List<Integer> mColors = null;
  protected boolean mDrawIcons = true;
  protected boolean mDrawValues = true;
  private Legend.LegendForm mForm = Legend.LegendForm.DEFAULT;
  private DashPathEffect mFormLineDashEffect = null;
  private float mFormLineWidth = NaN.0F;
  private float mFormSize = NaN.0F;
  protected boolean mHighlightEnabled = true;
  protected MPPointF mIconsOffset = new MPPointF();
  private String mLabel = "DataSet";
  protected List<Integer> mValueColors = null;
  protected transient IValueFormatter mValueFormatter;
  protected float mValueTextSize = 17.0F;
  protected Typeface mValueTypeface;
  protected boolean mVisible = true;
  
  public BaseDataSet()
  {
    mColors.add(Integer.valueOf(Color.rgb(140, 234, 255)));
    mValueColors.add(Integer.valueOf(-16777216));
  }
  
  public BaseDataSet(String paramString)
  {
    this();
    mLabel = paramString;
  }
  
  public void addColor(int paramInt)
  {
    if (mColors == null) {
      mColors = new ArrayList();
    }
    mColors.add(Integer.valueOf(paramInt));
  }
  
  public boolean contains(T paramT)
  {
    int i = 0;
    while (i < getEntryCount())
    {
      if (getEntryForIndex(i).equals(paramT)) {
        return true;
      }
      i += 1;
    }
    return false;
  }
  
  public YAxis.AxisDependency getAxisDependency()
  {
    return mAxisDependency;
  }
  
  public int getColor()
  {
    return ((Integer)mColors.get(0)).intValue();
  }
  
  public int getColor(int paramInt)
  {
    return ((Integer)mColors.get(paramInt % mColors.size())).intValue();
  }
  
  public List<Integer> getColors()
  {
    return mColors;
  }
  
  public Legend.LegendForm getForm()
  {
    return mForm;
  }
  
  public DashPathEffect getFormLineDashEffect()
  {
    return mFormLineDashEffect;
  }
  
  public float getFormLineWidth()
  {
    return mFormLineWidth;
  }
  
  public float getFormSize()
  {
    return mFormSize;
  }
  
  public MPPointF getIconsOffset()
  {
    return mIconsOffset;
  }
  
  public int getIndexInEntries(int paramInt)
  {
    int i = 0;
    while (i < getEntryCount())
    {
      if (paramInt == getEntryForIndex(i).getX()) {
        return i;
      }
      i += 1;
    }
    return -1;
  }
  
  public String getLabel()
  {
    return mLabel;
  }
  
  public List<Integer> getValueColors()
  {
    return mValueColors;
  }
  
  public IValueFormatter getValueFormatter()
  {
    if (needsFormatter()) {
      return Utils.getDefaultValueFormatter();
    }
    return mValueFormatter;
  }
  
  public int getValueTextColor()
  {
    return ((Integer)mValueColors.get(0)).intValue();
  }
  
  public int getValueTextColor(int paramInt)
  {
    return ((Integer)mValueColors.get(paramInt % mValueColors.size())).intValue();
  }
  
  public float getValueTextSize()
  {
    return mValueTextSize;
  }
  
  public Typeface getValueTypeface()
  {
    return mValueTypeface;
  }
  
  public boolean isDrawIconsEnabled()
  {
    return mDrawIcons;
  }
  
  public boolean isDrawValuesEnabled()
  {
    return mDrawValues;
  }
  
  public boolean isHighlightEnabled()
  {
    return mHighlightEnabled;
  }
  
  public boolean isVisible()
  {
    return mVisible;
  }
  
  public boolean needsFormatter()
  {
    return mValueFormatter == null;
  }
  
  public void notifyDataSetChanged()
  {
    calcMinMax();
  }
  
  public boolean removeEntry(int paramInt)
  {
    return removeEntry(getEntryForIndex(paramInt));
  }
  
  public boolean removeEntryByXValue(float paramFloat)
  {
    return removeEntry(getEntryForXValue(paramFloat, NaN.0F));
  }
  
  public boolean removeFirst()
  {
    if (getEntryCount() > 0) {
      return removeEntry(getEntryForIndex(0));
    }
    return false;
  }
  
  public boolean removeLast()
  {
    if (getEntryCount() > 0) {
      return removeEntry(getEntryForIndex(getEntryCount() - 1));
    }
    return false;
  }
  
  public void resetColors()
  {
    if (mColors == null) {
      mColors = new ArrayList();
    }
    mColors.clear();
  }
  
  public void setAxisDependency(YAxis.AxisDependency paramAxisDependency)
  {
    mAxisDependency = paramAxisDependency;
  }
  
  public void setColor(int paramInt)
  {
    resetColors();
    mColors.add(Integer.valueOf(paramInt));
  }
  
  public void setColor(int paramInt1, int paramInt2)
  {
    setColor(Color.argb(paramInt2, Color.red(paramInt1), Color.green(paramInt1), Color.blue(paramInt1)));
  }
  
  public void setColors(List<Integer> paramList)
  {
    mColors = paramList;
  }
  
  public void setColors(int... paramVarArgs)
  {
    mColors = ColorTemplate.createColors(paramVarArgs);
  }
  
  public void setColors(int[] paramArrayOfInt, int paramInt)
  {
    resetColors();
    int i = 0;
    int j = paramArrayOfInt.length;
    while (i < j)
    {
      int k = paramArrayOfInt[i];
      addColor(Color.argb(paramInt, Color.red(k), Color.green(k), Color.blue(k)));
      i += 1;
    }
  }
  
  public void setColors(int[] paramArrayOfInt, Context paramContext)
  {
    if (mColors == null) {
      mColors = new ArrayList();
    }
    mColors.clear();
    int j = paramArrayOfInt.length;
    int i = 0;
    while (i < j)
    {
      int k = paramArrayOfInt[i];
      mColors.add(Integer.valueOf(paramContext.getResources().getColor(k)));
      i += 1;
    }
  }
  
  public void setDrawIcons(boolean paramBoolean)
  {
    mDrawIcons = paramBoolean;
  }
  
  public void setDrawValues(boolean paramBoolean)
  {
    mDrawValues = paramBoolean;
  }
  
  public void setForm(Legend.LegendForm paramLegendForm)
  {
    mForm = paramLegendForm;
  }
  
  public void setFormLineDashEffect(DashPathEffect paramDashPathEffect)
  {
    mFormLineDashEffect = paramDashPathEffect;
  }
  
  public void setFormLineWidth(float paramFloat)
  {
    mFormLineWidth = paramFloat;
  }
  
  public void setFormSize(float paramFloat)
  {
    mFormSize = paramFloat;
  }
  
  public void setHighlightEnabled(boolean paramBoolean)
  {
    mHighlightEnabled = paramBoolean;
  }
  
  public void setIconsOffset(MPPointF paramMPPointF)
  {
    mIconsOffset.x = x;
    mIconsOffset.y = y;
  }
  
  public void setLabel(String paramString)
  {
    mLabel = paramString;
  }
  
  public void setValueFormatter(IValueFormatter paramIValueFormatter)
  {
    if (paramIValueFormatter == null) {
      return;
    }
    mValueFormatter = paramIValueFormatter;
  }
  
  public void setValueTextColor(int paramInt)
  {
    mValueColors.clear();
    mValueColors.add(Integer.valueOf(paramInt));
  }
  
  public void setValueTextColors(List<Integer> paramList)
  {
    mValueColors = paramList;
  }
  
  public void setValueTextSize(float paramFloat)
  {
    mValueTextSize = Utils.convertDpToPixel(paramFloat);
  }
  
  public void setValueTypeface(Typeface paramTypeface)
  {
    mValueTypeface = paramTypeface;
  }
  
  public void setVisible(boolean paramBoolean)
  {
    mVisible = paramBoolean;
  }
}
