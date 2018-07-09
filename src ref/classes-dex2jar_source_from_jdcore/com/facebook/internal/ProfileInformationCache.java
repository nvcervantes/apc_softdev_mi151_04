package com.facebook.internal;

import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONObject;

class ProfileInformationCache
{
  private static final ConcurrentHashMap<String, JSONObject> infoCache = new ConcurrentHashMap();
  
  ProfileInformationCache() {}
  
  public static JSONObject getProfileInformation(String paramString)
  {
    return (JSONObject)infoCache.get(paramString);
  }
  
  public static void putProfileInformation(String paramString, JSONObject paramJSONObject)
  {
    infoCache.put(paramString, paramJSONObject);
  }
}
