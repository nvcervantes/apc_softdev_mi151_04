package com.amplitude.api;

import java.util.HashSet;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Identify
{
  public static final String TAG = "com.amplitude.api.Identify";
  protected Set<String> userProperties = new HashSet();
  protected JSONObject userPropertiesOperations = new JSONObject();
  
  public Identify() {}
  
  private void addToUserProperties(String paramString1, String paramString2, Object paramObject)
  {
    if (Utils.isEmptyString(paramString2))
    {
      AmplitudeLog.getLogger().w("com.amplitude.api.Identify", String.format("Attempting to perform operation %s with a null or empty string property, ignoring", new Object[] { paramString1 }));
      return;
    }
    if (paramObject == null)
    {
      AmplitudeLog.getLogger().w("com.amplitude.api.Identify", String.format("Attempting to perform operation %s with null value for property %s, ignoring", new Object[] { paramString1, paramString2 }));
      return;
    }
    if (userPropertiesOperations.has("$clearAll"))
    {
      AmplitudeLog.getLogger().w("com.amplitude.api.Identify", String.format("This Identify already contains a $clearAll operation, ignoring operation %s", new Object[] { paramString1 }));
      return;
    }
    if (userProperties.contains(paramString2))
    {
      AmplitudeLog.getLogger().w("com.amplitude.api.Identify", String.format("Already used property %s in previous operation, ignoring operation %s", new Object[] { paramString2, paramString1 }));
      return;
    }
    try
    {
      if (!userPropertiesOperations.has(paramString1)) {
        userPropertiesOperations.put(paramString1, new JSONObject());
      }
      userPropertiesOperations.getJSONObject(paramString1).put(paramString2, paramObject);
      userProperties.add(paramString2);
      return;
    }
    catch (JSONException paramString1)
    {
      AmplitudeLog.getLogger().e("com.amplitude.api.Identify", paramString1.toString());
    }
  }
  
  private JSONArray booleanArrayToJSONArray(boolean[] paramArrayOfBoolean)
  {
    JSONArray localJSONArray = new JSONArray();
    int i = 0;
    int j = paramArrayOfBoolean.length;
    while (i < j)
    {
      localJSONArray.put(paramArrayOfBoolean[i]);
      i += 1;
    }
    return localJSONArray;
  }
  
  private JSONArray doubleArrayToJSONArray(double[] paramArrayOfDouble)
  {
    JSONArray localJSONArray = new JSONArray();
    int j = paramArrayOfDouble.length;
    int i = 0;
    while (i < j)
    {
      double d = paramArrayOfDouble[i];
      try
      {
        localJSONArray.put(d);
      }
      catch (JSONException localJSONException)
      {
        AmplitudeLog.getLogger().e("com.amplitude.api.Identify", String.format("Error converting double %d to JSON: %s", new Object[] { Double.valueOf(d), localJSONException.toString() }));
      }
      i += 1;
    }
    return localJSONArray;
  }
  
  private JSONArray floatArrayToJSONArray(float[] paramArrayOfFloat)
  {
    JSONArray localJSONArray = new JSONArray();
    int j = paramArrayOfFloat.length;
    int i = 0;
    while (i < j)
    {
      float f = paramArrayOfFloat[i];
      double d = f;
      try
      {
        localJSONArray.put(d);
      }
      catch (JSONException localJSONException)
      {
        AmplitudeLog.getLogger().e("com.amplitude.api.Identify", String.format("Error converting float %f to JSON: %s", new Object[] { Float.valueOf(f), localJSONException.toString() }));
      }
      i += 1;
    }
    return localJSONArray;
  }
  
  private JSONArray intArrayToJSONArray(int[] paramArrayOfInt)
  {
    JSONArray localJSONArray = new JSONArray();
    int i = 0;
    int j = paramArrayOfInt.length;
    while (i < j)
    {
      localJSONArray.put(paramArrayOfInt[i]);
      i += 1;
    }
    return localJSONArray;
  }
  
  private JSONArray longArrayToJSONArray(long[] paramArrayOfLong)
  {
    JSONArray localJSONArray = new JSONArray();
    int i = 0;
    int j = paramArrayOfLong.length;
    while (i < j)
    {
      localJSONArray.put(paramArrayOfLong[i]);
      i += 1;
    }
    return localJSONArray;
  }
  
  private JSONArray stringArrayToJSONArray(String[] paramArrayOfString)
  {
    JSONArray localJSONArray = new JSONArray();
    int i = 0;
    int j = paramArrayOfString.length;
    while (i < j)
    {
      localJSONArray.put(paramArrayOfString[i]);
      i += 1;
    }
    return localJSONArray;
  }
  
  public Identify add(String paramString, double paramDouble)
  {
    addToUserProperties("$add", paramString, Double.valueOf(paramDouble));
    return this;
  }
  
  public Identify add(String paramString, float paramFloat)
  {
    addToUserProperties("$add", paramString, Float.valueOf(paramFloat));
    return this;
  }
  
  public Identify add(String paramString, int paramInt)
  {
    addToUserProperties("$add", paramString, Integer.valueOf(paramInt));
    return this;
  }
  
  public Identify add(String paramString, long paramLong)
  {
    addToUserProperties("$add", paramString, Long.valueOf(paramLong));
    return this;
  }
  
  public Identify add(String paramString1, String paramString2)
  {
    addToUserProperties("$add", paramString1, paramString2);
    return this;
  }
  
  public Identify add(String paramString, JSONObject paramJSONObject)
  {
    addToUserProperties("$add", paramString, paramJSONObject);
    return this;
  }
  
  public Identify append(String paramString, double paramDouble)
  {
    addToUserProperties("$append", paramString, Double.valueOf(paramDouble));
    return this;
  }
  
  public Identify append(String paramString, float paramFloat)
  {
    addToUserProperties("$append", paramString, Float.valueOf(paramFloat));
    return this;
  }
  
  public Identify append(String paramString, int paramInt)
  {
    addToUserProperties("$append", paramString, Integer.valueOf(paramInt));
    return this;
  }
  
  public Identify append(String paramString, long paramLong)
  {
    addToUserProperties("$append", paramString, Long.valueOf(paramLong));
    return this;
  }
  
  public Identify append(String paramString1, String paramString2)
  {
    addToUserProperties("$append", paramString1, paramString2);
    return this;
  }
  
  public Identify append(String paramString, JSONArray paramJSONArray)
  {
    addToUserProperties("$append", paramString, paramJSONArray);
    return this;
  }
  
  public Identify append(String paramString, JSONObject paramJSONObject)
  {
    addToUserProperties("$append", paramString, paramJSONObject);
    return this;
  }
  
  public Identify append(String paramString, boolean paramBoolean)
  {
    addToUserProperties("$append", paramString, Boolean.valueOf(paramBoolean));
    return this;
  }
  
  public Identify append(String paramString, double[] paramArrayOfDouble)
  {
    addToUserProperties("$append", paramString, doubleArrayToJSONArray(paramArrayOfDouble));
    return this;
  }
  
  public Identify append(String paramString, float[] paramArrayOfFloat)
  {
    addToUserProperties("$append", paramString, floatArrayToJSONArray(paramArrayOfFloat));
    return this;
  }
  
  public Identify append(String paramString, int[] paramArrayOfInt)
  {
    addToUserProperties("$append", paramString, intArrayToJSONArray(paramArrayOfInt));
    return this;
  }
  
  public Identify append(String paramString, long[] paramArrayOfLong)
  {
    addToUserProperties("$append", paramString, longArrayToJSONArray(paramArrayOfLong));
    return this;
  }
  
  public Identify append(String paramString, String[] paramArrayOfString)
  {
    addToUserProperties("$append", paramString, stringArrayToJSONArray(paramArrayOfString));
    return this;
  }
  
  public Identify append(String paramString, boolean[] paramArrayOfBoolean)
  {
    addToUserProperties("$append", paramString, booleanArrayToJSONArray(paramArrayOfBoolean));
    return this;
  }
  
  public Identify clearAll()
  {
    if (userPropertiesOperations.length() > 0)
    {
      if (!userProperties.contains("$clearAll")) {
        AmplitudeLog.getLogger().w("com.amplitude.api.Identify", String.format("Need to send $clearAll on its own Identify object without any other operations, ignoring $clearAll", new Object[0]));
      }
      return this;
    }
    try
    {
      userPropertiesOperations.put("$clearAll", "-");
      return this;
    }
    catch (JSONException localJSONException)
    {
      AmplitudeLog.getLogger().e("com.amplitude.api.Identify", localJSONException.toString());
    }
    return this;
  }
  
  public JSONObject getUserPropertiesOperations()
  {
    try
    {
      JSONObject localJSONObject = new JSONObject(userPropertiesOperations.toString());
      return localJSONObject;
    }
    catch (JSONException localJSONException)
    {
      AmplitudeLog.getLogger().e("com.amplitude.api.Identify", localJSONException.toString());
    }
    return new JSONObject();
  }
  
  public Identify prepend(String paramString, double paramDouble)
  {
    addToUserProperties("$prepend", paramString, Double.valueOf(paramDouble));
    return this;
  }
  
  public Identify prepend(String paramString, float paramFloat)
  {
    addToUserProperties("$prepend", paramString, Float.valueOf(paramFloat));
    return this;
  }
  
  public Identify prepend(String paramString, int paramInt)
  {
    addToUserProperties("$prepend", paramString, Integer.valueOf(paramInt));
    return this;
  }
  
  public Identify prepend(String paramString, long paramLong)
  {
    addToUserProperties("$prepend", paramString, Long.valueOf(paramLong));
    return this;
  }
  
  public Identify prepend(String paramString1, String paramString2)
  {
    addToUserProperties("$prepend", paramString1, paramString2);
    return this;
  }
  
  public Identify prepend(String paramString, JSONArray paramJSONArray)
  {
    addToUserProperties("$prepend", paramString, paramJSONArray);
    return this;
  }
  
  public Identify prepend(String paramString, JSONObject paramJSONObject)
  {
    addToUserProperties("$prepend", paramString, paramJSONObject);
    return this;
  }
  
  public Identify prepend(String paramString, boolean paramBoolean)
  {
    addToUserProperties("$prepend", paramString, Boolean.valueOf(paramBoolean));
    return this;
  }
  
  public Identify prepend(String paramString, double[] paramArrayOfDouble)
  {
    addToUserProperties("$prepend", paramString, doubleArrayToJSONArray(paramArrayOfDouble));
    return this;
  }
  
  public Identify prepend(String paramString, float[] paramArrayOfFloat)
  {
    addToUserProperties("$prepend", paramString, floatArrayToJSONArray(paramArrayOfFloat));
    return this;
  }
  
  public Identify prepend(String paramString, int[] paramArrayOfInt)
  {
    addToUserProperties("$prepend", paramString, intArrayToJSONArray(paramArrayOfInt));
    return this;
  }
  
  public Identify prepend(String paramString, long[] paramArrayOfLong)
  {
    addToUserProperties("$prepend", paramString, longArrayToJSONArray(paramArrayOfLong));
    return this;
  }
  
  public Identify prepend(String paramString, String[] paramArrayOfString)
  {
    addToUserProperties("$prepend", paramString, stringArrayToJSONArray(paramArrayOfString));
    return this;
  }
  
  public Identify prepend(String paramString, boolean[] paramArrayOfBoolean)
  {
    addToUserProperties("$prepend", paramString, booleanArrayToJSONArray(paramArrayOfBoolean));
    return this;
  }
  
  public Identify set(String paramString, double paramDouble)
  {
    addToUserProperties("$set", paramString, Double.valueOf(paramDouble));
    return this;
  }
  
  public Identify set(String paramString, float paramFloat)
  {
    addToUserProperties("$set", paramString, Float.valueOf(paramFloat));
    return this;
  }
  
  public Identify set(String paramString, int paramInt)
  {
    addToUserProperties("$set", paramString, Integer.valueOf(paramInt));
    return this;
  }
  
  public Identify set(String paramString, long paramLong)
  {
    addToUserProperties("$set", paramString, Long.valueOf(paramLong));
    return this;
  }
  
  public Identify set(String paramString, Object paramObject)
  {
    AmplitudeLog.getLogger().w("com.amplitude.api.Identify", "This version of set is deprecated. Please use one with a different signature.");
    return this;
  }
  
  public Identify set(String paramString1, String paramString2)
  {
    addToUserProperties("$set", paramString1, paramString2);
    return this;
  }
  
  public Identify set(String paramString, JSONArray paramJSONArray)
  {
    addToUserProperties("$set", paramString, paramJSONArray);
    return this;
  }
  
  public Identify set(String paramString, JSONObject paramJSONObject)
  {
    addToUserProperties("$set", paramString, paramJSONObject);
    return this;
  }
  
  public Identify set(String paramString, boolean paramBoolean)
  {
    addToUserProperties("$set", paramString, Boolean.valueOf(paramBoolean));
    return this;
  }
  
  public Identify set(String paramString, double[] paramArrayOfDouble)
  {
    addToUserProperties("$set", paramString, doubleArrayToJSONArray(paramArrayOfDouble));
    return this;
  }
  
  public Identify set(String paramString, float[] paramArrayOfFloat)
  {
    addToUserProperties("$set", paramString, floatArrayToJSONArray(paramArrayOfFloat));
    return this;
  }
  
  public Identify set(String paramString, int[] paramArrayOfInt)
  {
    addToUserProperties("$set", paramString, intArrayToJSONArray(paramArrayOfInt));
    return this;
  }
  
  public Identify set(String paramString, long[] paramArrayOfLong)
  {
    addToUserProperties("$set", paramString, longArrayToJSONArray(paramArrayOfLong));
    return this;
  }
  
  public Identify set(String paramString, String[] paramArrayOfString)
  {
    addToUserProperties("$set", paramString, stringArrayToJSONArray(paramArrayOfString));
    return this;
  }
  
  public Identify set(String paramString, boolean[] paramArrayOfBoolean)
  {
    addToUserProperties("$set", paramString, booleanArrayToJSONArray(paramArrayOfBoolean));
    return this;
  }
  
  public Identify setOnce(String paramString, double paramDouble)
  {
    addToUserProperties("$setOnce", paramString, Double.valueOf(paramDouble));
    return this;
  }
  
  public Identify setOnce(String paramString, float paramFloat)
  {
    addToUserProperties("$setOnce", paramString, Float.valueOf(paramFloat));
    return this;
  }
  
  public Identify setOnce(String paramString, int paramInt)
  {
    addToUserProperties("$setOnce", paramString, Integer.valueOf(paramInt));
    return this;
  }
  
  public Identify setOnce(String paramString, long paramLong)
  {
    addToUserProperties("$setOnce", paramString, Long.valueOf(paramLong));
    return this;
  }
  
  public Identify setOnce(String paramString, Object paramObject)
  {
    AmplitudeLog.getLogger().w("com.amplitude.api.Identify", "This version of setOnce is deprecated. Please use one with a different signature.");
    return this;
  }
  
  public Identify setOnce(String paramString1, String paramString2)
  {
    addToUserProperties("$setOnce", paramString1, paramString2);
    return this;
  }
  
  public Identify setOnce(String paramString, JSONArray paramJSONArray)
  {
    addToUserProperties("$setOnce", paramString, paramJSONArray);
    return this;
  }
  
  public Identify setOnce(String paramString, JSONObject paramJSONObject)
  {
    addToUserProperties("$setOnce", paramString, paramJSONObject);
    return this;
  }
  
  public Identify setOnce(String paramString, boolean paramBoolean)
  {
    addToUserProperties("$setOnce", paramString, Boolean.valueOf(paramBoolean));
    return this;
  }
  
  public Identify setOnce(String paramString, double[] paramArrayOfDouble)
  {
    addToUserProperties("$setOnce", paramString, doubleArrayToJSONArray(paramArrayOfDouble));
    return this;
  }
  
  public Identify setOnce(String paramString, float[] paramArrayOfFloat)
  {
    addToUserProperties("$setOnce", paramString, floatArrayToJSONArray(paramArrayOfFloat));
    return this;
  }
  
  public Identify setOnce(String paramString, int[] paramArrayOfInt)
  {
    addToUserProperties("$setOnce", paramString, intArrayToJSONArray(paramArrayOfInt));
    return this;
  }
  
  public Identify setOnce(String paramString, long[] paramArrayOfLong)
  {
    addToUserProperties("$setOnce", paramString, longArrayToJSONArray(paramArrayOfLong));
    return this;
  }
  
  public Identify setOnce(String paramString, String[] paramArrayOfString)
  {
    addToUserProperties("$setOnce", paramString, stringArrayToJSONArray(paramArrayOfString));
    return this;
  }
  
  public Identify setOnce(String paramString, boolean[] paramArrayOfBoolean)
  {
    addToUserProperties("$setOnce", paramString, booleanArrayToJSONArray(paramArrayOfBoolean));
    return this;
  }
  
  Identify setUserProperty(String paramString, Object paramObject)
  {
    addToUserProperties("$set", paramString, paramObject);
    return this;
  }
  
  public Identify unset(String paramString)
  {
    addToUserProperties("$unset", paramString, "-");
    return this;
  }
}
