package okhttp3.internal.tls;

import java.security.PublicKey;
import java.security.cert.X509Certificate;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import javax.security.auth.x500.X500Principal;

public final class BasicTrustRootIndex
  implements TrustRootIndex
{
  private final Map<X500Principal, Set<X509Certificate>> subjectToCaCerts = new LinkedHashMap();
  
  public BasicTrustRootIndex(X509Certificate... paramVarArgs)
  {
    int i = 0;
    int j = paramVarArgs.length;
    while (i < j)
    {
      X509Certificate localX509Certificate = paramVarArgs[i];
      X500Principal localX500Principal = localX509Certificate.getSubjectX500Principal();
      Set localSet = (Set)subjectToCaCerts.get(localX500Principal);
      Object localObject = localSet;
      if (localSet == null)
      {
        localObject = new LinkedHashSet(1);
        subjectToCaCerts.put(localX500Principal, localObject);
      }
      ((Set)localObject).add(localX509Certificate);
      i += 1;
    }
  }
  
  public boolean equals(Object paramObject)
  {
    if (paramObject == this) {
      return true;
    }
    return ((paramObject instanceof BasicTrustRootIndex)) && (subjectToCaCerts.equals(subjectToCaCerts));
  }
  
  public X509Certificate findByIssuerAndSignature(X509Certificate paramX509Certificate)
  {
    Object localObject = paramX509Certificate.getIssuerX500Principal();
    localObject = (Set)subjectToCaCerts.get(localObject);
    if (localObject == null) {
      return null;
    }
    localObject = ((Set)localObject).iterator();
    for (;;)
    {
      X509Certificate localX509Certificate;
      PublicKey localPublicKey;
      if (((Iterator)localObject).hasNext())
      {
        localX509Certificate = (X509Certificate)((Iterator)localObject).next();
        localPublicKey = localX509Certificate.getPublicKey();
      }
      try
      {
        paramX509Certificate.verify(localPublicKey);
        return localX509Certificate;
      }
      catch (Exception localException) {}
      return null;
    }
  }
  
  public int hashCode()
  {
    return subjectToCaCerts.hashCode();
  }
}
