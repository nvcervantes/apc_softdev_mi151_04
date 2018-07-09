package com.android.volley;

import android.annotation.TargetApi;
import android.net.TrafficStats;
import android.os.Build.VERSION;
import android.os.Process;
import android.os.SystemClock;
import java.util.concurrent.BlockingQueue;

public class NetworkDispatcher
  extends Thread
{
  private final Cache mCache;
  private final ResponseDelivery mDelivery;
  private final Network mNetwork;
  private final BlockingQueue<Request<?>> mQueue;
  private volatile boolean mQuit = false;
  
  public NetworkDispatcher(BlockingQueue<Request<?>> paramBlockingQueue, Network paramNetwork, Cache paramCache, ResponseDelivery paramResponseDelivery)
  {
    mQueue = paramBlockingQueue;
    mNetwork = paramNetwork;
    mCache = paramCache;
    mDelivery = paramResponseDelivery;
  }
  
  @TargetApi(14)
  private void addTrafficStatsTag(Request<?> paramRequest)
  {
    if (Build.VERSION.SDK_INT >= 14) {
      TrafficStats.setThreadStatsTag(paramRequest.getTrafficStatsTag());
    }
  }
  
  private void parseAndDeliverNetworkError(Request<?> paramRequest, VolleyError paramVolleyError)
  {
    paramVolleyError = paramRequest.parseNetworkError(paramVolleyError);
    mDelivery.postError(paramRequest, paramVolleyError);
  }
  
  public void quit()
  {
    mQuit = true;
    interrupt();
  }
  
  public void run()
  {
    Process.setThreadPriority(10);
    do
    {
      for (;;)
      {
        long l = SystemClock.elapsedRealtime();
        try
        {
          Request localRequest = (Request)mQueue.take();
          try
          {
            localRequest.addMarker("network-queue-take");
            if (localRequest.isCanceled())
            {
              localRequest.finish("network-discard-cancelled");
            }
            else
            {
              addTrafficStatsTag(localRequest);
              Object localObject = mNetwork.performRequest(localRequest);
              localRequest.addMarker("network-http-complete");
              if ((notModified) && (localRequest.hasHadResponseDelivered()))
              {
                localRequest.finish("not-modified");
              }
              else
              {
                localObject = localRequest.parseNetworkResponse((NetworkResponse)localObject);
                localRequest.addMarker("network-parse-complete");
                if ((localRequest.shouldCache()) && (cacheEntry != null))
                {
                  mCache.put(localRequest.getCacheKey(), cacheEntry);
                  localRequest.addMarker("network-cache-written");
                }
                localRequest.markDelivered();
                mDelivery.postResponse(localRequest, (Response)localObject);
              }
            }
          }
          catch (Exception localException)
          {
            VolleyLog.e(localException, "Unhandled exception %s", new Object[] { localException.toString() });
            VolleyError localVolleyError1 = new VolleyError(localException);
            localVolleyError1.setNetworkTimeMs(SystemClock.elapsedRealtime() - l);
            mDelivery.postError(localRequest, localVolleyError1);
          }
          catch (VolleyError localVolleyError2)
          {
            localVolleyError2.setNetworkTimeMs(SystemClock.elapsedRealtime() - l);
            parseAndDeliverNetworkError(localRequest, localVolleyError2);
          }
        }
        catch (InterruptedException localInterruptedException)
        {
          for (;;) {}
        }
      }
    } while (!mQuit);
  }
}
