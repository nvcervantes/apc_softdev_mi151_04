package com.github.mikephil.charting.data;

import android.util.Log;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarLineScatterCandleBubbleDataSet;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CombinedData
  extends BarLineScatterCandleBubbleData<IBarLineScatterCandleBubbleDataSet<? extends Entry>>
{
  private BarData mBarData;
  private BubbleData mBubbleData;
  private CandleData mCandleData;
  private LineData mLineData;
  private ScatterData mScatterData;
  
  public CombinedData() {}
  
  public void calcMinMax()
  {
    if (mDataSets == null) {
      mDataSets = new ArrayList();
    }
    mDataSets.clear();
    mYMax = -3.4028235E38F;
    mYMin = Float.MAX_VALUE;
    mXMax = -3.4028235E38F;
    mXMin = Float.MAX_VALUE;
    mLeftAxisMax = -3.4028235E38F;
    mLeftAxisMin = Float.MAX_VALUE;
    mRightAxisMax = -3.4028235E38F;
    mRightAxisMin = Float.MAX_VALUE;
    Iterator localIterator = getAllData().iterator();
    while (localIterator.hasNext())
    {
      ChartData localChartData = (ChartData)localIterator.next();
      localChartData.calcMinMax();
      List localList = localChartData.getDataSets();
      mDataSets.addAll(localList);
      if (localChartData.getYMax() > mYMax) {
        mYMax = localChartData.getYMax();
      }
      if (localChartData.getYMin() < mYMin) {
        mYMin = localChartData.getYMin();
      }
      if (localChartData.getXMax() > mXMax) {
        mXMax = localChartData.getXMax();
      }
      if (localChartData.getXMin() < mXMin) {
        mXMin = localChartData.getXMin();
      }
      if (mLeftAxisMax > mLeftAxisMax) {
        mLeftAxisMax = mLeftAxisMax;
      }
      if (mLeftAxisMin < mLeftAxisMin) {
        mLeftAxisMin = mLeftAxisMin;
      }
      if (mRightAxisMax > mRightAxisMax) {
        mRightAxisMax = mRightAxisMax;
      }
      if (mRightAxisMin < mRightAxisMin) {
        mRightAxisMin = mRightAxisMin;
      }
    }
  }
  
  public List<BarLineScatterCandleBubbleData> getAllData()
  {
    ArrayList localArrayList = new ArrayList();
    if (mLineData != null) {
      localArrayList.add(mLineData);
    }
    if (mBarData != null) {
      localArrayList.add(mBarData);
    }
    if (mScatterData != null) {
      localArrayList.add(mScatterData);
    }
    if (mCandleData != null) {
      localArrayList.add(mCandleData);
    }
    if (mBubbleData != null) {
      localArrayList.add(mBubbleData);
    }
    return localArrayList;
  }
  
  public BarData getBarData()
  {
    return mBarData;
  }
  
  public BubbleData getBubbleData()
  {
    return mBubbleData;
  }
  
  public CandleData getCandleData()
  {
    return mCandleData;
  }
  
  public BarLineScatterCandleBubbleData getDataByIndex(int paramInt)
  {
    return (BarLineScatterCandleBubbleData)getAllData().get(paramInt);
  }
  
  public int getDataIndex(ChartData paramChartData)
  {
    return getAllData().indexOf(paramChartData);
  }
  
  public IBarLineScatterCandleBubbleDataSet<? extends Entry> getDataSetByHighlight(Highlight paramHighlight)
  {
    if (paramHighlight.getDataIndex() >= getAllData().size()) {
      return null;
    }
    BarLineScatterCandleBubbleData localBarLineScatterCandleBubbleData = getDataByIndex(paramHighlight.getDataIndex());
    if (paramHighlight.getDataSetIndex() >= localBarLineScatterCandleBubbleData.getDataSetCount()) {
      return null;
    }
    return (IBarLineScatterCandleBubbleDataSet)localBarLineScatterCandleBubbleData.getDataSets().get(paramHighlight.getDataSetIndex());
  }
  
  public Entry getEntryForHighlight(Highlight paramHighlight)
  {
    if (paramHighlight.getDataIndex() >= getAllData().size()) {
      return null;
    }
    Object localObject = getDataByIndex(paramHighlight.getDataIndex());
    if (paramHighlight.getDataSetIndex() >= ((ChartData)localObject).getDataSetCount()) {
      return null;
    }
    localObject = ((ChartData)localObject).getDataSetByIndex(paramHighlight.getDataSetIndex()).getEntriesForXValue(paramHighlight.getX()).iterator();
    while (((Iterator)localObject).hasNext())
    {
      Entry localEntry = (Entry)((Iterator)localObject).next();
      if ((localEntry.getY() == paramHighlight.getY()) || (Float.isNaN(paramHighlight.getY()))) {
        return localEntry;
      }
    }
    return null;
  }
  
  public LineData getLineData()
  {
    return mLineData;
  }
  
  public ScatterData getScatterData()
  {
    return mScatterData;
  }
  
  public void notifyDataChanged()
  {
    if (mLineData != null) {
      mLineData.notifyDataChanged();
    }
    if (mBarData != null) {
      mBarData.notifyDataChanged();
    }
    if (mCandleData != null) {
      mCandleData.notifyDataChanged();
    }
    if (mScatterData != null) {
      mScatterData.notifyDataChanged();
    }
    if (mBubbleData != null) {
      mBubbleData.notifyDataChanged();
    }
    calcMinMax();
  }
  
  @Deprecated
  public boolean removeDataSet(int paramInt)
  {
    Log.e("MPAndroidChart", "removeDataSet(int index) not supported for CombinedData");
    return false;
  }
  
  public boolean removeDataSet(IBarLineScatterCandleBubbleDataSet<? extends Entry> paramIBarLineScatterCandleBubbleDataSet)
  {
    Iterator localIterator = getAllData().iterator();
    boolean bool1 = false;
    while (localIterator.hasNext())
    {
      boolean bool2 = ((ChartData)localIterator.next()).removeDataSet(paramIBarLineScatterCandleBubbleDataSet);
      bool1 = bool2;
      if (bool2) {
        bool1 = bool2;
      }
    }
    return bool1;
  }
  
  @Deprecated
  public boolean removeEntry(float paramFloat, int paramInt)
  {
    Log.e("MPAndroidChart", "removeEntry(...) not supported for CombinedData");
    return false;
  }
  
  @Deprecated
  public boolean removeEntry(Entry paramEntry, int paramInt)
  {
    Log.e("MPAndroidChart", "removeEntry(...) not supported for CombinedData");
    return false;
  }
  
  public void setData(BarData paramBarData)
  {
    mBarData = paramBarData;
    notifyDataChanged();
  }
  
  public void setData(BubbleData paramBubbleData)
  {
    mBubbleData = paramBubbleData;
    notifyDataChanged();
  }
  
  public void setData(CandleData paramCandleData)
  {
    mCandleData = paramCandleData;
    notifyDataChanged();
  }
  
  public void setData(LineData paramLineData)
  {
    mLineData = paramLineData;
    notifyDataChanged();
  }
  
  public void setData(ScatterData paramScatterData)
  {
    mScatterData = paramScatterData;
    notifyDataChanged();
  }
}
