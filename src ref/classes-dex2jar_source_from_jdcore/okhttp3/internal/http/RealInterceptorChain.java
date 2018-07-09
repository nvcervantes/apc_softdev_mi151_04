package okhttp3.internal.http;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import okhttp3.Call;
import okhttp3.Connection;
import okhttp3.EventListener;
import okhttp3.Interceptor;
import okhttp3.Interceptor.Chain;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.Util;
import okhttp3.internal.connection.RealConnection;
import okhttp3.internal.connection.StreamAllocation;

public final class RealInterceptorChain
  implements Interceptor.Chain
{
  private final Call call;
  private int calls;
  private final int connectTimeout;
  private final RealConnection connection;
  private final EventListener eventListener;
  private final HttpCodec httpCodec;
  private final int index;
  private final List<Interceptor> interceptors;
  private final int readTimeout;
  private final Request request;
  private final StreamAllocation streamAllocation;
  private final int writeTimeout;
  
  public RealInterceptorChain(List<Interceptor> paramList, StreamAllocation paramStreamAllocation, HttpCodec paramHttpCodec, RealConnection paramRealConnection, int paramInt1, Request paramRequest, Call paramCall, EventListener paramEventListener, int paramInt2, int paramInt3, int paramInt4)
  {
    interceptors = paramList;
    connection = paramRealConnection;
    streamAllocation = paramStreamAllocation;
    httpCodec = paramHttpCodec;
    index = paramInt1;
    request = paramRequest;
    call = paramCall;
    eventListener = paramEventListener;
    connectTimeout = paramInt2;
    readTimeout = paramInt3;
    writeTimeout = paramInt4;
  }
  
  public Call call()
  {
    return call;
  }
  
  public int connectTimeoutMillis()
  {
    return connectTimeout;
  }
  
  public Connection connection()
  {
    return connection;
  }
  
  public EventListener eventListener()
  {
    return eventListener;
  }
  
  public HttpCodec httpStream()
  {
    return httpCodec;
  }
  
  public Response proceed(Request paramRequest)
    throws IOException
  {
    return proceed(paramRequest, streamAllocation, httpCodec, connection);
  }
  
  public Response proceed(Request paramRequest, StreamAllocation paramStreamAllocation, HttpCodec paramHttpCodec, RealConnection paramRealConnection)
    throws IOException
  {
    if (index >= interceptors.size()) {
      throw new AssertionError();
    }
    calls += 1;
    if ((httpCodec != null) && (!connection.supportsUrl(paramRequest.url())))
    {
      paramRequest = new StringBuilder();
      paramRequest.append("network interceptor ");
      paramRequest.append(interceptors.get(index - 1));
      paramRequest.append(" must retain the same host and port");
      throw new IllegalStateException(paramRequest.toString());
    }
    if ((httpCodec != null) && (calls > 1))
    {
      paramRequest = new StringBuilder();
      paramRequest.append("network interceptor ");
      paramRequest.append(interceptors.get(index - 1));
      paramRequest.append(" must call proceed() exactly once");
      throw new IllegalStateException(paramRequest.toString());
    }
    paramStreamAllocation = new RealInterceptorChain(interceptors, paramStreamAllocation, paramHttpCodec, paramRealConnection, index + 1, paramRequest, call, eventListener, connectTimeout, readTimeout, writeTimeout);
    paramRequest = (Interceptor)interceptors.get(index);
    paramRealConnection = paramRequest.intercept(paramStreamAllocation);
    if ((paramHttpCodec != null) && (index + 1 < interceptors.size()) && (calls != 1))
    {
      paramStreamAllocation = new StringBuilder();
      paramStreamAllocation.append("network interceptor ");
      paramStreamAllocation.append(paramRequest);
      paramStreamAllocation.append(" must call proceed() exactly once");
      throw new IllegalStateException(paramStreamAllocation.toString());
    }
    if (paramRealConnection == null)
    {
      paramStreamAllocation = new StringBuilder();
      paramStreamAllocation.append("interceptor ");
      paramStreamAllocation.append(paramRequest);
      paramStreamAllocation.append(" returned null");
      throw new NullPointerException(paramStreamAllocation.toString());
    }
    if (paramRealConnection.body() == null)
    {
      paramStreamAllocation = new StringBuilder();
      paramStreamAllocation.append("interceptor ");
      paramStreamAllocation.append(paramRequest);
      paramStreamAllocation.append(" returned a response with no body");
      throw new IllegalStateException(paramStreamAllocation.toString());
    }
    return paramRealConnection;
  }
  
  public int readTimeoutMillis()
  {
    return readTimeout;
  }
  
  public Request request()
  {
    return request;
  }
  
  public StreamAllocation streamAllocation()
  {
    return streamAllocation;
  }
  
  public Interceptor.Chain withConnectTimeout(int paramInt, TimeUnit paramTimeUnit)
  {
    paramInt = Util.checkDuration("timeout", paramInt, paramTimeUnit);
    return new RealInterceptorChain(interceptors, streamAllocation, httpCodec, connection, index, request, call, eventListener, paramInt, readTimeout, writeTimeout);
  }
  
  public Interceptor.Chain withReadTimeout(int paramInt, TimeUnit paramTimeUnit)
  {
    paramInt = Util.checkDuration("timeout", paramInt, paramTimeUnit);
    return new RealInterceptorChain(interceptors, streamAllocation, httpCodec, connection, index, request, call, eventListener, connectTimeout, paramInt, writeTimeout);
  }
  
  public Interceptor.Chain withWriteTimeout(int paramInt, TimeUnit paramTimeUnit)
  {
    paramInt = Util.checkDuration("timeout", paramInt, paramTimeUnit);
    return new RealInterceptorChain(interceptors, streamAllocation, httpCodec, connection, index, request, call, eventListener, connectTimeout, readTimeout, paramInt);
  }
  
  public int writeTimeoutMillis()
  {
    return writeTimeout;
  }
}
