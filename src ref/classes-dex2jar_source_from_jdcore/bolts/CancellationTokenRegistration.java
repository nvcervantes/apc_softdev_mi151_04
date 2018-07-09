package bolts;

import java.io.Closeable;

public class CancellationTokenRegistration
  implements Closeable
{
  private Runnable action;
  private boolean closed;
  private final Object lock = new Object();
  private CancellationTokenSource tokenSource;
  
  CancellationTokenRegistration(CancellationTokenSource paramCancellationTokenSource, Runnable paramRunnable)
  {
    tokenSource = paramCancellationTokenSource;
    action = paramRunnable;
  }
  
  private void throwIfClosed()
  {
    if (closed) {
      throw new IllegalStateException("Object already closed");
    }
  }
  
  public void close()
  {
    synchronized (lock)
    {
      if (closed) {
        return;
      }
      closed = true;
      tokenSource.unregister(this);
      tokenSource = null;
      action = null;
      return;
    }
  }
  
  void runAction()
  {
    synchronized (lock)
    {
      throwIfClosed();
      action.run();
      close();
      return;
    }
  }
}
