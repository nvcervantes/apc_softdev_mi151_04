package com.amplitude.api;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.location.Location;
import android.os.Build.VERSION;
import android.util.Pair;
import com.amplitude.security.MD5;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import okhttp3.Call;
import okhttp3.FormBody.Builder;
import okhttp3.OkHttpClient;
import okhttp3.Request.Builder;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AmplitudeClient
{
  public static final String DEVICE_ID_KEY = "device_id";
  public static final String END_SESSION_EVENT = "session_end";
  public static final String LAST_EVENT_ID_KEY = "last_event_id";
  public static final String LAST_EVENT_TIME_KEY = "last_event_time";
  public static final String LAST_IDENTIFY_ID_KEY = "last_identify_id";
  public static final String OPT_OUT_KEY = "opt_out";
  public static final String PREVIOUS_SESSION_ID_KEY = "previous_session_id";
  public static final String SEQUENCE_NUMBER_KEY = "sequence_number";
  public static final String START_SESSION_EVENT = "session_start";
  public static final String TAG = "com.amplitude.api.AmplitudeClient";
  public static final String USER_ID_KEY = "user_id";
  private static final AmplitudeLog logger = ;
  protected String apiKey;
  private boolean backoffUpload = false;
  private int backoffUploadBatchSize = eventUploadMaxBatchSize;
  protected Context context;
  protected DatabaseHelper dbHelper;
  protected String deviceId;
  private DeviceInfo deviceInfo;
  private int eventMaxCount = 1000;
  private int eventUploadMaxBatchSize = 100;
  private long eventUploadPeriodMillis = 30000L;
  private int eventUploadThreshold = 30;
  private boolean flushEventsOnClose = true;
  protected OkHttpClient httpClient;
  WorkerThread httpThread = new WorkerThread("httpThread");
  private boolean inForeground = false;
  protected boolean initialized = false;
  protected String instanceName;
  Throwable lastError;
  long lastEventId = -1L;
  long lastEventTime = -1L;
  long lastIdentifyId = -1L;
  WorkerThread logThread = new WorkerThread("logThread");
  private long minTimeBetweenSessionsMillis = 300000L;
  private boolean newDeviceIdPerInstall = false;
  private boolean offline = false;
  private boolean optOut = false;
  long previousSessionId = -1L;
  long sequenceNumber = 0L;
  long sessionId = -1L;
  private long sessionTimeoutMillis = 1800000L;
  private boolean trackingSessionEvents = false;
  private AtomicBoolean updateScheduled = new AtomicBoolean(false);
  AtomicBoolean uploadingCurrently = new AtomicBoolean(false);
  String url = "https://api.amplitude.com/";
  private boolean useAdvertisingIdForDeviceId = false;
  protected String userId;
  private boolean usingForegroundTracking = false;
  
  public AmplitudeClient()
  {
    this(null);
  }
  
  public AmplitudeClient(String paramString)
  {
    instanceName = Utils.normalizeInstanceName(paramString);
    logThread.start();
    httpThread.start();
  }
  
  private Set<String> getInvalidDeviceIds()
  {
    HashSet localHashSet = new HashSet();
    localHashSet.add("");
    localHashSet.add("9774d56d682e549c");
    localHashSet.add("unknown");
    localHashSet.add("000000000000000");
    localHashSet.add("Android");
    localHashSet.add("DEFACE");
    localHashSet.add("00000000-0000-0000-0000-000000000000");
    return localHashSet;
  }
  
  private long getLongvalue(String paramString, long paramLong)
  {
    paramString = dbHelper.getLongValue(paramString);
    if (paramString == null) {
      return paramLong;
    }
    return paramString.longValue();
  }
  
  private boolean inSession()
  {
    return sessionId >= 0L;
  }
  
  private String initializeDeviceId()
  {
    Object localObject1 = getInvalidDeviceIds();
    Object localObject2 = dbHelper.getValue("device_id");
    if ((!Utils.isEmptyString((String)localObject2)) && (!((Set)localObject1).contains(localObject2))) {
      return localObject2;
    }
    if ((!newDeviceIdPerInstall) && (useAdvertisingIdForDeviceId))
    {
      localObject2 = deviceInfo.getAdvertisingId();
      if ((!Utils.isEmptyString((String)localObject2)) && (!((Set)localObject1).contains(localObject2)))
      {
        dbHelper.insertOrReplaceKeyValue("device_id", (String)localObject2);
        return localObject2;
      }
    }
    localObject1 = new StringBuilder();
    localObject2 = deviceInfo;
    ((StringBuilder)localObject1).append(DeviceInfo.generateUUID());
    ((StringBuilder)localObject1).append("R");
    localObject1 = ((StringBuilder)localObject1).toString();
    dbHelper.insertOrReplaceKeyValue("device_id", (String)localObject1);
    return localObject1;
  }
  
  private void initializeDeviceInfo()
  {
    deviceInfo = new DeviceInfo(context);
    deviceId = initializeDeviceId();
    deviceInfo.prefetch();
  }
  
  private boolean isWithinMinTimeBetweenSessions(long paramLong)
  {
    long l;
    if (usingForegroundTracking) {
      l = minTimeBetweenSessionsMillis;
    } else {
      l = sessionTimeoutMillis;
    }
    return paramLong - lastEventTime < l;
  }
  
  private static void migrateBooleanValue(SharedPreferences paramSharedPreferences, String paramString1, boolean paramBoolean, DatabaseHelper paramDatabaseHelper, String paramString2)
  {
    if (paramDatabaseHelper.getLongValue(paramString2) != null) {
      return;
    }
    long l;
    if (paramSharedPreferences.getBoolean(paramString1, paramBoolean)) {
      l = 1L;
    } else {
      l = 0L;
    }
    paramDatabaseHelper.insertOrReplaceKeyLongValue(paramString2, Long.valueOf(l));
    paramSharedPreferences.edit().remove(paramString1).apply();
  }
  
  private static void migrateLongValue(SharedPreferences paramSharedPreferences, String paramString1, long paramLong, DatabaseHelper paramDatabaseHelper, String paramString2)
  {
    if (paramDatabaseHelper.getLongValue(paramString2) != null) {
      return;
    }
    paramDatabaseHelper.insertOrReplaceKeyLongValue(paramString2, Long.valueOf(paramSharedPreferences.getLong(paramString1, paramLong)));
    paramSharedPreferences.edit().remove(paramString1).apply();
  }
  
  private static void migrateStringValue(SharedPreferences paramSharedPreferences, String paramString1, String paramString2, DatabaseHelper paramDatabaseHelper, String paramString3)
  {
    if (!Utils.isEmptyString(paramDatabaseHelper.getValue(paramString3))) {
      return;
    }
    paramString2 = paramSharedPreferences.getString(paramString1, paramString2);
    if (!Utils.isEmptyString(paramString2))
    {
      paramDatabaseHelper.insertOrReplaceKeyValue(paramString3, paramString2);
      paramSharedPreferences.edit().remove(paramString1).apply();
    }
  }
  
  private void sendSessionEvent(String paramString)
  {
    if (!contextAndApiKeySet(String.format("sendSessionEvent('%s')", new Object[] { paramString }))) {
      return;
    }
    if (!inSession()) {
      return;
    }
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("special", paramString);
      logEvent(paramString, null, localJSONObject, null, null, lastEventTime, false);
      return;
    }
    catch (JSONException paramString) {}
  }
  
  private void setSessionId(long paramLong)
  {
    sessionId = paramLong;
    setPreviousSessionId(paramLong);
  }
  
  private void startNewSession(long paramLong)
  {
    if (trackingSessionEvents) {
      sendSessionEvent("session_end");
    }
    setSessionId(paramLong);
    refreshSessionTime(paramLong);
    if (trackingSessionEvents) {
      sendSessionEvent("session_start");
    }
  }
  
  private void updateServerLater(long paramLong)
  {
    if (updateScheduled.getAndSet(true)) {
      return;
    }
    logThread.postDelayed(new Runnable()
    {
      public void run()
      {
        updateScheduled.set(false);
        updateServer();
      }
    }, paramLong);
  }
  
  static boolean upgradePrefs(Context paramContext)
  {
    return upgradePrefs(paramContext, null, null);
  }
  
  /* Error */
  static boolean upgradePrefs(Context paramContext, String paramString1, String paramString2)
  {
    // Byte code:
    //   0: aload_1
    //   1: astore_3
    //   2: aload_1
    //   3: ifnonnull +26 -> 29
    //   6: ldc_w 450
    //   9: astore_3
    //   10: ldc_w 452
    //   13: invokevirtual 458	java/lang/Class:getPackage	()Ljava/lang/Package;
    //   16: invokevirtual 463	java/lang/Package:getName	()Ljava/lang/String;
    //   19: astore_1
    //   20: aload_1
    //   21: astore_3
    //   22: goto +7 -> 29
    //   25: astore_0
    //   26: goto +567 -> 593
    //   29: aload_2
    //   30: astore_1
    //   31: aload_2
    //   32: ifnonnull +7 -> 39
    //   35: ldc_w 450
    //   38: astore_1
    //   39: aload_1
    //   40: aload_3
    //   41: invokevirtual 466	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   44: ifeq +5 -> 49
    //   47: iconst_0
    //   48: ireturn
    //   49: new 321	java/lang/StringBuilder
    //   52: dup
    //   53: invokespecial 322	java/lang/StringBuilder:<init>	()V
    //   56: astore_2
    //   57: aload_2
    //   58: aload_3
    //   59: invokevirtual 329	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   62: pop
    //   63: aload_2
    //   64: ldc_w 468
    //   67: invokevirtual 329	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   70: pop
    //   71: aload_2
    //   72: aload_0
    //   73: invokevirtual 473	android/content/Context:getPackageName	()Ljava/lang/String;
    //   76: invokevirtual 329	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   79: pop
    //   80: aload_2
    //   81: invokevirtual 334	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   84: astore_2
    //   85: aload_0
    //   86: aload_2
    //   87: iconst_0
    //   88: invokevirtual 477	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   91: astore 4
    //   93: aload 4
    //   95: invokeinterface 481 1 0
    //   100: invokeinterface 487 1 0
    //   105: ifne +5 -> 110
    //   108: iconst_0
    //   109: ireturn
    //   110: new 321	java/lang/StringBuilder
    //   113: dup
    //   114: invokespecial 322	java/lang/StringBuilder:<init>	()V
    //   117: astore 5
    //   119: aload 5
    //   121: aload_1
    //   122: invokevirtual 329	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   125: pop
    //   126: aload 5
    //   128: ldc_w 468
    //   131: invokevirtual 329	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   134: pop
    //   135: aload 5
    //   137: aload_0
    //   138: invokevirtual 473	android/content/Context:getPackageName	()Ljava/lang/String;
    //   141: invokevirtual 329	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   144: pop
    //   145: aload 5
    //   147: invokevirtual 334	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   150: astore_1
    //   151: aload_0
    //   152: aload_1
    //   153: iconst_0
    //   154: invokevirtual 477	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   157: invokeinterface 368 1 0
    //   162: astore_0
    //   163: new 321	java/lang/StringBuilder
    //   166: dup
    //   167: invokespecial 322	java/lang/StringBuilder:<init>	()V
    //   170: astore 5
    //   172: aload 5
    //   174: aload_3
    //   175: invokevirtual 329	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   178: pop
    //   179: aload 5
    //   181: ldc_w 489
    //   184: invokevirtual 329	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   187: pop
    //   188: aload 4
    //   190: aload 5
    //   192: invokevirtual 334	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   195: invokeinterface 491 2 0
    //   200: ifeq +53 -> 253
    //   203: new 321	java/lang/StringBuilder
    //   206: dup
    //   207: invokespecial 322	java/lang/StringBuilder:<init>	()V
    //   210: astore 5
    //   212: aload 5
    //   214: aload_3
    //   215: invokevirtual 329	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   218: pop
    //   219: aload 5
    //   221: ldc_w 489
    //   224: invokevirtual 329	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   227: pop
    //   228: aload_0
    //   229: ldc_w 493
    //   232: aload 4
    //   234: aload 5
    //   236: invokevirtual 334	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   239: ldc2_w 147
    //   242: invokeinterface 382 4 0
    //   247: invokeinterface 497 4 0
    //   252: pop
    //   253: new 321	java/lang/StringBuilder
    //   256: dup
    //   257: invokespecial 322	java/lang/StringBuilder:<init>	()V
    //   260: astore 5
    //   262: aload 5
    //   264: aload_3
    //   265: invokevirtual 329	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   268: pop
    //   269: aload 5
    //   271: ldc_w 499
    //   274: invokevirtual 329	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   277: pop
    //   278: aload 4
    //   280: aload 5
    //   282: invokevirtual 334	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   285: invokeinterface 491 2 0
    //   290: ifeq +51 -> 341
    //   293: new 321	java/lang/StringBuilder
    //   296: dup
    //   297: invokespecial 322	java/lang/StringBuilder:<init>	()V
    //   300: astore 5
    //   302: aload 5
    //   304: aload_3
    //   305: invokevirtual 329	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   308: pop
    //   309: aload 5
    //   311: ldc_w 499
    //   314: invokevirtual 329	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   317: pop
    //   318: aload_0
    //   319: ldc_w 501
    //   322: aload 4
    //   324: aload 5
    //   326: invokevirtual 334	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   329: aconst_null
    //   330: invokeinterface 388 3 0
    //   335: invokeinterface 505 3 0
    //   340: pop
    //   341: new 321	java/lang/StringBuilder
    //   344: dup
    //   345: invokespecial 322	java/lang/StringBuilder:<init>	()V
    //   348: astore 5
    //   350: aload 5
    //   352: aload_3
    //   353: invokevirtual 329	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   356: pop
    //   357: aload 5
    //   359: ldc_w 507
    //   362: invokevirtual 329	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   365: pop
    //   366: aload 4
    //   368: aload 5
    //   370: invokevirtual 334	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   373: invokeinterface 491 2 0
    //   378: ifeq +51 -> 429
    //   381: new 321	java/lang/StringBuilder
    //   384: dup
    //   385: invokespecial 322	java/lang/StringBuilder:<init>	()V
    //   388: astore 5
    //   390: aload 5
    //   392: aload_3
    //   393: invokevirtual 329	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   396: pop
    //   397: aload 5
    //   399: ldc_w 507
    //   402: invokevirtual 329	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   405: pop
    //   406: aload_0
    //   407: ldc_w 509
    //   410: aload 4
    //   412: aload 5
    //   414: invokevirtual 334	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   417: aconst_null
    //   418: invokeinterface 388 3 0
    //   423: invokeinterface 505 3 0
    //   428: pop
    //   429: new 321	java/lang/StringBuilder
    //   432: dup
    //   433: invokespecial 322	java/lang/StringBuilder:<init>	()V
    //   436: astore 5
    //   438: aload 5
    //   440: aload_3
    //   441: invokevirtual 329	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   444: pop
    //   445: aload 5
    //   447: ldc_w 511
    //   450: invokevirtual 329	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   453: pop
    //   454: aload 4
    //   456: aload 5
    //   458: invokevirtual 334	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   461: invokeinterface 491 2 0
    //   466: ifeq +51 -> 517
    //   469: new 321	java/lang/StringBuilder
    //   472: dup
    //   473: invokespecial 322	java/lang/StringBuilder:<init>	()V
    //   476: astore 5
    //   478: aload 5
    //   480: aload_3
    //   481: invokevirtual 329	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   484: pop
    //   485: aload 5
    //   487: ldc_w 511
    //   490: invokevirtual 329	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   493: pop
    //   494: aload_0
    //   495: ldc_w 513
    //   498: aload 4
    //   500: aload 5
    //   502: invokevirtual 334	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   505: iconst_0
    //   506: invokeinterface 356 3 0
    //   511: invokeinterface 517 3 0
    //   516: pop
    //   517: aload_0
    //   518: invokeinterface 377 1 0
    //   523: aload 4
    //   525: invokeinterface 368 1 0
    //   530: invokeinterface 520 1 0
    //   535: invokeinterface 377 1 0
    //   540: getstatic 129	com/amplitude/api/AmplitudeClient:logger	Lcom/amplitude/api/AmplitudeLog;
    //   543: astore_0
    //   544: new 321	java/lang/StringBuilder
    //   547: dup
    //   548: invokespecial 322	java/lang/StringBuilder:<init>	()V
    //   551: astore_3
    //   552: aload_3
    //   553: ldc_w 522
    //   556: invokevirtual 329	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   559: pop
    //   560: aload_3
    //   561: aload_2
    //   562: invokevirtual 329	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   565: pop
    //   566: aload_3
    //   567: ldc_w 524
    //   570: invokevirtual 329	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   573: pop
    //   574: aload_3
    //   575: aload_1
    //   576: invokevirtual 329	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   579: pop
    //   580: aload_0
    //   581: ldc 67
    //   583: aload_3
    //   584: invokevirtual 334	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   587: invokevirtual 528	com/amplitude/api/AmplitudeLog:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   590: pop
    //   591: iconst_1
    //   592: ireturn
    //   593: getstatic 129	com/amplitude/api/AmplitudeClient:logger	Lcom/amplitude/api/AmplitudeLog;
    //   596: ldc 67
    //   598: ldc_w 530
    //   601: aload_0
    //   602: invokevirtual 534	com/amplitude/api/AmplitudeLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   605: pop
    //   606: iconst_0
    //   607: ireturn
    //   608: astore_1
    //   609: goto -580 -> 29
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	612	0	paramContext	Context
    //   0	612	1	paramString1	String
    //   0	612	2	paramString2	String
    //   1	583	3	localObject	Object
    //   91	433	4	localSharedPreferences	SharedPreferences
    //   117	384	5	localStringBuilder	StringBuilder
    // Exception table:
    //   from	to	target	type
    //   39	47	25	java/lang/Exception
    //   49	108	25	java/lang/Exception
    //   110	253	25	java/lang/Exception
    //   253	341	25	java/lang/Exception
    //   341	429	25	java/lang/Exception
    //   429	517	25	java/lang/Exception
    //   517	591	25	java/lang/Exception
    //   10	20	608	java/lang/Exception
  }
  
  static boolean upgradeSharedPrefsToDB(Context paramContext)
  {
    return upgradeSharedPrefsToDB(paramContext, null);
  }
  
  static boolean upgradeSharedPrefsToDB(Context paramContext, String paramString)
  {
    String str = paramString;
    if (paramString == null) {
      str = "com.amplitude.api";
    }
    paramString = DatabaseHelper.getDatabaseHelper(paramContext);
    Object localObject = paramString.getValue("device_id");
    Long localLong1 = paramString.getLongValue("previous_session_id");
    Long localLong2 = paramString.getLongValue("last_event_time");
    if ((!Utils.isEmptyString((String)localObject)) && (localLong1 != null) && (localLong2 != null)) {
      return true;
    }
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append(str);
    ((StringBuilder)localObject).append(".");
    ((StringBuilder)localObject).append(paramContext.getPackageName());
    paramContext = paramContext.getSharedPreferences(((StringBuilder)localObject).toString(), 0);
    migrateStringValue(paramContext, "com.amplitude.api.deviceId", null, paramString, "device_id");
    migrateLongValue(paramContext, "com.amplitude.api.lastEventTime", -1L, paramString, "last_event_time");
    migrateLongValue(paramContext, "com.amplitude.api.lastEventId", -1L, paramString, "last_event_id");
    migrateLongValue(paramContext, "com.amplitude.api.lastIdentifyId", -1L, paramString, "last_identify_id");
    migrateLongValue(paramContext, "com.amplitude.api.previousSessionId", -1L, paramString, "previous_session_id");
    migrateStringValue(paramContext, "com.amplitude.api.userId", null, paramString, "user_id");
    migrateBooleanValue(paramContext, "com.amplitude.api.optOut", false, paramString, "opt_out");
    return true;
  }
  
  protected String bytesToHexString(byte[] paramArrayOfByte)
  {
    char[] arrayOfChar1 = new char[16];
    char[] tmp8_6 = arrayOfChar1;
    tmp8_6[0] = 48;
    char[] tmp14_8 = tmp8_6;
    tmp14_8[1] = 49;
    char[] tmp20_14 = tmp14_8;
    tmp20_14[2] = 50;
    char[] tmp26_20 = tmp20_14;
    tmp26_20[3] = 51;
    char[] tmp32_26 = tmp26_20;
    tmp32_26[4] = 52;
    char[] tmp38_32 = tmp32_26;
    tmp38_32[5] = 53;
    char[] tmp44_38 = tmp38_32;
    tmp44_38[6] = 54;
    char[] tmp51_44 = tmp44_38;
    tmp51_44[7] = 55;
    char[] tmp58_51 = tmp51_44;
    tmp58_51[8] = 56;
    char[] tmp65_58 = tmp58_51;
    tmp65_58[9] = 57;
    char[] tmp72_65 = tmp65_58;
    tmp72_65[10] = 97;
    char[] tmp79_72 = tmp72_65;
    tmp79_72[11] = 98;
    char[] tmp86_79 = tmp79_72;
    tmp86_79[12] = 99;
    char[] tmp93_86 = tmp86_79;
    tmp93_86[13] = 100;
    char[] tmp100_93 = tmp93_86;
    tmp100_93[14] = 101;
    char[] tmp107_100 = tmp100_93;
    tmp107_100[15] = 102;
    tmp107_100;
    int i = 0;
    char[] arrayOfChar2 = new char[paramArrayOfByte.length * 2];
    while (i < paramArrayOfByte.length)
    {
      int j = paramArrayOfByte[i] & 0xFF;
      int k = i * 2;
      arrayOfChar2[k] = arrayOfChar1[(j >>> 4)];
      arrayOfChar2[(k + 1)] = arrayOfChar1[(j & 0xF)];
      i += 1;
    }
    return new String(arrayOfChar2);
  }
  
  public void clearUserProperties()
  {
    identify(new Identify().clearAll());
  }
  
  protected boolean contextAndApiKeySet(String paramString)
  {
    try
    {
      AmplitudeLog localAmplitudeLog;
      StringBuilder localStringBuilder;
      if (context == null)
      {
        localAmplitudeLog = logger;
        localStringBuilder = new StringBuilder();
        localStringBuilder.append("context cannot be null, set context with initialize() before calling ");
        localStringBuilder.append(paramString);
        localAmplitudeLog.e("com.amplitude.api.AmplitudeClient", localStringBuilder.toString());
        return false;
      }
      if (Utils.isEmptyString(apiKey))
      {
        localAmplitudeLog = logger;
        localStringBuilder = new StringBuilder();
        localStringBuilder.append("apiKey cannot be null or empty, set apiKey with initialize() before calling ");
        localStringBuilder.append(paramString);
        localAmplitudeLog.e("com.amplitude.api.AmplitudeClient", localStringBuilder.toString());
        return false;
      }
      return true;
    }
    finally {}
  }
  
  public AmplitudeClient disableLocationListening()
  {
    runOnLogThread(new Runnable()
    {
      public void run()
      {
        if (deviceInfo == null) {
          throw new IllegalStateException("Must initialize before acting on location listening.");
        }
        deviceInfo.setLocationListening(false);
      }
    });
    return this;
  }
  
  public AmplitudeClient enableForegroundTracking(Application paramApplication)
  {
    if (!usingForegroundTracking)
    {
      if (!contextAndApiKeySet("enableForegroundTracking()")) {
        return this;
      }
      if (Build.VERSION.SDK_INT >= 14) {
        paramApplication.registerActivityLifecycleCallbacks(new AmplitudeCallbacks(this));
      }
      return this;
    }
    return this;
  }
  
  public AmplitudeClient enableLocationListening()
  {
    runOnLogThread(new Runnable()
    {
      public void run()
      {
        if (deviceInfo == null) {
          throw new IllegalStateException("Must initialize before acting on location listening.");
        }
        deviceInfo.setLocationListening(true);
      }
    });
    return this;
  }
  
  public AmplitudeClient enableLogging(boolean paramBoolean)
  {
    logger.setEnableLogging(paramBoolean);
    return this;
  }
  
  public AmplitudeClient enableNewDeviceIdPerInstall(boolean paramBoolean)
  {
    newDeviceIdPerInstall = paramBoolean;
    return this;
  }
  
  protected long getCurrentTimeMillis()
  {
    return System.currentTimeMillis();
  }
  
  public String getDeviceId()
  {
    return deviceId;
  }
  
  long getNextSequenceNumber()
  {
    sequenceNumber += 1L;
    dbHelper.insertOrReplaceKeyLongValue("sequence_number", Long.valueOf(sequenceNumber));
    return sequenceNumber;
  }
  
  public long getSessionId()
  {
    return sessionId;
  }
  
  public String getUserId()
  {
    return userId;
  }
  
  public void identify(Identify paramIdentify)
  {
    identify(paramIdentify, false);
  }
  
  public void identify(Identify paramIdentify, boolean paramBoolean)
  {
    if ((paramIdentify != null) && (userPropertiesOperations.length() != 0))
    {
      if (!contextAndApiKeySet("identify()")) {
        return;
      }
      logEventAsync("$identify", null, null, userPropertiesOperations, null, getCurrentTimeMillis(), paramBoolean);
      return;
    }
  }
  
  public AmplitudeClient initialize(Context paramContext, String paramString)
  {
    return initialize(paramContext, paramString, null);
  }
  
  public AmplitudeClient initialize(final Context paramContext, String paramString1, final String paramString2)
  {
    if (paramContext == null) {
      try
      {
        logger.e("com.amplitude.api.AmplitudeClient", "Argument context cannot be null in initialize()");
        return this;
      }
      finally
      {
        break label96;
      }
    }
    if (Utils.isEmptyString(paramString1))
    {
      logger.e("com.amplitude.api.AmplitudeClient", "Argument apiKey cannot be null or blank in initialize()");
      return this;
    }
    context = paramContext.getApplicationContext();
    apiKey = paramString1;
    dbHelper = DatabaseHelper.getDatabaseHelper(context, instanceName);
    runOnLogThread(new Runnable()
    {
      public void run()
      {
        if (!initialized) {}
        for (;;)
        {
          try
          {
            if (instanceName.equals("$default_instance"))
            {
              AmplitudeClient.upgradePrefs(paramContext);
              AmplitudeClient.upgradeSharedPrefsToDB(paramContext);
            }
            httpClient = new OkHttpClient();
            AmplitudeClient.this.initializeDeviceInfo();
            if (paramString2 != null)
            {
              jdField_thisuserId = paramString2;
              dbHelper.insertOrReplaceKeyValue("user_id", paramString2);
            }
            else
            {
              jdField_thisuserId = dbHelper.getValue("user_id");
            }
            Long localLong = dbHelper.getLongValue("opt_out");
            AmplitudeClient localAmplitudeClient = AmplitudeClient.this;
            if ((localLong == null) || (localLong.longValue() != 1L)) {
              break label324;
            }
            bool = true;
            AmplitudeClient.access$102(localAmplitudeClient, bool);
            previousSessionId = AmplitudeClient.this.getLongvalue("previous_session_id", -1L);
            if (previousSessionId >= 0L) {
              sessionId = previousSessionId;
            }
            sequenceNumber = AmplitudeClient.this.getLongvalue("sequence_number", 0L);
            lastEventId = AmplitudeClient.this.getLongvalue("last_event_id", -1L);
            lastIdentifyId = AmplitudeClient.this.getLongvalue("last_identify_id", -1L);
            lastEventTime = AmplitudeClient.this.getLongvalue("last_event_time", -1L);
            initialized = true;
            return;
          }
          catch (CursorWindowAllocationException localCursorWindowAllocationException)
          {
            AmplitudeClient.logger.e("com.amplitude.api.AmplitudeClient", String.format("Failed to initialize Amplitude SDK due to: %s", new Object[] { localCursorWindowAllocationException.getMessage() }));
            jdField_thisapiKey = null;
          }
          return;
          label324:
          boolean bool = false;
        }
      }
    });
    return this;
    label96:
    throw paramContext;
  }
  
  boolean isInForeground()
  {
    return inForeground;
  }
  
  public boolean isOptedOut()
  {
    return optOut;
  }
  
  boolean isUsingForegroundTracking()
  {
    return usingForegroundTracking;
  }
  
  protected long logEvent(String paramString, JSONObject paramJSONObject1, JSONObject paramJSONObject2, JSONObject paramJSONObject3, JSONObject paramJSONObject4, long paramLong, boolean paramBoolean)
  {
    Object localObject1 = logger;
    Object localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append("Logged event to Amplitude: ");
    ((StringBuilder)localObject2).append(paramString);
    ((AmplitudeLog)localObject1).d("com.amplitude.api.AmplitudeClient", ((StringBuilder)localObject2).toString());
    if (optOut) {
      return -1L;
    }
    int i;
    if ((trackingSessionEvents) && ((paramString.equals("session_start")) || (paramString.equals("session_end")))) {
      i = 1;
    } else {
      i = 0;
    }
    if ((i == 0) && (!paramBoolean)) {
      if (!inForeground) {
        startNewSessionIfNeeded(paramLong);
      } else {
        refreshSessionTime(paramLong);
      }
    }
    localObject2 = new JSONObject();
    try
    {
      ((JSONObject)localObject2).put("event_type", replaceWithJSONNull(paramString));
      ((JSONObject)localObject2).put("timestamp", paramLong);
      ((JSONObject)localObject2).put("user_id", replaceWithJSONNull(userId));
      ((JSONObject)localObject2).put("device_id", replaceWithJSONNull(deviceId));
      if (paramBoolean) {
        paramLong = -1L;
      } else {
        paramLong = sessionId;
      }
      ((JSONObject)localObject2).put("session_id", paramLong);
      ((JSONObject)localObject2).put("version_name", replaceWithJSONNull(deviceInfo.getVersionName()));
      ((JSONObject)localObject2).put("os_name", replaceWithJSONNull(deviceInfo.getOsName()));
      ((JSONObject)localObject2).put("os_version", replaceWithJSONNull(deviceInfo.getOsVersion()));
      ((JSONObject)localObject2).put("device_brand", replaceWithJSONNull(deviceInfo.getBrand()));
      ((JSONObject)localObject2).put("device_manufacturer", replaceWithJSONNull(deviceInfo.getManufacturer()));
      ((JSONObject)localObject2).put("device_model", replaceWithJSONNull(deviceInfo.getModel()));
      ((JSONObject)localObject2).put("carrier", replaceWithJSONNull(deviceInfo.getCarrier()));
      ((JSONObject)localObject2).put("country", replaceWithJSONNull(deviceInfo.getCountry()));
      ((JSONObject)localObject2).put("language", replaceWithJSONNull(deviceInfo.getLanguage()));
      ((JSONObject)localObject2).put("platform", "Android");
      ((JSONObject)localObject2).put("uuid", UUID.randomUUID().toString());
      ((JSONObject)localObject2).put("sequence_number", getNextSequenceNumber());
      localObject1 = new JSONObject();
      ((JSONObject)localObject1).put("name", "amplitude-android");
      ((JSONObject)localObject1).put("version", "2.16.0");
      ((JSONObject)localObject2).put("library", localObject1);
      localObject1 = paramJSONObject2;
      if (paramJSONObject2 == null) {
        localObject1 = new JSONObject();
      }
      paramJSONObject2 = deviceInfo.getMostRecentLocation();
      if (paramJSONObject2 != null)
      {
        JSONObject localJSONObject = new JSONObject();
        localJSONObject.put("lat", paramJSONObject2.getLatitude());
        localJSONObject.put("lng", paramJSONObject2.getLongitude());
        ((JSONObject)localObject1).put("location", localJSONObject);
      }
      if (deviceInfo.getAdvertisingId() != null) {
        ((JSONObject)localObject1).put("androidADID", deviceInfo.getAdvertisingId());
      }
      ((JSONObject)localObject1).put("limit_ad_tracking", deviceInfo.isLimitAdTrackingEnabled());
      ((JSONObject)localObject1).put("gps_enabled", deviceInfo.isGooglePlayServicesEnabled());
      ((JSONObject)localObject2).put("api_properties", localObject1);
      if (paramJSONObject1 == null) {
        paramJSONObject1 = new JSONObject();
      } else {
        paramJSONObject1 = truncate(paramJSONObject1);
      }
      ((JSONObject)localObject2).put("event_properties", paramJSONObject1);
      if (paramJSONObject3 == null) {
        paramJSONObject1 = new JSONObject();
      } else {
        paramJSONObject1 = truncate(paramJSONObject3);
      }
      ((JSONObject)localObject2).put("user_properties", paramJSONObject1);
      if (paramJSONObject4 == null) {
        paramJSONObject1 = new JSONObject();
      } else {
        paramJSONObject1 = truncate(paramJSONObject4);
      }
      ((JSONObject)localObject2).put("groups", paramJSONObject1);
      paramLong = saveEvent(paramString, (JSONObject)localObject2);
      return paramLong;
    }
    catch (JSONException paramJSONObject1)
    {
      logger.e("com.amplitude.api.AmplitudeClient", String.format("JSON Serialization of event type %s failed, skipping: %s", new Object[] { paramString, paramJSONObject1.toString() }));
    }
    return -1L;
  }
  
  public void logEvent(String paramString)
  {
    logEvent(paramString, null);
  }
  
  public void logEvent(String paramString, JSONObject paramJSONObject)
  {
    logEvent(paramString, paramJSONObject, false);
  }
  
  public void logEvent(String paramString, JSONObject paramJSONObject1, JSONObject paramJSONObject2)
  {
    logEvent(paramString, paramJSONObject1, paramJSONObject2, false);
  }
  
  public void logEvent(String paramString, JSONObject paramJSONObject1, JSONObject paramJSONObject2, long paramLong, boolean paramBoolean)
  {
    if (validateLogEvent(paramString)) {
      logEventAsync(paramString, paramJSONObject1, null, null, paramJSONObject2, paramLong, paramBoolean);
    }
  }
  
  public void logEvent(String paramString, JSONObject paramJSONObject1, JSONObject paramJSONObject2, boolean paramBoolean)
  {
    logEvent(paramString, paramJSONObject1, paramJSONObject2, getCurrentTimeMillis(), paramBoolean);
  }
  
  public void logEvent(String paramString, JSONObject paramJSONObject, boolean paramBoolean)
  {
    logEvent(paramString, paramJSONObject, null, paramBoolean);
  }
  
  protected void logEventAsync(final String paramString, final JSONObject paramJSONObject1, final JSONObject paramJSONObject2, final JSONObject paramJSONObject3, final JSONObject paramJSONObject4, final long paramLong, boolean paramBoolean)
  {
    if (paramJSONObject1 != null) {
      paramJSONObject1 = Utils.cloneJSONObject(paramJSONObject1);
    }
    if (paramJSONObject3 != null) {
      paramJSONObject3 = Utils.cloneJSONObject(paramJSONObject3);
    }
    if (paramJSONObject4 != null) {
      paramJSONObject4 = Utils.cloneJSONObject(paramJSONObject4);
    }
    runOnLogThread(new Runnable()
    {
      public void run()
      {
        if (Utils.isEmptyString(apiKey)) {
          return;
        }
        logEvent(paramString, paramJSONObject1, paramJSONObject2, paramJSONObject3, paramJSONObject4, paramLong, val$outOfSession);
      }
    });
  }
  
  public void logEventSync(String paramString)
  {
    logEventSync(paramString, null);
  }
  
  public void logEventSync(String paramString, JSONObject paramJSONObject)
  {
    logEventSync(paramString, paramJSONObject, false);
  }
  
  public void logEventSync(String paramString, JSONObject paramJSONObject1, JSONObject paramJSONObject2)
  {
    logEventSync(paramString, paramJSONObject1, paramJSONObject2, false);
  }
  
  public void logEventSync(String paramString, JSONObject paramJSONObject1, JSONObject paramJSONObject2, long paramLong, boolean paramBoolean)
  {
    if (validateLogEvent(paramString)) {
      logEvent(paramString, paramJSONObject1, null, null, paramJSONObject2, paramLong, paramBoolean);
    }
  }
  
  public void logEventSync(String paramString, JSONObject paramJSONObject1, JSONObject paramJSONObject2, boolean paramBoolean)
  {
    logEventSync(paramString, paramJSONObject1, paramJSONObject2, getCurrentTimeMillis(), paramBoolean);
  }
  
  public void logEventSync(String paramString, JSONObject paramJSONObject, boolean paramBoolean)
  {
    logEventSync(paramString, paramJSONObject, null, paramBoolean);
  }
  
  public void logRevenue(double paramDouble)
  {
    logRevenue(null, 1, paramDouble);
  }
  
  public void logRevenue(String paramString, int paramInt, double paramDouble)
  {
    logRevenue(paramString, paramInt, paramDouble, null, null);
  }
  
  public void logRevenue(String paramString1, int paramInt, double paramDouble, String paramString2, String paramString3)
  {
    if (!contextAndApiKeySet("logRevenue()")) {
      return;
    }
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("special", "revenue_amount");
      localJSONObject.put("productId", paramString1);
      localJSONObject.put("quantity", paramInt);
      localJSONObject.put("price", paramDouble);
      localJSONObject.put("receipt", paramString2);
      localJSONObject.put("receiptSig", paramString3);
      logEventAsync("revenue_amount", null, localJSONObject, null, null, getCurrentTimeMillis(), false);
      return;
    }
    catch (JSONException paramString1)
    {
      for (;;) {}
    }
  }
  
  public void logRevenueV2(Revenue paramRevenue)
  {
    if ((contextAndApiKeySet("logRevenueV2()")) && (paramRevenue != null))
    {
      if (!paramRevenue.isValidRevenue()) {
        return;
      }
      logEvent("revenue_amount", paramRevenue.toJSONObject());
      return;
    }
  }
  
  protected void makeEventUploadPostRequest(OkHttpClient paramOkHttpClient, String paramString, final long paramLong1, long paramLong2)
  {
    Object localObject1 = new StringBuilder();
    ((StringBuilder)localObject1).append("");
    ((StringBuilder)localObject1).append(getCurrentTimeMillis());
    String str = ((StringBuilder)localObject1).toString();
    localObject1 = "";
    try
    {
      Object localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append("2");
      ((StringBuilder)localObject2).append(apiKey);
      ((StringBuilder)localObject2).append(paramString);
      ((StringBuilder)localObject2).append(str);
      localObject2 = ((StringBuilder)localObject2).toString();
      localObject2 = bytesToHexString(new MD5().digest(((String)localObject2).getBytes("UTF-8")));
      localObject1 = localObject2;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      logger.e("com.amplitude.api.AmplitudeClient", localUnsupportedEncodingException.toString());
    }
    paramString = new FormBody.Builder().add("v", "2").add("client", apiKey).add("e", paramString).add("upload_time", str).add("checksum", (String)localObject1).build();
    try
    {
      paramString = new Request.Builder().url(url).post(paramString).build();
      int j = 1;
      int k = 1;
      int m = 1;
      int n = 1;
      int i1 = 1;
      int i = 1;
      try
      {
        paramString = paramOkHttpClient.newCall(paramString).execute();
        paramOkHttpClient = paramString.body().string();
        boolean bool = paramOkHttpClient.equals("success");
        if (bool)
        {
          try
          {
            logThread.post(new Runnable()
            {
              public void run()
              {
                if (paramLong1 >= 0L) {
                  dbHelper.removeEvents(paramLong1);
                }
                if (val$maxIdentifyId >= 0L) {
                  dbHelper.removeIdentifys(val$maxIdentifyId);
                }
                uploadingCurrently.set(false);
                if (dbHelper.getTotalEventCount() > eventUploadThreshold)
                {
                  logThread.post(new Runnable()
                  {
                    public void run()
                    {
                      updateServer(backoffUpload);
                    }
                  });
                  return;
                }
                AmplitudeClient.access$902(AmplitudeClient.this, false);
                AmplitudeClient.access$1002(AmplitudeClient.this, eventUploadMaxBatchSize);
              }
            });
            i = i1;
          }
          catch (Exception paramOkHttpClient)
          {
            break label577;
          }
          catch (AssertionError paramOkHttpClient)
          {
            i = j;
            break label602;
          }
          catch (IOException paramOkHttpClient)
          {
            i = k;
            break label627;
          }
          catch (UnknownHostException paramOkHttpClient)
          {
            i = m;
            break label652;
          }
          catch (ConnectException paramOkHttpClient)
          {
            i = n;
            break label664;
          }
        }
        else
        {
          if (paramOkHttpClient.equals("invalid_api_key"))
          {
            logger.e("com.amplitude.api.AmplitudeClient", "Invalid API key, make sure your API key is correct in initialize()");
          }
          else if (paramOkHttpClient.equals("bad_checksum"))
          {
            logger.w("com.amplitude.api.AmplitudeClient", "Bad checksum, post request was mangled in transit, will attempt to reupload later");
          }
          else if (paramOkHttpClient.equals("request_db_write_failed"))
          {
            logger.w("com.amplitude.api.AmplitudeClient", "Couldn't write to request database on server, will attempt to reupload later");
          }
          else if (paramString.code() == 413)
          {
            if ((backoffUpload) && (backoffUploadBatchSize == 1))
            {
              if (paramLong1 >= 0L) {
                dbHelper.removeEvent(paramLong1);
              }
              if (paramLong2 >= 0L) {
                dbHelper.removeIdentify(paramLong2);
              }
            }
            backoffUpload = true;
            backoffUploadBatchSize = ((int)Math.ceil(Math.min((int)dbHelper.getEventCount(), backoffUploadBatchSize) / 2.0D));
            logger.w("com.amplitude.api.AmplitudeClient", "Request too large, will decrease size and attempt to reupload");
            logThread.post(new Runnable()
            {
              public void run()
              {
                uploadingCurrently.set(false);
                updateServer(true);
              }
            });
          }
          else
          {
            paramString = logger;
            localObject1 = new StringBuilder();
            ((StringBuilder)localObject1).append("Upload failed, ");
            ((StringBuilder)localObject1).append(paramOkHttpClient);
            ((StringBuilder)localObject1).append(", will attempt to reupload later");
            paramString.w("com.amplitude.api.AmplitudeClient", ((StringBuilder)localObject1).toString());
          }
          i = 0;
        }
      }
      catch (Exception paramOkHttpClient)
      {
        i = 0;
        logger.e("com.amplitude.api.AmplitudeClient", "Exception:", paramOkHttpClient);
        lastError = paramOkHttpClient;
      }
      catch (AssertionError paramOkHttpClient)
      {
        i = 0;
        logger.e("com.amplitude.api.AmplitudeClient", "Exception:", paramOkHttpClient);
        lastError = paramOkHttpClient;
      }
      catch (IOException paramOkHttpClient)
      {
        i = 0;
        logger.e("com.amplitude.api.AmplitudeClient", paramOkHttpClient.toString());
        lastError = paramOkHttpClient;
      }
      catch (UnknownHostException paramOkHttpClient)
      {
        i = 0;
        lastError = paramOkHttpClient;
      }
      catch (ConnectException paramOkHttpClient)
      {
        label577:
        label602:
        label627:
        label652:
        i = 0;
        label664:
        lastError = paramOkHttpClient;
      }
      if (i == 0) {
        uploadingCurrently.set(false);
      }
      return;
    }
    catch (IllegalArgumentException paramOkHttpClient)
    {
      logger.e("com.amplitude.api.AmplitudeClient", paramOkHttpClient.toString());
      uploadingCurrently.set(false);
    }
  }
  
  protected Pair<Pair<Long, Long>, JSONArray> mergeEventsAndIdentifys(List<JSONObject> paramList1, List<JSONObject> paramList2, long paramLong)
    throws JSONException
  {
    JSONArray localJSONArray = new JSONArray();
    long l3 = -1L;
    long l2 = -1L;
    if (localJSONArray.length() < paramLong)
    {
      boolean bool1 = paramList1.isEmpty();
      boolean bool2 = paramList2.isEmpty();
      if ((bool1) && (bool2))
      {
        logger.w("com.amplitude.api.AmplitudeClient", String.format("mergeEventsAndIdentifys: number of events and identifys less than expected by %d", new Object[] { Long.valueOf(paramLong - localJSONArray.length()) }));
      }
      else
      {
        JSONObject localJSONObject;
        long l1;
        if (bool2)
        {
          localJSONObject = (JSONObject)paramList1.remove(0);
          l1 = localJSONObject.getLong("event_id");
          localJSONArray.put(localJSONObject);
        }
        for (;;)
        {
          l3 = l1;
          break;
          if (bool1)
          {
            localJSONObject = (JSONObject)paramList2.remove(0);
            l1 = localJSONObject.getLong("event_id");
            localJSONArray.put(localJSONObject);
          }
          for (;;)
          {
            l2 = l1;
            break;
            if ((!((JSONObject)paramList1.get(0)).has("sequence_number")) || (((JSONObject)paramList1.get(0)).getLong("sequence_number") < ((JSONObject)paramList2.get(0)).getLong("sequence_number"))) {
              break label264;
            }
            localJSONObject = (JSONObject)paramList2.remove(0);
            l1 = localJSONObject.getLong("event_id");
            localJSONArray.put(localJSONObject);
          }
          label264:
          localJSONObject = (JSONObject)paramList1.remove(0);
          l1 = localJSONObject.getLong("event_id");
          localJSONArray.put(localJSONObject);
        }
      }
    }
    return new Pair(new Pair(Long.valueOf(l3), Long.valueOf(l2)), localJSONArray);
  }
  
  void onEnterForeground(final long paramLong)
  {
    runOnLogThread(new Runnable()
    {
      public void run()
      {
        if (Utils.isEmptyString(apiKey)) {
          return;
        }
        startNewSessionIfNeeded(paramLong);
        AmplitudeClient.access$502(AmplitudeClient.this, true);
      }
    });
  }
  
  void onExitForeground(final long paramLong)
  {
    runOnLogThread(new Runnable()
    {
      public void run()
      {
        if (Utils.isEmptyString(apiKey)) {
          return;
        }
        refreshSessionTime(paramLong);
        AmplitudeClient.access$502(AmplitudeClient.this, false);
        if (flushEventsOnClose) {
          updateServer();
        }
      }
    });
  }
  
  void refreshSessionTime(long paramLong)
  {
    if (!inSession()) {
      return;
    }
    setLastEventTime(paramLong);
  }
  
  public AmplitudeClient regenerateDeviceId()
  {
    if (!contextAndApiKeySet("regenerateDeviceId()")) {
      return this;
    }
    runOnLogThread(new Runnable()
    {
      public void run()
      {
        if (Utils.isEmptyString(jdField_thisapiKey)) {
          return;
        }
        Object localObject = new StringBuilder();
        ((StringBuilder)localObject).append(DeviceInfo.generateUUID());
        ((StringBuilder)localObject).append("R");
        localObject = ((StringBuilder)localObject).toString();
        setDeviceId((String)localObject);
      }
    });
    return this;
  }
  
  protected Object replaceWithJSONNull(Object paramObject)
  {
    Object localObject = paramObject;
    if (paramObject == null) {
      localObject = JSONObject.NULL;
    }
    return localObject;
  }
  
  protected void runOnLogThread(Runnable paramRunnable)
  {
    if (Thread.currentThread() != logThread)
    {
      logThread.post(paramRunnable);
      return;
    }
    paramRunnable.run();
  }
  
  protected long saveEvent(String paramString, JSONObject paramJSONObject)
  {
    paramJSONObject = paramJSONObject.toString();
    if (Utils.isEmptyString(paramJSONObject))
    {
      logger.e("com.amplitude.api.AmplitudeClient", String.format("Detected empty event string for event type %s, skipping", new Object[] { paramString }));
      return -1L;
    }
    if (paramString.equals("$identify"))
    {
      lastIdentifyId = dbHelper.addIdentify(paramJSONObject);
      setLastIdentifyId(lastIdentifyId);
    }
    else
    {
      lastEventId = dbHelper.addEvent(paramJSONObject);
      setLastEventId(lastEventId);
    }
    int i = Math.min(Math.max(1, eventMaxCount / 10), 20);
    if (dbHelper.getEventCount() > eventMaxCount) {
      dbHelper.removeEvents(dbHelper.getNthEventId(i));
    }
    if (dbHelper.getIdentifyCount() > eventMaxCount) {
      dbHelper.removeIdentifys(dbHelper.getNthIdentifyId(i));
    }
    long l = dbHelper.getTotalEventCount();
    if ((l % eventUploadThreshold == 0L) && (l >= eventUploadThreshold)) {
      updateServer();
    } else {
      updateServerLater(eventUploadPeriodMillis);
    }
    if (paramString.equals("$identify")) {
      return lastIdentifyId;
    }
    return lastEventId;
  }
  
  public AmplitudeClient setDeviceId(final String paramString)
  {
    Set localSet = getInvalidDeviceIds();
    if ((contextAndApiKeySet("setDeviceId()")) && (!Utils.isEmptyString(paramString)))
    {
      if (localSet.contains(paramString)) {
        return this;
      }
      runOnLogThread(new Runnable()
      {
        public void run()
        {
          if (Utils.isEmptyString(jdField_thisapiKey)) {
            return;
          }
          jdField_thisdeviceId = paramString;
          dbHelper.insertOrReplaceKeyValue("device_id", paramString);
        }
      });
      return this;
    }
    return this;
  }
  
  public AmplitudeClient setEventMaxCount(int paramInt)
  {
    eventMaxCount = paramInt;
    return this;
  }
  
  public AmplitudeClient setEventUploadMaxBatchSize(int paramInt)
  {
    eventUploadMaxBatchSize = paramInt;
    backoffUploadBatchSize = paramInt;
    return this;
  }
  
  public AmplitudeClient setEventUploadPeriodMillis(int paramInt)
  {
    eventUploadPeriodMillis = paramInt;
    return this;
  }
  
  public AmplitudeClient setEventUploadThreshold(int paramInt)
  {
    eventUploadThreshold = paramInt;
    return this;
  }
  
  public AmplitudeClient setFlushEventsOnClose(boolean paramBoolean)
  {
    flushEventsOnClose = paramBoolean;
    return this;
  }
  
  public void setGroup(String paramString, Object paramObject)
  {
    if (contextAndApiKeySet("setGroup()"))
    {
      if (Utils.isEmptyString(paramString)) {
        return;
      }
      JSONObject localJSONObject2;
      try
      {
        JSONObject localJSONObject1 = new JSONObject().put(paramString, paramObject);
      }
      catch (JSONException localJSONException)
      {
        logger.e("com.amplitude.api.AmplitudeClient", localJSONException.toString());
        localJSONObject2 = null;
      }
      logEventAsync("$identify", null, null, IdentifysetUserPropertyuserPropertiesOperations, localJSONObject2, getCurrentTimeMillis(), false);
      return;
    }
  }
  
  void setLastEventId(long paramLong)
  {
    lastEventId = paramLong;
    dbHelper.insertOrReplaceKeyLongValue("last_event_id", Long.valueOf(paramLong));
  }
  
  void setLastEventTime(long paramLong)
  {
    lastEventTime = paramLong;
    dbHelper.insertOrReplaceKeyLongValue("last_event_time", Long.valueOf(paramLong));
  }
  
  void setLastIdentifyId(long paramLong)
  {
    lastIdentifyId = paramLong;
    dbHelper.insertOrReplaceKeyLongValue("last_identify_id", Long.valueOf(paramLong));
  }
  
  public AmplitudeClient setLogLevel(int paramInt)
  {
    logger.setLogLevel(paramInt);
    return this;
  }
  
  public AmplitudeClient setMinTimeBetweenSessionsMillis(long paramLong)
  {
    minTimeBetweenSessionsMillis = paramLong;
    return this;
  }
  
  public AmplitudeClient setOffline(boolean paramBoolean)
  {
    offline = paramBoolean;
    if (!paramBoolean) {
      uploadEvents();
    }
    return this;
  }
  
  public AmplitudeClient setOptOut(final boolean paramBoolean)
  {
    if (!contextAndApiKeySet("setOptOut()")) {
      return this;
    }
    runOnLogThread(new Runnable()
    {
      public void run()
      {
        if (Utils.isEmptyString(apiKey)) {
          return;
        }
        AmplitudeClient.access$102(jdField_this, paramBoolean);
        DatabaseHelper localDatabaseHelper = dbHelper;
        long l;
        if (paramBoolean) {
          l = 1L;
        } else {
          l = 0L;
        }
        localDatabaseHelper.insertOrReplaceKeyLongValue("opt_out", Long.valueOf(l));
      }
    });
    return this;
  }
  
  void setPreviousSessionId(long paramLong)
  {
    previousSessionId = paramLong;
    dbHelper.insertOrReplaceKeyLongValue("previous_session_id", Long.valueOf(paramLong));
  }
  
  public AmplitudeClient setSessionTimeoutMillis(long paramLong)
  {
    sessionTimeoutMillis = paramLong;
    return this;
  }
  
  public AmplitudeClient setUserId(final String paramString)
  {
    if (!contextAndApiKeySet("setUserId()")) {
      return this;
    }
    runOnLogThread(new Runnable()
    {
      public void run()
      {
        if (Utils.isEmptyString(jdField_thisapiKey)) {
          return;
        }
        jdField_thisuserId = paramString;
        dbHelper.insertOrReplaceKeyValue("user_id", paramString);
      }
    });
    return this;
  }
  
  public void setUserProperties(JSONObject paramJSONObject)
  {
    if ((paramJSONObject != null) && (paramJSONObject.length() != 0))
    {
      if (!contextAndApiKeySet("setUserProperties")) {
        return;
      }
      paramJSONObject = truncate(paramJSONObject);
      if (paramJSONObject.length() == 0) {
        return;
      }
      Identify localIdentify = new Identify();
      Iterator localIterator = paramJSONObject.keys();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        try
        {
          localIdentify.setUserProperty(str, paramJSONObject.get(str));
        }
        catch (JSONException localJSONException)
        {
          logger.e("com.amplitude.api.AmplitudeClient", localJSONException.toString());
        }
      }
      identify(localIdentify);
      return;
    }
  }
  
  public void setUserProperties(JSONObject paramJSONObject, boolean paramBoolean)
  {
    setUserProperties(paramJSONObject);
  }
  
  boolean startNewSessionIfNeeded(long paramLong)
  {
    if (inSession())
    {
      if (isWithinMinTimeBetweenSessions(paramLong))
      {
        refreshSessionTime(paramLong);
        return false;
      }
      startNewSession(paramLong);
      return true;
    }
    if (isWithinMinTimeBetweenSessions(paramLong))
    {
      if (previousSessionId == -1L)
      {
        startNewSession(paramLong);
        return true;
      }
      setSessionId(previousSessionId);
      refreshSessionTime(paramLong);
      return false;
    }
    startNewSession(paramLong);
    return true;
  }
  
  public AmplitudeClient trackSessionEvents(boolean paramBoolean)
  {
    trackingSessionEvents = paramBoolean;
    return this;
  }
  
  public String truncate(String paramString)
  {
    if (paramString.length() <= 1024) {
      return paramString;
    }
    return paramString.substring(0, 1024);
  }
  
  public JSONArray truncate(JSONArray paramJSONArray)
    throws JSONException
  {
    if (paramJSONArray == null) {
      return new JSONArray();
    }
    int i = 0;
    while (i < paramJSONArray.length())
    {
      Object localObject = paramJSONArray.get(i);
      if (localObject.getClass().equals(String.class)) {
        paramJSONArray.put(i, truncate((String)localObject));
      } else if (localObject.getClass().equals(JSONObject.class)) {
        paramJSONArray.put(i, truncate((JSONObject)localObject));
      } else if (localObject.getClass().equals(JSONArray.class)) {
        paramJSONArray.put(i, truncate((JSONArray)localObject));
      }
      i += 1;
    }
    return paramJSONArray;
  }
  
  public JSONObject truncate(JSONObject paramJSONObject)
  {
    if (paramJSONObject == null) {
      return new JSONObject();
    }
    if (paramJSONObject.length() > 1000)
    {
      logger.w("com.amplitude.api.AmplitudeClient", "Warning: too many properties (more than 1000), ignoring");
      return new JSONObject();
    }
    Iterator localIterator = paramJSONObject.keys();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      try
      {
        Object localObject = paramJSONObject.get(str);
        if ((!str.equals("$receipt")) && (!str.equals("$receiptSig")))
        {
          if (localObject.getClass().equals(String.class)) {
            paramJSONObject.put(str, truncate((String)localObject));
          } else if (localObject.getClass().equals(JSONObject.class)) {
            paramJSONObject.put(str, truncate((JSONObject)localObject));
          } else if (localObject.getClass().equals(JSONArray.class)) {
            paramJSONObject.put(str, truncate((JSONArray)localObject));
          }
        }
        else {
          paramJSONObject.put(str, localObject);
        }
      }
      catch (JSONException localJSONException)
      {
        logger.e("com.amplitude.api.AmplitudeClient", localJSONException.toString());
      }
    }
    return paramJSONObject;
  }
  
  protected void updateServer()
  {
    updateServer(false);
  }
  
  protected void updateServer(boolean paramBoolean)
  {
    if (!optOut)
    {
      if (offline) {
        return;
      }
      if (!uploadingCurrently.getAndSet(true))
      {
        final long l1 = dbHelper.getTotalEventCount();
        if (paramBoolean) {}
        long l2;
        for (int i = backoffUploadBatchSize;; i = eventUploadMaxBatchSize)
        {
          l2 = i;
          break;
        }
        l1 = Math.min(l2, l1);
        if (l1 <= 0L)
        {
          uploadingCurrently.set(false);
          return;
        }
        try
        {
          Object localObject = mergeEventsAndIdentifys(dbHelper.getEvents(lastEventId, l1), dbHelper.getIdentifys(lastIdentifyId, l1), l1);
          if (((JSONArray)second).length() == 0)
          {
            uploadingCurrently.set(false);
            return;
          }
          l1 = ((Long)first).first).longValue();
          l2 = ((Long)first).second).longValue();
          localObject = ((JSONArray)second).toString();
          httpThread.post(new Runnable()
          {
            public void run()
            {
              makeEventUploadPostRequest(httpClient, val$mergedEventsString, l1, val$maxIdentifyId);
            }
          });
          return;
        }
        catch (CursorWindowAllocationException localCursorWindowAllocationException)
        {
          uploadingCurrently.set(false);
          logger.e("com.amplitude.api.AmplitudeClient", String.format("Caught Cursor window exception during event upload, deferring upload: %s", new Object[] { localCursorWindowAllocationException.getMessage() }));
          return;
        }
        catch (JSONException localJSONException)
        {
          uploadingCurrently.set(false);
          logger.e("com.amplitude.api.AmplitudeClient", localJSONException.toString());
        }
      }
      return;
    }
  }
  
  public void uploadEvents()
  {
    if (!contextAndApiKeySet("uploadEvents()")) {
      return;
    }
    logThread.post(new Runnable()
    {
      public void run()
      {
        if (Utils.isEmptyString(apiKey)) {
          return;
        }
        updateServer();
      }
    });
  }
  
  public AmplitudeClient useAdvertisingIdForDeviceId()
  {
    useAdvertisingIdForDeviceId = true;
    return this;
  }
  
  void useForegroundTracking()
  {
    usingForegroundTracking = true;
  }
  
  protected boolean validateLogEvent(String paramString)
  {
    if (Utils.isEmptyString(paramString))
    {
      logger.e("com.amplitude.api.AmplitudeClient", "Argument eventType cannot be null or blank in logEvent()");
      return false;
    }
    return contextAndApiKeySet("logEvent()");
  }
}
