package okhttp3;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

public abstract interface Dns
{
  public static final Dns SYSTEM = new Dns()
  {
    public List<InetAddress> lookup(String paramAnonymousString)
      throws UnknownHostException
    {
      if (paramAnonymousString == null) {
        throw new UnknownHostException("hostname == null");
      }
      return Arrays.asList(InetAddress.getAllByName(paramAnonymousString));
    }
  };
  
  public abstract List<InetAddress> lookup(String paramString)
    throws UnknownHostException;
}
