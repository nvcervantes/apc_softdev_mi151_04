package okhttp3.internal.connection;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.ProxySelector;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import okhttp3.Address;
import okhttp3.Call;
import okhttp3.Dns;
import okhttp3.EventListener;
import okhttp3.HttpUrl;
import okhttp3.Route;
import okhttp3.internal.Util;

public final class RouteSelector
{
  private final Address address;
  private final Call call;
  private final EventListener eventListener;
  private List<InetSocketAddress> inetSocketAddresses = Collections.emptyList();
  private int nextProxyIndex;
  private final List<Route> postponedRoutes = new ArrayList();
  private List<Proxy> proxies = Collections.emptyList();
  private final RouteDatabase routeDatabase;
  
  public RouteSelector(Address paramAddress, RouteDatabase paramRouteDatabase, Call paramCall, EventListener paramEventListener)
  {
    address = paramAddress;
    routeDatabase = paramRouteDatabase;
    call = paramCall;
    eventListener = paramEventListener;
    resetNextProxy(paramAddress.url(), paramAddress.proxy());
  }
  
  static String getHostString(InetSocketAddress paramInetSocketAddress)
  {
    InetAddress localInetAddress = paramInetSocketAddress.getAddress();
    if (localInetAddress == null) {
      return paramInetSocketAddress.getHostName();
    }
    return localInetAddress.getHostAddress();
  }
  
  private boolean hasNextProxy()
  {
    return nextProxyIndex < proxies.size();
  }
  
  private Proxy nextProxy()
    throws IOException
  {
    if (!hasNextProxy())
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("No route to ");
      ((StringBuilder)localObject).append(address.url().host());
      ((StringBuilder)localObject).append("; exhausted proxy configurations: ");
      ((StringBuilder)localObject).append(proxies);
      throw new SocketException(((StringBuilder)localObject).toString());
    }
    Object localObject = proxies;
    int i = nextProxyIndex;
    nextProxyIndex = (i + 1);
    localObject = (Proxy)((List)localObject).get(i);
    resetNextInetSocketAddress((Proxy)localObject);
    return localObject;
  }
  
  private void resetNextInetSocketAddress(Proxy paramProxy)
    throws IOException
  {
    inetSocketAddresses = new ArrayList();
    Object localObject;
    int i;
    if ((paramProxy.type() != Proxy.Type.DIRECT) && (paramProxy.type() != Proxy.Type.SOCKS))
    {
      localObject = paramProxy.address();
      if (!(localObject instanceof InetSocketAddress))
      {
        paramProxy = new StringBuilder();
        paramProxy.append("Proxy.address() is not an InetSocketAddress: ");
        paramProxy.append(localObject.getClass());
        throw new IllegalArgumentException(paramProxy.toString());
      }
      InetSocketAddress localInetSocketAddress = (InetSocketAddress)localObject;
      localObject = getHostString(localInetSocketAddress);
      i = localInetSocketAddress.getPort();
    }
    else
    {
      localObject = address.url().host();
      i = address.url().port();
    }
    if ((i >= 1) && (i <= 65535))
    {
      if (paramProxy.type() == Proxy.Type.SOCKS)
      {
        inetSocketAddresses.add(InetSocketAddress.createUnresolved((String)localObject, i));
        return;
      }
      eventListener.dnsStart(call, (String)localObject);
      paramProxy = address.dns().lookup((String)localObject);
      if (paramProxy.isEmpty())
      {
        paramProxy = new StringBuilder();
        paramProxy.append(address.dns());
        paramProxy.append(" returned no addresses for ");
        paramProxy.append((String)localObject);
        throw new UnknownHostException(paramProxy.toString());
      }
      eventListener.dnsEnd(call, (String)localObject, paramProxy);
      int j = 0;
      int k = paramProxy.size();
      while (j < k)
      {
        localObject = (InetAddress)paramProxy.get(j);
        inetSocketAddresses.add(new InetSocketAddress((InetAddress)localObject, i));
        j += 1;
      }
      return;
    }
    paramProxy = new StringBuilder();
    paramProxy.append("No route to ");
    paramProxy.append((String)localObject);
    paramProxy.append(":");
    paramProxy.append(i);
    paramProxy.append("; port is out of range");
    throw new SocketException(paramProxy.toString());
  }
  
  private void resetNextProxy(HttpUrl paramHttpUrl, Proxy paramProxy)
  {
    if (paramProxy != null)
    {
      proxies = Collections.singletonList(paramProxy);
    }
    else
    {
      paramHttpUrl = address.proxySelector().select(paramHttpUrl.uri());
      if ((paramHttpUrl != null) && (!paramHttpUrl.isEmpty())) {
        paramHttpUrl = Util.immutableList(paramHttpUrl);
      } else {
        paramHttpUrl = Util.immutableList(new Proxy[] { Proxy.NO_PROXY });
      }
      proxies = paramHttpUrl;
    }
    nextProxyIndex = 0;
  }
  
  public void connectFailed(Route paramRoute, IOException paramIOException)
  {
    if ((paramRoute.proxy().type() != Proxy.Type.DIRECT) && (address.proxySelector() != null)) {
      address.proxySelector().connectFailed(address.url().uri(), paramRoute.proxy().address(), paramIOException);
    }
    routeDatabase.failed(paramRoute);
  }
  
  public boolean hasNext()
  {
    return (hasNextProxy()) || (!postponedRoutes.isEmpty());
  }
  
  public Selection next()
    throws IOException
  {
    if (!hasNext()) {
      throw new NoSuchElementException();
    }
    ArrayList localArrayList = new ArrayList();
    do
    {
      if (!hasNextProxy()) {
        break;
      }
      Proxy localProxy = nextProxy();
      int i = 0;
      int j = inetSocketAddresses.size();
      while (i < j)
      {
        Route localRoute = new Route(address, localProxy, (InetSocketAddress)inetSocketAddresses.get(i));
        if (routeDatabase.shouldPostpone(localRoute)) {
          postponedRoutes.add(localRoute);
        } else {
          localArrayList.add(localRoute);
        }
        i += 1;
      }
    } while (localArrayList.isEmpty());
    if (localArrayList.isEmpty())
    {
      localArrayList.addAll(postponedRoutes);
      postponedRoutes.clear();
    }
    return new Selection(localArrayList);
  }
  
  public static final class Selection
  {
    private int nextRouteIndex = 0;
    private final List<Route> routes;
    
    Selection(List<Route> paramList)
    {
      routes = paramList;
    }
    
    public List<Route> getAll()
    {
      return new ArrayList(routes);
    }
    
    public boolean hasNext()
    {
      return nextRouteIndex < routes.size();
    }
    
    public Route next()
    {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      List localList = routes;
      int i = nextRouteIndex;
      nextRouteIndex = (i + 1);
      return (Route)localList.get(i);
    }
  }
}
