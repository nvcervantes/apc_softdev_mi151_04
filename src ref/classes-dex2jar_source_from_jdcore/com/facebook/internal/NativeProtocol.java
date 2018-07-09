package com.facebook.internal;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import com.facebook.FacebookException;
import com.facebook.FacebookOperationCanceledException;
import com.facebook.FacebookSdk;
import com.facebook.login.DefaultAudience;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;

public final class NativeProtocol
{
  public static final String ACTION_APPINVITE_DIALOG = "com.facebook.platform.action.request.APPINVITES_DIALOG";
  public static final String ACTION_CAMERA_EFFECT = "com.facebook.platform.action.request.CAMERA_EFFECT";
  public static final String ACTION_FEED_DIALOG = "com.facebook.platform.action.request.FEED_DIALOG";
  public static final String ACTION_LIKE_DIALOG = "com.facebook.platform.action.request.LIKE_DIALOG";
  public static final String ACTION_MESSAGE_DIALOG = "com.facebook.platform.action.request.MESSAGE_DIALOG";
  public static final String ACTION_OGACTIONPUBLISH_DIALOG = "com.facebook.platform.action.request.OGACTIONPUBLISH_DIALOG";
  public static final String ACTION_OGMESSAGEPUBLISH_DIALOG = "com.facebook.platform.action.request.OGMESSAGEPUBLISH_DIALOG";
  public static final String AUDIENCE_EVERYONE = "everyone";
  public static final String AUDIENCE_FRIENDS = "friends";
  public static final String AUDIENCE_ME = "only_me";
  public static final String BRIDGE_ARG_ACTION_ID_STRING = "action_id";
  public static final String BRIDGE_ARG_APP_NAME_STRING = "app_name";
  public static final String BRIDGE_ARG_ERROR_BUNDLE = "error";
  public static final String BRIDGE_ARG_ERROR_CODE = "error_code";
  public static final String BRIDGE_ARG_ERROR_DESCRIPTION = "error_description";
  public static final String BRIDGE_ARG_ERROR_JSON = "error_json";
  public static final String BRIDGE_ARG_ERROR_SUBCODE = "error_subcode";
  public static final String BRIDGE_ARG_ERROR_TYPE = "error_type";
  private static final String CONTENT_SCHEME = "content://";
  public static final String ERROR_APPLICATION_ERROR = "ApplicationError";
  public static final String ERROR_NETWORK_ERROR = "NetworkError";
  public static final String ERROR_PERMISSION_DENIED = "PermissionDenied";
  public static final String ERROR_PROTOCOL_ERROR = "ProtocolError";
  public static final String ERROR_SERVICE_DISABLED = "ServiceDisabled";
  public static final String ERROR_UNKNOWN_ERROR = "UnknownError";
  public static final String ERROR_USER_CANCELED = "UserCanceled";
  public static final String EXTRA_ACCESS_TOKEN = "com.facebook.platform.extra.ACCESS_TOKEN";
  public static final String EXTRA_APPLICATION_ID = "com.facebook.platform.extra.APPLICATION_ID";
  public static final String EXTRA_APPLICATION_NAME = "com.facebook.platform.extra.APPLICATION_NAME";
  public static final String EXTRA_ARGS_PROFILE = "com.facebook.platform.extra.PROFILE";
  public static final String EXTRA_ARGS_PROFILE_FIRST_NAME = "com.facebook.platform.extra.PROFILE_FIRST_NAME";
  public static final String EXTRA_ARGS_PROFILE_LAST_NAME = "com.facebook.platform.extra.PROFILE_LAST_NAME";
  public static final String EXTRA_ARGS_PROFILE_LINK = "com.facebook.platform.extra.PROFILE_LINK";
  public static final String EXTRA_ARGS_PROFILE_MIDDLE_NAME = "com.facebook.platform.extra.PROFILE_MIDDLE_NAME";
  public static final String EXTRA_ARGS_PROFILE_NAME = "com.facebook.platform.extra.PROFILE_NAME";
  public static final String EXTRA_ARGS_PROFILE_USER_ID = "com.facebook.platform.extra.PROFILE_USER_ID";
  public static final String EXTRA_DIALOG_COMPLETE_KEY = "com.facebook.platform.extra.DID_COMPLETE";
  public static final String EXTRA_DIALOG_COMPLETION_GESTURE_KEY = "com.facebook.platform.extra.COMPLETION_GESTURE";
  public static final String EXTRA_EXPIRES_SECONDS_SINCE_EPOCH = "com.facebook.platform.extra.EXPIRES_SECONDS_SINCE_EPOCH";
  public static final String EXTRA_GET_INSTALL_DATA_PACKAGE = "com.facebook.platform.extra.INSTALLDATA_PACKAGE";
  public static final String EXTRA_GRAPH_API_VERSION = "com.facebook.platform.extra.GRAPH_API_VERSION";
  public static final String EXTRA_LOGGER_REF = "com.facebook.platform.extra.LOGGER_REF";
  public static final String EXTRA_PERMISSIONS = "com.facebook.platform.extra.PERMISSIONS";
  public static final String EXTRA_PROTOCOL_ACTION = "com.facebook.platform.protocol.PROTOCOL_ACTION";
  public static final String EXTRA_PROTOCOL_BRIDGE_ARGS = "com.facebook.platform.protocol.BRIDGE_ARGS";
  public static final String EXTRA_PROTOCOL_CALL_ID = "com.facebook.platform.protocol.CALL_ID";
  public static final String EXTRA_PROTOCOL_METHOD_ARGS = "com.facebook.platform.protocol.METHOD_ARGS";
  public static final String EXTRA_PROTOCOL_METHOD_RESULTS = "com.facebook.platform.protocol.RESULT_ARGS";
  public static final String EXTRA_PROTOCOL_VERSION = "com.facebook.platform.protocol.PROTOCOL_VERSION";
  static final String EXTRA_PROTOCOL_VERSIONS = "com.facebook.platform.extra.PROTOCOL_VERSIONS";
  public static final String EXTRA_TOAST_DURATION_MS = "com.facebook.platform.extra.EXTRA_TOAST_DURATION_MS";
  public static final String EXTRA_USER_ID = "com.facebook.platform.extra.USER_ID";
  private static final String FACEBOOK_PROXY_AUTH_ACTIVITY = "com.facebook.katana.ProxyAuth";
  public static final String FACEBOOK_PROXY_AUTH_APP_ID_KEY = "client_id";
  public static final String FACEBOOK_PROXY_AUTH_E2E_KEY = "e2e";
  public static final String FACEBOOK_PROXY_AUTH_PERMISSIONS_KEY = "scope";
  public static final String FACEBOOK_SDK_VERSION_KEY = "facebook_sdk_version";
  private static final String FACEBOOK_TOKEN_REFRESH_ACTIVITY = "com.facebook.katana.platform.TokenRefreshService";
  public static final String IMAGE_URL_KEY = "url";
  public static final String IMAGE_USER_GENERATED_KEY = "user_generated";
  static final String INTENT_ACTION_PLATFORM_ACTIVITY = "com.facebook.platform.PLATFORM_ACTIVITY";
  static final String INTENT_ACTION_PLATFORM_SERVICE = "com.facebook.platform.PLATFORM_SERVICE";
  private static final List<Integer> KNOWN_PROTOCOL_VERSIONS = Arrays.asList(new Integer[] { Integer.valueOf(20170417), Integer.valueOf(20160327), Integer.valueOf(20141218), Integer.valueOf(20141107), Integer.valueOf(20141028), Integer.valueOf(20141001), Integer.valueOf(20140701), Integer.valueOf(20140324), Integer.valueOf(20140204), Integer.valueOf(20131107), Integer.valueOf(20130618), Integer.valueOf(20130502), Integer.valueOf(20121101) });
  public static final int MESSAGE_GET_ACCESS_TOKEN_REPLY = 65537;
  public static final int MESSAGE_GET_ACCESS_TOKEN_REQUEST = 65536;
  public static final int MESSAGE_GET_AK_SEAMLESS_TOKEN_REPLY = 65545;
  public static final int MESSAGE_GET_AK_SEAMLESS_TOKEN_REQUEST = 65544;
  public static final int MESSAGE_GET_INSTALL_DATA_REPLY = 65541;
  public static final int MESSAGE_GET_INSTALL_DATA_REQUEST = 65540;
  public static final int MESSAGE_GET_LIKE_STATUS_REPLY = 65543;
  public static final int MESSAGE_GET_LIKE_STATUS_REQUEST = 65542;
  public static final int MESSAGE_GET_LOGIN_STATUS_REPLY = 65547;
  public static final int MESSAGE_GET_LOGIN_STATUS_REQUEST = 65546;
  static final int MESSAGE_GET_PROTOCOL_VERSIONS_REPLY = 65539;
  static final int MESSAGE_GET_PROTOCOL_VERSIONS_REQUEST = 65538;
  public static final int NO_PROTOCOL_AVAILABLE = -1;
  public static final String OPEN_GRAPH_CREATE_OBJECT_KEY = "fbsdk:create_object";
  private static final String PLATFORM_PROVIDER = ".provider.PlatformProvider";
  private static final String PLATFORM_PROVIDER_VERSIONS = ".provider.PlatformProvider/versions";
  private static final String PLATFORM_PROVIDER_VERSION_COLUMN = "version";
  public static final int PROTOCOL_VERSION_20121101 = 20121101;
  public static final int PROTOCOL_VERSION_20130502 = 20130502;
  public static final int PROTOCOL_VERSION_20130618 = 20130618;
  public static final int PROTOCOL_VERSION_20131107 = 20131107;
  public static final int PROTOCOL_VERSION_20140204 = 20140204;
  public static final int PROTOCOL_VERSION_20140324 = 20140324;
  public static final int PROTOCOL_VERSION_20140701 = 20140701;
  public static final int PROTOCOL_VERSION_20141001 = 20141001;
  public static final int PROTOCOL_VERSION_20141028 = 20141028;
  public static final int PROTOCOL_VERSION_20141107 = 20141107;
  public static final int PROTOCOL_VERSION_20141218 = 20141218;
  public static final int PROTOCOL_VERSION_20160327 = 20160327;
  public static final int PROTOCOL_VERSION_20170213 = 20170213;
  public static final int PROTOCOL_VERSION_20170411 = 20170411;
  public static final int PROTOCOL_VERSION_20170417 = 20170417;
  public static final int PROTOCOL_VERSION_20171115 = 20171115;
  public static final String RESULT_ARGS_ACCESS_TOKEN = "access_token";
  public static final String RESULT_ARGS_DIALOG_COMPLETE_KEY = "didComplete";
  public static final String RESULT_ARGS_DIALOG_COMPLETION_GESTURE_KEY = "completionGesture";
  public static final String RESULT_ARGS_EXPIRES_SECONDS_SINCE_EPOCH = "expires_seconds_since_epoch";
  public static final String RESULT_ARGS_PERMISSIONS = "permissions";
  public static final String RESULT_ARGS_SIGNED_REQUEST = "signed request";
  public static final String STATUS_ERROR_CODE = "com.facebook.platform.status.ERROR_CODE";
  public static final String STATUS_ERROR_DESCRIPTION = "com.facebook.platform.status.ERROR_DESCRIPTION";
  public static final String STATUS_ERROR_JSON = "com.facebook.platform.status.ERROR_JSON";
  public static final String STATUS_ERROR_SUBCODE = "com.facebook.platform.status.ERROR_SUBCODE";
  public static final String STATUS_ERROR_TYPE = "com.facebook.platform.status.ERROR_TYPE";
  private static final String TAG = "com.facebook.internal.NativeProtocol";
  public static final String WEB_DIALOG_ACTION = "action";
  public static final String WEB_DIALOG_IS_FALLBACK = "is_fallback";
  public static final String WEB_DIALOG_PARAMS = "params";
  public static final String WEB_DIALOG_URL = "url";
  private static final Map<String, List<NativeAppInfo>> actionToAppInfoMap;
  private static final List<NativeAppInfo> effectCameraAppInfoList;
  private static final List<NativeAppInfo> facebookAppInfoList = ;
  private static final AtomicBoolean protocolVersionsAsyncUpdating;
  
