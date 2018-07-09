package com.amplitude.api;

import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

public class DeviceInfo
{
  public static final String OS_NAME = "android";
  private static final String SETTING_ADVERTISING_ID = "advertising_id";
  private static final String SETTING_LIMIT_AD_TRACKING = "limit_ad_tracking";
  public static final String TAG = "com.amplitude.api.DeviceInfo";
  private CachedInfo cachedInfo;
  private Context context;
  private boolean locationListening = true;
  
  public DeviceInfo(Context paramContext)
  {
    context = paramContext;
  }
  
  public static String generateUUID()
  {
    return UUID.randomUUID().toString();
  }
  
  private CachedInfo getCachedInfo()
  {
    if (cachedInfo == null) {
      cachedInfo = new CachedInfo(null);
    }
    return cachedInfo;
  }
  
  public String getAdvertisingId()
  {
    return getCachedInfoadvertisingId;
  }
  
  public String getBrand()
  {
    return getCachedInfobrand;
  }
  
  public String getCarrier()
  {
    return getCachedInfocarrier;
  }
  
  public String getCountry()
  {
    return getCachedInfocountry;
  }
  
  protected Geocoder getGeocoder()
  {
    return new Geocoder(context, Locale.ENGLISH);
  }
  
  public String getLanguage()
  {
    return getCachedInfolanguage;
  }
  
  public String getManufacturer()
  {
    return getCachedInfomanufacturer;
  }
  
  public String getModel()
  {
    return getCachedInfomodel;
  }
  
  public Location getMostRecentLocation()
  {
    boolean bool = isLocationListening();
    Location localLocation = null;
    if (!bool) {
      return null;
    }
    Object localObject2 = (LocationManager)context.getSystemService("location");
    if (localObject2 == null) {
      return null;
    }
    try
    {
      localObject1 = ((LocationManager)localObject2).getProviders(true);
    }
    catch (SecurityException localSecurityException)
    {
      Object localObject1;
      ArrayList localArrayList;
      Iterator localIterator;
      for (;;) {}
    }
    localObject1 = null;
    if (localObject1 == null) {
      return null;
    }
    localArrayList = new ArrayList();
    localIterator = ((List)localObject1).iterator();
    while (localIterator.hasNext())
    {
      localObject1 = (String)localIterator.next();
      try
      {
        localObject1 = ((LocationManager)localObject2).getLastKnownLocation((String)localObject1);
      }
      catch (IllegalArgumentException|SecurityException localIllegalArgumentException)
      {
        long l;
        for (;;) {}
      }
      localObject1 = null;
      if (localObject1 != null) {
        localArrayList.add(localObject1);
      }
    }
    l = -1L;
    localObject2 = localArrayList.iterator();
    localObject1 = localLocation;
    while (((Iterator)localObject2).hasNext())
    {
      localLocation = (Location)((Iterator)localObject2).next();
      if (localLocation.getTime() > l)
      {
        l = localLocation.getTime();
        localObject1 = localLocation;
      }
    }
    return localObject1;
  }
  
  public String getOsName()
  {
    return getCachedInfoosName;
  }
  
  public String getOsVersion()
  {
    return getCachedInfoosVersion;
  }
  
  public String getVersionName()
  {
    return getCachedInfoversionName;
  }
  
  public boolean isGooglePlayServicesEnabled()
  {
    return getCachedInfogpsEnabled;
  }
  
  public boolean isLimitAdTrackingEnabled()
  {
    return getCachedInfolimitAdTrackingEnabled;
  }
  
  public boolean isLocationListening()
  {
    return locationListening;
  }
  
  public void prefetch()
  {
    getCachedInfo();
  }
  
  public void setLocationListening(boolean paramBoolean)
  {
    locationListening = paramBoolean;
  }
  
  private class CachedInfo
  {
    private String advertisingId = getAdvertisingId();
    private String brand = getBrand();
    private String carrier = getCarrier();
    private String country = getCountry();
    private boolean gpsEnabled = checkGPSEnabled();
    private String language = getLanguage();
    private boolean limitAdTrackingEnabled;
    private String manufacturer = getManufacturer();
    private String model = getModel();
    private String osName = getOsName();
    private String osVersion = getOsVersion();
    private String versionName = getVersionName();
    
    private CachedInfo() {}
    
