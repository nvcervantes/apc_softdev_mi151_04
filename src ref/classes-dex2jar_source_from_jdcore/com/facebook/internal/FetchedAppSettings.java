package com.facebook.internal;

import android.net.Uri;
import java.util.EnumSet;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public final class FetchedAppSettings
{
  private boolean IAPAutomaticLoggingEnabled;
  private boolean automaticLoggingEnabled;
  private boolean customTabsEnabled;
  private Map<String, Map<String, DialogFeatureConfig>> dialogConfigMap;
  private FacebookRequestErrorClassification errorClassification;
  private String nuxContent;
  private boolean nuxEnabled;
  private String sdkUpdateMessage;
  private int sessionTimeoutInSeconds;
  private String smartLoginBookmarkIconURL;
  private String smartLoginMenuIconURL;
  private EnumSet<SmartLoginOption> smartLoginOptions;
  private boolean supportsImplicitLogging;
  
  public FetchedAppSettings(boolean paramBoolean1, String paramString1, boolean paramBoolean2, boolean paramBoolean3, int paramInt, EnumSet<SmartLoginOption> paramEnumSet, Map<String, Map<String, DialogFeatureConfig>> paramMap, boolean paramBoolean4, FacebookRequestErrorClassification paramFacebookRequestErrorClassification, String paramString2, String paramString3, boolean paramBoolean5, String paramString4)
  {
    supportsImplicitLogging = paramBoolean1;
    nuxContent = paramString1;
    nuxEnabled = paramBoolean2;
    customTabsEnabled = paramBoolean3;
    dialogConfigMap = paramMap;
    errorClassification = paramFacebookRequestErrorClassification;
    sessionTimeoutInSeconds = paramInt;
    automaticLoggingEnabled = paramBoolean4;
    smartLoginOptions = paramEnumSet;
    smartLoginBookmarkIconURL = paramString2;
    smartLoginMenuIconURL = paramString3;
    IAPAutomaticLoggingEnabled = paramBoolean5;
    sdkUpdateMessage = paramString4;
  }
  
  public static DialogFeatureConfig getDialogFeatureConfig(String paramString1, String paramString2, String paramString3)
  {
    if (!Utility.isNullOrEmpty(paramString2))
    {
      if (Utility.isNullOrEmpty(paramString3)) {
        return null;
      }
      paramString1 = FetchedAppSettingsManager.getAppSettingsWithoutQuery(paramString1);
      if (paramString1 != null)
      {
        paramString1 = (Map)paramString1.getDialogConfigurations().get(paramString2);
        if (paramString1 != null) {
          return (DialogFeatureConfig)paramString1.get(paramString3);
        }
      }
      return null;
    }
    return null;
  }
  
  public boolean getAutomaticLoggingEnabled()
  {
    return automaticLoggingEnabled;
  }
  
  public boolean getCustomTabsEnabled()
  {
    return customTabsEnabled;
  }
  
  public Map<String, Map<String, DialogFeatureConfig>> getDialogConfigurations()
  {
    return dialogConfigMap;
  }
  
  public FacebookRequestErrorClassification getErrorClassification()
  {
    return errorClassification;
  }
  
  public boolean getIAPAutomaticLoggingEnabled()
  {
    return IAPAutomaticLoggingEnabled;
  }
  
  public String getNuxContent()
  {
    return nuxContent;
  }
  
  public boolean getNuxEnabled()
  {
    return nuxEnabled;
  }
  
  public String getSdkUpdateMessage()
  {
    return sdkUpdateMessage;
  }
  
  public int getSessionTimeoutInSeconds()
  {
    return sessionTimeoutInSeconds;
  }
  
  public String getSmartLoginBookmarkIconURL()
  {
    return smartLoginBookmarkIconURL;
  }
  
  public String getSmartLoginMenuIconURL()
  {
    return smartLoginMenuIconURL;
  }
  
  public EnumSet<SmartLoginOption> getSmartLoginOptions()
  {
    return smartLoginOptions;
  }
  
  public boolean supportsImplicitLogging()
  {
    return supportsImplicitLogging;
  }
  
  public static class DialogFeatureConfig
  {
    private static final String DIALOG_CONFIG_DIALOG_NAME_FEATURE_NAME_SEPARATOR = "\\|";
    private static final String DIALOG_CONFIG_NAME_KEY = "name";
    private static final String DIALOG_CONFIG_URL_KEY = "url";
    private static final String DIALOG_CONFIG_VERSIONS_KEY = "versions";
    private String dialogName;
    private Uri fallbackUrl;
    private String featureName;
    private int[] featureVersionSpec;
    
    private DialogFeatureConfig(String paramString1, String paramString2, Uri paramUri, int[] paramArrayOfInt)
    {
      dialogName = paramString1;
      featureName = paramString2;
      fallbackUrl = paramUri;
      featureVersionSpec = paramArrayOfInt;
    }
    
    public static DialogFeatureConfig parseDialogConfig(JSONObject paramJSONObject)
    {
      String str1 = paramJSONObject.optString("name");
      boolean bool = Utility.isNullOrEmpty(str1);
      Uri localUri = null;
      if (bool) {
        return null;
      }
      Object localObject = str1.split("\\|");
      if (localObject.length != 2) {
        return null;
      }
      str1 = localObject[0];
      localObject = localObject[1];
      if (!Utility.isNullOrEmpty(str1))
      {
        if (Utility.isNullOrEmpty((String)localObject)) {
          return null;
        }
        String str2 = paramJSONObject.optString("url");
        if (!Utility.isNullOrEmpty(str2)) {
          localUri = Uri.parse(str2);
        }
        return new DialogFeatureConfig(str1, (String)localObject, localUri, parseVersionSpec(paramJSONObject.optJSONArray("versions")));
      }
      return null;
    }
    
    private static int[] parseVersionSpec(JSONArray paramJSONArray)
    {
      if (paramJSONArray != null)
      {
        int m = paramJSONArray.length();
        int[] arrayOfInt2 = new int[m];
        int j = 0;
        for (;;)
        {
          Object localObject = arrayOfInt2;
          if (j >= m) {
            break;
          }
          int k = paramJSONArray.optInt(j, -1);
          int i = k;
          if (k == -1)
          {
            localObject = paramJSONArray.optString(j);
            i = k;
            if (!Utility.isNullOrEmpty((String)localObject)) {
              try
              {
                i = Integer.parseInt((String)localObject);
              }
              catch (NumberFormatException localNumberFormatException)
              {
                Utility.logd("FacebookSDK", localNumberFormatException);
                i = -1;
              }
            }
          }
          arrayOfInt2[j] = i;
          j += 1;
        }
      }
      int[] arrayOfInt1 = null;
      return arrayOfInt1;
    }
    
    public String getDialogName()
    {
      return dialogName;
    }
    
    public Uri getFallbackUrl()
    {
      return fallbackUrl;
    }
    
    public String getFeatureName()
    {
      return featureName;
    }
    
    public int[] getVersionSpec()
    {
      return featureVersionSpec;
    }
  }
}
