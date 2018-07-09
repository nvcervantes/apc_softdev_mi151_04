package android.support.v4.graphics;

import android.content.Context;
import android.content.res.Resources;
import android.os.Process;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

@RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
public class TypefaceCompatUtil
{
  private static final String CACHE_FILE_PREFIX = ".font";
  private static final String TAG = "TypefaceCompatUtil";
  
  private TypefaceCompatUtil() {}
  
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
  
  @Nullable
  @RequiresApi(19)
  public static ByteBuffer copyToDirectBuffer(Context paramContext, Resources paramResources, int paramInt)
  {
    paramContext = getTempFile(paramContext);
    if (paramContext == null) {
      return null;
    }
    try
    {
      boolean bool = copyToFile(paramContext, paramResources, paramInt);
      if (!bool) {
        return null;
      }
      paramResources = mmap(paramContext);
      return paramResources;
    }
    finally
    {
      paramContext.delete();
    }
  }
  
  /* Error */
  public static boolean copyToFile(File paramFile, Resources paramResources, int paramInt)
  {
    // Byte code:
    //   0: aload_1
    //   1: iload_2
    //   2: invokevirtual 59	android/content/res/Resources:openRawResource	(I)Ljava/io/InputStream;
    //   5: astore_1
    //   6: aload_0
    //   7: aload_1
    //   8: invokestatic 62	android/support/v4/graphics/TypefaceCompatUtil:copyToFile	(Ljava/io/File;Ljava/io/InputStream;)Z
    //   11: istore_3
    //   12: aload_1
    //   13: invokestatic 64	android/support/v4/graphics/TypefaceCompatUtil:closeQuietly	(Ljava/io/Closeable;)V
    //   16: iload_3
    //   17: ireturn
    //   18: astore_0
    //   19: goto +6 -> 25
    //   22: astore_0
    //   23: aconst_null
    //   24: astore_1
    //   25: aload_1
    //   26: invokestatic 64	android/support/v4/graphics/TypefaceCompatUtil:closeQuietly	(Ljava/io/Closeable;)V
    //   29: aload_0
    //   30: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	31	0	paramFile	File
    //   0	31	1	paramResources	Resources
    //   0	31	2	paramInt	int
    //   11	6	3	bool	boolean
    // Exception table:
    //   from	to	target	type
    //   6	12	18	finally
    //   0	6	22	finally
  }
  
