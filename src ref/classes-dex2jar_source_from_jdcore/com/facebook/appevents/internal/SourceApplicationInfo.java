package com.facebook.appevents.internal;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import bolts.AppLinks;
import com.facebook.FacebookSdk;

class SourceApplicationInfo
{
  private static final String CALL_APPLICATION_PACKAGE_KEY = "com.facebook.appevents.SourceApplicationInfo.callingApplicationPackage";
  private static final String OPENED_BY_APP_LINK_KEY = "com.facebook.appevents.SourceApplicationInfo.openedByApplink";
  private static final String SOURCE_APPLICATION_HAS_BEEN_SET_BY_THIS_INTENT = "_fbSourceApplicationHasBeenSet";
  private String callingApplicationPackage;
  private boolean openedByAppLink;
  
  private SourceApplicationInfo(String paramString, boolean paramBoolean)
  {
    callingApplicationPackage = paramString;
    openedByAppLink = paramBoolean;
  }
  
  public static void clearSavedSourceApplicationInfoFromDisk()
  {
    SharedPreferences.Editor localEditor = PreferenceManager.getDefaultSharedPreferences(FacebookSdk.getApplicationContext()).edit();
    localEditor.remove("com.facebook.appevents.SourceApplicationInfo.callingApplicationPackage");
    localEditor.remove("com.facebook.appevents.SourceApplicationInfo.openedByApplink");
    localEditor.apply();
  }
  
  public static SourceApplicationInfo getStoredSourceApplicatioInfo()
  {
    SharedPreferences localSharedPreferences = PreferenceManager.getDefaultSharedPreferences(FacebookSdk.getApplicationContext());
    if (!localSharedPreferences.contains("com.facebook.appevents.SourceApplicationInfo.callingApplicationPackage")) {
      return null;
    }
    return new SourceApplicationInfo(localSharedPreferences.getString("com.facebook.appevents.SourceApplicationInfo.callingApplicationPackage", null), localSharedPreferences.getBoolean("com.facebook.appevents.SourceApplicationInfo.openedByApplink", false));
  }
  
  public String getCallingApplicationPackage()
  {
    return callingApplicationPackage;
  }
  
  public boolean isOpenedByAppLink()
  {
    return openedByAppLink;
  }
  
  public String toString()
  {
    String str = "Unclassified";
    if (openedByAppLink) {
      str = "Applink";
    }
    if (callingApplicationPackage != null)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(str);
      localStringBuilder.append("(");
      localStringBuilder.append(callingApplicationPackage);
      localStringBuilder.append(")");
      return localStringBuilder.toString();
    }
    return str;
  }
  
  public void writeSourceApplicationInfoToDisk()
  {
    SharedPreferences.Editor localEditor = PreferenceManager.getDefaultSharedPreferences(FacebookSdk.getApplicationContext()).edit();
    localEditor.putString("com.facebook.appevents.SourceApplicationInfo.callingApplicationPackage", callingApplicationPackage);
    localEditor.putBoolean("com.facebook.appevents.SourceApplicationInfo.openedByApplink", openedByAppLink);
    localEditor.apply();
  }
  
  public static class Factory
  {
    public Factory() {}
    
    public static SourceApplicationInfo create(Activity paramActivity)
    {
      Object localObject1 = "";
      Object localObject2 = paramActivity.getCallingActivity();
      if (localObject2 != null)
      {
        localObject2 = ((ComponentName)localObject2).getPackageName();
        localObject1 = localObject2;
        if (((String)localObject2).equals(paramActivity.getPackageName())) {
          return null;
        }
      }
      localObject2 = paramActivity.getIntent();
      boolean bool2 = false;
      paramActivity = (Activity)localObject1;
      boolean bool1 = bool2;
      if (localObject2 != null)
      {
        paramActivity = (Activity)localObject1;
        bool1 = bool2;
        if (!((Intent)localObject2).getBooleanExtra("_fbSourceApplicationHasBeenSet", false))
        {
          ((Intent)localObject2).putExtra("_fbSourceApplicationHasBeenSet", true);
          Bundle localBundle = AppLinks.getAppLinkData((Intent)localObject2);
          paramActivity = (Activity)localObject1;
          bool1 = bool2;
          if (localBundle != null)
          {
            paramActivity = localBundle.getBundle("referer_app_link");
            if (paramActivity != null) {
              localObject1 = paramActivity.getString("package");
            }
            bool1 = true;
            paramActivity = (Activity)localObject1;
          }
        }
      }
      ((Intent)localObject2).putExtra("_fbSourceApplicationHasBeenSet", true);
      return new SourceApplicationInfo(paramActivity, bool1, null);
    }
  }
}
