package okhttp3;

import java.net.URL;
import java.util.List;
import javax.annotation.Nullable;
import okhttp3.internal.Util;
import okhttp3.internal.http.HttpMethod;

public final class Request
{
  @Nullable
  final RequestBody body;
  private volatile CacheControl cacheControl;
  final Headers headers;
  final String method;
  final Object tag;
  final HttpUrl url;
  
  Request(Builder paramBuilder)
  {
    url = url;
    method = method;
    headers = headers.build();
    body = body;
    if (tag != null) {
      paramBuilder = tag;
    } else {
      paramBuilder = this;
    }
    tag = paramBuilder;
  }
  
  @Nullable
  public RequestBody body()
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
  
  public String header(String paramString)
  {
    return headers.get(paramString);
  }
  
  public List<String> headers(String paramString)
  {
    return headers.values(paramString);
  }
  
  public Headers headers()
  {
    return headers;
  }
  
  public boolean isHttps()
  {
    return url.isHttps();
  }
  
  public String method()
  {
    return method;
  }
  
  public Builder newBuilder()
  {
    return new Builder(this);
  }
  
  public Object tag()
  {
    return tag;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Request{method=");
    localStringBuilder.append(method);
    localStringBuilder.append(", url=");
    localStringBuilder.append(url);
    localStringBuilder.append(", tag=");
    Object localObject;
    if (tag != this) {
      localObject = tag;
    } else {
      localObject = null;
    }
    localStringBuilder.append(localObject);
    localStringBuilder.append('}');
    return localStringBuilder.toString();
  }
  
  public HttpUrl url()
  {
    return url;
  }
  
  public static class Builder
  {
    RequestBody body;
    Headers.Builder headers;
    String method;
    Object tag;
    HttpUrl url;
    
    public Builder()
    {
      method = "GET";
      headers = new Headers.Builder();
    }
    
    Builder(Request paramRequest)
    {
      url = url;
      method = method;
      body = body;
      tag = tag;
      headers = headers.newBuilder();
    }
    
    public Builder addHeader(String paramString1, String paramString2)
    {
      headers.add(paramString1, paramString2);
      return this;
    }
    
    public Request build()
    {
      if (url == null) {
        throw new IllegalStateException("url == null");
      }
      return new Request(this);
    }
    
    public Builder cacheControl(CacheControl paramCacheControl)
    {
      paramCacheControl = paramCacheControl.toString();
      if (paramCacheControl.isEmpty()) {
        return removeHeader("Cache-Control");
      }
      return header("Cache-Control", paramCacheControl);
    }
    
    public Builder delete()
    {
      return delete(Util.EMPTY_REQUEST);
    }
    
    public Builder delete(@Nullable RequestBody paramRequestBody)
    {
      return method("DELETE", paramRequestBody);
    }
    
    public Builder get()
    {
      return method("GET", null);
    }
    
    public Builder head()
    {
      return method("HEAD", null);
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
    
    public Builder method(String paramString, @Nullable RequestBody paramRequestBody)
    {
      if (paramString == null) {
        throw new NullPointerException("method == null");
      }
      if (paramString.length() == 0) {
        throw new IllegalArgumentException("method.length() == 0");
      }
      if ((paramRequestBody != null) && (!HttpMethod.permitsRequestBody(paramString)))
      {
        paramRequestBody = new StringBuilder();
        paramRequestBody.append("method ");
        paramRequestBody.append(paramString);
        paramRequestBody.append(" must not have a request body.");
        throw new IllegalArgumentException(paramRequestBody.toString());
      }
      if ((paramRequestBody == null) && (HttpMethod.requiresRequestBody(paramString)))
      {
        paramRequestBody = new StringBuilder();
        paramRequestBody.append("method ");
        paramRequestBody.append(paramString);
        paramRequestBody.append(" must have a request body.");
        throw new IllegalArgumentException(paramRequestBody.toString());
      }
      method = paramString;
      body = paramRequestBody;
      return this;
    }
    
    public Builder patch(RequestBody paramRequestBody)
    {
      return method("PATCH", paramRequestBody);
    }
    
    public Builder post(RequestBody paramRequestBody)
    {
      return method("POST", paramRequestBody);
    }
    
    public Builder put(RequestBody paramRequestBody)
    {
      return method("PUT", paramRequestBody);
    }
    
    public Builder removeHeader(String paramString)
    {
      headers.removeAll(paramString);
      return this;
    }
    
    public Builder tag(Object paramObject)
    {
      tag = paramObject;
      return this;
    }
    
    public Builder url(String paramString)
    {
      if (paramString == null) {
        throw new NullPointerException("url == null");
      }
      Object localObject;
      if (paramString.regionMatches(true, 0, "ws:", 0, 3))
      {
        localObject = new StringBuilder();
        ((StringBuilder)localObject).append("http:");
        ((StringBuilder)localObject).append(paramString.substring(3));
        localObject = ((StringBuilder)localObject).toString();
      }
      else
      {
        localObject = paramString;
        if (paramString.regionMatches(true, 0, "wss:", 0, 4))
        {
          localObject = new StringBuilder();
          ((StringBuilder)localObject).append("https:");
          ((StringBuilder)localObject).append(paramString.substring(4));
          localObject = ((StringBuilder)localObject).toString();
        }
      }
      paramString = HttpUrl.parse((String)localObject);
      if (paramString == null)
      {
        paramString = new StringBuilder();
        paramString.append("unexpected url: ");
        paramString.append((String)localObject);
        throw new IllegalArgumentException(paramString.toString());
      }
      return url(paramString);
    }
    
    public Builder url(URL paramURL)
    {
      if (paramURL == null) {
        throw new NullPointerException("url == null");
      }
      Object localObject = HttpUrl.get(paramURL);
      if (localObject == null)
      {
        localObject = new StringBuilder();
        ((StringBuilder)localObject).append("unexpected url: ");
        ((StringBuilder)localObject).append(paramURL);
        throw new IllegalArgumentException(((StringBuilder)localObject).toString());
      }
      return url((HttpUrl)localObject);
    }
    
    public Builder url(HttpUrl paramHttpUrl)
    {
      if (paramHttpUrl == null) {
        throw new NullPointerException("url == null");
      }
      url = paramHttpUrl;
      return this;
    }
  }
}
