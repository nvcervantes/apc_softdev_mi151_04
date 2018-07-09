package okhttp3.internal;

import java.io.Closeable;
import java.io.IOException;
import java.net.IDN;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Nullable;
import okhttp3.HttpUrl;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;
import okio.Source;

public final class Util
{
  public static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
  public static final RequestBody EMPTY_REQUEST;
  public static final ResponseBody EMPTY_RESPONSE;
  public static final String[] EMPTY_STRING_ARRAY = new String[0];
  public static final Charset ISO_8859_1;
  public static final Comparator<String> NATURAL_ORDER = new Comparator()
  {
    public int compare(String paramAnonymousString1, String paramAnonymousString2)
    {
      return paramAnonymousString1.compareTo(paramAnonymousString2);
    }
  };
  public static final TimeZone UTC;
  private static final Charset UTF_16_BE;
  private static final ByteString UTF_16_BE_BOM;
  private static final Charset UTF_16_LE;
  private static final ByteString UTF_16_LE_BOM;
  private static final Charset UTF_32_BE;
  private static final ByteString UTF_32_BE_BOM;
  private static final Charset UTF_32_LE;
  private static final ByteString UTF_32_LE_BOM;
  public static final Charset UTF_8;
  private static final ByteString UTF_8_BOM;
  private static final Pattern VERIFY_AS_IP_ADDRESS = Pattern.compile("([0-9a-fA-F]*:[0-9a-fA-F:.]*)|([\\d.]+)");
  
  static
  {
    EMPTY_RESPONSE = ResponseBody.create(null, EMPTY_BYTE_ARRAY);
    EMPTY_REQUEST = RequestBody.create(null, EMPTY_BYTE_ARRAY);
    UTF_8_BOM = ByteString.decodeHex("efbbbf");
    UTF_16_BE_BOM = ByteString.decodeHex("feff");
    UTF_16_LE_BOM = ByteString.decodeHex("fffe");
    UTF_32_BE_BOM = ByteString.decodeHex("0000ffff");
    UTF_32_LE_BOM = ByteString.decodeHex("ffff0000");
    UTF_8 = Charset.forName("UTF-8");
    ISO_8859_1 = Charset.forName("ISO-8859-1");
    UTF_16_BE = Charset.forName("UTF-16BE");
    UTF_16_LE = Charset.forName("UTF-16LE");
    UTF_32_BE = Charset.forName("UTF-32BE");
    UTF_32_LE = Charset.forName("UTF-32LE");
    UTC = TimeZone.getTimeZone("GMT");
  }
  
  private Util() {}
  
  public static AssertionError assertionError(String paramString, Exception paramException)
  {
    return (AssertionError)new AssertionError(paramString).initCause(paramException);
  }
  
  public static Charset bomAwareCharset(BufferedSource paramBufferedSource, Charset paramCharset)
    throws IOException
  {
    if (paramBufferedSource.rangeEquals(0L, UTF_8_BOM))
    {
      paramBufferedSource.skip(UTF_8_BOM.size());
      return UTF_8;
    }
    if (paramBufferedSource.rangeEquals(0L, UTF_16_BE_BOM))
    {
      paramBufferedSource.skip(UTF_16_BE_BOM.size());
      return UTF_16_BE;
    }
    if (paramBufferedSource.rangeEquals(0L, UTF_16_LE_BOM))
    {
      paramBufferedSource.skip(UTF_16_LE_BOM.size());
      return UTF_16_LE;
    }
    if (paramBufferedSource.rangeEquals(0L, UTF_32_BE_BOM))
    {
      paramBufferedSource.skip(UTF_32_BE_BOM.size());
      return UTF_32_BE;
    }
    if (paramBufferedSource.rangeEquals(0L, UTF_32_LE_BOM))
    {
      paramBufferedSource.skip(UTF_32_LE_BOM.size());
      return UTF_32_LE;
    }
    return paramCharset;
  }
  