  static
  {
    effectCameraAppInfoList = buildEffectCameraAppInfoList();
    actionToAppInfoMap = buildActionToAppInfoMap();
    protocolVersionsAsyncUpdating = new AtomicBoolean(false);
  }
  
  public NativeProtocol() {}
  
  private static Map<String, List<NativeAppInfo>> buildActionToAppInfoMap()
  {
    HashMap localHashMap = new HashMap();
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(new MessengerAppInfo(null));
    localHashMap.put("com.facebook.platform.action.request.OGACTIONPUBLISH_DIALOG", facebookAppInfoList);
    localHashMap.put("com.facebook.platform.action.request.FEED_DIALOG", facebookAppInfoList);
    localHashMap.put("com.facebook.platform.action.request.LIKE_DIALOG", facebookAppInfoList);
    localHashMap.put("com.facebook.platform.action.request.APPINVITES_DIALOG", facebookAppInfoList);
    localHashMap.put("com.facebook.platform.action.request.MESSAGE_DIALOG", localArrayList);
    localHashMap.put("com.facebook.platform.action.request.OGMESSAGEPUBLISH_DIALOG", localArrayList);
    localHashMap.put("com.facebook.platform.action.request.CAMERA_EFFECT", effectCameraAppInfoList);
    return localHashMap;
  }
  
