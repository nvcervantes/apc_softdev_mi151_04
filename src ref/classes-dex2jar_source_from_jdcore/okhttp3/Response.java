package okhttp3;

import java.io.Closeable;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;
import okhttp3.internal.http.HttpHeaders;
import okio.Buffer;
import okio.BufferedSource;

public final class Response
  implements Closeable
{
  @Nullable
  final ResponseBody body;
  private volatile CacheControl cacheControl;
  @Nullable
  final Response cacheResponse;
  final int code;
  @Nullable
  final Handshake handshake;
  final Headers headers;
  final String message;
  @Nullable
  final Response networkResponse;
  @Nullable
  final Response priorResponse;
  final Protocol protocol;
  final long receivedResponseAtMillis;
  final Request request;
  final long sentRequestAtMillis;
  
  Response(Builder paramBuilder)
  {
    request = request;
    protocol = protocol;
    code = code;
    message = message;
    handshake = handshake;
    headers = headers.build();
    body = body;
    networkResponse = networkResponse;
    cacheResponse = cacheResponse;
    priorResponse = priorResponse;
    sentRequestAtMillis = sentRequestAtMillis;
    receivedResponseAtMillis = receivedResponseAtMillis;
  }
  
  @Nullable
  public ResponseBody body()
  {
    return body;
  }
  
  public CacheControl cacheControl()
  {
    CacheControl localCacheControl = cacheControl;
    if (localCacheControl != null) {
      return localCacheControl;
    }
    localCacheControl = CacheControl.parse(headers);
    cacheControl = localCacheControl;
    return localCacheControl;
  }
  
  @Nullable
  public Response cacheResponse()
  {
    return cacheResponse;
  }
  
  public List<Challenge> challenges()
  {
    String str;
    if (code == 401)
    {
      str = "WWW-Authenticate";
    }
    else
    {
      if (code != 407) {
        break label38;
      }
      str = "Proxy-Authenticate";
    }
    return HttpHeaders.parseChallenges(headers(), str);
    label38:
    return Collections.emptyList();
  }
  
  public void close()
  {
    if (body == null) {
      throw new IllegalStateException("response is not eligible for a body and must not be closed");
    }
    body.close();
  }
  
  public int code()
  {
    return code;
  }
  
  public Handshake handshake()
  {
    return handshake;
  }
  
  @Nullable
  public String header(String paramString)
  {
    return header(paramString, null);
  }
  
  @Nullable
  public String header(String paramString1, @Nullable String paramString2)
  {
    paramString1 = headers.get(paramString1);
    if (paramString1 != null) {
      return paramString1;
    }
    return paramString2;
  }
  
  public List<String> headers(String paramString)
  {
    return headers.values(paramString);
  }
  
  public Headers headers()
  {
    return headers;
  }
  
  public boolean isRedirect()
  {
    switch (code)
    {
    case 304: 
    case 305: 
    case 306: 
    default: 
      return false;
    }
    return true;
  }
  
  public boolean isSuccessful()
  {
    return (code >= 200) && (code < 300);
  }
  
  public String message()
  {
    return message;
  }
  
  @Nullable
  public Response networkResponse()
  {
    return networkResponse;
  }
  
  public Builder newBuilder()
  {
    return new Builder(this);
  }
  
  public ResponseBody peekBody(long paramLong)
    throws IOException
  {
    Object localObject = body.source();
    ((BufferedSource)localObject).request(paramLong);
    Buffer localBuffer = ((BufferedSource)localObject).buffer().clone();
    localObject = localBuffer;
    if (localBuffer.size() > paramLong)
    {
      localObject = new Buffer();
      ((Buffer)localObject).write(localBuffer, paramLong);
      localBuffer.clear();
    }
    return ResponseBody.create(body.contentType(), ((Buffer)localObject).size(), (BufferedSource)localObject);
  }
  
  @Nullable
  public Response priorResponse()
  {
    return priorResponse;
  }
  
  public Protocol protocol()
  {
    return protocol;
  }
  
  public long receivedResponseAtMillis()
  {
    return receivedResponseAtMillis;
  }
  
  public Request request()
  {
    return request;
  }
  
  public long sentRequestAtMillis()
  {
    return sentRequestAtMillis;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Response{protocol=");
    localStringBuilder.append(protocol);
    localStringBuilder.append(", code=");
    localStringBuilder.append(code);
    localStringBuilder.append(", message=");
    localStringBuilder.append(message);
    localStringBuilder.append(", url=");
    localStringBuilder.append(request.url());
    localStringBuilder.append('}');
    return localStringBuilder.toString();
  }
  
  public static class Builder
  {
    ResponseBody body;
    Response cacheResponse;
    int code = -1;
    @Nullable
    Handshake handshake;
    Headers.Builder headers;
    String message;
    Response networkResponse;
    Response priorResponse;
    Protocol protocol;
    long receivedResponseAtMillis;
    Request request;
    long sentRequestAtMillis;
    
    public Builder()
    {
      headers = new Headers.Builder();
    }
    
    Builder(Response paramResponse)
    {
      request = request;
      protocol = protocol;
      code = code;
      message = message;
      handshake = handshake;
      headers = headers.newBuilder();
      body = body;
      networkResponse = networkResponse;
      cacheResponse = cacheResponse;
      priorResponse = priorResponse;
      sentRequestAtMillis = sentRequestAtMillis;
      receivedResponseAtMillis = receivedResponseAtMillis;
    }
    
    private void checkPriorResponse(Response paramResponse)
    {
      if (body != null) {
        throw new IllegalArgumentException("priorResponse.body != null");
      }
    }
    
    private void checkSupportResponse(String paramString, Response paramResponse)
    {
      if (body != null)
      {
        paramResponse = new StringBuilder();
        paramResponse.append(paramString);
        paramResponse.append(".body != null");
        throw new IllegalArgumentException(paramResponse.toString());
      }
      if (networkResponse != null)
      {
        paramResponse = new StringBuilder();
        paramResponse.append(paramString);
        paramResponse.append(".networkResponse != null");
        throw new IllegalArgumentException(paramResponse.toString());
      }
      if (cacheResponse != null)
      {
        paramResponse = new StringBuilder();
        paramResponse.append(paramString);
        paramResponse.append(".cacheResponse != null");
        throw new IllegalArgumentException(paramResponse.toString());
      }
      if (priorResponse != null)
      {
        paramResponse = new StringBuilder();
        paramResponse.append(paramString);
        paramResponse.append(".priorResponse != null");
        throw new IllegalArgumentException(paramResponse.toString());
      }
    }
    
    public Builder addHeader(String paramString1, String paramString2)
    {
      headers.add(paramString1, paramString2);
      return this;
    }
    
    public Builder body(@Nullable ResponseBody paramResponseBody)
    {
      body = paramResponseBody;
      return this;
    }
    
    public Response build()
    {
      if (request == null) {
        throw new IllegalStateException("request == null");
      }
      if (protocol == null) {
        throw new IllegalStateException("protocol == null");
      }
      if (code < 0)
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("code < 0: ");
        localStringBuilder.append(code);
        throw new IllegalStateException(localStringBuilder.toString());
      }
      if (message == null) {
        throw new IllegalStateException("message == null");
      }
      return new Response(this);
    }
    
    public Builder cacheResponse(@Nullable Response paramResponse)
    {
      if (paramResponse != null) {
        checkSupportResponse("cacheResponse", paramResponse);
      }
      cacheResponse = paramResponse;
      return this;
    }
    
    public Builder code(int paramInt)
    {
      code = paramInt;
      return this;
    }
    
    public Builder handshake(@Nullable Handshake paramHandshake)
    {
      handshake = paramHandshake;
      return this;
    }
    
    public Builder header(String paramString1, String paramString2)
    {
      headers.set(paramString1, paramString2);
      return this;
    }
    
    public Builder headers(Headers paramHeaders)
    {
      headers = paramHeaders.newBuilder();
      return this;
    }
    
    public Builder message(String paramString)
    {
      message = paramString;
      return this;
    }
    
    public Builder networkResponse(@Nullable Response paramResponse)
    {
      if (paramResponse != null) {
        checkSupportResponse("networkResponse", paramResponse);
      }
      networkResponse = paramResponse;
      return this;
    }
    
    public Builder priorResponse(@Nullable Response paramResponse)
    {
      if (paramResponse != null) {
        checkPriorResponse(paramResponse);
      }
      priorResponse = paramResponse;
      return this;
    }
    
    public Builder protocol(Protocol paramProtocol)
    {
      protocol = paramProtocol;
      return this;
    }
    
    public Builder receivedResponseAtMillis(long paramLong)
    {
      receivedResponseAtMillis = paramLong;
      return this;
    }
    
    public Builder removeHeader(String paramString)
    {
      headers.removeAll(paramString);
      return this;
    }
    
    public Builder request(Request paramRequest)
    {
      request = paramRequest;
      return this;
    }
    
    public Builder sentRequestAtMillis(long paramLong)
    {
      sentRequestAtMillis = paramLong;
      return this;
    }
  }
}
