package okhttp3.internal.publicsuffix;

import java.net.IDN;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import okhttp3.internal.Util;

public final class PublicSuffixDatabase
{
  private static final String[] EMPTY_RULE = new String[0];
  private static final byte EXCEPTION_MARKER = 33;
  private static final String[] PREVAILING_RULE = { "*" };
  public static final String PUBLIC_SUFFIX_RESOURCE = "publicsuffixes.gz";
  private static final byte[] WILDCARD_LABEL = { 42 };
  private static final PublicSuffixDatabase instance = new PublicSuffixDatabase();
  private final AtomicBoolean listRead = new AtomicBoolean(false);
  private byte[] publicSuffixExceptionListBytes;
  private byte[] publicSuffixListBytes;
  private final CountDownLatch readCompleteLatch = new CountDownLatch(1);
  
  public PublicSuffixDatabase() {}
  
  private static String binarySearchBytes(byte[] paramArrayOfByte, byte[][] paramArrayOfByte1, int paramInt)
  {
    int n = paramArrayOfByte.length;
    int i1 = 0;
    if (i1 < n)
    {
      int i = (i1 + n) / 2;
      while ((i > -1) && (paramArrayOfByte[i] != 10)) {
        i -= 1;
      }
      int i4 = i + 1;
      i = 1;
      int i5;
      for (;;)
      {
        i5 = i4 + i;
        if (paramArrayOfByte[i5] == 10) {
          break;
        }
        i += 1;
      }
      int i6 = i5 - i4;
      int j = paramInt;
      int m = 0;
      i = m;
      int k = i;
      for (;;)
      {
        int i2;
        if (m != 0)
        {
          i2 = 46;
          m = 0;
        }
        else
        {
          i2 = paramArrayOfByte1[j][i] & 0xFF;
        }
        int i7 = i2 - (paramArrayOfByte[(i4 + k)] & 0xFF);
        if (i7 == 0)
        {
          i2 = k + 1;
          int i3 = i + 1;
          if (i2 == i6)
          {
            i = i3;
            k = i2;
          }
          else
          {
            i = i3;
            k = i2;
            if (paramArrayOfByte1[j].length != i3) {
              continue;
            }
            if (j != paramArrayOfByte1.length - 1) {
              break label317;
            }
            k = i2;
            i = i3;
          }
        }
        if (i7 < 0)
        {
          i = i4 - 1;
          label212:
          n = i;
          break;
        }
        if (i7 > 0) {}
        for (i = i5 + 1;; i = i5 + 1)
        {
          i1 = i;
          break;
          k = i6 - k;
          i = paramArrayOfByte1[j].length - i;
          for (;;)
          {
            j += 1;
            if (j >= paramArrayOfByte1.length) {
              break;
            }
            i += paramArrayOfByte1[j].length;
          }
          if (i < k)
          {
            i = i4 - 1;
            break label212;
          }
          if (i <= k) {
            break label301;
          }
        }
        label301:
        return new String(paramArrayOfByte, i4, i6, Util.UTF_8);
        label317:
        j += 1;
        i = -1;
        m = 1;
        k = i2;
      }
    }
    return null;
  }
  
