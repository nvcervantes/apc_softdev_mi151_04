package okhttp3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import okhttp3.internal.NamedRunnable;
import okhttp3.internal.cache.CacheInterceptor;
import okhttp3.internal.connection.ConnectInterceptor;
import okhttp3.internal.connection.StreamAllocation;
import okhttp3.internal.http.BridgeInterceptor;
import okhttp3.internal.http.CallServerInterceptor;
import okhttp3.internal.http.RealInterceptorChain;
import okhttp3.internal.http.RetryAndFollowUpInterceptor;
import okhttp3.internal.platform.Platform;

final class RealCall
  implements Call
{
  final OkHttpClient client;
  private EventListener eventListener;
  private boolean executed;
  final boolean forWebSocket;
  final Request originalRequest;
  final RetryAndFollowUpInterceptor retryAndFollowUpInterceptor;
  
  private RealCall(OkHttpClient paramOkHttpClient, Request paramRequest, boolean paramBoolean)
  {
    client = paramOkHttpClient;
    originalRequest = paramRequest;
    forWebSocket = paramBoolean;
    retryAndFollowUpInterceptor = new RetryAndFollowUpInterceptor(paramOkHttpClient, paramBoolean);
  }
  
  private void captureCallStackTrace()
  {
    Object localObject = Platform.get().getStackTraceForCloseable("response.body().close()");
    retryAndFollowUpInterceptor.setCallStackTrace(localObject);
  }
  
  static RealCall newRealCall(OkHttpClient paramOkHttpClient, Request paramRequest, boolean paramBoolean)
  {
    paramRequest = new RealCall(paramOkHttpClient, paramRequest, paramBoolean);
    eventListener = paramOkHttpClient.eventListenerFactory().create(paramRequest);
    return paramRequest;
  }
  
  public void cancel()
  {
    retryAndFollowUpInterceptor.cancel();
  }
  
  public RealCall clone()
  {
    return newRealCall(client, originalRequest, forWebSocket);
  }
  
  public void enqueue(Callback paramCallback)
  {
    try
    {
      if (executed) {
        throw new IllegalStateException("Already Executed");
      }
      executed = true;
      captureCallStackTrace();
      eventListener.callStart(this);
      client.dispatcher().enqueue(new AsyncCall(paramCallback));
      return;
    }
    finally {}
  }
  
  /* Error */
  public Response execute()
    throws IOException
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 94	okhttp3/RealCall:executed	Z
    //   6: ifeq +13 -> 19
    //   9: new 96	java/lang/IllegalStateException
    //   12: dup
    //   13: ldc 98
    //   15: invokespecial 101	java/lang/IllegalStateException:<init>	(Ljava/lang/String;)V
    //   18: athrow
    //   19: aload_0
    //   20: iconst_1
    //   21: putfield 94	okhttp3/RealCall:executed	Z
    //   24: aload_0
    //   25: monitorexit
    //   26: aload_0
    //   27: invokespecial 103	okhttp3/RealCall:captureCallStackTrace	()V
    //   30: aload_0
    //   31: getfield 43	okhttp3/RealCall:eventListener	Lokhttp3/EventListener;
    //   34: aload_0
    //   35: invokevirtual 109	okhttp3/EventListener:callStart	(Lokhttp3/Call;)V
    //   38: aload_0
    //   39: getfield 27	okhttp3/RealCall:client	Lokhttp3/OkHttpClient;
    //   42: invokevirtual 113	okhttp3/OkHttpClient:dispatcher	()Lokhttp3/Dispatcher;
    //   45: aload_0
    //   46: invokevirtual 128	okhttp3/Dispatcher:executed	(Lokhttp3/RealCall;)V
    //   49: aload_0
    //   50: invokevirtual 131	okhttp3/RealCall:getResponseWithInterceptorChain	()Lokhttp3/Response;
    //   53: astore_1
    //   54: aload_1
    //   55: ifnonnull +13 -> 68
    //   58: new 125	java/io/IOException
    //   61: dup
    //   62: ldc -123
    //   64: invokespecial 134	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   67: athrow
    //   68: aload_0
    //   69: getfield 27	okhttp3/RealCall:client	Lokhttp3/OkHttpClient;
    //   72: invokevirtual 113	okhttp3/OkHttpClient:dispatcher	()Lokhttp3/Dispatcher;
    //   75: aload_0
    //   76: invokevirtual 137	okhttp3/Dispatcher:finished	(Lokhttp3/RealCall;)V
    //   79: aload_1
    //   80: areturn
    //   81: astore_1
    //   82: goto +15 -> 97
    //   85: astore_1
    //   86: aload_0
    //   87: getfield 43	okhttp3/RealCall:eventListener	Lokhttp3/EventListener;
    //   90: aload_0
    //   91: aload_1
    //   92: invokevirtual 141	okhttp3/EventListener:callFailed	(Lokhttp3/Call;Ljava/io/IOException;)V
    //   95: aload_1
    //   96: athrow
    //   97: aload_0
    //   98: getfield 27	okhttp3/RealCall:client	Lokhttp3/OkHttpClient;
    //   101: invokevirtual 113	okhttp3/OkHttpClient:dispatcher	()Lokhttp3/Dispatcher;
    //   104: aload_0
    //   105: invokevirtual 137	okhttp3/Dispatcher:finished	(Lokhttp3/RealCall;)V
    //   108: aload_1
    //   109: athrow
    //   110: astore_1
    //   111: aload_0
    //   112: monitorexit
    //   113: aload_1
    //   114: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	115	0	this	RealCall
    //   53	27	1	localResponse	Response
    //   81	1	1	localObject1	Object
    //   85	24	1	localIOException	IOException
    //   110	4	1	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   38	54	81	finally
    //   58	68	81	finally
    //   86	97	81	finally
    //   38	54	85	java/io/IOException
    //   58	68	85	java/io/IOException
    //   2	19	110	finally
    //   19	26	110	finally
    //   111	113	110	finally
  }
  
  Response getResponseWithInterceptorChain()
    throws IOException
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.addAll(client.interceptors());
    localArrayList.add(retryAndFollowUpInterceptor);
    localArrayList.add(new BridgeInterceptor(client.cookieJar()));
    localArrayList.add(new CacheInterceptor(client.internalCache()));
    localArrayList.add(new ConnectInterceptor(client));
    if (!forWebSocket) {
      localArrayList.addAll(client.networkInterceptors());
    }
    localArrayList.add(new CallServerInterceptor(forWebSocket));
    return new RealInterceptorChain(localArrayList, null, null, null, 0, originalRequest, this, eventListener, client.connectTimeoutMillis(), client.readTimeoutMillis(), client.writeTimeoutMillis()).proceed(originalRequest);
  }
  
  public boolean isCanceled()
  {
    return retryAndFollowUpInterceptor.isCanceled();
  }
  
  public boolean isExecuted()
  {
    try
    {
      boolean bool = executed;
      return bool;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  String redactedUrl()
  {
    return originalRequest.url().redact();
  }
  
  public Request request()
  {
    return originalRequest;
  }
  
  StreamAllocation streamAllocation()
  {
    return retryAndFollowUpInterceptor.streamAllocation();
  }
  
  String toLoggableString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    String str;
    if (isCanceled()) {
      str = "canceled ";
    } else {
      str = "";
    }
    localStringBuilder.append(str);
    if (forWebSocket) {
      str = "web socket";
    } else {
      str = "call";
    }
    localStringBuilder.append(str);
    localStringBuilder.append(" to ");
    localStringBuilder.append(redactedUrl());
    return localStringBuilder.toString();
  }
  
  final class AsyncCall
    extends NamedRunnable
  {
    private final Callback responseCallback;
    
    AsyncCall(Callback paramCallback)
    {
      super(new Object[] { redactedUrl() });
      responseCallback = paramCallback;
    }
    
    /* Error */
    protected void execute()
    {
      // Byte code:
      //   0: iconst_1
      //   1: istore_1
      //   2: aload_0
      //   3: getfield 15	okhttp3/RealCall$AsyncCall:this$0	Lokhttp3/RealCall;
      //   6: invokevirtual 37	okhttp3/RealCall:getResponseWithInterceptorChain	()Lokhttp3/Response;
      //   9: astore_3
      //   10: aload_0
      //   11: getfield 15	okhttp3/RealCall$AsyncCall:this$0	Lokhttp3/RealCall;
      //   14: getfield 41	okhttp3/RealCall:retryAndFollowUpInterceptor	Lokhttp3/internal/http/RetryAndFollowUpInterceptor;
      //   17: invokevirtual 47	okhttp3/internal/http/RetryAndFollowUpInterceptor:isCanceled	()Z
      //   20: istore_2
      //   21: iload_2
      //   22: ifeq +28 -> 50
      //   25: aload_0
      //   26: getfield 28	okhttp3/RealCall$AsyncCall:responseCallback	Lokhttp3/Callback;
      //   29: aload_0
      //   30: getfield 15	okhttp3/RealCall$AsyncCall:this$0	Lokhttp3/RealCall;
      //   33: new 33	java/io/IOException
      //   36: dup
      //   37: ldc 49
      //   39: invokespecial 52	java/io/IOException:<init>	(Ljava/lang/String;)V
      //   42: invokeinterface 58 3 0
      //   47: goto +17 -> 64
      //   50: aload_0
      //   51: getfield 28	okhttp3/RealCall$AsyncCall:responseCallback	Lokhttp3/Callback;
      //   54: aload_0
      //   55: getfield 15	okhttp3/RealCall$AsyncCall:this$0	Lokhttp3/RealCall;
      //   58: aload_3
      //   59: invokeinterface 62 3 0
      //   64: aload_0
      //   65: getfield 15	okhttp3/RealCall$AsyncCall:this$0	Lokhttp3/RealCall;
      //   68: getfield 66	okhttp3/RealCall:client	Lokhttp3/OkHttpClient;
      //   71: invokevirtual 72	okhttp3/OkHttpClient:dispatcher	()Lokhttp3/Dispatcher;
      //   74: aload_0
      //   75: invokevirtual 78	okhttp3/Dispatcher:finished	(Lokhttp3/RealCall$AsyncCall;)V
      //   78: return
      //   79: astore_3
      //   80: goto +92 -> 172
      //   83: astore_3
      //   84: iconst_0
      //   85: istore_1
      //   86: iload_1
      //   87: ifeq +53 -> 140
      //   90: invokestatic 84	okhttp3/internal/platform/Platform:get	()Lokhttp3/internal/platform/Platform;
      //   93: astore 4
      //   95: new 86	java/lang/StringBuilder
      //   98: dup
      //   99: invokespecial 88	java/lang/StringBuilder:<init>	()V
      //   102: astore 5
      //   104: aload 5
      //   106: ldc 90
      //   108: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   111: pop
      //   112: aload 5
      //   114: aload_0
      //   115: getfield 15	okhttp3/RealCall$AsyncCall:this$0	Lokhttp3/RealCall;
      //   118: invokevirtual 97	okhttp3/RealCall:toLoggableString	()Ljava/lang/String;
      //   121: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   124: pop
      //   125: aload 4
      //   127: iconst_4
      //   128: aload 5
      //   130: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   133: aload_3
      //   134: invokevirtual 104	okhttp3/internal/platform/Platform:log	(ILjava/lang/String;Ljava/lang/Throwable;)V
      //   137: goto -73 -> 64
      //   140: aload_0
      //   141: getfield 15	okhttp3/RealCall$AsyncCall:this$0	Lokhttp3/RealCall;
      //   144: invokestatic 108	okhttp3/RealCall:access$000	(Lokhttp3/RealCall;)Lokhttp3/EventListener;
      //   147: aload_0
      //   148: getfield 15	okhttp3/RealCall$AsyncCall:this$0	Lokhttp3/RealCall;
      //   151: aload_3
      //   152: invokevirtual 113	okhttp3/EventListener:callFailed	(Lokhttp3/Call;Ljava/io/IOException;)V
      //   155: aload_0
      //   156: getfield 28	okhttp3/RealCall$AsyncCall:responseCallback	Lokhttp3/Callback;
      //   159: aload_0
      //   160: getfield 15	okhttp3/RealCall$AsyncCall:this$0	Lokhttp3/RealCall;
      //   163: aload_3
      //   164: invokeinterface 58 3 0
      //   169: goto -105 -> 64
      //   172: aload_0
      //   173: getfield 15	okhttp3/RealCall$AsyncCall:this$0	Lokhttp3/RealCall;
      //   176: getfield 66	okhttp3/RealCall:client	Lokhttp3/OkHttpClient;
      //   179: invokevirtual 72	okhttp3/OkHttpClient:dispatcher	()Lokhttp3/Dispatcher;
      //   182: aload_0
      //   183: invokevirtual 78	okhttp3/Dispatcher:finished	(Lokhttp3/RealCall$AsyncCall;)V
      //   186: aload_3
      //   187: athrow
      //   188: astore_3
      //   189: goto -103 -> 86
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	192	0	this	AsyncCall
      //   1	86	1	i	int
      //   20	2	2	bool	boolean
      //   9	50	3	localResponse	Response
      //   79	1	3	localObject	Object
      //   83	104	3	localIOException1	IOException
      //   188	1	3	localIOException2	IOException
      //   93	33	4	localPlatform	Platform
      //   102	27	5	localStringBuilder	StringBuilder
      // Exception table:
      //   from	to	target	type
      //   2	21	79	finally
      //   25	47	79	finally
      //   50	64	79	finally
      //   90	137	79	finally
      //   140	169	79	finally
      //   2	21	83	java/io/IOException
      //   25	47	188	java/io/IOException
      //   50	64	188	java/io/IOException
    }
    
    RealCall get()
    {
      return RealCall.this;
    }
    
    String host()
    {
      return originalRequest.url().host();
    }
    
    Request request()
    {
      return originalRequest;
    }
  }
}
