package android.support.v4.media.session;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.IBinder.DeathRecipient;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.os.ResultReceiver;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.BundleCompat;
import android.support.v4.app.SupportActivity;
import android.support.v4.app.SupportActivity.ExtraData;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.RatingCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public final class MediaControllerCompat
{
  static final String COMMAND_ADD_QUEUE_ITEM = "android.support.v4.media.session.command.ADD_QUEUE_ITEM";
  static final String COMMAND_ADD_QUEUE_ITEM_AT = "android.support.v4.media.session.command.ADD_QUEUE_ITEM_AT";
  static final String COMMAND_ARGUMENT_INDEX = "android.support.v4.media.session.command.ARGUMENT_INDEX";
  static final String COMMAND_ARGUMENT_MEDIA_DESCRIPTION = "android.support.v4.media.session.command.ARGUMENT_MEDIA_DESCRIPTION";
  static final String COMMAND_GET_EXTRA_BINDER = "android.support.v4.media.session.command.GET_EXTRA_BINDER";
  static final String COMMAND_REMOVE_QUEUE_ITEM = "android.support.v4.media.session.command.REMOVE_QUEUE_ITEM";
  static final String COMMAND_REMOVE_QUEUE_ITEM_AT = "android.support.v4.media.session.command.REMOVE_QUEUE_ITEM_AT";
  static final String TAG = "MediaControllerCompat";
  private final MediaControllerImpl mImpl;
  private final HashSet<Callback> mRegisteredCallbacks = new HashSet();
  private final MediaSessionCompat.Token mToken;
  
  public MediaControllerCompat(Context paramContext, @NonNull MediaSessionCompat.Token paramToken)
    throws RemoteException
  {
    if (paramToken == null) {
      throw new IllegalArgumentException("sessionToken must not be null");
    }
    mToken = paramToken;
    if (Build.VERSION.SDK_INT >= 24)
    {
      mImpl = new MediaControllerImplApi24(paramContext, paramToken);
      return;
    }
    if (Build.VERSION.SDK_INT >= 23)
    {
      mImpl = new MediaControllerImplApi23(paramContext, paramToken);
      return;
    }
    if (Build.VERSION.SDK_INT >= 21)
    {
      mImpl = new MediaControllerImplApi21(paramContext, paramToken);
      return;
    }
    mImpl = new MediaControllerImplBase(mToken);
  }
  
  public MediaControllerCompat(Context paramContext, @NonNull MediaSessionCompat paramMediaSessionCompat)
  {
    if (paramMediaSessionCompat == null) {
      throw new IllegalArgumentException("session must not be null");
    }
    mToken = paramMediaSessionCompat.getSessionToken();
    if (Build.VERSION.SDK_INT >= 24)
    {
      mImpl = new MediaControllerImplApi24(paramContext, paramMediaSessionCompat);
      return;
    }
    if (Build.VERSION.SDK_INT >= 23)
    {
      mImpl = new MediaControllerImplApi23(paramContext, paramMediaSessionCompat);
      return;
    }
    if (Build.VERSION.SDK_INT >= 21)
    {
      mImpl = new MediaControllerImplApi21(paramContext, paramMediaSessionCompat);
      return;
    }
    mImpl = new MediaControllerImplBase(mToken);
  }
  
  public static MediaControllerCompat getMediaController(@NonNull Activity paramActivity)
  {
    boolean bool = paramActivity instanceof SupportActivity;
    Object localObject = null;
    if (bool)
    {
      MediaControllerExtraData localMediaControllerExtraData = (MediaControllerExtraData)((SupportActivity)paramActivity).getExtraData(MediaControllerExtraData.class);
      paramActivity = localObject;
      if (localMediaControllerExtraData != null) {
        paramActivity = localMediaControllerExtraData.getMediaController();
      }
      return paramActivity;
    }
    if (Build.VERSION.SDK_INT >= 21)
    {
      localObject = MediaControllerCompatApi21.getMediaController(paramActivity);
      if (localObject == null) {
        return null;
      }
      localObject = MediaControllerCompatApi21.getSessionToken(localObject);
      try
      {
        paramActivity = new MediaControllerCompat(paramActivity, MediaSessionCompat.Token.fromToken(localObject));
        return paramActivity;
      }
      catch (RemoteException paramActivity)
      {
        Log.e("MediaControllerCompat", "Dead object in getMediaController.", paramActivity);
      }
    }
    return null;
  }
  
  public static void setMediaController(@NonNull Activity paramActivity, MediaControllerCompat paramMediaControllerCompat)
  {
    if ((paramActivity instanceof SupportActivity)) {
      ((SupportActivity)paramActivity).putExtraData(new MediaControllerExtraData(paramMediaControllerCompat));
    }
    if (Build.VERSION.SDK_INT >= 21)
    {
      Object localObject = null;
      if (paramMediaControllerCompat != null) {
        localObject = MediaControllerCompatApi21.fromToken(paramActivity, paramMediaControllerCompat.getSessionToken().getToken());
      }
      MediaControllerCompatApi21.setMediaController(paramActivity, localObject);
    }
  }
  
  private static void validateCustomAction(String paramString, Bundle paramBundle)
  {
    if (paramString == null) {
      return;
    }
    int i = -1;
    int j = paramString.hashCode();
    if (j != -1348483723)
    {
      if ((j == 503011406) && (paramString.equals("android.support.v4.media.session.action.UNFOLLOW"))) {
        i = 1;
      }
    }
    else if (paramString.equals("android.support.v4.media.session.action.FOLLOW")) {
      i = 0;
    }
    switch (i)
    {
    default: 
      return;
    }
    if ((paramBundle == null) || (!paramBundle.containsKey("android.support.v4.media.session.ARGUMENT_MEDIA_ATTRIBUTE")))
    {
      paramBundle = new StringBuilder();
      paramBundle.append("An extra field android.support.v4.media.session.ARGUMENT_MEDIA_ATTRIBUTE is required for this action ");
      paramBundle.append(paramString);
      paramBundle.append(".");
      throw new IllegalArgumentException(paramBundle.toString());
    }
  }
  
  public void addQueueItem(MediaDescriptionCompat paramMediaDescriptionCompat)
  {
    mImpl.addQueueItem(paramMediaDescriptionCompat);
  }
  
  public void addQueueItem(MediaDescriptionCompat paramMediaDescriptionCompat, int paramInt)
  {
    mImpl.addQueueItem(paramMediaDescriptionCompat, paramInt);
  }
  
  public void adjustVolume(int paramInt1, int paramInt2)
  {
    mImpl.adjustVolume(paramInt1, paramInt2);
  }
  
  public boolean dispatchMediaButtonEvent(KeyEvent paramKeyEvent)
  {
    if (paramKeyEvent == null) {
      throw new IllegalArgumentException("KeyEvent may not be null");
    }
    return mImpl.dispatchMediaButtonEvent(paramKeyEvent);
  }
  
  public Bundle getExtras()
  {
    return mImpl.getExtras();
  }
  
  public long getFlags()
  {
    return mImpl.getFlags();
  }
  
  public Object getMediaController()
  {
    return mImpl.getMediaController();
  }
  
  public MediaMetadataCompat getMetadata()
  {
    return mImpl.getMetadata();
  }
  
  public String getPackageName()
  {
    return mImpl.getPackageName();
  }
  
  public PlaybackInfo getPlaybackInfo()
  {
    return mImpl.getPlaybackInfo();
  }
  
  public PlaybackStateCompat getPlaybackState()
  {
    return mImpl.getPlaybackState();
  }
  
  public List<MediaSessionCompat.QueueItem> getQueue()
  {
    return mImpl.getQueue();
  }
  
  public CharSequence getQueueTitle()
  {
    return mImpl.getQueueTitle();
  }
  
  public int getRatingType()
  {
    return mImpl.getRatingType();
  }
  
  public int getRepeatMode()
  {
    return mImpl.getRepeatMode();
  }
  
  public PendingIntent getSessionActivity()
  {
    return mImpl.getSessionActivity();
  }
  
  public MediaSessionCompat.Token getSessionToken()
  {
    return mToken;
  }
  
  public int getShuffleMode()
  {
    return mImpl.getShuffleMode();
  }
  
  public TransportControls getTransportControls()
  {
    return mImpl.getTransportControls();
  }
  
  public boolean isCaptioningEnabled()
  {
    return mImpl.isCaptioningEnabled();
  }
  
  public boolean isSessionReady()
  {
    return mImpl.isSessionReady();
  }
  
  public void registerCallback(@NonNull Callback paramCallback)
  {
    registerCallback(paramCallback, null);
  }
  
  public void registerCallback(@NonNull Callback paramCallback, Handler paramHandler)
  {
    if (paramCallback == null) {
      throw new IllegalArgumentException("callback must not be null");
    }
    Handler localHandler = paramHandler;
    if (paramHandler == null) {
      localHandler = new Handler();
    }
    paramCallback.setHandler(localHandler);
    mImpl.registerCallback(paramCallback, localHandler);
    mRegisteredCallbacks.add(paramCallback);
  }
  
  public void removeQueueItem(MediaDescriptionCompat paramMediaDescriptionCompat)
  {
    mImpl.removeQueueItem(paramMediaDescriptionCompat);
  }
  
  @Deprecated
  public void removeQueueItemAt(int paramInt)
  {
    Object localObject = getQueue();
    if ((localObject != null) && (paramInt >= 0) && (paramInt < ((List)localObject).size()))
    {
      localObject = (MediaSessionCompat.QueueItem)((List)localObject).get(paramInt);
      if (localObject != null) {
        removeQueueItem(((MediaSessionCompat.QueueItem)localObject).getDescription());
      }
    }
  }
  
  public void sendCommand(@NonNull String paramString, Bundle paramBundle, ResultReceiver paramResultReceiver)
  {
    if (TextUtils.isEmpty(paramString)) {
      throw new IllegalArgumentException("command must neither be null nor empty");
    }
    mImpl.sendCommand(paramString, paramBundle, paramResultReceiver);
  }
  
  public void setVolumeTo(int paramInt1, int paramInt2)
  {
    mImpl.setVolumeTo(paramInt1, paramInt2);
  }
  
  public void unregisterCallback(@NonNull Callback paramCallback)
  {
    if (paramCallback == null) {
      throw new IllegalArgumentException("callback must not be null");
    }
    try
    {
      mRegisteredCallbacks.remove(paramCallback);
      mImpl.unregisterCallback(paramCallback);
      return;
    }
    finally
    {
      paramCallback.setHandler(null);
    }
  }
  
  public static abstract class Callback
    implements IBinder.DeathRecipient
  {
    private final Object mCallbackObj;
    MessageHandler mHandler;
    boolean mHasExtraCallback;
    
    public Callback()
    {
      if (Build.VERSION.SDK_INT >= 21)
      {
        mCallbackObj = MediaControllerCompatApi21.createCallback(new StubApi21(this));
        return;
      }
      mCallbackObj = new StubCompat(this);
    }
    
    public void binderDied()
    {
      onSessionDestroyed();
    }
    
    public void onAudioInfoChanged(MediaControllerCompat.PlaybackInfo paramPlaybackInfo) {}
    
    public void onCaptioningEnabledChanged(boolean paramBoolean) {}
    
    public void onExtrasChanged(Bundle paramBundle) {}
    
    public void onMetadataChanged(MediaMetadataCompat paramMediaMetadataCompat) {}
    
    public void onPlaybackStateChanged(PlaybackStateCompat paramPlaybackStateCompat) {}
    
    public void onQueueChanged(List<MediaSessionCompat.QueueItem> paramList) {}
    
    public void onQueueTitleChanged(CharSequence paramCharSequence) {}
    
    public void onRepeatModeChanged(int paramInt) {}
    
    public void onSessionDestroyed() {}
    
    public void onSessionEvent(String paramString, Bundle paramBundle) {}
    
    public void onSessionReady() {}
    
    public void onShuffleModeChanged(int paramInt) {}
    
    void postToHandler(int paramInt, Object paramObject, Bundle paramBundle)
    {
      if (mHandler != null)
      {
        paramObject = mHandler.obtainMessage(paramInt, paramObject);
        paramObject.setData(paramBundle);
        paramObject.sendToTarget();
      }
    }
    
    void setHandler(Handler paramHandler)
    {
      if (paramHandler == null)
      {
        if (mHandler != null)
        {
          mHandler.mRegistered = false;
          mHandler.removeCallbacksAndMessages(null);
          mHandler = null;
        }
      }
      else
      {
        mHandler = new MessageHandler(paramHandler.getLooper());
        mHandler.mRegistered = true;
      }
    }
    
    private class MessageHandler
      extends Handler
    {
      private static final int MSG_DESTROYED = 8;
      private static final int MSG_EVENT = 1;
      private static final int MSG_SESSION_READY = 13;
      private static final int MSG_UPDATE_CAPTIONING_ENABLED = 11;
      private static final int MSG_UPDATE_EXTRAS = 7;
      private static final int MSG_UPDATE_METADATA = 3;
      private static final int MSG_UPDATE_PLAYBACK_STATE = 2;
      private static final int MSG_UPDATE_QUEUE = 5;
      private static final int MSG_UPDATE_QUEUE_TITLE = 6;
      private static final int MSG_UPDATE_REPEAT_MODE = 9;
      private static final int MSG_UPDATE_SHUFFLE_MODE = 12;
      private static final int MSG_UPDATE_VOLUME = 4;
      boolean mRegistered = false;
      
      MessageHandler(Looper paramLooper)
      {
        super();
      }
      
      public void handleMessage(Message paramMessage)
      {
        if (!mRegistered) {
          return;
        }
        switch (what)
        {
        case 10: 
        default: 
          return;
        case 13: 
          onSessionReady();
          return;
        case 12: 
          onShuffleModeChanged(((Integer)obj).intValue());
          return;
        case 11: 
          onCaptioningEnabledChanged(((Boolean)obj).booleanValue());
          return;
        case 9: 
          onRepeatModeChanged(((Integer)obj).intValue());
          return;
        case 8: 
          onSessionDestroyed();
          return;
        case 7: 
          onExtrasChanged((Bundle)obj);
          return;
        case 6: 
          onQueueTitleChanged((CharSequence)obj);
          return;
        case 5: 
          onQueueChanged((List)obj);
          return;
        case 4: 
          onAudioInfoChanged((MediaControllerCompat.PlaybackInfo)obj);
          return;
        case 3: 
          onMetadataChanged((MediaMetadataCompat)obj);
          return;
        case 2: 
          onPlaybackStateChanged((PlaybackStateCompat)obj);
          return;
        }
        onSessionEvent((String)obj, paramMessage.getData());
      }
    }
    
    private static class StubApi21
      implements MediaControllerCompatApi21.Callback
    {
      private final WeakReference<MediaControllerCompat.Callback> mCallback;
      
      StubApi21(MediaControllerCompat.Callback paramCallback)
      {
        mCallback = new WeakReference(paramCallback);
      }
      
      public void onAudioInfoChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
      {
        MediaControllerCompat.Callback localCallback = (MediaControllerCompat.Callback)mCallback.get();
        if (localCallback != null) {
          localCallback.onAudioInfoChanged(new MediaControllerCompat.PlaybackInfo(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5));
        }
      }
      
      public void onExtrasChanged(Bundle paramBundle)
      {
        MediaControllerCompat.Callback localCallback = (MediaControllerCompat.Callback)mCallback.get();
        if (localCallback != null) {
          localCallback.onExtrasChanged(paramBundle);
        }
      }
      
      public void onMetadataChanged(Object paramObject)
      {
        MediaControllerCompat.Callback localCallback = (MediaControllerCompat.Callback)mCallback.get();
        if (localCallback != null) {
          localCallback.onMetadataChanged(MediaMetadataCompat.fromMediaMetadata(paramObject));
        }
      }
      
      public void onPlaybackStateChanged(Object paramObject)
      {
        MediaControllerCompat.Callback localCallback = (MediaControllerCompat.Callback)mCallback.get();
        if (localCallback != null)
        {
          if (mHasExtraCallback) {
            return;
          }
          localCallback.onPlaybackStateChanged(PlaybackStateCompat.fromPlaybackState(paramObject));
        }
      }
      
      public void onQueueChanged(List<?> paramList)
      {
        MediaControllerCompat.Callback localCallback = (MediaControllerCompat.Callback)mCallback.get();
        if (localCallback != null) {
          localCallback.onQueueChanged(MediaSessionCompat.QueueItem.fromQueueItemList(paramList));
        }
      }
      
      public void onQueueTitleChanged(CharSequence paramCharSequence)
      {
        MediaControllerCompat.Callback localCallback = (MediaControllerCompat.Callback)mCallback.get();
        if (localCallback != null) {
          localCallback.onQueueTitleChanged(paramCharSequence);
        }
      }
      
      public void onSessionDestroyed()
      {
        MediaControllerCompat.Callback localCallback = (MediaControllerCompat.Callback)mCallback.get();
        if (localCallback != null) {
          localCallback.onSessionDestroyed();
        }
      }
      
      public void onSessionEvent(String paramString, Bundle paramBundle)
      {
        MediaControllerCompat.Callback localCallback = (MediaControllerCompat.Callback)mCallback.get();
        if (localCallback != null)
        {
          if ((mHasExtraCallback) && (Build.VERSION.SDK_INT < 23)) {
            return;
          }
          localCallback.onSessionEvent(paramString, paramBundle);
        }
      }
    }
    
    private static class StubCompat
      extends IMediaControllerCallback.Stub
    {
      private final WeakReference<MediaControllerCompat.Callback> mCallback;
      
      StubCompat(MediaControllerCompat.Callback paramCallback)
      {
        mCallback = new WeakReference(paramCallback);
      }
      
      public void onCaptioningEnabledChanged(boolean paramBoolean)
        throws RemoteException
      {
        MediaControllerCompat.Callback localCallback = (MediaControllerCompat.Callback)mCallback.get();
        if (localCallback != null) {
          localCallback.postToHandler(11, Boolean.valueOf(paramBoolean), null);
        }
      }
      
      public void onEvent(String paramString, Bundle paramBundle)
        throws RemoteException
      {
        MediaControllerCompat.Callback localCallback = (MediaControllerCompat.Callback)mCallback.get();
        if (localCallback != null) {
          localCallback.postToHandler(1, paramString, paramBundle);
        }
      }
      
      public void onExtrasChanged(Bundle paramBundle)
        throws RemoteException
      {
        MediaControllerCompat.Callback localCallback = (MediaControllerCompat.Callback)mCallback.get();
        if (localCallback != null) {
          localCallback.postToHandler(7, paramBundle, null);
        }
      }
      
      public void onMetadataChanged(MediaMetadataCompat paramMediaMetadataCompat)
        throws RemoteException
      {
        MediaControllerCompat.Callback localCallback = (MediaControllerCompat.Callback)mCallback.get();
        if (localCallback != null) {
          localCallback.postToHandler(3, paramMediaMetadataCompat, null);
        }
      }
      
      public void onPlaybackStateChanged(PlaybackStateCompat paramPlaybackStateCompat)
        throws RemoteException
      {
        MediaControllerCompat.Callback localCallback = (MediaControllerCompat.Callback)mCallback.get();
        if (localCallback != null) {
          localCallback.postToHandler(2, paramPlaybackStateCompat, null);
        }
      }
      
      public void onQueueChanged(List<MediaSessionCompat.QueueItem> paramList)
        throws RemoteException
      {
        MediaControllerCompat.Callback localCallback = (MediaControllerCompat.Callback)mCallback.get();
        if (localCallback != null) {
          localCallback.postToHandler(5, paramList, null);
        }
      }
      
      public void onQueueTitleChanged(CharSequence paramCharSequence)
        throws RemoteException
      {
        MediaControllerCompat.Callback localCallback = (MediaControllerCompat.Callback)mCallback.get();
        if (localCallback != null) {
          localCallback.postToHandler(6, paramCharSequence, null);
        }
      }
      
      public void onRepeatModeChanged(int paramInt)
        throws RemoteException
      {
        MediaControllerCompat.Callback localCallback = (MediaControllerCompat.Callback)mCallback.get();
        if (localCallback != null) {
          localCallback.postToHandler(9, Integer.valueOf(paramInt), null);
        }
      }
      
      public void onSessionDestroyed()
        throws RemoteException
      {
        MediaControllerCompat.Callback localCallback = (MediaControllerCompat.Callback)mCallback.get();
        if (localCallback != null) {
          localCallback.postToHandler(8, null, null);
        }
      }
      
      public void onSessionReady()
        throws RemoteException
      {
        MediaControllerCompat.Callback localCallback = (MediaControllerCompat.Callback)mCallback.get();
        if (localCallback != null) {
          localCallback.postToHandler(13, null, null);
        }
      }
      
      public void onShuffleModeChanged(int paramInt)
        throws RemoteException
      {
        MediaControllerCompat.Callback localCallback = (MediaControllerCompat.Callback)mCallback.get();
        if (localCallback != null) {
          localCallback.postToHandler(12, Integer.valueOf(paramInt), null);
        }
      }
      
      public void onShuffleModeChangedRemoved(boolean paramBoolean)
        throws RemoteException
      {}
      
      public void onVolumeInfoChanged(ParcelableVolumeInfo paramParcelableVolumeInfo)
        throws RemoteException
      {
        MediaControllerCompat.Callback localCallback = (MediaControllerCompat.Callback)mCallback.get();
        if (localCallback != null)
        {
          if (paramParcelableVolumeInfo != null) {
            paramParcelableVolumeInfo = new MediaControllerCompat.PlaybackInfo(volumeType, audioStream, controlType, maxVolume, currentVolume);
          } else {
            paramParcelableVolumeInfo = null;
          }
          localCallback.postToHandler(4, paramParcelableVolumeInfo, null);
        }
      }
    }
  }
  
  private static class MediaControllerExtraData
    extends SupportActivity.ExtraData
  {
    private final MediaControllerCompat mMediaController;
    
    MediaControllerExtraData(MediaControllerCompat paramMediaControllerCompat)
    {
      mMediaController = paramMediaControllerCompat;
    }
    
    MediaControllerCompat getMediaController()
    {
      return mMediaController;
    }
  }
  
  static abstract interface MediaControllerImpl
  {
    public abstract void addQueueItem(MediaDescriptionCompat paramMediaDescriptionCompat);
    
    public abstract void addQueueItem(MediaDescriptionCompat paramMediaDescriptionCompat, int paramInt);
    
    public abstract void adjustVolume(int paramInt1, int paramInt2);
    
    public abstract boolean dispatchMediaButtonEvent(KeyEvent paramKeyEvent);
    
    public abstract Bundle getExtras();
    
    public abstract long getFlags();
    
    public abstract Object getMediaController();
    
    public abstract MediaMetadataCompat getMetadata();
    
    public abstract String getPackageName();
    
    public abstract MediaControllerCompat.PlaybackInfo getPlaybackInfo();
    
    public abstract PlaybackStateCompat getPlaybackState();
    
    public abstract List<MediaSessionCompat.QueueItem> getQueue();
    
    public abstract CharSequence getQueueTitle();
    
    public abstract int getRatingType();
    
    public abstract int getRepeatMode();
    
    public abstract PendingIntent getSessionActivity();
    
    public abstract int getShuffleMode();
    
    public abstract MediaControllerCompat.TransportControls getTransportControls();
    
    public abstract boolean isCaptioningEnabled();
    
    public abstract boolean isSessionReady();
    
    public abstract void registerCallback(MediaControllerCompat.Callback paramCallback, Handler paramHandler);
    
    public abstract void removeQueueItem(MediaDescriptionCompat paramMediaDescriptionCompat);
    
    public abstract void sendCommand(String paramString, Bundle paramBundle, ResultReceiver paramResultReceiver);
    
    public abstract void setVolumeTo(int paramInt1, int paramInt2);
    
    public abstract void unregisterCallback(MediaControllerCompat.Callback paramCallback);
  }
  
  @RequiresApi(21)
  static class MediaControllerImplApi21
    implements MediaControllerCompat.MediaControllerImpl
  {
    private HashMap<MediaControllerCompat.Callback, ExtraCallback> mCallbackMap = new HashMap();
    protected final Object mControllerObj;
    private IMediaSession mExtraBinder;
    private final List<MediaControllerCompat.Callback> mPendingCallbacks = new ArrayList();
    
    public MediaControllerImplApi21(Context paramContext, MediaSessionCompat.Token paramToken)
      throws RemoteException
    {
      mControllerObj = MediaControllerCompatApi21.fromToken(paramContext, paramToken.getToken());
      if (mControllerObj == null) {
        throw new RemoteException();
      }
      mExtraBinder = paramToken.getExtraBinder();
      if (mExtraBinder == null) {
        requestExtraBinder();
      }
    }
    
    public MediaControllerImplApi21(Context paramContext, MediaSessionCompat paramMediaSessionCompat)
    {
      mControllerObj = MediaControllerCompatApi21.fromToken(paramContext, paramMediaSessionCompat.getSessionToken().getToken());
      mExtraBinder = paramMediaSessionCompat.getSessionToken().getExtraBinder();
      if (mExtraBinder == null) {
        requestExtraBinder();
      }
    }
    
    private void processPendingCallbacks()
    {
      if (mExtraBinder == null) {
        return;
      }
      synchronized (mPendingCallbacks)
      {
        Iterator localIterator = mPendingCallbacks.iterator();
        while (localIterator.hasNext())
        {
          MediaControllerCompat.Callback localCallback = (MediaControllerCompat.Callback)localIterator.next();
          ExtraCallback localExtraCallback = new ExtraCallback(localCallback);
          mCallbackMap.put(localCallback, localExtraCallback);
          mHasExtraCallback = true;
          try
          {
            mExtraBinder.registerCallbackListener(localExtraCallback);
            localCallback.onSessionReady();
          }
          catch (RemoteException localRemoteException)
          {
            Log.e("MediaControllerCompat", "Dead object in registerCallback.", localRemoteException);
          }
        }
        mPendingCallbacks.clear();
        return;
      }
    }
    
    private void requestExtraBinder()
    {
      sendCommand("android.support.v4.media.session.command.GET_EXTRA_BINDER", null, new ExtraBinderRequestResultReceiver(this, new Handler()));
    }
    
    public void addQueueItem(MediaDescriptionCompat paramMediaDescriptionCompat)
    {
      if ((getFlags() & 0x4) == 0L) {
        throw new UnsupportedOperationException("This session doesn't support queue management operations");
      }
      Bundle localBundle = new Bundle();
      localBundle.putParcelable("android.support.v4.media.session.command.ARGUMENT_MEDIA_DESCRIPTION", paramMediaDescriptionCompat);
      sendCommand("android.support.v4.media.session.command.ADD_QUEUE_ITEM", localBundle, null);
    }
    
    public void addQueueItem(MediaDescriptionCompat paramMediaDescriptionCompat, int paramInt)
    {
      if ((getFlags() & 0x4) == 0L) {
        throw new UnsupportedOperationException("This session doesn't support queue management operations");
      }
      Bundle localBundle = new Bundle();
      localBundle.putParcelable("android.support.v4.media.session.command.ARGUMENT_MEDIA_DESCRIPTION", paramMediaDescriptionCompat);
      localBundle.putInt("android.support.v4.media.session.command.ARGUMENT_INDEX", paramInt);
      sendCommand("android.support.v4.media.session.command.ADD_QUEUE_ITEM_AT", localBundle, null);
    }
    
    public void adjustVolume(int paramInt1, int paramInt2)
    {
      MediaControllerCompatApi21.adjustVolume(mControllerObj, paramInt1, paramInt2);
    }
    
    public boolean dispatchMediaButtonEvent(KeyEvent paramKeyEvent)
    {
      return MediaControllerCompatApi21.dispatchMediaButtonEvent(mControllerObj, paramKeyEvent);
    }
    
    public Bundle getExtras()
    {
      return MediaControllerCompatApi21.getExtras(mControllerObj);
    }
    
    public long getFlags()
    {
      return MediaControllerCompatApi21.getFlags(mControllerObj);
    }
    
    public Object getMediaController()
    {
      return mControllerObj;
    }
    
    public MediaMetadataCompat getMetadata()
    {
      Object localObject = MediaControllerCompatApi21.getMetadata(mControllerObj);
      if (localObject != null) {
        return MediaMetadataCompat.fromMediaMetadata(localObject);
      }
      return null;
    }
    
    public String getPackageName()
    {
      return MediaControllerCompatApi21.getPackageName(mControllerObj);
    }
    
    public MediaControllerCompat.PlaybackInfo getPlaybackInfo()
    {
      Object localObject = MediaControllerCompatApi21.getPlaybackInfo(mControllerObj);
      if (localObject != null) {
        return new MediaControllerCompat.PlaybackInfo(MediaControllerCompatApi21.PlaybackInfo.getPlaybackType(localObject), MediaControllerCompatApi21.PlaybackInfo.getLegacyAudioStream(localObject), MediaControllerCompatApi21.PlaybackInfo.getVolumeControl(localObject), MediaControllerCompatApi21.PlaybackInfo.getMaxVolume(localObject), MediaControllerCompatApi21.PlaybackInfo.getCurrentVolume(localObject));
      }
      return null;
    }
    
    public PlaybackStateCompat getPlaybackState()
    {
      if (mExtraBinder != null) {
        try
        {
          PlaybackStateCompat localPlaybackStateCompat = mExtraBinder.getPlaybackState();
          return localPlaybackStateCompat;
        }
        catch (RemoteException localRemoteException)
        {
          Log.e("MediaControllerCompat", "Dead object in getPlaybackState.", localRemoteException);
        }
      }
      Object localObject = MediaControllerCompatApi21.getPlaybackState(mControllerObj);
      if (localObject != null) {
        return PlaybackStateCompat.fromPlaybackState(localObject);
      }
      return null;
    }
    
    public List<MediaSessionCompat.QueueItem> getQueue()
    {
      List localList = MediaControllerCompatApi21.getQueue(mControllerObj);
      if (localList != null) {
        return MediaSessionCompat.QueueItem.fromQueueItemList(localList);
      }
      return null;
    }
    
    public CharSequence getQueueTitle()
    {
      return MediaControllerCompatApi21.getQueueTitle(mControllerObj);
    }
    
    public int getRatingType()
    {
      if ((Build.VERSION.SDK_INT < 22) && (mExtraBinder != null)) {
        try
        {
          int i = mExtraBinder.getRatingType();
          return i;
        }
        catch (RemoteException localRemoteException)
        {
          Log.e("MediaControllerCompat", "Dead object in getRatingType.", localRemoteException);
        }
      }
      return MediaControllerCompatApi21.getRatingType(mControllerObj);
    }
    
    public int getRepeatMode()
    {
      if (mExtraBinder != null) {
        try
        {
          int i = mExtraBinder.getRepeatMode();
          return i;
        }
        catch (RemoteException localRemoteException)
        {
          Log.e("MediaControllerCompat", "Dead object in getRepeatMode.", localRemoteException);
        }
      }
      return -1;
    }
    
    public PendingIntent getSessionActivity()
    {
      return MediaControllerCompatApi21.getSessionActivity(mControllerObj);
    }
    
    public int getShuffleMode()
    {
      if (mExtraBinder != null) {
        try
        {
          int i = mExtraBinder.getShuffleMode();
          return i;
        }
        catch (RemoteException localRemoteException)
        {
          Log.e("MediaControllerCompat", "Dead object in getShuffleMode.", localRemoteException);
        }
      }
      return -1;
    }
    
    public MediaControllerCompat.TransportControls getTransportControls()
    {
      Object localObject = MediaControllerCompatApi21.getTransportControls(mControllerObj);
      if (localObject != null) {
        return new MediaControllerCompat.TransportControlsApi21(localObject);
      }
      return null;
    }
    
    public boolean isCaptioningEnabled()
    {
      if (mExtraBinder != null) {
        try
        {
          boolean bool = mExtraBinder.isCaptioningEnabled();
          return bool;
        }
        catch (RemoteException localRemoteException)
        {
          Log.e("MediaControllerCompat", "Dead object in isCaptioningEnabled.", localRemoteException);
        }
      }
      return false;
    }
    
    public boolean isSessionReady()
    {
      return mExtraBinder != null;
    }
    
    public final void registerCallback(MediaControllerCompat.Callback paramCallback, Handler arg2)
    {
      MediaControllerCompatApi21.registerCallback(mControllerObj, mCallbackObj, ???);
      if (mExtraBinder != null)
      {
        ??? = new ExtraCallback(paramCallback);
        mCallbackMap.put(paramCallback, ???);
        mHasExtraCallback = true;
        try
        {
          mExtraBinder.registerCallbackListener(???);
          return;
        }
        catch (RemoteException paramCallback)
        {
          Log.e("MediaControllerCompat", "Dead object in registerCallback.", paramCallback);
          return;
        }
      }
      synchronized (mPendingCallbacks)
      {
        mHasExtraCallback = false;
        mPendingCallbacks.add(paramCallback);
        return;
      }
    }
    
    public void removeQueueItem(MediaDescriptionCompat paramMediaDescriptionCompat)
    {
      if ((getFlags() & 0x4) == 0L) {
        throw new UnsupportedOperationException("This session doesn't support queue management operations");
      }
      Bundle localBundle = new Bundle();
      localBundle.putParcelable("android.support.v4.media.session.command.ARGUMENT_MEDIA_DESCRIPTION", paramMediaDescriptionCompat);
      sendCommand("android.support.v4.media.session.command.REMOVE_QUEUE_ITEM", localBundle, null);
    }
    
    public void sendCommand(String paramString, Bundle paramBundle, ResultReceiver paramResultReceiver)
    {
      MediaControllerCompatApi21.sendCommand(mControllerObj, paramString, paramBundle, paramResultReceiver);
    }
    
    public void setVolumeTo(int paramInt1, int paramInt2)
    {
      MediaControllerCompatApi21.setVolumeTo(mControllerObj, paramInt1, paramInt2);
    }
    
    public final void unregisterCallback(MediaControllerCompat.Callback paramCallback)
    {
      MediaControllerCompatApi21.unregisterCallback(mControllerObj, mCallbackObj);
      if (mExtraBinder != null) {
        try
        {
          ??? = (ExtraCallback)mCallbackMap.remove(paramCallback);
          if (??? == null) {
            return;
          }
          mHasExtraCallback = false;
          mExtraBinder.unregisterCallbackListener((IMediaControllerCallback)???);
          return;
        }
        catch (RemoteException paramCallback)
        {
          Log.e("MediaControllerCompat", "Dead object in unregisterCallback.", paramCallback);
          return;
        }
      } else {
        synchronized (mPendingCallbacks)
        {
          mPendingCallbacks.remove(paramCallback);
          return;
        }
      }
    }
    
    private static class ExtraBinderRequestResultReceiver
      extends ResultReceiver
    {
      private WeakReference<MediaControllerCompat.MediaControllerImplApi21> mMediaControllerImpl;
      
      public ExtraBinderRequestResultReceiver(MediaControllerCompat.MediaControllerImplApi21 paramMediaControllerImplApi21, Handler paramHandler)
      {
        super();
        mMediaControllerImpl = new WeakReference(paramMediaControllerImplApi21);
      }
      
      protected void onReceiveResult(int paramInt, Bundle paramBundle)
      {
        MediaControllerCompat.MediaControllerImplApi21 localMediaControllerImplApi21 = (MediaControllerCompat.MediaControllerImplApi21)mMediaControllerImpl.get();
        if (localMediaControllerImplApi21 != null)
        {
          if (paramBundle == null) {
            return;
          }
          MediaControllerCompat.MediaControllerImplApi21.access$202(localMediaControllerImplApi21, IMediaSession.Stub.asInterface(BundleCompat.getBinder(paramBundle, "android.support.v4.media.session.EXTRA_BINDER")));
          localMediaControllerImplApi21.processPendingCallbacks();
          return;
        }
      }
    }
    
    private static class ExtraCallback
      extends MediaControllerCompat.Callback.StubCompat
    {
      ExtraCallback(MediaControllerCompat.Callback paramCallback)
      {
        super();
      }
      
      public void onExtrasChanged(Bundle paramBundle)
        throws RemoteException
      {
        throw new AssertionError();
      }
      
      public void onMetadataChanged(MediaMetadataCompat paramMediaMetadataCompat)
        throws RemoteException
      {
        throw new AssertionError();
      }
      
      public void onQueueChanged(List<MediaSessionCompat.QueueItem> paramList)
        throws RemoteException
      {
        throw new AssertionError();
      }
      
      public void onQueueTitleChanged(CharSequence paramCharSequence)
        throws RemoteException
      {
        throw new AssertionError();
      }
      
      public void onSessionDestroyed()
        throws RemoteException
      {
        throw new AssertionError();
      }
      
      public void onVolumeInfoChanged(ParcelableVolumeInfo paramParcelableVolumeInfo)
        throws RemoteException
      {
        throw new AssertionError();
      }
    }
  }
  
  @RequiresApi(23)
  static class MediaControllerImplApi23
    extends MediaControllerCompat.MediaControllerImplApi21
  {
    public MediaControllerImplApi23(Context paramContext, MediaSessionCompat.Token paramToken)
      throws RemoteException
    {
      super(paramToken);
    }
    
    public MediaControllerImplApi23(Context paramContext, MediaSessionCompat paramMediaSessionCompat)
    {
      super(paramMediaSessionCompat);
    }
    
    public MediaControllerCompat.TransportControls getTransportControls()
    {
      Object localObject = MediaControllerCompatApi21.getTransportControls(mControllerObj);
      if (localObject != null) {
        return new MediaControllerCompat.TransportControlsApi23(localObject);
      }
      return null;
    }
  }
  
  @RequiresApi(24)
  static class MediaControllerImplApi24
    extends MediaControllerCompat.MediaControllerImplApi23
  {
    public MediaControllerImplApi24(Context paramContext, MediaSessionCompat.Token paramToken)
      throws RemoteException
    {
      super(paramToken);
    }
    
    public MediaControllerImplApi24(Context paramContext, MediaSessionCompat paramMediaSessionCompat)
    {
      super(paramMediaSessionCompat);
    }
    
    public MediaControllerCompat.TransportControls getTransportControls()
    {
      Object localObject = MediaControllerCompatApi21.getTransportControls(mControllerObj);
      if (localObject != null) {
        return new MediaControllerCompat.TransportControlsApi24(localObject);
      }
      return null;
    }
  }
  
  static class MediaControllerImplBase
    implements MediaControllerCompat.MediaControllerImpl
  {
    private IMediaSession mBinder;
    private MediaControllerCompat.TransportControls mTransportControls;
    
    public MediaControllerImplBase(MediaSessionCompat.Token paramToken)
    {
      mBinder = IMediaSession.Stub.asInterface((IBinder)paramToken.getToken());
    }
    
    public void addQueueItem(MediaDescriptionCompat paramMediaDescriptionCompat)
    {
      try
      {
        if ((mBinder.getFlags() & 0x4) == 0L) {
          throw new UnsupportedOperationException("This session doesn't support queue management operations");
        }
        mBinder.addQueueItem(paramMediaDescriptionCompat);
        return;
      }
      catch (RemoteException paramMediaDescriptionCompat)
      {
        Log.e("MediaControllerCompat", "Dead object in addQueueItem.", paramMediaDescriptionCompat);
      }
    }
    
    public void addQueueItem(MediaDescriptionCompat paramMediaDescriptionCompat, int paramInt)
    {
      try
      {
        if ((mBinder.getFlags() & 0x4) == 0L) {
          throw new UnsupportedOperationException("This session doesn't support queue management operations");
        }
        mBinder.addQueueItemAt(paramMediaDescriptionCompat, paramInt);
        return;
      }
      catch (RemoteException paramMediaDescriptionCompat)
      {
        Log.e("MediaControllerCompat", "Dead object in addQueueItemAt.", paramMediaDescriptionCompat);
      }
    }
    
    public void adjustVolume(int paramInt1, int paramInt2)
    {
      try
      {
        mBinder.adjustVolume(paramInt1, paramInt2, null);
        return;
      }
      catch (RemoteException localRemoteException)
      {
        Log.e("MediaControllerCompat", "Dead object in adjustVolume.", localRemoteException);
      }
    }
    
    public boolean dispatchMediaButtonEvent(KeyEvent paramKeyEvent)
    {
      if (paramKeyEvent == null) {
        throw new IllegalArgumentException("event may not be null.");
      }
      try
      {
        mBinder.sendMediaButton(paramKeyEvent);
      }
      catch (RemoteException paramKeyEvent)
      {
        Log.e("MediaControllerCompat", "Dead object in dispatchMediaButtonEvent.", paramKeyEvent);
      }
      return false;
    }
    
    public Bundle getExtras()
    {
      try
      {
        Bundle localBundle = mBinder.getExtras();
        return localBundle;
      }
      catch (RemoteException localRemoteException)
      {
        Log.e("MediaControllerCompat", "Dead object in getExtras.", localRemoteException);
      }
      return null;
    }
    
    public long getFlags()
    {
      try
      {
        long l = mBinder.getFlags();
        return l;
      }
      catch (RemoteException localRemoteException)
      {
        Log.e("MediaControllerCompat", "Dead object in getFlags.", localRemoteException);
      }
      return 0L;
    }
    
    public Object getMediaController()
    {
      return null;
    }
    
    public MediaMetadataCompat getMetadata()
    {
      try
      {
        MediaMetadataCompat localMediaMetadataCompat = mBinder.getMetadata();
        return localMediaMetadataCompat;
      }
      catch (RemoteException localRemoteException)
      {
        Log.e("MediaControllerCompat", "Dead object in getMetadata.", localRemoteException);
      }
      return null;
    }
    
    public String getPackageName()
    {
      try
      {
        String str = mBinder.getPackageName();
        return str;
      }
      catch (RemoteException localRemoteException)
      {
        Log.e("MediaControllerCompat", "Dead object in getPackageName.", localRemoteException);
      }
      return null;
    }
    
    public MediaControllerCompat.PlaybackInfo getPlaybackInfo()
    {
      try
      {
        Object localObject = mBinder.getVolumeAttributes();
        localObject = new MediaControllerCompat.PlaybackInfo(volumeType, audioStream, controlType, maxVolume, currentVolume);
        return localObject;
      }
      catch (RemoteException localRemoteException)
      {
        Log.e("MediaControllerCompat", "Dead object in getPlaybackInfo.", localRemoteException);
      }
      return null;
    }
    
    public PlaybackStateCompat getPlaybackState()
    {
      try
      {
        PlaybackStateCompat localPlaybackStateCompat = mBinder.getPlaybackState();
        return localPlaybackStateCompat;
      }
      catch (RemoteException localRemoteException)
      {
        Log.e("MediaControllerCompat", "Dead object in getPlaybackState.", localRemoteException);
      }
      return null;
    }
    
    public List<MediaSessionCompat.QueueItem> getQueue()
    {
      try
      {
        List localList = mBinder.getQueue();
        return localList;
      }
      catch (RemoteException localRemoteException)
      {
        Log.e("MediaControllerCompat", "Dead object in getQueue.", localRemoteException);
      }
      return null;
    }
    
    public CharSequence getQueueTitle()
    {
      try
      {
        CharSequence localCharSequence = mBinder.getQueueTitle();
        return localCharSequence;
      }
      catch (RemoteException localRemoteException)
      {
        Log.e("MediaControllerCompat", "Dead object in getQueueTitle.", localRemoteException);
      }
      return null;
    }
    
    public int getRatingType()
    {
      try
      {
        int i = mBinder.getRatingType();
        return i;
      }
      catch (RemoteException localRemoteException)
      {
        Log.e("MediaControllerCompat", "Dead object in getRatingType.", localRemoteException);
      }
      return 0;
    }
    
    public int getRepeatMode()
    {
      try
      {
        int i = mBinder.getRepeatMode();
        return i;
      }
      catch (RemoteException localRemoteException)
      {
        Log.e("MediaControllerCompat", "Dead object in getRepeatMode.", localRemoteException);
      }
      return -1;
    }
    
    public PendingIntent getSessionActivity()
    {
      try
      {
        PendingIntent localPendingIntent = mBinder.getLaunchPendingIntent();
        return localPendingIntent;
      }
      catch (RemoteException localRemoteException)
      {
        Log.e("MediaControllerCompat", "Dead object in getSessionActivity.", localRemoteException);
      }
      return null;
    }
    
    public int getShuffleMode()
    {
      try
      {
        int i = mBinder.getShuffleMode();
        return i;
      }
      catch (RemoteException localRemoteException)
      {
        Log.e("MediaControllerCompat", "Dead object in getShuffleMode.", localRemoteException);
      }
      return -1;
    }
    
    public MediaControllerCompat.TransportControls getTransportControls()
    {
      if (mTransportControls == null) {
        mTransportControls = new MediaControllerCompat.TransportControlsBase(mBinder);
      }
      return mTransportControls;
    }
    
    public boolean isCaptioningEnabled()
    {
      try
      {
        boolean bool = mBinder.isCaptioningEnabled();
        return bool;
      }
      catch (RemoteException localRemoteException)
      {
        Log.e("MediaControllerCompat", "Dead object in isCaptioningEnabled.", localRemoteException);
      }
      return false;
    }
    
    public boolean isSessionReady()
    {
      return true;
    }
    
    public void registerCallback(MediaControllerCompat.Callback paramCallback, Handler paramHandler)
    {
      if (paramCallback == null) {
        throw new IllegalArgumentException("callback may not be null.");
      }
      try
      {
        mBinder.asBinder().linkToDeath(paramCallback, 0);
        mBinder.registerCallbackListener((IMediaControllerCallback)mCallbackObj);
        return;
      }
      catch (RemoteException paramHandler)
      {
        Log.e("MediaControllerCompat", "Dead object in registerCallback.", paramHandler);
        paramCallback.onSessionDestroyed();
      }
    }
    
    public void removeQueueItem(MediaDescriptionCompat paramMediaDescriptionCompat)
    {
      try
      {
        if ((mBinder.getFlags() & 0x4) == 0L) {
          throw new UnsupportedOperationException("This session doesn't support queue management operations");
        }
        mBinder.removeQueueItem(paramMediaDescriptionCompat);
        return;
      }
      catch (RemoteException paramMediaDescriptionCompat)
      {
        Log.e("MediaControllerCompat", "Dead object in removeQueueItem.", paramMediaDescriptionCompat);
      }
    }
    
    public void sendCommand(String paramString, Bundle paramBundle, ResultReceiver paramResultReceiver)
    {
      try
      {
        mBinder.sendCommand(paramString, paramBundle, new MediaSessionCompat.ResultReceiverWrapper(paramResultReceiver));
        return;
      }
      catch (RemoteException paramString)
      {
        Log.e("MediaControllerCompat", "Dead object in sendCommand.", paramString);
      }
    }
    
    public void setVolumeTo(int paramInt1, int paramInt2)
    {
      try
      {
        mBinder.setVolumeTo(paramInt1, paramInt2, null);
        return;
      }
      catch (RemoteException localRemoteException)
      {
        Log.e("MediaControllerCompat", "Dead object in setVolumeTo.", localRemoteException);
      }
    }
    
    public void unregisterCallback(MediaControllerCompat.Callback paramCallback)
    {
      if (paramCallback == null) {
        throw new IllegalArgumentException("callback may not be null.");
      }
      try
      {
        mBinder.unregisterCallbackListener((IMediaControllerCallback)mCallbackObj);
        mBinder.asBinder().unlinkToDeath(paramCallback, 0);
        return;
      }
      catch (RemoteException paramCallback)
      {
        Log.e("MediaControllerCompat", "Dead object in unregisterCallback.", paramCallback);
      }
    }
  }
  
  public static final class PlaybackInfo
  {
    public static final int PLAYBACK_TYPE_LOCAL = 1;
    public static final int PLAYBACK_TYPE_REMOTE = 2;
    private final int mAudioStream;
    private final int mCurrentVolume;
    private final int mMaxVolume;
    private final int mPlaybackType;
    private final int mVolumeControl;
    
    PlaybackInfo(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
    {
      mPlaybackType = paramInt1;
      mAudioStream = paramInt2;
      mVolumeControl = paramInt3;
      mMaxVolume = paramInt4;
      mCurrentVolume = paramInt5;
    }
    
    public int getAudioStream()
    {
      return mAudioStream;
    }
    
    public int getCurrentVolume()
    {
      return mCurrentVolume;
    }
    
    public int getMaxVolume()
    {
      return mMaxVolume;
    }
    
    public int getPlaybackType()
    {
      return mPlaybackType;
    }
    
    public int getVolumeControl()
    {
      return mVolumeControl;
    }
  }
  
  public static abstract class TransportControls
  {
    public static final String EXTRA_LEGACY_STREAM_TYPE = "android.media.session.extra.LEGACY_STREAM_TYPE";
    
    TransportControls() {}
    
    public abstract void fastForward();
    
    public abstract void pause();
    
    public abstract void play();
    
    public abstract void playFromMediaId(String paramString, Bundle paramBundle);
    
    public abstract void playFromSearch(String paramString, Bundle paramBundle);
    
    public abstract void playFromUri(Uri paramUri, Bundle paramBundle);
    
    public abstract void prepare();
    
    public abstract void prepareFromMediaId(String paramString, Bundle paramBundle);
    
    public abstract void prepareFromSearch(String paramString, Bundle paramBundle);
    
    public abstract void prepareFromUri(Uri paramUri, Bundle paramBundle);
    
    public abstract void rewind();
    
    public abstract void seekTo(long paramLong);
    
    public abstract void sendCustomAction(PlaybackStateCompat.CustomAction paramCustomAction, Bundle paramBundle);
    
    public abstract void sendCustomAction(String paramString, Bundle paramBundle);
    
    public abstract void setCaptioningEnabled(boolean paramBoolean);
    
    public abstract void setRating(RatingCompat paramRatingCompat);
    
    public abstract void setRating(RatingCompat paramRatingCompat, Bundle paramBundle);
    
    public abstract void setRepeatMode(int paramInt);
    
    public abstract void setShuffleMode(int paramInt);
    
    public abstract void skipToNext();
    
    public abstract void skipToPrevious();
    
    public abstract void skipToQueueItem(long paramLong);
    
    public abstract void stop();
  }
  
  static class TransportControlsApi21
    extends MediaControllerCompat.TransportControls
  {
    protected final Object mControlsObj;
    
    public TransportControlsApi21(Object paramObject)
    {
      mControlsObj = paramObject;
    }
    
    public void fastForward()
    {
      MediaControllerCompatApi21.TransportControls.fastForward(mControlsObj);
    }
    
    public void pause()
    {
      MediaControllerCompatApi21.TransportControls.pause(mControlsObj);
    }
    
    public void play()
    {
      MediaControllerCompatApi21.TransportControls.play(mControlsObj);
    }
    
    public void playFromMediaId(String paramString, Bundle paramBundle)
    {
      MediaControllerCompatApi21.TransportControls.playFromMediaId(mControlsObj, paramString, paramBundle);
    }
    
    public void playFromSearch(String paramString, Bundle paramBundle)
    {
      MediaControllerCompatApi21.TransportControls.playFromSearch(mControlsObj, paramString, paramBundle);
    }
    
    public void playFromUri(Uri paramUri, Bundle paramBundle)
    {
      if ((paramUri != null) && (!Uri.EMPTY.equals(paramUri)))
      {
        Bundle localBundle = new Bundle();
        localBundle.putParcelable("android.support.v4.media.session.action.ARGUMENT_URI", paramUri);
        localBundle.putParcelable("android.support.v4.media.session.action.ARGUMENT_EXTRAS", paramBundle);
        sendCustomAction("android.support.v4.media.session.action.PLAY_FROM_URI", localBundle);
        return;
      }
      throw new IllegalArgumentException("You must specify a non-empty Uri for playFromUri.");
    }
    
    public void prepare()
    {
      sendCustomAction("android.support.v4.media.session.action.PREPARE", null);
    }
    
    public void prepareFromMediaId(String paramString, Bundle paramBundle)
    {
      Bundle localBundle = new Bundle();
      localBundle.putString("android.support.v4.media.session.action.ARGUMENT_MEDIA_ID", paramString);
      localBundle.putBundle("android.support.v4.media.session.action.ARGUMENT_EXTRAS", paramBundle);
      sendCustomAction("android.support.v4.media.session.action.PREPARE_FROM_MEDIA_ID", localBundle);
    }
    
    public void prepareFromSearch(String paramString, Bundle paramBundle)
    {
      Bundle localBundle = new Bundle();
      localBundle.putString("android.support.v4.media.session.action.ARGUMENT_QUERY", paramString);
      localBundle.putBundle("android.support.v4.media.session.action.ARGUMENT_EXTRAS", paramBundle);
      sendCustomAction("android.support.v4.media.session.action.PREPARE_FROM_SEARCH", localBundle);
    }
    
    public void prepareFromUri(Uri paramUri, Bundle paramBundle)
    {
      Bundle localBundle = new Bundle();
      localBundle.putParcelable("android.support.v4.media.session.action.ARGUMENT_URI", paramUri);
      localBundle.putBundle("android.support.v4.media.session.action.ARGUMENT_EXTRAS", paramBundle);
      sendCustomAction("android.support.v4.media.session.action.PREPARE_FROM_URI", localBundle);
    }
    
    public void rewind()
    {
      MediaControllerCompatApi21.TransportControls.rewind(mControlsObj);
    }
    
    public void seekTo(long paramLong)
    {
      MediaControllerCompatApi21.TransportControls.seekTo(mControlsObj, paramLong);
    }
    
    public void sendCustomAction(PlaybackStateCompat.CustomAction paramCustomAction, Bundle paramBundle)
    {
      MediaControllerCompat.validateCustomAction(paramCustomAction.getAction(), paramBundle);
      MediaControllerCompatApi21.TransportControls.sendCustomAction(mControlsObj, paramCustomAction.getAction(), paramBundle);
    }
    
    public void sendCustomAction(String paramString, Bundle paramBundle)
    {
      MediaControllerCompat.validateCustomAction(paramString, paramBundle);
      MediaControllerCompatApi21.TransportControls.sendCustomAction(mControlsObj, paramString, paramBundle);
    }
    
    public void setCaptioningEnabled(boolean paramBoolean)
    {
      Bundle localBundle = new Bundle();
      localBundle.putBoolean("android.support.v4.media.session.action.ARGUMENT_CAPTIONING_ENABLED", paramBoolean);
      sendCustomAction("android.support.v4.media.session.action.SET_CAPTIONING_ENABLED", localBundle);
    }
    
    public void setRating(RatingCompat paramRatingCompat)
    {
      Object localObject = mControlsObj;
      if (paramRatingCompat != null) {
        paramRatingCompat = paramRatingCompat.getRating();
      } else {
        paramRatingCompat = null;
      }
      MediaControllerCompatApi21.TransportControls.setRating(localObject, paramRatingCompat);
    }
    
    public void setRating(RatingCompat paramRatingCompat, Bundle paramBundle)
    {
      Bundle localBundle = new Bundle();
      localBundle.putParcelable("android.support.v4.media.session.action.ARGUMENT_RATING", paramRatingCompat);
      localBundle.putParcelable("android.support.v4.media.session.action.ARGUMENT_EXTRAS", paramBundle);
      sendCustomAction("android.support.v4.media.session.action.SET_RATING", localBundle);
    }
    
    public void setRepeatMode(int paramInt)
    {
      Bundle localBundle = new Bundle();
      localBundle.putInt("android.support.v4.media.session.action.ARGUMENT_REPEAT_MODE", paramInt);
      sendCustomAction("android.support.v4.media.session.action.SET_REPEAT_MODE", localBundle);
    }
    
    public void setShuffleMode(int paramInt)
    {
      Bundle localBundle = new Bundle();
      localBundle.putInt("android.support.v4.media.session.action.ARGUMENT_SHUFFLE_MODE", paramInt);
      sendCustomAction("android.support.v4.media.session.action.SET_SHUFFLE_MODE", localBundle);
    }
    
    public void skipToNext()
    {
      MediaControllerCompatApi21.TransportControls.skipToNext(mControlsObj);
    }
    
    public void skipToPrevious()
    {
      MediaControllerCompatApi21.TransportControls.skipToPrevious(mControlsObj);
    }
    
    public void skipToQueueItem(long paramLong)
    {
      MediaControllerCompatApi21.TransportControls.skipToQueueItem(mControlsObj, paramLong);
    }
    
    public void stop()
    {
      MediaControllerCompatApi21.TransportControls.stop(mControlsObj);
    }
  }
  
  @RequiresApi(23)
  static class TransportControlsApi23
    extends MediaControllerCompat.TransportControlsApi21
  {
    public TransportControlsApi23(Object paramObject)
    {
      super();
    }
    
    public void playFromUri(Uri paramUri, Bundle paramBundle)
    {
      MediaControllerCompatApi23.TransportControls.playFromUri(mControlsObj, paramUri, paramBundle);
    }
  }
  
  @RequiresApi(24)
  static class TransportControlsApi24
    extends MediaControllerCompat.TransportControlsApi23
  {
    public TransportControlsApi24(Object paramObject)
    {
      super();
    }
    
    public void prepare()
    {
      MediaControllerCompatApi24.TransportControls.prepare(mControlsObj);
    }
    
    public void prepareFromMediaId(String paramString, Bundle paramBundle)
    {
      MediaControllerCompatApi24.TransportControls.prepareFromMediaId(mControlsObj, paramString, paramBundle);
    }
    
    public void prepareFromSearch(String paramString, Bundle paramBundle)
    {
      MediaControllerCompatApi24.TransportControls.prepareFromSearch(mControlsObj, paramString, paramBundle);
    }
    
    public void prepareFromUri(Uri paramUri, Bundle paramBundle)
    {
      MediaControllerCompatApi24.TransportControls.prepareFromUri(mControlsObj, paramUri, paramBundle);
    }
  }
  
  static class TransportControlsBase
    extends MediaControllerCompat.TransportControls
  {
    private IMediaSession mBinder;
    
    public TransportControlsBase(IMediaSession paramIMediaSession)
    {
      mBinder = paramIMediaSession;
    }
    
    public void fastForward()
    {
      try
      {
        mBinder.fastForward();
        return;
      }
      catch (RemoteException localRemoteException)
      {
        Log.e("MediaControllerCompat", "Dead object in fastForward.", localRemoteException);
      }
    }
    
    public void pause()
    {
      try
      {
        mBinder.pause();
        return;
      }
      catch (RemoteException localRemoteException)
      {
        Log.e("MediaControllerCompat", "Dead object in pause.", localRemoteException);
      }
    }
    
    public void play()
    {
      try
      {
        mBinder.play();
        return;
      }
      catch (RemoteException localRemoteException)
      {
        Log.e("MediaControllerCompat", "Dead object in play.", localRemoteException);
      }
    }
    
    public void playFromMediaId(String paramString, Bundle paramBundle)
    {
      try
      {
        mBinder.playFromMediaId(paramString, paramBundle);
        return;
      }
      catch (RemoteException paramString)
      {
        Log.e("MediaControllerCompat", "Dead object in playFromMediaId.", paramString);
      }
    }
    
    public void playFromSearch(String paramString, Bundle paramBundle)
    {
      try
      {
        mBinder.playFromSearch(paramString, paramBundle);
        return;
      }
      catch (RemoteException paramString)
      {
        Log.e("MediaControllerCompat", "Dead object in playFromSearch.", paramString);
      }
    }
    
    public void playFromUri(Uri paramUri, Bundle paramBundle)
    {
      try
      {
        mBinder.playFromUri(paramUri, paramBundle);
        return;
      }
      catch (RemoteException paramUri)
      {
        Log.e("MediaControllerCompat", "Dead object in playFromUri.", paramUri);
      }
    }
    
    public void prepare()
    {
      try
      {
        mBinder.prepare();
        return;
      }
      catch (RemoteException localRemoteException)
      {
        Log.e("MediaControllerCompat", "Dead object in prepare.", localRemoteException);
      }
    }
    
    public void prepareFromMediaId(String paramString, Bundle paramBundle)
    {
      try
      {
        mBinder.prepareFromMediaId(paramString, paramBundle);
        return;
      }
      catch (RemoteException paramString)
      {
        Log.e("MediaControllerCompat", "Dead object in prepareFromMediaId.", paramString);
      }
    }
    
    public void prepareFromSearch(String paramString, Bundle paramBundle)
    {
      try
      {
        mBinder.prepareFromSearch(paramString, paramBundle);
        return;
      }
      catch (RemoteException paramString)
      {
        Log.e("MediaControllerCompat", "Dead object in prepareFromSearch.", paramString);
      }
    }
    
    public void prepareFromUri(Uri paramUri, Bundle paramBundle)
    {
      try
      {
        mBinder.prepareFromUri(paramUri, paramBundle);
        return;
      }
      catch (RemoteException paramUri)
      {
        Log.e("MediaControllerCompat", "Dead object in prepareFromUri.", paramUri);
      }
    }
    
    public void rewind()
    {
      try
      {
        mBinder.rewind();
        return;
      }
      catch (RemoteException localRemoteException)
      {
        Log.e("MediaControllerCompat", "Dead object in rewind.", localRemoteException);
      }
    }
    
    public void seekTo(long paramLong)
    {
      try
      {
        mBinder.seekTo(paramLong);
        return;
      }
      catch (RemoteException localRemoteException)
      {
        Log.e("MediaControllerCompat", "Dead object in seekTo.", localRemoteException);
      }
    }
    
    public void sendCustomAction(PlaybackStateCompat.CustomAction paramCustomAction, Bundle paramBundle)
    {
      sendCustomAction(paramCustomAction.getAction(), paramBundle);
    }
    
    public void sendCustomAction(String paramString, Bundle paramBundle)
    {
      MediaControllerCompat.validateCustomAction(paramString, paramBundle);
      try
      {
        mBinder.sendCustomAction(paramString, paramBundle);
        return;
      }
      catch (RemoteException paramString)
      {
        Log.e("MediaControllerCompat", "Dead object in sendCustomAction.", paramString);
      }
    }
    
    public void setCaptioningEnabled(boolean paramBoolean)
    {
      try
      {
        mBinder.setCaptioningEnabled(paramBoolean);
        return;
      }
      catch (RemoteException localRemoteException)
      {
        Log.e("MediaControllerCompat", "Dead object in setCaptioningEnabled.", localRemoteException);
      }
    }
    
    public void setRating(RatingCompat paramRatingCompat)
    {
      try
      {
        mBinder.rate(paramRatingCompat);
        return;
      }
      catch (RemoteException paramRatingCompat)
      {
        Log.e("MediaControllerCompat", "Dead object in setRating.", paramRatingCompat);
      }
    }
    
    public void setRating(RatingCompat paramRatingCompat, Bundle paramBundle)
    {
      try
      {
        mBinder.rateWithExtras(paramRatingCompat, paramBundle);
        return;
      }
      catch (RemoteException paramRatingCompat)
      {
        Log.e("MediaControllerCompat", "Dead object in setRating.", paramRatingCompat);
      }
    }
    
    public void setRepeatMode(int paramInt)
    {
      try
      {
        mBinder.setRepeatMode(paramInt);
        return;
      }
      catch (RemoteException localRemoteException)
      {
        Log.e("MediaControllerCompat", "Dead object in setRepeatMode.", localRemoteException);
      }
    }
    
    public void setShuffleMode(int paramInt)
    {
      try
      {
        mBinder.setShuffleMode(paramInt);
        return;
      }
      catch (RemoteException localRemoteException)
      {
        Log.e("MediaControllerCompat", "Dead object in setShuffleMode.", localRemoteException);
      }
    }
    
    public void skipToNext()
    {
      try
      {
        mBinder.next();
        return;
      }
      catch (RemoteException localRemoteException)
      {
        Log.e("MediaControllerCompat", "Dead object in skipToNext.", localRemoteException);
      }
    }
    
    public void skipToPrevious()
    {
      try
      {
        mBinder.previous();
        return;
      }
      catch (RemoteException localRemoteException)
      {
        Log.e("MediaControllerCompat", "Dead object in skipToPrevious.", localRemoteException);
      }
    }
    
    public void skipToQueueItem(long paramLong)
    {
      try
      {
        mBinder.skipToQueueItem(paramLong);
        return;
      }
      catch (RemoteException localRemoteException)
      {
        Log.e("MediaControllerCompat", "Dead object in skipToQueueItem.", localRemoteException);
      }
    }
    
    public void stop()
    {
      try
      {
        mBinder.stop();
        return;
      }
      catch (RemoteException localRemoteException)
      {
        Log.e("MediaControllerCompat", "Dead object in stop.", localRemoteException);
      }
    }
  }
}