  /* Error */
  public static boolean copyToFile(File paramFile, java.io.InputStream paramInputStream)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: aconst_null
    //   4: astore_3
    //   5: new 66	java/io/FileOutputStream
    //   8: dup
    //   9: aload_0
    //   10: iconst_0
    //   11: invokespecial 69	java/io/FileOutputStream:<init>	(Ljava/io/File;Z)V
    //   14: astore_0
    //   15: sipush 1024
    //   18: newarray byte
    //   20: astore_3
    //   21: aload_1
    //   22: aload_3
    //   23: invokevirtual 75	java/io/InputStream:read	([B)I
    //   26: istore_2
    //   27: iload_2
    //   28: iconst_m1
    //   29: if_icmpeq +13 -> 42
    //   32: aload_0
    //   33: aload_3
    //   34: iconst_0
    //   35: iload_2
    //   36: invokevirtual 79	java/io/FileOutputStream:write	([BII)V
    //   39: goto -18 -> 21
    //   42: aload_0
    //   43: invokestatic 64	android/support/v4/graphics/TypefaceCompatUtil:closeQuietly	(Ljava/io/Closeable;)V
    //   46: iconst_1
    //   47: ireturn
    //   48: astore_1
    //   49: aload_0
    //   50: astore_3
    //   51: aload_1
    //   52: astore_0
    //   53: goto +67 -> 120
    //   56: astore_1
    //   57: goto +11 -> 68
    //   60: astore_0
    //   61: goto +59 -> 120
    //   64: astore_1
    //   65: aload 4
    //   67: astore_0
    //   68: aload_0
    //   69: astore_3
    //   70: new 81	java/lang/StringBuilder
    //   73: dup
    //   74: invokespecial 82	java/lang/StringBuilder:<init>	()V
    //   77: astore 4
    //   79: aload_0
    //   80: astore_3
    //   81: aload 4
    //   83: ldc 84
    //   85: invokevirtual 88	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   88: pop
    //   89: aload_0
    //   90: astore_3
    //   91: aload 4
    //   93: aload_1
    //   94: invokevirtual 92	java/io/IOException:getMessage	()Ljava/lang/String;
    //   97: invokevirtual 88	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   100: pop
    //   101: aload_0
    //   102: astore_3
    //   103: ldc 15
    //   105: aload 4
    //   107: invokevirtual 95	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   110: invokestatic 101	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   113: pop
    //   114: aload_0
    //   115: invokestatic 64	android/support/v4/graphics/TypefaceCompatUtil:closeQuietly	(Ljava/io/Closeable;)V
    //   118: iconst_0
    //   119: ireturn
    //   120: aload_3
    //   121: invokestatic 64	android/support/v4/graphics/TypefaceCompatUtil:closeQuietly	(Ljava/io/Closeable;)V
    //   124: aload_0
    //   125: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	126	0	paramFile	File
    //   0	126	1	paramInputStream	java.io.InputStream
    //   26	10	2	i	int
    //   4	117	3	localObject	Object
    //   1	105	4	localStringBuilder	StringBuilder
    // Exception table:
    //   from	to	target	type
    //   15	21	48	finally
    //   21	27	48	finally
    //   32	39	48	finally
    //   15	21	56	java/io/IOException
    //   21	27	56	java/io/IOException
    //   32	39	56	java/io/IOException
    //   5	15	60	finally
    //   70	79	60	finally
    //   81	89	60	finally
    //   91	101	60	finally
    //   103	114	60	finally
    //   5	15	64	java/io/IOException
  }
  
  @Nullable
  public static File getTempFile(Context paramContext)
  {
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append(".font");
    ((StringBuilder)localObject).append(Process.myPid());
    ((StringBuilder)localObject).append("-");
    ((StringBuilder)localObject).append(Process.myTid());
    ((StringBuilder)localObject).append("-");
    localObject = ((StringBuilder)localObject).toString();
    int i = 0;
    while (i < 100)
    {
      File localFile = paramContext.getCacheDir();
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append((String)localObject);
      localStringBuilder.append(i);
      localFile = new File(localFile, localStringBuilder.toString());
      try
      {
        boolean bool = localFile.createNewFile();
        if (bool) {
          return localFile;
        }
      }
      catch (IOException localIOException)
      {
        for (;;) {}
      }
      i += 1;
    }
    return null;
  }
  
  /* Error */
  @Nullable
  @RequiresApi(19)
  public static ByteBuffer mmap(Context paramContext, android.os.CancellationSignal paramCancellationSignal, android.net.Uri paramUri)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 134	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   4: astore_0
    //   5: aload_0
    //   6: aload_2
    //   7: ldc -120
    //   9: aload_1
    //   10: invokevirtual 142	android/content/ContentResolver:openFileDescriptor	(Landroid/net/Uri;Ljava/lang/String;Landroid/os/CancellationSignal;)Landroid/os/ParcelFileDescriptor;
    //   13: astore_2
    //   14: aload_2
    //   15: ifnonnull +13 -> 28
    //   18: aload_2
    //   19: ifnull +7 -> 26
    //   22: aload_2
    //   23: invokevirtual 145	android/os/ParcelFileDescriptor:close	()V
    //   26: aconst_null
    //   27: areturn
    //   28: new 147	java/io/FileInputStream
    //   31: dup
    //   32: aload_2
    //   33: invokevirtual 151	android/os/ParcelFileDescriptor:getFileDescriptor	()Ljava/io/FileDescriptor;
    //   36: invokespecial 154	java/io/FileInputStream:<init>	(Ljava/io/FileDescriptor;)V
    //   39: astore 5
    //   41: aload 5
    //   43: invokevirtual 158	java/io/FileInputStream:getChannel	()Ljava/nio/channels/FileChannel;
    //   46: astore_0
    //   47: aload_0
    //   48: invokevirtual 164	java/nio/channels/FileChannel:size	()J
    //   51: lstore_3
    //   52: aload_0
    //   53: getstatic 170	java/nio/channels/FileChannel$MapMode:READ_ONLY	Ljava/nio/channels/FileChannel$MapMode;
    //   56: lconst_0
    //   57: lload_3
    //   58: invokevirtual 174	java/nio/channels/FileChannel:map	(Ljava/nio/channels/FileChannel$MapMode;JJ)Ljava/nio/MappedByteBuffer;
    //   61: astore_0
    //   62: aload 5
    //   64: ifnull +8 -> 72
    //   67: aload 5
    //   69: invokevirtual 175	java/io/FileInputStream:close	()V
    //   72: aload_2
    //   73: ifnull +7 -> 80
    //   76: aload_2
    //   77: invokevirtual 145	android/os/ParcelFileDescriptor:close	()V
    //   80: aload_0
    //   81: areturn
    //   82: astore_1
    //   83: aconst_null
    //   84: astore_0
    //   85: goto +7 -> 92
    //   88: astore_0
    //   89: aload_0
    //   90: athrow
    //   91: astore_1
    //   92: aload 5
    //   94: ifnull +31 -> 125
    //   97: aload_0
    //   98: ifnull +22 -> 120
    //   101: aload 5
    //   103: invokevirtual 175	java/io/FileInputStream:close	()V
    //   106: goto +19 -> 125
    //   109: astore 5
    //   111: aload_0
    //   112: aload 5
    //   114: invokevirtual 179	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   117: goto +8 -> 125
    //   120: aload 5
    //   122: invokevirtual 175	java/io/FileInputStream:close	()V
    //   125: aload_1
    //   126: athrow
    //   127: astore_0
    //   128: aconst_null
    //   129: astore_1
    //   130: goto +7 -> 137
    //   133: astore_1
    //   134: aload_1
    //   135: athrow
    //   136: astore_0
    //   137: aload_2
    //   138: ifnull +27 -> 165
    //   141: aload_1
    //   142: ifnull +19 -> 161
    //   145: aload_2
    //   146: invokevirtual 145	android/os/ParcelFileDescriptor:close	()V
    //   149: goto +16 -> 165
    //   152: astore_2
    //   153: aload_1
    //   154: aload_2
    //   155: invokevirtual 179	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   158: goto +7 -> 165
    //   161: aload_2
    //   162: invokevirtual 145	android/os/ParcelFileDescriptor:close	()V
    //   165: aload_0
    //   166: athrow
    //   167: astore_0
    //   168: aconst_null
    //   169: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	170	0	paramContext	Context
    //   0	170	1	paramCancellationSignal	android.os.CancellationSignal
    //   0	170	2	paramUri	android.net.Uri
    //   51	7	3	l	long
    //   39	63	5	localFileInputStream	java.io.FileInputStream
    //   109	12	5	localThrowable	Throwable
    // Exception table:
    //   from	to	target	type
    //   41	62	82	finally
    //   41	62	88	java/lang/Throwable
    //   89	91	91	finally
    //   101	106	109	java/lang/Throwable
    //   28	41	127	finally
    //   67	72	127	finally
    //   101	106	127	finally
    //   111	117	127	finally
    //   120	125	127	finally
    //   125	127	127	finally
    //   28	41	133	java/lang/Throwable
    //   67	72	133	java/lang/Throwable
    //   111	117	133	java/lang/Throwable
    //   120	125	133	java/lang/Throwable
    //   125	127	133	java/lang/Throwable
    //   134	136	136	finally
    //   145	149	152	java/lang/Throwable
    //   5	14	167	java/io/IOException
    //   22	26	167	java/io/IOException
    //   76	80	167	java/io/IOException
    //   145	149	167	java/io/IOException
    //   153	158	167	java/io/IOException
    //   161	165	167	java/io/IOException
    //   165	167	167	java/io/IOException
  }
  
  /* Error */
  @Nullable
  @RequiresApi(19)
  private static ByteBuffer mmap(File paramFile)
  {
    // Byte code:
    //   0: new 147	java/io/FileInputStream
    //   3: dup
    //   4: aload_0
    //   5: invokespecial 182	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   8: astore 4
    //   10: aload 4
    //   12: invokevirtual 158	java/io/FileInputStream:getChannel	()Ljava/nio/channels/FileChannel;
    //   15: astore_0
    //   16: aload_0
    //   17: invokevirtual 164	java/nio/channels/FileChannel:size	()J
    //   20: lstore_1
    //   21: aload_0
    //   22: getstatic 170	java/nio/channels/FileChannel$MapMode:READ_ONLY	Ljava/nio/channels/FileChannel$MapMode;
    //   25: lconst_0
    //   26: lload_1
    //   27: invokevirtual 174	java/nio/channels/FileChannel:map	(Ljava/nio/channels/FileChannel$MapMode;JJ)Ljava/nio/MappedByteBuffer;
    //   30: astore_0
    //   31: aload 4
    //   33: ifnull +8 -> 41
    //   36: aload 4
    //   38: invokevirtual 175	java/io/FileInputStream:close	()V
    //   41: aload_0
    //   42: areturn
    //   43: astore_3
    //   44: aconst_null
    //   45: astore_0
    //   46: goto +7 -> 53
    //   49: astore_0
    //   50: aload_0
    //   51: athrow
    //   52: astore_3
    //   53: aload 4
    //   55: ifnull +31 -> 86
    //   58: aload_0
    //   59: ifnull +22 -> 81
    //   62: aload 4
    //   64: invokevirtual 175	java/io/FileInputStream:close	()V
    //   67: goto +19 -> 86
    //   70: astore 4
    //   72: aload_0
    //   73: aload 4
    //   75: invokevirtual 179	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   78: goto +8 -> 86
    //   81: aload 4
    //   83: invokevirtual 175	java/io/FileInputStream:close	()V
    //   86: aload_3
    //   87: athrow
    //   88: astore_0
    //   89: aconst_null
    //   90: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	91	0	paramFile	File
    //   20	7	1	l	long
    //   43	1	3	localObject1	Object
    //   52	35	3	localObject2	Object
    //   8	55	4	localFileInputStream	java.io.FileInputStream
    //   70	12	4	localThrowable	Throwable
    // Exception table:
    //   from	to	target	type
    //   10	31	43	finally
    //   10	31	49	java/lang/Throwable
    //   50	52	52	finally
    //   62	67	70	java/lang/Throwable
    //   0	10	88	java/io/IOException
    //   36	41	88	java/io/IOException
    //   62	67	88	java/io/IOException
    //   72	78	88	java/io/IOException
    //   81	86	88	java/io/IOException
    //   86	88	88	java/io/IOException
  }
}
