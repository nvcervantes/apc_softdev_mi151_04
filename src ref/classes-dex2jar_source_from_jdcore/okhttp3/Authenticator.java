package okhttp3;

import java.io.IOException;
import javax.annotation.Nullable;

public abstract interface Authenticator
{
  public static final Authenticator NONE = new Authenticator()
  {
    public Request authenticate(Route paramAnonymousRoute, Response paramAnonymousResponse)
    {
      return null;
    }
  };
  
  @Nullable
  public abstract Request authenticate(Route paramRoute, Response paramResponse)
    throws IOException;
}
