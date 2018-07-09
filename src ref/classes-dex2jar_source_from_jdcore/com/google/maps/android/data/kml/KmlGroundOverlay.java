package com.google.maps.android.data.kml;

import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLngBounds;
import java.util.HashMap;
import java.util.Map;

public class KmlGroundOverlay
{
  private final GroundOverlayOptions mGroundOverlayOptions = new GroundOverlayOptions();
  private String mImageUrl;
  private LatLngBounds mLatLngBox;
  private final Map<String, String> mProperties;
  
  KmlGroundOverlay(String paramString, LatLngBounds paramLatLngBounds, float paramFloat1, int paramInt, HashMap<String, String> paramHashMap, float paramFloat2)
  {
    mImageUrl = paramString;
    mProperties = paramHashMap;
    if (paramLatLngBounds == null) {
      throw new IllegalArgumentException("No LatLonBox given");
    }
    mLatLngBox = paramLatLngBounds;
    mGroundOverlayOptions.positionFromBounds(paramLatLngBounds);
    mGroundOverlayOptions.bearing(paramFloat2);
    mGroundOverlayOptions.zIndex(paramFloat1);
    paramString = mGroundOverlayOptions;
    boolean bool;
    if (paramInt != 0) {
      bool = true;
    } else {
      bool = false;
    }
    paramString.visible(bool);
  }
  
  GroundOverlayOptions getGroundOverlayOptions()
  {
    return mGroundOverlayOptions;
  }
  
  public String getImageUrl()
  {
    return mImageUrl;
  }
  
  public LatLngBounds getLatLngBox()
  {
    return mLatLngBox;
  }
  
  public Iterable<String> getProperties()
  {
    return mProperties.keySet();
  }
  
  public String getProperty(String paramString)
  {
    return (String)mProperties.get(paramString);
  }
  
  public boolean hasProperty(String paramString)
  {
    return mProperties.get(paramString) != null;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder("GroundOverlay");
    localStringBuilder.append("{");
    localStringBuilder.append("\n properties=");
    localStringBuilder.append(mProperties);
    localStringBuilder.append(",\n image url=");
    localStringBuilder.append(mImageUrl);
    localStringBuilder.append(",\n LatLngBox=");
    localStringBuilder.append(mLatLngBox);
    localStringBuilder.append("\n}\n");
    return localStringBuilder.toString();
  }
}
