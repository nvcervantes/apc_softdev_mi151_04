package okhttp3;

import java.util.Arrays;
import java.util.List;
import javax.annotation.Nullable;
import javax.net.ssl.SSLSocket;
import okhttp3.internal.Util;

public final class ConnectionSpec
{
  private static final CipherSuite[] APPROVED_CIPHER_SUITES = { CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384, CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384, CipherSuite.TLS_ECDHE_ECDSA_WITH_CHACHA20_POLY1305_SHA256, CipherSuite.TLS_ECDHE_RSA_WITH_CHACHA20_POLY1305_SHA256, CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA, CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA, CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA, CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA, CipherSuite.TLS_RSA_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_RSA_WITH_AES_256_GCM_SHA384, CipherSuite.TLS_RSA_WITH_AES_128_CBC_SHA, CipherSuite.TLS_RSA_WITH_AES_256_CBC_SHA, CipherSuite.TLS_RSA_WITH_3DES_EDE_CBC_SHA };
  public static final ConnectionSpec CLEARTEXT = new Builder(false).build();
  public static final ConnectionSpec COMPATIBLE_TLS;
  public static final ConnectionSpec MODERN_TLS = new Builder(true).cipherSuites(APPROVED_CIPHER_SUITES).tlsVersions(new TlsVersion[] { TlsVersion.TLS_1_3, TlsVersion.TLS_1_2, TlsVersion.TLS_1_1, TlsVersion.TLS_1_0 }).supportsTlsExtensions(true).build();
  @Nullable
  final String[] cipherSuites;
  final boolean supportsTlsExtensions;
  final boolean tls;
  @Nullable
  final String[] tlsVersions;
  
  static
  {
    COMPATIBLE_TLS = new Builder(MODERN_TLS).tlsVersions(new TlsVersion[] { TlsVersion.TLS_1_0 }).supportsTlsExtensions(true).build();
  }
  
  ConnectionSpec(Builder paramBuilder)
  {
    tls = tls;
    cipherSuites = cipherSuites;
    tlsVersions = tlsVersions;
    supportsTlsExtensions = supportsTlsExtensions;
  }
  
  private ConnectionSpec supportedSpec(SSLSocket paramSSLSocket, boolean paramBoolean)
  {
    String[] arrayOfString1;
    if (cipherSuites != null) {
      arrayOfString1 = Util.intersect(CipherSuite.ORDER_BY_NAME, paramSSLSocket.getEnabledCipherSuites(), cipherSuites);
    } else {
      arrayOfString1 = paramSSLSocket.getEnabledCipherSuites();
    }
    String[] arrayOfString2;
    if (tlsVersions != null) {
      arrayOfString2 = Util.intersect(Util.NATURAL_ORDER, paramSSLSocket.getEnabledProtocols(), tlsVersions);
    } else {
      arrayOfString2 = paramSSLSocket.getEnabledProtocols();
    }
    String[] arrayOfString3 = paramSSLSocket.getSupportedCipherSuites();
    int i = Util.indexOf(CipherSuite.ORDER_BY_NAME, arrayOfString3, "TLS_FALLBACK_SCSV");
    paramSSLSocket = arrayOfString1;
    if (paramBoolean)
    {
      paramSSLSocket = arrayOfString1;
      if (i != -1) {
        paramSSLSocket = Util.concat(arrayOfString1, arrayOfString3[i]);
      }
    }
    return new Builder(this).cipherSuites(paramSSLSocket).tlsVersions(arrayOfString2).build();
  }
  
  void apply(SSLSocket paramSSLSocket, boolean paramBoolean)
  {
    ConnectionSpec localConnectionSpec = supportedSpec(paramSSLSocket, paramBoolean);
    if (tlsVersions != null) {
      paramSSLSocket.setEnabledProtocols(tlsVersions);
    }
    if (cipherSuites != null) {
      paramSSLSocket.setEnabledCipherSuites(cipherSuites);
    }
  }
  
  @Nullable
  public List<CipherSuite> cipherSuites()
  {
    if (cipherSuites != null) {
      return CipherSuite.forJavaNames(cipherSuites);
    }
    return null;
  }
  
  public boolean equals(@Nullable Object paramObject)
  {
    if (!(paramObject instanceof ConnectionSpec)) {
      return false;
    }
    if (paramObject == this) {
      return true;
    }
    paramObject = (ConnectionSpec)paramObject;
    if (tls != tls) {
      return false;
    }
    if (tls)
    {
      if (!Arrays.equals(cipherSuites, cipherSuites)) {
        return false;
      }
      if (!Arrays.equals(tlsVersions, tlsVersions)) {
        return false;
      }
      if (supportsTlsExtensions != supportsTlsExtensions) {
        return false;
      }
    }
    return true;
  }
  
