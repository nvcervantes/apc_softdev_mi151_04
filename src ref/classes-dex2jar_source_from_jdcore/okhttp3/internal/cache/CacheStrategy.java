package okhttp3.internal.cache;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import okhttp3.CacheControl;
import okhttp3.Headers;
import okhttp3.Headers.Builder;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.Response;
import okhttp3.Response.Builder;
import okhttp3.internal.Internal;
import okhttp3.internal.http.HttpDate;
import okhttp3.internal.http.HttpHeaders;

public final class CacheStrategy
{
  @Nullable
  public final Response cacheResponse;
  @Nullable
  public final Request networkRequest;
  
  CacheStrategy(Request paramRequest, Response paramResponse)
  {
    networkRequest = paramRequest;
    cacheResponse = paramResponse;
  }
  
  public static boolean isCacheable(Response paramResponse, Request paramRequest)
  {
    int i = paramResponse.code();
    boolean bool2 = false;
    switch (i)
    {
    default: 
      return false;
    case 302: 
    case 307: 
      if ((paramResponse.header("Expires") == null) && (paramResponse.cacheControl().maxAgeSeconds() == -1) && (!paramResponse.cacheControl().isPublic()) && (!paramResponse.cacheControl().isPrivate())) {
        break;
      }
    case 200: 
    case 203: 
    case 204: 
    case 300: 
    case 301: 
    case 308: 
    case 404: 
    case 405: 
    case 410: 
    case 414: 
    case 501: 
      boolean bool1 = bool2;
      if (!paramResponse.cacheControl().noStore())
      {
        bool1 = bool2;
        if (!paramRequest.cacheControl().noStore()) {
          bool1 = true;
        }
      }
      return bool1;
    }
    return false;
  }
  
  public static class Factory
  {
    private int ageSeconds = -1;
    final Response cacheResponse;
    private String etag;
    private Date expires;
    private Date lastModified;
    private String lastModifiedString;
    final long nowMillis;
    private long receivedResponseMillis;
    final Request request;
    private long sentRequestMillis;
    private Date servedDate;
    private String servedDateString;
    
    public Factory(long paramLong, Request paramRequest, Response paramResponse)
    {
      nowMillis = paramLong;
      request = paramRequest;
      cacheResponse = paramResponse;
      if (paramResponse != null)
      {
        sentRequestMillis = paramResponse.sentRequestAtMillis();
        receivedResponseMillis = paramResponse.receivedResponseAtMillis();
        paramRequest = paramResponse.headers();
        int i = 0;
        int j = paramRequest.size();
        while (i < j)
        {
          paramResponse = paramRequest.name(i);
          String str = paramRequest.value(i);
          if ("Date".equalsIgnoreCase(paramResponse))
          {
            servedDate = HttpDate.parse(str);
            servedDateString = str;
          }
          else if ("Expires".equalsIgnoreCase(paramResponse))
          {
            expires = HttpDate.parse(str);
          }
          else if ("Last-Modified".equalsIgnoreCase(paramResponse))
          {
            lastModified = HttpDate.parse(str);
            lastModifiedString = str;
          }
          else if ("ETag".equalsIgnoreCase(paramResponse))
          {
            etag = str;
          }
          else if ("Age".equalsIgnoreCase(paramResponse))
          {
            ageSeconds = HttpHeaders.parseSeconds(str, -1);
          }
          i += 1;
        }
      }
    }
    
    private long cacheResponseAge()
    {
      Date localDate = servedDate;
      long l1 = 0L;
      if (localDate != null) {
        l1 = Math.max(0L, receivedResponseMillis - servedDate.getTime());
      }
      long l2 = l1;
      if (ageSeconds != -1) {
        l2 = Math.max(l1, TimeUnit.SECONDS.toMillis(ageSeconds));
      }
      return l2 + (receivedResponseMillis - sentRequestMillis) + (nowMillis - receivedResponseMillis);
    }
    
