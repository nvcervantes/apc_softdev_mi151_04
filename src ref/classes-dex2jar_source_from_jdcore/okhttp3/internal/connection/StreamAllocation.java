package okhttp3.internal.connection;

import java.io.IOException;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.net.Socket;
import java.util.List;
import okhttp3.Address;
import okhttp3.Call;
import okhttp3.Connection;
import okhttp3.ConnectionPool;
import okhttp3.EventListener;
import okhttp3.Route;
import okhttp3.internal.Internal;
import okhttp3.internal.Util;
import okhttp3.internal.http.HttpCodec;
import okhttp3.internal.http2.ConnectionShutdownException;
import okhttp3.internal.http2.ErrorCode;
import okhttp3.internal.http2.StreamResetException;

public final class StreamAllocation
{
  public final Address address;
  public final Call call;
  private final Object callStackTrace;
  private boolean canceled;
  private HttpCodec codec;
  private RealConnection connection;
  private final ConnectionPool connectionPool;
  public final EventListener eventListener;
  private int refusedStreamCount;
  private boolean released;
  private boolean reportedAcquired;
  private Route route;
  private RouteSelector.Selection routeSelection;
  private final RouteSelector routeSelector;
  
  public StreamAllocation(ConnectionPool paramConnectionPool, Address paramAddress, Call paramCall, EventListener paramEventListener, Object paramObject)
  {
    connectionPool = paramConnectionPool;
    address = paramAddress;
    call = paramCall;
    eventListener = paramEventListener;
    routeSelector = new RouteSelector(paramAddress, routeDatabase(), paramCall, paramEventListener);
    callStackTrace = paramObject;
  }
  