  private String[] findMatchingRule(String[] paramArrayOfString)
  {
    boolean bool = listRead.get();
    int j = 0;
    if ((!bool) && (listRead.compareAndSet(false, true))) {
      readTheListUninterruptibly();
    }
    try
    {
      readCompleteLatch.await();
      try
      {
        if (publicSuffixListBytes == null) {
          throw new IllegalStateException("Unable to load publicsuffixes.gz resource from the classpath.");
        }
        byte[][] arrayOfByte = new byte[paramArrayOfString.length][];
        int i = 0;
        while (i < paramArrayOfString.length)
        {
          arrayOfByte[i] = paramArrayOfString[i].getBytes(Util.UTF_8);
          i += 1;
        }
        i = 0;
        while (i < arrayOfByte.length)
        {
          paramArrayOfString = binarySearchBytes(publicSuffixListBytes, arrayOfByte, i);
          if (paramArrayOfString != null) {
            break label134;
          }
          i += 1;
        }
        paramArrayOfString = null;
        label134:
        if (arrayOfByte.length > 1)
        {
          localObject2 = (byte[][])arrayOfByte.clone();
          i = 0;
          while (i < localObject2.length - 1)
          {
            localObject2[i] = WILDCARD_LABEL;
            localObject1 = binarySearchBytes(publicSuffixListBytes, (byte[][])localObject2, i);
            if (localObject1 != null) {
              break label199;
            }
            i += 1;
          }
        }
        Object localObject1 = null;
        label199:
        if (localObject1 != null)
        {
          i = j;
          while (i < arrayOfByte.length - 1)
          {
            localObject2 = binarySearchBytes(publicSuffixExceptionListBytes, arrayOfByte, i);
            if (localObject2 != null) {
              break label245;
            }
            i += 1;
          }
        }
        Object localObject2 = null;
        label245:
        if (localObject2 != null)
        {
          paramArrayOfString = new StringBuilder();
          paramArrayOfString.append("!");
          paramArrayOfString.append((String)localObject2);
          return paramArrayOfString.toString().split("\\.");
        }
        if ((paramArrayOfString == null) && (localObject1 == null)) {
          return PREVAILING_RULE;
        }
        if (paramArrayOfString != null) {
          paramArrayOfString = paramArrayOfString.split("\\.");
        } else {
          paramArrayOfString = EMPTY_RULE;
        }
        if (localObject1 != null) {
          localObject1 = ((String)localObject1).split("\\.");
        } else {
          localObject1 = EMPTY_RULE;
        }
        if (paramArrayOfString.length > localObject1.length) {
          return paramArrayOfString;
        }
        return localObject1;
      }
      finally {}
    }
    catch (InterruptedException localInterruptedException)
    {
      for (;;) {}
    }
  }
  
  public static PublicSuffixDatabase get()
  {
    return instance;
  }
  
  /* Error */
  private void readTheList()
    throws java.io.IOException
  {
    // Byte code:
    //   0: ldc 2
    //   2: ldc 14
    //   4: invokevirtual 139	java/lang/Class:getResourceAsStream	(Ljava/lang/String;)Ljava/io/InputStream;
    //   7: astore_1
    //   8: aload_1
    //   9: ifnonnull +4 -> 13
    //   12: return
    //   13: new 141	okio/GzipSource
    //   16: dup
    //   17: aload_1
    //   18: invokestatic 147	okio/Okio:source	(Ljava/io/InputStream;)Lokio/Source;
    //   21: invokespecial 150	okio/GzipSource:<init>	(Lokio/Source;)V
    //   24: invokestatic 154	okio/Okio:buffer	(Lokio/Source;)Lokio/BufferedSource;
    //   27: astore_1
    //   28: aload_1
    //   29: invokeinterface 160 1 0
    //   34: newarray byte
    //   36: astore_2
    //   37: aload_1
    //   38: aload_2
    //   39: invokeinterface 164 2 0
    //   44: aload_1
    //   45: invokeinterface 160 1 0
    //   50: newarray byte
    //   52: astore_3
    //   53: aload_1
    //   54: aload_3
    //   55: invokeinterface 164 2 0
    //   60: aload_1
    //   61: invokestatic 168	okhttp3/internal/Util:closeQuietly	(Ljava/io/Closeable;)V
    //   64: aload_0
    //   65: monitorenter
    //   66: aload_0
    //   67: aload_2
    //   68: putfield 88	okhttp3/internal/publicsuffix/PublicSuffixDatabase:publicSuffixListBytes	[B
    //   71: aload_0
    //   72: aload_3
    //   73: putfield 110	okhttp3/internal/publicsuffix/PublicSuffixDatabase:publicSuffixExceptionListBytes	[B
    //   76: aload_0
    //   77: monitorexit
    //   78: aload_0
    //   79: getfield 57	okhttp3/internal/publicsuffix/PublicSuffixDatabase:readCompleteLatch	Ljava/util/concurrent/CountDownLatch;
    //   82: invokevirtual 171	java/util/concurrent/CountDownLatch:countDown	()V
    //   85: return
    //   86: astore_1
    //   87: aload_0
    //   88: monitorexit
    //   89: aload_1
    //   90: athrow
    //   91: astore_2
    //   92: aload_1
    //   93: invokestatic 168	okhttp3/internal/Util:closeQuietly	(Ljava/io/Closeable;)V
    //   96: aload_2
    //   97: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	98	0	this	PublicSuffixDatabase
    //   7	54	1	localObject1	Object
    //   86	7	1	localCloseable	java.io.Closeable
    //   36	32	2	arrayOfByte1	byte[]
    //   91	6	2	localObject2	Object
    //   52	21	3	arrayOfByte2	byte[]
    // Exception table:
    //   from	to	target	type
    //   66	78	86	finally
    //   87	89	86	finally
    //   28	60	91	finally
  }
  