    private long computeFreshnessLifetime()
    {
      Object localObject = cacheResponse.cacheControl();
      if (((CacheControl)localObject).maxAgeSeconds() != -1) {
        return TimeUnit.SECONDS.toMillis(((CacheControl)localObject).maxAgeSeconds());
      }
      localObject = expires;
      long l1 = 0L;
      long l2;
      if (localObject != null)
      {
        if (servedDate != null) {
          l2 = servedDate.getTime();
        } else {
          l2 = receivedResponseMillis;
        }
        l2 = expires.getTime() - l2;
        if (l2 > 0L) {
          l1 = l2;
        }
        return l1;
      }
      if ((lastModified != null) && (cacheResponse.request().url().query() == null))
      {
        if (servedDate != null) {
          l2 = servedDate.getTime();
        } else {
          l2 = sentRequestMillis;
        }
        l2 -= lastModified.getTime();
        if (l2 > 0L) {
          l1 = l2 / 10L;
        }
        return l1;
      }
      return 0L;
    }
    
    private CacheStrategy getCandidate()
    {
      if (cacheResponse == null) {
        return new CacheStrategy(request, null);
      }
      if ((request.isHttps()) && (cacheResponse.handshake() == null)) {
        return new CacheStrategy(request, null);
      }
      if (!CacheStrategy.isCacheable(cacheResponse, request)) {
        return new CacheStrategy(request, null);
      }
      Object localObject1 = request.cacheControl();
      if ((!((CacheControl)localObject1).noCache()) && (!hasConditions(request)))
      {
        Object localObject2 = cacheResponse.cacheControl();
        if (((CacheControl)localObject2).immutable()) {
          return new CacheStrategy(null, cacheResponse);
        }
        long l5 = cacheResponseAge();
        long l2 = computeFreshnessLifetime();
        long l1 = l2;
        if (((CacheControl)localObject1).maxAgeSeconds() != -1) {
          l1 = Math.min(l2, TimeUnit.SECONDS.toMillis(((CacheControl)localObject1).maxAgeSeconds()));
        }
        int i = ((CacheControl)localObject1).minFreshSeconds();
        long l4 = 0L;
        if (i != -1) {
          l2 = TimeUnit.SECONDS.toMillis(((CacheControl)localObject1).minFreshSeconds());
        } else {
          l2 = 0L;
        }
        long l3 = l4;
        if (!((CacheControl)localObject2).mustRevalidate())
        {
          l3 = l4;
          if (((CacheControl)localObject1).maxStaleSeconds() != -1) {
            l3 = TimeUnit.SECONDS.toMillis(((CacheControl)localObject1).maxStaleSeconds());
          }
        }
        if (!((CacheControl)localObject2).noCache())
        {
          l2 = l5 + l2;
          if (l2 < l1 + l3)
          {
            localObject1 = cacheResponse.newBuilder();
            if (l2 >= l1) {
              ((Response.Builder)localObject1).addHeader("Warning", "110 HttpURLConnection \"Response is stale\"");
            }
            if ((l5 > 86400000L) && (isFreshnessLifetimeHeuristic())) {
              ((Response.Builder)localObject1).addHeader("Warning", "113 HttpURLConnection \"Heuristic expiration\"");
            }
            return new CacheStrategy(null, ((Response.Builder)localObject1).build());
          }
        }
        if (etag != null)
        {
          localObject1 = "If-None-Match";
          localObject2 = etag;
        }
        else if (lastModified != null)
        {
          localObject1 = "If-Modified-Since";
          localObject2 = lastModifiedString;
        }
        else
        {
          if (servedDate == null) {
            break label457;
          }
          localObject1 = "If-Modified-Since";
          localObject2 = servedDateString;
        }
        Headers.Builder localBuilder = request.headers().newBuilder();
        Internal.instance.addLenient(localBuilder, (String)localObject1, (String)localObject2);
        return new CacheStrategy(request.newBuilder().headers(localBuilder.build()).build(), cacheResponse);
        label457:
        return new CacheStrategy(request, null);
      }
      return new CacheStrategy(request, null);
    }
    
    private static boolean hasConditions(Request paramRequest)
    {
      return (paramRequest.header("If-Modified-Since") != null) || (paramRequest.header("If-None-Match") != null);
    }
    
    private boolean isFreshnessLifetimeHeuristic()
    {
      return (cacheResponse.cacheControl().maxAgeSeconds() == -1) && (expires == null);
    }
    
    public CacheStrategy get()
    {
      CacheStrategy localCacheStrategy = getCandidate();
      if ((networkRequest != null) && (request.cacheControl().onlyIfCached())) {
        return new CacheStrategy(null, null);
      }
      return localCacheStrategy;
    }
  }
}
