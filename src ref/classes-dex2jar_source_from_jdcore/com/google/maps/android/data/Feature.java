package com.google.maps.android.data;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

public class Feature
  extends Observable
{
  private Geometry mGeometry;
  private final String mId;
  private final Map<String, String> mProperties;
  
  public Feature(Geometry paramGeometry, String paramString, Map<String, String> paramMap)
  {
    mGeometry = paramGeometry;
    mId = paramString;
    if (paramMap == null)
    {
      mProperties = new HashMap();
      return;
    }
    mProperties = paramMap;
  }
  
  public Geometry getGeometry()
  {
    return mGeometry;
  }
  
  public String getId()
  {
    return mId;
  }
  
  public Iterable getProperties()
  {
    return mProperties.entrySet();
  }
  
  public String getProperty(String paramString)
  {
    return (String)mProperties.get(paramString);
  }
  
  public Iterable<String> getPropertyKeys()
  {
    return mProperties.keySet();
  }
  
  public boolean hasGeometry()
  {
    return mGeometry != null;
  }
  
  public boolean hasProperties()
  {
    return mProperties.size() > 0;
  }
  
  public boolean hasProperty(String paramString)
  {
    return mProperties.containsKey(paramString);
  }
  
  protected String removeProperty(String paramString)
  {
    return (String)mProperties.remove(paramString);
  }
  
  protected void setGeometry(Geometry paramGeometry)
  {
    mGeometry = paramGeometry;
  }
  
  protected String setProperty(String paramString1, String paramString2)
  {
    return (String)mProperties.put(paramString1, paramString2);
  }
}
