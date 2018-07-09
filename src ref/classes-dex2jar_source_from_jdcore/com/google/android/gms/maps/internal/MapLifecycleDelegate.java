package com.google.android.gms.maps.internal;

import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.dynamic.LifecycleDelegate;
import com.google.android.gms.maps.OnMapReadyCallback;

@Hide
public abstract interface MapLifecycleDelegate
  extends LifecycleDelegate
{
  public abstract void getMapAsync(OnMapReadyCallback paramOnMapReadyCallback);
}
