package okhttp3;

import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import okhttp3.internal.Internal;
import okhttp3.internal.Util;
import okhttp3.internal.cache.InternalCache;
import okhttp3.internal.connection.RealConnection;
import okhttp3.internal.connection.RouteDatabase;
import okhttp3.internal.connection.StreamAllocation;
import okhttp3.internal.platform.Platform;
import okhttp3.internal.tls.CertificateChainCleaner;
import okhttp3.internal.tls.OkHostnameVerifier;
import okhttp3.internal.ws.RealWebSocket;

public class OkHttpClient
  implements Cloneable, Call.Factory, WebSocket.Factory
{
  static final List<ConnectionSpec> DEFAULT_CONNECTION_SPECS;
  static final List<Protocol> DEFAULT_PROTOCOLS = Util.immutableList(new Protocol[] { Protocol.HTTP_2, Protocol.HTTP_1_1 });
  final Authenticator authenticator;
  @Nullable
  final Cache cache;
  @Nullable
  final CertificateChainCleaner certificateChainCleaner;
  final CertificatePinner certificatePinner;
  final int connectTimeout;
  final ConnectionPool connectionPool;
  final List<ConnectionSpec> connectionSpecs;
  final CookieJar cookieJar;
  final Dispatcher dispatcher;
  final Dns dns;
  final EventListener.Factory eventListenerFactory;
  final boolean followRedirects;
  final boolean followSslRedirects;
  final HostnameVerifier hostnameVerifier;
  final List<Interceptor> interceptors;
  @Nullable
  final InternalCache internalCache;
  final List<Interceptor> networkInterceptors;
  final int pingInterval;
  final List<Protocol> protocols;
  @Nullable
  final Proxy proxy;
  final Authenticator proxyAuthenticator;
  final ProxySelector proxySelector;
  final int readTimeout;
  final boolean retryOnConnectionFailure;
  final SocketFactory socketFactory;
  @Nullable
  final SSLSocketFactory sslSocketFactory;
  final int writeTimeout;
  
  static
  {
    DEFAULT_CONNECTION_SPECS = Util.immutableList(new ConnectionSpec[] { ConnectionSpec.MODERN_TLS, ConnectionSpec.CLEARTEXT });
    Internal.instance = new Internal()
    {
      public void addLenient(Headers.Builder paramAnonymousBuilder, String paramAnonymousString)
      {
        paramAnonymousBuilder.addLenient(paramAnonymousString);
      }
      
      public void addLenient(Headers.Builder paramAnonymousBuilder, String paramAnonymousString1, String paramAnonymousString2)
      {
        paramAnonymousBuilder.addLenient(paramAnonymousString1, paramAnonymousString2);
      }
      
      public void apply(ConnectionSpec paramAnonymousConnectionSpec, SSLSocket paramAnonymousSSLSocket, boolean paramAnonymousBoolean)
      {
        paramAnonymousConnectionSpec.apply(paramAnonymousSSLSocket, paramAnonymousBoolean);
      }
      
      public int code(Response.Builder paramAnonymousBuilder)
      {
        return code;
      }
      
      public boolean connectionBecameIdle(ConnectionPool paramAnonymousConnectionPool, RealConnection paramAnonymousRealConnection)
      {
        return paramAnonymousConnectionPool.connectionBecameIdle(paramAnonymousRealConnection);
      }
      
      public Socket deduplicate(ConnectionPool paramAnonymousConnectionPool, Address paramAnonymousAddress, StreamAllocation paramAnonymousStreamAllocation)
      {
        return paramAnonymousConnectionPool.deduplicate(paramAnonymousAddress, paramAnonymousStreamAllocation);
      }
      
      public boolean equalsNonHost(Address paramAnonymousAddress1, Address paramAnonymousAddress2)
      {
        return paramAnonymousAddress1.equalsNonHost(paramAnonymousAddress2);
      }
      
      public RealConnection get(ConnectionPool paramAnonymousConnectionPool, Address paramAnonymousAddress, StreamAllocation paramAnonymousStreamAllocation, Route paramAnonymousRoute)
      {
        return paramAnonymousConnectionPool.get(paramAnonymousAddress, paramAnonymousStreamAllocation, paramAnonymousRoute);
      }
      
      public HttpUrl getHttpUrlChecked(String paramAnonymousString)
        throws MalformedURLException, UnknownHostException
      {
        return HttpUrl.getChecked(paramAnonymousString);
      }
      
      public Call newWebSocketCall(OkHttpClient paramAnonymousOkHttpClient, Request paramAnonymousRequest)
      {
        return RealCall.newRealCall(paramAnonymousOkHttpClient, paramAnonymousRequest, true);
      }
      
      public void put(ConnectionPool paramAnonymousConnectionPool, RealConnection paramAnonymousRealConnection)
      {
        paramAnonymousConnectionPool.put(paramAnonymousRealConnection);
      }
      
      public RouteDatabase routeDatabase(ConnectionPool paramAnonymousConnectionPool)
      {
        return routeDatabase;
      }
      
      public void setCache(OkHttpClient.Builder paramAnonymousBuilder, InternalCache paramAnonymousInternalCache)
      {
        paramAnonymousBuilder.setInternalCache(paramAnonymousInternalCache);
      }
      
      public StreamAllocation streamAllocation(Call paramAnonymousCall)
      {
        return ((RealCall)paramAnonymousCall).streamAllocation();
      }
    };
  }
  
  public OkHttpClient()
  {
    this(new Builder());
  }
  
  OkHttpClient(Builder paramBuilder)
  {
    dispatcher = dispatcher;
    proxy = proxy;
    protocols = protocols;
    connectionSpecs = connectionSpecs;
    interceptors = Util.immutableList(interceptors);
    networkInterceptors = Util.immutableList(networkInterceptors);
    eventListenerFactory = eventListenerFactory;
    proxySelector = proxySelector;
    cookieJar = cookieJar;
    cache = cache;
    internalCache = internalCache;
    socketFactory = socketFactory;
    Object localObject = connectionSpecs.iterator();
    for (int i = 0;; i = 1)
    {
      if (!((Iterator)localObject).hasNext()) {
        break label155;
      }
      ConnectionSpec localConnectionSpec = (ConnectionSpec)((Iterator)localObject).next();
      if ((i == 0) && (!localConnectionSpec.isTls())) {
        break;
      }
    }
    label155:
    if ((sslSocketFactory == null) && (i != 0))
    {
      localObject = systemDefaultTrustManager();
      sslSocketFactory = systemDefaultSslSocketFactory((X509TrustManager)localObject);
      certificateChainCleaner = CertificateChainCleaner.get((X509TrustManager)localObject);
    }
    else
    {
      sslSocketFactory = sslSocketFactory;
      certificateChainCleaner = certificateChainCleaner;
    }
    hostnameVerifier = hostnameVerifier;
    certificatePinner = certificatePinner.withCertificateChainCleaner(certificateChainCleaner);
    proxyAuthenticator = proxyAuthenticator;
    authenticator = authenticator;
    connectionPool = connectionPool;
    dns = dns;
    followSslRedirects = followSslRedirects;
    followRedirects = followRedirects;
    retryOnConnectionFailure = retryOnConnectionFailure;
    connectTimeout = connectTimeout;
    readTimeout = readTimeout;
    writeTimeout = writeTimeout;
    pingInterval = pingInterval;
    if (interceptors.contains(null))
    {
      paramBuilder = new StringBuilder();
      paramBuilder.append("Null interceptor: ");
      paramBuilder.append(interceptors);
      throw new IllegalStateException(paramBuilder.toString());
    }
    if (networkInterceptors.contains(null))
    {
      paramBuilder = new StringBuilder();
      paramBuilder.append("Null network interceptor: ");
      paramBuilder.append(networkInterceptors);
      throw new IllegalStateException(paramBuilder.toString());
    }
  }
  
  private SSLSocketFactory systemDefaultSslSocketFactory(X509TrustManager paramX509TrustManager)
  {
    try
    {
      SSLContext localSSLContext = SSLContext.getInstance("TLS");
      localSSLContext.init(null, new TrustManager[] { paramX509TrustManager }, null);
      paramX509TrustManager = localSSLContext.getSocketFactory();
      return paramX509TrustManager;
    }
    catch (GeneralSecurityException paramX509TrustManager)
    {
      throw Util.assertionError("No System TLS", paramX509TrustManager);
    }
  }
  
  private X509TrustManager systemDefaultTrustManager()
  {
    try
    {
      Object localObject = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
      ((TrustManagerFactory)localObject).init((KeyStore)null);
      localObject = ((TrustManagerFactory)localObject).getTrustManagers();
      if ((localObject.length == 1) && ((localObject[0] instanceof X509TrustManager))) {
        return (X509TrustManager)localObject[0];
      }
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Unexpected default trust managers:");
      localStringBuilder.append(Arrays.toString((Object[])localObject));
      throw new IllegalStateException(localStringBuilder.toString());
    }
    catch (GeneralSecurityException localGeneralSecurityException)
    {
      throw Util.assertionError("No System TLS", localGeneralSecurityException);
    }
  }
  
  public Authenticator authenticator()
  {
    return authenticator;
  }
  
  public Cache cache()
  {
    return cache;
  }
  
  public CertificatePinner certificatePinner()
  {
    return certificatePinner;
  }
  
  public int connectTimeoutMillis()
  {
    return connectTimeout;
  }
  
  public ConnectionPool connectionPool()
  {
    return connectionPool;
  }
  
  public List<ConnectionSpec> connectionSpecs()
  {
    return connectionSpecs;
  }
  
  public CookieJar cookieJar()
  {
    return cookieJar;
  }
  
  public Dispatcher dispatcher()
  {
    return dispatcher;
  }
  
  public Dns dns()
  {
    return dns;
  }
  
  public EventListener.Factory eventListenerFactory()
  {
    return eventListenerFactory;
  }
  
  public boolean followRedirects()
  {
    return followRedirects;
  }
  
  public boolean followSslRedirects()
  {
    return followSslRedirects;
  }
  
  public HostnameVerifier hostnameVerifier()
  {
    return hostnameVerifier;
  }
  
  public List<Interceptor> interceptors()
  {
    return interceptors;
  }
  
  InternalCache internalCache()
  {
    if (cache != null) {
      return cache.internalCache;
    }
    return internalCache;
  }
  
  public List<Interceptor> networkInterceptors()
  {
    return networkInterceptors;
  }
  
  public Builder newBuilder()
  {
    return new Builder(this);
  }
  
  public Call newCall(Request paramRequest)
  {
    return RealCall.newRealCall(this, paramRequest, false);
  }
  
  public WebSocket newWebSocket(Request paramRequest, WebSocketListener paramWebSocketListener)
  {
    paramRequest = new RealWebSocket(paramRequest, paramWebSocketListener, new Random());
    paramRequest.connect(this);
    return paramRequest;
  }
  
  public int pingIntervalMillis()
  {
    return pingInterval;
  }
  
  public List<Protocol> protocols()
  {
    return protocols;
  }
  
  public Proxy proxy()
  {
    return proxy;
  }
  
  public Authenticator proxyAuthenticator()
  {
    return proxyAuthenticator;
  }
  
  public ProxySelector proxySelector()
  {
    return proxySelector;
  }
  
  public int readTimeoutMillis()
  {
    return readTimeout;
  }
  
  public boolean retryOnConnectionFailure()
  {
    return retryOnConnectionFailure;
  }
  
  public SocketFactory socketFactory()
  {
    return socketFactory;
  }
  
  public SSLSocketFactory sslSocketFactory()
  {
    return sslSocketFactory;
  }
  
  public int writeTimeoutMillis()
  {
    return writeTimeout;
  }
  
  public static final class Builder
  {
    Authenticator authenticator;
    @Nullable
    Cache cache;
    @Nullable
    CertificateChainCleaner certificateChainCleaner;
    CertificatePinner certificatePinner;
    int connectTimeout;
    ConnectionPool connectionPool;
    List<ConnectionSpec> connectionSpecs;
    CookieJar cookieJar;
    Dispatcher dispatcher;
    Dns dns;
    EventListener.Factory eventListenerFactory;
    boolean followRedirects;
    boolean followSslRedirects;
    HostnameVerifier hostnameVerifier;
    final List<Interceptor> interceptors = new ArrayList();
    @Nullable
    InternalCache internalCache;
    final List<Interceptor> networkInterceptors = new ArrayList();
    int pingInterval;
    List<Protocol> protocols;
    @Nullable
    Proxy proxy;
    Authenticator proxyAuthenticator;
    ProxySelector proxySelector;
    int readTimeout;
    boolean retryOnConnectionFailure;
    SocketFactory socketFactory;
    @Nullable
    SSLSocketFactory sslSocketFactory;
    int writeTimeout;
    
    public Builder()
    {
      dispatcher = new Dispatcher();
      protocols = OkHttpClient.DEFAULT_PROTOCOLS;
      connectionSpecs = OkHttpClient.DEFAULT_CONNECTION_SPECS;
      eventListenerFactory = EventListener.factory(EventListener.NONE);
      proxySelector = ProxySelector.getDefault();
      cookieJar = CookieJar.NO_COOKIES;
      socketFactory = SocketFactory.getDefault();
      hostnameVerifier = OkHostnameVerifier.INSTANCE;
      certificatePinner = CertificatePinner.DEFAULT;
      proxyAuthenticator = Authenticator.NONE;
      authenticator = Authenticator.NONE;
      connectionPool = new ConnectionPool();
      dns = Dns.SYSTEM;
      followSslRedirects = true;
      followRedirects = true;
      retryOnConnectionFailure = true;
      connectTimeout = 10000;
      readTimeout = 10000;
      writeTimeout = 10000;
      pingInterval = 0;
    }
    
    Builder(OkHttpClient paramOkHttpClient)
    {
      dispatcher = dispatcher;
      proxy = proxy;
      protocols = protocols;
      connectionSpecs = connectionSpecs;
      interceptors.addAll(interceptors);
      networkInterceptors.addAll(networkInterceptors);
      eventListenerFactory = eventListenerFactory;
      proxySelector = proxySelector;
      cookieJar = cookieJar;
      internalCache = internalCache;
      cache = cache;
      socketFactory = socketFactory;
      sslSocketFactory = sslSocketFactory;
      certificateChainCleaner = certificateChainCleaner;
      hostnameVerifier = hostnameVerifier;
      certificatePinner = certificatePinner;
      proxyAuthenticator = proxyAuthenticator;
      authenticator = authenticator;
      connectionPool = connectionPool;
      dns = dns;
      followSslRedirects = followSslRedirects;
      followRedirects = followRedirects;
      retryOnConnectionFailure = retryOnConnectionFailure;
      connectTimeout = connectTimeout;
      readTimeout = readTimeout;
      writeTimeout = writeTimeout;
      pingInterval = pingInterval;
    }
    
    public Builder addInterceptor(Interceptor paramInterceptor)
    {
      if (paramInterceptor == null) {
        throw new IllegalArgumentException("interceptor == null");
      }
      interceptors.add(paramInterceptor);
      return this;
    }
    
    public Builder addNetworkInterceptor(Interceptor paramInterceptor)
    {
      if (paramInterceptor == null) {
        throw new IllegalArgumentException("interceptor == null");
      }
      networkInterceptors.add(paramInterceptor);
      return this;
    }
    
    public Builder authenticator(Authenticator paramAuthenticator)
    {
      if (paramAuthenticator == null) {
        throw new NullPointerException("authenticator == null");
      }
      authenticator = paramAuthenticator;
      return this;
    }
    
    public OkHttpClient build()
    {
      return new OkHttpClient(this);
    }
    
    public Builder cache(@Nullable Cache paramCache)
    {
      cache = paramCache;
      internalCache = null;
      return this;
    }
    
    public Builder certificatePinner(CertificatePinner paramCertificatePinner)
    {
      if (paramCertificatePinner == null) {
        throw new NullPointerException("certificatePinner == null");
      }
      certificatePinner = paramCertificatePinner;
      return this;
    }
    
    public Builder connectTimeout(long paramLong, TimeUnit paramTimeUnit)
    {
      connectTimeout = Util.checkDuration("timeout", paramLong, paramTimeUnit);
      return this;
    }
    
    public Builder connectionPool(ConnectionPool paramConnectionPool)
    {
      if (paramConnectionPool == null) {
        throw new NullPointerException("connectionPool == null");
      }
      connectionPool = paramConnectionPool;
      return this;
    }
    
    public Builder connectionSpecs(List<ConnectionSpec> paramList)
    {
      connectionSpecs = Util.immutableList(paramList);
      return this;
    }
    
    public Builder cookieJar(CookieJar paramCookieJar)
    {
      if (paramCookieJar == null) {
        throw new NullPointerException("cookieJar == null");
      }
      cookieJar = paramCookieJar;
      return this;
    }
    
    public Builder dispatcher(Dispatcher paramDispatcher)
    {
      if (paramDispatcher == null) {
        throw new IllegalArgumentException("dispatcher == null");
      }
      dispatcher = paramDispatcher;
      return this;
    }
    
    public Builder dns(Dns paramDns)
    {
      if (paramDns == null) {
        throw new NullPointerException("dns == null");
      }
      dns = paramDns;
      return this;
    }
    
    public Builder eventListener(EventListener paramEventListener)
    {
      if (paramEventListener == null) {
        throw new NullPointerException("eventListener == null");
      }
      eventListenerFactory = EventListener.factory(paramEventListener);
      return this;
    }
    
    public Builder eventListenerFactory(EventListener.Factory paramFactory)
    {
      if (paramFactory == null) {
        throw new NullPointerException("eventListenerFactory == null");
      }
      eventListenerFactory = paramFactory;
      return this;
    }
    
    public Builder followRedirects(boolean paramBoolean)
    {
      followRedirects = paramBoolean;
      return this;
    }
    
    public Builder followSslRedirects(boolean paramBoolean)
    {
      followSslRedirects = paramBoolean;
      return this;
    }
    
    public Builder hostnameVerifier(HostnameVerifier paramHostnameVerifier)
    {
      if (paramHostnameVerifier == null) {
        throw new NullPointerException("hostnameVerifier == null");
      }
      hostnameVerifier = paramHostnameVerifier;
      return this;
    }
    
    public List<Interceptor> interceptors()
    {
      return interceptors;
    }
    
    public List<Interceptor> networkInterceptors()
    {
      return networkInterceptors;
    }
    
    public Builder pingInterval(long paramLong, TimeUnit paramTimeUnit)
    {
      pingInterval = Util.checkDuration("interval", paramLong, paramTimeUnit);
      return this;
    }
    
    public Builder protocols(List<Protocol> paramList)
    {
      paramList = new ArrayList(paramList);
      StringBuilder localStringBuilder;
      if (!paramList.contains(Protocol.HTTP_1_1))
      {
        localStringBuilder = new StringBuilder();
        localStringBuilder.append("protocols doesn't contain http/1.1: ");
        localStringBuilder.append(paramList);
        throw new IllegalArgumentException(localStringBuilder.toString());
      }
      if (paramList.contains(Protocol.HTTP_1_0))
      {
        localStringBuilder = new StringBuilder();
        localStringBuilder.append("protocols must not contain http/1.0: ");
        localStringBuilder.append(paramList);
        throw new IllegalArgumentException(localStringBuilder.toString());
      }
      if (paramList.contains(null)) {
        throw new IllegalArgumentException("protocols must not contain null");
      }
      paramList.remove(Protocol.SPDY_3);
      protocols = Collections.unmodifiableList(paramList);
      return this;
    }
    
    public Builder proxy(@Nullable Proxy paramProxy)
    {
      proxy = paramProxy;
      return this;
    }
    
    public Builder proxyAuthenticator(Authenticator paramAuthenticator)
    {
      if (paramAuthenticator == null) {
        throw new NullPointerException("proxyAuthenticator == null");
      }
      proxyAuthenticator = paramAuthenticator;
      return this;
    }
    
    public Builder proxySelector(ProxySelector paramProxySelector)
    {
      proxySelector = paramProxySelector;
      return this;
    }
    
    public Builder readTimeout(long paramLong, TimeUnit paramTimeUnit)
    {
      readTimeout = Util.checkDuration("timeout", paramLong, paramTimeUnit);
      return this;
    }
    
    public Builder retryOnConnectionFailure(boolean paramBoolean)
    {
      retryOnConnectionFailure = paramBoolean;
      return this;
    }
    
    void setInternalCache(@Nullable InternalCache paramInternalCache)
    {
      internalCache = paramInternalCache;
      cache = null;
    }
    
    public Builder socketFactory(SocketFactory paramSocketFactory)
    {
      if (paramSocketFactory == null) {
        throw new NullPointerException("socketFactory == null");
      }
      socketFactory = paramSocketFactory;
      return this;
    }
    
    public Builder sslSocketFactory(SSLSocketFactory paramSSLSocketFactory)
    {
      if (paramSSLSocketFactory == null) {
        throw new NullPointerException("sslSocketFactory == null");
      }
      Object localObject = Platform.get().trustManager(paramSSLSocketFactory);
      if (localObject == null)
      {
        localObject = new StringBuilder();
        ((StringBuilder)localObject).append("Unable to extract the trust manager on ");
        ((StringBuilder)localObject).append(Platform.get());
        ((StringBuilder)localObject).append(", sslSocketFactory is ");
        ((StringBuilder)localObject).append(paramSSLSocketFactory.getClass());
        throw new IllegalStateException(((StringBuilder)localObject).toString());
      }
      sslSocketFactory = paramSSLSocketFactory;
      certificateChainCleaner = CertificateChainCleaner.get((X509TrustManager)localObject);
      return this;
    }
    
    public Builder sslSocketFactory(SSLSocketFactory paramSSLSocketFactory, X509TrustManager paramX509TrustManager)
    {
      if (paramSSLSocketFactory == null) {
        throw new NullPointerException("sslSocketFactory == null");
      }
      if (paramX509TrustManager == null) {
        throw new NullPointerException("trustManager == null");
      }
      sslSocketFactory = paramSSLSocketFactory;
      certificateChainCleaner = CertificateChainCleaner.get(paramX509TrustManager);
      return this;
    }
    
    public Builder writeTimeout(long paramLong, TimeUnit paramTimeUnit)
    {
      writeTimeout = Util.checkDuration("timeout", paramLong, paramTimeUnit);
      return this;
    }
  }
}
