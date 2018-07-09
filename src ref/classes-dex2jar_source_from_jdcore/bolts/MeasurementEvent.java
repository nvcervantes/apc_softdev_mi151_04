package bolts;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;

public class MeasurementEvent
{
  public static final String APP_LINK_NAVIGATE_IN_EVENT_NAME = "al_nav_in";
  public static final String APP_LINK_NAVIGATE_OUT_EVENT_NAME = "al_nav_out";
  public static final String MEASUREMENT_EVENT_ARGS_KEY = "event_args";
  public static final String MEASUREMENT_EVENT_NAME_KEY = "event_name";
  public static final String MEASUREMENT_EVENT_NOTIFICATION_NAME = "com.parse.bolts.measurement_event";
  private Context appContext;
  private Bundle args;
  private String name;
  
  private MeasurementEvent(Context paramContext, String paramString, Bundle paramBundle)
  {
    appContext = paramContext.getApplicationContext();
    name = paramString;
    args = paramBundle;
  }
  
  private static Bundle getApplinkLogData(Context paramContext, String paramString, Bundle paramBundle, Intent paramIntent)
  {
    Bundle localBundle = new Bundle();
    paramContext = paramIntent.resolveActivity(paramContext.getPackageManager());
    if (paramContext != null) {
      localBundle.putString("class", paramContext.getShortClassName());
    }
    if ("al_nav_out".equals(paramString))
    {
      if (paramContext != null) {
        localBundle.putString("package", paramContext.getPackageName());
      }
      if (paramIntent.getData() != null) {
        localBundle.putString("outputURL", paramIntent.getData().toString());
      }
      if (paramIntent.getScheme() != null) {
        localBundle.putString("outputURLScheme", paramIntent.getScheme());
      }
    }
    else if ("al_nav_in".equals(paramString))
    {
      if (paramIntent.getData() != null) {
        localBundle.putString("inputURL", paramIntent.getData().toString());
      }
      if (paramIntent.getScheme() != null) {
        localBundle.putString("inputURLScheme", paramIntent.getScheme());
      }
    }
    paramContext = paramBundle.keySet().iterator();
    while (paramContext.hasNext())
    {
      paramString = (String)paramContext.next();
      paramIntent = paramBundle.get(paramString);
      if ((paramIntent instanceof Bundle))
      {
        paramIntent = (Bundle)paramIntent;
        Iterator localIterator = paramIntent.keySet().iterator();
        while (localIterator.hasNext())
        {
          String str1 = (String)localIterator.next();
          String str2 = objectToJSONString(paramIntent.get(str1));
          if (paramString.equals("referer_app_link"))
          {
            if (str1.equalsIgnoreCase("url"))
            {
              localBundle.putString("refererURL", str2);
              continue;
            }
            if (str1.equalsIgnoreCase("app_name"))
            {
              localBundle.putString("refererAppName", str2);
              continue;
            }
            if (str1.equalsIgnoreCase("package"))
            {
              localBundle.putString("sourceApplication", str2);
              continue;
            }
          }
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append(paramString);
          localStringBuilder.append("/");
          localStringBuilder.append(str1);
          localBundle.putString(localStringBuilder.toString(), str2);
        }
      }
      else
      {
        paramIntent = objectToJSONString(paramIntent);
        if (paramString.equals("target_url"))
        {
          paramString = Uri.parse(paramIntent);
          localBundle.putString("targetURL", paramString.toString());
          localBundle.putString("targetURLHost", paramString.getHost());
        }
        else
        {
          localBundle.putString(paramString, paramIntent);
        }
      }
    }
    return localBundle;
  }
  
  private static String objectToJSONString(Object paramObject)
  {
    if (paramObject == null) {
      return null;
    }
    if ((!(paramObject instanceof JSONArray)) && (!(paramObject instanceof JSONObject))) {}
    try
    {
      if ((paramObject instanceof Collection)) {
        return new JSONArray((Collection)paramObject).toString();
      }
      if ((paramObject instanceof Map)) {
        return new JSONObject((Map)paramObject).toString();
      }
      paramObject = paramObject.toString();
      return paramObject;
    }
    catch (Exception paramObject) {}
    return paramObject.toString();
    return null;
  }
  
  private void sendBroadcast()
  {
    if (name == null) {
      Log.d(getClass().getName(), "Event name is required");
    }
    try
    {
      Object localObject1 = Class.forName("android.support.v4.content.LocalBroadcastManager");
      Object localObject2 = ((Class)localObject1).getMethod("getInstance", new Class[] { Context.class });
      localObject1 = ((Class)localObject1).getMethod("sendBroadcast", new Class[] { Intent.class });
      localObject2 = ((Method)localObject2).invoke(null, new Object[] { appContext });
      Intent localIntent = new Intent("com.parse.bolts.measurement_event");
      localIntent.putExtra("event_name", name);
      localIntent.putExtra("event_args", args);
      ((Method)localObject1).invoke(localObject2, new Object[] { localIntent });
      return;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    Log.d(getClass().getName(), "LocalBroadcastManager in android support library is required to raise bolts event.");
  }
  
  static void sendBroadcastEvent(Context paramContext, String paramString, Intent paramIntent, Map<String, String> paramMap)
  {
    Object localObject2 = new Bundle();
    Object localObject1 = localObject2;
    if (paramIntent != null)
    {
      localObject1 = AppLinks.getAppLinkData(paramIntent);
      if (localObject1 != null)
      {
        localObject1 = getApplinkLogData(paramContext, paramString, (Bundle)localObject1, paramIntent);
      }
      else
      {
        localObject1 = paramIntent.getData();
        if (localObject1 != null) {
          ((Bundle)localObject2).putString("intentData", ((Uri)localObject1).toString());
        }
        paramIntent = paramIntent.getExtras();
        localObject1 = localObject2;
        if (paramIntent != null)
        {
          Iterator localIterator = paramIntent.keySet().iterator();
          for (;;)
          {
            localObject1 = localObject2;
            if (!localIterator.hasNext()) {
              break;
            }
            localObject1 = (String)localIterator.next();
            ((Bundle)localObject2).putString((String)localObject1, objectToJSONString(paramIntent.get((String)localObject1)));
          }
        }
      }
    }
    if (paramMap != null)
    {
      paramIntent = paramMap.keySet().iterator();
      while (paramIntent.hasNext())
      {
        localObject2 = (String)paramIntent.next();
        ((Bundle)localObject1).putString((String)localObject2, (String)paramMap.get(localObject2));
      }
    }
    new MeasurementEvent(paramContext, paramString, (Bundle)localObject1).sendBroadcast();
  }
}
