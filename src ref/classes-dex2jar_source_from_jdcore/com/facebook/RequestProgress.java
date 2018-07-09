package com.facebook;

import android.os.Handler;

class RequestProgress
{
  private final Handler callbackHandler;
  private long lastReportedProgress;
  private long maxProgress;
  private long progress;
  private final GraphRequest request;
  private final long threshold;
  
  RequestProgress(Handler paramHandler, GraphRequest paramGraphRequest)
  {
    request = paramGraphRequest;
    callbackHandler = paramHandler;
    threshold = FacebookSdk.getOnProgressThreshold();
  }
  
  void addProgress(long paramLong)
  {
    progress += paramLong;
    if ((progress >= lastReportedProgress + threshold) || (progress >= maxProgress)) {
      reportProgress();
    }
  }
  
  void addToMax(long paramLong)
  {
    maxProgress += paramLong;
  }
  
  long getMaxProgress()
  {
    return maxProgress;
  }
  
  long getProgress()
  {
    return progress;
  }
  
  void reportProgress()
  {
    if (progress > lastReportedProgress)
    {
      Object localObject = request.getCallback();
      if ((maxProgress > 0L) && ((localObject instanceof GraphRequest.OnProgressCallback)))
      {
        final long l1 = progress;
        long l2 = maxProgress;
        localObject = (GraphRequest.OnProgressCallback)localObject;
        if (callbackHandler == null) {
          ((GraphRequest.OnProgressCallback)localObject).onProgress(l1, l2);
        } else {
          callbackHandler.post(new Runnable()
          {
            public void run()
            {
              val$callbackCopy.onProgress(l1, val$maxProgressCopy);
            }
          });
        }
        lastReportedProgress = progress;
      }
    }
  }
}