  public static String canonicalizeHost(String paramString)
  {
    if (paramString.contains(":"))
    {
      if ((paramString.startsWith("[")) && (paramString.endsWith("]"))) {
        localObject = decodeIpv6(paramString, 1, paramString.length() - 1);
      } else {
        localObject = decodeIpv6(paramString, 0, paramString.length());
      }
      if (localObject == null) {
        return null;
      }
      Object localObject = ((InetAddress)localObject).getAddress();
      if (localObject.length == 16) {
        return inet6AddressToAscii((byte[])localObject);
      }
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("Invalid IPv6 address: '");
      ((StringBuilder)localObject).append(paramString);
      ((StringBuilder)localObject).append("'");
      throw new AssertionError(((StringBuilder)localObject).toString());
    }
    try
    {
      paramString = IDN.toASCII(paramString).toLowerCase(Locale.US);
      if (paramString.isEmpty()) {
        return null;
      }
      boolean bool = containsInvalidHostnameAsciiCodes(paramString);
      if (bool) {
        return null;
      }
      return paramString;
    }
    catch (IllegalArgumentException paramString) {}
    return null;
  }
  
  public static int checkDuration(String paramString, long paramLong, TimeUnit paramTimeUnit)
  {
    if (paramLong < 0L)
    {
      paramTimeUnit = new StringBuilder();
      paramTimeUnit.append(paramString);
      paramTimeUnit.append(" < 0");
      throw new IllegalArgumentException(paramTimeUnit.toString());
    }
    if (paramTimeUnit == null) {
      throw new NullPointerException("unit == null");
    }
    long l = paramTimeUnit.toMillis(paramLong);
    if (l > 2147483647L)
    {
      paramTimeUnit = new StringBuilder();
      paramTimeUnit.append(paramString);
      paramTimeUnit.append(" too large.");
      throw new IllegalArgumentException(paramTimeUnit.toString());
    }
    if ((l == 0L) && (paramLong > 0L))
    {
      paramTimeUnit = new StringBuilder();
      paramTimeUnit.append(paramString);
      paramTimeUnit.append(" too small.");
      throw new IllegalArgumentException(paramTimeUnit.toString());
    }
    return (int)l;
  }
  
  public static void checkOffsetAndCount(long paramLong1, long paramLong2, long paramLong3)
  {
    if (((paramLong2 | paramLong3) >= 0L) && (paramLong2 <= paramLong1) && (paramLong1 - paramLong2 >= paramLong3)) {
      return;
    }
    throw new ArrayIndexOutOfBoundsException();
  }
  
  public static void closeQuietly(Closeable paramCloseable)
  {
    if (paramCloseable != null) {}
    try
    {
      paramCloseable.close();
      return;
    }
    catch (RuntimeException paramCloseable)
    {
      throw paramCloseable;
      return;
    }
    catch (Exception paramCloseable) {}
  }
  
  public static void closeQuietly(ServerSocket paramServerSocket)
  {
    if (paramServerSocket != null) {}
    try
    {
      paramServerSocket.close();
      return;
    }
    catch (RuntimeException paramServerSocket)
    {
      throw paramServerSocket;
      return;
    }
    catch (Exception paramServerSocket) {}
  }
  
  public static void closeQuietly(Socket paramSocket)
  {
    if (paramSocket != null) {}
    try
    {
      paramSocket.close();
      return;
    }
    catch (RuntimeException paramSocket)
    {
      throw paramSocket;
    }
    catch (AssertionError paramSocket)
    {
      if (!isAndroidGetsocknameError(paramSocket)) {
        throw paramSocket;
      }
      return;
    }
    catch (Exception paramSocket) {}
  }
  
  public static String[] concat(String[] paramArrayOfString, String paramString)
  {
    String[] arrayOfString = new String[paramArrayOfString.length + 1];
    System.arraycopy(paramArrayOfString, 0, arrayOfString, 0, paramArrayOfString.length);
    arrayOfString[(arrayOfString.length - 1)] = paramString;
    return arrayOfString;
  }
  
  private static boolean containsInvalidHostnameAsciiCodes(String paramString)
  {
    int i = 0;
    while (i < paramString.length())
    {
      int j = paramString.charAt(i);
      if (j > 31)
      {
        if (j >= 127) {
          return true;
        }
        if (" #%/:?@[\\]".indexOf(j) != -1) {
          return true;
        }
        i += 1;
      }
      else
      {
        return true;
      }
    }
    return false;
  }
  
