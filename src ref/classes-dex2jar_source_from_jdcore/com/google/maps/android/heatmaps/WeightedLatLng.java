package com.google.maps.android.heatmaps;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.geometry.Point;
import com.google.maps.android.projection.SphericalMercatorProjection;
import com.google.maps.android.quadtree.PointQuadTree.Item;

public class WeightedLatLng
  implements PointQuadTree.Item
{
  public static final double DEFAULT_INTENSITY = 1.0D;
  private static final SphericalMercatorProjection sProjection = new SphericalMercatorProjection(1.0D);
  private double mIntensity;
  private Point mPoint;
  
  public WeightedLatLng(LatLng paramLatLng)
  {
    this(paramLatLng, 1.0D);
  }
  
  public WeightedLatLng(LatLng paramLatLng, double paramDouble)
  {
    mPoint = sProjection.toPoint(paramLatLng);
    if (paramDouble >= 0.0D)
    {
      mIntensity = paramDouble;
      return;
    }
    mIntensity = 1.0D;
  }
  
  public double getIntensity()
  {
    return mIntensity;
  }
  
  public Point getPoint()
  {
    return mPoint;
  }
}
