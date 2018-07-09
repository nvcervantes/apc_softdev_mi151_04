package okhttp3.internal.http2;

import java.io.EOFException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;
import okio.AsyncTimeout;
import okio.Buffer;
import okio.BufferedSource;
import okio.Sink;
import okio.Source;
import okio.Timeout;

public final class Http2Stream
{
  long bytesLeftInWriteWindow;
  final Http2Connection connection;
  ErrorCode errorCode = null;
  private boolean hasResponseHeaders;
  final int id;
  final StreamTimeout readTimeout = new StreamTimeout();
  private final List<Header> requestHeaders;
  private List<Header> responseHeaders;
  final FramingSink sink;
  private final FramingSource source;
  long unacknowledgedBytesRead = 0L;
  final StreamTimeout writeTimeout = new StreamTimeout();
  
  Http2Stream(int paramInt, Http2Connection paramHttp2Connection, boolean paramBoolean1, boolean paramBoolean2, List<Header> paramList)
  {
    if (paramHttp2Connection == null) {
      throw new NullPointerException("connection == null");
    }
    if (paramList == null) {
      throw new NullPointerException("requestHeaders == null");
    }
    id = paramInt;
    connection = paramHttp2Connection;
    bytesLeftInWriteWindow = peerSettings.getInitialWindowSize();
    source = new FramingSource(okHttpSettings.getInitialWindowSize());
    sink = new FramingSink();
    source.finished = paramBoolean2;
    sink.finished = paramBoolean1;
    requestHeaders = paramList;
  }
  
  private boolean closeInternal(ErrorCode paramErrorCode)
  {
    try
    {
      if (errorCode != null) {
        return false;
      }
      if ((source.finished) && (sink.finished)) {
        return false;
      }
      errorCode = paramErrorCode;
      notifyAll();
      connection.removeStream(id);
      return true;
    }
    finally {}
  }
  
  void addBytesToWriteWindow(long paramLong)
  {
    bytesLeftInWriteWindow += paramLong;
    if (paramLong > 0L) {
      notifyAll();
    }
  }
  
  void cancelStreamIfNecessary()
    throws IOException
  {
    for (;;)
    {
      try
      {
        if ((source.finished) || (!source.closed)) {
          break label91;
        }
        if (sink.finished) {
          break label86;
        }
        if (!sink.closed) {
          break label91;
        }
      }
      finally {}
      boolean bool = isOpen();
      if (i != 0)
      {
        close(ErrorCode.CANCEL);
        return;
      }
      if (!bool) {
        connection.removeStream(id);
      }
      return;
      label86:
      int i = 1;
      continue;
      label91:
      i = 0;
    }
  }
  
  void checkOutNotClosed()
    throws IOException
  {
    if (sink.closed) {
      throw new IOException("stream closed");
    }
    if (sink.finished) {
      throw new IOException("stream finished");
    }
    if (errorCode != null) {
      throw new StreamResetException(errorCode);
    }
  }
  
  public void close(ErrorCode paramErrorCode)
    throws IOException
  {
    if (!closeInternal(paramErrorCode)) {
      return;
    }
    connection.writeSynReset(id, paramErrorCode);
  }
  
  public void closeLater(ErrorCode paramErrorCode)
  {
    if (!closeInternal(paramErrorCode)) {
      return;
    }
    connection.writeSynResetLater(id, paramErrorCode);
  }
  
  public Http2Connection getConnection()
  {
    return connection;
  }
  
