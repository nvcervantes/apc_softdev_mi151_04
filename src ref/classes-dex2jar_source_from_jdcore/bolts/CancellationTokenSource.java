package bolts;

import java.io.Closeable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class CancellationTokenSource
  implements Closeable
{
  private boolean cancellationRequested;
  private boolean closed;
  private final ScheduledExecutorService executor = BoltsExecutors.scheduled();
  private final Object lock = new Object();
  private final List<CancellationTokenRegistration> registrations = new ArrayList();
  private ScheduledFuture<?> scheduledCancellation;
  
  public CancellationTokenSource() {}
  
  private void cancelAfter(long paramLong, TimeUnit paramTimeUnit)
  {
    if (paramLong < -1L) {
      throw new IllegalArgumentException("Delay must be >= -1");
    }
    if (paramLong == 0L)
    {
      cancel();
      return;
    }
    synchronized (lock)
    {
      if (cancellationRequested) {
        return;
      }
      cancelScheduledCancellation();
      if (paramLong != -1L) {
        scheduledCancellation = executor.schedule(new Runnable()
        {
          public void run()
          {
            synchronized (lock)
            {
              CancellationTokenSource.access$102(CancellationTokenSource.this, null);
              cancel();
              return;
            }
          }
        }, paramLong, paramTimeUnit);
      }
      return;
    }
  }
  
  private void cancelScheduledCancellation()
  {
    if (scheduledCancellation != null)
    {
      scheduledCancellation.cancel(true);
      scheduledCancellation = null;
    }
  }
  
  private void notifyListeners(List<CancellationTokenRegistration> paramList)
  {
    paramList = paramList.iterator();
    while (paramList.hasNext()) {
      ((CancellationTokenRegistration)paramList.next()).runAction();
    }
  }
  
  private void throwIfClosed()
  {
    if (closed) {
      throw new IllegalStateException("Object already closed");
    }
  }
  
  public void cancel()
  {
    synchronized (lock)
    {
      throwIfClosed();
      if (cancellationRequested) {
        return;
      }
      cancelScheduledCancellation();
      cancellationRequested = true;
      ArrayList localArrayList = new ArrayList(registrations);
      notifyListeners(localArrayList);
      return;
    }
  }
  
  public void cancelAfter(long paramLong)
  {
    cancelAfter(paramLong, TimeUnit.MILLISECONDS);
  }
  
  public void close()
  {
    synchronized (lock)
    {
      if (closed) {
        return;
      }
      cancelScheduledCancellation();
      Iterator localIterator = registrations.iterator();
      while (localIterator.hasNext()) {
        ((CancellationTokenRegistration)localIterator.next()).close();
      }
      registrations.clear();
      closed = true;
      return;
    }
  }
  
  public CancellationToken getToken()
  {
    synchronized (lock)
    {
      throwIfClosed();
      CancellationToken localCancellationToken = new CancellationToken(this);
      return localCancellationToken;
    }
  }
  
  public boolean isCancellationRequested()
  {
    synchronized (lock)
    {
      throwIfClosed();
      boolean bool = cancellationRequested;
      return bool;
    }
  }
  
  CancellationTokenRegistration register(Runnable paramRunnable)
  {
    synchronized (lock)
    {
      throwIfClosed();
      paramRunnable = new CancellationTokenRegistration(this, paramRunnable);
      if (cancellationRequested) {
        paramRunnable.runAction();
      } else {
        registrations.add(paramRunnable);
      }
      return paramRunnable;
    }
  }
  
  void throwIfCancellationRequested()
    throws CancellationException
  {
    synchronized (lock)
    {
      throwIfClosed();
      if (cancellationRequested) {
        throw new CancellationException();
      }
      return;
    }
  }
  
  public String toString()
  {
    return String.format(Locale.US, "%s@%s[cancellationRequested=%s]", new Object[] { getClass().getName(), Integer.toHexString(hashCode()), Boolean.toString(isCancellationRequested()) });
  }
  
  void unregister(CancellationTokenRegistration paramCancellationTokenRegistration)
  {
    synchronized (lock)
    {
      throwIfClosed();
      registrations.remove(paramCancellationTokenRegistration);
      return;
    }
  }
}
