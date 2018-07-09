package okhttp3.internal.http2;

import java.io.Closeable;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import okhttp3.Protocol;
import okhttp3.internal.NamedRunnable;
import okhttp3.internal.Util;
import okhttp3.internal.platform.Platform;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ByteString;
import okio.Okio;

public final class Http2Connection
  implements Closeable
{
  private static final int OKHTTP_CLIENT_WINDOW_SIZE = 16777216;
  static final ExecutorService executor = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue(), Util.threadFactory("OkHttp Http2Connection", true));
  long bytesLeftInWriteWindow;
  final boolean client;
  final Set<Integer> currentPushRequests = new LinkedHashSet();
  final String hostname;
  int lastGoodStreamId;
  final Listener listener;
  private int nextPingId;
  int nextStreamId;
  Settings okHttpSettings = new Settings();
  final Settings peerSettings = new Settings();
  private Map<Integer, Ping> pings;
  private final ExecutorService pushExecutor;
  final PushObserver pushObserver;
  final ReaderRunnable readerRunnable;
  boolean receivedInitialPeerSettings = false;
  boolean shutdown;
  final Socket socket;
  final Map<Integer, Http2Stream> streams = new LinkedHashMap();
  long unacknowledgedBytesRead = 0L;
  final Http2Writer writer;
  
  Http2Connection(Builder paramBuilder)
  {
    pushObserver = pushObserver;
    client = client;
    listener = listener;
    boolean bool = client;
    int j = 2;
    if (bool) {
      i = 1;
    } else {
      i = 2;
    }
    nextStreamId = i;
    if (client) {
      nextStreamId += 2;
    }
    int i = j;
    if (client) {
      i = 1;
    }
    nextPingId = i;
    if (client) {
      okHttpSettings.set(7, 16777216);
    }
    hostname = hostname;
    pushExecutor = new ThreadPoolExecutor(0, 1, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue(), Util.threadFactory(Util.format("OkHttp %s Push Observer", new Object[] { hostname }), true));
    peerSettings.set(7, 65535);
    peerSettings.set(5, 16384);
    bytesLeftInWriteWindow = peerSettings.getInitialWindowSize();
    socket = socket;
    writer = new Http2Writer(sink, client);
    readerRunnable = new ReaderRunnable(new Http2Reader(source, client));
  }
  
  private Http2Stream newStream(int paramInt, List<Header> paramList, boolean paramBoolean)
    throws IOException
  {
    boolean bool = paramBoolean ^ true;
    for (;;)
    {
      synchronized (writer)
      {
        int j;
        Http2Stream localHttp2Stream;
        try
        {
          if (shutdown) {
            throw new ConnectionShutdownException();
          }
          j = nextStreamId;
          nextStreamId += 2;
          localHttp2Stream = new Http2Stream(j, this, bool, false, paramList);
          if ((!paramBoolean) || (bytesLeftInWriteWindow == 0L)) {
            break label199;
          }
          if (bytesLeftInWriteWindow != 0L) {
            break label193;
          }
        }
        finally {}
        if (localHttp2Stream.isOpen()) {
          streams.put(Integer.valueOf(j), localHttp2Stream);
        }
        if (paramInt == 0)
        {
          writer.synStream(bool, j, paramInt, paramList);
        }
        else
        {
          if (client) {
            throw new IllegalArgumentException("client streams shouldn't have associated stream IDs");
          }
          writer.pushPromise(paramInt, j, paramList);
        }
        if (i != 0) {
          writer.flush();
        }
        return localHttp2Stream;
      }
      label193:
      int i = 0;
      continue;
      label199:
      i = 1;
    }
  }
  
  void addBytesToWriteWindow(long paramLong)
  {
    bytesLeftInWriteWindow += paramLong;
    if (paramLong > 0L) {
      notifyAll();
    }
  }
  
  public void close()
    throws IOException
  {
    close(ErrorCode.NO_ERROR, ErrorCode.CANCEL);
  }
  
  void close(ErrorCode paramErrorCode1, ErrorCode paramErrorCode2)
    throws IOException
  {
    Ping[] arrayOfPing = null;
    try
    {
      shutdown(paramErrorCode1);
      paramErrorCode1 = null;
    }
    catch (IOException paramErrorCode1) {}
    for (;;)
    {
      try
      {
        if (!streams.isEmpty())
        {
          arrayOfHttp2Stream = (Http2Stream[])streams.values().toArray(new Http2Stream[streams.size()]);
          streams.clear();
          if (pings != null)
          {
            arrayOfPing = (Ping[])pings.values().toArray(new Ping[pings.size()]);
            pings = null;
          }
          int j = 0;
          Object localObject = paramErrorCode1;
          int k;
          int i;
          if (arrayOfHttp2Stream != null)
          {
            k = arrayOfHttp2Stream.length;
            i = 0;
            if (i < k)
            {
              localObject = arrayOfHttp2Stream[i];
              try
              {
                ((Http2Stream)localObject).close(paramErrorCode2);
                localObject = paramErrorCode1;
              }
              catch (IOException localIOException)
              {
                localObject = paramErrorCode1;
                if (paramErrorCode1 != null) {
                  localObject = localIOException;
                }
              }
              i += 1;
              paramErrorCode1 = (ErrorCode)localObject;
              continue;
            }
            localObject = paramErrorCode1;
          }
          if (arrayOfPing != null)
          {
            k = arrayOfPing.length;
            i = j;
            if (i < k)
            {
              arrayOfPing[i].cancel();
              i += 1;
              continue;
            }
          }
          try
          {
            writer.close();
            paramErrorCode1 = (ErrorCode)localObject;
          }
          catch (IOException paramErrorCode2)
          {
            paramErrorCode1 = (ErrorCode)localObject;
            if (localObject == null) {
              paramErrorCode1 = paramErrorCode2;
            }
          }
          try
          {
            socket.close();
          }
          catch (IOException paramErrorCode1) {}
          if (paramErrorCode1 != null) {
            throw paramErrorCode1;
          }
          return;
        }
      }
      finally {}
      Http2Stream[] arrayOfHttp2Stream = null;
    }
  }
  
  public void flush()
    throws IOException
  {
    writer.flush();
  }
  
  public Protocol getProtocol()
  {
    return Protocol.HTTP_2;
  }
  
  Http2Stream getStream(int paramInt)
  {
    try
    {
      Http2Stream localHttp2Stream = (Http2Stream)streams.get(Integer.valueOf(paramInt));
      return localHttp2Stream;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  public boolean isShutdown()
  {
    try
    {
      boolean bool = shutdown;
      return bool;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  public int maxConcurrentStreams()
  {
    try
    {
      int i = peerSettings.getMaxConcurrentStreams(Integer.MAX_VALUE);
      return i;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  public Http2Stream newStream(List<Header> paramList, boolean paramBoolean)
    throws IOException
  {
    return newStream(0, paramList, paramBoolean);
  }
  
  public int openStreamCount()
  {
    try
    {
      int i = streams.size();
      return i;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  public Ping ping()
    throws IOException
  {
    Ping localPing = new Ping();
    try
    {
      if (shutdown) {
        throw new ConnectionShutdownException();
      }
      int i = nextPingId;
      nextPingId += 2;
      if (pings == null) {
        pings = new LinkedHashMap();
      }
      pings.put(Integer.valueOf(i), localPing);
      writePing(false, i, 1330343787, localPing);
      return localPing;
    }
    finally {}
  }
  
  void pushDataLater(final int paramInt1, BufferedSource paramBufferedSource, final int paramInt2, final boolean paramBoolean)
    throws IOException
  {
    final Buffer localBuffer = new Buffer();
    long l = paramInt2;
    paramBufferedSource.require(l);
    paramBufferedSource.read(localBuffer, l);
    if (localBuffer.size() != l)
    {
      paramBufferedSource = new StringBuilder();
      paramBufferedSource.append(localBuffer.size());
      paramBufferedSource.append(" != ");
      paramBufferedSource.append(paramInt2);
      throw new IOException(paramBufferedSource.toString());
    }
    pushExecutor.execute(new NamedRunnable("OkHttp %s Push Data[%s]", new Object[] { hostname, Integer.valueOf(paramInt1) })
    {
      public void execute()
      {
        try
        {
          boolean bool = pushObserver.onData(paramInt1, localBuffer, paramInt2, paramBoolean);
          if (bool) {
            writer.rstStream(paramInt1, ErrorCode.CANCEL);
          }
          if ((bool) || (paramBoolean)) {
            synchronized (Http2Connection.this)
            {
              currentPushRequests.remove(Integer.valueOf(paramInt1));
              return;
            }
          }
          return;
        }
        catch (IOException localIOException) {}
      }
    });
  }
  
  void pushHeadersLater(final int paramInt, final List<Header> paramList, final boolean paramBoolean)
  {
    pushExecutor.execute(new NamedRunnable("OkHttp %s Push Headers[%s]", new Object[] { hostname, Integer.valueOf(paramInt) })
    {
      public void execute()
      {
        boolean bool = pushObserver.onHeaders(paramInt, paramList, paramBoolean);
        if (bool) {}
        try
        {
          writer.rstStream(paramInt, ErrorCode.CANCEL);
          if ((bool) || (paramBoolean)) {
            synchronized (Http2Connection.this)
            {
              currentPushRequests.remove(Integer.valueOf(paramInt));
              return;
            }
          }
          return;
        }
        catch (IOException localIOException) {}
      }
    });
  }
  
  void pushRequestLater(final int paramInt, final List<Header> paramList)
  {
    try
    {
      if (currentPushRequests.contains(Integer.valueOf(paramInt)))
      {
        writeSynResetLater(paramInt, ErrorCode.PROTOCOL_ERROR);
        return;
      }
      currentPushRequests.add(Integer.valueOf(paramInt));
      pushExecutor.execute(new NamedRunnable("OkHttp %s Push Request[%s]", new Object[] { hostname, Integer.valueOf(paramInt) })
      {
        public void execute()
        {
          if (pushObserver.onRequest(paramInt, paramList)) {}
          try
          {
            writer.rstStream(paramInt, ErrorCode.CANCEL);
            synchronized (Http2Connection.this)
            {
              currentPushRequests.remove(Integer.valueOf(paramInt));
              return;
            }
            return;
          }
          catch (IOException localIOException) {}
        }
      });
      return;
    }
    finally {}
  }
  
  void pushResetLater(final int paramInt, final ErrorCode paramErrorCode)
  {
    pushExecutor.execute(new NamedRunnable("OkHttp %s Push Reset[%s]", new Object[] { hostname, Integer.valueOf(paramInt) })
    {
      public void execute()
      {
        pushObserver.onReset(paramInt, paramErrorCode);
        synchronized (Http2Connection.this)
        {
          currentPushRequests.remove(Integer.valueOf(paramInt));
          return;
        }
      }
    });
  }
  
  public Http2Stream pushStream(int paramInt, List<Header> paramList, boolean paramBoolean)
    throws IOException
  {
    if (client) {
      throw new IllegalStateException("Client cannot push requests.");
    }
    return newStream(paramInt, paramList, paramBoolean);
  }
  
  boolean pushedStream(int paramInt)
  {
    return (paramInt != 0) && ((paramInt & 0x1) == 0);
  }
  
  Ping removePing(int paramInt)
  {
    try
    {
      Ping localPing;
      if (pings != null) {
        localPing = (Ping)pings.remove(Integer.valueOf(paramInt));
      } else {
        localPing = null;
      }
      return localPing;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  Http2Stream removeStream(int paramInt)
  {
    try
    {
      Http2Stream localHttp2Stream = (Http2Stream)streams.remove(Integer.valueOf(paramInt));
      notifyAll();
      return localHttp2Stream;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  public void setSettings(Settings paramSettings)
    throws IOException
  {
    synchronized (writer)
    {
      try
      {
        if (shutdown) {
          throw new ConnectionShutdownException();
        }
        okHttpSettings.merge(paramSettings);
        writer.settings(paramSettings);
        return;
      }
      finally {}
    }
  }
  
  public void shutdown(ErrorCode paramErrorCode)
    throws IOException
  {
    synchronized (writer)
    {
      try
      {
        if (shutdown) {
          return;
        }
        shutdown = true;
        int i = lastGoodStreamId;
        writer.goAway(i, paramErrorCode, Util.EMPTY_BYTE_ARRAY);
        return;
      }
      finally {}
    }
  }
  
  public void start()
    throws IOException
  {
    start(true);
  }
  
  void start(boolean paramBoolean)
    throws IOException
  {
    if (paramBoolean)
    {
      writer.connectionPreface();
      writer.settings(okHttpSettings);
      int i = okHttpSettings.getInitialWindowSize();
      if (i != 65535) {
        writer.windowUpdate(0, i - 65535);
      }
    }
    new Thread(readerRunnable).start();
  }
  
  /* Error */
  public void writeData(int paramInt, boolean paramBoolean, Buffer paramBuffer, long paramLong)
    throws IOException
  {
    // Byte code:
    //   0: lload 4
    //   2: lstore 8
    //   4: lload 4
    //   6: lconst_0
    //   7: lcmp
    //   8: ifne +15 -> 23
    //   11: aload_0
    //   12: getfield 185	okhttp3/internal/http2/Http2Connection:writer	Lokhttp3/internal/http2/Http2Writer;
    //   15: iload_2
    //   16: iload_1
    //   17: aload_3
    //   18: iconst_0
    //   19: invokevirtual 485	okhttp3/internal/http2/Http2Writer:data	(ZILokio/Buffer;I)V
    //   22: return
    //   23: lload 8
    //   25: lconst_0
    //   26: lcmp
    //   27: ifle +155 -> 182
    //   30: aload_0
    //   31: monitorenter
    //   32: aload_0
    //   33: getfield 171	okhttp3/internal/http2/Http2Connection:bytesLeftInWriteWindow	J
    //   36: lconst_0
    //   37: lcmp
    //   38: ifgt +37 -> 75
    //   41: aload_0
    //   42: getfield 117	okhttp3/internal/http2/Http2Connection:streams	Ljava/util/Map;
    //   45: iload_1
    //   46: invokestatic 224	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   49: invokeinterface 488 2 0
    //   54: ifne +14 -> 68
    //   57: new 203	java/io/IOException
    //   60: dup
    //   61: ldc_w 490
    //   64: invokespecial 379	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   67: athrow
    //   68: aload_0
    //   69: invokevirtual 493	java/lang/Object:wait	()V
    //   72: goto -40 -> 32
    //   75: lload 8
    //   77: aload_0
    //   78: getfield 171	okhttp3/internal/http2/Http2Connection:bytesLeftInWriteWindow	J
    //   81: invokestatic 499	java/lang/Math:min	(JJ)J
    //   84: l2i
    //   85: aload_0
    //   86: getfield 185	okhttp3/internal/http2/Http2Connection:writer	Lokhttp3/internal/http2/Http2Writer;
    //   89: invokevirtual 502	okhttp3/internal/http2/Http2Writer:maxDataLength	()I
    //   92: invokestatic 505	java/lang/Math:min	(II)I
    //   95: istore 6
    //   97: aload_0
    //   98: getfield 171	okhttp3/internal/http2/Http2Connection:bytesLeftInWriteWindow	J
    //   101: lstore 4
    //   103: iload 6
    //   105: i2l
    //   106: lstore 10
    //   108: aload_0
    //   109: lload 4
    //   111: lload 10
    //   113: lsub
    //   114: putfield 171	okhttp3/internal/http2/Http2Connection:bytesLeftInWriteWindow	J
    //   117: aload_0
    //   118: monitorexit
    //   119: lload 8
    //   121: lload 10
    //   123: lsub
    //   124: lstore 8
    //   126: aload_0
    //   127: getfield 185	okhttp3/internal/http2/Http2Connection:writer	Lokhttp3/internal/http2/Http2Writer;
    //   130: astore 12
    //   132: iload_2
    //   133: ifeq +16 -> 149
    //   136: lload 8
    //   138: lconst_0
    //   139: lcmp
    //   140: ifne +9 -> 149
    //   143: iconst_1
    //   144: istore 7
    //   146: goto +6 -> 152
    //   149: iconst_0
    //   150: istore 7
    //   152: aload 12
    //   154: iload 7
    //   156: iload_1
    //   157: aload_3
    //   158: iload 6
    //   160: invokevirtual 485	okhttp3/internal/http2/Http2Writer:data	(ZILokio/Buffer;I)V
    //   163: goto -140 -> 23
    //   166: astore_3
    //   167: goto +11 -> 178
    //   170: new 507	java/io/InterruptedIOException
    //   173: dup
    //   174: invokespecial 508	java/io/InterruptedIOException:<init>	()V
    //   177: athrow
    //   178: aload_0
    //   179: monitorexit
    //   180: aload_3
    //   181: athrow
    //   182: return
    //   183: astore_3
    //   184: goto -14 -> 170
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	187	0	this	Http2Connection
    //   0	187	1	paramInt	int
    //   0	187	2	paramBoolean	boolean
    //   0	187	3	paramBuffer	Buffer
    //   0	187	4	paramLong	long
    //   95	64	6	i	int
    //   144	11	7	bool	boolean
    //   2	135	8	l1	long
    //   106	16	10	l2	long
    //   130	23	12	localHttp2Writer	Http2Writer
    // Exception table:
    //   from	to	target	type
    //   32	68	166	finally
    //   68	72	166	finally
    //   75	103	166	finally
    //   108	119	166	finally
    //   170	178	166	finally
    //   178	180	166	finally
    //   32	68	183	java/lang/InterruptedException
    //   68	72	183	java/lang/InterruptedException
  }
  
  void writePing(boolean paramBoolean, int paramInt1, int paramInt2, Ping paramPing)
    throws IOException
  {
    Http2Writer localHttp2Writer = writer;
    if (paramPing != null) {}
    try
    {
      paramPing.send();
      writer.ping(paramBoolean, paramInt1, paramInt2);
      return;
    }
    finally
    {
      for (;;) {}
    }
    throw paramPing;
  }
  
  void writePingLater(final boolean paramBoolean, final int paramInt1, final int paramInt2, final Ping paramPing)
  {
    executor.execute(new NamedRunnable("OkHttp %s ping %08x%08x", new Object[] { hostname, Integer.valueOf(paramInt1), Integer.valueOf(paramInt2) })
    {
      public void execute()
      {
        try
        {
          writePing(paramBoolean, paramInt1, paramInt2, paramPing);
          return;
        }
        catch (IOException localIOException) {}
      }
    });
  }
  
  void writeSynReply(int paramInt, boolean paramBoolean, List<Header> paramList)
    throws IOException
  {
    writer.synReply(paramBoolean, paramInt, paramList);
  }
  
  void writeSynReset(int paramInt, ErrorCode paramErrorCode)
    throws IOException
  {
    writer.rstStream(paramInt, paramErrorCode);
  }
  
  void writeSynResetLater(final int paramInt, final ErrorCode paramErrorCode)
  {
    executor.execute(new NamedRunnable("OkHttp %s stream %d", new Object[] { hostname, Integer.valueOf(paramInt) })
    {
      public void execute()
      {
        try
        {
          writeSynReset(paramInt, paramErrorCode);
          return;
        }
        catch (IOException localIOException) {}
      }
    });
  }
  
  void writeWindowUpdateLater(final int paramInt, final long paramLong)
  {
    executor.execute(new NamedRunnable("OkHttp Window Update %s stream %d", new Object[] { hostname, Integer.valueOf(paramInt) })
    {
      public void execute()
      {
        try
        {
          writer.windowUpdate(paramInt, paramLong);
          return;
        }
        catch (IOException localIOException) {}
      }
    });
  }
  
  public static class Builder
  {
    boolean client;
    String hostname;
    Http2Connection.Listener listener = Http2Connection.Listener.REFUSE_INCOMING_STREAMS;
    PushObserver pushObserver = PushObserver.CANCEL;
    BufferedSink sink;
    Socket socket;
    BufferedSource source;
    
    public Builder(boolean paramBoolean)
    {
      client = paramBoolean;
    }
    
    public Http2Connection build()
    {
      return new Http2Connection(this);
    }
    
    public Builder listener(Http2Connection.Listener paramListener)
    {
      listener = paramListener;
      return this;
    }
    
    public Builder pushObserver(PushObserver paramPushObserver)
    {
      pushObserver = paramPushObserver;
      return this;
    }
    
    public Builder socket(Socket paramSocket)
      throws IOException
    {
      return socket(paramSocket, ((InetSocketAddress)paramSocket.getRemoteSocketAddress()).getHostName(), Okio.buffer(Okio.source(paramSocket)), Okio.buffer(Okio.sink(paramSocket)));
    }
    
    public Builder socket(Socket paramSocket, String paramString, BufferedSource paramBufferedSource, BufferedSink paramBufferedSink)
    {
      socket = paramSocket;
      hostname = paramString;
      source = paramBufferedSource;
      sink = paramBufferedSink;
      return this;
    }
  }
  
  public static abstract class Listener
  {
    public static final Listener REFUSE_INCOMING_STREAMS = new Listener()
    {
      public void onStream(Http2Stream paramAnonymousHttp2Stream)
        throws IOException
      {
        paramAnonymousHttp2Stream.close(ErrorCode.REFUSED_STREAM);
      }
    };
    
    public Listener() {}
    
    public void onSettings(Http2Connection paramHttp2Connection) {}
    
    public abstract void onStream(Http2Stream paramHttp2Stream)
      throws IOException;
  }
  
  class ReaderRunnable
    extends NamedRunnable
    implements Http2Reader.Handler
  {
    final Http2Reader reader;
    
    ReaderRunnable(Http2Reader paramHttp2Reader)
    {
      super(new Object[] { hostname });
      reader = paramHttp2Reader;
    }
    
    private void applyAndAckSettings(final Settings paramSettings)
    {
      Http2Connection.executor.execute(new NamedRunnable("OkHttp %s ACK Settings", new Object[] { hostname })
      {
        public void execute()
        {
          try
          {
            writer.applyAndAckSettings(paramSettings);
            return;
          }
          catch (IOException localIOException) {}
        }
      });
    }
    
    public void ackSettings() {}
    
    public void alternateService(int paramInt1, String paramString1, ByteString paramByteString, String paramString2, int paramInt2, long paramLong) {}
    
    public void data(boolean paramBoolean, int paramInt1, BufferedSource paramBufferedSource, int paramInt2)
      throws IOException
    {
      if (pushedStream(paramInt1))
      {
        pushDataLater(paramInt1, paramBufferedSource, paramInt2, paramBoolean);
        return;
      }
      Http2Stream localHttp2Stream = getStream(paramInt1);
      if (localHttp2Stream == null)
      {
        writeSynResetLater(paramInt1, ErrorCode.PROTOCOL_ERROR);
        paramBufferedSource.skip(paramInt2);
        return;
      }
      localHttp2Stream.receiveData(paramBufferedSource, paramInt2);
      if (paramBoolean) {
        localHttp2Stream.receiveFin();
      }
    }
    
    /* Error */
    protected void execute()
    {
      // Byte code:
      //   0: getstatic 103	okhttp3/internal/http2/ErrorCode:INTERNAL_ERROR	Lokhttp3/internal/http2/ErrorCode;
      //   3: astore_3
      //   4: getstatic 103	okhttp3/internal/http2/ErrorCode:INTERNAL_ERROR	Lokhttp3/internal/http2/ErrorCode;
      //   7: astore 5
      //   9: aload_3
      //   10: astore_2
      //   11: aload_0
      //   12: getfield 36	okhttp3/internal/http2/Http2Connection$ReaderRunnable:reader	Lokhttp3/internal/http2/Http2Reader;
      //   15: aload_0
      //   16: invokevirtual 109	okhttp3/internal/http2/Http2Reader:readConnectionPreface	(Lokhttp3/internal/http2/Http2Reader$Handler;)V
      //   19: aload_3
      //   20: astore_2
      //   21: aload_0
      //   22: getfield 36	okhttp3/internal/http2/Http2Connection$ReaderRunnable:reader	Lokhttp3/internal/http2/Http2Reader;
      //   25: iconst_0
      //   26: aload_0
      //   27: invokevirtual 113	okhttp3/internal/http2/Http2Reader:nextFrame	(ZLokhttp3/internal/http2/Http2Reader$Handler;)Z
      //   30: ifeq +6 -> 36
      //   33: goto -14 -> 19
      //   36: aload_3
      //   37: astore_2
      //   38: getstatic 116	okhttp3/internal/http2/ErrorCode:NO_ERROR	Lokhttp3/internal/http2/ErrorCode;
      //   41: astore_1
      //   42: aload_1
      //   43: astore_2
      //   44: getstatic 119	okhttp3/internal/http2/ErrorCode:CANCEL	Lokhttp3/internal/http2/ErrorCode;
      //   47: astore_3
      //   48: aload_0
      //   49: getfield 23	okhttp3/internal/http2/Http2Connection$ReaderRunnable:this$0	Lokhttp3/internal/http2/Http2Connection;
      //   52: astore 4
      //   54: aload_3
      //   55: astore_2
      //   56: aload 4
      //   58: astore_3
      //   59: goto +31 -> 90
      //   62: aload_1
      //   63: astore_2
      //   64: goto +7 -> 71
      //   67: astore_1
      //   68: goto +37 -> 105
      //   71: getstatic 80	okhttp3/internal/http2/ErrorCode:PROTOCOL_ERROR	Lokhttp3/internal/http2/ErrorCode;
      //   74: astore_1
      //   75: aload_1
      //   76: astore_2
      //   77: getstatic 80	okhttp3/internal/http2/ErrorCode:PROTOCOL_ERROR	Lokhttp3/internal/http2/ErrorCode;
      //   80: astore 4
      //   82: aload_0
      //   83: getfield 23	okhttp3/internal/http2/Http2Connection$ReaderRunnable:this$0	Lokhttp3/internal/http2/Http2Connection;
      //   86: astore_3
      //   87: aload 4
      //   89: astore_2
      //   90: aload_3
      //   91: aload_1
      //   92: aload_2
      //   93: invokevirtual 123	okhttp3/internal/http2/Http2Connection:close	(Lokhttp3/internal/http2/ErrorCode;Lokhttp3/internal/http2/ErrorCode;)V
      //   96: aload_0
      //   97: getfield 36	okhttp3/internal/http2/Http2Connection$ReaderRunnable:reader	Lokhttp3/internal/http2/Http2Reader;
      //   100: invokestatic 129	okhttp3/internal/Util:closeQuietly	(Ljava/io/Closeable;)V
      //   103: return
      //   104: astore_1
      //   105: aload_0
      //   106: getfield 23	okhttp3/internal/http2/Http2Connection$ReaderRunnable:this$0	Lokhttp3/internal/http2/Http2Connection;
      //   109: aload_2
      //   110: aload 5
      //   112: invokevirtual 123	okhttp3/internal/http2/Http2Connection:close	(Lokhttp3/internal/http2/ErrorCode;Lokhttp3/internal/http2/ErrorCode;)V
      //   115: aload_0
      //   116: getfield 36	okhttp3/internal/http2/Http2Connection$ReaderRunnable:reader	Lokhttp3/internal/http2/Http2Reader;
      //   119: invokestatic 129	okhttp3/internal/Util:closeQuietly	(Ljava/io/Closeable;)V
      //   122: aload_1
      //   123: athrow
      //   124: astore_1
      //   125: aload_3
      //   126: astore_2
      //   127: goto -56 -> 71
      //   130: astore_2
      //   131: goto -69 -> 62
      //   134: astore_1
      //   135: goto -39 -> 96
      //   138: astore_2
      //   139: goto -24 -> 115
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	142	0	this	ReaderRunnable
      //   41	22	1	localErrorCode1	ErrorCode
      //   67	1	1	localObject1	Object
      //   74	18	1	localErrorCode2	ErrorCode
      //   104	19	1	localObject2	Object
      //   124	1	1	localIOException1	IOException
      //   134	1	1	localIOException2	IOException
      //   10	117	2	localObject3	Object
      //   130	1	2	localIOException3	IOException
      //   138	1	2	localIOException4	IOException
      //   3	123	3	localObject4	Object
      //   52	36	4	localObject5	Object
      //   7	104	5	localErrorCode3	ErrorCode
      // Exception table:
      //   from	to	target	type
      //   11	19	67	finally
      //   21	33	67	finally
      //   38	42	67	finally
      //   71	75	67	finally
      //   44	48	104	finally
      //   77	82	104	finally
      //   11	19	124	java/io/IOException
      //   21	33	124	java/io/IOException
      //   38	42	124	java/io/IOException
      //   44	48	130	java/io/IOException
      //   48	54	134	java/io/IOException
      //   82	87	134	java/io/IOException
      //   90	96	134	java/io/IOException
      //   105	115	138	java/io/IOException
    }
    
    public void goAway(int paramInt, ErrorCode arg2, ByteString paramByteString)
    {
      paramByteString.size();
      synchronized (Http2Connection.this)
      {
        paramByteString = (Http2Stream[])streams.values().toArray(new Http2Stream[streams.size()]);
        shutdown = true;
        int j = paramByteString.length;
        int i = 0;
        while (i < j)
        {
          ??? = paramByteString[i];
          if ((???.getId() > paramInt) && (???.isLocallyInitiated()))
          {
            ???.receiveRstStream(ErrorCode.REFUSED_STREAM);
            removeStream(???.getId());
          }
          i += 1;
        }
        return;
      }
    }
    
    public void headers(boolean paramBoolean, int paramInt1, int paramInt2, final List<Header> paramList)
    {
      if (pushedStream(paramInt1))
      {
        pushHeadersLater(paramInt1, paramList, paramBoolean);
        return;
      }
      synchronized (Http2Connection.this)
      {
        Http2Stream localHttp2Stream = getStream(paramInt1);
        if (localHttp2Stream == null)
        {
          if (shutdown) {
            return;
          }
          if (paramInt1 <= lastGoodStreamId) {
            return;
          }
          if (paramInt1 % 2 == nextStreamId % 2) {
            return;
          }
          paramList = new Http2Stream(paramInt1, Http2Connection.this, false, paramBoolean, paramList);
          lastGoodStreamId = paramInt1;
          streams.put(Integer.valueOf(paramInt1), paramList);
          Http2Connection.executor.execute(new NamedRunnable("OkHttp %s stream %d", new Object[] { hostname, Integer.valueOf(paramInt1) })
          {
            public void execute()
            {
              try
              {
                listener.onStream(paramList);
                return;
              }
              catch (IOException localIOException1)
              {
                Platform localPlatform = Platform.get();
                StringBuilder localStringBuilder = new StringBuilder();
                localStringBuilder.append("Http2Connection.Listener failure for ");
                localStringBuilder.append(hostname);
                localPlatform.log(4, localStringBuilder.toString(), localIOException1);
                try
                {
                  paramList.close(ErrorCode.PROTOCOL_ERROR);
                  return;
                }
                catch (IOException localIOException2) {}
              }
            }
          });
          return;
        }
        localHttp2Stream.receiveHeaders(paramList);
        if (paramBoolean) {
          localHttp2Stream.receiveFin();
        }
        return;
      }
    }
    
    public void ping(boolean paramBoolean, int paramInt1, int paramInt2)
    {
      if (paramBoolean)
      {
        Ping localPing = removePing(paramInt1);
        if (localPing != null) {
          localPing.receive();
        }
      }
      else
      {
        writePingLater(true, paramInt1, paramInt2, null);
      }
    }
    
    public void priority(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean) {}
    
    public void pushPromise(int paramInt1, int paramInt2, List<Header> paramList)
    {
      pushRequestLater(paramInt2, paramList);
    }
    
    public void rstStream(int paramInt, ErrorCode paramErrorCode)
    {
      if (pushedStream(paramInt))
      {
        pushResetLater(paramInt, paramErrorCode);
        return;
      }
      Http2Stream localHttp2Stream = removeStream(paramInt);
      if (localHttp2Stream != null) {
        localHttp2Stream.receiveRstStream(paramErrorCode);
      }
    }
    
    public void settings(boolean paramBoolean, Settings paramSettings)
    {
      for (;;)
      {
        synchronized (Http2Connection.this)
        {
          int i = peerSettings.getInitialWindowSize();
          if (paramBoolean) {
            peerSettings.clear();
          }
          peerSettings.merge(paramSettings);
          applyAndAckSettings(paramSettings);
          int j = peerSettings.getInitialWindowSize();
          paramSettings = null;
          if ((j != -1) && (j != i))
          {
            long l2 = j - i;
            if (!receivedInitialPeerSettings)
            {
              addBytesToWriteWindow(l2);
              receivedInitialPeerSettings = true;
            }
            l1 = l2;
            if (!streams.isEmpty())
            {
              paramSettings = (Http2Stream[])streams.values().toArray(new Http2Stream[streams.size()]);
              l1 = l2;
            }
            ExecutorService localExecutorService = Http2Connection.executor;
            String str = hostname;
            i = 0;
            localExecutorService.execute(new NamedRunnable("OkHttp %s settings", new Object[] { str })
            {
              public void execute()
              {
                listener.onSettings(Http2Connection.this);
              }
            });
            if ((paramSettings != null) && (l1 != 0L))
            {
              j = paramSettings.length;
              if (i < j) {
                synchronized (paramSettings[i])
                {
                  ???.addBytesToWriteWindow(l1);
                  i += 1;
                }
              }
            }
            return;
          }
        }
        long l1 = 0L;
      }
    }
    
    public void windowUpdate(int paramInt, long paramLong)
    {
      if (paramInt == 0) {
        synchronized (Http2Connection.this)
        {
          Http2Connection localHttp2Connection = Http2Connection.this;
          bytesLeftInWriteWindow += paramLong;
          notifyAll();
          return;
        }
      }
      ??? = getStream(paramInt);
      if (??? != null) {
        try
        {
          ((Http2Stream)???).addBytesToWriteWindow(paramLong);
          return;
        }
        finally {}
      }
    }
  }
}
