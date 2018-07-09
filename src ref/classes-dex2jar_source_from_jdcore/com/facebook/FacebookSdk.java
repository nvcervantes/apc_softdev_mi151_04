package com.facebook;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.appevents.internal.ActivityLifecycleTracker;
import com.facebook.appevents.internal.AppEventsLoggerUtility;
import com.facebook.appevents.internal.AppEventsLoggerUtility.GraphAPIActivityType;
import com.facebook.internal.AttributionIdentifiers;
import com.facebook.internal.BoltsMeasurementEventListener;
import com.facebook.internal.FetchedAppSettingsManager;
import com.facebook.internal.LockOnGetVariable;
import com.facebook.internal.NativeProtocol;
import com.facebook.internal.ServerProtocol;
import com.facebook.internal.Utility;
import com.facebook.internal.Validate;
import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import org.json.JSONException;

public final class FacebookSdk
{
  public static final String APPLICATION_ID_PROPERTY = "com.facebook.sdk.ApplicationId";
  public static final String APPLICATION_NAME_PROPERTY = "com.facebook.sdk.ApplicationName";
  private static final String ATTRIBUTION_PREFERENCES = "com.facebook.sdk.attributionTracking";
  public static final String AUTO_LOG_APP_EVENTS_ENABLED_PROPERTY = "com.facebook.sdk.AutoLogAppEventsEnabled";
  static final String CALLBACK_OFFSET_CHANGED_AFTER_INIT = "The callback request code offset can't be updated once the SDK is initialized. Call FacebookSdk.setCallbackRequestCodeOffset inside your Application.onCreate method";
  static final String CALLBACK_OFFSET_NEGATIVE = "The callback request code offset can't be negative.";
  public static final String CALLBACK_OFFSET_PROPERTY = "com.facebook.sdk.CallbackOffset";
  public static final String CLIENT_TOKEN_PROPERTY = "com.facebook.sdk.ClientToken";
  private static final int DEFAULT_CALLBACK_REQUEST_CODE_OFFSET = 64206;
  private static final int DEFAULT_CORE_POOL_SIZE = 5;
  private static final int DEFAULT_KEEP_ALIVE = 1;
  private static final int DEFAULT_MAXIMUM_POOL_SIZE = 128;
  private static final ThreadFactory DEFAULT_THREAD_FACTORY = new ThreadFactory()
  {
    private final AtomicInteger counter = new AtomicInteger(0);
    
    public Thread newThread(Runnable paramAnonymousRunnable)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("FacebookSdk #");
      localStringBuilder.append(counter.incrementAndGet());
      return new Thread(paramAnonymousRunnable, localStringBuilder.toString());
    }
  };
  private static final BlockingQueue<Runnable> DEFAULT_WORK_QUEUE;
  private static final String FACEBOOK_COM = "facebook.com";
  private static final Object LOCK;
  private static final int MAX_REQUEST_CODE_RANGE = 100;
  private static final String PUBLISH_ACTIVITY_PATH = "%s/activities";
  private static final String TAG = FacebookSdk.class.getCanonicalName();
  public static final String WEB_DIALOG_THEME = "com.facebook.sdk.WebDialogTheme";
  private static volatile String appClientToken;
  private static Context applicationContext;
  private static volatile String applicationId;
  private static volatile String applicationName;
  private static volatile Boolean autoLogAppEventsEnabled;
  private static LockOnGetVariable<File> cacheDir;
  private static int callbackRequestCodeOffset = 64206;
  private static Executor executor;
  private static volatile String facebookDomain = "facebook.com";
  private static String graphApiVersion;
  private static volatile boolean isDebugEnabled = false;
  private static boolean isLegacyTokenUpgradeSupported = false;
  private static final HashSet<LoggingBehavior> loggingBehaviors = new HashSet(Arrays.asList(new LoggingBehavior[] { LoggingBehavior.DEVELOPER_ERRORS }));
  private static AtomicLong onProgressThreshold = new AtomicLong(65536L);
  private static Boolean sdkInitialized = Boolean.valueOf(false);
  
  static
  {
    LOCK = new Object();
    graphApiVersion = ServerProtocol.getDefaultAPIVersion();
    DEFAULT_WORK_QUEUE = new LinkedBlockingQueue(10);
  }
  
  public FacebookSdk() {}
  
  public static void addLoggingBehavior(LoggingBehavior paramLoggingBehavior)
  {
    synchronized (loggingBehaviors)
    {
      loggingBehaviors.add(paramLoggingBehavior);
      updateGraphDebugBehavior();
      return;
    }
  }
  
  public static void clearLoggingBehaviors()
  {
    synchronized (loggingBehaviors)
    {
      loggingBehaviors.clear();
      return;
    }
  }
  
  public static Context getApplicationContext()
  {
    Validate.sdkInitialized();
    return applicationContext;
  }
  
  public static String getApplicationId()
  {
    Validate.sdkInitialized();
    return applicationId;
  }
  
  public static String getApplicationName()
  {
    Validate.sdkInitialized();
    return applicationName;
  }
  
  /* Error */
  public static String getApplicationSignature(Context paramContext)
  {
    // Byte code:
    //   0: invokestatic 187	com/facebook/internal/Validate:sdkInitialized	()V
    //   3: aload_0
    //   4: ifnonnull +5 -> 9
    //   7: aconst_null
    //   8: areturn
    //   9: aload_0
    //   10: invokevirtual 203	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   13: astore_1
    //   14: aload_1
    //   15: ifnonnull +5 -> 20
    //   18: aconst_null
    //   19: areturn
    //   20: aload_0
    //   21: invokevirtual 206	android/content/Context:getPackageName	()Ljava/lang/String;
    //   24: astore_0
    //   25: aload_1
    //   26: aload_0
    //   27: bipush 64
    //   29: invokevirtual 212	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   32: astore_0
    //   33: aload_0
    //   34: getfield 218	android/content/pm/PackageInfo:signatures	[Landroid/content/pm/Signature;
    //   37: astore_1
    //   38: aload_1
    //   39: ifnull +39 -> 78
    //   42: aload_1
    //   43: arraylength
    //   44: ifne +5 -> 49
    //   47: aconst_null
    //   48: areturn
    //   49: ldc -36
    //   51: invokestatic 226	java/security/MessageDigest:getInstance	(Ljava/lang/String;)Ljava/security/MessageDigest;
    //   54: astore_1
    //   55: aload_1
    //   56: aload_0
    //   57: getfield 218	android/content/pm/PackageInfo:signatures	[Landroid/content/pm/Signature;
    //   60: iconst_0
    //   61: aaload
    //   62: invokevirtual 232	android/content/pm/Signature:toByteArray	()[B
    //   65: invokevirtual 236	java/security/MessageDigest:update	([B)V
    //   68: aload_1
    //   69: invokevirtual 239	java/security/MessageDigest:digest	()[B
    //   72: bipush 9
    //   74: invokestatic 245	android/util/Base64:encodeToString	([BI)Ljava/lang/String;
    //   77: areturn
    //   78: aconst_null
    //   79: areturn
    //   80: astore_0
    //   81: aconst_null
    //   82: areturn
    //   83: astore_0
    //   84: aconst_null
    //   85: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	86	0	paramContext	Context
    //   13	56	1	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   25	33	80	android/content/pm/PackageManager$NameNotFoundException
    //   49	55	83	java/security/NoSuchAlgorithmException
  }
  
  public static boolean getAutoLogAppEventsEnabled()
  {
    Validate.sdkInitialized();
    return autoLogAppEventsEnabled.booleanValue();
  }
  
  public static File getCacheDir()
  {
    Validate.sdkInitialized();
    return (File)cacheDir.getValue();
  }
  
  public static int getCallbackRequestCodeOffset()
  {
    Validate.sdkInitialized();
    return callbackRequestCodeOffset;
  }
  
  public static String getClientToken()
  {
    Validate.sdkInitialized();
    return appClientToken;
  }
  
  public static Executor getExecutor()
  {
    synchronized (LOCK)
    {
      if (executor == null) {
        executor = AsyncTask.THREAD_POOL_EXECUTOR;
      }
      return executor;
    }
  }
  
  public static String getFacebookDomain()
  {
    return facebookDomain;
  }
  
  public static String getGraphApiVersion()
  {
    Log.d(TAG, String.format("getGraphApiVersion: %s", new Object[] { graphApiVersion }));
    return graphApiVersion;
  }
  
  public static boolean getLimitEventAndDataUsage(Context paramContext)
  {
    Validate.sdkInitialized();
    return paramContext.getSharedPreferences("com.facebook.sdk.appEventPreferences", 0).getBoolean("limitEventUsage", false);
  }
  
  public static Set<LoggingBehavior> getLoggingBehaviors()
  {
    synchronized (loggingBehaviors)
    {
      Set localSet = Collections.unmodifiableSet(new HashSet(loggingBehaviors));
      return localSet;
    }
  }
  
  public static long getOnProgressThreshold()
  {
    Validate.sdkInitialized();
    return onProgressThreshold.get();
  }
  
  public static String getSdkVersion()
  {
    return "4.33.0";
  }
  
  public static boolean isDebugEnabled()
  {
    return isDebugEnabled;
  }
  
  public static boolean isFacebookRequestCode(int paramInt)
  {
    return (paramInt >= callbackRequestCodeOffset) && (paramInt < callbackRequestCodeOffset + 100);
  }
  
  public static boolean isInitialized()
  {
    try
    {
      boolean bool = sdkInitialized.booleanValue();
      return bool;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  public static boolean isLegacyTokenUpgradeSupported()
  {
    return isLegacyTokenUpgradeSupported;
  }
  
  public static boolean isLoggingBehaviorEnabled(LoggingBehavior paramLoggingBehavior)
  {
    for (;;)
    {
      synchronized (loggingBehaviors)
      {
        if ((isDebugEnabled()) && (loggingBehaviors.contains(paramLoggingBehavior)))
        {
          bool = true;
          return bool;
        }
      }
      boolean bool = false;
    }
  }
  
  static void loadDefaultsFromMetadata(Context paramContext)
  {
    if (paramContext == null) {
      return;
    }
    try
    {
      paramContext = paramContext.getPackageManager().getApplicationInfo(paramContext.getPackageName(), 128);
      if (paramContext != null)
      {
        if (metaData == null) {
          return;
        }
        if (applicationId == null)
        {
          Object localObject = metaData.get("com.facebook.sdk.ApplicationId");
          if ((localObject instanceof String))
          {
            localObject = (String)localObject;
            if (((String)localObject).toLowerCase(Locale.ROOT).startsWith("fb")) {
              applicationId = ((String)localObject).substring(2);
            } else {
              applicationId = (String)localObject;
            }
          }
          else if ((localObject instanceof Integer))
          {
            throw new FacebookException("App Ids cannot be directly placed in the manifest.They must be prefixed by 'fb' or be placed in the string resource file.");
          }
        }
        if (applicationName == null) {
          applicationName = metaData.getString("com.facebook.sdk.ApplicationName");
        }
        if (appClientToken == null) {
          appClientToken = metaData.getString("com.facebook.sdk.ClientToken");
        }
        if (callbackRequestCodeOffset == 64206) {
          callbackRequestCodeOffset = metaData.getInt("com.facebook.sdk.CallbackOffset", 64206);
        }
        if (autoLogAppEventsEnabled == null) {
          autoLogAppEventsEnabled = Boolean.valueOf(metaData.getBoolean("com.facebook.sdk.AutoLogAppEventsEnabled", true));
        }
        return;
      }
      return;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
  }
  
  static void publishInstallAndWaitForResponse(Context paramContext, String paramString)
  {
    if ((paramContext != null) && (paramString != null)) {}
    try
    {
      AttributionIdentifiers localAttributionIdentifiers = AttributionIdentifiers.getAttributionIdentifiers(paramContext);
      SharedPreferences localSharedPreferences = paramContext.getSharedPreferences("com.facebook.sdk.attributionTracking", 0);
      Object localObject = new StringBuilder();
      ((StringBuilder)localObject).append(paramString);
      ((StringBuilder)localObject).append("ping");
      localObject = ((StringBuilder)localObject).toString();
      long l = localSharedPreferences.getLong((String)localObject, 0L);
      try
      {
        paramContext = AppEventsLoggerUtility.getJSONObjectForGraphAPICall(AppEventsLoggerUtility.GraphAPIActivityType.MOBILE_INSTALL_EVENT, localAttributionIdentifiers, AppEventsLogger.getAnonymousAppDeviceGUID(paramContext), getLimitEventAndDataUsage(paramContext), paramContext);
        paramContext = GraphRequest.newPostRequest(null, String.format("%s/activities", new Object[] { paramString }), paramContext, null);
        if (l != 0L) {
          return;
        }
        paramContext.executeAndWait();
        paramContext = localSharedPreferences.edit();
        paramContext.putLong((String)localObject, System.currentTimeMillis());
        paramContext.apply();
        return;
      }
      catch (JSONException paramContext)
      {
        throw new FacebookException("An error occurred while publishing install.", paramContext);
      }
      throw new IllegalArgumentException("Both context and applicationId must be non-null");
    }
    catch (Exception paramContext)
    {
      Utility.logd("Facebook-publish", paramContext);
    }
  }
  
  public static void publishInstallAsync(Context paramContext, final String paramString)
  {
    paramContext = paramContext.getApplicationContext();
    getExecutor().execute(new Runnable()
    {
      public void run()
      {
        FacebookSdk.publishInstallAndWaitForResponse(val$applicationContext, paramString);
      }
    });
  }
  
  public static void removeLoggingBehavior(LoggingBehavior paramLoggingBehavior)
  {
    synchronized (loggingBehaviors)
    {
      loggingBehaviors.remove(paramLoggingBehavior);
      return;
    }
  }
  
  @Deprecated
  public static void sdkInitialize(Context paramContext)
  {
    try
    {
      sdkInitialize(paramContext, null);
      return;
    }
    finally
    {
      paramContext = finally;
      throw paramContext;
    }
  }
  
  @Deprecated
  public static void sdkInitialize(Context paramContext, int paramInt)
  {
    try
    {
      sdkInitialize(paramContext, paramInt, null);
      return;
    }
    finally
    {
      paramContext = finally;
      throw paramContext;
    }
  }
  
  @Deprecated
  public static void sdkInitialize(Context paramContext, int paramInt, InitializeCallback paramInitializeCallback)
  {
    try
    {
      if ((sdkInitialized.booleanValue()) && (paramInt != callbackRequestCodeOffset)) {
        throw new FacebookException("The callback request code offset can't be updated once the SDK is initialized. Call FacebookSdk.setCallbackRequestCodeOffset inside your Application.onCreate method");
      }
      if (paramInt < 0) {
        throw new FacebookException("The callback request code offset can't be negative.");
      }
      callbackRequestCodeOffset = paramInt;
      sdkInitialize(paramContext, paramInitializeCallback);
      return;
    }
    finally {}
  }
  
  @Deprecated
  public static void sdkInitialize(final Context paramContext, InitializeCallback paramInitializeCallback)
  {
    try
    {
      if (sdkInitialized.booleanValue())
      {
        if (paramInitializeCallback != null) {
          paramInitializeCallback.onInitialized();
        }
        return;
      }
      Validate.notNull(paramContext, "applicationContext");
      Validate.hasFacebookActivity(paramContext, false);
      Validate.hasInternetPermissions(paramContext, false);
      applicationContext = paramContext.getApplicationContext();
      loadDefaultsFromMetadata(applicationContext);
      if (Utility.isNullOrEmpty(applicationId)) {
        throw new FacebookException("A valid Facebook app id must be set in the AndroidManifest.xml or set by calling FacebookSdk.setApplicationId before initializing the sdk.");
      }
      if (((applicationContext instanceof Application)) && (autoLogAppEventsEnabled.booleanValue())) {
        ActivityLifecycleTracker.startTracking((Application)applicationContext, applicationId);
      }
      sdkInitialized = Boolean.valueOf(true);
      FetchedAppSettingsManager.loadAppSettingsAsync();
      NativeProtocol.updateAllAvailableProtocolVersionsAsync();
      BoltsMeasurementEventListener.getInstance(applicationContext);
      cacheDir = new LockOnGetVariable(new Callable()
      {
        public File call()
          throws Exception
        {
          return FacebookSdk.applicationContext.getCacheDir();
        }
      });
      paramContext = new FutureTask(new Callable()
      {
        public Void call()
          throws Exception
        {
          AccessTokenManager.getInstance().loadCurrentAccessToken();
          ProfileManager.getInstance().loadCurrentProfile();
          if ((AccessToken.isCurrentAccessTokenActive()) && (Profile.getCurrentProfile() == null)) {
            Profile.fetchProfileForCurrentAccessToken();
          }
          if (val$callback != null) {
            val$callback.onInitialized();
          }
          AppEventsLogger.initializeLib(FacebookSdk.applicationContext, FacebookSdk.applicationId);
          AppEventsLogger.newLogger(paramContext.getApplicationContext()).flush();
          return null;
        }
      });
      getExecutor().execute(paramContext);
      return;
    }
    finally {}
  }
  
  public static void setApplicationId(String paramString)
  {
    applicationId = paramString;
  }
  
  public static void setApplicationName(String paramString)
  {
    applicationName = paramString;
  }
  
  public static void setAutoLogAppEventsEnabled(boolean paramBoolean)
  {
    autoLogAppEventsEnabled = Boolean.valueOf(paramBoolean);
  }
  
  public static void setCacheDir(File paramFile)
  {
    cacheDir = new LockOnGetVariable(paramFile);
  }
  
  public static void setClientToken(String paramString)
  {
    appClientToken = paramString;
  }
  
  public static void setExecutor(Executor paramExecutor)
  {
    Validate.notNull(paramExecutor, "executor");
    synchronized (LOCK)
    {
      executor = paramExecutor;
      return;
    }
  }
  
  public static void setFacebookDomain(String paramString)
  {
    Log.w(TAG, "WARNING: Calling setFacebookDomain from non-DEBUG code.");
    facebookDomain = paramString;
  }
  
  public static void setGraphApiVersion(String paramString)
  {
    Log.w(TAG, "WARNING: Calling setGraphApiVersion from non-DEBUG code.");
    if ((!Utility.isNullOrEmpty(paramString)) && (!graphApiVersion.equals(paramString))) {
      graphApiVersion = paramString;
    }
  }
  
  public static void setIsDebugEnabled(boolean paramBoolean)
  {
    isDebugEnabled = paramBoolean;
  }
  
  public static void setLegacyTokenUpgradeSupported(boolean paramBoolean)
  {
    isLegacyTokenUpgradeSupported = paramBoolean;
  }
  
  public static void setLimitEventAndDataUsage(Context paramContext, boolean paramBoolean)
  {
    paramContext.getSharedPreferences("com.facebook.sdk.appEventPreferences", 0).edit().putBoolean("limitEventUsage", paramBoolean).apply();
  }
  
  public static void setOnProgressThreshold(long paramLong)
  {
    onProgressThreshold.set(paramLong);
  }
  
  private static void updateGraphDebugBehavior()
  {
    if ((loggingBehaviors.contains(LoggingBehavior.GRAPH_API_DEBUG_INFO)) && (!loggingBehaviors.contains(LoggingBehavior.GRAPH_API_DEBUG_WARNING))) {
      loggingBehaviors.add(LoggingBehavior.GRAPH_API_DEBUG_WARNING);
    }
  }
  
  public static abstract interface InitializeCallback
  {
    public abstract void onInitialized();
  }
}
