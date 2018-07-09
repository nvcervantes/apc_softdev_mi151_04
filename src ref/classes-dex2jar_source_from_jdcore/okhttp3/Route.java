package okhttp3;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import javax.annotation.Nullable;

public final class Route
{
  final Address address;
  final InetSocketAddress inetSocketAddress;
  final Proxy proxy;
  
  public Route(Address paramAddress, Proxy paramProxy, InetSocketAddress paramInetSocketAddress)
  {
    if (paramAddress == null) {
      throw new NullPointerException("address == null");
    }
    if (paramProxy == null) {
      throw new NullPointerException("proxy == null");
    }
    if (paramInetSocketAddress == null) {
      throw new NullPointerException("inetSocketAddress == null");
    }
    address = paramAddress;
    proxy = paramProxy;
    inetSocketAddress = paramInetSocketAddress;
  }
  
  public Address address()
  {
    return address;
  }
  
  public boolean equals(@Nullable Object paramObject)
  {
    if ((paramObject instanceof Route))
    {
      paramObject = (Route)paramObject;
      if ((address.equals(address)) && (proxy.equals(proxy)) && (inetSocketAddress.equals(inetSocketAddress))) {
        return true;
      }
    }
    return false;
  }
  
  public int hashCode()
  {
    return 31 * ((527 + address.hashCode()) * 31 + proxy.hashCode()) + inetSocketAddress.hashCode();
  }
  
  public Proxy proxy()
  {
    return proxy;
  }
  
  public boolean requiresTunnel()
  {
    return (address.sslSocketFactory != null) && (proxy.type() == Proxy.Type.HTTP);
  }
  
  public InetSocketAddress socketAddress()
  {
    return inetSocketAddress;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Route{");
    localStringBuilder.append(inetSocketAddress);
    localStringBuilder.append("}");
    return localStringBuilder.toString();
  }
}
