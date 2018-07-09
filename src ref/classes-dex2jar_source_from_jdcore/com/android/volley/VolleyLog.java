package com.android.volley;

import android.os.SystemClock;
import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class VolleyLog
{
  public static boolean DEBUG = Log.isLoggable(TAG, 2);
  public static String TAG = "Volley";
  
  public VolleyLog() {}
  
  private static String buildMessage(String paramString, Object... paramVarArgs)
  {
    if (paramVarArgs != null) {
      paramString = String.format(Locale.US, paramString, paramVarArgs);
    }
    Object localObject = new Throwable().fillInStackTrace().getStackTrace();
    String str = "<unknown>";
    int i = 2;
    for (;;)
    {
      paramVarArgs = str;
      if (i >= localObject.length) {
        break;
      }
      if (!localObject[i].getClass().equals(VolleyLog.class))
      {
        paramVarArgs = localObject[i].getClassName();
        paramVarArgs = paramVarArgs.substring(paramVarArgs.lastIndexOf('.') + 1);
        paramVarArgs = String.valueOf(String.valueOf(paramVarArgs.substring(paramVarArgs.lastIndexOf('$') + 1)));
        str = String.valueOf(String.valueOf(localObject[i].getMethodName()));
        localObject = new StringBuilder(paramVarArgs.length() + 1 + str.length());
        ((StringBuilder)localObject).append(paramVarArgs);
        ((StringBuilder)localObject).append(".");
        ((StringBuilder)localObject).append(str);
        paramVarArgs = ((StringBuilder)localObject).toString();
        break;
      }
      i += 1;
    }
    return String.format(Locale.US, "[%d] %s: %s", new Object[] { Long.valueOf(Thread.currentThread().getId()), paramVarArgs, paramString });
  }
  
  public static void d(String paramString, Object... paramVarArgs)
  {
    Log.d(TAG, buildMessage(paramString, paramVarArgs));
  }
  
  public static void e(String paramString, Object... paramVarArgs)
  {
    Log.e(TAG, buildMessage(paramString, paramVarArgs));
  }
  
  public static void e(Throwable paramThrowable, String paramString, Object... paramVarArgs)
  {
    Log.e(TAG, buildMessage(paramString, paramVarArgs), paramThrowable);
  }
  
  public static void setTag(String paramString)
  {
    d("Changing log tag to %s", new Object[] { paramString });
    TAG = paramString;
    DEBUG = Log.isLoggable(TAG, 2);
  }
  
  public static void v(String paramString, Object... paramVarArgs)
  {
    if (DEBUG) {
      Log.v(TAG, buildMessage(paramString, paramVarArgs));
    }
  }
  
  public static void wtf(String paramString, Object... paramVarArgs)
  {
    Log.wtf(TAG, buildMessage(paramString, paramVarArgs));
  }
  
  public static void wtf(Throwable paramThrowable, String paramString, Object... paramVarArgs)
  {
    Log.wtf(TAG, buildMessage(paramString, paramVarArgs), paramThrowable);
  }
  
  static class MarkerLog
  {
    public static final boolean ENABLED = VolleyLog.DEBUG;
    private static final long MIN_DURATION_FOR_LOGGING_MS = 0L;
    private boolean mFinished = false;
    private final List<Marker> mMarkers = new ArrayList();
    
    MarkerLog() {}
    
    private long getTotalDuration()
    {
      if (mMarkers.size() == 0) {
        return 0L;
      }
      long l = mMarkers.get(0)).time;
      return mMarkers.get(mMarkers.size() - 1)).time - l;
    }
    
    public void add(String paramString, long paramLong)
    {
      try
      {
        if (mFinished) {
          throw new IllegalStateException("Marker added to finished log");
        }
        mMarkers.add(new Marker(paramString, paramLong, SystemClock.elapsedRealtime()));
        return;
      }
      finally {}
    }
    
    protected void finalize()
      throws Throwable
    {
      if (!mFinished)
      {
        finish("Request on the loose");
        VolleyLog.e("Marker log finalized without finish() - uncaught exit point for request", new Object[0]);
      }
    }
    
    public void finish(String paramString)
    {
      try
      {
        mFinished = true;
        long l2 = getTotalDuration();
        if (l2 <= 0L) {
          return;
        }
        long l1 = mMarkers.get(0)).time;
        VolleyLog.d("(%-4d ms) %s", new Object[] { Long.valueOf(l2), paramString });
        paramString = mMarkers.iterator();
        while (paramString.hasNext())
        {
          Marker localMarker = (Marker)paramString.next();
          l2 = time;
          VolleyLog.d("(+%-4d) [%2d] %s", new Object[] { Long.valueOf(l2 - l1), Long.valueOf(thread), name });
          l1 = l2;
        }
        return;
      }
      finally {}
    }
    
    private static class Marker
    {
      public final String name;
      public final long thread;
      public final long time;
      
      public Marker(String paramString, long paramLong1, long paramLong2)
      {
        name = paramString;
        thread = paramLong1;
        time = paramLong2;
      }
    }
  }
}