  public static int decodeHexDigit(char paramChar)
  {
    if ((paramChar >= '0') && (paramChar <= '9')) {
      return paramChar - '0';
    }
    if ((paramChar >= 'a') && (paramChar <= 'f')) {
      return paramChar - 'a' + 10;
    }
    if ((paramChar >= 'A') && (paramChar <= 'F')) {
      return paramChar - 'A' + 10;
    }
    return -1;
  }
  
  private static boolean decodeIpv4Suffix(String paramString, int paramInt1, int paramInt2, byte[] paramArrayOfByte, int paramInt3)
  {
    int j = paramInt3;
    int i = paramInt1;
    while (i < paramInt2)
    {
      if (j == paramArrayOfByte.length) {
        return false;
      }
      paramInt1 = i;
      if (j != paramInt3)
      {
        if (paramString.charAt(i) != '.') {
          return false;
        }
        paramInt1 = i + 1;
      }
      i = paramInt1;
      int k = 0;
      while (i < paramInt2)
      {
        int m = paramString.charAt(i);
        if ((m < 48) || (m > 57)) {
          break;
        }
        if ((k == 0) && (paramInt1 != i)) {
          return false;
        }
        k = k * 10 + m - 48;
        if (k > 255) {
          return false;
        }
        i += 1;
      }
      if (i - paramInt1 == 0) {
        return false;
      }
      paramArrayOfByte[j] = ((byte)k);
      j += 1;
    }
    return j == paramInt3 + 4;
  }
  
  @Nullable
  private static InetAddress decodeIpv6(String paramString, int paramInt1, int paramInt2)
  {
    byte[] arrayOfByte = new byte[16];
    int j = -1;
    int m = j;
    int i = 0;
    int k;
    int n;
    for (;;)
    {
      k = i;
      n = j;
      if (paramInt1 >= paramInt2) {
        break label288;
      }
      if (i == arrayOfByte.length) {
        return null;
      }
      k = paramInt1 + 2;
      if ((k <= paramInt2) && (paramString.regionMatches(paramInt1, "::", 0, 2)))
      {
        if (j != -1) {
          return null;
        }
        paramInt1 = i + 2;
        if (k == paramInt2)
        {
          n = paramInt1;
          k = paramInt1;
          break label288;
        }
        j = paramInt1;
        i = paramInt1;
        paramInt1 = k;
      }
      else
      {
        k = paramInt1;
        if (i != 0) {
          if (paramString.regionMatches(paramInt1, ":", 0, 1))
          {
            k = paramInt1 + 1;
          }
          else
          {
            if (paramString.regionMatches(paramInt1, ".", 0, 1))
            {
              if (!decodeIpv4Suffix(paramString, m, paramInt2, arrayOfByte, i - 2)) {
                return null;
              }
              k = i + 2;
              n = j;
              break label288;
            }
            return null;
          }
        }
        paramInt1 = k;
      }
      m = 0;
      k = paramInt1;
      while (k < paramInt2)
      {
        n = decodeHexDigit(paramString.charAt(k));
        if (n == -1) {
          break;
        }
        m = (m << 4) + n;
        k += 1;
      }
      n = k - paramInt1;
      if (n == 0) {
        break;
      }
      if (n > 4) {
        return null;
      }
      n = i + 1;
      arrayOfByte[i] = ((byte)(m >>> 8 & 0xFF));
      i = n + 1;
      arrayOfByte[n] = ((byte)(m & 0xFF));
      m = paramInt1;
      paramInt1 = k;
    }
    return null;
    label288:
    if (k != arrayOfByte.length)
    {
      if (n == -1) {
        return null;
      }
      paramInt1 = arrayOfByte.length;
      paramInt2 = k - n;
      System.arraycopy(arrayOfByte, n, arrayOfByte, paramInt1 - paramInt2, paramInt2);
      Arrays.fill(arrayOfByte, n, arrayOfByte.length - k + n, (byte)0);
    }
    try
    {
      paramString = InetAddress.getByAddress(arrayOfByte);
      return paramString;
    }
    catch (UnknownHostException paramString)
    {
      for (;;) {}
    }
    throw new AssertionError();
  }
  
  public static int delimiterOffset(String paramString, int paramInt1, int paramInt2, char paramChar)
  {
    while (paramInt1 < paramInt2)
    {
      if (paramString.charAt(paramInt1) == paramChar) {
        return paramInt1;
      }
      paramInt1 += 1;
    }
    return paramInt2;
  }
  
