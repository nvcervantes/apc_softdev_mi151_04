package com.facebook.appevents;

import com.facebook.appevents.internal.AppEventUtility;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

class AppEventStore
{
  private static final String PERSISTED_EVENTS_FILENAME = "AppEventsLogger.persistedevents";
  private static final String TAG = "com.facebook.appevents.AppEventStore";
  
  AppEventStore() {}
  
  public static void persistEvents(AccessTokenAppIdPair paramAccessTokenAppIdPair, SessionEventsState paramSessionEventsState)
  {
    try
    {
      AppEventUtility.assertIsNotMainThread();
      PersistedEvents localPersistedEvents = readAndClearStore();
      if (localPersistedEvents.containsKey(paramAccessTokenAppIdPair)) {
        localPersistedEvents.get(paramAccessTokenAppIdPair).addAll(paramSessionEventsState.getEventsToPersist());
      } else {
        localPersistedEvents.addEvents(paramAccessTokenAppIdPair, paramSessionEventsState.getEventsToPersist());
      }
      saveEventsToDisk(localPersistedEvents);
      return;
    }
    finally {}
  }
  
  public static void persistEvents(AppEventCollection paramAppEventCollection)
  {
    try
    {
      AppEventUtility.assertIsNotMainThread();
      PersistedEvents localPersistedEvents = readAndClearStore();
      Iterator localIterator = paramAppEventCollection.keySet().iterator();
      while (localIterator.hasNext())
      {
        AccessTokenAppIdPair localAccessTokenAppIdPair = (AccessTokenAppIdPair)localIterator.next();
        localPersistedEvents.addEvents(localAccessTokenAppIdPair, paramAppEventCollection.get(localAccessTokenAppIdPair).getEventsToPersist());
      }
      saveEventsToDisk(localPersistedEvents);
      return;
    }
    finally {}
  }
  
