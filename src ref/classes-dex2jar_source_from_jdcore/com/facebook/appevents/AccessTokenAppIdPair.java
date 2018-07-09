package com.facebook.appevents;

import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.internal.Utility;
import java.io.Serializable;

class AccessTokenAppIdPair
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  private final String accessTokenString;
  private final String applicationId;
  
  public AccessTokenAppIdPair(AccessToken paramAccessToken)
  {
    this(paramAccessToken.getToken(), FacebookSdk.getApplicationId());
  }
  
  public AccessTokenAppIdPair(String paramString1, String paramString2)
  {
    String str = paramString1;
    if (Utility.isNullOrEmpty(paramString1)) {
      str = null;
    }
    accessTokenString = str;
    applicationId = paramString2;
  }
  
  private Object writeReplace()
  {
    return new SerializationProxyV1(accessTokenString, applicationId, null);
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool1 = paramObject instanceof AccessTokenAppIdPair;
    boolean bool2 = false;
    if (!bool1) {
      return false;
    }
    paramObject = (AccessTokenAppIdPair)paramObject;
    bool1 = bool2;
    if (Utility.areObjectsEqual(accessTokenString, accessTokenString))
    {
      bool1 = bool2;
      if (Utility.areObjectsEqual(applicationId, applicationId)) {
        bool1 = true;
      }
    }
    return bool1;
  }
  
  public String getAccessTokenString()
  {
    return accessTokenString;
  }
  
  public String getApplicationId()
  {
    return applicationId;
  }
  
  public int hashCode()
  {
    String str = accessTokenString;
    int j = 0;
    int i;
    if (str == null) {
      i = 0;
    } else {
      i = accessTokenString.hashCode();
    }
    if (applicationId != null) {
      j = applicationId.hashCode();
    }
    return i ^ j;
  }
  
  static class SerializationProxyV1
    implements Serializable
  {
    private static final long serialVersionUID = -2488473066578201069L;
    private final String accessTokenString;
    private final String appId;
    
    private SerializationProxyV1(String paramString1, String paramString2)
    {
      accessTokenString = paramString1;
      appId = paramString2;
    }
    
    private Object readResolve()
    {
      return new AccessTokenAppIdPair(accessTokenString, appId);
    }
  }
}
