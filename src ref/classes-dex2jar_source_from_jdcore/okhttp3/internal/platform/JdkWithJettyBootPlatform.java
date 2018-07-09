package okhttp3.internal.platform;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;
import javax.net.ssl.SSLSocket;
import okhttp3.Protocol;
import okhttp3.internal.Util;

class JdkWithJettyBootPlatform
  extends Platform
{
  private final Class<?> clientProviderClass;
  private final Method getMethod;
  private final Method putMethod;
  private final Method removeMethod;
  private final Class<?> serverProviderClass;
  
  JdkWithJettyBootPlatform(Method paramMethod1, Method paramMethod2, Method paramMethod3, Class<?> paramClass1, Class<?> paramClass2)
  {
    putMethod = paramMethod1;
    getMethod = paramMethod2;
    removeMethod = paramMethod3;
    clientProviderClass = paramClass1;
    serverProviderClass = paramClass2;
  }
  
  public static Platform buildIfSupported()
  {
    try
    {
      Object localObject1 = Class.forName("org.eclipse.jetty.alpn.ALPN");
      Object localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append("org.eclipse.jetty.alpn.ALPN");
      ((StringBuilder)localObject2).append("$Provider");
      localObject2 = Class.forName(((StringBuilder)localObject2).toString());
      Object localObject3 = new StringBuilder();
      ((StringBuilder)localObject3).append("org.eclipse.jetty.alpn.ALPN");
      ((StringBuilder)localObject3).append("$ClientProvider");
      localObject3 = Class.forName(((StringBuilder)localObject3).toString());
      Object localObject4 = new StringBuilder();
      ((StringBuilder)localObject4).append("org.eclipse.jetty.alpn.ALPN");
      ((StringBuilder)localObject4).append("$ServerProvider");
      localObject4 = Class.forName(((StringBuilder)localObject4).toString());
      localObject1 = new JdkWithJettyBootPlatform(((Class)localObject1).getMethod("put", new Class[] { SSLSocket.class, localObject2 }), ((Class)localObject1).getMethod("get", new Class[] { SSLSocket.class }), ((Class)localObject1).getMethod("remove", new Class[] { SSLSocket.class }), (Class)localObject3, (Class)localObject4);
      return localObject1;
    }
    catch (ClassNotFoundException|NoSuchMethodException localClassNotFoundException)
    {
      for (;;) {}
    }
    return null;
  }
  
  public void afterHandshake(SSLSocket paramSSLSocket)
  {
    try
    {
      removeMethod.invoke(null, new Object[] { paramSSLSocket });
      return;
    }
    catch (IllegalAccessException|InvocationTargetException paramSSLSocket)
    {
      throw Util.assertionError("unable to remove alpn", paramSSLSocket);
    }
  }
  
  public void configureTlsExtensions(SSLSocket paramSSLSocket, String paramString, List<Protocol> paramList)
  {
    Object localObject = alpnProtocolNames(paramList);
    try
    {
      paramString = Platform.class.getClassLoader();
      paramList = clientProviderClass;
      Class localClass = serverProviderClass;
      localObject = new JettyNegoProvider((List)localObject);
      paramString = Proxy.newProxyInstance(paramString, new Class[] { paramList, localClass }, (InvocationHandler)localObject);
      putMethod.invoke(null, new Object[] { paramSSLSocket, paramString });
      return;
    }
    catch (InvocationTargetException|IllegalAccessException paramSSLSocket)
    {
      throw Util.assertionError("unable to set alpn", paramSSLSocket);
    }
  }
  
  public String getSelectedProtocol(SSLSocket paramSSLSocket)
  {
    try
    {
      paramSSLSocket = (JettyNegoProvider)Proxy.getInvocationHandler(getMethod.invoke(null, new Object[] { paramSSLSocket }));
      if ((!unsupported) && (selected == null))
      {
        Platform.get().log(4, "ALPN callback dropped: HTTP/2 is disabled. Is alpn-boot on the boot class path?", null);
        return null;
      }
      if (unsupported) {
        return null;
      }
      paramSSLSocket = selected;
      return paramSSLSocket;
    }
    catch (InvocationTargetException|IllegalAccessException paramSSLSocket)
    {
      throw Util.assertionError("unable to get selected protocol", paramSSLSocket);
    }
  }
  
  private static class JettyNegoProvider
    implements InvocationHandler
  {
    private final List<String> protocols;
    String selected;
    boolean unsupported;
    
    JettyNegoProvider(List<String> paramList)
    {
      protocols = paramList;
    }
    
    public Object invoke(Object paramObject, Method paramMethod, Object[] paramArrayOfObject)
      throws Throwable
    {
      String str = paramMethod.getName();
      Class localClass = paramMethod.getReturnType();
      paramObject = paramArrayOfObject;
      if (paramArrayOfObject == null) {
        paramObject = Util.EMPTY_STRING_ARRAY;
      }
      if ((str.equals("supports")) && (Boolean.TYPE == localClass)) {
        return Boolean.valueOf(true);
      }
      if ((str.equals("unsupported")) && (Void.TYPE == localClass))
      {
        unsupported = true;
        return null;
      }
      if ((str.equals("protocols")) && (paramObject.length == 0)) {
        return protocols;
      }
      if (((str.equals("selectProtocol")) || (str.equals("select"))) && (String.class == localClass) && (paramObject.length == 1) && ((paramObject[0] instanceof List)))
      {
        paramObject = (List)paramObject[0];
        int j = paramObject.size();
        int i = 0;
        while (i < j)
        {
          if (protocols.contains(paramObject.get(i)))
          {
            paramObject = (String)paramObject.get(i);
            selected = paramObject;
            return paramObject;
          }
          i += 1;
        }
        paramObject = (String)protocols.get(0);
        selected = paramObject;
        return paramObject;
      }
      if (((str.equals("protocolSelected")) || (str.equals("selected"))) && (paramObject.length == 1))
      {
        selected = ((String)paramObject[0]);
        return null;
      }
      return paramMethod.invoke(this, paramObject);
    }
  }
}
