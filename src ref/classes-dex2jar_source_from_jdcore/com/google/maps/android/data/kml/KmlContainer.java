package com.google.maps.android.data.kml;

import com.google.android.gms.maps.model.GroundOverlay;
import java.util.ArrayList;
import java.util.HashMap;

public class KmlContainer
{
  private String mContainerId;
  private final ArrayList<KmlContainer> mContainers;
  private final HashMap<KmlGroundOverlay, GroundOverlay> mGroundOverlays;
  private final HashMap<KmlPlacemark, Object> mPlacemarks;
  private final HashMap<String, String> mProperties;
  private final HashMap<String, String> mStyleMap;
  private HashMap<String, KmlStyle> mStyles;
  
  KmlContainer(HashMap<String, String> paramHashMap1, HashMap<String, KmlStyle> paramHashMap, HashMap<KmlPlacemark, Object> paramHashMap2, HashMap<String, String> paramHashMap3, ArrayList<KmlContainer> paramArrayList, HashMap<KmlGroundOverlay, GroundOverlay> paramHashMap4, String paramString)
  {
    mProperties = paramHashMap1;
    mPlacemarks = paramHashMap2;
    mStyles = paramHashMap;
    mStyleMap = paramHashMap3;
    mContainers = paramArrayList;
    mGroundOverlays = paramHashMap4;
    mContainerId = paramString;
  }
  
  public String getContainerId()
  {
    return mContainerId;
  }
  
  public Iterable<KmlContainer> getContainers()
  {
    return mContainers;
  }
  
  HashMap<KmlGroundOverlay, GroundOverlay> getGroundOverlayHashMap()
  {
    return mGroundOverlays;
  }
  
  public Iterable<KmlGroundOverlay> getGroundOverlays()
  {
    return mGroundOverlays.keySet();
  }
  
  public Iterable<KmlPlacemark> getPlacemarks()
  {
    return mPlacemarks.keySet();
  }
  
  HashMap<KmlPlacemark, Object> getPlacemarksHashMap()
  {
    return mPlacemarks;
  }
  
  public Iterable<String> getProperties()
  {
    return mProperties.keySet();
  }
  
  public String getProperty(String paramString)
  {
    return (String)mProperties.get(paramString);
  }
  
  public KmlStyle getStyle(String paramString)
  {
    return (KmlStyle)mStyles.get(paramString);
  }
  
  HashMap<String, String> getStyleMap()
  {
    return mStyleMap;
  }
  
  HashMap<String, KmlStyle> getStyles()
  {
    return mStyles;
  }
  
  public boolean hasContainers()
  {
    return mContainers.size() > 0;
  }
  
  public boolean hasPlacemarks()
  {
    return mPlacemarks.size() > 0;
  }
  
  public boolean hasProperties()
  {
    return mProperties.size() > 0;
  }
  
  public boolean hasProperty(String paramString)
  {
    return mProperties.containsKey(paramString);
  }
  
  void setPlacemark(KmlPlacemark paramKmlPlacemark, Object paramObject)
  {
    mPlacemarks.put(paramKmlPlacemark, paramObject);
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder("Container");
    localStringBuilder.append("{");
    localStringBuilder.append("\n properties=");
    localStringBuilder.append(mProperties);
    localStringBuilder.append(",\n placemarks=");
    localStringBuilder.append(mPlacemarks);
    localStringBuilder.append(",\n containers=");
    localStringBuilder.append(mContainers);
    localStringBuilder.append(",\n ground overlays=");
    localStringBuilder.append(mGroundOverlays);
    localStringBuilder.append(",\n style maps=");
    localStringBuilder.append(mStyleMap);
    localStringBuilder.append(",\n styles=");
    localStringBuilder.append(mStyles);
    localStringBuilder.append("\n}\n");
    return localStringBuilder.toString();
  }
}
