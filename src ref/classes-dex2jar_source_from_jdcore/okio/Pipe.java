package okio;

import java.io.IOException;

public final class Pipe
{
  final Buffer buffer = new Buffer();
  final long maxBufferSize;
  private final Sink sink = new PipeSink();
  boolean sinkClosed;
  private final Source source = new PipeSource();
  boolean sourceClosed;
  
  public Pipe(long paramLong)
  {
    if (paramLong < 1L)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("maxBufferSize < 1: ");
      localStringBuilder.append(paramLong);
      throw new IllegalArgumentException(localStringBuilder.toString());
    }
    maxBufferSize = paramLong;
  }
  
  public Sink sink()
  {
    return sink;
  }
  
  public Source source()
  {
    return source;
  }
  
  final class PipeSink
    implements Sink
  {
    final Timeout timeout = new Timeout();
    
    PipeSink() {}
    
    public void close()
      throws IOException
    {
      synchronized (buffer)
      {
        if (sinkClosed) {
          return;
        }
        if ((sourceClosed) && (buffer.size() > 0L)) {
          throw new IOException("source is closed");
        }
        sinkClosed = true;
        buffer.notifyAll();
        return;
      }
    }
    
    public void flush()
      throws IOException
    {
      synchronized (buffer)
      {
        if (sinkClosed) {
          throw new IllegalStateException("closed");
        }
        if ((sourceClosed) && (buffer.size() > 0L)) {
          throw new IOException("source is closed");
        }
        return;
      }
    }
    
    public Timeout timeout()
    {
      return timeout;
    }
    
    public void write(Buffer paramBuffer, long paramLong)
      throws IOException
    {
      synchronized (buffer)
      {
        if (sinkClosed) {
          throw new IllegalStateException("closed");
        }
        while (paramLong > 0L)
        {
          if (sourceClosed) {
            throw new IOException("source is closed");
          }
          long l = maxBufferSize - buffer.size();
          if (l == 0L)
          {
            timeout.waitUntilNotified(buffer);
          }
          else
          {
            l = Math.min(l, paramLong);
            buffer.write(paramBuffer, l);
            buffer.notifyAll();
            paramLong -= l;
          }
        }
        return;
      }
    }
  }
  
  final class PipeSource
    implements Source
  {
    final Timeout timeout = new Timeout();
    
    PipeSource() {}
    
    public void close()
      throws IOException
    {
      synchronized (buffer)
      {
        sourceClosed = true;
        buffer.notifyAll();
        return;
      }
    }
    
    public long read(Buffer paramBuffer, long paramLong)
      throws IOException
    {
      synchronized (buffer)
      {
        if (sourceClosed) {
          throw new IllegalStateException("closed");
        }
        while (buffer.size() == 0L)
        {
          if (sinkClosed) {
            return -1L;
          }
          timeout.waitUntilNotified(buffer);
        }
        paramLong = buffer.read(paramBuffer, paramLong);
        buffer.notifyAll();
        return paramLong;
      }
    }
    
    public Timeout timeout()
    {
      return timeout;
    }
  }
}
