package com.amplitude.api;

import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Utils
{
  public static final String TAG = "com.amplitude.api.Utils";
  private static AmplitudeLog logger = ;
  
  public Utils() {}
  
  static JSONObject cloneJSONObject(JSONObject paramJSONObject)
  {
    if (paramJSONObject == null) {
      return null;
    }
    Object localObject;
    try
    {
      JSONArray localJSONArray = paramJSONObject.names();
    }
    catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException)
    {
      logger.e("com.amplitude.api.Utils", localArrayIndexOutOfBoundsException.toString());
      localObject = null;
    }
    int j = 0;
    int i;
    if (localObject != null) {
      i = localObject.length();
    } else {
      i = 0;
    }
    String[] arrayOfString = new String[i];
    while (j < i)
    {
      arrayOfString[j] = localObject.optString(j);
      j += 1;
    }
    try
    {
      paramJSONObject = new JSONObject(paramJSONObject, arrayOfString);
      return paramJSONObject;
    }
    catch (JSONException paramJSONObject)
    {
      logger.e("com.amplitude.api.Utils", paramJSONObject.toString());
    }
    return null;
  }
  
  static boolean compareJSONObjects(JSONObject paramJSONObject1, JSONObject paramJSONObject2)
  {
    if (paramJSONObject1 == paramJSONObject2) {
      return true;
    }
    if (((paramJSONObject1 != null) && (paramJSONObject2 == null)) || ((paramJSONObject1 == null) && (paramJSONObject2 != null))) {
      return false;
    }
    try
    {
      if (paramJSONObject1.length() != paramJSONObject2.length()) {
        return false;
      }
      Iterator localIterator = paramJSONObject1.keys();
      while (localIterator.hasNext())
      {
        Object localObject2 = (String)localIterator.next();
        if (!paramJSONObject2.has((String)localObject2)) {
          return false;
        }
        Object localObject1 = paramJSONObject1.get((String)localObject2);
        localObject2 = paramJSONObject2.get((String)localObject2);
        if (!localObject1.getClass().equals(localObject2.getClass())) {
          return false;
        }
        if (localObject1.getClass() == JSONObject.class)
        {
          if (!compareJSONObjects((JSONObject)localObject1, (JSONObject)localObject2)) {
            return false;
          }
        }
        else
        {
          boolean bool = localObject1.equals(localObject2);
          if (!bool) {
            return false;
          }
        }
      }
      return true;
    }
    catch (JSONException paramJSONObject1) {}
    return false;
  }
  
  public static boolean isEmptyString(String paramString)
  {
    return (paramString == null) || (paramString.length() == 0);
  }
  
  static String normalizeInstanceName(String paramString)
  {
    String str = paramString;
    if (isEmptyString(paramString)) {
      str = "$default_instance";
    }
    return str.toLowerCase();
  }
}
