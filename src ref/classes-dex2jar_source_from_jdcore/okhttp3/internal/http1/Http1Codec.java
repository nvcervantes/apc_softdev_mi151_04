package okhttp3.internal.http1;

import java.io.EOFException;
import java.io.IOException;
import java.net.ProtocolException;
import java.net.Proxy;
import java.util.concurrent.TimeUnit;
import okhttp3.EventListener;
import okhttp3.Headers;
import okhttp3.Headers.Builder;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Response.Builder;
import okhttp3.ResponseBody;
import okhttp3.Route;
import okhttp3.internal.Internal;
import okhttp3.internal.Util;
import okhttp3.internal.connection.RealConnection;
import okhttp3.internal.connection.StreamAllocation;
import okhttp3.internal.http.HttpCodec;
import okhttp3.internal.http.HttpHeaders;
import okhttp3.internal.http.RealResponseBody;
import okhttp3.internal.http.RequestLine;
import okhttp3.internal.http.StatusLine;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ForwardingTimeout;
import okio.Okio;
import okio.Sink;
import okio.Source;
import okio.Timeout;

public final class Http1Codec
  implements HttpCodec
{
  private static final int STATE_CLOSED = 6;
  private static final int STATE_IDLE = 0;
  private static final int STATE_OPEN_REQUEST_BODY = 1;
  private static final int STATE_OPEN_RESPONSE_BODY = 4;
  private static final int STATE_READING_RESPONSE_BODY = 5;
  private static final int STATE_READ_RESPONSE_HEADERS = 3;
  private static final int STATE_WRITING_REQUEST_BODY = 2;
  final OkHttpClient client;
  final BufferedSink sink;
  final BufferedSource source;
  int state = 0;
  final StreamAllocation streamAllocation;
  
  public Http1Codec(OkHttpClient paramOkHttpClient, StreamAllocation paramStreamAllocation, BufferedSource paramBufferedSource, BufferedSink paramBufferedSink)
  {
    client = paramOkHttpClient;
    streamAllocation = paramStreamAllocation;
    source = paramBufferedSource;
    sink = paramBufferedSink;
  }
  
  public void cancel()
  {
    RealConnection localRealConnection = streamAllocation.connection();
    if (localRealConnection != null) {
      localRealConnection.cancel();
    }
  }
  
  public Sink createRequestBody(Request paramRequest, long paramLong)
  {
    if ("chunked".equalsIgnoreCase(paramRequest.header("Transfer-Encoding"))) {
      return newChunkedSink();
    }
    if (paramLong != -1L) {
      return newFixedLengthSink(paramLong);
    }
    throw new IllegalStateException("Cannot stream a request body without chunked encoding or a known content length!");
  }
  
  void detachTimeout(ForwardingTimeout paramForwardingTimeout)
  {
    Timeout localTimeout = paramForwardingTimeout.delegate();
    paramForwardingTimeout.setDelegate(Timeout.NONE);
    localTimeout.clearDeadline();
    localTimeout.clearTimeout();
  }
  
  public void finishRequest()
    throws IOException
  {
    sink.flush();
  }
  
  public void flushRequest()
    throws IOException
  {
    sink.flush();
  }
  
  public boolean isClosed()
  {
    return state == 6;
  }
  
  public Sink newChunkedSink()
  {
    if (state != 1)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("state: ");
      localStringBuilder.append(state);
      throw new IllegalStateException(localStringBuilder.toString());
    }
    state = 2;
    return new ChunkedSink();
  }
  
  public Source newChunkedSource(HttpUrl paramHttpUrl)
    throws IOException
  {
    if (state != 4)
    {
      paramHttpUrl = new StringBuilder();
      paramHttpUrl.append("state: ");
      paramHttpUrl.append(state);
      throw new IllegalStateException(paramHttpUrl.toString());
    }
    state = 5;
    return new ChunkedSource(paramHttpUrl);
  }
  
  public Sink newFixedLengthSink(long paramLong)
  {
    if (state != 1)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("state: ");
      localStringBuilder.append(state);
      throw new IllegalStateException(localStringBuilder.toString());
    }
    state = 2;
    return new FixedLengthSink(paramLong);
  }
  
  public Source newFixedLengthSource(long paramLong)
    throws IOException
  {
    if (state != 4)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("state: ");
      localStringBuilder.append(state);
      throw new IllegalStateException(localStringBuilder.toString());
    }
    state = 5;
    return new FixedLengthSource(paramLong);
  }
  
  public Source newUnknownLengthSource()
    throws IOException
  {
    if (state != 4)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("state: ");
      localStringBuilder.append(state);
      throw new IllegalStateException(localStringBuilder.toString());
    }
    if (streamAllocation == null) {
      throw new IllegalStateException("streamAllocation == null");
    }
    state = 5;
    streamAllocation.noNewStreams();
    return new UnknownLengthSource();
  }
  
  public ResponseBody openResponseBody(Response paramResponse)
    throws IOException
  {
    streamAllocation.eventListener.responseBodyStart(streamAllocation.call);
    String str = paramResponse.header("Content-Type");
    if (!HttpHeaders.hasBody(paramResponse)) {
      return new RealResponseBody(str, 0L, Okio.buffer(newFixedLengthSource(0L)));
    }
    if ("chunked".equalsIgnoreCase(paramResponse.header("Transfer-Encoding"))) {
      return new RealResponseBody(str, -1L, Okio.buffer(newChunkedSource(paramResponse.request().url())));
    }
    long l = HttpHeaders.contentLength(paramResponse);
    if (l != -1L) {
      return new RealResponseBody(str, l, Okio.buffer(newFixedLengthSource(l)));
    }
    return new RealResponseBody(str, -1L, Okio.buffer(newUnknownLengthSource()));
  }
  
  public Headers readHeaders()
    throws IOException
  {
    Headers.Builder localBuilder = new Headers.Builder();
    for (;;)
    {
      String str = source.readUtf8LineStrict();
      if (str.length() == 0) {
        break;
      }
      Internal.instance.addLenient(localBuilder, str);
    }
    return localBuilder.build();
  }
  
  public Response.Builder readResponseHeaders(boolean paramBoolean)
    throws IOException
  {
    Object localObject1;
    if ((state != 1) && (state != 3))
    {
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append("state: ");
      ((StringBuilder)localObject1).append(state);
      throw new IllegalStateException(((StringBuilder)localObject1).toString());
    }
    try
    {
      localObject1 = StatusLine.parse(source.readUtf8LineStrict());
      localObject2 = new Response.Builder().protocol(protocol).code(code).message(message).headers(readHeaders());
      if ((paramBoolean) && (code == 100)) {
        return null;
      }
      state = 4;
      return localObject2;
    }
    catch (EOFException localEOFException)
    {
      Object localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append("unexpected end of stream on ");
      ((StringBuilder)localObject2).append(streamAllocation);
      localObject2 = new IOException(((StringBuilder)localObject2).toString());
      ((IOException)localObject2).initCause(localEOFException);
      throw ((Throwable)localObject2);
    }
  }
  
  public void writeRequest(Headers paramHeaders, String paramString)
    throws IOException
  {
    if (state != 0)
    {
      paramHeaders = new StringBuilder();
      paramHeaders.append("state: ");
      paramHeaders.append(state);
      throw new IllegalStateException(paramHeaders.toString());
    }
    sink.writeUtf8(paramString).writeUtf8("\r\n");
    int i = 0;
    int j = paramHeaders.size();
    while (i < j)
    {
      sink.writeUtf8(paramHeaders.name(i)).writeUtf8(": ").writeUtf8(paramHeaders.value(i)).writeUtf8("\r\n");
      i += 1;
    }
    sink.writeUtf8("\r\n");
    state = 1;
  }
  
  public void writeRequestHeaders(Request paramRequest)
    throws IOException
  {
    String str = RequestLine.get(paramRequest, streamAllocation.connection().route().proxy().type());
    writeRequest(paramRequest.headers(), str);
  }
  
  private abstract class AbstractSource
    implements Source
  {
    protected long bytesRead = 0L;
    protected boolean closed;
    protected final ForwardingTimeout timeout = new ForwardingTimeout(source.timeout());
    
    private AbstractSource() {}
    
    protected final void endOfInput(boolean paramBoolean, IOException paramIOException)
      throws IOException
    {
      if (state == 6) {
        return;
      }
      if (state != 5)
      {
        paramIOException = new StringBuilder();
        paramIOException.append("state: ");
        paramIOException.append(state);
        throw new IllegalStateException(paramIOException.toString());
      }
      detachTimeout(timeout);
      state = 6;
      if (streamAllocation != null) {
        streamAllocation.streamFinished(paramBoolean ^ true, Http1Codec.this, bytesRead, paramIOException);
      }
    }
    
    public long read(Buffer paramBuffer, long paramLong)
      throws IOException
    {
      try
      {
        paramLong = source.read(paramBuffer, paramLong);
        if (paramLong > 0L) {
          bytesRead += paramLong;
        }
        return paramLong;
      }
      catch (IOException paramBuffer)
      {
        endOfInput(false, paramBuffer);
        throw paramBuffer;
      }
    }
    
    public Timeout timeout()
    {
      return timeout;
    }
  }
  
  private final class ChunkedSink
    implements Sink
  {
    private boolean closed;
    private final ForwardingTimeout timeout = new ForwardingTimeout(sink.timeout());
    
    ChunkedSink() {}
    
    public void close()
      throws IOException
    {
      try
      {
        boolean bool = closed;
        if (bool) {
          return;
        }
        closed = true;
        sink.writeUtf8("0\r\n\r\n");
        detachTimeout(timeout);
        state = 3;
        return;
      }
      finally {}
    }
    
    public void flush()
      throws IOException
    {
      try
      {
        boolean bool = closed;
        if (bool) {
          return;
        }
        sink.flush();
        return;
      }
      finally {}
    }
    
    public Timeout timeout()
    {
      return timeout;
    }
    
    public void write(Buffer paramBuffer, long paramLong)
      throws IOException
    {
      if (closed) {
        throw new IllegalStateException("closed");
      }
      if (paramLong == 0L) {
        return;
      }
      sink.writeHexadecimalUnsignedLong(paramLong);
      sink.writeUtf8("\r\n");
      sink.write(paramBuffer, paramLong);
      sink.writeUtf8("\r\n");
    }
  }
  
  private class ChunkedSource
    extends Http1Codec.AbstractSource
  {
    private static final long NO_CHUNK_YET = -1L;
    private long bytesRemainingInChunk = -1L;
    private boolean hasMoreChunks = true;
    private final HttpUrl url;
    
    ChunkedSource(HttpUrl paramHttpUrl)
    {
      super(null);
      url = paramHttpUrl;
    }
    
    private void readChunkSize()
      throws IOException
    {
      if (bytesRemainingInChunk != -1L) {
        source.readUtf8LineStrict();
      }
      try
      {
        bytesRemainingInChunk = source.readHexadecimalUnsignedLong();
        String str = source.readUtf8LineStrict().trim();
        if (bytesRemainingInChunk >= 0L) {
          if (!str.isEmpty())
          {
            boolean bool = str.startsWith(";");
            if (!bool) {}
          }
          else
          {
            if (bytesRemainingInChunk == 0L)
            {
              hasMoreChunks = false;
              HttpHeaders.receiveHeaders(client.cookieJar(), url, readHeaders());
              endOfInput(true, null);
            }
            return;
          }
        }
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("expected chunk size and optional extensions but was \"");
        localStringBuilder.append(bytesRemainingInChunk);
        localStringBuilder.append(str);
        localStringBuilder.append("\"");
        throw new ProtocolException(localStringBuilder.toString());
      }
      catch (NumberFormatException localNumberFormatException)
      {
        throw new ProtocolException(localNumberFormatException.getMessage());
      }
    }
    
    public void close()
      throws IOException
    {
      if (closed) {
        return;
      }
      if ((hasMoreChunks) && (!Util.discard(this, 100, TimeUnit.MILLISECONDS))) {
        endOfInput(false, null);
      }
      closed = true;
    }
    
    public long read(Buffer paramBuffer, long paramLong)
      throws IOException
    {
      if (paramLong < 0L)
      {
        paramBuffer = new StringBuilder();
        paramBuffer.append("byteCount < 0: ");
        paramBuffer.append(paramLong);
        throw new IllegalArgumentException(paramBuffer.toString());
      }
      if (closed) {
        throw new IllegalStateException("closed");
      }
      if (!hasMoreChunks) {
        return -1L;
      }
      if ((bytesRemainingInChunk == 0L) || (bytesRemainingInChunk == -1L))
      {
        readChunkSize();
        if (!hasMoreChunks) {
          return -1L;
        }
      }
      paramLong = super.read(paramBuffer, Math.min(paramLong, bytesRemainingInChunk));
      if (paramLong == -1L)
      {
        paramBuffer = new ProtocolException("unexpected end of stream");
        endOfInput(false, paramBuffer);
        throw paramBuffer;
      }
      bytesRemainingInChunk -= paramLong;
      return paramLong;
    }
  }
  
  private final class FixedLengthSink
    implements Sink
  {
    private long bytesRemaining;
    private boolean closed;
    private final ForwardingTimeout timeout = new ForwardingTimeout(sink.timeout());
    
    FixedLengthSink(long paramLong)
    {
      bytesRemaining = paramLong;
    }
    
    public void close()
      throws IOException
    {
      if (closed) {
        return;
      }
      closed = true;
      if (bytesRemaining > 0L) {
        throw new ProtocolException("unexpected end of stream");
      }
      detachTimeout(timeout);
      state = 3;
    }
    
    public void flush()
      throws IOException
    {
      if (closed) {
        return;
      }
      sink.flush();
    }
    
    public Timeout timeout()
    {
      return timeout;
    }
    
    public void write(Buffer paramBuffer, long paramLong)
      throws IOException
    {
      if (closed) {
        throw new IllegalStateException("closed");
      }
      Util.checkOffsetAndCount(paramBuffer.size(), 0L, paramLong);
      if (paramLong > bytesRemaining)
      {
        paramBuffer = new StringBuilder();
        paramBuffer.append("expected ");
        paramBuffer.append(bytesRemaining);
        paramBuffer.append(" bytes but received ");
        paramBuffer.append(paramLong);
        throw new ProtocolException(paramBuffer.toString());
      }
      sink.write(paramBuffer, paramLong);
      bytesRemaining -= paramLong;
    }
  }
  
  private class FixedLengthSource
    extends Http1Codec.AbstractSource
  {
    private long bytesRemaining;
    
    FixedLengthSource(long paramLong)
      throws IOException
    {
      super(null);
      bytesRemaining = paramLong;
      if (bytesRemaining == 0L) {
        endOfInput(true, null);
      }
    }
    
    public void close()
      throws IOException
    {
      if (closed) {
        return;
      }
      if ((bytesRemaining != 0L) && (!Util.discard(this, 100, TimeUnit.MILLISECONDS))) {
        endOfInput(false, null);
      }
      closed = true;
    }
    
    public long read(Buffer paramBuffer, long paramLong)
      throws IOException
    {
      if (paramLong < 0L)
      {
        paramBuffer = new StringBuilder();
        paramBuffer.append("byteCount < 0: ");
        paramBuffer.append(paramLong);
        throw new IllegalArgumentException(paramBuffer.toString());
      }
      if (closed) {
        throw new IllegalStateException("closed");
      }
      if (bytesRemaining == 0L) {
        return -1L;
      }
      paramLong = super.read(paramBuffer, Math.min(bytesRemaining, paramLong));
      if (paramLong == -1L)
      {
        paramBuffer = new ProtocolException("unexpected end of stream");
        endOfInput(false, paramBuffer);
        throw paramBuffer;
      }
      bytesRemaining -= paramLong;
      if (bytesRemaining == 0L) {
        endOfInput(true, null);
      }
      return paramLong;
    }
  }
  
  private class UnknownLengthSource
    extends Http1Codec.AbstractSource
  {
    private boolean inputExhausted;
    
    UnknownLengthSource()
    {
      super(null);
    }
    
    public void close()
      throws IOException
    {
      if (closed) {
        return;
      }
      if (!inputExhausted) {
        endOfInput(false, null);
      }
      closed = true;
    }
    
    public long read(Buffer paramBuffer, long paramLong)
      throws IOException
    {
      if (paramLong < 0L)
      {
        paramBuffer = new StringBuilder();
        paramBuffer.append("byteCount < 0: ");
        paramBuffer.append(paramLong);
        throw new IllegalArgumentException(paramBuffer.toString());
      }
      if (closed) {
        throw new IllegalStateException("closed");
      }
      if (inputExhausted) {
        return -1L;
      }
      paramLong = super.read(paramBuffer, paramLong);
      if (paramLong == -1L)
      {
        inputExhausted = true;
        endOfInput(true, null);
        return -1L;
      }
      return paramLong;
    }
  }
}
