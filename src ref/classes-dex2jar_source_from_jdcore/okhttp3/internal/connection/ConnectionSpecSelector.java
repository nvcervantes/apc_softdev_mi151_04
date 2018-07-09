package okhttp3.internal.connection;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ProtocolException;
import java.net.UnknownServiceException;
import java.security.cert.CertificateException;
import java.util.Arrays;
import java.util.List;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLProtocolException;
import javax.net.ssl.SSLSocket;
import okhttp3.ConnectionSpec;
import okhttp3.internal.Internal;

public final class ConnectionSpecSelector
{
  private final List<ConnectionSpec> connectionSpecs;
  private boolean isFallback;
  private boolean isFallbackPossible;
  private int nextModeIndex = 0;
  
  public ConnectionSpecSelector(List<ConnectionSpec> paramList)
  {
    connectionSpecs = paramList;
  }
  
  private boolean isFallbackPossible(SSLSocket paramSSLSocket)
  {
    int i = nextModeIndex;
    while (i < connectionSpecs.size())
    {
      if (((ConnectionSpec)connectionSpecs.get(i)).isCompatible(paramSSLSocket)) {
        return true;
      }
      i += 1;
    }
    return false;
  }
  
  public ConnectionSpec configureSecureSocket(SSLSocket paramSSLSocket)
    throws IOException
  {
    int i = nextModeIndex;
    int j = connectionSpecs.size();
    while (i < j)
    {
      localObject = (ConnectionSpec)connectionSpecs.get(i);
      if (((ConnectionSpec)localObject).isCompatible(paramSSLSocket))
      {
        nextModeIndex = (i + 1);
        break label64;
      }
      i += 1;
    }
    Object localObject = null;
    label64:
    if (localObject == null)
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("Unable to find acceptable protocols. isFallback=");
      ((StringBuilder)localObject).append(isFallback);
      ((StringBuilder)localObject).append(", modes=");
      ((StringBuilder)localObject).append(connectionSpecs);
      ((StringBuilder)localObject).append(", supported protocols=");
      ((StringBuilder)localObject).append(Arrays.toString(paramSSLSocket.getEnabledProtocols()));
      throw new UnknownServiceException(((StringBuilder)localObject).toString());
    }
    isFallbackPossible = isFallbackPossible(paramSSLSocket);
    Internal.instance.apply((ConnectionSpec)localObject, paramSSLSocket, isFallback);
    return localObject;
  }
  
  public boolean connectionFailed(IOException paramIOException)
  {
    boolean bool1 = true;
    isFallback = true;
    if (!isFallbackPossible) {
      return false;
    }
    if ((paramIOException instanceof ProtocolException)) {
      return false;
    }
    if ((paramIOException instanceof InterruptedIOException)) {
      return false;
    }
    boolean bool2 = paramIOException instanceof SSLHandshakeException;
    if ((bool2) && ((paramIOException.getCause() instanceof CertificateException))) {
      return false;
    }
    if ((paramIOException instanceof SSLPeerUnverifiedException)) {
      return false;
    }
    if (!bool2)
    {
      if ((paramIOException instanceof SSLProtocolException)) {
        return true;
      }
      bool1 = false;
    }
    return bool1;
  }
}
