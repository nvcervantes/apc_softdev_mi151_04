package com.facebook.appevents.internal;

import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.internal.FetchedAppSettings;
import com.facebook.internal.FetchedAppSettingsManager;
import com.facebook.internal.Utility;
import com.facebook.internal.Validate;

public class AutomaticAnalyticsLogger
{
  private static final String INAPP_PURCHASE_DATA = "INAPP_PURCHASE_DATA";
  private static final String TAG = AutomaticAnalyticsLogger.class.getCanonicalName();
  @Nullable
  private static Object inAppBillingObj;
  
  public AutomaticAnalyticsLogger() {}
  
  public static boolean isImplicitPurchaseLoggingEnabled()
  {
    FetchedAppSettings localFetchedAppSettings = FetchedAppSettingsManager.getAppSettingsWithoutQuery(FacebookSdk.getApplicationId());
    boolean bool2 = false;
    if (localFetchedAppSettings == null) {
      return false;
    }
    boolean bool1 = bool2;
    if (FacebookSdk.getAutoLogAppEventsEnabled())
    {
      bool1 = bool2;
      if (localFetchedAppSettings.getIAPAutomaticLoggingEnabled()) {
        bool1 = true;
      }
    }
    return bool1;
  }
  
  public static void logActivateAppEvent()
  {
    Context localContext = FacebookSdk.getApplicationContext();
    String str = FacebookSdk.getApplicationId();
    boolean bool = FacebookSdk.getAutoLogAppEventsEnabled();
    Validate.notNull(localContext, "context");
    if (bool)
    {
      if ((localContext instanceof Application))
      {
        AppEventsLogger.activateApp((Application)localContext, str);
        return;
      }
      Log.w(TAG, "Automatic logging of basic events will not happen, because FacebookSdk.getApplicationContext() returns object that is not instance of android.app.Application. Make sure you call FacebookSdk.sdkInitialize() from Application class and pass application context.");
    }
  }
  
  public static void logActivityTimeSpentEvent(String paramString, long paramLong)
  {
    Object localObject1 = FacebookSdk.getApplicationContext();
    Object localObject2 = FacebookSdk.getApplicationId();
    Validate.notNull(localObject1, "context");
    localObject2 = FetchedAppSettingsManager.queryAppSettings((String)localObject2, false);
    if ((localObject2 != null) && (((FetchedAppSettings)localObject2).getAutomaticLoggingEnabled()) && (paramLong > 0L))
    {
      localObject1 = AppEventsLogger.newLogger((Context)localObject1);
      localObject2 = new Bundle(1);
      ((Bundle)localObject2).putCharSequence("fb_aa_time_spent_view_name", paramString);
      ((AppEventsLogger)localObject1).logEvent("fb_aa_time_spent_on_view", paramLong, (Bundle)localObject2);
    }
  }
  
