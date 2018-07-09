package okio;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.concurrent.TimeUnit;

public class Timeout
{
  public static final Timeout NONE = new Timeout()
  {
    public Timeout deadlineNanoTime(long paramAnonymousLong)
    {
      return this;
    }
    
    public void throwIfReached()
      throws IOException
    {}
    
    public Timeout timeout(long paramAnonymousLong, TimeUnit paramAnonymousTimeUnit)
    {
      return this;
    }
  };
  private long deadlineNanoTime;
  private boolean hasDeadline;
  private long timeoutNanos;
  
  public Timeout() {}
  
  public Timeout clearDeadline()
  {
    hasDeadline = false;
    return this;
  }
  
  public Timeout clearTimeout()
  {
    timeoutNanos = 0L;
    return this;
  }
  
  public final Timeout deadline(long paramLong, TimeUnit paramTimeUnit)
  {
    if (paramLong <= 0L)
    {
      paramTimeUnit = new StringBuilder();
      paramTimeUnit.append("duration <= 0: ");
      paramTimeUnit.append(paramLong);
      throw new IllegalArgumentException(paramTimeUnit.toString());
    }
    if (paramTimeUnit == null) {
      throw new IllegalArgumentException("unit == null");
    }
    return deadlineNanoTime(System.nanoTime() + paramTimeUnit.toNanos(paramLong));
  }
  
  public long deadlineNanoTime()
  {
    if (!hasDeadline) {
      throw new IllegalStateException("No deadline");
    }
    return deadlineNanoTime;
  }
  
  public Timeout deadlineNanoTime(long paramLong)
  {
    hasDeadline = true;
    deadlineNanoTime = paramLong;
    return this;
  }
  
  public boolean hasDeadline()
  {
    return hasDeadline;
  }
  
  public void throwIfReached()
    throws IOException
  {
    if (Thread.interrupted()) {
      throw new InterruptedIOException("thread interrupted");
    }
    if ((hasDeadline) && (deadlineNanoTime - System.nanoTime() <= 0L)) {
      throw new InterruptedIOException("deadline reached");
    }
  }
  
  public Timeout timeout(long paramLong, TimeUnit paramTimeUnit)
  {
    if (paramLong < 0L)
    {
      paramTimeUnit = new StringBuilder();
      paramTimeUnit.append("timeout < 0: ");
      paramTimeUnit.append(paramLong);
      throw new IllegalArgumentException(paramTimeUnit.toString());
    }
    if (paramTimeUnit == null) {
      throw new IllegalArgumentException("unit == null");
    }
    timeoutNanos = paramTimeUnit.toNanos(paramLong);
    return this;
  }
  
  public long timeoutNanos()
  {
    return timeoutNanos;
  }
  
  public final void waitUntilNotified(Object paramObject)
    throws InterruptedIOException
  {
    try
    {
      boolean bool = hasDeadline();
      long l1 = timeoutNanos();
      long l2 = 0L;
      if ((!bool) && (l1 == 0L))
      {
        paramObject.wait();
        return;
      }
      long l3 = System.nanoTime();
      if ((bool) && (l1 != 0L)) {
        l1 = Math.min(l1, deadlineNanoTime() - l3);
      } else if (bool) {
        l1 = deadlineNanoTime() - l3;
      }
      if (l1 > 0L)
      {
        l2 = l1 / 1000000L;
        paramObject.wait(l2, (int)(l1 - 1000000L * l2));
        l2 = System.nanoTime() - l3;
      }
      if (l2 >= l1) {
        throw new InterruptedIOException("timeout");
      }
      return;
    }
    catch (InterruptedException paramObject)
    {
      for (;;) {}
    }
    throw new InterruptedIOException("interrupted");
  }
}