  public static int delimiterOffset(String paramString1, int paramInt1, int paramInt2, String paramString2)
  {
    while (paramInt1 < paramInt2)
    {
      if (paramString2.indexOf(paramString1.charAt(paramInt1)) != -1) {
        return paramInt1;
      }
      paramInt1 += 1;
    }
    return paramInt2;
  }
  
  public static boolean discard(Source paramSource, int paramInt, TimeUnit paramTimeUnit)
  {
    try
    {
      boolean bool = skipAll(paramSource, paramInt, paramTimeUnit);
      return bool;
    }
    catch (IOException paramSource)
    {
      for (;;) {}
    }
    return false;
  }
  
  public static boolean equal(Object paramObject1, Object paramObject2)
  {
    return (paramObject1 == paramObject2) || ((paramObject1 != null) && (paramObject1.equals(paramObject2)));
  }
  
  public static String format(String paramString, Object... paramVarArgs)
  {
    return String.format(Locale.US, paramString, paramVarArgs);
  }
  
  public static String hostHeader(HttpUrl paramHttpUrl, boolean paramBoolean)
  {
    Object localObject1;
    if (paramHttpUrl.host().contains(":"))
    {
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append("[");
      ((StringBuilder)localObject1).append(paramHttpUrl.host());
      ((StringBuilder)localObject1).append("]");
      localObject1 = ((StringBuilder)localObject1).toString();
    }
    else
    {
      localObject1 = paramHttpUrl.host();
    }
    Object localObject2;
    if (!paramBoolean)
    {
      localObject2 = localObject1;
      if (paramHttpUrl.port() == HttpUrl.defaultPort(paramHttpUrl.scheme())) {}
    }
    else
    {
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append((String)localObject1);
      ((StringBuilder)localObject2).append(":");
      ((StringBuilder)localObject2).append(paramHttpUrl.port());
      localObject2 = ((StringBuilder)localObject2).toString();
    }
    return localObject2;
  }
  
  public static <T> List<T> immutableList(List<T> paramList)
  {
    return Collections.unmodifiableList(new ArrayList(paramList));
  }
  
  public static <T> List<T> immutableList(T... paramVarArgs)
  {
    return Collections.unmodifiableList(Arrays.asList((Object[])paramVarArgs.clone()));
  }
  
  public static int indexOf(Comparator<String> paramComparator, String[] paramArrayOfString, String paramString)
  {
    int i = 0;
    int j = paramArrayOfString.length;
    while (i < j)
    {
      if (paramComparator.compare(paramArrayOfString[i], paramString) == 0) {
        return i;
      }
      i += 1;
    }
    return -1;
  }
  
  public static int indexOfControlOrNonAscii(String paramString)
  {
    int j = paramString.length();
    int i = 0;
    while (i < j)
    {
      int k = paramString.charAt(i);
      if (k > 31)
      {
        if (k >= 127) {
          return i;
        }
        i += 1;
      }
      else
      {
        return i;
      }
    }
    return -1;
  }
  
  private static String inet6AddressToAscii(byte[] paramArrayOfByte)
  {
    int i2 = 0;
    int k = 0;
    int j = -1;
    int i = k;
    int m;
    while (i < paramArrayOfByte.length)
    {
      m = i;
      while ((m < 16) && (paramArrayOfByte[m] == 0) && (paramArrayOfByte[(m + 1)] == 0)) {
        m += 2;
      }
      int i3 = m - i;
      int i1 = k;
      int n = j;
      if (i3 > k)
      {
        i1 = k;
        n = j;
        if (i3 >= 4)
        {
          i1 = i3;
          n = i;
        }
      }
      i = m + 2;
      k = i1;
      j = n;
    }
    Buffer localBuffer = new Buffer();
    i = i2;
    while (i < paramArrayOfByte.length) {
      if (i == j)
      {
        localBuffer.writeByte(58);
        m = i + k;
        i = m;
        if (m == 16)
        {
          localBuffer.writeByte(58);
          i = m;
        }
      }
      else
      {
        if (i > 0) {
          localBuffer.writeByte(58);
        }
        localBuffer.writeHexadecimalUnsignedLong((paramArrayOfByte[i] & 0xFF) << 8 | paramArrayOfByte[(i + 1)] & 0xFF);
        i += 2;
      }
    }
    return localBuffer.readUtf8();
  }
  
