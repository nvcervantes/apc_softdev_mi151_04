package okio;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

public class AsyncTimeout
  extends Timeout
{
  private static final long IDLE_TIMEOUT_MILLIS = TimeUnit.SECONDS.toMillis(60L);
  private static final long IDLE_TIMEOUT_NANOS = TimeUnit.MILLISECONDS.toNanos(IDLE_TIMEOUT_MILLIS);
  private static final int TIMEOUT_WRITE_SIZE = 65536;
  @Nullable
  static AsyncTimeout head;
  private boolean inQueue;
  @Nullable
  private AsyncTimeout next;
  private long timeoutAt;
  
  public AsyncTimeout() {}
  
  @Nullable
  static AsyncTimeout awaitTimeout()
    throws InterruptedException
  {
    Object localObject1 = headnext;
    Object localObject2 = null;
    if (localObject1 == null)
    {
      l1 = System.nanoTime();
      AsyncTimeout.class.wait(IDLE_TIMEOUT_MILLIS);
      localObject1 = localObject2;
      if (headnext == null)
      {
        localObject1 = localObject2;
        if (System.nanoTime() - l1 >= IDLE_TIMEOUT_NANOS) {
          localObject1 = head;
        }
      }
      return localObject1;
    }
    long l1 = ((AsyncTimeout)localObject1).remainingNanos(System.nanoTime());
    if (l1 > 0L)
    {
      long l2 = l1 / 1000000L;
      AsyncTimeout.class.wait(l2, (int)(l1 - 1000000L * l2));
      return null;
    }
    headnext = next;
    next = null;
    return localObject1;
  }
  
  private static boolean cancelScheduledTimeout(AsyncTimeout paramAsyncTimeout)
  {
    try
    {
      for (AsyncTimeout localAsyncTimeout = head; localAsyncTimeout != null; localAsyncTimeout = next) {
        if (next == paramAsyncTimeout)
        {
          next = next;
          next = null;
          return false;
        }
      }
      return true;
    }
    finally {}
  }
  
  private long remainingNanos(long paramLong)
  {
    return timeoutAt - paramLong;
  }
  
  private static void scheduleTimeout(AsyncTimeout paramAsyncTimeout, long paramLong, boolean paramBoolean)
  {
    try
    {
      if (head == null)
      {
        head = new AsyncTimeout();
        new Watchdog().start();
      }
      long l = System.nanoTime();
      if ((paramLong != 0L) && (paramBoolean))
      {
        timeoutAt = (l + Math.min(paramLong, paramAsyncTimeout.deadlineNanoTime() - l));
      }
      else if (paramLong != 0L)
      {
        timeoutAt = (l + paramLong);
      }
      else
      {
        if (!paramBoolean) {
          break label174;
        }
        timeoutAt = paramAsyncTimeout.deadlineNanoTime();
      }
      paramLong = paramAsyncTimeout.remainingNanos(l);
      for (AsyncTimeout localAsyncTimeout = head; (next != null) && (paramLong >= next.remainingNanos(l)); localAsyncTimeout = next) {}
      next = next;
      next = paramAsyncTimeout;
      if (localAsyncTimeout == head) {
        AsyncTimeout.class.notify();
      }
      return;
      label174:
      throw new AssertionError();
    }
    finally {}
  }
  
  public final void enter()
  {
    if (inQueue) {
      throw new IllegalStateException("Unbalanced enter/exit");
    }
    long l = timeoutNanos();
    boolean bool = hasDeadline();
    if ((l == 0L) && (!bool)) {
      return;
    }
    inQueue = true;
    scheduleTimeout(this, l, bool);
  }
  
  final IOException exit(IOException paramIOException)
    throws IOException
  {
    if (!exit()) {
      return paramIOException;
    }
    return newTimeoutException(paramIOException);
  }
  
  final void exit(boolean paramBoolean)
    throws IOException
  {
    if ((exit()) && (paramBoolean)) {
      throw newTimeoutException(null);
    }
  }
  
  public final boolean exit()
  {
    if (!inQueue) {
      return false;
    }
    inQueue = false;
    return cancelScheduledTimeout(this);
  }
  
  protected IOException newTimeoutException(@Nullable IOException paramIOException)
  {
    InterruptedIOException localInterruptedIOException = new InterruptedIOException("timeout");
    if (paramIOException != null) {
      localInterruptedIOException.initCause(paramIOException);
    }
    return localInterruptedIOException;
  }
  
  public final Sink sink(final Sink paramSink)
  {
    new Sink()
    {
      /* Error */
      public void close()
        throws IOException
      {
        // Byte code:
        //   0: aload_0
        //   1: getfield 19	okio/AsyncTimeout$1:this$0	Lokio/AsyncTimeout;
        //   4: invokevirtual 31	okio/AsyncTimeout:enter	()V
        //   7: aload_0
        //   8: getfield 21	okio/AsyncTimeout$1:val$sink	Lokio/Sink;
        //   11: invokeinterface 33 1 0
        //   16: aload_0
        //   17: getfield 19	okio/AsyncTimeout$1:this$0	Lokio/AsyncTimeout;
        //   20: iconst_1
        //   21: invokevirtual 37	okio/AsyncTimeout:exit	(Z)V
        //   24: return
        //   25: astore_1
        //   26: goto +13 -> 39
        //   29: astore_1
        //   30: aload_0
        //   31: getfield 19	okio/AsyncTimeout$1:this$0	Lokio/AsyncTimeout;
        //   34: aload_1
        //   35: invokevirtual 40	okio/AsyncTimeout:exit	(Ljava/io/IOException;)Ljava/io/IOException;
        //   38: athrow
        //   39: aload_0
        //   40: getfield 19	okio/AsyncTimeout$1:this$0	Lokio/AsyncTimeout;
        //   43: iconst_0
        //   44: invokevirtual 37	okio/AsyncTimeout:exit	(Z)V
        //   47: aload_1
        //   48: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	49	0	this	1
        //   25	1	1	localObject	Object
        //   29	19	1	localIOException	IOException
        // Exception table:
        //   from	to	target	type
        //   7	16	25	finally
        //   30	39	25	finally
        //   7	16	29	java/io/IOException
      }
      
      /* Error */
      public void flush()
        throws IOException
      {
        // Byte code:
        //   0: aload_0
        //   1: getfield 19	okio/AsyncTimeout$1:this$0	Lokio/AsyncTimeout;
        //   4: invokevirtual 31	okio/AsyncTimeout:enter	()V
        //   7: aload_0
        //   8: getfield 21	okio/AsyncTimeout$1:val$sink	Lokio/Sink;
        //   11: invokeinterface 44 1 0
        //   16: aload_0
        //   17: getfield 19	okio/AsyncTimeout$1:this$0	Lokio/AsyncTimeout;
        //   20: iconst_1
        //   21: invokevirtual 37	okio/AsyncTimeout:exit	(Z)V
        //   24: return
        //   25: astore_1
        //   26: goto +13 -> 39
        //   29: astore_1
        //   30: aload_0
        //   31: getfield 19	okio/AsyncTimeout$1:this$0	Lokio/AsyncTimeout;
        //   34: aload_1
        //   35: invokevirtual 40	okio/AsyncTimeout:exit	(Ljava/io/IOException;)Ljava/io/IOException;
        //   38: athrow
        //   39: aload_0
        //   40: getfield 19	okio/AsyncTimeout$1:this$0	Lokio/AsyncTimeout;
        //   43: iconst_0
        //   44: invokevirtual 37	okio/AsyncTimeout:exit	(Z)V
        //   47: aload_1
        //   48: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	49	0	this	1
        //   25	1	1	localObject	Object
        //   29	19	1	localIOException	IOException
        // Exception table:
        //   from	to	target	type
        //   7	16	25	finally
        //   30	39	25	finally
        //   7	16	29	java/io/IOException
      }
      
      public Timeout timeout()
      {
        return AsyncTimeout.this;
      }
      
      public String toString()
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("AsyncTimeout.sink(");
        localStringBuilder.append(paramSink);
        localStringBuilder.append(")");
        return localStringBuilder.toString();
      }
      
      /* Error */
      public void write(Buffer paramAnonymousBuffer, long paramAnonymousLong)
        throws IOException
      {
        // Byte code:
        //   0: aload_1
        //   1: getfield 72	okio/Buffer:size	J
        //   4: lconst_0
        //   5: lload_2
        //   6: invokestatic 78	okio/Util:checkOffsetAndCount	(JJJ)V
        //   9: lconst_0
        //   10: lstore 4
        //   12: lload_2
        //   13: lconst_0
        //   14: lcmp
        //   15: ifle +125 -> 140
        //   18: aload_1
        //   19: getfield 82	okio/Buffer:head	Lokio/Segment;
        //   22: astore 8
        //   24: lload 4
        //   26: lstore 6
        //   28: lload 4
        //   30: ldc2_w 83
        //   33: lcmp
        //   34: ifge +47 -> 81
        //   37: lload 4
        //   39: aload_1
        //   40: getfield 82	okio/Buffer:head	Lokio/Segment;
        //   43: getfield 90	okio/Segment:limit	I
        //   46: aload_1
        //   47: getfield 82	okio/Buffer:head	Lokio/Segment;
        //   50: getfield 93	okio/Segment:pos	I
        //   53: isub
        //   54: i2l
        //   55: ladd
        //   56: lstore 4
        //   58: lload 4
        //   60: lload_2
        //   61: lcmp
        //   62: iflt +9 -> 71
        //   65: lload_2
        //   66: lstore 6
        //   68: goto +13 -> 81
        //   71: aload 8
        //   73: getfield 96	okio/Segment:next	Lokio/Segment;
        //   76: astore 8
        //   78: goto -54 -> 24
        //   81: aload_0
        //   82: getfield 19	okio/AsyncTimeout$1:this$0	Lokio/AsyncTimeout;
        //   85: invokevirtual 31	okio/AsyncTimeout:enter	()V
        //   88: aload_0
        //   89: getfield 21	okio/AsyncTimeout$1:val$sink	Lokio/Sink;
        //   92: aload_1
        //   93: lload 6
        //   95: invokeinterface 98 4 0
        //   100: aload_0
        //   101: getfield 19	okio/AsyncTimeout$1:this$0	Lokio/AsyncTimeout;
        //   104: iconst_1
        //   105: invokevirtual 37	okio/AsyncTimeout:exit	(Z)V
        //   108: lload_2
        //   109: lload 6
        //   111: lsub
        //   112: lstore_2
        //   113: goto -104 -> 9
        //   116: astore_1
        //   117: goto +13 -> 130
        //   120: astore_1
        //   121: aload_0
        //   122: getfield 19	okio/AsyncTimeout$1:this$0	Lokio/AsyncTimeout;
        //   125: aload_1
        //   126: invokevirtual 40	okio/AsyncTimeout:exit	(Ljava/io/IOException;)Ljava/io/IOException;
        //   129: athrow
        //   130: aload_0
        //   131: getfield 19	okio/AsyncTimeout$1:this$0	Lokio/AsyncTimeout;
        //   134: iconst_0
        //   135: invokevirtual 37	okio/AsyncTimeout:exit	(Z)V
        //   138: aload_1
        //   139: athrow
        //   140: return
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	141	0	this	1
        //   0	141	1	paramAnonymousBuffer	Buffer
        //   0	141	2	paramAnonymousLong	long
        //   10	49	4	l1	long
        //   26	84	6	l2	long
        //   22	55	8	localSegment	Segment
        // Exception table:
        //   from	to	target	type
        //   88	100	116	finally
        //   121	130	116	finally
        //   88	100	120	java/io/IOException
      }
    };
  }
  
  public final Source source(final Source paramSource)
  {
    new Source()
    {
      /* Error */
      public void close()
        throws IOException
      {
        // Byte code:
        //   0: aload_0
        //   1: getfield 21	okio/AsyncTimeout$2:val$source	Lokio/Source;
        //   4: invokeinterface 30 1 0
        //   9: aload_0
        //   10: getfield 19	okio/AsyncTimeout$2:this$0	Lokio/AsyncTimeout;
        //   13: iconst_1
        //   14: invokevirtual 34	okio/AsyncTimeout:exit	(Z)V
        //   17: return
        //   18: astore_1
        //   19: goto +13 -> 32
        //   22: astore_1
        //   23: aload_0
        //   24: getfield 19	okio/AsyncTimeout$2:this$0	Lokio/AsyncTimeout;
        //   27: aload_1
        //   28: invokevirtual 37	okio/AsyncTimeout:exit	(Ljava/io/IOException;)Ljava/io/IOException;
        //   31: athrow
        //   32: aload_0
        //   33: getfield 19	okio/AsyncTimeout$2:this$0	Lokio/AsyncTimeout;
        //   36: iconst_0
        //   37: invokevirtual 34	okio/AsyncTimeout:exit	(Z)V
        //   40: aload_1
        //   41: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	42	0	this	2
        //   18	1	1	localObject	Object
        //   22	19	1	localIOException	IOException
        // Exception table:
        //   from	to	target	type
        //   0	9	18	finally
        //   23	32	18	finally
        //   0	9	22	java/io/IOException
      }
      
      /* Error */
      public long read(Buffer paramAnonymousBuffer, long paramAnonymousLong)
        throws IOException
      {
        // Byte code:
        //   0: aload_0
        //   1: getfield 19	okio/AsyncTimeout$2:this$0	Lokio/AsyncTimeout;
        //   4: invokevirtual 43	okio/AsyncTimeout:enter	()V
        //   7: aload_0
        //   8: getfield 21	okio/AsyncTimeout$2:val$source	Lokio/Source;
        //   11: aload_1
        //   12: lload_2
        //   13: invokeinterface 45 4 0
        //   18: lstore_2
        //   19: aload_0
        //   20: getfield 19	okio/AsyncTimeout$2:this$0	Lokio/AsyncTimeout;
        //   23: iconst_1
        //   24: invokevirtual 34	okio/AsyncTimeout:exit	(Z)V
        //   27: lload_2
        //   28: lreturn
        //   29: astore_1
        //   30: goto +13 -> 43
        //   33: astore_1
        //   34: aload_0
        //   35: getfield 19	okio/AsyncTimeout$2:this$0	Lokio/AsyncTimeout;
        //   38: aload_1
        //   39: invokevirtual 37	okio/AsyncTimeout:exit	(Ljava/io/IOException;)Ljava/io/IOException;
        //   42: athrow
        //   43: aload_0
        //   44: getfield 19	okio/AsyncTimeout$2:this$0	Lokio/AsyncTimeout;
        //   47: iconst_0
        //   48: invokevirtual 34	okio/AsyncTimeout:exit	(Z)V
        //   51: aload_1
        //   52: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	53	0	this	2
        //   0	53	1	paramAnonymousBuffer	Buffer
        //   0	53	2	paramAnonymousLong	long
        // Exception table:
        //   from	to	target	type
        //   7	19	29	finally
        //   34	43	29	finally
        //   7	19	33	java/io/IOException
      }
      
      public Timeout timeout()
      {
        return AsyncTimeout.this;
      }
      
      public String toString()
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("AsyncTimeout.source(");
        localStringBuilder.append(paramSource);
        localStringBuilder.append(")");
        return localStringBuilder.toString();
      }
    };
  }
  
  protected void timedOut() {}
  
  private static final class Watchdog
    extends Thread
  {
    Watchdog()
    {
      super();
      setDaemon(true);
    }
    
    public void run()
    {
      try
      {
        for (;;)
        {
          try
          {
            AsyncTimeout localAsyncTimeout = AsyncTimeout.awaitTimeout();
            if (localAsyncTimeout == null) {}
            if (localAsyncTimeout == AsyncTimeout.head)
            {
              AsyncTimeout.head = null;
              return;
            }
            localAsyncTimeout.timedOut();
          }
          finally {}
        }
      }
      catch (InterruptedException localInterruptedException) {}
    }
  }
}