  public static boolean logInAppPurchaseEvent(Context paramContext, int paramInt, final Intent paramIntent)
  {
    if ((paramIntent != null) && (isImplicitPurchaseLoggingEnabled()))
    {
      paramIntent = paramIntent.getStringExtra("INAPP_PURCHASE_DATA");
      if (paramInt == -1)
      {
        paramIntent = new ServiceConnection()
        {
          /* Error */
          public void onServiceConnected(ComponentName paramAnonymousComponentName, android.os.IBinder paramAnonymousIBinder)
          {
            // Byte code:
            //   0: aload_0
            //   1: getfield 19	com/facebook/appevents/internal/AutomaticAnalyticsLogger$1:val$context	Landroid/content/Context;
            //   4: aload_2
            //   5: invokestatic 35	com/facebook/appevents/internal/InAppPurchaseEventManager:getServiceInterface	(Landroid/content/Context;Landroid/os/IBinder;)Ljava/lang/Object;
            //   8: invokestatic 39	com/facebook/appevents/internal/AutomaticAnalyticsLogger:access$002	(Ljava/lang/Object;)Ljava/lang/Object;
            //   11: pop
            //   12: new 41	org/json/JSONObject
            //   15: dup
            //   16: aload_0
            //   17: getfield 21	com/facebook/appevents/internal/AutomaticAnalyticsLogger$1:val$purchaseData	Ljava/lang/String;
            //   20: invokespecial 44	org/json/JSONObject:<init>	(Ljava/lang/String;)V
            //   23: astore_1
            //   24: aload_1
            //   25: ldc 46
            //   27: invokevirtual 50	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
            //   30: astore_2
            //   31: aload_1
            //   32: ldc 52
            //   34: invokevirtual 56	org/json/JSONObject:has	(Ljava/lang/String;)Z
            //   37: istore_3
            //   38: aload_0
            //   39: getfield 19	com/facebook/appevents/internal/AutomaticAnalyticsLogger$1:val$context	Landroid/content/Context;
            //   42: aload_2
            //   43: invokestatic 60	com/facebook/appevents/internal/AutomaticAnalyticsLogger:access$000	()Ljava/lang/Object;
            //   46: iload_3
            //   47: invokestatic 64	com/facebook/appevents/internal/InAppPurchaseEventManager:getPurchaseDetails	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/Object;Z)Ljava/lang/String;
            //   50: astore 4
            //   52: aload 4
            //   54: ldc 66
            //   56: invokevirtual 72	java/lang/String:equals	(Ljava/lang/Object;)Z
            //   59: istore_3
            //   60: iload_3
            //   61: ifeq +12 -> 73
            //   64: aload_0
            //   65: getfield 19	com/facebook/appevents/internal/AutomaticAnalyticsLogger$1:val$context	Landroid/content/Context;
            //   68: aload_0
            //   69: invokevirtual 78	android/content/Context:unbindService	(Landroid/content/ServiceConnection;)V
            //   72: return
            //   73: new 41	org/json/JSONObject
            //   76: dup
            //   77: aload 4
            //   79: invokespecial 44	org/json/JSONObject:<init>	(Ljava/lang/String;)V
            //   82: astore 4
            //   84: aload_0
            //   85: getfield 19	com/facebook/appevents/internal/AutomaticAnalyticsLogger$1:val$context	Landroid/content/Context;
            //   88: invokestatic 84	com/facebook/appevents/AppEventsLogger:newLogger	(Landroid/content/Context;)Lcom/facebook/appevents/AppEventsLogger;
            //   91: astore 5
            //   93: new 86	android/os/Bundle
            //   96: dup
            //   97: iconst_1
            //   98: invokespecial 89	android/os/Bundle:<init>	(I)V
            //   101: astore 6
            //   103: aload 6
            //   105: ldc 91
            //   107: aload_2
            //   108: invokevirtual 95	android/os/Bundle:putCharSequence	(Ljava/lang/String;Ljava/lang/CharSequence;)V
            //   111: aload 6
            //   113: ldc 97
            //   115: aload_1
            //   116: ldc 99
            //   118: invokevirtual 50	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
            //   121: invokevirtual 95	android/os/Bundle:putCharSequence	(Ljava/lang/String;Ljava/lang/CharSequence;)V
            //   124: aload 6
            //   126: ldc 101
            //   128: aload_1
            //   129: ldc 103
            //   131: invokevirtual 50	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
            //   134: invokevirtual 95	android/os/Bundle:putCharSequence	(Ljava/lang/String;Ljava/lang/CharSequence;)V
            //   137: aload 6
            //   139: ldc 105
            //   141: aload_1
            //   142: ldc 107
            //   144: invokevirtual 50	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
            //   147: invokevirtual 95	android/os/Bundle:putCharSequence	(Ljava/lang/String;Ljava/lang/CharSequence;)V
            //   150: aload 6
            //   152: ldc 109
            //   154: aload_1
            //   155: ldc 111
            //   157: invokevirtual 50	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
            //   160: invokevirtual 95	android/os/Bundle:putCharSequence	(Ljava/lang/String;Ljava/lang/CharSequence;)V
            //   163: aload 6
            //   165: ldc 113
            //   167: aload 4
            //   169: ldc 115
            //   171: invokevirtual 50	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
            //   174: invokevirtual 95	android/os/Bundle:putCharSequence	(Ljava/lang/String;Ljava/lang/CharSequence;)V
            //   177: aload 6
            //   179: ldc 117
            //   181: aload 4
            //   183: ldc 119
            //   185: invokevirtual 50	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
            //   188: invokevirtual 95	android/os/Bundle:putCharSequence	(Ljava/lang/String;Ljava/lang/CharSequence;)V
            //   191: aload 6
            //   193: ldc 121
            //   195: aload 4
            //   197: ldc 123
            //   199: invokevirtual 50	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
            //   202: invokevirtual 95	android/os/Bundle:putCharSequence	(Ljava/lang/String;Ljava/lang/CharSequence;)V
            //   205: aload 6
            //   207: ldc 125
            //   209: aload_1
            //   210: ldc 52
            //   212: iconst_0
            //   213: invokevirtual 129	org/json/JSONObject:optBoolean	(Ljava/lang/String;Z)Z
            //   216: invokestatic 135	java/lang/Boolean:toString	(Z)Ljava/lang/String;
            //   219: invokevirtual 95	android/os/Bundle:putCharSequence	(Ljava/lang/String;Ljava/lang/CharSequence;)V
            //   222: aload 6
            //   224: ldc -119
            //   226: aload 4
            //   228: ldc -117
            //   230: invokevirtual 142	org/json/JSONObject:optString	(Ljava/lang/String;)Ljava/lang/String;
            //   233: invokevirtual 95	android/os/Bundle:putCharSequence	(Ljava/lang/String;Ljava/lang/CharSequence;)V
            //   236: aload 6
            //   238: ldc -112
            //   240: aload 4
            //   242: ldc -110
            //   244: invokevirtual 142	org/json/JSONObject:optString	(Ljava/lang/String;)Ljava/lang/String;
            //   247: invokevirtual 95	android/os/Bundle:putCharSequence	(Ljava/lang/String;Ljava/lang/CharSequence;)V
            //   250: aload 6
            //   252: ldc -108
            //   254: aload 4
            //   256: ldc -106
            //   258: invokevirtual 142	org/json/JSONObject:optString	(Ljava/lang/String;)Ljava/lang/String;
            //   261: invokevirtual 95	android/os/Bundle:putCharSequence	(Ljava/lang/String;Ljava/lang/CharSequence;)V
            //   264: aload 6
            //   266: ldc -104
            //   268: aload 4
            //   270: ldc -102
            //   272: invokevirtual 142	org/json/JSONObject:optString	(Ljava/lang/String;)Ljava/lang/String;
            //   275: invokevirtual 95	android/os/Bundle:putCharSequence	(Ljava/lang/String;Ljava/lang/CharSequence;)V
            //   278: aload 5
            //   280: new 156	java/math/BigDecimal
            //   283: dup
            //   284: aload 4
            //   286: ldc -98
            //   288: invokevirtual 162	org/json/JSONObject:getLong	(Ljava/lang/String;)J
            //   291: l2d
            //   292: ldc2_w 163
            //   295: ddiv
            //   296: invokespecial 167	java/math/BigDecimal:<init>	(D)V
            //   299: aload 4
            //   301: ldc -87
            //   303: invokevirtual 50	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
            //   306: invokestatic 175	java/util/Currency:getInstance	(Ljava/lang/String;)Ljava/util/Currency;
            //   309: aload 6
            //   311: invokevirtual 179	com/facebook/appevents/AppEventsLogger:logPurchaseImplicitly	(Ljava/math/BigDecimal;Ljava/util/Currency;Landroid/os/Bundle;)V
            //   314: goto +18 -> 332
            //   317: astore_1
            //   318: goto +23 -> 341
            //   321: astore_1
            //   322: invokestatic 183	com/facebook/appevents/internal/AutomaticAnalyticsLogger:access$100	()Ljava/lang/String;
            //   325: ldc -71
            //   327: aload_1
            //   328: invokestatic 191	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
            //   331: pop
            //   332: aload_0
            //   333: getfield 19	com/facebook/appevents/internal/AutomaticAnalyticsLogger$1:val$context	Landroid/content/Context;
            //   336: aload_0
            //   337: invokevirtual 78	android/content/Context:unbindService	(Landroid/content/ServiceConnection;)V
            //   340: return
            //   341: aload_0
            //   342: getfield 19	com/facebook/appevents/internal/AutomaticAnalyticsLogger$1:val$context	Landroid/content/Context;
            //   345: aload_0
            //   346: invokevirtual 78	android/content/Context:unbindService	(Landroid/content/ServiceConnection;)V
            //   349: aload_1
            //   350: athrow
            // Local variable table:
            //   start	length	slot	name	signature
            //   0	351	0	this	1
            //   0	351	1	paramAnonymousComponentName	ComponentName
            //   0	351	2	paramAnonymousIBinder	android.os.IBinder
            //   37	24	3	bool	boolean
            //   50	250	4	localObject	Object
            //   91	188	5	localAppEventsLogger	AppEventsLogger
            //   101	209	6	localBundle	Bundle
            // Exception table:
            //   from	to	target	type
            //   12	60	317	finally
            //   73	314	317	finally
            //   322	332	317	finally
            //   12	60	321	org/json/JSONException
            //   73	314	321	org/json/JSONException
          }
          
          public void onServiceDisconnected(ComponentName paramAnonymousComponentName)
          {
            AutomaticAnalyticsLogger.access$002(null);
            Utility.logd(AutomaticAnalyticsLogger.TAG, "In-app billing service disconnected");
          }
        };
        Intent localIntent = new Intent("com.android.vending.billing.InAppBillingService.BIND");
        localIntent.setPackage("com.android.vending");
        paramContext.bindService(localIntent, paramIntent, 1);
      }
      return true;
    }
    return false;
  }
}
