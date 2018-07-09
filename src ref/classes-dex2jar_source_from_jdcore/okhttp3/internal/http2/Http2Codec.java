package okhttp3.internal.http2;

import java.io.IOException;
import java.net.ProtocolException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import okhttp3.EventListener;
import okhttp3.Headers;
import okhttp3.Headers.Builder;
import okhttp3.HttpUrl;
import okhttp3.Interceptor.Chain;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Response.Builder;
import okhttp3.ResponseBody;
import okhttp3.internal.Internal;
import okhttp3.internal.Util;
import okhttp3.internal.connection.StreamAllocation;
import okhttp3.internal.http.HttpCodec;
import okhttp3.internal.http.HttpHeaders;
import okhttp3.internal.http.RealResponseBody;
import okhttp3.internal.http.RequestLine;
import okhttp3.internal.http.StatusLine;
import okio.Buffer;
import okio.ByteString;
import okio.ForwardingSource;
import okio.Okio;
import okio.Sink;
import okio.Source;
import okio.Timeout;

public final class Http2Codec
  implements HttpCodec
{
  private static final ByteString CONNECTION = ByteString.encodeUtf8("connection");
  private static final ByteString ENCODING;
  private static final ByteString HOST = ByteString.encodeUtf8("host");
  private static final List<ByteString> HTTP_2_SKIPPED_REQUEST_HEADERS = Util.immutableList(new ByteString[] { CONNECTION, HOST, KEEP_ALIVE, PROXY_CONNECTION, TE, TRANSFER_ENCODING, ENCODING, UPGRADE, Header.TARGET_METHOD, Header.TARGET_PATH, Header.TARGET_SCHEME, Header.TARGET_AUTHORITY });
  private static final List<ByteString> HTTP_2_SKIPPED_RESPONSE_HEADERS = Util.immutableList(new ByteString[] { CONNECTION, HOST, KEEP_ALIVE, PROXY_CONNECTION, TE, TRANSFER_ENCODING, ENCODING, UPGRADE });
  private static final ByteString KEEP_ALIVE = ByteString.encodeUtf8("keep-alive");
  private static final ByteString PROXY_CONNECTION = ByteString.encodeUtf8("proxy-connection");
  private static final ByteString TE;
  private static final ByteString TRANSFER_ENCODING = ByteString.encodeUtf8("transfer-encoding");
  private static final ByteString UPGRADE;
  private final Interceptor.Chain chain;
  private final OkHttpClient client;
  private final Http2Connection connection;
  private Http2Stream stream;
  final StreamAllocation streamAllocation;
  
  static
  {
    TE = ByteString.encodeUtf8("te");
    ENCODING = ByteString.encodeUtf8("encoding");
    UPGRADE = ByteString.encodeUtf8("upgrade");
  }
  
  public Http2Codec(OkHttpClient paramOkHttpClient, Interceptor.Chain paramChain, StreamAllocation paramStreamAllocation, Http2Connection paramHttp2Connection)
  {
    client = paramOkHttpClient;
    chain = paramChain;
    streamAllocation = paramStreamAllocation;
    connection = paramHttp2Connection;
  }
  
  public static List<Header> http2HeadersList(Request paramRequest)
  {
    Headers localHeaders = paramRequest.headers();
    ArrayList localArrayList = new ArrayList(localHeaders.size() + 4);
    localArrayList.add(new Header(Header.TARGET_METHOD, paramRequest.method()));
    localArrayList.add(new Header(Header.TARGET_PATH, RequestLine.requestPath(paramRequest.url())));
    String str = paramRequest.header("Host");
    if (str != null) {
      localArrayList.add(new Header(Header.TARGET_AUTHORITY, str));
    }
    localArrayList.add(new Header(Header.TARGET_SCHEME, paramRequest.url().scheme()));
    int i = 0;
    int j = localHeaders.size();
    while (i < j)
    {
      paramRequest = ByteString.encodeUtf8(localHeaders.name(i).toLowerCase(Locale.US));
      if (!HTTP_2_SKIPPED_REQUEST_HEADERS.contains(paramRequest)) {
        localArrayList.add(new Header(paramRequest, localHeaders.value(i)));
      }
      i += 1;
    }
    return localArrayList;
  }
  
  public static Response.Builder readHttp2HeadersList(List<Header> paramList)
    throws IOException
  {
    Object localObject2 = new Headers.Builder();
    int j = paramList.size();
    int i = 0;
    Object localObject3 = null;
    while (i < j)
    {
      Object localObject1 = (Header)paramList.get(i);
      Object localObject4;
      if (localObject1 == null)
      {
        localObject1 = localObject3;
        localObject4 = localObject2;
        if (localObject3 != null)
        {
          localObject1 = localObject3;
          localObject4 = localObject2;
          if (code == 100)
          {
            localObject4 = new Headers.Builder();
            localObject1 = null;
          }
        }
      }
      else
      {
        ByteString localByteString = name;
        String str = value.utf8();
        if (localByteString.equals(Header.RESPONSE_STATUS))
        {
          localObject1 = new StringBuilder();
          ((StringBuilder)localObject1).append("HTTP/1.1 ");
          ((StringBuilder)localObject1).append(str);
          localObject1 = StatusLine.parse(((StringBuilder)localObject1).toString());
          localObject4 = localObject2;
        }
        else
        {
          localObject1 = localObject3;
          localObject4 = localObject2;
          if (!HTTP_2_SKIPPED_RESPONSE_HEADERS.contains(localByteString))
          {
            Internal.instance.addLenient((Headers.Builder)localObject2, localByteString.utf8(), str);
            localObject4 = localObject2;
            localObject1 = localObject3;
          }
        }
      }
      i += 1;
      localObject3 = localObject1;
      localObject2 = localObject4;
    }
    if (localObject3 == null) {
      throw new ProtocolException("Expected ':status' header not present");
    }
    return new Response.Builder().protocol(Protocol.HTTP_2).code(code).message(message).headers(((Headers.Builder)localObject2).build());
  }
  
  public void cancel()
  {
    if (stream != null) {
      stream.closeLater(ErrorCode.CANCEL);
    }
  }
  
  public Sink createRequestBody(Request paramRequest, long paramLong)
  {
    return stream.getSink();
  }
  
  public void finishRequest()
    throws IOException
  {
    stream.getSink().close();
  }
  
  public void flushRequest()
    throws IOException
  {
    connection.flush();
  }
  
  public ResponseBody openResponseBody(Response paramResponse)
    throws IOException
  {
    streamAllocation.eventListener.responseBodyStart(streamAllocation.call);
    return new RealResponseBody(paramResponse.header("Content-Type"), HttpHeaders.contentLength(paramResponse), Okio.buffer(new StreamFinishingSource(stream.getSource())));
  }
  
  public Response.Builder readResponseHeaders(boolean paramBoolean)
    throws IOException
  {
    Response.Builder localBuilder = readHttp2HeadersList(stream.takeResponseHeaders());
    if ((paramBoolean) && (Internal.instance.code(localBuilder) == 100)) {
      return null;
    }
    return localBuilder;
  }
  
  public void writeRequestHeaders(Request paramRequest)
    throws IOException
  {
    if (stream != null) {
      return;
    }
    boolean bool;
    if (paramRequest.body() != null) {
      bool = true;
    } else {
      bool = false;
    }
    paramRequest = http2HeadersList(paramRequest);
    stream = connection.newStream(paramRequest, bool);
    stream.readTimeout().timeout(chain.readTimeoutMillis(), TimeUnit.MILLISECONDS);
    stream.writeTimeout().timeout(chain.writeTimeoutMillis(), TimeUnit.MILLISECONDS);
  }
  
  class StreamFinishingSource
    extends ForwardingSource
  {
    long bytesRead = 0L;
    boolean completed = false;
    
    StreamFinishingSource(Source paramSource)
    {
      super();
    }
    
    private void endOfInput(IOException paramIOException)
    {
      if (completed) {
        return;
      }
      completed = true;
      streamAllocation.streamFinished(false, Http2Codec.this, bytesRead, paramIOException);
    }
    
    public void close()
      throws IOException
    {
      super.close();
      endOfInput(null);
    }
    
    public long read(Buffer paramBuffer, long paramLong)
      throws IOException
    {
      try
      {
        paramLong = delegate().read(paramBuffer, paramLong);
        if (paramLong > 0L) {
          bytesRead += paramLong;
        }
        return paramLong;
      }
      catch (IOException paramBuffer)
      {
        endOfInput(paramBuffer);
        throw paramBuffer;
      }
    }
  }
}
