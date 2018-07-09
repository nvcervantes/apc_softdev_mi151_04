package okhttp3.internal.ws;

import java.io.Closeable;
import java.io.IOException;
import java.net.ProtocolException;
import java.net.Socket;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.EventListener;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okhttp3.internal.Internal;
import okhttp3.internal.Util;
import okhttp3.internal.connection.RealConnection;
import okhttp3.internal.connection.StreamAllocation;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ByteString;
import okio.Okio;

public final class RealWebSocket
  implements WebSocket, WebSocketReader.FrameCallback
{
  private static final long CANCEL_AFTER_CLOSE_MILLIS = 60000L;
  private static final long MAX_QUEUE_SIZE = 16777216L;
  private static final List<Protocol> ONLY_HTTP1 = Collections.singletonList(Protocol.HTTP_1_1);
  private Call call;
  private ScheduledFuture<?> cancelFuture;
  private boolean enqueuedClose;
  private ScheduledExecutorService executor;
  private boolean failed;
  private final String key;
  final WebSocketListener listener;
  private final ArrayDeque<Object> messageAndCloseQueue = new ArrayDeque();
  private final Request originalRequest;
  int pingCount;
  int pongCount;
  private final ArrayDeque<ByteString> pongQueue = new ArrayDeque();
  private long queueSize;
  private final Random random;
  private WebSocketReader reader;
  private int receivedCloseCode = -1;
  private String receivedCloseReason;
  private Streams streams;
  private WebSocketWriter writer;
  private final Runnable writerRunnable;
  
  public RealWebSocket(Request paramRequest, WebSocketListener paramWebSocketListener, Random paramRandom)
  {
    if (!"GET".equals(paramRequest.method()))
    {
      paramWebSocketListener = new StringBuilder();
      paramWebSocketListener.append("Request must be GET: ");
      paramWebSocketListener.append(paramRequest.method());
      throw new IllegalArgumentException(paramWebSocketListener.toString());
    }
    originalRequest = paramRequest;
    listener = paramWebSocketListener;
    random = paramRandom;
    paramRequest = new byte[16];
    paramRandom.nextBytes(paramRequest);
    key = ByteString.of(paramRequest).base64();
    writerRunnable = new Runnable()
    {
      public void run()
      {
        try
        {
          boolean bool;
          do
          {
            bool = writeOneFrame();
          } while (bool);
          return;
        }
        catch (IOException localIOException)
        {
          failWebSocket(localIOException, null);
        }
      }
    };
  }
  
  private void runWriter()
  {
    if (executor != null) {
      executor.execute(writerRunnable);
    }
  }
  
  private boolean send(ByteString paramByteString, int paramInt)
  {
    try
    {
      if ((!failed) && (!enqueuedClose))
      {
        if (queueSize + paramByteString.size() > 16777216L)
        {
          close(1001, null);
          return false;
        }
        queueSize += paramByteString.size();
        messageAndCloseQueue.add(new Message(paramInt, paramByteString));
        runWriter();
        return true;
      }
      return false;
    }
    finally {}
  }
  
  void awaitTermination(int paramInt, TimeUnit paramTimeUnit)
    throws InterruptedException
  {
    executor.awaitTermination(paramInt, paramTimeUnit);
  }
  
  public void cancel()
  {
    call.cancel();
  }
  
  void checkResponse(Response paramResponse)
    throws ProtocolException
  {
    if (paramResponse.code() != 101)
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("Expected HTTP 101 response but was '");
      ((StringBuilder)localObject).append(paramResponse.code());
      ((StringBuilder)localObject).append(" ");
      ((StringBuilder)localObject).append(paramResponse.message());
      ((StringBuilder)localObject).append("'");
      throw new ProtocolException(((StringBuilder)localObject).toString());
    }
    Object localObject = paramResponse.header("Connection");
    if (!"Upgrade".equalsIgnoreCase((String)localObject))
    {
      paramResponse = new StringBuilder();
      paramResponse.append("Expected 'Connection' header value 'Upgrade' but was '");
      paramResponse.append((String)localObject);
      paramResponse.append("'");
      throw new ProtocolException(paramResponse.toString());
    }
    localObject = paramResponse.header("Upgrade");
    if (!"websocket".equalsIgnoreCase((String)localObject))
    {
      paramResponse = new StringBuilder();
      paramResponse.append("Expected 'Upgrade' header value 'websocket' but was '");
      paramResponse.append((String)localObject);
      paramResponse.append("'");
      throw new ProtocolException(paramResponse.toString());
    }
    paramResponse = paramResponse.header("Sec-WebSocket-Accept");
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append(key);
    ((StringBuilder)localObject).append("258EAFA5-E914-47DA-95CA-C5AB0DC85B11");
    localObject = ByteString.encodeUtf8(((StringBuilder)localObject).toString()).sha1().base64();
    if (!((String)localObject).equals(paramResponse))
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Expected 'Sec-WebSocket-Accept' header value '");
      localStringBuilder.append((String)localObject);
      localStringBuilder.append("' but was '");
      localStringBuilder.append(paramResponse);
      localStringBuilder.append("'");
      throw new ProtocolException(localStringBuilder.toString());
    }
  }
  
  public boolean close(int paramInt, String paramString)
  {
    return close(paramInt, paramString, 60000L);
  }
  
  boolean close(int paramInt, String paramString, long paramLong)
  {
    try
    {
      WebSocketProtocol.validateCloseCode(paramInt);
      Object localObject = null;
      if (paramString != null)
      {
        ByteString localByteString = ByteString.encodeUtf8(paramString);
        localObject = localByteString;
        if (localByteString.size() > 123L)
        {
          localObject = new StringBuilder();
          ((StringBuilder)localObject).append("reason.size() > 123: ");
          ((StringBuilder)localObject).append(paramString);
          throw new IllegalArgumentException(((StringBuilder)localObject).toString());
        }
      }
      if ((!failed) && (!enqueuedClose))
      {
        enqueuedClose = true;
        messageAndCloseQueue.add(new Close(paramInt, (ByteString)localObject, paramLong));
        runWriter();
        return true;
      }
      return false;
    }
    finally {}
  }
  
  public void connect(OkHttpClient paramOkHttpClient)
  {
    paramOkHttpClient = paramOkHttpClient.newBuilder().eventListener(EventListener.NONE).protocols(ONLY_HTTP1).build();
    final int i = paramOkHttpClient.pingIntervalMillis();
    final Request localRequest = originalRequest.newBuilder().header("Upgrade", "websocket").header("Connection", "Upgrade").header("Sec-WebSocket-Key", key).header("Sec-WebSocket-Version", "13").build();
    call = Internal.instance.newWebSocketCall(paramOkHttpClient, localRequest);
    call.enqueue(new Callback()
    {
      public void onFailure(Call paramAnonymousCall, IOException paramAnonymousIOException)
      {
        failWebSocket(paramAnonymousIOException, null);
      }
      
      public void onResponse(Call paramAnonymousCall, Response paramAnonymousResponse)
      {
        try
        {
          checkResponse(paramAnonymousResponse);
          paramAnonymousCall = Internal.instance.streamAllocation(paramAnonymousCall);
          paramAnonymousCall.noNewStreams();
          RealWebSocket.Streams localStreams = paramAnonymousCall.connection().newWebSocketStreams(paramAnonymousCall);
          try
          {
            listener.onOpen(RealWebSocket.this, paramAnonymousResponse);
            paramAnonymousResponse = new StringBuilder();
            paramAnonymousResponse.append("OkHttp WebSocket ");
            paramAnonymousResponse.append(localRequest.url().redact());
            paramAnonymousResponse = paramAnonymousResponse.toString();
            initReaderAndWriter(paramAnonymousResponse, i, localStreams);
            paramAnonymousCall.connection().socket().setSoTimeout(0);
            loopReader();
            return;
          }
          catch (Exception paramAnonymousCall)
          {
            failWebSocket(paramAnonymousCall, null);
            return;
          }
          return;
        }
        catch (ProtocolException paramAnonymousCall)
        {
          failWebSocket(paramAnonymousCall, paramAnonymousResponse);
          Util.closeQuietly(paramAnonymousResponse);
        }
      }
    });
  }
  
  /* Error */
  public void failWebSocket(Exception paramException, @javax.annotation.Nullable Response paramResponse)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 178	okhttp3/internal/ws/RealWebSocket:failed	Z
    //   6: ifeq +6 -> 12
    //   9: aload_0
    //   10: monitorexit
    //   11: return
    //   12: aload_0
    //   13: iconst_1
    //   14: putfield 178	okhttp3/internal/ws/RealWebSocket:failed	Z
    //   17: aload_0
    //   18: getfield 355	okhttp3/internal/ws/RealWebSocket:streams	Lokhttp3/internal/ws/RealWebSocket$Streams;
    //   21: astore_3
    //   22: aload_0
    //   23: aconst_null
    //   24: putfield 355	okhttp3/internal/ws/RealWebSocket:streams	Lokhttp3/internal/ws/RealWebSocket$Streams;
    //   27: aload_0
    //   28: getfield 357	okhttp3/internal/ws/RealWebSocket:cancelFuture	Ljava/util/concurrent/ScheduledFuture;
    //   31: ifnull +14 -> 45
    //   34: aload_0
    //   35: getfield 357	okhttp3/internal/ws/RealWebSocket:cancelFuture	Ljava/util/concurrent/ScheduledFuture;
    //   38: iconst_0
    //   39: invokeinterface 362 2 0
    //   44: pop
    //   45: aload_0
    //   46: getfield 168	okhttp3/internal/ws/RealWebSocket:executor	Ljava/util/concurrent/ScheduledExecutorService;
    //   49: ifnull +12 -> 61
    //   52: aload_0
    //   53: getfield 168	okhttp3/internal/ws/RealWebSocket:executor	Ljava/util/concurrent/ScheduledExecutorService;
    //   56: invokeinterface 365 1 0
    //   61: aload_0
    //   62: monitorexit
    //   63: aload_0
    //   64: getfield 141	okhttp3/internal/ws/RealWebSocket:listener	Lokhttp3/WebSocketListener;
    //   67: aload_0
    //   68: aload_1
    //   69: aload_2
    //   70: invokevirtual 371	okhttp3/WebSocketListener:onFailure	(Lokhttp3/WebSocket;Ljava/lang/Throwable;Lokhttp3/Response;)V
    //   73: aload_3
    //   74: invokestatic 377	okhttp3/internal/Util:closeQuietly	(Ljava/io/Closeable;)V
    //   77: return
    //   78: astore_1
    //   79: aload_3
    //   80: invokestatic 377	okhttp3/internal/Util:closeQuietly	(Ljava/io/Closeable;)V
    //   83: aload_1
    //   84: athrow
    //   85: astore_1
    //   86: aload_0
    //   87: monitorexit
    //   88: aload_1
    //   89: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	90	0	this	RealWebSocket
    //   0	90	1	paramException	Exception
    //   0	90	2	paramResponse	Response
    //   21	59	3	localStreams	Streams
    // Exception table:
    //   from	to	target	type
    //   63	73	78	finally
    //   2	11	85	finally
    //   12	45	85	finally
    //   45	61	85	finally
    //   61	63	85	finally
    //   86	88	85	finally
  }
  
  public void initReaderAndWriter(String paramString, long paramLong, Streams paramStreams)
    throws IOException
  {
    try
    {
      streams = paramStreams;
      writer = new WebSocketWriter(client, sink, random);
      executor = new ScheduledThreadPoolExecutor(1, Util.threadFactory(paramString, false));
      if (paramLong != 0L) {
        executor.scheduleAtFixedRate(new PingRunnable(), paramLong, paramLong, TimeUnit.MILLISECONDS);
      }
      if (!messageAndCloseQueue.isEmpty()) {
        runWriter();
      }
      reader = new WebSocketReader(client, source, this);
      return;
    }
    finally {}
  }
  
  public void loopReader()
    throws IOException
  {
    while (receivedCloseCode == -1) {
      reader.processNextFrame();
    }
  }
  
  public void onReadClose(int paramInt, String paramString)
  {
    if (paramInt == -1) {
      throw new IllegalArgumentException();
    }
    for (;;)
    {
      try
      {
        if (receivedCloseCode != -1) {
          throw new IllegalStateException("already closed");
        }
        receivedCloseCode = paramInt;
        receivedCloseReason = paramString;
        if ((enqueuedClose) && (messageAndCloseQueue.isEmpty()))
        {
          localStreams = streams;
          streams = null;
          if (cancelFuture != null) {
            cancelFuture.cancel(false);
          }
          executor.shutdown();
          try
          {
            listener.onClosing(this, paramInt, paramString);
            if (localStreams != null) {
              listener.onClosed(this, paramInt, paramString);
            }
            return;
          }
          finally
          {
            Util.closeQuietly(localStreams);
          }
        }
        Streams localStreams = null;
      }
      finally {}
    }
  }
  
  public void onReadMessage(String paramString)
    throws IOException
  {
    listener.onMessage(this, paramString);
  }
  
  public void onReadMessage(ByteString paramByteString)
    throws IOException
  {
    listener.onMessage(this, paramByteString);
  }
  
  public void onReadPing(ByteString paramByteString)
  {
    try
    {
      if ((!failed) && ((!enqueuedClose) || (!messageAndCloseQueue.isEmpty())))
      {
        pongQueue.add(paramByteString);
        runWriter();
        pingCount += 1;
        return;
      }
      return;
    }
    finally {}
  }
  
  public void onReadPong(ByteString paramByteString)
  {
    try
    {
      pongCount += 1;
      return;
    }
    finally
    {
      paramByteString = finally;
      throw paramByteString;
    }
  }
  
  int pingCount()
  {
    try
    {
      int i = pingCount;
      return i;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  boolean pong(ByteString paramByteString)
  {
    try
    {
      if ((!failed) && ((!enqueuedClose) || (!messageAndCloseQueue.isEmpty())))
      {
        pongQueue.add(paramByteString);
        runWriter();
        return true;
      }
      return false;
    }
    finally {}
  }
  
  int pongCount()
  {
    try
    {
      int i = pongCount;
      return i;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  boolean processNextFrame()
    throws IOException
  {
    boolean bool = false;
    try
    {
      reader.processNextFrame();
      int i = receivedCloseCode;
      if (i == -1) {
        bool = true;
      }
      return bool;
    }
    catch (Exception localException)
    {
      failWebSocket(localException, null);
    }
    return false;
  }
  
  public long queueSize()
  {
    try
    {
      long l = queueSize;
      return l;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  public Request request()
  {
    return originalRequest;
  }
  
  public boolean send(String paramString)
  {
    if (paramString == null) {
      throw new NullPointerException("text == null");
    }
    return send(ByteString.encodeUtf8(paramString), 1);
  }
  
  public boolean send(ByteString paramByteString)
  {
    if (paramByteString == null) {
      throw new NullPointerException("bytes == null");
    }
    return send(paramByteString, 2);
  }
  
  void tearDown()
    throws InterruptedException
  {
    if (cancelFuture != null) {
      cancelFuture.cancel(false);
    }
    executor.shutdown();
    executor.awaitTermination(10L, TimeUnit.SECONDS);
  }
  
  boolean writeOneFrame()
    throws IOException
  {
    for (;;)
    {
      WebSocketWriter localWebSocketWriter;
      ByteString localByteString;
      Streams localStreams;
      Object localObject1;
      try
      {
        if (failed) {
          return false;
        }
        localWebSocketWriter = writer;
        localByteString = (ByteString)pongQueue.poll();
        localStreams = null;
        if (localByteString == null)
        {
          localObject5 = messageAndCloseQueue.poll();
          if ((localObject5 instanceof Close))
          {
            i = receivedCloseCode;
            localObject1 = receivedCloseReason;
            if (i != -1)
            {
              localStreams = streams;
              streams = null;
              executor.shutdown();
            }
            else
            {
              cancelFuture = executor.schedule(new CancelRunnable(), cancelAfterCloseMillis, TimeUnit.MILLISECONDS);
            }
          }
          else
          {
            if (localObject5 != null) {
              break label304;
            }
            return false;
          }
          if (localByteString == null) {}
        }
      }
      finally {}
      try
      {
        localWebSocketWriter.writePong(localByteString);
        continue;
        if ((localObject5 instanceof Message))
        {
          localObject1 = data;
          localObject5 = Okio.buffer(localWebSocketWriter.newMessageSink(formatOpcode, ((ByteString)localObject1).size()));
          ((BufferedSink)localObject5).write((ByteString)localObject1);
          ((BufferedSink)localObject5).close();
          try
          {
            queueSize -= ((ByteString)localObject1).size();
          }
          finally {}
        }
        else
        {
          if (!(localObject5 instanceof Close)) {
            continue;
          }
          localObject5 = (Close)localObject5;
          localWebSocketWriter.writeClose(code, reason);
          if (localStreams != null) {
            listener.onClosed(this, i, str);
          }
        }
        Util.closeQuietly(localStreams);
        return true;
        throw new AssertionError();
      }
      finally
      {
        Object localObject3;
        continue;
      }
      Util.closeQuietly(localStreams);
      throw str;
      label304:
      int i = -1;
      localObject3 = null;
      continue;
      i = -1;
      Object localObject5 = null;
      localObject3 = localObject5;
    }
  }
  
  /* Error */
  void writePingFrame()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 178	okhttp3/internal/ws/RealWebSocket:failed	Z
    //   6: ifeq +6 -> 12
    //   9: aload_0
    //   10: monitorexit
    //   11: return
    //   12: aload_0
    //   13: getfield 396	okhttp3/internal/ws/RealWebSocket:writer	Lokhttp3/internal/ws/WebSocketWriter;
    //   16: astore_1
    //   17: aload_0
    //   18: monitorexit
    //   19: aload_1
    //   20: getstatic 546	okio/ByteString:EMPTY	Lokio/ByteString;
    //   23: invokevirtual 549	okhttp3/internal/ws/WebSocketWriter:writePing	(Lokio/ByteString;)V
    //   26: return
    //   27: astore_1
    //   28: aload_0
    //   29: aload_1
    //   30: aconst_null
    //   31: invokevirtual 473	okhttp3/internal/ws/RealWebSocket:failWebSocket	(Ljava/lang/Exception;Lokhttp3/Response;)V
    //   34: return
    //   35: astore_1
    //   36: aload_0
    //   37: monitorexit
    //   38: aload_1
    //   39: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	40	0	this	RealWebSocket
    //   16	4	1	localWebSocketWriter	WebSocketWriter
    //   27	3	1	localIOException	IOException
    //   35	4	1	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   19	26	27	java/io/IOException
    //   2	11	35	finally
    //   12	19	35	finally
    //   36	38	35	finally
  }
  
  final class CancelRunnable
    implements Runnable
  {
    CancelRunnable() {}
    
    public void run()
    {
      cancel();
    }
  }
  
  static final class Close
  {
    final long cancelAfterCloseMillis;
    final int code;
    final ByteString reason;
    
    Close(int paramInt, ByteString paramByteString, long paramLong)
    {
      code = paramInt;
      reason = paramByteString;
      cancelAfterCloseMillis = paramLong;
    }
  }
  
  static final class Message
  {
    final ByteString data;
    final int formatOpcode;
    
    Message(int paramInt, ByteString paramByteString)
    {
      formatOpcode = paramInt;
      data = paramByteString;
    }
  }
  
  private final class PingRunnable
    implements Runnable
  {
    PingRunnable() {}
    
    public void run()
    {
      writePingFrame();
    }
  }
  
  public static abstract class Streams
    implements Closeable
  {
    public final boolean client;
    public final BufferedSink sink;
    public final BufferedSource source;
    
    public Streams(boolean paramBoolean, BufferedSource paramBufferedSource, BufferedSink paramBufferedSink)
    {
      client = paramBoolean;
      source = paramBufferedSource;
      sink = paramBufferedSink;
    }
  }
}
