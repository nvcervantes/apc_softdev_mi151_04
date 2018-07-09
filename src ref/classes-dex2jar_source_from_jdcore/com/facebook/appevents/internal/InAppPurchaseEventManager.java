package com.facebook.appevents.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import com.facebook.internal.Utility;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

public class InAppPurchaseEventManager
{
  private static final String DETAILS_LIST = "DETAILS_LIST";
  private static final String GET_INTERFACE_METHOD = "iap_get_interface";
  private static final String GET_SKU_DETAILS_METHOD = "iap_get_sku_details";
  private static final String IN_APP_BILLING_SERVICE = "com.android.vending.billing.IInAppBillingService";
  private static final String IN_APP_BILLING_SERVICE_STUB = "com.android.vending.billing.IInAppBillingService$Stub";
  private static final String ITEM_ID_LIST = "ITEM_ID_LIST";
  private static final String RESPONSE_CODE = "RESPONSE_CODE";
  private static final String TAG = InAppPurchaseEventManager.class.getCanonicalName();
  private static final HashMap<String, Class<?>> classMap;
  private static final HashMap<String, Method> methodMap = new HashMap();
  
  static
  {
    classMap = new HashMap();
  }
  
  public InAppPurchaseEventManager() {}
  
  public static String getPurchaseDetails(Context paramContext, String paramString, Object paramObject, boolean paramBoolean)
  {
    if ((paramObject != null) && (paramString != "")) {}
    for (;;)
    {
      try
      {
        Method localMethod = (Method)methodMap.get("iap_get_sku_details");
        Object localObject2 = (Class)classMap.get("com.android.vending.billing.IInAppBillingService");
        if (localMethod != null)
        {
          localObject1 = localObject2;
          if (localObject2 != null) {}
        }
        else
        {
          localObject1 = paramContext.getClassLoader().loadClass("com.android.vending.billing.IInAppBillingService");
          localMethod = ((Class)localObject1).getDeclaredMethod("getSkuDetails", new Class[] { Integer.TYPE, String.class, String.class, Bundle.class });
          methodMap.put("iap_get_sku_details", localMethod);
          classMap.put("com.android.vending.billing.IInAppBillingService", localObject1);
        }
        localObject2 = new ArrayList();
        ((ArrayList)localObject2).add(paramString);
        paramString = new Bundle();
        paramString.putStringArrayList("ITEM_ID_LIST", (ArrayList)localObject2);
        paramObject = ((Class)localObject1).cast(paramObject);
        Object localObject1 = paramContext.getPackageName();
        if (!paramBoolean) {
          break label306;
        }
        paramContext = "subs";
        paramContext = (Bundle)localMethod.invoke(paramObject, new Object[] { Integer.valueOf(3), localObject1, paramContext, paramString });
        if (paramContext.getInt("RESPONSE_CODE") == 0)
        {
          paramContext = paramContext.getStringArrayList("DETAILS_LIST");
          if (paramContext.size() < 1) {
            return "";
          }
          paramContext = (String)paramContext.get(0);
          return paramContext;
        }
      }
      catch (IllegalAccessException paramContext)
      {
        Log.e(TAG, "Illegal access to method com.android.vending.billing.IInAppBillingService.getSkuDetails", paramContext);
      }
      catch (InvocationTargetException paramContext)
      {
        Log.e(TAG, "Invocation target exception in com.android.vending.billing.IInAppBillingService.getSkuDetails", paramContext);
      }
      catch (NoSuchMethodException paramContext)
      {
        Log.e(TAG, "com.android.vending.billing.IInAppBillingService.getSkuDetails method is not available", paramContext);
      }
      catch (ClassNotFoundException paramContext)
      {
        Log.e(TAG, "com.android.vending.billing.IInAppBillingService is not available, please add com.android.vending.billing.IInAppBillingService to the project, and import the IInAppBillingService.aidl file into this package", paramContext);
      }
      return "";
      return "";
      label306:
      paramContext = "inapp";
    }
  }
  
  public static Object getServiceInterface(Context paramContext, IBinder paramIBinder)
  {
    try
    {
      Method localMethod2 = (Method)methodMap.get("iap_get_interface");
      Method localMethod1 = localMethod2;
      if (localMethod2 == null)
      {
        localMethod1 = paramContext.getClassLoader().loadClass("com.android.vending.billing.IInAppBillingService$Stub").getDeclaredMethod("asInterface", new Class[] { IBinder.class });
        methodMap.put("iap_get_interface", localMethod1);
      }
      Utility.logd(TAG, "In-app billing service connected");
      paramContext = localMethod1.invoke(null, new Object[] { paramIBinder });
      return paramContext;
    }
    catch (InvocationTargetException paramContext)
    {
      Log.e(TAG, "Invocation target exception in com.android.vending.billing.IInAppBillingService$Stub.asInterface", paramContext);
      return null;
    }
    catch (IllegalAccessException paramContext)
    {
      Log.e(TAG, "Illegal access to method com.android.vending.billing.IInAppBillingService$Stub.asInterface", paramContext);
      return null;
    }
    catch (NoSuchMethodException paramContext)
    {
      Log.e(TAG, "com.android.vending.billing.IInAppBillingService$Stub.asInterface method not found", paramContext);
      return null;
    }
    catch (ClassNotFoundException paramContext)
    {
      Log.e(TAG, "com.android.vending.billing.IInAppBillingService$Stub is not available, please add com.android.vending.billing.IInAppBillingService to the project.", paramContext);
    }
    return null;
  }
}