  /* Error */
  public static PersistedEvents readAndClearStore()
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: invokestatic 27	com/facebook/appevents/internal/AppEventUtility:assertIsNotMainThread	()V
    //   6: invokestatic 99	com/facebook/FacebookSdk:getApplicationContext	()Landroid/content/Context;
    //   9: astore 4
    //   11: aconst_null
    //   12: astore_3
    //   13: aconst_null
    //   14: astore_1
    //   15: new 6	com/facebook/appevents/AppEventStore$MovedClassObjectInputStream
    //   18: dup
    //   19: new 101	java/io/BufferedInputStream
    //   22: dup
    //   23: aload 4
    //   25: ldc 11
    //   27: invokevirtual 107	android/content/Context:openFileInput	(Ljava/lang/String;)Ljava/io/FileInputStream;
    //   30: invokespecial 110	java/io/BufferedInputStream:<init>	(Ljava/io/InputStream;)V
    //   33: invokespecial 111	com/facebook/appevents/AppEventStore$MovedClassObjectInputStream:<init>	(Ljava/io/InputStream;)V
    //   36: astore_0
    //   37: aload_0
    //   38: astore_1
    //   39: aload_0
    //   40: invokevirtual 114	com/facebook/appevents/AppEventStore$MovedClassObjectInputStream:readObject	()Ljava/lang/Object;
    //   43: checkcast 33	com/facebook/appevents/PersistedEvents
    //   46: astore_2
    //   47: aload_0
    //   48: invokestatic 120	com/facebook/internal/Utility:closeQuietly	(Ljava/io/Closeable;)V
    //   51: aload 4
    //   53: ldc 11
    //   55: invokevirtual 124	android/content/Context:getFileStreamPath	(Ljava/lang/String;)Ljava/io/File;
    //   58: invokevirtual 129	java/io/File:delete	()Z
    //   61: pop
    //   62: goto +14 -> 76
    //   65: astore_0
    //   66: getstatic 131	com/facebook/appevents/AppEventStore:TAG	Ljava/lang/String;
    //   69: ldc -123
    //   71: aload_0
    //   72: invokestatic 139	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   75: pop
    //   76: aload_2
    //   77: astore_0
    //   78: goto +125 -> 203
    //   81: astore_2
    //   82: goto +12 -> 94
    //   85: astore_2
    //   86: aload_1
    //   87: astore_0
    //   88: goto +56 -> 144
    //   91: astore_2
    //   92: aconst_null
    //   93: astore_0
    //   94: aload_0
    //   95: astore_1
    //   96: getstatic 131	com/facebook/appevents/AppEventStore:TAG	Ljava/lang/String;
    //   99: ldc -115
    //   101: aload_2
    //   102: invokestatic 139	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   105: pop
    //   106: aload_0
    //   107: invokestatic 120	com/facebook/internal/Utility:closeQuietly	(Ljava/io/Closeable;)V
    //   110: aload 4
    //   112: ldc 11
    //   114: invokevirtual 124	android/content/Context:getFileStreamPath	(Ljava/lang/String;)Ljava/io/File;
    //   117: invokevirtual 129	java/io/File:delete	()Z
    //   120: pop
    //   121: aload_3
    //   122: astore_0
    //   123: goto +80 -> 203
    //   126: astore_0
    //   127: getstatic 131	com/facebook/appevents/AppEventStore:TAG	Ljava/lang/String;
    //   130: astore_1
    //   131: aload_1
    //   132: ldc -123
    //   134: aload_0
    //   135: invokestatic 139	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   138: pop
    //   139: aload_3
    //   140: astore_0
    //   141: goto +62 -> 203
    //   144: aload_0
    //   145: invokestatic 120	com/facebook/internal/Utility:closeQuietly	(Ljava/io/Closeable;)V
    //   148: aload 4
    //   150: ldc 11
    //   152: invokevirtual 124	android/content/Context:getFileStreamPath	(Ljava/lang/String;)Ljava/io/File;
    //   155: invokevirtual 129	java/io/File:delete	()Z
    //   158: pop
    //   159: goto +14 -> 173
    //   162: astore_0
    //   163: getstatic 131	com/facebook/appevents/AppEventStore:TAG	Ljava/lang/String;
    //   166: ldc -123
    //   168: aload_0
    //   169: invokestatic 139	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   172: pop
    //   173: aload_2
    //   174: athrow
    //   175: aload_0
    //   176: invokestatic 120	com/facebook/internal/Utility:closeQuietly	(Ljava/io/Closeable;)V
    //   179: aload 4
    //   181: ldc 11
    //   183: invokevirtual 124	android/content/Context:getFileStreamPath	(Ljava/lang/String;)Ljava/io/File;
    //   186: invokevirtual 129	java/io/File:delete	()Z
    //   189: pop
    //   190: aload_3
    //   191: astore_0
    //   192: goto +11 -> 203
    //   195: astore_0
    //   196: getstatic 131	com/facebook/appevents/AppEventStore:TAG	Ljava/lang/String;
    //   199: astore_1
    //   200: goto -69 -> 131
    //   203: aload_0
    //   204: astore_1
    //   205: aload_0
    //   206: ifnonnull +11 -> 217
    //   209: new 33	com/facebook/appevents/PersistedEvents
    //   212: dup
    //   213: invokespecial 142	com/facebook/appevents/PersistedEvents:<init>	()V
    //   216: astore_1
    //   217: ldc 2
    //   219: monitorexit
    //   220: aload_1
    //   221: areturn
    //   222: astore_0
    //   223: ldc 2
    //   225: monitorexit
    //   226: aload_0
    //   227: athrow
    //   228: astore_0
    //   229: goto +13 -> 242
    //   232: astore_1
    //   233: goto -58 -> 175
    //   236: astore_2
    //   237: aload_1
    //   238: astore_0
    //   239: goto -95 -> 144
    //   242: aconst_null
    //   243: astore_0
    //   244: goto -69 -> 175
    // Local variable table:
    //   start	length	slot	name	signature
    //   36	12	0	localMovedClassObjectInputStream	MovedClassObjectInputStream
    //   65	7	0	localException1	Exception
    //   77	46	0	localObject1	Object
    //   126	9	0	localException2	Exception
    //   140	5	0	localObject2	Object
    //   162	14	0	localException3	Exception
    //   191	1	0	localObject3	Object
    //   195	11	0	localException4	Exception
    //   222	5	0	localObject4	Object
    //   228	1	0	localFileNotFoundException1	java.io.FileNotFoundException
    //   238	6	0	localFileNotFoundException2	java.io.FileNotFoundException
    //   14	207	1	localObject5	Object
    //   232	6	1	localFileNotFoundException3	java.io.FileNotFoundException
    //   46	31	2	localPersistedEvents	PersistedEvents
    //   81	1	2	localException5	Exception
    //   85	1	2	localObject6	Object
    //   91	83	2	localException6	Exception
    //   236	1	2	localObject7	Object
    //   12	179	3	localObject8	Object
    //   9	171	4	localContext	android.content.Context
    // Exception table:
    //   from	to	target	type
    //   51	62	65	java/lang/Exception
    //   39	47	81	java/lang/Exception
    //   15	37	85	finally
    //   15	37	91	java/lang/Exception
    //   110	121	126	java/lang/Exception
    //   148	159	162	java/lang/Exception
    //   179	190	195	java/lang/Exception
    //   3	11	222	finally
    //   47	51	222	finally
    //   51	62	222	finally
    //   66	76	222	finally
    //   106	110	222	finally
    //   110	121	222	finally
    //   127	131	222	finally
    //   131	139	222	finally
    //   144	148	222	finally
    //   148	159	222	finally
    //   163	173	222	finally
    //   173	175	222	finally
    //   175	179	222	finally
    //   179	190	222	finally
    //   196	200	222	finally
    //   209	217	222	finally
    //   15	37	228	java/io/FileNotFoundException
    //   39	47	232	java/io/FileNotFoundException
    //   39	47	236	finally
    //   96	106	236	finally
  }
  
  /* Error */
  private static void saveEventsToDisk(PersistedEvents paramPersistedEvents)
  {
    // Byte code:
    //   0: invokestatic 99	com/facebook/FacebookSdk:getApplicationContext	()Landroid/content/Context;
    //   3: astore 4
    //   5: aconst_null
    //   6: astore_3
    //   7: aconst_null
    //   8: astore_1
    //   9: new 144	java/io/ObjectOutputStream
    //   12: dup
    //   13: new 146	java/io/BufferedOutputStream
    //   16: dup
    //   17: aload 4
    //   19: ldc 11
    //   21: iconst_0
    //   22: invokevirtual 150	android/content/Context:openFileOutput	(Ljava/lang/String;I)Ljava/io/FileOutputStream;
    //   25: invokespecial 153	java/io/BufferedOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   28: invokespecial 154	java/io/ObjectOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   31: astore_2
    //   32: aload_2
    //   33: aload_0
    //   34: invokevirtual 158	java/io/ObjectOutputStream:writeObject	(Ljava/lang/Object;)V
    //   37: aload_2
    //   38: invokestatic 120	com/facebook/internal/Utility:closeQuietly	(Ljava/io/Closeable;)V
    //   41: return
    //   42: astore_0
    //   43: aload_2
    //   44: astore_1
    //   45: goto +48 -> 93
    //   48: astore_1
    //   49: aload_2
    //   50: astore_0
    //   51: aload_1
    //   52: astore_2
    //   53: goto +10 -> 63
    //   56: astore_0
    //   57: goto +36 -> 93
    //   60: astore_2
    //   61: aload_3
    //   62: astore_0
    //   63: aload_0
    //   64: astore_1
    //   65: getstatic 131	com/facebook/appevents/AppEventStore:TAG	Ljava/lang/String;
    //   68: ldc -96
    //   70: aload_2
    //   71: invokestatic 139	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   74: pop
    //   75: aload_0
    //   76: astore_1
    //   77: aload 4
    //   79: ldc 11
    //   81: invokevirtual 124	android/content/Context:getFileStreamPath	(Ljava/lang/String;)Ljava/io/File;
    //   84: invokevirtual 129	java/io/File:delete	()Z
    //   87: pop
    //   88: aload_0
    //   89: invokestatic 120	com/facebook/internal/Utility:closeQuietly	(Ljava/io/Closeable;)V
    //   92: return
    //   93: aload_1
    //   94: invokestatic 120	com/facebook/internal/Utility:closeQuietly	(Ljava/io/Closeable;)V
    //   97: aload_0
    //   98: athrow
    //   99: astore_1
    //   100: goto -12 -> 88
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	103	0	paramPersistedEvents	PersistedEvents
    //   8	37	1	localObject1	Object
    //   48	4	1	localException1	Exception
    //   64	30	1	localPersistedEvents	PersistedEvents
    //   99	1	1	localException2	Exception
    //   31	22	2	localObject2	Object
    //   60	11	2	localException3	Exception
    //   6	56	3	localObject3	Object
    //   3	75	4	localContext	android.content.Context
    // Exception table:
    //   from	to	target	type
    //   32	37	42	finally
    //   32	37	48	java/lang/Exception
    //   9	32	56	finally
    //   65	75	56	finally
    //   77	88	56	finally
    //   9	32	60	java/lang/Exception
    //   77	88	99	java/lang/Exception
  }
  
  private static class MovedClassObjectInputStream
    extends ObjectInputStream
  {
    private static final String ACCESS_TOKEN_APP_ID_PAIR_SERIALIZATION_PROXY_V1_CLASS_NAME = "com.facebook.appevents.AppEventsLogger$AccessTokenAppIdPair$SerializationProxyV1";
    private static final String APP_EVENT_SERIALIZATION_PROXY_V1_CLASS_NAME = "com.facebook.appevents.AppEventsLogger$AppEvent$SerializationProxyV1";
    
    public MovedClassObjectInputStream(InputStream paramInputStream)
      throws IOException
    {
      super();
    }
    
    protected ObjectStreamClass readClassDescriptor()
      throws IOException, ClassNotFoundException
    {
      ObjectStreamClass localObjectStreamClass2 = super.readClassDescriptor();
      if (localObjectStreamClass2.getName().equals("com.facebook.appevents.AppEventsLogger$AccessTokenAppIdPair$SerializationProxyV1")) {
        return ObjectStreamClass.lookup(AccessTokenAppIdPair.SerializationProxyV1.class);
      }
      ObjectStreamClass localObjectStreamClass1 = localObjectStreamClass2;
      if (localObjectStreamClass2.getName().equals("com.facebook.appevents.AppEventsLogger$AppEvent$SerializationProxyV1")) {
        localObjectStreamClass1 = ObjectStreamClass.lookup(AppEvent.SerializationProxyV1.class);
      }
      return localObjectStreamClass1;
    }
  }
}
