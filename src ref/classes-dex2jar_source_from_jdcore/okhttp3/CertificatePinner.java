package okhttp3;

import java.security.Principal;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Nullable;
import javax.net.ssl.SSLPeerUnverifiedException;
import okhttp3.internal.Util;
import okhttp3.internal.tls.CertificateChainCleaner;
import okio.ByteString;

public final class CertificatePinner
{
  public static final CertificatePinner DEFAULT = new Builder().build();
  @Nullable
  private final CertificateChainCleaner certificateChainCleaner;
  private final Set<Pin> pins;
  
  CertificatePinner(Set<Pin> paramSet, @Nullable CertificateChainCleaner paramCertificateChainCleaner)
  {
    pins = paramSet;
    certificateChainCleaner = paramCertificateChainCleaner;
  }
  
  public static String pin(Certificate paramCertificate)
  {
    if (!(paramCertificate instanceof X509Certificate)) {
      throw new IllegalArgumentException("Certificate pinning requires X509 certificates");
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("sha256/");
    localStringBuilder.append(sha256((X509Certificate)paramCertificate).base64());
    return localStringBuilder.toString();
  }
  
  static ByteString sha1(X509Certificate paramX509Certificate)
  {
    return ByteString.of(paramX509Certificate.getPublicKey().getEncoded()).sha1();
  }
  
  static ByteString sha256(X509Certificate paramX509Certificate)
  {
    return ByteString.of(paramX509Certificate.getPublicKey().getEncoded()).sha256();
  }
  
  public void check(String paramString, List<Certificate> paramList)
    throws SSLPeerUnverifiedException
  {
    List localList = findMatchingPins(paramString);
    if (localList.isEmpty()) {
      return;
    }
    Object localObject3 = paramList;
    if (certificateChainCleaner != null) {
      localObject3 = certificateChainCleaner.clean(paramList, paramString);
    }
    int m = ((List)localObject3).size();
    int k = 0;
    int i = 0;
    Object localObject1;
    while (i < m)
    {
      X509Certificate localX509Certificate = (X509Certificate)((List)localObject3).get(i);
      int n = localList.size();
      localObject1 = null;
      paramList = (List<Certificate>)localObject1;
      j = 0;
      while (j < n)
      {
        Pin localPin = (Pin)localList.get(j);
        Object localObject2;
        if (hashAlgorithm.equals("sha256/"))
        {
          localObject2 = localObject1;
          if (localObject1 == null) {
            localObject2 = sha256(localX509Certificate);
          }
          localObject1 = localObject2;
          if (!hash.equals(localObject2)) {}
        }
        else
        {
          if (!hashAlgorithm.equals("sha1/")) {
            break label211;
          }
          localObject2 = paramList;
          if (paramList == null) {
            localObject2 = sha1(localX509Certificate);
          }
          paramList = (List<Certificate>)localObject2;
          if (hash.equals(localObject2)) {
            return;
          }
        }
        j += 1;
        continue;
        label211:
        paramString = new StringBuilder();
        paramString.append("unsupported hashAlgorithm: ");
        paramString.append(hashAlgorithm);
        throw new AssertionError(paramString.toString());
      }
      i += 1;
    }
    paramList = new StringBuilder();
    paramList.append("Certificate pinning failure!");
    paramList.append("\n  Peer certificate chain:");
    int j = ((List)localObject3).size();
    i = 0;
    while (i < j)
    {
      localObject1 = (X509Certificate)((List)localObject3).get(i);
      paramList.append("\n    ");
      paramList.append(pin((Certificate)localObject1));
      paramList.append(": ");
      paramList.append(((X509Certificate)localObject1).getSubjectDN().getName());
      i += 1;
    }
    paramList.append("\n  Pinned certificates for ");
    paramList.append(paramString);
    paramList.append(":");
    j = localList.size();
    i = k;
    while (i < j)
    {
      paramString = (Pin)localList.get(i);
      paramList.append("\n    ");
      paramList.append(paramString);
      i += 1;
    }
    throw new SSLPeerUnverifiedException(paramList.toString());
  }
  
  public void check(String paramString, Certificate... paramVarArgs)
    throws SSLPeerUnverifiedException
  {
    check(paramString, Arrays.asList(paramVarArgs));
  }
  
  public boolean equals(@Nullable Object paramObject)
  {
    if (paramObject == this) {
      return true;
    }
    if ((paramObject instanceof CertificatePinner))
    {
      CertificateChainCleaner localCertificateChainCleaner = certificateChainCleaner;
      paramObject = (CertificatePinner)paramObject;
      if ((Util.equal(localCertificateChainCleaner, certificateChainCleaner)) && (pins.equals(pins))) {
        return true;
      }
    }
    return false;
  }
  
  List<Pin> findMatchingPins(String paramString)
  {
    Object localObject1 = Collections.emptyList();
    Iterator localIterator = pins.iterator();
    while (localIterator.hasNext())
    {
      Pin localPin = (Pin)localIterator.next();
      if (localPin.matches(paramString))
      {
        Object localObject2 = localObject1;
        if (((List)localObject1).isEmpty()) {
          localObject2 = new ArrayList();
        }
        ((List)localObject2).add(localPin);
        localObject1 = localObject2;
      }
    }
    return localObject1;
  }
  
  public int hashCode()
  {
    int i;
    if (certificateChainCleaner != null) {
      i = certificateChainCleaner.hashCode();
    } else {
      i = 0;
    }
    return 31 * i + pins.hashCode();
  }
  
  CertificatePinner withCertificateChainCleaner(@Nullable CertificateChainCleaner paramCertificateChainCleaner)
  {
    if (Util.equal(certificateChainCleaner, paramCertificateChainCleaner)) {
      return this;
    }
    return new CertificatePinner(pins, paramCertificateChainCleaner);
  }
  
  public static final class Builder
  {
    private final List<CertificatePinner.Pin> pins = new ArrayList();
    
    public Builder() {}
    
    public Builder add(String paramString, String... paramVarArgs)
    {
      if (paramString == null) {
        throw new NullPointerException("pattern == null");
      }
      int j = paramVarArgs.length;
      int i = 0;
      while (i < j)
      {
        String str = paramVarArgs[i];
        pins.add(new CertificatePinner.Pin(paramString, str));
        i += 1;
      }
      return this;
    }
    
    public CertificatePinner build()
    {
      return new CertificatePinner(new LinkedHashSet(pins), null);
    }
  }
  
  static final class Pin
  {
    private static final String WILDCARD = "*.";
    final String canonicalHostname;
    final ByteString hash;
    final String hashAlgorithm;
    final String pattern;
    
    Pin(String paramString1, String paramString2)
    {
      pattern = paramString1;
      StringBuilder localStringBuilder;
      if (paramString1.startsWith("*."))
      {
        localStringBuilder = new StringBuilder();
        localStringBuilder.append("http://");
        localStringBuilder.append(paramString1.substring("*.".length()));
        paramString1 = HttpUrl.parse(localStringBuilder.toString()).host();
      }
      else
      {
        localStringBuilder = new StringBuilder();
        localStringBuilder.append("http://");
        localStringBuilder.append(paramString1);
        paramString1 = HttpUrl.parse(localStringBuilder.toString()).host();
      }
      canonicalHostname = paramString1;
      if (paramString2.startsWith("sha1/"))
      {
        hashAlgorithm = "sha1/";
        hash = ByteString.decodeBase64(paramString2.substring("sha1/".length()));
      }
      else
      {
        if (!paramString2.startsWith("sha256/")) {
          break label204;
        }
        hashAlgorithm = "sha256/";
        hash = ByteString.decodeBase64(paramString2.substring("sha256/".length()));
      }
      if (hash == null)
      {
        paramString1 = new StringBuilder();
        paramString1.append("pins must be base64: ");
        paramString1.append(paramString2);
        throw new IllegalArgumentException(paramString1.toString());
      }
      return;
      label204:
      paramString1 = new StringBuilder();
      paramString1.append("pins must start with 'sha256/' or 'sha1/': ");
      paramString1.append(paramString2);
      throw new IllegalArgumentException(paramString1.toString());
    }
    
    public boolean equals(Object paramObject)
    {
      if ((paramObject instanceof Pin))
      {
        String str = pattern;
        paramObject = (Pin)paramObject;
        if ((str.equals(pattern)) && (hashAlgorithm.equals(hashAlgorithm)) && (hash.equals(hash))) {
          return true;
        }
      }
      return false;
    }
    
    public int hashCode()
    {
      return 31 * ((527 + pattern.hashCode()) * 31 + hashAlgorithm.hashCode()) + hash.hashCode();
    }
    
    boolean matches(String paramString)
    {
      if (pattern.startsWith("*."))
      {
        int i = paramString.indexOf('.');
        return (paramString.length() - i - 1 == canonicalHostname.length()) && (paramString.regionMatches(false, i + 1, canonicalHostname, 0, canonicalHostname.length()));
      }
      return paramString.equals(canonicalHostname);
    }
    
    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(hashAlgorithm);
      localStringBuilder.append(hash.base64());
      return localStringBuilder.toString();
    }
  }
}
