package okhttp3;

import java.security.Principal;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import okhttp3.internal.Util;

public final class Handshake
{
  private final CipherSuite cipherSuite;
  private final List<Certificate> localCertificates;
  private final List<Certificate> peerCertificates;
  private final TlsVersion tlsVersion;
  
  private Handshake(TlsVersion paramTlsVersion, CipherSuite paramCipherSuite, List<Certificate> paramList1, List<Certificate> paramList2)
  {
    tlsVersion = paramTlsVersion;
    cipherSuite = paramCipherSuite;
    peerCertificates = paramList1;
    localCertificates = paramList2;
  }
  
  public static Handshake get(SSLSession paramSSLSession)
  {
    Object localObject = paramSSLSession.getCipherSuite();
    if (localObject == null) {
      throw new IllegalStateException("cipherSuite == null");
    }
    CipherSuite localCipherSuite = CipherSuite.forJavaName((String)localObject);
    localObject = paramSSLSession.getProtocol();
    if (localObject == null) {
      throw new IllegalStateException("tlsVersion == null");
    }
    TlsVersion localTlsVersion = TlsVersion.forJavaName((String)localObject);
    try
    {
      localObject = paramSSLSession.getPeerCertificates();
    }
    catch (SSLPeerUnverifiedException localSSLPeerUnverifiedException)
    {
      for (;;) {}
    }
    localObject = null;
    if (localObject != null) {
      localObject = Util.immutableList((Object[])localObject);
    } else {
      localObject = Collections.emptyList();
    }
    paramSSLSession = paramSSLSession.getLocalCertificates();
    if (paramSSLSession != null) {
      paramSSLSession = Util.immutableList(paramSSLSession);
    } else {
      paramSSLSession = Collections.emptyList();
    }
    return new Handshake(localTlsVersion, localCipherSuite, (List)localObject, paramSSLSession);
  }
  
  public static Handshake get(TlsVersion paramTlsVersion, CipherSuite paramCipherSuite, List<Certificate> paramList1, List<Certificate> paramList2)
  {
    if (paramTlsVersion == null) {
      throw new NullPointerException("tlsVersion == null");
    }
    if (paramCipherSuite == null) {
      throw new NullPointerException("cipherSuite == null");
    }
    return new Handshake(paramTlsVersion, paramCipherSuite, Util.immutableList(paramList1), Util.immutableList(paramList2));
  }
  
  public CipherSuite cipherSuite()
  {
    return cipherSuite;
  }
  
  public boolean equals(@Nullable Object paramObject)
  {
    boolean bool1 = paramObject instanceof Handshake;
    boolean bool2 = false;
    if (!bool1) {
      return false;
    }
    paramObject = (Handshake)paramObject;
    bool1 = bool2;
    if (tlsVersion.equals(tlsVersion))
    {
      bool1 = bool2;
      if (cipherSuite.equals(cipherSuite))
      {
        bool1 = bool2;
        if (peerCertificates.equals(peerCertificates))
        {
          bool1 = bool2;
          if (localCertificates.equals(localCertificates)) {
            bool1 = true;
          }
        }
      }
    }
    return bool1;
  }
  
  public int hashCode()
  {
    return 31 * (((527 + tlsVersion.hashCode()) * 31 + cipherSuite.hashCode()) * 31 + peerCertificates.hashCode()) + localCertificates.hashCode();
  }
  
  public List<Certificate> localCertificates()
  {
    return localCertificates;
  }
  
  @Nullable
  public Principal localPrincipal()
  {
    if (!localCertificates.isEmpty()) {
      return ((X509Certificate)localCertificates.get(0)).getSubjectX500Principal();
    }
    return null;
  }
  
  public List<Certificate> peerCertificates()
  {
    return peerCertificates;
  }
  
  @Nullable
  public Principal peerPrincipal()
  {
    if (!peerCertificates.isEmpty()) {
      return ((X509Certificate)peerCertificates.get(0)).getSubjectX500Principal();
    }
    return null;
  }
  
  public TlsVersion tlsVersion()
  {
    return tlsVersion;
  }
}
