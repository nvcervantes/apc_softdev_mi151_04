package com.google.android.gms.maps.model;

import android.support.annotation.NonNull;

public final class CustomCap
  extends Cap
{
  public final BitmapDescriptor bitmapDescriptor;
  public final float refWidth;
  
  public CustomCap(@NonNull BitmapDescriptor paramBitmapDescriptor)
  {
    this(paramBitmapDescriptor, 10.0F);
  }
  
  public CustomCap(@NonNull BitmapDescriptor paramBitmapDescriptor, float paramFloat)
  {
    super(localBitmapDescriptor, paramFloat);
    bitmapDescriptor = paramBitmapDescriptor;
    refWidth = paramFloat;
  }
  
  public final String toString()
  {
    String str = String.valueOf(bitmapDescriptor);
    float f = refWidth;
    StringBuilder localStringBuilder = new StringBuilder(55 + String.valueOf(str).length());
    localStringBuilder.append("[CustomCap: bitmapDescriptor=");
    localStringBuilder.append(str);
    localStringBuilder.append(" refWidth=");
    localStringBuilder.append(f);
    localStringBuilder.append("]");
    return localStringBuilder.toString();
  }
}
