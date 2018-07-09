package com.facebook.appevents;

import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.facebook.FacebookException;
import com.facebook.LoggingBehavior;
import com.facebook.internal.Logger;
import com.facebook.internal.Utility;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

class AppEvent
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  private static final HashSet<String> validatedIdentifiers = new HashSet();
  private final String checksum;
  private final boolean isImplicit;
  private final JSONObject jsonObject;
  private final String name;
  
  public AppEvent(String paramString1, String paramString2, Double paramDouble, Bundle paramBundle, boolean paramBoolean, @Nullable UUID paramUUID)
    throws JSONException, FacebookException
  {
    jsonObject = getJSONObjectForAppEvent(paramString1, paramString2, paramDouble, paramBundle, paramBoolean, paramUUID);
    isImplicit = paramBoolean;
    name = paramString2;
    checksum = calculateChecksum();
  }
  
  private AppEvent(String paramString1, boolean paramBoolean, String paramString2)
    throws JSONException
  {
    jsonObject = new JSONObject(paramString1);
    isImplicit = paramBoolean;
    name = jsonObject.optString("_eventName");
    checksum = paramString2;
  }
  
  private static String bytesToHex(byte[] paramArrayOfByte)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    int j = paramArrayOfByte.length;
    int i = 0;
    while (i < j)
    {
      localStringBuffer.append(String.format("%02x", new Object[] { Byte.valueOf(paramArrayOfByte[i]) }));
      i += 1;
    }
    return localStringBuffer.toString();
  }
  
  private String calculateChecksum()
  {
    if (Build.VERSION.SDK_INT > 19) {
      return md5Checksum(jsonObject.toString());
    }
    Object localObject2 = new ArrayList();
    Object localObject1 = jsonObject.keys();
    while (((Iterator)localObject1).hasNext()) {
      ((ArrayList)localObject2).add(((Iterator)localObject1).next());
    }
    Collections.sort((List)localObject2);
    localObject1 = new StringBuilder();
    localObject2 = ((ArrayList)localObject2).iterator();
    while (((Iterator)localObject2).hasNext())
    {
      String str = (String)((Iterator)localObject2).next();
      ((StringBuilder)localObject1).append(str);
      ((StringBuilder)localObject1).append(" = ");
      ((StringBuilder)localObject1).append(jsonObject.optString(str));
      ((StringBuilder)localObject1).append('\n');
    }
    return md5Checksum(((StringBuilder)localObject1).toString());
  }
  
  private static JSONObject getJSONObjectForAppEvent(String paramString1, String paramString2, Double paramDouble, Bundle paramBundle, boolean paramBoolean, @Nullable UUID paramUUID)
    throws FacebookException, JSONException
  {
    validateIdentifier(paramString2);
    JSONObject localJSONObject = new JSONObject();
    localJSONObject.put("_eventName", paramString2);
    localJSONObject.put("_eventName_md5", md5Checksum(paramString2));
    localJSONObject.put("_logTime", System.currentTimeMillis() / 1000L);
    localJSONObject.put("_ui", paramString1);
    if (paramUUID != null) {
      localJSONObject.put("_session_id", paramUUID);
    }
    if (paramDouble != null) {
      localJSONObject.put("_valueToSum", paramDouble.doubleValue());
    }
    if (paramBoolean) {
      localJSONObject.put("_implicitlyLogged", "1");
    }
    if (paramBundle != null)
    {
      paramString1 = paramBundle.keySet().iterator();
      while (paramString1.hasNext())
      {
        paramString2 = (String)paramString1.next();
        validateIdentifier(paramString2);
        paramDouble = paramBundle.get(paramString2);
        if ((!(paramDouble instanceof String)) && (!(paramDouble instanceof Number))) {
          throw new FacebookException(String.format("Parameter value '%s' for key '%s' should be a string or a numeric type.", new Object[] { paramDouble, paramString2 }));
        }
        localJSONObject.put(paramString2, paramDouble.toString());
      }
    }
    if (!paramBoolean) {
      Logger.log(LoggingBehavior.APP_EVENTS, "AppEvents", "Created app event '%s'", new Object[] { localJSONObject.toString() });
    }
    return localJSONObject;
  }
  
  private static String md5Checksum(String paramString)
  {
    try
    {
      MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
      paramString = paramString.getBytes("UTF-8");
      localMessageDigest.update(paramString, 0, paramString.length);
      paramString = bytesToHex(localMessageDigest.digest());
      return paramString;
    }
    catch (UnsupportedEncodingException paramString)
    {
      Utility.logd("Failed to generate checksum: ", paramString);
      return "1";
    }
    catch (NoSuchAlgorithmException paramString)
    {
      Utility.logd("Failed to generate checksum: ", paramString);
    }
    return "0";
  }
  
  private static void validateIdentifier(String paramString)
    throws FacebookException
  {
    if ((paramString != null) && (paramString.length() != 0) && (paramString.length() <= 40)) {
      synchronized (validatedIdentifiers)
      {
        boolean bool = validatedIdentifiers.contains(paramString);
        if (!bool)
        {
          if (paramString.matches("^[0-9a-zA-Z_]+[0-9a-zA-Z _-]*$")) {
            synchronized (validatedIdentifiers)
            {
              validatedIdentifiers.add(paramString);
              return;
            }
          }
          throw new FacebookException(String.format("Skipping event named '%s' due to illegal name - must be under 40 chars and alphanumeric, _, - or space, and not start with a space or hyphen.", new Object[] { paramString }));
        }
        return;
      }
    }
    ??? = paramString;
    if (paramString == null) {
      ??? = "<None Provided>";
    }
    throw new FacebookException(String.format(Locale.ROOT, "Identifier '%s' must be less than %d characters", new Object[] { ???, Integer.valueOf(40) }));
  }
  
  private Object writeReplace()
  {
    return new SerializationProxyV2(jsonObject.toString(), isImplicit, checksum, null);
  }
  
  public boolean getIsImplicit()
  {
    return isImplicit;
  }
  
  public JSONObject getJSONObject()
  {
    return jsonObject;
  }
  
  public String getName()
  {
    return name;
  }
  
  public boolean isChecksumValid()
  {
    if (checksum == null) {
      return true;
    }
    return calculateChecksum().equals(checksum);
  }
  
  public String toString()
  {
    return String.format("\"%s\", implicit: %b, json: %s", new Object[] { jsonObject.optString("_eventName"), Boolean.valueOf(isImplicit), jsonObject.toString() });
  }
  
  static class SerializationProxyV1
    implements Serializable
  {
    private static final long serialVersionUID = -2488473066578201069L;
    private final boolean isImplicit;
    private final String jsonString;
    
    private SerializationProxyV1(String paramString, boolean paramBoolean)
    {
      jsonString = paramString;
      isImplicit = paramBoolean;
    }
    
    private Object readResolve()
      throws JSONException
    {
      return new AppEvent(jsonString, isImplicit, null, null);
    }
  }
  
  static class SerializationProxyV2
    implements Serializable
  {
    private static final long serialVersionUID = 20160803001L;
    private final String checksum;
    private final boolean isImplicit;
    private final String jsonString;
    
    private SerializationProxyV2(String paramString1, boolean paramBoolean, String paramString2)
    {
      jsonString = paramString1;
      isImplicit = paramBoolean;
      checksum = paramString2;
    }
    
    private Object readResolve()
      throws JSONException
    {
      return new AppEvent(jsonString, isImplicit, checksum, null);
    }
  }
}
