package android.support.design.widget;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import java.lang.ref.WeakReference;

class SnackbarManager
{
  private static final int LONG_DURATION_MS = 2750;
  static final int MSG_TIMEOUT = 0;
  private static final int SHORT_DURATION_MS = 1500;
  private static SnackbarManager sSnackbarManager;
  private SnackbarRecord mCurrentSnackbar;
  private final Handler mHandler = new Handler(Looper.getMainLooper(), new Handler.Callback()
  {
    public boolean handleMessage(Message paramAnonymousMessage)
    {
      if (what != 0) {
        return false;
      }
      handleTimeout((SnackbarManager.SnackbarRecord)obj);
      return true;
    }
  });
  private final Object mLock = new Object();
  private SnackbarRecord mNextSnackbar;
  
  private SnackbarManager() {}
  
  private boolean cancelSnackbarLocked(SnackbarRecord paramSnackbarRecord, int paramInt)
  {
    Callback localCallback = (Callback)callback.get();
    if (localCallback != null)
    {
      mHandler.removeCallbacksAndMessages(paramSnackbarRecord);
      localCallback.dismiss(paramInt);
      return true;
    }
    return false;
  }
  
  static SnackbarManager getInstance()
  {
    if (sSnackbarManager == null) {
      sSnackbarManager = new SnackbarManager();
    }
    return sSnackbarManager;
  }
  
  private boolean isCurrentSnackbarLocked(Callback paramCallback)
  {
    return (mCurrentSnackbar != null) && (mCurrentSnackbar.isSnackbar(paramCallback));
  }
  
  private boolean isNextSnackbarLocked(Callback paramCallback)
  {
    return (mNextSnackbar != null) && (mNextSnackbar.isSnackbar(paramCallback));
  }
  
  private void scheduleTimeoutLocked(SnackbarRecord paramSnackbarRecord)
  {
    if (duration == -2) {
      return;
    }
    int i = 2750;
    if (duration > 0) {
      i = duration;
    } else if (duration == -1) {
      i = 1500;
    }
    mHandler.removeCallbacksAndMessages(paramSnackbarRecord);
    mHandler.sendMessageDelayed(Message.obtain(mHandler, 0, paramSnackbarRecord), i);
  }
  
  private void showNextSnackbarLocked()
  {
    if (mNextSnackbar != null)
    {
      mCurrentSnackbar = mNextSnackbar;
      mNextSnackbar = null;
      Callback localCallback = (Callback)mCurrentSnackbar.callback.get();
      if (localCallback != null)
      {
        localCallback.show();
        return;
      }
      mCurrentSnackbar = null;
    }
  }
  
  public void dismiss(Callback paramCallback, int paramInt)
  {
    synchronized (mLock)
    {
      if (isCurrentSnackbarLocked(paramCallback)) {
        cancelSnackbarLocked(mCurrentSnackbar, paramInt);
      } else if (isNextSnackbarLocked(paramCallback)) {
        cancelSnackbarLocked(mNextSnackbar, paramInt);
      }
      return;
    }
  }
  
  void handleTimeout(SnackbarRecord paramSnackbarRecord)
  {
    synchronized (mLock)
    {
      if ((mCurrentSnackbar == paramSnackbarRecord) || (mNextSnackbar == paramSnackbarRecord)) {
        cancelSnackbarLocked(paramSnackbarRecord, 2);
      }
      return;
    }
  }
  
  public boolean isCurrent(Callback paramCallback)
  {
    synchronized (mLock)
    {
      boolean bool = isCurrentSnackbarLocked(paramCallback);
      return bool;
    }
  }
  
  public boolean isCurrentOrNext(Callback paramCallback)
  {
    for (;;)
    {
      synchronized (mLock)
      {
        if (isCurrentSnackbarLocked(paramCallback)) {
          break label40;
        }
        if (isNextSnackbarLocked(paramCallback))
        {
          break label40;
          return bool;
        }
      }
      boolean bool = false;
      continue;
      label40:
      bool = true;
    }
  }
  
  public void onDismissed(Callback paramCallback)
  {
    synchronized (mLock)
    {
      if (isCurrentSnackbarLocked(paramCallback))
      {
        mCurrentSnackbar = null;
        if (mNextSnackbar != null) {
          showNextSnackbarLocked();
        }
      }
      return;
    }
  }
  
  public void onShown(Callback paramCallback)
  {
    synchronized (mLock)
    {
      if (isCurrentSnackbarLocked(paramCallback)) {
        scheduleTimeoutLocked(mCurrentSnackbar);
      }
      return;
    }
  }
  
  public void pauseTimeout(Callback paramCallback)
  {
    synchronized (mLock)
    {
      if ((isCurrentSnackbarLocked(paramCallback)) && (!mCurrentSnackbar.paused))
      {
        mCurrentSnackbar.paused = true;
        mHandler.removeCallbacksAndMessages(mCurrentSnackbar);
      }
      return;
    }
  }
  
  public void restoreTimeoutIfPaused(Callback paramCallback)
  {
    synchronized (mLock)
    {
      if ((isCurrentSnackbarLocked(paramCallback)) && (mCurrentSnackbar.paused))
      {
        mCurrentSnackbar.paused = false;
        scheduleTimeoutLocked(mCurrentSnackbar);
      }
      return;
    }
  }
  
  public void show(int paramInt, Callback paramCallback)
  {
    synchronized (mLock)
    {
      if (isCurrentSnackbarLocked(paramCallback))
      {
        mCurrentSnackbar.duration = paramInt;
        mHandler.removeCallbacksAndMessages(mCurrentSnackbar);
        scheduleTimeoutLocked(mCurrentSnackbar);
        return;
      }
      if (isNextSnackbarLocked(paramCallback)) {
        mNextSnackbar.duration = paramInt;
      } else {
        mNextSnackbar = new SnackbarRecord(paramInt, paramCallback);
      }
      if ((mCurrentSnackbar != null) && (cancelSnackbarLocked(mCurrentSnackbar, 4))) {
        return;
      }
      mCurrentSnackbar = null;
      showNextSnackbarLocked();
      return;
    }
  }
  
  static abstract interface Callback
  {
    public abstract void dismiss(int paramInt);
    
    public abstract void show();
  }
  
  private static class SnackbarRecord
  {
    final WeakReference<SnackbarManager.Callback> callback;
    int duration;
    boolean paused;
    
    SnackbarRecord(int paramInt, SnackbarManager.Callback paramCallback)
    {
      callback = new WeakReference(paramCallback);
      duration = paramInt;
    }
    
    boolean isSnackbar(SnackbarManager.Callback paramCallback)
    {
      return (paramCallback != null) && (callback.get() == paramCallback);
    }
  }
}
