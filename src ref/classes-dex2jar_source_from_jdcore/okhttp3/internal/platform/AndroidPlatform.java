package okhttp3.internal.platform;

import android.util.Log;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.security.Security;
import java.security.cert.Certificate;
import java.security.cert.TrustAnchor;
import java.security.cert.X509Certificate;
import java.util.List;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
import okhttp3.Protocol;
import okhttp3.internal.Util;
import okhttp3.internal.tls.CertificateChainCleaner;
import okhttp3.internal.tls.TrustRootIndex;

class AndroidPlatform
  extends Platform
{
  private static final int MAX_LOG_LENGTH = 4000;
  private final CloseGuard closeGuard = CloseGuard.get();
  private final OptionalMethod<Socket> getAlpnSelectedProtocol;
  private final OptionalMethod<Socket> setAlpnProtocols;
  private final OptionalMethod<Socket> setHostname;
  private final OptionalMethod<Socket> setUseSessionTickets;
  private final Class<?> sslParametersClass;
  
  AndroidPlatform(Class<?> paramClass, OptionalMethod<Socket> paramOptionalMethod1, OptionalMethod<Socket> paramOptionalMethod2, OptionalMethod<Socket> paramOptionalMethod3, OptionalMethod<Socket> paramOptionalMethod4)
  {
    sslParametersClass = paramClass;
    setUseSessionTickets = paramOptionalMethod1;
    setHostname = paramOptionalMethod2;
    getAlpnSelectedProtocol = paramOptionalMethod3;
    setAlpnProtocols = paramOptionalMethod4;
  }
  
  private boolean api23IsCleartextTrafficPermitted(String paramString, Class<?> paramClass, Object paramObject)
    throws InvocationTargetException, IllegalAccessException
  {
    try
    {
      boolean bool = ((Boolean)paramClass.getMethod("isCleartextTrafficPermitted", new Class[0]).invoke(paramObject, new Object[0])).booleanValue();
      return bool;
    }
    catch (NoSuchMethodException paramClass)
    {
      for (;;) {}
    }
    return super.isCleartextTrafficPermitted(paramString);
  }
  
  private boolean api24IsCleartextTrafficPermitted(String paramString, Class<?> paramClass, Object paramObject)
    throws InvocationTargetException, IllegalAccessException
  {
    try
    {
      boolean bool = ((Boolean)paramClass.getMethod("isCleartextTrafficPermitted", new Class[] { String.class }).invoke(paramObject, new Object[] { paramString })).booleanValue();
      return bool;
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      for (;;) {}
    }
    return api23IsCleartextTrafficPermitted(paramString, paramClass, paramObject);
  }
  
  public static Platform buildIfSupported()
  {
    OptionalMethod localOptionalMethod1;
    for (;;)
    {
      try
      {
        localObject = Class.forName("com.android.org.conscrypt.SSLParametersImpl");
      }
      catch (ClassNotFoundException localClassNotFoundException1)
      {
        Object localObject;
        OptionalMethod localOptionalMethod3;
        OptionalMethod localOptionalMethod4;
        continue;
      }
      try
      {
        localObject = Class.forName("org.apache.harmony.xnet.provider.jsse.SSLParametersImpl");
      }
      catch (ClassNotFoundException localClassNotFoundException2)
      {
        return null;
      }
    }
    localOptionalMethod3 = new OptionalMethod(null, "setUseSessionTickets", new Class[] { Boolean.TYPE });
    localOptionalMethod4 = new OptionalMethod(null, "setHostname", new Class[] { String.class });
    OptionalMethod localOptionalMethod2;
    if (supportsAlpn())
    {
      localOptionalMethod1 = new OptionalMethod([B.class, "getAlpnSelectedProtocol", new Class[0]);
      localOptionalMethod2 = new OptionalMethod(null, "setAlpnProtocols", new Class[] { [B.class });
    }
    for (;;)
    {
      localObject = new AndroidPlatform((Class)localObject, localOptionalMethod3, localOptionalMethod4, localOptionalMethod1, localOptionalMethod2);
      return localObject;
      localOptionalMethod1 = null;
      localOptionalMethod2 = localOptionalMethod1;
    }
  }
  
  private static boolean supportsAlpn()
  {
    if (Security.getProvider("GMSCore_OpenSSL") != null) {
      return true;
    }
    try
    {
      Class.forName("android.net.Network");
      return true;
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      for (;;) {}
    }
    return false;
  }
  
  public CertificateChainCleaner buildCertificateChainCleaner(X509TrustManager paramX509TrustManager)
  {
    try
    {
      Object localObject = Class.forName("android.net.http.X509TrustManagerExtensions");
      localObject = new AndroidCertificateChainCleaner(((Class)localObject).getConstructor(new Class[] { X509TrustManager.class }).newInstance(new Object[] { paramX509TrustManager }), ((Class)localObject).getMethod("checkServerTrusted", new Class[] { [Ljava.security.cert.X509Certificate.class, String.class, String.class }));
      return localObject;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    return super.buildCertificateChainCleaner(paramX509TrustManager);
  }
  
  public TrustRootIndex buildTrustRootIndex(X509TrustManager paramX509TrustManager)
  {
    try
    {
      Object localObject = paramX509TrustManager.getClass().getDeclaredMethod("findTrustAnchorByIssuerAndSignature", new Class[] { X509Certificate.class });
      ((Method)localObject).setAccessible(true);
      localObject = new AndroidTrustRootIndex(paramX509TrustManager, (Method)localObject);
      return localObject;
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      for (;;) {}
    }
    return super.buildTrustRootIndex(paramX509TrustManager);
  }
  
  public void configureTlsExtensions(SSLSocket paramSSLSocket, String paramString, List<Protocol> paramList)
  {
    if (paramString != null)
    {
      setUseSessionTickets.invokeOptionalWithoutCheckedException(paramSSLSocket, new Object[] { Boolean.valueOf(true) });
      setHostname.invokeOptionalWithoutCheckedException(paramSSLSocket, new Object[] { paramString });
    }
    if ((setAlpnProtocols != null) && (setAlpnProtocols.isSupported(paramSSLSocket)))
    {
      paramString = concatLengthPrefixed(paramList);
      setAlpnProtocols.invokeWithoutCheckedException(paramSSLSocket, new Object[] { paramString });
    }
  }
  
  public void connectSocket(Socket paramSocket, InetSocketAddress paramInetSocketAddress, int paramInt)
    throws IOException
  {
    try
    {
      paramSocket.connect(paramInetSocketAddress, paramInt);
      return;
    }
    catch (SecurityException paramSocket)
    {
      paramInetSocketAddress = new IOException("Exception in connect");
      paramInetSocketAddress.initCause(paramSocket);
      throw paramInetSocketAddress;
    }
    catch (AssertionError paramSocket)
    {
      if (Util.isAndroidGetsocknameError(paramSocket)) {
        throw new IOException(paramSocket);
      }
      throw paramSocket;
    }
  }
  
  public String getSelectedProtocol(SSLSocket paramSSLSocket)
  {
    Object localObject2 = getAlpnSelectedProtocol;
    Object localObject1 = null;
    if (localObject2 == null) {
      return null;
    }
    if (!getAlpnSelectedProtocol.isSupported(paramSSLSocket)) {
      return null;
    }
    localObject2 = (byte[])getAlpnSelectedProtocol.invokeWithoutCheckedException(paramSSLSocket, new Object[0]);
    paramSSLSocket = localObject1;
    if (localObject2 != null) {
      paramSSLSocket = new String((byte[])localObject2, Util.UTF_8);
    }
    return paramSSLSocket;
  }
  
  public Object getStackTraceForCloseable(String paramString)
  {
    return closeGuard.createAndOpen(paramString);
  }
  
  public boolean isCleartextTrafficPermitted(String paramString)
  {
    try
    {
      Class localClass = Class.forName("android.security.NetworkSecurityPolicy");
      boolean bool = api24IsCleartextTrafficPermitted(paramString, localClass, localClass.getMethod("getInstance", new Class[0]).invoke(null, new Object[0]));
      return bool;
    }
    catch (IllegalAccessException|IllegalArgumentException|InvocationTargetException paramString)
    {
      throw Util.assertionError("unable to determine cleartext support", paramString);
      return super.isCleartextTrafficPermitted(paramString);
    }
    catch (ClassNotFoundException|NoSuchMethodException localClassNotFoundException)
    {
      for (;;) {}
    }
  }
  
  public void log(int paramInt, String paramString, Throwable paramThrowable)
  {
    int i = 5;
    if (paramInt != 5) {
      i = 3;
    }
    Object localObject = paramString;
    if (paramThrowable != null)
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append(paramString);
      ((StringBuilder)localObject).append('\n');
      ((StringBuilder)localObject).append(Log.getStackTraceString(paramThrowable));
      localObject = ((StringBuilder)localObject).toString();
    }
    paramInt = 0;
    int k = ((String)localObject).length();
    if (paramInt < k)
    {
      int j = ((String)localObject).indexOf('\n', paramInt);
      if (j == -1) {
        j = k;
      }
      for (;;)
      {
        int m = Math.min(j, paramInt + 4000);
        Log.println(i, "OkHttp", ((String)localObject).substring(paramInt, m));
        if (m >= j)
        {
          paramInt = m + 1;
          break;
        }
        paramInt = m;
      }
    }
  }
  
  public void logCloseableLeak(String paramString, Object paramObject)
  {
    if (!closeGuard.warnIfOpen(paramObject)) {
      log(5, paramString, null);
    }
  }
  
  public X509TrustManager trustManager(SSLSocketFactory paramSSLSocketFactory)
  {
    Object localObject2 = readFieldOrNull(paramSSLSocketFactory, sslParametersClass, "sslParameters");
    Object localObject1 = localObject2;
    if (localObject2 == null) {}
    try
    {
      localObject1 = readFieldOrNull(paramSSLSocketFactory, Class.forName("com.google.android.gms.org.conscrypt.SSLParametersImpl", false, paramSSLSocketFactory.getClass().getClassLoader()), "sslParameters");
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      for (;;) {}
    }
    return super.trustManager(paramSSLSocketFactory);
    paramSSLSocketFactory = (X509TrustManager)readFieldOrNull(localObject1, X509TrustManager.class, "x509TrustManager");
    if (paramSSLSocketFactory != null) {
      return paramSSLSocketFactory;
    }
    return (X509TrustManager)readFieldOrNull(localObject1, X509TrustManager.class, "trustManager");
  }
  
  static final class AndroidCertificateChainCleaner
    extends CertificateChainCleaner
  {
    private final Method checkServerTrusted;
    private final Object x509TrustManagerExtensions;
    
    AndroidCertificateChainCleaner(Object paramObject, Method paramMethod)
    {
      x509TrustManagerExtensions = paramObject;
      checkServerTrusted = paramMethod;
    }
    
    public List<Certificate> clean(List<Certificate> paramList, String paramString)
      throws SSLPeerUnverifiedException
    {
      try
      {
        paramList = (X509Certificate[])paramList.toArray(new X509Certificate[paramList.size()]);
        paramList = (List)checkServerTrusted.invoke(x509TrustManagerExtensions, new Object[] { paramList, "RSA", paramString });
        return paramList;
      }
      catch (IllegalAccessException paramList)
      {
        throw new AssertionError(paramList);
      }
      catch (InvocationTargetException paramList)
      {
        paramString = new SSLPeerUnverifiedException(paramList.getMessage());
        paramString.initCause(paramList);
        throw paramString;
      }
    }
    
    public boolean equals(Object paramObject)
    {
      return paramObject instanceof AndroidCertificateChainCleaner;
    }
    
    public int hashCode()
    {
      return 0;
    }
  }
  
  static final class AndroidTrustRootIndex
    implements TrustRootIndex
  {
    private final Method findByIssuerAndSignatureMethod;
    private final X509TrustManager trustManager;
    
    AndroidTrustRootIndex(X509TrustManager paramX509TrustManager, Method paramMethod)
    {
      findByIssuerAndSignatureMethod = paramMethod;
      trustManager = paramX509TrustManager;
    }
    
    public boolean equals(Object paramObject)
    {
      if (paramObject == this) {
        return true;
      }
      if (!(paramObject instanceof AndroidTrustRootIndex)) {
        return false;
      }
      paramObject = (AndroidTrustRootIndex)paramObject;
      return (trustManager.equals(trustManager)) && (findByIssuerAndSignatureMethod.equals(findByIssuerAndSignatureMethod));
    }
    
    public X509Certificate findByIssuerAndSignature(X509Certificate paramX509Certificate)
    {
      try
      {
        paramX509Certificate = (TrustAnchor)findByIssuerAndSignatureMethod.invoke(trustManager, new Object[] { paramX509Certificate });
        if (paramX509Certificate != null)
        {
          paramX509Certificate = paramX509Certificate.getTrustedCert();
          return paramX509Certificate;
        }
        return null;
      }
      catch (IllegalAccessException paramX509Certificate)
      {
        throw Util.assertionError("unable to get issues and signature", paramX509Certificate);
      }
      catch (InvocationTargetException paramX509Certificate) {}
      return null;
    }
    
    public int hashCode()
    {
      return trustManager.hashCode() + 31 * findByIssuerAndSignatureMethod.hashCode();
    }
  }
  
  static final class CloseGuard
  {
    private final Method getMethod;
    private final Method openMethod;
    private final Method warnIfOpenMethod;
    
    CloseGuard(Method paramMethod1, Method paramMethod2, Method paramMethod3)
    {
      getMethod = paramMethod1;
      openMethod = paramMethod2;
      warnIfOpenMethod = paramMethod3;
    }
    
    static CloseGuard get()
    {
      Object localObject1 = null;
      try
      {
        localObject2 = Class.forName("dalvik.system.CloseGuard");
        Method localMethod = ((Class)localObject2).getMethod("get", new Class[0]);
        localObject3 = ((Class)localObject2).getMethod("open", new Class[] { String.class });
        localObject2 = ((Class)localObject2).getMethod("warnIfOpen", new Class[0]);
        localObject1 = localMethod;
      }
      catch (Exception localException)
      {
        Object localObject2;
        Object localObject3;
        for (;;) {}
      }
      localObject2 = null;
      localObject3 = localObject2;
      return new CloseGuard(localObject1, (Method)localObject3, (Method)localObject2);
    }
    
    Object createAndOpen(String paramString)
    {
      if (getMethod != null) {}
      try
      {
        Object localObject = getMethod.invoke(null, new Object[0]);
        openMethod.invoke(localObject, new Object[] { paramString });
        return localObject;
      }
      catch (Exception paramString) {}
      return null;
      return null;
    }
    
    boolean warnIfOpen(Object paramObject)
    {
      boolean bool = false;
      if (paramObject != null) {}
      try
      {
        warnIfOpenMethod.invoke(paramObject, new Object[0]);
        bool = true;
        return bool;
      }
      catch (Exception paramObject) {}
      return false;
    }
  }
}
