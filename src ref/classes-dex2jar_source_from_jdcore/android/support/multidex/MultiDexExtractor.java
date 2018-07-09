package android.support.multidex;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;
import android.util.Log;
import java.io.Closeable;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

final class MultiDexExtractor
  implements Closeable
{
  private static final int BUFFER_SIZE = 16384;
  private static final String DEX_PREFIX = "classes";
  static final String DEX_SUFFIX = ".dex";
  private static final String EXTRACTED_NAME_EXT = ".classes";
  static final String EXTRACTED_SUFFIX = ".zip";
  private static final String KEY_CRC = "crc";
  private static final String KEY_DEX_CRC = "dex.crc.";
  private static final String KEY_DEX_NUMBER = "dex.number";
  private static final String KEY_DEX_TIME = "dex.time.";
  private static final String KEY_TIME_STAMP = "timestamp";
  private static final String LOCK_FILENAME = "MultiDex.lock";
  private static final int MAX_EXTRACT_ATTEMPTS = 3;
  private static final long NO_VALUE = -1L;
  private static final String PREFS_FILE = "multidex.version";
  private static final String TAG = "MultiDex";
  private final FileLock cacheLock;
  private final File dexDir;
  private final FileChannel lockChannel;
  private final RandomAccessFile lockRaf;
  private final File sourceApk;
  private final long sourceCrc;
  
  /* Error */
  MultiDexExtractor(File paramFile1, File paramFile2)
    throws IOException
  {
    // Byte code:
    //   0: aload_0
    //   1: invokespecial 78	java/lang/Object:<init>	()V
    //   4: new 80	java/lang/StringBuilder
    //   7: dup
    //   8: invokespecial 81	java/lang/StringBuilder:<init>	()V
    //   11: astore_3
    //   12: aload_3
    //   13: ldc 83
    //   15: invokevirtual 87	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   18: pop
    //   19: aload_3
    //   20: aload_1
    //   21: invokevirtual 93	java/io/File:getPath	()Ljava/lang/String;
    //   24: invokevirtual 87	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   27: pop
    //   28: aload_3
    //   29: ldc 95
    //   31: invokevirtual 87	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   34: pop
    //   35: aload_3
    //   36: aload_2
    //   37: invokevirtual 93	java/io/File:getPath	()Ljava/lang/String;
    //   40: invokevirtual 87	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   43: pop
    //   44: aload_3
    //   45: ldc 97
    //   47: invokevirtual 87	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   50: pop
    //   51: ldc 57
    //   53: aload_3
    //   54: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   57: invokestatic 106	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   60: pop
    //   61: aload_0
    //   62: aload_1
    //   63: putfield 108	android/support/multidex/MultiDexExtractor:sourceApk	Ljava/io/File;
    //   66: aload_0
    //   67: aload_2
    //   68: putfield 110	android/support/multidex/MultiDexExtractor:dexDir	Ljava/io/File;
    //   71: aload_0
    //   72: aload_1
    //   73: invokestatic 114	android/support/multidex/MultiDexExtractor:getZipCrc	(Ljava/io/File;)J
    //   76: putfield 116	android/support/multidex/MultiDexExtractor:sourceCrc	J
    //   79: new 89	java/io/File
    //   82: dup
    //   83: aload_2
    //   84: ldc 45
    //   86: invokespecial 119	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   89: astore_1
    //   90: aload_0
    //   91: new 121	java/io/RandomAccessFile
    //   94: dup
    //   95: aload_1
    //   96: ldc 123
    //   98: invokespecial 124	java/io/RandomAccessFile:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   101: putfield 126	android/support/multidex/MultiDexExtractor:lockRaf	Ljava/io/RandomAccessFile;
    //   104: aload_0
    //   105: aload_0
    //   106: getfield 126	android/support/multidex/MultiDexExtractor:lockRaf	Ljava/io/RandomAccessFile;
    //   109: invokevirtual 130	java/io/RandomAccessFile:getChannel	()Ljava/nio/channels/FileChannel;
    //   112: putfield 132	android/support/multidex/MultiDexExtractor:lockChannel	Ljava/nio/channels/FileChannel;
    //   115: new 80	java/lang/StringBuilder
    //   118: dup
    //   119: invokespecial 81	java/lang/StringBuilder:<init>	()V
    //   122: astore_2
    //   123: aload_2
    //   124: ldc -122
    //   126: invokevirtual 87	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   129: pop
    //   130: aload_2
    //   131: aload_1
    //   132: invokevirtual 93	java/io/File:getPath	()Ljava/lang/String;
    //   135: invokevirtual 87	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   138: pop
    //   139: ldc 57
    //   141: aload_2
    //   142: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   145: invokestatic 106	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   148: pop
    //   149: aload_0
    //   150: aload_0
    //   151: getfield 132	android/support/multidex/MultiDexExtractor:lockChannel	Ljava/nio/channels/FileChannel;
    //   154: invokevirtual 140	java/nio/channels/FileChannel:lock	()Ljava/nio/channels/FileLock;
    //   157: putfield 142	android/support/multidex/MultiDexExtractor:cacheLock	Ljava/nio/channels/FileLock;
    //   160: new 80	java/lang/StringBuilder
    //   163: dup
    //   164: invokespecial 81	java/lang/StringBuilder:<init>	()V
    //   167: astore_2
    //   168: aload_2
    //   169: aload_1
    //   170: invokevirtual 93	java/io/File:getPath	()Ljava/lang/String;
    //   173: invokevirtual 87	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   176: pop
    //   177: aload_2
    //   178: ldc -112
    //   180: invokevirtual 87	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   183: pop
    //   184: ldc 57
    //   186: aload_2
    //   187: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   190: invokestatic 106	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   193: pop
    //   194: return
    //   195: astore_1
    //   196: aload_0
    //   197: getfield 132	android/support/multidex/MultiDexExtractor:lockChannel	Ljava/nio/channels/FileChannel;
    //   200: invokestatic 148	android/support/multidex/MultiDexExtractor:closeQuietly	(Ljava/io/Closeable;)V
    //   203: aload_1
    //   204: athrow
    //   205: astore_1
    //   206: aload_0
    //   207: getfield 126	android/support/multidex/MultiDexExtractor:lockRaf	Ljava/io/RandomAccessFile;
    //   210: invokestatic 148	android/support/multidex/MultiDexExtractor:closeQuietly	(Ljava/io/Closeable;)V
    //   213: aload_1
    //   214: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	215	0	this	MultiDexExtractor
    //   0	215	1	paramFile1	File
    //   0	215	2	paramFile2	File
    //   11	43	3	localStringBuilder	StringBuilder
    // Exception table:
    //   from	to	target	type
    //   115	160	195	java/io/IOException
    //   115	160	195	java/lang/RuntimeException
    //   115	160	195	java/lang/Error
    //   104	115	205	java/io/IOException
    //   104	115	205	java/lang/RuntimeException
    //   104	115	205	java/lang/Error
    //   160	194	205	java/io/IOException
    //   160	194	205	java/lang/RuntimeException
    //   160	194	205	java/lang/Error
    //   196	205	205	java/io/IOException
    //   196	205	205	java/lang/RuntimeException
    //   196	205	205	java/lang/Error
  }
  
  private void clearDexDir()
  {
    Object localObject1 = dexDir.listFiles(new FileFilter()
    {
      public boolean accept(File paramAnonymousFile)
      {
        return paramAnonymousFile.getName().equals("MultiDex.lock") ^ true;
      }
    });
    if (localObject1 == null)
    {
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append("Failed to list secondary dex dir content (");
      ((StringBuilder)localObject1).append(dexDir.getPath());
      ((StringBuilder)localObject1).append(").");
      Log.w("MultiDex", ((StringBuilder)localObject1).toString());
      return;
    }
    int j = localObject1.length;
    int i = 0;
    while (i < j)
    {
      Object localObject2 = localObject1[i];
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Trying to delete old file ");
      localStringBuilder.append(localObject2.getPath());
      localStringBuilder.append(" of size ");
      localStringBuilder.append(localObject2.length());
      Log.i("MultiDex", localStringBuilder.toString());
      if (!localObject2.delete())
      {
        localStringBuilder = new StringBuilder();
        localStringBuilder.append("Failed to delete old file ");
        localStringBuilder.append(localObject2.getPath());
        Log.w("MultiDex", localStringBuilder.toString());
      }
      else
      {
        localStringBuilder = new StringBuilder();
        localStringBuilder.append("Deleted old file ");
        localStringBuilder.append(localObject2.getPath());
        Log.i("MultiDex", localStringBuilder.toString());
      }
      i += 1;
    }
  }
  
  private static void closeQuietly(Closeable paramCloseable)
  {
    try
    {
      paramCloseable.close();
      return;
    }
    catch (IOException paramCloseable)
    {
      Log.w("MultiDex", "Failed to close resource", paramCloseable);
    }
  }
  
  /* Error */
  private static void extract(ZipFile paramZipFile, ZipEntry paramZipEntry, File paramFile, String paramString)
    throws IOException, java.io.FileNotFoundException
  {
    // Byte code:
    //   0: aload_0
    //   1: aload_1
    //   2: invokevirtual 202	java/util/zip/ZipFile:getInputStream	(Ljava/util/zip/ZipEntry;)Ljava/io/InputStream;
    //   5: astore_0
    //   6: new 80	java/lang/StringBuilder
    //   9: dup
    //   10: invokespecial 81	java/lang/StringBuilder:<init>	()V
    //   13: astore 5
    //   15: aload 5
    //   17: ldc -52
    //   19: invokevirtual 87	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   22: pop
    //   23: aload 5
    //   25: aload_3
    //   26: invokevirtual 87	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   29: pop
    //   30: aload 5
    //   32: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   35: ldc 27
    //   37: aload_2
    //   38: invokevirtual 208	java/io/File:getParentFile	()Ljava/io/File;
    //   41: invokestatic 212	java/io/File:createTempFile	(Ljava/lang/String;Ljava/lang/String;Ljava/io/File;)Ljava/io/File;
    //   44: astore_3
    //   45: new 80	java/lang/StringBuilder
    //   48: dup
    //   49: invokespecial 81	java/lang/StringBuilder:<init>	()V
    //   52: astore 5
    //   54: aload 5
    //   56: ldc -42
    //   58: invokevirtual 87	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   61: pop
    //   62: aload 5
    //   64: aload_3
    //   65: invokevirtual 93	java/io/File:getPath	()Ljava/lang/String;
    //   68: invokevirtual 87	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   71: pop
    //   72: ldc 57
    //   74: aload 5
    //   76: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   79: invokestatic 106	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   82: pop
    //   83: new 216	java/util/zip/ZipOutputStream
    //   86: dup
    //   87: new 218	java/io/BufferedOutputStream
    //   90: dup
    //   91: new 220	java/io/FileOutputStream
    //   94: dup
    //   95: aload_3
    //   96: invokespecial 223	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   99: invokespecial 226	java/io/BufferedOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   102: invokespecial 227	java/util/zip/ZipOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   105: astore 5
    //   107: new 229	java/util/zip/ZipEntry
    //   110: dup
    //   111: ldc -25
    //   113: invokespecial 234	java/util/zip/ZipEntry:<init>	(Ljava/lang/String;)V
    //   116: astore 6
    //   118: aload 6
    //   120: aload_1
    //   121: invokevirtual 237	java/util/zip/ZipEntry:getTime	()J
    //   124: invokevirtual 241	java/util/zip/ZipEntry:setTime	(J)V
    //   127: aload 5
    //   129: aload 6
    //   131: invokevirtual 245	java/util/zip/ZipOutputStream:putNextEntry	(Ljava/util/zip/ZipEntry;)V
    //   134: sipush 16384
    //   137: newarray byte
    //   139: astore_1
    //   140: aload_0
    //   141: aload_1
    //   142: invokevirtual 251	java/io/InputStream:read	([B)I
    //   145: istore 4
    //   147: iload 4
    //   149: iconst_m1
    //   150: if_icmpeq +22 -> 172
    //   153: aload 5
    //   155: aload_1
    //   156: iconst_0
    //   157: iload 4
    //   159: invokevirtual 255	java/util/zip/ZipOutputStream:write	([BII)V
    //   162: aload_0
    //   163: aload_1
    //   164: invokevirtual 251	java/io/InputStream:read	([B)I
    //   167: istore 4
    //   169: goto -22 -> 147
    //   172: aload 5
    //   174: invokevirtual 258	java/util/zip/ZipOutputStream:closeEntry	()V
    //   177: aload 5
    //   179: invokevirtual 259	java/util/zip/ZipOutputStream:close	()V
    //   182: aload_3
    //   183: invokevirtual 262	java/io/File:setReadOnly	()Z
    //   186: ifne +65 -> 251
    //   189: new 80	java/lang/StringBuilder
    //   192: dup
    //   193: invokespecial 81	java/lang/StringBuilder:<init>	()V
    //   196: astore_1
    //   197: aload_1
    //   198: ldc_w 264
    //   201: invokevirtual 87	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   204: pop
    //   205: aload_1
    //   206: aload_3
    //   207: invokevirtual 267	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   210: invokevirtual 87	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   213: pop
    //   214: aload_1
    //   215: ldc_w 269
    //   218: invokevirtual 87	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   221: pop
    //   222: aload_1
    //   223: aload_2
    //   224: invokevirtual 267	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   227: invokevirtual 87	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   230: pop
    //   231: aload_1
    //   232: ldc_w 271
    //   235: invokevirtual 87	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   238: pop
    //   239: new 71	java/io/IOException
    //   242: dup
    //   243: aload_1
    //   244: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   247: invokespecial 272	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   250: athrow
    //   251: new 80	java/lang/StringBuilder
    //   254: dup
    //   255: invokespecial 81	java/lang/StringBuilder:<init>	()V
    //   258: astore_1
    //   259: aload_1
    //   260: ldc_w 274
    //   263: invokevirtual 87	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   266: pop
    //   267: aload_1
    //   268: aload_2
    //   269: invokevirtual 93	java/io/File:getPath	()Ljava/lang/String;
    //   272: invokevirtual 87	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   275: pop
    //   276: ldc 57
    //   278: aload_1
    //   279: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   282: invokestatic 106	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   285: pop
    //   286: aload_3
    //   287: aload_2
    //   288: invokevirtual 278	java/io/File:renameTo	(Ljava/io/File;)Z
    //   291: ifne +65 -> 356
    //   294: new 80	java/lang/StringBuilder
    //   297: dup
    //   298: invokespecial 81	java/lang/StringBuilder:<init>	()V
    //   301: astore_1
    //   302: aload_1
    //   303: ldc_w 280
    //   306: invokevirtual 87	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   309: pop
    //   310: aload_1
    //   311: aload_3
    //   312: invokevirtual 267	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   315: invokevirtual 87	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   318: pop
    //   319: aload_1
    //   320: ldc_w 282
    //   323: invokevirtual 87	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   326: pop
    //   327: aload_1
    //   328: aload_2
    //   329: invokevirtual 267	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   332: invokevirtual 87	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   335: pop
    //   336: aload_1
    //   337: ldc_w 284
    //   340: invokevirtual 87	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   343: pop
    //   344: new 71	java/io/IOException
    //   347: dup
    //   348: aload_1
    //   349: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   352: invokespecial 272	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   355: athrow
    //   356: aload_0
    //   357: invokestatic 148	android/support/multidex/MultiDexExtractor:closeQuietly	(Ljava/io/Closeable;)V
    //   360: aload_3
    //   361: invokevirtual 180	java/io/File:delete	()Z
    //   364: pop
    //   365: return
    //   366: astore_1
    //   367: aload 5
    //   369: invokevirtual 259	java/util/zip/ZipOutputStream:close	()V
    //   372: aload_1
    //   373: athrow
    //   374: astore_1
    //   375: aload_0
    //   376: invokestatic 148	android/support/multidex/MultiDexExtractor:closeQuietly	(Ljava/io/Closeable;)V
    //   379: aload_3
    //   380: invokevirtual 180	java/io/File:delete	()Z
    //   383: pop
    //   384: aload_1
    //   385: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	386	0	paramZipFile	ZipFile
    //   0	386	1	paramZipEntry	ZipEntry
    //   0	386	2	paramFile	File
    //   0	386	3	paramString	String
    //   145	23	4	i	int
    //   13	355	5	localObject	Object
    //   116	14	6	localZipEntry	ZipEntry
    // Exception table:
    //   from	to	target	type
    //   107	147	366	finally
    //   153	169	366	finally
    //   172	177	366	finally
    //   83	107	374	finally
    //   177	251	374	finally
    //   251	356	374	finally
    //   367	374	374	finally
  }
  
  private static SharedPreferences getMultiDexPreferences(Context paramContext)
  {
    int i;
    if (Build.VERSION.SDK_INT < 11) {
      i = 0;
    } else {
      i = 4;
    }
    return paramContext.getSharedPreferences("multidex.version", i);
  }
  
  private static long getTimeStamp(File paramFile)
  {
    long l2 = paramFile.lastModified();
    long l1 = l2;
    if (l2 == -1L) {
      l1 = l2 - 1L;
    }
    return l1;
  }
  
  private static long getZipCrc(File paramFile)
    throws IOException
  {
    long l2 = ZipUtil.getZipCrc(paramFile);
    long l1 = l2;
    if (l2 == -1L) {
      l1 = l2 - 1L;
    }
    return l1;
  }
  
  private static boolean isModified(Context paramContext, File paramFile, long paramLong, String paramString)
  {
    paramContext = getMultiDexPreferences(paramContext);
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramString);
    localStringBuilder.append("timestamp");
    if (paramContext.getLong(localStringBuilder.toString(), -1L) == getTimeStamp(paramFile))
    {
      paramFile = new StringBuilder();
      paramFile.append(paramString);
      paramFile.append("crc");
      if (paramContext.getLong(paramFile.toString(), -1L) == paramLong) {
        return false;
      }
    }
    return true;
  }
  
  private List<ExtractedDex> loadExistingExtractions(Context paramContext, String paramString)
    throws IOException
  {
    Log.i("MultiDex", "loading existing secondary dex files");
    Object localObject1 = new StringBuilder();
    ((StringBuilder)localObject1).append(sourceApk.getName());
    ((StringBuilder)localObject1).append(".classes");
    localObject1 = ((StringBuilder)localObject1).toString();
    paramContext = getMultiDexPreferences(paramContext);
    Object localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append(paramString);
    ((StringBuilder)localObject2).append("dex.number");
    int j = paramContext.getInt(((StringBuilder)localObject2).toString(), 1);
    localObject2 = new ArrayList(j - 1);
    int i = 2;
    while (i <= j)
    {
      Object localObject3 = new StringBuilder();
      ((StringBuilder)localObject3).append((String)localObject1);
      ((StringBuilder)localObject3).append(i);
      ((StringBuilder)localObject3).append(".zip");
      localObject3 = ((StringBuilder)localObject3).toString();
      localObject3 = new ExtractedDex(dexDir, (String)localObject3);
      if (((ExtractedDex)localObject3).isFile())
      {
        crc = getZipCrc((File)localObject3);
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append(paramString);
        localStringBuilder.append("dex.crc.");
        localStringBuilder.append(i);
        long l1 = paramContext.getLong(localStringBuilder.toString(), -1L);
        localStringBuilder = new StringBuilder();
        localStringBuilder.append(paramString);
        localStringBuilder.append("dex.time.");
        localStringBuilder.append(i);
        long l2 = paramContext.getLong(localStringBuilder.toString(), -1L);
        long l3 = ((ExtractedDex)localObject3).lastModified();
        if ((l2 == l3) && (l1 == crc))
        {
          ((List)localObject2).add(localObject3);
          i += 1;
        }
        else
        {
          paramContext = new StringBuilder();
          paramContext.append("Invalid extracted dex: ");
          paramContext.append(localObject3);
          paramContext.append(" (key \"");
          paramContext.append(paramString);
          paramContext.append("\"), expected modification time: ");
          paramContext.append(l2);
          paramContext.append(", modification time: ");
          paramContext.append(l3);
          paramContext.append(", expected crc: ");
          paramContext.append(l1);
          paramContext.append(", file crc: ");
          paramContext.append(crc);
          throw new IOException(paramContext.toString());
        }
      }
      else
      {
        paramContext = new StringBuilder();
        paramContext.append("Missing extracted secondary dex file '");
        paramContext.append(((ExtractedDex)localObject3).getPath());
        paramContext.append("'");
        throw new IOException(paramContext.toString());
      }
    }
    return localObject2;
  }
  
  private List<ExtractedDex> performExtractions()
    throws IOException
  {
    Object localObject1 = new StringBuilder();
    ((StringBuilder)localObject1).append(sourceApk.getName());
    ((StringBuilder)localObject1).append(".classes");
    String str2 = ((StringBuilder)localObject1).toString();
    clearDexDir();
    ArrayList localArrayList = new ArrayList();
    ZipFile localZipFile = new ZipFile(sourceApk);
    int j = 2;
    for (;;)
    {
      try
      {
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append("classes");
        ((StringBuilder)localObject1).append(2);
        ((StringBuilder)localObject1).append(".dex");
        localObject1 = localZipFile.getEntry(((StringBuilder)localObject1).toString());
        if (localObject1 != null)
        {
          Object localObject3 = new StringBuilder();
          ((StringBuilder)localObject3).append(str2);
          ((StringBuilder)localObject3).append(j);
          ((StringBuilder)localObject3).append(".zip");
          localObject3 = ((StringBuilder)localObject3).toString();
          ExtractedDex localExtractedDex = new ExtractedDex(dexDir, (String)localObject3);
          localArrayList.add(localExtractedDex);
          localObject3 = new StringBuilder();
          ((StringBuilder)localObject3).append("Extraction is needed for file ");
          ((StringBuilder)localObject3).append(localExtractedDex);
          Log.i("MultiDex", ((StringBuilder)localObject3).toString());
          int m = 0;
          int k = m;
          if ((m < 3) && (k == 0))
          {
            int n = m + 1;
            extract(localZipFile, (ZipEntry)localObject1, localExtractedDex, str2);
            int i;
            try
            {
              crc = getZipCrc(localExtractedDex);
              i = 1;
            }
            catch (IOException localIOException2)
            {
              localStringBuilder = new StringBuilder();
              localStringBuilder.append("Failed to read crc from ");
              localStringBuilder.append(localExtractedDex.getAbsolutePath());
              Log.w("MultiDex", localStringBuilder.toString(), localIOException2);
              i = 0;
            }
            StringBuilder localStringBuilder = new StringBuilder();
            localStringBuilder.append("Extraction ");
            if (i != 0)
            {
              Object localObject4 = "succeeded";
              localStringBuilder.append((String)localObject4);
              localStringBuilder.append(" '");
              localStringBuilder.append(localExtractedDex.getAbsolutePath());
              localStringBuilder.append("': length ");
              localStringBuilder.append(localExtractedDex.length());
              localStringBuilder.append(" - crc: ");
              localStringBuilder.append(crc);
              Log.i("MultiDex", localStringBuilder.toString());
              m = n;
              k = i;
              if (i != 0) {
                continue;
              }
              localExtractedDex.delete();
              m = n;
              k = i;
              if (!localExtractedDex.exists()) {
                continue;
              }
              localObject4 = new StringBuilder();
              ((StringBuilder)localObject4).append("Failed to delete corrupted secondary dex '");
              ((StringBuilder)localObject4).append(localExtractedDex.getPath());
              ((StringBuilder)localObject4).append("'");
              Log.w("MultiDex", ((StringBuilder)localObject4).toString());
              m = n;
              k = i;
            }
          }
          else
          {
            if (k == 0)
            {
              localObject1 = new StringBuilder();
              ((StringBuilder)localObject1).append("Could not create zip file ");
              ((StringBuilder)localObject1).append(localExtractedDex.getAbsolutePath());
              ((StringBuilder)localObject1).append(" for secondary dex (");
              ((StringBuilder)localObject1).append(j);
              ((StringBuilder)localObject1).append(")");
              throw new IOException(((StringBuilder)localObject1).toString());
            }
            j += 1;
            localObject1 = new StringBuilder();
            ((StringBuilder)localObject1).append("classes");
            ((StringBuilder)localObject1).append(j);
            ((StringBuilder)localObject1).append(".dex");
            localObject1 = localZipFile.getEntry(((StringBuilder)localObject1).toString());
          }
        }
        else
        {
          try
          {
            localZipFile.close();
            return localArrayList;
          }
          catch (IOException localIOException1)
          {
            Log.w("MultiDex", "Failed to close resource", localIOException1);
            return localArrayList;
          }
        }
        String str1 = "failed";
      }
      finally
      {
        try
        {
          localZipFile.close();
        }
        catch (IOException localIOException3)
        {
          Log.w("MultiDex", "Failed to close resource", localIOException3);
        }
      }
    }
  }
  
  private static void putStoredApkInfo(Context paramContext, String paramString, long paramLong1, long paramLong2, List<ExtractedDex> paramList)
  {
    paramContext = getMultiDexPreferences(paramContext).edit();
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append(paramString);
    ((StringBuilder)localObject).append("timestamp");
    paramContext.putLong(((StringBuilder)localObject).toString(), paramLong1);
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append(paramString);
    ((StringBuilder)localObject).append("crc");
    paramContext.putLong(((StringBuilder)localObject).toString(), paramLong2);
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append(paramString);
    ((StringBuilder)localObject).append("dex.number");
    paramContext.putInt(((StringBuilder)localObject).toString(), paramList.size() + 1);
    paramList = paramList.iterator();
    int i = 2;
    while (paramList.hasNext())
    {
      localObject = (ExtractedDex)paramList.next();
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(paramString);
      localStringBuilder.append("dex.crc.");
      localStringBuilder.append(i);
      paramContext.putLong(localStringBuilder.toString(), crc);
      localStringBuilder = new StringBuilder();
      localStringBuilder.append(paramString);
      localStringBuilder.append("dex.time.");
      localStringBuilder.append(i);
      paramContext.putLong(localStringBuilder.toString(), ((ExtractedDex)localObject).lastModified());
      i += 1;
    }
    paramContext.commit();
  }
  
  public void close()
    throws IOException
  {
    cacheLock.release();
    lockChannel.close();
    lockRaf.close();
  }
  
  List<? extends File> load(Context paramContext, String paramString, boolean paramBoolean)
    throws IOException
  {
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append("MultiDexExtractor.load(");
    ((StringBuilder)localObject).append(sourceApk.getPath());
    ((StringBuilder)localObject).append(", ");
    ((StringBuilder)localObject).append(paramBoolean);
    ((StringBuilder)localObject).append(", ");
    ((StringBuilder)localObject).append(paramString);
    ((StringBuilder)localObject).append(")");
    Log.i("MultiDex", ((StringBuilder)localObject).toString());
    if (!cacheLock.isValid()) {
      throw new IllegalStateException("MultiDexExtractor was closed");
    }
    List localList;
    if ((!paramBoolean) && (!isModified(paramContext, sourceApk, sourceCrc, paramString)))
    {
      try
      {
        localObject = loadExistingExtractions(paramContext, paramString);
        paramContext = (Context)localObject;
      }
      catch (IOException localIOException)
      {
        Log.w("MultiDex", "Failed to reload existing extracted secondary dex files, falling back to fresh extraction", localIOException);
        localList = performExtractions();
        putStoredApkInfo(paramContext, paramString, getTimeStamp(sourceApk), sourceCrc, localList);
        paramContext = localList;
      }
    }
    else
    {
      if (paramBoolean) {
        Log.i("MultiDex", "Forced extraction must be performed.");
      } else {
        Log.i("MultiDex", "Detected that extraction must be performed.");
      }
      localList = performExtractions();
      putStoredApkInfo(paramContext, paramString, getTimeStamp(sourceApk), sourceCrc, localList);
      paramContext = localList;
    }
    paramString = new StringBuilder();
    paramString.append("load found ");
    paramString.append(paramContext.size());
    paramString.append(" secondary dex files");
    Log.i("MultiDex", paramString.toString());
    return paramContext;
  }
  
  private static class ExtractedDex
    extends File
  {
    public long crc = -1L;
    
    public ExtractedDex(File paramFile, String paramString)
    {
      super(paramString);
    }
  }
}
