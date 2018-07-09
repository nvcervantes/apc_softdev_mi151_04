package com.android.volley.toolbox;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolVersion;
import org.apache.http.StatusLine;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicStatusLine;

public class HurlStack
  implements HttpStack
{
  private static final String HEADER_CONTENT_TYPE = "Content-Type";
  private final SSLSocketFactory mSslSocketFactory;
  private final UrlRewriter mUrlRewriter;
  
  public HurlStack()
  {
    this(null);
  }
  
  public HurlStack(UrlRewriter paramUrlRewriter)
  {
    this(paramUrlRewriter, null);
  }
  
  public HurlStack(UrlRewriter paramUrlRewriter, SSLSocketFactory paramSSLSocketFactory)
  {
    mUrlRewriter = paramUrlRewriter;
    mSslSocketFactory = paramSSLSocketFactory;
  }
  
  private static void addBodyIfExists(HttpURLConnection paramHttpURLConnection, Request<?> paramRequest)
    throws IOException, AuthFailureError
  {
    byte[] arrayOfByte = paramRequest.getBody();
    if (arrayOfByte != null)
    {
      paramHttpURLConnection.setDoOutput(true);
      paramHttpURLConnection.addRequestProperty("Content-Type", paramRequest.getBodyContentType());
      paramHttpURLConnection = new DataOutputStream(paramHttpURLConnection.getOutputStream());
      paramHttpURLConnection.write(arrayOfByte);
      paramHttpURLConnection.close();
    }
  }
  
  private static HttpEntity entityFromConnection(HttpURLConnection paramHttpURLConnection)
  {
    BasicHttpEntity localBasicHttpEntity = new BasicHttpEntity();
    try
    {
      localInputStream = paramHttpURLConnection.getInputStream();
    }
    catch (IOException localIOException)
    {
      InputStream localInputStream;
      for (;;) {}
    }
    localInputStream = paramHttpURLConnection.getErrorStream();
    localBasicHttpEntity.setContent(localInputStream);
    localBasicHttpEntity.setContentLength(paramHttpURLConnection.getContentLength());
    localBasicHttpEntity.setContentEncoding(paramHttpURLConnection.getContentEncoding());
    localBasicHttpEntity.setContentType(paramHttpURLConnection.getContentType());
    return localBasicHttpEntity;
  }
  
  private static boolean hasResponseBody(int paramInt1, int paramInt2)
  {
    return (paramInt1 != 4) && ((100 > paramInt2) || (paramInt2 >= 200)) && (paramInt2 != 204) && (paramInt2 != 304);
  }
  
  private HttpURLConnection openConnection(URL paramURL, Request<?> paramRequest)
    throws IOException
  {
    HttpURLConnection localHttpURLConnection = createConnection(paramURL);
    int i = paramRequest.getTimeoutMs();
    localHttpURLConnection.setConnectTimeout(i);
    localHttpURLConnection.setReadTimeout(i);
    localHttpURLConnection.setUseCaches(false);
    localHttpURLConnection.setDoInput(true);
    if (("https".equals(paramURL.getProtocol())) && (mSslSocketFactory != null)) {
      ((HttpsURLConnection)localHttpURLConnection).setSSLSocketFactory(mSslSocketFactory);
    }
    return localHttpURLConnection;
  }
  
  static void setConnectionParametersForRequest(HttpURLConnection paramHttpURLConnection, Request<?> paramRequest)
    throws IOException, AuthFailureError
  {
    switch (paramRequest.getMethod())
    {
    default: 
      throw new IllegalStateException("Unknown method type.");
    case 7: 
      paramHttpURLConnection.setRequestMethod("PATCH");
      addBodyIfExists(paramHttpURLConnection, paramRequest);
      return;
    case 6: 
      paramHttpURLConnection.setRequestMethod("TRACE");
      return;
    case 5: 
      paramHttpURLConnection.setRequestMethod("OPTIONS");
      return;
    case 4: 
      paramHttpURLConnection.setRequestMethod("HEAD");
      return;
    case 3: 
      paramHttpURLConnection.setRequestMethod("DELETE");
      return;
    case 2: 
      paramHttpURLConnection.setRequestMethod("PUT");
      addBodyIfExists(paramHttpURLConnection, paramRequest);
      return;
    case 1: 
      paramHttpURLConnection.setRequestMethod("POST");
      addBodyIfExists(paramHttpURLConnection, paramRequest);
      return;
    case 0: 
      paramHttpURLConnection.setRequestMethod("GET");
      return;
    }
    byte[] arrayOfByte = paramRequest.getPostBody();
    if (arrayOfByte != null)
    {
      paramHttpURLConnection.setDoOutput(true);
      paramHttpURLConnection.setRequestMethod("POST");
      paramHttpURLConnection.addRequestProperty("Content-Type", paramRequest.getPostBodyContentType());
      paramHttpURLConnection = new DataOutputStream(paramHttpURLConnection.getOutputStream());
      paramHttpURLConnection.write(arrayOfByte);
      paramHttpURLConnection.close();
    }
  }
  
  protected HttpURLConnection createConnection(URL paramURL)
    throws IOException
  {
    paramURL = (HttpURLConnection)paramURL.openConnection();
    paramURL.setInstanceFollowRedirects(HttpURLConnection.getFollowRedirects());
    return paramURL;
  }
  
  public HttpResponse performRequest(Request<?> paramRequest, Map<String, String> paramMap)
    throws IOException, AuthFailureError
  {
    Object localObject1 = paramRequest.getUrl();
    HashMap localHashMap = new HashMap();
    localHashMap.putAll(paramRequest.getHeaders());
    localHashMap.putAll(paramMap);
    if (mUrlRewriter != null)
    {
      localObject2 = mUrlRewriter.rewriteUrl((String)localObject1);
      paramMap = (Map<String, String>)localObject2;
      if (localObject2 == null)
      {
        paramRequest = String.valueOf(localObject1);
        if (paramRequest.length() != 0) {
          paramRequest = "URL blocked by rewriter: ".concat(paramRequest);
        } else {
          paramRequest = new String("URL blocked by rewriter: ");
        }
        throw new IOException(paramRequest);
      }
    }
    else
    {
      paramMap = (Map<String, String>)localObject1;
    }
    paramMap = openConnection(new URL(paramMap), paramRequest);
    localObject1 = localHashMap.keySet().iterator();
    while (((Iterator)localObject1).hasNext())
    {
      localObject2 = (String)((Iterator)localObject1).next();
      paramMap.addRequestProperty((String)localObject2, (String)localHashMap.get(localObject2));
    }
    setConnectionParametersForRequest(paramMap, paramRequest);
    localObject1 = new ProtocolVersion("HTTP", 1, 1);
    if (paramMap.getResponseCode() == -1) {
      throw new IOException("Could not retrieve response code from HttpUrlConnection.");
    }
    Object localObject2 = new BasicStatusLine((ProtocolVersion)localObject1, paramMap.getResponseCode(), paramMap.getResponseMessage());
    localObject1 = new BasicHttpResponse((StatusLine)localObject2);
    if (hasResponseBody(paramRequest.getMethod(), ((StatusLine)localObject2).getStatusCode())) {
      ((BasicHttpResponse)localObject1).setEntity(entityFromConnection(paramMap));
    }
    paramRequest = paramMap.getHeaderFields().entrySet().iterator();
    while (paramRequest.hasNext())
    {
      paramMap = (Map.Entry)paramRequest.next();
      if (paramMap.getKey() != null) {
        ((BasicHttpResponse)localObject1).addHeader(new BasicHeader((String)paramMap.getKey(), (String)((List)paramMap.getValue()).get(0)));
      }
    }
    return localObject1;
  }
  
  public static abstract interface UrlRewriter
  {
    public abstract String rewriteUrl(String paramString);
  }
}