  public int hashCode()
  {
    if (tls) {
      return 31 * ((527 + Arrays.hashCode(cipherSuites)) * 31 + Arrays.hashCode(tlsVersions)) + (supportsTlsExtensions ^ true);
    }
    return 17;
  }
  
  public boolean isCompatible(SSLSocket paramSSLSocket)
  {
    if (!tls) {
      return false;
    }
    if ((tlsVersions != null) && (!Util.nonEmptyIntersection(Util.NATURAL_ORDER, tlsVersions, paramSSLSocket.getEnabledProtocols()))) {
      return false;
    }
    return (cipherSuites == null) || (Util.nonEmptyIntersection(CipherSuite.ORDER_BY_NAME, cipherSuites, paramSSLSocket.getEnabledCipherSuites()));
  }
  
  public boolean isTls()
  {
    return tls;
  }
  
  public boolean supportsTlsExtensions()
  {
    return supportsTlsExtensions;
  }
  
  @Nullable
  public List<TlsVersion> tlsVersions()
  {
    if (tlsVersions != null) {
      return TlsVersion.forJavaNames(tlsVersions);
    }
    return null;
  }
  
  public String toString()
  {
    if (!tls) {
      return "ConnectionSpec()";
    }
    String str1;
    if (cipherSuites != null) {
      str1 = cipherSuites().toString();
    } else {
      str1 = "[all enabled]";
    }
    String str2;
    if (tlsVersions != null) {
      str2 = tlsVersions().toString();
    } else {
      str2 = "[all enabled]";
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("ConnectionSpec(cipherSuites=");
    localStringBuilder.append(str1);
    localStringBuilder.append(", tlsVersions=");
    localStringBuilder.append(str2);
    localStringBuilder.append(", supportsTlsExtensions=");
    localStringBuilder.append(supportsTlsExtensions);
    localStringBuilder.append(")");
    return localStringBuilder.toString();
  }
  
  public static final class Builder
  {
    @Nullable
    String[] cipherSuites;
    boolean supportsTlsExtensions;
    boolean tls;
    @Nullable
    String[] tlsVersions;
    
    public Builder(ConnectionSpec paramConnectionSpec)
    {
      tls = tls;
      cipherSuites = cipherSuites;
      tlsVersions = tlsVersions;
      supportsTlsExtensions = supportsTlsExtensions;
    }
    
    Builder(boolean paramBoolean)
    {
      tls = paramBoolean;
    }
    
    public Builder allEnabledCipherSuites()
    {
      if (!tls) {
        throw new IllegalStateException("no cipher suites for cleartext connections");
      }
      cipherSuites = null;
      return this;
    }
    
    public Builder allEnabledTlsVersions()
    {
      if (!tls) {
        throw new IllegalStateException("no TLS versions for cleartext connections");
      }
      tlsVersions = null;
      return this;
    }
    
    public ConnectionSpec build()
    {
      return new ConnectionSpec(this);
    }
    
    public Builder cipherSuites(String... paramVarArgs)
    {
      if (!tls) {
        throw new IllegalStateException("no cipher suites for cleartext connections");
      }
      if (paramVarArgs.length == 0) {
        throw new IllegalArgumentException("At least one cipher suite is required");
      }
      cipherSuites = ((String[])paramVarArgs.clone());
      return this;
    }
    
    public Builder cipherSuites(CipherSuite... paramVarArgs)
    {
      if (!tls) {
        throw new IllegalStateException("no cipher suites for cleartext connections");
      }
      String[] arrayOfString = new String[paramVarArgs.length];
      int i = 0;
      while (i < paramVarArgs.length)
      {
        arrayOfString[i] = javaName;
        i += 1;
      }
      return cipherSuites(arrayOfString);
    }
    
    public Builder supportsTlsExtensions(boolean paramBoolean)
    {
      if (!tls) {
        throw new IllegalStateException("no TLS extensions for cleartext connections");
      }
      supportsTlsExtensions = paramBoolean;
      return this;
    }
    
    public Builder tlsVersions(String... paramVarArgs)
    {
      if (!tls) {
        throw new IllegalStateException("no TLS versions for cleartext connections");
      }
      if (paramVarArgs.length == 0) {
        throw new IllegalArgumentException("At least one TLS version is required");
      }
      tlsVersions = ((String[])paramVarArgs.clone());
      return this;
    }
    
    public Builder tlsVersions(TlsVersion... paramVarArgs)
    {
      if (!tls) {
        throw new IllegalStateException("no TLS versions for cleartext connections");
      }
      String[] arrayOfString = new String[paramVarArgs.length];
      int i = 0;
      while (i < paramVarArgs.length)
      {
        arrayOfString[i] = javaName;
        i += 1;
      }
      return tlsVersions(arrayOfString);
    }
  }
}
