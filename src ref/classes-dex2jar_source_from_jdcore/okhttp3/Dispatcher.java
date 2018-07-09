package okhttp3;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import okhttp3.internal.Util;

public final class Dispatcher
{
  @Nullable
  private ExecutorService executorService;
  @Nullable
  private Runnable idleCallback;
  private int maxRequests = 64;
  private int maxRequestsPerHost = 5;
  private final Deque<RealCall.AsyncCall> readyAsyncCalls = new ArrayDeque();
  private final Deque<RealCall.AsyncCall> runningAsyncCalls = new ArrayDeque();
  private final Deque<RealCall> runningSyncCalls = new ArrayDeque();
  
  public Dispatcher() {}
  
  public Dispatcher(ExecutorService paramExecutorService)
  {
    executorService = paramExecutorService;
  }
  
  private <T> void finished(Deque<T> paramDeque, T paramT, boolean paramBoolean)
  {
    try
    {
      if (!paramDeque.remove(paramT)) {
        throw new AssertionError("Call wasn't in-flight!");
      }
      if (paramBoolean) {
        promoteCalls();
      }
      int i = runningCallsCount();
      paramDeque = idleCallback;
      if ((i == 0) && (paramDeque != null)) {
        paramDeque.run();
      }
      return;
    }
    finally {}
  }
  
  private void promoteCalls()
  {
    if (runningAsyncCalls.size() >= maxRequests) {
      return;
    }
    if (readyAsyncCalls.isEmpty()) {
      return;
    }
    Iterator localIterator = readyAsyncCalls.iterator();
    while (localIterator.hasNext())
    {
      RealCall.AsyncCall localAsyncCall = (RealCall.AsyncCall)localIterator.next();
      if (runningCallsForHost(localAsyncCall) < maxRequestsPerHost)
      {
        localIterator.remove();
        runningAsyncCalls.add(localAsyncCall);
        executorService().execute(localAsyncCall);
      }
      if (runningAsyncCalls.size() >= maxRequests) {}
    }
  }
  
  private int runningCallsForHost(RealCall.AsyncCall paramAsyncCall)
  {
    Iterator localIterator = runningAsyncCalls.iterator();
    int i = 0;
    while (localIterator.hasNext()) {
      if (((RealCall.AsyncCall)localIterator.next()).host().equals(paramAsyncCall.host())) {
        i += 1;
      }
    }
    return i;
  }
  
  public void cancelAll()
  {
    try
    {
      Iterator localIterator = readyAsyncCalls.iterator();
      while (localIterator.hasNext()) {
        ((RealCall.AsyncCall)localIterator.next()).get().cancel();
      }
      localIterator = runningAsyncCalls.iterator();
      while (localIterator.hasNext()) {
        ((RealCall.AsyncCall)localIterator.next()).get().cancel();
      }
      localIterator = runningSyncCalls.iterator();
      while (localIterator.hasNext()) {
        ((RealCall)localIterator.next()).cancel();
      }
      return;
    }
    finally {}
  }
  
  void enqueue(RealCall.AsyncCall paramAsyncCall)
  {
    try
    {
      if ((runningAsyncCalls.size() < maxRequests) && (runningCallsForHost(paramAsyncCall) < maxRequestsPerHost))
      {
        runningAsyncCalls.add(paramAsyncCall);
        executorService().execute(paramAsyncCall);
      }
      else
      {
        readyAsyncCalls.add(paramAsyncCall);
      }
      return;
    }
    finally {}
  }
  
  void executed(RealCall paramRealCall)
  {
    try
    {
      runningSyncCalls.add(paramRealCall);
      return;
    }
    finally
    {
      paramRealCall = finally;
      throw paramRealCall;
    }
  }
  
  public ExecutorService executorService()
  {
    try
    {
      if (executorService == null) {
        executorService = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue(), Util.threadFactory("OkHttp Dispatcher", false));
      }
      ExecutorService localExecutorService = executorService;
      return localExecutorService;
    }
    finally {}
  }
  
  void finished(RealCall.AsyncCall paramAsyncCall)
  {
    finished(runningAsyncCalls, paramAsyncCall, true);
  }
  
  void finished(RealCall paramRealCall)
  {
    finished(runningSyncCalls, paramRealCall, false);
  }
  
  public int getMaxRequests()
  {
    try
    {
      int i = maxRequests;
      return i;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  public int getMaxRequestsPerHost()
  {
    try
    {
      int i = maxRequestsPerHost;
      return i;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  public List<Call> queuedCalls()
  {
    try
    {
      Object localObject1 = new ArrayList();
      Iterator localIterator = readyAsyncCalls.iterator();
      while (localIterator.hasNext()) {
        ((List)localObject1).add(((RealCall.AsyncCall)localIterator.next()).get());
      }
      localObject1 = Collections.unmodifiableList((List)localObject1);
      return localObject1;
    }
    finally {}
  }
  
  public int queuedCallsCount()
  {
    try
    {
      int i = readyAsyncCalls.size();
      return i;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  public List<Call> runningCalls()
  {
    try
    {
      Object localObject1 = new ArrayList();
      ((List)localObject1).addAll(runningSyncCalls);
      Iterator localIterator = runningAsyncCalls.iterator();
      while (localIterator.hasNext()) {
        ((List)localObject1).add(((RealCall.AsyncCall)localIterator.next()).get());
      }
      localObject1 = Collections.unmodifiableList((List)localObject1);
      return localObject1;
    }
    finally {}
  }
  
  public int runningCallsCount()
  {
    try
    {
      int i = runningAsyncCalls.size();
      int j = runningSyncCalls.size();
      return i + j;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  public void setIdleCallback(@Nullable Runnable paramRunnable)
  {
    try
    {
      idleCallback = paramRunnable;
      return;
    }
    finally
    {
      paramRunnable = finally;
      throw paramRunnable;
    }
  }
  
  public void setMaxRequests(int paramInt)
  {
    if (paramInt < 1) {}
    try
    {
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("max < 1: ");
      localStringBuilder.append(paramInt);
      throw new IllegalArgumentException(localStringBuilder.toString());
    }
    finally
    {
      StringBuilder localStringBuilder;
      for (;;) {}
    }
    maxRequests = paramInt;
    promoteCalls();
    return;
    throw localStringBuilder;
  }
  
  public void setMaxRequestsPerHost(int paramInt)
  {
    if (paramInt < 1) {}
    try
    {
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("max < 1: ");
      localStringBuilder.append(paramInt);
      throw new IllegalArgumentException(localStringBuilder.toString());
    }
    finally
    {
      StringBuilder localStringBuilder;
      for (;;) {}
    }
    maxRequestsPerHost = paramInt;
    promoteCalls();
    return;
    throw localStringBuilder;
  }
}
