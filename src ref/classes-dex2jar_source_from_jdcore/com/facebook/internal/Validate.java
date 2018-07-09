package com.facebook.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Looper;
import android.util.Log;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.FacebookSdkNotInitializedException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public final class Validate
{
  private static final String CONTENT_PROVIDER_BASE = "com.facebook.app.FacebookContentProvider";
  private static final String CONTENT_PROVIDER_NOT_FOUND_REASON = "A ContentProvider for this app was not set up in the AndroidManifest.xml, please add %s as a provider to your AndroidManifest.xml file. See https://developers.facebook.com/docs/sharing/android for more info.";
  private static final String CUSTOM_TAB_REDIRECT_ACTIVITY_NOT_FOUND_REASON = "FacebookActivity is declared incorrectly in the AndroidManifest.xml, please add com.facebook.FacebookActivity to your AndroidManifest.xml file. See https://developers.facebook.com/docs/android/getting-started for more info.";
  private static final String FACEBOOK_ACTIVITY_NOT_FOUND_REASON = "FacebookActivity is not declared in the AndroidManifest.xml, please add com.facebook.FacebookActivity to your AndroidManifest.xml file. See https://developers.facebook.com/docs/android/getting-started for more info.";
  private static final String NO_INTERNET_PERMISSION_REASON = "No internet permissions granted for the app, please add <uses-permission android:name=\"android.permission.INTERNET\" /> to your AndroidManifest.xml.";
  private static final String TAG = "com.facebook.internal.Validate";
  
  public Validate() {}
  
  public static void checkCustomTabRedirectActivity(Context paramContext)
  {
    checkCustomTabRedirectActivity(paramContext, true);
  }
  
  public static void checkCustomTabRedirectActivity(Context paramContext, boolean paramBoolean)
  {
    if (!hasCustomTabRedirectActivity(paramContext))
    {
      if (paramBoolean) {
        throw new IllegalStateException("FacebookActivity is declared incorrectly in the AndroidManifest.xml, please add com.facebook.FacebookActivity to your AndroidManifest.xml file. See https://developers.facebook.com/docs/android/getting-started for more info.");
      }
      Log.w(TAG, "FacebookActivity is declared incorrectly in the AndroidManifest.xml, please add com.facebook.FacebookActivity to your AndroidManifest.xml file. See https://developers.facebook.com/docs/android/getting-started for more info.");
    }
  }
  
  public static void containsNoNullOrEmpty(Collection<String> paramCollection, String paramString)
  {
    notNull(paramCollection, paramString);
    paramCollection = paramCollection.iterator();
    while (paramCollection.hasNext())
    {
      String str = (String)paramCollection.next();
      if (str == null)
      {
        paramCollection = new StringBuilder();
        paramCollection.append("Container '");
        paramCollection.append(paramString);
        paramCollection.append("' cannot contain null values");
        throw new NullPointerException(paramCollection.toString());
      }
      if (str.length() == 0)
      {
        paramCollection = new StringBuilder();
        paramCollection.append("Container '");
        paramCollection.append(paramString);
        paramCollection.append("' cannot contain empty values");
        throw new IllegalArgumentException(paramCollection.toString());
      }
    }
  }
  
  public static <T> void containsNoNulls(Collection<T> paramCollection, String paramString)
  {
    notNull(paramCollection, paramString);
    paramCollection = paramCollection.iterator();
    while (paramCollection.hasNext()) {
      if (paramCollection.next() == null)
      {
        paramCollection = new StringBuilder();
        paramCollection.append("Container '");
        paramCollection.append(paramString);
        paramCollection.append("' cannot contain null values");
        throw new NullPointerException(paramCollection.toString());
      }
    }
  }
  
  public static String hasAppID()
  {
    String str = FacebookSdk.getApplicationId();
    if (str == null) {
      throw new IllegalStateException("No App ID found, please set the App ID.");
    }
    return str;
  }
  
  public static boolean hasBluetoothPermission(Context paramContext)
  {
    return (hasPermission(paramContext, "android.permission.BLUETOOTH")) && (hasPermission(paramContext, "android.permission.BLUETOOTH_ADMIN"));
  }
  
  public static boolean hasChangeWifiStatePermission(Context paramContext)
  {
    return hasPermission(paramContext, "android.permission.CHANGE_WIFI_STATE");
  }
  
  public static String hasClientToken()
  {
    String str = FacebookSdk.getClientToken();
    if (str == null) {
      throw new IllegalStateException("No Client Token found, please set the Client Token.");
    }
    return str;
  }
  
  public static void hasContentProvider(Context paramContext)
  {
    notNull(paramContext, "context");
    String str = hasAppID();
    paramContext = paramContext.getPackageManager();
    if (paramContext != null)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("com.facebook.app.FacebookContentProvider");
      localStringBuilder.append(str);
      str = localStringBuilder.toString();
      if (paramContext.resolveContentProvider(str, 0) == null) {
        throw new IllegalStateException(String.format("A ContentProvider for this app was not set up in the AndroidManifest.xml, please add %s as a provider to your AndroidManifest.xml file. See https://developers.facebook.com/docs/sharing/android for more info.", new Object[] { str }));
      }
    }
  }
  
  public static boolean hasCustomTabRedirectActivity(Context paramContext)
  {
    notNull(paramContext, "context");
    Object localObject1 = paramContext.getPackageManager();
    Object localObject2;
    if (localObject1 != null)
    {
      localObject2 = new Intent();
      ((Intent)localObject2).setAction("android.intent.action.VIEW");
      ((Intent)localObject2).addCategory("android.intent.category.DEFAULT");
      ((Intent)localObject2).addCategory("android.intent.category.BROWSABLE");
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("fb");
      localStringBuilder.append(FacebookSdk.getApplicationId());
      localStringBuilder.append("://authorize");
      ((Intent)localObject2).setData(Uri.parse(localStringBuilder.toString()));
      localObject1 = ((PackageManager)localObject1).queryIntentActivities((Intent)localObject2, 64);
    }
    else
    {
      localObject1 = null;
    }
    boolean bool = false;
    if (localObject1 != null)
    {
      localObject1 = ((List)localObject1).iterator();
      bool = false;
      while (((Iterator)localObject1).hasNext())
      {
        localObject2 = nextactivityInfo;
        if ((name.equals("com.facebook.CustomTabActivity")) && (packageName.equals(paramContext.getPackageName()))) {
          bool = true;
        } else {
          return false;
        }
      }
    }
    return bool;
  }
  
  public static void hasFacebookActivity(Context paramContext)
  {
    hasFacebookActivity(paramContext, true);
  }
  
  public static void hasFacebookActivity(Context paramContext, boolean paramBoolean)
  {
    notNull(paramContext, "context");
    PackageManager localPackageManager = paramContext.getPackageManager();
    if (localPackageManager != null) {
      paramContext = new ComponentName(paramContext, "com.facebook.FacebookActivity");
    }
    try
    {
      paramContext = localPackageManager.getActivityInfo(paramContext, 1);
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      for (;;) {}
    }
    paramContext = null;
    if (paramContext == null)
    {
      if (paramBoolean) {
        throw new IllegalStateException("FacebookActivity is not declared in the AndroidManifest.xml, please add com.facebook.FacebookActivity to your AndroidManifest.xml file. See https://developers.facebook.com/docs/android/getting-started for more info.");
      }
      Log.w(TAG, "FacebookActivity is not declared in the AndroidManifest.xml, please add com.facebook.FacebookActivity to your AndroidManifest.xml file. See https://developers.facebook.com/docs/android/getting-started for more info.");
    }
  }
  
  public static void hasInternetPermissions(Context paramContext)
  {
    hasInternetPermissions(paramContext, true);
  }
  
  public static void hasInternetPermissions(Context paramContext, boolean paramBoolean)
  {
    notNull(paramContext, "context");
    if (paramContext.checkCallingOrSelfPermission("android.permission.INTERNET") == -1)
    {
      if (paramBoolean) {
        throw new IllegalStateException("No internet permissions granted for the app, please add <uses-permission android:name=\"android.permission.INTERNET\" /> to your AndroidManifest.xml.");
      }
      Log.w(TAG, "No internet permissions granted for the app, please add <uses-permission android:name=\"android.permission.INTERNET\" /> to your AndroidManifest.xml.");
    }
  }
  
  public static boolean hasLocationPermission(Context paramContext)
  {
    return (hasPermission(paramContext, "android.permission.ACCESS_COARSE_LOCATION")) || (hasPermission(paramContext, "android.permission.ACCESS_FINE_LOCATION"));
  }
  
  public static boolean hasPermission(Context paramContext, String paramString)
  {
    return paramContext.checkCallingOrSelfPermission(paramString) == 0;
  }
  
  public static boolean hasWiFiPermission(Context paramContext)
  {
    return hasPermission(paramContext, "android.permission.ACCESS_WIFI_STATE");
  }
  
  public static <T> void notEmpty(Collection<T> paramCollection, String paramString)
  {
    if (paramCollection.isEmpty())
    {
      paramCollection = new StringBuilder();
      paramCollection.append("Container '");
      paramCollection.append(paramString);
      paramCollection.append("' cannot be empty");
      throw new IllegalArgumentException(paramCollection.toString());
    }
  }
  
  public static <T> void notEmptyAndContainsNoNulls(Collection<T> paramCollection, String paramString)
  {
    containsNoNulls(paramCollection, paramString);
    notEmpty(paramCollection, paramString);
  }
  
  public static void notNull(Object paramObject, String paramString)
  {
    if (paramObject == null)
    {
      paramObject = new StringBuilder();
      paramObject.append("Argument '");
      paramObject.append(paramString);
      paramObject.append("' cannot be null");
      throw new NullPointerException(paramObject.toString());
    }
  }
  
  public static void notNullOrEmpty(String paramString1, String paramString2)
  {
    if (Utility.isNullOrEmpty(paramString1))
    {
      paramString1 = new StringBuilder();
      paramString1.append("Argument '");
      paramString1.append(paramString2);
      paramString1.append("' cannot be null or empty");
      throw new IllegalArgumentException(paramString1.toString());
    }
  }
  
  public static void oneOf(Object paramObject, String paramString, Object... paramVarArgs)
  {
    int i = 0;
    int j = paramVarArgs.length;
    while (i < j)
    {
      Object localObject = paramVarArgs[i];
      if (localObject != null)
      {
        if (!localObject.equals(paramObject)) {}
      }
      else if (paramObject == null) {
        return;
      }
      i += 1;
    }
    paramObject = new StringBuilder();
    paramObject.append("Argument '");
    paramObject.append(paramString);
    paramObject.append("' was not one of the allowed values");
    throw new IllegalArgumentException(paramObject.toString());
  }
  
  public static void runningOnUiThread()
  {
    if (!Looper.getMainLooper().equals(Looper.myLooper())) {
      throw new FacebookException("This method should be called from the UI thread");
    }
  }
  
  public static void sdkInitialized()
  {
    if (!FacebookSdk.isInitialized()) {
      throw new FacebookSdkNotInitializedException("The SDK has not been initialized, make sure to call FacebookSdk.sdkInitialize() first.");
    }
  }
}