    private boolean checkGPSEnabled()
    {
      boolean bool2 = false;
      try
      {
        Integer localInteger = (Integer)Class.forName("com.google.android.gms.common.GooglePlayServicesUtil").getMethod("isGooglePlayServicesAvailable", new Class[] { Context.class }).invoke(null, new Object[] { context });
        boolean bool1 = bool2;
        if (localInteger != null)
        {
          int i = localInteger.intValue();
          bool1 = bool2;
          if (i == 0) {
            bool1 = true;
          }
        }
        return bool1;
      }
      catch (Exception localException)
      {
        AmplitudeLog localAmplitudeLog = AmplitudeLog.getLogger();
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("Error when checking for Google Play Services: ");
        localStringBuilder.append(localException);
        localAmplitudeLog.w("com.amplitude.api.DeviceInfo", localStringBuilder.toString());
        return false;
        AmplitudeLog.getLogger().w("com.amplitude.api.DeviceInfo", "Google Play Services not available");
        return false;
        AmplitudeLog.getLogger().w("com.amplitude.api.DeviceInfo", "Google Play Services not available");
        return false;
        AmplitudeLog.getLogger().w("com.amplitude.api.DeviceInfo", "Google Play Services not available");
        return false;
        AmplitudeLog.getLogger().w("com.amplitude.api.DeviceInfo", "Google Play Services Util not found!");
        return false;
        AmplitudeLog.getLogger().w("com.amplitude.api.DeviceInfo", "Google Play Services Util not found!");
        return false;
      }
      catch (NoClassDefFoundError localNoClassDefFoundError)
      {
        for (;;) {}
      }
      catch (ClassNotFoundException localClassNotFoundException)
      {
        for (;;) {}
      }
      catch (NoSuchMethodException localNoSuchMethodException)
      {
        for (;;) {}
      }
      catch (InvocationTargetException localInvocationTargetException)
      {
        for (;;) {}
      }
      catch (IllegalAccessException localIllegalAccessException)
      {
        for (;;) {}
      }
    }
    
    private String getAdvertisingId()
    {
      if ("Amazon".equals(getManufacturer())) {
        return getAndCacheAmazonAdvertisingId();
      }
      return getAndCacheGoogleAdvertisingId();
    }
    
    private String getAndCacheAmazonAdvertisingId()
    {
      ContentResolver localContentResolver = context.getContentResolver();
      boolean bool = false;
      if (Settings.Secure.getInt(localContentResolver, "limit_ad_tracking", 0) == 1) {
        bool = true;
      }
      limitAdTrackingEnabled = bool;
      advertisingId = Settings.Secure.getString(localContentResolver, "advertising_id");
      return advertisingId;
    }
    
    /* Error */
    private String getAndCacheGoogleAdvertisingId()
    {
      // Byte code:
      //   0: ldc -40
      //   2: invokestatic 127	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
      //   5: astore_2
      //   6: iconst_1
      //   7: istore_1
      //   8: aload_2
      //   9: ldc -38
      //   11: iconst_1
      //   12: anewarray 123	java/lang/Class
      //   15: dup
      //   16: iconst_0
      //   17: ldc -125
      //   19: aastore
      //   20: invokevirtual 135	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
      //   23: aconst_null
      //   24: iconst_1
      //   25: anewarray 4	java/lang/Object
      //   28: dup
      //   29: iconst_0
      //   30: aload_0
      //   31: getfield 27	com/amplitude/api/DeviceInfo$CachedInfo:this$0	Lcom/amplitude/api/DeviceInfo;
      //   34: invokestatic 139	com/amplitude/api/DeviceInfo:access$000	(Lcom/amplitude/api/DeviceInfo;)Landroid/content/Context;
      //   37: aastore
      //   38: invokevirtual 145	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
      //   41: astore_2
      //   42: aload_2
      //   43: invokevirtual 222	java/lang/Object:getClass	()Ljava/lang/Class;
      //   46: ldc -32
      //   48: iconst_0
      //   49: anewarray 123	java/lang/Class
      //   52: invokevirtual 135	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
      //   55: aload_2
      //   56: iconst_0
      //   57: anewarray 4	java/lang/Object
      //   60: invokevirtual 145	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
      //   63: checkcast 226	java/lang/Boolean
      //   66: astore_3
      //   67: aload_3
      //   68: ifnull +103 -> 171
      //   71: aload_3
      //   72: invokevirtual 229	java/lang/Boolean:booleanValue	()Z
      //   75: ifeq +96 -> 171
      //   78: goto +3 -> 81
      //   81: aload_0
      //   82: iload_1
      //   83: putfield 98	com/amplitude/api/DeviceInfo$CachedInfo:limitAdTrackingEnabled	Z
      //   86: aload_0
      //   87: aload_2
      //   88: invokevirtual 222	java/lang/Object:getClass	()Ljava/lang/Class;
      //   91: ldc -25
      //   93: iconst_0
      //   94: anewarray 123	java/lang/Class
      //   97: invokevirtual 135	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
      //   100: aload_2
      //   101: iconst_0
      //   102: anewarray 4	java/lang/Object
      //   105: invokevirtual 145	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
      //   108: checkcast 186	java/lang/String
      //   111: putfield 36	com/amplitude/api/DeviceInfo$CachedInfo:advertisingId	Ljava/lang/String;
      //   114: goto +44 -> 158
      //   117: astore_2
      //   118: invokestatic 157	com/amplitude/api/AmplitudeLog:getLogger	()Lcom/amplitude/api/AmplitudeLog;
      //   121: ldc -85
      //   123: ldc -23
      //   125: aload_2
      //   126: invokevirtual 237	com/amplitude/api/AmplitudeLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
      //   129: pop
      //   130: goto +28 -> 158
      //   133: invokestatic 157	com/amplitude/api/AmplitudeLog:getLogger	()Lcom/amplitude/api/AmplitudeLog;
      //   136: ldc -85
      //   138: ldc -76
      //   140: invokevirtual 178	com/amplitude/api/AmplitudeLog:w	(Ljava/lang/String;Ljava/lang/String;)I
      //   143: pop
      //   144: goto +14 -> 158
      //   147: invokestatic 157	com/amplitude/api/AmplitudeLog:getLogger	()Lcom/amplitude/api/AmplitudeLog;
      //   150: ldc -85
      //   152: ldc -17
      //   154: invokevirtual 178	com/amplitude/api/AmplitudeLog:w	(Ljava/lang/String;Ljava/lang/String;)I
      //   157: pop
      //   158: aload_0
      //   159: getfield 36	com/amplitude/api/DeviceInfo$CachedInfo:advertisingId	Ljava/lang/String;
      //   162: areturn
      //   163: astore_2
      //   164: goto -17 -> 147
      //   167: astore_2
      //   168: goto -35 -> 133
      //   171: iconst_0
      //   172: istore_1
      //   173: goto -92 -> 81
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	176	0	this	CachedInfo
      //   7	166	1	bool	boolean
      //   5	96	2	localObject	Object
      //   117	9	2	localException	Exception
      //   163	1	2	localClassNotFoundException	ClassNotFoundException
      //   167	1	2	localInvocationTargetException	InvocationTargetException
      //   66	6	3	localBoolean	Boolean
      // Exception table:
      //   from	to	target	type
      //   0	6	117	java/lang/Exception
      //   8	67	117	java/lang/Exception
      //   71	78	117	java/lang/Exception
      //   81	114	117	java/lang/Exception
      //   0	6	163	java/lang/ClassNotFoundException
      //   8	67	163	java/lang/ClassNotFoundException
      //   71	78	163	java/lang/ClassNotFoundException
      //   81	114	163	java/lang/ClassNotFoundException
      //   0	6	167	java/lang/reflect/InvocationTargetException
      //   8	67	167	java/lang/reflect/InvocationTargetException
      //   71	78	167	java/lang/reflect/InvocationTargetException
      //   81	114	167	java/lang/reflect/InvocationTargetException
    }
    
