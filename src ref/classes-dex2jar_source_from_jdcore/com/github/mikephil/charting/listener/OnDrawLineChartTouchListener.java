package com.github.mikephil.charting.listener;

import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class OnDrawLineChartTouchListener
  extends GestureDetector.SimpleOnGestureListener
  implements View.OnTouchListener
{
  public OnDrawLineChartTouchListener() {}
  
  public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
  {
    return false;
  }
}
