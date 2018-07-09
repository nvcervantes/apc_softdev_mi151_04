package com.facebook.appevents;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import bolts.AppLinks;
import com.facebook.AccessToken;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphRequest.Callback;
import com.facebook.HttpMethod;
import com.facebook.LoggingBehavior;
import com.facebook.appevents.internal.ActivityLifecycleTracker;
import com.facebook.appevents.internal.AutomaticAnalyticsLogger;
import com.facebook.internal.AttributionIdentifiers;
import com.facebook.internal.BundleJSONConverter;
import com.facebook.internal.FetchedAppSettingsManager;
import com.facebook.internal.Logger;
import com.facebook.internal.Utility;
import com.facebook.internal.Validate;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AppEventsLogger
{
  public static final String ACTION_APP_EVENTS_FLUSHED = "com.facebook.sdk.APP_EVENTS_FLUSHED";
  public static final String APP_EVENTS_EXTRA_FLUSH_RESULT = "com.facebook.sdk.APP_EVENTS_FLUSH_RESULT";
  public static final String APP_EVENTS_EXTRA_NUM_EVENTS_FLUSHED = "com.facebook.sdk.APP_EVENTS_NUM_EVENTS_FLUSHED";
  private static final String APP_EVENT_NAME_PUSH_OPENED = "fb_mobile_push_opened";
  public static final String APP_EVENT_PREFERENCES = "com.facebook.sdk.appEventPreferences";
  private static final String APP_EVENT_PUSH_PARAMETER_ACTION = "fb_push_action";
  private static final String APP_EVENT_PUSH_PARAMETER_CAMPAIGN = "fb_push_campaign";
  private static final int APP_SUPPORTS_ATTRIBUTION_ID_RECHECK_PERIOD_IN_SECONDS = 86400;
  private static final int FLUSH_APP_SESSION_INFO_IN_SECONDS = 30;
  private static final String PUSH_PAYLOAD_CAMPAIGN_KEY = "campaign";
  private static final String PUSH_PAYLOAD_KEY = "fb_push_payload";
  private static final String SOURCE_APPLICATION_HAS_BEEN_SET_BY_THIS_INTENT = "_fbSourceApplicationHasBeenSet";
  private static final String TAG = AppEventsLogger.class.getCanonicalName();
  private static String anonymousAppDeviceGUID;
  private static ScheduledThreadPoolExecutor backgroundExecutor;
  private static FlushBehavior flushBehavior = FlushBehavior.AUTO;
  private static boolean isActivateAppEventRequested;
  private static boolean isOpenedByAppLink;
  private static String pushNotificationsRegistrationId;
  private static String sourceApplication;
  private static Object staticLock = new Object();
  private final AccessTokenAppIdPair accessTokenAppId;
  private final String contextName;
  
  private AppEventsLogger(Context paramContext, String paramString, AccessToken paramAccessToken)
  {
    this(Utility.getActivityName(paramContext), paramString, paramAccessToken);
  }
  
  protected AppEventsLogger(String paramString1, String paramString2, AccessToken paramAccessToken)
  {
    Validate.sdkInitialized();
    contextName = paramString1;
    paramString1 = paramAccessToken;
    if (paramAccessToken == null) {
      paramString1 = AccessToken.getCurrentAccessToken();
    }
    if ((AccessToken.isCurrentAccessTokenActive()) && ((paramString2 == null) || (paramString2.equals(paramString1.getApplicationId()))))
    {
      accessTokenAppId = new AccessTokenAppIdPair(paramString1);
    }
    else
    {
      paramString1 = paramString2;
      if (paramString2 == null) {
        paramString1 = Utility.getMetadataApplicationId(FacebookSdk.getApplicationContext());
      }
      accessTokenAppId = new AccessTokenAppIdPair(null, paramString1);
    }
    initializeTimersIfNeeded();
  }
  
  public static void activateApp(Application paramApplication)
  {
    activateApp(paramApplication, null);
  }
  
  public static void activateApp(Application paramApplication, String paramString)
  {
    if (!FacebookSdk.isInitialized()) {
      throw new FacebookException("The Facebook sdk must be initialized before calling activateApp");
    }
    AnalyticsUserIDStore.initStore();
    String str = paramString;
    if (paramString == null) {
      str = FacebookSdk.getApplicationId();
    }
    FacebookSdk.publishInstallAsync(paramApplication, str);
    ActivityLifecycleTracker.startTracking(paramApplication, str);
  }
  
  @Deprecated
  public static void activateApp(Context paramContext)
  {
    if (ActivityLifecycleTracker.isTracking())
    {
      Log.w(TAG, "activateApp events are being logged automatically. There's no need to call activateApp explicitly, this is safe to remove.");
      return;
    }
    FacebookSdk.sdkInitialize(paramContext);
    activateApp(paramContext, Utility.getMetadataApplicationId(paramContext));
  }
  
  @Deprecated
  public static void activateApp(Context paramContext, String paramString)
  {
    if (ActivityLifecycleTracker.isTracking())
    {
      Log.w(TAG, "activateApp events are being logged automatically. There's no need to call activateApp explicitly, this is safe to remove.");
      return;
    }
    if ((paramContext != null) && (paramString != null))
    {
      AnalyticsUserIDStore.initStore();
      if ((paramContext instanceof Activity))
      {
        setSourceApplication((Activity)paramContext);
      }
      else
      {
        resetSourceApplication();
        Log.d(AppEventsLogger.class.getName(), "To set source application the context of activateApp must be an instance of Activity");
      }
      FacebookSdk.publishInstallAsync(paramContext, paramString);
      paramContext = new AppEventsLogger(paramContext, paramString, null);
      final long l = System.currentTimeMillis();
      paramString = getSourceApplication();
      backgroundExecutor.execute(new Runnable()
      {
        public void run()
        {
          val$logger.logAppSessionResumeEvent(l, val$sourceApplicationInfo);
        }
      });
      return;
    }
    throw new IllegalArgumentException("Both context and applicationId must be non-null");
  }
  
  public static void clearUserID()
  {
    AnalyticsUserIDStore.setUserID(null);
  }
  
  @Deprecated
  public static void deactivateApp(Context paramContext)
  {
    if (ActivityLifecycleTracker.isTracking())
    {
      Log.w(TAG, "deactivateApp events are being logged automatically. There's no need to call deactivateApp, this is safe to remove.");
      return;
    }
    deactivateApp(paramContext, Utility.getMetadataApplicationId(paramContext));
  }
  
  @Deprecated
  public static void deactivateApp(Context paramContext, String paramString)
  {
    if (ActivityLifecycleTracker.isTracking())
    {
      Log.w(TAG, "deactivateApp events are being logged automatically. There's no need to call deactivateApp, this is safe to remove.");
      return;
    }
    if ((paramContext != null) && (paramString != null))
    {
      resetSourceApplication();
      paramContext = new AppEventsLogger(paramContext, paramString, null);
      final long l = System.currentTimeMillis();
      backgroundExecutor.execute(new Runnable()
      {
        public void run()
        {
          val$logger.logAppSessionSuspendEvent(l);
        }
      });
      return;
    }
    throw new IllegalArgumentException("Both context and applicationId must be non-null");
  }
  
  static void eagerFlush()
  {
    if (getFlushBehavior() != FlushBehavior.EXPLICIT_ONLY) {
      AppEventQueue.flush(FlushReason.EAGER_FLUSHING_EVENT);
    }
  }
  
  static Executor getAnalyticsExecutor()
  {
    if (backgroundExecutor == null) {
      initializeTimersIfNeeded();
    }
    return backgroundExecutor;
  }
  
  public static String getAnonymousAppDeviceGUID(Context paramContext)
  {
    if (anonymousAppDeviceGUID == null) {
      synchronized (staticLock)
      {
        if (anonymousAppDeviceGUID == null)
        {
          anonymousAppDeviceGUID = paramContext.getSharedPreferences("com.facebook.sdk.appEventPreferences", 0).getString("anonymousAppDeviceGUID", null);
          if (anonymousAppDeviceGUID == null)
          {
            StringBuilder localStringBuilder = new StringBuilder();
            localStringBuilder.append("XZ");
            localStringBuilder.append(UUID.randomUUID().toString());
            anonymousAppDeviceGUID = localStringBuilder.toString();
            paramContext.getSharedPreferences("com.facebook.sdk.appEventPreferences", 0).edit().putString("anonymousAppDeviceGUID", anonymousAppDeviceGUID).apply();
          }
        }
      }
    }
    return anonymousAppDeviceGUID;
  }
  
  public static FlushBehavior getFlushBehavior()
  {
    synchronized (staticLock)
    {
      FlushBehavior localFlushBehavior = flushBehavior;
      return localFlushBehavior;
    }
  }
  
  static String getPushNotificationsRegistrationId()
  {
    synchronized (staticLock)
    {
      String str = pushNotificationsRegistrationId;
      return str;
    }
  }
  
  static String getSourceApplication()
  {
    String str = "Unclassified";
    if (isOpenedByAppLink) {
      str = "Applink";
    }
    if (sourceApplication != null)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(str);
      localStringBuilder.append("(");
      localStringBuilder.append(sourceApplication);
      localStringBuilder.append(")");
      return localStringBuilder.toString();
    }
    return str;
  }
  
  public static String getUserID()
  {
    return AnalyticsUserIDStore.getUserID();
  }
  
  public static void initializeLib(Context paramContext, String paramString)
  {
    paramContext = new AppEventsLogger(paramContext, paramString, null);
    backgroundExecutor.execute(new Runnable()
    {
      public void run()
      {
        Bundle localBundle = new Bundle();
        try
        {
          Class.forName("com.facebook.core.Core");
          localBundle.putInt("core_lib_included", 1);
        }
        catch (ClassNotFoundException localClassNotFoundException6)
        {
          try
          {
            Class.forName("com.facebook.login.Login");
            localBundle.putInt("login_lib_included", 1);
          }
          catch (ClassNotFoundException localClassNotFoundException6)
          {
            try
            {
              Class.forName("com.facebook.share.Share");
              localBundle.putInt("share_lib_included", 1);
            }
            catch (ClassNotFoundException localClassNotFoundException6)
            {
              try
              {
                Class.forName("com.facebook.places.Places");
                localBundle.putInt("places_lib_included", 1);
              }
              catch (ClassNotFoundException localClassNotFoundException6)
              {
                try
                {
                  Class.forName("com.facebook.messenger.Messenger");
                  localBundle.putInt("messenger_lib_included", 1);
                }
                catch (ClassNotFoundException localClassNotFoundException6)
                {
                  try
                  {
                    Class.forName("com.facebook.applinks.AppLinks");
                    localBundle.putInt("applinks_lib_included", 1);
                  }
                  catch (ClassNotFoundException localClassNotFoundException6)
                  {
                    try
                    {
                      for (;;)
                      {
                        Class.forName("com.facebook.all.All");
                        localBundle.putInt("all_lib_included", 1);
                        val$logger.logSdkEvent("fb_sdk_initialize", null, localBundle);
                        return;
                        localClassNotFoundException1 = localClassNotFoundException1;
                        continue;
                        localClassNotFoundException2 = localClassNotFoundException2;
                        continue;
                        localClassNotFoundException3 = localClassNotFoundException3;
                        continue;
                        localClassNotFoundException4 = localClassNotFoundException4;
                        continue;
                        localClassNotFoundException5 = localClassNotFoundException5;
                        continue;
                        localClassNotFoundException6 = localClassNotFoundException6;
                      }
                    }
                    catch (ClassNotFoundException localClassNotFoundException7)
                    {
                      for (;;) {}
                    }
                  }
                }
              }
            }
          }
        }
      }
    });
  }
  
  private static void initializeTimersIfNeeded()
  {
    synchronized (staticLock)
    {
      if (backgroundExecutor != null) {
        return;
      }
      backgroundExecutor = new ScheduledThreadPoolExecutor(1);
      ??? = new Runnable()
      {
        public void run()
        {
          Object localObject = new HashSet();
          Iterator localIterator = AppEventQueue.getKeySet().iterator();
          while (localIterator.hasNext()) {
            ((Set)localObject).add(((AccessTokenAppIdPair)localIterator.next()).getApplicationId());
          }
          localObject = ((Set)localObject).iterator();
          while (((Iterator)localObject).hasNext()) {
            FetchedAppSettingsManager.queryAppSettings((String)((Iterator)localObject).next(), true);
          }
        }
      };
      backgroundExecutor.scheduleAtFixedRate((Runnable)???, 0L, 86400L, TimeUnit.SECONDS);
      return;
    }
  }
  
  private void logAppSessionResumeEvent(long paramLong, String paramString)
  {
    PersistedAppSessionInfo.onResume(FacebookSdk.getApplicationContext(), accessTokenAppId, this, paramLong, paramString);
  }
  
  private void logAppSessionSuspendEvent(long paramLong)
  {
    PersistedAppSessionInfo.onSuspend(FacebookSdk.getApplicationContext(), accessTokenAppId, this, paramLong);
  }
  
  private static void logEvent(Context paramContext, AppEvent paramAppEvent, AccessTokenAppIdPair paramAccessTokenAppIdPair)
  {
    AppEventQueue.add(paramAccessTokenAppIdPair, paramAppEvent);
    if ((!paramAppEvent.getIsImplicit()) && (!isActivateAppEventRequested))
    {
      if (paramAppEvent.getName() == "fb_mobile_activate_app")
      {
        isActivateAppEventRequested = true;
        return;
      }
      Logger.log(LoggingBehavior.APP_EVENTS, "AppEvents", "Warning: Please call AppEventsLogger.activateApp(...)from the long-lived activity's onResume() methodbefore logging other app events.");
    }
  }
  
  private void logEvent(String paramString, Double paramDouble, Bundle paramBundle, boolean paramBoolean, @Nullable UUID paramUUID)
  {
    try
    {
      paramString = new AppEvent(contextName, paramString, paramDouble, paramBundle, paramBoolean, paramUUID);
      logEvent(FacebookSdk.getApplicationContext(), paramString, accessTokenAppId);
      return;
    }
    catch (FacebookException paramString)
    {
      Logger.log(LoggingBehavior.APP_EVENTS, "AppEvents", "Invalid app event: %s", new Object[] { paramString.toString() });
      return;
    }
    catch (JSONException paramString)
    {
      Logger.log(LoggingBehavior.APP_EVENTS, "AppEvents", "JSON encoding for app event failed: '%s'", new Object[] { paramString.toString() });
    }
  }
  
  private void logPurchase(BigDecimal paramBigDecimal, Currency paramCurrency, Bundle paramBundle, boolean paramBoolean)
  {
    if (paramBigDecimal == null)
    {
      notifyDeveloperError("purchaseAmount cannot be null");
      return;
    }
    if (paramCurrency == null)
    {
      notifyDeveloperError("currency cannot be null");
      return;
    }
    Bundle localBundle = paramBundle;
    if (paramBundle == null) {
      localBundle = new Bundle();
    }
    localBundle.putString("fb_currency", paramCurrency.getCurrencyCode());
    logEvent("fb_mobile_purchase", Double.valueOf(paramBigDecimal.doubleValue()), localBundle, paramBoolean, ActivityLifecycleTracker.getCurrentSessionGuid());
    eagerFlush();
  }
  
  public static AppEventsLogger newLogger(Context paramContext)
  {
    return new AppEventsLogger(paramContext, null, null);
  }
  
  public static AppEventsLogger newLogger(Context paramContext, AccessToken paramAccessToken)
  {
    return new AppEventsLogger(paramContext, null, paramAccessToken);
  }
  
  public static AppEventsLogger newLogger(Context paramContext, String paramString)
  {
    return new AppEventsLogger(paramContext, paramString, null);
  }
  
  public static AppEventsLogger newLogger(Context paramContext, String paramString, AccessToken paramAccessToken)
  {
    return new AppEventsLogger(paramContext, paramString, paramAccessToken);
  }
  
  private static void notifyDeveloperError(String paramString)
  {
    Logger.log(LoggingBehavior.DEVELOPER_ERRORS, "AppEvents", paramString);
  }
  
  public static void onContextStop() {}
  
  static void resetSourceApplication()
  {
    sourceApplication = null;
    isOpenedByAppLink = false;
  }
  
  public static void setFlushBehavior(FlushBehavior paramFlushBehavior)
  {
    synchronized (staticLock)
    {
      flushBehavior = paramFlushBehavior;
      return;
    }
  }
  
  public static void setPushNotificationsRegistrationId(String paramString)
  {
    synchronized (staticLock)
    {
      if (!Utility.stringsEqualOrEmpty(pushNotificationsRegistrationId, paramString))
      {
        pushNotificationsRegistrationId = paramString;
        paramString = newLogger(FacebookSdk.getApplicationContext());
        paramString.logEvent("fb_mobile_obtain_push_token");
        if (getFlushBehavior() != FlushBehavior.EXPLICIT_ONLY) {
          paramString.flush();
        }
      }
      return;
    }
  }
  
  private static void setSourceApplication(Activity paramActivity)
  {
    Object localObject = paramActivity.getCallingActivity();
    if (localObject != null)
    {
      localObject = ((ComponentName)localObject).getPackageName();
      if (((String)localObject).equals(paramActivity.getPackageName()))
      {
        resetSourceApplication();
        return;
      }
      sourceApplication = (String)localObject;
    }
    paramActivity = paramActivity.getIntent();
    if ((paramActivity != null) && (!paramActivity.getBooleanExtra("_fbSourceApplicationHasBeenSet", false)))
    {
      localObject = AppLinks.getAppLinkData(paramActivity);
      if (localObject == null)
      {
        resetSourceApplication();
        return;
      }
      isOpenedByAppLink = true;
      localObject = ((Bundle)localObject).getBundle("referer_app_link");
      if (localObject == null)
      {
        sourceApplication = null;
        return;
      }
      sourceApplication = ((Bundle)localObject).getString("package");
      paramActivity.putExtra("_fbSourceApplicationHasBeenSet", true);
      return;
    }
    resetSourceApplication();
  }
  
  static void setSourceApplication(String paramString, boolean paramBoolean)
  {
    sourceApplication = paramString;
    isOpenedByAppLink = paramBoolean;
  }
  
  public static void setUserID(String paramString)
  {
    AnalyticsUserIDStore.setUserID(paramString);
  }
  
  public static void updateUserProperties(Bundle paramBundle, GraphRequest.Callback paramCallback)
  {
    updateUserProperties(paramBundle, FacebookSdk.getApplicationId(), paramCallback);
  }
  
  public static void updateUserProperties(Bundle paramBundle, final String paramString, final GraphRequest.Callback paramCallback)
  {
    getAnalyticsExecutor().execute(new Runnable()
    {
      public void run()
      {
        Object localObject2 = AppEventsLogger.getUserID();
        if ((localObject2 != null) && (!((String)localObject2).isEmpty()))
        {
          Object localObject1 = new Bundle();
          ((Bundle)localObject1).putString("user_unique_id", (String)localObject2);
          ((Bundle)localObject1).putBundle("custom_data", val$parameters);
          localObject2 = AttributionIdentifiers.getAttributionIdentifiers(FacebookSdk.getApplicationContext());
          if ((localObject2 != null) && (((AttributionIdentifiers)localObject2).getAndroidAdvertiserId() != null)) {
            ((Bundle)localObject1).putString("advertiser_id", ((AttributionIdentifiers)localObject2).getAndroidAdvertiserId());
          }
          localObject2 = new Bundle();
          try
          {
            localObject1 = BundleJSONConverter.convertToJSON((Bundle)localObject1);
            JSONArray localJSONArray = new JSONArray();
            localJSONArray.put(localObject1);
            ((Bundle)localObject2).putString("data", localJSONArray.toString());
            localObject1 = new GraphRequest(AccessToken.getCurrentAccessToken(), String.format(Locale.US, "%s/user_properties", new Object[] { paramString }), (Bundle)localObject2, HttpMethod.POST, paramCallback);
            ((GraphRequest)localObject1).setSkipClientToken(true);
            ((GraphRequest)localObject1).executeAsync();
            return;
          }
          catch (JSONException localJSONException)
          {
            throw new FacebookException("Failed to construct request", localJSONException);
          }
        }
        Logger.log(LoggingBehavior.APP_EVENTS, AppEventsLogger.TAG, "AppEventsLogger userID cannot be null or empty");
      }
    });
  }
  
  public void flush()
  {
    AppEventQueue.flush(FlushReason.EXPLICIT);
  }
  
  public String getApplicationId()
  {
    return accessTokenAppId.getApplicationId();
  }
  
  public boolean isValidForAccessToken(AccessToken paramAccessToken)
  {
    paramAccessToken = new AccessTokenAppIdPair(paramAccessToken);
    return accessTokenAppId.equals(paramAccessToken);
  }
  
  public void logEvent(String paramString)
  {
    logEvent(paramString, null);
  }
  
  public void logEvent(String paramString, double paramDouble)
  {
    logEvent(paramString, paramDouble, null);
  }
  
  public void logEvent(String paramString, double paramDouble, Bundle paramBundle)
  {
    logEvent(paramString, Double.valueOf(paramDouble), paramBundle, false, ActivityLifecycleTracker.getCurrentSessionGuid());
  }
  
  public void logEvent(String paramString, Bundle paramBundle)
  {
    logEvent(paramString, null, paramBundle, false, ActivityLifecycleTracker.getCurrentSessionGuid());
  }
  
  public void logPurchase(BigDecimal paramBigDecimal, Currency paramCurrency)
  {
    if (AutomaticAnalyticsLogger.isImplicitPurchaseLoggingEnabled()) {
      Log.w(TAG, "You are logging purchase events while auto-logging of in-app purchase is enabled in the SDK. Make sure you don't log duplicate events");
    }
    logPurchase(paramBigDecimal, paramCurrency, null, false);
  }
  
  public void logPurchase(BigDecimal paramBigDecimal, Currency paramCurrency, Bundle paramBundle)
  {
    if (AutomaticAnalyticsLogger.isImplicitPurchaseLoggingEnabled()) {
      Log.w(TAG, "You are logging purchase events while auto-logging of in-app purchase is enabled in the SDK. Make sure you don't log duplicate events");
    }
    logPurchase(paramBigDecimal, paramCurrency, paramBundle, false);
  }
  
  public void logPurchaseImplicitly(BigDecimal paramBigDecimal, Currency paramCurrency, Bundle paramBundle)
  {
    logPurchase(paramBigDecimal, paramCurrency, paramBundle, true);
  }
  
  public void logPushNotificationOpen(Bundle paramBundle)
  {
    logPushNotificationOpen(paramBundle, null);
  }
  
  public void logPushNotificationOpen(Bundle paramBundle, String paramString)
  {
    try
    {
      paramBundle = paramBundle.getString("fb_push_payload");
      if (Utility.isNullOrEmpty(paramBundle)) {
        return;
      }
      paramBundle = new JSONObject(paramBundle).getString("campaign");
    }
    catch (JSONException paramBundle)
    {
      Bundle localBundle;
      for (;;) {}
    }
    paramBundle = null;
    if (paramBundle == null)
    {
      Logger.log(LoggingBehavior.DEVELOPER_ERRORS, TAG, "Malformed payload specified for logging a push notification open.");
      return;
    }
    localBundle = new Bundle();
    localBundle.putString("fb_push_campaign", paramBundle);
    if (paramString != null) {
      localBundle.putString("fb_push_action", paramString);
    }
    logEvent("fb_mobile_push_opened", localBundle);
  }
  
  public void logSdkEvent(String paramString, Double paramDouble, Bundle paramBundle)
  {
    logEvent(paramString, paramDouble, paramBundle, true, ActivityLifecycleTracker.getCurrentSessionGuid());
  }
  
  public static enum FlushBehavior
  {
    AUTO,  EXPLICIT_ONLY;
    
    private FlushBehavior() {}
  }
  
  static class PersistedAppSessionInfo
  {
    private static final String PERSISTED_SESSION_INFO_FILENAME = "AppEventsLogger.persistedsessioninfo";
    private static final Runnable appSessionInfoFlushRunnable = new Runnable()
    {
      public void run()
      {
        AppEventsLogger.PersistedAppSessionInfo.saveAppSessionInformation(FacebookSdk.getApplicationContext());
      }
    };
    private static Map<AccessTokenAppIdPair, FacebookTimeSpentData> appSessionInfoMap;
    private static boolean hasChanges = false;
    private static boolean isLoaded = false;
    private static final Object staticLock = new Object();
    
    PersistedAppSessionInfo() {}
    
    private static FacebookTimeSpentData getTimeSpentData(Context paramContext, AccessTokenAppIdPair paramAccessTokenAppIdPair)
    {
      restoreAppSessionInformation(paramContext);
      FacebookTimeSpentData localFacebookTimeSpentData = (FacebookTimeSpentData)appSessionInfoMap.get(paramAccessTokenAppIdPair);
      paramContext = localFacebookTimeSpentData;
      if (localFacebookTimeSpentData == null)
      {
        paramContext = new FacebookTimeSpentData();
        appSessionInfoMap.put(paramAccessTokenAppIdPair, paramContext);
      }
      return paramContext;
    }
    
    static void onResume(Context paramContext, AccessTokenAppIdPair paramAccessTokenAppIdPair, AppEventsLogger paramAppEventsLogger, long paramLong, String paramString)
    {
      synchronized (staticLock)
      {
        getTimeSpentData(paramContext, paramAccessTokenAppIdPair).onResume(paramAppEventsLogger, paramLong, paramString);
        onTimeSpentDataUpdate();
        return;
      }
    }
    
    static void onSuspend(Context paramContext, AccessTokenAppIdPair paramAccessTokenAppIdPair, AppEventsLogger paramAppEventsLogger, long paramLong)
    {
      synchronized (staticLock)
      {
        getTimeSpentData(paramContext, paramAccessTokenAppIdPair).onSuspend(paramAppEventsLogger, paramLong);
        onTimeSpentDataUpdate();
        return;
      }
    }
    
    private static void onTimeSpentDataUpdate()
    {
      if (!hasChanges)
      {
        hasChanges = true;
        AppEventsLogger.backgroundExecutor.schedule(appSessionInfoFlushRunnable, 30L, TimeUnit.SECONDS);
      }
    }
    
    /* Error */
    private static void restoreAppSessionInformation(Context paramContext)
    {
      // Byte code:
      //   0: getstatic 31	com/facebook/appevents/AppEventsLogger$PersistedAppSessionInfo:staticLock	Ljava/lang/Object;
      //   3: astore 5
      //   5: aload 5
      //   7: monitorenter
      //   8: getstatic 97	com/facebook/appevents/AppEventsLogger$PersistedAppSessionInfo:isLoaded	Z
      //   11: istore_1
      //   12: iload_1
      //   13: ifne +253 -> 266
      //   16: new 99	java/io/ObjectInputStream
      //   19: dup
      //   20: aload_0
      //   21: ldc 13
      //   23: invokevirtual 105	android/content/Context:openFileInput	(Ljava/lang/String;)Ljava/io/FileInputStream;
      //   26: invokespecial 108	java/io/ObjectInputStream:<init>	(Ljava/io/InputStream;)V
      //   29: astore_3
      //   30: aload_3
      //   31: astore_2
      //   32: aload_3
      //   33: invokevirtual 112	java/io/ObjectInputStream:readObject	()Ljava/lang/Object;
      //   36: checkcast 114	java/util/HashMap
      //   39: putstatic 43	com/facebook/appevents/AppEventsLogger$PersistedAppSessionInfo:appSessionInfoMap	Ljava/util/Map;
      //   42: aload_3
      //   43: astore_2
      //   44: getstatic 120	com/facebook/LoggingBehavior:APP_EVENTS	Lcom/facebook/LoggingBehavior;
      //   47: ldc 122
      //   49: ldc 124
      //   51: invokestatic 130	com/facebook/internal/Logger:log	(Lcom/facebook/LoggingBehavior;Ljava/lang/String;Ljava/lang/String;)V
      //   54: aload_3
      //   55: invokestatic 136	com/facebook/internal/Utility:closeQuietly	(Ljava/io/Closeable;)V
      //   58: aload_0
      //   59: ldc 13
      //   61: invokevirtual 140	android/content/Context:deleteFile	(Ljava/lang/String;)Z
      //   64: pop
      //   65: getstatic 43	com/facebook/appevents/AppEventsLogger$PersistedAppSessionInfo:appSessionInfoMap	Ljava/util/Map;
      //   68: ifnonnull +13 -> 81
      //   71: new 114	java/util/HashMap
      //   74: dup
      //   75: invokespecial 141	java/util/HashMap:<init>	()V
      //   78: putstatic 43	com/facebook/appevents/AppEventsLogger$PersistedAppSessionInfo:appSessionInfoMap	Ljava/util/Map;
      //   81: iconst_1
      //   82: putstatic 97	com/facebook/appevents/AppEventsLogger$PersistedAppSessionInfo:isLoaded	Z
      //   85: iconst_0
      //   86: putstatic 73	com/facebook/appevents/AppEventsLogger$PersistedAppSessionInfo:hasChanges	Z
      //   89: goto +177 -> 266
      //   92: astore 4
      //   94: goto +13 -> 107
      //   97: astore_3
      //   98: aconst_null
      //   99: astore_2
      //   100: goto +95 -> 195
      //   103: astore 4
      //   105: aconst_null
      //   106: astore_3
      //   107: aload_3
      //   108: astore_2
      //   109: invokestatic 145	com/facebook/appevents/AppEventsLogger:access$200	()Ljava/lang/String;
      //   112: astore 6
      //   114: aload_3
      //   115: astore_2
      //   116: new 147	java/lang/StringBuilder
      //   119: dup
      //   120: invokespecial 148	java/lang/StringBuilder:<init>	()V
      //   123: astore 7
      //   125: aload_3
      //   126: astore_2
      //   127: aload 7
      //   129: ldc -106
      //   131: invokevirtual 154	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   134: pop
      //   135: aload_3
      //   136: astore_2
      //   137: aload 7
      //   139: aload 4
      //   141: invokevirtual 157	java/lang/Exception:toString	()Ljava/lang/String;
      //   144: invokevirtual 154	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   147: pop
      //   148: aload_3
      //   149: astore_2
      //   150: aload 6
      //   152: aload 7
      //   154: invokevirtual 158	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   157: invokestatic 164	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
      //   160: pop
      //   161: aload_3
      //   162: invokestatic 136	com/facebook/internal/Utility:closeQuietly	(Ljava/io/Closeable;)V
      //   165: aload_0
      //   166: ldc 13
      //   168: invokevirtual 140	android/content/Context:deleteFile	(Ljava/lang/String;)Z
      //   171: pop
      //   172: getstatic 43	com/facebook/appevents/AppEventsLogger$PersistedAppSessionInfo:appSessionInfoMap	Ljava/util/Map;
      //   175: ifnonnull +13 -> 188
      //   178: new 114	java/util/HashMap
      //   181: dup
      //   182: invokespecial 141	java/util/HashMap:<init>	()V
      //   185: putstatic 43	com/facebook/appevents/AppEventsLogger$PersistedAppSessionInfo:appSessionInfoMap	Ljava/util/Map;
      //   188: iconst_1
      //   189: putstatic 97	com/facebook/appevents/AppEventsLogger$PersistedAppSessionInfo:isLoaded	Z
      //   192: goto -107 -> 85
      //   195: aload_2
      //   196: invokestatic 136	com/facebook/internal/Utility:closeQuietly	(Ljava/io/Closeable;)V
      //   199: aload_0
      //   200: ldc 13
      //   202: invokevirtual 140	android/content/Context:deleteFile	(Ljava/lang/String;)Z
      //   205: pop
      //   206: getstatic 43	com/facebook/appevents/AppEventsLogger$PersistedAppSessionInfo:appSessionInfoMap	Ljava/util/Map;
      //   209: ifnonnull +13 -> 222
      //   212: new 114	java/util/HashMap
      //   215: dup
      //   216: invokespecial 141	java/util/HashMap:<init>	()V
      //   219: putstatic 43	com/facebook/appevents/AppEventsLogger$PersistedAppSessionInfo:appSessionInfoMap	Ljava/util/Map;
      //   222: iconst_1
      //   223: putstatic 97	com/facebook/appevents/AppEventsLogger$PersistedAppSessionInfo:isLoaded	Z
      //   226: iconst_0
      //   227: putstatic 73	com/facebook/appevents/AppEventsLogger$PersistedAppSessionInfo:hasChanges	Z
      //   230: aload_3
      //   231: athrow
      //   232: aload_3
      //   233: invokestatic 136	com/facebook/internal/Utility:closeQuietly	(Ljava/io/Closeable;)V
      //   236: aload_0
      //   237: ldc 13
      //   239: invokevirtual 140	android/content/Context:deleteFile	(Ljava/lang/String;)Z
      //   242: pop
      //   243: getstatic 43	com/facebook/appevents/AppEventsLogger$PersistedAppSessionInfo:appSessionInfoMap	Ljava/util/Map;
      //   246: ifnonnull +13 -> 259
      //   249: new 114	java/util/HashMap
      //   252: dup
      //   253: invokespecial 141	java/util/HashMap:<init>	()V
      //   256: putstatic 43	com/facebook/appevents/AppEventsLogger$PersistedAppSessionInfo:appSessionInfoMap	Ljava/util/Map;
      //   259: iconst_1
      //   260: putstatic 97	com/facebook/appevents/AppEventsLogger$PersistedAppSessionInfo:isLoaded	Z
      //   263: goto -178 -> 85
      //   266: aload 5
      //   268: monitorexit
      //   269: return
      //   270: astore_0
      //   271: aload 5
      //   273: monitorexit
      //   274: aload_0
      //   275: athrow
      //   276: astore_2
      //   277: goto +11 -> 288
      //   280: astore_2
      //   281: goto -49 -> 232
      //   284: astore_3
      //   285: goto -90 -> 195
      //   288: aconst_null
      //   289: astore_3
      //   290: goto -58 -> 232
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	293	0	paramContext	Context
      //   11	2	1	bool	boolean
      //   31	165	2	localObject1	Object
      //   276	1	2	localFileNotFoundException1	java.io.FileNotFoundException
      //   280	1	2	localFileNotFoundException2	java.io.FileNotFoundException
      //   29	26	3	localObjectInputStream	java.io.ObjectInputStream
      //   97	1	3	localObject2	Object
      //   106	127	3	localCloseable	java.io.Closeable
      //   284	1	3	localObject3	Object
      //   289	1	3	localObject4	Object
      //   92	1	4	localException1	Exception
      //   103	37	4	localException2	Exception
      //   3	269	5	localObject5	Object
      //   112	39	6	str	String
      //   123	30	7	localStringBuilder	StringBuilder
      // Exception table:
      //   from	to	target	type
      //   32	42	92	java/lang/Exception
      //   44	54	92	java/lang/Exception
      //   16	30	97	finally
      //   16	30	103	java/lang/Exception
      //   8	12	270	finally
      //   54	81	270	finally
      //   81	85	270	finally
      //   85	89	270	finally
      //   161	188	270	finally
      //   188	192	270	finally
      //   195	222	270	finally
      //   222	232	270	finally
      //   232	259	270	finally
      //   259	263	270	finally
      //   266	269	270	finally
      //   271	274	270	finally
      //   16	30	276	java/io/FileNotFoundException
      //   32	42	280	java/io/FileNotFoundException
      //   44	54	280	java/io/FileNotFoundException
      //   32	42	284	finally
      //   44	54	284	finally
      //   109	114	284	finally
      //   116	125	284	finally
      //   127	135	284	finally
      //   137	148	284	finally
      //   150	161	284	finally
    }
    
    /* Error */
    static void saveAppSessionInformation(Context paramContext)
    {
      // Byte code:
      //   0: getstatic 31	com/facebook/appevents/AppEventsLogger$PersistedAppSessionInfo:staticLock	Ljava/lang/Object;
      //   3: astore 5
      //   5: aload 5
      //   7: monitorenter
      //   8: getstatic 73	com/facebook/appevents/AppEventsLogger$PersistedAppSessionInfo:hasChanges	Z
      //   11: istore_1
      //   12: iload_1
      //   13: ifeq +144 -> 157
      //   16: aconst_null
      //   17: astore 4
      //   19: aconst_null
      //   20: astore_2
      //   21: new 167	java/io/ObjectOutputStream
      //   24: dup
      //   25: new 169	java/io/BufferedOutputStream
      //   28: dup
      //   29: aload_0
      //   30: ldc 13
      //   32: iconst_0
      //   33: invokevirtual 173	android/content/Context:openFileOutput	(Ljava/lang/String;I)Ljava/io/FileOutputStream;
      //   36: invokespecial 176	java/io/BufferedOutputStream:<init>	(Ljava/io/OutputStream;)V
      //   39: invokespecial 177	java/io/ObjectOutputStream:<init>	(Ljava/io/OutputStream;)V
      //   42: astore_0
      //   43: aload_0
      //   44: getstatic 43	com/facebook/appevents/AppEventsLogger$PersistedAppSessionInfo:appSessionInfoMap	Ljava/util/Map;
      //   47: invokevirtual 181	java/io/ObjectOutputStream:writeObject	(Ljava/lang/Object;)V
      //   50: iconst_0
      //   51: putstatic 73	com/facebook/appevents/AppEventsLogger$PersistedAppSessionInfo:hasChanges	Z
      //   54: getstatic 120	com/facebook/LoggingBehavior:APP_EVENTS	Lcom/facebook/LoggingBehavior;
      //   57: ldc 122
      //   59: ldc -73
      //   61: invokestatic 130	com/facebook/internal/Logger:log	(Lcom/facebook/LoggingBehavior;Ljava/lang/String;Ljava/lang/String;)V
      //   64: aload_0
      //   65: invokestatic 136	com/facebook/internal/Utility:closeQuietly	(Ljava/io/Closeable;)V
      //   68: goto +89 -> 157
      //   71: astore_3
      //   72: aload_0
      //   73: astore_2
      //   74: aload_3
      //   75: astore_0
      //   76: goto +75 -> 151
      //   79: astore_3
      //   80: goto +11 -> 91
      //   83: astore_0
      //   84: goto +67 -> 151
      //   87: astore_3
      //   88: aload 4
      //   90: astore_0
      //   91: aload_0
      //   92: astore_2
      //   93: invokestatic 145	com/facebook/appevents/AppEventsLogger:access$200	()Ljava/lang/String;
      //   96: astore 4
      //   98: aload_0
      //   99: astore_2
      //   100: new 147	java/lang/StringBuilder
      //   103: dup
      //   104: invokespecial 148	java/lang/StringBuilder:<init>	()V
      //   107: astore 6
      //   109: aload_0
      //   110: astore_2
      //   111: aload 6
      //   113: ldc -71
      //   115: invokevirtual 154	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   118: pop
      //   119: aload_0
      //   120: astore_2
      //   121: aload 6
      //   123: aload_3
      //   124: invokevirtual 157	java/lang/Exception:toString	()Ljava/lang/String;
      //   127: invokevirtual 154	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   130: pop
      //   131: aload_0
      //   132: astore_2
      //   133: aload 4
      //   135: aload 6
      //   137: invokevirtual 158	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   140: invokestatic 164	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
      //   143: pop
      //   144: aload_0
      //   145: invokestatic 136	com/facebook/internal/Utility:closeQuietly	(Ljava/io/Closeable;)V
      //   148: goto +9 -> 157
      //   151: aload_2
      //   152: invokestatic 136	com/facebook/internal/Utility:closeQuietly	(Ljava/io/Closeable;)V
      //   155: aload_0
      //   156: athrow
      //   157: aload 5
      //   159: monitorexit
      //   160: return
      //   161: astore_0
      //   162: aload 5
      //   164: monitorexit
      //   165: aload_0
      //   166: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	167	0	paramContext	Context
      //   11	2	1	bool	boolean
      //   20	132	2	localContext	Context
      //   71	4	3	localObject1	Object
      //   79	1	3	localException1	Exception
      //   87	37	3	localException2	Exception
      //   17	117	4	str	String
      //   3	160	5	localObject2	Object
      //   107	29	6	localStringBuilder	StringBuilder
      // Exception table:
      //   from	to	target	type
      //   43	64	71	finally
      //   43	64	79	java/lang/Exception
      //   21	43	83	finally
      //   93	98	83	finally
      //   100	109	83	finally
      //   111	119	83	finally
      //   121	131	83	finally
      //   133	144	83	finally
      //   21	43	87	java/lang/Exception
      //   8	12	161	finally
      //   64	68	161	finally
      //   144	148	161	finally
      //   151	157	161	finally
      //   157	160	161	finally
      //   162	165	161	finally
    }
  }
}