  private Socket deallocate(boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
  {
    if (paramBoolean3) {
      codec = null;
    }
    if (paramBoolean2) {
      released = true;
    }
    if (connection != null)
    {
      if (paramBoolean1) {
        connection.noNewStreams = true;
      }
      if ((codec == null) && ((released) || (connection.noNewStreams)))
      {
        release(connection);
        if (connection.allocations.isEmpty())
        {
          connection.idleAtNanos = System.nanoTime();
          if (Internal.instance.connectionBecameIdle(connectionPool, connection))
          {
            localSocket = connection.socket();
            break label126;
          }
        }
        Socket localSocket = null;
        label126:
        connection = null;
        return localSocket;
      }
    }
    return null;
  }
  
  private RealConnection findConnection(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean)
    throws IOException
  {
    for (;;)
    {
      int k;
      synchronized (connectionPool)
      {
        if (released) {
          throw new IllegalStateException("released");
        }
        if (codec != null) {
          throw new IllegalStateException("codec != null");
        }
        if (canceled) {
          throw new IOException("Canceled");
        }
        localObject6 = connection;
        Object localObject11 = releaseIfNoNewStreams();
        Object localObject1 = connection;
        Object localObject9 = null;
        if (localObject1 != null)
        {
          localObject1 = connection;
          localObject6 = null;
          ??? = localObject6;
          if (!reportedAcquired) {
            ??? = null;
          }
          if (localObject1 != null) {
            break label624;
          }
          Internal.instance.get(connectionPool, address, this, null);
          if (connection != null)
          {
            localObject6 = connection;
            localObject1 = null;
            i = 1;
          }
          else
          {
            localObject7 = route;
            break label627;
          }
          Util.closeQuietly((Socket)localObject11);
          if (??? != null) {
            eventListener.connectionReleased(call, (Connection)???);
          }
          if (i != 0) {
            eventListener.connectionAcquired(call, (Connection)localObject6);
          }
          if (localObject6 != null) {
            return localObject6;
          }
          if ((localObject1 == null) && ((routeSelection == null) || (!routeSelection.hasNext())))
          {
            routeSelection = routeSelector.next();
            k = 1;
          }
          else
          {
            k = 0;
          }
          synchronized (connectionPool)
          {
            if (canceled) {
              throw new IOException("Canceled");
            }
            int j = i;
            localObject7 = localObject6;
            if (k != 0)
            {
              ??? = routeSelection.getAll();
              int m = ((List)???).size();
              k = 0;
              j = i;
              localObject7 = localObject6;
              if (k < m)
              {
                localObject11 = (Route)((List)???).get(k);
                Internal.instance.get(connectionPool, address, this, (Route)localObject11);
                if (connection == null) {
                  break label641;
                }
                localObject7 = connection;
                route = ((Route)localObject11);
                j = 1;
              }
            }
            localObject6 = localObject7;
            if (j == 0)
            {
              localObject6 = localObject1;
              if (localObject1 == null) {
                localObject6 = routeSelection.next();
              }
              route = ((Route)localObject6);
              refusedStreamCount = 0;
              localObject6 = new RealConnection(connectionPool, (Route)localObject6);
              acquire((RealConnection)localObject6, false);
            }
            if (j != 0)
            {
              eventListener.connectionAcquired(call, (Connection)localObject6);
              return localObject6;
            }
            ((RealConnection)localObject6).connect(paramInt1, paramInt2, paramInt3, paramBoolean, call, eventListener);
            routeDatabase().connected(((RealConnection)localObject6).route());
            synchronized (connectionPool)
            {
              reportedAcquired = true;
              Internal.instance.put(connectionPool, (RealConnection)localObject6);
              localObject7 = localObject9;
              localObject1 = localObject6;
              if (((RealConnection)localObject6).isMultiplexed())
              {
                localObject7 = Internal.instance.deduplicate(connectionPool, address, this);
                localObject1 = connection;
              }
              Util.closeQuietly((Socket)localObject7);
              eventListener.connectionAcquired(call, (Connection)localObject1);
              return localObject1;
            }
          }
        }
      }
      Object localObject5 = null;
      continue;
      label624:
      Object localObject7 = null;
      label627:
      int i = 0;
      Object localObject6 = localObject5;
      localObject5 = localObject7;
      continue;
      label641:
      k += 1;
    }
  }
  
  private RealConnection findHealthyConnection(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean1, boolean paramBoolean2)
    throws IOException
  {
    for (;;)
    {
      RealConnection localRealConnection = findConnection(paramInt1, paramInt2, paramInt3, paramBoolean1);
      synchronized (connectionPool)
      {
        if (successCount == 0) {
          return localRealConnection;
        }
        if (!localRealConnection.isHealthy(paramBoolean2))
        {
          noNewStreams();
          continue;
        }
        return localRealConnection;
      }
    }
  }
  
  private void release(RealConnection paramRealConnection)
  {
    int j = allocations.size();
    int i = 0;
    while (i < j)
    {
      if (((Reference)allocations.get(i)).get() == this)
      {
        allocations.remove(i);
        return;
      }
      i += 1;
    }
    throw new IllegalStateException();
  }
  
  private Socket releaseIfNoNewStreams()
  {
    RealConnection localRealConnection = connection;
    if ((localRealConnection != null) && (noNewStreams)) {
      return deallocate(false, false, true);
    }
    return null;
  }
  
  private RouteDatabase routeDatabase()
  {
    return Internal.instance.routeDatabase(connectionPool);
  }
  
  public void acquire(RealConnection paramRealConnection, boolean paramBoolean)
  {
    if (connection != null) {
      throw new IllegalStateException();
    }
    connection = paramRealConnection;
    reportedAcquired = paramBoolean;
    allocations.add(new StreamAllocationReference(this, callStackTrace));
  }
  
  public void cancel()
  {
    synchronized (connectionPool)
    {
      canceled = true;
      HttpCodec localHttpCodec = codec;
      RealConnection localRealConnection = connection;
      if (localHttpCodec != null)
      {
        localHttpCodec.cancel();
        return;
      }
      if (localRealConnection != null) {
        localRealConnection.cancel();
      }
      return;
    }
  }
  
  public HttpCodec codec()
  {
    synchronized (connectionPool)
    {
      HttpCodec localHttpCodec = codec;
      return localHttpCodec;
    }
  }
  
  public RealConnection connection()
  {
    try
    {
      RealConnection localRealConnection = connection;
      return localRealConnection;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  public boolean hasMoreRoutes()
  {
    return (route != null) || ((routeSelection != null) && (routeSelection.hasNext())) || (routeSelector.hasNext());
  }
  
  /* Error */
  public HttpCodec newStream(okhttp3.OkHttpClient arg1, okhttp3.Interceptor.Chain paramChain, boolean paramBoolean)
  {
    // Byte code:
    //   0: aload_2
    //   1: invokeinterface 268 1 0
    //   6: istore 4
    //   8: aload_2
    //   9: invokeinterface 271 1 0
    //   14: istore 5
    //   16: aload_2
    //   17: invokeinterface 274 1 0
    //   22: istore 6
    //   24: aload_1
    //   25: invokevirtual 279	okhttp3/OkHttpClient:retryOnConnectionFailure	()Z
    //   28: istore 7
    //   30: aload_0
    //   31: iload 4
    //   33: iload 5
    //   35: iload 6
    //   37: iload 7
    //   39: iload_3
    //   40: invokespecial 281	okhttp3/internal/connection/StreamAllocation:findHealthyConnection	(IIIZZ)Lokhttp3/internal/connection/RealConnection;
    //   43: aload_1
    //   44: aload_2
    //   45: aload_0
    //   46: invokevirtual 285	okhttp3/internal/connection/RealConnection:newCodec	(Lokhttp3/OkHttpClient;Lokhttp3/Interceptor$Chain;Lokhttp3/internal/connection/StreamAllocation;)Lokhttp3/internal/http/HttpCodec;
    //   49: astore_2
    //   50: aload_0
    //   51: getfield 44	okhttp3/internal/connection/StreamAllocation:connectionPool	Lokhttp3/ConnectionPool;
    //   54: astore_1
    //   55: aload_1
    //   56: monitorenter
    //   57: aload_0
    //   58: aload_2
    //   59: putfield 67	okhttp3/internal/connection/StreamAllocation:codec	Lokhttp3/internal/http/HttpCodec;
    //   62: aload_1
    //   63: monitorexit
    //   64: aload_2
    //   65: areturn
    //   66: astore_2
    //   67: aload_1
    //   68: monitorexit
    //   69: aload_2
    //   70: athrow
    //   71: astore_1
    //   72: new 287	okhttp3/internal/connection/RouteException
    //   75: dup
    //   76: aload_1
    //   77: invokespecial 290	okhttp3/internal/connection/RouteException:<init>	(Ljava/io/IOException;)V
    //   80: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	81	0	this	StreamAllocation
    //   0	81	2	paramChain	okhttp3.Interceptor.Chain
    //   0	81	3	paramBoolean	boolean
    //   6	26	4	i	int
    //   14	20	5	j	int
    //   22	14	6	k	int
    //   28	10	7	bool	boolean
    // Exception table:
    //   from	to	target	type
    //   57	64	66	finally
    //   67	69	66	finally
    //   30	57	71	java/io/IOException
    //   69	71	71	java/io/IOException
  }
  
  public void noNewStreams()
  {
    synchronized (connectionPool)
    {
      RealConnection localRealConnection = connection;
      Socket localSocket = deallocate(true, false, false);
      if (connection != null) {
        localRealConnection = null;
      }
      Util.closeQuietly(localSocket);
      if (localRealConnection != null) {
        eventListener.connectionReleased(call, localRealConnection);
      }
      return;
    }
  }
  
  public void release()
  {
    synchronized (connectionPool)
    {
      RealConnection localRealConnection = connection;
      Socket localSocket = deallocate(false, true, false);
      if (connection != null) {
        localRealConnection = null;
      }
      Util.closeQuietly(localSocket);
      if (localRealConnection != null) {
        eventListener.connectionReleased(call, localRealConnection);
      }
      return;
    }
  }
  
  public Socket releaseAndAcquire(RealConnection paramRealConnection)
  {
    if ((codec == null) && (connection.allocations.size() == 1))
    {
      Reference localReference = (Reference)connection.allocations.get(0);
      Socket localSocket = deallocate(true, false, false);
      connection = paramRealConnection;
      allocations.add(localReference);
      return localSocket;
    }
    throw new IllegalStateException();
  }
  
  public void streamFailed(IOException paramIOException)
  {
    for (;;)
    {
      synchronized (connectionPool)
      {
        if ((paramIOException instanceof StreamResetException))
        {
          paramIOException = (StreamResetException)paramIOException;
          if (errorCode == ErrorCode.REFUSED_STREAM) {
            refusedStreamCount += 1;
          }
          if ((errorCode == ErrorCode.REFUSED_STREAM) && (refusedStreamCount <= 1)) {
            break label195;
          }
          route = null;
        }
        else
        {
          if ((connection == null) || ((connection.isMultiplexed()) && (!(paramIOException instanceof ConnectionShutdownException)))) {
            break label195;
          }
          if (connection.successCount == 0)
          {
            if ((route != null) && (paramIOException != null)) {
              routeSelector.connectFailed(route, paramIOException);
            }
            route = null;
            break label190;
            paramIOException = connection;
            Socket localSocket = deallocate(bool, false, true);
            if ((connection != null) || (!reportedAcquired)) {
              break label200;
            }
            Util.closeQuietly(localSocket);
            if (paramIOException != null) {
              eventListener.connectionReleased(call, paramIOException);
            }
            return;
          }
        }
      }
      label190:
      boolean bool = true;
      continue;
      label195:
      bool = false;
      continue;
      label200:
      paramIOException = null;
    }
  }
  
  public void streamFinished(boolean paramBoolean, HttpCodec paramHttpCodec, long paramLong, IOException paramIOException)
  {
    eventListener.responseBodyEnd(call, paramLong);
    ConnectionPool localConnectionPool = connectionPool;
    if (paramHttpCodec != null) {
      try
      {
        if (paramHttpCodec == codec)
        {
          if (!paramBoolean)
          {
            paramHttpCodec = connection;
            successCount += 1;
          }
          paramHttpCodec = connection;
          Socket localSocket = deallocate(paramBoolean, false, true);
          if (connection != null) {
            paramHttpCodec = null;
          }
          paramBoolean = released;
          Util.closeQuietly(localSocket);
          if (paramHttpCodec != null) {
            eventListener.connectionReleased(call, paramHttpCodec);
          }
          if (paramIOException != null)
          {
            eventListener.callFailed(call, paramIOException);
            return;
          }
          if (paramBoolean) {
            eventListener.callEnd(call);
          }
          return;
        }
      }
      finally
      {
        break label203;
      }
    }
    paramIOException = new StringBuilder();
    paramIOException.append("expected ");
    paramIOException.append(codec);
    paramIOException.append(" but was ");
    paramIOException.append(paramHttpCodec);
    throw new IllegalStateException(paramIOException.toString());
    label203:
    throw paramHttpCodec;
  }
  
  public String toString()
  {
    RealConnection localRealConnection = connection();
    if (localRealConnection != null) {
      return localRealConnection.toString();
    }
    return address.toString();
  }
  
  public static final class StreamAllocationReference
    extends WeakReference<StreamAllocation>
  {
    public final Object callStackTrace;
    
    StreamAllocationReference(StreamAllocation paramStreamAllocation, Object paramObject)
    {
      super();
      callStackTrace = paramObject;
    }
  }
}
