package com.facebook.internal;

import com.facebook.FacebookSdk;
import com.facebook.LoggingBehavior;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidParameterException;
import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicLong;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public final class FileLruCache
{
  private static final String HEADER_CACHEKEY_KEY = "key";
  private static final String HEADER_CACHE_CONTENT_TAG_KEY = "tag";
  static final String TAG = "FileLruCache";
  private static final AtomicLong bufferIndex = new AtomicLong();
  private final File directory;
  private boolean isTrimInProgress;
  private boolean isTrimPending;
  private AtomicLong lastClearCacheTime = new AtomicLong(0L);
  private final Limits limits;
  private final Object lock;
  private final String tag;
  
  public FileLruCache(String paramString, Limits paramLimits)
  {
    tag = paramString;
    limits = paramLimits;
    directory = new File(FacebookSdk.getCacheDir(), paramString);
    lock = new Object();
    if ((directory.mkdirs()) || (directory.isDirectory())) {
      BufferFile.deleteAll(directory);
    }
  }
  
  private void postTrim()
  {
    synchronized (lock)
    {
      if (!isTrimPending)
      {
        isTrimPending = true;
        FacebookSdk.getExecutor().execute(new Runnable()
        {
          public void run()
          {
            FileLruCache.this.trim();
          }
        });
      }
      return;
    }
  }
  
  private void renameToTargetAndTrim(String paramString, File paramFile)
  {
    if (!paramFile.renameTo(new File(directory, Utility.md5hash(paramString)))) {
      paramFile.delete();
    }
    postTrim();
  }
  
  /* Error */
  private void trim()
  {
    // Byte code:
    //   0: aload_0
    //   1: astore 10
    //   3: aload 10
    //   5: getfield 93	com/facebook/internal/FileLruCache:lock	Ljava/lang/Object;
    //   8: astore 9
    //   10: aload 9
    //   12: monitorenter
    //   13: aload 10
    //   15: iconst_0
    //   16: putfield 122	com/facebook/internal/FileLruCache:isTrimPending	Z
    //   19: aload 10
    //   21: iconst_1
    //   22: putfield 151	com/facebook/internal/FileLruCache:isTrimInProgress	Z
    //   25: aload 9
    //   27: monitorexit
    //   28: getstatic 157	com/facebook/LoggingBehavior:CACHE	Lcom/facebook/LoggingBehavior;
    //   31: getstatic 159	com/facebook/internal/FileLruCache:TAG	Ljava/lang/String;
    //   34: ldc -95
    //   36: invokestatic 167	com/facebook/internal/Logger:log	(Lcom/facebook/LoggingBehavior;Ljava/lang/String;Ljava/lang/String;)V
    //   39: new 169	java/util/PriorityQueue
    //   42: dup
    //   43: invokespecial 170	java/util/PriorityQueue:<init>	()V
    //   46: astore 9
    //   48: aload 10
    //   50: getfield 91	com/facebook/internal/FileLruCache:directory	Ljava/io/File;
    //   53: invokestatic 174	com/facebook/internal/FileLruCache$BufferFile:excludeBufferFiles	()Ljava/io/FilenameFilter;
    //   56: invokevirtual 178	java/io/File:listFiles	(Ljava/io/FilenameFilter;)[Ljava/io/File;
    //   59: astore 10
    //   61: lconst_0
    //   62: lstore 5
    //   64: aload 10
    //   66: ifnull +153 -> 219
    //   69: aload 10
    //   71: arraylength
    //   72: istore_2
    //   73: lconst_0
    //   74: lstore 5
    //   76: lload 5
    //   78: lstore_3
    //   79: iconst_0
    //   80: istore_1
    //   81: iload_1
    //   82: iload_2
    //   83: if_icmpge +133 -> 216
    //   86: aload 10
    //   88: iload_1
    //   89: aaload
    //   90: astore 11
    //   92: new 28	com/facebook/internal/FileLruCache$ModifiedFile
    //   95: dup
    //   96: aload 11
    //   98: invokespecial 180	com/facebook/internal/FileLruCache$ModifiedFile:<init>	(Ljava/io/File;)V
    //   101: astore 12
    //   103: aload 9
    //   105: aload 12
    //   107: invokevirtual 184	java/util/PriorityQueue:add	(Ljava/lang/Object;)Z
    //   110: pop
    //   111: getstatic 157	com/facebook/LoggingBehavior:CACHE	Lcom/facebook/LoggingBehavior;
    //   114: astore 13
    //   116: getstatic 159	com/facebook/internal/FileLruCache:TAG	Ljava/lang/String;
    //   119: astore 14
    //   121: new 186	java/lang/StringBuilder
    //   124: dup
    //   125: invokespecial 187	java/lang/StringBuilder:<init>	()V
    //   128: astore 15
    //   130: aload 15
    //   132: ldc -67
    //   134: invokevirtual 193	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   137: pop
    //   138: aload 15
    //   140: aload 12
    //   142: invokevirtual 197	com/facebook/internal/FileLruCache$ModifiedFile:getModified	()J
    //   145: invokestatic 203	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   148: invokevirtual 206	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   151: pop
    //   152: aload 15
    //   154: ldc -48
    //   156: invokevirtual 193	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   159: pop
    //   160: aload 15
    //   162: aload 12
    //   164: invokevirtual 211	com/facebook/internal/FileLruCache$ModifiedFile:getFile	()Ljava/io/File;
    //   167: invokevirtual 215	java/io/File:getName	()Ljava/lang/String;
    //   170: invokevirtual 193	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   173: pop
    //   174: aload 13
    //   176: aload 14
    //   178: aload 15
    //   180: invokevirtual 218	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   183: invokestatic 167	com/facebook/internal/Logger:log	(Lcom/facebook/LoggingBehavior;Ljava/lang/String;Ljava/lang/String;)V
    //   186: aload 11
    //   188: invokevirtual 221	java/io/File:length	()J
    //   191: lstore 7
    //   193: iload_1
    //   194: iconst_1
    //   195: iadd
    //   196: istore_1
    //   197: lload_3
    //   198: lconst_1
    //   199: ladd
    //   200: lstore_3
    //   201: lload 5
    //   203: lload 7
    //   205: ladd
    //   206: lstore 5
    //   208: goto -127 -> 81
    //   211: astore 9
    //   213: goto +173 -> 386
    //   216: goto +5 -> 221
    //   219: lconst_0
    //   220: lstore_3
    //   221: aload_0
    //   222: astore 10
    //   224: lload 5
    //   226: aload 10
    //   228: getfield 78	com/facebook/internal/FileLruCache:limits	Lcom/facebook/internal/FileLruCache$Limits;
    //   231: invokevirtual 225	com/facebook/internal/FileLruCache$Limits:getByteCount	()I
    //   234: i2l
    //   235: lcmp
    //   236: ifgt +58 -> 294
    //   239: aload 10
    //   241: getfield 78	com/facebook/internal/FileLruCache:limits	Lcom/facebook/internal/FileLruCache$Limits;
    //   244: invokevirtual 228	com/facebook/internal/FileLruCache$Limits:getFileCount	()I
    //   247: istore_1
    //   248: lload_3
    //   249: iload_1
    //   250: i2l
    //   251: lcmp
    //   252: ifle +6 -> 258
    //   255: goto +39 -> 294
    //   258: aload 10
    //   260: getfield 93	com/facebook/internal/FileLruCache:lock	Ljava/lang/Object;
    //   263: astore 9
    //   265: aload 9
    //   267: monitorenter
    //   268: aload 10
    //   270: iconst_0
    //   271: putfield 151	com/facebook/internal/FileLruCache:isTrimInProgress	Z
    //   274: aload 10
    //   276: getfield 93	com/facebook/internal/FileLruCache:lock	Ljava/lang/Object;
    //   279: invokevirtual 231	java/lang/Object:notifyAll	()V
    //   282: aload 9
    //   284: monitorexit
    //   285: return
    //   286: astore 10
    //   288: aload 9
    //   290: monitorexit
    //   291: aload 10
    //   293: athrow
    //   294: aload 9
    //   296: invokevirtual 235	java/util/PriorityQueue:remove	()Ljava/lang/Object;
    //   299: checkcast 28	com/facebook/internal/FileLruCache$ModifiedFile
    //   302: invokevirtual 211	com/facebook/internal/FileLruCache$ModifiedFile:getFile	()Ljava/io/File;
    //   305: astore 10
    //   307: getstatic 157	com/facebook/LoggingBehavior:CACHE	Lcom/facebook/LoggingBehavior;
    //   310: astore 11
    //   312: getstatic 159	com/facebook/internal/FileLruCache:TAG	Ljava/lang/String;
    //   315: astore 12
    //   317: new 186	java/lang/StringBuilder
    //   320: dup
    //   321: invokespecial 187	java/lang/StringBuilder:<init>	()V
    //   324: astore 13
    //   326: aload 13
    //   328: ldc -19
    //   330: invokevirtual 193	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   333: pop
    //   334: aload 13
    //   336: aload 10
    //   338: invokevirtual 215	java/io/File:getName	()Ljava/lang/String;
    //   341: invokevirtual 193	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   344: pop
    //   345: aload 11
    //   347: aload 12
    //   349: aload 13
    //   351: invokevirtual 218	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   354: invokestatic 167	com/facebook/internal/Logger:log	(Lcom/facebook/LoggingBehavior;Ljava/lang/String;Ljava/lang/String;)V
    //   357: aload 10
    //   359: invokevirtual 221	java/io/File:length	()J
    //   362: lstore 7
    //   364: aload 10
    //   366: invokevirtual 147	java/io/File:delete	()Z
    //   369: pop
    //   370: lload_3
    //   371: lconst_1
    //   372: lsub
    //   373: lstore_3
    //   374: lload 5
    //   376: lload 7
    //   378: lsub
    //   379: lstore 5
    //   381: goto -160 -> 221
    //   384: astore 9
    //   386: aload_0
    //   387: astore 10
    //   389: aload 10
    //   391: getfield 93	com/facebook/internal/FileLruCache:lock	Ljava/lang/Object;
    //   394: astore 11
    //   396: aload 11
    //   398: monitorenter
    //   399: aload 10
    //   401: iconst_0
    //   402: putfield 151	com/facebook/internal/FileLruCache:isTrimInProgress	Z
    //   405: aload 10
    //   407: getfield 93	com/facebook/internal/FileLruCache:lock	Ljava/lang/Object;
    //   410: invokevirtual 231	java/lang/Object:notifyAll	()V
    //   413: aload 11
    //   415: monitorexit
    //   416: aload 9
    //   418: athrow
    //   419: astore 9
    //   421: aload 11
    //   423: monitorexit
    //   424: aload 9
    //   426: athrow
    //   427: astore 10
    //   429: aload 9
    //   431: monitorexit
    //   432: aload 10
    //   434: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	435	0	this	FileLruCache
    //   80	170	1	i	int
    //   72	12	2	j	int
    //   78	296	3	l1	long
    //   62	318	5	l2	long
    //   191	186	7	l3	long
    //   8	96	9	localObject1	Object
    //   211	1	9	localObject2	Object
    //   384	33	9	localObject4	Object
    //   419	11	9	localObject5	Object
    //   1	274	10	localObject6	Object
    //   286	6	10	localObject7	Object
    //   305	101	10	localObject8	Object
    //   427	6	10	localObject9	Object
    //   101	247	12	localObject11	Object
    //   114	236	13	localObject12	Object
    //   119	58	14	str	String
    //   128	51	15	localStringBuilder	StringBuilder
    // Exception table:
    //   from	to	target	type
    //   138	193	211	finally
    //   268	285	286	finally
    //   288	291	286	finally
    //   28	61	384	finally
    //   69	73	384	finally
    //   92	138	384	finally
    //   224	248	384	finally
    //   294	370	384	finally
    //   399	416	419	finally
    //   421	424	419	finally
    //   13	28	427	finally
    //   429	432	427	finally
  }
  
  public void clearCache()
  {
    final File[] arrayOfFile = directory.listFiles(BufferFile.excludeBufferFiles());
    lastClearCacheTime.set(System.currentTimeMillis());
    if (arrayOfFile != null) {
      FacebookSdk.getExecutor().execute(new Runnable()
      {
        public void run()
        {
          File[] arrayOfFile = arrayOfFile;
          int i = 0;
          int j = arrayOfFile.length;
          while (i < j)
          {
            arrayOfFile[i].delete();
            i += 1;
          }
        }
      });
    }
  }
  
  public InputStream get(String paramString)
    throws IOException
  {
    return get(paramString, null);
  }
  
  public InputStream get(String paramString1, String paramString2)
    throws IOException
  {
    File localFile = new File(directory, Utility.md5hash(paramString1));
    try
    {
      Object localObject1 = new FileInputStream(localFile);
      localObject1 = new BufferedInputStream((InputStream)localObject1, 8192);
      try
      {
        Object localObject2 = StreamHeader.readHeader((InputStream)localObject1);
        if (localObject2 == null) {
          return null;
        }
        String str = ((JSONObject)localObject2).optString("key");
        if ((str != null) && (str.equals(paramString1)))
        {
          paramString1 = ((JSONObject)localObject2).optString("tag", null);
          if ((paramString2 != null) || (paramString1 == null))
          {
            if (paramString2 != null)
            {
              boolean bool = paramString2.equals(paramString1);
              if (bool) {}
            }
          }
          else {
            return null;
          }
          long l = new Date().getTime();
          paramString1 = LoggingBehavior.CACHE;
          paramString2 = TAG;
          localObject2 = new StringBuilder();
          ((StringBuilder)localObject2).append("Setting lastModified to ");
          ((StringBuilder)localObject2).append(Long.valueOf(l));
          ((StringBuilder)localObject2).append(" for ");
          ((StringBuilder)localObject2).append(localFile.getName());
          Logger.log(paramString1, paramString2, ((StringBuilder)localObject2).toString());
          localFile.setLastModified(l);
          return localObject1;
        }
        return null;
      }
      finally
      {
        ((BufferedInputStream)localObject1).close();
      }
      return null;
    }
    catch (IOException paramString1) {}
  }
  
  public String getLocation()
  {
    return directory.getPath();
  }
  
  public InputStream interceptAndPut(String paramString, InputStream paramInputStream)
    throws IOException
  {
    return new CopyingInputStream(paramInputStream, openPutStream(paramString));
  }
  
  public OutputStream openPutStream(String paramString)
    throws IOException
  {
    return openPutStream(paramString, null);
  }
  
  /* Error */
  public OutputStream openPutStream(final String paramString1, String paramString2)
    throws IOException
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 91	com/facebook/internal/FileLruCache:directory	Ljava/io/File;
    //   4: invokestatic 323	com/facebook/internal/FileLruCache$BufferFile:newFile	(Ljava/io/File;)Ljava/io/File;
    //   7: astore_3
    //   8: aload_3
    //   9: invokevirtual 147	java/io/File:delete	()Z
    //   12: pop
    //   13: aload_3
    //   14: invokevirtual 326	java/io/File:createNewFile	()Z
    //   17: ifne +40 -> 57
    //   20: new 186	java/lang/StringBuilder
    //   23: dup
    //   24: invokespecial 187	java/lang/StringBuilder:<init>	()V
    //   27: astore_1
    //   28: aload_1
    //   29: ldc_w 328
    //   32: invokevirtual 193	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   35: pop
    //   36: aload_1
    //   37: aload_3
    //   38: invokevirtual 331	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   41: invokevirtual 193	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   44: pop
    //   45: new 253	java/io/IOException
    //   48: dup
    //   49: aload_1
    //   50: invokevirtual 218	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   53: invokespecial 334	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   56: athrow
    //   57: new 336	java/io/FileOutputStream
    //   60: dup
    //   61: aload_3
    //   62: invokespecial 337	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   65: astore 4
    //   67: new 339	java/io/BufferedOutputStream
    //   70: dup
    //   71: new 19	com/facebook/internal/FileLruCache$CloseCallbackOutputStream
    //   74: dup
    //   75: aload 4
    //   77: new 6	com/facebook/internal/FileLruCache$1
    //   80: dup
    //   81: aload_0
    //   82: invokestatic 243	java/lang/System:currentTimeMillis	()J
    //   85: aload_3
    //   86: aload_1
    //   87: invokespecial 342	com/facebook/internal/FileLruCache$1:<init>	(Lcom/facebook/internal/FileLruCache;JLjava/io/File;Ljava/lang/String;)V
    //   90: invokespecial 345	com/facebook/internal/FileLruCache$CloseCallbackOutputStream:<init>	(Ljava/io/OutputStream;Lcom/facebook/internal/FileLruCache$StreamCloseCallback;)V
    //   93: sipush 8192
    //   96: invokespecial 348	java/io/BufferedOutputStream:<init>	(Ljava/io/OutputStream;I)V
    //   99: astore_3
    //   100: new 274	org/json/JSONObject
    //   103: dup
    //   104: invokespecial 349	org/json/JSONObject:<init>	()V
    //   107: astore 4
    //   109: aload 4
    //   111: ldc 39
    //   113: aload_1
    //   114: invokevirtual 353	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   117: pop
    //   118: aload_2
    //   119: invokestatic 357	com/facebook/internal/Utility:isNullOrEmpty	(Ljava/lang/String;)Z
    //   122: ifne +12 -> 134
    //   125: aload 4
    //   127: ldc 42
    //   129: aload_2
    //   130: invokevirtual 353	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   133: pop
    //   134: aload_3
    //   135: aload 4
    //   137: invokestatic 361	com/facebook/internal/FileLruCache$StreamHeader:writeHeader	(Ljava/io/OutputStream;Lorg/json/JSONObject;)V
    //   140: aload_3
    //   141: areturn
    //   142: astore_1
    //   143: goto +62 -> 205
    //   146: astore_1
    //   147: getstatic 157	com/facebook/LoggingBehavior:CACHE	Lcom/facebook/LoggingBehavior;
    //   150: astore_2
    //   151: getstatic 159	com/facebook/internal/FileLruCache:TAG	Ljava/lang/String;
    //   154: astore 4
    //   156: new 186	java/lang/StringBuilder
    //   159: dup
    //   160: invokespecial 187	java/lang/StringBuilder:<init>	()V
    //   163: astore 5
    //   165: aload 5
    //   167: ldc_w 363
    //   170: invokevirtual 193	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   173: pop
    //   174: aload 5
    //   176: aload_1
    //   177: invokevirtual 206	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   180: pop
    //   181: aload_2
    //   182: iconst_5
    //   183: aload 4
    //   185: aload 5
    //   187: invokevirtual 218	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   190: invokestatic 366	com/facebook/internal/Logger:log	(Lcom/facebook/LoggingBehavior;ILjava/lang/String;Ljava/lang/String;)V
    //   193: new 253	java/io/IOException
    //   196: dup
    //   197: aload_1
    //   198: invokevirtual 369	org/json/JSONException:getMessage	()Ljava/lang/String;
    //   201: invokespecial 334	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   204: athrow
    //   205: aload_3
    //   206: invokevirtual 370	java/io/BufferedOutputStream:close	()V
    //   209: aload_1
    //   210: athrow
    //   211: astore_1
    //   212: getstatic 157	com/facebook/LoggingBehavior:CACHE	Lcom/facebook/LoggingBehavior;
    //   215: astore_2
    //   216: getstatic 159	com/facebook/internal/FileLruCache:TAG	Ljava/lang/String;
    //   219: astore_3
    //   220: new 186	java/lang/StringBuilder
    //   223: dup
    //   224: invokespecial 187	java/lang/StringBuilder:<init>	()V
    //   227: astore 4
    //   229: aload 4
    //   231: ldc_w 372
    //   234: invokevirtual 193	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   237: pop
    //   238: aload 4
    //   240: aload_1
    //   241: invokevirtual 206	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   244: pop
    //   245: aload_2
    //   246: iconst_5
    //   247: aload_3
    //   248: aload 4
    //   250: invokevirtual 218	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   253: invokestatic 366	com/facebook/internal/Logger:log	(Lcom/facebook/LoggingBehavior;ILjava/lang/String;Ljava/lang/String;)V
    //   256: new 253	java/io/IOException
    //   259: dup
    //   260: aload_1
    //   261: invokevirtual 373	java/io/FileNotFoundException:getMessage	()Ljava/lang/String;
    //   264: invokespecial 334	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   267: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	268	0	this	FileLruCache
    //   0	268	1	paramString1	String
    //   0	268	2	paramString2	String
    //   7	241	3	localObject1	Object
    //   65	184	4	localObject2	Object
    //   163	23	5	localStringBuilder	StringBuilder
    // Exception table:
    //   from	to	target	type
    //   100	134	142	finally
    //   134	140	142	finally
    //   147	205	142	finally
    //   100	134	146	org/json/JSONException
    //   134	140	146	org/json/JSONException
    //   57	67	211	java/io/FileNotFoundException
  }
  
  long sizeInBytesForTest()
  {
    for (;;)
    {
      synchronized (lock)
      {
        if ((!isTrimPending) && (!isTrimInProgress))
        {
          ??? = directory.listFiles();
          long l1 = 0L;
          long l2 = l1;
          if (??? != null)
          {
            int j = ???.length;
            int i = 0;
            l2 = l1;
            if (i < j)
            {
              l2 = ???[i].length();
              i += 1;
              l1 += l2;
              continue;
            }
          }
          return l2;
        }
      }
    }
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("{FileLruCache: tag:");
    localStringBuilder.append(tag);
    localStringBuilder.append(" file:");
    localStringBuilder.append(directory.getName());
    localStringBuilder.append("}");
    return localStringBuilder.toString();
  }
  
  private static class BufferFile
  {
    private static final String FILE_NAME_PREFIX = "buffer";
    private static final FilenameFilter filterExcludeBufferFiles = new FilenameFilter()
    {
      public boolean accept(File paramAnonymousFile, String paramAnonymousString)
      {
        return paramAnonymousString.startsWith("buffer") ^ true;
      }
    };
    private static final FilenameFilter filterExcludeNonBufferFiles = new FilenameFilter()
    {
      public boolean accept(File paramAnonymousFile, String paramAnonymousString)
      {
        return paramAnonymousString.startsWith("buffer");
      }
    };
    
    private BufferFile() {}
    
    static void deleteAll(File paramFile)
    {
      paramFile = paramFile.listFiles(excludeNonBufferFiles());
      if (paramFile != null)
      {
        int j = paramFile.length;
        int i = 0;
        while (i < j)
        {
          paramFile[i].delete();
          i += 1;
        }
      }
    }
    
    static FilenameFilter excludeBufferFiles()
    {
      return filterExcludeBufferFiles;
    }
    
    static FilenameFilter excludeNonBufferFiles()
    {
      return filterExcludeNonBufferFiles;
    }
    
    static File newFile(File paramFile)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("buffer");
      localStringBuilder.append(Long.valueOf(FileLruCache.bufferIndex.incrementAndGet()).toString());
      return new File(paramFile, localStringBuilder.toString());
    }
  }
  
  private static class CloseCallbackOutputStream
    extends OutputStream
  {
    final FileLruCache.StreamCloseCallback callback;
    final OutputStream innerStream;
    
    CloseCallbackOutputStream(OutputStream paramOutputStream, FileLruCache.StreamCloseCallback paramStreamCloseCallback)
    {
      innerStream = paramOutputStream;
      callback = paramStreamCloseCallback;
    }
    
    public void close()
      throws IOException
    {
      try
      {
        innerStream.close();
        return;
      }
      finally
      {
        callback.onClose();
      }
    }
    
    public void flush()
      throws IOException
    {
      innerStream.flush();
    }
    
    public void write(int paramInt)
      throws IOException
    {
      innerStream.write(paramInt);
    }
    
    public void write(byte[] paramArrayOfByte)
      throws IOException
    {
      innerStream.write(paramArrayOfByte);
    }
    
    public void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
      throws IOException
    {
      innerStream.write(paramArrayOfByte, paramInt1, paramInt2);
    }
  }
  
  private static final class CopyingInputStream
    extends InputStream
  {
    final InputStream input;
    final OutputStream output;
    
    CopyingInputStream(InputStream paramInputStream, OutputStream paramOutputStream)
    {
      input = paramInputStream;
      output = paramOutputStream;
    }
    
    public int available()
      throws IOException
    {
      return input.available();
    }
    
    public void close()
      throws IOException
    {
      try
      {
        input.close();
        return;
      }
      finally
      {
        output.close();
      }
    }
    
    public void mark(int paramInt)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean markSupported()
    {
      return false;
    }
    
    public int read()
      throws IOException
    {
      int i = input.read();
      if (i >= 0) {
        output.write(i);
      }
      return i;
    }
    
    public int read(byte[] paramArrayOfByte)
      throws IOException
    {
      int i = input.read(paramArrayOfByte);
      if (i > 0) {
        output.write(paramArrayOfByte, 0, i);
      }
      return i;
    }
    
    public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
      throws IOException
    {
      paramInt2 = input.read(paramArrayOfByte, paramInt1, paramInt2);
      if (paramInt2 > 0) {
        output.write(paramArrayOfByte, paramInt1, paramInt2);
      }
      return paramInt2;
    }
    
    public void reset()
    {
      try
      {
        throw new UnsupportedOperationException();
      }
      finally {}
    }
    
    public long skip(long paramLong)
      throws IOException
    {
      byte[] arrayOfByte = new byte['Ѐ'];
      int i;
      for (long l = 0L; l < paramLong; l += i)
      {
        i = read(arrayOfByte, 0, (int)Math.min(paramLong - l, arrayOfByte.length));
        if (i < 0) {
          return l;
        }
      }
      return l;
    }
  }
  
  public static final class Limits
  {
    private int byteCount = 1048576;
    private int fileCount = 1024;
    
    public Limits() {}
    
    int getByteCount()
    {
      return byteCount;
    }
    
    int getFileCount()
    {
      return fileCount;
    }
    
    void setByteCount(int paramInt)
    {
      if (paramInt < 0) {
        throw new InvalidParameterException("Cache byte-count limit must be >= 0");
      }
      byteCount = paramInt;
    }
    
    void setFileCount(int paramInt)
    {
      if (paramInt < 0) {
        throw new InvalidParameterException("Cache file count limit must be >= 0");
      }
      fileCount = paramInt;
    }
  }
  
  private static final class ModifiedFile
    implements Comparable<ModifiedFile>
  {
    private static final int HASH_MULTIPLIER = 37;
    private static final int HASH_SEED = 29;
    private final File file;
    private final long modified;
    
    ModifiedFile(File paramFile)
    {
      file = paramFile;
      modified = paramFile.lastModified();
    }
    
    public int compareTo(ModifiedFile paramModifiedFile)
    {
      if (getModified() < paramModifiedFile.getModified()) {
        return -1;
      }
      if (getModified() > paramModifiedFile.getModified()) {
        return 1;
      }
      return getFile().compareTo(paramModifiedFile.getFile());
    }
    
    public boolean equals(Object paramObject)
    {
      return ((paramObject instanceof ModifiedFile)) && (compareTo((ModifiedFile)paramObject) == 0);
    }
    
    File getFile()
    {
      return file;
    }
    
    long getModified()
    {
      return modified;
    }
    
    public int hashCode()
    {
      return (1073 + file.hashCode()) * 37 + (int)(modified % 2147483647L);
    }
  }
  
  private static abstract interface StreamCloseCallback
  {
    public abstract void onClose();
  }
  
  private static final class StreamHeader
  {
    private static final int HEADER_VERSION = 0;
    
    private StreamHeader() {}
    
    static JSONObject readHeader(InputStream paramInputStream)
      throws IOException
    {
      if (paramInputStream.read() != 0) {
        return null;
      }
      int k = 0;
      int i = 0;
      int j = i;
      while (i < 3)
      {
        int m = paramInputStream.read();
        if (m == -1)
        {
          Logger.log(LoggingBehavior.CACHE, FileLruCache.TAG, "readHeader: stream.read returned -1 while reading header size");
          return null;
        }
        j = (j << 8) + (m & 0xFF);
        i += 1;
      }
      Object localObject = new byte[j];
      i = k;
      String str;
      StringBuilder localStringBuilder;
      while (i < localObject.length)
      {
        j = paramInputStream.read((byte[])localObject, i, localObject.length - i);
        if (j < 1)
        {
          paramInputStream = LoggingBehavior.CACHE;
          str = FileLruCache.TAG;
          localStringBuilder = new StringBuilder();
          localStringBuilder.append("readHeader: stream.read stopped at ");
          localStringBuilder.append(Integer.valueOf(i));
          localStringBuilder.append(" when expected ");
          localStringBuilder.append(localObject.length);
          Logger.log(paramInputStream, str, localStringBuilder.toString());
          return null;
        }
        i += j;
      }
      paramInputStream = new JSONTokener(new String((byte[])localObject));
      try
      {
        paramInputStream = paramInputStream.nextValue();
        if (!(paramInputStream instanceof JSONObject))
        {
          localObject = LoggingBehavior.CACHE;
          str = FileLruCache.TAG;
          localStringBuilder = new StringBuilder();
          localStringBuilder.append("readHeader: expected JSONObject, got ");
          localStringBuilder.append(paramInputStream.getClass().getCanonicalName());
          Logger.log((LoggingBehavior)localObject, str, localStringBuilder.toString());
          return null;
        }
        paramInputStream = (JSONObject)paramInputStream;
        return paramInputStream;
      }
      catch (JSONException paramInputStream)
      {
        throw new IOException(paramInputStream.getMessage());
      }
    }
    
    static void writeHeader(OutputStream paramOutputStream, JSONObject paramJSONObject)
      throws IOException
    {
      paramJSONObject = paramJSONObject.toString().getBytes();
      paramOutputStream.write(0);
      paramOutputStream.write(paramJSONObject.length >> 16 & 0xFF);
      paramOutputStream.write(paramJSONObject.length >> 8 & 0xFF);
      paramOutputStream.write(paramJSONObject.length >> 0 & 0xFF);
      paramOutputStream.write(paramJSONObject);
    }
  }
}
