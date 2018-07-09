package com.facebook.internal;

import android.os.Bundle;
import com.facebook.FacebookSdk;
import com.facebook.LoggingBehavior;
import java.util.Collection;
import org.json.JSONException;
import org.json.JSONObject;

public final class ServerProtocol
{
  private static final String DIALOG_AUTHORITY_FORMAT = "m.%s";
  public static final String DIALOG_CANCEL_URI = "fbconnect://cancel";
  public static final String DIALOG_PARAM_ACCESS_TOKEN = "access_token";
  public static final String DIALOG_PARAM_APP_ID = "app_id";
  public static final String DIALOG_PARAM_AUTH_TYPE = "auth_type";
  public static final String DIALOG_PARAM_CLIENT_ID = "client_id";
  public static final String DIALOG_PARAM_DEFAULT_AUDIENCE = "default_audience";
  public static final String DIALOG_PARAM_DISPLAY = "display";
  public static final String DIALOG_PARAM_DISPLAY_TOUCH = "touch";
  public static final String DIALOG_PARAM_E2E = "e2e";
  public static final String DIALOG_PARAM_LEGACY_OVERRIDE = "legacy_override";
  public static final String DIALOG_PARAM_REDIRECT_URI = "redirect_uri";
  public static final String DIALOG_PARAM_RESPONSE_TYPE = "response_type";
  public static final String DIALOG_PARAM_RETURN_SCOPES = "return_scopes";
  public static final String DIALOG_PARAM_SCOPE = "scope";
  public static final String DIALOG_PARAM_SDK_VERSION = "sdk";
  public static final String DIALOG_PARAM_SSO_DEVICE = "sso";
  public static final String DIALOG_PARAM_STATE = "state";
  public static final String DIALOG_PATH = "dialog/";
  public static final String DIALOG_REDIRECT_URI = "fbconnect://success";
  public static final String DIALOG_REREQUEST_AUTH_TYPE = "rerequest";
  public static final String DIALOG_RESPONSE_TYPE_TOKEN_AND_SIGNED_REQUEST = "token,signed_request";
  public static final String DIALOG_RETURN_SCOPES_TRUE = "true";
  public static final String FALLBACK_DIALOG_DISPLAY_VALUE_TOUCH = "touch";
  public static final String FALLBACK_DIALOG_PARAM_APP_ID = "app_id";
  public static final String FALLBACK_DIALOG_PARAM_BRIDGE_ARGS = "bridge_args";
  public static final String FALLBACK_DIALOG_PARAM_KEY_HASH = "android_key_hash";
  public static final String FALLBACK_DIALOG_PARAM_METHOD_ARGS = "method_args";
  public static final String FALLBACK_DIALOG_PARAM_METHOD_RESULTS = "method_results";
  public static final String FALLBACK_DIALOG_PARAM_VERSION = "version";
  private static final String GRAPH_API_VERSION = "v3.0";
  private static final String GRAPH_URL_FORMAT = "https://graph.%s";
  private static final String GRAPH_VIDEO_URL_FORMAT = "https://graph-video.%s";
  private static final String TAG = "com.facebook.internal.ServerProtocol";
  public static final String errorConnectionFailure = "CONNECTION_FAILURE";
  public static final Collection<String> errorsProxyAuthDisabled = Utility.unmodifiableCollection(new String[] { "service_disabled", "AndroidAuthKillSwitchException" });
  public static final Collection<String> errorsUserCanceled = Utility.unmodifiableCollection(new String[] { "access_denied", "OAuthAccessDeniedException" });
  
  public ServerProtocol() {}
  
  public static final String getDefaultAPIVersion()
  {
    return "v3.0";
  }
  
  public static final String getDialogAuthority()
  {
    return String.format("m.%s", new Object[] { FacebookSdk.getFacebookDomain() });
  }
  
  public static final String getGraphUrlBase()
  {
    return String.format("https://graph.%s", new Object[] { FacebookSdk.getFacebookDomain() });
  }
  
  public static final String getGraphVideoUrlBase()
  {
    return String.format("https://graph-video.%s", new Object[] { FacebookSdk.getFacebookDomain() });
  }
  
  public static Bundle getQueryParamsForPlatformActivityIntentWebFallback(String paramString, int paramInt, Bundle paramBundle)
  {
    Object localObject2 = FacebookSdk.getApplicationSignature(FacebookSdk.getApplicationContext());
    if (Utility.isNullOrEmpty((String)localObject2)) {
      return null;
    }
    Object localObject1 = new Bundle();
    ((Bundle)localObject1).putString("android_key_hash", (String)localObject2);
    ((Bundle)localObject1).putString("app_id", FacebookSdk.getApplicationId());
    ((Bundle)localObject1).putInt("version", paramInt);
    ((Bundle)localObject1).putString("display", "touch");
    localObject2 = new Bundle();
    ((Bundle)localObject2).putString("action_id", paramString);
    paramString = paramBundle;
    if (paramBundle == null) {
      paramString = new Bundle();
    }
    try
    {
      paramBundle = BundleJSONConverter.convertToJSON((Bundle)localObject2);
      paramString = BundleJSONConverter.convertToJSON(paramString);
      if (paramBundle != null)
      {
        if (paramString == null) {
          return null;
        }
        ((Bundle)localObject1).putString("bridge_args", paramBundle.toString());
        ((Bundle)localObject1).putString("method_args", paramString.toString());
        return localObject1;
      }
      return null;
    }
    catch (JSONException paramString)
    {
      paramBundle = LoggingBehavior.DEVELOPER_ERRORS;
      localObject1 = TAG;
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append("Error creating Url -- ");
      ((StringBuilder)localObject2).append(paramString);
      Logger.log(paramBundle, 6, (String)localObject1, ((StringBuilder)localObject2).toString());
    }
    return null;
  }
}
