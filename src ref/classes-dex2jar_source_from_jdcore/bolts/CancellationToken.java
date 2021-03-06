package bolts;

import java.util.Locale;
import java.util.concurrent.CancellationException;

public class CancellationToken
{
  private final CancellationTokenSource tokenSource;
  
  CancellationToken(CancellationTokenSource paramCancellationTokenSource)
  {
    tokenSource = paramCancellationTokenSource;
  }
  
  public boolean isCancellationRequested()
  {
    return tokenSource.isCancellationRequested();
  }
  
  public CancellationTokenRegistration register(Runnable paramRunnable)
  {
    return tokenSource.register(paramRunnable);
  }
  
  public void throwIfCancellationRequested()
    throws CancellationException
  {
    tokenSource.throwIfCancellationRequested();
  }
  
  public String toString()
  {
    return String.format(Locale.US, "%s@%s[cancellationRequested=%s]", new Object[] { getClass().getName(), Integer.toHexString(hashCode()), Boolean.toString(tokenSource.isCancellationRequested()) });
  }
}
