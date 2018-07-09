package okhttp3;

import java.net.Proxy;
import java.net.ProxySelector;
import java.util.List;
import javax.annotation.Nullable;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;
import okhttp3.internal.Util;

public final class Address
{
  @Nullable
  final CertificatePinner certificatePinner;
  final List<ConnectionSpec> connectionSpecs;
  final Dns dns;
  @Nullable
  final HostnameVerifier hostnameVerifier;
  final List<Protocol> protocols;
  @Nullable
  final Proxy proxy;
  final Authenticator proxyAuthenticator;
  final ProxySelector proxySelector;
  final SocketFactory socketFactory;
  @Nullable
  final SSLSocketFactory sslSocketFactory;
  final HttpUrl url;
  
  public Address(String paramString, int paramInt, Dns paramDns, SocketFactory paramSocketFactory, @Nullable SSLSocketFactory paramSSLSocketFactory, @Nullable HostnameVerifier paramHostnameVerifier, @Nullable CertificatePinner paramCertificatePinner, Authenticator paramAuthenticator, @Nullable Proxy paramProxy, List<Protocol> paramList, List<ConnectionSpec> paramList1, ProxySelector paramProxySelector)
  {
    HttpUrl.Builder localBuilder = new HttpUrl.Builder();
    String str;
    if (paramSSLSocketFactory != null) {
      str = "https";
    } else {
      str = "http";
    }
    url = localBuilder.scheme(str).host(paramString).port(paramInt).build();
    if (paramDns == null) {
      throw new NullPointerException("dns == null");
    }
    dns = paramDns;
    if (paramSocketFactory == null) {
      throw new NullPointerException("socketFactory == null");
    }
    socketFactory = paramSocketFactory;
    if (paramAuthenticator == null) {
      throw new NullPointerException("proxyAuthenticator == null");
    }
    proxyAuthenticator = paramAuthenticator;
    if (paramList == null) {
      throw new NullPointerException("protocols == null");
    }
    protocols = Util.immutableList(paramList);
    if (paramList1 == null) {
      throw new NullPointerException("connectionSpecs == null");
    }
    connectionSpecs = Util.immutableList(paramList1);
    if (paramProxySelector == null) {
      throw new NullPointerException("proxySelector == null");
    }
    proxySelector = paramProxySelector;
    proxy = paramProxy;
    sslSocketFactory = paramSSLSocketFactory;
    hostnameVerifier = paramHostnameVerifier;
    certificatePinner = paramCertificatePinner;
  }
  
  @Nullable
  public CertificatePinner certificatePinner()
  {
    return certificatePinner;
  }
  
  public List<ConnectionSpec> connectionSpecs()
  {
    return connectionSpecs;
  }
  
  public Dns dns()
  {
    return dns;
  }
  
  public boolean equals(@Nullable Object paramObject)
  {
    if ((paramObject instanceof Address))
    {
      HttpUrl localHttpUrl = url;
      paramObject = (Address)paramObject;
      if ((localHttpUrl.equals(url)) && (equalsNonHost(paramObject))) {
        return true;
      }
    }
    return false;
  }
  
  boolean equalsNonHost(Address paramAddress)
  {
    return (dns.equals(dns)) && (proxyAuthenticator.equals(proxyAuthenticator)) && (protocols.equals(protocols)) && (connectionSpecs.equals(connectionSpecs)) && (proxySelector.equals(proxySelector)) && (Util.equal(proxy, proxy)) && (Util.equal(sslSocketFactory, sslSocketFactory)) && (Util.equal(hostnameVerifier, hostnameVerifier)) && (Util.equal(certificatePinner, certificatePinner)) && (url().port() == paramAddress.url().port());
  }
  
  public int hashCode()
  {
    int n = url.hashCode();
    int i1 = dns.hashCode();
    int i2 = proxyAuthenticator.hashCode();
    int i3 = protocols.hashCode();
    int i4 = connectionSpecs.hashCode();
    int i5 = proxySelector.hashCode();
    Proxy localProxy = proxy;
    int m = 0;
    int i;
    if (localProxy != null) {
      i = proxy.hashCode();
    } else {
      i = 0;
    }
    int j;
    if (sslSocketFactory != null) {
      j = sslSocketFactory.hashCode();
    } else {
      j = 0;
    }
    int k;
    if (hostnameVerifier != null) {
      k = hostnameVerifier.hashCode();
    } else {
      k = 0;
    }
    if (certificatePinner != null) {
      m = certificatePinner.hashCode();
    }
    return 31 * (((((((((527 + n) * 31 + i1) * 31 + i2) * 31 + i3) * 31 + i4) * 31 + i5) * 31 + i) * 31 + j) * 31 + k) + m;
  }
  
  @Nullable
  public HostnameVerifier hostnameVerifier()
  {
    return hostnameVerifier;
  }
  
  public List<Protocol> protocols()
  {
    return protocols;
  }
  
  @Nullable
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
  
  public SocketFactory socketFactory()
  {
    return socketFactory;
  }
  
  @Nullable
  public SSLSocketFactory sslSocketFactory()
  {
    return sslSocketFactory;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Address{");
    localStringBuilder.append(url.host());
    localStringBuilder.append(":");
    localStringBuilder.append(url.port());
    if (proxy != null)
    {
      localStringBuilder.append(", proxy=");
      localStringBuilder.append(proxy);
    }
    else
    {
      localStringBuilder.append(", proxySelector=");
      localStringBuilder.append(proxySelector);
    }
    localStringBuilder.append("}");
    return localStringBuilder.toString();
  }
  
  public HttpUrl url()
  {
    return url;
  }
}