  public ErrorCode getErrorCode()
  {
    try
    {
      ErrorCode localErrorCode = errorCode;
      return localErrorCode;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  public int getId()
  {
    return id;
  }
  
  public List<Header> getRequestHeaders()
  {
    return requestHeaders;
  }
  
  public Sink getSink()
  {
    try
    {
      if ((!hasResponseHeaders) && (!isLocallyInitiated())) {
        throw new IllegalStateException("reply before requesting the sink");
      }
      return sink;
    }
    finally {}
  }
  
  public Source getSource()
  {
    return source;
  }
  
  public boolean isLocallyInitiated()
  {
    int i;
    if ((id & 0x1) == 1) {
      i = 1;
    } else {
      i = 0;
    }
    return connection.client == i;
  }
  
  public boolean isOpen()
  {
    try
    {
      ErrorCode localErrorCode = errorCode;
      if (localErrorCode != null) {
        return false;
      }
      if (((source.finished) || (source.closed)) && ((sink.finished) || (sink.closed)))
      {
        boolean bool = hasResponseHeaders;
        if (bool) {
          return false;
        }
      }
      return true;
    }
    finally {}
  }
  
  public Timeout readTimeout()
  {
    return readTimeout;
  }
  
  void receiveData(BufferedSource paramBufferedSource, int paramInt)
    throws IOException
  {
    source.receive(paramBufferedSource, paramInt);
  }
  
  void receiveFin()
  {
    try
    {
      source.finished = true;
      boolean bool = isOpen();
      notifyAll();
      if (!bool) {
        connection.removeStream(id);
      }
      return;
    }
    finally {}
  }
  
  void receiveHeaders(List<Header> paramList)
  {
    boolean bool = true;
    try
    {
      hasResponseHeaders = true;
      if (responseHeaders == null)
      {
        responseHeaders = paramList;
        bool = isOpen();
        notifyAll();
      }
      else
      {
        ArrayList localArrayList = new ArrayList();
        localArrayList.addAll(responseHeaders);
        localArrayList.add(null);
        localArrayList.addAll(paramList);
        responseHeaders = localArrayList;
      }
      if (!bool) {
        connection.removeStream(id);
      }
      return;
    }
    finally {}
  }
  
  void receiveRstStream(ErrorCode paramErrorCode)
  {
    try
    {
      if (errorCode == null)
      {
        errorCode = paramErrorCode;
        notifyAll();
      }
      return;
    }
    finally
    {
      paramErrorCode = finally;
      throw paramErrorCode;
    }
  }
  
  public void sendResponseHeaders(List<Header> paramList, boolean paramBoolean)
    throws IOException
  {
    if (paramList == null) {
      throw new NullPointerException("responseHeaders == null");
    }
    boolean bool = false;
    try
    {
      hasResponseHeaders = true;
      if (!paramBoolean)
      {
        sink.finished = true;
        bool = true;
      }
      connection.writeSynReply(id, bool, paramList);
      if (bool) {
        connection.flush();
      }
      return;
    }
    finally {}
  }
  
  /* Error */
  public List<Header> takeResponseHeaders()
    throws IOException
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokevirtual 168	okhttp3/internal/http2/Http2Stream:isLocallyInitiated	()Z
    //   6: ifne +13 -> 19
    //   9: new 170	java/lang/IllegalStateException
    //   12: dup
    //   13: ldc -36
    //   15: invokespecial 173	java/lang/IllegalStateException:<init>	(Ljava/lang/String;)V
    //   18: athrow
    //   19: aload_0
    //   20: getfield 51	okhttp3/internal/http2/Http2Stream:readTimeout	Lokhttp3/internal/http2/Http2Stream$StreamTimeout;
    //   23: invokevirtual 223	okhttp3/internal/http2/Http2Stream$StreamTimeout:enter	()V
    //   26: aload_0
    //   27: getfield 190	okhttp3/internal/http2/Http2Stream:responseHeaders	Ljava/util/List;
    //   30: ifnonnull +17 -> 47
    //   33: aload_0
    //   34: getfield 55	okhttp3/internal/http2/Http2Stream:errorCode	Lokhttp3/internal/http2/ErrorCode;
    //   37: ifnonnull +10 -> 47
    //   40: aload_0
    //   41: invokevirtual 226	okhttp3/internal/http2/Http2Stream:waitForIo	()V
    //   44: goto -18 -> 26
    //   47: aload_0
    //   48: getfield 51	okhttp3/internal/http2/Http2Stream:readTimeout	Lokhttp3/internal/http2/Http2Stream$StreamTimeout;
    //   51: invokevirtual 229	okhttp3/internal/http2/Http2Stream$StreamTimeout:exitAndThrowIfTimedOut	()V
    //   54: aload_0
    //   55: getfield 190	okhttp3/internal/http2/Http2Stream:responseHeaders	Ljava/util/List;
    //   58: astore_1
    //   59: aload_1
    //   60: ifnull +12 -> 72
    //   63: aload_0
    //   64: aconst_null
    //   65: putfield 190	okhttp3/internal/http2/Http2Stream:responseHeaders	Ljava/util/List;
    //   68: aload_0
    //   69: monitorexit
    //   70: aload_1
    //   71: areturn
    //   72: new 141	okhttp3/internal/http2/StreamResetException
    //   75: dup
    //   76: aload_0
    //   77: getfield 55	okhttp3/internal/http2/Http2Stream:errorCode	Lokhttp3/internal/http2/ErrorCode;
    //   80: invokespecial 143	okhttp3/internal/http2/StreamResetException:<init>	(Lokhttp3/internal/http2/ErrorCode;)V
    //   83: athrow
    //   84: astore_1
    //   85: aload_0
    //   86: getfield 51	okhttp3/internal/http2/Http2Stream:readTimeout	Lokhttp3/internal/http2/Http2Stream$StreamTimeout;
    //   89: invokevirtual 229	okhttp3/internal/http2/Http2Stream$StreamTimeout:exitAndThrowIfTimedOut	()V
    //   92: aload_1
    //   93: athrow
    //   94: astore_1
    //   95: aload_0
    //   96: monitorexit
    //   97: aload_1
    //   98: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	99	0	this	Http2Stream
    //   58	13	1	localList	List
    //   84	9	1	localObject1	Object
    //   94	4	1	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   26	44	84	finally
    //   2	19	94	finally
    //   19	26	94	finally
    //   47	59	94	finally
    //   63	68	94	finally
    //   72	84	94	finally
    //   85	94	94	finally
  }
  
  void waitForIo()
    throws InterruptedIOException
  {
    try
    {
      wait();
      return;
    }
    catch (InterruptedException localInterruptedException)
    {
      for (;;) {}
    }
    throw new InterruptedIOException();
  }
  
  public Timeout writeTimeout()
  {
    return writeTimeout;
  }
  
  final class FramingSink
    implements Sink
  {
    private static final long EMIT_BUFFER_SIZE = 16384L;
    boolean closed;
    boolean finished;
    private final Buffer sendBuffer = new Buffer();
    
    FramingSink() {}
    
    private void emitFrame(boolean paramBoolean)
      throws IOException
    {
      for (;;)
      {
        synchronized (Http2Stream.this)
        {
          writeTimeout.enter();
          try
          {
            if ((bytesLeftInWriteWindow <= 0L) && (!finished) && (!closed) && (errorCode == null))
            {
              waitForIo();
              continue;
            }
            writeTimeout.exitAndThrowIfTimedOut();
            checkOutNotClosed();
            long l = Math.min(bytesLeftInWriteWindow, sendBuffer.size());
            Http2Stream localHttp2Stream = Http2Stream.this;
            bytesLeftInWriteWindow -= l;
            writeTimeout.enter();
            try
            {
              ??? = connection;
              int i = id;
              if ((!paramBoolean) || (l != sendBuffer.size())) {
                break label230;
              }
              paramBoolean = true;
              ((Http2Connection)???).writeData(i, paramBoolean, sendBuffer, l);
              return;
            }
            finally
            {
              writeTimeout.exitAndThrowIfTimedOut();
            }
            localObject4 = finally;
          }
          finally
          {
            writeTimeout.exitAndThrowIfTimedOut();
          }
        }
        label230:
        paramBoolean = false;
      }
    }
    
    public void close()
      throws IOException
    {
      synchronized (Http2Stream.this)
      {
        if (closed) {
          return;
        }
        if (!sink.finished)
        {
          if (sendBuffer.size() > 0L) {
            while (sendBuffer.size() > 0L) {
              emitFrame(true);
            }
          }
          connection.writeData(id, true, null, 0L);
        }
        synchronized (Http2Stream.this)
        {
          closed = true;
          connection.flush();
          cancelStreamIfNecessary();
          return;
        }
      }
    }
    
    public void flush()
      throws IOException
    {
      synchronized (Http2Stream.this)
      {
        checkOutNotClosed();
        while (sendBuffer.size() > 0L)
        {
          emitFrame(false);
          connection.flush();
        }
        return;
      }
    }
    
    public Timeout timeout()
    {
      return writeTimeout;
    }
    
    public void write(Buffer paramBuffer, long paramLong)
      throws IOException
    {
      sendBuffer.write(paramBuffer, paramLong);
      while (sendBuffer.size() >= 16384L) {
        emitFrame(false);
      }
    }
  }
  
  private final class FramingSource
    implements Source
  {
    boolean closed;
    boolean finished;
    private final long maxByteCount;
    private final Buffer readBuffer = new Buffer();
    private final Buffer receiveBuffer = new Buffer();
    
    FramingSource(long paramLong)
    {
      maxByteCount = paramLong;
    }
    
    private void checkNotClosed()
      throws IOException
    {
      if (closed) {
        throw new IOException("stream closed");
      }
      if (errorCode != null) {
        throw new StreamResetException(errorCode);
      }
    }
    
    private void waitUntilReadable()
      throws IOException
    {
      readTimeout.enter();
      try
      {
        while ((readBuffer.size() == 0L) && (!finished) && (!closed) && (errorCode == null)) {
          waitForIo();
        }
        return;
      }
      finally
      {
        readTimeout.exitAndThrowIfTimedOut();
      }
    }
    
    public void close()
      throws IOException
    {
      synchronized (Http2Stream.this)
      {
        closed = true;
        readBuffer.clear();
        notifyAll();
        cancelStreamIfNecessary();
        return;
      }
    }
    
    public long read(Buffer arg1, long paramLong)
      throws IOException
    {
      if (paramLong < 0L)
      {
        ??? = new StringBuilder();
        ???.append("byteCount < 0: ");
        ???.append(paramLong);
        throw new IllegalArgumentException(???.toString());
      }
      synchronized (Http2Stream.this)
      {
        waitUntilReadable();
        checkNotClosed();
        if (readBuffer.size() == 0L) {
          return -1L;
        }
        paramLong = readBuffer.read(???, Math.min(paramLong, readBuffer.size()));
        ??? = Http2Stream.this;
        unacknowledgedBytesRead += paramLong;
        if (unacknowledgedBytesRead >= connection.okHttpSettings.getInitialWindowSize() / 2)
        {
          connection.writeWindowUpdateLater(id, unacknowledgedBytesRead);
          unacknowledgedBytesRead = 0L;
        }
        synchronized (connection)
        {
          ??? = connection;
          unacknowledgedBytesRead += paramLong;
          if (connection.unacknowledgedBytesRead >= connection.okHttpSettings.getInitialWindowSize() / 2)
          {
            connection.writeWindowUpdateLater(0, connection.unacknowledgedBytesRead);
            connection.unacknowledgedBytesRead = 0L;
          }
          return paramLong;
        }
      }
    }
    
    void receive(BufferedSource paramBufferedSource, long paramLong)
      throws IOException
    {
      if (paramLong > 0L) {}
      for (;;)
      {
        synchronized (Http2Stream.this)
        {
          boolean bool = finished;
          long l1 = readBuffer.size();
          long l2 = maxByteCount;
          int j = 0;
          if (paramLong + l1 <= l2) {
            break label200;
          }
          i = 1;
          if (i != 0)
          {
            paramBufferedSource.skip(paramLong);
            closeLater(ErrorCode.FLOW_CONTROL_ERROR);
            return;
          }
          if (bool)
          {
            paramBufferedSource.skip(paramLong);
            return;
          }
          l1 = paramBufferedSource.read(receiveBuffer, paramLong);
          if (l1 == -1L) {
            throw new EOFException();
          }
          ??? = Http2Stream.this;
          i = j;
          try
          {
            if (readBuffer.size() == 0L) {
              i = 1;
            }
            readBuffer.writeAll(receiveBuffer);
            if (i != 0) {
              notifyAll();
            }
            paramLong -= l1;
            break;
          }
          finally {}
        }
        return;
        label200:
        int i = 0;
      }
    }
    
    public Timeout timeout()
    {
      return readTimeout;
    }
  }
  
  class StreamTimeout
    extends AsyncTimeout
  {
    StreamTimeout() {}
    
    public void exitAndThrowIfTimedOut()
      throws IOException
    {
      if (exit()) {
        throw newTimeoutException(null);
      }
    }
    
    protected IOException newTimeoutException(IOException paramIOException)
    {
      SocketTimeoutException localSocketTimeoutException = new SocketTimeoutException("timeout");
      if (paramIOException != null) {
        localSocketTimeoutException.initCause(paramIOException);
      }
      return localSocketTimeoutException;
    }
    
    protected void timedOut()
    {
      closeLater(ErrorCode.CANCEL);
    }
  }
}
