package com.android.volley.toolbox;

import com.android.volley.Request;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class RequestFuture<T>
  implements Future<T>, Response.Listener<T>, Response.ErrorListener
{
  private VolleyError mException;
  private Request<?> mRequest;
  private T mResult;
  private boolean mResultReceived = false;
  
  private RequestFuture() {}
  
  private T doGet(Long paramLong)
    throws InterruptedException, ExecutionException, TimeoutException
  {
    try
    {
      if (mException != null) {
        throw new ExecutionException(mException);
      }
      if (mResultReceived)
      {
        paramLong = mResult;
        return paramLong;
      }
      if (paramLong == null) {
        wait(0L);
      } else if (paramLong.longValue() > 0L) {
        wait(paramLong.longValue());
      }
      if (mException != null) {
        throw new ExecutionException(mException);
      }
      if (!mResultReceived) {
        throw new TimeoutException();
      }
      paramLong = mResult;
      return paramLong;
    }
    finally {}
  }
  
  public static <E> RequestFuture<E> newFuture()
  {
    return new RequestFuture();
  }
  
  public boolean cancel(boolean paramBoolean)
  {
    try
    {
      Request localRequest = mRequest;
      if (localRequest == null) {
        return false;
      }
      if (!isDone())
      {
        mRequest.cancel();
        return true;
      }
      return false;
    }
    finally {}
  }
  
  public T get()
    throws InterruptedException, ExecutionException
  {
    try
    {
      Object localObject = doGet(null);
      return localObject;
    }
    catch (TimeoutException localTimeoutException)
    {
      throw new AssertionError(localTimeoutException);
    }
  }
  
  public T get(long paramLong, TimeUnit paramTimeUnit)
    throws InterruptedException, ExecutionException, TimeoutException
  {
    return doGet(Long.valueOf(TimeUnit.MILLISECONDS.convert(paramLong, paramTimeUnit)));
  }
  
  public boolean isCancelled()
  {
    if (mRequest == null) {
      return false;
    }
    return mRequest.isCanceled();
  }
  
  public boolean isDone()
  {
    try
    {
      if ((!mResultReceived) && (mException == null))
      {
        bool = isCancelled();
        if (!bool)
        {
          bool = false;
          break label35;
        }
      }
      boolean bool = true;
      label35:
      return bool;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  public void onErrorResponse(VolleyError paramVolleyError)
  {
    try
    {
      mException = paramVolleyError;
      notifyAll();
      return;
    }
    finally
    {
      paramVolleyError = finally;
      throw paramVolleyError;
    }
  }
  
  public void onResponse(T paramT)
  {
    try
    {
      mResultReceived = true;
      mResult = paramT;
      notifyAll();
      return;
    }
    finally
    {
      paramT = finally;
      throw paramT;
    }
  }
  
  public void setRequest(Request<?> paramRequest)
  {
    mRequest = paramRequest;
  }
}