  private static List<NativeAppInfo> buildEffectCameraAppInfoList()
  {
    ArrayList localArrayList = new ArrayList(buildFacebookAppList());
    localArrayList.add(0, new EffectTestAppInfo(null));
    return localArrayList;
  }
  
  private static List<NativeAppInfo> buildFacebookAppList()
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(new KatanaAppInfo(null));
    localArrayList.add(new WakizashiAppInfo(null));
    return localArrayList;
  }
  
  private static Uri buildPlatformProviderVersionURI(NativeAppInfo paramNativeAppInfo)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("content://");
    localStringBuilder.append(paramNativeAppInfo.getPackage());
    localStringBuilder.append(".provider.PlatformProvider/versions");
    return Uri.parse(localStringBuilder.toString());
  }
  
  public static int computeLatestAvailableVersionFromVersionSpec(TreeSet<Integer> paramTreeSet, int paramInt, int[] paramArrayOfInt)
  {
    int m = -1;
    int i = paramArrayOfInt.length;
    paramTreeSet = paramTreeSet.descendingIterator();
    i -= 1;
    int k = -1;
    while (paramTreeSet.hasNext())
    {
      int i1 = ((Integer)paramTreeSet.next()).intValue();
      int n = Math.max(k, i1);
      int j = i;
      while ((j >= 0) && (paramArrayOfInt[j] > i1)) {
        j -= 1;
      }
      if (j < 0) {
        return -1;
      }
      k = n;
      i = j;
      if (paramArrayOfInt[j] == i1)
      {
        i = m;
        if (j % 2 == 0) {
          i = Math.min(n, paramInt);
        }
        return i;
      }
    }
    return -1;
  }
  
  public static Bundle createBundleForException(FacebookException paramFacebookException)
  {
    if (paramFacebookException == null) {
      return null;
    }
    Bundle localBundle = new Bundle();
    localBundle.putString("error_description", paramFacebookException.toString());
    if ((paramFacebookException instanceof FacebookOperationCanceledException)) {
      localBundle.putString("error_type", "UserCanceled");
    }
    return localBundle;
  }
  
  public static Intent createFacebookLiteIntent(Context paramContext, String paramString1, Collection<String> paramCollection, String paramString2, boolean paramBoolean1, boolean paramBoolean2, DefaultAudience paramDefaultAudience, String paramString3)
  {
    FBLiteAppInfo localFBLiteAppInfo = new FBLiteAppInfo(null);
    return validateActivityIntent(paramContext, createNativeAppIntent(localFBLiteAppInfo, paramString1, paramCollection, paramString2, paramBoolean1, paramBoolean2, paramDefaultAudience, paramString3), localFBLiteAppInfo);
  }
  
  private static Intent createNativeAppIntent(NativeAppInfo paramNativeAppInfo, String paramString1, Collection<String> paramCollection, String paramString2, boolean paramBoolean1, boolean paramBoolean2, DefaultAudience paramDefaultAudience, String paramString3)
  {
    String str = paramNativeAppInfo.getLoginActivity();
    if (str == null) {
      return null;
    }
    paramNativeAppInfo = new Intent().setClassName(paramNativeAppInfo.getPackage(), str).putExtra("client_id", paramString1);
    paramNativeAppInfo.putExtra("facebook_sdk_version", FacebookSdk.getSdkVersion());
    if (!Utility.isNullOrEmpty(paramCollection)) {
      paramNativeAppInfo.putExtra("scope", TextUtils.join(",", paramCollection));
    }
    if (!Utility.isNullOrEmpty(paramString2)) {
      paramNativeAppInfo.putExtra("e2e", paramString2);
    }
    paramNativeAppInfo.putExtra("state", paramString3);
    paramNativeAppInfo.putExtra("response_type", "token,signed_request");
    paramNativeAppInfo.putExtra("return_scopes", "true");
    if (paramBoolean2) {
      paramNativeAppInfo.putExtra("default_audience", paramDefaultAudience.getNativeProtocolAudience());
    }
    paramNativeAppInfo.putExtra("legacy_override", FacebookSdk.getGraphApiVersion());
    paramNativeAppInfo.putExtra("auth_type", "rerequest");
    return paramNativeAppInfo;
  }
  
  public static Intent createPlatformActivityIntent(Context paramContext, String paramString1, String paramString2, ProtocolVersionQueryResult paramProtocolVersionQueryResult, Bundle paramBundle)
  {
    if (paramProtocolVersionQueryResult == null) {
      return null;
    }
    NativeAppInfo localNativeAppInfo = nativeAppInfo;
    if (localNativeAppInfo == null) {
      return null;
    }
    paramContext = validateActivityIntent(paramContext, new Intent().setAction("com.facebook.platform.PLATFORM_ACTIVITY").setPackage(localNativeAppInfo.getPackage()).addCategory("android.intent.category.DEFAULT"), localNativeAppInfo);
    if (paramContext == null) {
      return null;
    }
    setupProtocolRequestIntent(paramContext, paramString1, paramString2, protocolVersion, paramBundle);
    return paramContext;
  }
  
  public static Intent createPlatformServiceIntent(Context paramContext)
  {
    Iterator localIterator = facebookAppInfoList.iterator();
    while (localIterator.hasNext())
    {
      Object localObject = (NativeAppInfo)localIterator.next();
      localObject = validateServiceIntent(paramContext, new Intent("com.facebook.platform.PLATFORM_SERVICE").setPackage(((NativeAppInfo)localObject).getPackage()).addCategory("android.intent.category.DEFAULT"), (NativeAppInfo)localObject);
      if (localObject != null) {
        return localObject;
      }
    }
    return null;
  }
  
  public static Intent createProtocolResultIntent(Intent paramIntent, Bundle paramBundle, FacebookException paramFacebookException)
  {
    UUID localUUID = getCallIdFromIntent(paramIntent);
    if (localUUID == null) {
      return null;
    }
    Intent localIntent = new Intent();
    localIntent.putExtra("com.facebook.platform.protocol.PROTOCOL_VERSION", getProtocolVersionFromIntent(paramIntent));
    paramIntent = new Bundle();
    paramIntent.putString("action_id", localUUID.toString());
    if (paramFacebookException != null) {
      paramIntent.putBundle("error", createBundleForException(paramFacebookException));
    }
    localIntent.putExtra("com.facebook.platform.protocol.BRIDGE_ARGS", paramIntent);
    if (paramBundle != null) {
      localIntent.putExtra("com.facebook.platform.protocol.RESULT_ARGS", paramBundle);
    }
    return localIntent;
  }
  
  public static Intent createProxyAuthIntent(Context paramContext, String paramString1, Collection<String> paramCollection, String paramString2, boolean paramBoolean1, boolean paramBoolean2, DefaultAudience paramDefaultAudience, String paramString3)
  {
    Iterator localIterator = facebookAppInfoList.iterator();
    while (localIterator.hasNext())
    {
      Object localObject = (NativeAppInfo)localIterator.next();
      localObject = validateActivityIntent(paramContext, createNativeAppIntent((NativeAppInfo)localObject, paramString1, paramCollection, paramString2, paramBoolean1, paramBoolean2, paramDefaultAudience, paramString3), (NativeAppInfo)localObject);
      if (localObject != null) {
        return localObject;
      }
    }
    return null;
  }
  
  public static Intent createTokenRefreshIntent(Context paramContext)
  {
    Iterator localIterator = facebookAppInfoList.iterator();
    while (localIterator.hasNext())
    {
      Object localObject = (NativeAppInfo)localIterator.next();
      localObject = validateServiceIntent(paramContext, new Intent().setClassName(((NativeAppInfo)localObject).getPackage(), "com.facebook.katana.platform.TokenRefreshService"), (NativeAppInfo)localObject);
      if (localObject != null) {
        return localObject;
      }
    }
    return null;
  }
  
  private static TreeSet<Integer> fetchAllAvailableProtocolVersionsForAppInfo(NativeAppInfo paramNativeAppInfo)
  {
    TreeSet localTreeSet = new TreeSet();
    ContentResolver localContentResolver = FacebookSdk.getApplicationContext().getContentResolver();
    Uri localUri = buildPlatformProviderVersionURI(paramNativeAppInfo);
    Object localObject2 = null;
    Object localObject3 = null;
    localNativeAppInfo = null;
    localObject1 = localObject2;
    try
    {
      PackageManager localPackageManager = FacebookSdk.getApplicationContext().getPackageManager();
      localObject1 = localObject2;
      StringBuilder localStringBuilder = new StringBuilder();
      localObject1 = localObject2;
      localStringBuilder.append(paramNativeAppInfo.getPackage());
      localObject1 = localObject2;
      localStringBuilder.append(".provider.PlatformProvider");
      localObject1 = localObject2;
      paramNativeAppInfo = localStringBuilder.toString();
      localObject1 = localObject2;
      try
      {
        paramNativeAppInfo = localPackageManager.resolveContentProvider(paramNativeAppInfo, 0);
      }
      catch (RuntimeException paramNativeAppInfo)
      {
        localObject1 = localObject2;
        Log.e(TAG, "Failed to query content resolver.", paramNativeAppInfo);
        paramNativeAppInfo = null;
      }
      if (paramNativeAppInfo != null) {
        localObject1 = localObject2;
      }
    }
    finally
    {
      label140:
      label158:
      label200:
      if (localObject1 != null) {
        ((Cursor)localObject1).close();
      }
    }
    try
    {
      paramNativeAppInfo = localContentResolver.query(localUri, new String[] { "version" }, null, null, null);
    }
    catch (NullPointerException|SecurityException|IllegalArgumentException paramNativeAppInfo)
    {
      break label140;
      localNativeAppInfo = paramNativeAppInfo;
      if (paramNativeAppInfo == null) {
        break label200;
      }
      break label158;
    }
    localObject1 = localObject2;
    Log.e(TAG, "Failed to query content resolver.");
    paramNativeAppInfo = localObject3;
    break label230;
    for (;;)
    {
      localNativeAppInfo = paramNativeAppInfo;
      localObject1 = paramNativeAppInfo;
      if (!paramNativeAppInfo.moveToNext()) {
        break;
      }
      localObject1 = paramNativeAppInfo;
      localTreeSet.add(Integer.valueOf(paramNativeAppInfo.getInt(paramNativeAppInfo.getColumnIndex("version"))));
    }
    if (localNativeAppInfo != null) {
      localNativeAppInfo.close();
    }
    return localTreeSet;
  }
  
  public static Bundle getBridgeArgumentsFromIntent(Intent paramIntent)
  {
    if (!isVersionCompatibleWithBucketedIntent(getProtocolVersionFromIntent(paramIntent))) {
      return null;
    }
    return paramIntent.getBundleExtra("com.facebook.platform.protocol.BRIDGE_ARGS");
  }
  
  public static UUID getCallIdFromIntent(Intent paramIntent)
  {
    if (paramIntent == null) {
      return null;
    }
    if (isVersionCompatibleWithBucketedIntent(getProtocolVersionFromIntent(paramIntent)))
    {
      paramIntent = paramIntent.getBundleExtra("com.facebook.platform.protocol.BRIDGE_ARGS");
      if (paramIntent != null) {
        paramIntent = paramIntent.getString("action_id");
      } else {
        paramIntent = null;
      }
    }
    else
    {
      paramIntent = paramIntent.getStringExtra("com.facebook.platform.protocol.CALL_ID");
    }
    if (paramIntent != null) {}
    try
    {
      paramIntent = UUID.fromString(paramIntent);
      return paramIntent;
    }
    catch (IllegalArgumentException paramIntent)
    {
      for (;;) {}
    }
    return null;
  }
  
  public static Bundle getErrorDataFromResultIntent(Intent paramIntent)
  {
    if (!isErrorResult(paramIntent)) {
      return null;
    }
    Bundle localBundle = getBridgeArgumentsFromIntent(paramIntent);
    if (localBundle != null) {
      return localBundle.getBundle("error");
    }
    return paramIntent.getExtras();
  }
  
  public static FacebookException getExceptionFromErrorData(Bundle paramBundle)
  {
    if (paramBundle == null) {
      return null;
    }
    Object localObject2 = paramBundle.getString("error_type");
    Object localObject1 = localObject2;
    if (localObject2 == null) {
      localObject1 = paramBundle.getString("com.facebook.platform.status.ERROR_TYPE");
    }
    String str = paramBundle.getString("error_description");
    localObject2 = str;
    if (str == null) {
      localObject2 = paramBundle.getString("com.facebook.platform.status.ERROR_DESCRIPTION");
    }
    if ((localObject1 != null) && (((String)localObject1).equalsIgnoreCase("UserCanceled"))) {
      return new FacebookOperationCanceledException((String)localObject2);
    }
    return new FacebookException((String)localObject2);
  }
  
  public static ProtocolVersionQueryResult getLatestAvailableProtocolVersionForAction(String paramString, int[] paramArrayOfInt)
  {
    return getLatestAvailableProtocolVersionForAppInfoList((List)actionToAppInfoMap.get(paramString), paramArrayOfInt);
  }
  
  private static ProtocolVersionQueryResult getLatestAvailableProtocolVersionForAppInfoList(List<NativeAppInfo> paramList, int[] paramArrayOfInt)
  {
    
    if (paramList == null) {
      return ProtocolVersionQueryResult.createEmpty();
    }
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      NativeAppInfo localNativeAppInfo = (NativeAppInfo)paramList.next();
      int i = computeLatestAvailableVersionFromVersionSpec(localNativeAppInfo.getAvailableVersions(), getLatestKnownVersion(), paramArrayOfInt);
      if (i != -1) {
        return ProtocolVersionQueryResult.create(localNativeAppInfo, i);
      }
    }
    return ProtocolVersionQueryResult.createEmpty();
  }
  
  public static int getLatestAvailableProtocolVersionForService(int paramInt)
  {
    return getLatestAvailableProtocolVersionForAppInfoList(facebookAppInfoList, new int[] { paramInt }).getProtocolVersion();
  }
  
  public static final int getLatestKnownVersion()
  {
    return ((Integer)KNOWN_PROTOCOL_VERSIONS.get(0)).intValue();
  }
  
  public static Bundle getMethodArgumentsFromIntent(Intent paramIntent)
  {
    if (!isVersionCompatibleWithBucketedIntent(getProtocolVersionFromIntent(paramIntent))) {
      return paramIntent.getExtras();
    }
    return paramIntent.getBundleExtra("com.facebook.platform.protocol.METHOD_ARGS");
  }
  
  public static int getProtocolVersionFromIntent(Intent paramIntent)
  {
    return paramIntent.getIntExtra("com.facebook.platform.protocol.PROTOCOL_VERSION", 0);
  }
  
  public static Bundle getSuccessResultsFromIntent(Intent paramIntent)
  {
    int i = getProtocolVersionFromIntent(paramIntent);
    paramIntent = paramIntent.getExtras();
    if (isVersionCompatibleWithBucketedIntent(i))
    {
      if (paramIntent == null) {
        return paramIntent;
      }
      return paramIntent.getBundle("com.facebook.platform.protocol.RESULT_ARGS");
    }
    return paramIntent;
  }
  
  public static boolean isErrorResult(Intent paramIntent)
  {
    Bundle localBundle = getBridgeArgumentsFromIntent(paramIntent);
    if (localBundle != null) {
      return localBundle.containsKey("error");
    }
    return paramIntent.hasExtra("com.facebook.platform.status.ERROR_TYPE");
  }
  
  public static boolean isVersionCompatibleWithBucketedIntent(int paramInt)
  {
    return (KNOWN_PROTOCOL_VERSIONS.contains(Integer.valueOf(paramInt))) && (paramInt >= 20140701);
  }
  
  public static void setupProtocolRequestIntent(Intent paramIntent, String paramString1, String paramString2, int paramInt, Bundle paramBundle)
  {
    String str2 = FacebookSdk.getApplicationId();
    String str1 = FacebookSdk.getApplicationName();
    paramIntent.putExtra("com.facebook.platform.protocol.PROTOCOL_VERSION", paramInt).putExtra("com.facebook.platform.protocol.PROTOCOL_ACTION", paramString2).putExtra("com.facebook.platform.extra.APPLICATION_ID", str2);
    if (isVersionCompatibleWithBucketedIntent(paramInt))
    {
      paramString2 = new Bundle();
      paramString2.putString("action_id", paramString1);
      Utility.putNonEmptyString(paramString2, "app_name", str1);
      paramIntent.putExtra("com.facebook.platform.protocol.BRIDGE_ARGS", paramString2);
      paramString1 = paramBundle;
      if (paramBundle == null) {
        paramString1 = new Bundle();
      }
      paramIntent.putExtra("com.facebook.platform.protocol.METHOD_ARGS", paramString1);
      return;
    }
    paramIntent.putExtra("com.facebook.platform.protocol.CALL_ID", paramString1);
    if (!Utility.isNullOrEmpty(str1)) {
      paramIntent.putExtra("com.facebook.platform.extra.APPLICATION_NAME", str1);
    }
    paramIntent.putExtras(paramBundle);
  }
  
  public static void updateAllAvailableProtocolVersionsAsync()
  {
    if (!protocolVersionsAsyncUpdating.compareAndSet(false, true)) {
      return;
    }
    FacebookSdk.getExecutor().execute(new Runnable()
    {
      public void run()
      {
        try
        {
          Iterator localIterator = NativeProtocol.facebookAppInfoList.iterator();
          while (localIterator.hasNext()) {
            NativeProtocol.NativeAppInfo.access$1000((NativeProtocol.NativeAppInfo)localIterator.next(), true);
          }
          return;
        }
        finally
        {
          NativeProtocol.protocolVersionsAsyncUpdating.set(false);
        }
      }
    });
  }
  
  static Intent validateActivityIntent(Context paramContext, Intent paramIntent, NativeAppInfo paramNativeAppInfo)
  {
    if (paramIntent == null) {
      return null;
    }
    paramNativeAppInfo = paramContext.getPackageManager().resolveActivity(paramIntent, 0);
    if (paramNativeAppInfo == null) {
      return null;
    }
    if (!FacebookSignatureValidator.validateSignature(paramContext, activityInfo.packageName)) {
      return null;
    }
    return paramIntent;
  }
  
  static Intent validateServiceIntent(Context paramContext, Intent paramIntent, NativeAppInfo paramNativeAppInfo)
  {
    if (paramIntent == null) {
      return null;
    }
    paramNativeAppInfo = paramContext.getPackageManager().resolveService(paramIntent, 0);
    if (paramNativeAppInfo == null) {
      return null;
    }
    if (!FacebookSignatureValidator.validateSignature(paramContext, serviceInfo.packageName)) {
      return null;
    }
    return paramIntent;
  }
  
  private static class EffectTestAppInfo
    extends NativeProtocol.NativeAppInfo
  {
    static final String EFFECT_TEST_APP_PACKAGE = "com.facebook.arstudio.player";
    
    private EffectTestAppInfo()
    {
      super();
    }
    
    protected String getLoginActivity()
    {
      return null;
    }
    
    protected String getPackage()
    {
      return "com.facebook.arstudio.player";
    }
  }
  
  private static class FBLiteAppInfo
    extends NativeProtocol.NativeAppInfo
  {
    static final String FACEBOOK_LITE_ACTIVITY = "com.facebook.lite.platform.LoginGDPDialogActivity";
    static final String FBLITE_PACKAGE = "com.facebook.lite";
    
    private FBLiteAppInfo()
    {
      super();
    }
    
    protected String getLoginActivity()
    {
      return "com.facebook.lite.platform.LoginGDPDialogActivity";
    }
    
    protected String getPackage()
    {
      return "com.facebook.lite";
    }
  }
  
  private static class KatanaAppInfo
    extends NativeProtocol.NativeAppInfo
  {
    static final String KATANA_PACKAGE = "com.facebook.katana";
    
    private KatanaAppInfo()
    {
      super();
    }
    
    protected String getLoginActivity()
    {
      return "com.facebook.katana.ProxyAuth";
    }
    
    protected String getPackage()
    {
      return "com.facebook.katana";
    }
  }
  
  private static class MessengerAppInfo
    extends NativeProtocol.NativeAppInfo
  {
    static final String MESSENGER_PACKAGE = "com.facebook.orca";
    
    private MessengerAppInfo()
    {
      super();
    }
    
    protected String getLoginActivity()
    {
      return null;
    }
    
    protected String getPackage()
    {
      return "com.facebook.orca";
    }
  }
  
  private static abstract class NativeAppInfo
  {
    private TreeSet<Integer> availableVersions;
    
    private NativeAppInfo() {}
    
    private void fetchAvailableVersions(boolean paramBoolean)
    {
      if (!paramBoolean) {}
      try
      {
        if (availableVersions == null) {
          availableVersions = NativeProtocol.fetchAllAvailableProtocolVersionsForAppInfo(this);
        }
        return;
      }
      finally
      {
        Object localObject1;
        for (;;) {}
      }
      throw localObject1;
    }
    
    public TreeSet<Integer> getAvailableVersions()
    {
      if (availableVersions == null) {
        fetchAvailableVersions(false);
      }
      return availableVersions;
    }
    
    protected abstract String getLoginActivity();
    
    protected abstract String getPackage();
  }
  
  public static class ProtocolVersionQueryResult
  {
    private NativeProtocol.NativeAppInfo nativeAppInfo;
    private int protocolVersion;
    
    private ProtocolVersionQueryResult() {}
    
    public static ProtocolVersionQueryResult create(NativeProtocol.NativeAppInfo paramNativeAppInfo, int paramInt)
    {
      ProtocolVersionQueryResult localProtocolVersionQueryResult = new ProtocolVersionQueryResult();
      nativeAppInfo = paramNativeAppInfo;
      protocolVersion = paramInt;
      return localProtocolVersionQueryResult;
    }
    
    public static ProtocolVersionQueryResult createEmpty()
    {
      ProtocolVersionQueryResult localProtocolVersionQueryResult = new ProtocolVersionQueryResult();
      protocolVersion = -1;
      return localProtocolVersionQueryResult;
    }
    
    @Nullable
    public NativeProtocol.NativeAppInfo getAppInfo()
    {
      return nativeAppInfo;
    }
    
    public int getProtocolVersion()
    {
      return protocolVersion;
    }
  }
  
  private static class WakizashiAppInfo
    extends NativeProtocol.NativeAppInfo
  {
    static final String WAKIZASHI_PACKAGE = "com.facebook.wakizashi";
    
    private WakizashiAppInfo()
    {
      super();
    }
    
    protected String getLoginActivity()
    {
      return "com.facebook.katana.ProxyAuth";
    }
    
    protected String getPackage()
    {
      return "com.facebook.wakizashi";
    }
  }
}
