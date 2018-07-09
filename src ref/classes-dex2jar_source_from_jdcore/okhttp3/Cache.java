package okhttp3;

import java.io.Closeable;
import java.io.File;
import java.io.Flushable;
import java.io.IOException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import javax.annotation.Nullable;
import okhttp3.internal.Util;
import okhttp3.internal.cache.CacheRequest;
import okhttp3.internal.cache.CacheStrategy;
import okhttp3.internal.cache.DiskLruCache;
import okhttp3.internal.cache.DiskLruCache.Editor;
import okhttp3.internal.cache.DiskLruCache.Snapshot;
import okhttp3.internal.cache.InternalCache;
import okhttp3.internal.http.HttpHeaders;
import okhttp3.internal.http.HttpMethod;
import okhttp3.internal.http.StatusLine;
import okhttp3.internal.io.FileSystem;
import okhttp3.internal.platform.Platform;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ByteString;
import okio.ForwardingSink;
import okio.ForwardingSource;
import okio.Okio;
import okio.Sink;
import okio.Source;

public final class Cache
  implements Closeable, Flushable
{
  private static final int ENTRY_BODY = 1;
  private static final int ENTRY_COUNT = 2;
  private static final int ENTRY_METADATA = 0;
  private static final int VERSION = 201105;
  final DiskLruCache cache;
  private int hitCount;
  final InternalCache internalCache = new InternalCache()
  {
    public Response get(Request paramAnonymousRequest)
      throws IOException
    {
      return Cache.this.get(paramAnonymousRequest);
    }
    
    public CacheRequest put(Response paramAnonymousResponse)
      throws IOException
    {
      return Cache.this.put(paramAnonymousResponse);
    }
    
    public void remove(Request paramAnonymousRequest)
      throws IOException
    {
      Cache.this.remove(paramAnonymousRequest);
    }
    
    public void trackConditionalCacheHit()
    {
      Cache.this.trackConditionalCacheHit();
    }
    
    public void trackResponse(CacheStrategy paramAnonymousCacheStrategy)
    {
      Cache.this.trackResponse(paramAnonymousCacheStrategy);
    }
    
    public void update(Response paramAnonymousResponse1, Response paramAnonymousResponse2)
    {
      Cache.this.update(paramAnonymousResponse1, paramAnonymousResponse2);
    }
  };
  private int networkCount;
  private int requestCount;
  int writeAbortCount;
  int writeSuccessCount;
  
  public Cache(File paramFile, long paramLong)
  {
    this(paramFile, paramLong, FileSystem.SYSTEM);
  }
  
  Cache(File paramFile, long paramLong, FileSystem paramFileSystem)
  {
    cache = DiskLruCache.create(paramFileSystem, paramFile, 201105, 2, paramLong);
  }
  
  private void abortQuietly(@Nullable DiskLruCache.Editor paramEditor)
  {
    if (paramEditor != null) {}
    try
    {
      paramEditor.abort();
      return;
    }
    catch (IOException paramEditor) {}
  }
  
  public static String key(HttpUrl paramHttpUrl)
  {
    return ByteString.encodeUtf8(paramHttpUrl.toString()).md5().hex();
  }
  
  static int readInt(BufferedSource paramBufferedSource)
    throws IOException
  {
    long l;
    try
    {
      l = paramBufferedSource.readDecimalLong();
      paramBufferedSource = paramBufferedSource.readUtf8LineStrict();
      if ((l >= 0L) && (l <= 2147483647L)) {
        if (paramBufferedSource.isEmpty()) {
          break label97;
        }
      }
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("expected an int but was \"");
      localStringBuilder.append(l);
      localStringBuilder.append(paramBufferedSource);
      localStringBuilder.append("\"");
      throw new IOException(localStringBuilder.toString());
    }
    catch (NumberFormatException paramBufferedSource)
    {
      throw new IOException(paramBufferedSource.getMessage());
    }
    label97:
    return (int)l;
  }
  
  public void close()
    throws IOException
  {
    cache.close();
  }
  
  public void delete()
    throws IOException
  {
    cache.delete();
  }
  
  public File directory()
  {
    return cache.getDirectory();
  }
  
  public void evictAll()
    throws IOException
  {
    cache.evictAll();
  }
  
  public void flush()
    throws IOException
  {
    cache.flush();
  }
  
  @Nullable
  Response get(Request paramRequest)
  {
    Object localObject = key(paramRequest.url());
    for (;;)
    {
      try
      {
        localObject = cache.get((String)localObject);
        if (localObject == null) {
          return null;
        }
      }
      catch (IOException paramRequest)
      {
        Entry localEntry;
        return null;
      }
      try
      {
        localEntry = new Entry(((DiskLruCache.Snapshot)localObject).getSource(0));
        localObject = localEntry.response((DiskLruCache.Snapshot)localObject);
        if (!localEntry.matches(paramRequest, (Response)localObject))
        {
          Util.closeQuietly(((Response)localObject).body());
          return null;
        }
        return localObject;
      }
      catch (IOException paramRequest) {}
    }
    Util.closeQuietly((Closeable)localObject);
    return null;
  }
  
  public int hitCount()
  {
    try
    {
      int i = hitCount;
      return i;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  public void initialize()
    throws IOException
  {
    cache.initialize();
  }
  
  public boolean isClosed()
  {
    return cache.isClosed();
  }
  
  public long maxSize()
  {
    return cache.getMaxSize();
  }
  
  public int networkCount()
  {
    try
    {
      int i = networkCount;
      return i;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  @Nullable
  CacheRequest put(Response paramResponse)
  {
    Object localObject = paramResponse.request().method();
    if (HttpMethod.invalidatesCache(paramResponse.request().method())) {}
    try
    {
      remove(paramResponse.request());
      return null;
    }
    catch (IOException paramResponse)
    {
      return null;
    }
    if (!((String)localObject).equals("GET")) {
      return null;
    }
    if (HttpHeaders.hasVaryAll(paramResponse)) {
      return null;
    }
    localObject = new Entry(paramResponse);
    try
    {
      paramResponse = cache.edit(key(paramResponse.request().url()));
      if (paramResponse == null) {
        return null;
      }
    }
    catch (IOException paramResponse)
    {
      for (;;) {}
    }
    try
    {
      ((Entry)localObject).writeTo(paramResponse);
      localObject = new CacheRequestImpl(paramResponse);
      return localObject;
    }
    catch (IOException localIOException)
    {
      break label103;
    }
    paramResponse = null;
    label103:
    abortQuietly(paramResponse);
    return null;
  }
  
  void remove(Request paramRequest)
    throws IOException
  {
    cache.remove(key(paramRequest.url()));
  }
  
  public int requestCount()
  {
    try
    {
      int i = requestCount;
      return i;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  public long size()
    throws IOException
  {
    return cache.size();
  }
  
  void trackConditionalCacheHit()
  {
    try
    {
      hitCount += 1;
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  void trackResponse(CacheStrategy paramCacheStrategy)
  {
    try
    {
      requestCount += 1;
      if (networkRequest != null) {
        networkCount += 1;
      } else if (cacheResponse != null) {
        hitCount += 1;
      }
      return;
    }
    finally {}
  }
  
  void update(Response paramResponse1, Response paramResponse2)
  {
    paramResponse2 = new Entry(paramResponse2);
    paramResponse1 = bodysnapshot;
    try
    {
      paramResponse1 = paramResponse1.edit();
      if (paramResponse1 == null) {}
    }
    catch (IOException paramResponse1)
    {
      label41:
      for (;;) {}
    }
    try
    {
      paramResponse2.writeTo(paramResponse1);
      paramResponse1.commit();
      return;
    }
    catch (IOException paramResponse2)
    {
      break label41;
    }
    paramResponse1 = null;
    abortQuietly(paramResponse1);
  }
  
  public Iterator<String> urls()
    throws IOException
  {
    new Iterator()
    {
      boolean canRemove;
      final Iterator<DiskLruCache.Snapshot> delegate = cache.snapshots();
      @Nullable
      String nextUrl;
      
      /* Error */
      public boolean hasNext()
      {
        // Byte code:
        //   0: aload_0
        //   1: getfield 48	okhttp3/Cache$2:nextUrl	Ljava/lang/String;
        //   4: ifnull +5 -> 9
        //   7: iconst_1
        //   8: ireturn
        //   9: aload_0
        //   10: iconst_0
        //   11: putfield 50	okhttp3/Cache$2:canRemove	Z
        //   14: aload_0
        //   15: getfield 42	okhttp3/Cache$2:delegate	Ljava/util/Iterator;
        //   18: invokeinterface 52 1 0
        //   23: ifeq +53 -> 76
        //   26: aload_0
        //   27: getfield 42	okhttp3/Cache$2:delegate	Ljava/util/Iterator;
        //   30: invokeinterface 56 1 0
        //   35: checkcast 58	okhttp3/internal/cache/DiskLruCache$Snapshot
        //   38: astore_1
        //   39: aload_0
        //   40: aload_1
        //   41: iconst_0
        //   42: invokevirtual 62	okhttp3/internal/cache/DiskLruCache$Snapshot:getSource	(I)Lokio/Source;
        //   45: invokestatic 68	okio/Okio:buffer	(Lokio/Source;)Lokio/BufferedSource;
        //   48: invokeinterface 74 1 0
        //   53: putfield 48	okhttp3/Cache$2:nextUrl	Ljava/lang/String;
        //   56: aload_1
        //   57: invokevirtual 77	okhttp3/internal/cache/DiskLruCache$Snapshot:close	()V
        //   60: iconst_1
        //   61: ireturn
        //   62: astore_2
        //   63: aload_1
        //   64: invokevirtual 77	okhttp3/internal/cache/DiskLruCache$Snapshot:close	()V
        //   67: aload_2
        //   68: athrow
        //   69: aload_1
        //   70: invokevirtual 77	okhttp3/internal/cache/DiskLruCache$Snapshot:close	()V
        //   73: goto -59 -> 14
        //   76: iconst_0
        //   77: ireturn
        //   78: astore_2
        //   79: goto -10 -> 69
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	82	0	this	2
        //   38	32	1	localSnapshot	DiskLruCache.Snapshot
        //   62	6	2	localObject	Object
        //   78	1	2	localIOException	IOException
        // Exception table:
        //   from	to	target	type
        //   39	56	62	finally
        //   39	56	78	java/io/IOException
      }
      
      public String next()
      {
        if (!hasNext()) {
          throw new NoSuchElementException();
        }
        String str = nextUrl;
        nextUrl = null;
        canRemove = true;
        return str;
      }
      
      public void remove()
      {
        if (!canRemove) {
          throw new IllegalStateException("remove() before next()");
        }
        delegate.remove();
      }
    };
  }
  
  public int writeAbortCount()
  {
    try
    {
      int i = writeAbortCount;
      return i;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  public int writeSuccessCount()
  {
    try
    {
      int i = writeSuccessCount;
      return i;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  private final class CacheRequestImpl
    implements CacheRequest
  {
    private Sink body;
    private Sink cacheOut;
    boolean done;
    private final DiskLruCache.Editor editor;
    
    CacheRequestImpl(final DiskLruCache.Editor paramEditor)
    {
      editor = paramEditor;
      cacheOut = paramEditor.newSink(1);
      body = new ForwardingSink(cacheOut)
      {
        public void close()
          throws IOException
        {
          synchronized (Cache.this)
          {
            if (done) {
              return;
            }
            done = true;
            Cache localCache2 = Cache.this;
            writeSuccessCount += 1;
            super.close();
            paramEditor.commit();
            return;
          }
        }
      };
    }
    
    public void abort()
    {
      synchronized (Cache.this)
      {
        if (done) {
          return;
        }
        done = true;
        Cache localCache2 = Cache.this;
        writeAbortCount += 1;
        Util.closeQuietly(cacheOut);
      }
    }
    
    public Sink body()
    {
      return body;
    }
  }
  
  private static class CacheResponseBody
    extends ResponseBody
  {
    private final BufferedSource bodySource;
    @Nullable
    private final String contentLength;
    @Nullable
    private final String contentType;
    final DiskLruCache.Snapshot snapshot;
    
    CacheResponseBody(final DiskLruCache.Snapshot paramSnapshot, String paramString1, String paramString2)
    {
      snapshot = paramSnapshot;
      contentType = paramString1;
      contentLength = paramString2;
      bodySource = Okio.buffer(new ForwardingSource(paramSnapshot.getSource(1))
      {
        public void close()
          throws IOException
        {
          paramSnapshot.close();
          super.close();
        }
      });
    }
    
    public long contentLength()
    {
      long l = -1L;
      try
      {
        if (contentLength != null) {
          l = Long.parseLong(contentLength);
        }
        return l;
      }
      catch (NumberFormatException localNumberFormatException) {}
      return -1L;
    }
    
    public MediaType contentType()
    {
      if (contentType != null) {
        return MediaType.parse(contentType);
      }
      return null;
    }
    
    public BufferedSource source()
    {
      return bodySource;
    }
  }
  
  private static final class Entry
  {
    private static final String RECEIVED_MILLIS;
    private static final String SENT_MILLIS;
    private final int code;
    @Nullable
    private final Handshake handshake;
    private final String message;
    private final Protocol protocol;
    private final long receivedResponseMillis;
    private final String requestMethod;
    private final Headers responseHeaders;
    private final long sentRequestMillis;
    private final String url;
    private final Headers varyHeaders;
    
    static
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(Platform.get().getPrefix());
      localStringBuilder.append("-Sent-Millis");
      SENT_MILLIS = localStringBuilder.toString();
      localStringBuilder = new StringBuilder();
      localStringBuilder.append(Platform.get().getPrefix());
      localStringBuilder.append("-Received-Millis");
      RECEIVED_MILLIS = localStringBuilder.toString();
    }
    
    Entry(Response paramResponse)
    {
      url = paramResponse.request().url().toString();
      varyHeaders = HttpHeaders.varyHeaders(paramResponse);
      requestMethod = paramResponse.request().method();
      protocol = paramResponse.protocol();
      code = paramResponse.code();
      message = paramResponse.message();
      responseHeaders = paramResponse.headers();
      handshake = paramResponse.handshake();
      sentRequestMillis = paramResponse.sentRequestAtMillis();
      receivedResponseMillis = paramResponse.receivedResponseAtMillis();
    }
    
    Entry(Source paramSource)
      throws IOException
    {
      for (;;)
      {
        try
        {
          Object localObject1 = Okio.buffer(paramSource);
          url = ((BufferedSource)localObject1).readUtf8LineStrict();
          requestMethod = ((BufferedSource)localObject1).readUtf8LineStrict();
          Object localObject3 = new Headers.Builder();
          int k = Cache.readInt((BufferedSource)localObject1);
          int j = 0;
          int i = 0;
          if (i < k)
          {
            ((Headers.Builder)localObject3).addLenient(((BufferedSource)localObject1).readUtf8LineStrict());
            i += 1;
            continue;
          }
          varyHeaders = ((Headers.Builder)localObject3).build();
          localObject3 = StatusLine.parse(((BufferedSource)localObject1).readUtf8LineStrict());
          protocol = protocol;
          code = code;
          message = message;
          localObject3 = new Headers.Builder();
          k = Cache.readInt((BufferedSource)localObject1);
          i = j;
          if (i < k)
          {
            ((Headers.Builder)localObject3).addLenient(((BufferedSource)localObject1).readUtf8LineStrict());
            i += 1;
            continue;
          }
          Object localObject4 = ((Headers.Builder)localObject3).get(SENT_MILLIS);
          Object localObject5 = ((Headers.Builder)localObject3).get(RECEIVED_MILLIS);
          ((Headers.Builder)localObject3).removeAll(SENT_MILLIS);
          ((Headers.Builder)localObject3).removeAll(RECEIVED_MILLIS);
          long l2 = 0L;
          if (localObject4 != null)
          {
            l1 = Long.parseLong((String)localObject4);
            sentRequestMillis = l1;
            l1 = l2;
            if (localObject5 != null) {
              l1 = Long.parseLong((String)localObject5);
            }
            receivedResponseMillis = l1;
            responseHeaders = ((Headers.Builder)localObject3).build();
            if (isHttps())
            {
              localObject3 = ((BufferedSource)localObject1).readUtf8LineStrict();
              if (((String)localObject3).length() > 0)
              {
                localObject1 = new StringBuilder();
                ((StringBuilder)localObject1).append("expected \"\" but was \"");
                ((StringBuilder)localObject1).append((String)localObject3);
                ((StringBuilder)localObject1).append("\"");
                throw new IOException(((StringBuilder)localObject1).toString());
              }
              localObject3 = CipherSuite.forJavaName(((BufferedSource)localObject1).readUtf8LineStrict());
              localObject4 = readCertificateList((BufferedSource)localObject1);
              localObject5 = readCertificateList((BufferedSource)localObject1);
              if (!((BufferedSource)localObject1).exhausted()) {
                localObject1 = TlsVersion.forJavaName(((BufferedSource)localObject1).readUtf8LineStrict());
              } else {
                localObject1 = TlsVersion.SSL_3_0;
              }
              handshake = Handshake.get((TlsVersion)localObject1, (CipherSuite)localObject3, (List)localObject4, (List)localObject5);
            }
            else
            {
              handshake = null;
            }
            return;
          }
        }
        finally
        {
          paramSource.close();
        }
        long l1 = 0L;
      }
    }
    
    private boolean isHttps()
    {
      return url.startsWith("https://");
    }
    
    private List<Certificate> readCertificateList(BufferedSource paramBufferedSource)
      throws IOException
    {
      int j = Cache.readInt(paramBufferedSource);
      if (j == -1) {
        return Collections.emptyList();
      }
      try
      {
        CertificateFactory localCertificateFactory = CertificateFactory.getInstance("X.509");
        ArrayList localArrayList = new ArrayList(j);
        int i = 0;
        while (i < j)
        {
          String str = paramBufferedSource.readUtf8LineStrict();
          Buffer localBuffer = new Buffer();
          localBuffer.write(ByteString.decodeBase64(str));
          localArrayList.add(localCertificateFactory.generateCertificate(localBuffer.inputStream()));
          i += 1;
        }
        return localArrayList;
      }
      catch (CertificateException paramBufferedSource)
      {
        throw new IOException(paramBufferedSource.getMessage());
      }
    }
    
    private void writeCertList(BufferedSink paramBufferedSink, List<Certificate> paramList)
      throws IOException
    {
      try
      {
        paramBufferedSink.writeDecimalLong(paramList.size()).writeByte(10);
        int i = 0;
        int j = paramList.size();
        while (i < j)
        {
          paramBufferedSink.writeUtf8(ByteString.of(((Certificate)paramList.get(i)).getEncoded()).base64()).writeByte(10);
          i += 1;
        }
        return;
      }
      catch (CertificateEncodingException paramBufferedSink)
      {
        throw new IOException(paramBufferedSink.getMessage());
      }
    }
    
    public boolean matches(Request paramRequest, Response paramResponse)
    {
      return (url.equals(paramRequest.url().toString())) && (requestMethod.equals(paramRequest.method())) && (HttpHeaders.varyMatches(paramResponse, varyHeaders, paramRequest));
    }
    
    public Response response(DiskLruCache.Snapshot paramSnapshot)
    {
      String str1 = responseHeaders.get("Content-Type");
      String str2 = responseHeaders.get("Content-Length");
      Request localRequest = new Request.Builder().url(url).method(requestMethod, null).headers(varyHeaders).build();
      return new Response.Builder().request(localRequest).protocol(protocol).code(code).message(message).headers(responseHeaders).body(new Cache.CacheResponseBody(paramSnapshot, str1, str2)).handshake(handshake).sentRequestAtMillis(sentRequestMillis).receivedResponseAtMillis(receivedResponseMillis).build();
    }
    
    public void writeTo(DiskLruCache.Editor paramEditor)
      throws IOException
    {
      int j = 0;
      paramEditor = Okio.buffer(paramEditor.newSink(0));
      paramEditor.writeUtf8(url).writeByte(10);
      paramEditor.writeUtf8(requestMethod).writeByte(10);
      paramEditor.writeDecimalLong(varyHeaders.size()).writeByte(10);
      int k = varyHeaders.size();
      int i = 0;
      while (i < k)
      {
        paramEditor.writeUtf8(varyHeaders.name(i)).writeUtf8(": ").writeUtf8(varyHeaders.value(i)).writeByte(10);
        i += 1;
      }
      paramEditor.writeUtf8(new StatusLine(protocol, code, message).toString()).writeByte(10);
      paramEditor.writeDecimalLong(responseHeaders.size() + 2).writeByte(10);
      k = responseHeaders.size();
      i = j;
      while (i < k)
      {
        paramEditor.writeUtf8(responseHeaders.name(i)).writeUtf8(": ").writeUtf8(responseHeaders.value(i)).writeByte(10);
        i += 1;
      }
      paramEditor.writeUtf8(SENT_MILLIS).writeUtf8(": ").writeDecimalLong(sentRequestMillis).writeByte(10);
      paramEditor.writeUtf8(RECEIVED_MILLIS).writeUtf8(": ").writeDecimalLong(receivedResponseMillis).writeByte(10);
      if (isHttps())
      {
        paramEditor.writeByte(10);
        paramEditor.writeUtf8(handshake.cipherSuite().javaName()).writeByte(10);
        writeCertList(paramEditor, handshake.peerCertificates());
        writeCertList(paramEditor, handshake.localCertificates());
        paramEditor.writeUtf8(handshake.tlsVersion().javaName()).writeByte(10);
      }
      paramEditor.close();
    }
  }
}
