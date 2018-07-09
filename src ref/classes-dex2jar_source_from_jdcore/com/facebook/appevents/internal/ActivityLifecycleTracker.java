package com.facebook.appevents.internal;

import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.facebook.FacebookSdk;
import com.facebook.LoggingBehavior;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.internal.FetchedAppSettings;
import com.facebook.internal.FetchedAppSettingsManager;
import com.facebook.internal.Logger;
import com.facebook.internal.Utility;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class ActivityLifecycleTracker
{
  private static final String INCORRECT_IMPL_WARNING = "Unexpected activity pause without a matching activity resume. Logging data may be incorrect. Make sure you call activateApp from your Application's onCreate method";
  private static final long INTERRUPTION_THRESHOLD_MILLISECONDS = 1000L;
  private static final String TAG = ActivityLifecycleTracker.class.getCanonicalName();
  private static String appId;
  private static long currentActivityAppearTime;
  private static volatile ScheduledFuture currentFuture;
  private static final Object currentFutureLock = new Object();
  private static volatile SessionInfo currentSession;
  private static AtomicInteger foregroundActivityCount = new AtomicInteger(0);
  private static final ScheduledExecutorService singleThreadExecutor = Executors.newSingleThreadScheduledExecutor();
  private static AtomicBoolean tracking = new AtomicBoolean(false);
  
  public ActivityLifecycleTracker() {}
  
  private static void cancelCurrentTask()
  {
    synchronized (currentFutureLock)
    {
      if (currentFuture != null) {
        currentFuture.cancel(false);
      }
      currentFuture = null;
      return;
    }
  }
  
  public static UUID getCurrentSessionGuid()
  {
    if (currentSession != null) {
      return currentSession.getSessionId();
    }
    return null;
  }
  
  private static int getSessionTimeoutInSeconds()
  {
    FetchedAppSettings localFetchedAppSettings = FetchedAppSettingsManager.getAppSettingsWithoutQuery(FacebookSdk.getApplicationId());
    if (localFetchedAppSettings == null) {
      return Constants.getDefaultAppEventsSessionTimeoutInSeconds();
    }
    return localFetchedAppSettings.getSessionTimeoutInSeconds();
  }
  
  public static boolean isTracking()
  {
    return tracking.get();
  }
  
  public static void onActivityCreated(Activity paramActivity)
  {
    final long l = System.currentTimeMillis();
    paramActivity = new Runnable()
    {
      public void run()
      {
        if (ActivityLifecycleTracker.currentSession == null)
        {
          SessionInfo localSessionInfo = SessionInfo.getStoredSessionInfo();
          if (localSessionInfo != null) {
            SessionLogger.logDeactivateApp(val$applicationContext, val$activityName, localSessionInfo, ActivityLifecycleTracker.appId);
          }
          ActivityLifecycleTracker.access$202(new SessionInfo(Long.valueOf(l), null));
          ActivityLifecycleTracker.currentSession.setSourceApplicationInfo(val$sourceApplicationInfo);
          SessionLogger.logActivateApp(val$applicationContext, val$activityName, val$sourceApplicationInfo, ActivityLifecycleTracker.appId);
        }
      }
    };
    singleThreadExecutor.execute(paramActivity);
  }
  
  private static void onActivityPaused(Activity paramActivity)
  {
    if (foregroundActivityCount.decrementAndGet() < 0)
    {
      foregroundActivityCount.set(0);
      Log.w(TAG, "Unexpected activity pause without a matching activity resume. Logging data may be incorrect. Make sure you call activateApp from your Application's onCreate method");
    }
    cancelCurrentTask();
    paramActivity = new Runnable()
    {
      public void run()
      {
        if (ActivityLifecycleTracker.currentSession == null) {
          ActivityLifecycleTracker.access$202(new SessionInfo(Long.valueOf(val$currentTime), null));
        }
        ActivityLifecycleTracker.currentSession.setSessionLastEventTime(Long.valueOf(val$currentTime));
        if (ActivityLifecycleTracker.foregroundActivityCount.get() <= 0)
        {
          Runnable local1 = new Runnable()
          {
            public void run()
            {
              if (ActivityLifecycleTracker.foregroundActivityCount.get() <= 0)
              {
                SessionLogger.logDeactivateApp(val$applicationContext, val$activityName, ActivityLifecycleTracker.currentSession, ActivityLifecycleTracker.appId);
                SessionInfo.clearSavedSessionFromDisk();
                ActivityLifecycleTracker.access$202(null);
              }
              synchronized (ActivityLifecycleTracker.currentFutureLock)
              {
                ActivityLifecycleTracker.access$702(null);
                return;
              }
            }
          };
          synchronized (ActivityLifecycleTracker.currentFutureLock)
          {
            ActivityLifecycleTracker.access$702(ActivityLifecycleTracker.singleThreadExecutor.schedule(local1, ActivityLifecycleTracker.access$400(), TimeUnit.SECONDS));
          }
        }
        long l2 = ActivityLifecycleTracker.currentActivityAppearTime;
        long l1 = 0L;
        if (l2 > 0L) {
          l1 = (val$currentTime - l2) / 1000L;
        }
        AutomaticAnalyticsLogger.logActivityTimeSpentEvent(val$activityName, l1);
        ActivityLifecycleTracker.currentSession.writeSessionToDisk();
      }
    };
    singleThreadExecutor.execute(paramActivity);
  }
  
  public static void onActivityResumed(Activity paramActivity)
  {
    foregroundActivityCount.incrementAndGet();
    cancelCurrentTask();
    long l = System.currentTimeMillis();
    currentActivityAppearTime = l;
    paramActivity = new Runnable()
    {
      public void run()
      {
        if (ActivityLifecycleTracker.currentSession == null)
        {
          ActivityLifecycleTracker.access$202(new SessionInfo(Long.valueOf(val$currentTime), null));
          SessionLogger.logActivateApp(val$applicationContext, val$activityName, null, ActivityLifecycleTracker.appId);
        }
        else if (ActivityLifecycleTracker.currentSession.getSessionLastEventTime() != null)
        {
          long l = val$currentTime - ActivityLifecycleTracker.currentSession.getSessionLastEventTime().longValue();
          if (l > ActivityLifecycleTracker.access$400() * 1000)
          {
            SessionLogger.logDeactivateApp(val$applicationContext, val$activityName, ActivityLifecycleTracker.currentSession, ActivityLifecycleTracker.appId);
            SessionLogger.logActivateApp(val$applicationContext, val$activityName, null, ActivityLifecycleTracker.appId);
            ActivityLifecycleTracker.access$202(new SessionInfo(Long.valueOf(val$currentTime), null));
          }
          else if (l > 1000L)
          {
            ActivityLifecycleTracker.currentSession.incrementInterruptionCount();
          }
        }
        ActivityLifecycleTracker.currentSession.setSessionLastEventTime(Long.valueOf(val$currentTime));
        ActivityLifecycleTracker.currentSession.writeSessionToDisk();
      }
    };
    singleThreadExecutor.execute(paramActivity);
  }
  
  public static void startTracking(Application paramApplication, String paramString)
  {
    if (!tracking.compareAndSet(false, true)) {
      return;
    }
    appId = paramString;
    paramApplication.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks()
    {
      public void onActivityCreated(Activity paramAnonymousActivity, Bundle paramAnonymousBundle)
      {
        Logger.log(LoggingBehavior.APP_EVENTS, ActivityLifecycleTracker.TAG, "onActivityCreated");
        AppEventUtility.assertIsMainThread();
        ActivityLifecycleTracker.onActivityCreated(paramAnonymousActivity);
      }
      
      public void onActivityDestroyed(Activity paramAnonymousActivity)
      {
        Logger.log(LoggingBehavior.APP_EVENTS, ActivityLifecycleTracker.TAG, "onActivityDestroyed");
      }
      
      public void onActivityPaused(Activity paramAnonymousActivity)
      {
        Logger.log(LoggingBehavior.APP_EVENTS, ActivityLifecycleTracker.TAG, "onActivityPaused");
        AppEventUtility.assertIsMainThread();
        ActivityLifecycleTracker.onActivityPaused(paramAnonymousActivity);
      }
      
      public void onActivityResumed(Activity paramAnonymousActivity)
      {
        Logger.log(LoggingBehavior.APP_EVENTS, ActivityLifecycleTracker.TAG, "onActivityResumed");
        AppEventUtility.assertIsMainThread();
        ActivityLifecycleTracker.onActivityResumed(paramAnonymousActivity);
      }
      
      public void onActivitySaveInstanceState(Activity paramAnonymousActivity, Bundle paramAnonymousBundle)
      {
        Logger.log(LoggingBehavior.APP_EVENTS, ActivityLifecycleTracker.TAG, "onActivitySaveInstanceState");
      }
      
      public void onActivityStarted(Activity paramAnonymousActivity)
      {
        Logger.log(LoggingBehavior.APP_EVENTS, ActivityLifecycleTracker.TAG, "onActivityStarted");
      }
      
      public void onActivityStopped(Activity paramAnonymousActivity)
      {
        Logger.log(LoggingBehavior.APP_EVENTS, ActivityLifecycleTracker.TAG, "onActivityStopped");
        AppEventsLogger.onContextStop();
      }
    });
  }
}
