package com.facebook;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import com.facebook.internal.Utility;
import com.facebook.internal.Validate;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONArray;
import org.json.JSONObject;

public final class AccessTokenManager
{
  public static final String ACTION_CURRENT_ACCESS_TOKEN_CHANGED = "com.facebook.sdk.ACTION_CURRENT_ACCESS_TOKEN_CHANGED";
  public static final String EXTRA_NEW_ACCESS_TOKEN = "com.facebook.sdk.EXTRA_NEW_ACCESS_TOKEN";
  public static final String EXTRA_OLD_ACCESS_TOKEN = "com.facebook.sdk.EXTRA_OLD_ACCESS_TOKEN";
  private static final String ME_PERMISSIONS_GRAPH_PATH = "me/permissions";
  public static final String SHARED_PREFERENCES_NAME = "com.facebook.AccessTokenManager.SharedPreferences";
  public static final String TAG = "AccessTokenManager";
  private static final String TOKEN_EXTEND_GRAPH_PATH = "oauth/access_token";
  private static final int TOKEN_EXTEND_RETRY_SECONDS = 3600;
  private static final int TOKEN_EXTEND_THRESHOLD_SECONDS = 86400;
  private static volatile AccessTokenManager instance;
  private final AccessTokenCache accessTokenCache;
  private AccessToken currentAccessToken;
  private Date lastAttemptedTokenExtendDate = new Date(0L);
  private final LocalBroadcastManager localBroadcastManager;
  private AtomicBoolean tokenRefreshInProgress = new AtomicBoolean(false);
  
  AccessTokenManager(LocalBroadcastManager paramLocalBroadcastManager, AccessTokenCache paramAccessTokenCache)
  {
    Validate.notNull(paramLocalBroadcastManager, "localBroadcastManager");
    Validate.notNull(paramAccessTokenCache, "accessTokenCache");
    localBroadcastManager = paramLocalBroadcastManager;
    accessTokenCache = paramAccessTokenCache;
  }
  
  private static GraphRequest createExtendAccessTokenRequest(AccessToken paramAccessToken, GraphRequest.Callback paramCallback)
  {
    Bundle localBundle = new Bundle();
    localBundle.putString("grant_type", "fb_extend_sso_token");
    return new GraphRequest(paramAccessToken, "oauth/access_token", localBundle, HttpMethod.GET, paramCallback);
  }
  
  private static GraphRequest createGrantedPermissionsRequest(AccessToken paramAccessToken, GraphRequest.Callback paramCallback)
  {
    return new GraphRequest(paramAccessToken, "me/permissions", new Bundle(), HttpMethod.GET, paramCallback);
  }
  
  static AccessTokenManager getInstance()
  {
    if (instance == null) {
      try
      {
        if (instance == null) {
          instance = new AccessTokenManager(LocalBroadcastManager.getInstance(FacebookSdk.getApplicationContext()), new AccessTokenCache());
        }
      }
      finally {}
    }
    return instance;
  }
  
