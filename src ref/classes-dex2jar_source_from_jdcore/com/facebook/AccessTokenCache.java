package com.facebook;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import com.facebook.internal.Validate;
import org.json.JSONException;
import org.json.JSONObject;

class AccessTokenCache
{
  static final String CACHED_ACCESS_TOKEN_KEY = "com.facebook.AccessTokenManager.CachedAccessToken";
  private final SharedPreferences sharedPreferences;
  private LegacyTokenHelper tokenCachingStrategy;
  private final SharedPreferencesTokenCachingStrategyFactory tokenCachingStrategyFactory;
  
  public AccessTokenCache()
  {
    this(FacebookSdk.getApplicationContext().getSharedPreferences("com.facebook.AccessTokenManager.SharedPreferences", 0), new SharedPreferencesTokenCachingStrategyFactory());
  }
  
  AccessTokenCache(SharedPreferences paramSharedPreferences, SharedPreferencesTokenCachingStrategyFactory paramSharedPreferencesTokenCachingStrategyFactory)
  {
    sharedPreferences = paramSharedPreferences;
    tokenCachingStrategyFactory = paramSharedPreferencesTokenCachingStrategyFactory;
  }
  
  private AccessToken getCachedAccessToken()
  {
    Object localObject = sharedPreferences.getString("com.facebook.AccessTokenManager.CachedAccessToken", null);
    if (localObject != null) {}
    try
    {
      localObject = AccessToken.createFromJSONObject(new JSONObject((String)localObject));
      return localObject;
    }
    catch (JSONException localJSONException) {}
    return null;
    return null;
  }
  
  private AccessToken getLegacyAccessToken()
  {
    Bundle localBundle = getTokenCachingStrategy().load();
    if ((localBundle != null) && (LegacyTokenHelper.hasTokenInformation(localBundle))) {
      return AccessToken.createFromLegacyCache(localBundle);
    }
    return null;
  }
  
  private LegacyTokenHelper getTokenCachingStrategy()
  {
    if (tokenCachingStrategy == null) {
      try
      {
        if (tokenCachingStrategy == null) {
          tokenCachingStrategy = tokenCachingStrategyFactory.create();
        }
      }
      finally {}
    }
    return tokenCachingStrategy;
  }
  
  private boolean hasCachedAccessToken()
  {
    return sharedPreferences.contains("com.facebook.AccessTokenManager.CachedAccessToken");
  }
  
  private boolean shouldCheckLegacyToken()
  {
    return FacebookSdk.isLegacyTokenUpgradeSupported();
  }
  
  public void clear()
  {
    sharedPreferences.edit().remove("com.facebook.AccessTokenManager.CachedAccessToken").apply();
    if (shouldCheckLegacyToken()) {
      getTokenCachingStrategy().clear();
    }
  }
  
  public AccessToken load()
  {
    if (hasCachedAccessToken()) {
      return getCachedAccessToken();
    }
    AccessToken localAccessToken1;
    if (shouldCheckLegacyToken())
    {
      AccessToken localAccessToken2 = getLegacyAccessToken();
      localAccessToken1 = localAccessToken2;
      if (localAccessToken2 != null)
      {
        save(localAccessToken2);
        getTokenCachingStrategy().clear();
        return localAccessToken2;
      }
    }
    else
    {
      localAccessToken1 = null;
    }
    return localAccessToken1;
  }
  
  public void save(AccessToken paramAccessToken)
  {
    Validate.notNull(paramAccessToken, "accessToken");
    try
    {
      paramAccessToken = paramAccessToken.toJSONObject();
      sharedPreferences.edit().putString("com.facebook.AccessTokenManager.CachedAccessToken", paramAccessToken.toString()).apply();
      return;
    }
    catch (JSONException paramAccessToken) {}
  }
  
  static class SharedPreferencesTokenCachingStrategyFactory
  {
    SharedPreferencesTokenCachingStrategyFactory() {}
    
    public LegacyTokenHelper create()
    {
      return new LegacyTokenHelper(FacebookSdk.getApplicationContext());
    }
  }
}
