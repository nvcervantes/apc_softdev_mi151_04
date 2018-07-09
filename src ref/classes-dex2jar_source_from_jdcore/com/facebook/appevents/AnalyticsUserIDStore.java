package com.facebook.appevents;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import android.util.Log;
import com.facebook.FacebookSdk;
import com.facebook.appevents.internal.AppEventUtility;
import java.util.concurrent.Executor;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

class AnalyticsUserIDStore
{
  private static final String ANALYTICS_USER_ID_KEY = "com.facebook.appevents.AnalyticsUserIDStore.userID";
  private static final String TAG = "AnalyticsUserIDStore";
  private static volatile boolean initialized = false;
  private static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
  private static String userID;
  
  AnalyticsUserIDStore() {}
  
  public static String getUserID()
  {
    if (!initialized)
    {
      Log.w(TAG, "initStore should have been called before calling setUserID");
      initAndWait();
    }
    lock.readLock().lock();
    try
    {
      String str = userID;
      return str;
    }
    finally
    {
      lock.readLock().unlock();
    }
  }
  
  private static void initAndWait()
  {
    if (initialized) {
      return;
    }
    lock.writeLock().lock();
    try
    {
      boolean bool = initialized;
      if (bool) {
        return;
      }
      userID = PreferenceManager.getDefaultSharedPreferences(FacebookSdk.getApplicationContext()).getString("com.facebook.appevents.AnalyticsUserIDStore.userID", null);
      initialized = true;
      return;
    }
    finally
    {
      lock.writeLock().unlock();
    }
  }
  
  public static void initStore()
  {
    if (initialized) {
      return;
    }
    AppEventsLogger.getAnalyticsExecutor().execute(new Runnable()
    {
      public void run() {}
    });
  }
  
  public static void setUserID(String paramString)
  {
    
    if (!initialized)
    {
      Log.w(TAG, "initStore should have been called before calling setUserID");
      initAndWait();
    }
    AppEventsLogger.getAnalyticsExecutor().execute(new Runnable()
    {
      public void run()
      {
        AnalyticsUserIDStore.lock.writeLock().lock();
        try
        {
          AnalyticsUserIDStore.access$202(val$id);
          SharedPreferences.Editor localEditor = PreferenceManager.getDefaultSharedPreferences(FacebookSdk.getApplicationContext()).edit();
          localEditor.putString("com.facebook.appevents.AnalyticsUserIDStore.userID", AnalyticsUserIDStore.userID);
          localEditor.apply();
          return;
        }
        finally
        {
          AnalyticsUserIDStore.lock.writeLock().unlock();
        }
      }
    });
  }
}