    private String getBrand()
    {
      return Build.BRAND;
    }
    
    private String getCarrier()
    {
      try
      {
        String str = ((TelephonyManager)context.getSystemService("phone")).getNetworkOperatorName();
        return str;
      }
      catch (Exception localException)
      {
        for (;;) {}
      }
      return null;
    }
    
    private String getCountry()
    {
      String str = getCountryFromLocation();
      if (!Utils.isEmptyString(str)) {
        return str;
      }
      str = getCountryFromNetwork();
      if (!Utils.isEmptyString(str)) {
        return str;
      }
      return getCountryFromLocale();
    }
    
    private String getCountryFromLocale()
    {
      return Locale.getDefault().getCountry();
    }
    
    private String getCountryFromLocation()
    {
      if (!isLocationListening()) {
        return null;
      }
      Object localObject = getMostRecentLocation();
      if (localObject != null) {}
      try
      {
        if (Geocoder.isPresent())
        {
          localObject = getGeocoder().getFromLocation(((Location)localObject).getLatitude(), ((Location)localObject).getLongitude(), 1);
          if (localObject != null)
          {
            localObject = ((List)localObject).iterator();
            while (((Iterator)localObject).hasNext())
            {
              Address localAddress = (Address)((Iterator)localObject).next();
              if (localAddress != null)
              {
                localObject = localAddress.getCountryCode();
                return localObject;
              }
            }
          }
        }
        return null;
      }
      catch (IOException|NullPointerException|NoSuchMethodError|IllegalArgumentException|IllegalStateException localIOException) {}
      return null;
    }
    
    private String getCountryFromNetwork()
    {
      try
      {
        Object localObject = (TelephonyManager)context.getSystemService("phone");
        if (((TelephonyManager)localObject).getPhoneType() != 2)
        {
          localObject = ((TelephonyManager)localObject).getNetworkCountryIso();
          if (localObject != null)
          {
            localObject = ((String)localObject).toUpperCase(Locale.US);
            return localObject;
          }
        }
      }
      catch (Exception localException)
      {
        for (;;) {}
      }
      return null;
    }
    
    private String getLanguage()
    {
      return Locale.getDefault().getLanguage();
    }
    
    private String getManufacturer()
    {
      return Build.MANUFACTURER;
    }
    
    private String getModel()
    {
      return Build.MODEL;
    }
    
    private String getOsName()
    {
      return "android";
    }
    
    private String getOsVersion()
    {
      return Build.VERSION.RELEASE;
    }
    
    private String getVersionName()
    {
      try
      {
        String str = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        return str;
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        for (;;) {}
      }
      return null;
    }
  }
}
