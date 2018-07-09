package com.android.volley;

import android.os.Process;
import java.util.concurrent.BlockingQueue;

public class CacheDispatcher
  extends Thread
{
  private static final boolean DEBUG = VolleyLog.DEBUG;
  private final Cache mCache;
  private final BlockingQueue<Request<?>> mCacheQueue;
  private final ResponseDelivery mDelivery;
  private final BlockingQueue<Request<?>> mNetworkQueue;
  private volatile boolean mQuit = false;
  
  public CacheDispatcher(BlockingQueue<Request<?>> paramBlockingQueue1, BlockingQueue<Request<?>> paramBlockingQueue2, Cache paramCache, ResponseDelivery paramResponseDelivery)
  {
    mCacheQueue = paramBlockingQueue1;
    mNetworkQueue = paramBlockingQueue2;
    mCache = paramCache;
    mDelivery = paramResponseDelivery;
  }
  
  public void quit()
  {
    mQuit = true;
    interrupt();
  }
  
  public void run()
  {
    if (DEBUG) {
      VolleyLog.v("start new dispatcher", new Object[0]);
    }
    Process.setThreadPriority(10);
    mCache.initialize();
    do
    {
      try
      {
        for (;;)
        {
          final Request localRequest = (Request)mCacheQueue.take();
          localRequest.addMarker("cache-queue-take");
          if (localRequest.isCanceled())
          {
            localRequest.finish("cache-discard-canceled");
          }
          else
          {
            Cache.Entry localEntry = mCache.get(localRequest.getCacheKey());
            if (localEntry == null)
            {
              localRequest.addMarker("cache-miss");
              mNetworkQueue.put(localRequest);
            }
            else if (localEntry.isExpired())
            {
              localRequest.addMarker("cache-hit-expired");
              localRequest.setCacheEntry(localEntry);
              mNetworkQueue.put(localRequest);
            }
            else
            {
              localRequest.addMarker("cache-hit");
              Response localResponse = localRequest.parseNetworkResponse(new NetworkResponse(data, responseHeaders));
              localRequest.addMarker("cache-hit-parsed");
              if (!localEntry.refreshNeeded())
              {
                mDelivery.postResponse(localRequest, localResponse);
              }
              else
              {
                localRequest.addMarker("cache-hit-refresh-needed");
                localRequest.setCacheEntry(localEntry);
                intermediate = true;
                mDelivery.postResponse(localRequest, localResponse, new Runnable()
                {
                  public void run()
                  {
                    try
                    {
                      mNetworkQueue.put(localRequest);
                      return;
                    }
                    catch (InterruptedException localInterruptedException) {}
                  }
                });
              }
            }
          }
        }
      }
      catch (InterruptedException localInterruptedException)
      {
        for (;;) {}
      }
    } while (!mQuit);
  }
}