  private void refreshCurrentAccessTokenImpl(final AccessToken.AccessTokenRefreshCallback paramAccessTokenRefreshCallback)
  {
    final AccessToken localAccessToken = currentAccessToken;
    if (localAccessToken == null)
    {
      if (paramAccessTokenRefreshCallback != null) {
        paramAccessTokenRefreshCallback.OnTokenRefreshFailed(new FacebookException("No current access token to refresh"));
      }
      return;
    }
    if (!tokenRefreshInProgress.compareAndSet(false, true))
    {
      if (paramAccessTokenRefreshCallback != null) {
        paramAccessTokenRefreshCallback.OnTokenRefreshFailed(new FacebookException("Refresh already in progress"));
      }
      return;
    }
    lastAttemptedTokenExtendDate = new Date();
    final HashSet localHashSet1 = new HashSet();
    final HashSet localHashSet2 = new HashSet();
    final AtomicBoolean localAtomicBoolean = new AtomicBoolean(false);
    final RefreshResult localRefreshResult = new RefreshResult(null);
    GraphRequestBatch localGraphRequestBatch = new GraphRequestBatch(new GraphRequest[] { createGrantedPermissionsRequest(localAccessToken, new GraphRequest.Callback()
    {
      public void onCompleted(GraphResponse paramAnonymousGraphResponse)
      {
        paramAnonymousGraphResponse = paramAnonymousGraphResponse.getJSONObject();
        if (paramAnonymousGraphResponse == null) {
          return;
        }
        paramAnonymousGraphResponse = paramAnonymousGraphResponse.optJSONArray("data");
        if (paramAnonymousGraphResponse == null) {
          return;
        }
        localAtomicBoolean.set(true);
        int i = 0;
        while (i < paramAnonymousGraphResponse.length())
        {
          Object localObject2 = paramAnonymousGraphResponse.optJSONObject(i);
          if (localObject2 != null)
          {
            Object localObject1 = ((JSONObject)localObject2).optString("permission");
            localObject2 = ((JSONObject)localObject2).optString("status");
            if ((!Utility.isNullOrEmpty((String)localObject1)) && (!Utility.isNullOrEmpty((String)localObject2)))
            {
              localObject2 = ((String)localObject2).toLowerCase(Locale.US);
              if (((String)localObject2).equals("granted"))
              {
                localHashSet1.add(localObject1);
              }
              else if (((String)localObject2).equals("declined"))
              {
                localHashSet2.add(localObject1);
              }
              else
              {
                localObject1 = new StringBuilder();
                ((StringBuilder)localObject1).append("Unexpected status: ");
                ((StringBuilder)localObject1).append((String)localObject2);
                Log.w("AccessTokenManager", ((StringBuilder)localObject1).toString());
              }
            }
          }
          i += 1;
        }
      }
    }), createExtendAccessTokenRequest(localAccessToken, new GraphRequest.Callback()
    {
      public void onCompleted(GraphResponse paramAnonymousGraphResponse)
      {
        paramAnonymousGraphResponse = paramAnonymousGraphResponse.getJSONObject();
        if (paramAnonymousGraphResponse == null) {
          return;
        }
        localRefreshResultaccessToken = paramAnonymousGraphResponse.optString("access_token");
        localRefreshResultexpiresAt = paramAnonymousGraphResponse.optInt("expires_at");
      }
    }) });
    localGraphRequestBatch.addCallback(new GraphRequestBatch.Callback()
    {
      public void onBatchCompleted(GraphRequestBatch paramAnonymousGraphRequestBatch)
      {
        Object localObject2 = null;
        label413:
        label416:
        for (;;)
        {
          try
          {
            if ((AccessTokenManager.getInstance().getCurrentAccessToken() != null) && (AccessTokenManager.getInstance().getCurrentAccessToken().getUserId() == localAccessToken.getUserId()))
            {
              if ((!localAtomicBoolean.get()) && (localRefreshResultaccessToken == null) && (localRefreshResultexpiresAt == 0))
              {
                if (paramAccessTokenRefreshCallback != null) {
                  paramAccessTokenRefreshCallback.OnTokenRefreshFailed(new FacebookException("Failed to refresh access token"));
                }
                tokenRefreshInProgress.set(false);
                paramAnonymousGraphRequestBatch = paramAccessTokenRefreshCallback;
                return;
              }
              if (localRefreshResultaccessToken != null) {
                paramAnonymousGraphRequestBatch = localRefreshResultaccessToken;
              } else {
                paramAnonymousGraphRequestBatch = localAccessToken.getToken();
              }
              String str1 = localAccessToken.getApplicationId();
              String str2 = localAccessToken.getUserId();
              if (localAtomicBoolean.get())
              {
                localObject1 = localHashSet1;
              }
              else
              {
                localObject1 = localAccessToken.getPermissions();
                break label413;
                if (localAtomicBoolean.get())
                {
                  localSet = localHashSet2;
                  break label416;
                }
                Set localSet = localAccessToken.getDeclinedPermissions();
                break label416;
                AccessTokenSource localAccessTokenSource = localAccessToken.getSource();
                Date localDate;
                if (localRefreshResultexpiresAt != 0) {
                  localDate = new Date(localRefreshResultexpiresAt * 1000L);
                } else {
                  localDate = localAccessToken.getExpires();
                }
                localObject1 = new AccessToken(paramAnonymousGraphRequestBatch, str1, str2, (Collection)localObject1, localSet, localAccessTokenSource, localDate, new Date());
                try
                {
                  AccessTokenManager.getInstance().setCurrentAccessToken((AccessToken)localObject1);
                  tokenRefreshInProgress.set(false);
                  if ((paramAccessTokenRefreshCallback != null) && (localObject1 != null)) {
                    paramAccessTokenRefreshCallback.OnTokenRefreshed((AccessToken)localObject1);
                  }
                  return;
                }
                finally
                {
                  continue;
                }
              }
            }
            else
            {
              if (paramAccessTokenRefreshCallback != null) {
                paramAccessTokenRefreshCallback.OnTokenRefreshFailed(new FacebookException("No current access token to refresh"));
              }
              tokenRefreshInProgress.set(false);
              paramAnonymousGraphRequestBatch = paramAccessTokenRefreshCallback;
              return;
            }
          }
          finally
          {
            Object localObject1 = localObject2;
            tokenRefreshInProgress.set(false);
            if ((paramAccessTokenRefreshCallback != null) && (localObject1 != null)) {
              paramAccessTokenRefreshCallback.OnTokenRefreshed((AccessToken)localObject1);
            }
          }
        }
      }
    });
    localGraphRequestBatch.executeAsync();
  }
  
