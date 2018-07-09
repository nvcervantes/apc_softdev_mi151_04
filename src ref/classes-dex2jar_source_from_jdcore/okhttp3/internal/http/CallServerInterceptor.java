package okhttp3.internal.http;

import java.io.IOException;
import java.net.ProtocolException;
import okhttp3.EventListener;
import okhttp3.Interceptor;
import okhttp3.Interceptor.Chain;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.Response.Builder;
import okhttp3.ResponseBody;
import okhttp3.internal.Util;
import okhttp3.internal.connection.RealConnection;
import okhttp3.internal.connection.StreamAllocation;
import okio.Buffer;
import okio.BufferedSink;
import okio.ForwardingSink;
import okio.Okio;
import okio.Sink;

public final class CallServerInterceptor
  implements Interceptor
{
  private final boolean forWebSocket;
  
  public CallServerInterceptor(boolean paramBoolean)
  {
    forWebSocket = paramBoolean;
  }
  
  public Response intercept(Interceptor.Chain paramChain)
    throws IOException
  {
    RealInterceptorChain localRealInterceptorChain = (RealInterceptorChain)paramChain;
    HttpCodec localHttpCodec = localRealInterceptorChain.httpStream();
    StreamAllocation localStreamAllocation = localRealInterceptorChain.streamAllocation();
    RealConnection localRealConnection = (RealConnection)localRealInterceptorChain.connection();
    Request localRequest = localRealInterceptorChain.request();
    long l = System.currentTimeMillis();
    localRealInterceptorChain.eventListener().requestHeadersStart(localRealInterceptorChain.call());
    localHttpCodec.writeRequestHeaders(localRequest);
    localRealInterceptorChain.eventListener().requestHeadersEnd(localRealInterceptorChain.call(), localRequest);
    boolean bool = HttpMethod.permitsRequestBody(localRequest.method());
    BufferedSink localBufferedSink = null;
    Object localObject = null;
    paramChain = localBufferedSink;
    if (bool)
    {
      paramChain = localBufferedSink;
      if (localRequest.body() != null)
      {
        if ("100-continue".equalsIgnoreCase(localRequest.header("Expect")))
        {
          localHttpCodec.flushRequest();
          localRealInterceptorChain.eventListener().responseHeadersStart(localRealInterceptorChain.call());
          localObject = localHttpCodec.readResponseHeaders(true);
        }
        if (localObject == null)
        {
          localRealInterceptorChain.eventListener().requestBodyStart(localRealInterceptorChain.call());
          paramChain = new CountingSink(localHttpCodec.createRequestBody(localRequest, localRequest.body().contentLength()));
          localBufferedSink = Okio.buffer(paramChain);
          localRequest.body().writeTo(localBufferedSink);
          localBufferedSink.close();
          localRealInterceptorChain.eventListener().requestBodyEnd(localRealInterceptorChain.call(), successfulCount);
          paramChain = (Interceptor.Chain)localObject;
        }
        else
        {
          paramChain = (Interceptor.Chain)localObject;
          if (!localRealConnection.isMultiplexed())
          {
            localStreamAllocation.noNewStreams();
            paramChain = (Interceptor.Chain)localObject;
          }
        }
      }
    }
    localHttpCodec.finishRequest();
    localObject = paramChain;
    if (paramChain == null)
    {
      localRealInterceptorChain.eventListener().responseHeadersStart(localRealInterceptorChain.call());
      localObject = localHttpCodec.readResponseHeaders(false);
    }
    paramChain = ((Response.Builder)localObject).request(localRequest).handshake(localStreamAllocation.connection().handshake()).sentRequestAtMillis(l).receivedResponseAtMillis(System.currentTimeMillis()).build();
    localRealInterceptorChain.eventListener().responseHeadersEnd(localRealInterceptorChain.call(), paramChain);
    int i = paramChain.code();
    if ((forWebSocket) && (i == 101)) {
      paramChain = paramChain.newBuilder().body(Util.EMPTY_RESPONSE).build();
    } else {
      paramChain = paramChain.newBuilder().body(localHttpCodec.openResponseBody(paramChain)).build();
    }
    if (("close".equalsIgnoreCase(paramChain.request().header("Connection"))) || ("close".equalsIgnoreCase(paramChain.header("Connection")))) {
      localStreamAllocation.noNewStreams();
    }
    if (((i == 204) || (i == 205)) && (paramChain.body().contentLength() > 0L))
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("HTTP ");
      ((StringBuilder)localObject).append(i);
      ((StringBuilder)localObject).append(" had non-zero Content-Length: ");
      ((StringBuilder)localObject).append(paramChain.body().contentLength());
      throw new ProtocolException(((StringBuilder)localObject).toString());
    }
    return paramChain;
  }
  
  static final class CountingSink
    extends ForwardingSink
  {
    long successfulCount;
    
    CountingSink(Sink paramSink)
    {
      super();
    }
    
    public void write(Buffer paramBuffer, long paramLong)
      throws IOException
    {
      super.write(paramBuffer, paramLong);
      successfulCount += paramLong;
    }
  }
}
