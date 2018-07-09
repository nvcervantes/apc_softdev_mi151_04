package com.facebook.internal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.internal.AutomaticAnalyticsLogger;
import com.facebook.appevents.internal.Constants;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class FetchedAppSettingsManager
{
  private static final String APPLICATION_FIELDS = "fields";
  private static final String APP_SETTINGS_PREFS_KEY_FORMAT = "com.facebook.internal.APP_SETTINGS.%s";
  private static final String APP_SETTINGS_PREFS_STORE = "com.facebook.internal.preferences.APP_SETTINGS";
  private static final String APP_SETTING_ANDROID_SDK_ERROR_CATEGORIES = "android_sdk_error_categories";
  private static final String APP_SETTING_APP_EVENTS_FEATURE_BITMASK = "app_events_feature_bitmask";
  private static final String APP_SETTING_APP_EVENTS_SESSION_TIMEOUT = "app_events_session_timeout";
  private static final String APP_SETTING_CUSTOM_TABS_ENABLED = "gdpv4_chrome_custom_tabs_enabled";
  private static final String APP_SETTING_DIALOG_CONFIGS = "android_dialog_configs";
  private static final String[] APP_SETTING_FIELDS = { "supports_implicit_sdk_logging", "gdpv4_nux_content", "gdpv4_nux_enabled", "gdpv4_chrome_custom_tabs_enabled", "android_dialog_configs", "android_sdk_error_categories", "app_events_session_timeout", "app_events_feature_bitmask", "seamless_login", "smart_login_bookmark_icon_url", "smart_login_menu_icon_url" };
  private static final String APP_SETTING_NUX_CONTENT = "gdpv4_nux_content";
  private static final String APP_SETTING_NUX_ENABLED = "gdpv4_nux_enabled";
  private static final String APP_SETTING_SMART_LOGIN_OPTIONS = "seamless_login";
  private static final String APP_SETTING_SUPPORTS_IMPLICIT_SDK_LOGGING = "supports_implicit_sdk_logging";
  private static final int AUTOMATIC_LOGGING_ENABLED_BITMASK_FIELD = 8;
  private static final int IAP_AUTOMATIC_LOGGING_ENABLED_BITMASK_FIELD = 16;
  private static final String SDK_UPDATE_MESSAGE = "sdk_update_message";
  private static final String SMART_LOGIN_BOOKMARK_ICON_URL = "smart_login_bookmark_icon_url";
  private static final String SMART_LOGIN_MENU_ICON_URL = "smart_login_menu_icon_url";
  private static final String TAG = FetchedAppSettingsManager.class.getCanonicalName();
  private static Map<String, FetchedAppSettings> fetchedAppSettings = new ConcurrentHashMap();
  private static AtomicBoolean loadingSettings = new AtomicBoolean(false);
  private static boolean printedSDKUpdatedMessage = false;
  
  public FetchedAppSettingsManager() {}
  
  private static JSONObject getAppSettingsQueryResponse(String paramString)
  {
    Bundle localBundle = new Bundle();
    localBundle.putString("fields", TextUtils.join(",", new ArrayList(Arrays.asList(APP_SETTING_FIELDS))));
    paramString = GraphRequest.newGraphPathRequest(null, paramString, null);
    paramString.setSkipClientToken(true);
    paramString.setParameters(localBundle);
    return paramString.executeAndWait().getJSONObject();
  }
  
  public static FetchedAppSettings getAppSettingsWithoutQuery(String paramString)
  {
    if (paramString != null) {
      return (FetchedAppSettings)fetchedAppSettings.get(paramString);
    }
    return null;
  }
  
  public static void loadAppSettingsAsync()
  {
    Context localContext = FacebookSdk.getApplicationContext();
    final String str1 = FacebookSdk.getApplicationId();
    boolean bool = loadingSettings.compareAndSet(false, true);
    if ((!Utility.isNullOrEmpty(str1)) && (!fetchedAppSettings.containsKey(str1)))
    {
      if (!bool) {
        return;
      }
      final String str2 = String.format("com.facebook.internal.APP_SETTINGS.%s", new Object[] { str1 });
      FacebookSdk.getExecutor().execute(new Runnable()
      {
        public void run()
        {
          SharedPreferences localSharedPreferences = val$context.getSharedPreferences("com.facebook.internal.preferences.APP_SETTINGS", 0);
          Object localObject1 = str2;
          Object localObject4 = null;
          Object localObject3 = localSharedPreferences.getString((String)localObject1, null);
          localObject1 = localObject4;
          Object localObject2;
          if (!Utility.isNullOrEmpty((String)localObject3))
          {
            try
            {
              localObject3 = new JSONObject((String)localObject3);
            }
            catch (JSONException localJSONException)
            {
              Utility.logd("FacebookSDK", localJSONException);
              localObject3 = null;
            }
            localObject2 = localObject4;
            if (localObject3 != null) {
              localObject2 = FetchedAppSettingsManager.parseAppSettingsFromJSON(str1, (JSONObject)localObject3);
            }
          }
          localObject3 = FetchedAppSettingsManager.getAppSettingsQueryResponse(str1);
          if (localObject3 != null)
          {
            FetchedAppSettingsManager.parseAppSettingsFromJSON(str1, (JSONObject)localObject3);
            localSharedPreferences.edit().putString(str2, ((JSONObject)localObject3).toString()).apply();
          }
          if (localObject2 != null)
          {
            localObject2 = ((FetchedAppSettings)localObject2).getSdkUpdateMessage();
            if ((!FetchedAppSettingsManager.printedSDKUpdatedMessage) && (localObject2 != null) && (((String)localObject2).length() > 0))
            {
              FetchedAppSettingsManager.access$202(true);
              Log.w(FetchedAppSettingsManager.TAG, (String)localObject2);
            }
          }
          AutomaticAnalyticsLogger.logActivateAppEvent();
          FetchedAppSettingsManager.startInAppPurchaseAutoLogging(val$context);
          FetchedAppSettingsManager.loadingSettings.set(false);
        }
      });
      return;
    }
  }
  
  private static FetchedAppSettings parseAppSettingsFromJSON(String paramString, JSONObject paramJSONObject)
  {
    Object localObject = paramJSONObject.optJSONArray("android_sdk_error_categories");
    if (localObject == null) {}
    for (localObject = FacebookRequestErrorClassification.getDefaultErrorClassification();; localObject = FacebookRequestErrorClassification.createFromJSON((JSONArray)localObject)) {
      break;
    }
    int i = paramJSONObject.optInt("app_events_feature_bitmask", 0);
    boolean bool1;
    if ((i & 0x8) != 0) {
      bool1 = true;
    } else {
      bool1 = false;
    }
    boolean bool2;
    if ((i & 0x10) != 0) {
      bool2 = true;
    } else {
      bool2 = false;
    }
    paramJSONObject = new FetchedAppSettings(paramJSONObject.optBoolean("supports_implicit_sdk_logging", false), paramJSONObject.optString("gdpv4_nux_content", ""), paramJSONObject.optBoolean("gdpv4_nux_enabled", false), paramJSONObject.optBoolean("gdpv4_chrome_custom_tabs_enabled", false), paramJSONObject.optInt("app_events_session_timeout", Constants.getDefaultAppEventsSessionTimeoutInSeconds()), SmartLoginOption.parseOptions(paramJSONObject.optLong("seamless_login")), parseDialogConfigurations(paramJSONObject.optJSONObject("android_dialog_configs")), bool1, (FacebookRequestErrorClassification)localObject, paramJSONObject.optString("smart_login_bookmark_icon_url"), paramJSONObject.optString("smart_login_menu_icon_url"), bool2, paramJSONObject.optString("sdk_update_message"));
    fetchedAppSettings.put(paramString, paramJSONObject);
    return paramJSONObject;
  }
  
  private static Map<String, Map<String, FetchedAppSettings.DialogFeatureConfig>> parseDialogConfigurations(JSONObject paramJSONObject)
  {
    HashMap localHashMap = new HashMap();
    if (paramJSONObject != null)
    {
      JSONArray localJSONArray = paramJSONObject.optJSONArray("data");
      if (localJSONArray != null)
      {
        int i = 0;
        while (i < localJSONArray.length())
        {
          FetchedAppSettings.DialogFeatureConfig localDialogFeatureConfig = FetchedAppSettings.DialogFeatureConfig.parseDialogConfig(localJSONArray.optJSONObject(i));
          if (localDialogFeatureConfig != null)
          {
            String str = localDialogFeatureConfig.getDialogName();
            Map localMap = (Map)localHashMap.get(str);
            paramJSONObject = localMap;
            if (localMap == null)
            {
              paramJSONObject = new HashMap();
              localHashMap.put(str, paramJSONObject);
            }
            paramJSONObject.put(localDialogFeatureConfig.getFeatureName(), localDialogFeatureConfig);
          }
          i += 1;
        }
      }
    }
    return localHashMap;
  }
  
  public static FetchedAppSettings queryAppSettings(String paramString, boolean paramBoolean)
  {
    if ((!paramBoolean) && (fetchedAppSettings.containsKey(paramString))) {
      return (FetchedAppSettings)fetchedAppSettings.get(paramString);
    }
    JSONObject localJSONObject = getAppSettingsQueryResponse(paramString);
    if (localJSONObject == null) {
      return null;
    }
    return parseAppSettingsFromJSON(paramString, localJSONObject);
  }
  
  private static void startInAppPurchaseAutoLogging(Context paramContext)
  {
    CallbackManagerImpl.registerStaticCallback(CallbackManagerImpl.RequestCodeOffset.InAppPurchase.toRequestCode(), new CallbackManagerImpl.Callback()
    {
      public boolean onActivityResult(final int paramAnonymousInt, final Intent paramAnonymousIntent)
      {
        FacebookSdk.getExecutor().execute(new Runnable()
        {
          public void run()
          {
            AutomaticAnalyticsLogger.logInAppPurchaseEvent(val$context, paramAnonymousInt, paramAnonymousIntent);
          }
        });
        return true;
      }
    });
  }
}
