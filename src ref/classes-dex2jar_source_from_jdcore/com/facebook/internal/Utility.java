package com.facebook.internal;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcel;
import android.os.StatFs;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import android.view.autofill.AutofillManager;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import com.facebook.AccessToken;
import com.facebook.FacebookException;
import com.facebook.FacebookRequestError;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphRequest.Callback;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import java.io.Closeable;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import java.util.TimeZone;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public final class Utility
{
  public static final int DEFAULT_STREAM_BUFFER_SIZE = 8192;
  private static final String EXTRA_APP_EVENTS_INFO_FORMAT_VERSION = "a2";
  private static final int GINGERBREAD_MR1 = 10;
  private static final String HASH_ALGORITHM_MD5 = "MD5";
  private static final String HASH_ALGORITHM_SHA1 = "SHA-1";
  static final String LOG_TAG = "FacebookSDK";
  private static final int REFRESH_TIME_FOR_EXTENDED_DEVICE_INFO_MILLIS = 1800000;
  private static final String URL_SCHEME = "https";
  private static final String UTF8 = "UTF-8";
  private static long availableExternalStorageGB = -1L;
  private static String carrierName = "NoCarrier";
  private static String deviceTimeZoneName = "";
  private static String deviceTimezoneAbbreviation = "";
  private static final String noCarrierConstant = "NoCarrier";
  private static int numCPUCores = 0;
  private static long timestampOfLastCheck = -1L;
  private static long totalExternalStorageGB = -1L;
  
  public Utility() {}
  
  public static <T> boolean areObjectsEqual(T paramT1, T paramT2)
  {
    if (paramT1 == null) {
      return paramT2 == null;
    }
    return paramT1.equals(paramT2);
  }
  
  public static <T> ArrayList<T> arrayList(T... paramVarArgs)
  {
    int i = 0;
    ArrayList localArrayList = new ArrayList(paramVarArgs.length);
    int j = paramVarArgs.length;
    while (i < j)
    {
      localArrayList.add(paramVarArgs[i]);
      i += 1;
    }
    return localArrayList;
  }
  
  public static <T> List<T> asListNoNulls(T... paramVarArgs)
  {
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    int j = paramVarArgs.length;
    while (i < j)
    {
      T ? = paramVarArgs[i];
      if (? != null) {
        localArrayList.add(?);
      }
      i += 1;
    }
    return localArrayList;
  }
  
  public static JSONObject awaitGetGraphMeRequestWithCache(String paramString)
  {
    JSONObject localJSONObject = ProfileInformationCache.getProfileInformation(paramString);
    if (localJSONObject != null) {
      return localJSONObject;
    }
    paramString = getGraphMeRequestWithCache(paramString).executeAndWait();
    if (paramString.getError() != null) {
      return null;
    }
    return paramString.getJSONObject();
  }
  
  public static Uri buildUri(String paramString1, String paramString2, Bundle paramBundle)
  {
    Uri.Builder localBuilder = new Uri.Builder();
    localBuilder.scheme("https");
    localBuilder.authority(paramString1);
    localBuilder.path(paramString2);
    if (paramBundle != null)
    {
      paramString1 = paramBundle.keySet().iterator();
      while (paramString1.hasNext())
      {
        paramString2 = (String)paramString1.next();
        Object localObject = paramBundle.get(paramString2);
        if ((localObject instanceof String)) {
          localBuilder.appendQueryParameter(paramString2, (String)localObject);
        }
      }
    }
    return localBuilder.build();
  }
  
  public static void clearCaches(Context paramContext)
  {
    ImageDownloader.clearCache(paramContext);
  }
  
  private static void clearCookiesForDomain(Context paramContext, String paramString)
  {
    CookieSyncManager.createInstance(paramContext).sync();
    paramContext = CookieManager.getInstance();
    Object localObject = paramContext.getCookie(paramString);
    if (localObject == null) {
      return;
    }
    localObject = ((String)localObject).split(";");
    int j = localObject.length;
    int i = 0;
    while (i < j)
    {
      String[] arrayOfString = localObject[i].split("=");
      if (arrayOfString.length > 0)
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append(arrayOfString[0].trim());
        localStringBuilder.append("=;expires=Sat, 1 Jan 2000 00:00:01 UTC;");
        paramContext.setCookie(paramString, localStringBuilder.toString());
      }
      i += 1;
    }
    paramContext.removeExpiredCookie();
  }
  
  public static void clearFacebookCookies(Context paramContext)
  {
    clearCookiesForDomain(paramContext, "facebook.com");
    clearCookiesForDomain(paramContext, ".facebook.com");
    clearCookiesForDomain(paramContext, "https://facebook.com");
    clearCookiesForDomain(paramContext, "https://.facebook.com");
  }
  
  public static void closeQuietly(Closeable paramCloseable)
  {
    if (paramCloseable != null) {}
    try
    {
      paramCloseable.close();
      return;
    }
    catch (IOException paramCloseable) {}
  }
  
  public static String coerceValueIfNullOrEmpty(String paramString1, String paramString2)
  {
    if (isNullOrEmpty(paramString1)) {
      return paramString2;
    }
    return paramString1;
  }
  
  private static long convertBytesToGB(double paramDouble)
  {
    return Math.round(paramDouble / 1.073741824E9D);
  }
  
  static Map<String, Object> convertJSONObjectToHashMap(JSONObject paramJSONObject)
  {
    HashMap localHashMap = new HashMap();
    JSONArray localJSONArray = paramJSONObject.names();
    int i = 0;
    while (i < localJSONArray.length())
    {
      try
      {
        String str = localJSONArray.getString(i);
        Object localObject2 = paramJSONObject.get(str);
        Object localObject1 = localObject2;
        if ((localObject2 instanceof JSONObject)) {
          localObject1 = convertJSONObjectToHashMap((JSONObject)localObject2);
        }
        localHashMap.put(str, localObject1);
      }
      catch (JSONException localJSONException)
      {
        for (;;) {}
      }
      i += 1;
    }
    return localHashMap;
  }
  
  /* Error */
  public static int copyAndCloseInputStream(java.io.InputStream paramInputStream, java.io.OutputStream paramOutputStream)
    throws IOException
  {
    // Byte code:
    //   0: new 298	java/io/BufferedInputStream
    //   3: dup
    //   4: aload_0
    //   5: invokespecial 301	java/io/BufferedInputStream:<init>	(Ljava/io/InputStream;)V
    //   8: astore 4
    //   10: sipush 8192
    //   13: newarray byte
    //   15: astore 5
    //   17: iconst_0
    //   18: istore_2
    //   19: aload 4
    //   21: aload 5
    //   23: invokevirtual 305	java/io/BufferedInputStream:read	([B)I
    //   26: istore_3
    //   27: iload_3
    //   28: iconst_m1
    //   29: if_icmpeq +18 -> 47
    //   32: aload_1
    //   33: aload 5
    //   35: iconst_0
    //   36: iload_3
    //   37: invokevirtual 311	java/io/OutputStream:write	([BII)V
    //   40: iload_2
    //   41: iload_3
    //   42: iadd
    //   43: istore_2
    //   44: goto -25 -> 19
    //   47: aload 4
    //   49: ifnull +8 -> 57
    //   52: aload 4
    //   54: invokevirtual 312	java/io/BufferedInputStream:close	()V
    //   57: aload_0
    //   58: ifnull +7 -> 65
    //   61: aload_0
    //   62: invokevirtual 315	java/io/InputStream:close	()V
    //   65: iload_2
    //   66: ireturn
    //   67: astore 5
    //   69: aload 4
    //   71: astore_1
    //   72: aload 5
    //   74: astore 4
    //   76: goto +7 -> 83
    //   79: astore 4
    //   81: aconst_null
    //   82: astore_1
    //   83: aload_1
    //   84: ifnull +7 -> 91
    //   87: aload_1
    //   88: invokevirtual 312	java/io/BufferedInputStream:close	()V
    //   91: aload_0
    //   92: ifnull +7 -> 99
    //   95: aload_0
    //   96: invokevirtual 315	java/io/InputStream:close	()V
    //   99: aload 4
    //   101: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	102	0	paramInputStream	java.io.InputStream
    //   0	102	1	paramOutputStream	java.io.OutputStream
    //   18	48	2	i	int
    //   26	17	3	j	int
    //   8	67	4	localObject1	Object
    //   79	21	4	localObject2	Object
    //   15	19	5	arrayOfByte	byte[]
    //   67	6	5	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   10	17	67	finally
    //   19	27	67	finally
    //   32	40	67	finally
    //   0	10	79	finally
  }
  
  public static void deleteDirectory(File paramFile)
  {
    if (!paramFile.exists()) {
      return;
    }
    if (paramFile.isDirectory())
    {
      File[] arrayOfFile = paramFile.listFiles();
      if (arrayOfFile != null)
      {
        int j = arrayOfFile.length;
        int i = 0;
        while (i < j)
        {
          deleteDirectory(arrayOfFile[i]);
          i += 1;
        }
      }
    }
    paramFile.delete();
  }
  
  public static void disconnectQuietly(URLConnection paramURLConnection)
  {
    if ((paramURLConnection != null) && ((paramURLConnection instanceof HttpURLConnection))) {
      ((HttpURLConnection)paramURLConnection).disconnect();
    }
  }
  
  private static boolean externalStorageExists()
  {
    return "mounted".equals(Environment.getExternalStorageState());
  }
  
  public static <T> List<T> filter(List<T> paramList, Predicate<T> paramPredicate)
  {
    if (paramList == null) {
      return null;
    }
    ArrayList localArrayList = new ArrayList();
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      Object localObject = paramList.next();
      if (paramPredicate.apply(localObject)) {
        localArrayList.add(localObject);
      }
    }
    if (localArrayList.size() == 0) {
      return null;
    }
    return localArrayList;
  }
  
  public static String generateRandomString(int paramInt)
  {
    return new BigInteger(paramInt * 5, new Random()).toString(32);
  }
  
  public static String getActivityName(Context paramContext)
  {
    if (paramContext == null) {
      return "null";
    }
    if (paramContext == paramContext.getApplicationContext()) {
      return "unknown";
    }
    return paramContext.getClass().getSimpleName();
  }
  
  public static Date getBundleLongAsDate(Bundle paramBundle, String paramString, Date paramDate)
  {
    if (paramBundle == null) {
      return null;
    }
    paramBundle = paramBundle.get(paramString);
    long l;
    if ((paramBundle instanceof Long)) {
      l = ((Long)paramBundle).longValue();
    } else {
      if (!(paramBundle instanceof String)) {
        break label80;
      }
    }
    label80:
    try
    {
      l = Long.parseLong((String)paramBundle);
      if (l == 0L) {
        return new Date(Long.MAX_VALUE);
      }
      return new Date(paramDate.getTime() + l * 1000L);
    }
    catch (NumberFormatException paramBundle) {}
    return null;
    return null;
  }
  
  /* Error */
  public static long getContentSize(Uri paramUri)
  {
    // Byte code:
    //   0: invokestatic 427	com/facebook/FacebookSdk:getApplicationContext	()Landroid/content/Context;
    //   3: invokevirtual 431	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   6: aload_0
    //   7: aconst_null
    //   8: aconst_null
    //   9: aconst_null
    //   10: aconst_null
    //   11: invokevirtual 437	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   14: astore 4
    //   16: aload 4
    //   18: ldc_w 439
    //   21: invokeinterface 445 2 0
    //   26: istore_1
    //   27: aload 4
    //   29: invokeinterface 448 1 0
    //   34: pop
    //   35: aload 4
    //   37: iload_1
    //   38: invokeinterface 452 2 0
    //   43: lstore_2
    //   44: aload 4
    //   46: ifnull +10 -> 56
    //   49: aload 4
    //   51: invokeinterface 453 1 0
    //   56: lload_2
    //   57: lreturn
    //   58: astore_0
    //   59: goto +7 -> 66
    //   62: astore_0
    //   63: aconst_null
    //   64: astore 4
    //   66: aload 4
    //   68: ifnull +10 -> 78
    //   71: aload 4
    //   73: invokeinterface 453 1 0
    //   78: aload_0
    //   79: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	80	0	paramUri	Uri
    //   26	12	1	i	int
    //   43	14	2	l	long
    //   14	58	4	localCursor	android.database.Cursor
    // Exception table:
    //   from	to	target	type
    //   16	44	58	finally
    //   0	16	62	finally
  }
  
  private static GraphRequest getGraphMeRequestWithCache(String paramString)
  {
    Bundle localBundle = new Bundle();
    localBundle.putString("fields", "id,name,first_name,middle_name,last_name,link");
    localBundle.putString("access_token", paramString);
    return new GraphRequest(null, "me", localBundle, HttpMethod.GET, null);
  }
  
  public static void getGraphMeRequestWithCacheAsync(final String paramString, GraphMeRequestWithCacheCallback paramGraphMeRequestWithCacheCallback)
  {
    JSONObject localJSONObject = ProfileInformationCache.getProfileInformation(paramString);
    if (localJSONObject != null)
    {
      paramGraphMeRequestWithCacheCallback.onSuccess(localJSONObject);
      return;
    }
    paramGraphMeRequestWithCacheCallback = new GraphRequest.Callback()
    {
      public void onCompleted(GraphResponse paramAnonymousGraphResponse)
      {
        if (paramAnonymousGraphResponse.getError() != null)
        {
          val$callback.onFailure(paramAnonymousGraphResponse.getError().getException());
          return;
        }
        ProfileInformationCache.putProfileInformation(paramString, paramAnonymousGraphResponse.getJSONObject());
        val$callback.onSuccess(paramAnonymousGraphResponse.getJSONObject());
      }
    };
    paramString = getGraphMeRequestWithCache(paramString);
    paramString.setCallback(paramGraphMeRequestWithCacheCallback);
    paramString.executeAsync();
  }
  
  public static String getMetadataApplicationId(Context paramContext)
  {
    Validate.notNull(paramContext, "context");
    FacebookSdk.sdkInitialize(paramContext);
    return FacebookSdk.getApplicationId();
  }
  
  public static Method getMethodQuietly(Class<?> paramClass, String paramString, Class<?>... paramVarArgs)
  {
    try
    {
      paramClass = paramClass.getMethod(paramString, paramVarArgs);
      return paramClass;
    }
    catch (NoSuchMethodException paramClass)
    {
      for (;;) {}
    }
    return null;
  }
  
  public static Method getMethodQuietly(String paramString1, String paramString2, Class<?>... paramVarArgs)
  {
    try
    {
      paramString1 = getMethodQuietly(Class.forName(paramString1), paramString2, paramVarArgs);
      return paramString1;
    }
    catch (ClassNotFoundException paramString1)
    {
      for (;;) {}
    }
    return null;
  }
  
  public static Object getStringPropertyAsJSON(JSONObject paramJSONObject, String paramString1, String paramString2)
    throws JSONException
  {
    paramString1 = paramJSONObject.opt(paramString1);
    paramJSONObject = paramString1;
    if (paramString1 != null)
    {
      paramJSONObject = paramString1;
      if ((paramString1 instanceof String)) {
        paramJSONObject = new JSONTokener((String)paramString1).nextValue();
      }
    }
    if ((paramJSONObject != null) && (!(paramJSONObject instanceof JSONObject)) && (!(paramJSONObject instanceof JSONArray)))
    {
      if (paramString2 != null)
      {
        paramString1 = new JSONObject();
        paramString1.putOpt(paramString2, paramJSONObject);
        return paramString1;
      }
      throw new FacebookException("Got an unexpected non-JSON object.");
    }
    return paramJSONObject;
  }
  
  public static String getUriString(Uri paramUri)
  {
    if (paramUri == null) {
      return null;
    }
    return paramUri.toString();
  }
  
  public static PermissionsPair handlePermissionResponse(JSONObject paramJSONObject)
    throws JSONException
  {
    paramJSONObject = paramJSONObject.getJSONObject("permissions").getJSONArray("data");
    ArrayList localArrayList1 = new ArrayList(paramJSONObject.length());
    ArrayList localArrayList2 = new ArrayList(paramJSONObject.length());
    int i = 0;
    while (i < paramJSONObject.length())
    {
      Object localObject = paramJSONObject.optJSONObject(i);
      String str = ((JSONObject)localObject).optString("permission");
      if ((str != null) && (!str.equals("installed")))
      {
        localObject = ((JSONObject)localObject).optString("status");
        if (localObject != null) {
          if (((String)localObject).equals("granted")) {
            localArrayList1.add(str);
          } else if (((String)localObject).equals("declined")) {
            localArrayList2.add(str);
          }
        }
      }
      i += 1;
    }
    return new PermissionsPair(localArrayList1, localArrayList2);
  }
  
  public static boolean hasSameId(JSONObject paramJSONObject1, JSONObject paramJSONObject2)
  {
    if ((paramJSONObject1 != null) && (paramJSONObject2 != null) && (paramJSONObject1.has("id")))
    {
      if (!paramJSONObject2.has("id")) {
        return false;
      }
      if (paramJSONObject1.equals(paramJSONObject2)) {
        return true;
      }
      paramJSONObject1 = paramJSONObject1.optString("id");
      paramJSONObject2 = paramJSONObject2.optString("id");
      if (paramJSONObject1 != null)
      {
        if (paramJSONObject2 == null) {
          return false;
        }
        return paramJSONObject1.equals(paramJSONObject2);
      }
      return false;
    }
    return false;
  }
  
  private static String hashBytes(MessageDigest paramMessageDigest, byte[] paramArrayOfByte)
  {
    paramMessageDigest.update(paramArrayOfByte);
    paramMessageDigest = paramMessageDigest.digest();
    paramArrayOfByte = new StringBuilder();
    int j = paramMessageDigest.length;
    int i = 0;
    while (i < j)
    {
      int k = paramMessageDigest[i];
      paramArrayOfByte.append(Integer.toHexString(k >> 4 & 0xF));
      paramArrayOfByte.append(Integer.toHexString(k >> 0 & 0xF));
      i += 1;
    }
    return paramArrayOfByte.toString();
  }
  
  public static <T> HashSet<T> hashSet(T... paramVarArgs)
  {
    int i = 0;
    HashSet localHashSet = new HashSet(paramVarArgs.length);
    int j = paramVarArgs.length;
    while (i < j)
    {
      localHashSet.add(paramVarArgs[i]);
      i += 1;
    }
    return localHashSet;
  }
  
  private static String hashWithAlgorithm(String paramString1, String paramString2)
  {
    return hashWithAlgorithm(paramString1, paramString2.getBytes());
  }
  
  private static String hashWithAlgorithm(String paramString, byte[] paramArrayOfByte)
  {
    try
    {
      paramString = MessageDigest.getInstance(paramString);
      return hashBytes(paramString, paramArrayOfByte);
    }
    catch (NoSuchAlgorithmException paramString)
    {
      for (;;) {}
    }
    return null;
  }
  
  public static int[] intersectRanges(int[] paramArrayOfInt1, int[] paramArrayOfInt2)
  {
    if (paramArrayOfInt1 == null) {
      return paramArrayOfInt2;
    }
    if (paramArrayOfInt2 == null) {
      return paramArrayOfInt1;
    }
    int m = 0;
    int[] arrayOfInt = new int[paramArrayOfInt1.length + paramArrayOfInt2.length];
    int n = 0;
    int k = n;
    for (;;)
    {
      i = k;
      if (m >= paramArrayOfInt1.length) {
        break label291;
      }
      i = k;
      if (n >= paramArrayOfInt2.length) {
        break label291;
      }
      int j = paramArrayOfInt1[m];
      int i1 = paramArrayOfInt2[n];
      int i2;
      if (m < paramArrayOfInt1.length - 1) {
        i2 = paramArrayOfInt1[(m + 1)];
      } else {
        i2 = Integer.MAX_VALUE;
      }
      if (n < paramArrayOfInt2.length - 1) {
        i = paramArrayOfInt2[(n + 1)];
      } else {
        i = Integer.MAX_VALUE;
      }
      if (j < i1) {
        if (i2 > i1)
        {
          if (i2 > i)
          {
            n += 2;
            j = i1;
            i1 = n;
            i2 = i;
            i = m;
            break label231;
          }
          i = m + 2;
          j = i1;
          i1 = n;
          break label231;
        }
      }
      for (i = m + 2;; i = m)
      {
        i2 = Integer.MAX_VALUE;
        j = Integer.MIN_VALUE;
        i1 = n;
        break label231;
        if (i > j)
        {
          if (i > i2)
          {
            i = m + 2;
            i1 = n;
            break label231;
          }
          i1 = n + 2;
          break;
        }
        n += 2;
      }
      label231:
      m = i;
      n = i1;
      if (j != Integer.MIN_VALUE)
      {
        m = k + 1;
        arrayOfInt[k] = j;
        if (i2 == Integer.MAX_VALUE) {
          break;
        }
        k = m + 1;
        arrayOfInt[m] = i2;
        m = i;
        n = i1;
      }
    }
    int i = m;
    label291:
    return Arrays.copyOf(arrayOfInt, i);
  }
  
  public static Object invokeMethodQuietly(Object paramObject, Method paramMethod, Object... paramVarArgs)
  {
    try
    {
      paramObject = paramMethod.invoke(paramObject, paramVarArgs);
      return paramObject;
    }
    catch (IllegalAccessException paramObject)
    {
      return null;
    }
    catch (InvocationTargetException paramObject) {}
    return null;
  }
  
  public static boolean isAutofillAvailable(Context paramContext)
  {
    int i = Build.VERSION.SDK_INT;
    boolean bool2 = false;
    if (i < 26) {
      return false;
    }
    paramContext = (AutofillManager)paramContext.getSystemService(AutofillManager.class);
    boolean bool1 = bool2;
    if (paramContext != null)
    {
      bool1 = bool2;
      if (paramContext.isAutofillSupported())
      {
        bool1 = bool2;
        if (paramContext.isEnabled()) {
          bool1 = true;
        }
      }
    }
    return bool1;
  }
  
  public static boolean isContentUri(Uri paramUri)
  {
    return (paramUri != null) && ("content".equalsIgnoreCase(paramUri.getScheme()));
  }
  
  public static boolean isCurrentAccessToken(AccessToken paramAccessToken)
  {
    return (paramAccessToken != null) && (paramAccessToken.equals(AccessToken.getCurrentAccessToken()));
  }
  
  public static boolean isFileUri(Uri paramUri)
  {
    return (paramUri != null) && ("file".equalsIgnoreCase(paramUri.getScheme()));
  }
  
  public static boolean isNullOrEmpty(String paramString)
  {
    return (paramString == null) || (paramString.length() == 0);
  }
  
  public static <T> boolean isNullOrEmpty(Collection<T> paramCollection)
  {
    return (paramCollection == null) || (paramCollection.size() == 0);
  }
  
  public static <T> boolean isSubset(Collection<T> paramCollection1, Collection<T> paramCollection2)
  {
    boolean bool = true;
    if ((paramCollection2 != null) && (paramCollection2.size() != 0))
    {
      paramCollection2 = new HashSet(paramCollection2);
      paramCollection1 = paramCollection1.iterator();
      while (paramCollection1.hasNext()) {
        if (!paramCollection2.contains(paramCollection1.next())) {
          return false;
        }
      }
      return true;
    }
    if (paramCollection1 != null)
    {
      if (paramCollection1.size() == 0) {
        return true;
      }
      bool = false;
    }
    return bool;
  }
  
  public static boolean isWebUri(Uri paramUri)
  {
    return (paramUri != null) && (("http".equalsIgnoreCase(paramUri.getScheme())) || ("https".equalsIgnoreCase(paramUri.getScheme())) || ("fbstaging".equalsIgnoreCase(paramUri.getScheme())));
  }
  
  public static Set<String> jsonArrayToSet(JSONArray paramJSONArray)
    throws JSONException
  {
    HashSet localHashSet = new HashSet();
    int i = 0;
    while (i < paramJSONArray.length())
    {
      localHashSet.add(paramJSONArray.getString(i));
      i += 1;
    }
    return localHashSet;
  }
  
  public static List<String> jsonArrayToStringList(JSONArray paramJSONArray)
    throws JSONException
  {
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    while (i < paramJSONArray.length())
    {
      localArrayList.add(paramJSONArray.getString(i));
      i += 1;
    }
    return localArrayList;
  }
  
  public static void logd(String paramString, Exception paramException)
  {
    if ((FacebookSdk.isDebugEnabled()) && (paramString != null) && (paramException != null))
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(paramException.getClass().getSimpleName());
      localStringBuilder.append(": ");
      localStringBuilder.append(paramException.getMessage());
      Log.d(paramString, localStringBuilder.toString());
    }
  }
  
  public static void logd(String paramString1, String paramString2)
  {
    if ((FacebookSdk.isDebugEnabled()) && (paramString1 != null) && (paramString2 != null)) {
      Log.d(paramString1, paramString2);
    }
  }
  
  public static void logd(String paramString1, String paramString2, Throwable paramThrowable)
  {
    if ((FacebookSdk.isDebugEnabled()) && (!isNullOrEmpty(paramString1))) {
      Log.d(paramString1, paramString2, paramThrowable);
    }
  }
  
  public static <T, K> List<K> map(List<T> paramList, Mapper<T, K> paramMapper)
  {
    if (paramList == null) {
      return null;
    }
    ArrayList localArrayList = new ArrayList();
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      Object localObject = paramMapper.apply(paramList.next());
      if (localObject != null) {
        localArrayList.add(localObject);
      }
    }
    if (localArrayList.size() == 0) {
      return null;
    }
    return localArrayList;
  }
  
  public static String md5hash(String paramString)
  {
    return hashWithAlgorithm("MD5", paramString);
  }
  
  public static boolean mustFixWindowParamsForAutofill(Context paramContext)
  {
    return isAutofillAvailable(paramContext);
  }
  
  public static Bundle parseUrlQueryString(String paramString)
  {
    Bundle localBundle = new Bundle();
    if (!isNullOrEmpty(paramString))
    {
      paramString = paramString.split("&");
      int j = paramString.length;
      int i = 0;
      while (i < j)
      {
        String[] arrayOfString = paramString[i].split("=");
        try
        {
          if (arrayOfString.length == 2) {
            localBundle.putString(URLDecoder.decode(arrayOfString[0], "UTF-8"), URLDecoder.decode(arrayOfString[1], "UTF-8"));
          } else if (arrayOfString.length == 1) {
            localBundle.putString(URLDecoder.decode(arrayOfString[0], "UTF-8"), "");
          }
        }
        catch (UnsupportedEncodingException localUnsupportedEncodingException)
        {
          logd("FacebookSDK", localUnsupportedEncodingException);
        }
        i += 1;
      }
    }
    return localBundle;
  }
  
  public static void putCommaSeparatedStringList(Bundle paramBundle, String paramString, List<String> paramList)
  {
    if (paramList != null)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      paramList = paramList.iterator();
      while (paramList.hasNext())
      {
        localStringBuilder.append((String)paramList.next());
        localStringBuilder.append(",");
      }
      paramList = "";
      if (localStringBuilder.length() > 0) {
        paramList = localStringBuilder.substring(0, localStringBuilder.length() - 1);
      }
      paramBundle.putString(paramString, paramList);
    }
  }
  
  public static boolean putJSONValueInBundle(Bundle paramBundle, String paramString, Object paramObject)
  {
    if (paramObject == null)
    {
      paramBundle.remove(paramString);
    }
    else if ((paramObject instanceof Boolean))
    {
      paramBundle.putBoolean(paramString, ((Boolean)paramObject).booleanValue());
    }
    else if ((paramObject instanceof boolean[]))
    {
      paramBundle.putBooleanArray(paramString, (boolean[])paramObject);
    }
    else if ((paramObject instanceof Double))
    {
      paramBundle.putDouble(paramString, ((Double)paramObject).doubleValue());
    }
    else if ((paramObject instanceof double[]))
    {
      paramBundle.putDoubleArray(paramString, (double[])paramObject);
    }
    else if ((paramObject instanceof Integer))
    {
      paramBundle.putInt(paramString, ((Integer)paramObject).intValue());
    }
    else if ((paramObject instanceof int[]))
    {
      paramBundle.putIntArray(paramString, (int[])paramObject);
    }
    else if ((paramObject instanceof Long))
    {
      paramBundle.putLong(paramString, ((Long)paramObject).longValue());
    }
    else if ((paramObject instanceof long[]))
    {
      paramBundle.putLongArray(paramString, (long[])paramObject);
    }
    else if ((paramObject instanceof String))
    {
      paramBundle.putString(paramString, (String)paramObject);
    }
    else if ((paramObject instanceof JSONArray))
    {
      paramBundle.putString(paramString, paramObject.toString());
    }
    else
    {
      if (!(paramObject instanceof JSONObject)) {
        break label232;
      }
      paramBundle.putString(paramString, paramObject.toString());
    }
    return true;
    label232:
    return false;
  }
  
  public static void putNonEmptyString(Bundle paramBundle, String paramString1, String paramString2)
  {
    if (!isNullOrEmpty(paramString2)) {
      paramBundle.putString(paramString1, paramString2);
    }
  }
  
  public static void putUri(Bundle paramBundle, String paramString, Uri paramUri)
  {
    if (paramUri != null) {
      putNonEmptyString(paramBundle, paramString, paramUri.toString());
    }
  }
  
  /* Error */
  public static String readStreamToString(java.io.InputStream paramInputStream)
    throws IOException
  {
    // Byte code:
    //   0: new 298	java/io/BufferedInputStream
    //   3: dup
    //   4: aload_0
    //   5: invokespecial 301	java/io/BufferedInputStream:<init>	(Ljava/io/InputStream;)V
    //   8: astore_3
    //   9: new 849	java/io/InputStreamReader
    //   12: dup
    //   13: aload_3
    //   14: invokespecial 850	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   17: astore_2
    //   18: new 207	java/lang/StringBuilder
    //   21: dup
    //   22: invokespecial 208	java/lang/StringBuilder:<init>	()V
    //   25: astore_0
    //   26: sipush 2048
    //   29: newarray char
    //   31: astore 4
    //   33: aload_2
    //   34: aload 4
    //   36: invokevirtual 853	java/io/InputStreamReader:read	([C)I
    //   39: istore_1
    //   40: iload_1
    //   41: iconst_m1
    //   42: if_icmpeq +15 -> 57
    //   45: aload_0
    //   46: aload 4
    //   48: iconst_0
    //   49: iload_1
    //   50: invokevirtual 856	java/lang/StringBuilder:append	([CII)Ljava/lang/StringBuilder;
    //   53: pop
    //   54: goto -21 -> 33
    //   57: aload_0
    //   58: invokevirtual 221	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   61: astore_0
    //   62: aload_3
    //   63: invokestatic 858	com/facebook/internal/Utility:closeQuietly	(Ljava/io/Closeable;)V
    //   66: aload_2
    //   67: invokestatic 858	com/facebook/internal/Utility:closeQuietly	(Ljava/io/Closeable;)V
    //   70: aload_0
    //   71: areturn
    //   72: astore_0
    //   73: goto +14 -> 87
    //   76: astore_0
    //   77: aconst_null
    //   78: astore_2
    //   79: goto +8 -> 87
    //   82: astore_0
    //   83: aconst_null
    //   84: astore_3
    //   85: aload_3
    //   86: astore_2
    //   87: aload_3
    //   88: invokestatic 858	com/facebook/internal/Utility:closeQuietly	(Ljava/io/Closeable;)V
    //   91: aload_2
    //   92: invokestatic 858	com/facebook/internal/Utility:closeQuietly	(Ljava/io/Closeable;)V
    //   95: aload_0
    //   96: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	97	0	paramInputStream	java.io.InputStream
    //   39	11	1	i	int
    //   17	75	2	localObject	Object
    //   8	80	3	localBufferedInputStream	java.io.BufferedInputStream
    //   31	16	4	arrayOfChar	char[]
    // Exception table:
    //   from	to	target	type
    //   18	33	72	finally
    //   33	40	72	finally
    //   45	54	72	finally
    //   57	62	72	finally
    //   9	18	76	finally
    //   0	9	82	finally
  }
  
  public static Map<String, String> readStringMapFromParcel(Parcel paramParcel)
  {
    int j = paramParcel.readInt();
    if (j < 0) {
      return null;
    }
    HashMap localHashMap = new HashMap();
    int i = 0;
    while (i < j)
    {
      localHashMap.put(paramParcel.readString(), paramParcel.readString());
      i += 1;
    }
    return localHashMap;
  }
  
  private static void refreshAvailableExternalStorage()
  {
    try
    {
      if (externalStorageExists())
      {
        StatFs localStatFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
        availableExternalStorageGB = localStatFs.getAvailableBlocks() * localStatFs.getBlockSize();
      }
      availableExternalStorageGB = convertBytesToGB(availableExternalStorageGB);
      return;
    }
    catch (Exception localException) {}
  }
  
  private static int refreshBestGuessNumberOfCPUCores()
  {
    if (numCPUCores > 0) {
      return numCPUCores;
    }
    try
    {
      File[] arrayOfFile = new File("/sys/devices/system/cpu/").listFiles(new FilenameFilter()
      {
        public boolean accept(File paramAnonymousFile, String paramAnonymousString)
        {
          return Pattern.matches("cpu[0-9]+", paramAnonymousString);
        }
      });
      if (arrayOfFile != null) {
        numCPUCores = arrayOfFile.length;
      }
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    if (numCPUCores <= 0) {
      numCPUCores = Math.max(Runtime.getRuntime().availableProcessors(), 1);
    }
    return numCPUCores;
  }
  
  private static void refreshCarrierName(Context paramContext)
  {
    if (carrierName.equals("NoCarrier")) {}
    try
    {
      carrierName = ((TelephonyManager)paramContext.getSystemService("phone")).getNetworkOperatorName();
      return;
    }
    catch (Exception paramContext) {}
  }
  
  private static void refreshPeriodicExtendedDeviceInfo(Context paramContext)
  {
    if ((timestampOfLastCheck == -1L) || (System.currentTimeMillis() - timestampOfLastCheck >= 1800000L))
    {
      timestampOfLastCheck = System.currentTimeMillis();
      refreshTimezone();
      refreshCarrierName(paramContext);
      refreshTotalExternalStorage();
      refreshAvailableExternalStorage();
    }
  }
  
  private static void refreshTimezone()
  {
    try
    {
      TimeZone localTimeZone = TimeZone.getDefault();
      deviceTimezoneAbbreviation = localTimeZone.getDisplayName(localTimeZone.inDaylightTime(new Date()), 0);
      deviceTimeZoneName = localTimeZone.getID();
      return;
    }
    catch (Exception localException) {}
  }
  
  private static void refreshTotalExternalStorage()
  {
    try
    {
      if (externalStorageExists())
      {
        StatFs localStatFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
        totalExternalStorageGB = localStatFs.getBlockCount() * localStatFs.getBlockSize();
      }
      totalExternalStorageGB = convertBytesToGB(totalExternalStorageGB);
      return;
    }
    catch (Exception localException) {}
  }
  
  public static String safeGetStringFromResponse(JSONObject paramJSONObject, String paramString)
  {
    if (paramJSONObject != null) {
      return paramJSONObject.optString(paramString, "");
    }
    return "";
  }
  
  public static void setAppEventAttributionParameters(JSONObject paramJSONObject, AttributionIdentifiers paramAttributionIdentifiers, String paramString, boolean paramBoolean)
    throws JSONException
  {
    if ((paramAttributionIdentifiers != null) && (paramAttributionIdentifiers.getAttributionId() != null)) {
      paramJSONObject.put("attribution", paramAttributionIdentifiers.getAttributionId());
    }
    if ((paramAttributionIdentifiers != null) && (paramAttributionIdentifiers.getAndroidAdvertiserId() != null))
    {
      paramJSONObject.put("advertiser_id", paramAttributionIdentifiers.getAndroidAdvertiserId());
      paramJSONObject.put("advertiser_tracking_enabled", paramAttributionIdentifiers.isTrackingLimited() ^ true);
    }
    if ((paramAttributionIdentifiers != null) && (paramAttributionIdentifiers.getAndroidInstallerPackage() != null)) {
      paramJSONObject.put("installer_package", paramAttributionIdentifiers.getAndroidInstallerPackage());
    }
    paramJSONObject.put("anon_id", paramString);
    paramJSONObject.put("application_tracking_enabled", paramBoolean ^ true);
  }
  
  public static void setAppEventExtendedDeviceInfoParameters(JSONObject paramJSONObject, Context paramContext)
    throws JSONException
  {
    JSONArray localJSONArray = new JSONArray();
    localJSONArray.put("a2");
    refreshPeriodicExtendedDeviceInfo(paramContext);
    String str = paramContext.getPackageName();
    Object localObject1 = "";
    for (;;)
    {
      try
      {
        localObject2 = paramContext.getPackageManager().getPackageInfo(str, 0);
        i = versionCode;
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException1)
      {
        Object localObject2;
        int i;
        double d;
        continue;
      }
      try
      {
        localObject2 = versionName;
        localObject1 = localObject2;
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException2) {}
    }
    i = -1;
    localJSONArray.put(str);
    localJSONArray.put(i);
    localJSONArray.put(localObject1);
    localJSONArray.put(Build.VERSION.RELEASE);
    localJSONArray.put(Build.MODEL);
    try
    {
      localObject1 = getResourcesgetConfigurationlocale;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    localObject1 = Locale.getDefault();
    localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append(((Locale)localObject1).getLanguage());
    ((StringBuilder)localObject2).append("_");
    ((StringBuilder)localObject2).append(((Locale)localObject1).getCountry());
    localJSONArray.put(((StringBuilder)localObject2).toString());
    localJSONArray.put(deviceTimezoneAbbreviation);
    localJSONArray.put(carrierName);
    d = 0.0D;
    for (;;)
    {
      try
      {
        paramContext = (WindowManager)paramContext.getSystemService("window");
        if (paramContext != null)
        {
          paramContext = paramContext.getDefaultDisplay();
          localObject1 = new DisplayMetrics();
          paramContext.getMetrics((DisplayMetrics)localObject1);
          j = widthPixels;
        }
      }
      catch (Exception paramContext)
      {
        int j;
        float f;
        continue;
      }
      try
      {
        i = heightPixels;
      }
      catch (Exception paramContext)
      {
        continue;
      }
      try
      {
        f = density;
        d = f;
      }
      catch (Exception paramContext) {}
    }
    i = 0;
    break label278;
    j = 0;
    i = j;
    label278:
    localJSONArray.put(j);
    localJSONArray.put(i);
    localJSONArray.put(String.format("%.2f", new Object[] { Double.valueOf(d) }));
    localJSONArray.put(refreshBestGuessNumberOfCPUCores());
    localJSONArray.put(totalExternalStorageGB);
    localJSONArray.put(availableExternalStorageGB);
    localJSONArray.put(deviceTimeZoneName);
    paramJSONObject.put("extinfo", localJSONArray.toString());
  }
  
  public static String sha1hash(String paramString)
  {
    return hashWithAlgorithm("SHA-1", paramString);
  }
  
  public static String sha1hash(byte[] paramArrayOfByte)
  {
    return hashWithAlgorithm("SHA-1", paramArrayOfByte);
  }
  
  public static boolean stringsEqualOrEmpty(String paramString1, String paramString2)
  {
    boolean bool1 = TextUtils.isEmpty(paramString1);
    boolean bool2 = TextUtils.isEmpty(paramString2);
    if ((bool1) && (bool2)) {
      return true;
    }
    if ((!bool1) && (!bool2)) {
      return paramString1.equals(paramString2);
    }
    return false;
  }
  
  public static JSONArray tryGetJSONArrayFromResponse(JSONObject paramJSONObject, String paramString)
  {
    if (paramJSONObject != null) {
      return paramJSONObject.optJSONArray(paramString);
    }
    return null;
  }
  
  public static JSONObject tryGetJSONObjectFromResponse(JSONObject paramJSONObject, String paramString)
  {
    if (paramJSONObject != null) {
      return paramJSONObject.optJSONObject(paramString);
    }
    return null;
  }
  
  public static <T> Collection<T> unmodifiableCollection(T... paramVarArgs)
  {
    return Collections.unmodifiableCollection(Arrays.asList(paramVarArgs));
  }
  
  public static void writeStringMapToParcel(Parcel paramParcel, Map<String, String> paramMap)
  {
    if (paramMap == null)
    {
      paramParcel.writeInt(-1);
      return;
    }
    paramParcel.writeInt(paramMap.size());
    paramMap = paramMap.entrySet().iterator();
    while (paramMap.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)paramMap.next();
      paramParcel.writeString((String)localEntry.getKey());
      paramParcel.writeString((String)localEntry.getValue());
    }
  }
  
  public static abstract interface GraphMeRequestWithCacheCallback
  {
    public abstract void onFailure(FacebookException paramFacebookException);
    
    public abstract void onSuccess(JSONObject paramJSONObject);
  }
  
  public static abstract interface Mapper<T, K>
  {
    public abstract K apply(T paramT);
  }
  
  public static class PermissionsPair
  {
    List<String> declinedPermissions;
    List<String> grantedPermissions;
    
    public PermissionsPair(List<String> paramList1, List<String> paramList2)
    {
      grantedPermissions = paramList1;
      declinedPermissions = paramList2;
    }
    
    public List<String> getDeclinedPermissions()
    {
      return declinedPermissions;
    }
    
    public List<String> getGrantedPermissions()
    {
      return grantedPermissions;
    }
  }
  
  public static abstract interface Predicate<T>
  {
    public abstract boolean apply(T paramT);
  }
}
