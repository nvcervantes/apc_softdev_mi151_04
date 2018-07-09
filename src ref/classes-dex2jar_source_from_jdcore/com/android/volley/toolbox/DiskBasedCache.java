package com.android.volley.toolbox;

import android.os.SystemClock;
import com.android.volley.Cache;
import com.android.volley.Cache.Entry;
import com.android.volley.VolleyLog;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class DiskBasedCache
  implements Cache
{
  private static final int CACHE_MAGIC = 538247942;
  private static final int DEFAULT_DISK_USAGE_BYTES = 5242880;
  private static final float HYSTERESIS_FACTOR = 0.9F;
  private final Map<String, CacheHeader> mEntries = new LinkedHashMap(16, 0.75F, true);
  private final int mMaxCacheSizeInBytes;
  private final File mRootDirectory;
  private long mTotalSize = 0L;
  
  public DiskBasedCache(File paramFile)
  {
    this(paramFile, 5242880);
  }
  
  public DiskBasedCache(File paramFile, int paramInt)
  {
    mRootDirectory = paramFile;
    mMaxCacheSizeInBytes = paramInt;
  }
  
  private String getFilenameForKey(String paramString)
  {
    int i = paramString.length() / 2;
    String str = String.valueOf(String.valueOf(paramString.substring(0, i).hashCode()));
    paramString = String.valueOf(String.valueOf(paramString.substring(i).hashCode()));
    if (paramString.length() != 0) {
      return str.concat(paramString);
    }
    return new String(str);
  }
  
  private void pruneIfNeeded(int paramInt)
  {
    long l2 = mTotalSize;
    long l1 = paramInt;
    if (l2 + l1 < mMaxCacheSizeInBytes) {
      return;
    }
    if (VolleyLog.DEBUG) {
      VolleyLog.v("Pruning old cache entries.", new Object[0]);
    }
    long l3 = mTotalSize;
    l2 = SystemClock.elapsedRealtime();
    Iterator localIterator = mEntries.entrySet().iterator();
    paramInt = 0;
    while (localIterator.hasNext())
    {
      CacheHeader localCacheHeader = (CacheHeader)((Map.Entry)localIterator.next()).getValue();
      if (getFileForKey(key).delete()) {
        mTotalSize -= size;
      } else {
        VolleyLog.d("Could not delete cache entry for key=%s, filename=%s", new Object[] { key, getFilenameForKey(key) });
      }
      localIterator.remove();
      paramInt += 1;
      if ((float)(mTotalSize + l1) < mMaxCacheSizeInBytes * 0.9F) {
        break;
      }
    }
    if (VolleyLog.DEBUG) {
      VolleyLog.v("pruned %d files, %d bytes, %d ms", new Object[] { Integer.valueOf(paramInt), Long.valueOf(mTotalSize - l3), Long.valueOf(SystemClock.elapsedRealtime() - l2) });
    }
  }
  
  private void putEntry(String paramString, CacheHeader paramCacheHeader)
  {
    if (!mEntries.containsKey(paramString))
    {
      mTotalSize += size;
    }
    else
    {
      CacheHeader localCacheHeader = (CacheHeader)mEntries.get(paramString);
      mTotalSize += size - size;
    }
    mEntries.put(paramString, paramCacheHeader);
  }
  
  private static int read(InputStream paramInputStream)
    throws IOException
  {
    int i = paramInputStream.read();
    if (i == -1) {
      throw new EOFException();
    }
    return i;
  }
  
  static int readInt(InputStream paramInputStream)
    throws IOException
  {
    int i = read(paramInputStream);
    int j = read(paramInputStream);
    int k = read(paramInputStream);
    return read(paramInputStream) << 24 | i << 0 | 0x0 | j << 8 | k << 16;
  }
  
  static long readLong(InputStream paramInputStream)
    throws IOException
  {
    return 0L | (read(paramInputStream) & 0xFF) << 0 | (read(paramInputStream) & 0xFF) << 8 | (read(paramInputStream) & 0xFF) << 16 | (read(paramInputStream) & 0xFF) << 24 | (read(paramInputStream) & 0xFF) << 32 | (read(paramInputStream) & 0xFF) << 40 | (read(paramInputStream) & 0xFF) << 48 | (read(paramInputStream) & 0xFF) << 56;
  }
  
  static String readString(InputStream paramInputStream)
    throws IOException
  {
    return new String(streamToBytes(paramInputStream, (int)readLong(paramInputStream)), "UTF-8");
  }
  
  static Map<String, String> readStringStringMap(InputStream paramInputStream)
    throws IOException
  {
    int j = readInt(paramInputStream);
    Object localObject;
    if (j == 0) {
      localObject = Collections.emptyMap();
    } else {
      localObject = new HashMap(j);
    }
    int i = 0;
    while (i < j)
    {
      ((Map)localObject).put(readString(paramInputStream).intern(), readString(paramInputStream).intern());
      i += 1;
    }
    return localObject;
  }
  
  private void removeEntry(String paramString)
  {
    CacheHeader localCacheHeader = (CacheHeader)mEntries.get(paramString);
    if (localCacheHeader != null)
    {
      mTotalSize -= size;
      mEntries.remove(paramString);
    }
  }
  
  private static byte[] streamToBytes(InputStream paramInputStream, int paramInt)
    throws IOException
  {
    byte[] arrayOfByte = new byte[paramInt];
    int i = 0;
    while (i < paramInt)
    {
      int j = paramInputStream.read(arrayOfByte, i, paramInt - i);
      if (j == -1) {
        break;
      }
      i += j;
    }
    if (i != paramInt)
    {
      paramInputStream = new StringBuilder(50);
      paramInputStream.append("Expected ");
      paramInputStream.append(paramInt);
      paramInputStream.append(" bytes, read ");
      paramInputStream.append(i);
      paramInputStream.append(" bytes");
      throw new IOException(paramInputStream.toString());
    }
    return arrayOfByte;
  }
  
  static void writeInt(OutputStream paramOutputStream, int paramInt)
    throws IOException
  {
    paramOutputStream.write(paramInt >> 0 & 0xFF);
    paramOutputStream.write(paramInt >> 8 & 0xFF);
    paramOutputStream.write(paramInt >> 16 & 0xFF);
    paramOutputStream.write(paramInt >> 24 & 0xFF);
  }
  
  static void writeLong(OutputStream paramOutputStream, long paramLong)
    throws IOException
  {
    paramOutputStream.write((byte)(int)(paramLong >>> 0));
    paramOutputStream.write((byte)(int)(paramLong >>> 8));
    paramOutputStream.write((byte)(int)(paramLong >>> 16));
    paramOutputStream.write((byte)(int)(paramLong >>> 24));
    paramOutputStream.write((byte)(int)(paramLong >>> 32));
    paramOutputStream.write((byte)(int)(paramLong >>> 40));
    paramOutputStream.write((byte)(int)(paramLong >>> 48));
    paramOutputStream.write((byte)(int)(paramLong >>> 56));
  }
  
  static void writeString(OutputStream paramOutputStream, String paramString)
    throws IOException
  {
    paramString = paramString.getBytes("UTF-8");
    writeLong(paramOutputStream, paramString.length);
    paramOutputStream.write(paramString, 0, paramString.length);
  }
  
  static void writeStringStringMap(Map<String, String> paramMap, OutputStream paramOutputStream)
    throws IOException
  {
    if (paramMap != null)
    {
      writeInt(paramOutputStream, paramMap.size());
      paramMap = paramMap.entrySet().iterator();
      while (paramMap.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)paramMap.next();
        writeString(paramOutputStream, (String)localEntry.getKey());
        writeString(paramOutputStream, (String)localEntry.getValue());
      }
    }
    writeInt(paramOutputStream, 0);
  }
  
  public void clear()
  {
    try
    {
      File[] arrayOfFile = mRootDirectory.listFiles();
      if (arrayOfFile != null)
      {
        int j = arrayOfFile.length;
        int i = 0;
        while (i < j)
        {
          arrayOfFile[i].delete();
          i += 1;
        }
      }
      mEntries.clear();
      mTotalSize = 0L;
      VolleyLog.d("Cache cleared.", new Object[0]);
      return;
    }
    finally {}
  }
  
  /* Error */
  public Cache.Entry get(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 47	com/android/volley/toolbox/DiskBasedCache:mEntries	Ljava/util/Map;
    //   6: aload_1
    //   7: invokeinterface 178 2 0
    //   12: checkcast 10	com/android/volley/toolbox/DiskBasedCache$CacheHeader
    //   15: astore 4
    //   17: aload 4
    //   19: ifnonnull +7 -> 26
    //   22: aload_0
    //   23: monitorexit
    //   24: aconst_null
    //   25: areturn
    //   26: aload_0
    //   27: aload_1
    //   28: invokevirtual 138	com/android/volley/toolbox/DiskBasedCache:getFileForKey	(Ljava/lang/String;)Ljava/io/File;
    //   31: astore 5
    //   33: new 13	com/android/volley/toolbox/DiskBasedCache$CountingInputStream
    //   36: dup
    //   37: new 306	java/io/BufferedInputStream
    //   40: dup
    //   41: new 308	java/io/FileInputStream
    //   44: dup
    //   45: aload 5
    //   47: invokespecial 310	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   50: invokespecial 313	java/io/BufferedInputStream:<init>	(Ljava/io/InputStream;)V
    //   53: aconst_null
    //   54: invokespecial 316	com/android/volley/toolbox/DiskBasedCache$CountingInputStream:<init>	(Ljava/io/InputStream;Lcom/android/volley/toolbox/DiskBasedCache$1;)V
    //   57: astore_3
    //   58: aload_3
    //   59: astore_2
    //   60: aload_3
    //   61: invokestatic 320	com/android/volley/toolbox/DiskBasedCache$CacheHeader:readHeader	(Ljava/io/InputStream;)Lcom/android/volley/toolbox/DiskBasedCache$CacheHeader;
    //   64: pop
    //   65: aload_3
    //   66: astore_2
    //   67: aload 4
    //   69: aload_3
    //   70: aload 5
    //   72: invokevirtual 322	java/io/File:length	()J
    //   75: aload_3
    //   76: invokestatic 326	com/android/volley/toolbox/DiskBasedCache$CountingInputStream:access$100	(Lcom/android/volley/toolbox/DiskBasedCache$CountingInputStream;)I
    //   79: i2l
    //   80: lsub
    //   81: l2i
    //   82: invokestatic 209	com/android/volley/toolbox/DiskBasedCache:streamToBytes	(Ljava/io/InputStream;I)[B
    //   85: invokevirtual 330	com/android/volley/toolbox/DiskBasedCache$CacheHeader:toCacheEntry	([B)Lcom/android/volley/Cache$Entry;
    //   88: astore 4
    //   90: aload_3
    //   91: ifnull +14 -> 105
    //   94: aload_3
    //   95: invokevirtual 333	com/android/volley/toolbox/DiskBasedCache$CountingInputStream:close	()V
    //   98: goto +7 -> 105
    //   101: aload_0
    //   102: monitorexit
    //   103: aconst_null
    //   104: areturn
    //   105: aload_0
    //   106: monitorexit
    //   107: aload 4
    //   109: areturn
    //   110: astore 4
    //   112: goto +13 -> 125
    //   115: astore_1
    //   116: aconst_null
    //   117: astore_2
    //   118: goto +62 -> 180
    //   121: astore 4
    //   123: aconst_null
    //   124: astore_3
    //   125: aload_3
    //   126: astore_2
    //   127: ldc_w 335
    //   130: iconst_2
    //   131: anewarray 4	java/lang/Object
    //   134: dup
    //   135: iconst_0
    //   136: aload 5
    //   138: invokevirtual 338	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   141: aastore
    //   142: dup
    //   143: iconst_1
    //   144: aload 4
    //   146: invokevirtual 339	java/io/IOException:toString	()Ljava/lang/String;
    //   149: aastore
    //   150: invokestatic 153	com/android/volley/VolleyLog:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   153: aload_3
    //   154: astore_2
    //   155: aload_0
    //   156: aload_1
    //   157: invokevirtual 341	com/android/volley/toolbox/DiskBasedCache:remove	(Ljava/lang/String;)V
    //   160: aload_3
    //   161: ifnull +14 -> 175
    //   164: aload_3
    //   165: invokevirtual 333	com/android/volley/toolbox/DiskBasedCache$CountingInputStream:close	()V
    //   168: goto +7 -> 175
    //   171: aload_0
    //   172: monitorexit
    //   173: aconst_null
    //   174: areturn
    //   175: aload_0
    //   176: monitorexit
    //   177: aconst_null
    //   178: areturn
    //   179: astore_1
    //   180: aload_2
    //   181: ifnull +14 -> 195
    //   184: aload_2
    //   185: invokevirtual 333	com/android/volley/toolbox/DiskBasedCache$CountingInputStream:close	()V
    //   188: goto +7 -> 195
    //   191: aload_0
    //   192: monitorexit
    //   193: aconst_null
    //   194: areturn
    //   195: aload_1
    //   196: athrow
    //   197: astore_1
    //   198: aload_0
    //   199: monitorexit
    //   200: aload_1
    //   201: athrow
    //   202: astore_1
    //   203: goto -102 -> 101
    //   206: astore_1
    //   207: goto -36 -> 171
    //   210: astore_1
    //   211: goto -20 -> 191
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	214	0	this	DiskBasedCache
    //   0	214	1	paramString	String
    //   59	126	2	localCountingInputStream1	CountingInputStream
    //   57	108	3	localCountingInputStream2	CountingInputStream
    //   15	93	4	localObject	Object
    //   110	1	4	localIOException1	IOException
    //   121	24	4	localIOException2	IOException
    //   31	106	5	localFile	File
    // Exception table:
    //   from	to	target	type
    //   60	65	110	java/io/IOException
    //   67	90	110	java/io/IOException
    //   33	58	115	finally
    //   33	58	121	java/io/IOException
    //   60	65	179	finally
    //   67	90	179	finally
    //   127	153	179	finally
    //   155	160	179	finally
    //   2	17	197	finally
    //   26	33	197	finally
    //   94	98	197	finally
    //   164	168	197	finally
    //   184	188	197	finally
    //   195	197	197	finally
    //   94	98	202	java/io/IOException
    //   164	168	206	java/io/IOException
    //   184	188	210	java/io/IOException
  }
  
  public File getFileForKey(String paramString)
  {
    return new File(mRootDirectory, getFilenameForKey(paramString));
  }
  
  /* Error */
  public void initialize()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 51	com/android/volley/toolbox/DiskBasedCache:mRootDirectory	Ljava/io/File;
    //   6: invokevirtual 348	java/io/File:exists	()Z
    //   9: istore_3
    //   10: iconst_0
    //   11: istore_1
    //   12: iload_3
    //   13: ifne +36 -> 49
    //   16: aload_0
    //   17: getfield 51	com/android/volley/toolbox/DiskBasedCache:mRootDirectory	Ljava/io/File;
    //   20: invokevirtual 351	java/io/File:mkdirs	()Z
    //   23: ifne +23 -> 46
    //   26: ldc_w 353
    //   29: iconst_1
    //   30: anewarray 4	java/lang/Object
    //   33: dup
    //   34: iconst_0
    //   35: aload_0
    //   36: getfield 51	com/android/volley/toolbox/DiskBasedCache:mRootDirectory	Ljava/io/File;
    //   39: invokevirtual 338	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   42: aastore
    //   43: invokestatic 356	com/android/volley/VolleyLog:e	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   46: aload_0
    //   47: monitorexit
    //   48: return
    //   49: aload_0
    //   50: getfield 51	com/android/volley/toolbox/DiskBasedCache:mRootDirectory	Ljava/io/File;
    //   53: invokevirtual 299	java/io/File:listFiles	()[Ljava/io/File;
    //   56: astore 7
    //   58: aload 7
    //   60: ifnonnull +6 -> 66
    //   63: aload_0
    //   64: monitorexit
    //   65: return
    //   66: aload 7
    //   68: arraylength
    //   69: istore_2
    //   70: iload_1
    //   71: iload_2
    //   72: if_icmpge +143 -> 215
    //   75: aload 7
    //   77: iload_1
    //   78: aaload
    //   79: astore 8
    //   81: aconst_null
    //   82: astore 6
    //   84: aconst_null
    //   85: astore 4
    //   87: new 306	java/io/BufferedInputStream
    //   90: dup
    //   91: new 308	java/io/FileInputStream
    //   94: dup
    //   95: aload 8
    //   97: invokespecial 310	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   100: invokespecial 313	java/io/BufferedInputStream:<init>	(Ljava/io/InputStream;)V
    //   103: astore 5
    //   105: aload 5
    //   107: invokestatic 320	com/android/volley/toolbox/DiskBasedCache$CacheHeader:readHeader	(Ljava/io/InputStream;)Lcom/android/volley/toolbox/DiskBasedCache$CacheHeader;
    //   110: astore 4
    //   112: aload 4
    //   114: aload 8
    //   116: invokevirtual 322	java/io/File:length	()J
    //   119: putfield 146	com/android/volley/toolbox/DiskBasedCache$CacheHeader:size	J
    //   122: aload_0
    //   123: aload 4
    //   125: getfield 134	com/android/volley/toolbox/DiskBasedCache$CacheHeader:key	Ljava/lang/String;
    //   128: aload 4
    //   130: invokespecial 358	com/android/volley/toolbox/DiskBasedCache:putEntry	(Ljava/lang/String;Lcom/android/volley/toolbox/DiskBasedCache$CacheHeader;)V
    //   133: aload 5
    //   135: ifnull +73 -> 208
    //   138: aload 5
    //   140: invokevirtual 359	java/io/BufferedInputStream:close	()V
    //   143: goto +65 -> 208
    //   146: astore 6
    //   148: aload 5
    //   150: astore 4
    //   152: aload 6
    //   154: astore 5
    //   156: goto +29 -> 185
    //   159: goto +8 -> 167
    //   162: astore 5
    //   164: goto +21 -> 185
    //   167: aload 8
    //   169: ifnull +29 -> 198
    //   172: aload 5
    //   174: astore 4
    //   176: aload 8
    //   178: invokevirtual 143	java/io/File:delete	()Z
    //   181: pop
    //   182: goto +16 -> 198
    //   185: aload 4
    //   187: ifnull +8 -> 195
    //   190: aload 4
    //   192: invokevirtual 359	java/io/BufferedInputStream:close	()V
    //   195: aload 5
    //   197: athrow
    //   198: aload 5
    //   200: ifnull +8 -> 208
    //   203: aload 5
    //   205: invokevirtual 359	java/io/BufferedInputStream:close	()V
    //   208: iload_1
    //   209: iconst_1
    //   210: iadd
    //   211: istore_1
    //   212: goto -142 -> 70
    //   215: aload_0
    //   216: monitorexit
    //   217: return
    //   218: astore 4
    //   220: aload_0
    //   221: monitorexit
    //   222: aload 4
    //   224: athrow
    //   225: astore 4
    //   227: aload 6
    //   229: astore 5
    //   231: goto -64 -> 167
    //   234: astore 4
    //   236: goto -77 -> 159
    //   239: astore 4
    //   241: goto -33 -> 208
    //   244: astore 4
    //   246: goto -51 -> 195
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	249	0	this	DiskBasedCache
    //   11	201	1	i	int
    //   69	4	2	j	int
    //   9	4	3	bool	boolean
    //   85	106	4	localObject1	Object
    //   218	5	4	localObject2	Object
    //   225	1	4	localIOException1	IOException
    //   234	1	4	localIOException2	IOException
    //   239	1	4	localIOException3	IOException
    //   244	1	4	localIOException4	IOException
    //   103	52	5	localObject3	Object
    //   162	42	5	localObject4	Object
    //   229	1	5	localObject5	Object
    //   82	1	6	localObject6	Object
    //   146	82	6	localObject7	Object
    //   56	20	7	arrayOfFile	File[]
    //   79	98	8	localFile	File
    // Exception table:
    //   from	to	target	type
    //   105	133	146	finally
    //   87	105	162	finally
    //   176	182	162	finally
    //   2	10	218	finally
    //   16	46	218	finally
    //   49	58	218	finally
    //   66	70	218	finally
    //   138	143	218	finally
    //   190	195	218	finally
    //   195	198	218	finally
    //   203	208	218	finally
    //   87	105	225	java/io/IOException
    //   105	133	234	java/io/IOException
    //   138	143	239	java/io/IOException
    //   203	208	239	java/io/IOException
    //   190	195	244	java/io/IOException
  }
  
  public void invalidate(String paramString, boolean paramBoolean)
  {
    try
    {
      Cache.Entry localEntry = get(paramString);
      if (localEntry != null)
      {
        softTtl = 0L;
        if (paramBoolean) {
          ttl = 0L;
        }
        put(paramString, localEntry);
      }
      return;
    }
    finally {}
  }
  
  public void put(String paramString, Cache.Entry paramEntry)
  {
    File localFile;
    BufferedOutputStream localBufferedOutputStream;
    CacheHeader localCacheHeader;
    label109:
    try
    {
      pruneIfNeeded(data.length);
      localFile = getFileForKey(paramString);
    }
    finally {}
    try
    {
      localBufferedOutputStream = new BufferedOutputStream(new FileOutputStream(localFile));
      localCacheHeader = new CacheHeader(paramString, paramEntry);
      if (!localCacheHeader.writeHeader(localBufferedOutputStream))
      {
        localBufferedOutputStream.close();
        VolleyLog.d("Failed to write header for %s", new Object[] { localFile.getAbsolutePath() });
        throw new IOException();
      }
      localBufferedOutputStream.write(data);
      localBufferedOutputStream.close();
      putEntry(paramString, localCacheHeader);
      return;
    }
    catch (IOException paramString)
    {
      break label109;
    }
    if (!localFile.delete()) {
      VolleyLog.d("Could not clean up file %s", new Object[] { localFile.getAbsolutePath() });
    }
  }
  
  public void remove(String paramString)
  {
    try
    {
      boolean bool = getFileForKey(paramString).delete();
      removeEntry(paramString);
      if (!bool) {
        VolleyLog.d("Could not delete cache entry for key=%s, filename=%s", new Object[] { paramString, getFilenameForKey(paramString) });
      }
      return;
    }
    finally {}
  }
  
  static class CacheHeader
  {
    public String etag;
    public String key;
    public long lastModified;
    public Map<String, String> responseHeaders;
    public long serverDate;
    public long size;
    public long softTtl;
    public long ttl;
    
    private CacheHeader() {}
    
    public CacheHeader(String paramString, Cache.Entry paramEntry)
    {
      key = paramString;
      size = data.length;
      etag = etag;
      serverDate = serverDate;
      lastModified = lastModified;
      ttl = ttl;
      softTtl = softTtl;
      responseHeaders = responseHeaders;
    }
    
    public static CacheHeader readHeader(InputStream paramInputStream)
      throws IOException
    {
      CacheHeader localCacheHeader = new CacheHeader();
      if (DiskBasedCache.readInt(paramInputStream) != 538247942) {
        throw new IOException();
      }
      key = DiskBasedCache.readString(paramInputStream);
      etag = DiskBasedCache.readString(paramInputStream);
      if (etag.equals("")) {
        etag = null;
      }
      serverDate = DiskBasedCache.readLong(paramInputStream);
      lastModified = DiskBasedCache.readLong(paramInputStream);
      ttl = DiskBasedCache.readLong(paramInputStream);
      softTtl = DiskBasedCache.readLong(paramInputStream);
      responseHeaders = DiskBasedCache.readStringStringMap(paramInputStream);
      return localCacheHeader;
    }
    
    public Cache.Entry toCacheEntry(byte[] paramArrayOfByte)
    {
      Cache.Entry localEntry = new Cache.Entry();
      data = paramArrayOfByte;
      etag = etag;
      serverDate = serverDate;
      lastModified = lastModified;
      ttl = ttl;
      softTtl = softTtl;
      responseHeaders = responseHeaders;
      return localEntry;
    }
    
    public boolean writeHeader(OutputStream paramOutputStream)
    {
      try
      {
        DiskBasedCache.writeInt(paramOutputStream, 538247942);
        DiskBasedCache.writeString(paramOutputStream, key);
        String str;
        if (etag == null) {
          str = "";
        } else {
          str = etag;
        }
        DiskBasedCache.writeString(paramOutputStream, str);
        DiskBasedCache.writeLong(paramOutputStream, serverDate);
        DiskBasedCache.writeLong(paramOutputStream, lastModified);
        DiskBasedCache.writeLong(paramOutputStream, ttl);
        DiskBasedCache.writeLong(paramOutputStream, softTtl);
        DiskBasedCache.writeStringStringMap(responseHeaders, paramOutputStream);
        paramOutputStream.flush();
        return true;
      }
      catch (IOException paramOutputStream)
      {
        VolleyLog.d("%s", new Object[] { paramOutputStream.toString() });
      }
      return false;
    }
  }
  
  private static class CountingInputStream
    extends FilterInputStream
  {
    private int bytesRead = 0;
    
    private CountingInputStream(InputStream paramInputStream)
    {
      super();
    }
    
    public int read()
      throws IOException
    {
      int i = super.read();
      if (i != -1) {
        bytesRead += 1;
      }
      return i;
    }
    
    public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
      throws IOException
    {
      paramInt1 = super.read(paramArrayOfByte, paramInt1, paramInt2);
      if (paramInt1 != -1) {
        bytesRead += paramInt1;
      }
      return paramInt1;
    }
  }
}
