package com.github.mikephil.charting.data;

import android.graphics.Typeface;
import android.util.Log;
import com.github.mikephil.charting.components.YAxis.AxisDependency;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class ChartData<T extends IDataSet<? extends Entry>>
{
  protected List<T> mDataSets;
  protected float mLeftAxisMax = -3.4028235E38F;
  protected float mLeftAxisMin = Float.MAX_VALUE;
  protected float mRightAxisMax = -3.4028235E38F;
  protected float mRightAxisMin = Float.MAX_VALUE;
  protected float mXMax = -3.4028235E38F;
  protected float mXMin = Float.MAX_VALUE;
  protected float mYMax = -3.4028235E38F;
  protected float mYMin = Float.MAX_VALUE;
  
  public ChartData()
  {
    mDataSets = new ArrayList();
  }
  
  public ChartData(List<T> paramList)
  {
    mDataSets = paramList;
    notifyDataChanged();
  }
  
  public ChartData(T... paramVarArgs)
  {
    mDataSets = arrayToList(paramVarArgs);
    notifyDataChanged();
  }
  
  private List<T> arrayToList(T[] paramArrayOfT)
  {
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    int j = paramArrayOfT.length;
    while (i < j)
    {
      localArrayList.add(paramArrayOfT[i]);
      i += 1;
    }
    return localArrayList;
  }
  
  public void addDataSet(T paramT)
  {
    if (paramT == null) {
      return;
    }
    calcMinMax(paramT);
    mDataSets.add(paramT);
  }
  
  public void addEntry(Entry paramEntry, int paramInt)
  {
    if ((mDataSets.size() > paramInt) && (paramInt >= 0))
    {
      IDataSet localIDataSet = (IDataSet)mDataSets.get(paramInt);
      if (!localIDataSet.addEntry(paramEntry)) {
        return;
      }
      calcMinMax(paramEntry, localIDataSet.getAxisDependency());
      return;
    }
    Log.e("addEntry", "Cannot add Entry because dataSetIndex too high or too low.");
  }
  
  protected void calcMinMax()
  {
    if (mDataSets == null) {
      return;
    }
    mYMax = -3.4028235E38F;
    mYMin = Float.MAX_VALUE;
    mXMax = -3.4028235E38F;
    mXMin = Float.MAX_VALUE;
    Object localObject = mDataSets.iterator();
    while (((Iterator)localObject).hasNext()) {
      calcMinMax((IDataSet)((Iterator)localObject).next());
    }
    mLeftAxisMax = -3.4028235E38F;
    mLeftAxisMin = Float.MAX_VALUE;
    mRightAxisMax = -3.4028235E38F;
    mRightAxisMin = Float.MAX_VALUE;
    localObject = getFirstLeft(mDataSets);
    IDataSet localIDataSet;
    if (localObject != null)
    {
      mLeftAxisMax = ((IDataSet)localObject).getYMax();
      mLeftAxisMin = ((IDataSet)localObject).getYMin();
      localObject = mDataSets.iterator();
      while (((Iterator)localObject).hasNext())
      {
        localIDataSet = (IDataSet)((Iterator)localObject).next();
        if (localIDataSet.getAxisDependency() == YAxis.AxisDependency.LEFT)
        {
          if (localIDataSet.getYMin() < mLeftAxisMin) {
            mLeftAxisMin = localIDataSet.getYMin();
          }
          if (localIDataSet.getYMax() > mLeftAxisMax) {
            mLeftAxisMax = localIDataSet.getYMax();
          }
        }
      }
    }
    localObject = getFirstRight(mDataSets);
    if (localObject != null)
    {
      mRightAxisMax = ((IDataSet)localObject).getYMax();
      mRightAxisMin = ((IDataSet)localObject).getYMin();
      localObject = mDataSets.iterator();
      while (((Iterator)localObject).hasNext())
      {
        localIDataSet = (IDataSet)((Iterator)localObject).next();
        if (localIDataSet.getAxisDependency() == YAxis.AxisDependency.RIGHT)
        {
          if (localIDataSet.getYMin() < mRightAxisMin) {
            mRightAxisMin = localIDataSet.getYMin();
          }
          if (localIDataSet.getYMax() > mRightAxisMax) {
            mRightAxisMax = localIDataSet.getYMax();
          }
        }
      }
    }
  }
  
  protected void calcMinMax(Entry paramEntry, YAxis.AxisDependency paramAxisDependency)
  {
    if (mYMax < paramEntry.getY()) {
      mYMax = paramEntry.getY();
    }
    if (mYMin > paramEntry.getY()) {
      mYMin = paramEntry.getY();
    }
    if (mXMax < paramEntry.getX()) {
      mXMax = paramEntry.getX();
    }
    if (mXMin > paramEntry.getX()) {
      mXMin = paramEntry.getX();
    }
    if (paramAxisDependency == YAxis.AxisDependency.LEFT)
    {
      if (mLeftAxisMax < paramEntry.getY()) {
        mLeftAxisMax = paramEntry.getY();
      }
      if (mLeftAxisMin > paramEntry.getY()) {
        mLeftAxisMin = paramEntry.getY();
      }
    }
    else
    {
      if (mRightAxisMax < paramEntry.getY()) {
        mRightAxisMax = paramEntry.getY();
      }
      if (mRightAxisMin > paramEntry.getY()) {
        mRightAxisMin = paramEntry.getY();
      }
    }
  }
  
  protected void calcMinMax(T paramT)
  {
    if (mYMax < paramT.getYMax()) {
      mYMax = paramT.getYMax();
    }
    if (mYMin > paramT.getYMin()) {
      mYMin = paramT.getYMin();
    }
    if (mXMax < paramT.getXMax()) {
      mXMax = paramT.getXMax();
    }
    if (mXMin > paramT.getXMin()) {
      mXMin = paramT.getXMin();
    }
    if (paramT.getAxisDependency() == YAxis.AxisDependency.LEFT)
    {
      if (mLeftAxisMax < paramT.getYMax()) {
        mLeftAxisMax = paramT.getYMax();
      }
      if (mLeftAxisMin > paramT.getYMin()) {
        mLeftAxisMin = paramT.getYMin();
      }
    }
    else
    {
      if (mRightAxisMax < paramT.getYMax()) {
        mRightAxisMax = paramT.getYMax();
      }
      if (mRightAxisMin > paramT.getYMin()) {
        mRightAxisMin = paramT.getYMin();
      }
    }
  }
  
  public void calcMinMaxY(float paramFloat1, float paramFloat2)
  {
    Iterator localIterator = mDataSets.iterator();
    while (localIterator.hasNext()) {
      ((IDataSet)localIterator.next()).calcMinMaxY(paramFloat1, paramFloat2);
    }
    calcMinMax();
  }
  
  public void clearValues()
  {
    if (mDataSets != null) {
      mDataSets.clear();
    }
    notifyDataChanged();
  }
  
  public boolean contains(T paramT)
  {
    Iterator localIterator = mDataSets.iterator();
    while (localIterator.hasNext()) {
      if (((IDataSet)localIterator.next()).equals(paramT)) {
        return true;
      }
    }
    return false;
  }
  
  public int[] getColors()
  {
    if (mDataSets == null) {
      return null;
    }
    int k = 0;
    int i = 0;
    int j = i;
    while (i < mDataSets.size())
    {
      j += ((IDataSet)mDataSets.get(i)).getColors().size();
      i += 1;
    }
    int[] arrayOfInt = new int[j];
    j = 0;
    i = k;
    while (i < mDataSets.size())
    {
      Iterator localIterator = ((IDataSet)mDataSets.get(i)).getColors().iterator();
      while (localIterator.hasNext())
      {
        arrayOfInt[j] = ((Integer)localIterator.next()).intValue();
        j += 1;
      }
      i += 1;
    }
    return arrayOfInt;
  }
  
  public T getDataSetByIndex(int paramInt)
  {
    if ((mDataSets != null) && (paramInt >= 0) && (paramInt < mDataSets.size())) {
      return (IDataSet)mDataSets.get(paramInt);
    }
    return null;
  }
  
  public T getDataSetByLabel(String paramString, boolean paramBoolean)
  {
    int i = getDataSetIndexByLabel(mDataSets, paramString, paramBoolean);
    if ((i >= 0) && (i < mDataSets.size())) {
      return (IDataSet)mDataSets.get(i);
    }
    return null;
  }
  
  public int getDataSetCount()
  {
    if (mDataSets == null) {
      return 0;
    }
    return mDataSets.size();
  }
  
  public T getDataSetForEntry(Entry paramEntry)
  {
    if (paramEntry == null) {
      return null;
    }
    int i = 0;
    while (i < mDataSets.size())
    {
      IDataSet localIDataSet = (IDataSet)mDataSets.get(i);
      int j = 0;
      while (j < localIDataSet.getEntryCount())
      {
        if (paramEntry.equalTo(localIDataSet.getEntryForXValue(paramEntry.getX(), paramEntry.getY()))) {
          return localIDataSet;
        }
        j += 1;
      }
      i += 1;
    }
    return null;
  }
  
  protected int getDataSetIndexByLabel(List<T> paramList, String paramString, boolean paramBoolean)
  {
    int i = 0;
    int j = 0;
    if (paramBoolean)
    {
      i = j;
      while (i < paramList.size())
      {
        if (paramString.equalsIgnoreCase(((IDataSet)paramList.get(i)).getLabel())) {
          return i;
        }
        i += 1;
      }
    }
    while (i < paramList.size())
    {
      if (paramString.equals(((IDataSet)paramList.get(i)).getLabel())) {
        return i;
      }
      i += 1;
    }
    return -1;
  }
  
  public String[] getDataSetLabels()
  {
    String[] arrayOfString = new String[mDataSets.size()];
    int i = 0;
    while (i < mDataSets.size())
    {
      arrayOfString[i] = ((IDataSet)mDataSets.get(i)).getLabel();
      i += 1;
    }
    return arrayOfString;
  }
  
  public List<T> getDataSets()
  {
    return mDataSets;
  }
  
  public int getEntryCount()
  {
    Iterator localIterator = mDataSets.iterator();
    int i = 0;
    while (localIterator.hasNext()) {
      i += ((IDataSet)localIterator.next()).getEntryCount();
    }
    return i;
  }
  
  public Entry getEntryForHighlight(Highlight paramHighlight)
  {
    if (paramHighlight.getDataSetIndex() >= mDataSets.size()) {
      return null;
    }
    return ((IDataSet)mDataSets.get(paramHighlight.getDataSetIndex())).getEntryForXValue(paramHighlight.getX(), paramHighlight.getY());
  }
  
  protected T getFirstLeft(List<T> paramList)
  {
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      IDataSet localIDataSet = (IDataSet)paramList.next();
      if (localIDataSet.getAxisDependency() == YAxis.AxisDependency.LEFT) {
        return localIDataSet;
      }
    }
    return null;
  }
  
  public T getFirstRight(List<T> paramList)
  {
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      IDataSet localIDataSet = (IDataSet)paramList.next();
      if (localIDataSet.getAxisDependency() == YAxis.AxisDependency.RIGHT) {
        return localIDataSet;
      }
    }
    return null;
  }
  
  public int getIndexOfDataSet(T paramT)
  {
    return mDataSets.indexOf(paramT);
  }
  
  public T getMaxEntryCountSet()
  {
    if ((mDataSets != null) && (!mDataSets.isEmpty()))
    {
      Object localObject = (IDataSet)mDataSets.get(0);
      Iterator localIterator = mDataSets.iterator();
      while (localIterator.hasNext())
      {
        IDataSet localIDataSet = (IDataSet)localIterator.next();
        if (localIDataSet.getEntryCount() > ((IDataSet)localObject).getEntryCount()) {
          localObject = localIDataSet;
        }
      }
      return localObject;
    }
    return null;
  }
  
  public float getXMax()
  {
    return mXMax;
  }
  
  public float getXMin()
  {
    return mXMin;
  }
  
  public float getYMax()
  {
    return mYMax;
  }
  
  public float getYMax(YAxis.AxisDependency paramAxisDependency)
  {
    if (paramAxisDependency == YAxis.AxisDependency.LEFT)
    {
      if (mLeftAxisMax == -3.4028235E38F) {
        return mRightAxisMax;
      }
      return mLeftAxisMax;
    }
    if (mRightAxisMax == -3.4028235E38F) {
      return mLeftAxisMax;
    }
    return mRightAxisMax;
  }
  
  public float getYMin()
  {
    return mYMin;
  }
  
  public float getYMin(YAxis.AxisDependency paramAxisDependency)
  {
    if (paramAxisDependency == YAxis.AxisDependency.LEFT)
    {
      if (mLeftAxisMin == Float.MAX_VALUE) {
        return mRightAxisMin;
      }
      return mLeftAxisMin;
    }
    if (mRightAxisMin == Float.MAX_VALUE) {
      return mLeftAxisMin;
    }
    return mRightAxisMin;
  }
  
  public boolean isHighlightEnabled()
  {
    Iterator localIterator = mDataSets.iterator();
    while (localIterator.hasNext()) {
      if (!((IDataSet)localIterator.next()).isHighlightEnabled()) {
        return false;
      }
    }
    return true;
  }
  
  public void notifyDataChanged()
  {
    calcMinMax();
  }
  
  public boolean removeDataSet(int paramInt)
  {
    if ((paramInt < mDataSets.size()) && (paramInt >= 0)) {
      return removeDataSet((IDataSet)mDataSets.get(paramInt));
    }
    return false;
  }
  
  public boolean removeDataSet(T paramT)
  {
    if (paramT == null) {
      return false;
    }
    boolean bool = mDataSets.remove(paramT);
    if (bool) {
      calcMinMax();
    }
    return bool;
  }
  
  public boolean removeEntry(float paramFloat, int paramInt)
  {
    if (paramInt >= mDataSets.size()) {
      return false;
    }
    Entry localEntry = ((IDataSet)mDataSets.get(paramInt)).getEntryForXValue(paramFloat, NaN.0F);
    if (localEntry == null) {
      return false;
    }
    return removeEntry(localEntry, paramInt);
  }
  
  public boolean removeEntry(Entry paramEntry, int paramInt)
  {
    if (paramEntry != null)
    {
      if (paramInt >= mDataSets.size()) {
        return false;
      }
      IDataSet localIDataSet = (IDataSet)mDataSets.get(paramInt);
      if (localIDataSet != null)
      {
        boolean bool = localIDataSet.removeEntry(paramEntry);
        if (bool) {
          calcMinMax();
        }
        return bool;
      }
      return false;
    }
    return false;
  }
  
  public void setDrawValues(boolean paramBoolean)
  {
    Iterator localIterator = mDataSets.iterator();
    while (localIterator.hasNext()) {
      ((IDataSet)localIterator.next()).setDrawValues(paramBoolean);
    }
  }
  
  public void setHighlightEnabled(boolean paramBoolean)
  {
    Iterator localIterator = mDataSets.iterator();
    while (localIterator.hasNext()) {
      ((IDataSet)localIterator.next()).setHighlightEnabled(paramBoolean);
    }
  }
  
  public void setValueFormatter(IValueFormatter paramIValueFormatter)
  {
    if (paramIValueFormatter == null) {
      return;
    }
    Iterator localIterator = mDataSets.iterator();
    while (localIterator.hasNext()) {
      ((IDataSet)localIterator.next()).setValueFormatter(paramIValueFormatter);
    }
  }
  
  public void setValueTextColor(int paramInt)
  {
    Iterator localIterator = mDataSets.iterator();
    while (localIterator.hasNext()) {
      ((IDataSet)localIterator.next()).setValueTextColor(paramInt);
    }
  }
  
  public void setValueTextColors(List<Integer> paramList)
  {
    Iterator localIterator = mDataSets.iterator();
    while (localIterator.hasNext()) {
      ((IDataSet)localIterator.next()).setValueTextColors(paramList);
    }
  }
  
  public void setValueTextSize(float paramFloat)
  {
    Iterator localIterator = mDataSets.iterator();
    while (localIterator.hasNext()) {
      ((IDataSet)localIterator.next()).setValueTextSize(paramFloat);
    }
  }
  
  public void setValueTypeface(Typeface paramTypeface)
  {
    Iterator localIterator = mDataSets.iterator();
    while (localIterator.hasNext()) {
      ((IDataSet)localIterator.next()).setValueTypeface(paramTypeface);
    }
  }
}