  public static String[] intersect(Comparator<? super String> paramComparator, String[] paramArrayOfString1, String[] paramArrayOfString2)
  {
    ArrayList localArrayList = new ArrayList();
    int k = paramArrayOfString1.length;
    int i = 0;
    while (i < k)
    {
      String str = paramArrayOfString1[i];
      int m = paramArrayOfString2.length;
      int j = 0;
      while (j < m)
      {
        if (paramComparator.compare(str, paramArrayOfString2[j]) == 0)
        {
          localArrayList.add(str);
          break;
        }
        j += 1;
      }
      i += 1;
    }
    return (String[])localArrayList.toArray(new String[localArrayList.size()]);
  }
  
  public static boolean isAndroidGetsocknameError(AssertionError paramAssertionError)
  {
    return (paramAssertionError.getCause() != null) && (paramAssertionError.getMessage() != null) && (paramAssertionError.getMessage().contains("getsockname failed"));
  }
  
  public static boolean nonEmptyIntersection(Comparator<String> paramComparator, String[] paramArrayOfString1, String[] paramArrayOfString2)
  {
    if ((paramArrayOfString1 != null) && (paramArrayOfString2 != null) && (paramArrayOfString1.length != 0))
    {
      if (paramArrayOfString2.length == 0) {
        return false;
      }
      int k = paramArrayOfString1.length;
      int i = 0;
      while (i < k)
      {
        String str = paramArrayOfString1[i];
        int m = paramArrayOfString2.length;
        int j = 0;
        while (j < m)
        {
          if (paramComparator.compare(str, paramArrayOfString2[j]) == 0) {
            return true;
          }
          j += 1;
        }
        i += 1;
      }
      return false;
    }
    return false;
  }
  
  /* Error */
  public static boolean skipAll(Source paramSource, int paramInt, TimeUnit paramTimeUnit)
    throws IOException
  {
    // Byte code:
    //   0: invokestatic 468	java/lang/System:nanoTime	()J
    //   3: lstore 5
    //   5: aload_0
    //   6: invokeinterface 474 1 0
    //   11: invokevirtual 479	okio/Timeout:hasDeadline	()Z
    //   14: ifeq +19 -> 33
    //   17: aload_0
    //   18: invokeinterface 474 1 0
    //   23: invokevirtual 482	okio/Timeout:deadlineNanoTime	()J
    //   26: lload 5
    //   28: lsub
    //   29: lstore_3
    //   30: goto +7 -> 37
    //   33: ldc2_w 483
    //   36: lstore_3
    //   37: aload_0
    //   38: invokeinterface 474 1 0
    //   43: lload 5
    //   45: lload_3
    //   46: aload_2
    //   47: iload_1
    //   48: i2l
    //   49: invokevirtual 487	java/util/concurrent/TimeUnit:toNanos	(J)J
    //   52: invokestatic 493	java/lang/Math:min	(JJ)J
    //   55: ladd
    //   56: invokevirtual 496	okio/Timeout:deadlineNanoTime	(J)Lokio/Timeout;
    //   59: pop
    //   60: new 423	okio/Buffer
    //   63: dup
    //   64: invokespecial 424	okio/Buffer:<init>	()V
    //   67: astore_2
    //   68: aload_0
    //   69: aload_2
    //   70: ldc2_w 497
    //   73: invokeinterface 502 4 0
    //   78: ldc2_w 503
    //   81: lcmp
    //   82: ifeq +10 -> 92
    //   85: aload_2
    //   86: invokevirtual 507	okio/Buffer:clear	()V
    //   89: goto -21 -> 68
    //   92: lload_3
    //   93: ldc2_w 483
    //   96: lcmp
    //   97: ifne +15 -> 112
    //   100: aload_0
    //   101: invokeinterface 474 1 0
    //   106: invokevirtual 510	okio/Timeout:clearDeadline	()Lokio/Timeout;
    //   109: pop
    //   110: iconst_1
    //   111: ireturn
    //   112: aload_0
    //   113: invokeinterface 474 1 0
    //   118: lload 5
    //   120: lload_3
    //   121: ladd
    //   122: invokevirtual 496	okio/Timeout:deadlineNanoTime	(J)Lokio/Timeout;
    //   125: pop
    //   126: iconst_1
    //   127: ireturn
    //   128: astore_2
    //   129: lload_3
    //   130: ldc2_w 483
    //   133: lcmp
    //   134: ifne +16 -> 150
    //   137: aload_0
    //   138: invokeinterface 474 1 0
    //   143: invokevirtual 510	okio/Timeout:clearDeadline	()Lokio/Timeout;
    //   146: pop
    //   147: goto +17 -> 164
    //   150: aload_0
    //   151: invokeinterface 474 1 0
    //   156: lload 5
    //   158: lload_3
    //   159: ladd
    //   160: invokevirtual 496	okio/Timeout:deadlineNanoTime	(J)Lokio/Timeout;
    //   163: pop
    //   164: aload_2
    //   165: athrow
    //   166: lload_3
    //   167: ldc2_w 483
    //   170: lcmp
    //   171: ifne +15 -> 186
    //   174: aload_0
    //   175: invokeinterface 474 1 0
    //   180: invokevirtual 510	okio/Timeout:clearDeadline	()Lokio/Timeout;
    //   183: pop
    //   184: iconst_0
    //   185: ireturn
    //   186: aload_0
    //   187: invokeinterface 474 1 0
    //   192: lload 5
    //   194: lload_3
    //   195: ladd
    //   196: invokevirtual 496	okio/Timeout:deadlineNanoTime	(J)Lokio/Timeout;
    //   199: pop
    //   200: iconst_0
    //   201: ireturn
    //   202: astore_2
    //   203: goto -37 -> 166
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	206	0	paramSource	Source
    //   0	206	1	paramInt	int
    //   0	206	2	paramTimeUnit	TimeUnit
    //   29	166	3	l1	long
    //   3	190	5	l2	long
    // Exception table:
    //   from	to	target	type
    //   60	68	128	finally
    //   68	89	128	finally
    //   60	68	202	java/io/InterruptedIOException
    //   68	89	202	java/io/InterruptedIOException
  }
  
