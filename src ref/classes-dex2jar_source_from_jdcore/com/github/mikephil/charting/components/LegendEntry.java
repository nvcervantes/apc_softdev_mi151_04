package com.github.mikephil.charting.components;

import android.graphics.DashPathEffect;

public class LegendEntry
{
  public Legend.LegendForm form = Legend.LegendForm.DEFAULT;
  public int formColor = 1122867;
  public DashPathEffect formLineDashEffect = null;
  public float formLineWidth = NaN.0F;
  public float formSize = NaN.0F;
  public String label;
  
  public LegendEntry() {}
  
  public LegendEntry(String paramString, Legend.LegendForm paramLegendForm, float paramFloat1, float paramFloat2, DashPathEffect paramDashPathEffect, int paramInt)
  {
    label = paramString;
    form = paramLegendForm;
    formSize = paramFloat1;
    formLineWidth = paramFloat2;
    formLineDashEffect = paramDashPathEffect;
    formColor = paramInt;
  }
}
