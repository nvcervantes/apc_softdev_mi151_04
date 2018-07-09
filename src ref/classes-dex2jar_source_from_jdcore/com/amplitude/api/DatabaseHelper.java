package com.amplitude.api;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

class DatabaseHelper
  extends SQLiteOpenHelper
{
  private static final String CREATE_EVENTS_TABLE = "CREATE TABLE IF NOT EXISTS events (id INTEGER PRIMARY KEY AUTOINCREMENT, event TEXT);";
  private static final String CREATE_IDENTIFYS_TABLE = "CREATE TABLE IF NOT EXISTS identifys (id INTEGER PRIMARY KEY AUTOINCREMENT, event TEXT);";
  private static final String CREATE_LONG_STORE_TABLE = "CREATE TABLE IF NOT EXISTS long_store (key TEXT PRIMARY KEY NOT NULL, value INTEGER);";
  private static final String CREATE_STORE_TABLE = "CREATE TABLE IF NOT EXISTS store (key TEXT PRIMARY KEY NOT NULL, value TEXT);";
  private static final String EVENT_FIELD = "event";
  protected static final String EVENT_TABLE_NAME = "events";
  protected static final String IDENTIFY_TABLE_NAME = "identifys";
  private static final String ID_FIELD = "id";
  private static final String KEY_FIELD = "key";
  protected static final String LONG_STORE_TABLE_NAME = "long_store";
  protected static final String STORE_TABLE_NAME = "store";
  private static final String TAG = "com.amplitude.api.DatabaseHelper";
  private static final String VALUE_FIELD = "value";
  static final Map<String, DatabaseHelper> instances = new HashMap();
  private static final AmplitudeLog logger = AmplitudeLog.getLogger();
  private File file;
  private String instanceName;
  
  protected DatabaseHelper(Context paramContext)
  {
    this(paramContext, null);
  }
  
  protected DatabaseHelper(Context paramContext, String paramString)
  {
    super(paramContext, getDatabaseName(paramString), null, 3);
    file = paramContext.getDatabasePath(getDatabaseName(paramString));
    instanceName = Utils.normalizeInstanceName(paramString);
  }
  
  /* Error */
  private long addEventToTable(String paramString1, String paramString2)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokevirtual 106	com/amplitude/api/DatabaseHelper:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   6: astore 7
    //   8: new 108	android/content/ContentValues
    //   11: dup
    //   12: invokespecial 109	android/content/ContentValues:<init>	()V
    //   15: astore 8
    //   17: aload 8
    //   19: ldc 20
    //   21: aload_2
    //   22: invokevirtual 113	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   25: aload 7
    //   27: aload_1
    //   28: aconst_null
    //   29: aload 8
    //   31: invokevirtual 119	android/database/sqlite/SQLiteDatabase:insert	(Ljava/lang/String;Ljava/lang/String;Landroid/content/ContentValues;)J
    //   34: lstore_3
    //   35: lload_3
    //   36: lstore 5
    //   38: lload_3
    //   39: ldc2_w 120
    //   42: lcmp
    //   43: ifne +39 -> 82
    //   46: getstatic 69	com/amplitude/api/DatabaseHelper:logger	Lcom/amplitude/api/AmplitudeLog;
    //   49: ldc 41
    //   51: ldc 123
    //   53: iconst_1
    //   54: anewarray 125	java/lang/Object
    //   57: dup
    //   58: iconst_0
    //   59: aload_1
    //   60: aastore
    //   61: invokestatic 131	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   64: invokevirtual 135	com/amplitude/api/AmplitudeLog:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   67: pop
    //   68: lload_3
    //   69: lstore 5
    //   71: goto +11 -> 82
    //   74: astore_2
    //   75: goto +23 -> 98
    //   78: astore_2
    //   79: goto +52 -> 131
    //   82: aload_0
    //   83: invokevirtual 138	com/amplitude/api/DatabaseHelper:close	()V
    //   86: goto +78 -> 164
    //   89: astore_1
    //   90: goto +79 -> 169
    //   93: astore_2
    //   94: ldc2_w 120
    //   97: lstore_3
    //   98: getstatic 69	com/amplitude/api/DatabaseHelper:logger	Lcom/amplitude/api/AmplitudeLog;
    //   101: ldc 41
    //   103: ldc -116
    //   105: iconst_1
    //   106: anewarray 125	java/lang/Object
    //   109: dup
    //   110: iconst_0
    //   111: aload_1
    //   112: aastore
    //   113: invokestatic 131	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   116: aload_2
    //   117: invokevirtual 144	com/amplitude/api/AmplitudeLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   120: pop
    //   121: aload_0
    //   122: invokespecial 147	com/amplitude/api/DatabaseHelper:delete	()V
    //   125: lload_3
    //   126: lstore 5
    //   128: goto -46 -> 82
    //   131: getstatic 69	com/amplitude/api/DatabaseHelper:logger	Lcom/amplitude/api/AmplitudeLog;
    //   134: ldc 41
    //   136: ldc -116
    //   138: iconst_1
    //   139: anewarray 125	java/lang/Object
    //   142: dup
    //   143: iconst_0
    //   144: aload_1
    //   145: aastore
    //   146: invokestatic 131	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   149: aload_2
    //   150: invokevirtual 144	com/amplitude/api/AmplitudeLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   153: pop
    //   154: aload_0
    //   155: invokespecial 147	com/amplitude/api/DatabaseHelper:delete	()V
    //   158: lload_3
    //   159: lstore 5
    //   161: goto -79 -> 82
    //   164: aload_0
    //   165: monitorexit
    //   166: lload 5
    //   168: lreturn
    //   169: aload_0
    //   170: invokevirtual 138	com/amplitude/api/DatabaseHelper:close	()V
    //   173: aload_1
    //   174: athrow
    //   175: astore_1
    //   176: aload_0
    //   177: monitorexit
    //   178: aload_1
    //   179: athrow
    //   180: astore_2
    //   181: ldc2_w 120
    //   184: lstore_3
    //   185: goto -54 -> 131
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	188	0	this	DatabaseHelper
    //   0	188	1	paramString1	String
    //   0	188	2	paramString2	String
    //   34	151	3	l1	long
    //   36	131	5	l2	long
    //   6	20	7	localSQLiteDatabase	SQLiteDatabase
    //   15	15	8	localContentValues	android.content.ContentValues
    // Exception table:
    //   from	to	target	type
    //   46	68	74	java/lang/StackOverflowError
    //   46	68	78	android/database/sqlite/SQLiteException
    //   2	35	89	finally
    //   46	68	89	finally
    //   98	125	89	finally
    //   131	158	89	finally
    //   2	35	93	java/lang/StackOverflowError
    //   82	86	175	finally
    //   169	175	175	finally
    //   2	35	180	android/database/sqlite/SQLiteException
  }
  
  private static void convertIfCursorWindowException(RuntimeException paramRuntimeException)
  {
    String str = paramRuntimeException.getMessage();
    if ((!Utils.isEmptyString(str)) && (str.startsWith("Cursor window allocation of"))) {
      throw new CursorWindowAllocationException(str);
    }
    throw paramRuntimeException;
  }
  
  private void delete()
  {
    try
    {
      close();
      file.delete();
      return;
    }
    catch (SecurityException localSecurityException)
    {
      logger.e("com.amplitude.api.DatabaseHelper", "delete failed", localSecurityException);
    }
  }
  
  @Deprecated
  static DatabaseHelper getDatabaseHelper(Context paramContext)
  {
    return getDatabaseHelper(paramContext, null);
  }
  
  static DatabaseHelper getDatabaseHelper(Context paramContext, String paramString)
  {
    try
    {
      String str = Utils.normalizeInstanceName(paramString);
      DatabaseHelper localDatabaseHelper = (DatabaseHelper)instances.get(str);
      paramString = localDatabaseHelper;
      if (localDatabaseHelper == null)
      {
        paramString = new DatabaseHelper(paramContext.getApplicationContext(), str);
        instances.put(str, paramString);
      }
      return paramString;
    }
    finally {}
  }
  
  private static String getDatabaseName(String paramString)
  {
    if ((!Utils.isEmptyString(paramString)) && (!paramString.equals("$default_instance")))
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("com.amplitude.api_");
      localStringBuilder.append(paramString);
      return localStringBuilder.toString();
    }
    return "com.amplitude.api";
  }
  
  /* Error */
  private long getEventCountFromTable(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: lconst_0
    //   3: lstore_2
    //   4: aconst_null
    //   5: astore 9
    //   7: aconst_null
    //   8: astore 10
    //   10: aconst_null
    //   11: astore 7
    //   13: aload 7
    //   15: astore 6
    //   17: aload_0
    //   18: invokevirtual 223	com/amplitude/api/DatabaseHelper:getReadableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   21: astore 8
    //   23: aload 7
    //   25: astore 6
    //   27: new 206	java/lang/StringBuilder
    //   30: dup
    //   31: invokespecial 207	java/lang/StringBuilder:<init>	()V
    //   34: astore 11
    //   36: aload 7
    //   38: astore 6
    //   40: aload 11
    //   42: ldc -31
    //   44: invokevirtual 213	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   47: pop
    //   48: aload 7
    //   50: astore 6
    //   52: aload 11
    //   54: aload_1
    //   55: invokevirtual 213	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   58: pop
    //   59: aload 7
    //   61: astore 6
    //   63: aload 8
    //   65: aload 11
    //   67: invokevirtual 216	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   70: invokevirtual 229	android/database/sqlite/SQLiteDatabase:compileStatement	(Ljava/lang/String;)Landroid/database/sqlite/SQLiteStatement;
    //   73: astore 7
    //   75: aload 7
    //   77: invokevirtual 235	android/database/sqlite/SQLiteStatement:simpleQueryForLong	()J
    //   80: lstore 4
    //   82: aload 7
    //   84: ifnull +8 -> 92
    //   87: aload 7
    //   89: invokevirtual 236	android/database/sqlite/SQLiteStatement:close	()V
    //   92: aload_0
    //   93: invokevirtual 138	com/amplitude/api/DatabaseHelper:close	()V
    //   96: lload 4
    //   98: lstore_2
    //   99: goto +147 -> 246
    //   102: astore_1
    //   103: aload 7
    //   105: astore 6
    //   107: goto +143 -> 250
    //   110: astore 6
    //   112: aload 6
    //   114: astore 8
    //   116: goto +22 -> 138
    //   119: astore 6
    //   121: aload 6
    //   123: astore 8
    //   125: goto +72 -> 197
    //   128: astore_1
    //   129: goto +121 -> 250
    //   132: astore 8
    //   134: aload 9
    //   136: astore 7
    //   138: aload 7
    //   140: astore 6
    //   142: getstatic 69	com/amplitude/api/DatabaseHelper:logger	Lcom/amplitude/api/AmplitudeLog;
    //   145: ldc 41
    //   147: ldc -18
    //   149: iconst_1
    //   150: anewarray 125	java/lang/Object
    //   153: dup
    //   154: iconst_0
    //   155: aload_1
    //   156: aastore
    //   157: invokestatic 131	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   160: aload 8
    //   162: invokevirtual 144	com/amplitude/api/AmplitudeLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   165: pop
    //   166: aload 7
    //   168: astore 6
    //   170: aload_0
    //   171: invokespecial 147	com/amplitude/api/DatabaseHelper:delete	()V
    //   174: aload 7
    //   176: ifnull +8 -> 184
    //   179: aload 7
    //   181: invokevirtual 236	android/database/sqlite/SQLiteStatement:close	()V
    //   184: aload_0
    //   185: invokevirtual 138	com/amplitude/api/DatabaseHelper:close	()V
    //   188: goto +58 -> 246
    //   191: astore 8
    //   193: aload 10
    //   195: astore 7
    //   197: aload 7
    //   199: astore 6
    //   201: getstatic 69	com/amplitude/api/DatabaseHelper:logger	Lcom/amplitude/api/AmplitudeLog;
    //   204: ldc 41
    //   206: ldc -18
    //   208: iconst_1
    //   209: anewarray 125	java/lang/Object
    //   212: dup
    //   213: iconst_0
    //   214: aload_1
    //   215: aastore
    //   216: invokestatic 131	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   219: aload 8
    //   221: invokevirtual 144	com/amplitude/api/AmplitudeLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   224: pop
    //   225: aload 7
    //   227: astore 6
    //   229: aload_0
    //   230: invokespecial 147	com/amplitude/api/DatabaseHelper:delete	()V
    //   233: aload 7
    //   235: ifnull -51 -> 184
    //   238: aload 7
    //   240: invokevirtual 236	android/database/sqlite/SQLiteStatement:close	()V
    //   243: goto -59 -> 184
    //   246: aload_0
    //   247: monitorexit
    //   248: lload_2
    //   249: lreturn
    //   250: aload 6
    //   252: ifnull +11 -> 263
    //   255: aload 6
    //   257: invokevirtual 236	android/database/sqlite/SQLiteStatement:close	()V
    //   260: goto +3 -> 263
    //   263: aload_0
    //   264: invokevirtual 138	com/amplitude/api/DatabaseHelper:close	()V
    //   267: aload_1
    //   268: athrow
    //   269: aload_0
    //   270: monitorexit
    //   271: aload_1
    //   272: athrow
    //   273: astore_1
    //   274: goto -5 -> 269
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	277	0	this	DatabaseHelper
    //   0	277	1	paramString	String
    //   3	246	2	l1	long
    //   80	17	4	l2	long
    //   15	91	6	localObject1	Object
    //   110	3	6	localStackOverflowError1	StackOverflowError
    //   119	3	6	localSQLiteException1	android.database.sqlite.SQLiteException
    //   140	116	6	localObject2	Object
    //   11	228	7	localObject3	Object
    //   21	103	8	localObject4	Object
    //   132	29	8	localStackOverflowError2	StackOverflowError
    //   191	29	8	localSQLiteException2	android.database.sqlite.SQLiteException
    //   5	130	9	localObject5	Object
    //   8	186	10	localObject6	Object
    //   34	32	11	localStringBuilder	StringBuilder
    // Exception table:
    //   from	to	target	type
    //   75	82	102	finally
    //   75	82	110	java/lang/StackOverflowError
    //   75	82	119	android/database/sqlite/SQLiteException
    //   17	23	128	finally
    //   27	36	128	finally
    //   40	48	128	finally
    //   52	59	128	finally
    //   63	75	128	finally
    //   142	166	128	finally
    //   170	174	128	finally
    //   201	225	128	finally
    //   229	233	128	finally
    //   17	23	132	java/lang/StackOverflowError
    //   27	36	132	java/lang/StackOverflowError
    //   40	48	132	java/lang/StackOverflowError
    //   52	59	132	java/lang/StackOverflowError
    //   63	75	132	java/lang/StackOverflowError
    //   17	23	191	android/database/sqlite/SQLiteException
    //   27	36	191	android/database/sqlite/SQLiteException
    //   40	48	191	android/database/sqlite/SQLiteException
    //   52	59	191	android/database/sqlite/SQLiteException
    //   63	75	191	android/database/sqlite/SQLiteException
    //   87	92	273	finally
    //   92	96	273	finally
    //   179	184	273	finally
    //   184	188	273	finally
    //   238	243	273	finally
    //   255	260	273	finally
    //   263	269	273	finally
  }
  
  /* Error */
  private long getNthEventIdFromTable(String paramString, long paramLong)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aconst_null
    //   3: astore 11
    //   5: aconst_null
    //   6: astore 12
    //   8: aconst_null
    //   9: astore 9
    //   11: ldc2_w 120
    //   14: lstore 4
    //   16: aload 9
    //   18: astore 8
    //   20: aload_0
    //   21: invokevirtual 223	com/amplitude/api/DatabaseHelper:getReadableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   24: astore 10
    //   26: aload 9
    //   28: astore 8
    //   30: new 206	java/lang/StringBuilder
    //   33: dup
    //   34: invokespecial 207	java/lang/StringBuilder:<init>	()V
    //   37: astore 13
    //   39: aload 9
    //   41: astore 8
    //   43: aload 13
    //   45: ldc -12
    //   47: invokevirtual 213	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   50: pop
    //   51: aload 9
    //   53: astore 8
    //   55: aload 13
    //   57: aload_1
    //   58: invokevirtual 213	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   61: pop
    //   62: aload 9
    //   64: astore 8
    //   66: aload 13
    //   68: ldc -10
    //   70: invokevirtual 213	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   73: pop
    //   74: aload 9
    //   76: astore 8
    //   78: aload 13
    //   80: lload_2
    //   81: lconst_1
    //   82: lsub
    //   83: invokevirtual 249	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   86: pop
    //   87: aload 9
    //   89: astore 8
    //   91: aload 10
    //   93: aload 13
    //   95: invokevirtual 216	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   98: invokevirtual 229	android/database/sqlite/SQLiteDatabase:compileStatement	(Ljava/lang/String;)Landroid/database/sqlite/SQLiteStatement;
    //   101: astore 9
    //   103: aload 9
    //   105: invokevirtual 235	android/database/sqlite/SQLiteStatement:simpleQueryForLong	()J
    //   108: lstore_2
    //   109: goto +37 -> 146
    //   112: astore_1
    //   113: aload 9
    //   115: astore 8
    //   117: goto +187 -> 304
    //   120: astore 10
    //   122: goto +57 -> 179
    //   125: astore 10
    //   127: goto +115 -> 242
    //   130: astore 8
    //   132: getstatic 69	com/amplitude/api/DatabaseHelper:logger	Lcom/amplitude/api/AmplitudeLog;
    //   135: ldc 41
    //   137: aload 8
    //   139: invokevirtual 252	com/amplitude/api/AmplitudeLog:w	(Ljava/lang/String;Ljava/lang/Throwable;)I
    //   142: pop
    //   143: lload 4
    //   145: lstore_2
    //   146: lload_2
    //   147: lstore 6
    //   149: aload 9
    //   151: ifnull +11 -> 162
    //   154: aload 9
    //   156: invokevirtual 236	android/database/sqlite/SQLiteStatement:close	()V
    //   159: lload_2
    //   160: lstore 6
    //   162: aload_0
    //   163: invokevirtual 138	com/amplitude/api/DatabaseHelper:close	()V
    //   166: goto +133 -> 299
    //   169: astore_1
    //   170: goto +134 -> 304
    //   173: astore 10
    //   175: aload 11
    //   177: astore 9
    //   179: aload 9
    //   181: astore 8
    //   183: getstatic 69	com/amplitude/api/DatabaseHelper:logger	Lcom/amplitude/api/AmplitudeLog;
    //   186: ldc 41
    //   188: ldc -2
    //   190: iconst_1
    //   191: anewarray 125	java/lang/Object
    //   194: dup
    //   195: iconst_0
    //   196: aload_1
    //   197: aastore
    //   198: invokestatic 131	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   201: aload 10
    //   203: invokevirtual 144	com/amplitude/api/AmplitudeLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   206: pop
    //   207: aload 9
    //   209: astore 8
    //   211: aload_0
    //   212: invokespecial 147	com/amplitude/api/DatabaseHelper:delete	()V
    //   215: lload 4
    //   217: lstore 6
    //   219: aload 9
    //   221: ifnull -59 -> 162
    //   224: aload 9
    //   226: invokevirtual 236	android/database/sqlite/SQLiteStatement:close	()V
    //   229: lload 4
    //   231: lstore 6
    //   233: goto -71 -> 162
    //   236: astore 10
    //   238: aload 12
    //   240: astore 9
    //   242: aload 9
    //   244: astore 8
    //   246: getstatic 69	com/amplitude/api/DatabaseHelper:logger	Lcom/amplitude/api/AmplitudeLog;
    //   249: ldc 41
    //   251: ldc -2
    //   253: iconst_1
    //   254: anewarray 125	java/lang/Object
    //   257: dup
    //   258: iconst_0
    //   259: aload_1
    //   260: aastore
    //   261: invokestatic 131	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   264: aload 10
    //   266: invokevirtual 144	com/amplitude/api/AmplitudeLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   269: pop
    //   270: aload 9
    //   272: astore 8
    //   274: aload_0
    //   275: invokespecial 147	com/amplitude/api/DatabaseHelper:delete	()V
    //   278: lload 4
    //   280: lstore 6
    //   282: aload 9
    //   284: ifnull -122 -> 162
    //   287: aload 9
    //   289: invokevirtual 236	android/database/sqlite/SQLiteStatement:close	()V
    //   292: lload 4
    //   294: lstore 6
    //   296: goto -134 -> 162
    //   299: aload_0
    //   300: monitorexit
    //   301: lload 6
    //   303: lreturn
    //   304: aload 8
    //   306: ifnull +11 -> 317
    //   309: aload 8
    //   311: invokevirtual 236	android/database/sqlite/SQLiteStatement:close	()V
    //   314: goto +3 -> 317
    //   317: aload_0
    //   318: invokevirtual 138	com/amplitude/api/DatabaseHelper:close	()V
    //   321: aload_1
    //   322: athrow
    //   323: aload_0
    //   324: monitorexit
    //   325: aload_1
    //   326: athrow
    //   327: astore_1
    //   328: goto -5 -> 323
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	331	0	this	DatabaseHelper
    //   0	331	1	paramString	String
    //   0	331	2	paramLong	long
    //   14	279	4	l1	long
    //   147	155	6	l2	long
    //   18	98	8	localObject1	Object
    //   130	8	8	localSQLiteDoneException	android.database.sqlite.SQLiteDoneException
    //   181	129	8	localObject2	Object
    //   9	279	9	localObject3	Object
    //   24	68	10	localSQLiteDatabase	SQLiteDatabase
    //   120	1	10	localStackOverflowError1	StackOverflowError
    //   125	1	10	localSQLiteException1	android.database.sqlite.SQLiteException
    //   173	29	10	localStackOverflowError2	StackOverflowError
    //   236	29	10	localSQLiteException2	android.database.sqlite.SQLiteException
    //   3	173	11	localObject4	Object
    //   6	233	12	localObject5	Object
    //   37	57	13	localStringBuilder	StringBuilder
    // Exception table:
    //   from	to	target	type
    //   103	109	112	finally
    //   132	143	112	finally
    //   103	109	120	java/lang/StackOverflowError
    //   132	143	120	java/lang/StackOverflowError
    //   103	109	125	android/database/sqlite/SQLiteException
    //   132	143	125	android/database/sqlite/SQLiteException
    //   103	109	130	android/database/sqlite/SQLiteDoneException
    //   20	26	169	finally
    //   30	39	169	finally
    //   43	51	169	finally
    //   55	62	169	finally
    //   66	74	169	finally
    //   78	87	169	finally
    //   91	103	169	finally
    //   183	207	169	finally
    //   211	215	169	finally
    //   246	270	169	finally
    //   274	278	169	finally
    //   20	26	173	java/lang/StackOverflowError
    //   30	39	173	java/lang/StackOverflowError
    //   43	51	173	java/lang/StackOverflowError
    //   55	62	173	java/lang/StackOverflowError
    //   66	74	173	java/lang/StackOverflowError
    //   78	87	173	java/lang/StackOverflowError
    //   91	103	173	java/lang/StackOverflowError
    //   20	26	236	android/database/sqlite/SQLiteException
    //   30	39	236	android/database/sqlite/SQLiteException
    //   43	51	236	android/database/sqlite/SQLiteException
    //   55	62	236	android/database/sqlite/SQLiteException
    //   66	74	236	android/database/sqlite/SQLiteException
    //   78	87	236	android/database/sqlite/SQLiteException
    //   91	103	236	android/database/sqlite/SQLiteException
    //   154	159	327	finally
    //   162	166	327	finally
    //   224	229	327	finally
    //   287	292	327	finally
    //   309	314	327	finally
    //   317	323	327	finally
  }
  
  /* Error */
  private void removeEventFromTable(String paramString, long paramLong)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokevirtual 106	com/amplitude/api/DatabaseHelper:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   6: astore 4
    //   8: new 206	java/lang/StringBuilder
    //   11: dup
    //   12: invokespecial 207	java/lang/StringBuilder:<init>	()V
    //   15: astore 5
    //   17: aload 5
    //   19: ldc_w 258
    //   22: invokevirtual 213	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   25: pop
    //   26: aload 5
    //   28: lload_2
    //   29: invokevirtual 249	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   32: pop
    //   33: aload 4
    //   35: aload_1
    //   36: aload 5
    //   38: invokevirtual 216	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   41: aconst_null
    //   42: invokevirtual 261	android/database/sqlite/SQLiteDatabase:delete	(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)I
    //   45: pop
    //   46: aload_0
    //   47: invokevirtual 138	com/amplitude/api/DatabaseHelper:close	()V
    //   50: goto +75 -> 125
    //   53: astore_1
    //   54: goto +74 -> 128
    //   57: astore 4
    //   59: getstatic 69	com/amplitude/api/DatabaseHelper:logger	Lcom/amplitude/api/AmplitudeLog;
    //   62: ldc 41
    //   64: ldc_w 263
    //   67: iconst_1
    //   68: anewarray 125	java/lang/Object
    //   71: dup
    //   72: iconst_0
    //   73: aload_1
    //   74: aastore
    //   75: invokestatic 131	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   78: aload 4
    //   80: invokevirtual 144	com/amplitude/api/AmplitudeLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   83: pop
    //   84: aload_0
    //   85: invokespecial 147	com/amplitude/api/DatabaseHelper:delete	()V
    //   88: goto -42 -> 46
    //   91: astore 4
    //   93: getstatic 69	com/amplitude/api/DatabaseHelper:logger	Lcom/amplitude/api/AmplitudeLog;
    //   96: ldc 41
    //   98: ldc_w 263
    //   101: iconst_1
    //   102: anewarray 125	java/lang/Object
    //   105: dup
    //   106: iconst_0
    //   107: aload_1
    //   108: aastore
    //   109: invokestatic 131	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   112: aload 4
    //   114: invokevirtual 144	com/amplitude/api/AmplitudeLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   117: pop
    //   118: aload_0
    //   119: invokespecial 147	com/amplitude/api/DatabaseHelper:delete	()V
    //   122: goto -76 -> 46
    //   125: aload_0
    //   126: monitorexit
    //   127: return
    //   128: aload_0
    //   129: invokevirtual 138	com/amplitude/api/DatabaseHelper:close	()V
    //   132: aload_1
    //   133: athrow
    //   134: astore_1
    //   135: aload_0
    //   136: monitorexit
    //   137: aload_1
    //   138: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	139	0	this	DatabaseHelper
    //   0	139	1	paramString	String
    //   0	139	2	paramLong	long
    //   6	28	4	localSQLiteDatabase	SQLiteDatabase
    //   57	22	4	localStackOverflowError	StackOverflowError
    //   91	22	4	localSQLiteException	android.database.sqlite.SQLiteException
    //   15	22	5	localStringBuilder	StringBuilder
    // Exception table:
    //   from	to	target	type
    //   2	46	53	finally
    //   59	88	53	finally
    //   93	122	53	finally
    //   2	46	57	java/lang/StackOverflowError
    //   2	46	91	android/database/sqlite/SQLiteException
    //   46	50	134	finally
    //   128	134	134	finally
  }
  
  /* Error */
  private void removeEventsFromTable(String paramString, long paramLong)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokevirtual 106	com/amplitude/api/DatabaseHelper:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   6: astore 4
    //   8: new 206	java/lang/StringBuilder
    //   11: dup
    //   12: invokespecial 207	java/lang/StringBuilder:<init>	()V
    //   15: astore 5
    //   17: aload 5
    //   19: ldc_w 266
    //   22: invokevirtual 213	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   25: pop
    //   26: aload 5
    //   28: lload_2
    //   29: invokevirtual 249	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   32: pop
    //   33: aload 4
    //   35: aload_1
    //   36: aload 5
    //   38: invokevirtual 216	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   41: aconst_null
    //   42: invokevirtual 261	android/database/sqlite/SQLiteDatabase:delete	(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)I
    //   45: pop
    //   46: aload_0
    //   47: invokevirtual 138	com/amplitude/api/DatabaseHelper:close	()V
    //   50: goto +75 -> 125
    //   53: astore_1
    //   54: goto +74 -> 128
    //   57: astore 4
    //   59: getstatic 69	com/amplitude/api/DatabaseHelper:logger	Lcom/amplitude/api/AmplitudeLog;
    //   62: ldc 41
    //   64: ldc_w 268
    //   67: iconst_1
    //   68: anewarray 125	java/lang/Object
    //   71: dup
    //   72: iconst_0
    //   73: aload_1
    //   74: aastore
    //   75: invokestatic 131	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   78: aload 4
    //   80: invokevirtual 144	com/amplitude/api/AmplitudeLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   83: pop
    //   84: aload_0
    //   85: invokespecial 147	com/amplitude/api/DatabaseHelper:delete	()V
    //   88: goto -42 -> 46
    //   91: astore 4
    //   93: getstatic 69	com/amplitude/api/DatabaseHelper:logger	Lcom/amplitude/api/AmplitudeLog;
    //   96: ldc 41
    //   98: ldc_w 268
    //   101: iconst_1
    //   102: anewarray 125	java/lang/Object
    //   105: dup
    //   106: iconst_0
    //   107: aload_1
    //   108: aastore
    //   109: invokestatic 131	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   112: aload 4
    //   114: invokevirtual 144	com/amplitude/api/AmplitudeLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   117: pop
    //   118: aload_0
    //   119: invokespecial 147	com/amplitude/api/DatabaseHelper:delete	()V
    //   122: goto -76 -> 46
    //   125: aload_0
    //   126: monitorexit
    //   127: return
    //   128: aload_0
    //   129: invokevirtual 138	com/amplitude/api/DatabaseHelper:close	()V
    //   132: aload_1
    //   133: athrow
    //   134: astore_1
    //   135: aload_0
    //   136: monitorexit
    //   137: aload_1
    //   138: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	139	0	this	DatabaseHelper
    //   0	139	1	paramString	String
    //   0	139	2	paramLong	long
    //   6	28	4	localSQLiteDatabase	SQLiteDatabase
    //   57	22	4	localStackOverflowError	StackOverflowError
    //   91	22	4	localSQLiteException	android.database.sqlite.SQLiteException
    //   15	22	5	localStringBuilder	StringBuilder
    // Exception table:
    //   from	to	target	type
    //   2	46	53	finally
    //   59	88	53	finally
    //   93	122	53	finally
    //   2	46	57	java/lang/StackOverflowError
    //   2	46	91	android/database/sqlite/SQLiteException
    //   46	50	134	finally
    //   128	134	134	finally
  }
  
  private void resetDatabase(SQLiteDatabase paramSQLiteDatabase)
  {
    paramSQLiteDatabase.execSQL("DROP TABLE IF EXISTS store");
    paramSQLiteDatabase.execSQL("DROP TABLE IF EXISTS long_store");
    paramSQLiteDatabase.execSQL("DROP TABLE IF EXISTS events");
    paramSQLiteDatabase.execSQL("DROP TABLE IF EXISTS identifys");
    onCreate(paramSQLiteDatabase);
  }
  
  long addEvent(String paramString)
  {
    try
    {
      long l = addEventToTable("events", paramString);
      return l;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  long addIdentify(String paramString)
  {
    try
    {
      long l = addEventToTable("identifys", paramString);
      return l;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  boolean dbFileExists()
  {
    return file.exists();
  }
  
  /* Error */
  long deleteKeyFromTable(String paramString1, String paramString2)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: ldc2_w 120
    //   5: lstore 4
    //   7: aload_0
    //   8: invokevirtual 106	com/amplitude/api/DatabaseHelper:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   11: aload_1
    //   12: ldc_w 295
    //   15: iconst_1
    //   16: anewarray 127	java/lang/String
    //   19: dup
    //   20: iconst_0
    //   21: aload_2
    //   22: aastore
    //   23: invokevirtual 261	android/database/sqlite/SQLiteDatabase:delete	(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)I
    //   26: istore_3
    //   27: iload_3
    //   28: i2l
    //   29: lstore 4
    //   31: aload_0
    //   32: invokevirtual 138	com/amplitude/api/DatabaseHelper:close	()V
    //   35: goto +75 -> 110
    //   38: astore_1
    //   39: goto +76 -> 115
    //   42: astore_2
    //   43: getstatic 69	com/amplitude/api/DatabaseHelper:logger	Lcom/amplitude/api/AmplitudeLog;
    //   46: ldc 41
    //   48: ldc_w 297
    //   51: iconst_1
    //   52: anewarray 125	java/lang/Object
    //   55: dup
    //   56: iconst_0
    //   57: aload_1
    //   58: aastore
    //   59: invokestatic 131	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   62: aload_2
    //   63: invokevirtual 144	com/amplitude/api/AmplitudeLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   66: pop
    //   67: aload_0
    //   68: invokespecial 147	com/amplitude/api/DatabaseHelper:delete	()V
    //   71: aload_0
    //   72: invokevirtual 138	com/amplitude/api/DatabaseHelper:close	()V
    //   75: goto +35 -> 110
    //   78: astore_2
    //   79: getstatic 69	com/amplitude/api/DatabaseHelper:logger	Lcom/amplitude/api/AmplitudeLog;
    //   82: ldc 41
    //   84: ldc_w 297
    //   87: iconst_1
    //   88: anewarray 125	java/lang/Object
    //   91: dup
    //   92: iconst_0
    //   93: aload_1
    //   94: aastore
    //   95: invokestatic 131	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   98: aload_2
    //   99: invokevirtual 144	com/amplitude/api/AmplitudeLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   102: pop
    //   103: aload_0
    //   104: invokespecial 147	com/amplitude/api/DatabaseHelper:delete	()V
    //   107: goto -36 -> 71
    //   110: aload_0
    //   111: monitorexit
    //   112: lload 4
    //   114: lreturn
    //   115: aload_0
    //   116: invokevirtual 138	com/amplitude/api/DatabaseHelper:close	()V
    //   119: aload_1
    //   120: athrow
    //   121: astore_1
    //   122: aload_0
    //   123: monitorexit
    //   124: aload_1
    //   125: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	126	0	this	DatabaseHelper
    //   0	126	1	paramString1	String
    //   0	126	2	paramString2	String
    //   26	2	3	i	int
    //   5	108	4	l	long
    // Exception table:
    //   from	to	target	type
    //   7	27	38	finally
    //   43	71	38	finally
    //   79	107	38	finally
    //   7	27	42	java/lang/StackOverflowError
    //   7	27	78	android/database/sqlite/SQLiteException
    //   31	35	121	finally
    //   71	75	121	finally
    //   115	121	121	finally
  }
  
  long getEventCount()
  {
    try
    {
      long l = getEventCountFromTable("events");
      return l;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  List<JSONObject> getEvents(long paramLong1, long paramLong2)
    throws JSONException
  {
    try
    {
      List localList = getEventsFromTable("events", paramLong1, paramLong2);
      return localList;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  /* Error */
  protected List<JSONObject> getEventsFromTable(String paramString, long paramLong1, long paramLong2)
    throws JSONException
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: new 313	java/util/LinkedList
    //   5: dup
    //   6: invokespecial 314	java/util/LinkedList:<init>	()V
    //   9: astore 9
    //   11: aload_0
    //   12: invokevirtual 223	com/amplitude/api/DatabaseHelper:getReadableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   15: astore 8
    //   17: lload_2
    //   18: lconst_0
    //   19: lcmp
    //   20: iflt +412 -> 432
    //   23: new 206	java/lang/StringBuilder
    //   26: dup
    //   27: invokespecial 207	java/lang/StringBuilder:<init>	()V
    //   30: astore 6
    //   32: aload 6
    //   34: ldc_w 266
    //   37: invokevirtual 213	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   40: pop
    //   41: aload 6
    //   43: lload_2
    //   44: invokevirtual 249	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   47: pop
    //   48: aload 6
    //   50: invokevirtual 216	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   53: astore 6
    //   55: goto +3 -> 58
    //   58: lload 4
    //   60: lconst_0
    //   61: lcmp
    //   62: iflt +376 -> 438
    //   65: new 206	java/lang/StringBuilder
    //   68: dup
    //   69: invokespecial 207	java/lang/StringBuilder:<init>	()V
    //   72: astore 7
    //   74: aload 7
    //   76: ldc_w 316
    //   79: invokevirtual 213	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   82: pop
    //   83: aload 7
    //   85: lload 4
    //   87: invokevirtual 249	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   90: pop
    //   91: aload 7
    //   93: invokevirtual 216	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   96: astore 7
    //   98: goto +3 -> 101
    //   101: aload_0
    //   102: aload 8
    //   104: aload_1
    //   105: iconst_2
    //   106: anewarray 127	java/lang/String
    //   109: dup
    //   110: iconst_0
    //   111: ldc 29
    //   113: aastore
    //   114: dup
    //   115: iconst_1
    //   116: ldc 20
    //   118: aastore
    //   119: aload 6
    //   121: aconst_null
    //   122: aconst_null
    //   123: aconst_null
    //   124: ldc_w 318
    //   127: aload 7
    //   129: invokevirtual 322	com/amplitude/api/DatabaseHelper:queryDb	(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   132: astore 6
    //   134: aload 6
    //   136: invokeinterface 327 1 0
    //   141: ifeq +67 -> 208
    //   144: aload 6
    //   146: iconst_0
    //   147: invokeinterface 331 2 0
    //   152: lstore_2
    //   153: aload 6
    //   155: iconst_1
    //   156: invokeinterface 335 2 0
    //   161: astore 7
    //   163: aload 7
    //   165: invokestatic 159	com/amplitude/api/Utils:isEmptyString	(Ljava/lang/String;)Z
    //   168: ifeq +6 -> 174
    //   171: goto -37 -> 134
    //   174: new 337	org/json/JSONObject
    //   177: dup
    //   178: aload 7
    //   180: invokespecial 338	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   183: astore 7
    //   185: aload 7
    //   187: ldc_w 340
    //   190: lload_2
    //   191: invokevirtual 343	org/json/JSONObject:put	(Ljava/lang/String;J)Lorg/json/JSONObject;
    //   194: pop
    //   195: aload 9
    //   197: aload 7
    //   199: invokeinterface 348 2 0
    //   204: pop
    //   205: goto -71 -> 134
    //   208: aload 6
    //   210: ifnull +10 -> 220
    //   213: aload 6
    //   215: invokeinterface 349 1 0
    //   220: aload_0
    //   221: invokevirtual 138	com/amplitude/api/DatabaseHelper:close	()V
    //   224: goto +179 -> 403
    //   227: astore_1
    //   228: goto +181 -> 409
    //   231: astore 7
    //   233: aload 6
    //   235: astore_1
    //   236: goto +32 -> 268
    //   239: astore 8
    //   241: aload 6
    //   243: astore 7
    //   245: goto +49 -> 294
    //   248: astore 8
    //   250: aload 6
    //   252: astore 7
    //   254: goto +97 -> 351
    //   257: astore_1
    //   258: aconst_null
    //   259: astore 6
    //   261: goto +148 -> 409
    //   264: astore 7
    //   266: aconst_null
    //   267: astore_1
    //   268: aload_1
    //   269: astore 6
    //   271: aload 7
    //   273: invokestatic 351	com/amplitude/api/DatabaseHelper:convertIfCursorWindowException	(Ljava/lang/RuntimeException;)V
    //   276: aload_1
    //   277: ifnull -57 -> 220
    //   280: aload_1
    //   281: invokeinterface 349 1 0
    //   286: goto -66 -> 220
    //   289: astore 8
    //   291: aconst_null
    //   292: astore 7
    //   294: aload 7
    //   296: astore 6
    //   298: getstatic 69	com/amplitude/api/DatabaseHelper:logger	Lcom/amplitude/api/AmplitudeLog;
    //   301: ldc 41
    //   303: ldc_w 263
    //   306: iconst_1
    //   307: anewarray 125	java/lang/Object
    //   310: dup
    //   311: iconst_0
    //   312: aload_1
    //   313: aastore
    //   314: invokestatic 131	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   317: aload 8
    //   319: invokevirtual 144	com/amplitude/api/AmplitudeLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   322: pop
    //   323: aload 7
    //   325: astore 6
    //   327: aload_0
    //   328: invokespecial 147	com/amplitude/api/DatabaseHelper:delete	()V
    //   331: aload 7
    //   333: ifnull -113 -> 220
    //   336: aload 7
    //   338: invokeinterface 349 1 0
    //   343: goto -123 -> 220
    //   346: astore 8
    //   348: aconst_null
    //   349: astore 7
    //   351: aload 7
    //   353: astore 6
    //   355: getstatic 69	com/amplitude/api/DatabaseHelper:logger	Lcom/amplitude/api/AmplitudeLog;
    //   358: ldc 41
    //   360: ldc_w 353
    //   363: iconst_1
    //   364: anewarray 125	java/lang/Object
    //   367: dup
    //   368: iconst_0
    //   369: aload_1
    //   370: aastore
    //   371: invokestatic 131	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   374: aload 8
    //   376: invokevirtual 144	com/amplitude/api/AmplitudeLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   379: pop
    //   380: aload 7
    //   382: astore 6
    //   384: aload_0
    //   385: invokespecial 147	com/amplitude/api/DatabaseHelper:delete	()V
    //   388: aload 7
    //   390: ifnull -170 -> 220
    //   393: aload 7
    //   395: invokeinterface 349 1 0
    //   400: goto -180 -> 220
    //   403: aload_0
    //   404: monitorexit
    //   405: aload 9
    //   407: areturn
    //   408: astore_1
    //   409: aload 6
    //   411: ifnull +10 -> 421
    //   414: aload 6
    //   416: invokeinterface 349 1 0
    //   421: aload_0
    //   422: invokevirtual 138	com/amplitude/api/DatabaseHelper:close	()V
    //   425: aload_1
    //   426: athrow
    //   427: astore_1
    //   428: aload_0
    //   429: monitorexit
    //   430: aload_1
    //   431: athrow
    //   432: aconst_null
    //   433: astore 6
    //   435: goto -377 -> 58
    //   438: aconst_null
    //   439: astore 7
    //   441: goto -340 -> 101
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	444	0	this	DatabaseHelper
    //   0	444	1	paramString	String
    //   0	444	2	paramLong1	long
    //   0	444	4	paramLong2	long
    //   30	404	6	localObject1	Object
    //   72	126	7	localObject2	Object
    //   231	1	7	localRuntimeException1	RuntimeException
    //   243	10	7	localObject3	Object
    //   264	8	7	localRuntimeException2	RuntimeException
    //   292	148	7	localObject4	Object
    //   15	88	8	localSQLiteDatabase	SQLiteDatabase
    //   239	1	8	localStackOverflowError1	StackOverflowError
    //   248	1	8	localSQLiteException1	android.database.sqlite.SQLiteException
    //   289	29	8	localStackOverflowError2	StackOverflowError
    //   346	29	8	localSQLiteException2	android.database.sqlite.SQLiteException
    //   9	397	9	localLinkedList	java.util.LinkedList
    // Exception table:
    //   from	to	target	type
    //   134	171	227	finally
    //   174	205	227	finally
    //   134	171	231	java/lang/RuntimeException
    //   174	205	231	java/lang/RuntimeException
    //   134	171	239	java/lang/StackOverflowError
    //   174	205	239	java/lang/StackOverflowError
    //   134	171	248	android/database/sqlite/SQLiteException
    //   174	205	248	android/database/sqlite/SQLiteException
    //   11	17	257	finally
    //   23	55	257	finally
    //   65	98	257	finally
    //   101	134	257	finally
    //   11	17	264	java/lang/RuntimeException
    //   23	55	264	java/lang/RuntimeException
    //   65	98	264	java/lang/RuntimeException
    //   101	134	264	java/lang/RuntimeException
    //   11	17	289	java/lang/StackOverflowError
    //   23	55	289	java/lang/StackOverflowError
    //   65	98	289	java/lang/StackOverflowError
    //   101	134	289	java/lang/StackOverflowError
    //   11	17	346	android/database/sqlite/SQLiteException
    //   23	55	346	android/database/sqlite/SQLiteException
    //   65	98	346	android/database/sqlite/SQLiteException
    //   101	134	346	android/database/sqlite/SQLiteException
    //   271	276	408	finally
    //   298	323	408	finally
    //   327	331	408	finally
    //   355	380	408	finally
    //   384	388	408	finally
    //   2	11	427	finally
    //   213	220	427	finally
    //   220	224	427	finally
    //   280	286	427	finally
    //   336	343	427	finally
    //   393	400	427	finally
    //   414	421	427	finally
    //   421	427	427	finally
  }
  
  long getIdentifyCount()
  {
    try
    {
      long l = getEventCountFromTable("identifys");
      return l;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  List<JSONObject> getIdentifys(long paramLong1, long paramLong2)
    throws JSONException
  {
    try
    {
      List localList = getEventsFromTable("identifys", paramLong1, paramLong2);
      return localList;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  Long getLongValue(String paramString)
  {
    try
    {
      paramString = (Long)getValueFromTable("long_store", paramString);
      return paramString;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  long getNthEventId(long paramLong)
  {
    try
    {
      paramLong = getNthEventIdFromTable("events", paramLong);
      return paramLong;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  long getNthIdentifyId(long paramLong)
  {
    try
    {
      paramLong = getNthEventIdFromTable("identifys", paramLong);
      return paramLong;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  long getTotalEventCount()
  {
    try
    {
      long l1 = getEventCount();
      long l2 = getIdentifyCount();
      return l1 + l2;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  String getValue(String paramString)
  {
    try
    {
      paramString = (String)getValueFromTable("store", paramString);
      return paramString;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  /* Error */
  protected Object getValueFromTable(String paramString1, String paramString2)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aconst_null
    //   3: astore 5
    //   5: aconst_null
    //   6: astore 4
    //   8: aload_0
    //   9: aload_0
    //   10: invokevirtual 223	com/amplitude/api/DatabaseHelper:getReadableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   13: aload_1
    //   14: iconst_2
    //   15: anewarray 127	java/lang/String
    //   18: dup
    //   19: iconst_0
    //   20: ldc 32
    //   22: aastore
    //   23: dup
    //   24: iconst_1
    //   25: ldc 44
    //   27: aastore
    //   28: ldc_w 377
    //   31: iconst_1
    //   32: anewarray 127	java/lang/String
    //   35: dup
    //   36: iconst_0
    //   37: aload_2
    //   38: aastore
    //   39: aconst_null
    //   40: aconst_null
    //   41: aconst_null
    //   42: aconst_null
    //   43: invokevirtual 322	com/amplitude/api/DatabaseHelper:queryDb	(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   46: astore_3
    //   47: aload_3
    //   48: astore_2
    //   49: aload_3
    //   50: invokeinterface 380 1 0
    //   55: ifeq +51 -> 106
    //   58: aload_3
    //   59: astore_2
    //   60: aload_1
    //   61: ldc 38
    //   63: invokevirtual 204	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   66: ifeq +20 -> 86
    //   69: aload_3
    //   70: astore_2
    //   71: aload_3
    //   72: iconst_1
    //   73: invokeinterface 335 2 0
    //   78: astore 4
    //   80: aload 4
    //   82: astore_1
    //   83: goto +234 -> 317
    //   86: aload_3
    //   87: astore_2
    //   88: aload_3
    //   89: iconst_1
    //   90: invokeinterface 331 2 0
    //   95: invokestatic 384	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   98: astore 4
    //   100: aload 4
    //   102: astore_1
    //   103: goto +214 -> 317
    //   106: aload 4
    //   108: astore_1
    //   109: aload_3
    //   110: ifnull +12 -> 122
    //   113: aload_3
    //   114: invokeinterface 349 1 0
    //   119: aload 4
    //   121: astore_1
    //   122: aload_0
    //   123: invokevirtual 138	com/amplitude/api/DatabaseHelper:close	()V
    //   126: goto +163 -> 289
    //   129: astore_1
    //   130: goto +22 -> 152
    //   133: astore 4
    //   135: goto +46 -> 181
    //   138: astore 4
    //   140: goto +97 -> 237
    //   143: astore_1
    //   144: aconst_null
    //   145: astore_2
    //   146: goto +148 -> 294
    //   149: astore_1
    //   150: aconst_null
    //   151: astore_3
    //   152: aload_3
    //   153: astore_2
    //   154: aload_1
    //   155: invokestatic 351	com/amplitude/api/DatabaseHelper:convertIfCursorWindowException	(Ljava/lang/RuntimeException;)V
    //   158: aload 5
    //   160: astore_1
    //   161: aload_3
    //   162: ifnull -40 -> 122
    //   165: aload_3
    //   166: invokeinterface 349 1 0
    //   171: aload 5
    //   173: astore_1
    //   174: goto -52 -> 122
    //   177: astore 4
    //   179: aconst_null
    //   180: astore_3
    //   181: aload_3
    //   182: astore_2
    //   183: getstatic 69	com/amplitude/api/DatabaseHelper:logger	Lcom/amplitude/api/AmplitudeLog;
    //   186: ldc 41
    //   188: ldc_w 386
    //   191: iconst_1
    //   192: anewarray 125	java/lang/Object
    //   195: dup
    //   196: iconst_0
    //   197: aload_1
    //   198: aastore
    //   199: invokestatic 131	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   202: aload 4
    //   204: invokevirtual 144	com/amplitude/api/AmplitudeLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   207: pop
    //   208: aload_3
    //   209: astore_2
    //   210: aload_0
    //   211: invokespecial 147	com/amplitude/api/DatabaseHelper:delete	()V
    //   214: aload 5
    //   216: astore_1
    //   217: aload_3
    //   218: ifnull -96 -> 122
    //   221: aload_3
    //   222: invokeinterface 349 1 0
    //   227: aload 5
    //   229: astore_1
    //   230: goto -108 -> 122
    //   233: astore 4
    //   235: aconst_null
    //   236: astore_3
    //   237: aload_3
    //   238: astore_2
    //   239: getstatic 69	com/amplitude/api/DatabaseHelper:logger	Lcom/amplitude/api/AmplitudeLog;
    //   242: ldc 41
    //   244: ldc_w 386
    //   247: iconst_1
    //   248: anewarray 125	java/lang/Object
    //   251: dup
    //   252: iconst_0
    //   253: aload_1
    //   254: aastore
    //   255: invokestatic 131	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   258: aload 4
    //   260: invokevirtual 144	com/amplitude/api/AmplitudeLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   263: pop
    //   264: aload_3
    //   265: astore_2
    //   266: aload_0
    //   267: invokespecial 147	com/amplitude/api/DatabaseHelper:delete	()V
    //   270: aload 5
    //   272: astore_1
    //   273: aload_3
    //   274: ifnull -152 -> 122
    //   277: aload_3
    //   278: invokeinterface 349 1 0
    //   283: aload 5
    //   285: astore_1
    //   286: goto -164 -> 122
    //   289: aload_0
    //   290: monitorexit
    //   291: aload_1
    //   292: areturn
    //   293: astore_1
    //   294: aload_2
    //   295: ifnull +12 -> 307
    //   298: aload_2
    //   299: invokeinterface 349 1 0
    //   304: goto +3 -> 307
    //   307: aload_0
    //   308: invokevirtual 138	com/amplitude/api/DatabaseHelper:close	()V
    //   311: aload_1
    //   312: athrow
    //   313: aload_0
    //   314: monitorexit
    //   315: aload_1
    //   316: athrow
    //   317: aload_1
    //   318: astore 4
    //   320: goto -214 -> 106
    //   323: astore_1
    //   324: goto -11 -> 313
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	327	0	this	DatabaseHelper
    //   0	327	1	paramString1	String
    //   0	327	2	paramString2	String
    //   46	232	3	localCursor	Cursor
    //   6	114	4	localObject1	Object
    //   133	1	4	localStackOverflowError1	StackOverflowError
    //   138	1	4	localSQLiteException1	android.database.sqlite.SQLiteException
    //   177	26	4	localStackOverflowError2	StackOverflowError
    //   233	26	4	localSQLiteException2	android.database.sqlite.SQLiteException
    //   318	1	4	str	String
    //   3	281	5	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   49	58	129	java/lang/RuntimeException
    //   60	69	129	java/lang/RuntimeException
    //   71	80	129	java/lang/RuntimeException
    //   88	100	129	java/lang/RuntimeException
    //   49	58	133	java/lang/StackOverflowError
    //   60	69	133	java/lang/StackOverflowError
    //   71	80	133	java/lang/StackOverflowError
    //   88	100	133	java/lang/StackOverflowError
    //   49	58	138	android/database/sqlite/SQLiteException
    //   60	69	138	android/database/sqlite/SQLiteException
    //   71	80	138	android/database/sqlite/SQLiteException
    //   88	100	138	android/database/sqlite/SQLiteException
    //   8	47	143	finally
    //   8	47	149	java/lang/RuntimeException
    //   8	47	177	java/lang/StackOverflowError
    //   8	47	233	android/database/sqlite/SQLiteException
    //   49	58	293	finally
    //   60	69	293	finally
    //   71	80	293	finally
    //   88	100	293	finally
    //   154	158	293	finally
    //   183	208	293	finally
    //   210	214	293	finally
    //   239	264	293	finally
    //   266	270	293	finally
    //   113	119	323	finally
    //   122	126	323	finally
    //   165	171	323	finally
    //   221	227	323	finally
    //   277	283	323	finally
    //   298	304	323	finally
    //   307	313	323	finally
  }
  
  long insertOrReplaceKeyLongValue(String paramString, Long paramLong)
  {
    if (paramLong == null) {}
    try
    {
      long l = deleteKeyFromTable("long_store", paramString);
      break label26;
      l = insertOrReplaceKeyValueToTable("long_store", paramString, paramLong);
      label26:
      return l;
    }
    finally
    {
      for (;;) {}
    }
    throw paramString;
  }
  
  long insertOrReplaceKeyValue(String paramString1, String paramString2)
  {
    if (paramString2 == null) {}
    try
    {
      long l = deleteKeyFromTable("store", paramString1);
      break label26;
      l = insertOrReplaceKeyValueToTable("store", paramString1, paramString2);
      label26:
      return l;
    }
    finally
    {
      for (;;) {}
    }
    throw paramString1;
  }
  
  /* Error */
  long insertOrReplaceKeyValueToTable(String paramString1, String paramString2, Object paramObject)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: ldc2_w 120
    //   5: lstore 6
    //   7: aload_0
    //   8: invokevirtual 106	com/amplitude/api/DatabaseHelper:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   11: astore 8
    //   13: new 108	android/content/ContentValues
    //   16: dup
    //   17: invokespecial 109	android/content/ContentValues:<init>	()V
    //   20: astore 9
    //   22: aload 9
    //   24: ldc 32
    //   26: aload_2
    //   27: invokevirtual 113	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   30: aload_3
    //   31: instanceof 364
    //   34: ifeq +17 -> 51
    //   37: aload 9
    //   39: ldc 44
    //   41: aload_3
    //   42: checkcast 364	java/lang/Long
    //   45: invokevirtual 398	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Long;)V
    //   48: goto +14 -> 62
    //   51: aload 9
    //   53: ldc 44
    //   55: aload_3
    //   56: checkcast 127	java/lang/String
    //   59: invokevirtual 113	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   62: aload 8
    //   64: aload_1
    //   65: aconst_null
    //   66: aload 9
    //   68: iconst_5
    //   69: invokevirtual 402	android/database/sqlite/SQLiteDatabase:insertWithOnConflict	(Ljava/lang/String;Ljava/lang/String;Landroid/content/ContentValues;I)J
    //   72: lstore 4
    //   74: lload 4
    //   76: ldc2_w 120
    //   79: lcmp
    //   80: ifne +26 -> 106
    //   83: getstatic 69	com/amplitude/api/DatabaseHelper:logger	Lcom/amplitude/api/AmplitudeLog;
    //   86: ldc 41
    //   88: ldc_w 404
    //   91: invokevirtual 135	com/amplitude/api/AmplitudeLog:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   94: pop
    //   95: goto +11 -> 106
    //   98: astore_2
    //   99: goto +23 -> 122
    //   102: astore_2
    //   103: goto +59 -> 162
    //   106: aload_0
    //   107: invokevirtual 138	com/amplitude/api/DatabaseHelper:close	()V
    //   110: goto +83 -> 193
    //   113: astore_1
    //   114: goto +84 -> 198
    //   117: astore_2
    //   118: lload 6
    //   120: lstore 4
    //   122: getstatic 69	com/amplitude/api/DatabaseHelper:logger	Lcom/amplitude/api/AmplitudeLog;
    //   125: ldc 41
    //   127: ldc_w 406
    //   130: iconst_1
    //   131: anewarray 125	java/lang/Object
    //   134: dup
    //   135: iconst_0
    //   136: aload_1
    //   137: aastore
    //   138: invokestatic 131	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   141: aload_2
    //   142: invokevirtual 144	com/amplitude/api/AmplitudeLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   145: pop
    //   146: aload_0
    //   147: invokespecial 147	com/amplitude/api/DatabaseHelper:delete	()V
    //   150: aload_0
    //   151: invokevirtual 138	com/amplitude/api/DatabaseHelper:close	()V
    //   154: goto +39 -> 193
    //   157: astore_2
    //   158: lload 6
    //   160: lstore 4
    //   162: getstatic 69	com/amplitude/api/DatabaseHelper:logger	Lcom/amplitude/api/AmplitudeLog;
    //   165: ldc 41
    //   167: ldc_w 406
    //   170: iconst_1
    //   171: anewarray 125	java/lang/Object
    //   174: dup
    //   175: iconst_0
    //   176: aload_1
    //   177: aastore
    //   178: invokestatic 131	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   181: aload_2
    //   182: invokevirtual 144	com/amplitude/api/AmplitudeLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   185: pop
    //   186: aload_0
    //   187: invokespecial 147	com/amplitude/api/DatabaseHelper:delete	()V
    //   190: goto -40 -> 150
    //   193: aload_0
    //   194: monitorexit
    //   195: lload 4
    //   197: lreturn
    //   198: aload_0
    //   199: invokevirtual 138	com/amplitude/api/DatabaseHelper:close	()V
    //   202: aload_1
    //   203: athrow
    //   204: astore_1
    //   205: aload_0
    //   206: monitorexit
    //   207: aload_1
    //   208: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	209	0	this	DatabaseHelper
    //   0	209	1	paramString1	String
    //   0	209	2	paramString2	String
    //   0	209	3	paramObject	Object
    //   72	124	4	l1	long
    //   5	154	6	l2	long
    //   11	52	8	localSQLiteDatabase	SQLiteDatabase
    //   20	47	9	localContentValues	android.content.ContentValues
    // Exception table:
    //   from	to	target	type
    //   83	95	98	java/lang/StackOverflowError
    //   83	95	102	android/database/sqlite/SQLiteException
    //   7	48	113	finally
    //   51	62	113	finally
    //   62	74	113	finally
    //   83	95	113	finally
    //   122	150	113	finally
    //   162	190	113	finally
    //   7	48	117	java/lang/StackOverflowError
    //   51	62	117	java/lang/StackOverflowError
    //   62	74	117	java/lang/StackOverflowError
    //   7	48	157	android/database/sqlite/SQLiteException
    //   51	62	157	android/database/sqlite/SQLiteException
    //   62	74	157	android/database/sqlite/SQLiteException
    //   106	110	204	finally
    //   150	154	204	finally
    //   198	204	204	finally
  }
  
  public void onCreate(SQLiteDatabase paramSQLiteDatabase)
  {
    paramSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS store (key TEXT PRIMARY KEY NOT NULL, value TEXT);");
    paramSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS long_store (key TEXT PRIMARY KEY NOT NULL, value INTEGER);");
    paramSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS events (id INTEGER PRIMARY KEY AUTOINCREMENT, event TEXT);");
    paramSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS identifys (id INTEGER PRIMARY KEY AUTOINCREMENT, event TEXT);");
  }
  
  public void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2)
  {
    if (paramInt1 > paramInt2)
    {
      logger.e("com.amplitude.api.DatabaseHelper", "onUpgrade() with invalid oldVersion and newVersion");
      resetDatabase(paramSQLiteDatabase);
      return;
    }
    if (paramInt2 <= 1) {
      return;
    }
    switch (paramInt1)
    {
    default: 
      AmplitudeLog localAmplitudeLog = logger;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("onUpgrade() with unknown oldVersion ");
      localStringBuilder.append(paramInt1);
      localAmplitudeLog.e("com.amplitude.api.DatabaseHelper", localStringBuilder.toString());
      resetDatabase(paramSQLiteDatabase);
      return;
    case 1: 
      paramSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS store (key TEXT PRIMARY KEY NOT NULL, value TEXT);");
      if (paramInt2 <= 2) {
        return;
      }
    case 2: 
      paramSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS identifys (id INTEGER PRIMARY KEY AUTOINCREMENT, event TEXT);");
      paramSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS long_store (key TEXT PRIMARY KEY NOT NULL, value INTEGER);");
    }
  }
  
  Cursor queryDb(SQLiteDatabase paramSQLiteDatabase, String paramString1, String[] paramArrayOfString1, String paramString2, String[] paramArrayOfString2, String paramString3, String paramString4, String paramString5, String paramString6)
  {
    return paramSQLiteDatabase.query(paramString1, paramArrayOfString1, paramString2, paramArrayOfString2, paramString3, paramString4, paramString5, paramString6);
  }
  
  void removeEvent(long paramLong)
  {
    try
    {
      removeEventFromTable("events", paramLong);
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  void removeEvents(long paramLong)
  {
    try
    {
      removeEventsFromTable("events", paramLong);
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  void removeIdentify(long paramLong)
  {
    try
    {
      removeEventFromTable("identifys", paramLong);
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  void removeIdentifys(long paramLong)
  {
    try
    {
      removeEventsFromTable("identifys", paramLong);
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
}
