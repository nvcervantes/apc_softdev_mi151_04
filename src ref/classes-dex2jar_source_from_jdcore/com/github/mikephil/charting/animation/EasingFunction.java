package com.github.mikephil.charting.animation;

import android.animation.TimeInterpolator;
import android.annotation.SuppressLint;

@SuppressLint({"NewApi"})
public abstract interface EasingFunction
  extends TimeInterpolator
{
  public abstract float getInterpolation(float paramFloat);
}