  private void sendCurrentAccessTokenChangedBroadcastIntent(AccessToken paramAccessToken1, AccessToken paramAccessToken2)
  {
    Intent localIntent = new Intent(FacebookSdk.getApplicationContext(), CurrentAccessTokenExpirationBroadcastReceiver.class);
    localIntent.setAction("com.facebook.sdk.ACTION_CURRENT_ACCESS_TOKEN_CHANGED");
    localIntent.putExtra("com.facebook.sdk.EXTRA_OLD_ACCESS_TOKEN", paramAccessToken1);
    localIntent.putExtra("com.facebook.sdk.EXTRA_NEW_ACCESS_TOKEN", paramAccessToken2);
    localBroadcastManager.sendBroadcast(localIntent);
  }
  
  private void setCurrentAccessToken(AccessToken paramAccessToken, boolean paramBoolean)
  {
    AccessToken localAccessToken = currentAccessToken;
    currentAccessToken = paramAccessToken;
    tokenRefreshInProgress.set(false);
    lastAttemptedTokenExtendDate = new Date(0L);
    if (paramBoolean) {
      if (paramAccessToken != null)
      {
        accessTokenCache.save(paramAccessToken);
      }
      else
      {
        accessTokenCache.clear();
        Utility.clearFacebookCookies(FacebookSdk.getApplicationContext());
      }
    }
    if (!Utility.areObjectsEqual(localAccessToken, paramAccessToken))
    {
      sendCurrentAccessTokenChangedBroadcastIntent(localAccessToken, paramAccessToken);
      setTokenExpirationBroadcastAlarm();
    }
  }
  
  private void setTokenExpirationBroadcastAlarm()
  {
    Object localObject = FacebookSdk.getApplicationContext();
    AccessToken localAccessToken = AccessToken.getCurrentAccessToken();
    AlarmManager localAlarmManager = (AlarmManager)((Context)localObject).getSystemService("alarm");
    if ((AccessToken.isCurrentAccessTokenActive()) && (localAccessToken.getExpires() != null))
    {
      if (localAlarmManager == null) {
        return;
      }
      Intent localIntent = new Intent((Context)localObject, CurrentAccessTokenExpirationBroadcastReceiver.class);
      localIntent.setAction("com.facebook.sdk.ACTION_CURRENT_ACCESS_TOKEN_CHANGED");
      localObject = PendingIntent.getBroadcast((Context)localObject, 0, localIntent, 0);
      localAlarmManager.set(1, localAccessToken.getExpires().getTime(), (PendingIntent)localObject);
      return;
    }
  }
  
  private boolean shouldExtendAccessToken()
  {
    Object localObject = currentAccessToken;
    boolean bool2 = false;
    if (localObject == null) {
      return false;
    }
    localObject = Long.valueOf(new Date().getTime());
    boolean bool1 = bool2;
    if (currentAccessToken.getSource().canExtendToken())
    {
      bool1 = bool2;
      if (((Long)localObject).longValue() - lastAttemptedTokenExtendDate.getTime() > 3600000L)
      {
        bool1 = bool2;
        if (((Long)localObject).longValue() - currentAccessToken.getLastRefresh().getTime() > 86400000L) {
          bool1 = true;
        }
      }
    }
    return bool1;
  }
  
  void currentAccessTokenChanged()
  {
    sendCurrentAccessTokenChangedBroadcastIntent(currentAccessToken, currentAccessToken);
  }
  
  void extendAccessTokenIfNeeded()
  {
    if (!shouldExtendAccessToken()) {
      return;
    }
    refreshCurrentAccessToken(null);
  }
  
  AccessToken getCurrentAccessToken()
  {
    return currentAccessToken;
  }
  
  boolean loadCurrentAccessToken()
  {
    AccessToken localAccessToken = accessTokenCache.load();
    if (localAccessToken != null)
    {
      setCurrentAccessToken(localAccessToken, false);
      return true;
    }
    return false;
  }
  
  void refreshCurrentAccessToken(final AccessToken.AccessTokenRefreshCallback paramAccessTokenRefreshCallback)
  {
    if (Looper.getMainLooper().equals(Looper.myLooper()))
    {
      refreshCurrentAccessTokenImpl(paramAccessTokenRefreshCallback);
      return;
    }
    new Handler(Looper.getMainLooper()).post(new Runnable()
    {
      public void run()
      {
        AccessTokenManager.this.refreshCurrentAccessTokenImpl(paramAccessTokenRefreshCallback);
      }
    });
  }
  
  void setCurrentAccessToken(AccessToken paramAccessToken)
  {
    setCurrentAccessToken(paramAccessToken, true);
  }
  
  private static class RefreshResult
  {
    public String accessToken;
    public int expiresAt;
    
    private RefreshResult() {}
  }
}