  public static int skipLeadingAsciiWhitespace(String paramString, int paramInt1, int paramInt2)
  {
    while (paramInt1 < paramInt2)
    {
      switch (paramString.charAt(paramInt1))
      {
      default: 
        return paramInt1;
      }
      paramInt1 += 1;
    }
    return paramInt2;
  }
  
  public static int skipTrailingAsciiWhitespace(String paramString, int paramInt1, int paramInt2)
  {
    paramInt2 -= 1;
    while (paramInt2 >= paramInt1)
    {
      switch (paramString.charAt(paramInt2))
      {
      default: 
        return paramInt2 + 1;
      }
      paramInt2 -= 1;
    }
    return paramInt1;
  }
  
  public static ThreadFactory threadFactory(String paramString, final boolean paramBoolean)
  {
    new ThreadFactory()
    {
      public Thread newThread(Runnable paramAnonymousRunnable)
      {
        paramAnonymousRunnable = new Thread(paramAnonymousRunnable, Util.this);
        paramAnonymousRunnable.setDaemon(paramBoolean);
        return paramAnonymousRunnable;
      }
    };
  }
  
  public static String toHumanReadableAscii(String paramString)
  {
    int m = paramString.length();
    int i = 0;
    while (i < m)
    {
      int j = paramString.codePointAt(i);
      if ((j > 31) && (j < 127))
      {
        i += Character.charCount(j);
      }
      else
      {
        Buffer localBuffer = new Buffer();
        localBuffer.writeUtf8(paramString, 0, i);
        while (i < m)
        {
          int k = paramString.codePointAt(i);
          if ((k > 31) && (k < 127)) {
            j = k;
          } else {
            j = 63;
          }
          localBuffer.writeUtf8CodePoint(j);
          i += Character.charCount(k);
        }
        return localBuffer.readUtf8();
      }
    }
    return paramString;
  }
  
  public static String trimSubstring(String paramString, int paramInt1, int paramInt2)
  {
    paramInt1 = skipLeadingAsciiWhitespace(paramString, paramInt1, paramInt2);
    return paramString.substring(paramInt1, skipTrailingAsciiWhitespace(paramString, paramInt1, paramInt2));
  }
  
  public static boolean verifyAsIpAddress(String paramString)
  {
    return VERIFY_AS_IP_ADDRESS.matcher(paramString).matches();
  }
}