  /* Error */
  private void readTheListUninterruptibly()
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_1
    //   2: aload_0
    //   3: invokespecial 176	okhttp3/internal/publicsuffix/PublicSuffixDatabase:readTheList	()V
    //   6: iload_1
    //   7: ifeq +9 -> 16
    //   10: invokestatic 182	java/lang/Thread:currentThread	()Ljava/lang/Thread;
    //   13: invokevirtual 185	java/lang/Thread:interrupt	()V
    //   16: return
    //   17: astore_2
    //   18: goto +25 -> 43
    //   21: astore_2
    //   22: invokestatic 190	okhttp3/internal/platform/Platform:get	()Lokhttp3/internal/platform/Platform;
    //   25: iconst_5
    //   26: ldc -64
    //   28: aload_2
    //   29: invokevirtual 196	okhttp3/internal/platform/Platform:log	(ILjava/lang/String;Ljava/lang/Throwable;)V
    //   32: iload_1
    //   33: ifeq +9 -> 42
    //   36: invokestatic 182	java/lang/Thread:currentThread	()Ljava/lang/Thread;
    //   39: invokevirtual 185	java/lang/Thread:interrupt	()V
    //   42: return
    //   43: iload_1
    //   44: ifeq +9 -> 53
    //   47: invokestatic 182	java/lang/Thread:currentThread	()Ljava/lang/Thread;
    //   50: invokevirtual 185	java/lang/Thread:interrupt	()V
    //   53: aload_2
    //   54: athrow
    //   55: iconst_1
    //   56: istore_1
    //   57: goto -55 -> 2
    //   60: astore_2
    //   61: goto -6 -> 55
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	64	0	this	PublicSuffixDatabase
    //   1	56	1	i	int
    //   17	1	2	localObject	Object
    //   21	33	2	localIOException	java.io.IOException
    //   60	1	2	localInterruptedIOException	java.io.InterruptedIOException
    // Exception table:
    //   from	to	target	type
    //   2	6	17	finally
    //   22	32	17	finally
    //   2	6	21	java/io/IOException
    //   2	6	60	java/io/InterruptedIOException
  }
  
  public String getEffectiveTldPlusOne(String paramString)
  {
    if (paramString == null) {
      throw new NullPointerException("domain == null");
    }
    Object localObject = IDN.toUnicode(paramString).split("\\.");
    String[] arrayOfString = findMatchingRule((String[])localObject);
    if ((localObject.length == arrayOfString.length) && (arrayOfString[0].charAt(0) != '!')) {
      return null;
    }
    int i;
    if (arrayOfString[0].charAt(0) == '!') {
      i = localObject.length - arrayOfString.length;
    } else {
      i = localObject.length - (arrayOfString.length + 1);
    }
    localObject = new StringBuilder();
    paramString = paramString.split("\\.");
    while (i < paramString.length)
    {
      ((StringBuilder)localObject).append(paramString[i]);
      ((StringBuilder)localObject).append('.');
      i += 1;
    }
    ((StringBuilder)localObject).deleteCharAt(((StringBuilder)localObject).length() - 1);
    return ((StringBuilder)localObject).toString();
  }
  
  void setListBytes(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
  {
    publicSuffixListBytes = paramArrayOfByte1;
    publicSuffixExceptionListBytes = paramArrayOfByte2;
    listRead.set(true);
    readCompleteLatch.countDown();
  }
}
